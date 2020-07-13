package com.eydiz.awards;

public class Awards {
	private int rowNum;
	private int projectNo;			// 프로젝트 번호
	private String projectName;		// 프로젝트명
	private String projectImageUrl;	// 프로젝트 대표 이미지 경로
	
	private String brandNo;			// 브랜드 번호
	private String brandName;		// 브랜드명
	private String memberImageUrl;	// 브랜드 이미지 경로
	
	private String managerName;		// 브랜드 소유자 이름
	private String managerEmail;	// 이메일
	private int projectAmount;		// 브랜드가 소유한 프로젝트 수
	
	private int totalAmount;// 총 모인 금액
	private int projectGoalAmaount;	// 목표액
	private double percentage;		// 달성률	(%)	// 달성률 = total/target * 100
	
	private String categoryName;	// 프로젝트 카테고리 이름

	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public int getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(int projectNo) {
		this.projectNo = projectNo;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectImageUrl() {
		return projectImageUrl;
	}
	public void setProjectImageUrl(String projectImageUrl) {
		this.projectImageUrl = projectImageUrl;
	}
	public String getBrandNo() {
		return brandNo;
	}
	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getMemberImageUrl() {
		return memberImageUrl;
	}
	public void setMemberImageUrl(String memberImageUrl) {
		this.memberImageUrl = memberImageUrl;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getProjectGoalAmaount() {
		return projectGoalAmaount;
	}
	public void setProjectGoalAmaount(int projectGoalAmaount) {
		this.projectGoalAmaount = projectGoalAmaount;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getProjectAmount() {
		return projectAmount;
	}
	public void setProjectAmount(int projectAmount) {
		this.projectAmount = projectAmount;
	}
	
	

	

	
	
	
	}
