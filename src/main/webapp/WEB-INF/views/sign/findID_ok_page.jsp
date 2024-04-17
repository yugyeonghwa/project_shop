<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>아이디/비밀번호 찾기</title>
<link href="<c:url value="/resources/css/common.css"/>" rel='stylesheet' />
<link href="<c:url value="/resources/css/findID_ok.css" />" rel='stylesheet' />
<%--
    <script src="/resources/js/그 페이지.js"></script>
    --%>
</head>
<body>
	<%@ include file="../common/header.jsp"%>

	<!-- =========================================================== -->

	<section>
		<div id="findID_wrap">
				<h1>아이디 찾기 결과</h1>
				<div class="id_res">
					<span>회원님의 아이디는 </span> <span style="border-bottom: 1px solid black; font-weight: bold;" >${u_id}</span><span> 입니다.</span>
				</div>
				<div class="a_link">
					<a href="login_page.do">로그인 | </a> 
					<a href="findPWD_page.do">비밀번호 찾기</a>
				</div>
		</div>
		
		

		<!-- =========================================================== -->

		<%@ include file="../common/footer.jsp"%>
	</section>
</body>
</html>
