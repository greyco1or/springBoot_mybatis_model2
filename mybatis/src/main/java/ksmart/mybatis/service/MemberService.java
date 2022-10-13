package ksmart.mybatis.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.mybatis.dto.Member;
import ksmart.mybatis.dto.MemberLevel;
import ksmart.mybatis.mapper.MemberMapper;

@Service
@Transactional
public class MemberService {

	// 의존성 주입
	private MemberMapper memberMapper;
	
	public MemberService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	@PostConstruct
	public void memberServiceInit() {
		System.out.println("memberService bean 생성");
	}
	
	// 회원 탈퇴
	public int removeMember(String memberId, int memberLevel) {
		int resultRemove = 0;
		//권한별 삭제
		//판매자(1. 등록된 상품에 대한 주문이력삭제, 2. 등록한 상품삭제)
		if(memberLevel == 2) {
			resultRemove += memberMapper.removeOrderByGoodsCode(memberId);
			resultRemove += memberMapper.removeGoodsBySellerId(memberId);
			
		}
		//구매자(주문이력 삭제)
		if(memberLevel == 3) {
			resultRemove += memberMapper.removeOrderByOrderId(memberId);
		}
		//공통(1. 로그인 이력삭제, 2. 회원탈퇴)
		resultRemove += memberMapper.removeLoginHistory(memberId);
		
		resultRemove += memberMapper.removeMember(memberId);
		return resultRemove;
	}
	
	// 회원 수정
	public void modifyMember(Member member) {
		memberMapper.modifyMember(member);
	}
	
	// 특정회원 조회
	public Member getMemberInfoById(String memberId) {
		
		Member member = memberMapper.getMemberInfoById(memberId);
		
		return member;
	}
	
	//회원아이디 중복체크
	public boolean idCheck(String memberId) {
		
		boolean result = memberMapper.idCheck(memberId);
		
		return result;
	}
	
	//회원가입
	public void addMember(Member member) {
		
		int result = memberMapper.addMember(member);
		
		System.out.println("회원가입 결과 : " + result);
	
	}
	
	public List<MemberLevel> getMemberLevelList(){
		
		List<MemberLevel> memberLevelList = memberMapper.getMemberLevelList();
		
		return memberLevelList;
	}
	
	public List<Member> getMemberList(){
		
		List<Member> memberList = memberMapper.getMemberList();
		
		if(memberList != null) {
			//향상된 for문
			for(Member member : memberList) {
				int memberLevel = member.getMemberLevel();
				
				if(memberLevel == 1) {
					member.setMemberLevelName("관리자");
				}else if(memberLevel == 2) {
					member.setMemberLevelName("판매자");
				}else if(memberLevel == 3) {
					member.setMemberLevelName("구매자");
				}else {
					member.setMemberLevelName("회원");
				}
			}
		}
		return memberList;
	}
	
}
