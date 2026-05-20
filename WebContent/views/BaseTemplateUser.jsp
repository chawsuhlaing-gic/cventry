<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap core CSS -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title><tiles:insertAttribute name="title" ignore="true" />
</title>
<!-- CV Entry -->
  <link rel="icon" href="img/logo-img/gic_icon.png">
  <!-- Core Stylesheet -->
  <link href="css/animate.css" rel="stylesheet">
  <link href="css/classy-nav.css" rel="stylesheet">
  <link href="css/font-awesome.min.css" rel="stylesheet">
  <link href="css/style.css" rel="stylesheet">     
  <link href="scss/backtotop.css" rel="stylesheet">
  <link href="scss/footer.css" rel="stylesheet">
  <link href="scss/header.css" rel="stylesheet">
  <link href="scss/homepage.css" rel="stylesheet">
  <link href="scss/preloader.css" rel="stylesheet">
  <link href="scss/reboot.css" rel="stylesheet">
  <link href="assets/bootstrap/css/bootstrap.css" rel="stylesheet"> 
</head>
<body>
   <tiles:insertAttribute name="header" /><br/>
      
   <tiles:insertAttribute name="body" /><br/>
   
   <tiles:insertAttribute name="footer" /><br/>
</body>

<!-- ##### All Javascript Files ##### -->

  <!-- Bootstrap js -->
  <script src="assets/bootstrap/js/bootstrap.min.js"></script>
  <!-- Classynav -->
  <script src="js/classynav.js"></script>
  <!-- Sticky js -->
  <script src="js/jquery.sticky.js"></script>
  <!-- Magnific Popup js -->
  <script src="js/jquery.magnific-popup.min.js"></script>
  <!-- Scrollup js -->
  <script src="js/jquery.scrollup.min.js"></script>
  <!-- Active js -->
  <script src="js/active.js"></script>
</html>