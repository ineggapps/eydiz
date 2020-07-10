package com.eydiz.mypage;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eydiz.member.Member;
import com.eydiz.studio.Project;

public interface MyPageService {

	public void updateMyInfo(Member member) throws Exception;
	public String uploadAvatarImage(Member member, MultipartFile uploadAvatar, String realPath, String uriPath) throws Exception;
	
	public int countBoughtMyProjects(int memberNo);
	public List<Project> readBoughtMyProjects(int memberNo, int offset, int rows);
}
