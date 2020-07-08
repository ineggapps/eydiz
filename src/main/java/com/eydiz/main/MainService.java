package com.eydiz.main;

import java.util.List;

import com.eydiz.studio.Project;

public interface MainService {
	public List<ProjectCategory> listProjectCategory();
	public List<ProjectCategory> listProjectCategoryOfFunding();
	public int dataProjectCount();
	public List<Project> listProject();
	public List<Project> listProject(int categoryNo);
}
