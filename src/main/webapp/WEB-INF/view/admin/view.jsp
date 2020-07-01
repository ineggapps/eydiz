<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">

<div class="admin_view_text">
           <div class="titleBar">
             <div class="title"><h2>프로젝트 목록</h2></div>
			     <form name="searchForm" action="" method="post" >
               <div align="right">
                   <input type="text" name="keyword"placeholder="검색" value="${keyword}"/>
                    <button type="button" class="v_searchButton" onclick="searchlist()"><i class="fa fa-search" aria-hidden="true"></i></button>
                    <select name="condition">
                      <option value="">브랜드</option>
                      <option value="">프로젝트</option>
                    </select>
                </div>
				 </form>
              </div>
              

            <div class="admin_view" >
             <table class="admin_view_project">
             	<tr class="admin_view_oo">
             		<td class="admin_view_bname">브랜드이름</td>
					<td class="admin_view_pname">프로젝트이름</td> 
					<td class="admin_view_cname">카테고리이름</td> 
					<td class="admin_view_sname">상태</td> 
				</tr>
             	<tr class="admin_view_o">
             		<td class="admin_view_bname">이클립스</td>
					<td class="admin_view_pname">복숭아맛 이클립스</td> 
					<td class="admin_view_cname">캔디/초콜릿</td> 
					<td class="admin_view_sname">진행중</td> 
				</tr>
			 </table>
		    </div>

           </div>
 		<table style="width: 100%; border-spacing: 0px;">
			<tr height="35">
				<td align="center">
					 ${dataCount==0?"데이터가 없습니다.":paging}
				</td>
			</tr>
		</table>