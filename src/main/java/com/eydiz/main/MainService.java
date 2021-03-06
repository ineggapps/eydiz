package com.eydiz.main;

import java.util.List;

import com.eydiz.studio.Project;

public interface MainService {
	public List<ProjectCategory> listProjectCategory();
	public List<ProjectCategory> listProjectCategoryOfFunding();
	public String selectCategoryName(int categoryNo);
	
	public int dataProjectCount(SnippetOption options);
	public int dataProjectCount(Integer categoryNo, SnippetOption options);
	
	public List<Project> listProject(int offset, int rows, SnippetOption options);
	public List<Project> listProject(Integer categoryNo, int offset, int rows, SnippetOption options);
}
