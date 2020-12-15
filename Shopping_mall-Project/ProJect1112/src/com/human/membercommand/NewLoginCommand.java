package com.human.membercommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.dao.MemberDao;
import com.human.dto.MemberVo;

public class NewLoginCommand implements MemberCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String rawpwd = request.getParameter("pwd");
		String pwd = SHA256.encodeSha64(rawpwd);
		System.out.println("접속한 ID = " + id);
		System.out.println("접속한 PW = " + pwd);
		MemberDao mDao = new MemberDao();
		MemberVo memberVo = new MemberVo();
		
		String temp = mDao.login(id,pwd,memberVo);
		
		String name =memberVo.getName();
		String useyn =memberVo.getUseyn();
		String id1 =memberVo.getId();
		
		request.setAttribute("temp", temp);
		request.setAttribute("name", name);
		request.setAttribute("useyn", useyn);
		request.setAttribute("id", id1);
		
	}


}
