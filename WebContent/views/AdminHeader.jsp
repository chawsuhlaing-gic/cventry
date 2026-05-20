<%@page import="mm.com.gic.ces.application.model.RoleSetting"%>
<%@page import="java.util.Map"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="keyword" content="">
<link rel="icon" href="img/logo-img/gic_icon.png">
<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>
</head>
<body onLoad="noBack();" onpageshow="if(event.persisted)noBack();"
	onUnload="">

  <!-- ##### Header Area Start ##### -->
  <header class="header-area">
    <!-- Navbar Area -->
    <div class="famie-main-menu">
      <div class="classy-nav-container breakpoint-off" style="background-color:#92bed4 !important;">
        <div class="container">
          <!-- Menu -->
          <nav class="classy-navbar justify-content-between" id="famieNav">
            <!-- Nav Brand -->
            <a href="logoutAction" class="nav-brand">
            <img src="img/logo-img/gic_logo.jpg" style="content: url(img/logo-img/GIC_logo.png);"></a>
            <!-- Navbar Toggler -->
            <div class="classy-navbar-toggler">
              <span class="navbarToggler"><span></span><span></span><span></span></span>
            </div>
            <!-- Menu -->
            <div class="classy-menu">
              <!-- Close Button -->
              <div class="classycloseIcon">
                <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
              </div>
              <!-- Navbar Start -->
              <div class="classynav">
                <ul>
                  <%
					Map<String, Object> mapSession = ActionContext.getContext().getSession();
					RoleSetting roleSetting = (RoleSetting) mapSession.get("loggedInadmin");
					String rolePermission = roleSetting.getRole_Permission();
					if (roleSetting != null) {
								if (rolePermission.contains("履歴書管理")) {
				   %>
                  <li class="active"><a href="#">履歴書管理</a>
                    <ul class="dropdown">
                      <s:url id="registerManagement" action="registerManagement" />
					  <s:url id="getApplicantList" action="getApplicantListAction" />
					  <s:url id="appList" action="ApplicantList" />
                      <li><s:a href="%{registerManagement}">履歴書情報一覧</s:a></li>
                      <li><s:a href="%{getApplicantList}">履歴書出力</s:a></li>
                      <li><s:a href="%{appList}">受験番号出力</s:a></li>
                    </ul>
                  </li>
                  <%}if (rolePermission.contains("メール送信")) {%>
                  <s:url id="SendMail" action="SendMail" />
				  <li><s:a href="%{SendMail}">メール送信</s:a></li>
				  <%}if (rolePermission.contains("受験者管理")) {%>
                  <li><a href="#">受験者管理 </a>
                    <ul class="dropdown">
                      <s:url id="AttendedApplicantDecision" action="AttendedApplicantDecision" />
					  <s:url id="examineeReg" action="ExamineeRegistration" />
					  <s:url id="examineeInfo" action="ExportExamineeInfo" />
                      <li><s:a href="%{AttendedApplicantDecision}">受験者決定</s:a></li>
                      <li><s:a href="%{examineeReg}">受験者情報登録</s:a></li>
                      <li><s:a href="%{examineeInfo}">会社別受験者情報照会</s:a></li>
                    </ul>
                  </li>
                  <%}if (rolePermission.contains("合格者管理")) {%>
                  <li><a href="#">合格者管理</a>
                    <ul class="dropdown">
                      <s:url id="SuccessfulApplicantDecision" action="SuccessfulApplicantDecision" />
					  <s:url id="intervieweeReg" action="IntervieweeRegistration" />
					  <s:url id="passExamineeInfo" action="ExportPassExamineeInfo" />
                      <li><s:a href="%{SuccessfulApplicantDecision}">会社別合格者決定</s:a></li>
                      <li><s:a href="%{intervieweeReg}">会社別合格者情報登録</s:a></li>
                      <li><s:a href="%{passExamineeInfo}">会社別合格者照会</s:a></li>
                    </ul>
                  </li>
                  <%} if (rolePermission.contains("採用者管理")) { %>
                  <li><a href="#">採用者管理</a>
                    <ul class="dropdown">
                      <s:url id="SuccessfulInterviewerDecision" action="SuccessfulInterviewerDecision" />
					  <s:url id="employeeInfo" action="ExportEmployeeInfo" />
					  <s:url id="unemployeeInfo" action="ExportUnemployeeInfo" />
                      <li><s:a href="%{SuccessfulInterviewerDecision}">会社別採用者決定</s:a></li>
                      <li><s:a href="%{employeeInfo}">会社別採用情報照会</s:a></li>
                      <li><s:a href="%{unemployeeInfo}">会社別不採用応募者情報照会</s:a></li>
                    </ul>
                  </li>
                  <%}if (rolePermission.contains("顧客管理")) {%>
                  <li><a href="#">顧客管理 </a>
                    <ul class="dropdown">
                      <s:url id="companyReg" action="CompanyRegistration" />
                      <li><s:a href="%{companyReg}">顧客情報登録</s:a></li>
                    </ul>
                  </li>
                  <%}if (rolePermission.contains("権限設定")) {%>
                 <li><s:url id="role" action="RoleSettingPermission" /> 
                       <s:a href="%{role}">権限設定</s:a>
                       <ul class="dropdown">
                       <s:url id="LandingPageEdit" action="LandingPageEdit">
                       	<li><s:a href="LandingPageEdit">ホームページ設定</s:a></li>
                       </s:url>
                       </ul>
                 </li>
                 <%
						}
					}
				  %>               
                 <li><s:url id="logoutAction" action="logoutAction" /><s:a href="%{logoutAction}">ログアウト</s:a></li>
                </ul>
              </div>
              <!-- Navbar End -->
            </div>
          </nav>
        </div>
      </div>
    </div>
  </header>
  <!-- ##### Header Area End ##### -->
</body>
</html>