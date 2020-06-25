package com.eydiz.mate;

import java.util.List;
import java.util.Map;

public interface MateService {
	public void insertMate(Mate dto, String pathname) throws Exception;
	public int dataCount(Map<String, Object> map);
	public List<Mate> listMate(Map<String, Object> map);
	public Mate readMate(int fmNo);
	public void updateMate(Mate dto, String pathname) throws Exception;
	public void deleteMate(int fmNo, String pathname, int memberNo) throws Exception;
	
	public void insertMateProject(Map<String, Object> map) throws Exception;
	public int mateProjectCount(int fmNo);
}
