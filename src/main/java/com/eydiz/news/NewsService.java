package com.eydiz.news;

import java.util.List;
import java.util.Map;

public interface NewsService {
	public void insertNews(News dto, String pathname) throws Exception;
	public int dataCount(Map<String, Object> map);
	public List<News> listNews(Map<String, Object> map);
	public News readNews(int nNum);
	public News preReadNews(Map<String, Object> map);
	public News nextReadNews(Map<String, Object> map);
	public void updateNews(News dto, String pathname) throws Exception;
	public void deleteNews(int nNum, String pathname, String mNum) throws Exception;
	
}
