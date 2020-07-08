package com.eydiz.reward;

import java.util.Map;

import com.eydiz.reward.kakao.KakaoPayApproval;
import com.eydiz.studio.Reward;

public class SessionRewardInfo {
	private int buyNo; //카카오페이에서 사전에 주문번호를 읽어와야 함. rewardService에서 미리 할당받자
	private int projectNo;
	private int totalAmount;
	private int shipAmount;
	private int finalAmount;// 최종 금액 (펀딩금액 + 최종금액)
	private Map<Integer, Reward> rewards;
	private RewardShippingLocation rewardShippingLocation;
	private KakaoPayApproval kakaoPayApproval;

	public int getBuyNo() {
		return buyNo;
	}

	public void setBuyNo(int buyNo) {
		this.buyNo = buyNo;
	}

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

	public int getShipAmount() {
		return shipAmount;
	}

	public void setShipAmount(int shipAmount) {
		this.shipAmount = shipAmount;
	}

	public int getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(int finalAmount) {
		this.finalAmount = finalAmount;
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

	public KakaoPayApproval getKakaoPayApproval() {
		return kakaoPayApproval;
	}

	public void setKakaoPayApproval(KakaoPayApproval kakaoPayApproval) {
		this.kakaoPayApproval = kakaoPayApproval;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (rewards != null) {
			for (Integer key : rewards.keySet()) {
				s.append("==============");
				s.append(key + "...");
				s.append(rewards.get(key));
			}
		}
		return "SessionRewardInfo [projectNo=" + projectNo + ", totalAmount=" + totalAmount + ", rewards="
				+ s.toString() + ", rewardShippingLocation=" + rewardShippingLocation + "]";
	}

}
