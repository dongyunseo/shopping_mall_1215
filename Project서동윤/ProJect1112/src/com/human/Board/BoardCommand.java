package com.human.Board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.dao.BoardDao;
import com.human.dao.ReviewDao;
import com.human.dto.BoardVO;
import com.human.dto.Board_PageingVO;
import com.human.dto.ReviewCountDto;

public class BoardCommand implements BoardInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("게시판 출력 페이징");
		String page = (String) request.getParameter("page");
		String pageDataCount = (String) request.getParameter("pageDataCount");

		if (page == null) {
			page = "1";
		}
		if (pageDataCount == null) {
			pageDataCount = "10";
		}

		BoardDao boardDao = BoardDao.getInstance();
		ArrayList<BoardVO> SelectBoard = boardDao.Select_Board(page, pageDataCount);
		request.setAttribute("SelectBoard", SelectBoard);
		int totalDataCount = BoardDao.totalCount();

		Board_PageingVO board_PageingVO = new Board_PageingVO();
		board_PageingVO.Board_Pageing(Integer.parseInt(page), Integer.parseInt(pageDataCount), totalDataCount);
		request.setAttribute("board_PageingVO", board_PageingVO);
		System.out.println("현제 페이지 넘버 page = " + page);
		System.out.println("한 페이지에 출력할 게시물 개수 pageDataCount = " + pageDataCount);
	}

}
