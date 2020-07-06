package com.eydiz.admin.member;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eydiz.common.dao.CommonDAO;

@Service("member.adminMemberService")
public class AdminMemberServiceImpl implements AdminMemberService{
	
	@Autowired
	private CommonDAO dao;
	
	@Override
	public List<AdminMember> mainAdminMember(Map<String, Object> map) {
		List<AdminMember> main = null;
		
		try {
			main = dao.selectList("adminMember.main", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return main;
	}

	@Override
	public List<AdminMember> listAdminMember(Map<String, Object> map) {
		List<AdminMember> list = null;
		
		try {
			list = dao.selectList("adminMember.list", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int maindataCount(Map<String, Object> map) {
		int result = 0;
		
		try {
			result = dao.selectOne("adminMember.maindataCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int listdataCount(Map<String, Object> map) {
		int result = 0;
		
		try {
			result = dao.selectOne("adminMember.listdataCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
