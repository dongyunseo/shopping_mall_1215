<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/ForgetPwd.css">
<link rel="stylesheet" href="../css/footer.css">
</head>
<script type="text/javascript">
	function Email_request() {
		var id = $('.id').val();
		var email = $('.email').val();
		alert((document.getElementById("email").value) + " 로 인증번호가 발송되었습니다.");
		
		$.ajax({
			type : 'POST',
			url : '../EmailServlet',
			data : {
				id : id,
				email : email
			},
		})
	}
	function Email_AuthPage() {
		alert("이메일 인증번호 입력 페이지로 이동합니다.");
		document.AuthPage.action = "../Member/ForgetPw_if.jsp";
		document.AuthPage.submit();
	}
</script>
<body>
	<div id="top">
		<div id="header">
			<jsp:include page="../section/detail_header.jsp" />
		</div>

		<div id="id_forgot">
			<div id="id_forgot2">
				<h5>
					<span>비밀번호 </span> 변경하기
				</h5>
				<hr />
			</div>
			<form method="post">
				<br /> <input type="text" placeholder="아이디" name="id" id="id"
					required class="id"
					style="height: 30px; width: 495px; margin-left: 45px;" /><br /> <br />
				<input type="email" placeholder="이 메 일" name="email" id="email"
					class="email" required
					style="height: 30px; width: 495px; margin-left: 45px;" /><br /> <br />
				<input type="text" placeholder="전화 번호 ( - 제외  )" name="phone"
					required style="height: 30px; width: 495px; margin-left: 45px;" /><br />
				<br />

				<div>
					<ul class="email_but">
						<li><button class="email_c" style="padding: 5px 10px;"
								onclick="Email_request();">이메일 인증하기</button></li>
						<li><a href="https://www.naver.com" target="_blank"><img
								src="../img/icon/naver_icon.jpg"
								style="width: 32px; margin-top: 3px;"></a></li>
						<li><a href="https://www.google.com" target="_blank"><i
								class="fab fa-google" style="font-size: 20px; color: black;"></i></a></li>
					</ul>
				</div>

				<hr />
			</form>
			<form method="post" name="AuthPage">
				<ul class="email_but" style="">
					<li><button class="email_c" style="padding: 5px 22px;"
							onclick="Email_AuthPage();">인증번호 입력 페이지 이동</button></li>
				</ul>
			</form>
		</div>
		<div id="bott">
			<jsp:include page="../section/footer.jsp" />
		</div>
	</div>

</body>
</html>