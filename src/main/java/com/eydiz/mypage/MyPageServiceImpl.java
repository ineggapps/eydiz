package com.eydiz.mypage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eydiz.common.FileManager;
import com.eydiz.common.dao.CommonDAO;
import com.eydiz.member.Member;
import com.eydiz.member.MemberConstant;
import com.eydiz.studio.Project;
import com.eydiz.studio.Reward;
import com.eydiz.studio.StudioConstant;

@Service("mypage.myPageService")
public class MyPageServiceImpl implements MyPageService, MemberConstant, StudioConstant, MyPageConstant {

	@Autowired
	CommonDAO dao;

	@Autowired
	FileManager fileManager;

	@Override
	public void updateMyInfo(Member member) throws Exception {
		try {
			dao.updateData(MEMBER_NAMESPACE + "updateMyInfo", member);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public String uploadAvatarImage(Member member, MultipartFile uploadAvatar, String realPath, String uriPath)
			throws Exception {
		String newFilename = null;
		String uri = null;
		try {
			newFilename = fileManager.doFileUpload(uploadAvatar, realPath);
			uri = uriPath + "/" + newFilename;
			// 기존에 저장된 이미지 확인하기
			String savedAvatarImageUrl = member.getMemberImageUrl();
			if (savedAvatarImageUrl != null && savedAvatarImageUrl.length() > 0) {
				String filename = savedAvatarImageUrl.substring(savedAvatarImageUrl.lastIndexOf("/"));
				fileManager.doFileDelete(filename, realPath);
			}
			member.setMemberImageUrl(uri);
			dao.selectOne(MEMBER_NAMESPACE + "updateMemberImageUrl", member);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return uri;
	}

	@Override
	public int countBoughtMyProjects(int memberNo) {
		int count = 0;
		try {
			count = dao.selectOne(MyPageConstant.MAPPER_NAMESPACE + "countMyBoughProjects", memberNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Project> readBoughtMyProjects(int memberNo, int offset, int rows) {
		List<Project> list = null;
		Map<String, Integer> map = new HashMap<>();
		try {
			map.put(ATTRIBUTE_MEMBERNO, memberNo);
			map.put(ATTRIBUTE_OFFSET, offset);
			map.put(ATTRIBUTE_ROWS, rows);
			list = dao.selectList(MyPageConstant.MAPPER_NAMESPACE + "readMyBoughtProjects", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Project readBoughtMyProject(int memberNo, int buyNo) {
		Project project = null;
		Map<String, Object> map = new HashMap<>();
		try {
			map.put(ATTRIBUTE_MEMBERNO, memberNo);
			map.put(ATTRIBUTE_BUY_NO, buyNo);
			project = dao.selectOne(MyPageConstant.MAPPER_NAMESPACE + "readMyBoughtProject", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return project;
	}

	@Override
	public List<Reward> readBoughtMyReward(int memberNo, int buyNo) {
		List<Reward> rewards = null;
		Map<String, Object> map = new HashMap<>();
		try {
			map.put(ATTRIBUTE_MEMBERNO, memberNo);
			map.put(ATTRIBUTE_BUY_NO, buyNo);
			rewards = dao.selectList(MyPageConstant.MAPPER_NAMESPACE + "readMyBoughtRewards", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rewards;
	}

	@Override
	public void insertCancel(int memberNo, int buyNo, String memo) throws Exception {
		Map<String, Object> map = new HashMap<>();
		try {
			map.put(ATTRIBUTE_MEMBERNO, memberNo);
			map.put(ATTRIBUTE_BUY_NO, buyNo);
			map.put(ATTRIBUTE_MEMO, memo);
			dao.insertData(MyPageConstant.MAPPER_NAMESPACE + "insertCancelReward", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
