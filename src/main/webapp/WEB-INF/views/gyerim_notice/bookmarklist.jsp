<%@page import="bit.com.a.dto.NoticeDto"%>
<%@page import="bit.com.a.dto.SearchParam"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="./jquery/jquery.twbsPagination.min.js"></script>
<!-- 폰트 어썸 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />



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

<table class="list_table" style="width: 85%">

<colgroup>
	<col width="40"><col width="40"><col width="200">
	<col width="40"><col width="70"><col width="100">
</colgroup>

<thead>
<tr>
    <th>즐겨찾기</th><th>번호</th><th>제목</th>
	<th>조회수</th><th>작성자</th><th>작성일</th>
</tr>	
</thead>

<tbody>
<c:if test="${empty blist}">
<tr>
	<td colspan="5">즐겨찾기 한 글이 없습니다</td>
</tr>
</c:if>

<c:forEach var="bookmark" items="${blist}" varStatus="i">
<tr id="bookmarkRow${bookmark.seq}">
	  <td>
	  <button id="bookmark${bookmark.seq}" onclick="bookmark(${bookmark.seq},${bookmark.notice_seq})" style="color:#ffbb00; border: 0; background-color: white;"> 
	  <i class="fas fa-star"></i>
	  </button>
	  </td>
	   <th>
			${i.count}
	</th>
	<td style="text-align: left; color: red;">
		<a href="noticedetail.do?seq=${bookmark.notice_seq}">
			${bookmark.title}
		</a>
	</td>
	<td>${bookmark.readcount}</td>
	<td>${bookmark.username}</td>
	<td>${bookmark.createdate.substring(2,11)}</td>	
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
		<select id="_choice" name="choice">
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
			<button type="button" id="btnSearch" style="background-color: #2E9AFE; border-width:thin; color: white; font-weight: bold; border-color:#D8D8D8;" >검색</button>
	</td>
</tr>
</table>
</div>

<br>

<script>
/*
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
*/
</script>


<script>
$("#btnSearch").click(function () {
	location.href = "noticelist.do?search=" + $("#_search").val() + "&category=" + $("#_category").val();	
});

function bookmark(seq, no_seq){
//	alert(no_seq);
	$.ajax({
		url:"bookmark.do",
		type:"post",
		data:{"notice_seq" : no_seq, "userno" : ${login.userNo} },
		success:function(data){
			$('#bookmarkRow' + seq).remove();	
		},
		error:function(){
			alert('error');
		}
	})	
	
}
</script>