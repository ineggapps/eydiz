package com.eydiz.partner;

public class Partner {
	private int brandMemberCount;		// 이디즈 브랜드 개수
	private int projectDataCount;		// 진행중 & 종료된 프로젝트 수
	private int memberCount;			// 펀딩 or 후원에 한번이라도 참여한 회원 인원수
	
	private int brandNo;				// 브랜드 번호
	private String brandName;			// 브랜드 이름
	private String brandImageUrl;		// 브랜드 이미지 경로
	
	
	public int getBrandMemberCount() {
		return brandMemberCount;
	}
	public void setBrandMemberCount(int brandMemberCount) {
		this.brandMemberCount = brandMemberCount;
	}
	public int getProjectDataCount() {
		return projectDataCount;
	}
	public void setProjectDataCount(int projectDataCount) {
		this.projectDataCount = projectDataCount;
	}
	public int getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}
	public int getBrandNo() {
		return brandNo;
	}
	public void setBrandNo(int brandNo) {
		this.brandNo = brandNo;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBrandImageUrl() {
		return brandImageUrl;
	}
	public void setBrandImageUrl(String brandImageUrl) {
		this.brandImageUrl = brandImageUrl;
	}
	
	

}
