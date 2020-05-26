<%@page import="java.util.ArrayList"%>
<%@page import="poly.util.CmmUtil"%>
<%@page import="java.util.List"%>
<%@page import="poly.dto.BMQTDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    BMQTDTO pDTO = (BMQTDTO)request.getAttribute("pDTO");
if(pDTO ==null){
	pDTO = new BMQTDTO();
}
%>
<!DOCTYPE html>
<html>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>BandMoA</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">


$(function(){ //window.onload
	var submit = 0;
	console.log(submit);

	

	    $("#submit").click(function() {

	        //userid 를 param.
	        var user_id =  $("#board_pw").val();
	        console.log(user_id);
			 if($("#board_pw").val()==""){
		    	alert("비밀번호를 입력해주세요");
		    	$("#user_pw").focus();
		    	return false;
		    	
		    	
		    }
	        //유효성 검사가 끝나면 아이디 f를 찾아서 액션을 실행해라.
	        $('#f').submit();
	        

	 });


})

</script>
<head>

    <link rel="stylesheet" href="/bootstrap/Board/css/bootstrap.min.css">
    <link rel="stylesheet" href="/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="/css/Board/Forum---Thread-listing-1.css">
    <link rel="stylesheet" href="/css/Board/Forum---Thread-listing.css">
    <link rel="stylesheet" href="/css/Board/styles.css">
</head>
<header>
<%
	String seq = CmmUtil.nvl((String)request.getAttribute("seq"));
%>
</header>
<body style="overflow-x: hidden">

    <div style="width: 740px; margin: left;">
        <div class="row">
            <div class="col-md-12">
            	<div>
                    <ul class="nav nav-tabs">
                        <li class="nav-item"><a class="nav-link active" role="tab" data-toggle="tab">팁 게시판<span class="badge badge-pill badge-primary">★</span></a></li>
                    </ul><br>
                    
  	    <div style="width: 750px; margin: auto; text-align:center;">
		<%if(session.getAttribute("SS_USER_ID").equals("Admin")) { %>
		<form name="f" id="f" method="post" action="/BandMoa/ModifyCertify3QT.do?seq=<%=seq%>">
		<input value="<%=seq%>" name="seq" hidden="hidden"><!-- 이거하면 서티파이2로 넘어갈것임 -->
        <h2 class="sr-only">ModifyCertify Form</h2>
        <br><br><br><br><br>
              수정/삭제를 위해 <br>관리자 비밀번호를 입력해주세요.<br><br><br>
        <div class="form-group"><input class="form-control" type="password" name="user_pw" id="user_pw" style="width: 40%; margin-left:225px;"></div>
        <div class="form-group" id="submit"> <!-- submit이 input에 들어가면 유효성 검사가 실행이X -->
        <input class="btn btn-primary btn-block" style="cursor:pointer; width: 40%; margin-left:225px;" readonly="readonly" value="인증하기"></div>
        <a class="forgot" href="/BandMoa/QT/BMqtB.do?Pno=1" width="40%">돌아가기</a>
        
        <%} else {%>
        <!-- 사용자 비밀번호 인증창 -->
        <form name="f" id="f" method="post" action="/BandMoa/ModifyCertify2QT.do?seq=<%=seq%>">
		<input value="<%=seq%>" name="seq" hidden="hidden"><!-- 이거하면 서티파이2로 넘어갈것임 -->
        <h2 class="sr-only">ModifyCertify Form</h2>
        <br><br><br><br><br>
              수정/삭제를 위해 <br>게시글 비밀번호를 입력해주세요.<br><br><br>
        <div class="form-group"><input class="form-control" type="password" name="board_pw" id="board_pw" style="width: 40%; margin-left:225px;"></div>
        <div class="form-group" id="submit"> <!-- submit이 input에 들어가면 유효성 검사가 실행이X -->
        <input class="btn btn-primary btn-block" style="cursor:pointer; width: 40%; margin-left:225px;" readonly="readonly" value="인증하기"></div>
        <a class="forgot" href="/BandMoa/QT/BMqtB.do?Pno=1" width="40%">돌아가기</a>
        <%} %>
</form>
	

	</div>
    </div>
    </div>
    </div>
    </div>
    <script src="/js/Board/jquery.min.js"></script>
    <script src="/bootstrap/Board/js/bootstrap.min.js"></script>
</body>
</html>
