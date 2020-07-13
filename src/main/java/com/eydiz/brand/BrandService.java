package com.eydiz.brand;

import java.util.List;

import com.eydiz.studio.Brand;
import com.eydiz.studio.Project;

public interface BrandService {
	
	public Brand readBrand(int brandNo);
	
	//해당 브랜드가 만든 모든 프로젝트 조회 (진행 중, 종료에 한해)
	public int listMadeProjectCount(int brandNo);
	public List<Project> listMadeProject(int brandNo, int offset, int rows);	

	//해당 브랜드가 만든 프로젝트 중 펀딩한 프로젝트만 조회(진행 중, 종료에 한해)
	public int listFundedProjectCount(int brandNo, int memberNo);
	public List<Project> listFundedProject(int brandNo, int memberNo, int offset, int rows);
}
