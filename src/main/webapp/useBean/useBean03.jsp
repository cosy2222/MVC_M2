<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Bean�� Getter �� RAM�� ���� ȣ�� </title>
</head>
<body>

	<jsp:useBean id = "person"  class = "dao.Person" scope = "request"/>
	
	<p> ���̵� : <%= person.getId() %>
	<p> �̸� : <%= person.getName() %>

</body>
</html>