package com.human.Board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.dao.BoardDao;

public class Board_DeleteCommand implements BoardInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bNumber = request.getParameter("bNumber");
		String bid = request.getParameter("bid");
		String id = request.getParameter("id");

		System.out.println("삭제할 게시글의 넘버 = "+ bNumber);
		System.out.println("삭제할 게시글의 작성자 = "+ bid);
		System.out.println("현제 로그인한 ID = "+ id);
		
		// bid = 작성한 아이디 , id = 현제 로그인한 아이디
		if (bid.equals(id) && id != "") {
			BoardDao boardDao = BoardDao.getInstance();
			boardDao.Board_Delete(bNumber);
		} else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('고객님께서 작성하신 게시글이 아닙니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
	}

}
