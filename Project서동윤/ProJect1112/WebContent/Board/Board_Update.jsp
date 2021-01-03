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
<link rel="stylesheet" href="../css/Board_Update.css">
<link rel="stylesheet" href="../css/footer.css">
</head>
<body>
	<div id="top">
		<div id="header">
			<jsp:include page="../section/header.jsp" />
		</div>
		<div class="Board_Insert">
			<div class="Insert_name">Board_Update</div>
			<table class="Board_Update_detail">
				<tr class="Board_Update_Information">
					<td style="text-align: end; margin-bottom: -20px;"><hr
							class="top_BoardDetail">Hits : ${boardDetil.bHit}</td>
					<td>작성 날짜 : ${boardDetil.bDate}</td>
					<td style="text-align: end;">게시글 번호 : ${boardDetil.bNumber}</td>
					<td style="margin-top: -22px;">작성자 : ${boardDetil.id}
						<hr class="top_BoardDetail">
					</td>
				</tr>
			</table>
			<form method="post" action="../Board/Board_Update.bo">
				<input type="hidden" name="bNumber" value="${boardDetil.bNumber}">

				<table class="Board_detail">
					<thead>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="Board_title"
								placeholder="수정할 글 제목" name="bTitle" /></td>
						</tr>
						<tr>
							<td><textarea class="Board_context" placeholder="수정할 글 내용"
									name="bContent"></textarea></td>
						</tr>
					</tbody>
				</table>
				<div class="update_but_div">
					<input type="submit" class="Update_but" value="글 수정하기" />
				</div>
			</form>
		</div>

		<div id="bott">
			<jsp:include page="../section/footer.jsp" />
		</div>
	</div>
</body>
</html>