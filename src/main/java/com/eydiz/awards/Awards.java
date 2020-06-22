package com.eydiz.awards;

public class Awards {
	private int projectNo;			// 프로젝트 번호
	private String projectName;		// 프로젝트명
	private String projectSummary;	// 프로젝트 설명
	private String projectImageUrl;	// 프로젝트 대표 이미지 경로
	
	private String brandNo;			// 브랜드 번호
	private String brandName;		// 브랜드명
	private String brandImage;		// 브랜드 이미지 경로
	
	private int total;				// 현재 모인 금액
	private int projectGoalAmaount;	// 목표액
	private double percentage;		// 달성률	(%)	// 달성률 = total/target * 100
	
	private int awardsGroupNum;		// 탭 메뉴 분류번호(= 메인화면번호(0), 수상종목번호(1~7))
	private String awardsGroupName;	// 탭 메뉴 이름(= 메인화면이름, 수상종목이름)
	private String awardsGroupDetail;	// 수상종목설명?
	
	private int categoryNo;			// 프로젝트 카테고리 번호

	
	
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

	public String getProjectSummary() {
		return projectSummary;
	}

	public void setProjectSummary(String projectSummary) {
		this.projectSummary = projectSummary;
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

	public String getBrandImage() {
		return brandImage;
	}

	public void setBrandImage(String brandImage) {
		this.brandImage = brandImage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
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

	public int getAwardsGroupNum() {
		return awardsGroupNum;
	}

	public void setAwardsGroupNum(int awardsGroupNum) {
		this.awardsGroupNum = awardsGroupNum;
	}

	public String getAwardsGroupName() {
		return awardsGroupName;
	}

	public void setAwardsGroupName(String awardsGroupName) {
		this.awardsGroupName = awardsGroupName;
	}

	public String getAwardsGroupDetail() {
		return awardsGroupDetail;
	}

	public void setAwardsGroupDetail(String awardsGroupDetail) {
		this.awardsGroupDetail = awardsGroupDetail;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	
	
	
	}
