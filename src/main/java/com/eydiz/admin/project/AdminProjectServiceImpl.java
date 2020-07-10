package com.eydiz.admin.project;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eydiz.common.dao.CommonDAO;
import com.eydiz.studio.Project;
import com.eydiz.studio.Reward;

@Service("project.adminProjectServiceImpl")
public class AdminProjectServiceImpl implements AdminProjectService{
	
	@Autowired
	CommonDAO dao;
	
	@Override
	public List<AdminProject> adminProjectList(Map<String, Object> map) {
		List<AdminProject> list = null;
		
		try {
			list = dao.selectList("adminProject.adminProjectList", map);
		} catch (Exception e) {
		}
		
		return list;
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		int result = 0;
		try {
			result = dao.selectOne("adminProject.dataCount", map);
		} catch (Exception e) {
		}
		return result;
	}

	@Override
	public void updateProject(Map<String, Object> map) throws Exception {
		try {
			dao.updateData("adminProject.updateProject", map);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void insertProjectStatusList(AdminProject dto) throws Exception {
		try {
			dao.insertData("adminProject.insertProjectStatusList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Project readProject(int projectNo) {
		Project project = null;
		try {
			project = dao.selectOne("adminProject.readProject", projectNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return project;
	}

	@Override
	public List<Reward> listRewards(int projectNo) {
		List<Reward> list = null;
		try {
			list = dao.selectList("adminProject.listReward", projectNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
