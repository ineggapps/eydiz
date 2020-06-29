package com.eydiz.detail;

import java.util.List;

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
	public Project readProject(int projectNo) {
		Project project = null;
		try {
			project = dao.selectOne(MAPPER_NAMESPACE+"readProject", projectNo);
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
	
	
	
}
