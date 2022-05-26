<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<%
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");

	if(id.equals("admin") && pwd.equals("1234")){
		// 쿠키 생성  , 클라이언트 HDD에 쿠키의 값을 저장
		
		// 쿠키는 생성자에서 변수 이름과 값을 할당
		Cookie cookie_id = new Cookie("userid" , id);
		Cookie cookie_pwd = new Cookie("userpwd" , pwd);
		
		//쿠키 설정
		cookie_id.setPath(request.getContextPath());  //쿠키를 사용할 서버의 경로 설정
		cookie_id.setMaxAge(60 * 60);   // 3600초 == 1시간
		
		cookie_pwd.setPath(request.getContextPath());
		cookie_pwd.setMaxAge(60 * 60);
		
		//쿠키를 response객체를 사용해서 생성된 쿠키를 Client HDD에 저장 
		response.addCookie(cookie_id);
		response.addCookie(cookie_pwd);
		
		out.println("쿠키 생성이 성공 했습니다");
		out.println(id + "님 ㅎㅇ");
	}else{
		out.println("쿠키 생성 실패");
	}

%>

</body>
</html>