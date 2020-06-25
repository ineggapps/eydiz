<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   String cp = request.getContextPath();
%>

<script type="text/javascript">
$(function(){
	$("#news-10 span").addClass("on");
	listPage(1);

	$(".boardmenu a").click(function() {
		var nocaNo = $(this).attr("data-nocaNo");
		
		$(".boardmenu a span").each(function(){
			$(this).removeClass("on");
		});
		
		$("#news-"+nocaNo+" span").addClass("on");
	
		reloadNews();
	});
});

function ajaxJSON(url, method, query, fn) {
	$.ajax({
		type:method,
		url:url,
		data:query,
		dataType:"json",
		success:function(data) {
			fn(data);
		},
		beforeSend:function(jqXHR) {
			jqXHR.setRequestHeader("AJAX", true);
		},
		error:function(jqXHR) {
			if(jqXHR.state==403) {
				login();
				return false;
			}
			
			console.log(jqXHR.responseText);
		}
	});
}

function ajaxHTML(url, method, query, selector) {
	$.ajax({
		type:method,
		url:url,
		data:query,
		success:function(data) {
			if($.trim(data)=="error") {
				listPage(1);
				return false;
			}			
			$(selector).html(data);
		},
		beforeSend:function(jqXHR) {
			jqXHR.setRequestHeader("AJAX", true);
		},
		error:function(jqXHR) {
			if(jqXHR.state==403) {
				login();
				return false;
			}
			
			console.log(jqXHR.responseText);
		}
	});
}

function ajaxFileJSON(url, type, query, fn) {
	$.ajax({
		type:type
		,url:url
        ,processData: false  // file 전송시 필수
        ,contentType: false  // file 전송시 필수
		,data:query
		,dataType:"json"
		,success:function(data) {
			fn(data);
		}
		,beforeSend:function(jqXHR) {
	        jqXHR.setRequestHeader("AJAX", true);
	    }
	    ,error:function(jqXHR) {
	    	if(jqXHR.status==403) {
	    		login();
	    		return false;
	    	}
	    	console.log(jqXHR.responseText);
	    }
	});
}

// 글 리스트
function listPage(page) {
	var url="<%=cp%>/news/list";
	var $tab = $(".boardmenu a .on").parent();
	var nocaNo = $tab.attr("data-nocaNo");
	
	var query="pageNo="+page+"&nocaNo="+nocaNo;
	var search=$('form[name=newsSearchForm]').serialize();
	query=query+"&"+search;
	
	ajaxHTML(url, "get", query, "#contentLayout");
}

// 새로고침
function reloadNews() {
	var f=document.newsSearchForm;
	f.condition.value="all";
	f.keyword.value="";
	
	listPage(1);
}

// 검색
function searchList() {
	var f=document.newsSearchForm;
	f.condition.value=$("#condition").val();
	f.keyword.value=$.trim($("#keyword").val());

	listPage(1);
}

// 글쓰기폼
function insertForm() {
	var url="<%=cp%>/news/created";
	// var $tab = $(".boardmenu a .on").parent();
	// var nocaNo = $tab.attr("data-nocaNo");

	var query="tmp="+new Date().getTime();
	var selector = "#contentLayout";
	
	ajaxHTML(url, "get", query, selector);
}

// 글등록, 수정등록
function sendOk(mode, page) {
	var f = document.newsForm;
	
	var str = f.noticeSubject.value;
	if(!str) {
		alert("제목을 입력 하세요...");
		f.noticeSubject.focus();
		return;
	}
/*	
	var str = f.noticeContent.value;
	if(!str) {
		alert("내용을 입력 하세요...");
		f.noticeContent.focus();
		return;
	}
*/		
    var url="<%=cp%>/news/"+mode;
    var query = new FormData(f); // IE는 10이상에서만 가능
    
	var fn = function(data){
		var state=data.state;
        if(state=="false") {
            alert("게시물을 추가(수정)하지 못했습니다. !!!");
            return false;
        }
        
    	if(page==undefined || page=="") {
    		page="1";
    	}
    	
    	if(mode=="created") {
    		reloadNews()
    	} else {
    		listPage(page);
    	}
	};
	
	ajaxFileJSON(url, "post", query, fn);		
}

// 글쓰기 취소, 수정 취소
function sendCancel(page) {
	if(page==undefined || page=="") {
		page="1";
	}
	
	listPage(page);
}

// 글보기
function contentView(noticeNo, page) {
	var $tab = $(".boardmenu a .on").parent();
	var nocaNo = $tab.attr("data-nocaNo");
	
	var url="<%=cp%>/news/article";
	var query="noticeNo="+noticeNo+"&nocaNo="+nocaNo;
	
	var search=$('form[name=newsSearchForm]').serialize();
	query=query+"&pageNo="+page+"&"+search;
	var selector = "#contentLayout";
	
	ajaxHTML(url, "get", query, selector);
}

// 글 수정폼
function updateForm(noticeNo, page) {
	var url="<%=cp%>/news/update";
	var query="noticeNo="+noticeNo+"&pageNo="+page
	var selector = "#contentLayout";
	
	ajaxHTML(url, "get", query, selector);
}

// 글 삭제
function deleteNews(noticeNo, page) {
	var url="<%=cp%>/news/delete";
	
	var query="noticeNo="+noticeNo;

	if(! confirm("위 게시물을 삭제 하시 겠습니까 ? ")) {
		  return;
	}
	
	var fn = function(data){
		listPage(page);
	};
	
	ajaxJSON(url, "post", query, fn);		
}
</script>

<article class="row">
  		<div class="contentWrapper">
        	<div class="mainBoard">
        		<div class="boardHeader">
        			<div class="boardTitle">
        				<h2>공지사항</h2>
        			</div>
        			<div>
        				<ul class="boardmenu">
        					<li class="menuon"> <a href="#" id="news-10" data-nocaNo="10"><span>전체</span></a> </li>
        					<li class="menuon"> <a href="#" id="news-5" data-nocaNo="5"><span>공지</span></a> </li>
        					<li class="menuon"> <a href="#" id="news-6" data-nocaNo="6"><span>이벤트</span></a> </li>
        					<li class="menuon"> <a href="#" id="news-7" data-nocaNo="7"><span>보도자료</span></a> </li>
        				</ul>
        			</div>
        		</div>
        		<div id="contentLayout"></div>
        	</div>
        </div>
        
        <form name="newsSearchForm">
        	<input type="hidden" name="condition" value="all">
        	<input type="hidden" name="keyword" value="">
        </form>
</article>