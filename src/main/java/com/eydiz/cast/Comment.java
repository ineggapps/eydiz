package com.eydiz.cast;

public class Comment {
	private int castNum;
	private int commentNum;
	private String memberId;
	private String commentContent;
	private String commentCreated;

	public int getCastNum() {
		return castNum;
	}

	public void setCastNum(int castNum) {
		this.castNum = castNum;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentCreated() {
		return commentCreated;
	}

	public void setCommentCreated(String commentCreated) {
		this.commentCreated = commentCreated;
	}

}
