package com.eydiz.studio;

public class RewardCancel {
	private int cancelNo;
	private int buyNo;
	private int canceledAmount;
	private String memo;
	private String canceledDate;

	public int getCancelNo() {
		return cancelNo;
	}

	public void setCancelNo(int cancelNo) {
		this.cancelNo = cancelNo;
	}

	public int getBuyNo() {
		return buyNo;
	}

	public void setBuyNo(int buyNo) {
		this.buyNo = buyNo;
	}

	public int getCanceledAmount() {
		return canceledAmount;
	}

	public void setCanceledAmount(int canceledAmount) {
		this.canceledAmount = canceledAmount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCanceledDate() {
		return canceledDate;
	}

	public void setCanceledDate(String canceledDate) {
		this.canceledDate = canceledDate;
	}

	public RewardCancel() {
	}

	public RewardCancel(int cancelNo, int buyNo, int canceledAmount, String memo, String canceledDate) {
		this.cancelNo = cancelNo;
		this.buyNo = buyNo;
		this.canceledAmount = canceledAmount;
		this.memo = memo;
		this.canceledDate = canceledDate;
	}

}
