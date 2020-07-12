package com.eydiz.studio;

public class ProjectSessionInfo {
	private int rewardNo;
	private int buyNo;
	
	public ProjectSessionInfo(int rewardNo, int buyNo) {
		this.buyNo = buyNo;
		this.rewardNo = rewardNo;
	}
	
	public ProjectSessionInfo() {
		
	}
	
	public int getRewardNo() {
		return rewardNo;
	}
	public void setRewardNo(int rewardNo) {
		this.rewardNo = rewardNo;
	}
	public int getBuyNo() {
		return buyNo;
	}
	public void setBuyNo(int buyNo) {
		this.buyNo = buyNo;
	}
}
