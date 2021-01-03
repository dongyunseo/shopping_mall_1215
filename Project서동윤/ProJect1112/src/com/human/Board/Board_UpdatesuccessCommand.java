package com.human.Board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.dao.BoardDao;

public class Board_UpdatesuccessCommand implements BoardInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bNumber = request.getParameter("bNumber");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");

		System.out.println("수정할 게시글의 넘버 = " + bNumber);
		System.out.println("수정할 게시글의 제목 = " + bTitle);
		System.out.println("수정할 게시글의 내용 = " + bContent);
	
		BoardDao boardDao = new BoardDao();
		boardDao.Board_Update(bNumber,bTitle,bContent);
		
	}

}
