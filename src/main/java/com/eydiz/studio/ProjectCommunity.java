package com.eydiz.studio;

public class ProjectCommunity {
	private int commentNo;
	private Integer parentCommentNo;
	private int projectNo;
	private String memberId;
	private String content;
	private String createdDate;

	private int count;// 댓글 답글 총 개수
	private int parentCount; // 댓글 개수만
	private int replyCount; // 해당하는 답글 개수만 (parentCommentNo가 존재하는 경우만)
	private String memberNickname;
	private String memberImageUrl;

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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getParentCount() {
		return parentCount;
	}

	public void setParentCount(int parentCount) {
		this.parentCount = parentCount;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getMemberImageUrl() {
		return memberImageUrl;
	}

	public void setMemberImageUrl(String memberImageUrl) {
		this.memberImageUrl = memberImageUrl;
	}

}
