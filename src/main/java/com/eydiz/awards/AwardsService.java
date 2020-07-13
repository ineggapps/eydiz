package com.eydiz.awards;

import java.util.List;

public interface AwardsService {
	public List<Awards> highestPercentage();
	public List<Awards> bestSeller();
	public List<Awards> brandProjectRank();
}
