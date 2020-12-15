package com.human.membercommand;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.dao.MemberDao;


/**
 * Servlet implementation class ForgetPw_if_Servlet
 */
@WebServlet("/ForgetPw_if_Servlet")
public class ForgetPw_if_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ForgetPw_if_Servlet doPost");
		request.setCharacterEncoding("UTF-8");

		String AuthenticationKey = request.getParameter("AuthenticationKey");
		String Auth_number = request.getParameter("Auth_number");

	
		response.getWriter().write(new MemberDao().check_EmailAuth(Auth_number,AuthenticationKey) + "");

	}
}