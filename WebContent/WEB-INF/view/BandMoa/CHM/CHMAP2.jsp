<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   
   
<!doctype html>
<html lang="en">
<head>
<title>서울 공연장 검색 (내 주변에서 찾기)</title>
<meta charset="utf-8">
<meta name="viewport"
   content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,900" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript" src="/kido/js/kakao_map.js"></script>
</head>
</head>
<body style="overflow-x: hidden">

   <section class="site-hero site-hero-innerpage overlay"
      data-stellar-background-ratio="0.5"
      style="background-image: url(/img/project_img/backgroundimg7.jpg);">
      <div class="container">
         <div
            class="row align-items-center site-hero-inner justify-content-center">
            <div class="col-md-8 text-center">

               <div class="mb-5 element-animate">
                  <h1 style="text-align:center;">내 주변 공연장 찾기 (서울시)</h1>
                  <button type="button" onclick="location.href='CHMAP2.do'">내 주변에서 찾기</button>
                  <button type="button" onclick="location.href='CHMAP.do'">검색해서 찾기</button>
               </div>
            </div>
         </div>
      </div>
   </section>
   <!-- END section -->


   <section class="site-section bg-light">
      <div class="container">
         <hr>
         <div id="map" style="width: 740px; height: 595px;"></div>
        
            <p><font color="red">공연장 명을 클릭 시 빠른길찾기로 이동합니다</font></p>
         
         <div id="clickLatlng"></div>
         
         <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8099057709e6fd26e3c36a04caccd815"></script>
         <script>
         
         var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
          mapOption = { 
              center: new kakao.maps.LatLng(37.5495910448872, 126.84262217601648), // 지도의 중심좌표
              level: 11 // 지도의 확대 레벨
              // 학교 좌표 (37.5495910448872 ,126.84262217601648)
          };

      var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
   

      
         </script>
         
         <button type="button" onclick="init()" id="location">내 위치 불러오기</button>
         
         <script>
         function init()
         {
             window.navigator.geolocation.getCurrentPosition(current_position);
                         
               
         }
          
         function current_position(position)
         {
            
    
             $('#location').click(function(){
                
             let msg = "위치 정보를 불러옵니다, 확인 버튼을 눌러주세요."; 
             
             map.setLevel(5);
             
             alert(msg);
             var lat = position.coords.latitude;
             var lon = position.coords.longitude;
             var locPosition = new kakao.maps.LatLng(lat, lon) // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
            
             displayMarker(locPosition);
            
         // 지도에 마커와 인포윈도우를 표시하는 함수입니다
            function displayMarker(locPosition, message) {

                // 마커를 생성합니다
                var marker = new kakao.maps.Marker({  
                    map: map, 
                    position: locPosition
                }); 
            // 지도 중심좌표를 접속위치로 변경합니다
                map.setCenter(locPosition);    
            
             var json_url = '/json/CHALL_DATA_TABLE.json';

           $.getJSON(json_url, function (data, textStatus) {
              
               var title = null;
                var lat = null;
                var lng = null;
                
                var imageSrc = 'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png', // 마커이미지의 주소입니다
                   imageSize = new kakao.maps.Size(24, 35), // 마커이미지의 크기입니다
                   imageOption = {offset: new kakao.maps.Point(0, 0)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

                // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
                   var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption)


                $.each(data.items, function () {
                    title = this.title;
                    lat = this.lat;
                    lng = this.lng;
                    
                    console.log(title + " : " + lat + lng);
                    
                    var marker = new kakao.maps.Marker({

                        map: map, // 마커를 표시할 지도
                        image: markerImage,
                        position: new kakao.maps.LatLng(lat,lng) // 마커를 표시할 위치
                    });
                    //
                   var customOverlay = new daum.maps.CustomOverlay({
                        position: new kakao.maps.LatLng(lat, lng)
                    });

                    var content = document.createElement('div');
                    content.style.position = "relative";
                    content.style.bottom = "30px";
                    content.style.left = "25px";
                    content.style.padding = "5px";
                    content.style.borderRadius = "6px";
                    content.style.border = "1px solid #ccc";
                    content.style.borderBottom = "2px solid #ddd";
                    content.style.cssFloat = "left";
                    content.style.backgroundColor = "#fff";

                    var ourl = 'https://map.kakao.com/link/to/' + title + ', ' + lat + ', ' + lng

                    var info = document.createElement('div');
                    info.appendChild(document.createTextNode(title));
                    content.appendChild(info);
                    info.style.fontSize = "13pt";
                    info.style.fontWeight = "bold";
                    info.style.borderBottom = "1px solid #ddd";
                    info.style.textAlign = "center";
                    info.onclick = function() {
                        window.open(ourl,'_blank')
                    };

                    var closeBtn = document.createElement('div');
                    closeBtn.appendChild(document.createTextNode('공연장명 클릭'));
                    closeBtn.onclick = function() { customOverlay.setMap(null); };
                    content.appendChild(closeBtn);
                    closeBtn.style.color = "white";
                    closeBtn.style.fontWeight = "bold";
                    closeBtn.style.backgroundColor = "#FFC200";
                    closeBtn.style.textAlign = "center";
                    closeBtn.style.borderRadius = "0px 0px 4px 4px";

                    // 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
                    kakao.maps.event.addListener(marker, 'click', function () {
                        customOverlay.setMap(map);
                    });

                    customOverlay.setContent(content);
                    customOverlay.setMap(null);
                
                    
                    //
                });
            });
         }
             });
         }
 
         window.addEventListener("load", init);
 
         </script>



      </div>
   </section>
   <!-- END section -->

</body>
</html>