package com.eydiz.reward;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eydiz.common.dao.CommonDAO;
import com.eydiz.member.SessionInfo;
import com.eydiz.studio.Reward;
import com.eydiz.studio.RewardOverview;

@Service("reward.rewardService")
public class RewardServiceImpl implements RewardService, RewardConstant {

	@Autowired
	private CommonDAO dao;

	@Override
	public int nextBuyNo() {
		int buyNo = 0;
		try {
			buyNo = dao.selectOne(MAPPER_NAMESPACE + "nextBuyNo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buyNo;
	}

	@Override
	public void insertReward(SessionRewardInfo rewardInfo, SessionInfo memberInfo) throws Exception {
		try {
			insertRewardOverview(rewardInfo, memberInfo);
			insertRewardDetail(rewardInfo, rewardInfo.getRewards());
			if(rewardInfo.isAnyShipping()) {
				rewardInfo.getRewardShippingLocation().setBuyNo(rewardInfo.getBuyNo());
				insertRewardShppingLocation(rewardInfo.getRewardShippingLocation());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void insertRewardOverview(SessionRewardInfo rewardInfo, SessionInfo memberInfo) throws Exception {
		try {
			RewardOverview rewardOverview = new RewardOverview(rewardInfo.getBuyNo(), rewardInfo.getProjectNo(),
					memberInfo.getMemberNo(), rewardInfo.getTotalAmount(), rewardInfo.getShipAmount(), null);
			dao.insertData(MAPPER_NAMESPACE + "insertRewardBuyOverview", rewardOverview);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void insertRewardDetail(SessionRewardInfo rewardInfo, Reward reward) throws Exception {
		try {
			int buyNo = rewardInfo.getBuyNo();
			reward.setBuyNo(buyNo);
			dao.insertData(MAPPER_NAMESPACE + "insertRewardBuyDetail", reward);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void insertRewardDetail(SessionRewardInfo rewardInfo, Map<Integer, Reward> rewards) throws Exception {
		try {
			for (Integer key : rewards.keySet()) {
				insertRewardDetail(rewardInfo, rewards.get(key));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void insertRewardShppingLocation(RewardShippingLocation rewardShippingLocation) throws Exception {
		try {
			dao.insertData(MAPPER_NAMESPACE+"insertRewardShippingLocation", rewardShippingLocation);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void insertRewardBuyKakao(int buyNo, String tid) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("buyNo", buyNo);
			map.put("tid", tid);
			dao.insertData(MAPPER_NAMESPACE+"insertRewardBuyKakao", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	

}
