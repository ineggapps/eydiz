package com.eydiz.partner;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eydiz.common.dao.CommonDAO;

@Service("partner.partnerService")
public class PartnerServiceImpl implements PartnerService{
	@Autowired
	CommonDAO dao;
	
	@Override
	public int dataCount(Map<String, Object> map) {
		int result = 0;
		try {
			result = dao.selectOne("partner.dataCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return result;
	}

	@Override
	public List<Partner> listPartner(Map<String, Object> map) {
		List<Partner> list = null;
		
		try {
			list = dao.selectList("partner.listPartner",map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public Partner readPartner(int brandNo) {
		Partner dto = null;
		try {
			dto = dao.selectOne("partner.readPartner", brandNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	

}
