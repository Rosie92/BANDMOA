<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>BandMoa</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(function() { //window.onload

		var submit = 0;
		console.log(submit);

		var reId = /^[a-zA-Z0-9]{4,12}$/; // userpw
		var rePw = /^[a-zA-Z0-9]{4,12}$/; // userid
		var reMail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		var reTel = /^\d{3}\d{3,4}\d{4}$/;
		var reName = /^[ㄱ-ㅎㅏ-ㅣ가-힣]{2,4}$/;

		$("#submit").click(function() {

			//userid 를 param.
			var user_id = $("#user_id").val();
			console.log(user_id);

			if (user_id == "") {
				alert("아이디를 입력해 주세요.");
				return false;

			} 
			else if (!reId.test($("#user_id").val())) {
				alert("아이디 형식이 옳지 않습니다 (4~12글자의 영문+숫자)");
				$("#user_pw").focus();
				return false;
			}
			else if ($("#user_pw").val() == "") {
				alert("비밀번호를 입력해주세요");
				$("#user_pw").focus();
				return false;

			} else if (!rePw.test($("#user_pw").val())) {
				alert("패스워드는 4~12자늬 영문 대소문자와 숫자로만 입력하세요");
				$("#user_pw").focus();
				return false;

			} else if ($("#user_name").val() == "") {
				alert("이름을 입력해주세요");
				$("#user_name").focus();
				return false;
			}
			else if (!reName.test($("#user_name").val())) {
				alert("이름 형식이 옳지 않습니다 (2~4글자의 한글)");
				$("#user_name").focus();
				return false;
			}

			else if ($("#user_mail").val() == "") {
				alert("메일을 입력해주세요");
				$("#user_name").focus();
				return false;
			}

			else if (!reMail.test($("#user_mail").val())) {
				alert("메일 형식이 옳지 않습니다 (아이디@email.com)");
				$("#user_mail").focus();
				return false;
			}
			else if ($("#user_tel").val() == "") {
				alert("전화번호를 입력해주세요");
				$("#user_name").focus();
				return false;
			}
			else if (!reTel.test($("#user_tel").val())) {
				alert("전화번호 형식이 옳지 않습니다. (01012345678)");
				$("#user_tel").focus();
				return false;
			}

			$.ajax({
				type : 'post',
				data : {'user_id': user_id},
				url : "/idCheck.do",
				success : function(data) {
					if (data > 0) {

						alert("아이디가 존재합니다. 다른 아이디를 입력해주세요.");
						$("#user_id").focus();

					} else {
						alert("사용가능한 아이디입니다.");
						$("#user_id").focus();
						//아이디가 중복하지 않으면  submit = 1
						
						if(confirm("밴드모아에 가입하시겠습니까?")){
							//유효성 검사가 끝나면 아이디 f를 찾아서 액션을 실행해라.
							$('#f').submit();	
						} else {
							return false;
						}
					}
				},
				error : function(error) {
					alert("error : " + error);
				}
			});

			

		});

	})
</script>
<head>
<link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/fonts/ionicons.min.css">
<link rel="stylesheet" href="/css/Login-Form-Dark.css">
<link rel="stylesheet" href="/css/styles.css">
</head>
<body>
	<div class="login-dark">
		<form name="f" id="f" method="post"
			action="/BandMoa/insertLogin_Join.do">
			<h2 class="sr-only">Join Form</h2>
			<div class="form-group">
				<input class="form-control" type="text" name="user_id" id="user_id"
					placeholder="Id">
			</div>
			<div class="form-group">
				<input class="form-control" type="password" name="user_pw"
					id="user_pw" placeholder="Password">
			</div>
			<div class="form-group">
				<input class="form-control" type="text" name="user_name"
					id="user_name" placeholder="name">
			</div>
			<div class="form-group">
				<input class="form-control" type="text" name="user_mail"
					id="user_mail" placeholder="email">
			</div>
			<div class="form-group">
				<input class="form-control" type="text" name="user_tel"
					id="user_tel" placeholder="tel">
			</div>

			<div class="form-group" id="submit">
				<!-- submit이 input에 들어가면 유효성 검사가 실행이X -->
				<input class="btn btn-primary btn-block" readonly="readonly"
					style="cursor: pointer;" value="가입신청">
			</div>

			<a class="forgot" href="/BandMoa/Login_Help.do">돌아가기</a><br> <a
				class="forgot" href="/BandMoa/BMIndex.do">BandMoa 홈으로 돌아가기</a>
		</form>

	</div>
	<script src="/js/jquery.min.js"></script>
	<script src="/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>