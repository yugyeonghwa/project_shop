<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="<c:url value="/resources/css/common.css"/>" rel='stylesheet' />
<link href="<c:url value="/resources/css/update_page.css"/>"
	rel='stylesheet' />

<style type="text/css">
.table {
	box-sizing: border-box;
}

.table > table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 10px;
	text-align: center;
}

.table th {
	background-color: lightgray;
	font-size: 18px;
}

.table > table {
	margin: 0 auto;
	width: 1000px;
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		let pwdchk = "${pwdchk}";
		if (pwdchk == 'fail') {
			alert("비밀번호틀림");
			return;
		}
	});
</script>

<script type="text/javascript">

	function update_go(f) {
		//	수정하기 누르면 비밀번호 검사 후
		//	맞으면 정보가지고 감
		f.action = "update_ok.do";
		f.submit();
	}
	
	function basic_addr_update(addr_idx){
		location.href = "basic_update.do?addr_idx=" + addr_idx;
	}
	
	function addr_delete(addr_idx){
		location.href = "addr_delete.do?addr_idx=" + addr_idx;
	}
	
	function addr_add(){
		location.href = "addr_add.do";
	}
	
	//	비밀번호 변경 버튼 (경화)
	function repwd_go() {
		location.href = "repwd_page.do";
	}
	
	let msg = "${msg}";
    if (msg !== "") {
        alert(msg);
    }
	
</script>

<title>회원정보수정 페이지</title>
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

			<h2>회원정보수정</h2>
			<div class="update">
				<form method="post">
					<fieldset>
						<legend>회원 정보</legend>
						<ul>
							<li><label>이름 : </label> <input type="text"
								name="u_name" value="${userVO.u_name}" readonly></li>
							<li><label>연락처 : </label> <input type="text"
								name="u_phone" value="${userVO.u_phone}" required></li>
							<li><label>이메일 : </label> <input type="text"
								name="u_email" value="${userVO.u_email}" required></li>
							<li><label>생년월일 : </label> <input type="text"
								name="u_birth" pattern="[0-9]{6}" value="${userVO.u_birth}"
								readonly> <!-- patten 은 숫자형식과 자릿수도 맞춰야 넘어간다. --></li>
							<li>
								<!-- checked : 남자가 기본으로 선택되어 있도록 기본값 --> <label>성별
									: </label> <c:choose>
									<c:when test="${userVO.u_gender == '1'}">
										남자
									</c:when>
									<c:otherwise>
										여자
									</c:otherwise>
								</c:choose>
							</li>
							<li><label>비밀번호확인 : </label> 
								<input type="password" name="u_pwd" required>
								<button onclick="repwd_go()">비밀번호변경</button>
							</li>
						</ul>
					</fieldset>
					<br> 
						<input type="button" value="수정하기"
						onclick="update_go(this.form)"> <input type="reset"
						value="취소">
				</form>
				<br>
				<br>
			</div>
			
			<div class="table">
				<table>
					<thead>
						<tr>
							<th style="font-weight: bold; font-size: 20px;">배송지목록</th>
							<th>우편번호</th>
							<th>지번주소</th>
							<th>도로명주소</th>
							<th>상세주소</th>
							<th>삭제여부</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="k" items="${addr_list}">
							<tr>
								<td>
									<c:choose>
										<c:when test="${k.basic == '1'}">
											기본배송지
											<input type="hidden" name="basic_addr" value="k.addr_idx">
										</c:when>
										<c:otherwise>
											<button onclick="basic_addr_update(${k.addr_idx})">기본배송지로 변경</button>
										</c:otherwise>
									</c:choose>
								</td>
								<td>${k.zip_code}</td>
								<td>${k.roadAddr}</td>
								<td>${k.jibunAddr}</td>
								<td>${k.addrDetail}</td>
								<td>
									<c:choose>
										<c:when test="${k.basic == '1'}"></c:when>
										<c:otherwise>
											<button onclick="addr_delete(${k.addr_idx})">삭제</button>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<button onclick="addr_add()">배송지 추가</button>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<br><br><br><br>
			
		</div>

		<!--===============================================================================  -->


		<%@ include file="../common/footer.jsp"%>
	</section>
</body>
</html>
