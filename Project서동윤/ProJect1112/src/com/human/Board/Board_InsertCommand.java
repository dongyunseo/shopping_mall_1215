package com.human.Board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.dao.BoardDao;

public class Board_InsertCommand implements BoardInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String id = request.getParameter("id");

		System.out.println("게시판 작성자 = "+ id);
		System.out.println("게시판 title = "+ bTitle);
		System.out.println("게시판 content = " + bContent);
		
		// 인스턴스 boardDao 생성
		BoardDao boardDao = new BoardDao();
		boardDao.Board_Insert(bTitle,bContent,id);
		

	}

}
