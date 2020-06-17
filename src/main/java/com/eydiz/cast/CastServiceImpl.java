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
	public void insertCast(Cast dto, String pathname) throws Exception {
		
		
	}

	@Override
	public List<Cast> listCast(Map<String, Object> map) {
		
		return null;
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		
		return 0;
	}

	@Override
	public Cast readCast(int num) {
		
		return null;
	}

	@Override
	public void updateCast(Cast dto, String pathname) throws Exception {
		
		
	}

	@Override
	public void deleteCast(int num, String pathname, String memberId) throws Exception {
		
		
	}

	@Override
	public void insertComment(Comment dto) throws Exception {
		
		
	}
	
	@Override
	public List<Comment> listComment(Map<String, Object> map) {
		
		return null;
	}
	
	@Override
	public void deleteComment(Map<String, Object> map) throws Exception {
		
		
	}
	
	@Override
	public void insertCastLike(Map<String, Object> map) throws Exception {
		
		
	}

	@Override
	public int CastLikeCount(int num) {
		
		return 0;
	}
	
}
