<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script src="https://kit.fontawesome.com/d3367b6d2f.js"></script>
<link href="<c:url value="/resources/css/common.css"/>" rel='stylesheet' />
<link href="<c:url value="/resources/css/basket_page.css"/>"
	rel='stylesheet' />
<title>장바구니</title>

</head>
<body>
	<%@ include file="../common/header.jsp"%>

	<section>
		<div class="basket" style="text-align: center;">
			<h1>장바구니</h1>
			<hr />
				
				<table style="width: auto">
					<thead>
						<tr class="basket_table_head">
							<th><input type="checkbox" name="checkbox_all" /></th>
							<th>제품번호</th>
							<th>제품명</th>
							<th>단가</th>
							<th>수량</th>
							<th>사이즈</th>
							<th>금액</th>
							<th>저장</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty basket_list }">
								<tr class="basket_table_body">
									<td colspan="8"><h3>장바구니가 비었습니다.</h3></td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="k" items="${basket_list }">
										<form method="post" >	
											<tr class="basket_table_body">
												<td><input type="checkbox" class="checked" name="checked" value="${k.b_idx }"/></td>
													<td>${k.p_num }</td>
													<td><img  src="resources/images/${k.f_name}" style="width: 50px; height:40px; text-align: left;"> ${k.p_name }</td>
													<td><fmt:formatNumber value="${k.p_price }" pattern="#,##0" />원</td>
													<td>
														<input type="number" name="quant" min="1" value="${k.quant }" style="width: 40px;"> 
													</td>
													<td>
														<select name="p_size">
																<!-- SELECT 옵션에서 현재 선택된 사이즈가 보이도록 조건 -->
																<c:forEach var="op" items="${option_list}">
																	<c:if test="${op.p_idx == k.p_idx}">
																		<c:choose>
																			<c:when test="${op.p_size eq k.p_size}">
																				<option selected value="${op.p_size}">${op.p_size}</option>
																			</c:when>
																			
																			<c:otherwise>
																				<option value="${op.p_size}">${op.p_size}</option>
																			</c:otherwise>
																		</c:choose>
																		
																	</c:if>
																</c:forEach>
														</select>	
													</td>
													<td><fmt:formatNumber value="${k.totalPrice }" pattern="#,##0" /></td>
													<td>
													<input type="hidden" name="b_idx" value="${k.b_idx }">
													<input type="button" value="저장" onclick="basket_edit_go(this.form)"></td>
											</tr>
										</form>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>

					<tfoot>
						<tr>
							<td colspan="7" style="text-align: right; padding: 10px 50px">
								총 결제액: <span id="total_price">0원</span> 
								<input type="button" value="선택한 상품 비우기" onclick="delete_select_product()" />
								<input type="button" value="선택한 상품 주문하기" onclick="order_go()">
							</td>
						</tr>
					</tfoot>
				</table>
		</div>
		<%@ include file="../common/footer.jsp"%>
	</section>

</body>


<%-- 이동 스크립트 --%>
<script type="text/javascript">
function basket_edit_go(f) {
	f.action = "basket_edit.do";
	f.submit();
}
function order_go() {
	
	let form = document.createElement("form");
	form.method = "post";
	form.action = "order_page.do";
	
	let checkedboxs = document.querySelectorAll('input[name="checked"]:checked')
	
	checkedboxs.forEach(function(checkedbox) {
		let input = document.createElement("input");
        input.type = "hidden";
        input.name = "b_idx";
        input.value = checkedbox.value;
        form.appendChild(input);
	});
	
	if(checkedboxs.length > 0 ){
		document.body.appendChild(form);
		form.submit();
	}else{
		alert("선택된 상품이 없습니다.");
	}
	
}

function delete_select_product() {
	let form = document.createElement("form");
	form.method = "post";
	form.action = "basket_delete.do";
	
	let checkedboxs = document.querySelectorAll('input[name="checked"]:checked')
	
	checkedboxs.forEach(function(checkedbox) {
		let input = document.createElement("input");
        input.type = "hidden";
        input.name = "b_idx";
        input.value = checkedbox.value;
        form.appendChild(input);
	});
	
	if(checkedboxs.length > 0 ){
		document.body.appendChild(form);
		form.submit();
	}else{
		alert("선택된 상품이 없습니다.");
	}

}
</script>
<%-- 체크박스 결제금액 변동 스크립트 --%>
<script type="text/javascript">
	let AllCheckbox = document.querySelector('input[name="checkbox_all"]');
	let proCheck = document.getElementsByClassName('checked');
	
	// "모두 선택" 체크박스가 변경될 때 개별 체크박스 변경하기
	AllCheckbox.addEventListener('change', function() {
		let isChecked = AllCheckbox.checked;

		// 모든 상품 체크박스의 상태를 "모두 선택" 체크박스에 맞게 변경
		for (let i = 0; i < proCheck.length; i++) {
			proCheck[i].checked = isChecked;
			
		}

		// 총 결제액 업데이트
		updateTotalPrice();
	});
	

	// 개별 상품 체크박스가 변경될 때
	for (let i = 0; i < proCheck.length; i++) {
	    proCheck[i].addEventListener('change', function() {
	        let allChecked = true;
	        
	        // 모든 개별 체크박스가 체크되었는지 확인
	        for (let j = 0; j < proCheck.length; j++) {
	            if (!proCheck[j].checked) {
	                allChecked = false;
	                break;
	            }
	        }

	        // 모든 개별 체크박스가 체크되었다면 "모두 선택" 체크박스도 체크
	        AllCheckbox.checked = allChecked;

	        // 총 결제액 업데이트
	        updateTotalPrice();
	    });
	}
	// 가격 변동 함수
	function updateTotalPrice() {
		let totalPriceElement = document.getElementById("total_price");
		let totalPriceText = totalPriceElement.textContent;
		let currentTotalPrice = 0; // 현재 총 결제액 초기화

		/* 체크되어있는지 검사해서 가격 넘기기 */
		for (let i = 0; i < proCheck.length; i++) {
		/*  7번째 항목에 수량 x 단가 있음 선택된 checkbox의 부모->td의 부모-> tr의 자식 중 7번째 ->  */
			let priceCell = proCheck[i].parentNode.parentNode
					.querySelector('td:nth-child(7)');
			let totalPrice = parseInt(priceCell.textContent.replace(/[^\d]/g, ""));
			if (proCheck[i].checked) {
				currentTotalPrice += totalPrice;
			}
		}

		totalPriceElement.textContent = currentTotalPrice.toLocaleString()
				+ "원";
	}
</script>
</html>

