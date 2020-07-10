package com.eydiz.studio;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Project {
	private int projectNo;
	// 브랜드
	private int brandNo;
	private int memberNo;
	private String brandName;
	private String managerName;
	private String managerEmail;
	private String managerPhone;
	// 브랜드 끝
	private int categoryNo;
	private String categoryName;
	private String projectName;
	private String projectStatusMemo;// 브랜드용 project 테이블의 메모
	private String projectImageUrl;
	private int projectGoalAmount;
	private int projectAttainAmount;
	private double projectAttainmentRate;
	private String projectCreatedDate;
	private String projectUpdatedDate;
	private String projectStartDate;
	private String projectEndDate;
	private int remainDays;
	private int statusNo;
	private String statusMemo;// 사이트 관리자용 project_status_list에 들어갈 프로젝트 상태 메모(작성 중, 프로젝트 활성화 등...)
	private String projectSummary;
	private String projectStory;
	private List<ProjectHashtag> hashTag;
	private MultipartFile uploadImage;
	private int likeCount;
	private int myLikeCount;
	//////////////////////////////////////////////////////
	private String memberImageUrl; // 브랜드 프로필 이미지
	private int newsCount; // 새소식 카운트 V
	private int communityCount; // 커뮤니티 상위댓글 카운트 V
	private double attainRate;// 달성률
	private int totalAmount;// 펀딩 총 금액
	private int supportCount; // 지지자 카운트(리워드 총 카운트) V
	///////////////////////////////////////////////////////
	private String boughtDate;
	private int isCanceled;
	private int buyNo;
	private int shipAmount;// 최종 결제 시 배송비
	private int finalAmount;// 최종 결제 시 배송비 포함 결제금액

	public int getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(int projectNo) {
		this.projectNo = projectNo;
	}

	public int getBrandNo() {
		return brandNo;
	}

	public void setBrandNo(int brandNo) {
		this.brandNo = brandNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

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

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectStatusMemo() {
		return projectStatusMemo;
	}

	public void setProjectStatusMemo(String projectStatusMemo) {
		this.projectStatusMemo = projectStatusMemo;
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

	public int getProjectAttainAmount() {
		return projectAttainAmount;
	}

	public void setProjectAttainAmount(int projectAttainAmount) {
		this.projectAttainAmount = projectAttainAmount;
	}

	public double getProjectAttainmentRate() {
		return projectAttainmentRate;
	}

	public void setProjectAttainmentRate(double projectAttainmentRate) {
		this.projectAttainmentRate = projectAttainmentRate;
	}

	public String getProjectCreatedDate() {
		return projectCreatedDate;
	}

	public void setProjectCreatedDate(String projectCreatedDate) {
		this.projectCreatedDate = projectCreatedDate;
	}

	public String getProjectUpdatedDate() {
		return projectUpdatedDate;
	}

	public void setProjectUpdatedDate(String projectUpdatedDate) {
		this.projectUpdatedDate = projectUpdatedDate;
	}

	public String getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(String projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public String getProjectEndDate() {
		return projectEndDate;
	}

	public void setProjectEndDate(String projectEndDate) {
		this.projectEndDate = projectEndDate;
	}

	public int getRemainDays() {
		return remainDays;
	}

	public void setRemainDays(int remainDays) {
		this.remainDays = remainDays;
	}

	public int getStatusNo() {
		return statusNo;
	}

	public void setStatusNo(int statusNo) {
		this.statusNo = statusNo;
	}

	public String getStatusMemo() {
		return statusMemo;
	}

	public void setStatusMemo(String statusMemo) {
		this.statusMemo = statusMemo;
	}

	public String getProjectSummary() {
		return projectSummary;
	}

	public void setProjectSummary(String projectSummary) {
		this.projectSummary = projectSummary;
	}

	public String getProjectStory() {
		return projectStory;
	}

	public void setProjectStory(String projectStory) {
		this.projectStory = projectStory;
	}

	public List<ProjectHashtag> getHashTag() {
		return hashTag;
	}

	public void setHashTag(List<ProjectHashtag> hashTag) {
		this.hashTag = hashTag;
	}

	public MultipartFile getUploadImage() {
		return uploadImage;
	}

	public void setUploadImage(MultipartFile uploadImage) {
		this.uploadImage = uploadImage;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getMyLikeCount() {
		return myLikeCount;
	}

	public void setMyLikeCount(int myLikeCount) {
		this.myLikeCount = myLikeCount;
	}

	public int getNewsCount() {
		return newsCount;
	}

	public void setNewsCount(int newsCount) {
		this.newsCount = newsCount;
	}

	public int getCommunityCount() {
		return communityCount;
	}

	public void setCommunityCount(int communityCount) {
		this.communityCount = communityCount;
	}

	public double getAttainRate() {
		return attainRate;
	}

	public void setAttainRate(double attainRate) {
		this.attainRate = attainRate;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getSupportCount() {
		return supportCount;
	}

	public void setSupportCount(int supportCount) {
		this.supportCount = supportCount;
	}

	public String getMemberImageUrl() {
		return memberImageUrl;
	}

	public void setMemberImageUrl(String memberImageUrl) {
		this.memberImageUrl = memberImageUrl;
	}

	public String getBoughtDate() {
		return boughtDate;
	}

	public void setBoughtDate(String boughtDate) {
		this.boughtDate = boughtDate;
	}

	public int getIsCanceled() {
		return isCanceled;
	}

	public void setIsCanceled(int isCanceled) {
		this.isCanceled = isCanceled;
	}

	public int getBuyNo() {
		return buyNo;
	}

	public void setBuyNo(int buyNo) {
		this.buyNo = buyNo;
	}

	public int getShipAmount() {
		return shipAmount;
	}

	public void setShipAmount(int shipAmount) {
		this.shipAmount = shipAmount;
	}

	public int getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(int finalAmount) {
		this.finalAmount = finalAmount;
	}

}
