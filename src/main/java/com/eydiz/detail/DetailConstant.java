package com.eydiz.detail;

public interface DetailConstant {
	public final static String MAPPER_NAMESPACE = "detail.";
	
	public final static String REQUEST_MAPPING = "/detail";

	//ATTRIBUTE
	public final static String ATTRIBUTE_PROJECT = "project";
	public final static String ATTRIBUTE_REWARD = "reward";
	public final static String ATTRIBUTE_POPULAR_PROJECT = "popularProject";
	public final static String ATTRIBUTE_REWARDNO = "rewardNo";
	public final static String ATTRIBUTE_COMMUNITY_COMMENT = "comment";
	public final static String ATTRIBUTE_COMMUNITY_COMMENTS = "comments";
	public final static String ATTRIBUTE_COMMENT_NO = "commentNo";
	public final static String ATTRIBUTE_PARENT_COMMENT_NO = "parentCommentNo";
	
	public final static String ATTRIBUTE_PROJECTNO = "projectNo";
	public final static String ATTRIBUTE_MEMBERNO = "memberNo";
	public final static String ATTRIBUTE_URI = "uri";
	
	public final static String ATTRIBUTE_OFFSET = "offset";
	public final static String ATTRIBUTE_ROWS = "rows";
	
	//JSON
	public final static String JSON_PROJECT_LIKE_RESULT = "like_result";
	public final static String JSON_PROJECT_LIKE_RESULT_MARKED = "true";
	public final static String JSON_PROJECT_LIKE_RESULT_UNMARKED = "false";
	
	public final static String JSON_PROJECT_NEWS = "news";
	public final static String JSON_PROJECT_PAGE_COUNT = "page_count";
	
	public final static String JSON_PG_TOKEN = "pg_token";
}
