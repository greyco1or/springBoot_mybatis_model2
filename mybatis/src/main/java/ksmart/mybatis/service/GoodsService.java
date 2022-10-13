package ksmart.mybatis.service;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.mybatis.dto.Goods;
import ksmart.mybatis.dto.Member;
import ksmart.mybatis.mapper.GoodsMapper;
import ksmart.mybatis.mapper.MemberMapper;

@Service
@Transactional
public class GoodsService {
	
	
	private static final Logger log = LoggerFactory.getLogger(GoodsService.class);

	//DI 의존성 주입
	private final GoodsMapper goodsMapper;
	private final MemberMapper memberMapper;
	
	public GoodsService(GoodsMapper goodsMapper, MemberMapper memberMapper) {
		this.goodsMapper = goodsMapper;
		this.memberMapper = memberMapper;
	}
	
	@PostConstruct
	public void goodsServiceInit() {
		log.info("goodsService bean 생성");
	}
	
	//판매자 목록조회
	public List<Member> getSearchSellerList(Map<String,Object> paramMap){
		List<Member> sellerList = goodsMapper.getSearchSellerList(paramMap);
		return sellerList;
	}
	
	//상품등록
	public void addGoods(Goods goods) {
		log.info("상품등록 전 goods ::: {}", goods);
		goodsMapper.addGoods(goods);
		log.info("상품등록 후 goods ::: {}", goods);
	}
	
	//판매자 아이디 조회
	public List<Member> getSellerList() {
		List<Member> sellerList = memberMapper.getSellerList();
		
		return sellerList;
	}
	
	//상품목록조회
	public List<Goods> getGoodsList(){
		List<Goods> goodsList = goodsMapper.getGoodsList();
		return goodsList;
	}
	
}
