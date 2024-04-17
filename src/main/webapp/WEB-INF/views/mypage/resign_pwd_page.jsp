<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제하기</title>
<style type="text/css">
	a { 
		text-decoration: none;
	}
	table {
		width: 400px; 
		border-collapse:collapse; 
		text-align: center;
	}	
	table,th,td {
		border: 1px solid black; 
		padding: 3px;
	}
	div {
		width: 400px; 
		margin:auto; 
		text-align: center;
	}
</style>
<!-- 제이쿼리 사용하기 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		let pwdchk = "${pwdchk}";
		if (pwdchk == 'fail') {
			alert("비밀번호틀림");
			return;
		}
	})
</script>
<script type="text/javascript">
	function resign_pwd_chk(f) {
		f.action = "resign_ok.do";
		f.submit();
	}
</script>

</head>
<body>
<div>
<h2>비밀번호를 입력해주세요.</h2>
<hr>
<form method="post">
	<table>
		<tr align="center">
			<td bgcolor="#99ccff">비밀번호</td>
			<td><input type="password" name="cpwd" size="20"></td>
		</tr>
		<tfoot>
		<tr align="center">
			<td colspan="2">
				<input type="button" value="회원탈퇴하기" onclick="resign_pwd_chk(this.form)">
			</td>
		</tr>
		</tfoot>
	</table>
</form>
</div>
</body>
</html>