package com.eydiz.studio;

import java.util.List;

public interface StudioService {
	public Brand readBrand(int memberNo);
	public int myBrandCount(int memberNo);
	public void updateBrand(Brand dto) throws Exception;

	public List<ProjectCategory> listCategory(); 
}
