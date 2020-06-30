package com.eydiz.partner;

import java.util.List;
import java.util.Map;

public interface PartnerService {
	public int dataCount(Map<String, Object> map);					// 브랜드 전체 개수 구하기
	public List<Partner> listPartner(Map<String, Object> map, int brandNo);		// 브랜드 리스트
	public Partner readPartner(int brandNo);						// 브랜드 정보 가져오기
	
	public List<Partner> listProject(Map<String, Object> map);		// 프로젝트 리스트
	public int pDataCount(Map<String, Object> map);					// 해당 브랜드의 프로젝트 개수
	
	public int totalBrandCount();									// 이디즈의 총 브랜드 개수
	public int totalProjectCount();									// 이디즈의 총 프로젝트 개수
	public int totalBuyMemberCount();								// 이디즈에서 펀딩/후원에 참여한 회원 수
	
	public double brandTotalAmount(int brandNo);					// 브랜드의 총 펀딩 금액
	public int brandTotalProject(int brandNo);						// 브랜드의 총 프로젝트 개수
	public String memberImageUrl(int brandNo);						// 브랜드 이미지
	public int brandTotalBuyMember(int brandNo);					// 브랜드를 이용한 회원 수
	
	public String getToday();										// 오늘 날짜
	
}
