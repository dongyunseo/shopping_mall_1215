package com.human.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EmailServlet
 */
@WebServlet("/EmailServlet")
public class EmailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("EmailServlet doPost");
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");				// 인증번호 받을 계정
		String id = request.getParameter("id");						// 비밀번호 변경할 아이디
		String user = "sdy9716@gmail.com"; 							// 인증번호 보낼 계정
		String subject = "비밀번호를 찾기 위한 Dong_yun 쇼핑몰 이메일 인증입니다.";	// 이메일 제목
	
		// smpt 서버를 이용하기 위한 과정 
		Properties props = new Properties();
		props.put("mail.smtp.user", user); 					// smpt를 이용하기 위한 계정 
		props.put("mail.smtp.host", "smtp.googlemail.com");	// 구글에서 제공하는 smpt 서버
		props.put("mail.smtp.port", "465");					// 포트는 465번으로 정해져있습니다.
		props.put("mail.smtp.starttls.enable", "true");		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		// 인증 번호 생성기
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
			int rIndex = rnd.nextInt(3);
			switch (rIndex) {
			case 0:
				// a-z
				temp.append((char) ((int) (rnd.nextInt(26)) + 97));
				break;
			case 1:
				// A-Z
				temp.append((char) ((int) (rnd.nextInt(26)) + 65));
				break;
			case 2:
				// 0-9
				temp.append((rnd.nextInt(10)));
				break;
			}
		}
		String AuthenticationKey = temp.toString();


		System.out.println("비밀번호 변경할 아이디  : " + id);
		System.out.println("전송 받을 이메일 : " + email);
		System.out.println("인증번호 : " + AuthenticationKey);

		try {
			System.out.println("*************이메일 전송 중 *************");
			Authenticator auth = new Gmail();				//Gmail 에는 구글 아이디와 비밀번호가 있습니다.
			Session ses = Session.getInstance(props, auth);	
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses);			
			msg.setSubject(subject);
			// formAddr 보내는 사람
			Address formAddr = new InternetAddress(user);
			msg.setFrom(formAddr);
			// formAddr 받는 사람
			Address toAddr = new InternetAddress(email);
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			msg.setContent("이메일 인증 번호는 : " + AuthenticationKey + " 입니다.", "text/html;charset=UTF8");
			Transport.send(msg);
			System.out.println("*************이메일 전송 완료************");
		} catch (Exception e) {
			e.printStackTrace();
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('오류가 발생했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		HttpSession saveKey = request.getSession();
		saveKey.setAttribute("AuthenticationKey", AuthenticationKey);
		saveKey.setAttribute("id", id);
		// AuthenticationKey를 그 다음 페이지에도 볼 수 있게 하기 위해 getRequestDispatcher 를 사용
		request.getRequestDispatcher("/Member/ForgetPw_if.jsp").forward(request, response);
	}
}