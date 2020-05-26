<%@page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>BandMoa</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
$(function() { //window.onload

	var submit = 0;
	console.log(submit);

	var reId = /^[a-zA-Z0-9]{4,12}$/; // userpw
	var rePw = /^[a-zA-Z0-9]{4,12}$/; // userid
	var reMail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	var reTel = /^\d{3}\d{3,4}\d{4}$/;
	var reName = /^[ㄱ-ㅎㅏ-ㅣ가-힣]{2,4}$/;

	$("#submit").click(function() {

		//userid 를 param.
		var user_id = $("#no").val();
		console.log(no);

		if ($("#user_name").val() == "") {
			alert("이름을 입력해주세요");
			$("#user_name").focus();
			return false;
		}
		else if (!reName.test($("#user_name").val())) {
			alert("이름 형식이 옳지 않습니다 (2~4글자의 한글)");
			$("#user_name").focus();
			return false;
		}

		else if ($("#user_mail").val() == "") {
			alert("메일을 입력해주세요");
			$("#user_mail").focus();
			return false;
		}

		else if (!reMail.test($("#user_mail").val())) {
			alert("메일 형식이 옳지 않습니다 (아이디@email.com)");
			$("#user_mail").focus();
			return false;
		}
		else if ($("#user_tel").val() == "") {
			alert("전화번호를 입력해주세요");
			$("#user_tel").focus();
			return false;
		}
		else if (!reTel.test($("#user_tel").val())) {
			alert("전화번호 형식이 옳지 않습니다. (01012345678)");
			$("#user_tel").focus();
			return false;
		}

		//유효성 검사가 끝나면 아이디 f를 찾아서 액션을 실행해라.
		$('#m').submit();	

				
		

	});

})

</script>
<head>
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/fonts/ionicons.min.css">
    <link rel="stylesheet" href="/css/Login-Form-Dark.css">
    <link rel="stylesheet" href="/css/styles.css">
</head>
<header>

<%
	//CmmUtil 붙이기 수정
	String session_name = CmmUtil.nvl((String)session.getAttribute("SS_USER_NAME"));
	String session_mail = CmmUtil.nvl((String)session.getAttribute("SS_USER_MAIL"));
	String session_tel = CmmUtil.nvl((String)session.getAttribute("SS_USER_TEL"));
	String session_no= CmmUtil.nvl((String)session.getAttribute("SS_USER_NO"));
%>

</header>

<body>

    <div class="login-dark">
        <form name="m" id="m" method="post" action="/BandMoa/MypageModify.do">     
        <h2 class="sr-only">modify Form</h2>
        <div class="form-group"><input class="form-control" type="text" name="user_name" id="user_name" value="<%=session_name%>"></div>
        <div class="form-group"><input class="form-control" type="text" name="user_mail" id="user_mail" value="<%=session_mail%>"></div>
        <div class="form-group"><input class="form-control" type="text" name="user_tel" id="user_tel" value="<%=session_tel%>"></div>
        <input type="hidden" name="no" id="no" value="<%=session_no%>">
        <div class="form-group" id="submit"> <!-- submit이 input에 들어가면 유효성 검사가 실행이X -->
        <input class="btn btn-primary btn-block" style="cursor:pointer;" readonly="readonly" value="회원정보수정"></div>
        <a class="forgot" href="/BandMoa/UserMypage2.do">비밀번호 변경하기</a><br>
        <a class="forgot" href="/BandMoa/UserDeleteTry.do">회원 탈퇴하기</a><br>
        <a class="forgot" href="/BandMoa/BMIndex.do">BandMoa 홈으로 돌아가기</a>
		</form>

</div>
<script src="/js/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>