package com.eydiz.mate;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eydiz.common.FileManager;
import com.eydiz.common.dao.CommonDAO;

@Service("mate.mateService")
public class MateServiceImpl implements MateService {
	@Autowired
	private CommonDAO dao;
	
	@Autowired
	private FileManager fileManager;
	
	@Override
	public void insertMate(Mate dto, String pathname) throws Exception {
		try {
			String saveFilename = fileManager.doFileUpload(dto.getUpload(), pathname);
			if(saveFilename!=null) {
				dto.setfmFilename(saveFilename);
			}
			dao.insertData("mate.insertMate", dto);
		} catch (Exception e) {
			e.printStackTrace();
			
			throw e;
		}
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		int result = 0;
		
		try {
			result = dao.selectOne("mate.dataCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Mate> listMate(Map<String, Object> map) {
		List<Mate> list = null;
		
		try {
			list = dao.selectList("mate.listMate", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public Mate readMate(int fmNo) {
		Mate dto = null;
		
		try {
			dto = dao.selectOne("mate.readMate", fmNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	@Override
	public void updateMate(Mate dto, String pathname) throws Exception {
		try {
			String saveFilename = fileManager.doFileUpload(dto.getUpload(), pathname);
			
			if(saveFilename!=null) {
				if(dto.getfmFilename().length()!=0) {
					fileManager.doFileDelete(dto.getfmFilename(), pathname);
				}
				
				dto.setfmFilename(saveFilename);
			}
			
			dao.updateData("mate.updateMate", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void deleteMate(int fmNo, String pathname, int memberNo) throws Exception {
		try {
			Mate dto = readMate(fmNo);
			if(dto == null || (memberNo == 1))
				return;
			
			if(dto.getfmFilename()!=null) {
				fileManager.doFileDelete(dto.getfmFilename(), pathname);
			}
			
			dao.deleteData("mate.deleteMate", fmNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void insertMateProject(Map<String, Object> map) throws Exception {
		try {
			dao.insertData("mate.insertMateProject", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public int mateProjectCount(int fmNo) {
		int result = 0;
		try {
			result=dao.selectOne("mate.mateProjectCount", fmNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
