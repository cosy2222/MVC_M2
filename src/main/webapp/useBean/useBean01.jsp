<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>자바 빈 사용 </title>
</head>
<body>
	<jsp:useBean id = "date" class ="java.util.Date"/>
	
	<!-- Java Bean은 JSP페이지 에서 *.java 페이지에게 로직을 처리하도록 JSP페이지에서 셋팅  -->
	
	<p> <%
	
		out.println("오늘의 날짜 및 시간");
	%>
	
	<p> <%= date %>
</body>
</html>