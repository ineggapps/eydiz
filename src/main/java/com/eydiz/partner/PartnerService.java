package com.eydiz.partner;

import java.util.List;
import java.util.Map;

public interface PartnerService {
	public int dataCount(Map<String, Object> map);
	public List<Partner> listPartner(Map<String, Object> map);
	public Partner readPartner(int brandNo);
}
