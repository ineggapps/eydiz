package com.eydiz.school;

import org.springframework.web.multipart.MultipartFile;

public class School {
	private int scNo;
	private int memberNo;
	private int sccaNo;
	private String scSubject;
	private String scContent;
	private String scSavefilename;
	private String scDate;
	private int scNumber;
	private int scAllNumber;
	private int listNum;
	private MultipartFile upload;
	
	public int getScNo() {
		return scNo;
	}
	public void setScNo(int scNo) {
		this.scNo = scNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getSccaNo() {
		return sccaNo;
	}
	public void setSccaNo(int sccaNo) {
		this.sccaNo = sccaNo;
	}
	public String getScSubject() {
		return scSubject;
	}
	public void setScSubject(String scSubject) {
		this.scSubject = scSubject;
	}
	public String getScContent() {
		return scContent;
	}
	public void setScContent(String scContent) {
		this.scContent = scContent;
	}
	public String getScSavefilename() {
		return scSavefilename;
	}
	public void setScSavefilename(String scSavefilename) {
		this.scSavefilename = scSavefilename;
	}
	public String getScDate() {
		return scDate;
	}
	public void setScDate(String scDate) {
		this.scDate = scDate;
	}
	public int getScNumber() {
		return scNumber;
	}
	public void setScNumber(int scNumber) {
		this.scNumber = scNumber;
	}
	public int getScAllNumber() {
		return scAllNumber;
	}
	public void setScAllNumber(int scAllNumber) {
		this.scAllNumber = scAllNumber;
	}
	public int getListNum() {
		return listNum;
	}
	public void setListNum(int listNum) {
		this.listNum = listNum;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}
}
