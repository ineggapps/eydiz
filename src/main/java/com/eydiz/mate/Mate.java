package com.eydiz.mate;

import org.springframework.web.multipart.MultipartFile;

public class Mate {
	private int fmNo;
	private int memberNo;
	private int fmcaNo;
	private String fmSubject;
	private String fmContent;
	private int listNum;
	private String fmFilename;
	private int fmNumber;
	private int fmAllNumber;
	private MultipartFile upload;
	private int mateProjectCount;
	private String memberNickname;
	
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public int getMateProjectCount() {
		return mateProjectCount;
	}
	public void setMateProjectCount(int mateProjectCount) {
		this.mateProjectCount = mateProjectCount;
	}
	public int getListNum() {
		return listNum;
	}
	public void setListNum(int listNum) {
		this.listNum = listNum;
	}
	public int getFmcaNo() {
		return fmcaNo;
	}
	public void setFmcaNo(int fmcaNo) {
		this.fmcaNo = fmcaNo;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}
	public int getFmNo() {
		return fmNo;
	}
	public void setFmNo(int fmNo) {
		this.fmNo = fmNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getFmSubject() {
		return fmSubject;
	}
	public void setFmSubject(String fmSubject) {
		this.fmSubject = fmSubject;
	}
	public String getFmContent() {
		return fmContent;
	}
	public void setFmContent(String fmContent) {
		this.fmContent = fmContent;
	}
	public String getfmFilename() {
		return fmFilename;
	}
	public void setfmFilename(String fmFilename) {
		this.fmFilename = fmFilename;
	}
	public int getFmNumber() {
		return fmNumber;
	}
	public void setFmNumber(int fmNumber) {
		this.fmNumber = fmNumber;
	}
	public int getFmAllNumber() {
		return fmAllNumber;
	}
	public void setFmAllNumber(int fmAllNumber) {
		this.fmAllNumber = fmAllNumber;
	}
}
