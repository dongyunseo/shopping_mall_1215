public void OrderHalf(OrderVO orderVo) {
		String sql = "insert into tbl_order(ordernum,id,dressimg,dressid,dressname,price,amount,sum) "
				+ "VALUES(tbl_order_seq.nextval,?,?,?,?,?,?,?)";
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderVo.getId());
			System.out.println("�ֹ��� ���̵� : " + orderVo.getId());
			pstmt.setString(2, orderVo.getDressimg());
			System.out.println("�̹��� ��� : " + orderVo.getDressimg());
			pstmt.setInt(3, orderVo.getDressid());
			System.out.println("��ǰ �ѹ� :" + orderVo.getDressid());
			pstmt.setString(4, orderVo.getDressname());
			System.out.println("��ǰ �̸� : " + orderVo.getDressname());
			pstmt.setInt(5, orderVo.getPrice());
			System.out.println("��ǰ ���� : " + orderVo.getPrice());
			pstmt.setInt(6, orderVo.getAmount());
			System.out.println("��ǰ ���� : " + orderVo.getAmount());
			pstmt.setInt(7, orderVo.getSum());
			System.out.println("�� �ݾ�  : " + orderVo.getSum());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);
		}
	}