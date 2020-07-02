package com.eydiz.admin.brand;

import java.util.List;
import java.util.Map;


public interface AdminBrandService {
	public List<Brand> listBrand(Map<String, Object> map);
	public int dataCount(Map<String, Object> map);
	public Brand readBrandList(int brandNo);
}
