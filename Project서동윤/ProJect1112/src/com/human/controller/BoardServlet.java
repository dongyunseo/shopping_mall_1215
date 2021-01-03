package com.human.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.Board.BoardCommand;
import com.human.Board.BoardInterface;
import com.human.Board.Board_DeleteCommand;
import com.human.Board.Board_DetailCommand;
import com.human.Board.Board_InsertCommand;
import com.human.Board.Board_UpdatereadyCommand;
import com.human.Board.Board_UpdatesuccessCommand;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("*.bo")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("BoardController doGet");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("BoardController doPost");
		request.setCharacterEncoding("UTF-8");
		BoardInterface bCom = null;

		// ����ڿ��� ������ view ����
		String viewPage = null;
		String uri = request.getRequestURI();
		System.out.println(uri);
		String conPath = request.getContextPath();
		System.out.println(conPath);
		String com = uri.substring(conPath.length());
		System.out.println(com);

		if (com.equals("/Board/board.bo")) {
			viewPage = "board.jsp";
			bCom = new BoardCommand();
			bCom.execute(request, response);
		} else if (com.equals("/Board/Board_Detail.bo")) {
			viewPage = "Board_Detail.jsp";
			bCom = new Board_DetailCommand();
			bCom.execute(request, response);
		} else if (com.equals("/Board/Board_Insert.bo")) {
			viewPage = "board.bo";
			bCom = new Board_InsertCommand();
			bCom.execute(request, response);
		} else if (com.equals("/Board/Board_Delete.bo")) {
			viewPage = "board.bo";
			bCom = new Board_DeleteCommand();
			bCom.execute(request, response);
		} else if (com.equals("/Board/Board_UpdateReady.bo")) {
			viewPage = "Board_Update.jsp";
			bCom = new Board_UpdatereadyCommand();
			bCom.execute(request, response);
		} else if (com.equals("/Board/Board_Update.bo")) {
			viewPage = "Board_Detail.bo";
			bCom = new Board_UpdatesuccessCommand();
			bCom.execute(request, response);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
