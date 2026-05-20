<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="jp">
<head>
  <meta charset="UTF-8">
  <meta name="description" content="">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <!-- Title -->
  <title>GIC CVENTRY&amp; | Home</title>
  <link href="css/bootstrap.min.css" rel="stylesheet">  
  <link href="scss/mixins/_flex.css" rel="stylesheet">
  <link href="scss/utilities/_color.css" rel="stylesheet">
  <link href="scss/buttons.css" rel="stylesheet">
  <link href="scss/contact.css" rel="stylesheet">
  <link href="scss/hero.css" rel="stylesheet">
  <link href="scss/homepage.css" rel="stylesheet">
  <link href="scss/preloader.css" rel="stylesheet">
  <link href="scss/spacing.css" rel="stylesheet">
  <link href="scss/style.css" rel="stylesheet">
  <link href="src/customizeStyle.css" rel="stylesheet">
  <link rel="stylesheet" href='https://mmwebfonts.comquas.com/fonts/?font=zawgyi' />
</head>
<style>
.zawgyi{
	font-family:Zawgyi-One;
}
.unicode{
	font-family:Myanmar3,Yunghkio,'Masterpiece Uni Sans';
}
</style>
<body>
  <!-- Preloader -->
  <div class="preloader d-flex align-items-center justify-content-center">
    <div class="spinner"></div>
  </div>
  <!-- ##### Hero Area Start ##### -->
  <div class="hero-area">
      <!-- Home Page-->
      <div class="mmtitle">
         <div class="container">
             <div class="row">
                 <div class="col-md-12 col-sm-12">
                  <h2 class="jb-header-eng"><u><s:property value="dumpHeaderFirstLine"></s:property></u></h2>
                 </div>
             </div>
             <div class="row">
                <div class="col-md-2 col-sm-2"></div>
                <div class="col-md-8 col-sm-8">
                   <p class="unicode mmh1 line"><s:property value="dumpHeaderSecondLine"></s:property></p>
                </div>
                <div class="col-md-2 col-sm-2"></div>
             </div> 
             <!-- <img src="img/core-img/title.PNG"> -->
         </div>
      <hr> 
      </div>   
     <div class="jf-bg">
            <div class="container">
                <h4 class=" unicode jf-title mmh1">Job Fair ကျင်းပရန်နေရာ</h4>
                <div class="row">
                    <div class="col-md-6 col-sm-6">
                        <div class="jobfairTable green">
                            <div class="jobfairTable-header">
                                <h3 class="title">Yangon Job Fair</h3>
                            </div>
                            <ul class="jobfair-content">
                                <li><s:property value="dumpExamTimeYear"></s:property>&nbsp;
                                	<s:property value="dumpYgnExamTimeMonth"/>&nbsp;
                                	<s:property value="dumpYgnExamTimeDay"/><sup><s:property value="dumpYgnExamTimeAlias"/></sup>&nbsp;
                                	<s:property value="dumpYgnExamTimeDate"/>
                                </li>
                                <li><s:property value="dumpYgnExamPlace"></s:property></li>
                                <li>Yangon</li>
                               
                            </ul>
                             <a href="CVFormTmp" class="jobfairTable-signup unicode">လျှောက်ထားရန်</a>  <!--for CVEntry Open  -->
                            <!--  <a href="CVForm" class="jobfairTable-signup unicode">လျှောက်ထားရန်</a>--><!--for CVEntry Close  -->
                        </div>
                    </div>
                    <div class="col-md-6 col-sm-6">
                        <div class="jobfairTable orange">
                            <div class="jobfairTable-header">
                                <h3 class="title">Mandalay Job Fair</h3>
                            </div>
                            <ul class="jobfair-content">
                                <li><s:property value="dumpExamTimeYear"></s:property>&nbsp;
                                	<s:property value="dumpMdyExamTimeMonth"/>&nbsp;
                                	<s:property value="dumpMdyExamTimeDay"/><sup><s:property value="dumpMdyExamTimeAlias"/></sup>&nbsp;
                                	<s:property value="dumpMdyExamTimeDate"/>
                                </li>
                                <li><s:property value="dumpMdyExamPlace"></s:property></li>
                                <li>Mandalay</li>
                            </ul>
                            <a href="CVFormTmp" class="jobfairTable-signup  unicode">လျှောက်ထားရန်</a><!-- for CVEntry Open -->
                            <!-- <a href="CVForm" class="jobfairTable-signup  unicode">လျှောက်ထားရန်</a>--><!-- for CVEntry Close -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr>
  </div>
  <!-- ##### Hero Area End ##### -->
  <div class="demo9">
            <div class="container">
                <h4 class=" unicode jf-title mmh1">
                                                                    လျှောက်လွှာတင်ခြင်း                                    <s:property value="dumpApliStartDate"></s:property> <img src="img/core-img/dash.png" width="20px" /> <s:property value="dumpApliEndDate"></s:property> </h4>
                <div class="row">
                    <div class="col-md-6 col-sm-6">
                <p class="unicode" style="font-size:1.3em;">
                 ■ Online မှလျှောက်ထားခြင်း 
                 	<br>အောက်ပါလခ့်မှ ၀င်ရောက်လျှောက်ထားနိုင်ပါသည်။ <br>
                   <!-- <a href="http://www.gicfair.com/CVEntry/CVFormTmp"> http://www.gicfair.com/CVEntry/CVFormTmp</a><br> --><!-- for CVEntry Open -->
                   <a href="CVForm"> http://www.gicfair.com/CVEntry/CVFormTmp</a><br><!-- for CVEntry Close -->
                 ■ GIC ရုံးသို့တိုက်ရိုက်လျှောက်ထားခြင်း           <br><br>
                                                   ✪ GIC Myanmar Co., Ltd.(Yangon)<br>
                            <s:property value="dumpYgnAddressMm"></s:property><br>
                                                                                             ဖုန်း  - <s:property value="dumpYgnContactMm"></s:property><br>
                   <font color="red">(စနေ၊တနင်္ဂနွေနှင့် အစိုးရပိတ်ရက်များ ရုံးပိတ်ပါသည်။)</font> 
                  <br>                                                                                                             
                                                                              
                                                    ✪ GIC Myanmar Co., Ltd.(Mandalay) <br>                           
                          	<s:property value="dumpMdyAddressMm"></s:property><br>
                                                                                           ဖုန်း  - <s:property value="dumpMdyContactMm"></s:property><br>
                    <font color="red">(စနေ၊တနင်္ဂနွေနှင့် အစိုးရပိတ်ရက်များ ရုံးပိတ်ပါသည်။)</font>  
                    <br>                                                                              
                  </p>
                    </div>
                    <div class="col-md-6 col-sm-6">
                        <img src="img/core-img/Requirement.png" > 
                    </div>
                </div>
                <!-- <h4 class="unicode jf-title mmh1" style="color:red;">※အခမ်းအနားကျင်းပသည့်နေ့တွင်လည်း လျှောက်လွှာများလက်ခံပေးပါမည်။ </h4> -->
            </div>
  </div>

 <!-- ##### All Javascript Files ##### -->
  <!-- jquery 2.2.4  -->
  <script src="js/jquery.min.js"></script>
  <!-- Bootstrap js -->
  <script src="js/bootstrap.min.js"></script>
  <!-- Wow js -->
  <script src="js/wow.min.js"></script>
</body>

</html>