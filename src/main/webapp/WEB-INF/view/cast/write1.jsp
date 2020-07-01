<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   String cp = request.getContextPath();
%>
<script type="text/javascript" src="<%=cp%>/resource/se/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
    function send() {
        var f = document.castForm;

    	var str = f.castTitle.value;
        if(!str) {
            alert("제목을 입력하세요. ");
            f.castTitle.focus();
            return ;
        }

    	str = f.castContent.value;
    	  if(!str || str=="<p>&nbsp;</p>") {
            alert("내용을 입력하세요. ");
            f.castContent.focus();
            return ;
        }

    	f.action="<%=cp%>/cast/${mode}";

       f.submit();
    }
</script>
	<div class="wcontent">
	 <form name="castForm" method="post" onsubmit="return submitContents(this)" action="<%=cp%>/cast/${mode}">
		<div class="inner">
         <div class="cast">
          <h3><span class="cast_s"> 캐스트 </span> </h3>
         </div>
      	  
           <table class="writebox">
           <tr class="boxrow1" align="left"> 
               <td class="title">제&nbsp;&nbsp;&nbsp;&nbsp;목</td>
               <td class="title2"> 
                 <input class="text_in" type="text" name="castTitle" value="${dto.castTitle}">
               </td>
           </tr>
         
           <tr class="boxrow2" align="left"> 
               <td class="writer">작성자</td>
               <td class="writer2">${sessionScope.member.memberId}</td>
           </tr>
    
           <tr class="boxrow3" align="left"> 
               <td class="text">내&nbsp;&nbsp;&nbsp;&nbsp;용</td>
               <td class="text2"> 
                 <textarea class="aaa" name="castContent" rows="12" style="width: 95%;" id="castContent">${dto.castContent}</textarea>
               </td>
           </tr>
           </table>
          </div>
           
           <table class="btnbox">
              <tr height="45"> 
               <td align="center" >
                  <input type="hidden" name="castCnum" value="${castCnum}">
                 <button type="submit" class="btn">${mode == 'update'?'수정하기':'등록하기'}</button>
                 <button type="reset" class="btn">다시입력</button>
                 <button type="button" class="btn" onclick="javascript:location.href='<%=cp%>/cast/news';">${mode == 'update'?'수정취소':'등록취소'}</button>
              	 <c:if test="${mode == 'update'}">
              	 	<input type="hidden" name="castNum" value="${dto.castNum}">
              	 	<input type="hidden" name="page" value="${page}">
              	 </c:if>
               </td>
             </tr>
           </table>
           <script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content1",
	elPlaceHolder: "castContent",
	sSkinURI: "<%=cp%>/resource/se/SmartEditor2Skin.html",	
	htParams : {bUseToolbar : true,
		fOnBeforeUnload : function(){
			//alert("아싸!");
		}
	}, //boolean
	fOnAppLoad : function(){
		//예제 코드
		//oEditors.getById["content"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
	},
	fCreator: "createSEditor2"
});

function pasteHTML() {
	var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
	oEditors.getById["content1"].exec("PASTE_HTML", [sHTML]);
}

function showHTML() {
	var sHTML = oEditors.getById["content1"].getIR();
	oEditors.getById["castContent"].exec("PASTE_HTML", [sHTML]);
}

function showHTML() {
	var sHTML = oEditors.getById["castContent"].getIR();
	alert(sHTML);
}
	
function submitContents(elClickedObj) {
	oEditors.getById["castContent"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
	
	// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("content").value를 이용해서 처리하면 됩니다.
	
	try {
		elClickedObj.form.submit();
		return check();
	} catch(e) {}
}

function setDefaultFont() {
	var sDefaultFont = '돋움';
	var nFontSize = 24;
	oEditors.getById["castContent"].setDefaultFont(sDefaultFont, nFontSize);
}
</script>
           
          </form>
         </div>