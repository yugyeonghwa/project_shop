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
<link href="<c:url value="/resources/css/find_page.css" />" rel='stylesheet' />
<%--
    <script src="/resources/js/그 페이지.js"></script>
    --%>
<script type="text/javascript">
	function id_find(f) {
		f.action="id_find.do";
		f.submit();
	}
	
	function id_find_ok(f) {
		f.action="id_find_ok.do";
		f.submit();
	}
	
	let msg = "${msg}";
	if (msg !== "") {
	    alert(msg);
	}
</script>
</head>
<body>
	<%@ include file="../common/header.jsp"%>

	<!-- =========================================================== -->

	<section>
		<div id="find_wrap">
			<form method="post">
				<h1>아이디 찾기</h1>
				<div id="find_input">
					<ul>
						<li><input type="email" name="u_email" value="${u_email}" placeholder="이메일 입력"
							pattern="[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.]+[a-zA-Z]+[.]*[a-zA-Z]*"
							title="이메일 양식"></li>
						<li><input type="button" id="but_code" value="인증번호 받기" onclick="id_find(this.form)" /></li>
						<li><input type="text" name="ver_code" min="6" max="6" placeholder="인증번호 입력"> </li>
						<li><input type="button" id="but_idfind" value="인증번호 확인" onclick="id_find_ok(this.form)" /></li>
					</ul>
				</div>
			</form>
		</div>
		
		

		<!-- =========================================================== -->

		<%@ include file="../common/footer.jsp"%>
	</section>
</body>
</html>
