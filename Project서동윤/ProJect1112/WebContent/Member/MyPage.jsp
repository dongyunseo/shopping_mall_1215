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
<link rel="stylesheet" href="../css/mypage.css">
<link rel="stylesheet" href="../css/footer.css">
</head>
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
				<li class="Mid_li"><a href="../Board/board.bo"><i class="fas fa-clipboard"
					style="margin-left: 2px; margin-right: 6px;">&nbsp;&nbsp;&nbsp;게시판</i></a></li>
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


