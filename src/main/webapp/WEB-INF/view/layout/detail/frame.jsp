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
    <link rel="stylesheet" href="<%=cp%>/resource/css/detail.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/detail/reward.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/detail/step.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/detail/community.css" />
    <!--[if lt IE 9]>
      <script src="resource/js//html5shiv.min.js"></script>
    <![endif]-->
    <script>
    const cp = "<%=cp%>";
    const projectNo = ${project.projectNo};
    const memberId = "${sessionScope.member.memberId}";
    const memberNickname ="${sessionScope.member.memberNickname}";
    const memberImageUrl = "${sessionScope.member.memberImageUrl}";
    </script>
    <script src="<%=cp %>/resource/js/jquery-3.5.1.min.js"></script>
    <!--[if IE]> 
	<script type="text/javascript" src="<%=cp %>/resource/js/lib/bluebird.js"></script>    
	<![endif]-->
	<script type="text/javascript" src="<%=cp %>/resource/js/countUp.js"></script>    
	<script type="text/javascript" src="<%=cp %>/resource/js/jquery.ellipsis.min.js"></script>    
	<script type="text/javascript" src="<%=cp %>/resource/js/detail/detail.js"></script>    
	<script type="text/javascript" src="<%=cp %>/resource/js/detail/reward.js"></script>    
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
    		$(window).scrollTop(0);
    	});
        </script>
  </head>
  <body>
    <div id="wrap">
      <header id="header">
        <tiles:insertAttribute name="header" />
      </header>
      <main id="content">
        <div class="contentWrapper">
	      	<article class="row">
			  <div class="rowFull">
			    <div class="detailWrap">
			      <div class="detailContent">
			      	<tiles:insertAttribute name="content"/>
			      </div>
			      <div class="detailSide">
			        <tiles:insertAttribute name="side"/>
			      </div>
			    </div>
			  </div>
			</article>
        </div>
      </main>
      <footer id="footer">
        <tiles:insertAttribute name="footer" />
      </footer>
    </div>
  </body>
</html>
