package com.eydiz.awards;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eydiz.common.dao.CommonDAO;

@Service("awards.awardsService")
public class AwardsServiceImpl implements AwardsService{

	@Autowired
	private CommonDAO dao;
	
	@Override
	public List<Awards> highestPercentage() {
		List<Awards> highestList = null;
		
		try {
			highestList = dao.selectList("awards.highestPercentage");
		} catch (Exception e) {
		}
		
		return highestList;
	}

	@Override
	public List<Awards> bestSeller() {
		List<Awards> bestSeller = null;
		
		try {
			bestSeller = dao.selectList("awards.bestSeller");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return bestSeller;
	}

	@Override
	public List<Awards> brandProjectRank() {
		List<Awards> brandRank = null;
		
		try {
			brandRank = dao.selectList("awards.brandProjectRank");
		} catch (Exception e) {
		}
		
		return brandRank;
	}





}
