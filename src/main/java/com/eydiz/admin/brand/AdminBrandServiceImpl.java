package com.eydiz.admin.brand;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eydiz.common.dao.CommonDAO;
import com.eydiz.studio.Brand;
import com.eydiz.studio.Project;

@Service("brand.adminBrandService")
public class AdminBrandServiceImpl implements AdminBrandService{
	
	@Autowired
	private CommonDAO dao;
	
	@Override
	public List<Brand> listBrand(Map<String, Object> map) {
		List<Brand> list = null;
		try {
			dao.selectList("brand.listBrand", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Project> listProject(Map<String, Object> map) {
		List<Project> list = null;
		try {
			dao.selectList("brand.listProject", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
