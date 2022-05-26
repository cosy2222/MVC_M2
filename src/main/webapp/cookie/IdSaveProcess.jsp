<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "utils.CookieManager" %>
<%@ page import = "utils.JSFunction" %>

<%
	// 폼에서 넘기는 파라메타 값 받기
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String save_check = request.getParameter("save_check");
	
	if("kang".equals(id) && "1234".equals(pwd)){
		//로그인 성공
		if(save_check != null && save_check.equals("Y")) {   // 아이디 저장 체크 : 쿠키생성
			//쿠키저장
			CookieManager.makeCookie(response , "loginID" , id , (60 * 60 * 24) );
		}else{    // 아이디 저장 체크 해제 : 쿠키삭제
			CookieManager.deleteCookie(response, "loginID");
		}
		
		// 로그인 성공후 페이지로 이동 
		JSFunction.alertLocation("로그인 성공 했습니다", "IdSaveMain.jsp", out);
		
	}else{
		//로그인 실패 이후 페이지 이동
	}
%>