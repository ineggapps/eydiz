package com.eydiz.studio;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface StudioService {
	//브랜드
	public Brand readBrand(int memberNo);
	public int myBrandCount(int memberNo);
	public void updateBrand(Brand dto) throws Exception;

	//프로젝트
	public int insertEmptyProject(Project project) throws Exception;
	public List<Project> listProject(Map<String, Object> map);
	public int listProjectCount(int brandNo);
	public Project readProject(int projectNo, int brandNo);
	public List<ProjectCategory> listCategory(); 
	public String uploadProjectImage(Project project, MultipartFile uploadImage, String realPath, String uriPath) throws Exception;
	public void updateProjectBasic(Project project) throws Exception;
	public void deleteProjectImage(Project project, String realPath) throws Exception;
	public void deleteProject(int projectNo, int brandNo) throws Exception;
	
	//프로젝트 해시태그 조회/등록/삭제
	public List<ProjectHashtag> readHashtag(int projectNo);
	public void insertHashtag(ProjectHashtag hashtag) throws Exception;
	public void deleteHashtag(ProjectHashtag hashtag) throws Exception;
	
	
	//리워드
	public void insertReward(Reward reward) throws Exception;
	public List<Reward> selectReward(int projectNo, int brandNo);
	public void updateReward(Reward reward) throws Exception;
	public void deleteReward(int rewardNo, int projectNo, int brandNo) throws Exception;
	
	//스토리
	public void updateStory(Project project) throws Exception;
	
	//제출
	public void updateProjectStatus(int projectNo, int brandNo, int statusNo);
	
	// 새소식 ----------------------------- 
	public void insertProjectNews(ProjectNews dto) throws Exception;
	public List<ProjectNews> listProjectNews(Map<String, Object> map);
	public int dataCount(Map<String, Object> map);
	public ProjectNews readProjectNews(int newsNo, int projectNo);
	public void updateProjectNews(ProjectNews dto) throws Exception;
	
}
