<%@ page contentType="text/html; charset=UTF-8"%> <%@ page trimDirectiveWhitespaces="true" %> <%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %> <%@ taglib prefix="tiles"
uri="http://tiles.apache.org/tags-tiles" %> <% String cp=request.getContextPath(); %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>크라우드 펀딩</title>
    <link rel="stylesheet" href="<%=cp%>/resource/css/reset.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/jquery/css/jquery-ui.min.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/studio/layout.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/studio/reward.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/studio/story.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/studio/dashboard.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/admin/list.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/admin/view.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/admin/adminProject.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/admin/member/main.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/admin/member/brand.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/admin/brand/list.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/admin/brand/read.css" />
    
    <!--[if lt IE 9]>
      <script src="<%=cp%>/resource/js/html5shiv.min.js"></script>
    <![endif]-->
    <script>
      const cp = "<%=cp%>";
    </script>
    <script src="<%=cp%>/resource/js/jquery-3.5.1.min.js"></script>
    <script src="<%=cp%>/resource/jquery/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="<%=cp%>/resource/jquery/js/jquery.form.js"></script>
    <script type="text/javascript" src="<%=cp%>/resource/js/admin/admin_common.js"></script>
  </head>
  <body>
    <div id="wrap">
      <div class="overlay"></div>
      <header id="header">
        <div class="headerInner">
          <h3 class="btnMenu" id="btnGnbMenu"><span class="hidden">메뉴</span></h3>
          <h1 class="logo"><a href="#">Brand studio</a></h1>
          <div class="projectTitle"><h2>관리자 페이지</h2></div>
          <ul class="rightGnb">
            <li class="menuExit">
              <a href="<%=cp %>/" class="none"><span>나가기</span></a>
            </li>
            <li class="menuTemporary hide">
              <a href="#"><span>임시저장</span></a>
            </li>
            <!-- <li class="menuPreview">
              <a href="#"><span>미리보기</span></a>
            </li> -->
          </ul>
        </div>
      </header>
      <main id="content">
        <div class="contentWrapper">
          <div class="columnSide">
            <tiles:insertAttribute name="side" />
          </div>
          <div class="columnContent">
            <tiles:insertAttribute name="content" />
          </div>
        </div>
      </main>
  </body>
</html>
