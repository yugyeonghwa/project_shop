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
		<style type="text/css">
			.write_btn{
				margin-bottom: 50px;
			}
		</style>
		<script type="text/javascript">
			function report_write_ok(f) {
				f.action="report_write_ok.do";
				f.submit();
			}
			function report_list(f) {
				f.action="report_list.do";
				f.submit();
			}
			function back() {
				window.history.back();
			}
		</script>
	</head>
	<body>
		<%-- 헤더 위치 --%>
		<%@ include file="../common/header.jsp"%>
	
		<section class="write">
			<h1>신고 게시물 작성</h1>
			<hr />
			<form method="post" enctype="multipart/form-data" autocomplete="off">
				<table class="write_table">
					<tr>
						<td class="write_table_subject">제목</td>
						<td>
							<input type="text" name="report_subject" required/>
						</td>
					</tr>
					<tr></tr>
					<tr>
						<td class="write_table_subject">내용</td>
						<td>
							<textarea id="post_content" name="report_content" cols="100" rows="20" required></textarea>
						</td>
					</tr>
					<tr>
						<td class="write_table_subject">첨부 파일</td>
						<td>
							<input type="file" name="report_file">
						</td>
					</tr>
				</table>
				<div class="write_btn">
					<input type="hidden" name="u_idx" value="${userVO.u_idx}">
					<input type="hidden" name="report_idx" value="${report_idx}">
					<input type="button" value="등록" onclick="report_write_ok(this.form)" />
					<input type="button" value="목록" onclick="report_list(this.form)" />
					<input type="button" value="취소" onclick="back()">
				</div>
			</form>
			<%-- 푸터 위치 --%>
			<%@ include file="../common/footer.jsp"%>
		</section>
	</body>
</html>

