package com.eydiz.school;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service("school.schoolService")
public class SchoolServiceImpl implements SchoolService{

	@Override
	public void insertSchool(School dto, String pathname) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<School> listSchool(Map<String, Object> map) {
		List<School> list = null;
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}

	@Override
	public School readSchool(int scNo) {
		School dto = null;
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return dto;
	}

	@Override
	public void updateSchool(School dto, String pathname) throws Exception {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void deleteSchool(int scNo, String pathname, int memberNo) throws Exception {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}


}
