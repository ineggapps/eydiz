package com.eydiz.news;

import org.springframework.web.multipart.MultipartFile;

public class News {
	private int nNum;
	private int mNum;
	private int ncaNum;
	private int listNum;
	private String nsubject;
	private String ncontent;
	private String ncreated;
	private String imageFilename;
	
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
	private MultipartFile upload;
	
	public int getmNum() {
		return mNum;
	}
	public void setmNum(int mNum) {
		this.mNum = mNum;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}
	public int getnNum() {
		return nNum;
	}
	public void setnNum(int nNum) {
		this.nNum = nNum;
	}
	public int getNcaNum() {
		return ncaNum;
	}
	public void setNcaNum(int ncaNum) {
		this.ncaNum = ncaNum;
	}
	public String getNsubject() {
		return nsubject;
	}
	public void setNsubject(String nsubject) {
		this.nsubject = nsubject;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getNcreated() {
		return ncreated;
	}
	public void setNcreated(String ncreated) {
		this.ncreated = ncreated;
	}
	
}
