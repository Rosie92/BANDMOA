<%@page import="java.util.ArrayList"%>
<%@page import="poly.util.CmmUtil"%>
<%@page import="java.util.List"%>
<%@page import="poly.dto.BMUserInfoDTO"%>
<%@page import="poly.dto.BMFBDTO"%>
<%@page import="poly.dto.BMSRDTO"%>
<%@page import="poly.dto.BMQTDTO"%>
<%@page import="poly.dto.BMMDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
    <%
  	 /** List<BMDTO> bList = (List<BMDTO>)request.getAttribute("bList");
    
    if (bList == null){
    	bList = new ArrayList<BMDTO>();
    }
    
   System.out.println("bList.size :" + bList.size()); */
    %>
     
<!DOCTYPE html>
<!--[if IE 8 ]><html class="no-js oldie ie8" lang="en"> <![endif]-->
<!--[if IE 9 ]><html class="no-js oldie ie9" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html class="no-js" lang="en"> <!--<![endif]-->
<head>

   <!--- basic page needs
   ================================================== -->
   <meta charset="utf-8">
	<title>BandMoA</title>
	<meta name="description" content="">  
	<meta name="author" content="">

   <!-- mobile specific metas
   ================================================== -->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

 	<!-- CSS
   ================================================== -->
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
   <link rel="stylesheet" href="/css/base.css">
   <link rel="stylesheet" href="/css/vendor.css">  
   <link rel="stylesheet" href="/css/main.css?after"> 
   <link rel="stylesheet" href="/css/fonts.css">  

   <!-- script
   ================================================== -->
	<script src="/js/modernizr.js"></script>
	<script src="/js/pace.min.js"></script>

   <!-- favicons
	================================================== -->
	<link rel="shortcut icon" href="/images/fav-icon.png" type="image/x-icon">
	<link rel="icon" href="/images/fav-icon.png" type="image/x-icon">

</head>

<body id="top" class="pace-done">

<div class="pace pace-inactive">
<div class="pace-progress" data-progress-text="100%" data-progress="99" style="transform: translate3d(100%, 0px, 0px);">
  <div class="pace-progress-inner"></div>
</div>
<div class="pace-activity"></div>
</div>

	<!-- header 
   ================================================== -->
   <header> 
		<a id="header-menu-trigger" href="#0" class>
		 	<span class="header-menu-text">Menu</span>
		  	<span class="header-menu-icon"></span>
		</a> 

		<nav id="menu-nav-wrap">

			<a href="#0" class="close-button" title="close"><span>Close</span></a>	

	   	<h3>BandMoA .</h3>  

			<ul class="nav-list">
				<li class="current"><a class="smoothscroll" href="#home" title="">Home</a></li>
				<li><a class="smoothscroll" href="#about" title="">Introduce</a></li>
				<li><a class="smoothscroll" href="#ConcertHallMap" title="">Concert Hall Map</a></li>
				<li><a class="smoothscroll" href="#FB" title="">Free Board</a></li>
				<li><a class="smoothscroll" href="#portfolio" title="">Study Room</a></li>
				<li><a class="smoothscroll" href="#contact" title="">Tip</a></li>
				<li><a class="smoothscroll" href="#M" title="">Member</a></li>						
			</ul>	

			<p class="sponsor-text">
				모든 밴드 뮤지션들을 위한<br> 밴드 커뮤니티 사이트 <br> <span>BandMoA</span>에 오신것을 <br>환영합니다.
			</p>			

			<ul class="header-social-list">
			<%
			if (session.getAttribute("SS_USER_ID")==null){
				
			
			%>
	         <li>
	         	<a href="/BandMoa/Login.do" style="margin-right:20px"><i class="fas fa-sign-in-alt" style="font-size:40px"></i></a>
	         </li>
	         <li>
	         <li>
	         	<a href="/BandMoa/UserMypage.do">
	         	<i class="fas fa-file-alt" style="font-size:40px"></i></a>
	         </li>
	         
	         <% }else if(session.getAttribute("SS_USER_ID").equals("Admin")){  %>
	         	<a href="/BandMoa/Logout.do" style="margin-right:20px">
	         	<i class="fas fa-sign-out-alt" style="font-size:30px"></i></a>
	         <li>
	         	<a href="/BandMoa/UserMypage.do" style="margin-right:10px"><i class="fas fa-file-alt" style="font-size:30px"></i></a>
	         </li>
	         <a href="/BandMoa/InfoList.do?Pno=1"><i class="fas fa-users-cog" style="font-size:30px"></i></a>
	         
	         <% }else{  %>
	         	<a href="/BandMoa/Logout.do" style="margin-right:40px"><i class="fas fa-sign-out-alt" style="font-size:40px"></i></a>
	         	<a href="/BandMoa/UserMypage.do"><i class="fas fa-file-alt" style="font-size:40px"></i></a>
	         <% }%>
	         
	         
	      </ul>		

		</nav>  <!-- end #menu-nav-wrap -->

	</header> <!-- end header -->  



   <!-- home
   ================================================== -->

   <section id="home">

   	<div class="overlay"></div>

   	<div class="home-content-table">	
		   <div class="home-content-tablecell">
		   	<div class="row">
		   		<div class="col-twelve">		   			
			  		
			  				<h3 class="animate-intro">We Are BandMoA .</h3>
				  			<h1 class="animate-intro">
							Welcome to BandMoA  <br>
							 a place for all musicians.
				  			</h1>	

				  			<div class="more animate-intro">
				  				<a class="smoothscroll button stroke" href="#about">
				  					BandMoA ?
				  				</a>
				  			</div>							

			  		</div> <!-- end col-twelve --> 
		   	</div> <!-- end row --> 
		   </div> <!-- end home-content-tablecell --> 		   
		</div> <!-- end home-content-table -->

		<div class="scrolldown">
			<a href="#about" class="scroll-icon smoothscroll">		
		   	Scroll Down		   	
		   	<i class="fa fa-long-arrow-right" aria-hidden="true"></i>
			</a>
		</div>			
   
   </section> <!-- end home -->
   


   <!-- Introduce
   ================================================== -->
   <section id="about">

   	<div class="row about-wrap">
   		<div class="col-full">

   			<div class="about-profile-bg"></div>

				<div class="intro">
					<h3 class="animate-this">BandMoA ?</h3>
	   			<p class="lead animate-this"><span>BandMoA</span> 는 전국의 모든 뮤지션들을 위한 <br>커뮤니티 공간입니다.</p>
	   			<h6>
	   			자유게시판, 배움공간, 팁, 구인구직, 공연장지도 등의 메뉴를 통해 활발한 정보공유가 이루어지길 바랍니다. </h6>
				</div>   

   		</div> <!-- end col-full  -->
   	</div> <!-- end about-wrap  -->

   </section> <!-- end about -->

   
 <!-- 콘서트홀맵
   ================================================== -->
   <section id="ConcertHallMap">

   	<div class="overlay"></div>
   	<div class="gradient-overlay"></div>
   	
   	<div class="row narrow section-intro with-bottom-sep animate-this">
   		<div class="col-full">
   			
   				<h3>Concert Hall Map</h3>
   			   <h1>공연장 정보</h1>
   			
   			   <p class="lead">현 위치 주변의 공연장을 검색하거나, 카카오맵으로 검색합니다. </p>
<iframe src="/BandMoa/CHM/CHMAP.do" width="100%" height="800px" name="FRmain" id="FRmain"  style="background-color: white;">

</iframe>
   			
   	   </div> <!-- end col-full -->
   	</div> <!-- end row -->

   </section> <!-- end ConcertHallMap -->


   <!-- Free Board
   ================================================== -->
   <section id="FB">
   <section id="home">
<!-- services -->

   
   	<div class="row narrow section-intro animate-this">
   		<div class="col-full" style="margin-top:50px; margin-bottom:50px;">
		<div style="background-color: white; height:180px;'">
   			   <h3 style="color:gray">Free Board</h3>
   			   <h1>자유 게시판</h1>
   			   <p class="lead">어떤 이야기든 좋습니다. 의견을 자유롭게 주고 받는 공간입니다.</p>
   		</div>
<iframe src="/BandMoa/FB/BMFreeB.do?Pno=1" width="100%" height="750px" name="FRmain" id="FRmain"  style="background-color: white; overflow-x: hidden" scrolling="yes">

</iframe>
	   </div> <!-- end col-full -->
   	</div> <!-- end row -->
   </section> <!-- end services -->
</section>

   <!-- Study Room
   ================================================== -->
   <section id="portfolio">
   	
   	<div class="intro-wrap">

   		<div class="row narrow section-intro with-bottom-sep animate-this fadeInUp animated">
	   		<div class="col-twelve">
	   			<h3>Study Room</h3>
		   		<h1>배움 공간</h1>  			
		   		
		   		<p class="lead">실력 향상에 도움이 되는 영상 및 글이 업로드 되는 공간입니다.<br>기타, 드럼, 베이스, 보컬 등 모든 주제가 허용됩니다.</p>	   			
	   		</div>   		
	   	</div> <!-- end row section-intro -->   		

   	</div> <!-- end intro-wrap -->   	

<div class="row portfolio-content">
   		<div class="col-twelve">
   			<div id="folio-wrap" class="bricks-wrapper" style="position: relative; height: 1836.95px;">					

   				<div class="brick folio-item" style="position: absolute; left: 0px; top: 0px;">
	               <div class="item-wrap animate-this fadeInUp animated" data-src="/images/portfolio/gallery/g-shutterbug.jpg" data-sub-html="#01"> 	
	                  <a href="#" class="overlay">
	                  	<img src="/images/portfolio/shutterbug.jpg" alt="Skaterboy">	                     
	                     <div class="item-text">
	                     	<span class="folio-types">
		     					      Web Development
		     					   </span>
		     					   <h3 class="folio-title">Shutterbug</h3>	     					   
		     					</div>                                        
	                  </a>
	                  <a href="https://www.behance.net/" class="details-link" title="details">
	                  	<i class="icon-link"></i>
	                  </a>
	               </div> <!-- end item-wrap -->
						
						<div id="01" class="hide">
							<h4>Shutterbug</h4>
							<p>Lorem ipsum Dolor deserunt labore sint officia. Magna et aute enim proident tempor sunt quis nulla voluptate fugiat velit. <a href="https://www.behance.net/">Details</a></p>
						</div>
	        		</div> <!-- end folio-item -->

	        		<div class="brick folio-item" style="position: absolute; left: 565px; top: 0px;">
	               <div class="item-wrap animate-this fadeInUp animated" data-src="/images/portfolio/gallery/g-yellowwall.jpg" data-sub-html="#02"> 	
	                  <a href="#" class="overlay">
	                  	<img src="/images/portfolio/yellowwall.jpg" alt="Shutterbug">	                     
	                     <div class="item-text">
	                     	<span class="folio-types">
		     					      Marketing
		     					   </span>
		     					   <h3 class="folio-title">Yellow Wall</h3>  	     					   
		     					</div>                                        
	                  </a>
	                  <a href="https://www.behance.net/" class="details-link" title="details">
	                  	<i class="icon-link"></i>
	                  </a>
	               </div> <!-- end item-wrap -->

	               <div id="02" class="hide">
							<h4>Yellow Wall</h4>
							<p>Lorem ipsum Dolor deserunt labore sint officia. Magna et aute enim proident tempor sunt quis nulla voluptate fugiat velit. <a href="https://www.behance.net/">Details</a></p>
						</div>	               
	        		</div> <!-- end folio-item -->

	        		<div class="brick folio-item" style="position: absolute; left: 565px; top: 565px;">
	               <div class="item-wrap animate-this fadeInUp animated" data-src="/images/portfolio/gallery/g-architecture.jpg" data-sub-html="#03">   	
	                  <a href="#" class="overlay">
	                  	<img src="/images/portfolio/architecture.jpg" alt="Explore">	                     
	                     <div class="item-text">	                     		     					    
		     					   <span class="folio-types">
		     					      Web Design
		     					   </span>
		     					   <h3 class="folio-title">Architecture</h3>
		     					</div>                                        
	                  </a>
	                  <a href="https://www.behance.net/" class="details-link" title="details">
	                  	<i class="icon-link"></i>
	                  </a>
	               </div> <!-- end item-wrap -->	

	               <div id="03" class="hide">
							<h4>Architecture</h4>
							<p>Lorem ipsum Dolor deserunt labore sint officia. Magna et aute enim proident tempor sunt quis nulla voluptate fugiat velit. <a href="https://www.behance.net/">Details</a></p>
						</div>               
	        		</div> <!-- end folio-item -->

					<div class="brick folio-item" style="position: absolute; left: 0px; top: 706px;">
	               <div class="item-wrap animate-this fadeInUp animated" data-src="/images/portfolio/gallery/g-minimalismo.jpg" data-sub-html="#04">
	                  <a href="#" class="overlay">
	                  	<img src="/images/portfolio/minimalismo.jpg" alt="Minimalismo">	                     
	                     <div class="item-text">	     					    
		     					   <span class="folio-types">
		     					      Web Design
		     					   </span>
		     					   <h3 class="folio-title">Minimalismo</h3>
		     					</div>                                        
	                  </a>
	                  <a href="https://www.behance.net/" class="details-link" title="details">
	                  	<i class="icon-link"></i>
	                  </a>
	               </div> <!-- end item-wrap -->

	               <div id="04" class="hide">
							<h4>Minimalismo</h4>
							<p>Lorem ipsum Dolor deserunt labore sint officia. Magna et aute enim proident tempor sunt quis nulla voluptate fugiat velit. <a href="https://www.behance.net/">Details</a></p>
						</div>  	               
	        		</div> <!-- end folio-item -->

					<div class="brick folio-item" style="position: absolute; left: 565px; top: 1130px;">
	               <div class="item-wrap animate-this fadeInUp animated" data-src="/images/portfolio/gallery/g-skaterboy.jpg" data-sub-html="#05">  	
	                  <a href="#" class="overlay">
	                  	<img src="/images/portfolio/skaterboy.jpg" alt="Bicycle">	                     
	                     <div class="item-text">	     					    
		     					   <span class="folio-types">
		     					      Branding
		     					   </span>
		     					   <h3 class="folio-title">Skaterboy</h3>
		     					</div>                                        
	                  </a>
	                  <a href="https://www.behance.net/" class="details-link" title="details">
	                  	<i class="icon-link"></i>
	                  </a>
	               </div> <!-- end item-wrap -->

	               <div id="05" class="hide">
							<h4>Skaterboy</h4>
							<p>Lorem ipsum Dolor deserunt labore sint officia. Magna et aute enim proident tempor sunt quis nulla voluptate fugiat velit. <a href="https://www.behance.net/">Details</a></p>
						</div>	               
	        		</div> <!-- end folio-item -->
	           
					<div class="brick folio-item" style="position: absolute; left: 0px; top: 1271px;">
	               <div class="item-wrap animate-this fadeInUp animated" data-src="/images/portfolio/gallery/g-salad.jpg" data-sub-html="#06">     	
	                  <a href="#" class="overlay">
	                  	<img src="/images/portfolio/salad.jpg" alt="Salad">	                     
	                     <div class="item-text">	     					    
		     					   <span class="folio-types">
		     					      Branding
		     					   </span>
		     					   <h3 class="folio-title">Salad</h3>
		     					</div>                                        
	                  </a>
	                  <a href="https://www.behance.net/" class="details-link" title="details">
	                  	<i class="icon-link"></i>
	                  </a>
	               </div> <!-- end item-wrap -->

	               <div id="06" class="hide">
							<h4>Salad</h4>
							<p>Lorem ipsum Dolor deserunt labore sint officia. Magna et aute enim proident tempor sunt quis nulla voluptate fugiat velit. <a href="www.behance.net">Details</a></p>
						</div>	               
	        		</div> <!-- end folio-item -->   				

   			</div> <!-- end folio-wrap -->
   		</div> <!-- end twelve -->
   	</div>
   </section>  <!-- end portfolio -->


   <!-- 게시판 들어가는 곳
   ================================================== -->
   <section id="home">
   <!-- testimonials -->

   
   	<div class="row narrow section-intro animate-this">
   		<div class="col-full">
			
<iframe src="/BandMoa/SR/BMSrB.do?Pno=1" width="100%" height="800px" name="FRmain" id="FRmain" style="border-color: black; background-color: wihte; overflow-x: hidden; margin-top:80px;" scrolling="yes">

</iframe>

	   </div> <!-- end col-full -->
   	</div> <!-- end row -->
   </section> <!-- end services -->


	<!-- 질문 & 팁
   ================================================== -->
   <section id="contact">

      <div class="overlay"></div>

		<div class="row narrow section-intro with-bottom-sep animate-this">
   		<div class="col-twelve">
   			<h3>Tip</h3>
   			<h1>팁</h1>

   			<p class="lead">많은 이들에게 도움이 될만한 팁을 올리는 게시판입니다.</p>
   		
<iframe src="/BandMoa/QT/BMqtB.do?Pno=1" width="100%" height="800px" name="FRmain" id="FRmain" style="background-color: white; overflow-x: hidden" scrolling="yes">

</iframe>
   		
   		</div> 
   	</div> <!-- end section-intro -->

	</section> <!-- end contact -->

   <!-- 구인구직
   ================================================== -->
   <section id="M">
   <section id="home">
<!-- MemberFind -->

   	<div class="gradient-overlay"></div>
   	
   	<div class="row narrow section-intro with-bottom-sep animate-this">
   	<div class="col-full" style="margin-top:50px; margin-bottom:50px;">
   			<div style="background-color: white; height:180px;'">
   			   <h3 style="color:gray">Member</h3>
   			   <h1>구인 &amp; 구직</h1>
   			   <p class="lead2">밴드 멤버를 모집하는 게시판입니다. 좋은 인연을 찾으실 수 있길 바랍니다.</p>
   			</div>
<iframe src="/BandMoa/M/BMmB.do?Pno=1" width="100%" height="770px" name="FRmain" id="FRmain" style="background-color: white; overflow-x: hidden" scrolling="yes">

</iframe>
   			
   	   </div> <!-- end col-full -->
   	</div> <!-- end row -->

   </section> <!-- end MemberFind -->
</section>

	<!-- footer
   ================================================== -->
	<footer>
     	<div class="footer-main">

   		<div class="row">  

	      	<div class="col-five tab-full footer-about">       

	            <h4 class="h05"><font color="black">WHAT IS A BAND?</font></h4>

	            <p><font color="black">
What band?<br>
Different people get together and walk around looking at one place.<br>
Reckless and immature people beside me but to look good ... Moments so happy that the band to sing and play with them.<br>
I sometimes want to give up when I'm tired, but I don't know how lucky I am to have a colleague who asks me to come with you every time.<br>
Thanks to the still can dream too happy.<br>
So we good band.<br>
There are some of you who like Band. Really...So good. -Daybrake曰 -</font></p>	            

		      </div> <!-- end footer-about -->

	      	<div class="col-three tab-full footer-social">

	      		<h4 class="h05"><font color="black">Thanks for</font></h4>

	      		<ul class="list-links">
	      		<li><a href="#"><font color="black">leesh</font></a></li>
	      			<li><a href="#"><font color="black">Roze</font></a></li>
						<li><a href="#"><font color="black">JotPaul</font></a></li>
						<li><a href="#"><font color="black">hm40502</font></a></li>	
						<li><a href="#"><font color="black">HILNAN</font></a></li>
						<li><a href="#"><font color="black">Pangdolls</font></a></li>
						<li><a href="#"><font color="black">RuRu</font></a></li>
						<li><a href="#"><font color="black">HolySpiritGod</font></a></li>	
						<li><a href="#"><font color="black">SadFrog</font></a></li>	
						<li><a href="#"><font color="black">Avengers</font></a></li>
						<li><a href="#"><font color="black">leehan</font></a></li>	
						<li><a href="#"><font color="black">BattleFinger</font></a></li>	
						<li><a href="#"><font color="black">Lunus</font></a></li>
						<li><a href="#"><font color="black">YuaDiary</font></a></li>	
						<li><a href="#"><font color="black">2sTar_UnLock</font></a></li>
						<li><a href="#"><font color="black">K8vin</font></a></li>	
						<li><a href="#"><font color="black">KERORO</font></a></li>	
						<li><a href="#"><font color="black">Mr.CorHes</font></a></li>	
						<li><a href="#"><font color="black">EngineZete</font></a></li>								
					</ul>

	      	</div> <!-- end footer-social -->  

	      	<div class="col-four tab-full footer-subscribe end">

	      		<h4 class="h05"><font color="black">Opinion & Questions</font></h4>

	      		<p><font color="black">
	      		For comments or questions, 
	      		please send an email to 
	      		<font color="red">beautifuRoze@naver.com</font><br>
	      		I'll prompt response.</font></p>

	      	<!-- 	<div class="subscribe-form">
	      	
	      			<form id="mc-form" class="group" novalidate="true">

							<input type="email" value="" name="dEmail" class="email" id="mc-email" placeholder="type email" required=""> 
	   		
			   			<input type="submit" name="subscribe" >
			   			<button><i class="icon-mail"></i></button>
		   	
		   				<label for="mc-email" class="subscribe-message"></label>
			
						</form>

	      		</div> -->
	      	           	
	      	</div> <!-- end footer-subscribe -->      	    

	      </div> <!-- end row -->

   	</div> <!-- end footer-main -->

   	<div class="footer-bottom">

      	<div class="row">

      		<div class="col-twelve">
	      		<div class="copyright">
		         	<span><font color="black">© Copyright beautifulRoze 2019.</font></span> 
		         	<span><font color="black">Design by <a href="http://www.styleshout.com/">styleshout</a></font></span>		         	
		         </div>		               
	      	</div>

      	</div>   	

      </div> <!-- end footer-bottom -->

      <div id="go-top">
		   <a class="smoothscroll" title="Back to Top" href="#top">
		   	<i class="fa fa-long-arrow-up" aria-hidden="true"></i>
		   </a>
		</div>		
   </footer>

   <div id="preloader"> 
    	<div id="loader"></div>
   </div> 

   <!-- Java Script
   ================================================== --> 
   <script src="/js/jquery-2.1.3.min.js"></script>
   <script src="/js/plugins.js"></script>
   <script src="/js/main.js"></script>

</body>

</html>