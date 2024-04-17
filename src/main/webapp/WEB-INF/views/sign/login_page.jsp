<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인</title>
<link href="<c:url value="/resources/css/common.css"/>" rel='stylesheet' />
<link href="<c:url value="/resources/css/login_page.css"/>" rel='stylesheet' />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">

 	// 로그인 버튼 클릭 시
	function login_go(f) {
		if(f.u_id.value === '' || f.u_pwd.value === '' ){
	            alert("정보를 입력해 주세요.");
	            return;
	    } 
		f.action="user_login.do";
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
		<div id="login_wrap">
			<form method="post">
				<h1>로그인</h1>
				<ul>
					<li><input type="text" name="u_id" placeholder="아이디" required></li>
					<li><input type="password" name="u_pwd" placeholder="비밀번호" required></li>
					<li><input type="button" id="but_login" value="로그인" onclick="login_go(this.form)"></li>
				</ul>

				<div class="a_link">
					<a href="signup_page.do" class="a_sign">회원가입</a> 
					<a href="findID_page.do">아이디 찾기</a> 
					<a href="findPWD_page.do">비밀번호 찾기</a>
				</div>
			</form>
		</div>

		<!-- =========================================================== -->


		<%@ include file="../common/footer.jsp"%>
	</section>
</body>
</html>