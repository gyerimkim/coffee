<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <% request.setCharacterEncoding("utf-8"); %> --%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="utf-8" />	<!-- time, encoding -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<tiles:insertAttribute name="header" />		<!-- link파일 -->
</head>
<body>

<div id="body_wrap">
	<div id="main_wrap">
		<tiles:insertAttribute name="top_menu" />
		
	</div>
	
	<div id="middle_wrap">
	
		<div id="sidebar_wrap">
			<tiles:insertAttribute name="left_menu"/>
		</div>
		<div id="content_wrap">			
			<tiles:insertAttribute name="top_inc" />
			<div id="content_title_wrap">
				<div class="title" >
					<h1 style="text-align: center; vertical-align:middle; margin-top:4%">${doc_title}</h1>
				</div>
			</div>
			<div style="margin:-80px 0px 30px; padding:130px 0px 0px;">
				<tiles:insertAttribute name="main" />
			</div>		
		</div>		
	</div>
	
	<div id="footer_wrap">
		<tiles:insertAttribute name="bottom_inc" />
	</div>	
</div>


</body>
</html>






