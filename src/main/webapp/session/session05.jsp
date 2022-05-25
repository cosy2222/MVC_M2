<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2> 세션에 저장된 모든 속성(필드)를 제거 : session.invlidate()</h2>

<h4> ========= 세션 삭제하기 전 ============</h4>
<%
	String id = (String)session.getAttribute("id");
	String pwd = (String)session.getAttribute("pwd");
	
	out.println("설정된 세션 이름 id :" + id);
	out.println("<p> 설정된 세션 이름 pwd :" + pwd + "<br><br>");
	
	if(request.isRequestedSessionIdValid() == true){
		out.println("세션이 유효합니다");
	}else{
		out.println("세션이 유효하지 않습니다");
	}
	
	session.invalidate();

%>

<h4> ========= 세션 삭제 후 ============</h4>

<%
	if(request.isRequestedSessionIdValid() == true){
		out.println("세션이 유효합니다");
	}else{
		out.println("세션이 유효하지 않습니다");
	}


%>



</body>
</html>