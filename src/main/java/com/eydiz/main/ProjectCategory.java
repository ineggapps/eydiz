package com.eydiz.main;

public class ProjectCategory {
	private int categoryNo;
	private Integer parentCategoryNo;
	private String categoryName;
	private String imageUrl;

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public Integer getParentCategoryNo() {
		return parentCategoryNo;
	}

	public void setParentCategoryNo(Integer parentCategoryNo) {
		this.parentCategoryNo = parentCategoryNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
