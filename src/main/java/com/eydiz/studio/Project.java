package com.eydiz.studio;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Project {
	private int projectNo;
	private int brandNo;
	private int categoryNo;
	private String projectName;
	private String projectStatusMemo;
	private String projectImagesUrl;
	private int projectGoalAmount;
	private String projectCreatedDate;
	private String projectUpdatedDate;
	private String projectStartDate;
	private String projectEndDate;
	private int stateNo;
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

	public String getProjectImagesUrl() {
		return projectImagesUrl;
	}

	public void setProjectImagesUrl(String projectImagesUrl) {
		this.projectImagesUrl = projectImagesUrl;
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

	public int getStateNo() {
		return stateNo;
	}

	public void setStateNo(int stateNo) {
		this.stateNo = stateNo;
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
		return "Project [projectNo=" + projectNo + ", brandNo=" + brandNo + ", categoryNo=" + categoryNo
				+ ", projectName=" + projectName + ", projectStatusMemo=" + projectStatusMemo + ", projectImagesUrl="
				+ projectImagesUrl + ", projectGoalAmount=" + projectGoalAmount + ", projectCreatedDate="
				+ projectCreatedDate + ", projectUpdatedDate=" + projectUpdatedDate + ", projectStartDate="
				+ projectStartDate + ", projectEndDate=" + projectEndDate + ", stateNo=" + stateNo + ", projectSummary="
				+ projectSummary + ", projectStory=" + projectStory + ", categoryName=" + categoryName + ", hashTag="
				+ hashTag + ", uploadImage=" + uploadImage + "]";
	}
	
	

}
