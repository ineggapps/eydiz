package com.eydiz.mypage;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eydiz.member.Member;
import com.eydiz.studio.Project;
import com.eydiz.studio.Reward;

public interface MyPageService {

	public void updateMyInfo(Member member) throws Exception;
	public String uploadAvatarImage(Member member, MultipartFile uploadAvatar, String realPath, String uriPath) throws Exception;
	
	//구매한 프로젝트 목록
	public int countBoughtMyProjects(int memberNo);
	public List<Project> readBoughtMyProjects(int memberNo, int offset, int rows);
	
	
	//구매한 프로젝트 상세 정보(리워드)
	public Project readBoughtMyProject(int memberNo, int buyNo);
	public List<Reward> readBoughtMyReward(int memberNo, int buyNo);
	
	//결제 취소
	public void insertCancel(int memberNo, int buyNo, String memo) throws Exception;
	
}
