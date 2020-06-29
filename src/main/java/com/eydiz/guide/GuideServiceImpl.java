package com.eydiz.guide;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eydiz.common.FileManager;
import com.eydiz.common.dao.CommonDAO;

@Service("guide.guideService")
public class GuideServiceImpl implements GuideSerivce{
	@Autowired
	private CommonDAO dao;
	
	@Autowired
	private FileManager fileManager;

	@Override
	public void insertFaq(Guide dto, String pathname) throws Exception {
		try {
			String saveFilename = fileManager.doFileUpload(dto.getUpload(), pathname);
			if(saveFilename!=null) {
				dto.setFaqIcon(saveFilename);
			}
			dao.insertData("guide.insertGuide", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Guide> listFaq(Map<String, Object> map) {
		List<Guide> list = null;
		
		try {
			list = dao.selectList("guide.listGuide", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		int result = 0;
		
		try {
			result = dao.selectOne("guide.dataCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Guide readFaq(int faqNo) {
		Guide dto = null;
		
		try {
			dto = dao.selectOne("guide.readGuide", faqNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	@Override
	public void updateFaq(Guide dto, String pathname) throws Exception {
		try {
			String saveFilename = fileManager.doFileUpload(dto.getUpload(), pathname);
			
			if(saveFilename != null) {
				if(dto.getFaqFilename().length()!=0) {
					fileManager.doFileDelete(dto.getFaqFilename(), pathname);
				}
				
				dto.setFaqFilename(saveFilename);
			}
			
			dao.updateData("guide.updateGuide", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void deleteFaq(int faqNo, String pathname, int memberNo) throws Exception {
		try {
			dao.deleteData("guide.deleteGuide", faqNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	
}
