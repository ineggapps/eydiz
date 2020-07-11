package com.eydiz.brand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eydiz.common.dao.CommonDAO;
import com.eydiz.studio.Brand;
import com.eydiz.studio.Project;
import com.eydiz.studio.StudioConstant;

@Service("brand.brandService")
public class BrandServiceImpl implements BrandService, StudioConstant {

	@Autowired
	CommonDAO dao;

	private static final String MAPPER_NAMESPACE = "brand.";

	@Override
	public Brand readBrand(int brandNo) {
		Brand brand = null;
		try {
			brand = dao.selectOne("readBrand", brandNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return brand;
	}

	@Override
	public int listMadeProjectCount(int brandNo) {
		int count = 0;
		try {
			count = dao.selectOne("countMadeProject", brandNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Project> listMadeProject(int brandNo, int offset, int rows) {
		List<Project> list = null;
		try {
			Map<String, Object> map = new HashedMap<>();
			map.put(ATTRIBUTE_BRANDNO, brandNo);
			map.put(ATTRIBUTE_OFFSET, offset);
			map.put(ATTRIBUTE_ROWS, rows);
			list = dao.selectList(MAPPER_NAMESPACE + "listMadeProject", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int listFundedProjectCount(int brandNo, int memberNo) {
		int count = 0;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(ATTRIBUTE_BRANDNO, brandNo);
			map.put(ATTRIBUTE_MEMBERNO, memberNo);
			count = dao.selectOne(MAPPER_NAMESPACE + "countFundedProject", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Project> listFundedProject(int brandNo, int memberNo, int offset, int rows) {
		List<Project> list = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(ATTRIBUTE_BRANDNO, brandNo);
			map.put(ATTRIBUTE_MEMBERNO, memberNo);
			map.put(ATTRIBUTE_OFFSET, offset);
			map.put(ATTRIBUTE_ROWS, rows);
			list = dao.selectList(MAPPER_NAMESPACE + "listFundedProject", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
