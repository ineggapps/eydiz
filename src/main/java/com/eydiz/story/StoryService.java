package com.eydiz.story;

import java.util.List;
import java.util.Map;

public interface StoryService {
	public void insertStroy(Story dto) throws Exception;
	public List<Story> listStory(Map<String, Object> map);
	public int dataCount();
	public void updateStory(Story dto) throws Exception;
	public void deleteStory(int num, String memberId) throws Exception;
	
	public void insertStoryLike(Map<String, Object> map) throws Exception;
	public int StroyLikeCount(int num);
	
}
