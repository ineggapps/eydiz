package com.eydiz.guide;

import org.springframework.web.multipart.MultipartFile;

public class Guide {
	private int faqNo;
	private int memberNo;
	private String faqSubject;
	private String faqContent;
	private String faqCreated;
	private String faqFilename;
	private String faqIcon;
	private MultipartFile upload;
	
	public int getFaqNo() {
		return faqNo;
	}
	public void setFaqNo(int faqNo) {
		this.faqNo = faqNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getFaqSubject() {
		return faqSubject;
	}
	public void setFaqSubject(String faqSubject) {
		this.faqSubject = faqSubject;
	}
	public String getFaqContent() {
		return faqContent;
	}
	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}
	public String getFaqCreated() {
		return faqCreated;
	}
	public void setFaqCreated(String faqCreated) {
		this.faqCreated = faqCreated;
	}
	public String getFaqFilename() {
		return faqFilename;
	}
	public void setFaqFilename(String faqFilename) {
		this.faqFilename = faqFilename;
	}
	public String getFaqIcon() {
		return faqIcon;
	}
	public void setFaqIcon(String faqIcon) {
		this.faqIcon = faqIcon;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}
	
	
}
