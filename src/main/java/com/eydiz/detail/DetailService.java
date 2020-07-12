package com.eydiz.detail;

import java.util.List;
import java.util.Map;

import com.eydiz.studio.Project;
import com.eydiz.studio.ProjectCommunity;
import com.eydiz.studio.Reward;

public interface DetailService {
	public Project readProject(Map<String,Object> map);
	public List<Reward> listRewards(int projectNo);
	public Reward readReward(int rewardNo);
	
	//커뮤니티
	public List<ProjectCommunity> listCommunityComments(int projectNo);
	public List<ProjectCommunity> listCommunityComments(int projectNo, Integer parentCommentNo);
	public void insertCommunityComment(ProjectCommunity dto) throws Exception;
	public void updateCommunityComment(ProjectCommunity dto) throws Exception;
	public void deleteCommunityComment(ProjectCommunity dto) throws Exception;

	public List<Project> listPopularProject();
	
	public boolean toggleLike(Map<String, Object> map) throws Exception;//false: 좋아요 비활성화, true: 좋아요 활성화
}
