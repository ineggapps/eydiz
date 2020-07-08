package com.eydiz.school;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eydiz.common.FileManager;
import com.eydiz.common.dao.CommonDAO;

@Service("school.schoolService")
public class SchoolServiceImpl implements SchoolService{
	
	@Autowired
	private CommonDAO dao;
	
	@Autowired
	private FileManager fileManager;

	@Override
	public void insertSchool(School dto, String pathname) {
		try {
			String saveFilename = fileManager.doFileUpload(dto.getUpload(), pathname);
			if(saveFilename!=null) {
				dto.setScSavefilename(saveFilename);
			}
			dao.insertData("school.insertSchool", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<School> listSchool(Map<String, Object> map) {
		List<School> list = null;
		try {
			list = dao.selectList("school.listSchool", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public School readSchool(int scNo) {
		School dto = null;
		
		try {
			dto = dao.selectOne("school.readSchool", scNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public void updateSchool(School dto, String pathname) throws Exception {
		try {
			String saveFilename = fileManager.doFileUpload(dto.getUpload(), pathname);
			
			if(saveFilename!=null) {
				if(dto.getScSavefilename().length()!=0) {
					fileManager.doFileDelete(dto.getScSavefilename(), pathname);
				}
				
				dto.setScSavefilename(saveFilename);
			}
			
			dao.updateData("school.updateSchool", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void deleteSchool(int scNo, String pathname, int memberNo) throws Exception {
		try {
			School dto = readSchool(scNo);
			if(dto == null || (memberNo == 1))
				return;
			
			if(dto.getScSavefilename()!=null) {
				fileManager.doFileDelete(dto.getScSavefilename(), pathname);
			}
			
			dao.deleteData("school.deleteSchool", scNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<School> listSchool2(Map<String, Object> map) {
		List<School> list = null;
		try {
			list = dao.selectList("school.listSchool2", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void insertSchoolProject(Map<String, Object> map) throws Exception {
		try {
			dao.insertData("school.insertSchoolProject", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public int schoolProject(int scNo) {
		int result = 0;
		try {
			result=dao.selectOne("school.schoolProject", scNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}


}
