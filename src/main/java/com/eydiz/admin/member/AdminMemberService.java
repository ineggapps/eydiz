package com.eydiz.admin.member;

import java.util.List;
import java.util.Map;

public interface AdminMemberService {
	public List<AdminMember> mainAdminMember(Map<String, Object> map);
	public List<AdminMember> listAdminMember(Map<String, Object> map);
	public int maindataCount(Map<String, Object> map);
	public int listdataCount(Map<String, Object> map);
}
