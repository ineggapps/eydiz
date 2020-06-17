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
	public boolean isTakenId(String memberId) {
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
	public boolean isTakenEmail(String memberEmail) {
		boolean isTaken = true;
		try {
			Member dto = (Member)dao.selectOne(TABLE+"readMemberByEmail",memberEmail);
			if(dto==null) {
				isTaken = false;
			}
		} catch (Exception e) {
			isTaken = true;
			e.printStackTrace();

		}
		
		return isTaken;
	}

	@Override
	public boolean isValidateNewMember(Member dto) throws JoinException {//회원가입 유효성 검사
		//id, nickname, pwd, email만 있으면 된다.
		boolean result = true;
		try {
			if(dto==null) {
				throw new JoinException("신규 회원정보가 아무것도 입력되지 않았습니다.");
			}
			else if(dto.getMemberId()== null || dto.getMemberId().length()==0) {
				throw new JoinException("아이디가 입력되지 않았습니다.");
			}else if(isTakenId(dto.getMemberId())) {
				throw new JoinException("중복된 아이디입니다.");
			}else if(dto.getMemberNickname()==null || dto.getMemberNickname().length()==0) {
				throw new JoinException("닉네임이 입력되지 않았습니다.");
			}
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public void insertMember(Member dto) throws JoinException,Exception {//회원가입
		try {
			isValidateNewMember(dto);
			dao.insertData(TABLE + "insertMember", dto);
		}catch(JoinException e) {
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) {
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
