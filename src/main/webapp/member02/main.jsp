<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="../js/jquery-1.11.0.min.js"></script>
<script src="login.js"></script>
    
<%
String id ="";
try{
	id = (String)session.getAttribute("id");
%>

<%if(id == null || id.equals("")){ %>
  <div id="status">
     <ul>
        <li><label for="id">아이디</label>
            <input id="id" name="id" type="email" size="20" 
              maxlength="50" placeholder="example@kings.com">
        <li><label for="passwd">비밀번호</label>
            <input id="passwd" name="passwd" type="password" 
              size="20" placeholder="6~16자 숫자/문자" maxlength="16">
        <li class="label2">
            <button id="login">로그인</button>
            <button id="register">회원가입</button>
     </ul>
  </div>
<%}else{ %>
  <div id="status">
     <ul>
        <li><b><%=id %></b>님이 로그인 하셨습니다.
        <li class="label2"><button id="logout">로그아웃</button>
     </ul>
  </div>
<%}}catch(Exception e){e.printStackTrace();}%>