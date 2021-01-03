
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
<link rel="stylesheet" href="../css/Board.css">
<link rel="stylesheet" href="../css/footer.css">
</head>
<script>
	function Login_Insert() {
		alert('댓글입력을 위해 로그인 하셔야합니다.');
	}
	function Board_insert_Login() {
		alert('게시판을 작성하기 위해서는 로그인이 필요합니다.');
	}
</script>
<body>
	<div id="top">
		<div id="header">
			<jsp:include page="../section/header.jsp" />
		</div>
		<div class="Board_Div">
			<div class="Board_Title">게 시 판</div>
			<form name="board" method="post">
				<table class="Board">
					<tr>
						<th style="width: 10%;">작성자</th>
						<th style="width: 58%;">제목</th>
						<th style="width: 5%;">댓글</th>
						<th style="width: 15%;">작성일</th>
						<th style="width: 7%;">조회수</th>
						<th style="width: 5%;">삭제</th>
					</tr>

					<c:forEach items="${SelectBoard}" var="BoardVO">
						<tr class="Board_select">
							<td>${BoardVO.id}</td>
							<td class="Board_btn"><a
								href="../Board/Board_Detail.bo?bNumber=${BoardVO.bNumber}">${BoardVO.bTitle}</a></td>
							<td>(${BoardVO.bIndent -1})</td>
							<td><fmt:formatDate value="${BoardVO.bDate}" type="date" /></td>
							<td>${BoardVO.bHit}</td>
							<td class="delete"><a href="../Board/Board_Delete.bo?bNumber=${BoardVO.bNumber}&bid=${BoardVO.id}&id=${sessionScope.login_Id }">삭 제</a></td>
						</tr>

					</c:forEach>
				</table>
			</form>
		</div>
		<div style="display: flex; justify-content: center; ">
			<ul class="PageNumber">
				<li><a
					href="../Board/board.bo?page=${board_PageingVO.firstPageNum }&pageDataCount=${board_PageingVO.pageDataCount}">
						<< </a></li>
				<li><a
					href="../Board/board.bo?page=${board_PageingVO.prevPageNum }&pageDataCount=${board_PageingVO.pageDataCount}">
						<</a></li>
				<c:forEach var="num" begin="${board_PageingVO.startPageNum }"
					end="${board_PageingVO.endPageNum }" step="1">
					<li><a
						href="../Board/board.bo?page=${num }&pageDataCount=${board_PageingVO.pageDataCount}">${num }</a></li>
				</c:forEach>
				<li><a
					href="../Board/board.bo?page=${board_PageingVO.nextPageNum }&pageDataCount=${board_PageingVO.pageDataCount}">
						> </a></li>
				<li><a
					href="../Board/board.bo?page=${board_PageingVO.lastPageNum }&pageDataCount=${board_PageingVO.pageDataCount}">
						>> </a>
			</ul>
			<ul style="margin-top: 6px;">
				<li><c:choose>
						<c:when test="${empty sessionScope.login_Name}">
							<input type="submit" value="글쓰기 " class="Board_Insert_btn"
								onclick="javascript:Board_insert_Login()" />
						</c:when>
						<c:otherwise>

							<a href="../Board/Board_Insert.jsp"><i
								class="far fa-clipboard" style="font-size: 25px;"></i></a>
						</c:otherwise>
					</c:choose></li>
			</ul>
		</div>
		<div id="bott">
			<jsp:include page="../section/footer.jsp" />
		</div>
	</div>
</body>
</html>