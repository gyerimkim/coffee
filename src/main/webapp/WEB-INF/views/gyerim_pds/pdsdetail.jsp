<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.line{
	border-bottom: 1px solid #E6E6E6;
}
</style>
<table class="list_table" style="width: 880px; height: 270px;">

<colgroup>
	<col width="150px"><col width="290px"><col width="150px"><col width="290px">
</colgroup>

<tr class="line">
	<th colspan="4" style="text-align: center; font-weight:bold; font-size:x-large;">${pds.title }</th>
</tr >
<tr class="line" align="center">
	<th style="background-color:#F2F2F2;">작성자</th>
	<td  style="text-align: left;" >${pds.username }</td>
	<th style="background-color:#F2F2F2;">등록일</th>
	<td style="text-align: left;">${pds.regdate.substring(0,19) }</td>	
</tr>
<tr class="line" align="center">
	<th style="background-color:#F2F2F2;">다운수</th>
	<td style="text-align: left;">${pds.downcount }</td>
	<th style="background-color:#F2F2F2;">조회수</th>
	<td style="text-align: left;">${pds.readcount }</td>
</tr>
<tr class="line" align="center">
	<th style="background-color:#F2F2F2;">파일명</th>
	<td colspan="3" style="text-align: left;">${pds.filename } 
		&nbsp;&nbsp; <input type="button" name="btnDown" value="다운로드📩"=
		onclick="filedown('${pds.newfilename}', '${pds.seq }', '${pds.filename}')" 
		style="background-color: white; border-color: blue; border-width:thin; color:blue; font-size: small; height: 35px;">
	</td>
</tr>
<tr align="center">
	<th style="background-color:#F2F2F2;">내용</th>
	<td colspan="3" style="text-align: left;"><textarea rows="8" cols="100">${pds.content }</textarea></td>
</tr>
</table>
<br><br>
<!-- 수정하기 -->
<%-- <c:if test="${login.id eq pds.id }"> --%>
<div id="buttons_wrap" align="center">
	<span class="button blue">
		<button type="button" id="_btnUpdate">수정하기</button>
		<button type="button" id="_btnDel" title="삭제">삭제하기</button>
	</span>
</div>
<%-- </c:if> --%>
<br>
<!-- seq만 필요하므로 -->
<form name="frmForm" id="_frmForm" action="pdsupdate.do" method="post">
	<input type="hidden" name="seq" value="${pds.seq }">
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
	$("#_frmForm").attr({ "target":"_self", "action":"pdsdelete.do" }).submit();
});
</script> 













