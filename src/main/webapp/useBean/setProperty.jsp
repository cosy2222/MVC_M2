<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id = "person"  class = "dao.Person" scope = "request" />
	
	<!--  setProperty : setter ���� -->

	<jsp:setProperty name = "person" property ="id" value = "20220601" />
	<jsp:setProperty name = "person" property ="name" value = "����" />
	
	<p> ���̵� : <%= person.getId() %>
	<p> �̸� : <%= person.getName() %>
	
</body>
</html>