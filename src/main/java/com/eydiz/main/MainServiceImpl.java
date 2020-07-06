package com.eydiz.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eydiz.common.dao.CommonDAO;
import com.eydiz.studio.Project;

@Service("main.mainService")
public class MainServiceImpl implements MainService, MainConstant {

	@Autowired
	private CommonDAO dao;

	@Override
	public List<ProjectCategory> listProjectCategory() {
		List<ProjectCategory> list = null;
		try {
			list = dao.selectList(MAPPER_NAMESPACE + "listCategory");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProjectCategory> listProjectCategoryOfFunding() {
		List<ProjectCategory> list = null;
		try {
			list = dao.selectList(MAPPER_NAMESPACE + "listCategoryOfFunding");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int dataProjectCount() {
		return 0;
	}

	@Override
	public List<Project> listProject() {
		List<Project> list = null;
		try {
			list = dao.selectList(MAPPER_NAMESPACE + "listFunding");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Project> listProject(int categoryNo) {
		Map<String, Object> map = new HashMap<>();
		List<Project> list = null;
		try {
			map.put("categoryNo", categoryNo);
			list = dao.selectList(MAPPER_NAMESPACE + "listFunding", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
