<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<script src="https://kit.fontawesome.com/d3367b6d2f.js"></script>
		<link href="<c:url value="/resources/css/common.css"/>" rel='stylesheet' />
		<link href="<c:url value="/resources/css/community_detail_page.css"/>"
			rel='stylesheet' />
		<title>shop</title>
		<style type="text/css">
			.detail_btn{
				margin-bottom: 100px;
			}
			.td_box{
                padding: 10px;
            }
		</style>
		<script type="text/javascript">
			function review_update_go(f) {
				f.action="review_update.do";
				f.submit();
			}
			function review_answer_go(f) {
				f.action="review_answer.do";
				f.submit();
			}
			function review_list(f) {
				f.action="review_list.do";
				f.submit();
			}
			function review_delete_go(f) {
				f.action="review_delete.do";
				f.submit();
			}
		</script>
	</head>
	<body>
		<%-- 헤더 위치 --%>
		<%@ include file="../common/header.jsp"%>
	
		<section class="detail">
			<h1>상세보기</h1>
			<hr />
			<form method="post"  style="padding-top: 20px;">
				<table class="detail_table">
					<tr>
						<td class="detail_table_subject">제목</td>
						<td class="td_box">${reviewVO.review_subject}</td>
					</tr>
					<tr></tr>
					<tr>
						<td class="detail_table_subject">내용</td>
						<td style="height: 300px;" class="td_box">${reviewVO.review_content}</td>
					</tr>
					<tr>
						<td class="detail_table_subject">이미지</td>
						<c:choose>
							<c:when test="${empty reviewVO.review_f_name}">
								<td class="td_box">첨부 파일 없음</td>
							</c:when>
							<c:otherwise>
								<td class="td_box">
									<img src="resources/upload/${reviewVO.review_f_name}" width="100px;">
								</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</table>
				
				<div class="detail_btn">
					<input type="hidden" name="review_idx" value="${reviewVO.review_idx}">
					<input type="hidden" name="cPage" value="${cPage}">
	
					<c:if test="${userVO.u_id == 'admin' }">
						<input type="button" value="답글" onclick="review_answer_go(this.form)" >
					</c:if>
	
					<input type="button" value="목록으로" onclick="review_list(this.form)" >
					<input type="button" value="수정" onclick="review_update_go(this.form)" >
					<input type="button" value="삭제" onclick="review_delete_go(this.form)" >
				</div>
				
			</form>
			<%-- 푸터 위치 --%>
			<%@ include file="../common/footer.jsp"%>
		</section>
	</body>
</html>

