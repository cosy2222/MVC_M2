<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id = "bean" class = "dao.Calculator"/>
	<!-- Calculator 클래스를 bean 객체로 사용 -->
	<!-- Caculator Bean = new Calculator(); -->
	
	<%
		int m = bean.process(5);
	
		out.println(m);
	%>
	<p> <p>
	5의 3제곱은 : <%= m %>
	
</body>
</html>