package com.human.membercommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.human.dao.CartDao;
import com.human.dto.CartVO;
import com.human.dao.MemberDao;
import com.human.dao.OrderDao;
import com.human.dto.MemberVo;
import com.human.dto.OrderVO;


public class MyPageCommand  implements MemberCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		String id = (String) session.getAttribute("login_Id");
		System.out.println(id);
		
		OrderDao orderdao = new OrderDao();
		CartDao cartdao = new CartDao();
		MemberDao memberdao = new MemberDao();
		
		//이름 이메일 출력 
		MemberVo Mapage_name_email = memberdao.Mapage_name_email(id);
		request.setAttribute("Mapage_name_email", Mapage_name_email);
		
		// 입금 전 상품 개수 출력
		OrderVO Mypage_order = orderdao.Mypage_order(id);
		request.setAttribute("Mypage_order", Mypage_order);
		
		// 입금 완료 상품 개수 출력
		CartVO Mypage_cart = cartdao.Mypage_cart(id);
		request.setAttribute("Mypage_cart", Mypage_cart);
	}
}
