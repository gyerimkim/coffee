<%@page import="bit.com.a.dto.NoticeDto"%>
<%@page import="bit.com.a.dto.SearchParam"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="./jquery/jquery.twbsPagination.min.js"></script>
<!-- 폰트 어썸 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />

<style>
a:link{
	  	color:blue;
}
a:hover {
	text-decoration: underline;
}
a:visited {
  	color: black;
}


</style>

<script type="text/javascript">
var search = "${search}";
var category = "${category}";

$(document).ready(function () {
	if(search != ""){
		$("#_category").val( category );
		
		document.getElementById("_search").value = search;
	}	
});
</script>

<div align="right"> 
		<button type="button" id="_btnAdd" class="btn btn-primary">글쓰기</button>
</div>

<table class="table table-hover" style="width: 85%">

<colgroup>
	<col width="40"><col width="40"><col width="200">
	<col width="40"><col width="70"><col width="100">
</colgroup>

<thead>
<tr class="table-primary">
    <th>즐겨찾기</th><th>번호</th><th>제목</th>
	<th>조회수</th><th>작성자</th><th>작성일</th>
</tr>	
</thead>

<tbody>
<c:if test="${empty noticelist}">
<tr>
	<td colspan="5">작성된 글이 없습니다</td>
</tr>
</c:if>

<c:forEach var="notice" items="${noticelist}" varStatus="i">
<tr>
	  <td>
	  <button id="bookmark${notice.seq}" onclick="bookmark(${notice.seq})" style="color:gray; border: 0; background-color: white;">  
	  <i class="fas fa-star"></i>
	  </button>
	  </td>
	   <th><c:if test="${notice.category == '긴급' }"> 
			📌	
		</c:if>
		<c:if  test="${notice.category == '공지' }">
			${i.count}
		</c:if>
	</th>
	<td style="text-align: left; color: red;">
		<a href="noticedetail.do?seq=${notice.seq}">
			${notice.title}
			<c:if test="${notice.filename != null }"> 
			 	📁	
			 </c:if>
		</a>
	</td>
	<td>${notice.readcount}</td>
	<td>${notice.username}</td>
	<td>${notice.createdate.substring(2,11)}</td>	
</tr>
</c:forEach>
</tbody>
</table>
<br><br>
<!-- pagination -->
<div class="container">
    <nav aria-label="Page navigation">
        <ul class="pagination" id="pagination" style="justify-content:center;"></ul>
    </nav>
</div>
<hr>
<!-- 검색 -->
<div align="center">
<table>
<tr>
	<td style="padding-left: 5px">
		<select class="form-select" id="_choice" name="choice">
			<option value="" selected="selected">선택</option>
			<option value="title">제목</option>
			<option value="content">내용</option>
			<option value="username">작성자</option>
		</select>	
	</td>
	<td style="padding-left: 5px">
		<input type="text" id="_search" name="search">
	</td>
	<td style="padding-left: 5px">
			<button type="button" id="btnSearch" class="btn btn-primary btn-sm" >검색</button>
	</td>
</tr>
</table>
</div>

<br>

<script>
let totalCount = ${totalCount};	
let nowPage = ${pageNumber};	

let pageSize = 10;

let _totalPages = totalCount / pageSize;
if(totalCount % pageSize > 0){
	_totalPages++;
}

$("#pagination").twbsPagination({
	startPage: nowPage,
	totalPages: _totalPages,
	visiblePages: 10,
	first:'<span sria-hidden="true">«</span>',
	prev:"이전",
	next:"다음",
	last:'<span sria-hidden="true">»</span>',
	initiateStartPageClick:false,		
	onPageClick:function(event, page){
		location.href = "noticelist.do?search=" + $("#_search").val() + "&category=" + $("#_category").val() + "&pageNumber=" + (page - 1);	
	}
});	

</script>


<script>
$("#_btnAdd").click(function () {
	location.href = "noticewrite.do";	
});


$("#btnSearch").click(function () {
	location.href = "noticelist.do?search=" + $("#_search").val() + "&category=" + $("#_category").val();	
});

function bookmark(no_seq){
//	alert(no_seq);
	$.ajax({
		url:"bookmark.do",
		type:"post",
		data:{"notice_seq" : no_seq, "userno" : ${login.userNo} },
		success:function(data){
			if(data == "add"){
				alert('추가되었습니다!');
				$("#bookmark"+no_seq).css("color", "#ffbb00");
			}else{
				alert('해제되었습니다!');
				$("#bookmark"+no_seq).css("color", "gray");
			}
			
			
		},
		error:function(){
			alert('error');
		}
	})
	
	
	
}
</script>
















    












    