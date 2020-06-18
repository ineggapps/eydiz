package com.eydiz.awards;

public class Awards {
	private int projectNum;			// 프로젝트 번호
	private String projectName;		// 프로젝트명
	private String content;			// 프로젝트 설명
	private String projectImage;	// 프로젝트 대표 이미지 경로
	
	private String brandName;		// 브랜드명
	private String brandImage;		// 브랜드 이미지 경로
	
	private int total;				// 현재 모인 금액
	private int target;				// 목표액
	private double percentage;			// 달성률	(%)	// 달성률 = total/target * 100
	
	private int groupCategoryNum;		// 탭 메뉴 분류번호(= 메인화면번호(0), 수상종목번호(1~7))
	private String groupCategoryName;	// 탭 메뉴 이름(= 메인화면이름, 수상종목이름)
	private String groupCategoryDetail;	// 수상종목설명?
	
	private int categoryNum;			// 프로젝트 카테고리 번호
	
	
	
	
	public String getProjectImage() {
		return projectImage;
	}
	public void setProjectImage(String projectImage) {
		this.projectImage = projectImage;
	}
	public String getBrandImage() {
		return brandImage;
	}
	public void setBrandImage(String brandImage) {
		this.brandImage = brandImage;
	}
	public String getGroupCategoryName() {
		return groupCategoryName;
	}
	public void setGroupCategoryName(String groupCategoryName) {
		this.groupCategoryName = groupCategoryName;
	}
	
	public int getProjectNum() {
		return projectNum;
	}
	public void setProjectNum(int projectNum) {
		this.projectNum = projectNum;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public int getGroupCategoryNum() {
		return groupCategoryNum;
	}
	public void setGroupCategoryNum(int groupCategoryNum) {
		this.groupCategoryNum = groupCategoryNum;
	}
	public int getCategoryNum() {
		return categoryNum;
	}
	public void setCategoryNum(int categoryNum) {
		this.categoryNum = categoryNum;
	}
	public String getGroupCategoryDetail() {
		return groupCategoryDetail;
	}
	public void setGroupCategoryDetail(String groupCategoryDetail) {
		this.groupCategoryDetail = groupCategoryDetail;
	}
	
	
}
