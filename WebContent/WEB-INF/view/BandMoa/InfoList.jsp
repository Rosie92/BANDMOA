<%@page import="java.util.ArrayList"%>
<%@page import="poly.util.CmmUtil"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@page import="poly.dto.BMUserInfoDTO"%>
<%@page import="poly.dto.PagingDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<BMUserInfoDTO> bList = (List<BMUserInfoDTO>)request.getAttribute("bList");

PagingDTO paging= (PagingDTO)request.getAttribute("paging");

System.out.println("bList.size : " + bList.size());
 %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>BandMoa</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!--     <link rel="stylesheet" href="/bootstrap/Board/css/bootstrap.min.css">
    <link rel="stylesheet" href="/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="/css/Board/Forum---Thread-listing-1.css">
    <link rel="stylesheet" href="/css/Board/Forum---Thread-listing.css">
    <link rel="stylesheet" href="/css/Board/styles.css">
     -->
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

<body style="overflow-x: hidden;">

    <div class="login-dark">
             <h2 class="sr-only">UserInfo Form</h2>
            	<div style="margin-left:auto; margin-right:auto; ">
                    
    <div style="width: 750px; margin: auto; text-align:center;">
	<ul class="nav nav-tabs">
                        <li class="nav-item" style="margin-top:80px;"><a class="nav-link active" role="tab" data-toggle="tab">User List <span class="badge badge-pill badge-primary">★</span></a></li>
                    </ul>
	<ul style="background-color:white;">
    <hr>
            <span style="font-size: 20px"><b>번호</b></span>
            <span style="text-align: center; ; margin-left: 50px; font-size: 20px"><b>아이디</b></span>
            <span style="margin-left: 60px; font-size: 20px"><b>이름</b></span>
            <span style="margin-left: 50px; font-size: 20px"><b>메일</b></span>
			<span style="margin-left: 100px; font-size: 20px"><b>전화번호</b></span>
			<span style="margin-left:80px; margin-right: 50px; font-size: 20px"><b>@</b></span>
			<br><hr>
    </ul>
    <div style="background-color:white;">
         <% for(BMUserInfoDTO bDTO : bList) { %>
    <ul style=" padding:15px 0px 30px 0px; background-color: lightyellow;">
             <span style="width:100px">
                <%=CmmUtil.nvl(bDTO.getUser_no()) %>
            </span>
          
            <span style="width:150px">
            <div style=" width:150px; text-align:center; display:inline-block;">
            <%=CmmUtil.nvl(bDTO.getUser_id()) %>
            </div>            
            </span>
         
            <span style="width:100px;">
            <div style=" width:100px; text-align:center; display:inline-block;">
            <%=CmmUtil.nvl(bDTO.getUser_name()) %>
            </div>
            </span> 
            
            <span style="width:150px">
            <div style=" width:150px; text-align:center; display:inline-block;">
            <%=CmmUtil.nvl(bDTO.getUser_mail()) %>
            </div>
            </span> 
            
            <span style="width:130px">
            <div style=" width:130px; text-align:center; display:inline-block;">
            <%=CmmUtil.nvl(bDTO.getUser_tel()) %>
            </div>
            </span> 
            
            <span style="margin-left:50px">
            <div style="display:inline-block">
            <a href="/BandMoa/AdminUserInfoModify.do?user_no=<%=bDTO.getUser_no()%>">관리</a></div>
            </span>

    </ul>
          <%} %>
          
          
	<div class="tab-pane active" role="tabpanel" id="tab-1">
            <div class="thread-list-head">
                <nav class="thread-pages">
                    <div class="pagination" >
         <%
            if(paging.isPrev()==true) { %>
               <a class="page-item page-link" href="/BandMoa/InfoList.do?Pno=<%=paging.getStartPage()-3%>">이전</a>&nbsp;
               <%}   %>
         
         <% for(int a= paging.getStartPage(); a <= paging.getEndPage(); a++){
            if(paging.getPage()==a) { %>
               <div class='page-item page-link' style="background-color:#1abc9c;color:white;"><%=a%></div>
         <%} else {%>
               <a class='pNum' href="/BandMoa/InfoList.do?Pno=<%=a%>">&ensp;<%=a%>&ensp;</a>
         <%}}%>
         <%
            if(paging.isNext()==true) { %>
               &nbsp;<a class="page-item page-link" href="/BandMoa/InfoList.do?Pno=<%=paging.getEndPage()+1%>">다음</a>
         <%}   %>
         </div>
         <a href="/BandMoa/BMIndex.do">BandMoa 홈으로 돌아가기</a>
         </nav>
         </div>
         </div>
         </div>
	</div>
	
 	</div>

    </div>

    <script src="/js/jquery.min.js"></script>
    <script src="/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>