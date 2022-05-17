package com.controller;

import java.io.IOException;

import com.model.LoginBean;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Mycontroller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Client에서 Get방식으로 요청할 경우 처리하는 블락 
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Client에서 post방식으로 요청할 경우 처리하는 블락 
		
		response.setContentType("text.html; charset = UTF-8");
			// client에 뷰 페이지로 전송할 contentType을 정의
		String id = request.getParameter("id");
		String password = request.getParameter("passwd");
		
		LoginBean bean = new LoginBean();
		bean.setId(id);
		bean.setPassword(password);
		
		request.setAttribute("bean", bean);
		
		boolean status = bean.validate();	 // password = admin이면  true
		
		if (status) {
			RequestDispatcher rd = request.getRequestDispatcher("mvc_success.jsp");
			rd.forward(request , response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("mvc_error.jsp");
			rd.forward(request , response);
		}
		
		
	}		
	
	

}
