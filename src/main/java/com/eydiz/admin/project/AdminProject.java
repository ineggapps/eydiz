package com.eydiz.admin.project;

public class AdminProject {
	private int rowNum;
	
	private int projectNo;
	private String projectName;
	
	private String created;
	
	private int statusNo;
	private String statusMemo;	// 상태메모(반려사유) : 체크박스 내용
	
	private String brandName;
	private String managerName;
	
	private String categoryName;
	
	
	
	

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

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}


	
	
	
	

}
