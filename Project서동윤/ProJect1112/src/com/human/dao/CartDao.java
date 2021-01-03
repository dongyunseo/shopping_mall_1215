package com.human.dao;

import com.human.dto.CartVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.human.util.DBConn;

public class CartDao {

	public CartDao() {
	}

	private static CartDao instance = new CartDao();

	public static CartDao getInstance() {
		return instance;
	}

	// ----------------��ٱ��� ���----------------
	public void insertCart(CartVO cartVO) {
		String sql = "insert into tbl_cart(cartnum,id,dressId,dressName,amount,price,sum) VALUES(tbl_cart_seq.nextval,?,?,?,?,?,?)";

		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cartVO.getId());
			System.out.println("�ֹ��� ���̵� : " + cartVO.getId());
			pstmt.setInt(2, cartVO.getDressId());
			System.out.println("��ǰ ��ȣ : " + cartVO.getDressId());
			pstmt.setString(3, cartVO.getDressname());
			System.out.println("��ǰ �̸� : " + cartVO.getDressname());
			pstmt.setInt(4, cartVO.getAmount());
			System.out.println("��ǰ �ֹ� ���� :" + cartVO.getAmount());
			pstmt.setInt(5, cartVO.getPrice());
			System.out.println("��ǰ ���� : " + cartVO.getPrice());
			pstmt.setInt(6, cartVO.getSum());
			System.out.println("��ǰ ����  * ��ǰ ���� : " + cartVO.getSum());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);
		}
	}

	// ----------------��ٱ��� ��� ���----------------
	public ArrayList<CartVO> listCart(String userId) {
		ArrayList<CartVO> cartList = new ArrayList<CartVO>();
		Connection conn = DBConn.getConnection();
		String sql = String.format("select tbl_cart.cartnum, tbl_cart.id, tbl_cart.dressid,"
				+ " tbl_cart.dressName,tbl_cart.amount, tbl_cart.price, tbl_cart.sum, tbl_cart.addDate,"
				+ " dress.dressimg from tbl_cart,dress where tbl_cart.dressid = dress.dressid and id ='%s'order by cartnum desc",
				userId);
		System.out.println(sql);
		Statement st = null;
		ResultSet rs = null;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				cartList.add(new CartVO(rs.getInt("cartnum"), rs.getString("id"), rs.getInt("dressId"),
						rs.getString("dressName"), rs.getInt("amount"), rs.getInt("price"), rs.getInt("sum"),
						rs.getString("dressimg"), rs.getTimestamp("addDate"),0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartList;
	}

	// ----------------------�ֹ� �غ� -----------------------------

	public CartVO OrderReady(int cartnum) {
		CartVO OrderReady = null;
		Connection con = DBConn.getConnection();
		String sql = String.format("select tbl_cart.cartnum, tbl_cart.id, tbl_cart.dressid, "
				+ "tbl_cart.dressName,tbl_cart.amount, tbl_cart.price, tbl_cart.sum, tbl_cart.addDate, "
				+ "dress.dressimg from tbl_cart,dress where tbl_cart.dressid = dress.dressid " + "and cartnum =%d",
				cartnum);
		System.out.println(sql);
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				OrderReady = new CartVO(rs.getInt("cartnum"), rs.getString("id"), rs.getInt("dressId"),
						rs.getString("dressName"), rs.getInt("amount"), rs.getInt("price"), rs.getInt("sum"),
						rs.getString("dressimg"), rs.getTimestamp("addDate"),0);
			}
			DBConn.close(st, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return OrderReady;

	}
	
	// ----------------��ٱ��� ����----------------
		public void deleteCart(int cartnum) {
			String sql = "delete tbl_cart where cartnum=?";
			System.out.println(sql);
			Connection conn = null;
			PreparedStatement pstmt = null;

			try {
				conn = DBConn.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cartnum);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBConn.close(conn, pstmt);
			}
		}
	//--------------------- ��ٱ��Ͽ��� �ֹ��� ���� �� �ֹ��� ��ǰ�� ��ٱ��Ͽ��� ������ �ؾ� �Ѵ�.
	public void OrderCartdelete(int dressid) {
		// TODO Auto-generated method stub
		String sql = "delete tbl_cart where dressid=?";
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dressid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);
		}
	}

	public CartVO Mypage_cart(String id) {
		// TODO Auto-generated method stub
		CartVO Mypage_cart = null;
		Connection con = DBConn.getConnection();
		String sql = String.format("select count(id) totalCart from tbl_cart where id='%s'", id);
		System.out.println(sql);
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Mypage_cart = new CartVO(0,sql,0,sql,0,0,0,sql,null,rs.getInt("totalCart"));
			}
			DBConn.close(st, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Mypage_cart;
	}

}
