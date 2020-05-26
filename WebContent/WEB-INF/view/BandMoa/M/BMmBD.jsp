<%@page import="java.util.ArrayList"%>
<%@page import="poly.util.CmmUtil"%>
<%@page import="java.util.List"%>
<%@page import="poly.dto.BMMDTO"%>
<%@page import="poly.dto.COMMENTDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    BMMDTO bDTO = (BMMDTO)request.getAttribute("bDTO");
    List<COMMENTDTO> cList = (List<COMMENTDTO>) request.getAttribute("cList");
    if(bDTO ==null){
    	bDTO = new BMMDTO();
    }
    %>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>BandMoA</title>
<head>
    <link rel="stylesheet" href="/bootstrap/Board/css/bootstrap.min.css">
    <link rel="stylesheet" href="/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="/css/Board/Forum---Thread-listing-1.css">
    <link rel="stylesheet" href="/css/Board/Forum---Thread-listing.css">
    <link rel="stylesheet" href="/css/Board/styles.css">
</head>

<header>
<%
	//CmmUtil 붙이기 수정
	String session_id = CmmUtil.nvl((String)session.getAttribute("SS_USER_ID"));
	String seq = CmmUtil.nvl((String)request.getAttribute("seq"));
%>
</header>
<body style="overflow-x: hidden">


    <div style="width: 740px; margin: left;">
        <div class="row">
   <div class="col-md-12">
   	<div>
  <ul class="nav nav-tabs">
      <li class="nav-item"><a class="nav-link active" role="tab" data-toggle="tab">구인구직 게시판 <span class="badge badge-pill badge-primary">★</span></a></li>
  </ul><br>
  
    <div style="width: 750px; margin: auto;">

	
	<div>
	<div style="font-size: 20px; font-weight: 550; width:760px;">
	<div style="text-align:center;display: inline-block; padding: 10px 50px 10px 50px;">제 목</div>
	<div style="display: inline-block; padding: 10px 0px 10px 50px; width: 545px;">
	<div><input type="text" readonly="readonly" name="title" maxlength="100" style="width: 520px; border:none;" value='<%=bDTO.getTitle()%>'></div>
	</div>
	</div>
	<div>
	<div name="content" style="overflow: auto; width: 747px; height: 520px; margin-left: 10px; resize: none; border: 1px solid black; padding:5px 5px 5px 5px"><%=bDTO.getContent() %></div>
	</div>
	
	<!-- ================================================== -->
	<div style="width:740px;text-align:center;">
	<h4>댓글 리스트</h4>
	</div>
	 <% for(COMMENTDTO cDTO : cList) { %>
    <ul style="background-color: lightyellow;">
             <span>
             	<input type="hidden" value="<%=CmmUtil.nvl(cDTO.getBoard_seq()) %>">
                <input type="hidden" value="<%=CmmUtil.nvl(cDTO.getRno()) %>">
                <input type="hidden" value="<%=CmmUtil.nvl(cDTO.getPw()) %>">
            </span>
            <span style="margin-left:10px;">
            <div style=" width:85px; text-align:center; display:inline-block;">
            <%=CmmUtil.nvl(cDTO.getWriter()) %>
            </div>            
            </span>
            <span style="margin-left:10px">
            <div style="width:450px; text-align:center; display:inline-block;">
            <%=CmmUtil.nvl(cDTO.getContent()).replaceAll("<","&lt;").replaceAll(">","&gt;") %></div>
            </span>
            <span style="margin-left:10px">
            <div style="width:100px; text-align:center; display:inline-block;">
            <%if (session.getAttribute("SS_USER_ID") == null) { %>
            　			
            <%} else if (session.getAttribute("SS_USER_ID").equals("Admin")) { %>
            <a href="/BandMoa/CommentModifyTryM.do?rno=<%=cDTO.getRno() %>">관리</a>
            <%} else { %>
            <a href="/BandMoa/CommentModifyTryM.do?rno=<%=cDTO.getRno() %>">수정/삭제</a>
            <%} %>
            </div>
            </span>
    </ul>
          <%} %>
    
    <% if(session.getAttribute("SS_USER_ID") == null) {%>
     <div style="overflow-x: hidden">
    <div style="width: 740px; margin: left;">
        <div class="row">
            <div class="col-md-12">
    <div style="width: 750px; margin: auto;">
	<form>
	<div>
	<textarea style="width: 740px; height: 50px; margin-left: 5px; resize:none;" readonly="readonly" placeholder="댓글을 작성하려면 로그인해주세요."></textarea>
	</div>
	</form>
	</div>
	</div>
	</div>
	</div>
	</div> 
    <%} else if (session.getAttribute("SS_USER_ID").equals("Admin")) { %>
    <div style="overflow-x: hidden">
    <div style="width: 740px; margin: left;">
        <div class="row">
            <div class="col-md-12">

    <div style="width: 750px; margin: auto;">
	<form id="asdf" name="asdf" method="post" action="/BandMoa/CommentProcM.do?seq=<%=CmmUtil.nvl(bDTO.getSeq()) %>">
	<%-- <input type="hidden" value="<%=CmmUtil.nvl(bDTO.getSeq()) %>"> --%>
	<div>
	<textarea id="content" name="content" style="width: 740px; height: 50px; margin-left: 5px; resize:none;" placeholder="댓글을 입력해주세요"></textarea>
	</div>
	<div style="font-size: 15px; font-weight: 300; width:760px;">
	<div style="text-align:center;display: inline-block; padding: 0px 50px 0px 50px;">댓글 비밀번호</div>
	<div style="display: inline-block; padding: 0px 0px 0px 50px; width: 545px;">
	<div><input type="password" name="pw" id="pw" maxlength="100" style="width: 480px;"></div>
	</div>
	</div>
	<div class="form-group" style="width:155px; padding-top: 5px;">
	<div>
    <input type="button" id="submitA" class="btn btn-primary btn-block" readonly="readonly" style="cursor:pointer; margin-left:300px;" value="댓글쓰기"></div>
	</div>
	</form>
	</div>
	</div>
	</div>
	</div>
	</div> 
    <%} else {%>
    <div style="overflow-x: hidden">
    <div style="width: 740px; margin: left;">
        <div class="row">
            <div class="col-md-12">

    <div style="width: 750px; margin: auto;">
	<form id="asdf" name="asdf" method="post" action="/BandMoa/CommentProcM.do?seq=<%=CmmUtil.nvl(bDTO.getSeq()) %>">
	<%-- <input type="hidden" value="<%=CmmUtil.nvl(bDTO.getSeq()) %>"> --%>
	<div>
	<textarea id="content" name="content" style="width: 740px; height: 50px; margin-left: 5px; resize:none;" placeholder="댓글을 입력해주세요"></textarea>
	</div>
	<div style="font-size: 15px; font-weight: 300; width:760px;">
	<div style="text-align:center;display: inline-block; padding: 0px 50px 0px 50px;">댓글 비밀번호</div>
	<div style="display: inline-block; padding: 0px 0px 0px 50px; width: 545px;">
	<div><input type="password" name="pw" id="pw" maxlength="100" style="width: 480px;"></div>
	</div>
	</div>
	<div class="form-group" style="width:155px; padding-top: 5px;">
	<div>
    <input type="button" id="submitA" class="btn btn-primary btn-block" readonly="readonly" style="cursor:pointer; margin-left:300px;" value="댓글쓰기"></div>
	</div>
	</form>
	</div>
	</div>
	</div>
	</div>
	</div> 
    <%} %>
	<!-- ================================================== -->
	<hr>
	<div style="text-align:center;width:755px; padding-top: 5px;">
	<div>
	<!-- submit이 input에 들어가면 유효성 검사가 실행이X -->
	<% if(session.getAttribute("SS_USER_ID") == null) {%>
	<input type="button" onclick="location.href='/BandMoa/ModifyCertifyTryM.do?seq=<%=seq%>'" class="btn btn-primary btn-block" value="수정/삭제하기"><br>
	<%} else if (session.getAttribute("SS_USER_ID").equals("Admin")) { %>
	<input type="button" onclick="location.href='/BandMoa/ModifyCertifyTryM.do?seq=<%=seq%>'" class="btn btn-primary btn-block" value="관리자 수정/삭제하기"><br>
	<%} else {%>
	<input type="button" onclick="location.href='/BandMoa/ModifyCertifyTryM.do?seq=<%=seq%>'" class="btn btn-primary btn-block" value="수정/삭제하기"><br>
	<%} %>
	<a href="/BandMoa/M/BMmB.do?Pno=1">돌아가기</a>
	</div>
	</div>
	</div>		

	</div>
    </div>
    </div>
    </div>
    </div>
    <script src="/js/Board/jquery.min.js"></script>
    <script src="/bootstrap/Board/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script type="text/javascript">
	$(function(){ //window.onload
		var submit = 0;
		console.log(submit);
		
		var rePw = /^[a-zA-Z0-9]{4,12}$/; // userid

		    $("#submitA").click(function() {

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
		        document.getElementById('asdf').submit();		        
		        }
		        

		 });


	});
	</script>
</body>
</html>