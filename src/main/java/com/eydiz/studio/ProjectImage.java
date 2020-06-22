package com.eydiz.studio;

import org.springframework.web.multipart.MultipartFile;

public class ProjectImage {
	private MultipartFile uploadImage;

	public MultipartFile getUploadImage() {
		return uploadImage;
	}

	public void setUploadImage(MultipartFile uploadImage) {
		this.uploadImage = uploadImage;
	}

}
