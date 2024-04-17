<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link href="<c:url value="/resources/css/common.css"/>" rel='stylesheet' />
<link href="<c:url value="/resources/css/orderlist_page.css"/>" rel='stylesheet' />

<script type="text/javascript">
	function purchase_go(order_idx) {
		location.href = "purchase_ok.do?order_idx=" + order_idx;
	}
</script>

<title>주문목록 페이지</title>
</head>
<body>
	   <%@ include file="../common/header.jsp" %>
  


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
	
			<h2>주문목록</h2>	
			
			<table class="table">
				<thead>
	 				<tr>
		 				<th>주문 상품 정보</th>
		 				<th>주문 금액 및 수량</th>
		 				<th>주문 일자</th>
		 				<th>주문 상태</th>
	 				</tr>
	 			</thead>
	 			<tbody>
	 				<c:choose>
	 					<c:when test="${empty order_list}">
	 						<tr><td colspan="4"><h3>주문한 상품이 없습니다.</h3></td></tr>
	 					</c:when>
	 					<c:otherwise>
	 						<c:forEach var="k" items="${order_list}">
	 							<tr>
	 								<td>
	 									<img src="resources/images/${k.p_category}/${k.f_name}" width="70px;">
	 									${k.p_name} : ${k.p_size}
	 								</td>
	 								<td>${k.order_price}원<br>${k.order_p_quant}개</td>
	 								<td>${k.order_date.substring(0,10)}</td>
	 								<td>
	 									<c:choose>
	 										<c:when test="${k.order_state == '배송완료'}">
	 											${k.order_state}
	 											<button onclick="purchase_go(${k.order_idx})">구매확정</button>
	 										</c:when>
	 										<c:otherwise>
				 								${k.order_state}
	 										</c:otherwise>
	 									</c:choose>
	 								</td>
	 							</tr>
	 						</c:forEach>
	 					</c:otherwise>
	 				</c:choose>
	 			</tbody>
			</table>
						
		</div>

<!--===============================================================================  -->

  
      <%@ include file="../common/footer.jsp" %>
	</section>
</body>
</html>
