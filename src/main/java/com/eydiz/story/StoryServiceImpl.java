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
	public void insertStroy(Story dto) throws Exception {
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
			dao.selectList("story.listStory", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		
		return 0;
	}

	@Override
	public Story readStory(int num) {
		
		return null;
	}

	@Override
	public void updateStory(Story dto) throws Exception {
		
		
	}

	@Override
	public void deleteStory(int num, String memberId) throws Exception {
		
		
	}


	@Override
	public void insertStoryLike(Map<String, Object> map) throws Exception {
		
		
	}

	@Override
	public int StroyLikeCount(int num) {
		
		return 0;
	}

}
