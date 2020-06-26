package com.eydiz.guide;

import java.util.List;
import java.util.Map;

public interface GuideSerivce {
	public void insertFaq(Guide dto)throws Exception;
	public List<Guide> listFaq(Map<String, Object> map);
	public int dataCount(Map<String, Object> map);
	public Guide readFaq(int faqNo);
	public void updateFaq(Guide dto) throws Exception;
	public void deleteFaq(Map<String, Object> map) throws Exception; 
}
