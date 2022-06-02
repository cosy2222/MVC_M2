<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "logon02.LogonDAO" %>


<% request.setCharacterEncoding("utf-8");%>

<jsp:useBean id="member" class="logon02.LogonDTO">
    <jsp:setProperty name="member" property="*" />
</jsp:useBean>

<%  

  LogonDAO manager = LogonDAO.getinstance();
  manager.insert(member);
%>