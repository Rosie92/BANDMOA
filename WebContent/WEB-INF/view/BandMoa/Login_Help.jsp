<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>BandMoa</title>
<head>
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/fonts/ionicons.min.css">
    <link rel="stylesheet" href="/css/Login-Form-Dark.css">
    <link rel="stylesheet" href="/css/styles.css">
</head>
</head>
<body>
    <div class="login-dark">
        <form method="post">
            <h2 class="sr-only">Login_Help</h2>
            <div class="illustration"><img src="/img/Login_key.png"></div>
            <div class="form-group"><a href="/BandMoa/Login_IdFind.do" class="btn btn-primary btn-block">아이디 찾기</a></div>
            <div class="form-group"><a href="/BandMoa/Login_PwFind.do" class="btn btn-primary btn-block">비밀번호 찾기</a></div>
            <div class="form-group"><a href="/BandMoa/Login_Join.do" class="btn btn-primary btn-block">회원가입</a></div>
       		<a class="forgot" href="/BandMoa/Login.do">로그인 화면으로 돌아가기</a>
        </form>
    </div>
    <script src="/js/jquery.min.js"></script>
    <script src="/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>