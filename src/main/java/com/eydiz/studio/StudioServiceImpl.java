package com.eydiz.studio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eydiz.common.dao.CommonDAO;
import com.eydiz.member.MemberConstant;


@Service("studio.studioService")
public class StudioServiceImpl implements StudioService, StudioConstant{

	@Autowired
	CommonDAO dao;
	
	@Override
	public Brand readBrand(int memberNo) {
		Brand dto = null;
		try {
			dto = dao.selectOne(MAPPER_NAMESPACE+"readBrandByMemberNo",memberNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public void updateBrand(Brand dto) throws Exception {
		try {
			if(dto==null) {
				throw new Exception("유효하지 않은 브랜드입니다.");
			}
			Brand brand = readBrand(dto.getMemberNo());
			if(brand==null) {
				//새로 만들기
				dao.insertData(MAPPER_NAMESPACE+"insertBrand", dto);
			}
			else {
				//수정하기
				dao.updateData(MAPPER_NAMESPACE+"updateBrand", dto);				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
