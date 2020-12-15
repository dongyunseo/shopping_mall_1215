<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/cart.css">
<link rel="stylesheet" href="../css/footer.css">
<!-- <h2>${sessionScope.login_Name }님의장바구니입니다.</h2> -->
</head>
<script type="text/javascript">
	var request = new XMLHttpRequest();
	// 어떠한 웹사이트에 요청을 보내는 인스턴스 

	function before_OrderCheck() {
		request.open("Post",
				"../OrderCheckServlet?order_before="
						+ encodeURIComponent(document
								.getElementById("order_before").name), true);
		request.onreadystatechange = OrderProcess;
		request.send(null);
	}
	function confirmation_OrderCheck() {
		request.open("Post", "../OrderCheckServlet?order_confirmation="
				+ encodeURIComponent(document.getElementById("order_confirmation").name), true);
		request.onreadystatechange = OrderProcess;
		request.send(null);
	}
	function OrderProcess() {
		var table = document.getElementById("ajaxTable");
		// ajaxTable 라는 이름을 갖는 박스를 가져온다는 뜻 
		table.innerHTML = "";
		// ajaxTable 에 있는 데이터를 지운다. 
		if (request.readyState = 4 && request.status == 200) {
			// 성공적으로 통신이 이루어졌는지 확인 
			var object = eval('(' + request.responseText + ')');
			//object 변수를 만들어서 ( request.responseText  == getJSON ) 담는다.
			var result = object.result;
			// result 에는 object.result 가 담겨있다.
			// object.result 에는 검색 서블릿에서 출력된 데이터들은 result에 담았기 때문 
			for (var i = 0; i < result.length; i++) {
				var row = table.insertRow(0);
				for (var j = 0; j < result[i].length; j++) {
					var cell = row.insertCell(j);
					cell.innerHTML = result[i][j].value;
				}
			}
		}
	}
</script>
<style>
img {
	width: 80px;
	height: 80px;
	padding: 9px;
}

.order_state {
	padding-bottom: 30px;
	text-align: center;
}

.orderstate {
	background-color: #263343;
	color: rgb(223, 221, 221);
	padding: 5px;
	width: 110px;
	border-radius: 5px;
}
</style>
<body>
<body>
	<div id="top">
		<div id="header">
			<jsp:include page="../section/detail_header.jsp" />
		</div>

		<form name="formm" method="post">
			<div id="cartlist">
				<div id="shopping_cart_list">Shopping Order list</div>
				<div id="cart_name">${sessionScope.login_Name }님의주문상품입니다.</div>
				<div class="order_state">
					<input type="button" name="입금 전" class="orderstate"
						id="order_before" onclick="before_OrderCheck()" value="입금 전 상품">
					<input type="button" name="입금 확인" class="orderstate"
						id="order_confirmation" onclick="confirmation_OrderCheck()"
						value="입금 완료 상품">
				</div>
				<table id="cartList"
					style="font-family: Go, Arial, malgun gothic, 맑은고딕, NanumGothic, dotum, 돋움, sans-serif; font-weight: bold;">
					<tr>

						<th style="width: 10%;">이미지</th>
						<th style="width: 15%;">상품 이름</th>
						<th style="width: 9%;">금액</th>
						<th style="width: 5%;">수량</th>
						<th style="width: 8%;">배송비</th>
						<th style="width: 10%;">총 금액</th>
						<th style="width: 13%;">주문 일</th>
						<th style="width: 20%;">상태</th>
						<th style="width: 10%; padding-left: 10px;">주문 취소</th>

					</tr>
					<tbody id="ajaxTable">
						<c:forEach items="${ListOrder}" var="orderVO">
							<tr>
								<td>
									<div id="dressimg">
										<span>${orderVO.dressimg}</span>
									</div>
								</td>
								<td>${orderVO.dressname}</td>

								<td style="width: 150px" align="right"><fmt:formatNumber
										pattern="###,###,###" value="${orderVO.price}" /></td>
								<td>${orderVO.amount}</td>
								<td>2,500</td>
								<td style="width: 150px" align="right"><fmt:formatNumber
										pattern="###,###,###" value="${orderVO.sum}" /></td>
								<td><fmt:formatDate value="${orderVO.orderDate}"
										type="date" /></td>
								<td><details>
										<summary>${orderVO.delivery}</summary>
										<p>입금 계좌 <br>${orderVO.bank}</p></details></td>
								<td class="delete"><a
									style="color: navy; text-decoration: none"
									href="../order/orderDelete.co?ordernum=${orderVO.ordernum}">삭
										제</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</form>
		<div id="State">
			<img src="../img/Pay/Payment.jpg">
		</div>
		<div id="bott">
			<jsp:include page="../section/footer.jsp" />
		</div>
	</div>
</body>
</body>
</html>