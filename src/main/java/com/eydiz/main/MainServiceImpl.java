package com.eydiz.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eydiz.common.dao.CommonDAO;

@Service("main.mainService")
public class MainServiceImpl implements MainService, MainConstant {

	@Autowired
	private CommonDAO dao;
	
	@Override
	public List<ProjectCategory> listProjectCategoryOfFunding() {
		List<ProjectCategory> list = null;
		try {
			list = dao.selectList(MAPPER_NAMESPACE+"listCategoryOfFunding");
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
			list = dao.selectList(MAPPER_NAMESPACE+"listFunding");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
}
