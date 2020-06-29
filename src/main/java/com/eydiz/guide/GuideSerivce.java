package com.eydiz.guide;

import java.util.List;
import java.util.Map;

public interface GuideSerivce {
	public void insertFaq(Guide dto, String pathname)throws Exception;
	public List<Guide> listFaq(Map<String, Object> map);
	public int dataCount(Map<String, Object> map);
	public Guide readFaq(int faqNo);
	public void updateFaq(Guide dto, String pathname) throws Exception;
	public void deleteFaq(int faqNo, String pathname, int memberNo) throws Exception; 
}
