<%@page import="java.util.ArrayList"%>
<%@page import="poly.util.CmmUtil"%>
<%@page import="java.util.List"%>
<%@page import="poly.dto.BMFBDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
    BMFBDTO bDTO = (BMFBDTO) request.getAttribute("bDTO");

        if(bDTO ==null){
        	bDTO = new BMFBDTO();
        }

	%>
<html>


<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="/bootstrap/Board/css/bootstrap.min.css">
<link rel="stylesheet" href="/fonts/font-awesome.min.css">
<link rel="stylesheet" href="/css/Board/Forum---Thread-listing-1.css">
<link rel="stylesheet" href="/css/Board/Forum---Thread-listing.css">
<link rel="stylesheet" href="/css/Board/styles.css">


    <title>자유게시판 작성</title>
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
    <script src="/summernote/js/summernote-ko-KR.js"></script>
    <script>
    
        $(document).ready(function () {
            $('#summernote').summernote({
                placeholder: '내용을 입력해 주세요.',
                minHeight: 370,
                maxHeight: null,
                focus: true,
                lang: 'ko-KR',
                onImageUpload : function(files, editor, welEditable) {
                    sendFile(files[0], editor, welEditable);
                }
            });

            function sendFile(file, editor, welEditable) {
                data = new FormData();
                data.append("uploadFile", file);
                $.ajax({
                    data : data,
                    type : "POST",
                    url : "/imageUpload",
                    cache : false,
                    contentType : false,
                    processData : false,
                    success : function (data) {
                        editor.insertImage(welEditable, data.url);
                    }
                })
            }
        });

    </script>
</head>

<body style="overflow-x: hidden">
    <div style="width: 740px; margin: left;">
        <div class="row">
            <div class="col-md-12">
            	<div>
                    <ul class="nav nav-tabs">
                        <li class="nav-item"><a class="nav-link active" role="tab" data-toggle="tab">자유게시판<span class="badge badge-pill badge-primary">★</span></a></li>
                    </ul><br>
                    
    <div style="width: 750px; margin: auto;">
	<form method="post" action="/BandMoa/BMFreeBRProc.do">
	<div>
	<div style="font-size: 20px; font-weight: 550; width:760px;">
	<div style="text-align:center;display: inline-block; padding: 10px 50px 10px 50px;">제 목</div>
	<div style="display: inline-block; padding: 10px 0px 10px 50px; width: 545px;">
	<div><input type="text" name="title" id="title" maxlength="100" style="width: 521px" placeholder="제목을 입력해주세요" ></div>
	</div>
	</div>
	<div>
	<textarea id="summernote" name="content" style="width: 748px; height: 520px; margin-left: 5px; resize:none;"></textarea>
	</div>
	<div style="font-size: 20px; font-weight: 400; width:760px;">
	<div style="text-align:center;display: inline-block; padding: 10px 50px 10px 50px;">비밀번호</div>
	<div style="display: inline-block; padding: 10px 0px 10px 50px; width: 545px;">
	<div><input type="password" name="board_pw" id="board_pw" maxlength="100" style="width: 505px;"></div>
	</div>
	</div>
	
	<div style="text-align:center;width:755px; padding-top: 5px;">
	<div class="form-group" id="submit"> <!-- submit이 input에 들어가면 유효성 검사가 실행이X -->
        <input type="submit" id="subBtn" class="btn btn-primary btn-block" readonly="readonly" style="cursor:pointer;" value="작성완료"></div>
        <a class="forgot" href="/BandMoa/FB/BMFreeB.do?Pno=1" >돌아가기</a>
	</div>
	</div>
  
	</form>

<script>
    $('#subBtn').click(function () {
        var title = $('#title').val();
        var content = $('#summernote').val();
        var board_pw = $('#board_pw').val();

        if(title==""){
            alert("제목을 입력해 주세요.");
            return false;
            $('#title').focus();
        }
        else if(content==""){
            alert("내용을 입력해 주세요.");
            return false;
            $('#content').focus();
        }
        else if(board_pw==""){
            alert("비밀번호를 입력해 주세요.");
            return false;
            $('#board_pw').focus();
        }
    });
</script>

</body>
</html>











