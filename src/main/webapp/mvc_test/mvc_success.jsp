<%@page import="com.model.LoginBean"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α��� ���� ������</title>
</head>
<body>
	<p> �α��� ���� �߽��ϴ�
	
	<p> <% 
		LoginBean bean = (LoginBean)request.getAttribute("bean"); 
		out.println("���̵� : " + bean.getId());
	
		%>
	

</body>
</html>