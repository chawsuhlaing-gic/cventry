<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="src/customizeStyle.css" rel="stylesheet">
<link href="css/CVForm.css" rel="stylesheet">
<script type="text/javascript" src="assets/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="jquery/CVForm.js"></script>
</head>
<style>
label {
    font-weight: normal !important;
}

@media screen and (max-width: 600px) {
  .column{
     padding-top: 20px;
  }#blah {
   margin: unset !important;
  }
  #cvphoto{
   margin: unset !important;
  }
 }
</style>
<body>
	<!-- form start -->
	<div class="container register-form">
		<div class="row">
			<div class="col-lg-12">
				<s:if test="hasActionMessages()">
					<div class="alert alert-success alert-dismissible" align="center">
						<strong><s:actionmessage /></strong>
					</div>
				</s:if>
				<s:if test="hasActionErrors()">
					<div class="alert alert-danger alert-dismissible" align="center">
						<strong><s:actionerror /></strong>
					</div>
				</s:if>
			</div>
		</div>
		<s:set var="btnName">${btnName}</s:set>
		<div class="form">
			<div class="note">
				<h2>
					<font color="white">履歴書情報<s:property value="btnName" /></font>
				</h2>
			</div>
			<div class="form-content">
				<form method="post" action="${AddEditAction}" enctype="multipart/form-data" id="userRegister" >

					<div class="row">
						<div class="col-sm-10">

							<div class="row">
								<div class="col-sm-4">
									<div class="form-group">
										<label><b>受験ID</b></label> <input type="text" id="appExamID"
											name="applicant.exam_ID" class="form-control" maxlength="30"
											size="30" value="${applicant.exam_ID}" disabled>
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<label><b><font color="red">*</font> 名前</b></label><br> <input
											type="text" name="app_Name" class="form-control" id="appName"
											maxlength="30" size="30" value="${applicant.app_Name}">
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-4">
									<div class="form-group">
										<label><b><font color="red">*</font> 性別</b></label><br> 
										    <input type="radio" name="app_Gender" value="male" 
											<s:if test='applicant.app_Gender == "male"'>checked</s:if>> 男性
		                         &nbsp;&nbsp;&nbsp; <input type="radio" name="app_Gender" value="female" id="appGender"
		                                    <s:if test='applicant.app_Gender == "female"'>checked</s:if>>女性
									</div>
								</div>
								<div class="col-sm-4">

									<label><b><font color="red">*</font> 生年月日</b></label><br>
									<div class="form-group">
										<input type="date" class="form-control" id="datePicker"
											name="app_dob" value='<s:property value="tmp_dob"/>'/> 
									</div>
									<p id="demo1"></p>
								</div>
							</div>

							<div class="row">
								<div class="nrc_Check">
									<div class="form-inline">
										<div class="col-sm-7 " style="width: 52%;">
											<div class="form-group">
												<label><b><font color="red">*</font> 個人番号</b> </label> <br>
												<div id="cssNRC">
												<s:select name="nrc_no1" headerKey=" " headerValue="Select"
													list="lstNRCCity" listKey="nrcCityID" listValue="nrcCity"
													cssClass="form-control" id="parent_city_selection"/>
												/ <select name="nrc_no2" id="citizen_selection"
													class="form-control">
													<option value=" ">Select</option> 
													<s:if test="%{'更新'==#btnName}">
													   <option value='<s:property value="nrc_no2"/>' selected>
													           <s:property value="nrc_no2"/>
													   </option>
												    </s:if>
												  </select> (
												 <s:select name="nrc_no3" headerKey=" " headerValue="Select"
													list="lstNRCType" listKey="nrcType" listValue="nrcType"
													cssClass="form-control" id="nrcType" />
												) <input type="text" name="nrc_no4" style="width:75px;"
                                                    class="form-control"  id="checkNRC" maxlength="6"
													value='<s:property value="nrc_no4"/>' autocomplete="off">
												 </div>
													<div id="checkNRCErr" style="color:red;"></div>
											
											</div>
										</div>
									</div>
								</div>
							</div>
							<br>
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label><b><font color="red">*</font> 電話番号【Hyphen無し】 </b></label> <input
											type="text" placeholder="09XXXXXXXXX" name="app_phoneNo"
											class="form-control" maxlength="11" size="15" id="appphno"
											value="${applicant.app_PhNo}">

									</div>
								</div>

								<div class="col-md-4">
									<div class="form-group">
										<label><b><font color="red">*</font> メール </b></label> 
										<input type="text" name="app_email" class="form-control" maxlength="50"
										value="${applicant.app_Email}" size="50" id="txtemail" autocomplete="off">
										<div id="checkmailErr" style="color:red;"></div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="form-group">
									<div class="col-md-8">
										<label><b><font color="red">*</font> 住所</b></label>
										<textarea rows="4" cols="35" name="app_address" maxlength="200"
										class="form-control" id="appadd"> ${applicant.app_Address}</textarea>
									</div>
								</div>
							</div>

							<br>
							<div class="divider"></div>
							<br>

							<div class="row">
								<div class="col-sm-3">
									<div class="form-group" style="margin-bottom:-1px;">
										<label><b><font color="red">*</font> 大学市 </b></label>
										<s:select name="app_CityofUniversity" headerKey=" "
											headerValue="Select" list="lstCityofUniversity"
											listKey="cityofUniName" listValue="cityofUniName"
											cssClass="form-control" id="city_selection"/>
									</div>
								</div>

								<div class="col-md-4">
									<div class="form-group" style="margin-bottom:-1px;">
										<label><b><font color="red">*</font> 大学名</b> </label>
										<select name="app_University" id="uni_selection" 
											class="form-control">
											<option value=" ">Select</option>
											<s:if test="%{'更新'==#btnName}">
											 <option value='${app_University}' selected>
											      <s:property value="app_University" />
										   </option>
										   </s:if>

										</select> 

									</div>
								</div>

								<div class="col-sm-3 ">
								<div class="form-group">
										<input type="text" placeholder="その他" name="txtOtherUni"
											id="otheruni" class="form-control" maxlength="30"
											style="display: none; margin-top: 26px;">
									</div>
								</div>
							</div>
                            <br>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label><b><font color="red">*</font> 教育</b></label><br> <input
											type="radio" onclick="yesnoCheck();" name="app_Education"
											id="yesCheck" value="Graduated" 
									        <s:if test='applicant.app_Education == "Graduated"'>checked</s:if>> 卒業 &nbsp;&nbsp; 
									        <input
											type="radio" onclick="yesnoCheck();" name="app_Education"
											id="noCheck" value="Ungraduated" 
											<s:if test='applicant.app_Education == "Ungraduated"'>checked</s:if>> 未卒業
									</div>

								</div>
							</div>

							<div class="row">
							    
							    <s:set var="edu">${applicant.app_Education}</s:set>
							    <s:if test="%{'Graduated'==#edu && '更新'==#btnName}">
							        <div id="degree" style="margin-top: -14px;">
									<div class="col-sm-3 ">
										<div id="ifYes">
										      <s:select id='yes' name="editddlDegree1" headerKey=" " headerValue="Select"
													list="lstDegree" listKey="degreeID" listValue="degreeName" 
													cssClass="form-control" style="margin-bottom: 10px;"/>
											
										</div>
									</div>

									<div class="form-group">
										<div class="col-sm-3 ">
											<input type="text" placeholder="その他/専攻" id="other"
												maxlength="30" class="form-control" name="edittxtOtherDegree"
												value="${edittxtOtherDegree}"/>

										</div>
									</div>
								</div>
							    </s:if>
								<div id="degree" style="display: none; margin-top: -14px;">
									<div class="col-sm-3 ">
										<div id="ifYes">
										      <s:select id='yes' name="ddlDegree1" headerKey=" " headerValue="Select"
													list="lstDegree" listKey="degreeID" listValue="degreeName" 
													cssClass="form-control" style="margin-bottom: 10px;"/>
											
										</div>
									</div>

									<div class="form-group">
										<div class="col-sm-3 ">
											<input type="text" placeholder="その他/専攻" id="other"
												maxlength="30" class="form-control" name="txtOtherDegree"
												style="display: none;" />

										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-3 ">
								    <s:if test="%{'Ungraduated'==#edu && '更新'==#btnName}">
								    <div id="degree1">
										<div class="form-group">
											<div id="ifNo">
											   <s:select id='no' name="ddlDegree2" headerKey=" " headerValue="Select"
													list="lstAttendYrs" listKey="attendYearID"
													listValue="attendYear" cssClass="form-control" />
											</div>
										</div>
									</div>
								    </s:if>
									<div id="degree1" style="display: none;">
										<div class="form-group">
											<div id="ifNo">
											   <s:select id='no' name="ddlDegree2" headerKey=" " headerValue="Select"
													list="lstAttendYrs" listKey="attendYearID"
													listValue="attendYear" cssClass="form-control" />
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
										<div class="col-sm-3">
											<s:if test="%{'Ungraduated'==#edu && '更新'==#btnName}">
												<div class="form-group" id="app_ACPlan">
													<label>
														<b>
															<font color="red">* </font>大学に進学する予定はありますか?
														</b>
													</label><br>
													<input type="radio" name="app_ACPlan" value="Yes" id="ACPlan_Check"
														<s:if test='applicant.app_ACPlan == "Yes"'>checked
											</s:if>>
											はい
											<input type="radio" name="app_ACPlan" value="No" id="ACPlan_NoCheck" <s:if
												test='applicant.app_ACPlan == "No"'>checked</s:if>>いいえ
											<input type="radio" name="app_ACPlan" value="To Be Decided" <s:if
												test='applicant.app_ACPlan == "To Be Decided"'>checked</s:if>>未定
										</div>
										</s:if>
										<div class="form-group" id="app_ACPlan" style="display: none;">
											<label>
												<b>
													<font color="red">* </font>大学に進学する予定はありますか?
												</b>
											</label><br>
											<input type="radio" name="app_ACPlan" value="Yes" id="ACPlan_Check">
											はい
											<input type="radio" name="app_ACPlan" value="No" id="ACPlan_NoCheck">いいえ
											<input type="radio" name="app_ACPlan" value="To Be Decided">未定
										</div>

									</div>
								</div>

								<div class="row">
									<div class="col-sm-12">
										<div id="graduateyear" style="display: none;">
											<label><b>
													<font color="red">* </font>卒業予定日
												</b></label><br>
											<font color="green">正確な日付が確定しない場合は、見積日を入力可能</font>
											<div class="row">
												<div class="col-sm-3">
													<!-- <input type="date" class="form-control" id="egdatePicker"
															name="ddlACPLan" />  -->
													<!-- <input type="date" class="form-control" id="egdatePicker"
														name="ddlACPLan" value='<s:property value="tmp_egd"/>' /><input
														type="hidden" id="dateValid"> -->
													<input type="date" class="form-control" id="egdatePicker"
														name="ddlACPLan" value="<s:property value='tmp_egd' />" />
													<input type="hidden" id="dateValid">
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
												title="Certificate of passing Fundamental Engineering Exam of Information Technology Professional Examination Council"><b>
													<font color="red">* </font> ITPEC FE 証明書はがありますか?
												</b></label><br>
											<s:a href="https://itpec.org/pastexamqa/fe.html" target="_blank"
												style="color: #2352A2;">What is
												ITPEC FE? Click here</s:a>
											<br> <input type="radio" name="app_FEC" value="Yes" id="fecYes" <s:if
												test='applicant.app_FEC == "Yes"'>checked</s:if>>はい <input type="radio"
												name="app_FEC" value="No" id="fecNo" <s:if
												test='applicant.app_FEC == "No"'>checked</s:if>>いいえ
										</div>
									</div>
								</div>
								<div class="row">
									<div id="situationfe" style="display: none">
										<div class="col-sm-8">
											<div class="form-group">
												<label><b>
														<font color="red">* </font>FE試験を受ける予定はありますか?
													</b></label>
												<div class="row">
													<div class="col-sm-6">
														<%-- <s:select headerKey="" headerValue="Select"
															list="lstFeSituation" listKey="feSituation" id="fesituation"
															listValue="feSituation" name="ddlFE" cssClass="form-control"
															required="required" /> --%>
														<input type="radio" name="ddlFE" value="Yes" id="fesituation"
															<s:if test='applicant.app_FEPlan == "Yes"'>checked</s:if>>
														<font color="black">はい</font>
														<input type="radio" name="ddlFE" value="No" <s:if
															test='applicant.app_FEPlan == "No"'>checked</s:if>>
														<font color="black">いいえ</font> <input type="radio" name="ddlFE"
															value="To Be Decided" <s:if
															test='applicant.app_FEPlan == "To Be Decided"'>checked
														</s:if>>
														<font color="black">未定</font>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							
							<div class="row">
								<div class="col-md-12">
										<div class="form-group">
											<label><b>
													<font color="red">* </font> GICアカデミー参加の意向
												</b></label><br>
											<input type="radio" name="app_GICAcademy" value="Yes" id="appGICAcademy"
												<s:if test='applicant.app_GICAcademy == "Yes"'>checked</s:if>>
											<font color="black">はい</font>
											<input type="radio" name="app_GICAcademy" value="No"
												id="appGICAcademy_NoCheck" <s:if
												test='applicant.app_GICAcademy == "No"'>checked</s:if>>
											<font color="black">いいえ</font>
											<input type="radio" name="app_GICAcademy" value="To Be Decided" <s:if
												test='applicant.app_GICAcademy == "To Be Decided"'>checked</s:if>>
											<font color="black">未定</font> <br> <br> <label>

												<div>
													<label><b>
															<font color="red">* </font>
															0円日本留学(GICアカデミー奨学金プログラム)に興味がありますか?
														</b></label><br>
													<s:a href="https://www.facebook.com/share/p/TLRJgp12P4kDMpp9/"
														target="_blank" style="color: #2352A2;">Facebook Post</s:a><br>
													<input type="radio" name="zero_yen_study" value="Yes"
														id="zeroYenStudy" <s:if
														test='applicant.zero_yen_study == "Yes"'>checked</s:if>>
													<font color="black">はい </font>
													<input type="radio" name="zero_yen_study" value="No"
														id="zeroYenStudy_NoCheck" <s:if
														test='applicant.zero_yen_study == "No"'>checked</s:if>>
													<font color="black">いいえ</font>
													<input type="radio" name="zero_yen_study" value="To Be Decided"
														<s:if test='applicant.zero_yen_study == "To Be Decided"'>checked
													</s:if>>
													<font color="black">未定</font>
												</div>
										</div>
									</div>
								</div>

							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<label><b><font color="red">*</font>添付ファイル </b></label><br> 
										<s:if test="%{'登録'==#btnName}">
										<input type="file" name="gradFile" accept="application/pdf,image/*" 
										       id="check" value=""/> 
										</s:if>	
										
										<s:if test="%{'更新'==#btnName}">
										<s:set var="perGrad">${perGrad}</s:set>
										<label for="editgradFile" class="custom-file-upload">
                                             <i class="fa fa-cloud-upload">Custom Upload</i> 
                                             <span id="perGrad"><s:property value="perGrad"/></span>
                                        </label>
                                        <input id="editgradFile" type="file" name="editgradFile" accept="application/pdf,image/*"  
                                        style="display:none;"/>
                                        <br>
                                        </s:if>
                                        <font color="green">※卒業証明書または学生証をアップロードしてください。<br>
                                                             (最大5MBまで。JPEG,PNGが使えます)</font>
                                        <input type="hidden" name="grad_Flag" value="${applicant.grad_Flag}"
											id="selectType" />      
									</div>
									</div>
								</div>

							<br>
							<div class="divider"></div>
							<br>

							<div class="row">
								<div class="col-sm-6 ">
									<div class="form-group">
										<label><b><font color="red">*</font>勤務経験</b> </label><br> 
										<s:if test="%{'登録'==#btnName}">
										<input type="radio" name="app_Experience" value="Yes" id="appex"> あり
										</s:if>
										<s:if test="%{'更新'==#btnName}">
										<input type="radio" name="app_Experience" value="Yes" id="appex"
											<s:if test='applicant.app_Experience != "No"'>checked</s:if>> あり
										</s:if>
										&nbsp;&nbsp; <input type="radio" name="app_Experience" value="No"
										    <s:if test='applicant.app_Experience == "No"'>checked</s:if>> なし

									</div>
								</div>
							</div>
							   <div class="row experience-row" id="textboxes">
							        <div class="col-sm-3">
							            <label><b><font color="red">* </font>勤務経験年数</b></label>
							        </div>							
							        <div class="col-sm-3">							      
							            <label><b>会社名</b></label>	
							        </div>							
							        <div class="col-sm-3">
						                <label><b>位置</b></label>
							        </div>
							        </div>
							   							    
							    <div class="row" id="exp_list">
							    <s:iterator value="experienceList" var="exp" status="rowStatus">
							    <div class="row experience-entry" style="margin-bottom:10px;">
							        <div class="col-sm-3">
							            <div class="input-group">
							                <input type="text" class="form-control" 
							                       name="experience[<s:property value="#rowStatus.index"/>].exp_Year"
							                       value="<s:property value="#exp.exp_Year"/>" maxlength="2"/> 
							                <span class="input-group-addon">年数</span>
							            </div>      
							        </div>
							        <div class="col-sm-3">
							            <input type="text" class="form-control" 
							                   name="experience[<s:property value="#rowStatus.index"/>].exp_Company"
							                   value="<s:property value="#exp.exp_Company"/>" maxlength="50" size="50">
							        </div>
							        <div class="col-sm-3">
							            <div class="input-group">
							                <input type="text" class="form-control" 
							                       name="experience[<s:property value="#rowStatus.index"/>].exp_Position"
							                       value="<s:property value="#exp.exp_Position"/>" maxlength="50" size="50">
							                <span class="input-group-btn">
							                    <s:if test="#rowStatus.first">
							                        <button class="btn btn-success" type="button" onclick="addExperienceRow()">+</button>
							                    </s:if>
							                    <s:else>
							                        <button class="btn btn-danger" type="button" onclick="removeRow(this)">-</button>
							                    </s:else>
							                </span>
							            </div>
							        </div>
							    </div>
							</s:iterator>
							</div>
						

							<div id="additionalRows"></div>
							<br>
							<div class="row">
								<div class="divider"></div>
								<br>
								<div class="col-md-4">
									<b>言語スキル </b> <br> <br>
									<div class="form-group">
										<label><b><font color="red">*</font> 英語 </b></label>
										     <s:select name="app_EngSkill" headerKey=" " headerValue="Select"
													list="lstEngSkill" listKey="englishLevel" id="engskill"
													listValue="englishLevel" cssClass="form-control" />
									</div>
								</div>
								<div class="col-md-4">
									<br> <br>
									<div class="form-group">
										<label><b><font color="red">*</font> 日本語</b> </label> 
										       <s:select name="app_JPSkill" headerKey=" " headerValue="Select"
													list="lstJPSkill" listKey="japaneseID" id="jpskill"
													listValue="japaneseLevel" cssClass="form-control" />
									</div>
								</div>
							</div>


							<div class="row">
								<div class="col-sm-4 ">
									<div class="form-group">
									    <label><b>添付ファイル  1</b></label><br>
									    <s:if test="%{'登録'==#btnName}">
										<input type="file" name="Lan1File" accept="application/pdf,image/*" 
										       id="checkLan1" /> 
										</s:if>	
										<s:else>
										<s:set var="perLan1">${perLan1}</s:set>
										<label for="checkLan1" class="custom-file-upload">
                                             <i class="fa fa-cloud-upload">Custom Upload</i>  
                                             <span id="perLan1"><s:property value="perLan1"/></span>                                           
                                        </label> 
                                        <input id="checkLan1" type="file" name="Lan1File" accept="application/pdf,image/*"  
                                        style="display:none;" />
                                        <br>
                                        </s:else>
										<font color="green">※証明書をアップロードしてください。(最大5MBまで。JPEG,PNGが使えます)</font>
										<input type="hidden" name="lan1_Flag" value="${applicant.lan1_Flag}" 
										id="selectTypeLan1">									   
									</div>

								</div>

								<div class="col-sm-4 ">
									<div class="form-group">
									    <label><b>添付ファイル 2</b></label><br>
									    <s:if test="%{'登録'==#btnName}">
										<input type="file" name="Lan2File" accept="application/pdf,image/*" 
										       id="checkLan2" /> 
										</s:if>	
										<s:else>
										<s:set var="perLan2">${perLan2}</s:set>
										<label for="checkLan2" class="custom-file-upload">
                                             <i class="fa fa-cloud-upload">Custom Upload</i> 
                                             <span id="perLan2"><s:property value="perLan2"/></span>
                                        </label>
										<input id="checkLan2" type="file" name="Lan2File" accept="application/pdf,image/*" 
										style="display:none;" /> 
										<br>
										</s:else>
										<font color="green">※証明書をアップロードしてください。(最大5MBまで。JPEG,PNGが使えます)</font>
										<input type="hidden" name="lan2_Flag" value="${applicant.lan2_Flag}" 
										id="selectTypeLan2">							
									</div>
								</div>
							</div>

							<br>
							<div class="divider"></div>
							<br>
							<div class="row">
								<div class="col-md-12" >
									<div class="form-group">
									 <b>ITスキル【普通選択可能】</b><br>
									 <br>
										<div class="box-sizing">
											<b>プログラミング :</b> <br>
											<br>
											<s:checkboxlist list="lstITProgramming"
												listKey="programmingID" listValue="programmingName"
												name="app_ITPSkill" listCssClass="checkbox-padding" 
												value="%{selectedCheckBoxList}" cssClass="mycheckbox"/>

											<hr>
											<b>デザイン :</b> <br>
											<br>
											<div class="mycheckbox">
											<s:checkboxlist list="lstITDesign"
												listKey="designID" listValue="designName"
												name="app_ITDSkill" listCssClass="checkbox-padding" 
												value="%{selectedCheckBoxList}" />
											</div>
											<hr>
											<b>データベース /オペレーティング・システム/サーバ :</b><br>
											<br>
											<s:checkboxlist list="lstITDatabase"
												listKey="dbServerID" listValue="dbServerName"
												name="app_ITDBSkill" listCssClass="checkbox-padding"
												value="%{selectedCheckBoxList}" />
											<hr>
											<b>その他 :</b> <br>
											<br>
											<s:checkboxlist list="lstITOthers"
												listKey="othersID" listValue="othersName"
												name="app_ITOSkill" listCssClass="checkbox-padding"
												value="%{selectedCheckBoxList}" />

										</div>

									</div>
								</div>
							</div>



							<div class="row">
								<div class="col-sm-8">
									<div class="form-group">
									    <label><b>添付ファイル </b></label><br>
									    <s:if test="%{'登録'==#btnName}">
										<input type="file" name="IT1File" accept="application/pdf,image/*" 
										       id="checkIT1" /> 
										</s:if>	
										<s:else>
										<s:set var="perIT1">${perIT1}</s:set>
										<label for="checkIT1" class="custom-file-upload">
                                             <i class="fa fa-cloud-upload"> Custom Upload</i> 
                                             <span id="perIT1"><s:property value="perIT1"/></span>
                                        </label>
                                        <input type="file" id="checkIT1" name="IT1File" accept="application/pdf,image/*" 
                                        style="display:none;"/> 
                                        <br>
										</s:else>
										<font color="green">※証明書をアップロードしてください。(最大5MBまで。JPEG,PNGが使えます)</font>
										<input type="hidden" name="IT1_Flag" value="${applicant.IT1_Flag}" id="selectTypeIT1" >
									</div>
								</div>
								<div class="col-sm-4 ">
								
								</div>
							</div>
							<br>
							<div class="divider"></div>
							<br>
							<div class="row">
								<div class="col-sm-3 ">
									<div class="form-group">
										<label><b><font color="red">*</font> 受験場所</b> </label>
										<s:if test="%{'登録'==#btnName}">
										<s:select name="app_ExamPlace" 
										    headerKey=" " headerValue="Select" id="examplace"
											list="lstExamPlace" listKey="examPlaceID"
											listValue="examPlace" cssClass="form-control" />
										</s:if>
										<s:if test="%{'更新'==#btnName}">
										<s:select name="app_ExamPlace" 
										    headerKey=" " headerValue="Select" id="examplace"
											list="lstExamPlace" listKey="examPlaceID"
											listValue="examPlace" cssClass="form-control" disabled="true" />
										</s:if>										

									</div>

								</div>

							</div>

							<font color="green">※Yangonを選択した場合、受験場所はYangonです。Mandalayを選択した場合、受験場所はMandalayです。</font>

						</div>
                        <div class="row">
						    <div class="col-sm-2 column">	
						 		<s:if test="%{'更新'==#btnName}">
							      <div class="row">	
								    <img id="blah" src="<s:property value="cvphoto"/>"  alt="your image"
								    style="margin-left: -80px;" />
								    </div> <div class="row">	
								    <label for="cvphoto" class="custom-file-upload" style="margin-left: -80px;">
                                          <i class="fa fa-cloud-upload"></i>Upload CV Photo
                                    </label>
							        <input type='file' id="cvphoto" name="editphoto" onchange="readURL(this);" 
							        style="display:none;">
							        </div>
							        <input type="hidden" name="app_ID" value="<s:property value='app_ID'/>">
							        <input type="hidden" name="app_ex_key" value="<s:property value='app_ex_key'/>">
							     </s:if>
							    <s:if test="%{'登録'==#btnName}">							        
							       <img id="blah" src="img/core-img/profile.png" alt="your image"
									style="margin-left: -80px;" > 
									<input type='file' id="cvphoto" name="photo" 
									style="margin-left: -80px;" onchange="getBase64Image()"/>
							    </s:if>
								<font color="green" style="margin-left:-80px;white-space: nowrap;">
								 最大5MBまで。JPEG,PNGが使えます。</font>
						    </div>
                     </div>
					</div>
					<br>
					<div class="form-group">
						<div class="text-center">
						    <s:if test="%{'更新'==#btnName}">
							<button name="btn"  id="submitBtn" class="btn btn-primary btn-md" value='<s:property value="btnName" />' >
							      <s:property value="btnName" />
							</button>
							</s:if>
							<s:if test="%{'登録'==#btnName}">
							 <button name="btn" id="submitBtn" class="btn btn-primary btn-md" value="登録" type="submit">登録</button>
                             <s:a href="CVFormAdd" cssClass="btn btn-danger btn-md"> キャンセル</s:a>                            
							</s:if>
						</div>
					</div>
			</form>
					
    <div class="modal fade" id="modal-1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background-color:#92bed4;">               
               　　　　　　　　　<label style="float:left;font-size:20px;">確認個人情報 </label>
            </div>
            <div class="modal-body">
                                                 　<label class=""> 以下の個人情報は正しいですか？受験番号をメールにチェックしてください。</label>
                 <div class="form-content">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label><b>名前  <i class="fa fa-user"></i></b></label>
                                <input type="text" class="form-control" id="mname" disabled/>
                            </div>
                            <div class="form-group">
                                <label><b>生年月日 <i class="fa fa-calendar"></i></b></label>
                                <input type="text" class="form-control" id="mdob" disabled/>
                            </div>
                            <div class="form-group">
                                <label><b>電話番号  <i class="fa fa-phone"></i></b></label>
                                <input type="text" class="form-control" id="mphno" disabled/>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label><b>性別  <i class="fa fa-venus-mars"></i></b></label>
                                <input type="text" class="form-control"  id="mgender" disabled/>
                            </div>
                            <div class="form-group">
                                <label><b>個人番号  <i class="fa fa-address-card"></i></b></label>
                                 <input type="text" class="form-control" id="mnrc" disabled/>
                            </div>
                            <div class="form-group">
                                <label><b>メール <i class="fa fa-envelope"></i></b></label>
                                <input type="text" class="form-control" id="memail" disabled/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                      <div class="col-md-12">
                            <div class="form-group">
                                <label><b>住所 <i class="fa fa-map"></i></b></label>
                                <textarea class="form-control" id="maddress" disabled></textarea>
                            </div>
                       </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label><b>大学市 <i class="fa fa-university"></i></b> </label>
                                <input type="text" class="form-control" id="muni" disabled/>
                            </div>
                        </div>
						<div class="col-md-6">
							<div class="form-group">
								<label><b>大学名 <i class="fa fa-university"></i> </b></label>
								<input type="text" class="form-control" id="muniname" disabled />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
                                <label><b>教育 <i class="fa fa-graduation-cap"></i></b></label>
                                <input type="text" class="form-control" id="medu" disabled/>
                            </div>
                        </div>
						<div class="col-md-6">
							<div class="form-group">
								<label><b>ITPEC FE 証明書がありますか? <i
											class="fa fa-graduation-cap"></i></b></label> <input
									type="text" class="form-control" id="mfec" disabled />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
                            <div class="form-group">
                                <label><b>英語レベル <i class="fa fa-diploma"></i></b></label>
                                <input type="text" class="form-control" id="mENG" disabled/>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label><b>日本語レベル <i class="fa fa-diploma"></i> </b></label>
								<input type="text" class="form-control" id="mJP" disabled />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6">
							<div class="form-group" id="mACPlanGroup">
								<label><b>大学に進学する予定はありますか?<i class="fa fa-check-square"></i>
									</b></label> <input type="text" class="form-control"
									id="mACPlan" disabled />
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group" id="mgraduationDateGroup">
								<label><b>卒業予定日 <i class="fa fa-calendar"></i></b></label>
								<input type="text" class="form-control" id="mgraduationDate"
									disabled />
							</div>
						</div>
						<div class="col-md-6">
                            <div class="form-group">
                                <label><b>経験 <i class="fa fa-check-square"></i></b></label>
                                 <input type="text" class="form-control" id="mexp" disabled/>
                            </div>
                        </div>
						<div class="col-md-6">
							<div class="form-group">
								<label><b>GICアカデミー参加の意向
										<i class="fa fa-map-pin"></i></b></label>
								<input type="text" class="form-control" id="mGICAcademy" disabled />
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label><b>0円日本留学<i class="fa fa-map-pin"></i></b></label>
								<input type="text" class="form-control" id="mZeroYenStudy"
									disabled />
							</div>
						</div>
                      <div class="col-md-6">
                            <div class="form-group">
                                <label><b>受験場所 <i class="fa fa-map-pin"></i></b></label>
                                <input type="text" class="form-control" id="mexamplace" disabled/>
                            </div>
                       </div>
                    </div>
                </div>
              
            </div>
            <div class="modal-footer">
               <button class="btn btn-primary btn-md" name="btn" id="formSubmit">  提出</button>
               <button type="button" class="btn btn-danger btn-md" id="modalClose" data-dismiss="modal">キャンセル</button>
            </div>
        </div>
    </div>
</div>

			</div>
		</div>
		<div class="row" style="float: right;">
					<s:url id="appAdminRBack" action="appAdminRBack"></s:url>
					<s:a href="%{appAdminRBack}" cssClass="btn btn-primary btn-md">戻る</s:a>
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
															message : '名前を入力してください 。'
														},

														regexp : {
															regexp : /^[a-zA-Z\s-, ]+$/,
															message : '名前を英語文字のみで入力してください。'
														}
													}
												},

												app_Gender : {

													validators : {
														notEmpty : {
															message : '性別を選択してください。'
														}
													}
												},

												parent_city_selection : {
													selector : '#parent_city_selection',
													group : '.nrc_Check',
													validators : {
														notEmpty : {
															message :'  '
														}
													}
												},

												citizen_selection : {
													selector : '#citizen_selection',
													group : '.nrc_Check',
													validators : {
														notEmpty : {
															message :'個人番号を選択してください。'
														}
													}
												},

												nrc_no3 : {
													group : '.nrc_Check',
													validators : {
														notEmpty : {
															message :' '
														}
													}
												},

												nrc_no4 : {
													group : '.nrc_Check',
													validators : {
														notEmpty : {
															message : '個人番号を選択してください。'
														},
														stringLength : {
															min:6,
															max:6,
															message:'正しい個人番号の数字を入力してください。'
														},
														regexp : {
															regexp : /^[0-9]*$/,
															message : '個人番号＿番号を数字文字のみで入力してください。'
														}

													}
												},

												app_dob : {
													validators : {
														notEmpty : {
															message : '生年月日を選択してください。'
														},
														date : {
															format : 'MM/DD/YYYY',
															message : '日付フォーマット間違っています。'
														},
														callback: {
									                        message: 'The password is not valid',
									                        callback: function(value, validator, $field) {
									                        var parts = value.split("-");
									                        var dtCurrent = new Date();
									                        
									                     // Same yrs
								                            if (parts[0] == dtCurrent.getFullYear()) {
								                                return {
								                                    valid: false,
								                                    message: '過去の日付を入力してください。'
								                                }
								                            }
									                        // Less than 18 yrs
									                        else if ((dtCurrent.getFullYear()-parts[0]) < 18) {
								                                return {
								                                    valid: false,
								                                    message: '17歳以下であれば申請できません。'
								                                };
								                            }
								                            // Less than 18 yrs
									                        else if ((dtCurrent.getFullYear()-parts[0]) > 65) {
								                                return {
								                                    valid: false,
								                                    message: '65歳以上であれば申請できません。'
								                                };
								                            }
								                            return true;
									                        }
									                   }

													}
												},

												app_phoneNo : {
													validators : {
														notEmpty : {
															message : '電話番号を入力してください。'
														},

														regexp : {
															regexp : /^(?:0|\s?)([9](?:[\s]?\d){6,9}$|[1](?:[\s]?\d){6}$)/,
															message : '電話番号のフォーマットが間違っています。'
														}

													}
												},

												app_email : {
													validators : {
														notEmpty : {
															message : 'メールを入力してください。'
														},
														required : true,
														regexp : {
															regexp : /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{3})+$/,
															message : 'メールのフォーマットが間違っています。'
														}
													}
												},

												app_address : {
													validators : {
														notEmpty : {
															message : '住所を入力してください。'
														},
														regexp : {
															regexp : /[a-zA-Z\s-,().!@#$%^&*():{}/\|<>]+$|[0-9]+$/,
															message : '英字 (a-z) と半角数字 (0-9) のみ許可されています。'
														}

													}
												},

												city_selection : {
													selector : '#city_selection',
													group : '.uniCheck',
													validators : {
														notEmpty : {
															message : '大学市を選択してください。'
														}
													}
												},
												uni_selection : {
													selector : '#uni_selection',
													group : '.uniCheck',
													validators : {
														notEmpty : {
															message : '大学名を選択してください。 '
														}
													}
												},

												txtOtherUni : {
													validators : {
														notEmpty : {
															message : '   '
														}
													}
												},

												app_Education : {
													validators : {
														notEmpty : {
															message : '教育を選択してください。'
														}
													}
												},

												ddlDegree1 : {
													validators : {
														notEmpty : {
															message : '学位を選択してください。'
														}
													}
												},

												ddlDegree2 : {
													validators : {
														notEmpty : {
															message : '昨年度を選択してください。'
														}
													}
												},

												check : {
													selector : '#check',
													validators : {
														notEmpty : {
															message : '添付ファイルを入力してください。'
														},

													}
												},

												app_Experience : {

													validators : {
														notEmpty : {
															message : '経験を選択してください。'
														}
													}
												},

												experience_year : {
													
													validators : {
														notEmpty : {
															message : '経験年数を入力してください。'
														},
														stringLength : {
															max:2,
															message:''
														},
														regexp : {
															regexp : /^[1-9]\d*$/,
															message : '経験年数のフォーマットが間違っています。'
														}
													}
												},

												engskill : {
													selector : '#engskill',
													validators : {
														notEmpty : {
															message : '英語レベルを選択してください。'
														}
													}
												},

												jpskill : {
													selector : '#jpskill',
													validators : {
														notEmpty : {
															message : '日本語レベルを選択してください。'
														}
													}
												},

												photo : {
													validators : {
														notEmpty : {
															message : '写真をアップロードしてください。'
														}
													}
												},

												examplace : {
													selector : '#examplace',
													validators : {
														notEmpty : {
															message : '受験場所を選択してください。'
														}
													}
												},

										},
								onSuccess: function (e) {
								        e.preventDefault();
								        $("#submitBtn").attr("disabled", true); 
								        var nrcOne = $('#parent_city_selection').val();
								        var nrcTwo = $('#citizen_selection').val().trim();
								        var nrcThree = $('#nrcType').val();
								        var nrcFour =  $('#checkNRC').val();
								        var appnrc = nrcOne+'/'+nrcTwo+'('+nrcThree+')'+nrcFour;
								        var email=document.getElementById( "txtemail" ).value;	
								        if($('#submitBtn').val()== '更新'){
								        	var app_examid=document.getElementById( "appExamID" ).value;
								        }
								    	$.ajax({
								  		    type : "GET",
								  		    url : "<s:url action='checkDuplicateData'/>",
								  			data : {
								  				app_nrc : appnrc,
								  				appMail : email,
								  				    btn : $('#submitBtn').val(),
								  		      appExamID : app_examid
								  			},
								  			success : function(responseText) {
								  				if (responseText == "mailDuplicate"){
								  				    $('#txtemail').val(null);
								  					alert("メールアドレスがデータベースに重複しています。")
								  					document.getElementById("txtemail").style.borderColor="#a94442";
								  				    document.getElementById("checkmailErr").innerHTML = "メールを入力してください。";
								  				    $('#modal-1').modal('hide');
								  				    $("#submitBtn").attr("disabled", false);
								  				    
								  				}else if (responseText == "nrcDuplicate"){
								  					 $('#checkNRC').val(null); 
													 alert("個人番号がデータベースに重複しています。")
													 document.getElementById("cssNRC").style.border="3px solid #a94442";	
						                             document.getElementById("checkNRC").style.borderColor="#a94442";
						                             document.getElementById("checkNRCErr").innerHTML = "個人番号をもう一度選択してください。";
													 $('#modal-1').modal('hide');
													 $("#submitBtn").attr("disabled", false);
								  				}else if (responseText == "bothDuplicate"){
								  					 $('#checkNRC').val(null); 
								  					 $('#txtemail').val(null);
													 alert("Your NRC number & Email Address are already used!")
													 $("#submitBtn").attr("disabled", false);
													 document.getElementById("cssNRC").style.border="3px solid #a94442";	
						                             document.getElementById("checkNRC").style.borderColor="#a94442";
						                             document.getElementById("checkNRCErr").innerHTML = "NRC is required";
						                             document.getElementById("txtemail").style.borderColor="#a94442";
									  				 document.getElementById("checkmailErr").innerHTML = "Email address is required";
						                              $('#modal-1').modal('hide');
								  				}else if(responseText == "NO"){
								  					if(nrcTwo == ""){
								  						document.getElementById("checkNRCErr").innerHTML = "個人番号NRC(2)を選択してください。";
								  					}else if(nrcFour != '' && email != ''){
								  						$('#modal-1').modal('show');
														callModalData();
													} //Final
								  				}				
								  			}
								  	     });								        						     								       								        
								},
								onFailure: function (e) {
								    $('#modal-1').modal('hide');
								}
							});
						// Validate the form manually
						$('#submitBtn').click(function() {
							$('#userRegister').bootstrapValidator('validate');
								
						});
					});
</script>
<script language="javascript" type="text/javascript">
		$(document)
				.ready(
						function() {
							
							$(document)
							.ready(
									function() {
										$('#uni_selection')
												.change(
														function() {
															
															document
																	.getElementById('otheruni').value = '';
															if ($(
																	'#uni_selection')
																	.val() === 'Other') {
																$(
																		'#otheruni')
																		.show();
																document
																		.getElementById('otheruni').value = null;

															} else {

																$('#otheruni').hide();

															}
														});
									});
							//If parent option is changed
							$("#city_selection").change(function() {

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
									list(<s:property value='lstaywUni'/>);
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
								$('#userRegister').bootstrapValidator('revalidateField', 'uni_selection');
							});

							//function to populate child select box
							function list(array_list) {
								$("#uni_selection").html(""); //reset child options
								var o = new Option("Select"," ");
						        $("#uni_selection").append(o);
								$(array_list)
										.each(
												function(i) { //populate child options 
													$("#uni_selection")
															.append(
																	"<option value=\""+array_list[i].UniName+"\">"
																			+ array_list[i].UniName
																			+ "</option>");
												});
							}

				});
</script>
<script language="javascript" type="text/javascript">
		$(document)
				.ready(
						function() {	
							//If parent option is changed
							$("#parent_city_selection").change(function() {
								var parent = $(this).val(); //get option value from parent 

								switch (parent) { //using switch compare selected option and populate child
								case '1':
									list(<s:property value='lstnrcOne'/>);
									break;
								case '2':
									list(<s:property value='lstnrcTwo'/>);
									break;
								case '3':
									list(<s:property value='lstnrcThree'/>);
									break;
								case '4':
									list(<s:property value='lstnrcFour'/>);
									break;
								case '5':
									list(<s:property value='lstnrcFive'/>);
									break;
								case '6':
									list(<s:property value='lstnrcSix'/>);
									break;
								case '7':
									list(<s:property value='lstnrcSeven'/>);
									break;
								case '8':
									list(<s:property value='lstnrcEight'/>);
									break;
								case '9':
									list(<s:property value='lstnrcNine'/>);
									break;
								case '10':
									list(<s:property value='lstnrcTen'/>);
									break;
								case '11':
									list(<s:property value='lstnrcEleven'/>);
									break;
								case '12':
									list(<s:property value='lstnrcTwelve'/>);
									break;
								case '13':
									list(<s:property value='lstnrcThirteen'/>);
									break;
								case '14':
									list(<s:property value='lstnrcFourteen'/>);
									break;

								default: //default child option is blank
									$("#citizen_selection").html('');
									break;
								}
								$('#userRegister').bootstrapValidator('revalidateField', 'citizen_selection');
							});

							//function to populate child select box
							function list(array_list) {
								$("#citizen_selection").html(""); //reset child options
						        var o = new Option("Select"," ");
						        $("#citizen_selection").append(o);
								$(array_list).each(
										function(i) { //populate child options 
											$("#citizen_selection").append(
													"<option value=\""+array_list[i].NRCName+"\">"
															+ array_list[i].NRCName
															+ "</option>");
										});
							}
				});
</script>
<script type="text/javascript">
	$(function() {
		$('input[name="app_Experience"]').on('click', function() {
			if ($(this).val() == 'Yes') {
				$('#textboxes').show();
				$('#userRegister').bootstrapValidator('revalidateField', 'experience_year');
				$('#additionalRows').show();
				$('#exp_list').show();
			} else {
				document.getElementById('expYears').value = '';
				document.getElementById('cur_com').value = '';
				document.getElementById('cur_pos').value = '';
				$('#textboxes').hide();
				$('#additionalRows').hide();
				$('#exp_list').hide();
			}
		});
	});
</script>
<%-- <script type="text/javascript">
	$(function() {
		$('input[name="app_Exp"]').on(
				'click',
				function() {
					if ($(this).val() == 'Yes') {
						$('#textboxes').show();
						$('#additionalRows').show();
						$('#exp_list').show();
					} else {
						$('#textboxes').hide();
						$('#additionalRows').hide();
						$('#exp_list').hide();
					}
				});
	});
</script> --%>
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

	$("#cvphoto").change(function() {
		var ext = cvphoto.value.split('.');
	    ext = ext[ext.length-1].toLowerCase();      
	    var arrayExtensions = ['jpg' , 'jpeg', 'png'];
	    if (arrayExtensions.lastIndexOf(ext) == -1) {
	        alert('正しいファイルタイプをアップロードしてください。');
	        document.getElementById("blah").src="img/core-img/profile.png";
	        this.value = '';	       
	        return false;
	   }
	   else {
		   if(this.files[0].size > 1024 * 1024 * 5) {
	           alert("ファイルサイズが 5MB を超えています。");
	           document.getElementById("blah").src="img/core-img/profile.png";
	           this.value = '';
			   return false;
	        }else{
	        	readURL(this);
	        }
	   }	  
	});

</script>
<script type="text/javascript">
$("#btnReset").click(function() {
	document.getElementById("blah").src="img/core-img/profile.png";
});
</script>
<script language="javascript">
	var uploadField = document.getElementById("check");
	uploadField.onchange = function() {
		if (this.files[0].size > 1024 * 1024 * 5) {
			alert("ファイルサイズが 5MB を超えています。");
			this.value = "";
			selectType.value = "";
		} else {
			var ext = check.value.split('.');
			ext = ext[ext.length - 1].toLowerCase();
			var arrayExtensions = [ 'jpg', 'jpeg', 'png'];
			if (arrayExtensions.lastIndexOf(ext) == -1) {
				alert("正しいファイルタイプをアップロードしてください。");
				this.value = '';
				selectType.value = "";
			} else {
				selectType.value = check.value.split('.')[1];
			}
		}
		;
	};
</script>
<script language="javascript">   
    var uploadField = document.getElementById("editgradFile");
    uploadField.onchange = function() {
    if(this.files[0].size > 1024 * 1024 * 5){
       alert("ファイルサイズが 5MB を超えています。");
       this.value = "";
       selectType.value = "";
       document.getElementById("perGrad").textContent = "0%";
    }else{
    	var ext = editgradFile.value.split('.');
        ext = ext[ext.length-1].toLowerCase();      
        var arrayExtensions = ['jpg' ,'jpeg', 'png'];
        if (arrayExtensions.lastIndexOf(ext) == -1) {
            alert('正しいファイルタイプをアップロードしてください');
            this.value = '';
            selectType.value = "";
            document.getElementById("perGrad").textContent = "0%";
         }
        else{
        	selectType.value=editgradFile.value.split('.')[1];
        	document.getElementById("perGrad").textContent = "100%";
       }
    };
  }; 
</script>
<script language="javascript">
	var uploadField = document.getElementById("checkLan1");
	uploadField.onchange = function() {
		if (this.files[0].size > 1024 * 1024 * 5) {
			alert("ファイルサイズが 5MB を超えています。");
			this.value = "";
			selectTypeLan1.value = "";
			document.getElementById("perLan1").textContent = "0%";
		} else {
			var ext = checkLan1.value.split('.');
			ext = ext[ext.length - 1].toLowerCase();
			var arrayExtensions = [ 'jpg', 'jpeg', 'png' ];
			if (arrayExtensions.lastIndexOf(ext) == -1) {
				alert("正しいファイルタイプをアップロードしてください");
				this.value = '';
				selectTypeLan1.value = "";
				document.getElementById("perLan1").textContent = "0%";
			} else {
				selectTypeLan1.value = checkLan1.value.split('.')[1];
				document.getElementById("perLan1").textContent = "100%";
			}
		}
		;
	};
</script>
<script language="javascript">
	var uploadField = document.getElementById("checkLan2");
	uploadField.onchange = function() {
		if (this.files[0].size > 1024 * 1024 * 5) {
			alert("ファイルサイズが5MBを超えています。");
			this.value = "";
			selectTypeLan2.value = "";
			document.getElementById("perLan2").textContent = "0%";
		} else {
			var ext = checkLan2.value.split('.');
			ext = ext[ext.length - 1].toLowerCase();
			var arrayExtensions = [ 'jpg', 'jpeg', 'png'];
			if (arrayExtensions.lastIndexOf(ext) == -1) {
				alert("正しいファイルタイプをアップロードしてください。");
				this.value = '';
				selectTypeLan2.value = "";
				document.getElementById("perLan2").textContent = "0%";
			} else {
				selectTypeLan2.value = checkLan2.value.split('.')[1];
				document.getElementById("perLan2").textContent = "100%";
			}
		}
		;
	};
</script>
<script language="javascript">
	var uploadField = document.getElementById("checkIT1");
	uploadField.onchange = function() {
		if (this.files[0].size > 1024 * 1024 * 5) {
			alert("ファイルサイズが 5MB を超えています。");
			this.value = "";
			selectTypeIT1.value = "";
			document.getElementById("perIT1").textContent = "0%";
		} else {
			var ext = checkIT1.value.split('.');
			ext = ext[ext.length - 1].toLowerCase();
			var arrayExtensions = [ 'jpg', 'jpeg', 'png'];
			if (arrayExtensions.lastIndexOf(ext) == -1) {
				alert("正しいファイルタイプをアップロードしてください。");
				this.value = '';
				selectTypeIT1.value = "";
				document.getElementById("perIT1").textContent = "0%";				
			} else {
				selectTypeIT1.value = checkIT1.value.split('.')[1];
				document.getElementById("perIT1").textContent = "100%";
			}
		}
		;
	};
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#yes').change(function() {
			if ($('#yes').val() === ' ') {
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
			document.getElementById('app_ACPlan').style.visibility = 'visible';

		} else {
			document.getElementById('ifNo').style.visibility = 'hidden';
			document.getElementById('app_ACPlan').style.visibility = 'hidden';
		}
	}

</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('input:radio[name=app_Education]').change(function() {
			if (this.value == 'Graduated') {
				$('#degree').show();
				$('#degree1').hide();
				$('#app_ACPlan').hide();
				$('#graduateyear').hide();
				$('input:radio[name="app_ACPlan"][value="No"]').prop('checked', true); // set No as checked
				document.getElementById('egdatePicker').value = "";
				$("#no").val($("#no option:first").val());
				$('#userRegister').bootstrapValidator('revalidateField', 'ddlDegree1');
			}
			else if (this.value == 'Ungraduated') {
				$('#degree').hide();
				$('#degree1').show();
				$('#app_ACPlan').show();
				$("#yes").val($("#yes option:first").val());
				$('#other').hide();
				document.getElementById('other').value = '';
				$('#userRegister').bootstrapValidator('revalidateField', 'ddlDegree2');
			}
		});
		$('input:radio[name=app_ACPlan]').change(
			function () {
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
$('#txtemail').keypress(function(){
	  document.getElementById("checkmailErr").innerHTML = " ";
	  document.getElementById("txtemail").style.borderColor="#3c763d";
	  $('#checkmailErr').text(" ");
	  
});
$('#checkNRC').keypress(function(){
	document.getElementById("checkNRC").style.borderColor="#3c763d";
    document.getElementById("cssNRC").style.border="none";	                    
    document.getElementById("checkNRCErr").innerHTML = " ";
});
document.getElementById("citizen_selection").onchange = function (){
	 document.getElementById("checkNRCErr").innerHTML = " ";
}
$( "#formSubmit" ).click(function() {
	 event.preventDefault();
	 this.disabled=true;
	 document.getElementById('userRegister').submit();
	 $("#modalClose").attr("disabled", true);
	 $("#submitBtn").attr("disabled", true);
});
$( "#modalClose" ).click(function() {
	 $("#submitBtn").attr("disabled", false);
});
</script>
<script type="text/javascript">
function callModalData(){
	   var nrcOne = $('#parent_city_selection').val();
	   var nrcTwo = $('#citizen_selection').val().trim();
	   var nrcThree = $('#nrcType').val();
	   var nrcFour =  $('#checkNRC').val();
	   var appnrc = nrcOne+'/'+nrcTwo+'('+nrcThree+')'+nrcFour;
		   document.getElementById('mname').value = $('#appName').val();
		   if(document.getElementById('appGender').checked){
			   document.getElementById('mgender').value = '女性';
		   } else{
			   document.getElementById('mgender').value = '男性';		   
		   }
		   document.getElementById('mdob').value = $('#datePicker').val();
		   document.getElementById('mnrc').value = appnrc;
		   document.getElementById('mphno').value = $('#appphno').val();
		   document.getElementById('memail').value = $('#txtemail').val();
		   document.getElementById('maddress').value = $('#appadd').val();
		   document.getElementById('muni').value = $('#city_selection').val();
		   document.getElementById('muniname').value = $('#uni_selection').val();
		   document.getElementById('mgraduationDate').value = $('#egdatePicker').val();
		   var planGroup = document.getElementById('mACPlanGroup');
		   if (document.getElementById('yesCheck').checked) {
			   planGroup.style.display = 'none';
			   document.getElementById('medu').value = '卒業';
		   }else{
			   planGroup.style.display = 'block';
			   document.getElementById('medu').value = '未卒業';
		   }
		   if ( document.getElementById('appex').checked){
			   document.getElementById('mexp').value ='あり';
		   } else {
			   document.getElementById('mexp').value ='なし'; 
		   }	   
		   document.getElementById('mENG').value = $('#engskill').val();
		   document.getElementById('mJP').value =  $('#jpskill').val();
		   document.getElementById('mexamplace').value = $('#examplace').val();
		   if (document.getElementById('fecYes').checked) {
				document.getElementById('mfec').value = 'はい';
			} else {
				document.getElementById('mfec').value = 'いいえ';
			}
			var graduationGroup = document.getElementById('mgraduationDateGroup');
			if (document.getElementById('ACPlan_Check').checked) {
				document.getElementById('mACPlan').value = 'はい';
				graduationGroup.style.display = 'block';
			} else if (document.getElementById('ACPlan_NoCheck').checked) {
				graduationGroup.style.display = 'none';
				document.getElementById('mACPlan').value = 'いいえ';
			} else {
				graduationGroup.style.display = 'none';
				document.getElementById('mACPlan').value = '未提';
			}
			if (document.getElementById('appGICAcademy').checked) {
				document.getElementById('mGICAcademy').value = 'はい';
			} else if (document.getElementById('appGICAcademy_NoCheck').checked) {
				document.getElementById('mGICAcademy').value = 'いいえ';
			} else {
				document.getElementById('mGICAcademy').value = '未提';
			}
			//zero yen study
			if (document.getElementById('zeroYenStudy').checked) {
				document.getElementById('mZeroYenStudy').value = 'はい';
			} else if (document.getElementById('zeroYenStudy_NoCheck').checked) {
				document.getElementById('mZeroYenStudy').value = 'いいえ';
			} else {
				document.getElementById('mZeroYenStudy').value = '未提';
			}
		}

</script>
<script type="text/javascript">
	$(function () {
		$('input[name="app_FEC"]').on(
			'click',
			function () {
				if ($(this).val() == 'No') {
					$('#situationfe').show();
					$('#userRegister').bootstrapValidator(
						'revalidateField', 'ddlExperienceYears');
				} else {
					/* document.getElementById('fesituation').value = '';  */
					$('#situationfe').hide();
				}
			});
	});
</script>

<script type="text/javascript">
	$(function () {
		$('input[name="app_FEC"]').on(
			'click',
			function () {
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
	// Get references to the radio buttons and the situationfe div
	const acYes = document.getElementById("ACPlan_Check");
	const acNo = document.getElementById("ACPlan_NoCheck");
	const graduateyear = document.getElementById("graduateyear");

	// Add event listeners to toggle visibility
	acYes.addEventListener("change", function () {
		if (acYes.checked) {
			graduateyear.style.display = "none";
		}
	});

	acNo.addEventListener("change", function () {
		if (acNo.checked) {
			graduateyear.style.display = "block";
		}
	});

	// Initialize visibility on page load based on current selection
	document.addEventListener("DOMContentLoaded", function () {
		if (acYes.checked) {
			graduateyear.style.display = "block";
		} else {
			graduateyear.style.display = "none";
		}
	});
</script>

<script>
	// Get references to the radio buttons and the situationfe div
	const fecYes = document.getElementById("fecYes");
	const fecNo = document.getElementById("fecNo");
	const situationfe = document.getElementById("situationfe");

	// Add event listeners to toggle visibility
	fecYes.addEventListener("change", function () {
		if (fecYes.checked) {
			situationfe.style.display = "none";
		}
	});

	fecNo.addEventListener("change", function () {
		if (fecNo.checked) {
			situationfe.style.display = "block";
		}
	});

	// Initialize visibility on page load based on current selection
	document.addEventListener("DOMContentLoaded", function () {
		if (fecNo.checked) {
			situationfe.style.display = "block";
		} else {
			situationfe.style.display = "none";
		}
	});
</script>

<script type="text/javascript">
	// Get the value of tmp_egd from the Struts tag.
	var tmp_egd = "<s:property value='tmp_egd' />";
	console.log(tmp_egd);

	// Check if the value is not empty or null
	if (tmp_egd !== null && tmp_egd !== "" && tmp_egd !== "null") {
		// Set the value of the input date field
		document.getElementById('egdatePicker').value = tmp_egd;
	} else {
		// Optionally clear or set a default value if tmp_egd is empty or null
		console.log("here");
		document.getElementById('egdatePicker').value = ""; // or a default date like '2025-01-01'
	}
</script>

<script>
/* function addExperienceRow() {
    const container = document.getElementById('additionalRows');

    const newRow = document.createElement('div');
    newRow.className = 'row mt-2 align-items-end experience-row';

    newRow.innerHTML =`
    	<div class="row ">
	        <div class="col-sm-3">
	            <div class="input-group">
	                <input type="text" class="form-control experience-years" maxlength="2" />
	                <span class="input-group-addon">年数</span>
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
	    </div>    
    `;

    container.appendChild(newRow);
    reindexFields();
} */


function addExperienceRow() {
    // Get the container of experience entries
    const container = document.querySelectorAll('.experience-entry');
    const lastRow = container[container.length - 1];
    const newRow = lastRow.cloneNode(true); // Clone the last row

    // Get the new index based on how many rows exist
    const newIndex = container.length;

    // Update input names and clear values
    newRow.querySelectorAll('input').forEach(input => {
        // Use RegExp to replace index in name attribute
        input.name = input.name.replace(/\[\d+\]/, '['+newIndex+']');
        input.value = ""; // clear the value
    });

    // Update buttons: remove "+" from cloned row, add "-"
    const buttonGroup = newRow.querySelector('.input-group-btn');
    if (buttonGroup) {
        buttonGroup.innerHTML = `<button class="btn btn-danger" type="button" onclick="removeRow(this)">-</button>`;
    }

    // Insert the new row after the last one
    lastRow.parentNode.insertBefore(newRow, lastRow.nextSibling);
}

function removeRow(button) {
    button.closest('.row').remove();
    reindexFields();
}

function reindexFields() {
    const rows = document.querySelectorAll('.experience-entry');
    rows.forEach((row, index) => {
        const year = row.querySelector('input[name*=".exp_Year"]');
        const company = row.querySelector('input[name*=".exp_Company"]');
        const position = row.querySelector('input[name*=".exp_Position"]');

        if (year) year.setAttribute('name', 'experience['+index+'].exp_Year');
        if (company) company.setAttribute('name', 'experience['+index+'].exp_Company');
        if (position) position.setAttribute('name', 'experience['+index+'].exp_Position');

        // Add class names if missing
        if (year) year.classList.add('experience-years');
        if (company) company.classList.add('company');
        if (position) position.classList.add('position');
    });
}

// Ensure the fields in the initial row have the correct class names on load
document.addEventListener('DOMContentLoaded', () => {
    const initialRow = document.getElementById('textboxes');
    initialRow.querySelector('input[name="experience[0].years"]').classList.add('experience-years');
    initialRow.querySelector('input[name="experience[0].app_CurrentCom"]').classList.add('company');
    initialRow.querySelector('input[name="experience[0].app_CurrentPos"]').classList.add('position');

    //reindexFields();
});
</script>
</html>