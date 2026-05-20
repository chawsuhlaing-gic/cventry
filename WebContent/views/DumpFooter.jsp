<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="keyword" content="">
<style>
	.contact-info a {
	text-decoration: none;
	font-weight: normal;
	color: #6c757d;
	}
</style>
</head>

<body>
	<!-- ##### Footer Area Start ##### -->
	<footer class="footer-area">
		<!-- Main Footer Area -->
		<div class="main-footer">
			<div class="container">
				<div class="row" style="margin-bottom: -40px;margin-top: 17px;">

					<!-- Single Footer Widget Area -->
					<div class="col-12 col-sm-6 col-lg-5">
						<div class="footer-widget mb-80">
							<h5 class="widget-title">Yangon Branch:</h5>
							<div class="contact-info">
								<p><i class="fa fa-map" aria-hidden="true"></i>  : <a target="_blank" href="<s:property value="dumpYgnAddressGlo"></s:property>"><s:property value="dumpYgnAddressEn"></s:property></a>
								</p>
								<p>
									<i class="fa fa-envelope" aria-hidden="true"></i> : <a href="mailto:<s:property value="dumpOfficeMail"></s:property>" ><s:property value="dumpOfficeMail"></s:property></a>
								</p>
								<p>
									<i class="fa fa-phone"></i>  :  <a href="tel:+959457086030"><s:property value="dumpYgnContactEn"></s:property></a>
								</p>
							</div>
						</div>
					</div>

					<!-- Single Footer Widget Area -->
					<div class="col-12 col-sm-6 col-lg-5">
						<div class="footer-widget mb-80">
							<h5 class="widget-title">Mandalay Branch:</h5>
							<div class="contact-info">
								<p><i class="fa fa-map"></i>  :
									<a href="<s:property value="dumpMdyAddressGlo"></s:property>" target="_blank"><s:property value="dumpMdyAddressEn"></s:property></a>
								</p>
								<p>
									<i class="fa fa-envelope" aria-hidden="true"></i> : <a href="mailto:<s:property value="dumpOfficeMail"></s:property>" ><s:property value="dumpOfficeMail"></s:property></a>
								</p>
								<p>
									<i class="fa fa-phone" aria-hidden="true"></i>  :  <a href="tel:+959423385177"><s:property value="dumpMdyContactEn"></s:property></a>
								</p>
							</div>
						</div>
					</div>

				<!-- Single Footer Widget Area -->
					<!-- <div class="col-12 col-sm-6 col-lg-2">
						<div class="footer-widget mb-80">
							<h5 class="widget-title">Social </h5>
							Footer Social Info
							<div class="footer-social-info">
								<a href="#"> <i class="fa fa-facebook" aria-hidden="true" style="border: 1px solid #3c5a99;
	                                           background-color: #3c5a99;"></i>
									<span>Facebook</span><br>
								</a> <a href="#"> <i class="fa fa-twitter" aria-hidden="true" style="border: 1px solid #1da1f2;
	                                                 background-color: #1da1f2;"></i>
									<span>Twitter</span><br>
								</a> 
							</div>
						</div>
					</div> -->

				</div>
			</div>
		</div>

		<!-- Copywrite Area  -->
		<div class="copywrite-area">
			<div class="container">
				<div class="copywrite-text">
					<div class="row align-items-center">
						<div class="col-md-6">
							<p>								
								Copyright &copy;
								<script>
									document.write(new Date().getFullYear());
								</script>
								GIC Myanmar Co., Ltd. All Rights Reserved.
							</p>
						</div>
						<div class="col-md-6"></div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- ##### Footer Area End ##### -->
</body>
</html>