<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/footer.css">
</head>
<style>
* {
	font-family: Go, Arial, malgun gothic, 맑은고딕, NanumGothic, dotum, 돋움,
		sans-serif;
	font-weight: bold;
}

.fa-user-circle {
	font-size: 150px;
	font-weight: lighter;
	padding: 30px 30px;
}

li {
	list-style: none;
}

.mypagebox-border {
	width: 500px;
	height: 800px;
	margin: auto;
	margin-bottom: 40px;
	color: #263343;
}

.pick {
	width: 200px;
	height: 200px;
	margin-left: 110px;
	padding-top: 50px;
	margin-top: 30px;
	margin-bottom: 20px;
}

.hello {
	width: 200px;
	margin-left: 77px;
	margin-bottom: 20px;
	padding: 30px;
	text-align: center;
}

.eamil {
	width: 250px;
	margin-left: 71px;
	text-align: center;
	padding: 10px;
}

.Mid {
	margin-top: 50px;
	height: 375px;
}

.Mid_li {
	padding: 25px 50px;
	margin-left: -41px;
}

.Mid .fas {
	padding-left: 20px;
	padding: 0px 10px;
	font-size: 15px;
}

.Mid i:hover {
	color: #fff;
	transition: all .1s;
	border-radius: 10px;
	padding: 10px 130px;
	text-align: center;
	background-color: #263343;
}

.Mid_li a:hover {
	color: #fff;
}

.Mid_li a {
	text-decoration: none;
	color: #263343;
}
</style>


<body>
	<div id="top">
		<div id="header">
			<jsp:include page="../section/detail_header.jsp" />
		</div>

		<div class="mypagebox-border">
			<ul class="topM">
				<li class="pick"><i class="far fa-user-circle"></i></li>
				<li class="hello">반갑습니다. <br>${Mapage_name_email.name } 님<br>개인
					정보 창입니다.
				</li>
				<li class="eamil">이메일 : ${Mapage_name_email.email }</li>
			</ul>
			<ul class="Mid">
				<li class="Mid_li"><a href="../cart/cartSelect.co"><i
						class="fas fa-shopping-cart"
						style="margin-left: 1px; margin-right: 4px;">&nbsp;&nbsp;&nbsp;Cart&nbsp;&nbsp;&nbsp;&nbsp;
							${Mypage_cart.totalCart }</i></a></li>
				<li class="Mid_li"><a href="../order/orderSelect.co"><i
						class="fas fa-money-check-alt">&nbsp;&nbsp;Order&nbsp;&nbsp;&nbsp;
							${Mypage_order.totalOrders }</i></a></li>
				<li class="Mid_li"><i class="fas fa-clipboard"
					style="margin-left: 2px; margin-right: 6px;">&nbsp;&nbsp;&nbsp;게시판</i></li>
				<li class="Mid_li"><i class="fas fa-user-shield">&nbsp;&nbsp;개인정보
						변경</i></li>
			</ul>
		</div>
		<div id="bott">
			<jsp:include page="../section/footer.jsp" />
		</div>
	</div>
</body>

</html>


