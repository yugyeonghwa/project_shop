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
		<link href="<c:url value="/resources/css/write_page.css"/>"rel='stylesheet' />
		<title>shop</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				let pwdchk = "${pwdchk}";
				if (pwdchk == 'fail') {
					alert("다시 입력하세요.");
					return;
				}
			});
		</script>
		<script type="text/javascript">
			function qna_delete_ok_go(f) {
				f.action="qna_delete_ok.do";
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
			<h1>삭제 화면</h1>
			<hr />
			
			<form method="post">
				<table class="write_table">
					<tr>
						<td class="write_table_subject">비밀번호 </td>
						<td>
							<input type="password" name="u_pwd" size="20">
						</td>
					</tr>

				</table>
				
				<div class="write_btn">
					<input type="hidden" name="post_idx" value="${post_idx}">
					<input type="hidden" name="u_idx" value="${userVO.u_idx}">
					<input type="hidden" name="cPage" value="${cPage}">
					<input type="button" value="삭제" onclick="qna_delete_ok_go(this.form)">
					<input type="button" value="목록" onclick="qna_list(this.form)">
					<input type="reset" value="취소" />
				</div>
			</form>
		<%-- 푸터 위치 --%>
		<%@ include file="../common/footer.jsp"%>
		</section>
	</body>
</html>

