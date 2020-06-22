package com.eydiz.story;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eydiz.common.dao.CommonDAO;

@Service("story.storyService")
public class StoryServiceImpl implements StoryService {
	@Autowired
	private CommonDAO dao;

	@Override
	public void insertStory(Story dto) throws Exception {
		try {
			dao.insertData("story.insertStory", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public List<Story> listStory(Map<String, Object> map) {
		List<Story> list = null;
		
		try {
			list=dao.selectList("story.listStory", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int dataCount() {
		int result = 0;
		
		try {
			result=dao.selectOne("story.dataCount");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	

	@Override
	public void deleteStory(int storyNum, String memberId) throws Exception {
		try {
			dao.deleteData("story.deleteStory", storyNum);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}


	@Override
	public void insertStoryLike(Map<String, Object> map) throws Exception {
		try {
			dao.insertData("story.insertStoryLike", map);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public int storyLikeCount(int storyNum) {
		int result = 0;
		
		try {
			result = dao.selectOne("story.storyLikeCount", storyNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
