package com.eydiz.brand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eydiz.common.Pager;
import com.eydiz.member.MemberConstant;
import com.eydiz.member.SessionInfo;
import com.eydiz.studio.Brand;
import com.eydiz.studio.Project;
import com.eydiz.studio.StudioConstant;

@Controller
@RequestMapping("/brand/*")
public class BrandController implements StudioConstant, MemberConstant{
	
	@Autowired
	BrandService brandService;

	@Autowired
	Pager pager;
	
	private final static String ATTRIBUTE_API_CATEGORY = "category";
	
	@RequestMapping(value= {"/{brandNo}","/made/{brandNo}", "/funded/{brandNo}"})
	public String brandMade(@PathVariable Integer brandNo, Model model, HttpServletRequest req) {
		try {
			Brand brand = brandService.readBrand(brandNo);
			String uri = req.getRequestURI();
			model.addAttribute(ATTRIBUTE_API_CATEGORY, uri.indexOf("funded")>0?"funded":"made");//made는 /brand/{브랜드번호}로도 접근 가능하므로
			if(brand==null) {
				throw new NullPointerException();
			}
			model.addAttribute(ATTRIBUTE_BRANDNO, brandNo);
			model.addAttribute(ATTRIBUTE_BRAND, brand);
		} catch(NullPointerException e) {
			return "redirect:/";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ".brandLayout";
	}
	
	private final static int ROWS = 9;
	@RequestMapping(value= {"/ajax/made/{brandNo}","/ajax/made/{brandNo}/page/{page}"})
	@ResponseBody
	public Map<String, Object>ajaxMadeList(@PathVariable Integer brandNo, @PathVariable(required = false) Integer page, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		try {
			if(brandNo == null ) {
				throw new Exception("brand번호가 입력되지 않았습니다.");
			}
			if(page==null) {
				page = 1;
			}
			int listProjectCount = brandService.listMadeProjectCount(brandNo);
			int pageCount = pager.pageCount(ROWS, listProjectCount);
			int offset = pager.getOffset(page, ROWS);
			// 페이징 정보 계산과 해당하는 페이지의 프로젝트 불러오기
			List<Project> listProject = brandService.listMadeProject(brandNo, offset, ROWS);
			map.put(JSON_RESULT, JSON_RESULT_OK);
			// 페이징 정보 입력
			map.put(ATTRIBUTE_CURRENT_PAGE, page);
			map.put(ATTRIBUTE_PAGE_COUNT, pageCount);
			map.put(ATTRIBUTE_PROJECT, listProject);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(JSON_RESULT, JSON_RESULT_ERROR);
			map.put(JSON_RESULT_ERROR, e.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value= {"/ajax/funded/{brandNo}","/ajax/funded/{brandNo}/page/{page}"})
	@ResponseBody
	public Map<String, Object>ajaxFundedList(@PathVariable Integer brandNo, @PathVariable(required = false) Integer page, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		try {
			if(brandNo == null ) {
				throw new Exception("brand번호가 입력되지 않았습니다.");
			}
			SessionInfo info = (SessionInfo) session.getAttribute(SESSION_MEMBER);
			if(info==null) {
				throw new Exception("login required");
			}
			int memberNo = info.getMemberNo();
			if(page==null) {
				page = 1;
			}
			int listProjectCount = brandService.listFundedProjectCount(brandNo, memberNo);
			int pageCount = pager.pageCount(ROWS, listProjectCount);
			int offset = pager.getOffset(page, ROWS);
			// 페이징 정보 계산과 해당하는 페이지의 프로젝트 불러오기
			List<Project> listProject = brandService.listFundedProject(brandNo, memberNo, offset, ROWS);
			map.put(JSON_RESULT, JSON_RESULT_OK);
			// 페이징 정보 입력
			map.put(ATTRIBUTE_CURRENT_PAGE, page);
			map.put(ATTRIBUTE_PAGE_COUNT, pageCount);
			map.put(ATTRIBUTE_PROJECT, listProject);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(JSON_RESULT, JSON_RESULT_ERROR);
			map.put(JSON_RESULT_ERROR, e.getMessage());
		}
		return map;
	}
}
