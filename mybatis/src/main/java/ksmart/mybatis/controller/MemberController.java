package ksmart.mybatis.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ksmart.mybatis.dto.Member;
import ksmart.mybatis.dto.MemberLevel;
import ksmart.mybatis.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	
	private MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@PostConstruct
	public void memberControllerInit() {
		log.info("memberController 생성");
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		//세션 객체 초기화
		session.invalidate();
		
		return "redirect:/member/login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam(name="memberId") String memberId
						,@RequestParam(name="memberPw") String memberPw
						,RedirectAttributes reAttr
						,HttpSession session
						,HttpServletRequest request) {
		
		log.info("memberId ::: {}", memberId);
		log.info("memberPw ::: {}", memberPw);
		

		Member member = memberService.getMemberInfoById(memberId);
		
		if(member != null) {
			
			String checkPw = member.getMemberPw();
			
			if(memberPw != null && checkPw.equals(memberPw)) {
				session.setAttribute("SID", memberId);
				session.setAttribute("SNAME", member.getMemberName());
				session.setAttribute("SLEVEL", member.getMemberLevel());
				//1. 회원의 정보가 일치되면x
				return "redirect:/";
			}	
		}
		
		//2. 회원의 정보가 일치하지 않으면
		reAttr.addAttribute("msg", "회원의 정보가 일치하지 않습니다.");
		return "redirect:/member/login";
	}
	
	@GetMapping("/login")
	public String login(Model model
						,@RequestParam(value="msg", required = false) String msg) {
		
		model.addAttribute("title", "로그인 화면");
		if(msg != null) model.addAttribute("msg", msg);
		
		return "login/login";
	}
	
	@PostMapping("/removeMember")
	public String removeMember(@RequestParam(name="memberId") String memberId
							  ,@RequestParam(name="memberPw") String memberPw
							  ,RedirectAttributes reAttr) {
		
		//비밀번호 잘못되었다
		Member member = memberService.getMemberInfoById(memberId);
		
		if(member != null) {
			String checkPw = member.getMemberPw();
			int memberLevel = member.getMemberLevel();
			if(checkPw.equals(memberPw)) {
				int resultRemove = memberService.removeMember(memberId, memberLevel);
				
				if(resultRemove > 0) return "redirect:/member/memberList";
			}
		}
		
		reAttr.addAttribute("msg", "회원의 정보가 일치하지 않습니다.");
		
		return "redirect:/member/remove/" + memberId; 
	}
	
	/**
	 * @path /remove/{memberId}
	 * @PathVariable value(path 변수로 선언했던 변수명) memberId
	 * @param model
	 * @return String (@Controller 선언한 클래스에서는 논리주소, 
	 * 					@RestController (controller와 @ResponseBody가 합쳐진 것) 선언한 클래스에서는 data)
	 */
	@GetMapping("/remove/{memberId}")
	public String removeMember(@PathVariable(value = "memberId") String memberId
							  ,@RequestParam(value = "msg", required = false) String msg
								,Model model) {
		
		model.addAttribute("title", "회원탈퇴");
		model.addAttribute("memberId", memberId);
		if(msg != null) model.addAttribute("msg", msg);
		
		return "member/removeMember";
	}
	
	@PostMapping("/modifyMember")
	public String modifyMember(Member member) {
		
		memberService.modifyMember(member);
		
		return "redirect:/member/memberList";
	}
	
	@GetMapping("/modifyMember")
	public String modifyMember(@RequestParam(value="memberId", required = false) String memberId
							  ,Model model) {
		
		//특정회원의 정보
		Member memberInfo = memberService.getMemberInfoById(memberId);
		//회원등급
		List<MemberLevel> memberLevelList = memberService.getMemberLevelList();
		//model 셋팅
		model.addAttribute("title", "회원수정");
		model.addAttribute("memberInfo", memberInfo);
		model.addAttribute("memberLevelList", memberLevelList);
		
		return "member/modifyMember";
	}
	
	@GetMapping("/idCheck")
	@ResponseBody
	public boolean idCheck(@RequestParam(name="memberId") String memberId) {
		
		boolean result = memberService.idCheck(memberId);
		
		return result;
	}
	
	/**
	 * memberId : "사용자입력값" -> Member member = new Member();
	 * -> String memberId = request.getParameter("memberId");
	 * -> member.setMemberId(memberId);
	 * @param member
	 * @return String ( "redirect:/" -> 주소요청, response.sendRedirect("주소"))
	 */
	@PostMapping("/addMember")
	public String addMember(Member member) {
		
		log.info("사용자가 입력한 회원의 정보 ::: {}", member);
		memberService.addMember(member);

		return "redirect:/member/memberList";
	}

	@GetMapping("/addMember")
	public String addMemberForm(Model model) {
		List<MemberLevel> memberLevelList = memberService.getMemberLevelList();
		
		model.addAttribute("title", "회원가입");
		model.addAttribute("memberLevelList", memberLevelList);
		
		return "member/addMember";
	}
	
	/**
	 * 회원 목록조회
	 * @param model
	 * @return
	 */
	@GetMapping("/memberList")
	public String getMemberList(Model model) {
		List<Member> memberList = memberService.getMemberList();
		
		model.addAttribute("memberList", memberList);
		
		return "member/memberList";
	}
}
