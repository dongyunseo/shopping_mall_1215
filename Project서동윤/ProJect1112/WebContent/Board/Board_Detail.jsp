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
<link rel="stylesheet" href="../css/Board_detail.css">
<link rel="stylesheet" href="../css/footer.css">
<script>
	function Login_reply() {
		alert('댓글입력을 위해 로그인 하셔야합니다.');
	}
</script>
<style>
.replyList {
	border-collapse: collapse;
	border-top: 1px solid #333;
	border: 1px solid #d7d5d5;
	width: 100%;
	margin-bottom: 20px;
	margin: auto;
	color: #999;
	font-weight: normal;
	font-size: 11px;
	margin-bottom: 150px;
}

.Board_Update_btn a {
	text-decoration: none;
	color: white;
}

.Board_Update_btn {
	width: 65px;
	height: 30px;
	background-color: #263343;
	list-style: none;
	text-align: center;
	border-radius: 5px;
	padding-top: 15px;
}

.Update_button {
	text-align: -webkit-center;
	width: 1105px;
}

.Board_Update_btn :hover {
	background-color: rgb(209, 102, 15);
	border-radius: 5px;
	padding: 17px 14px;
}

@media screen and (min-width: 900px) {
	table.replyList {
		width: 900px;
	}
}

.Board_ul {
	list-style: none;
	display: flex;
	padding-left: 0;
	justify-content: center;
}
</style>
</head>
<body>
	<div id="top">
		<div id="header">
			<jsp:include page="../section/header.jsp" />
		</div>
		<div>
			<table class="Board_detail">
				<tr style="height: 25px;">
					<td class="Board_title">
						<hr class="top_BoardDetail">${boardDetil.bTitle}
					</td>
				</tr>
				<tr class="Board_Hit">
					<td style="text-align: end; margin-bottom: -20px;"><hr
							class="top_BoardDetail">Hits : ${boardDetil.bHit}</td>
					<td>작성 날짜 : ${boardDetil.bDate}</td>
					<td style="text-align: end;">게시글 번호 : ${boardDetil.bNumber}</td>
					<td style="margin-top: -22px;">작성자 : ${boardDetil.id}
						<hr class="top_BoardDetail">
					</td>
				</tr>
				<tr>
					<td class="Board_content">${boardDetil.bContent}</td>
				</tr>
			</table>
			<div class="Board_reply">
				<form method="post" name="Boardreply" action="Board_Detail.bo">
					<input type="hidden" name="id" value="${sessionScope.login_Id}">
					<input type="hidden" name="bNumber" value="${boardDetil.bNumber}">
					<input type="hidden" name="bGroup" value="${boardDetil.bGroup}">
					<input type="hidden" name="bIndent" value="${boardDetil.bIndent}">
					<ul class="Update_button">
						<li class="Board_Update_btn"><a
							href="../Board/Board_UpdateReady.bo?bNumber=${boardDetil.bNumber}&bid=${boardDetil.id}&id=${sessionScope.login_Id }">글
								수정</a></li>
					</ul>
					<ul class="Board_ul">
						<li><br /> <input type="text" placeholder="댓글 입력"
							name="bContent" required class="bContent" /><br /> <br /></li>
						<li><c:choose>
								<c:when test="${empty sessionScope.login_Name}">
									<input type="submit" value="댓글 입력" class="reply"
										onclick="Login_reply()" />
									<br />
									<br />
								</c:when>
								<c:otherwise>
									<input type="submit" value="댓글 입력" class="reply" />
									<br />
									<br />
								</c:otherwise>
							</c:choose></li>
					</ul>
				</form>
			</div>
		</div>
		<div>
			<table class="replyList"
				style="font-family: Go, Arial, malgun gothic, 맑은고딕, NanumGothic, dotum, 돋움, sans-serif; font-weight: bold;">
				<tr style="height: 40px; background: #263343; color: white;">
					<th style="width: 10%;">작성자</th>
					<th style="width: 75%;">댓글</th>
					<th style="width: 10%;">작성날짜</th>
					<th style="width: 5%;">삭제</th>
				</tr>

				<c:forEach items="${replyboard}" var="BoardVO">
					<tr style="text-align: center; height: 30px;">
						<td>${BoardVO.id }</td>
						<td>${BoardVO.bContent }</td>
						<td><fmt:formatDate value="${BoardVO.bDate}" type="date" /></td>
						<td>삭제</td>

					</tr>
				</c:forEach>

			</table>

		</div>
		<div id="bott">
			<jsp:include page="../section/footer.jsp" />
		</div>
	</div>
</body>
</html>