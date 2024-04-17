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
<link href="<c:url value="/resources/css/product_detail_page.css"/>"
	rel='stylesheet' />
<title>제품 상세 페이지</title>

	<script type="text/javascript">
		function send_go2(f) {
			let check1 = document.getElementById("check1").value
		    let check2 = document.getElementById("check2").value
		    
		    if (check1 === "" || check2 === "") {
		    	alert("사이즈와 수량을 선택해주세요")
			}else {
				alert("장바구니에 제품이 추가되었습니다.")
				f.action = "basket_go.do";
				f.submit();
			}
		}
		
	</script>
</head>
<body>
	<!-- 공용 헤더 -->
	<%@ include file="../common/header.jsp"%>
	<!-- 제품 상세 이미지 및 서브 사진 (맨 위 상단 왼쪽에 위치 함 -->
	<form action="order_page.do" method="post">
	<section>
		<div class="detail_top_wrapper">
			<div class="detail_top_wrapper_left">
				<div class="detail_left_head">
					<img src="./resources/images/${proVO.p_category}/${proVO.f_name}" class="detail_img">
					<div class="detail_sub">
						<div class="detail_sub_box">
							<img src="./resources/images/${proVO.p_category}/${proVO.sub_image}" class="detail_sub_img">
						</div>
					</div>
				</div>
			</div>
			
			<!-- 해당 제품 옵션 선택하는 곳 ( 맨 위 오른쪽 위치) -->
			<div class="detail_top_wrapper_right">
				<div class="wrapper_right_box">
					<div class="detail_right_head">
						<div class="head_text">
							<span class="text_line"> ------------------------------ </span> <br>
							<span class="text_title">${proVO.p_name}</span><br><span
								class="text_size"> <mark>250</mark>mm ~ <mark>300</mark>mm
								<strong>까지</strong>
							</span> <br> <span class="text_line">
								------------------------------ </span>
						</div>
					</div>
					<div class="detail_right_body">

						<div class="option_box">
							<div class="option_name_box">상품명</div>
							<input type="text" value="${proVO.p_name}" readonly="readonly"
								class="option_optionbox" name="p_name">
						</div>


						<div class="option_box">
							<div class="option_name_box">판매가</div>
							<input type="text" value="${proVO.p_price}" readonly="readonly" class="option_optionbox" name="p_price">
						</div>

						<div class="option_box">
							<span class="option_name_box">사이즈 </span>
							<select class="option_optionbox" id="check1" name="p_size">
								<option value="" selected disabled>[필수] 옵션을 선택해 주세요></option>
								<c:forEach var="k" items="${option_list}">
									<option value="${k.p_size}" >${k.p_size }</option>
								</c:forEach>
							</select>
						</div>
						
						<div class="option_box">
							<span class="option_name_box">수량</span>
							<input type="number" id="check2" name="quant">
						</div>
						<div>최소 주문 수량 1개 이상</div>
						<div style="color: red;">위 옵션선택 박스를 선택하시면 아래에 상품이 추가됩니다.</div>
					</div>
					<div class="detail_right_footer">
						<input type="button" value="장바구니 담기" class="btn2" onclick="send_go2(this.form)"> 
						<input type="hidden" name="p_idx" value="${proVO.p_idx}">
					</div>
				</div>
			</div>
		</div>
		
		<br> <br>
		
		<!-- 네비게이션 -->
		<!-- <div class="navigation">
			<span class="navigation_option1"><a href="#section1">상품상세</a></span>
			<span class="navigation_option2"><a href="#section2">구매안내</a></span>
			<span class="navigation_option3"><a href="#section3">상품후기</a></span>
			<span class="navigation_option4"><a href="#section4">상품문의</a></span>
		</div> -->
		
		<div class="main_detail_background">
			<div class="main_detail_inner">
				<div class="veiw_box" id="#section1">
					<img src="./resources/images/${proVO.p_category}/${proVO.f_name}" alt="" class="main_detail_img"> <br>
					<span class="main_detail_img1_title">MAIN</span>
				</div>

				<br>

				<div class="veiw_box">
					<img src="./resources/images/${proVO.p_category}/${proVO.sub_image}" alt="" class="main_detail_img"> <br>
					<span class="main_detail_img1_title">SUB</span>
				</div>
			</div>
		</div>
	</section>
	</form>
	<!-- 공용 푸터 -->
	<%@ include file="../common/footer.jsp"%>
</body>
</html>