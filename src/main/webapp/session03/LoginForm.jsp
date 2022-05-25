<%@page import="membership03.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>session</title>
</head>
<body>

<table border = "1" width = "90%">

	<tr>
		<td align = "center">
		<!-- 로그인 여부에 따른 메뉴 변화 -->
		<% if (session.getAttribute("id") == null) { %>
				<a href = "../session02/LoginForm.jsp">로그인</a>
		<% }else { %>
			<a href = "../session02/Logout.jsp">로그아웃</a>
		<% } %>	
		</td>
	</tr>

</table>


<h2> 로그인 페이지</h2>

<span style = "color:red; font-size:1.2em;">
	<!-- 로그인 error 메세지 -->
	<%= request.getAttribute("LoginErrMsg") == null ? "" : request.getAttribute("LoginErrMsg")
	
	%></span>

<% if (session.getAttribute("id") == null){ //로그아웃 상태일떄 %>  
	<!-- 로그아웃 상태일떄 HTML 처리 부분 -->
	
	<script>
		function validateForm (form){
			if (!form.id.value){
				alert ("아이디를 입력 해주세요");
				return false;
			}
			if(form.pwd.value == ""){
				alert("비밀번호를 입력해주세요")
			}
		}
	</script>
	
	<form action="LoginProcess.jsp" method = "post" name = "loginfrm" 
		  onsubmit = "return validateForm(this);">
		<p>아이디 : <input type="text" name = "id">
		<p>비밀번호 : <input type="password" name = "pwd">
		<p> <input type="submit" value="로그인" > 
	</form>
	
	
	
	
	
<% }else {    // 로그인 상태일때 %>
	<!-- 로그인 상태일떄 HTML 처리 부분 -->
	<%if(session.getAttribute("grade").equals("vip")) { %>
		<table border = "1" bordercolor="red">
			<td>vip</td>
		</table>
	<% } %>
	
	<%if(session.getAttribute("grade").equals("gold")) { %>
		<table border = "1" bordercolor="yellow">
			<td>gold                         </td>
		</table>
	<% } %>
	
	<%if(session.getAttribute("grade").equals("silver")) { %>
		<table border = "1" bordercolor="blue">
			<td>silver</td>
		</table>
		
		
	<% } %>
	
	<%= session.getAttribute("name") %> 회원님 ㅎㅇ  <br>
	<a href="Logout.jsp">[로그아웃]</a>

<% } %>

















</body>
</html>