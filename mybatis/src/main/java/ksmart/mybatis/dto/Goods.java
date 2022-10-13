package ksmart.mybatis.dto;

public class Goods {
	
	private String goodsCode;
	private String goodsName;
	private int goodsPrice;
	private String goodsSellerId;
	private String goodsSellerName;
	private String goodsRegDate;
	
	private Member sellerInfo;
	
	public Member getSellerInfo() {
		return sellerInfo;
	}
	public void setSellerInfo(Member sellerInfo) {
		this.sellerInfo = sellerInfo;
	}
	
	
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getGoodsSellerId() {
		return goodsSellerId;
	}
	public void setGoodsSellerId(String goodsSellerId) {
		this.goodsSellerId = goodsSellerId;
	}
	public String getGoodsSellerName() {
		return goodsSellerName;
	}
	public void setGoodsSellerName(String goodsSellerName) {
		this.goodsSellerName = goodsSellerName;
	}
	public String getGoodsRegDate() {
		return goodsRegDate;
	}
	public void setGoodsRegDate(String goodsRegDate) {
		this.goodsRegDate = goodsRegDate;
	}
	@Override
	public String toString() {
		return "Goods [goodsCode=" + goodsCode + ", goodsName=" + goodsName + ", goodsPrice=" + goodsPrice
				+ ", goodsSellerId=" + goodsSellerId + ", goodsSellerName=" + goodsSellerName + ", goodsRegDate="
				+ goodsRegDate + ", sellerInfo=" + sellerInfo + "]";
	}
	
	
}
