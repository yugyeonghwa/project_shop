<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="<c:url value="/resources/css/common.css"/>" rel='stylesheet' />
<link href="<c:url value="/resources/css/mypage_page.css"/>"
	rel='stylesheet' />


<title>마이페이지</title>
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

			<h2>${userVO.u_name}님반갑습니다.</h2>
			<h2 style="font-size: 20px;">메뉴탭을 이용해서 다른 페이지로 이동해주세요.</h2>

			<div class="table">
				<table>
					<thead>
						<tr>
							<th>이름</th>
							<th>연락처</th>
							<th>이메일</th>
							<th>생년월일</th>
							<th>성별</th>
							<th>기본배송지</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${userVO.u_name}</td>
							<td>${userVO.u_phone}</td>
							<td>${userVO.u_email}</td>
							<td>${userVO.u_birth}</td>
							<td><c:choose>
									<c:when test="${userVO.u_gender == '1'}">
											남자
										</c:when>
									<c:otherwise>
											여자
										</c:otherwise>
								</c:choose></td>
							<td><c:forEach var="k" items="${addr_list}">
									<c:if test="${k.basic == '1'}">
										<c:choose>
											<c:when test="${empty k.roadAddr}">
													${k.jibunAddr}, ${k.addrDetail}
												</c:when>
											<c:otherwise>
													${k.roadAddr}, ${k.addrDetail}
												</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach></td>
						</tr>
					</tbody>
				</table>
			</div>

			<br> <br> <br> <br>

		</div>

		<!--===============================================================================  -->


		<%@ include file="../common/footer.jsp"%>
	</section>
</body>
</html>
