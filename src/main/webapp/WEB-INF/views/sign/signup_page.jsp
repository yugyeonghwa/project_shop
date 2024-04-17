<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>회원가입</title>
<link href="<c:url value="/resources/css/common.css"/>" rel='stylesheet' />
<link href="<c:url value="/resources/css/signup_page.css"/>" rel='stylesheet' />
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
	
	
	// 아이디 중복확인
	// 아이디 중복확인을 위한 전역변수 (signup_go(f) 때도 사용)
	let idChkClicked = false;	
	function SignIdChk() {
		$.ajax({
			url : "sign_idchk.do",
			data : "u_id="+$("#u_id").val(), 
			method : "post", 	
			dataType : "text",
			success : function(data) {
				if (data === '1') {
					alert("사용가능");
					// 아이디 중복확인 버튼이 클릭됨을 표시
					idChkClicked = true; 
				} else if (data === '0') {
					alert("사용불가능");
					idChkClicked = false;
				}
			},
			error : function() {
				alert("읽기 실패");
				idChkClicked = false;
			}
		});
	}
	
	
	// 비밀번호 확인 
	function pwd_chk() {
		let u_pwd = document.getElementById('u_pwd');
		let u_pwd2 = document.getElementById('u_pwd2');
		let msg = document.getElementById('msg');
		
		let trueColor = "green";	
		let falseColor = "red";
		
		if (u_pwd.value == u_pwd2.value) {
			msg.style.color = trueColor;
			msg.innerHTML = "비밀번호 일치"
		} else {
			msg.style.color = falseColor;
			msg.innerHTML = "비밀번호 불일치"
		}
	}
	
	
	
	
	// 이용약관
	// 전체동의 체크박스
	function checkboxAll() {
		// #chk_all(전체동의) 체크박스 요소 가져오기
		let chkAll = document.getElementById('chk_all');
		// .chk_item(개별동의) 체크박스 요소들 가져오기
		let chkItems = document.querySelectorAll('.chk_item');
		
			// .chk_item의 체크상태를 #chk_all와 동일하게
			chkItems.forEach(function(chkItem) {
				chkItem.checked = chkAll.checked;
		});
	}
	
	// 개별 체크박스
	function chkItemClick() {
		let chkAll = document.getElementById('chk_all');
		let chkItems = document.querySelectorAll('.chk_item');
		
		let allChecked = true;
		// .chk_item 체크박스 중 하나라도 언체크이면 전체동의 체크박스도 해제
		chkItems.forEach(function(chkItem) {
			if (! chkItem.checked) {
				allChecked = false;
			}
		});
		chkAll.checked = allChecked;
	}
	
		
	// 가입하기 버튼 클릭 시
	function signup_go(f) {
		 if(f.u_id.value === '' || f.u_pwd.value === '' || f.u_pwd2.value === '' || 
				 f.u_name.value === ''  || f.u_phone.value === '' || f.u_email.value === '' 
				 || f.u_birth.value === '' || f.u_gender.value === ''  || f.zip_code.value === ''
				 ){
	            alert("필수항목을 입력해 주세요.");
	            return false;
	        } else if (!f.chk_1.checked || !f.chk_2.checked) {
	            alert("필수 이용약관을 동의해 주세요.");
	            return false;
			} else if (! idChkClicked) { 
		        alert("아이디 중복확인을 해주세요.");
		        return false;
		    } else if (f.u_phone.value.length !== 11) {
			    alert("- 를 제외한 전화번호 11자리로 입력해 주세요.");
		        return false;
			} else if (!/^[\w-]+(?:\.[\w-]+)*@(?:[\w-]+\.)+[a-zA-Z]{2,}$/.test(f.u_email.value)) {
				alert("이메일 양식을 확인해 주세요.");
		        return false;
			} else if (f.u_birth.value.length !== 6) {
				alert("생년월일 6자리를 입력해 주세요.");
				return false;
			}
		 
		f.action="user_signup.do";
		f.submit();
	}
	
</script>

</head>
<body>
	<%@ include file="../common/header.jsp"%>


	<!-- =========================================================== -->

	<section>
	<form action="user_signup.do" method="post">
		<div id="sign_wrap">
			<h1>회원가입</h1>
			<p>* 필수입력사항</p>
		</div>
		
		<div id="box_1">
			<table>
				<tr>
					<th colspan="2">기본정보</th>
				</tr>

				<tr>
					<th><label for="u_id">* 아이디</label></th>
					<td><input type="text" name="u_id" id="u_id" required/> 
					<input type="button" value="중복확인" onclick="SignIdChk()"></td>
				</tr>

				<tr>
					<th><label for="u_pwd">* 비밀번호</label></th>
					<td><input type="password" name="u_pwd" id="u_pwd" required /></td>
				</tr>

				<tr>
					<th><label for="u_pwd2">* 비밀번호 확인</label></th>
					<td><input type="password" name="u_pwd2" id="u_pwd2"
						required oninput="pwd_chk()" /> <span id="msg"></span> </td>
				</tr>

				<tr>
					<th><label for="u_name">* 이름</label></th>
					<td><input type="text" name="u_name" id="u_name" required /></td>
				</tr>

				<tr>
					<th>* 주소</th>
					<td><input type="checkbox" id="chk_addr" checked><label for="chk_addr" >기본배송지로 저장</label><br> 
						<input type="text" id="zip_code" name="zip_code" placeholder="우편번호" required readonly /> 
						<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기" /> <br> 
						<input type="text" id="roadAddr" name="roadAddr" placeholder="도로명주소" style="width: 500px" readonly /><br> 
						<input type="text" id="jibunAddr" name="jibunAddr" placeholder="지번주소" style="width: 500px" readonly /><br>
						<input type="text" id="addrDetail" name="addrDetail" placeholder="상세주소" style="width: 500px" />
					</td>
				</tr>

				<tr>
					<th>* 휴대전화</th>
					<td><input type="text" name="u_phone" title="- 를 제외한 11자리를 입력해 주세요." required /></td>
				</tr>

				<tr>
					<th><label for="u_email">* 이메일</label></th>
					<td><input type="email" name="u_email" id="u_email" required 
						pattern="[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.]+[a-zA-Z]+[.]*[a-zA-Z]*" title="이메일 양식" /></td>
				</tr>

				<tr>
					<th>* 생년월일</th>
					<td><input type="text" name="u_birth" placeholder="6자리 입력" required /></td>
				</tr>

				<tr>
					<th>* 성별</th>
					<td><input type="radio" name="u_gender" value="1" checked />남자
						<input type="radio" name="u_gender" value="2" />여자</td>
				</tr>
			</table>
		</div>

		<div id="box_2">
			<table>
				<tr>
					<th >
					<input type="checkbox" id="chk_all" onclick="checkboxAll()"> 
					<label for="chk_all">이용약관 전체 동의</label></th>
				</tr>

				<tr>
					<td>
						<p>[필수] 이용약관 동의</p> 
						<div class="p_box">
						<p>제1조(목적) <br>
						  	본 이용약관은 (쇼핑몰 이름) 쇼핑몰(이하 "회사"라 함)이 제공하는 인터넷 관련 서비스(이하 "서비스"라 함)를 이용함에 있어 회사와 이용자의 권리, 의무 및 책임사항을 규정함을 목적으로 합니다.
							제2조 (정의) <br>
							"회원"이라 함은 회사의 서비스에 접속하여 본 이용약관에 따라 회사와 이용계약을 체결하고 회사가 제공하는 서비스를 이용하는 고객을 말합니다.
							"아이디(ID)"라 함은 회원의 식별과 회원의 서비스 이용을 위하여 회원이 정하고 회사가 승인하는 문자와 숫자의 조합을 말합니다.</p> 
						</div>
							<input type="checkbox" id="chk_1" class="chk_item" onclick="chkItemClick()">
							<label for="chk_1">동의함</label>
					</td>
				</tr>
				<tr>
					<td>
						<p>[필수] 개인정보 수집 동의</p>
						<div class="p_box">
						<p>1. 개인정보 수집목적 및 이용목적
						가. 서비스 제공에 관한 계약 이행 및 서비스 제공에 따른 요금정산
						콘텐츠 제공 , 구매 및 요금 결제 , 물품배송 또는 청구지 등 발송 , 금융거래 본인 인증 및 금융 서비스
						나. 회원 관리
						회원제 서비스 이용에 따른 본인확인 , 개인 식별 , 불량회원의 부정 이용 방지와 비인가 사용 방지 , 가입 의사 확인 , 연령확인 , 
						만14세 미만 아동 개인정보 수집 시 법정 대리인 동의여부 확인, 불만처리 등 민원처리 , 고지사항 전달 </p>
						</div>
							<input type="checkbox" id="chk_2" class="chk_item" onclick="chkItemClick()">
							<label for="chk_2">동의함</label>
					</td>
				</tr>
				<tr>
					<td>
						<p>[선택] 광고 수신 동의</p>
						<div class="p_box">
						<p>할인쿠폰 및 혜택, 이벤트, 신상품 소식 등 쇼핑몰에서 제공하는 유익한 쇼핑정보를 SMS나 이메일로 받아보실 수 있습니다.
						단, 주문/거래 정보 및 주요 정책과 관련된 내용은 수신동의 여부와 관계없이 발송됩니다. </p>
						</div>
							<input type="checkbox" id="chk_3" class="chk_item" onclick="chkItemClick()">
							<label for="chk_3">동의함</label>
					</td>
				</tr>
			</table>
		</div>
		
		<div id="box_3">
			<input type="button" id="but_sign" value="가입하기"  onclick="signup_go(this.form)">
		</div>
</form>

		<!-- =========================================================== -->


		<%@ include file="../common/footer.jsp"%>
	</section>
</body>
</html>