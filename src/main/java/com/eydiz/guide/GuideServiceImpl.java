package com.eydiz.guide;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service("guide.guideService")
public class GuideServiceImpl implements GuideSerivce{

	@Override
	public void insertFaq(Guide dto) throws Exception {
		
	}

	@Override
	public List<Guide> listFaq(Map<String, Object> map) {
		return null;
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		return 0;
	}

	@Override
	public Guide readFaq(int faqNo) {
		return null;
	}

	@Override
	public void updateFaq(Guide dto) throws Exception {
		
	}

	@Override
	public void deleteFaq(Map<String, Object> map) throws Exception {
		
	}

	
}
