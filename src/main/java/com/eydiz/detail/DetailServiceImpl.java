package com.eydiz.detail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eydiz.common.MyUtil;
import com.eydiz.common.dao.CommonDAO;
import com.eydiz.studio.Project;
import com.eydiz.studio.ProjectCommunity;
import com.eydiz.studio.Reward;

@Service("detail.detailService")
public class DetailServiceImpl implements DetailService, DetailConstant {

	@Autowired
	CommonDAO dao;

	@Autowired
	MyUtil myUtil;

	@Override
	public Project readProject(Map<String, Object> map) {
		Project project = null;
		try {
			project = dao.selectOne(MAPPER_NAMESPACE + "readProject", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return project;
	}

	@Override
	public List<Reward> listRewards(int projectNo) {
		List<Reward> list = null;
		try {
			list = dao.selectList(MAPPER_NAMESPACE + "listReward", projectNo);
			// remove tags
			for (Reward r : list) {
				removeRewardTags(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int countCommunityComments(int projectNo) {
		int count = 0;
		try {
			count = dao.selectOne(MAPPER_NAMESPACE + "countCommunityComment", projectNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<ProjectCommunity> listCommunityComments(int projectNo, int offset, int rows) {
		List<ProjectCommunity> list = null;
		try {
			Map<String, Integer> map = new HashMap<>();
			map.put(ATTRIBUTE_PROJECTNO, projectNo);
			map.put(ATTRIBUTE_ROWS, rows);
			map.put(ATTRIBUTE_OFFSET, offset);
			list = dao.selectList(MAPPER_NAMESPACE + "selectCommunityComment", map);
			for (ProjectCommunity community : list) {
				removeProjectCommunityTags(community);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProjectCommunity> listCommunityComments(int projectNo, Integer parentCommentNo) {
		List<ProjectCommunity> list = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(ATTRIBUTE_PROJECTNO, projectNo);
			map.put(ATTRIBUTE_PARENT_COMMENT_NO, parentCommentNo);
			list = dao.selectList(MAPPER_NAMESPACE + "selectCommunityCommentReply", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getNewCommentNo() {
		int commentNo = 0;
		try {
			commentNo = dao.selectOne(MAPPER_NAMESPACE + "getNewCommentNo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commentNo;
	}

	@Override
	public ProjectCommunity readCommunity(int commentNo) {
		ProjectCommunity community = null;
		try {
			community = dao.selectOne(MAPPER_NAMESPACE + "readCommunity", commentNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return community;
	}

	@Override
	public void insertCommunityComment(ProjectCommunity dto) throws Exception {
		try {
			removeProjectCommunityTags(dto);
			dao.insertData(MAPPER_NAMESPACE + "insertCommunity", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void updateCommunityComment(ProjectCommunity dto) throws Exception {
		try {
			removeProjectCommunityTags(dto);
			dao.updateData(MAPPER_NAMESPACE + "updateCommunity", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void deleteCommunityComment(ProjectCommunity dto) throws Exception {
		try {
			dao.deleteData(MAPPER_NAMESPACE + "deleteCommunity", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Reward readReward(int rewardNo) {
		Reward reward = null;
		try {
			reward = dao.selectOne(MAPPER_NAMESPACE + "readReward", rewardNo);
			removeRewardTags(reward);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reward;
	}

	@Override
	public List<Project> listPopularProject() {
		List<Project> list = null;
		try {
			list = dao.selectList(MAPPER_NAMESPACE + "listPopularProject");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean toggleLike(Map<String, Object> map) throws Exception {
		boolean didLike = false;
		try {
			int myLikeCount = dao.selectOne(MAPPER_NAMESPACE + "myProjectLikeCount", map);
			didLike = myLikeCount > 0 ? true : false;
			if (didLike) {
				// 좋아요를 했으면 삭제
				dao.deleteData(MAPPER_NAMESPACE + "deleteProjectLike", map);
				return false;
			} else {
				// 좋아요가 없으면 등록
				dao.insertData(MAPPER_NAMESPACE + "insertProjectLike", map);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void removeRewardTags(Reward reward) {
		try {
			reward.setRewardContent(myUtil.removeHtmlTag(reward.getRewardContent()));
			reward.setRewardTitle(myUtil.removeHtmlTag(reward.getRewardTitle()));
			reward.setRewardOption(myUtil.removeHtmlTag(reward.getRewardOption()));
		} catch (NullPointerException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeProjectCommunityTags(ProjectCommunity community) {
		try {
			community.setContent(myUtil.removeHtmlTag(community.getContent()));
			community.setContent(myUtil.htmlSymbols(community.getContent()));
		} catch (NullPointerException e) {
		} catch (Exception e) {
		}
	}

}
