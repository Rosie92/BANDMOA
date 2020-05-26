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

$(function(){ //window.onload
	
	var submit = 0;
	console.log(submit);

	var reId = /^[a-zA-Z0-9]{4,12}$/; // userpw
	var rePw = /^[a-zA-Z0-9]{4,12}$/; // userid
	var reMail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	var reTel = /^\d{3}\d{3,4}\d{4}$/;
	var reName = /^[ㄱ-ㅎㅏ-ㅣ가-힣]{2,4}$/;


	    $("#submit").click(function() {

	        //userid 를 param.
	        var user_id =  $("#user_pw").val();
	        console.log(user_pw);

			if($("#user_pw").val()==""){
		    	alert("비밀번호를 입력해주세요");
		    	$("#user_pw").focus();
		    	return false;
		    	
		    	
		    } else if(!rePw.test($("#user_pw").val())) {
		    	alert("패스워드는 4~12자리의 영문 대소문자와 숫자로만 입력하세요");
		    	$("#user_pw").focus();
		    	return false;
		    
		    }
	        
	        //유효성 검사가 끝나면 아이디 f를 찾아서 액션을 실행해라.
	        $('#s').submit();
	        
	     
	    
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
	String session_pw = CmmUtil.nvl((String)session.getAttribute("SS_USER_PW"));
	String session_no= CmmUtil.nvl((String)session.getAttribute("SS_USER_NO"));
%>

</header>

<body>

    <div class="login-dark">
         
        <h2 class="sr-only">modify Form</h2>
        <form name="s" id="s" method="post" action="/BandMoa/MypageModify2.do"> 
        <div class="form-group"><input class="form-control" type="password" name="user_pw" id="user_pw" value="00000000"></div>
        <input type="hidden" name="no" id="no" value="<%=session_no%>">
        <div class="form-group" id="submit"> <!-- submit이 input에 들어가면 유효성 검사가 실행이X -->
        <input class="btn btn-primary btn-block" style="cursor:pointer;" readonly="readonly"  value="비밀번호 수정"></div>
        <a class="forgot" href="/BandMoa/UserMypage.do">돌아가기</a><br>
        <a class="forgot" href="/BandMoa/BMIndex.do">BandMoa 홈으로 돌아가기</a>

		</form>
	

</div>
<script src="/js/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>