package com.eydiz.reward;

import java.util.Map;

import com.eydiz.studio.Reward;

public class SessionRewardInfo {
	private int projectNo;
	private int totalAmount;
	private Map<Integer, Reward> rewards;
	private RewardShippingLocation rewardShippingLocation;

	public int getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(int projectNo) {
		this.projectNo = projectNo;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Map<Integer, Reward> getRewards() {
		return rewards;
	}

	public void setRewards(Map<Integer, Reward> rewards) {
		this.rewards = rewards;
	}

	public RewardShippingLocation getRewardShippingLocation() {
		return rewardShippingLocation;
	}

	public void setRewardShippingLocation(RewardShippingLocation rewardShippingLocation) {
		this.rewardShippingLocation = rewardShippingLocation;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		if(rewards!=null) {
			for(Integer key: rewards.keySet()) {
				s.append("==============");
				s.append(key+"...");
				s.append(rewards.get(key));
			}
		}
		return "SessionRewardInfo [projectNo=" + projectNo + ", totalAmount=" + totalAmount + ", rewards=" + s.toString()
				+ ", rewardShippingLocation=" + rewardShippingLocation + "]";
	}

	
}
