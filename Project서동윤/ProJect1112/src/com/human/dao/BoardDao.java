package com.human.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.human.dto.BoardVO;
import com.human.util.DBConn;

public class BoardDao {
	public BoardDao() {
	}

	private static BoardDao instance = new BoardDao();

	public static BoardDao getInstance() {
		return instance;
	}

	public ArrayList<BoardVO> Select_Board(String page, String pageDataCount) {
		// TODO Auto-generated method stub
		ArrayList<BoardVO> SelectBoard = new ArrayList<BoardVO>();
		Connection conn = DBConn.getConnection();
		String sql = String.format("select * from (select ROWNUM as rnum,"
				+ "A.* from (select * from MVC_Board where not btitle is null order by case when id like '관리자' then id end asc, bDate desc) A)"
				+ "where rnum <='%s'*'%s'  and rnum >=('%s'-1)*'%s'+1", page, pageDataCount, page, pageDataCount);
		System.out.println(sql);
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				SelectBoard.add(new BoardVO(rs.getInt("bNumber"), rs.getString("bTitle"), rs.getString("bContent"),
						rs.getString("id"), rs.getDate("bDate"), rs.getInt("bHit"), rs.getInt("bGroup"),
						rs.getInt("bIndent")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SelectBoard;
	}

	// 게시글 상세페이지 출력
	public BoardVO Board_Detail(String bNumber) {
		// TODO Auto-generated method stub
		UpdateHit(bNumber);
		BoardVO boardDetil = null;
		Connection con = DBConn.getConnection();
		String sql = String.format("select * from MVC_Board where bNumber='%s'", bNumber);
		System.out.println(sql);
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				boardDetil = new BoardVO(rs.getInt("bNumber"), rs.getString("bTitle"), rs.getString("bContent"),
						rs.getString("id"), rs.getDate("bDate"), rs.getInt("bHit"), rs.getInt("bGroup"),
						rs.getInt("bIndent"));
			}
			DBConn.close(st, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardDetil;
	}

	private void UpdateHit(String bNumber) {
		// TODO Auto-generated method stub
		Connection con = DBConn.getConnection();
		Statement st = null;

		try {
			st = con.createStatement();
			String sql = null;
			sql = String.format("update MVC_Board set bHit=bHit+1 where bNumber=%s", bNumber);
			System.out.println(sql);
			st.executeUpdate(sql);
			DBConn.close(st);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int totalCount() {
		int returnValue = 0;
		Connection conn = DBConn.getConnection();
		String sql = String.format("select count(bNumber) bCount from MVC_Board where not btitle is null");
		System.out.println(sql);
		Statement st = null;
		ResultSet rs = null;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				returnValue = rs.getInt("bCount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	// 댓글 입력
	public void boardrelpy(String bContent, String id, String bGroup, String bIndent) {
		// TODO Auto-generated method stub
		relpyUpdate(bGroup, bIndent);
		String sql = String.format(
				"insert into MVC_Board(bNumber,bContent,id,bGroup,bIndent)values(MVC_Board_seq.nextval,'%s','%s','%s','%s')",
				bContent, id, bGroup, bIndent);
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			System.out.println(bGroup + "번 글의 댓글 등록 완료!");
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);

		}
	}

	// 게시글을 작성하면 본 게시글의 bIndent 는 1이 됩니다. 그 게시글의 댓글을 작성하면 본 게시글의 bIndent는 +1이 됩니다. 
	// 또 새로운 댓글이 작성되면 게시글과 댓글이 +1이 되며 (본 게시글의 bIndent는) = (댓글 개수 + 1) 이라는 값이 나옵니다.  
	// 때문에 댓글 개수를 출력하기 위해서는 (BoardVO.bIndent -1) 이라고 작성해주면 댓글 개수만큼의 숫자가 출력됩니다.
	private void relpyUpdate(String bGroup, String bIndent) { // TODO Auto-generated method stub
		Connection con = DBConn.getConnection();
		Statement st = null;

		try {
			st = con.createStatement();
			String sql = null;
			sql = String.format("update MVC_Board set bIndent=bIndent+1 where bGroup='%s' and bIndent >= '%s'", 
					bGroup,bIndent);
			System.out.println(sql);
			st.executeUpdate(sql);
			DBConn.close(st);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 댓글 출력
	public ArrayList<BoardVO> boardreply(String bGroup) {
		// TODO Auto-generated method stub
		ArrayList<BoardVO> replyboard = new ArrayList<BoardVO>();
		Connection conn = DBConn.getConnection();
		String sql = String.format("select * from MVC_Board where btitle is null and bGroup='%s' order by bDate desc",
				bGroup);
		System.out.println(sql);
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				replyboard.add(new BoardVO(rs.getInt("bNumber"), rs.getString("bTitle"), rs.getString("bContent"),
						rs.getString("id"), rs.getDate("bDate"), rs.getInt("bHit"), rs.getInt("bGroup"),
						rs.getInt("bIndent")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return replyboard;
	}
	// 게시글 입력
	public void Board_Insert(String bTitle, String bContent, String id) {
		// TODO Auto-generated method stub
		String sql = String.format(
				"insert into MVC_Board(bNumber,bTitle,bContent,id,bGroup,bIndent)values"
				+ "(MVC_Board_seq.nextval,'%s','%s','%s',MVC_Board_seq.currval,1)",
				bTitle, bContent, id);
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
	// 게시글 삭제
	public void Board_Delete(String bNumber) {
		// TODO Auto-generated method stub
		String sql = "delete MVC_Board where bNumber=?";
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bNumber);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);
		}
	}
	// 게시글 수정
	public void Board_Update(String bNumber, String bTitle, String bContent) {
		// TODO Auto-generated method stub
		Connection con = DBConn.getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			String sql = null;
			sql = String.format("update MVC_Board set bTitle='%s', bContent='%s' where bNumber = '%s'", 
					bTitle, bContent, bNumber);
			System.out.println(sql);
			st.executeUpdate(sql);
			DBConn.close(st);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
