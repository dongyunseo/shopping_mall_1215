package com.human.Board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.dao.BoardDao;
import com.human.dto.BoardVO;

public class Board_DetailCommand implements BoardInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 상세 페이지 출력시 필요한 변수들
		String bNumber = request.getParameter("bNumber");
		
		// 댓글 입력시 필요한 변수들
		String id = request.getParameter("id");
		String bGroup = request.getParameter("bGroup");
		String bContent = request.getParameter("bContent");
		String bIndent = request.getParameter("bIndent");
		
		// 인스턴스 boardDao 생성
		BoardDao boardDao = new BoardDao();

		if (id != "" && id != null) {
			System.out.println("id : " + id);
			System.out.println("bGroup : " + bGroup);
			System.out.println("bContent(댓글 내용) :  " + bContent);
			System.out.println("bIndent (댓글 순서,개수) :  " + bIndent);
			// 댓글 입력
			boardDao.boardrelpy(bContent, id, bGroup, bIndent);
			// 댓글 출력
			ArrayList<BoardVO> boardreply = boardDao.boardreply(bGroup);
			request.setAttribute("boardreply", boardreply);
			System.out.println("글 넘버 : " + bNumber);
		}
		// 상세페이지 출력
		BoardVO boardDetil = boardDao.Board_Detail(bNumber);
		request.setAttribute("boardDetil", boardDetil);
		// 댓글 출력
		ArrayList<BoardVO> replyboard = boardDao.boardreply(bNumber);
		request.setAttribute("replyboard", replyboard);
	}
}
