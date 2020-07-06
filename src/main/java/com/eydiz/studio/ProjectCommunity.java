package com.eydiz.studio;

public class ProjectCommunity {
	private int commentNo;
	private Integer parentCommentNo;
	private int projectNo;
	private String memberId;
	private String content;
	private String createdDate;

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public Integer getParentCommentNo() {
		return parentCommentNo;
	}

	public void setParentCommentNo(Integer parentCommentNo) {
		this.parentCommentNo = parentCommentNo;
	}

	public int getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(int projectNo) {
		this.projectNo = projectNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

}
