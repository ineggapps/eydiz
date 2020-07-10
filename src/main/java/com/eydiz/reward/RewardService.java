package com.eydiz.reward;

import java.util.Map;

import com.eydiz.member.SessionInfo;
import com.eydiz.studio.Reward;

public interface RewardService {
	public int nextBuyNo();
	/**
	 * 한꺼번에 등록하기
	 * @param rewardInfo
	 * @param memberInfo
	 * @throws Exception
	 */
	public void insertReward(SessionRewardInfo rewardInfo, SessionInfo memberInfo) throws Exception;//한꺼번에 등록
	public void insertRewardOverview(SessionRewardInfo rewardInfo, SessionInfo memberInfo) throws Exception;
	
	public void insertRewardDetail(SessionRewardInfo rewardInfo, Reward reward) throws Exception ;
	public void insertRewardDetail(SessionRewardInfo rewardInfo, Map<Integer, Reward> rewards) throws Exception;
	public void insertRewardShppingLocation(RewardShippingLocation rewardShippingLocation) throws Exception;
	public void insertRewardBuyKakao(int buyNo, String tid) throws Exception;
}
