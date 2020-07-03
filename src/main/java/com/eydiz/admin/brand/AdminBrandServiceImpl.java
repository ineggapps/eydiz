package com.eydiz.admin.brand;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eydiz.common.dao.CommonDAO;

@Service("brand.adminBrandService")
public class AdminBrandServiceImpl implements AdminBrandService{
	
	@Autowired
	private CommonDAO dao;
	
	@Override
	public List<Brand> listBrand(Map<String, Object> map) {
		List<Brand> list = null;
		try {
			list = dao.selectList("adminBrand.listBrand", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		int result = 0;

		try {
			result = dao.selectOne("adminBrand.dataCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Brand readBrandList(int brandNo) {
		Brand dto = null;

		try {
			dto = dao.selectOne("adminBrand.readBrandList", brandNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

}
