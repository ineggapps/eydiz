package com.eydiz.member;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eydiz.common.dao.CommonDAO;

@Service("member.memberService")
public class MemberServiceImpl implements MemberService, MemberConstant {
	@Autowired
	private CommonDAO dao;

	private final static String TABLE = "member.";

	@Override
	public Member loginMember(Member dto) throws AuthenticationException {
		Member member = null;
		try {
			member = dao.selectOne(TABLE + "confirmMember", dto);
			if (member == null) {
				// 로그인 실패 시 정보가 뜨지 않음.
				throw new AuthenticationException("아이디나 비밀번호가 틀렸습니다.");
			}
		} catch (AuthenticationException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	@Override
	public boolean isTaken(String memberId) {
		boolean isTaken = true;
		try {
			Member dto = (Member) dao.selectOne(TABLE + "readMemberById", memberId);
			if (dto == null) {
				isTaken = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isTaken;
	}

	@Override
	public void insertMember(Member dto) throws Exception {
		try {
			dao.insertData(TABLE + "insertMember", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
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
