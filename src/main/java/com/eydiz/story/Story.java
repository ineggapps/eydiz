package com.eydiz.story;

public class Story {
	private int storyNum;
	private int listNum;
	private String memberId;
	private int storyCnum;
	private String stroyCname;
	private String storyContent;
	private String storyCreated;
	private String storyLikeCount;
	
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getStoryCnum() {
		return storyCnum;
	}
	public void setStoryCnum(int storyCnum) {
		this.storyCnum = storyCnum;
	}

	public String getStoryCreated() {
		return storyCreated;
	}
	public void setStoryCreated(String storyCreated) {
		this.storyCreated = storyCreated;
	}

	public String getStoryLikeCount() {
		return storyLikeCount;
	}
	public void setStoryLikeCount(String storyLikeCount) {
		this.storyLikeCount = storyLikeCount;
	}
	public String getStroyCname() {
		return stroyCname;
	}
	public void setStroyCname(String stroyCname) {
		this.stroyCname = stroyCname;
	}
	public int getListNum() {
		return listNum;
	}
	public void setListNum(int listNum) {
		this.listNum = listNum;
	}
	public String getStoryContent() {
		return storyContent;
	}
	public void setStoryContent(String storyContent) {
		this.storyContent = storyContent;
	}
	public int getStoryNum() {
		return storyNum;
	}
	public void setStoryNum(int storyNum) {
		this.storyNum = storyNum;
	}
	
}
