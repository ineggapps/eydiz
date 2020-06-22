package com.eydiz.studio;

public class BrandSessionInfo {
	private int brandNo;
	private String brandName;

	public BrandSessionInfo(int brandNo, String brandName) {
		this.brandNo = brandNo;
		this.brandName = brandName;
	}

	public int getBrandNo() {
		return brandNo;
	}

	public void setBrandNo(int brandNo) {
		this.brandNo = brandNo;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public BrandSessionInfo() {
	}

}
