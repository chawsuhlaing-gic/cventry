<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="icon" href="img/logo-img/gic_icon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ログイン</title>
<style type="text/css">
input[type=number]::-webkit-inner-spin-button, input[type=number]::-webkit-outer-spin-button
	{
	-webkit-appearance: none;
	margin: 0;
}

input[type=number] {
	-moz-appearance: textfield;
}
</style>
<link rel="stylesheet" type="text/css"
	href="login/css/fonts/iconic/css/material-design-iconic-font.min.css">
<link rel="stylesheet" type="text/css" href="login/css/login/util.css">
<link rel="stylesheet" type="text/css" href="login/css/login/main.css">

<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>

</head>
<body onLoad="noBack();" onpageshow="if(event.persisted)noBack();">
	<s:if test="hasActionErrors()">
		<div class="alert alert-danger alert-dismissible" align="center">			
				<strong><s:actionerror /></strong>
		</div>
	</s:if>

	<div class="limiter">
		<div class="container-login100"
			style="background-image: url('img/login-img/loginbg.png');">
			<div class="wrap-login100">
				<form action="checkAdminlogin" id="adminLoginForm"
					class="login100-form validate-form" method="POST">

					<span class="login100-form-logo"> <img
						src="img/login-img/GIC_myanmar_CI.png" alt="IMG">
					</span> <span class="login100-form-title p-b-34 p-t-27"> <img
						src="img/login-img/gicLogo.png" alt="IMG"><br> <br>
					</span>
					
					<div class="wrap-input100 validate-input"
						data-validate="Enter username">
						<s:fielderror fieldName="username"></s:fielderror>
						<input class="input100" type="number" name="username"
							placeholder="社員番号" id="社員番号"
							onkeypress="return onlyNumbers();"
							onmousedown="return onlyNumbers();" required="" 
							oninvalid="InvalidText(this); onlyNumbers();"
							oninput="InvalidText(this);"> 
						<span class="focus-input100" data-placeholder="&#xf207;"></span>
					</div>

					<div class="wrap-input100 validate-input"
						data-validate="Enter password">
						<s:fielderror fieldName="password"></s:fielderror>
						<input class="input100" type="password" name="password" id="パスワード"
							placeholder="パスワード" required="" oninvalid="InvalidText(this);">
						<span class="focus-input100" data-placeholder="&#xf191;"></span>
					</div>

					<div class="container-login100-form-btn">
						<button type="submit" class="login100-form-btn" id="btnLogin">
							ログイン</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="js/login/animsition.min.js"></script>
	<%-- 	<script src="js/login/main.js"></script> --%>


	<script language="JavaScript">
		$(document).ready(function() {
			$('#number').bind("cut copy paste drag drop", function(e) {
				e.preventDefault();
			});
		});

		function onlyNumbers(evt) {
			var charCode = (evt.which) ? evt.which : evt.keyCode;
			if (charCode > 31 && ((charCode < 48) || (charCode > 57)))
				return false;
			return true;

		}
	</script>
	<script>
		function InvalidText(textbox) {
			if (textbox.value == '') {
				textbox.setCustomValidity(textbox.id + ' を入力してください。');
			} else {
				textbox.setCustomValidity('');
			}
			return true;
		}
	</script>

</body>
</html>