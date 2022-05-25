<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>session 객체에 변수의 값을 할당 </title>
</head>
<body>

<%	// client 폼에서 ID , password
	String id = request.getParameter("id");
    String pwd = request.getParameter("pwd");
    
    //DB에서 가져온 값 
    if(id.equals("admin") && pwd.equals("1234")){
    	// 세션에 세션변수의 값을 할당
    	session.setAttribute("id", id);
    	session.setAttribute("pwd", pwd);
    	
    	out.println("세션설정이 성공 했습니다");
    	out.println(id + "님 ㅎㅇ");
    	
    }else{
    	out.println("세선설정이 실패 했습니다");
    }





%>


</body>
</html>