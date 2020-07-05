package com.eydiz.reward;

public interface RewardConstant {
	public static final String MAPPER_NAMESPACE = "reward.";
	
	//SESSION
	public static final String SESSION_REWARD = "reward";

	//
	public static final String ATTRIBUTE_REQUEST_QUANTITY = "requestQuantity";
	public static final String ATTRIBUTE_OPTION_ANSWER = "optionAnswer";

	// API
	public static final String API_DETAIL_PROJECT = "/detail/%d";
	public static final String API_REWARD_STEP2 = "/reward/%d/step2";
}
