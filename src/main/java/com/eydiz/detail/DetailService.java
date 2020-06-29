package com.eydiz.detail;

import java.util.List;

import com.eydiz.studio.Project;
import com.eydiz.studio.Reward;

public interface DetailService {
	public Project readProject(int projectNo);
	public List<Reward> listRewards(int projectNo);
}
