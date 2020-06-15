package com.eydiz.member;

// 세션에 저장할 정보(아이디, 이름, 권한등)
public class SessionInfo {

	private int memberNo;
	private String memberId;
	private String memberNickname;
	private String memberEmail;
	private int memberSocial;
	private boolean isAvailable;

	public SessionInfo() {
	}
	
	public SessionInfo(Member dto) {
		this.memberNo = dto.getMemberNo();
		this.memberId = dto.getMemberId();
		this.memberNickname = dto.getMemberNickname();
		this.memberEmail= dto.getMemberEmail();
		this.memberSocial = dto.getMemberSocial();
	}



	public SessionInfo(int memberNo, String memberId, String memberNickname, String memberEmail, int memberSocial,
			boolean isAvailable) {
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberNickname = memberNickname;
		this.memberEmail = memberEmail;
		this.memberSocial = memberSocial;
		this.isAvailable = isAvailable;
	}

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

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

}
