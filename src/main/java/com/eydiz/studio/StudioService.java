package com.eydiz.studio;

public interface StudioService {
	public Brand readBrand(int memberNo);
	public int myBrandCount(int memberNo);
	public void updateBrand(Brand dto) throws Exception;
}
