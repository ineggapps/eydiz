package com.eydiz.news;

import org.springframework.web.multipart.MultipartFile;

public class News {
	private int noticeNo;
	private int memberNo;
	private String memberNickname;
	private int nocaNo;
	private int listNum;
	private String noticeSubject;
	private String noticeContent;
	private String noticeCreated;
	private String imageFilename;
	private MultipartFile upload;
	private String nocaName;
	
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public String getNocaName() {
		return nocaName;
	}
	public void setNocaName(String nocaName) {
		this.nocaName = nocaName;
	}
	public int getListNum() {
		return listNum;
	}
	public void setListNum(int listNum) {
		this.listNum = listNum;
	}
	public String getImageFilename() {
		return imageFilename;
	}
	public void setImageFilename(String imageFilename) {
		this.imageFilename = imageFilename;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getNocaNo() {
		return nocaNo;
	}
	public void setNocaNo(int nocaNo) {
		this.nocaNo = nocaNo;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}
	public String getNoticeSubject() {
		return noticeSubject;
	}
	public void setNoticeSubject(String noticeSubject) {
		this.noticeSubject = noticeSubject;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public String getNoticeCreated() {
		return noticeCreated;
	}
	public void setNoticeCreated(String noticeCreated) {
		this.noticeCreated = noticeCreated;
	}
	
}
