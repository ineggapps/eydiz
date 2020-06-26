package com.eydiz.cast;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eydiz.common.dao.CommonDAO;

@Service("cast.castService")
public class CastServiceImpl implements CastService {
	@Autowired
	private CommonDAO dao;

	@Override
	public void insertCast(Cast dto) throws Exception {
		try {
			dao.insertData("cast.insertCast", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Cast> listCast(Map<String, Object> map) {
		List<Cast> list = null;
		
		try {
			list = dao.selectList("cast.listCast", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		int result = 0;
		
		try {
			result = dao.selectOne("cast.dataCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Cast readCast(int castNum) {
		Cast dto = null;
		
		try {
			dto = dao.selectOne("cast.readCast", castNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	@Override
	public void updateCast(Cast dto) throws Exception {
		try {
			dao.updateData("cast.updateCast", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public void deleteCast(int castNum, String memberId) throws Exception {
		try {
			Cast dto = readCast(castNum);
			if(dto == null || ! memberId.equals("eydiz")) return;
			
			dao.deleteData("cast.deleteCast", castNum);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public void insertComment(Comment dto) throws Exception {
		try {
			dao.insertData("cast.insertComment", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	@Override
	public List<Comment> listComment(Map<String, Object> map) {
		List<Comment> list = null;
		
		try {
			list = dao.selectList("cast.listComment", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int commentCount(Map<String, Object> map) {
		int result = 0;
		
		try {
			result = dao.selectOne("cast.commentCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public void deleteComment(Map<String, Object> map) throws Exception {
		try {
			dao.deleteData("cast.deleteComment", map);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	@Override
	public void insertCastLike(Map<String, Object> map) throws Exception {
		try {
			dao.insertData("cast.insertCastLike", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public int castLikeCount(int castNum) {
		int result = 0;
		
		try {
			result = dao.selectOne("cast.castLikeCount", castNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
}
