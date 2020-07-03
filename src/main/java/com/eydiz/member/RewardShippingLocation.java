package com.eydiz.member;

public class RewardShippingLocation extends MemberShippingLocation {
	private String recipient;
	private String phone;
	private String message;
	private int courierNo;
	private String invoiceNumber;
	private int statusNo;
	private int wasReceived;

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCourierNo() {
		return courierNo;
	}

	public void setCourierNo(int courierNo) {
		this.courierNo = courierNo;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public int getStatusNo() {
		return statusNo;
	}

	public void setStatusNo(int statusNo) {
		this.statusNo = statusNo;
	}

	public int getWasReceived() {
		return wasReceived;
	}

	public void setWasReceived(int wasReceived) {
		this.wasReceived = wasReceived;
	}

}
