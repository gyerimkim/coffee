<%@page import="bit.com.a.dto.PdsDto"%>
<%@page import="bit.com.a.dto.SearchParam"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="./jquery/jquery.twbsPagination.min.js"></script>

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

<div id="button.wrap" align="right">
		<button type="button" id="_btnAdd" class="btn btn-primary">자료추가</button>
</div>

<table class="table table-hover" style="width: 85%">
<colgroup>
	<col width="50"><col width="200"><col width="50"><col width="100"><col width="120">
</colgroup>

<thead>
<tr class="table-primary">
	<th>번호</th><th>제목</th><th>조회수</th><th>작성자</th><th>작성일</th>
</tr>	
</thead>

<tbody>
<c:if test="${empty pdslist}">
<tr>
	<td colspan="5">작성된 글이 없습니다</td>
</tr>
</c:if>

<c:forEach var="pds" items="${pdslist}" varStatus="i">
<tr>
	<th>${i.count}</th>
	<td style="text-align: left;">
		<a href="pdsdetail.do?seq=${pds.seq}">
			${pds.title} &nbsp;&nbsp;&nbsp;</a>
			<input type="button" name="btnDowm" value="다운로드"
			onclick="filedown('${pds.newfilename}', '${pds.seq}', '${pds.filename}')" style="background-color: white; border-color: #848484; border-width:thin; font-size: x-small; height: 25px;">			
	</td>
	<td>${pds.readcount}</td>
	<td>${pds.username}</td>
	<td>${pds.regdate.substring(2,11)}</td>
</tr>
</c:forEach>
</tbody>
</table>
<br><br>
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
</table></div>
<br>

<!-- pagination -->
<div class="container">
    <nav aria-label="Page navigation">
        <ul class="pagination" id="pagination" style="justify-content:center;"></ul>
    </nav>
</div>
<hr>


<form name="file_Down" action="fileDownload.do" method="post">
	<input type="hidden" name="newfilename">
	<input type="hidden" name="seq">
	<input type="hidden" name="filename">
</form>



<script>
function filedown(newfilename, seq, filename) {
	
	let doc = document.file_Down;
	doc.newfilename.value = newfilename;
	doc.seq.value = seq;
	doc.filename.value = filename;
	doc.submit(); 
}

$("#_btnAdd").click(function () {
	location.href = "pdswrite.do";	
});

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
















    