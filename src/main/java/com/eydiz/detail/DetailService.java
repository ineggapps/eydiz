package com.eydiz.detail;

import java.util.List;
import java.util.Map;

import com.eydiz.studio.Project;
import com.eydiz.studio.Reward;

public interface DetailService {
	public Project readProject(Map<String,Object> map);
	public List<Reward> listRewards(int projectNo);
	public Reward readReward(int rewardNo);
	
	public List<Project> listPopularProject();
	
	public boolean toggleLike(Map<String, Object> map) throws Exception;//false: 좋아요 비활성화, true: 좋아요 활성화
}
