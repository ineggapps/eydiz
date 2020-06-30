package com.eydiz.detail;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eydiz.common.dao.CommonDAO;
import com.eydiz.studio.Project;
import com.eydiz.studio.Reward;

@Service("detail.detailService")
public class DetailServiceImpl implements DetailService, DetailConstant{

	@Autowired
	CommonDAO dao;
	
	@Override
	public Project readProject(Map<String, Object> map) {
		Project project = null;
		try {
			project = dao.selectOne(MAPPER_NAMESPACE+"readProject", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return project;
	}

	@Override
	public List<Reward> listRewards(int projectNo) {
		List<Reward> list = null;
		try {
			list = dao.selectList(MAPPER_NAMESPACE+"listReward", projectNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Project> listPopularProject() {
		List<Project> list = null;
		try {
			list = dao.selectList(MAPPER_NAMESPACE+"listPopularProject");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	@Override
	public boolean toggleLike(Map<String, Object> map) throws Exception{
		boolean didLike = false;
		try {
			int myLikeCount = dao.selectOne(MAPPER_NAMESPACE+"myProjectLikeCount", map);
			didLike = myLikeCount>0?true:false;
			if(didLike) {
				//좋아요를 했으면 삭제
				dao.deleteData(MAPPER_NAMESPACE+"deleteProjectLike", map);
				return false;
			}else {
				//좋아요가 없으면 등록
				dao.insertData(MAPPER_NAMESPACE+"insertProjectLike", map);
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	
	
}