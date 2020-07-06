package com.eydiz.reward;

import com.eydiz.member.MemberShippingLocation;

public class RewardShippingLocation extends MemberShippingLocation {
	private int buyNo;
	private int memberNo;
	private String phone;
	private String zipCode;
	private String address1;
	private String address2;
	private String recipient;
	private String message;
	private int courierNo;
	private int invoiceNumber;
	private int statusNo;
	private int wasReceived;

	public int getBuyNo() {
		return buyNo;
	}

	public void setBuyNo(int buyNo) {
		this.buyNo = buyNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
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

	public int getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(int invoiceNumber) {
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

	public RewardShippingLocation() {
	}
	
	public RewardShippingLocation(int buyNo, int memberNo, String phone, String zipCode, String address1,
			String address2, String recipient, String message) {
		this.buyNo = buyNo;
		this.memberNo = memberNo;
		this.phone = phone;
		this.zipCode = zipCode;
		this.address1 = address1;
		this.address2 = address2;
		this.recipient = recipient;
		this.message = message;
	}

	public RewardShippingLocation(int buyNo, int memberNo, String phone, String zipCode, String address1,
			String address2, String recipient, String message, int courierNo, int invoiceNumber, int statusNo,
			int wasReceived) {
		this.buyNo = buyNo;
		this.memberNo = memberNo;
		this.phone = phone;
		this.zipCode = zipCode;
		this.address1 = address1;
		this.address2 = address2;
		this.recipient = recipient;
		this.message = message;
		this.courierNo = courierNo;
		this.invoiceNumber = invoiceNumber;
		this.statusNo = statusNo;
		this.wasReceived = wasReceived;
	}

}
