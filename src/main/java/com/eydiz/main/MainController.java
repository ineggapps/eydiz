package com.eydiz.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eydiz.common.Constant;
import com.eydiz.common.Pager;
import com.eydiz.studio.Project;

@Controller("mainController")
public class MainController implements MainConstant, Constant {

	@Autowired
	private MainService service;

	@Autowired
	private Pager pager;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(Model model) throws Exception {
		List<ProjectCategory> fundingCategories = null;
		try {
			fundingCategories = service.listProjectCategoryOfFunding();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute(ATTRIBUTE_FUNDING_CATEGORY, fundingCategories);
		return ".mainLayout";
	}

	@RequestMapping(value = "/category/{categoryNo}", method = RequestMethod.GET)
	public String category(@PathVariable Integer categoryNo, Model model) {
		List<ProjectCategory> fundingCategories = null;
		List<Project> projects = null;
		try {
			fundingCategories = service.listProjectCategoryOfFunding();
			model.addAttribute(ATTRIBUTE_CATEGORY_NAME, service.selectCategoryName(categoryNo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute(ATTRIBUTE_FUNDING_CATEGORY, fundingCategories);
		model.addAttribute(ATTRIBUTE_PROJECT, projects);
		return ".mainLayout";
	}

	@RequestMapping(value = "/main/project/snippet")
	@ResponseBody
	public Map<String, Object> projectSnippet(Integer categoryNo,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "9") int rows) {
		Map<String, Object> map = new HashMap<>();
		List<Project> list = null;
		try {
			int offset = pager.getOffset(page, rows);
			int dataCount = service.dataProjectCount(categoryNo);
			if (categoryNo == null) {
				list = service.listProject(offset, rows);
			} else {
				list = service.listProject(categoryNo, offset, rows);
			}
			map.put(JSON_RESULT, JSON_RESULT_OK);
			map.put(ATTRIBUTE_PROJECT, list);
			map.put(ATTRIBUTE_COUNT, list == null ? 0 : list.size());
			map.put(ATTRIBUTE_PAGE_COUNT, pager.pageCount(rows, dataCount));
		} catch (Exception e) {
			e.printStackTrace();
			map.put(JSON_RESULT, JSON_RESULT_ERROR);
			map.put(JSON_RESULT_ERROR_MESSAGE, e.getMessage());
		}
		return map;
	}

}
