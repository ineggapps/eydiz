package com.eydiz.studio;

import com.eydiz.common.Constant;

public interface StudioConstant extends Constant {

	public final static String MAPPER_NAMESPACE = "studio.";

	// API
	public final static String REQUEST_MAPPING = "/studio";
	public final static String API_PROJECT_LIST = "/studio/project/list";
	public final static String API_PROJECT_DASHBOARD = "/studio/project/%d/dashboard";
	public final static String API_PROJECT_REGISTER = "/studio/project/%d/register";
	public final static String API_PROJECT_STORY = "/studio/project/%d/story";
	public final static String API_BRAND_INFO = "/studio/brand/info";

	public final static String SESSION_BRAND = "brand";

	// 파일 업로드
	public final static String FOLDER_UPLOADS_BRAND = "brand";
	public final static String FOLDER_UPLOADS_PROJECT = "project";

	// VIEW
	public final static String VIEW_BRAND_INFO_PRIMAL = "/studio/info_insert";
	public final static String VIEW_BRAND_INFO_UPDATE = ".studioOverviewLayout.info";

	public final static String VIEW_PROJECT_LIST = ".studioOverviewLayout.list";
	public final static String VIEW_PROJECT_DASHBOARD = ".studioLayout.dashboard";
	public final static String VIEW_PROJECT_REGISTER = ".studioLayout.register";
	public final static String VIEW_PROJECT_REWARD = ".studioLayout.reward";
	public final static String VIEW_PROJECT_STORY = ".studioLayout.story";
	public final static String VIEW_PROJECT_MAIN = VIEW_PROJECT_LIST;

	// ATTRIBUTE
	public final static String ATTRIBUTE_PROJECT = "project";
	public final static String ATTRIBUTE_CATEGORY = "category";
	public final static String ATTRIBUTE_BRAND = "brand";
	public final static String ATTRIBUTE_URI = "uri";
	public final static String ATTRIBUTE_PROJECTNO = "projectNo";
	public final static String ATTRIBUTE_BRANDNO = "brandNo";
	public final static String ATTRIBUTE_REWARDNO = "rewardNo";
	public final static String ATTRIBUTE_STATUSNO = "statusNo";
	public final static String ATTRIBUTE_OFFSET = "offset";
	public final static String ATTRIBUTE_ROWS = "rows";
	public final static String ATTRIBUTE_CURRENT_PAGE = "currentPage";
	public final static String ATTRIBUTE_PAGE_COUNT = "pageCount";
	
	// JSON
	public final static String JSON_IMAGE_URL = "url";
	public final static String JSON_HASHTAG = "hashtag";
	public final static String JSON_HASHTAG_PROJECTNO = "projectNo";
	public final static String JSON_HASHTAG_KEYWORD = "keyword";
	public final static String JSON_REWARD_LIST = "rewards";

	//기본 값
	public final static String STATUS_MEMO_0 = "작성 시작";
}
