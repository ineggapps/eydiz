package com.eydiz.studio;

import com.eydiz.common.Constant;

public interface StudioConstant extends Constant {

	public final static String MAPPER_NAMESPACE = "studio.";

	//API
	public final static String REQUEST_MAPPING= "/studio";
	public final static String API_PROJECT_LIST = "/studio/project/list";
	public final static String API_BRAND_INFO = "/studio/brand/info";
	
	public final static String SESSION_BRAND = "brand";
	
	//VIEW
	public final static String VIEW_BRAND_INFO_PRIMAL = "/studio/info_insert";
	public final static String VIEW_BRAND_INFO_UPDATE = ".studioOverviewLayout.info";
	
	public final static String VIEW_PROJECT_LIST = ".studioOverviewLayout.list";
	public final static String VIEW_PROJECT_REGISTER = ".studioLayout.register";
	public final static String VIEW_PROJECT_MAIN = VIEW_PROJECT_LIST;
	
	//ATTRIBUTE
	public final static String ATTRIBUTE_CATEGORY = "category";
	public final static String ATTRIBUTE_BRAND = "brand";
	public final static String ATTRIBUTE_URI = "uri";
	
}
