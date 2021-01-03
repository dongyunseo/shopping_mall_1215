package com.human.membercommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.dao.MemberDao;

public class Pwd_changeCommand implements MemberCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String rawpwd = request.getParameter("pwd");
		String pwd = SHA256.encodeSha64(rawpwd);

		System.out.println(" 비밀번호를 변경할 고객 아이디 :" + id);
		System.out.println(" 변경할 비밀번호 :" + pwd);
		MemberDao mDao = new MemberDao();
		mDao.Change_pwd(pwd,id);
		
	}

}
