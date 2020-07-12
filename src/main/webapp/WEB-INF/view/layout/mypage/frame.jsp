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
    <link rel="stylesheet" href="<%=cp%>/resource/css/myinfo/myinfo.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/myinfo/funding.css" />
    <!--[if lt IE 9]>
      <script src="<%=cp%>/resource/js//html5shiv.min.js"></script>
    <![endif]-->
    <script>
    const cp = "<%=cp%>";
    </script>
    <script src="<%=cp%>/resource/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="/eydiz/resource/jquery/js/jquery.form.js"></script>
	<script src="<%=cp %>/resource/js/studio/brand_studio.js"></script>
	<script src="<%=cp %>/resource/js/mypage/funding.js"></script>
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
                <h2>마이 페이지</h2>
              </div>
            </div>
          </article>
          <nav class="row">
            <div class="rowFull nav bgWhite">
              <ul class="detailNav">
                <li ${uri=='' || uri=='/' || uri=='/myInfo' || uri=='/main' ? "class=\"on\"":""}>
                  <a href="<%=cp%>/mypage/main"><span>개인정보</span></a>
                </li>
                <li ${uri=='/funding'?"class=\"on\"":""}>
                  <a href="<%=cp%>/mypage/funding"><span>펀딩 내역</span></a>
                </li>
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
