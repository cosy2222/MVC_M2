<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id = "person" class = "dao.Person"  scope = "request" />
	<jsp:setProperty property="id" name="person"  value="20220606"/>
	<jsp:setProperty property="name" name="person" value="������"/>
	
	<p> ���̵� : <jsp:getProperty name = "person" property="id"/>
	<p> �̸� : <jsp:getProperty name = "person" property="name"/>

</body>
</html>