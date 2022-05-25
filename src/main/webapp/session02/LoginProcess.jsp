<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "membership.MemberDTO" %>
<%@ page import = "membership.MemberDAO" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	// form에서 넘겨주는 변수의 값
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	
	// DAO 객체 호출 회원정보에 대한 값을 DTO로 넘겨받는다
	MemberDAO dao = new MemberDAO();
	MemberDTO dto = dao.getMemberDTO(id, pwd);
	dao.close();
	
	// 로그인 성공여부에 따라 처리 
	if(dto.getName() == null || dto.equals("")){
		out.println("로그인 실패");
		request.setAttribute("LoginErrMsg", "로그인 오류 입니다");
		request.getRequestDispatcher("LoginForm.jsp").forward(request, response);
		
	}else {
		out.println("로그인 성공");
		session.setAttribute("id", dto.getId());
		session.setAttribute("name", dto.getName());
		response.sendRedirect("LoginForm.jsp");
	}



%>



</body>
</html>