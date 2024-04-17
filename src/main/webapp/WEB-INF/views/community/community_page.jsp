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
		<style type="text/css">
			.main_container{
				display: grid;
				 grid-template-columns: repeat(2, 1fr);
				 grid-auto-rows: 400px;
				 grid-gap: 50px;
				 margin: 30px; 0 0 30px;
			}
			.main_container .box{
				border: 1px solid black; 
				margin: 10px;
				padding: 20px;
			}
			.main_container h1{
				font-size: 30px;
				font-weight: bold; 
				margin: 20px;
				padding: 20px;
			}
			.main_container h3{
				font-size: 1.3em;
			}
		
		</style>
		<script type="text/javascript">
			function review_list(f) {
				f.action="review_list.do";
				f.submit();
			}
			function faq_list() {
				location.href="faq_list.do";
			}
			function report_list(f) {
				f.action="report_list.do";
				f.submit();
			}
			function qna_list(f) {
				f.action="qna_list.do";
				f.submit();
			}
		</script>
		<title>고객 센터</title>
	</head>
	<body >
		 <%@ include file="../common/header.jsp" %>
		<section class="board">
			<h1>고객 센터</h1>
			<hr>
			
			<div class="main_container">
				<div class="box">
					<form method="post">
						<h3>리뷰</h3><br>
						<input type="hidden" name="u_idx" value="${u_idx}">
						<input type="hidden" name="review_idx" value="${reviewVO.review_idx}">
						<input type="button" value="리뷰 페이지로 이동" onclick="review_list(this.form)"><br><br>
					</form>
				</div>
				
				<div class="box">
					<form method="post">
						<h3>자주 묻는 질문</h3><br>
						<input type="hidden" name="faq_idx" value="${faqVO.faq_idx}">
						<input type="button" value="자주 묻는 질문으로 이동" onclick="faq_list()"><br><br>
					</form>
				</div>
				
				<div class="box">
					<form method="post">
						<h3>신고 게시판</h3><br>
						<input type="hidden" name="u_idx" value="${u_idx}">
						<input type="hidden" name="report_idx" value="${reportVO.report_idx}">
						<input type="button" value="신고 게시판으로 이동" onclick="report_list(this.form)"><br><br>
					</form>
				</div>
				
				<div class="box">
					<form method="post">
						<h3>Q & A</h3><br>
						<input type="hidden" name="u_idx" value="${u_idx}">
						<input type="hidden" name="post_idx" value="${postVO.post_idx}">
						<input type="button" value="Q&A로 이동" onclick="qna_list(this.form)"><br><br>
					</form>
				</div>
			</div> 
			<%@ include file="../common/footer.jsp" %>
		</section>
	</body>
</html>