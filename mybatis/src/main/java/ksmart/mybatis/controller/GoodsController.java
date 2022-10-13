package ksmart.mybatis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ksmart.mybatis.dto.Goods;
import ksmart.mybatis.dto.Member;
import ksmart.mybatis.service.GoodsService;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	
	private final GoodsService goodsService;
	
	private static final Logger log = LoggerFactory.getLogger(GoodsController.class); 
	
	public GoodsController(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	
	@PostConstruct
	public void goodsControllerInit() {
		log.info("GoodsController bean 생성");
	}
	
	@GetMapping("/removeGoods")
	public String removeGoods(@RequestParam(name="memberId") String memberId) {
		
		
		
		
		return "goods/goodsList";
	}
	
	@PostMapping("/sellerList")
	public String getSearchSellerList(@RequestParam(name="searchKey", defaultValue = "memberId") String sk
									 ,@RequestParam(name="searchValue", required = false, defaultValue = "") String sv
									 ,Model model) {
		// 1. searchKey -> memberId, memberName...이런 식으로 들어옴 -> DB에 있는 컬럼명과 매칭되게 처리
		if("memberId".equals(sk)) {
			sk = "m_id";
		}else if("memberName".equals(sk)) {
			sk = "m_name";
		}else if("memberEmail".equals(sk)) {
			sk = "m_email";
		}else {
			sk = "g_name";
		}
			
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sk", sk);
		paramMap.put("sv", sv);
		// 2. ${} vs #{} => ${searchKey} LIKE #{searchValue} : ex) memberId LIKE 'id001'
		List<Member> sellerList = goodsService.getSearchSellerList(paramMap);
			
		// 3. model 검색된 리스트를 출력하면 됩니다.
		model.addAttribute("title", "팬매자 목록조회");
		model.addAttribute("sellerList", sellerList);
		
		return "goods/sellerList";
	}
	
	@GetMapping("/sellerList")
	public String getSellerList(Model model) {
		List<Member> sellerList = goodsService.getSearchSellerList(null);
		log.info("판매자 목록 조회 ::: {}", sellerList);
		model.addAttribute("title", "판매자 목록조회");
		model.addAttribute("sellerList", sellerList);
		return "goods/sellerList";
	}
	
	@PostMapping("/addGoods")
	public String addGoods(Goods goods) {
		
		goodsService.addGoods(goods);
		
		return "redirect:/goods/goodsList";
	}
	
	@GetMapping("/addGoods")
	public String addGoods(Model model) {
		
		//판매자 조회
		List<Member> sellerList = goodsService.getSellerList();
		
		model.addAttribute("title", "상품등록");
		model.addAttribute("sellerList", sellerList);
		
		return "goods/addGoods";
	}
	
	@GetMapping("/goodsList")
	public String getGoodsList(Model model) {
		List<Goods> goodsList = goodsService.getGoodsList();
		
		model.addAttribute("title", "상품목록");
		model.addAttribute("goodsList", goodsList);
		
		return "goods/goodsList";
	}
}
