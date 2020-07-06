package com.eydiz.detail;

public interface DetailConstant {
	public final static String MAPPER_NAMESPACE = "detail.";
	
	public final static String REQUEST_MAPPING = "/detail";

	//ATTRIBUTE
	public final static String ATTRIBUTE_PROJECT = "project";
	public final static String ATTRIBUTE_REWARD = "reward";
	public final static String ATTRIBUTE_POPULAR_PROJECT = "popularProject";
	public final static String ATTRIBUTE_REWARDNO = "rewardNo";
	
	public final static String ATTRIBUTE_PROJECTNO = "projectNo";
	public final static String ATTRIBUTE_MEMBERNO = "memberNo";
	public final static String ATTRIBUTE_URI = "uri";
	
	//JSON
	public final static String JSON_PROJECT_LIKE_RESULT = "like_result";
	public final static String JSON_PROJECT_LIKE_RESULT_MARKED = "true";
	public final static String JSON_PROJECT_LIKE_RESULT_UNMARKED = "false";
	
	public final static String JSON_PG_TOKEN = "pg_token";
}
