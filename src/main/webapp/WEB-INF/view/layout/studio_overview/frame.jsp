<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%
	String cp=request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>크라우드 펀딩</title>
    <link rel="stylesheet" href="<%=cp%>/resource/css/reset.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/layout.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/studio/overview.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/studio/brand_info.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/studio/project_list.css" />
    <!--[if lt IE 9]>
      <script src="<%=cp%>/resource/js//html5shiv.min.js"></script>
    <![endif]-->
    <script>
    const cp = "<%=cp%>";
    </script>
    <script src="<%=cp%>/resource/js/jquery-3.5.1.min.js"></script>
	<script src="<%=cp %>/resource/js/studio/brand_studio.js"></script>
    <script>
    	//sticky 구현
    	$(function(){
    		const $nav = $("div.nav");
    		const $clone = $nav.clone(true).attr("id","cloneNav");
    		const $mobileNav = $(".mobileNav");
    		$( window ).on("scroll resize", function() {
    		const offset = $mobileNav.css("display")=="block"?$mobileNav.height():0;
       		const navTop = $nav.offset().top;
    			if ( $(window).scrollTop() > navTop - offset) {
    				if($("header").find("#cloneNav").length==0){    					
			    		$clone.appendTo("header");
    				}
    					$clone.addClass( 'sticky' );
    			}
    			else {
    				$clone.remove();
    			   	$clone.removeClass( 'sticky' );
    			}			
      		});
    		$(window).scrollTop(10);
    	});
        </script>
  </head>
  <body>
    <div id="wrap">
      <tiles:insertAttribute name="header"/>
      <main id="content">
        <div class="contentWrapper">
          <article class="row bgWhite">
            <div class="rowFull">
              <div class="projectTitle">
                <h2>브랜드 &centerdot; 프로젝트 관리</h2>
              </div>
            </div>
          </article>
          <nav class="row">
            <div class="rowFull nav bgWhite">
              <ul class="detailNav">
                <li ${uri=='/brand/info'?"class=\"on\"":""}>
                  <a href="<%=cp%>/studio/brand/info"><span>브랜드</span></a>
                </li>
                <li ${uri=='/project/list'?"class=\"on\"":""}>
                  <a href="<%=cp%>/studio/project/list"><span>프로젝트 관리</span></a>
                </li>
                <%-- li ${categoryName=='funding'?"class=\"on\"":""}>
                  <a href="<%=cp%>/studio/project/list/funding"><span>펀딩</span></a>
                </li>
                <li ${categoryName=='donate'?"class=\"on\"":""}>
                  <a href="<%=cp%>/studio/project/list/donate"><span>후원</span></a>
                </li--%>
              </ul>
            </div>
          </nav>
          <article class="row content">
            <div class="contentInner">
              <tiles:insertAttribute name="content"/>
            </div>
          </article>
        </div>
      </main>
      <tiles:insertAttribute name="footer"/>
    </div>
  </body>
</html>
