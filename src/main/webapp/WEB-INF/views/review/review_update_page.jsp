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
		<script type="text/javascript">
			function review_update_ok_go(f) {
				f.action="review_update_ok.do";
				f.submit();
			}
			function review_list(f) {
				f.action="review_list.do";
				f.submit();
			}
		</script>
	</head>
	<body>
		<%-- 헤더 위치 --%>
		<%@ include file="../common/header.jsp"%>
			
		<section class="write">
			<h1>리뷰 수정</h1>
			<hr />
			
			<form method="post" enctype="multipart/form-data">
				<table class="write_table">
					<tr>
						<td class="write_table_subject">제목</td>
						<td>
							<input type="text" name="review_subject" value="${reviewVO.review_subject}">
						</td>
					</tr>
										
					<tr>
						<td class="write_table_subject">내용</td>
						<td>
							<textarea name="review_content" cols="100" rows="20" required>${reviewVO.review_content}</textarea>
						</td>
					</tr>
					
					<tr>
						<td class="write_table_subject">첨부 파일</td>
						<c:choose>
							<c:when test="${empty reviewVO.review_f_name}">
								<td>
									<input type="file" name="review_file"> <b>이전 파일 없음</b>
									<input type="hidden" name="old_f_name" value="">
								</td>
							</c:when>
							<c:otherwise>
								<td>
									<input type="file" name="review_file">
									<input type="hidden" name="old_f_name" value="${reviewVO.review_f_name}">
								</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</table>
				
				<div class="write_btn">
					<input type="hidden" name="review_idx" value="${reviewVO.review_idx}">
					<input type="hidden" name="cPage" value="${cPage}">
					<input type="button" value="수정" onclick="review_update_ok_go(this.form)">
					<input type="button" value="목록" onclick="review_list(this.form)">
					<input type="reset" value="취소" />
				</div>
			</form>
		<%-- 푸터 위치 --%>
		<%@ include file="../common/footer.jsp"%>
		</section>
	</body>
</html>

