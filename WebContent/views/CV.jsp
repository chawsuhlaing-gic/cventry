<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="./src/customizeStyle.css" rel="stylesheet">
<link href="./css/CVForm.css" rel="stylesheet">
<script type="text/javascript" src="assets/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="jquery/CVForm.js"></script>
<script type="text/javascript" src="js/sweet.js"></script>
<link rel='stylesheet' href='./css/minsweet.css'>
<link rel="stylesheet" href="./css/mediaquries.css">
<style>
label {
	font-weight: normal !important;
}

.disabled-dropdown {
	pointer-events: none;
	background-color: #eee;
	opacity: 1;
	cursor: not-allowed;
}
</style>
</head>
<body>
	<%-- <s:if test="hasActionMessages()">
		<script>
			var ID = "";
			var info = "Please check your Email for more information!";
			<s:iterator value="actionMessages" >
			// Iterate the messages, and build the JS String
			ID += '<s:property />';
			</s:iterator>
			ID = ID.bold();
			ID = ID.fontcolor("#0000FF");
			info = info.fontcolor("#FF0000");
			swal("Successfully Registered!!!!", "Your Exam ID is " + ID
					+ ".<br>" + info, "success");
		</script>
	</s:if> --%>

	<s:if test="hasActionMessages()">
		<script>
		var ID = "";
		var info = "Please check your Email for more information!";
		<s:iterator value="actionMessages">
					ID += '<s:property />';
		</s:iterator>
				ID = ID.trim(); // Just in case
				if (ID === "No Successful") {
					swal("Registration Failed!", "Unable to save applicant. Please try again.", "error");
				} else {
					ID = ID.bold();
					ID = ID.fontcolor("#0000FF");
					info = info.fontcolor("#FF0000");
					swal("Successfully Registered!!!!", "Your Exam ID is " + ID + ".<br>" + info, "success");
				}
		</script>
		<%-- <% session.invalidate(); %> --%>
	</s:if>



	<div class="container register-form">
		<div class="form">
			<div class="note">
				<h2>
					<font color="white">Curriculum Vitae</font>
				</h2>
			</div>
			<div class="form-content">
				<form method="post" action="userRegister"
					enctype="multipart/form-data" id="userRegister">
					<div class="row">
						<div class="col-sm-10">
							<div class="row">
								<div class="col-sm-3">
									<div class="form-group">
										<label> <b><font color="red">* </font>Name</b></label><input
											type="text" name="app_Name" class="form-control" id="appName"
											maxlength="30" size="30">
									</div>
								</div>
								<div class="col-sm-1" style="margin-left: 20.7%;"></div>
								<div class="col-sm-4 ">
									<div class="form-group">
										<label><b><font color="red">* </font>Gender</b></label><br>
										<input type="radio" name="app_Gender" value="male"> <font
											color="black"> Male</font> <input type="radio"
											name="app_Gender" value="female" id="appGender"> <font
											color="black">Female</font>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="nrc_Check">
									<div class="form-inline">
										<div class="col-sm-7 " style="width: 52%;">
											<div class="form-group">
												<label><b><font color="red">* </font>NRC Number</b></label>
												<br>
												<div id="cssNRC">
													<s:select name="nrc_no1" headerKey="" headerValue="Select"
														list="nrccityList" listKey="nrcCityID" listValue="nrcCity"
														cssClass="form-control" id="parent_city_selection" />
													/ <select name="nrc_no2" id="citizen_selection"
														class="form-control">
														<option value=" ">Select</option>
													</select> (
													<s:select name="nrc_no3" headerKey="" headerValue="Select"
														list="nrctypeList" listKey="nrcType" listValue="nrcType"
														cssClass="form-control" id="nrcType" />
													) <input type="text" name="nrc_no4" maxlength="6"
														style="width: 73px;" id="checkNRC" class="form-control"
														autocomplete="off">
												</div>
												<div id="checkNRCErr" style="color: red; font-size: 85%;"></div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-3" style="margin-left: -23%;"></div>
								<div class="col-md-3 ">
									<label><b><font color="red">* </font>Date-Of-Birth</b></label>
									<input type="date" class="form-control" id="datePicker"
										name="dob" /> <input type="hidden" id="dateValid">
									<div id="demo1"></div>
								</div>
							</div>
							<br>
							<div class="row">
								<div class="col-md-3 ">
									<div class="form-group">
										<label><b><font color="red">* </font>Phone No </b>(No
											Hyphen)</label><input type="text" placeholder="09XXXXXXXXX"
											name="phoneno" id="appphno" class="form-control"
											maxlength="13" size="15">
									</div>
									<div class="col-md-7 "
										style="margin-bottom: 10px; margin-top: -10px; width: 170%">
										<font color="green"><b>* WE WILL CONTACT TO THIS
												PHONE NO *</b></font>
									</div>
								</div>
								<div class="col-md-3" style="margin-left: 4%;"></div>
								<div class="col-md-3 mgin">
									<div class="form-group">
										<label><b><font color="red">* </font>Email</b></label><input
											type="text" name="email" id="txtemail" class="form-control"
											maxlength="50" size="50" autocomplete="off">
										<div id="checkmailErr" style="color: red; font-size: 85%;"></div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<div class="col-md-10 ">
										<label><b><font color="red">* </font>Address</b></label>
										<textarea rows="4" cols="50" name="address" maxlength="200"
											id="appadd" class="form-control" style="resize: none;"></textarea>
									</div>
								</div>
							</div>
							<br>
							<div class="divider"></div>
							<br>
							<div class="row">
								<div class="uniCheck">
									<div class="col-sm-3 ">
										<div class="form-group">
											<label><b><font color="red">* </font>City Of
													University </b></label>
											<s:select name="applicant.app_CityofUniversity" headerKey=""
												headerValue="Select" list="CityofUniList"
												listKey="cityofUniName" listValue="cityofUniName"
												cssClass="form-control" id="city_selection" />
										</div>
									</div>
									<div class="col-sm-4 ">
										<div class="form-group">
											<label><b><font color="red">* </font>University</b></label><select
												name="applicant.app_University" id="uni_selection"
												class="form-control">
												<option value="">Select</option>
											</select>
										</div>
									</div>
								</div>
								<div class="col-sm-3 ">
									<div class="form-group">
										<input type="text" placeholder="Other" name="txtOtherUni"
											id="otheruni" class="form-control" maxlength="30"
											style="display: none; margin-top: 26px;">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label><b><font color="red">* </font>Education</b></label><br>
										<input type="radio" onclick="yesnoCheck();"
											name="app_Education" id="yesCheck" value="Graduated">Graduated
										<input type="radio" onclick="yesnoCheck();"
											name="app_Education" id="noCheck" value="Ungraduated">
										Not Yet Graduated
									</div>
								</div>
							</div>
							<div class="row">
								<div id="degree" style="display: none; margin-top: -14px;">
									<div class="col-sm-3 ">
										<div id="ifYes">
											<s:select headerKey="" headerValue="Select" id="yes"
												list="DegreeList" listKey="degreeName"
												listValue="degreeName" name="ddlDegree1"
												cssClass="form-control" required="required"
												style="margin-bottom: 10px;" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-3 ">
											<input type="text" placeholder="Other/Major" id="other"
												maxlength="30" class="form-control" name="txtOtherDegree"
												style="display: none;" />
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<div id="degree1"
										style="display: none; margin-top: -14px; margin-left: -13px;">
										<div class="form-group">
											<div class="col-sm-6">
												<div id="ifNo">
													<s:select headerKey="" headerValue="Select" id='no'
														list="lstAttendYrs" listKey="attendYear"
														listValue="attendYear" name="ddlDegree2"
														cssClass="form-control" required="required" />
												</div>
											</div>
										</div>
										<div class="form-group" style="margin-left: 10px;">
											<label style="margin-top: 10px;"><b><font
													color="red">* </font>Do you have any Plan to continue
													University?</b></label><br> <input type="radio" name="app_ACPlan"
												value="Yes" id="ACPlan_Check"> Yes <input
												type="radio" name="app_ACPlan" value="No"
												id="ACPlan_NoCheck"> No <input type="radio"
												name="app_ACPlan" value="To Be Decided"> To Be
											Decided
										</div>

									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-12">
									<div id="graduateyear" style="display: none;">
										<label><b><font color="red">* </font>Expected
												Graduation Date</b></label><br> <font color="green">If the
											exact date can't be confirmed, can enter estimate date</font>
										<div class="row">
											<div class="col-sm-3">
												<input type="date" class="form-control" id="egdatePicker"
													name="ddlACPLan" /> <input type="hidden" id="dateValid">
												<div id="demo1"></div>
											</div>
										</div>
										<br>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6 ">
									<div class="form-group">
										<label
											title="Certificate of passing Fundamental Engineering Exam of Information Technology Professional Examination Council"><b><font
												color="red">* </font> Do you have ITPEC FE Certificate?</b></label><br>
										<s:a href="https://itpec.org/pastexamqa/fe.html"
											target="_blank">What is ITPEC FE? Click here</s:a>
										<br> <input type="radio" name="app_FEC" value="Yes"
											id="fec"> Yes <input type="radio" name="app_FEC"
											value="No"> No
									</div>
								</div>
							</div>
							<div class="row">
								<div id="situationfe" style="display: none">
									<div class="col-sm-8">
										<div class="form-group">
											<label><b><font color="red">* </font>Do you have
													any Plan to take FE Exam?</b></label>
											<div class="row">
												<div class="col-sm-6">
													<%--  <s:select headerKey="" headerValue="Select"
								              list="lstFeSituation" listKey="feSituation" id="fesituation"
								              listValue="feSituation" name="ddlFE"
								              cssClass="form-control" required="required" /> --%>
													<input type="radio" name="ddlFE" value="Yes"
														id="fesituation"> <font color="black"> Yes</font>
													<input type="radio" name="ddlFE" value="No"> <font
														color="black">No</font> <input type="radio" name="ddlFE"
														value="To Be Decided"> <font color="black">To
														Be Decided</font>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label><b><font color="red">* </font> GIC Academy
												Intention To Attend</b></label><br> <input type="radio"
											name="app_GICAcademy" value="Yes" id="appGICAcademy">
										<font color="black"> Yes</font> <input type="radio"
											name="app_GICAcademy" value="No" id="appGICAcademy_NoCheck">
										<font color="black">No</font> <input type="radio"
											name="app_GICAcademy" value="To Be Decided"> <font
											color="black">To Be Decided</font> <br> <br> <label>
											<b>What is GIC Academy?</b>
										</label><br>
										<s:a href="%{dumpOfficeUrl}" target="_blank">Watch Video</s:a>
										|
										<s:a href="%{dumpPdf}" target="_blank">Read More</s:a>
										|
										<s:a href="https://www.facebook.com/gic.academy.jp"
											target="_blank">Facebook Page</s:a>
										<br> <br>
										<div>
											<label><b><font color="red">* </font>Are you
													interest in 0 Yen Study in Japan (GIC Academy Scholarship
													Program)</b></label><br>
											<s:a
												href="https://www.facebook.com/share/p/TLRJgp12P4kDMpp9/"
												target="_blank">Facebook Post</s:a>
											<br> <input type="radio" name="zero_yen_study"
												value="Yes" id="zeroYenStudy"> <font color="black">
												Yes </font> <input type="radio" name="zero_yen_study" value="No"
												id="zeroYenStudy_NoCheck"> <font color="black">No</font>
											<input type="radio" name="zero_yen_study"
												value="To Be Decided"><font color="black"> To
												Be Decided</font>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<label><b><font color="red">* </font>Attachment</b></label><br>
										<input type="file" name="gradFile" style="color: #000;"
											accept="application/image/*" id="check" /> <font
											color="green">Please upload your Graduation
											Certificate or Student ID Card.<br> ※File size must be
											under 5MB.
										</font>
									</div>
								</div>
							</div>
							<input type="hidden" name="grad_Flag" id="selectType" /> <br>
							<div class="divider"></div>
							<br>
							<div class="row">
								<div class="col-sm-6">
									<div>
										<label><b><font color="red">* </font>Do you have
												experience working in a Japanese company?</b></label><br> <input
											type="radio" name="jp_company_exp" value="Yes"
											id="jpCompanyExp"> <font color="black"> Yes </font> <input
											type="radio" name="jp_company_exp" value="No"> <font
											color="black">No</font>
									</div>
								</div>
							</div>
							<br>
							<div class="row">
								<div class="col-sm-6 ">
									<div class="form-group">
										<label><b><font color="red">* </font>Work
												Experience </b></label><br> <input type="radio"
											name="app_Experience" value="Yes" id="appex"> Yes <input
											type="radio" name="app_Experience" value="No"> No
									</div>
								</div>
							</div>
							<%-- <div class="row">
								<div id="textboxes" style="display: none">
									<div class="col-sm-3 ">
										<label><b><font color="red">* </font>Experience
												Years</b></label>
										<div class="input-group">
											<input type="text" class="form-control" maxlength="2"
												name="ddlExperienceYears" id="ExpYears" /> <span
												class="input-group-addon"><span>Years</span></span>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="form-group">
											<label><b>Current Company </b></label><input type="text"
												name="applicant.app_CurrentCom" id="cur_com"
												class="form-control" maxlength="50" size="50">
										</div>
									</div>
									<div class="col-sm-3 ">
										<div class="form-group">
											<label><b>Current Position </b></label><input type="text"
												name="applicant.app_CurrentPos" id="cur_pos"
												class="form-control" maxlength="50" size="50">
										</div>
									</div>
								</div>
							</div> --%>

							<div class="row experience-row" id="textboxes" hidden>
								<div class="col-sm-3">
									<label><b><font color="red">* </font>Experience
											Years</b></label>
									<div class="input-group">
										<input type="text" class="form-control" maxlength="2"
											name="experience[0].exp_Year" /> <span
											class="input-group-addon"><span>Years</span></span>
									</div>
								</div>

								<div class="col-sm-3">
									<div class="form-group">
										<label><b>Company</b></label> <input type="text"
											name="experience[0].exp_Company" class="form-control"
											maxlength="50" size="50">
									</div>
								</div>

								<div class="col-sm-3">
									<div class="form-group">
										<label><b>Position</b></label>
										<div class="input-group">
											<input type="text" name="experience[0].exp_Position"
												class="form-control" maxlength="50" size="50"> <span
												class="input-group-btn">
												<button class="btn btn-success" type="button"
													onclick="addExperienceRow()">+</button>
											</span>
										</div>
									</div>
								</div>
							</div>

							<!-- Container for dynamically added rows -->
							<div id="additionalRows" hidden></div>

							<br>
							<div class="row">
								<div class="divider"></div>
								<br>
								<div class="col-sm-4">
									<b>Language Skill</b><br> <br>
									<div class="form-group">
										<label><b><font color="red">* </font>English</b></label>
										<s:select headerKey="" headerValue="Select"
											list="EngLevelList" listKey="englishLevel"
											listValue="englishLevel" name="app_ENGSkill" id="engskill"
											cssClass="form-control" required="required" />
									</div>
								</div>
								<div class="col-sm-4">
									<br> <br>
									<div class="form-group">
										<label><b><font color="red">* </font>Japanese</b></label>
										<s:select headerKey="" headerValue="Select" list="JPLevelList"
											listKey="japaneseLevel" listValue="japaneseLevel"
											name="app_JPSkill" cssClass="form-control"
											required="required" id="jpskill" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-4 ">
									<div class="form-group">
										<label><b>Attachment 1</b></label><br> <input type="file"
											name="Lan1File" style="color: #000;"
											accept="application/image/*" id="checkLan1"
											onChange="CheckFileExtensionLan1()" /> <font color="green">
											<input type="hidden" name="lan1_Flag" id="selectTypeLan1" />
											Please upload your Certificate.<br> ※File size must be
											under 5MB.
										</font>
									</div>
								</div>
								<div class="col-sm-4 ">
									<div class="form-group">
										<label><b>Attachment 2</b></label><br> <input type="file"
											name="Lan2File" style="color: #000;"
											accept="application/image/*" id="checkLan2"
											onChange="CheckFileExtensionLan2()" /> <font color="green">
											<input type="hidden" name="lan2_Flag" id="selectTypeLan2" />
											Please upload your Certificate.<br> ※File size must be
											under 5MB.

										</font>
									</div>
								</div>
							</div>
							<br>
							<div class="divider"></div>
							<br>
							<div class="row col-">
								<div class="col-md-12 ">
									<div class="form-group">
										<b>IT Skill (Multiple choice allowed)</b><br> <br>
										<div
											style="border-top: 1px solid; border-bottom: 1px solid; border-left: 1px solid; border-right: 1px solid; padding-left: 20px; padding-top: 20px; padding-bottom: 20px;">
											<b>Programming:</b> <br>
											<s:checkboxlist list="lstITProgramming"
												listKey="programmingName" listValue="programmingName"
												name="app_ITPSkill" />
											<hr>
											<b>Design:</b><br>
											<s:checkboxlist list="lstITDesign" listKey="designName"
												listValue="designName" name="app_ITDSkill" />
											<hr>
											<b>Database/OS:</b> <br>
											<s:checkboxlist list="lstITDatabase" listKey="dbServerName"
												listValue="dbServerName" name="app_ITDBSkill" />
											<hr>
											<b>Other:</b><br>
											<s:checkboxlist list="lstITOthers" listKey="othersName"
												listValue="othersName" name="app_ITOSkill" />
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-8">
									<div class="form-group">
										<label><b>Attachment</b></label><br> <input type="file"
											name="IT1File" style="color: #000;"
											accept="application/image/*" id="checkIT1"
											onChange="CheckFileExtensionIT1()" /> <font color="green">Please
											Upload your Certificate. ※File size must be under 5MB.</font>
									</div>
									<input type="hidden" name="IT1_Flag" id="selectTypeIT1" />
								</div>
								<div class="col-sm-4 "></div>
							</div>
							<br>
							<div class="divider"></div>
							<br>
							<div class="row">
								<div class="col-sm-3 ">
									<div class="form-group">
										<label><b><font color="red">* </font>Exam Place</b></label>
										<s:select headerKey="" headerValue="Select"
											list="lstExamPlace" listKey="examPlace" id="examplace"
											listValue="examPlace" name="app_ExamPlace"
											cssClass="form-control" required="required" />
									</div>
								</div>
							</div>
							<font color="green">※If you choose Yangon, your exam place
								is Yangon & if you choose Mandalay, your exam place is Mandalay.
							</font>
						</div>
						<div class="col-sm-2 ">
							<div class="row col-">
								<img id="blah" src="img/core-img/profile.png" alt="your image">
								<input type='file' name="photo" id="cvphoto" /> <font
									color="green" style="white-space: nowrap;">※Photo must
									be under 5MB.</font>
							</div>
						</div>
					</div>
					<br>
					<div class="form-group">
						<div class="text-center">
							<button class="btn btn-primary btn-lg" id="submitBtn"
								style="font-size: 18px;">
								<span class="fa fa-paper-plane">REGISTER</span>
							</button>
							<s:a href="CVFormTmp" class="btn btn-danger btn-lg"
								style="font-size: 18px;">
								<span class="fa fa-close">CANCEL</span>
							</s:a>
						</div>
					</div>
				</form>

				<div class="modal fade" id="modal-1" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true"
					data-backdrop="static">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header"
								style="background-color: #92bed4; height: 50px;">
								<label style="float: left;">Personal Information
									Confirmation</label>
							</div>
							<div class="modal-body">
								Are you sure you want to submit the following details?
								<div class="form-content">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><b>Name <i class="fa fa-user"></i></b></label> <input
													type="text" class="form-control" id="mname" disabled />
											</div>
											<div class="form-group">
												<label><b>Date Of Birth<i class="fa fa-calendar"></i>
												</b></label> <input type="text" class="form-control" id="mdob" disabled />
											</div>
											<div class="form-group">
												<label><b>Phone Number<i class="fa fa-phone"></i></b></label>
												<input type="text" class="form-control" id="mphno" disabled />
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label><b>Gender <i class="fa fa-venus-mars"></i></b></label>
												<input type="text" class="form-control" id="mgender"
													disabled />
											</div>
											<div class="form-group">
												<label><b>NRC <i class="fa fa-address-card"></i></b></label>
												<input type="text" class="form-control" id="mnrc" disabled />
											</div>
											<div class="form-group">
												<label><b>Email <i class="fa fa-envelope"></i></b></label> <input
													type="text" class="form-control" id="memail" disabled />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label><b>Address <i class="fa fa-map"></i></b></label>
												<textarea class="form-control" id="maddress" disabled></textarea>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><b>City of University <i
														class="fa fa-university"></i></b> </label> <input type="text"
													class="form-control" id="muni" disabled />
											</div>
											<div class="form-group">
												<label><b>Education <i
														class="fa fa-graduation-cap"></i></b></label> <input type="text"
													class="form-control" id="medu" disabled />
											</div>
											<div class="form-group">
												<label><b>English Level </b></label> <input type="text"
													class="form-control" id="mENG" disabled />
											</div>
											<div class="form-group">
												<label><b>GIC Academy Intention To Attend<i
														class="fa fa-map-pin"></i> <input type="text"
														class="form-control" id="mGICAcademy" disabled />
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label><b>University <i class="fa fa-university"></i></b></label>
												<input type="text" class="form-control" id="muniname"
													disabled />
											</div>
											<div class="form-group">
												<label><b>Experience<i
														class="fa fa-check-square"></i>
												</b></label> <input type="text" class="form-control" id="mexp" disabled />
											</div>
											<div class="form-group">
												<label><b>Japanese Level </b></label> <input type="text"
													class="form-control" id="mJP" disabled />
											</div>
											<div class="form-group">
												<label><b>Exam Place <i class="fa fa-map-pin"></i></b></label>
												<input type="text" class="form-control" id="mexamplace"
													disabled />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><b>Do you have ITPEC FE Certificate? <i
														class="fa fa-graduation-cap"></i></b></label> <input type="text"
													class="form-control" id="mfec" disabled />
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label><b>0 Yen Study in Japan<i
														class="fa fa-map-pin"></i></b></label> <input type="text"
													class="form-control" id="mZeroYenStudy" disabled />
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-6">
											<div class="form-group" id="mACPlanGroup">
												<label><b>Do you have any Plan to continue
														University?<i class="fa fa-check-square"></i>
												</b></label> <input type="text" class="form-control" id="mACPlan"
													disabled />
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label><b>Do you have experience working in a
														Japanese company?<i class="fa fa-map-pin"></i>
												</b></label> <input type="text" class="form-control" id="mJpCompanyExp"
													disabled />
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-6">
											<div class="form-group" id="mgraduationDateGroup">
												<label><b>Expected Graduation Date <i
														class="fa fa-calendar"></i></b></label> <input type="text"
													class="form-control" id="mgraduationDate" disabled />
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button class="btn btn-primary btn-md" name="btn"
									id="formSubmit">Yes</button>
								<button type="button" class="btn btn-danger btn-md"
									id="modalClose" data-dismiss="modal">Cancel</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#userRegister')
								.bootstrapValidator(
										{
											fields : {
												app_Name : {
													validators : {
														notEmpty : {
															message : 'Name is required '
														},

														regexp : {
															regexp : /^[a-zA-Z\s-, ]+$/,
															message : 'Only character available!'
														}
													}
												},

												app_Gender : {
													validators : {
														notEmpty : {
															message : 'Gender is required'
														}
													}
												},
												dob : {
													validators : {
														notEmpty : {
															message : 'Birthdate is required'
														},
														date : {
															format : 'MM/DD/YYYY',
															message : 'Date is not valid'
														},
														callback : {
															callback : function(
																	value,
																	validator,
																	$field) {
																var parts = value
																		.split("-");
																var dtCurrent = new Date();

																// Same yrs
																if (parts[0] == dtCurrent
																		.getFullYear()) {
																	return {
																		valid : false,
																		message : "Cann't Select Current Year"
																	}
																}
																// Less than 18 yrs
																else if ((dtCurrent
																		.getFullYear() - parts[0]) < 18) {
																	return {
																		valid : false,
																		message : 'Age must be greater than 18'
																	};
																}
																// Less than 18 yrs
																else if ((dtCurrent
																		.getFullYear() - parts[0]) > 65) {
																	return {
																		valid : false,
																		message : 'Age must be less than 65'
																	};
																}
																return true;
															}
														}
													}
												},
												ddlACPLan : {
													validators : {
														notEmpty : {
															message : 'Birthdate is required'
														},
														date : {
															format : 'MM/DD/YYYY',
															message : 'Date is not valid'
														},
														callback : {
															callback : function(
																	value,
																	validator,
																	$field) {
																var parts = value
																		.split("-");
																var dtCurrent = new Date();

																// Check if past date
																if (new Date(
																		value) < dtCurrent) {
																	return {
																		valid : false,
																		message : 'Cannot select a past date'
																	};
																}
																return true;
															}
														}
													}
												},
												parent_city_selection : {
													selector : '#parent_city_selection',
													group : '.nrc_Check',
													validators : {
														notEmpty : {
															message : '  '
														}
													}
												},

												citizen_selection : {
													selector : '#citizen_selection',
													group : '.nrc_Check',
													validators : {
														notEmpty : {
															message : 'Please Select Correct NRC Number '
														}
													}
												},
												nrc_no3 : {
													group : '.nrc_Check',
													validators : {
														notEmpty : {
															message : ' '
														}
													}
												},
												nrc_no4 : {
													group : '.nrc_Check',
													validators : {
														notEmpty : {
															message : 'NRC is required'
														},
														stringLength : {
															min : 6,
															max : 6,
															message : 'Please Enter Correct NRC Number'
														},
														regexp : {
															regexp : /^[0-9]*$/,
															message : 'Please Enter Number Only'
														}

													}
												},
												phoneno : {
													validators : {
														notEmpty : {
															message : 'Phone No is required'
														},

														regexp : {
															regexp : /^(?:0|\s?)([9](?:[\s]?\d){6,9}$)/,
															message : 'Please Enter Correct Phone No!'
														}

													}
												},
												email : {
													validators : {
														notEmpty : {
															message : 'Email address is required '
														},
														regexp : {
															regexp : /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{3})+$/,
															message : 'Please Enter Correct Email!'
														}
													}
												},
												address : {
													validators : {
														notEmpty : {
															message : 'Address is required'
														},
														regexp : {
															regexp : /[a-zA-Z\s-,().!@#$%^&*():{}/\|<>]+$|[0-9]+$/,
															message : 'Only character and integer are available!'
														}

													}
												},

												city_selection : {
													selector : '#city_selection',
													group : '.uniCheck',
													validators : {
														notEmpty : {
															message : 'City Of University is required '
														}
													}
												},
												uni_selection : {
													selector : '#uni_selection',
													group : '.uniCheck',
													validators : {
														notEmpty : {
															message : 'University is required '
														}
													}
												},

												txtOtherUni : {
													validators : {
														notEmpty : {
															message : ' '
														}
													}
												},

												app_Education : {
													validators : {
														notEmpty : {
															message : 'Education is required '
														}
													}
												},
												app_FEC : {
													validators : {
														notEmpty : {
															message : 'ITPEC FE is required '
														}
													}
												},
												
												ddlDegree1 : {
													validators : {
														notEmpty : {
															message : 'Degree is required'
														}
													}
												},

												ddlDegree2 : {
													validators : {
														notEmpty : {
															message : 'Student Year is required'
														}
													}
												},

												gradFile : {
													validators : {
														notEmpty : {
															message : 'Attachment is required'
														},

													}
												},

												app_Experience : {
													validators : {
														notEmpty : {
															message : 'Experience is required'
														}
													}
												},

												ddlExperienceYears : {
													validators : {
														notEmpty : {
															message : 'Experience Years is required'
														},
														stringLength : {
															max : 2,
															message : ''
														},
														regexp : {
															regexp : /^[1-9]\d*$/,
															message : 'Only Enter Number.'
														}
													}
												},
												'experience[0].exp_Year': {
        										    validators: {
        										        callback: {
        										            message: 'Experience Years is required',
        										            callback: function(value) {
        										                return $('input[name="app_Experience"]:checked').val() !== 'Yes' || value.trim() !== '';
        										            }
        										        },
														stringLength: {
			            								    max: 2,
			            								    message: 'Maximum 2 digits only'
			            								},
			            								regexp: {
			            								    regexp: /^[1-9]\d*$/,
			            								    message: 'Only Enter Number.'
			            								}
        										    }
        										},
												'experience[0].exp_Company': {
        										    validators: {
        										        callback: {
        										            message: 'Company Name is required',
        										            callback: function(value) {
        										                return $('input[name="app_Experience"]:checked').val() !== 'Yes' || value.trim() !== '';
        										            }
        										        },
														stringLength: {
			            								    max: 100,
			            								    message: 'Maximum 100 characters allowed'
			            								}
        										    }
        										},
												'experience[0].exp_Position': {
        										    validators: {
        										        callback: {
        										            message: 'Position is required',
        										            callback: function(value) {
        										                return $('input[name="app_Experience"]:checked').val() !== 'Yes' || value.trim() !== '';
        										            }
        										        },
														stringLength: {
			            								    max: 50,
			            								    message: 'Maximum 50 characters allowed'
			            								}
        										    }
        										},
												app_ENGSkill : {
													validators : {
														notEmpty : {
															message : 'English level is required'
														}
													}
												},

												app_JPSkill : {
													validators : {
														notEmpty : {
															message : 'Japanese level is required'
														}
													}
												},

												photo : {
													validators : {
														notEmpty : {
															message : 'Profile image is required'
														}
													}
												},

												app_ExamPlace : {
													validators : {
														notEmpty : {
															message : 'Exam place is required.'
														}
													}
												},
												zero_yen_study : {
													validators : {
														notEmpty : {
															message : '0 yen study plan is required.'
														}
													}
												},
												jp_company_exp : {
													validators : {
														notEmpty : {
															message : 'Experience with Japanese companies is required.'
														}
													}
												}
											},
											onSuccess : function(e) {
												e.preventDefault();

												$("#submitBtn").attr(
														"disabled", true);
												var nrcOne = $(
														'#parent_city_selection')
														.val();
												var nrcTwo = $(
														'#citizen_selection')
														.val().trim();
												var nrcThree = $('#nrcType')
														.val();
												var nrcFour = $('#checkNRC')
														.val();
												var appnrc = nrcOne + '/'
														+ nrcTwo + '('
														+ nrcThree + ')'
														+ nrcFour;
												var email = document
														.getElementById("txtemail").value;
												if (nrcTwo == "") {
													document
															.getElementById("checkNRCErr").innerHTML = "NRC is required";
												} else {
													$
															.ajax({
																type : "GET",
																url : "<s:url action='checkUserDuplicateData'/>",
																data : {
																	app_nrc : appnrc,
																	appMail : email
																},
																success : function(
																		responseText) {
																	if (responseText == "mailDuplicate") {
																		$(
																				'#txtemail')
																				.val(
																						null);
																		alert("Your email address is already used!")
																		$(
																				"#submitBtn")
																				.attr(
																						"disabled",
																						false);
																		document
																				.getElementById("txtemail").style.borderColor = "#a94442";
																		document
																				.getElementById("checkmailErr").innerHTML = "Email address is required";
																		$(
																				'#modal-1')
																				.modal(
																						'hide');
																	} else if (responseText == "nrcDuplicate") {
																		$(
																				'#checkNRC')
																				.val(
																						null);
																		alert("Your NRC number is already used!")
																		$(
																				"#submitBtn")
																				.attr(
																						"disabled",
																						false);
																		document
																				.getElementById("cssNRC").style.border = "3px solid #a94442";
																		document
																				.getElementById("checkNRC").style.borderColor = "#a94442";
																		document
																				.getElementById("checkNRCErr").innerHTML = "NRC is required";
																		$(
																				'#modal-1')
																				.modal(
																						'hide');
																	} else if (responseText == "bothDuplicate") {
																		$(
																				'#checkNRC')
																				.val(
																						null);
																		$(
																				'#txtemail')
																				.val(
																						null);
																		alert("Your NRC number & Email Address are already used!")
																		$(
																				"#submitBtn")
																				.attr(
																						"disabled",
																						false);
																		document
																				.getElementById("cssNRC").style.border = "3px solid #a94442";
																		document
																				.getElementById("checkNRC").style.borderColor = "#a94442";
																		document
																				.getElementById("checkNRCErr").innerHTML = "NRC is required";
																		document
																				.getElementById("txtemail").style.borderColor = "#a94442";
																		document
																				.getElementById("checkmailErr").innerHTML = "Email address is required";
																		$(
																				'#modal-1')
																				.modal(
																						'hide');
																	} else if (responseText == "NO") {
																		if (nrcFour != ''
																				&& email != '') {
																			$(
																					'#modal-1')
																					.modal(
																							'show');
																			callModalData();
																		} //Final
																	}
																}
															});
												}
											},
											onFailure : function(e) {
												$('#modal-1').modal('hide');
											}
										});
						$('#submitBtn').click(function() {
							$('#userRegister').bootstrapValidator('validate');
						});
					});
</script>
<script type="text/javascript">
	$('#txtemail').keypress(function() {
		document.getElementById("checkmailErr").innerHTML = " ";
		document.getElementById("txtemail").style.borderColor = "#3c763d";
		$('#checkmailErr').text(" ");

	});
	$('#checkNRC').keypress(function() {
		document.getElementById("checkNRC").style.borderColor = "#3c763d";
		document.getElementById("cssNRC").style.border = "none";
		document.getElementById("checkNRCErr").innerHTML = " ";
	});
	document.getElementById("citizen_selection").onchange = function() {
		document.getElementById("checkNRCErr").innerHTML = " ";
	}
	$("#formSubmit").click(function() {
		event.preventDefault();
		this.disabled = true;
		document.getElementById('userRegister').submit();
		$("#modalClose").attr("disabled", true);
		$("#submitBtn").attr("disabled", true);
	});
	$("#modalClose").click(function() {
		$("#submitBtn").attr("disabled", false);
	});
</script>
<script type="text/javascript">
	function restrictAlphabets(e) {
		var x = e.which || e.keycode;
		if ((x >= 48 && x <= 57) || x == 8 || (x >= 35 && x <= 40) || x == 46)
			return true;
		else
			return false;
	}
</script>
<script type="text/javascript">
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#blah').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	};

	$("#cvphoto")
			.change(
					function() {

						var ext = cvphoto.value.split('.');
						ext = ext[ext.length - 1].toLowerCase();
						var arrayExtensions = [ 'jpg', 'jpeg', 'png' ];
						if (arrayExtensions.lastIndexOf(ext) == -1) {
							alert('Image type Error!');
							document.getElementById("blah").src = "img/core-img/profile.png";
							this.value = '';
							return false;
						} else {
							if (this.files[0].size > 1024 * 1024 * 5) {
								alert("The file size limit exceeded 5MB.");
								document.getElementById("blah").src = "img/core-img/profile.png";
								this.value = '';
								return false;
							} else {
								readURL(this);
							}
						}

					});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#yes').change(function() {
			if ($('#yes').val() === '') {
				$('#other').hide();
				document.getElementById('other').value = '';

			} else {
				document.getElementById('other').value = '';
				$('#other').show();

			}
		});
	});

	function yesnoCheck() {
		if (document.getElementById('yesCheck').checked) {
			document.getElementById('ifYes').style.visibility = 'visible';
		} else
			document.getElementById('ifYes').style.visibility = 'hidden';

		if (document.getElementById('noCheck').checked) {
			document.getElementById('ifNo').style.visibility = 'visible';

		} else
		{
			document.getElementById('ifNo').style.visibility = 'hidden';
		}			
	}
	function yesnoRadioCheck() {
		if (document.getElementById('yesCheck').checked) {
			document.getElementById('ifYes').style.visibility = 'visible';

		} else
			document.getElementById('ifYes').style.visibility = 'hidden';

		if (document.getElementById('noCheck').checked) {
			document.getElementById('ifNo').style.visibility = 'visible';

		} else
			document.getElementById('ifNo').style.visibility = 'hidden';
	}
	function gic_academyCheck() {
		if (document.getElementById('yesCheck').checked) {
			document.getElementById('ifYes').style.visibility = 'visible';

		} else
			document.getElementById('ifYes').style.visibility = 'hidden';

		if (document.getElementById('noCheck').checked) {
			document.getElementById('ifNo').style.visibility = 'visible';

		} else
			document.getElementById('ifNo').style.visibility = 'hidden';
	}
</script>

<script type="text/javascript">
	$(document).ready(
			function() {
				$('input:radio[name=app_Education]').change(
						function() {
							if (this.value == 'Graduated') {
								$('#degree').show();
								$('#degree1').hide();
								$('#graduateyear').hide();
								document.getElementById('no').value = '';
								$('#userRegister').bootstrapValidator(
										'revalidateField', 'ddlDegree1');
								$('input:radio[name="app_ACPlan"][value="No"]').prop('checked', true); // set No as checked
								document.getElementById('egdatePicker').value = "";
							} else if (this.value == 'Ungraduated') {

								$('#degree').hide();
								$('#degree1').show();
								document.getElementById('yes').value = '';
								$('#other').hide();
								document.getElementById('other').value = '';
								$('#userRegister').bootstrapValidator(
										'revalidateField', 'ddlDegree2');
							}
						});

				$('input:radio[name=app_ACPlan]').change(
						function() {
							if (this.value == 'Yes') {
								$('#graduateyear').show();
								$('#userRegister').bootstrapValidator(
										'revalidateField', 'ddlDegree1');
								this.value == 'No';
							} else if (this.value == 'No') {
								$('#graduateyear').hide();
								document.getElementById('yes').value = '';
							} else if (this.value == 'To Be Decided') {
								$('#graduateyear').hide();
								document.getElementById('yes').value = '';
							}
						});
			});
</script>

<script type="text/javascript">
	$(function() {
		$('input[name="app_Experience"]').on(
				'click',
				function() {
					if ($(this).val() == 'Yes') {
						$('#textboxes').show();
						$('#userRegister').bootstrapValidator(
								'revalidateField', 'ddlExperienceYears');
						$('#additionalRows').show();

					} else {
					    /* document.getElementById('ExpYears').value = '';
						document.getElementById('cur_com').value = '';
						document.getElementById('cur_pos').value = ''; */
						$('#textboxes').hide();
						$('#additionalRows').hide();
					}
				});
	});

</script>

<script language="javascript">
	var uploadField = document.getElementById("check");
	uploadField.onchange = function() {

		if (this.files[0].size > 1024 * 1024 * 5) {
			alert("The file size limit exceeded 5MB.");
			this.value = "";
		} else {
			var ext = check.value.split('.');
			ext = ext[ext.length - 1].toLowerCase();
			var arrayExtensions = [ 'jpg', 'jpeg', 'png' ];
			if (arrayExtensions.lastIndexOf(ext) == -1) {
				alert("Image type Error!");
				this.value = '';
			} else {
				selectType.value = check.value.split('.')[1];
			}

		}
		;
	};
</script>

<script language="javascript">
	var uploadField = document.getElementById("checkLan1");
	uploadField.onchange = function() {

		if (this.files[0].size > 1024 * 1024 * 5) {
			alert("The file size limit exceeded 5MB.");
			this.value = "";
		} else {
			var ext = checkLan1.value.split('.');
			ext = ext[ext.length - 1].toLowerCase();
			var arrayExtensions = [ 'jpg', 'jpeg', 'png' ];
			if (arrayExtensions.lastIndexOf(ext) == -1) {
				alert("Image type Error!");
				this.value = '';
			} else {
				selectTypeLan1.value = checkLan1.value.split('.')[1];
			}

		}
		;
	};
</script>

<script language="javascript">
	var uploadField = document.getElementById("checkLan2");
	uploadField.onchange = function() {

		if (this.files[0].size > 1024 * 1024 * 5) {
			alert("The file size limit exceeded 5MB.");
			this.value = "";
		} else {
			var ext = checkLan2.value.split('.');
			ext = ext[ext.length - 1].toLowerCase();
			var arrayExtensions = [ 'jpg', 'jpeg', 'png' ];
			if (arrayExtensions.lastIndexOf(ext) == -1) {
				alert("Image type Error!");
				this.value = '';
			} else {
				selectTypeLan2.value = checkLan2.value.split('.')[1];
			}

		}
		;
	};
</script>

<script language="javascript">
	var uploadField = document.getElementById("checkIT1");
	uploadField.onchange = function() {

		if (this.files[0].size > 1024 * 1024 * 5) {
			alert("The file size limit exceeded 5MB.");
			this.value = "";
		} else {
			var ext = checkIT1.value.split('.');
			ext = ext[ext.length - 1].toLowerCase();
			var arrayExtensions = [ 'jpg', 'jpeg', 'png' ];
			if (arrayExtensions.lastIndexOf(ext) == -1) {
				alert("Input Type Error!");
				this.value = '';
			} else {
				selectTypeIT1.value = checkIT1.value.split('.')[1];
			}

		}
		;
	};
</script>
<script language="javascript" type="text/javascript">
	$(document).ready(
			function() {
				$('#uni_selection').change(function() {
					var other = document.getElementById('uni_selection');
					var otherText = other.options[other.selectedIndex].text;

					if (otherText === 'Other') {
						$('#otheruni').show();
						document.getElementById('otheruni').value = null;

					} else {

						$('#otheruni').hide();
					}
				});

				//If parent option is changed
				$("#city_selection").change(
						function() {

							$('#otheruni').hide();
							document.getElementById('otheruni').value = '';
							var parent = $(this).val(); //get option value from parent 
							switch (parent) { //using switch compare selected option and populate child
							case 'Yangon':
								list(<s:property value='lstygnUni'/>);
								break;
							case 'Mandalay':
								list(<s:property value='lstmdyUni'/>);
								break;
							case 'Ayeyarwaddy':
								list(<s:property value='lstayaUni'/>);
								break;
							case 'Bago':
								list(<s:property value='lstbagoUni'/>);
								break;
							case 'Kachin':
								list(<s:property value='lstkachinUni'/>);
								break;
							case 'Kayin':
								list(<s:property value='lstkayinUni'/>);
								break;
							case 'Kayah':
								list(<s:property value='lstkayahUni'/>);
								break;
							case 'Magway':
								list(<s:property value='lstmagwayUni'/>);
								break;
							case 'Mon':
								list(<s:property value='lstmonUni'/>);
								break;
							case 'Rakhine':
								list(<s:property value='lstrakhineUni'/>);
								break;
							case 'Sagaing':
								list(<s:property value='lstsagaingUni'/>);
								break;
							case 'Shan':
								list(<s:property value='lstshanUni'/>);
								break;

							case 'Thanintharyi':
								list(<s:property value='lstthanintharyiUni'/>);
								break;

							default: //default child option is blank
								$("#uni_selection").html('');
								break;
							}

							$('#userRegister').bootstrapValidator(
									'revalidateField', 'uni_selection');
						});

				//function to populate child select box
				function list(array_list) {
					$("#uni_selection").html(""); //reset child options
					var o = new Option("Select", " ");
					$("#uni_selection").append(o);
					$(array_list).each(
							function(i) { //populate child options 
								$("#uni_selection").append(
										"<option value=\""+array_list[i].UniName+"\">"
												+ array_list[i].UniName
												+ "</option>");
							});
				}

			});
</script>

<script language="javascript" type="text/javascript">
	$(document).ready(
			function() {

				$("#parent_city_selection").change(
						function() {
							var parent = $(this).val();

							switch (parent) {
							case '1':
								list(<s:property value='lstNRCOne'/>);
								break;

							case '2':
								list(<s:property value='lstNRCTwo'/>);
								break;

							case '3':
								list(<s:property value='lstNRCThree'/>);
								break;

							case '4':
								list(<s:property value='lstNRCFour'/>);
								break;

							case '5':
								list(<s:property value='lstNRCFive'/>);
								break;

							case '6':
								list(<s:property value='lstNRCSix'/>);
								break;

							case '7':
								list(<s:property value='lstNRCSeven'/>);
								break;

							case '8':
								list(<s:property value='lstNRCEight'/>);
								break;

							case '9':
								list(<s:property value='lstNRCNine'/>);
								break;
							case '10':
								list(<s:property value='lstNRCTen'/>);
								break;

							case '11':
								list(<s:property value='lstNRCEleven'/>);
								break;

							case '12':
								list(<s:property value='lstNRCTwelve'/>);
								break;

							case '13':
								list(<s:property value='lstNRCThirteen'/>);
								break;

							case '14':
								list(<s:property value='lstNRCFourteen'/>);
								break;

							default:
								$("#citizen_selection").html('');
								break;
							}
							$('#userRegister').bootstrapValidator(
									'revalidateField', 'citizen_selection');
						});

				function list(array_list) {
					$("#citizen_selection").html("");
					var o = new Option("Select", " ");
					$("#citizen_selection").append(o);
					$(array_list).each(
							function(i) {
								$("#citizen_selection").append(
										"<option value=\""+array_list[i].NRCName+"\">"
												+ array_list[i].NRCName
												+ "</option>");
							});
				}

			});
</script>
<script type="text/javascript">
	function callModalData() {
		var nrcOne = $('#parent_city_selection').val();
		var nrcTwo = $('#citizen_selection').val().trim();
		var nrcThree = $('#nrcType').val();
		var nrcFour = $('#checkNRC').val();
		var appnrc = nrcOne + '/' + nrcTwo + '(' + nrcThree + ')' + nrcFour;
		document.getElementById('mname').value = $('#appName').val();
		if (document.getElementById('appGender').checked) {
			document.getElementById('mgender').value = 'Female';
		} else {
			document.getElementById('mgender').value = 'Male';
		}
		document.getElementById('mdob').value = $('#datePicker').val();
		document.getElementById('mnrc').value = appnrc;
		document.getElementById('mphno').value = $('#appphno').val();
		document.getElementById('memail').value = $('#txtemail').val();
		document.getElementById('maddress').value = $('#appadd').val();
		document.getElementById('muni').value = $('#city_selection').val();
		document.getElementById('muniname').value = $('#uni_selection').val();
		if (document.getElementById('yesCheck').checked) {
			$('#mACPlanGroup').hide();
			document.getElementById('medu').value = 'Graduated';
		} else {
			$('#mACPlanGroup').show();
			document.getElementById('medu').value = 'Ungraduated';
		}
		if (document.getElementById('fec').checked) {
			document.getElementById('mfec').value = 'Yes';
		} else {
			document.getElementById('mfec').value = 'No';
		}
		if (document.getElementById('appex').checked) {
			document.getElementById('mexp').value = 'Yes';
		} else {
			document.getElementById('mexp').value = 'No';
		}
		if (document.getElementById('ACPlan_Check').checked) {
			$('#mgraduationDateGroup').show();
			document.getElementById('mACPlan').value = 'Yes';
		} else if (document.getElementById('ACPlan_NoCheck').checked) {
			$('#mgraduationDateGroup').hide();
			document.getElementById('mACPlan').value = 'No';
		} else {
			$('#mgraduationDateGroup').hide();
			document.getElementById('mACPlan').value = 'To Be Decided';
		}
		if (document.getElementById('appGICAcademy').checked) {
			document.getElementById('mGICAcademy').value = 'Yes';
		} else if (document.getElementById('appGICAcademy_NoCheck').checked) {
			document.getElementById('mGICAcademy').value = 'No';
		} else {
			document.getElementById('mGICAcademy').value = 'To Be Decided';
		}
		//zero yen study
		if (document.getElementById('zeroYenStudy').checked) {
			document.getElementById('mZeroYenStudy').value = 'Yes';
		} else if (document.getElementById('zeroYenStudy_NoCheck').checked) {
			document.getElementById('mZeroYenStudy').value = 'No';
		} else {
			document.getElementById('mZeroYenStudy').value = 'To Be Decided';
		}
		
		//zero yen study
		if (document.getElementById('jpCompanyExp').checked) {
			document.getElementById('mJpCompanyExp').value = 'Yes';
		} else {
			document.getElementById('mJpCompanyExp').value = 'No';
		}
		document.getElementById('mENG').value = $('#engskill').val();
		document.getElementById('mJP').value = $('#jpskill').val();
		document.getElementById('mexamplace').value = $('#examplace').val();
		document.getElementById('mgraduationDate').value = $('#egdatePicker').val();
	}
</script>
<script type="text/javascript">
	$(function() {
		$('input[name="app_FEC"]').on(
				'click',
				function() {
					if ($(this).val() == 'No') {
						$('#situationfe').show();
						$('#userRegister').bootstrapValidator(
								'revalidateField', 'ddlExperienceYears');
					} else {
						/* document.getElementById('fesituation').value = '';  */
						$('#situationfe').hide();
						$('input:radio[name="ddlFE"][value="No"]').prop('checked', true); // set No as checked
					}
				});
	});
</script>


<script>
function addExperienceRow() {
    const container = document.getElementById('additionalRows');

    const newRow = document.createElement('div');
    newRow.className = 'row mt-2 align-items-end experience-row';

    newRow.innerHTML =`
        <div class="col-sm-3">
            <div class="input-group">
                <input type="text" class="form-control experience-years" maxlength="2" />
                <span class="input-group-addon"><span>Years</span></span>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="form-group">
                <input type="text" class="form-control company" maxlength="50">
            </div>
        </div>
        <div class="col-sm-3">
            <div class="form-group">
                <div class="input-group">
                    <input type="text" class="form-control position" maxlength="50">
                    <span class="input-group-btn">
                        <button class="btn btn-danger" type="button" onclick="removeRow(this)">-</button>
                    </span>
                </div>
            </div>
        </div>
    `;

    container.appendChild(newRow);
    reindexFields();
}

function removeRow(button) {
    button.closest('.row').remove();
    updateExperienceFieldNames();
}

function updateExperienceFieldNames() {
    const rows = document.querySelectorAll('.experience-row');

    rows.forEach((row, index) => {
        
        const years = row.querySelector('.experience-years');
        const company = row.querySelector('.company');
        const position = row.querySelector('.position');

        if (years) years.setAttribute('name', 'experience[' + index + '].exp_Year');
        if (company) company.setAttribute('name', 'experience[' + index + '].exp_Company');
        if (position) position.setAttribute('name', 'experience[' + index + '].exp_Position');
    });
}

function reindexFields() {
	updateExperienceFieldNames();
    const rows = document.querySelectorAll('.experience-row');

    rows.forEach((row, index) => {

		if (index > 0) {
			$('#userRegister').bootstrapValidator(
			    'addField',
			    'experience[' + index + '].exp_Year',
			    {
			        validators: {
			            notEmpty: {
			                message: 'Experience Years is required'
			            },
			            stringLength: {
			                max: 2,
			                message: 'Maximum 2 digits only'
			            },
			            regexp: {
			                regexp: /^[1-9]\d*$/,
			                message: 'Only Enter Number.'
			            }
			        }
			    }
			);

			$('#userRegister').bootstrapValidator(
			    'addField',
			    'experience[' + index + '].exp_Company',
			    {
			        validators: {
			            notEmpty: {
			                message: 'Company Name is required'
			            },
			            stringLength: {
			                max: 100,
			                message: 'Maximum 100 characters allowed'
			            }
			        }
			    }
			);

			$('#userRegister').bootstrapValidator(
			    'addField',
			    'experience[' + index + '].exp_Position',
			    {
			        validators: {
			            notEmpty: {
			                message: 'Position is required'
			            },
			            stringLength: {
			                max: 50,
			                message: 'Maximum 50 characters allowed'
			            }
			        }
			    }
			);

		}
    });
}

</script>
</html>
