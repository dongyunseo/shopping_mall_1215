package com.human.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.human.dto.CartVO;
import com.human.dto.OrderVO;
import com.human.dto.QandAVo;
import com.human.util.DBConn;

public class OrderDao {

	public OrderDao() {

	}

	private static OrderDao instance = new OrderDao();

	public static OrderDao getInstance() {
		return instance;
	}

	// -------------------- �ֹ� ���-------------------
	public void OrderInsert(OrderVO orderVo) {
		String sql = "insert into tbl_order(ordernum,id,dressid,dressimg,dressname,price,amount,sum,"
				+ "ordername,address,phone,email,orderMessage,depositor,bank,delivery)"
				+ "VALUES(tbl_order_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'입금 전')";
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderVo.getId());
			System.out.println("주문자 아이디 : " + orderVo.getId());
			pstmt.setInt(2, orderVo.getDressid());
			System.out.println("상품 아이디 :" + orderVo.getDressid());
			pstmt.setString(3, orderVo.getDressimg());
			System.out.println("상품 이미지 주소 : " + orderVo.getDressimg());
			pstmt.setString(4, orderVo.getDressname());
			System.out.println("상품 이름 : " + orderVo.getDressname());
			pstmt.setInt(5, orderVo.getPrice());
			System.out.println("상품 가격 : " + orderVo.getPrice());
			pstmt.setInt(6, orderVo.getAmount());
			System.out.println("상품 수량 : " + orderVo.getAmount());
			pstmt.setInt(7, orderVo.getSum());
			System.out.println("주문 총 금액  : " + orderVo.getSum());
			pstmt.setString(8, orderVo.getOrdername());
			System.out.println("주문자 이름: " + orderVo.getOrdername());
			pstmt.setString(9, orderVo.getAddress());
			System.out.println("주소 : " + orderVo.getAddress());
			pstmt.setString(10, orderVo.getPhone());
			System.out.println("주문자 전화번호 : " + orderVo.getPhone());
			pstmt.setString(11, orderVo.getEmail());
			System.out.println("주문자 이메일 : " + orderVo.getEmail());
			pstmt.setString(12, orderVo.getOrderMessage());
			System.out.println("주문 메세지 : " + orderVo.getOrderMessage());
			pstmt.setString(13, orderVo.getDepositor());
			System.out.println("입금자 명 : " + orderVo.getDepositor());
			pstmt.setString(14, orderVo.getBank());
			System.out.println("입금 은행 : " + orderVo.getBank());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);
		}
	}

	// ------------------unll data delete -----------------
	public void OrderHalfNullDelete() {
		String sql = "delete from tbl_order where ordername IS NULL";
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);
		}
	}

	// ------------------orders select -------------------------------
	public ArrayList<OrderVO> OrderList(String userId) {
		ArrayList<OrderVO> ListOrder = new ArrayList<OrderVO>();
		Connection conn = DBConn.getConnection();
		String sql = String.format("select * from tbl_order where id='%s' and delivery='입금 전' order by orderDate desc",
				userId);
		System.out.println(sql);

		Statement st = null;
		ResultSet rs = null;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ListOrder.add(new OrderVO(rs.getInt("ordernum"), rs.getString("id"), rs.getInt("dressid"),
						rs.getString("dressname"), rs.getInt("price"), rs.getInt("amount"), rs.getInt("sum"),
						rs.getString("ordername"), rs.getString("address"), rs.getString("phone"),
						rs.getString("email"), rs.getString("orderMessage"), rs.getString("depositor"),
						rs.getString("bank"), rs.getString("delivery"), rs.getTimestamp("orderDate"),
						rs.getString("dressimg"), 0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListOrder;
	}

	// ------------------orders selectajax -------------------------------
	public ArrayList<OrderVO> before_deposit_ajax(String userId) {
		ArrayList<OrderVO> before_deposit_ajax = new ArrayList<OrderVO>();
		Connection conn = DBConn.getConnection();
		String sql = String.format("select * from tbl_order where id='%s' and delivery='입금 전' order by orderDate asc",
				userId);
		System.out.println(sql);

		Statement st = null;
		ResultSet rs = null;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				before_deposit_ajax.add(new OrderVO(rs.getInt("ordernum"), rs.getString("id"), rs.getInt("dressid"),
						rs.getString("dressname"), rs.getInt("price"), rs.getInt("amount"), rs.getInt("sum"),
						rs.getString("ordername"), rs.getString("address"), rs.getString("phone"),
						rs.getString("email"), rs.getString("orderMessage"), rs.getString("depositor"),
						rs.getString("bank"), rs.getString("delivery"), rs.getTimestamp("orderDate"),
						rs.getString("dressimg"), 0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return before_deposit_ajax;
	}
	// ------------------orders selectajax -------------------------------

	public ArrayList<OrderVO> Deposi_completed_ajax(String userId) {
		// TODO Auto-generated method stub
		ArrayList<OrderVO> Deposi_completed_ajax = new ArrayList<OrderVO>();
		Connection conn = DBConn.getConnection();
		String sql = String.format("select * from tbl_order where id='%s' and delivery='입금 완료' order by orderDate asc",
				userId);
		System.out.println(sql);

		Statement st = null;
		ResultSet rs = null;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Deposi_completed_ajax.add(new OrderVO(rs.getInt("ordernum"), rs.getString("id"), rs.getInt("dressid"),
						rs.getString("dressname"), rs.getInt("price"), rs.getInt("amount"), rs.getInt("sum"),
						rs.getString("ordername"), rs.getString("address"), rs.getString("phone"),
						rs.getString("email"), rs.getString("orderMessage"), rs.getString("depositor"),
						rs.getString("bank"), rs.getString("delivery"), rs.getTimestamp("orderDate"),
						rs.getString("dressimg"), 0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Deposi_completed_ajax;
	}

//--------------------�ֹ� ���̺� ���� ------------------------------
	public void OrderDelete(int ordernum) {
		String sql = "delete tbl_order where ordernum=?";
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ordernum);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);
		}
	}

// ������ ���������� �ֹ� ���̺� ��ü ���---------------------------------------------------------
	public ArrayList<OrderVO> AdminAllOrderList() {
		// TODO Auto-generated method stub
		ArrayList<OrderVO> adminAllListOrder = new ArrayList<OrderVO>();
		Connection conn = DBConn.getConnection();
		String sql = "select * from tbl_order order by ordernum desc";

		System.out.println(sql);

		Statement st = null;
		ResultSet rs = null;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				adminAllListOrder.add(new OrderVO(rs.getInt("ordernum"), rs.getString("id"), rs.getInt("dressid"),
						rs.getString("dressname"), rs.getInt("price"), rs.getInt("amount"), rs.getInt("sum"),
						rs.getString("ordername"), rs.getString("address"), rs.getString("phone"),
						rs.getString("email"), rs.getString("orderMessage"), rs.getString("depositor"),
						rs.getString("bank"), rs.getString("delivery"), rs.getTimestamp("orderDate"),
						rs.getString("dressimg"), 0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adminAllListOrder;
	}

// 리뷰 작성 시 주문을 했는지 .-----------------------
	public int checkOrder(String id, String dressid) {
		// TODO Auto-generated method stub
		DBConn.getInstance();
		int result = 0;
		String sql = "select * from tbl_order where id='" + id + "' and dressid='" + dressid + "'";
		System.out.println(sql);
		ResultSet rs = DBConn.statementQuery(sql);

		try {
			if (rs.next()) {
				result = 1;
				System.out.println(id + " 님은  " + dressid + "주문을 하셨습니다. ");
			} else {
				result = -1;
				System.out.println(id + " 님은 " + dressid + "주문 이력이 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

		DBConn.dbClose();
		return result;
	}

	public OrderVO Mypage_order(String id) {
		// TODO Auto-generated method stub
		OrderVO Mypage_order = null;
		Connection con = DBConn.getConnection();
		String sql = String.format("select count(id) totalOrders from tbl_order where id='%s'", id);
		System.out.println(sql);
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Mypage_order = new OrderVO(0, sql, 0, sql, 0, 0, 0, sql, sql, sql, sql, sql, sql, sql, sql, null, sql,
						rs.getInt("totalOrders"));
			}
			DBConn.close(st, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Mypage_order;
	}

}