package com.eydiz.main;

public interface MainConstant {
	public static final String MAPPER_NAMESPACE="main.";
	
	//ATTRIBUTE
	public static final String ATTRIBUTE_FUNDING_CATEGORY = "fundingCategory";
	public static final String ATTRIBUTE_PROJECT = "project";
	public static final String ATTRIBUTE_CATEGORYNO = "categoryNo";
	public static final String ATTRIBUTE_CATEGORY_NAME = "categoryName";
	
	//정렬: 추천순(recommend), 인기순(popular), 모금액순(amount), 마감임박순(deadline), 최신순(latest), 참여도(community)순
	public static final String ATTRIBUTE_SORT = "sort";
	public static final String ATTIRIBUTE_SORT_RECOMMEND ="recommend";
	public static final String ATTRIBUTE_SORT_POPULAR = "popular";
	public static final String ATTRIBUTE_SORT_AMOUNT = "amount";
	public static final String ATTRIBUTE_SORT_DEADLINE = "deadline";
	public static final String ATTRIBUTE_SORT_LATEST = "latest";
	public static final String ATTRIBUTE_SORT_COMMUNITY = "community";
	
	//프로젝트 필터링: 전체(all 또는 null), 진행 중(ongoing), 완료된(closed)
	public static final String ATTRIBUTE_STATUS = "status";
	public static final String ATTRIBUTE_STATUS_ALL = "all";
	public static final String ATTRIBUTE_ONGOING ="ongoing";
	public static final String ATTRIBUTE_CLOSED = "closed";
	
	//검색어
	public static final String ATTRIBUTE_KEYWORD = "keyword";
}
