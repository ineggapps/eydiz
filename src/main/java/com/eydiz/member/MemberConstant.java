package com.eydiz.member;

import com.eydiz.common.Constant;

public interface MemberConstant extends Constant {
	//API
	public final static String API_MAIN = "/main";
	public final static String API_LOGIN = "/member/login";
	public final static String API_CHECK_ID= "/member/checkId";
	
	//세션 정보
	public final static String SESSION_MEMBER = "member";

	//파라미터
	public final static String REDIRECTION_URI = "redirectionUri";
	
	
	//JSON 결괏값
	public final static String JSON_IS_TAKEN = "taken";
	
	
	//기타
	public final static String EXCEPTION_AUTHENTICATION = "error";
}
