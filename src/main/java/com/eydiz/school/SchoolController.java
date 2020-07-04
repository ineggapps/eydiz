package com.eydiz.school;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller("school.schoolController")
@RequestMapping("/school/*")
public class SchoolController {
	
	// @Autowired
	// private SchoolService service;
	
	@RequestMapping(value="main")
	public String main(
			@RequestParam(defaultValue="0") int sccaNo,
			@RequestParam(name="date", defaultValue="") String date,
			Model model) throws Exception {


		// 테이블에서 일일 전체일절 리스트 가져오기 가져오기
		// Map<String, Object> map=new HashMap<>();
		// map.put("date", date);
		// map.put("sccaNo", sccaNo);
		
		System.out.println(sccaNo+","+date);
		
		
		
		
	
		
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
				
				if(i==0) {
					days[row][i]="<span class='textDate sundayDate' data-date='"+s+"' >"+n+"</span>";
				} else if(i==6) {
					days[row][i]="<span class='textDate saturdayDate' data-date='"+s+"' >"+n+"</span>";
				} else {
					days[row][i]="<span class='textDate nowDate' data-date='"+s+"' >"+n+"</span>";
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
	
}
