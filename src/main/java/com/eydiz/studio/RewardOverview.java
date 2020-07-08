package com.eydiz.studio;

public class RewardOverview {
	private int buyNo;
	private int projectNo;
	private int memberNo;
	private int totalAmount;
	private int shipAmount;
	private String boughtDate;

	public int getBuyNo() {
		return buyNo;
	}

	public void setBuyNo(int buyNo) {
		this.buyNo = buyNo;
	}

	public int getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(int projectNo) {
		this.projectNo = projectNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getShipAmount() {
		return shipAmount;
	}

	public void setShipAmount(int shipAmount) {
		this.shipAmount = shipAmount;
	}

	public String getBoughtDate() {
		return boughtDate;
	}

	public void setBoughtDate(String boughtDate) {
		this.boughtDate = boughtDate;
	}

	public RewardOverview(int buyNo, int projectNo, int memberNo, int totalAmount, int shipAmount, String boughtDate) {
		this.buyNo = buyNo;
		this.projectNo = projectNo;
		this.memberNo = memberNo;
		this.totalAmount = totalAmount;
		this.shipAmount = shipAmount;
		this.boughtDate = boughtDate;
	}

	public RewardOverview() {
	}

}
