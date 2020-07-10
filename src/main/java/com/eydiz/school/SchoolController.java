package com.eydiz.school;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eydiz.member.SessionInfo;


@Controller("school.schoolController")
@RequestMapping("/school/*")
public class SchoolController {
	
	@Autowired
	SchoolService service;
	
	@RequestMapping(value="main")
	public String main(
			@RequestParam(defaultValue="0") int sccaNo,
			@RequestParam(name="date", defaultValue="") String date,
			Model model) throws Exception {


		// 테이블에서 일일 전체일절 리스트 가져오기 가져오기
		
		Map<String, Object> map=new HashMap<>();
		map.put("date", date);
		map.put("sccaNo", sccaNo);
		String date2="";
		if(date.length()==8) {
			date2=date.substring(0,  6);
		}
		map.put("date2", date2);
		
		
		List<School> list = service.listSchool(map);
		List<School> list2 = service.listSchool2(map);		
		// 작은 달력 만들기
		Calendar cal=Calendar.getInstance();
		
		// 오늘날짜
		String today=String.format("%04d%02d%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DATE));
		if(date.length()==0) {
			date=today;
		}
 
		int year=Integer.parseInt(date.substring(0, 4));
		int month=Integer.parseInt(date.substring(4, 6));
		int day=Integer.parseInt(date.substring(6));
		
		cal.set(year, month-1, day);
		year=cal.get(Calendar.YEAR);
		month=cal.get(Calendar.MONTH)+1;
		day=cal.get(Calendar.DATE);
		
		cal.set(year, month-1, 1);
		int week=cal.get(Calendar.DAY_OF_WEEK);
		
		// 이전달과 다음달 1일의 날짜
		Calendar cal2=(Calendar)cal.clone();
		cal2.add(Calendar.MONTH, -1);
		cal2.set(Calendar.DATE, 1);
		String preMonth=String.format("%04d%02d%02d", cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH)+1, cal2.get(Calendar.DATE));
		
		cal2.add(Calendar.MONTH, 2);
		String nextMonth=String.format("%04d%02d%02d", cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH)+1, cal2.get(Calendar.DATE));
		
		// 첫주의 year년도 month월 1일 이전 날짜
		Calendar scal=(Calendar)cal.clone();
		scal.add(Calendar.DATE, -(week-1));
		int syear=scal.get(Calendar.YEAR);
		int smonth=scal.get(Calendar.MONTH)+1;
		int sdate=scal.get(Calendar.DATE);
		
		// 마지막주의 year년도 month월 말일주의 토요일 날짜
		Calendar ecal=(Calendar)cal.clone();
		// year년도 month월 말일
		ecal.add(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		// year년도 month월 말일주의 토요일
		ecal.add(Calendar.DATE, 7-ecal.get(Calendar.DAY_OF_WEEK));
		int eyear=ecal.get(Calendar.YEAR);
		int emonth=ecal.get(Calendar.MONTH)+1;
		
		String s;
		String [][]days=new String[cal.getActualMaximum(Calendar.WEEK_OF_MONTH)][7];
		
		// 1일 앞의 전달 날짜
		String cls="";
		for(int i=1; i<week; i++) {
			s=String.format("%04d%02d%02d", syear, smonth, sdate);
			days[0][i-1]="<span class='textDate preMonthDate' data-date='"+s+"' >"+sdate+"</span>";
			sdate++;
		}
		
		// year년도 month월 날짜
		int row, n=0;
		jump:
		for(row=0; row<days.length; row++) {
			for(int i=week-1; i<7; i++) {
				n++;
				s=String.format("%04d%02d%02d", year, month, n);
				
				cls="noSchDate";
				for(School vo : list2) {
					if(vo.getStrDate().equals(s)) {
						cls="schDate";
						break;
					}
				}
				
				if(i==0) {
					days[row][i]="<span class='textDate sundayDate "+cls+"' data-date='"+s+"' >"+n+"</span>";
				} else if(i==6) {
					days[row][i]="<span class='textDate saturdayDate "+cls+"' data-date='"+s+"' >"+n+"</span>";
				} else {
					days[row][i]="<span class='textDate nowDate "+cls+"' data-date='"+s+"' >"+n+"</span>";
				}
				
				if(n==cal.getActualMaximum(Calendar.DATE)) {
					week=i+1;
					break jump;
				}
			}
			week=1;
		}
		
		// year년도 month월 마지막 날짜 이후
		if(week!=7) {
			n=0;
			for(int i=week; i<7; i++) {
				n++;
				s=String.format("%04d%02d%02d", eyear, emonth, n);
				days[row][i]="<span class='textDate nextMonthDate' data-date='"+s+"' >"+n+"</span>";
			}
		}
		
		model.addAttribute("list", list);
		model.addAttribute("sccaNo", sccaNo);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("date", date);
		model.addAttribute("today", today);
		model.addAttribute("preMonth", preMonth);
		model.addAttribute("nextMonth", nextMonth);
		
		model.addAttribute("days", days);
		
		return ".schoolLayout.main";
	}
	
	@RequestMapping(value="createschool", method=RequestMethod.GET)
	public String createForm(
			Model model
			) throws Exception {
		model.addAttribute("mode", "createschool");
		
		return ".schoolLayout.createschool";
	}
	
	@RequestMapping(value="createschool")
	public String createdSubmit(
			School dto,
			HttpSession session
			) throws Exception {
		String root = session.getServletContext().getRealPath("/");
		String path = root+"upload"+File.separator+"school";
		
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		try {
			dto.setMemberNo(info.getMemberNo());
			service.insertSchool(dto, path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/school/main";
	}
	
	@RequestMapping(value="article", method=RequestMethod.GET)
	public String article(
			@RequestParam int scNo,
			@RequestParam(defaultValue="0") int sccaNo,
			@RequestParam(name="date", defaultValue="") String date,
			Model model
			) throws Exception {
		School dto = service.readSchool(scNo);
		if(dto == null) {
			return "school/error";
		}			
		
		dto.setScContent(dto.getScContent().replaceAll("\n", "<br>"));
		
		Map<String, Object> map = new HashMap<>();
		map.put("sccaNo", sccaNo);
		map.put("scNo", scNo);
		map.put("date", date);
		
		model.addAttribute("dto", dto);
		return ".schoolLayout.schoolcontent";
	}
	
	@RequestMapping(value="update", method=RequestMethod.GET)
	public String updateForm(
			@RequestParam int scNo,
			@RequestParam(defaultValue="0") int sccaNo,
			@RequestParam(name="date", defaultValue="") String date,
			HttpSession session,
			Model model
			) throws Exception {
		
		School dto = service.readSchool(scNo);
		if(dto == null) {
			return "redirect:/school/main?sccaNo="+sccaNo+"&date="+date;
		}
		
		model.addAttribute("dto", dto);
		model.addAttribute("sccaNo", sccaNo);
		model.addAttribute("date", date);
		model.addAttribute("mode", "update");
		
		return ".schoolLayout.createschool";
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String updateSubmit(
			School dto,
			@RequestParam(defaultValue="0") int sccaNo,
			@RequestParam(name="date", defaultValue="") String date,
			HttpSession session
			) throws Exception {
		String root = session.getServletContext().getRealPath("/");
		String pathname=root+"uploads"+File.separator+"mate";
		
		try {
			service.updateSchool(dto, pathname);
		} catch (Exception e) {
		}
		
		
		return "redirect:/school/article?scNo="+dto.getScNo()+"&sccaNo="+sccaNo+"&date="+date;
	}
	
	@RequestMapping(value="delete", method=RequestMethod.GET)
	public String delete (
			@RequestParam int scNo,
			HttpSession session
			) throws Exception {
		
		String root = session.getServletContext().getRealPath("/");
		String pathname=root+"uploads"+File.separator+"mate";
		
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		try {
			service.deleteSchool(scNo, pathname, info.getMemberNo());
		} catch (Exception e) {
		}
		
		return "redirect:/school/main";
	}
	
	@RequestMapping(value="insertSchoolProject", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertSchoolProject(
			@RequestParam int scNo,
			HttpSession session
			) throws Exception {
		String state="true";
		int schoolProject=0;
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		Map<String, Object> model = new HashMap<>();
		School dto = service.readSchool(scNo);
		if(dto.getSchoolProject()>=dto.getScAllNumber()) {
			schoolProject = service.schoolProject(scNo);
			model.put("state", "over");
			model.put("schoolProject", schoolProject);
			return model;
		}
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("scNo", scNo);
		paramMap.put("memberNo", info.getMemberNo());
		
		try {
			service.insertSchoolProject(paramMap);
		} catch (Exception e) {
			state="false";
		}
		
		schoolProject = service.schoolProject(scNo);
		

		model.put("state", state);
		model.put("schoolProject", schoolProject);
		
		return model;
	}
	
}
