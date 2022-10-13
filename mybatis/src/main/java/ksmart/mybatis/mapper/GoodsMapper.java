package ksmart.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ksmart.mybatis.dto.Goods;
import ksmart.mybatis.dto.Member;

@Mapper
public interface GoodsMapper {
	
	//판매자 목록 조회
	public List<Member> getSearchSellerList(Map<String, Object> searchMap);
	
	// 상품등록
	public int addGoods(Goods goods);
	
	// 상품목록조회
	public List<Goods> getGoodsList();
}
