package com.human.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PageingServlet
 */
@WebServlet("/PageingServlet")
public class PageingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("PageingServlet 서블릿 이동 완료");
		
		String dressid = request.getParameter("dressid");
		String page = (String) request.getParameter("page");
		String pageDataCount = (String) request.getParameter("pageDataCount");
		
		System.out.println("상품 number : "+dressid);
		System.out.println("현제 페이지 page : " + page);
		System.out.println("한 페이지에 보여줄 게시글 수  : "+ pageDataCount);
	}

}
