package com.eydiz.member;

public class Member {
	private int memberNo;
	private String memberId;
	private String memberNickname;
	private String memberPwd;
	private String memberEmail;
	private int memberSocial;
	private boolean isAdmin;
	private String memberImageUrl;

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public int getMemberSocial() {
		return memberSocial;
	}

	public void setMemberSocial(int memberSocial) {
		this.memberSocial = memberSocial;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getMemberImageUrl() {
		return memberImageUrl;
	}

	public void setMemberImageUrl(String memberImageUrl) {
		this.memberImageUrl = memberImageUrl;
	}

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberNickname=" + memberNickname
				+ ", memberPwd=" + memberPwd + ", memberEmail=" + memberEmail + ", memberSocial=" + memberSocial
				+ ", isAdmin=" + isAdmin + ", memberImageUrl=" + memberImageUrl + "]";
	}

	
}
