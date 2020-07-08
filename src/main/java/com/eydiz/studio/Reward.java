package com.eydiz.studio;

public class Reward {
	private int rewardNo;
	private int projectNo;
	private int buyNo;
	private String projectName;
	private int brandNo; // 리워드 수정할 때 인증용도
	private int amount;
	private String rewardTitle;
	private String rewardContent;
	private String rewardOption;
	private int isShipping;
	private int shipAmount;
	private String startShippingDate;
	private int limitQuantity;
	private int remainQuantity;
	//////////////////////// buy_detail
	private int requestQuantity;// 요청수량
	private int purchasePrice;
	private String optionAnswer;

	public int getRewardNo() {
		return rewardNo;
	}

	public void setRewardNo(int rewardNo) {
		this.rewardNo = rewardNo;
	}

	public int getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(int projectNo) {
		this.projectNo = projectNo;
	}

	public int getBuyNo() {
		return buyNo;
	}

	public void setBuyNo(int buyNo) {
		this.buyNo = buyNo;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getBrandNo() {
		return brandNo;
	}

	public void setBrandNo(int brandNo) {
		this.brandNo = brandNo;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getRewardTitle() {
		return rewardTitle;
	}

	public void setRewardTitle(String rewardTitle) {
		this.rewardTitle = rewardTitle;
	}

	public String getRewardContent() {
		return rewardContent;
	}

	public void setRewardContent(String rewardContent) {
		this.rewardContent = rewardContent;
	}

	public String getRewardOption() {
		return rewardOption;
	}

	public void setRewardOption(String rewardOption) {
		this.rewardOption = rewardOption;
	}

	public int getIsShipping() {
		return isShipping;
	}

	public void setIsShipping(int isShipping) {
		this.isShipping = isShipping;
	}

	public int getShipAmount() {
		return shipAmount;
	}

	public void setShipAmount(int shipAmount) {
		this.shipAmount = shipAmount;
	}

	public String getStartShippingDate() {
		return startShippingDate;
	}

	public void setStartShippingDate(String startShippingDate) {
		this.startShippingDate = startShippingDate;
	}

	public int getLimitQuantity() {
		return limitQuantity;
	}

	public void setLimitQuantity(int limitQuantity) {
		this.limitQuantity = limitQuantity;
	}

	public int getRemainQuantity() {
		return remainQuantity;
	}

	public void setRemainQuantity(int remainQuantity) {
		this.remainQuantity = remainQuantity;
	}

	public int getRequestQuantity() {
		return requestQuantity;
	}

	public void setRequestQuantity(int requestQuantity) {
		this.requestQuantity = requestQuantity;
	}

	public int getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(int purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getOptionAnswer() {
		return optionAnswer;
	}

	public void setOptionAnswer(String optionAnswer) {
		this.optionAnswer = optionAnswer;
	}

	@Override
	public String toString() {
		return "Reward [rewardNo=" + rewardNo + ", projectNo=" + projectNo + ", amount=" + amount + ", rewardTitle="
				+ rewardTitle + ", rewardContent=" + rewardContent + ", rewardOption=" + rewardOption + ", isShipping="
				+ isShipping + ", shipAmount=" + shipAmount + ", startShippingDate=" + startShippingDate
				+ ", limitQuantity=" + limitQuantity + ", remainQuantity=" + remainQuantity + "]";
	}

}
