package com.eydiz.studio;

public class ProjectHashtag {
	private int projectNo;
	private String keyword;

	public ProjectHashtag() {
	}

	public ProjectHashtag(int projectNo, String keyword) {
		this.projectNo = projectNo;
		this.keyword = keyword;
	}

	public int getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(int projectNo) {
		this.projectNo = projectNo;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
