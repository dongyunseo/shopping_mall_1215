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
<link rel="stylesheet" href="../css/Board_Insert.css">
<link rel="stylesheet" href="../css/footer.css">
</head>
<body>
	<div id="top">
		<div id="header">
			<jsp:include page="../section/header.jsp" />
		</div>
		<div class="Board_Insert">
			<div class="Insert_name">Board_Insert</div>
			
			<form method="post" action="../Board/Board_Insert.bo">
				<input type="hidden" name="id" value="${sessionScope.login_Id }">

				<table class="Board_Insert_detail">
					<thead>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="Board_title"
								placeholder="글 제목" name="bTitle" /></td>
						</tr>
						<tr>
							<td><textarea class="Board_context" placeholder="글 내용"
									name="bContent"></textarea></td>
						</tr>
					</tbody>
				</table>
				<div class="Insert_but_div">
					<input type="submit" class="Update_but" value="글  쓰기" />
				</div>
			</form>
		</div>

		<div id="bott">
			<jsp:include page="../section/footer.jsp" />
		</div>
	</div>
</body>
</html>