package com.human.cartorderCommand;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.human.dao.OrderDao;
import com.human.dto.OrderVO;

public class OrderListCommand implements CartOrderCommand{
// �ֹ� ���������� �ֹ� List ���
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		String id = (String) session.getAttribute("login_Id");

		System.out.println(id);
		OrderDao orderDao = OrderDao.getInstance();
		ArrayList<OrderVO> ListOrder = orderDao.OrderList(id);
		request.setAttribute("ListOrder", ListOrder);
	}

}
