package com.eydiz.studio;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eydiz.common.dao.CommonDAO;

@Service("studio.studioService")
public class StudioServiceImpl implements StudioService, StudioConstant {

	private Logger logger = LoggerFactory.getLogger(StudioService.class);
	
	@Autowired
	CommonDAO dao;

	@Override
	public Brand readBrand(int memberNo) {
		Brand dto = null;
		try {
			dto = dao.selectOne(MAPPER_NAMESPACE + "readBrandByMemberNo", memberNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public int myBrandCount(int memberNo) {
		int count = 0;
		try {
			count = dao.selectOne(MAPPER_NAMESPACE + "myBrandCount", memberNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	@Override
	public List<ProjectCategory> listCategory() {
		List<ProjectCategory> category = null;
		try {
			category = dao.selectList(MAPPER_NAMESPACE+"readAllProjectCategories");
		} catch (Exception e) {
			logger.error(e.getMessage());
			category = new ArrayList<>();
			category.add(new ProjectCategory(0,"카테고리 불러오기 실패"));
		}
		return category;
	}

	@Override
	public void updateBrand(Brand dto) throws Exception {
		try {
			if (dto == null) {
				throw new Exception("유효하지 않은 브랜드입니다.");
			}
			Brand brand = readBrand(dto.getMemberNo());
			if (brand == null) {
				// 새로 만들기
				dao.insertData(MAPPER_NAMESPACE + "insertBrand", dto);
			} else {
				// 수정하기
				dao.updateData(MAPPER_NAMESPACE + "updateBrand", dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
