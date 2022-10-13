package ksmart.mybatis.dto;

import java.util.List;

public class Member {
	
	private String memberId;
	private String memberPw;
	private int memberLevel;
	private String memberLevelName;
	private String memberName;
	private String memberEmail;
	private String memberAddress;
	private String memberRegDate;
	
	private MemberLevel memberLevelDto;
	
	public MemberLevel getMemberLevelDto() {
		return memberLevelDto;
	}
	public void setMemberLevelDto(MemberLevel memberLevelDto) {
		this.memberLevelDto = memberLevelDto;
	}
	
	
	private List<Goods> goodsList;
	
	public List<Goods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}
	
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public int getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}
	public String getMemberLevelName() {
		return memberLevelName;
	}
	public void setMemberLevelName(String memberLevelName) {
		this.memberLevelName = memberLevelName;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getMemberRegDate() {
		return memberRegDate;
	}
	public void setMemberRegDate(String memberRegDate) {
		this.memberRegDate = memberRegDate;
	}
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPw=" + memberPw + ", memberLevel=" + memberLevel
				+ ", memberLevelName=" + memberLevelName + ", memberName=" + memberName + ", memberEmail=" + memberEmail
				+ ", memberAddress=" + memberAddress + ", memberRegDate=" + memberRegDate + ", memberLevelDto="
				+ memberLevelDto + ", goodsList=" + goodsList + "]";
	}

	
}
