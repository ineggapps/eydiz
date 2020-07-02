package com.eydiz.admin.brand;

import java.util.List;
import java.util.Map;

import com.eydiz.studio.Brand;
import com.eydiz.studio.Project;

public interface AdminBrandService {
	public List<Brand> listBrand(Map<String, Object> map);
	public List<Project> listProject(Map<String, Object> map);
}
