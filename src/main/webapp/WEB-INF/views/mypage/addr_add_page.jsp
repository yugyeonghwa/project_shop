<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="<c:url value="/resources/css/common.css"/>" rel='stylesheet' />
<link href="<c:url value="/resources/css/mypage_page.css"/>" rel='stylesheet' />

<title>배송지 추가</title>

<style type="text/css">
.table {
	box-sizing: border-box;
}

.table table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 10px;
	text-align: center;
}

.table th {
	background-color: lightgray;
	font-size: 18px;
}

.table table {
	margin: 0 auto;
	width: 600px;
}

.table input {
	text-align: left;
}
</style>


<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js" defer></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script type="text/javascript">
	//우편주소
	function execDaumPostcode() {
		new daum.Postcode({
			oncomplete : function(data) {
				document.getElementById('zip_code').value = data.zonecode;
				document.getElementById('roadAddr').value = data.address;
				document.getElementById('jibunAddr').value = data.jibunAddress;
				document.getElementById('addrDetail').focus();
			}
		}).open();
	}
</script>


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

			<br> <br> <br> <br>
		
			<div class="table"> 
				<form action="addr_add_ok.do" method="post">
					<table>
						<tr>
							<th>배송지 추가</th>
						</tr>
						<tr>
							<td>
								<input type="text" id="zip_code" name="zip_code" placeholder="우편번호" readonly />
								<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기" /> <br> 
								<input type="text" id="roadAddr" name="roadAddr" placeholder="도로명주소" style="width: 500px" readonly /><br>  
								<input type="text" id="jibunAddr" name="jibunAddr" placeholder="지번주소" style="width: 500px" readonly /><br> 
								<input type="text" id="addrDetail" name="addrDetail" placeholder="상세주소" style="width: 500px" />
							</td>
						</tr>
						<tr>
							<td>
								<input type="submit" value="배송지추가">
							</td>
						</tr>
					</table>
				</form>
			</div>

			<br> <br> <br> <br>

		</div>

		<!--===============================================================================  -->


		<%@ include file="../common/footer.jsp"%>
	</section>
</body>
</html>
