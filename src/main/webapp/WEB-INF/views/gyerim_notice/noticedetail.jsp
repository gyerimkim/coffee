<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.line{
	border-bottom: 1px solid #E6E6E6;
}
</style>

<table class="table table-hover" style="width:85%;">
<colgroup>
	<col width="100px"><col width="340px"><col width="100px"><col width="340px">
</colgroup>

<tr class="table-primary">
	<td colspan="4" style="text-align: center; font-weight:bold; font-size:x-large;">${notice.title }</td>
</tr>
<tr class="line">
	<th>카테고리</th>
	<td style="text-align: left;">${notice.category }</td>	
	<th>작성자</th>
	<td style="text-align: left;">${notice.username }</td>
</tr>
<tr class="line">
	<th>조회수</th>
	<td style="text-align: left;">${notice.readcount }</td>
	<th>등록일</th>
	<td style="text-align: left;">${notice.createdate.substring(0,19) }</td>
</tr>
	<c:if test = "${empty notice.filename }">
		<tr>
			<th>내용</th>
				<td colspan="3" style="text-align: left;">
				<p style="white-space: pre-line;">${notice.content }</p>
			</td>
		</tr>
	</c:if>
	
	<c:if test = "${null ne notice.filename }">	
	<tr class="line">
		<th>내용</th>
		<td colspan="3" style="text-align: left;">
			<img src="./upload/${notice.newfilename} " width="300px"><br><br>
			${notice.content }
		</td>
	</tr>
	<tr class="line">
		<th>첨부파일명</th>
		<td colspan="3" style="text-align: left;">${notice.filename }
		</td>
	</tr>
</c:if>
</table>

<!-- 수정하기 -->

<br>
<div align="center">
<c:if test="${notice.username eq login.userName}">
<button type="button" id="_btnUpdate" class="btn btn-primary">수정</button>
<button type="button" id="_btnDel" title="삭제" class="btn btn-primary">삭제</button>
</c:if>
<a href="noticelist.do" class="btn btn-primary" >글목록</a>
</div><br>

<form name="frmForm" id="_frmForm" action="noticeupdate.do" method="post">
	<input type="hidden" name="seq" value="${notice.seq }">
</form>


<form name="file_Down" action="fileDownload.do" method="post">
	<input type="hidden" name="newfilename">
	<input type="hidden" name="filename">
	<input type="hidden" name="seq">
</form>

<script>
var myVar;
function filedown(newfilename, seq, filename) {
	let doc = document.file_Down;
	doc.newfilename.value = newfilename;
	doc.filename.value = filename;
	doc.seq.value = seq;
	doc.submit();	
	
	myVar = setTimeout(_refrush, 10);
}	

function _refrush() {
	location.reload();
	clearTimeout(myVar);
}

$("#_btnUpdate").click(function () {
	$("#_frmForm").submit();
});

$("#_btnDel").click(function() {			
	$("#_frmForm").attr({ "target":"_self", "action":"noticedelete.do" }).submit();
});


</script> 

