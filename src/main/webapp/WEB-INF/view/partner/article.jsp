<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
        <div class="contentWrapper">
          <article class="row">
	 	    <div class = "partnerArticleImage" style="background-image: url('../resource/images/partner/partnerImage.jpg');" ></div>
            <div class="rowInner awardContentBody">
             <div class="pArticleName">
		        	<div class="pANWrap">
		        		<p class = "partnerContentText paTitle">${dto.brandName}</p>
		        		<!-- <p class="partnerContentText paContent">내용내용ㄹ늉하크라우드펀딩을 통한 시장검증형 기술상용화 지원프로젝트대중과 함께 기술과 제품의 상용화를 응원하는 크라우드펀딩 연계형 기술상용화 지원사업! SBA와 서울시가 크라우드펀딩 성공기업에게 추가로 R&amp;D자금을 지원합니다.</p> -->
		        	</div>
		      </div>
		      <div class = "pAResultBox">
		      	  <p class="pAResultTitle">프로젝트 현황</p>
		      	   <div class="pAResult1">
		      	   		<p>총 펀딩액</p>
		      	   		<span>2,108,985</span><span>원</span>
		      	   </div>
	              <ul>
	               <li class="item partnerResultCell">
	                  <div class="itemInner">
	                    <div class="partnerResultIcon" style="background-image: url('../resource/images/partner/iconmonstr-script-5-240.png');"></div>
	                      <div class="subject partnerResultText">
	                        <p>오픈한 프로젝트</p>
	                        <span class="partnerResultTextPoint">190</span><span> 건</span>
	                      </div>
	                  </div>
	                </li>
	               <li class="item partnerResultCell">
	                  <div class="itemInner">
	                    <div class="partnerResultIcon" style="background-image: url('../resource/images/partner/iconmonstr-party-21-120.png');"></div>
	                      <div class="subject partnerResultText">
	                        <p>참여한 투자자</p>
	                        <span class="partnerResultTextPoint">210,568</span><span> 명</span>
	                      </div>
	                  </div>
	                </li>
	               <li class="item partnerResultCell partnerStandard">
	                  <p> * 이디즈 파트너의 총 합산 기록입니다. (2020.06.12.금요일 14:40 기준)</p>
	                </li>
	              </ul>
		      </div>
            </div>
          </article>
          <article class="row partnerListContentBody">
	        <div class="rowInner">
	          <div class = "paListTitle">
	          	<p>참여프로젝트</p>
	          </div>
	         <nav class="row">
	            <div class="rowFull">
	              <ul class="= detailNav">
	                <li class="on">
	                  <a href="#"><span>진행중</span></a>
	                </li>
	                <li>
	                  <a href="#"><span>종료</span></a>
	                </li>
	              </ul>
	            </div>
          </nav>
          	<div class = "partnerArticleList"></div>
            </div>
            </article>
        </div>
