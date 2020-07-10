package com.eydiz.admin.project;

import java.util.List;
import java.util.Map;

import com.eydiz.studio.Project;
import com.eydiz.studio.Reward;

public interface AdminProjectService {
	public List<AdminProject> adminProjectList(Map<String, Object> map);
	public int dataCount(Map<String, Object> map);
	public void updateProject(Map<String, Object> map)throws Exception;
	public void insertProjectStatusList(AdminProject dto) throws Exception;
	public Project readProject(int projectNo);
	public List<Reward> listRewards(int projectNo);
}
