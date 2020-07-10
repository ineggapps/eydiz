package com.eydiz.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eydiz.common.Constant;
import com.eydiz.common.dao.CommonDAO;
import com.eydiz.studio.Project;

@Service("main.mainService")
public class MainServiceImpl implements Constant, MainService, MainConstant {

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
	public String selectCategoryName(int categoryNo) {
		String categoryName = null;
		try {
			categoryName = dao.selectOne(MAPPER_NAMESPACE+"selectCategoryName", categoryNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryName;
	}

	@Override
	public int dataProjectCount() {
		return dataProjectCount(null);
	}

	@Override
	public int dataProjectCount(Integer categoryNo) {
		int count = 0;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put(ATTRIBUTE_CATEGORYNO, categoryNo);
			count = dao.selectOne(MAPPER_NAMESPACE + "fundingDataCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

//	@Override
//	public List<Project> listProject() {
//		List<Project> list = null;
//		try {
//			list = dao.selectList(MAPPER_NAMESPACE + "listFunding");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

//	@Override
//	public List<Project> listProject(int categoryNo) {
//		Map<String, Object> map = new HashMap<>();
//		List<Project> list = null;
//		try {
//			map.put("categoryNo", categoryNo);
//			list = dao.selectList(MAPPER_NAMESPACE + "listFunding", map);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

	@Override
	public List<Project> listProject(int offset, int rows) {
		return listProject(null, offset, rows);
	}

	@Override
	public List<Project> listProject(Integer categoryNo, int offset, int rows) {
		Map<String, Object> map = new HashMap<>();
		List<Project> list = null;
		try {
			map.put(ATTRIBUTE_CATEGORYNO, categoryNo);
			map.put(ATTRIBUTE_OFFSET, offset);
			map.put(ATTRIBUTE_ROWS, rows);
			list = dao.selectList(MAPPER_NAMESPACE + "listFunding", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
