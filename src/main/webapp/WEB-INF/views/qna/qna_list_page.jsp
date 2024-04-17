<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<script src="https://kit.fontawesome.com/d3367b6d2f.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
		<script type="text/javascript">
			function qna_write_go() {
				location.href="qna_write_go.do";
			}
            
            $(document).ready(function() {
                const postOwner = "${postOwner}";
                if (postOwner == 'no') {
                    alert("작성자만 볼 수 있습니다.");
                    return;
                }
            });
		</script>
		<style type="text/css">
			.tbox{
				margin: 0 auto;
				max-width: 50%;
				padding: 50px;
			}
			.table {
				width: 100%;
				border-collapse: collapse;
				text-align: center;
				margin: 10px; auto;
			}
			
			.table {
				border-collapse: separate;
			 	border-spacing: 1px;
			  	text-align: left;
			  	line-height: 1.5;
			  	border-top: 1px solid #ccc;
			  	margin: 20px 10px;
			}
			
			.table th {
				width: 150px;
			  	padding: 10px;
			  	font-weight: bold;
			  	vertical-align: top;
			  	border-bottom: 1px solid #ccc;
			  	background: #efefef;
			}
			.table td {
			  	width: 350px;
			  	padding: 10px;
			  	vertical-align: top;
			  	border-bottom: 1px solid #ccc;
			}
			
			tbody tr:hover {
				background-color: lightgray;
			}
			p{
				margin: 10px;
				padding: 10px;
			}
			.tbox h2{
				margin: 20px;
				padding: 20px;
				font-weight: bold;
				font-size: 2rem;
			}
			.table tfoot ol.btn_paging {
				list-style: none;
			}
			
			.table tfoot ol.btn_paging li {
				float: left;
				margin-right: 8px;
			}
			.table tfoot ol.btn_paging li a {
				display: block;
				padding: 3px 7px;
				border: 1px solid #00B3DC;
				color: #2f313e;
			}
			
			.table tfoot ol.btn_paging li a:hover {
				background: #00B3DC;
				color: white;
			}
			
			.btn_disable {
				padding: 3px 7px;
				border: 1px solid silver;
				color: silver;
			}
			
			.btn_now {
				padding: 3px 7px;
				border: 1px solid white;
				background: white;
				color: black;
				font-weight: bold;
			}
			.table thead tr{
				text-align: center;
			}
			th, td{
				text-align: center;
			}
			
		</style>
		<link href="<c:url value="/resources/css/common.css"/>" rel='stylesheet' />
		<link href="<c:url value="/resources/css/main.css"/>" rel='stylesheet' />
		<title>shop</title>
	</head>
	<body>
		<%@ include file="../common/header.jsp"%>
		<section>
			<div class="tbox">
				<h2>Q&A 게시판</h2>
				
				<form method="post">
					<table class="table">
						<thead>
							<tr>
								<th>NO</th>
								<th style="width: 1000px;">제목</th>
								<th>작성자</th>
								<th>상태</th>
								<th>등록일</th>
								<th>조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${empty qna_list}">
									<tr>
										<td colspan="6"><h3>게시물이 존재하지 않습니다</h3></td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach var="k" items="${qna_list}" varStatus="vs">
										<tr>
											<td>${paging.totalRecord - ((paging.nowPage-1) * paging.numPerPage + vs.index)}</td>
											<td style="text-align: left;">
												<c:forEach begin="1" end="${k.step}">
													&nbsp; ↳ [답변]
												</c:forEach>
												<c:choose>
													<c:when test="${k.active == 1}">
														<span style="color: gray">삭제된 게시물입니다.</span>
													</c:when>
													<c:otherwise>
														<a href="qna_detail.do?post_idx=${k.post_idx}&cPage=${paging.nowPage}">${k.post_subject}</a>
													</c:otherwise>
												</c:choose>
											</td>

											<td>${k.u_idx}</td>

											<td>
												<c:choose>
													<c:when test="${k.post_status == 1}">
														<span>처리 완료</span>
													</c:when>
													<c:otherwise>
														<span>처리중</span>
													</c:otherwise>
												</c:choose>
											</td>
											<td>${k.regdate.substring(0, 10)}</td>
											<td>${k.hit}</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="6">
									<ol class="btn_paging">
										<c:choose>
											<c:when test="${paging.beginBlock <= paging.pagePerBlock}">
												<li class="btn_disable">&#8249;</li>
											</c:when>
											<c:otherwise>
												<li>
													<a href="qna_list.do?cPage=${paging.beginBlock - paging.pagePerBlock}">&#8249;</a>
												</li>
											</c:otherwise>
										</c:choose>
										<c:forEach begin="${paging.beginBlock}" end="${paging.endBlock}" step="1" var="k">
											<c:choose>
												<c:when test="${k == paging.nowPage}">
													<li class="btn_now">${k}</li>
												</c:when>
												<c:otherwise>
													<li>
														<a href="qna_list.do?cPage=${k}">${k}</a>
													</li>
												</c:otherwise>
											</c:choose>
										</c:forEach>
										<c:choose>
											<c:when test="${paging.endBlock >= paging.totalPage}">
												<li class="btn_disable">&#8250;</li>
											</c:when>
											<c:otherwise>
												<li>
													<a href="qna_list.do?cPage=${paging.beginBlock + paging.pagePerBlock}">&#8250;</a>
												</li>
											</c:otherwise>
										</c:choose>
									</ol>
								</td>
							</tr>
						</tfoot>
					</table>
				</form>
			<input type="hidden" name="u_idx"  value="${userVO.u_idx}">
			<input type="hidden" name="post_idx"  value="${postVO.post_idx}">
			<input type="button" onclick="qna_write_go()" value="글쓰기">
			</div>
			
			<%@ include file="../common/footer.jsp"%>
		</section>
	</body>
</html>

