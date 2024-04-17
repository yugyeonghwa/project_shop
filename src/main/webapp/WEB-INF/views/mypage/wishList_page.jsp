<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="<c:url value="/resources/css/common.css"/>" rel='stylesheet' />
<link href="<c:url value="/resources/css/orderlist_page.css"/>"
	rel='stylesheet' />
<link href="<c:url value="/resources/css/wishlist_page.css"/>"
	rel='stylesheet' />


<script type="text/javascript">
	function show_detail(f) {
		f.action = "product_detail_page.do";
		f.submit();
	}
	function wishDel_go(f) {
		f.action = "wishlist_delete.do";
		f.submit();
	}
</script>

<title>찜목록 페이지</title>
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

			<h2>찜목록</h2>

			<div class="wish">
				<c:choose>
					<c:when test="${empty wish_list}">
						<h3 style="margin: 0 auto; font-size: 25px; position: relative; left: 440px; ">위시리스트가 없습니다.</h3>
					</c:when>
					<c:otherwise>
						<c:forEach var="k" items="${wish_list}">
							<form method="post">
								<div class="product">
									 <img src="resources/images/${k.p_category}/${k.f_name}">
									<h2>${k.p_name}</h2>
									<p>${k.p_price}원</p>
									<input type="hidden" name="p_idx" value="${k.p_idx}">
									<button onclick="show_detail(this.form)">제품 보기</button>
									<input type="hidden" name="wish_idx" value="${k.wish_idx}">
									<button onclick="wishDel_go(this.form)">삭제</button>
								</div>
							</form>
						</c:forEach>
					</c:otherwise>
				</c:choose>	
			</div>

		</div>

		<!--===============================================================================  -->


		<%@ include file="../common/footer.jsp"%>
	</section>
	
	<script type="text/javascript">
		function wishDel_go(f) {
			f.action = "wishDel_go.do";
			f.submit();
		}
	</script>
	
</body>
</html>
