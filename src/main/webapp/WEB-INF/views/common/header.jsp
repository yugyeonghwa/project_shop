<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <script src="https://kit.fontawesome.com/d3367b6d2f.js"></script>
    <title>헤더</title>
  </head>
  <body>
    <header>
      <div class="inner">
        <div class="logo">
          <h1><a href="/">로고</a></h1>
        </div>
        <c:choose>
			<c:when test="${loginChk  != 'ok'}">
                <!-- 로그아웃 유틸 -->
                <div class="util logout_util">
					<ul class="info">
						<li><a href="login_page.do">로그인</a></li>
						<li><a href="signup_page.do">회원가입</a></li>
						<li><a href="community_page.do">고객센터</a></li>
					</ul>
                </div>
                </c:when>
            <c:otherwise>
                <!-- 로그인 유틸 -->
				<div class="util login_util">
					<ul class="info">
						<li>${userVO.u_name }님</li>
						<li>보유 포인트: <fmt:formatNumber value="${userVO.u_point}" pattern="#,##0" /></li>
						<li><a href="logout.do">로그아웃</a></li>
						<li><a href="mypage_page.do">마이페이지</a></li>
						<li><a href="basket_page.do">장바구니</a></li>
						<li><a href="community_page.do">고객센터</a></li>
					</ul>
				</div>
            </c:otherwise> 
        </c:choose>

        <div class="gnb_wrappr">
          <ul class="gnb">
             <li class="category">
              <a href="category_page.do?p_category=worker">워커</a>
            </li>
            <li class="category">
              <a href="category_page.do?p_category=slippers">슬리퍼</a>
            </li>
            <li class="category">
              <a href="category_page.do?p_category=003">카테고리3</a>
            </li>
            <li class="category">
              <a href="category_page.do?p_category=004">카테고리4</a>
            </li>
            <li class="category">
              <a href="category_page.do?p_category=005">카테고리5</a>
            </li>
            <li class="category">
              <a href="category_page.do?p_category=006">카테고리6</a>
            </li>
          </ul>
        </div>
      </div>

    </header>
  </body>
</html>
