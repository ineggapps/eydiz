package com.eydiz.school;

import java.util.List;
import java.util.Map;

public interface SchoolService {
	public void insertSchool(School dto, String pathname);
	public List<School> listSchool(Map<String, Object> map);
	public List<School> listSchool2(Map<String, Object> map);	
	public School readSchool(int scNo);
	public void updateSchool(School dto, String pathname) throws Exception;
	public void deleteSchool(int scNo, String pathname, int memberNo) throws Exception;
	public void insertSchoolProject(Map<String, Object> map) throws Exception;
	public int schoolProject(int scNo);
}
