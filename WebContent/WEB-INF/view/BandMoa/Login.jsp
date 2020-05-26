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
	        var user_id =  $("#user_id").val();
	        console.log(user_id);

	        if(user_id == ""){
	            alert("아이디를 입력해 주세요.");
	            return false;
	        }
	        else if(!reId.test($("#user_id").val())){
		    	alert("아이디 형식이 옳지 않습니다");
		    	$("#user_id").focus();
		    	return false;
		    }
	        else if($("#user_pw").val()==""){
		    	alert("비밀번호를 입력해주세요");
		    	$("#user_pw").focus();
		    	return false;
		    }
	        else if(!rePw.test($("#user_pw").val())){
		    	alert("비밀번호 형식이 옳지 않습니다");
		    	$("#user_pw").focus();
		    	return false;
		    }
	        //유효성 검사가 끝나면 아이디 f를 찾아서 액션을 실행해라.
	        $('#f').submit();
	        

	 });


})

</script>
<head>
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/fonts/ionicons.min.css">
    <link rel="stylesheet" href="/css/Login-Form-Dark.css">
    <link rel="stylesheet" href="/css/styles.css">
</head>
</head>
<body>
    <div class="login-dark">
        <form name="f" id="f" method="post" action="/BandMoa/getUserLoginCheck.do">
            <h2 class="sr-only">Login Form</h2>
            <div class="illustration"><img src="/img/Login_key.png"></div>
            <div class="form-group"><input class="form-control" type="text" id="user_id" name="user_id" placeholder="Id"></div>
            <div class="form-group"><input class="form-control" type="password" id="user_id" name="user_pw" placeholder="Password"></div>
            <div class="form-group"  id="submit">
            <input class="btn btn-primary btn-block" readonly="readonly" style="cursor:pointer;" value="Login"></div>
            <a class="forgot" href="/BandMoa/Login_Help.do">도움이 필요하십니까?</a><br>
            <a class="forgot" href="/BandMoa/BMIndex.do">BandMoa 홈으로 돌아가기</a>
    </form>
    </div>
    <script src="/js/jquery.min.js"></script>
    <script src="/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>