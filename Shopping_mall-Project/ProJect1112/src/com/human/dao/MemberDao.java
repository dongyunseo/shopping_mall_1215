package com.human.dao;

import com.human.dto.MemberVo;
import com.human.util.DBConn;

import java.sql.*;

public class MemberDao {

	public int checkId(String id) {
		DBConn.getInstance();
		int result = 0;
		String sql = "select id from customer where id='" + id + "'";
		System.out.println(sql);
		ResultSet rs = DBConn.statementQuery(sql);

		try {
			if (rs.next()) {
				result = 1;
				System.out.println(id + " = 존재합니다. ");

			} else {
				result = -1;
				System.out.println(id + " 로 회원가입이 가능합니다. ");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

		DBConn.dbClose();
		return result;
	}

	public void insertNewJoin(String id, String pwd, String name, String email, String zip_num, String address,
			String phone) {
		Connection conn = null;
		Statement stmt;

		String sql = "insert into customer(id,pwd,name,email,zip_num,address,phone) values('%s','%s','%s','%s','%s','%s','%s')";

		sql = String.format(sql, id, pwd, name, email, zip_num, address, phone);
		try {
			conn = DBConn.getInstance();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			stmt.executeUpdate(sql);
			System.out.println(sql);
			conn.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		DBConn.dbClose();

	}

	public String login(String id, String pwd, MemberVo memberVo) {
		String result = "";
		if (id == null && pwd == null) {
			return result;
		}
		DBConn.getInstance();
		String sql = "select name, useyn, Id from customer where Id = '" + id + "' AND pwd='" + pwd + "'";
		System.out.println(sql);
		ResultSet rs = DBConn.statementQuery(sql);

		try {
			if (rs.next()) {
				memberVo.setName(rs.getString("name"));
				memberVo.setUseyn(rs.getString("useyn"));
				memberVo.setId(rs.getString("id"));
				result = "1";
			} else {
				result = "0";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(result);
		DBConn.dbClose();
		return result;

	}

	public MemberVo GetId(String name, String email) {
		// TODO Auto-generated method stub
		MemberVo dtoid = null;
		Connection con = DBConn.getConnection();
		String sql = String.format("select * from customer where name='%s' and email='%s'", name, email);
		System.out.println(sql);
		Statement st = null;
		ResultSet rs = null;

		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				dtoid = new MemberVo(rs.getString("id"), rs.getString("pwd"), rs.getString("name"),
						rs.getString("email"), rs.getString("zip_num"), rs.getString("address"), rs.getString("phone"),
						rs.getString("useyn"), rs.getTimestamp("indate"));
			}
			DBConn.close(st, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoid;
	}

	// Mypage => name, email select
	public MemberVo Mapage_name_email(String id) {
		// TODO Auto-generated method stub
		MemberVo Mapage_name_email = null;
		Connection con = DBConn.getConnection();
		String sql = String.format("select * from customer where id='%s'", id);
		System.out.println(sql);
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Mapage_name_email = new MemberVo(rs.getString("id"), rs.getString("pwd"), rs.getString("name"),
						rs.getString("email"), rs.getString("zip_num"), rs.getString("address"), rs.getString("phone"),
						rs.getString("useyn"), rs.getTimestamp("indate"));
			}
			DBConn.close(st, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Mapage_name_email;
	}

	public int check_EmailAuth(String Auth_number, String AuthenticationKey) {
		// TODO Auto-generated method stub
		int result = 0;
		System.out.println("입력된 인증번호 : " + Auth_number);
		System.out.println("전송된 인증번호 : " + AuthenticationKey);
		if (Auth_number.equals(AuthenticationKey)) {
			result = 1;
			System.out.println(" 인증번호 확인!  ");

		} else {
			result = -1;
			System.out.println(" 인증번호를 다시 확인해주세요! ");
		}
		return result;
	}

	public int Change_pwd(String pwd, String id) {
		int returnValue = 0;
		// TODO Auto-generated method stub
		DBConn.getInstance();
		String sql = "update customer set pwd='%s' where id='%s'";
		sql = String.format(sql, pwd, id);
		System.out.println(sql);
		returnValue = DBConn.statementUpdate(sql);
		DBConn.dbClose();
		return returnValue;
	}
}