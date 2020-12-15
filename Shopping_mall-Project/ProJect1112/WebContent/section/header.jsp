<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script src="https://kit.fontawesome.com/17d793186c.js"
	crossorigin="anonymous"></script>
<script src="../script/header.js" defer>
	
</script>
<script type="text/javascript">
	var request = new XMLHttpRequest();
	// 어떠한 웹사이트에 요청을 보내는 인스턴스 

	function searchFunction() {
		request.open("Post", "../SearchServlet?search="
				+ encodeURIComponent(document.getElementById("search").value),
				true);
		request.onreadystatechange = searchProcess;
		request.send(null);
	}
	function searchProcess() {
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
</head>
<body>
	<nav class="navber">
		<div class="logo">
			<c:choose>
				<c:when test="${empty sessionScope.login_Name}">
					<ul class="nav__Login">
						<li><a href="../Member/mainLoginPage.jsp"><i class="fas fa-sign-in-alt"></i><br>Login</a></li>
						<li><a href="../Member/signupPage.jsp"><i class="fas fa-user-plus"style="margin-left: 6.5px;"></i><br>Join</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="nav__Login">
						<li style="color: white;"><a href="../Member/MyPage.so"><i class="fas fa-users"></i><br>MyPage</a></li>
						<li><a href="../logout.so" onclick="return logout();"><i class="fas fa-sign-out-alt"style="margin-left: 5px;"></i><br>Logout</a></li>
						<li><a href="../cart/cartSelect.co"><i class="fas fa-shopping-cart"style="margin-left: 2.5px;"></i><br>Cart</a></li>
						<li><a href="../order/orderSelect.co"><i class="fas fa-money-check-alt"style="margin-left: 2px;"></i><br>Order</a></li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="menu">
			<li><a href="../Main&Category/main.do" class=" ">HomePage</a></li>
			<li><a href="../Main&Category/outer.do">OUTER</a></li>
			<li><a href="../Main&Category/shipts.do">SHIPTS</a></li>
			<li><a href="../Main&Category/tee.do">TEE</a></li>
			<li><a href="../Main&Category/bottom.do">BOTTOM</a></li>
			<li><a href="../Main&Category/acc.do">ACC</a></li>
		</div>

		<div class="search">
			<input type="text" placeholder="Search" class="search_engine"
				id="search" onkeyup="searchFunction()">
			<button class="_search" onclick="searchFunction();">
				<i class="fas fa-search"></i>
			</button>
		</div>

		<a href="#" class="toggleBtn"><i class="fas fa-bars"></i> </a>
	</nav>
</body>

</html>