<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import = "logon02.LogonDAO" %>

<% request.setCharacterEncoding("utf-8");%>

<%
	String id = request.getParameter("id");

	LogonDAO dao = LogonDAO.getinstance();
	
	int check = dao.checkId(id);
	
	out.print(check);
%>