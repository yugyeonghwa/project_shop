<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>비밀번호 찾기</title>
<link href="<c:url value="/resources/css/common.css"/>" rel='stylesheet' />
<link href="<c:url value="/resources/css/find_page.css" />" rel='stylesheet' />
<%--
    <script src="/resources/js/그 페이지.js"></script>
    --%>
<script type="text/javascript">
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
			<form action="pwd_find.do" method="post">
				<h1>비밀번호 찾기</h1>
				<div id="find_input">
					<ul>
						<li><input type="text" name="u_id" placeholder="아이디 입력"></li>
						<li><input type="email" name="u_email" placeholder="이메일 입력"></li>
					</ul>
						<input type="submit" id="but_pwdfind" value="임시 비밀번호 발급"  />
				</div>
			</form>
		</div>
		
		

		<!-- =========================================================== -->

		<%@ include file="../common/footer.jsp"%>
	</section>
</body>
</html>
