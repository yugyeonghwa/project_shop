<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="<c:url value="/resources/css/common.css"/>" rel='stylesheet' />
<link href="<c:url value="/resources/css/resign_page.css"/>"
	rel='stylesheet' />


<script type="text/javascript">
	function resign_yes() {
		location.href = "resign_pwd.do";
	}
	function resign_no() {
		location.href = "mypage_page.do";
	}
</script>

<title>회원탈퇴 페이지</title>
</head>
<body>
	<%@ include file="../common/header.jsp"%>


	<section>

		<!--===============================================================================  -->

		<div class="div">
			<br>
			<ul>
				<li><a href="orderlist_page.do">주문목록</a></li>
				<li><a href="purchaselist_page.do">구매목록</a></li>
				<li><a href="wishlist_page.do">찜목록</a></li>
				<li><a href="update_page.do">회원정보수정</a></li>
				<li><a href="resign_page.do">회원탈퇴</a></li>
			</ul>

			<h2>회원탈퇴</h2>

			<div class="resign">
				<h3>가입된 회원정보가 모두 삭제됩니다.</h3>
				<h3>회원 탈퇴를 진행하시겠습니까?</h3>
				<br>
				<button onclick="resign_yes()">예</button> &nbsp;
				<button onclick="resign_no()">아니오</button>
			</div>

		</div>

		<!--===============================================================================  -->

		<%@ include file="../common/footer.jsp"%>
	</section>
</body>
</html>
