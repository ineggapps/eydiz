<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<article class="row">
            <div class="rowFull">
		        <div class="awardMaker100" style="text-align: center;">
		        	<p class = "awardContentTitle">${awardsGroupName} 부분</p>
		        	<p class="awardContentTitleSub">${awardsGroupDetail}</p>
		        </div>
            </div>
          </article>
          
          <article class="row">
            <div class="rowInner awardContentBody">
              <ul class="gridContent">
               
                <li class="item">
                  <div class="itemInner">
                    <div
                      class="thumbnail"
                      style="background-image: url('https://images.pexels.com/photos/995301/pexels-photo-995301.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260');">
                      <span class="hidden">드럼</span>
                    </div>
                    <div class="textWrap ">
                      <div class="subject awardsTabBName">
                        <a href="#"><span>${brandName}</span></a>
                      </div>
                      <div class = "aTabBDetail">
                      	<p>
                      		${projectName}
                      	</p>
                      </div>
                    </div>
                  </div>
                </li>
              
              </ul>
            </div>
          </article>