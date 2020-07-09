package com.eydiz.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eydiz.common.FileManager;
import com.eydiz.common.dao.CommonDAO;
import com.eydiz.member.Member;

@Service("mypage.myPageService")
public class MyPageServiceImpl implements MyPageService {

	@Autowired
	CommonDAO dao;

	@Autowired
	FileManager fileManager;
	
	private final static String MEMBER_NAMESPACE = "member.";

	@Override
	public void updateMyInfo(Member member) throws Exception {
		try {
			dao.updateData(MEMBER_NAMESPACE+"updateMyInfo", member);
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
			dao.selectOne(MEMBER_NAMESPACE+"updateMemberImageUrl", member);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return uri;
	}

}
