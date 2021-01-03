package com.human.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.human.dao.OrderDao;
import com.human.dto.OrderVO;

/**
 * Servlet implementation class OrderCheckServlet
 */
@WebServlet("/OrderCheckServlet")
public class OrderCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("OrderCheckServlet 서블릿 이동 완료");
		HttpSession session = request.getSession();

		String id = (String) session.getAttribute("login_Id");
		String order_before = request.getParameter("order_before");
		String order_confirmation = request.getParameter("order_confirmation");

		if (order_before == null) {
			System.out.println(order_confirmation);
			System.out.println("접속한 ID = " + id);
			response.getWriter().write(getJSON(order_confirmation, id));

		} else {
			System.out.println(order_before);
			System.out.println("접속한 ID = " + id);
			response.getWriter().write(getJSONbefore(order_confirmation, id));

		}
	}

	// order 입금 완료 물품 출력
	public String getJSON(String order_confirmation, String id) {
		System.out.println(" 입금 완료한 주문 목록 검색중....");
		if (order_confirmation == null)
			order_confirmation = "";
		StringBuffer result = new StringBuffer("");
		DateFormat formatDate = DateFormat.getDateInstance();
		DecimalFormat formatNumber = new DecimalFormat("###,###,###");
		// Servlet import DecimalFormat,DateFormat
		result.append("{\"result\":[");
		OrderDao orderDao = OrderDao.getInstance();
		ArrayList<OrderVO> Deposi_completed_ajax = orderDao.Deposi_completed_ajax(id);
		System.out.println(Deposi_completed_ajax);
		/* if (Deposi_completed_ajax!=null) { 왜 Deposi_completed_ajax == []?? */
		for (int i = 0; i < Deposi_completed_ajax.size(); i++) {
			result.append("[{\"value\":\"" + Deposi_completed_ajax.get(i).getDressimg() + "\"},");
			result.append("{\"value\":\"" + Deposi_completed_ajax.get(i).getDressname() + "\"},");
			result.append("{\"value\":\"" + formatNumber.format(Deposi_completed_ajax.get(i).getPrice()) + "\"},");
			result.append("{\"value\":\"" + Deposi_completed_ajax.get(i).getAmount() + "\"},");
			result.append("{\"value\":\"" + "2,500" + "\"},");
			result.append("{\"value\":\"" + formatNumber.format(Deposi_completed_ajax.get(i).getSum()) + "\"},");
			result.append("{\"value\":\"" + formatDate.format(Deposi_completed_ajax.get(i).getOrderDate()) + "\"},");
			result.append("{\"value\":\"" + "<details><summary>" + Deposi_completed_ajax.get(i).getDelivery()
					+ "</summary><p> 입금 계좌 <br> " + Deposi_completed_ajax.get(i).getBank() + "<p></details>" + "\"},");
			result.append("{\"value\":\""
					+ "<a style='color: navy; text-decoration: none' href='../order/orderDelete.co?ordernum="
					+ Deposi_completed_ajax.get(i).getOrdernum() + "'>" + "삭 제" + "</a>" + "\"}],\n");
		}
		/*
		 * } else { result.append("[{\"value\":\"" + "입금을 하신 상품이 없습니다." + "\"}],"); }
		 */

		System.out.println(result);
		result.append("]}");
		return result.toString();

	}

	// order 입금 전 물품 출력
	public String getJSONbefore(String order_before, String id) {
		System.out.println(" 입금 전 주문 목록 검색중....");
		if (order_before == null)
			order_before = "";
		StringBuffer result = new StringBuffer("");
		DateFormat formatDate = DateFormat.getDateInstance();
		DecimalFormat formatNumber = new DecimalFormat("###,###,###");
		// Servlet import DecimalFormat,DateFormat
		result.append("{\"result\":[");
		OrderDao orderDao = OrderDao.getInstance();
		ArrayList<OrderVO> before_deposit_ajax = orderDao.before_deposit_ajax(id);
		for (int i = 0; i < before_deposit_ajax.size(); i++) {
			result.append("[{\"value\":\"" + before_deposit_ajax.get(i).getDressimg() + "\"},");
			result.append("{\"value\":\"" + before_deposit_ajax.get(i).getDressname() + "\"},");
			result.append("{\"value\":\"" + formatNumber.format(before_deposit_ajax.get(i).getPrice()) + "\"},");
			result.append("{\"value\":\"" + before_deposit_ajax.get(i).getAmount() + "\"},");
			result.append("{\"value\":\"" + "2,500" + "\"},");
			result.append("{\"value\":\"" + formatNumber.format(before_deposit_ajax.get(i).getSum()) + "\"},");
			result.append("{\"value\":\"" + formatDate.format(before_deposit_ajax.get(i).getOrderDate()) + "\"},");
			result.append("{\"value\":\"" + "<details><summary>" + before_deposit_ajax.get(i).getDelivery()
					+ "</summary><p> 입금 계좌 <br> " + before_deposit_ajax.get(i).getBank() + "<p></details>" + "\"},");
			result.append("{\"value\":\""
					+ "<a style='color: navy; text-decoration: none' href='../order/orderDelete.co?ordernum="
					+ before_deposit_ajax.get(i).getOrdernum() + "'>" + "삭 제" + "</a>" + "\"}],\n");
		}
		System.out.println(result);
		result.append("]}");
		return result.toString();

	}

}