<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<script src="https://kit.fontawesome.com/d3367b6d2f.js"></script>
		<%-- <script src="/resources/js/basket.js"></script> --%>
		<link href="<c:url value="/resources/css/common.css"/>" rel='stylesheet' />
		<link href="<c:url value="/resources/css/write_page.css"/>"
			rel='stylesheet' />
		<title>shop</title>
		<script type="text/javascript">
			function qna_answer_ok(f) {
				f.action="qna_answer_ok.do";
				f.submit();
			}
			function qna_list(f) {
				f.action="qna_list.do";
				f.submit();
			}
		</script>
	</head>
	<body>
		<%-- 헤더 위치 --%>
		<%@ include file="../common/header.jsp"%>
	
		<section class="write">
			<h1>답변 작성</h1>
			<hr />
			<form method="post" enctype="multipart/form-data" autocomplete="off">
				<table class="write_table">
					<tr>
						<td class="write_table_subject">제목</td>
						<td><input type="text" name="post_subject" required/></td>
					</tr>
					<tr>
						<td class="write_table_subject">내용</td>
						<td><textarea name="post_content" cols="100" rows="20" required></textarea>
						</td>
					</tr>
					<tr>
						<td class="write_table_subject">첨부 파일</td>
						<td>
							<input type="file" name="post_file">
						</td>
					</tr>
				</table>
				<div class="write_btn">
					<input type="hidden" name="u_idx" value="${userVO.u_idx}">
					<input type="hidden" name="post_idx" value="${post_idx}">
					<input type="hidden" name="cPage" value="${cPage}">
					<input type="button" value="등록" onclick="qna_answer_ok(this.form)" />
					<input type="button" value="목록" onclick="qna_list(this.form)" />
					<input type="button" value="취소" onclick="back()">
				</div>
			</form>
			
			<%-- 푸터 위치 --%>
			<%@ include file="../common/footer.jsp"%>
		</section>
	</body>
</html>

