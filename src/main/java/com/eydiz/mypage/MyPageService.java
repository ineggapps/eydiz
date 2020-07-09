package com.eydiz.mypage;

import org.springframework.web.multipart.MultipartFile;

import com.eydiz.member.Member;

public interface MyPageService {
	public String uploadAvatarImage(Member member, MultipartFile uploadAvatar, String realPath, String uriPath) throws Exception;
}