package com.eydiz.partner;

public class Partner {
	private int totalBrandCount;			// 이디즈 전체 브랜드 개수
	private int totalProjectCount;			// 이디즈  전체 프로젝트 개수
	private int totalBuyMemberCount;		// 이디즈 전체 이용 회원
	
	private int brandNo;					// 브랜드 번호
	private String brandName;				// 브랜드 이름
	private String managerEmail;			// 대표이메일
	private String managerName;				// 대표 이름
	
	private int brandTotalProject;			// 진행중 & 종료된 프로젝트 수
	private int brandTotalBuyMember;		// 펀딩 or 후원에 한번이라도 참여한 회원 인원수
	private double brandTotalAmount;		// 브랜드에서 진행한 프로젝트의 총 합계 금액
	private String memberImageUrl;			// 브랜드 이미지 경로
	
	private String categoryName;			// 리스트에 표시할 카테고리 이름
	private int categoryNo;					// 카테고리 번호
	private int parentCategoryNo;			// 상위 카테고리 번호      1:펀딩, 2:기부
	
	private int projectNo;					// 프로젝트 번호
	private String projectName;				// 프로젝트 이름
	private String projectImageUrl;			// 프로젝트 이미지 경로
	
	private int projectTotalAmount;			// 프로젝트 총 모금액
	private int projectGoalAmount;			// 프로젝트 목표금액
	private int percentage;					// 프로젝트 달성률	= projectTotalAmount/projectTargetAmount * 100
	
	private int statusNo;					// 활성 : 5, 종료 : 6
	
	
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
	public int getProjectTotalAmount() {
		return projectTotalAmount;
	}
	public void setProjectTotalAmount(int projectTotalAmount) {
		this.projectTotalAmount = projectTotalAmount;
	}
	
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
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
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public int getParentCategoryNo() {
		return parentCategoryNo;
	}
	public void setParentCategoryNo(int parentCategoryNo) {
		this.parentCategoryNo = parentCategoryNo;
	}
	public int getStatusNo() {
		return statusNo;
	}
	public void setStatusNo(int statusNo) {
		this.statusNo = statusNo;
	}
	public int getTotalBrandCount() {
		return totalBrandCount;
	}
	public void setTotalBrandCount(int totalBrandCount) {
		this.totalBrandCount = totalBrandCount;
	}
	public int getTotalProjectCount() {
		return totalProjectCount;
	}
	public void setTotalProjectCount(int totalProjectCount) {
		this.totalProjectCount = totalProjectCount;
	}
	public int getTotalBuyMemberCount() {
		return totalBuyMemberCount;
	}
	public void setTotalBuyMemberCount(int totalBuyMemberCount) {
		this.totalBuyMemberCount = totalBuyMemberCount;
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
	public int getBrandTotalProject() {
		return brandTotalProject;
	}
	public void setBrandTotalProject(int brandTotalProject) {
		this.brandTotalProject = brandTotalProject;
	}
	public int getBrandTotalBuyMember() {
		return brandTotalBuyMember;
	}
	public void setBrandTotalBuyMember(int brandTotalBuyMember) {
		this.brandTotalBuyMember = brandTotalBuyMember;
	}
	public double getBrandTotalAmount() {
		return brandTotalAmount;
	}
	public void setBrandTotalAmount(double brandTotalAmount) {
		this.brandTotalAmount = brandTotalAmount;
	}
	public String getMemberImageUrl() {
		return memberImageUrl;
	}
	public void setMemberImageUrl(String memberImageUrl) {
		this.memberImageUrl = memberImageUrl;
	}
	public String getProjectImageUrl() {
		return projectImageUrl;
	}
	public void setProjectImageUrl(String projectImageUrl) {
		this.projectImageUrl = projectImageUrl;
	}
	public int getProjectGoalAmount() {
		return projectGoalAmount;
	}
	public void setProjectGoalAmount(int projectGoalAmount) {
		this.projectGoalAmount = projectGoalAmount;
	}
	
	
	}
