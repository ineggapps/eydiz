package com.eydiz.partner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
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
	public List<Partner> listPartner(Map<String, Object> map, int brandNo) {
		List<Partner> list = new ArrayList<>();
		
		try {
			list.addAll(dao.selectList("partner.listPartner",map));
			list.addAll(dao.selectOne("partner.brandTotalProject", brandNo));
			list.addAll(dao.selectOne("partner.brandTotalBuyMember", brandNo));
			list.addAll(dao.selectOne("partner.brandTotalAmount", brandNo));
			list.addAll(dao.selectOne("partner.memberImageUrl", brandNo));
			
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
	
	

	@Override
	public int totalBrandCount() {
		int result = 0;
		try {
			result = dao.selectOne("partner.totalBrandCount");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int totalProjectCount() {
		int result = 0;
		try {
			result = dao.selectOne("partner.totalProjectCount");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int totalBuyMemberCount() {
		int result = 0;
		try {
			result = dao.selectOne("partner.totalBuyMemberCount");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public double brandTotalAmount(int brandNo) {
		double result = 0;
		
		try {
			result = dao.selectOne("partner.brandTotalAmount", brandNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int brandTotalProject(int brandNo) {
		int result = 0;
		try {
			result = dao.selectOne("partner.brandTotalProject", brandNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String memberImageUrl(int brandNo) {
		String imageUrl = "";
		try {
			imageUrl = dao.selectOne("partner.memberImageUrl", brandNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return imageUrl;
	}

	@Override
	public int brandTotalBuyMember(int brandNo) {
		int result = 0;
		try {
			result = dao.selectOne("partner.brandTotalBuyMember", brandNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String getToday() {
		String today = "";
		
		Calendar now = Calendar.getInstance(Locale.KOREA);
		
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH)+1;
		int day = now.get(Calendar.DATE);
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);
		
		today = year+"."+month+"."+day+" "+hour+":"+minute+" 기준";	
		
		return today;
	}

	@Override
	public List<Partner> listProject(Map<String, Object> map) {
		List<Partner> plist = null;
		
		try {
			plist = dao.selectList("partner.listProject", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plist;
	}

	@Override
	public int pDataCount(Map<String, Object> map) {
		int result = 0;
		
		try {
			result = dao.selectOne("partner.pDataCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Partner getProjectMoneyInfo(int projectNo) {
		Partner vo = null;
		
		try {
			vo = dao.selectOne("partner.getProjectMoneyInfo", projectNo);
			if(vo == null) {
				vo = new Partner();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	
	

}
