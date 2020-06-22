package com.eydiz.studio;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Project {
	private int projectNo;
	private int brandNo;
	private String brandName;
	private int categoryNo;
	private String projectName;
	private String projectStatusMemo;// 브랜드용 project 테이블의 메모
	private String projectImageUrl;
	private int projectGoalAmount;
	private String projectCreatedDate;
	private String projectUpdatedDate;
	private String projectStartDate;
	private String projectEndDate;
	private int statusNo;
	private String statusMemo;// 사이트 관리자용 project_status_list에 들어갈 프로젝트 상태 메모(작성 중, 프로젝트 활성화 등...)
	private String projectSummary;
	private String projectStory;
	private String categoryName;
	private List<ProjectHashtag> hashTag;
	private MultipartFile uploadImage;

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

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	@Override
	public String toString() {
		return "Project [projectNo=" + projectNo + ", brandNo=" + brandNo + ", brandName=" + brandName + ", categoryNo="
				+ categoryNo + ", projectName=" + projectName + ", projectStatusMemo=" + projectStatusMemo
				+ ", projectImageUrl=" + projectImageUrl + ", projectGoalAmount=" + projectGoalAmount
				+ ", projectCreatedDate=" + projectCreatedDate + ", projectUpdatedDate=" + projectUpdatedDate
				+ ", projectStartDate=" + projectStartDate + ", projectEndDate=" + projectEndDate + ", statusNo="
				+ statusNo + ", statusMemo=" + statusMemo + ", projectSummary=" + projectSummary + ", projectStory="
				+ projectStory + ", categoryName=" + categoryName + ", hashTag=" + hashTag + ", uploadImage="
				+ uploadImage + "]";
	}


}
