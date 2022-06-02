<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import = "logon02.LogonDAO" %>

<% request.setCharacterEncoding("utf-8");%>


<%
	String id = request.getParameter("id");
	String pwd = request.getParameter("passwd");
	
	LogonDAO dao = LogonDAO.getinstance();
	
	int check = dao.check(id, pwd);
			
	if(check == 1){
		session.setAttribute("id",id);
	}
	
	out.print(check);

%>







