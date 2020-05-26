<%@page import="java.util.ArrayList"%>
<%@page import="poly.util.CmmUtil"%>
<%@page import="java.util.List"%>
<%@page import="poly.dto.COMMENTDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
    COMMENTDTO pDTO = (COMMENTDTO)request.getAttribute("pDTO");
    
        if(pDTO ==null){
        	pDTO = new COMMENTDTO();
        }
    String rno = CmmUtil.nvl((String)request.getAttribute("rno"));    
    String content = CmmUtil.nvl(pDTO.getContent());
	%>
<html>


<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="/bootstrap/Board/css/bootstrap.min.css">
<link rel="stylesheet" href="/fonts/font-awesome.min.css">
<link rel="stylesheet" href="/css/Board/Forum---Thread-listing-1.css">
<link rel="stylesheet" href="/css/Board/Forum---Thread-listing.css">
<link rel="stylesheet" href="/css/Board/styles.css">


    <title>댓글 수정</title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
    <!-- include summernote css/js-->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">    
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Cookie">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Nanum+Gothic">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
    <!-- include summernote-ko-KR -->
   
</head>
<header>
</header>
<body style="overflow-x: hidden">
    <div style="width: 740px; margin: left;">
        <div class="row">
            <div class="col-md-12">
            	<div>
                    <ul class="nav nav-tabs">
                        <li class="nav-item" style="margin-top:200px">
                        <a class="nav-link active" role="tab" data-toggle="tab">댓글<span class="badge badge-pill badge-primary">★</span></a></li>
                    </ul><br>
                    
    <div style="width: 750px; margin: auto;">
	<form id="AAAA" name="AAAA" method="post" action="/BandMoa/CommentModifyGoM.do<%-- ?rno=<%=pDTO.getRno() %> --%>">
	<input type="hidden" value="<%=rno %>" id="AAA" name="AAA">
	<input value="<%=pDTO.getWriter() %>" id="writer" name="writer"  hidden="hidden">
	<input value="<%=pDTO.getBoard_seq() %>" id="board_seq" name="board_seq"  hidden="hidden">
	<div>
	<div>
	<textarea id="content" name="content" style="width: 748px; height: 80px; margin-left: 5px; resize:none;" placeholder="<%=pDTO.getContent().replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\"","&quot;") %>"></textarea>
	</div>
	<div style="font-size: 20px; font-weight: 400; width:760px;">
	<div style="text-align:center;display: inline-block; padding: 10px 50px 10px 50px;">비밀번호</div>
	<div style="display: inline-block; padding: 10px 0px 10px 50px; width: 545px;">
	<div><input type="password" name="pw" id="pw" maxlength="100" style="width: 505px;"></div>
	</div>
	</div>
	
	<div style="text-align:center;width:755px; padding-top: 5px;">
	<div class="form-group"> <!-- submit이 input에 들어가면 유효성 검사가 실행이X -->
        <input type="button" id="submitB" class="btn btn-primary btn-block" readonly="readonly" style="cursor:pointer;" value="작성완료"></div>
        	<a class="forgot" href="/BandMoa/CommentDeleteM.do?rno=<%=rno %>">이 댓글 삭제하기 /<font color="red"> (주의)클릭 시 즉시 삭제됩니다.</font></a><br>
   			 <a class="forgot" href="/BandMoa/M/BMmB.do?Pno=1" >게시판으로 돌아가기</a>

	</div>
	</div>
	</form>

 	<script type="text/javascript">
	$(function(){ //window.onload
		var submit = 0;
		console.log(submit);
		
		var rePw = /^[a-zA-Z0-9]{4,12}$/; // userid

		    $("#submitB").click(function() {

		        //userid 를 param.
		        var content =  $("#content").val();
		        console.log(content);

		        if(content == ""){
		            alert("내용을 입력해 주세요.");
		            return false;
		        }  
		        else if($("#pw").val()==""){
			    	alert("댓글 비밀번호를 입력해주세요");
			    	$("#pw").focus();
			    	return false;
			    }
		        else if(!rePw.test($("#pw").val())){
			    	alert("비밀번호 형식이 옳지 않습니다.");
			    	$("#pw").focus();
			    	return false;
			    } else{
		        //유효성 검사가 끝나면 아이디 f를 찾아서 액션을 실행해라.
		        document.getElementById('AAAA').submit();		        
		        }
		        

		 });


	});
	</script>

</body>
</html>











