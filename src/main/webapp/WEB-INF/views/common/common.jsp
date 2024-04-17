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
    <%-- <script src="/resources/js/그 페이지.js"></script>--%>
    <%-- <link href="<c:url value="/resources/css/그 페이지.css"/>" rel='stylesheet' /> --%>
    <title>공통</title>
  </head>
  <body>
 	<%-- 헤더 위치 --%>
  	<%@ include file="header.jsp" %>
  
    <section>
     <%-- 각 페이지 내용 위치 --%>
     
     <%-- 푸터 위치 --%>
      <%@ include file="footer.jsp" %>
    </section>
  </body>
</html>

