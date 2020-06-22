package com.eydiz.news;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eydiz.common.FileManager;
import com.eydiz.common.dao.CommonDAO;

@Service("news.newsService")
public class NewsServiceImpl implements NewsService{
	@Autowired
	private CommonDAO dao;
	
	@Autowired
	private FileManager fileManager;

	@Override
	public void insertNews(News dto, String pathname) throws Exception {
		try {
			String saveFilename = fileManager.doFileUpload(dto.getUpload(), pathname);
			if(saveFilename!=null) {
				dto.setImageFilename(saveFilename);
			}
			dao.insertData("news.insertNews", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		int result = 0;
		
		try {
			result = dao.selectOne("news.dataCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<News> listNews(Map<String, Object> map) {
		List<News> list = null;
		
		try {
			list=dao.selectList("news.listNews", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public News readNews(int noticeNo) {
		News dto = null;
		
		try {
			dto = dao.selectOne("news.readNews", noticeNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	@Override
	public News preReadNews(Map<String, Object> map) {
		News dto = null;
		
		try {
			dto = dao.selectOne("news.preReadNews", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public News nextReadNews(Map<String, Object> map) {
		News dto = null;
		
		try {
			dto = dao.selectOne("news.nextReadNews", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public void updateNews(News dto, String pathname) throws Exception {
		try {
			String saveFilename = fileManager.doFileUpload(dto.getUpload(), pathname);
			
			if(saveFilename!=null) {
				if(dto.getImageFilename().length()!=0) {
					fileManager.doFileDelete(dto.getImageFilename(), pathname);
				}
				
				dto.setImageFilename(saveFilename);
			}
			
			dao.updateData("news.updateNews", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void deleteNews(int noticeNo, String pathname, int memberNo) throws Exception {
		try {
			News dto = readNews(noticeNo);
			if(dto == null || (memberNo==1))
				return;
			
			if(dto.getImageFilename()!=null) {
				fileManager.doFileDelete(dto.getImageFilename(), pathname);
			}
			
			dao.deleteData("news.deleteNews", noticeNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	
	
}
