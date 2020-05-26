<%@page import="java.util.ArrayList"%>
<%@page import="poly.util.CmmUtil"%>
<%@page import="java.util.List"%>
<%@page import="poly.dto.BMFBDTO"%>
<%@page import="poly.dto.PagingDTO"%>
<%@ page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
  	List<BMFBDTO> bList = (List<BMFBDTO>)request.getAttribute("bList");
    /* BMFBDTO bDTO = (BMFBDTO) request.getAttribute("bDTO"); */
        
    PagingDTO paging= (PagingDTO)request.getAttribute("paging");
 
   System.out.println("bList.size :" + bList.size());
   
    %>
<!DOCTYPE html>
<html>


<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>BandMoA</title>
    <link rel="stylesheet" href="/bootstrap/Board/css/bootstrap.min.css">
    <link rel="stylesheet" href="/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="/css/Board/Forum---Thread-listing-1.css">
    <link rel="stylesheet" href="/css/Board/Forum---Thread-listing.css">
    <link rel="stylesheet" href="/css/Board/styles.css">
</head>


<body style="overflow-x: hidden">

    <div style="width: 740px; margin: left;">
        <div class="row">
            <div class="col-md-12">
            	<div>
                    <ul class="nav nav-tabs">
                        <li class="nav-item"><a class="nav-link active" role="tab" data-toggle="tab">자유게시판 <span class="badge badge-pill badge-primary">★</span></a></li>
                    </ul>
    <div style="width: 750px; margin: auto; text-align:center;">
	<ul>
    <hr>
            <span style="font-size: 20px"><b>번호</b></span>
            <span style="text-align: center; ; margin-left: 180px; font-size: 20px"><b>글제목</b></span>
            <span style="margin-left: 180px; font-size: 20px"><b>작성자</b></span>
            <span style="margin-left: 100px; font-size: 20px"><b>수정일</b></span><br><hr>
    </ul>                     
    <div>
         <% for(BMFBDTO bDTO : bList) { %>
    <ul style=" padding:15px 0px 20px 0px; background-color: lightyellow;">
             <span style="; margin-left: 50px;">
                <%=CmmUtil.nvl(bDTO.getBoard_seq()) %>
            </span>
          
            <span style="margin-left:50px">
            <div style="width:350px; text-align:center; display:inline-block;"><a href="/BandMoa/FB/BMFreeBD.do?seq=<%=bDTO.getBoard_seq()%>"><%=CmmUtil.nvl(bDTO.getTitle()).replaceAll("<","&lt;").replaceAll(">","&gt;") %></a></div>
            </span>
         
            <span style="margin-left:10px;">
            <div style=" width:85px; text-align:center; display:inline-block;">
            <%=CmmUtil.nvl(bDTO.getUser_id()) %>
            </div>            
            </span>
         
         
            <span style="margin-left:20px;">
            <%=CmmUtil.nvl(bDTO.getUpd_date()) %>
            </span> 
    </ul>
          <%} %>
	<div class="tab-pane active" role="tabpanel" id="tab-1">
            <div class="thread-list-head">
                <nav class="thread-pages">
                    <ul class="pagination">
         <%
            if(paging.isPrev()==true) { %>
               <a class="page-item page-link" href="/BandMoa/FB/BMFreeB.do?Pno=<%=paging.getStartPage()-3%>">이전</a>&nbsp;
               <%}   %>
         
         <% for(int a= paging.getStartPage(); a <= paging.getEndPage(); a++){
            if(paging.getPage()==a) { %>
               <div class='page-item page-link' style="background-color:#1abc9c;color:white;"><%=a%></div>
         <%} else {%>
               <a class='pNum' href="/BandMoa/FB/BMFreeB.do?Pno=<%=a%>">&ensp;<%=a%>&ensp;</a>
         <%}}%>
         <%
            if(paging.isNext()==true) { %>
               &nbsp;<a class="page-item page-link" href="/BandMoa/FB/BMFreeB.do?Pno=<%=paging.getEndPage()+1%>">다음</a>
         <%}   %>
         </ul>
         </nav>
         </div>
         </div>
         </div>
	
	<div>
	<ul class="nav nav-tabs">
   		<li class="nav-item"><a class="btn btn-primay" href="/BandMoa/FB/BMFreeBR.do" role="button">글작성<span class="badge badge-pill badge-primary">★</span></a></li>
    </ul>
	</div>
	</div>
	
 	</div>
 	
    </div>
    </div>
    </div>
    <script src="/js/Board/jquery.min.js"></script>
    <script src="/bootstrap/Board/js/bootstrap.min.js"></script>
</body>



</html>