<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script src="https://kit.fontawesome.com/d3367b6d2f.js"></script>
<link href="<c:url value="/resources/css/common.css"/>" rel='stylesheet' />
<link href="<c:url value="/resources/css/category_page.css"/>"
	rel='stylesheet' />

<title>카테고리</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">


	$(document).ready(function() {
		$(".wishlist-btn").on("click" , function() {
				let p_idx = $(this).val();
				if ($(this).hasClass("wish-added")) {
					wish_delete(this, p_idx);
				}else {
					wish_insert(this, p_idx);
				}
			
		});
	});

	function wish_insert(tag, p_idx) {
		if(${heartloginchk == "no"}){
			alert("로그인 하셔야 찜 할 수 있습니다.");
		} else{
			$.ajax({
				url : "wish_insert.do",
				type : "post",
				data : "p_idx=" + p_idx,
				dataType : "text",
				success : function(data) {
					$(tag).addClass("wish-added");
					$(tag).text("♥");
				},
				error : function() {
					alert("실패");
				}
			});
		}
	}
	
	function wish_delete(tag, p_idx) {
		if(${heartloginchk == "no"}){
			alert("로그인 하셔야 찜 할 수 있습니다.");
		} else{
			$.ajax({
				url : "wish_delete.do",
				type : "post",
				data : "p_idx=" + p_idx,
				dataType : "text",
				success : function(data) {
					$(tag).text("♡");
					$(tag).removeClass("wish-added");
				},
				error : function() {
					alert("실패");
				}
			});
		}
		
	}

    </script>
</head>
<body>
	<!-- 공용 헤더  -->
	<%@ include file="../common/header.jsp"%>
	
<section>
	<div class="cartalog_page">
		<!-- best 상품들 슬라이드 효과로 순차적으로 보여주는 페이지 구간 -->
		<div class="model_best_box">
			<div class="model_best_background">
				<p class="model_best_background_title">
					<strong>BEST</strong>상품
				</p>
				<div class="model_best_wrapper_item">
					<!-- best 상품 순차적으로 보여주면서 보이는 상품 클릭시 클릭한 상품 상세페이지로 이동 가능 기능 구현  -->
					<c:forEach var="k" items="${pro_list}" end="5">
						<img src="./resources/images/${p_category}/${k.f_name}"
							class="slideshow-img" quality="high">
					</c:forEach>
				</div>

				<!-- 화면 슬라이드 자바스크립트 코드,
            이 위치에 놓고 싶지 않았으나 어떤이유인지 모르겠는데 헤드 부분에 놓으면 기능이 안먹음-->
				<script type="text/javascript">
        const images = document.querySelectorAll('.slideshow-img');
        let currentIndex = 0;
        for (let i = 0; i < images.length-7; i++) {
            images[i].style.opacity = 0;
        }
        function slideImage() {
            images[currentIndex].style.transition = 'opacity 1s ease';
            images[currentIndex].style.opacity = 0;
            currentIndex = (currentIndex + 1) % images.length;
            images[currentIndex].style.transition = 'opacity 1s ease';
            images[currentIndex].style.opacity = 1;
        }
        images[currentIndex].style.opacity = 1;
        setInterval(slideImage, 3000);
    </script>
				<!-- 화면 슬라이드 자바스크립트 코드 끝 -->

				<!-- 베스트 상품 small 이미지들 -->
				<!-- 베스트 상품 small 이미지들 -->
				<div class="model_best_wrapper_item2">
					<c:forEach var="k" items="${pro_list}" end="5">
						<span class="model_small_box"><img
							src="./resources/images/${p_category}/${k.f_name}"
							class="small_box_img"></span>
					</c:forEach>
				</div>
			</div>
		</div>

		<div class="model_box">
			<div class="model_box_head">
				<!-- 모델 제품 갯수 및 가격 높낮이로 정렬하여 표시 -->
				<span class="item_number" style="cursor: pointer;">${pro_list.size()}item</span> 
			</div>
			<div class="model_box_body">
				<div class="thumbs_wrapper">
				
					<!-- 반복문으로 상품 표시 -->
					<c:forEach var="k" items="${pro_list}">
						<div class="thumbs_item">
						
							<a href="product_detail_page.do?p_idx=${k.p_idx}">
							<img src="./resources/images/${p_category}/${k.f_name}" class="thumbs_img" />
							</a>
							
							<div class="thumbs_info">
								<div class="thumbs_text">
									<span class="thumbs_title">${k.p_name}</span>
                    				<span class="thumbs_owner"><fmt:formatNumber value="${k.p_price}" pattern="#,##0"/></span>
								</div>
								<div class="thumbs_emo_box">
									<c:choose>
										<c:when test="${k.active == 0 }">
											<span class="wish_btn"><button class="wishlist-btn"
													type="button" value="${k.p_idx}">♡</button></span>
										</c:when>
										<c:otherwise>
											<span class="wish_btn"><button class="wishlist-btn"
													type="button" value="${k.p_idx}">♥</button></span>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>

	<!-- 공용 footer -->
	<%@ include file="../common/footer.jsp"%>
	</section>
</body>
</html>