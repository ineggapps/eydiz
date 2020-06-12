package com.eydiz.member;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eydiz.common.dao.CommonDAO;

@Service("member.memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private CommonDAO  dao;
	
	private final static String TABLE="member.";

	@Override
	public Member loginMember(String userId) {
		Member member = null;
		try {
			member = dao.selectOne(TABLE+"readMemberById");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	@Override
	public void insertMember(Member dto) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMembership(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMember(Member dto) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Member readMember(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member readMember(long memberIdx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMember(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Member> listMember(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
