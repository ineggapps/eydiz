package com.eydiz.cast;

import java.util.List;
import java.util.Map;

public interface CastService {
	public void insertCast(Cast dto, String pathname) throws Exception;
	public List<Cast> listCast(Map<String, Object> map);
	public int dataCount(Map<String, Object> map);
	public Cast readCast(int num);
	public void updateCast(Cast dto, String pathname) throws Exception;
	public void deleteCast(int num, String pathname, String memberId) throws Exception;
	
	public void insertComment(Comment dto) throws Exception;
	public List<Comment> listComment(Map<String, Object> map);
	public void deleteComment(Map<String, Object> map) throws Exception;
	
	public void insertCastLike(Map<String, Object> map) throws Exception;
	public int CastLikeCount(int num);
	
}
