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
	function Email_AuthCheck() {
		var Auth_number = $('.Auth_number').val();
		var AuthenticationKey = $('.AuthenticationKey').val();

		$.ajax({
			type : 'POST',
			url : '../ForgetPw_if_Servlet',
			data : {
				Auth_number : Auth_number,
				AuthenticationKey : AuthenticationKey
			},
			success : function(result) {
				if (result == 1) {
					alert("비밀번호를 변경해주세요.")
					$(".pwd").prop("disabled", false);
					$(".pwd_Check").prop("disabled", false);
				} else {
					alert("인증번호가 다릅니다.");
					$(".pwd").prop("disabled", true);
					$(".pwd_Check").prop("disabled", true);
				}
			}
		})
	}
	function Pwd_Check() {
		var pwd = $('.pwd').val();
		var pwd_Check = $('.pwd_Check').val();
		// 비밀번호, 비밀번호 확인 2개의 값이 일치한지 확인 해야함

		if (pwd == pwd_Check && pwd != null && pwd_Check != null) {
			alert("비밀번호가 동일합니다.")
			$(".signupbtn").prop("disabled", false);
			$(".signupbtn").css("background-color", "#19c4197e");
		} else {
			alert("비밀번호가 다릅니다.")
			$(".signupbtn").prop("disabled", true);
			$(".signupbtn").css("background-color", "#aaaaaa");
		}
	}
	function Pwd_Change_success() {
		alert("비밀번호가 변경되었습니다.")
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
					<span>비밀번호 </span> 변경
				</h5>
				<hr />
			</div>
			<form method="post">
				<br /> 
				<input type="text" placeholder="이메일 인증번호 입력 "id="Auth_number" name="Auth_number"
				 required class="Auth_number" style="height: 30px; width: 495px; margin-left: 45px;" /> 
				 <br />
				<ul class="AuthPage_but">
					<li>
						<input type="button" class="email_c"style="padding: 5px 10px;"
						 onclick="Email_AuthCheck()"value=" 이메일 인증 확인">
					</li>
				</ul>
				<%
					String AuthenticationKey = (String) request.getAttribute("AuthenticationKey");
				%>
				<input type="hidden" name="AuthenticationKey" id="AuthenticationKey"
				value=${AuthenticationKey } class="AuthenticationKey" />
			</form>
			<form name="Pwd_Change" method="post" action="Pwd_Change.so">
				<br /> 
				<input type="password" placeholder="비밀번호" name="pwd" class="pwd" required id="join_text_box"
				style="height: 30px; width: 495px; margin-left: 45px;" disabled />
				<br />
				<p>
					<%
						String id = (String) request.getAttribute("id");
					%>
				<input type="hidden" name="id" value=${id } /> <br /> 
				<input type="password" placeholder="비밀번호 확인" class="pwd_Check"
				name="pwd_Check" required id="join_text_box" style="height: 30px; width: 495px; margin-left: 45px;" disabled />
				<br />
				<ul class="AuthPage_but">
					<li><input type="button" class="email_c"
						style="padding: 5px 10px;" onclick="Pwd_Check()" value="비밀번호 확인"></li>
				</ul>

				<p>
					<br /> <input type="submit" value="변경하기" id="Pwd_ChageBtu"
						onclick="Pwd_Change_success()" class="signupbtn" disabled /><br />
					<br />
				</p>
			</form>
			<hr />
		</div>
		<div id="bott">
			.
			<jsp:include page="../section/footer.jsp" />
		</div>
	</div>

</body>
</html>