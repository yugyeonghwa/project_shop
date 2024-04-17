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
<link href="<c:url value="/resources/css/order_page.css"/>"
	rel='stylesheet' />
<title>결제 페이지</title>
</head>
<body>
	<%-- 헤더 위치 --%>
	<%@ include file="../common/header.jsp"%>

	<section class="order_page_section">
		<h1>결제페이지</h1>
		<hr />
		<form id="myform" method="post">
			<table class="order_table">
				<tr>
					<td>
						<table class="order_porduct_info order_table_detail">
							<thead>
								<tr>
									<th colspan="4">상품 정보</th>
								</tr>
							</thead>
							<tbody>
								<tr class="order_table_detail_tr">
									<td><b>상품 정보</b></td>
									<td><b>주문 수량</b></td>
									<td><b>상품 크기</b></td>
									<td><b>상품 가격</b></td>
								</tr>

								<!-- 얘는 db에서 가져와야함 반복문-->
								<c:forEach var="k" items="${basket_list}">
									<tr>
										<td style="font-size: 40px;"><img src="resources/images/${k.f_name}"
											style="width: 50px; height:40px; text-align: left;"> ${k.p_name }</td>
										<td style="text-align: center; font-size: 40px;">${k.quant } </td>
										
										<td style="text-align: center; font-size: 40px;">${k.p_size }
										<input type="hidden" name ="quant" value = ${k.quant }> 
										<input type="hidden" name ="b_idx" value = ${k.b_idx }> 
										</td>
										<td style="text-align: center; font-size: 40px;"><fmt:formatNumber value="${k.totalPrice }" pattern="#,##0" /></td>
									</tr>
									<c:set var="cartTotal" value="${cartTotal + k.totalPrice }"></c:set>
								</c:forEach>
							</tbody>
							<tr>
								<td colspan="4" style="text-align: right; padding: 10px 50px">
									총 결재액: <fmt:formatNumber value="${cartTotal }" pattern="#,##0" />원
								</td>
							</tr>
						</table>
					</td>
				</tr>

				<tr>
					<td>
						<table class="order_people_info order_table_detail" >
							<thead>
								<tr>
									<th colspan="2">구매자 정보</th>
								</tr>
							</thead>
							<tbody>
								<tr style="width: 50px">
									<td style="text-align: center; font-size: 24px; padding: 5px">이름</td>
									<td style ="padding-left : 5px " >${userVO.u_name }</td>
								</tr>
								<tr>
									<td style="text-align: center; font-size: 24px; padding: 5px">연락처</td>
									<td style ="padding-left : 5px"  >${userVO.u_phone }</td>
								</tr>
								<tr>
									<td style="text-align: center; font-size: 24px; padding: 5px">이메일</td>
									<td style ="padding-left : 5px"  >${userVO.u_email }</td>
								</tr>
								<tr>
									<td style="text-align: center; font-size: 24px; padding: 5px">주소</td>
									<c:choose>
										<c:when test="${empty addr_basic.roadAddr  }">
										<td>
										<td style ="padding-left : 5px  ">우편번호: ${addr_basic.zip_code } <br> 주소: ${addr_basic.jibunAddr } ,상세주소: ${addr_basic.addrDetail} </td>
										</c:when>
										<c:otherwise>
										<td style ="padding-left : 5px"  >
											우편번호: ${addr_basic.zip_code }<br> 주소: ${addr_basic.roadAddr } ,상세주소: ${addr_basic.addrDetail}
											</td>
										</c:otherwise>
									</c:choose>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<form id ="receiver_form">
							<table class="order_people_info order_table_detail" >
								<thead>
									<tr>
										<th colspan="2">수령자 정보</th>
									</tr>
								</thead>
								<tbody>
									<tr style="width: 50px">
										<td style="text-align: center; font-size: 24px; padding: 5px">이름</td>
										<td style ="padding-left : 5px " ><input type="text" id="recever_name" name="receiver_name" value="${userVO.u_name }" /></td>
									</tr>
									<tr>
										<td style="text-align: center; font-size: 24px; padding: 5px">연락처</td>
										<td style ="padding-left : 5px"  ><input type="text" id="receiver_phone" name="receiver_phone" value="${userVO.u_phone }" />
											(숫자만 입력해 주세요.)</td>
									</tr>
									<tr id ="last_address">
										<%-- 배송지 출력 --%>
									</tr>
								</tbody>
							</table>
						
					</td>
				</tr>
			
				<tr>
					<td>
						<table class="order_people_info order_table_detail delivery_address_list_wrapper">
							<thead>
								<tr>
									<th colspan="2">배송지 변경</th>
								</tr>
							</thead>
							<tbody>
								<!-- 배송지 변경 버튼 클릭시 나타나는 테이블 -->
								<tr>
									<td >
										<table id="addr_t" style = "margin: 0 auto;">
											<%-- 배송지 목록들 --%>
										</table>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>

				<tr>
					<td>
						<table class="order_transaction order_table_detail">
							<tr>
								<th>결제 정보</th>
							</tr>
							<tr>
								<td style="width: 100px; text-align: left; border-right: none">
									보유 포인트 : <fmt:formatNumber value="${userVO.u_point }" pattern="#,##0" />p<br /> 
									총 결재액: <input type="hidden" name = "orderpriceTotal" value = "${cartTotal }"> <fmt:formatNumber value="${cartTotal }" pattern="#,##0" />원<br /> 
									결제 후 잔액: <fmt:formatNumber value="${userVO.u_point - cartTotal }" pattern="#,##0" />p<br />  
								</td>
							</tr>
							<tr>
							<tr>
								<th>약관 동의</th>
							</tr>
							<tr>
								<td>
								<details>
									<summary>제1조(목적) 이 약관은 OO(이하 “회사”)가 제공하는 쇼핑몰형 구매대행 관련 서비스(이하 “서비스”)를 이용함에 있어 회사와 이용자 간의 권리·의무, 책임사항 및 절차 등을 규정함을 목적으로 합니다.</summary>
									<div>
										제2조(정의) 이 약관에서 사용하는 용어의 정의는 다음과 같습
										니다.
										<br>
										1. “몰”은 회사가 이 약관에 의하여 재화 또는 용역(이하 “재화 등”)을 이용자에게 제공하기 위하여 정보통신설비와 정보 통신망을 이용하여 재화 등을 거래할 수 있도록 설정한 가 상의 영업장을 말하며, 아울러 “몰”을 운영하는 회사의 의미로도 사용합니다.
										<br>
										2. “이용자”라 함은 회사가 제공하는 서비스를 이용하는 자를 말합니다.
										<br>
										3. “해외사업자”라 함은 대한민국 이외의 국적이거나 대한민국 이외의 국가에 사업자 등록, 법인, 영업소, 호스트서버 소재지 등을 가진 사업자를 의미합니다.
										<br>
										4. “쇼핑몰형 구매대행”이라 함은 회사가 “몰”을 통해 해외에서 구매 가능한 재화 등에 대하여 정보를 제공하고 이용자의 청약을 받아, 회사가 해당 재화 등을 이용자의 명의로 수입하여 판매하는 것을 의미합니다. 다만, 이 경우 재화 등은 이용자의 자가 소비용에 한정합니다.
										<br>
										5. “검수”라 함은 이용자가 구매한 재화 등의 누락, 하자, 파손 여부 등을 회사가 정한 기준에 따라 확인하는 서비스를 의미합니다.
										<br><br>
										제3조(서비스의 제공) 회사는 다음 각 호의 업무를 수행할 수 있습니다.
										<br>
										1. 재화 등에 대한 정보제공
										<br>
										2. 수입 및 통관 관련 업무
										<br>
										3. 국제반송 관련 업무
										<br>
										4. 기타 회사가 정하는 업무
										<br><br>
										제4조(서비스이용 제한)
										1 회사는 이용자의 서비스 이용 요청이 다음 각 호의 어느 하나에 해당하는 경우 서비스 제공을 거절할 수 있습니다.
										<br>
										1-1. 신청내용에 허위, 기재누락, 오기가 있는 경우 
										<br>
										1-2. 이용자가 요청한 서비스의 제공이 회사의 경영상 또는 기술상의 이유로 현저히 곤란한 경우
										<br>
										1-3. 이용자가 불법 또는 부당한 목적을 위해 서비스를 이용하는 것으로 판단되는 경우
										<br>
										2 전항에 따라 서비스 제공을 거절하는 경우, 회사는 이용자에게 거절의 사유 및 근거를 통지하여야 합니다.
										<br><br>
										제5조(상품가의 구성)
										1 “몰”에 표시된 재화 등의 판매 가격은 [해외사업자로부터의 해당 재화 등 구매가격, 해외사업자로부터 회사의 해외 수령 장소까지의 운송료, 해외 현지 세금, 해외 구매 계약 체결 수수료, 해외 현지 수령 장소(해외 물류센터) 이용료, 국제운송료와 수입관세, 수입부가세, 국내운송료, 기타세금 등(이하 “관·부가세
										등”)]이 포함된 가격입니다.
										<br>
										2 회사는 이용자가 재화 등의 판매가격을 지급하기 전에 전항에 따른 판매가격의 구성 내역을 구분하여 고지하여야 합니다.
										<br>
										3 판매가격은 해외사업자의 할인 행사, 환율 변화 등의 사유로 변동될 수 있으며, 이로 인하여 회사가 청약을 받은 재화를 공급할 수 없는 경우 그 사유를 이용자에게 알리고 요금을 3영업일 이내에 환급하는 등의 조치를 해야 합니다.
									</div>
								</details>
							</td>
							</tr>
							<td>결제 약관 동의 <input type="checkbox" id="Payment_Terms"/>
							</td>
							</tr>

							<tr>
								<td colspan="2" style="text-align: center"><input
									type="button" value="결제하기" onclick="order_submit(this.form)"
									style="width: 300px; height: 50px; font-size: 30px; font-weight: bold;"
									 />
									</td>
									
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
		<%-- 푸터 위치 --%>
		<%@ include file="../common/footer.jsp"%>
	</section>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js" defer></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	// 우편주소
	function execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						document.getElementById('zip_code').value = data.zonecode;
						document.getElementById('roadAddr').value = data.address;
						document.getElementById('jibunAddr').value = data.jibunAddress;
						document.getElementById('addrDetail').focus();
					}
				}).open();

	}
</script>
	
<script type="text/javascript">
	/* 배송지 목록 열고 닫는 버튼 */
	let btn_count = 0;
	let cnt = 0;
	$(document).ready(function() {
	if("${paychk}" === "fail" && "${pointfail}" === "ok"){
		alert("보유 포인트 부족 결제 실패")
	} else if("${paychk}" === "fail"){
		alert("결제 실패")
	}
	
	getAddrList();
		/* DB에서 주소목록 가져오기 */
		function getAddrList() {
			$.ajax({
				url: "getAjaxAddrList.do",
				method : "post",
				dataType : "json",
			
				success : function(data) {
				console.log(1)
					let tbody = "";
					
					let recentAddress = data.find(function(address) {
							return address.selected === "1";
						});
					
					if (recentAddress && recentAddress.selected === "1") {
					    tbody += "<tr> ";
					    tbody += "<td>선택 배송지</td>";
					    
						$('#last_address').empty();
						
					    let html = "<td style='text-align: center; font-size: 24px; padding: 5px'>배송지</td>"
			                html += "<td style ='padding-left: 5px;'>우편번호: " + recentAddress.zip_code + "<br> 주소: ";

			                if (recentAddress.roadAddr === null || recentAddress.roadAddr === "") {
			                    html += recentAddress.jibunAddr + " ,상세주소: " + recentAddress.addrDetail;
			                } else {
			                    html += recentAddress.roadAddr + " ,상세주소: " + recentAddress.addrDetail;
			                }

			                html += "<button type='button' onclick='delivery_address_change()'>배송지변경</button></td>";


		                $("#last_address").append(html);
		                
					    if (recentAddress.roadAddr == null || recentAddress.roadAddr === "") {
					        let addressDetail = (recentAddress.addrDetail !== null && recentAddress.addrDetail !== "") ? recentAddress.addrDetail : "";
					        tbody += "<td style='padding-left: 5px;'>우편번호: " + recentAddress.zip_code + "<br> 주소: " + recentAddress.jibunAddr + "<br> 상세 주소: " + addressDetail + "</td>";
					    } else {
					        let addressDetail = (recentAddress.addrDetail !== null && recentAddress.addrDetail !== "") ? recentAddress.addrDetail : "";
					        tbody += "<td style='padding-left: 5px;'>우편번호: " + recentAddress.zip_code + "<br> 주소: " + recentAddress.roadAddr + "<br> 상세 주소: " + addressDetail + "</td>";
					    }

					    tbody += "<td><input type='hidden' name ='addr_idx' value='" + recentAddress.addr_idx + "'><button type='button' id ='addr_basic_" + recentAddress.addr_idx + "' class ='addr_basic'>배송지 선택</button></td>";
					    tbody += "</tr>";
					} else {
					    tbody += "<tr><td colspan='3'>최근 배송지가 없습니다.</td></tr>";
					}
					
					data.forEach(function(address, index) {
						if(address.selected !== "1") { // 최근 배송지가 아닌 경우에만 출력
					        tbody += "<tr>";
					        tbody += "<td> 배송지 </td>";
					        
					        let addressDetail = " ";
					        if (typeof address.addrDetail !== 'undefined' && address.addrDetail !== null && address.addrDetail !== "") {
					            addressDetail = address.addrDetail;
					        }
					        if (address.roadAddr === null || address.roadAddr === "") {
					            tbody += "<td style='padding-left: 5px;'>우편번호: " + address.zip_code + "<br> 주소: " + address.jibunAddr + "<br> 상세 주소:" + addressDetail + "</td>";
					        } else {
					            tbody += "<td style='padding-left: 5px;'>우편번호: " + address.zip_code + "<br> 주소: " + address.roadAddr + "<br> 상세 주소:" + addressDetail + "</td>";
					        }
					        
					        tbody += "<td><input type='hidden' name ='addr_idx' value='" + address.addr_idx + "'><button type='button' id ='addr_basic_" + address.addr_idx + "' class ='addr_basic'>배송지 선택</button></td>";
					        tbody += "</tr>";
					    }
					});
	            	
					 $("#addr_t").empty(); 
					 // 기존 테이블 내용 초기화
				        // 신규 배송지 추가 부분을 AJAX로 생성
				        let newAddressHTML = `
				            <tr>
				                <td>신규 배송지</td>
				                <td>
				                    <input type="text" id="zip_code" name="zip_code" placeholder="우편번호" readonly /> 
				                    <input type="button" onclick="execDaumPostcode()" value="우편번호 찾기" /> <br> 
				                    <input type="text" id="roadAddr" name="roadAddr" placeholder="도로명주소" style="width: 500px" readonly /><br> 
				                    <input type="text" id="jibunAddr" name="jibunAddr" placeholder="지번주소" style="width: 500px" readonly /><br>
				                    <input type="text" id="addrDetail" name="addrDetail" placeholder="상세주소" style="width: 500px" />
				                    <input type="hidden" name="u_idx" value="${userVO.u_idx}">
				                </td>
				                <td><button type="button" id="addr_add">배송지 추가</button></td>
				            </tr>
				        `;
				     $("#addr_t").append(newAddressHTML);
				     $("#addr_t").append(tbody);

				},
				error: function() {
					alert("읽기 실패")
				}
			});
		}

		

		$("table").on("click","#addr_add",(function() {
			event.stopPropagation();
	    	let zipCode = document.getElementById('zip_code').value;
	    	if (zipCode === null || zipCode === ''){
	    		alert("신규 배송지가 없습니다.")
	    	} else{
			let param = $("#myform").serialize();
			$.ajax({
				url : "Addr_add.do",
				method : "post",
				data : param,
				dataType : "json",
				success : function(data) {
					if(data == "0"){
						alert("배송주 추가 실패")
					}else if (data == "1") {
						$("#addr_t").empty();
						getAddrList();
					}
				},
				error: function() {
					alert("전송 실패")
				}
			})
	    	}
		}));
		
		
		/* 배송지 변경 기능 */
		$("table").on("click",".addr_basic",(function() {
			event.stopPropagation();
			/* 해당 버튼과 클래스 명이 같은 버튼들이 있으므로 이벤트 전파를 막음 */
			/* 현재 요소에서 가장 가까운 td의 name="addr_idx"의 val선택 */ 
			let addrIdx = $(this).closest('td').find('input[name="addr_idx"]').val();
		    // AJAX 요청에 addrIdx를 추가하여 보냄
		    let param = "&addr_idx=" + addrIdx;
		    $.ajax({
		        url: "addr_basicSet.do",
		        method: "post",
		        data: param,
		        dataType: "json",
		        success: function(data) {
		        	delivery_address_change();
		        	getAddrList();
		        },
		        error: function() {
		            alert("전송 실패");
		        }
		    });
		    
		}));
		
	});
	
	function order_submit(f){
		if(!document.getElementById('Payment_Terms').checked){
			alert("결제 약관에 동의해주세요.")
		} else{
			f.action = "paymentCheck.do"
			f.submit();
		}
	}
	
	
	
	
	
	/* 주소록 열고 닫는 기능 */
	function delivery_address_change() {
		
	  const delivery_address_list = document.querySelector(
	    ".delivery_address_list_wrapper"
	  );
		
	  if (btn_count % 2 == 0) {
	    delivery_address_list.style.display = "block";
	  } else {
	    delivery_address_list.style.display = "none";
	  }
	  btn_count++;
	}
	

		
		
		
</script>
</body>
</html>