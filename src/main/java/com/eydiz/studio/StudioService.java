package com.eydiz.studio;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface StudioService {
	//브랜드
	public Brand readBrand(int memberNo);
	public int myBrandCount(int memberNo);
	public void updateBrand(Brand dto) throws Exception;

	//프로젝트
	public int insertEmptyProject(Project project) throws Exception;
	public Project readProject(int projectNo);
	public List<ProjectCategory> listCategory(); 
	public String uploadProjectImage(MultipartFile uploadImage, String realPath) throws Exception;
	
	//프로젝트 해시태그 조회/등록/삭제
	public List<ProjectHashtag> readHashtag(int projectNo);
	public void insertHashtag(ProjectHashtag hashtag) throws Exception;
	public void deleteHashtag(ProjectHashtag hashtag) throws Exception;
}
