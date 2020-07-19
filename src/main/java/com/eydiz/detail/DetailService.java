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
	public int getNewCommentNo(); //새로운 덧글번호 조회 (hasFunded, isBrand 조회를 위해 echo해줘야 하므로 따로 번호 추출하여 작업)
	public int countCommunityComments(int projectNo);//최상위 댓글 개수 조회
	public List<ProjectCommunity> listCommunityComments(int projectNo, int offset, int rows);
	public ProjectCommunity readCommunity(int commentNo);
	//답글 조회
	public List<ProjectCommunity> listCommunityComments(int projectNo, Integer parentCommentNo);
	public void insertCommunityComment(ProjectCommunity dto) throws Exception;
	public void updateCommunityComment(ProjectCommunity dto) throws Exception;
	public void deleteCommunityComment(ProjectCommunity dto) throws Exception;

	public List<Project> listPopularProject();
	
	public boolean toggleLike(Map<String, Object> map) throws Exception;//false: 좋아요 비활성화, true: 좋아요 활성화
	
	//내부연산
	public void removeRewardTags(Reward reward);
	public void removeProjectCommunityTags(ProjectCommunity community);
}
