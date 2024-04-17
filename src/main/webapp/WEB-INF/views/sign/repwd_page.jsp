<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>아이디/비밀번호 찾기</title>
<link href="<c:url value="/resources/css/common.css"/>" rel='stylesheet' />
<link href="<c:url value="/resources/css/repwd_page.css"/>" rel='stylesheet' />
<script type="text/javascript">
	// 비밀번호 확인 
	function pwd_chk() {
		let re_pwd = document.getElementById('re_pwd');
		let re_pwd2 = document.getElementById('re_pwd2');
		let msg = document.getElementById('msg');
		
		let trueColor = "green";	
		let falseColor = "red";
		
		if (re_pwd.value == re_pwd2.value) {
			msg.style.color = trueColor;
			msg.innerHTML = "비밀번호 일치"
		} else {
			msg.style.color = falseColor;
			msg.innerHTML = "비밀번호 불일치"
		}
	}
	
	// 비밀번호 변경 후 결과 alert창
    var msg = "${msg}";
    if (msg !== "") {
        alert(msg);
    }
		
</script>
</head>
<body>
	<%@ include file="../common/header.jsp"%>


	<!-- =========================================================== -->

	<section>
		<div id="repwd_wrap">
			<form action="repwd_go.do" method="post">
				<h1>비밀번호 변경</h1>
				<div id="repwd_box">
					<ul>
						<li>현재 비밀번호<input type="password" name="u_pwd" id="u_pwd" required></li>
						<li>새 비밀번호<input type="password" name="re_pwd" id="re_pwd" required></li>
						<li>새 비밀번호 확인<input type="password" name="re_pwd2" id="re_pwd2" required oninput="pwd_chk()"><p id="msg"></p></li>
					</ul>
				</div>
				<input type="submit" id="repwd_ok" value="변경" onclick="repwd_go(this.form)">
			</form>
		</div>

		<!-- =========================================================== -->


		<%@ include file="../common/footer.jsp"%>
	</section>

</body>
</html>