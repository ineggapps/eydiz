package com.eydiz.studio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eydiz.common.FileManager;
import com.eydiz.common.dao.CommonDAO;

@Service("studio.studioService")
public class StudioServiceImpl implements StudioService, StudioConstant {

	private Logger logger = LoggerFactory.getLogger(StudioService.class);

	@Autowired
	FileManager fileManager;

	@Autowired
	CommonDAO dao;

	@Override
	public Brand readBrand(int memberNo) {
		Brand dto = null;
		try {
			dto = dao.selectOne(MAPPER_NAMESPACE + "readBrandByMemberNo", memberNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public int myBrandCount(int memberNo) {
		int count = 0;
		try {
			count = dao.selectOne(MAPPER_NAMESPACE + "myBrandCount", memberNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public void updateBrand(Brand dto) throws Exception {
		try {
			if (dto == null) {
				throw new Exception("유효하지 않은 브랜드입니다.");
			}
			Brand brand = readBrand(dto.getMemberNo());
			if (brand == null) {
				// 새로 만들기
				dao.insertData(MAPPER_NAMESPACE + "insertBrand", dto);
			} else {
				// 수정하기
				dao.updateData(MAPPER_NAMESPACE + "updateBrand", dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	////////// 프로젝트

	@Override
	public int insertEmptyProject(Project project) throws Exception {
		int projectNo = 0;
		try {
			projectNo = dao.selectOne(MAPPER_NAMESPACE + "getNewProjectNo");
			project.setProjectNo(projectNo);
			dao.insertData(MAPPER_NAMESPACE + "insertEmptyProject", project);
		} catch (Exception e) {
			logger.error(e.getMessage());
			projectNo = 0;
			throw e;
		}
		return projectNo;
	}

	@Override
	public List<Project> listProject(Map<String, Object> map) {
		List<Project> list = null;
		try {
			list = dao.selectList(MAPPER_NAMESPACE + "listProject", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int listProjectCount(int brandNo) {
		Map<String, Object> map = new HashMap<>();
		int count = 0;
		try {
			map.put(ATTRIBUTE_BRANDNO, brandNo);
			count = dao.selectOne(MAPPER_NAMESPACE + "listProjectCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public Project readProject(int projectNo, int brandNo) {
		Project project = null;
		try {
			Map<String, Integer> map = new HashMap<>();
			map.put(ATTRIBUTE_PROJECTNO, projectNo);
			map.put(ATTRIBUTE_BRANDNO, brandNo);
			project = dao.selectOne(MAPPER_NAMESPACE + "readProject", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return project;
	}

	@Override
	public List<ProjectCategory> listCategory() {
		List<ProjectCategory> category = null;
		try {
			category = dao.selectList(MAPPER_NAMESPACE + "readAllProjectCategories");
		} catch (Exception e) {
			logger.error(e.getMessage());
			category = new ArrayList<>();
			category.add(new ProjectCategory(0, "카테고리 불러오기 실패"));
		}
		return category;
	}

	@Override
	public void updateProjectBasic(Project project) throws Exception {
		try {
			dao.updateData(MAPPER_NAMESPACE + "updateProjectBasic", project);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public String uploadProjectImage(Project project, MultipartFile uploadImage, String realPath, String uriPath)
			throws Exception {
		String newFilename = null;
		String uri = null;
		try {
			newFilename = fileManager.doFileUpload(uploadImage, realPath);
			uri = uriPath + "/" + newFilename;
			// 기존에 저장되어 있는 이미지가 있는지 확인하기
			String savedProjectImageUrl = project.getProjectImageUrl();
			if (savedProjectImageUrl != null && savedProjectImageUrl.length() > 0) {
				String filename = savedProjectImageUrl.substring(savedProjectImageUrl.lastIndexOf("/"));
				fileManager.doFileDelete(filename, realPath);
			}
			project.setProjectImageUrl(uri);
			dao.selectOne(MAPPER_NAMESPACE + "updateProjectImage", project);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return uri;
	}

	@Override
	public void deleteProjectImage(Project project, String realPath) throws Exception {
		try {
			if (project.getProjectImageUrl() != null && project.getProjectImageUrl().length() > 0) {
				// 저장된 것이 유효하다면 이미지 삭제하기
				String filename = project.getProjectImageUrl().substring(project.getProjectImageUrl().lastIndexOf("/"));
				fileManager.doFileDelete(filename, realPath);
				dao.updateData(MAPPER_NAMESPACE + "updateNullProjectImage", project);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void deleteProject(int projectNo, int brandNo) throws Exception {
		try {
			Map<String, Integer> map = new HashMap<>();
			map.put(ATTRIBUTE_BRANDNO, brandNo);
			map.put(ATTRIBUTE_PROJECTNO, projectNo);
			dao.deleteData(MAPPER_NAMESPACE + "deleteHashtags", map);
			dao.deleteData(MAPPER_NAMESPACE + "deleteRewards", map);
			dao.deleteData(MAPPER_NAMESPACE + "deleteProjectList", map);
			dao.deleteData(MAPPER_NAMESPACE + "deleteProject", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/// 해시태그

	@Override
	public List<ProjectHashtag> readHashtag(int projectNo) {
		List<ProjectHashtag> tags = null;
		try {
			tags = dao.selectList(MAPPER_NAMESPACE + "readHashtag", projectNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tags;
	}

	@Override
	public void insertHashtag(ProjectHashtag hashtag) throws Exception {
		try {
			dao.insertData(MAPPER_NAMESPACE + "insertHashtag", hashtag);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void deleteHashtag(ProjectHashtag hashtag) throws Exception {
		try {
			dao.deleteData(MAPPER_NAMESPACE + "deleteHashTag", hashtag);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// 리워드

	@Override
	public void insertReward(Reward reward) throws Exception {
		try {
			dao.insertData(MAPPER_NAMESPACE + "insertReward", reward);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Reward> selectReward(int projectNo, int brandNo) {
		List<Reward> rewards = null;
		try {
			Map<String, Integer> map = new HashMap<>();
			map.put(ATTRIBUTE_PROJECTNO, projectNo);
			map.put(ATTRIBUTE_BRANDNO, brandNo);
			rewards = dao.selectList(MAPPER_NAMESPACE + "selectReward", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rewards;
	}

	@Override
	public void updateReward(Reward reward) throws Exception {
		try {
			dao.updateData(MAPPER_NAMESPACE + "updateReward", reward);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void deleteReward(int rewardNo, int projectNo, int brandNo) throws Exception {
		try {
			Map<String, Integer> map = new HashMap<>();
			map.put(ATTRIBUTE_PROJECTNO, projectNo);
			map.put(ATTRIBUTE_BRANDNO, brandNo);
			map.put(ATTRIBUTE_REWARDNO, rewardNo);
			dao.deleteData(MAPPER_NAMESPACE + "deleteReward", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void updateStory(Project project) throws Exception {
		// 스토리 고치기
		try {
			dao.updateData(MAPPER_NAMESPACE + "updateStory", project);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void updateProjectStatus(int projectNo, int brandNo, int statusNo) throws Exception {
		try {
			Map<String, Integer> map = new HashMap<>();
			map.put(ATTRIBUTE_BRANDNO, brandNo);
			map.put(ATTRIBUTE_PROJECTNO, projectNo);
			map.put(ATTRIBUTE_STATUSNO, statusNo);
			dao.updateData(MAPPER_NAMESPACE + "updateProjectStatus", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void insertProjectStatusList(int projectNo, int brandNo, int statusNo, String memo) throws Exception {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put(ATTRIBUTE_PROJECTNO, projectNo);
			map.put(ATTRIBUTE_BRANDNO, brandNo);
			map.put(ATTRIBUTE_STATUSNO, statusNo);
			map.put(ATTRIBUTE_STATUS_MEMO, memo);
			dao.updateData(MAPPER_NAMESPACE + "insertProjectStatusList", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	// 새소식 -------------------------------------------

	@Override
	public void insertProjectNews(ProjectNews dto) throws Exception {
		try {
			dao.insertData("studio.insertProjectNews", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<ProjectNews> listProjectNews(Map<String, Object> map) {
		List<ProjectNews> list = null;

		try {
			list = dao.selectList("studio.listProjectNews", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		int result = 0;

		try {
			result = dao.selectOne("studio.dataCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public ProjectNews readProjectNews(int newsNo) {
		ProjectNews dto = null;

		try {
			dto = dao.selectOne("studio.readProjectNews", newsNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	@Override
	public void updateProjectNews(ProjectNews dto) throws Exception {
		try {
			dao.updateData("studio.updateProjectNews", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void deleteProjectNews(int newsNo) throws Exception {
		try {
			dao.deleteData("studio.deleteProjectNews", newsNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	// 배송 관리
	@Override
	public List<Send> listSend(Map<String, Object> map) {
		List<Send> list = null;

		try {
			list = dao.selectList("studio.listSend", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Send> listSendmanage(Map<String, Object> map) {
		List<Send> list = null;

		try {
			list = dao.selectList("studio.listSendmanage", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int sendDataCount(Map<String, Object> map) {
		int result = 0;

		try {
			result = dao.selectOne("studio.sendDataCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int manageDataCount(Map<String, Object> map) {
		int result = 0;

		try {
			result = dao.selectOne("studio.manageDataCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Send readSend(int projectNo , int buyNo) {
		Send dto = null;

		try {
			Map<String, Object> map = new HashMap<>();
			map.put("projectNo", projectNo);
			map.put("buyNo", buyNo);
			
			dto = dao.selectOne("studio.readSend", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public void updateStatus(int buyNo) throws Exception {
		try {
			dao.updateData("studio.updateStatusNo", buyNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Send> listSendready(Map<String, Object> map) {
		List<Send> list = null;

		try {
			list = dao.selectList("studio.listSendready", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int readyDataCount(Map<String, Object> map) {
		int result = 0;

		try {
			result = dao.selectOne("studio.readyDataCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
