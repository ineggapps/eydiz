package com.eydiz.main;

import java.util.List;

import com.eydiz.studio.Project;

public interface MainService {
	public List<ProjectCategory> listProjectCategoryOfFunding();
	public int dataProjectCount();
	public List<Project> listProject();
}
