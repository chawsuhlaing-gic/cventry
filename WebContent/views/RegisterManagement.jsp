<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="src/customizeStyle.css" rel="stylesheet">
<script type="text/javascript" src="jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<sx:head />
</head>
<body>
	<section class="contact-area bg-gray section-padding">
		<div class="container">
			<div class="row align-items-end">
				<form action="appAdminSearch" method="post" name="form">
					<fieldset>
					  <div class="form-inline">
							<div class="row">
								<div class='col-sm-3'>
									<div class="form-group">
										<label><font color="red" style="font-weight:bold;font-size:large;">*</font> JobFair年 :</label>
										<s:select name="ddlJobFairYear" list="lstJFYear" headerKey="0"
											headerValue="Select" cssClass="form-control"
											style="width: 123px;" id="ddlJFYear" />
										<p id="jfYearError" style="color: red;"></p> 
									</div>
								</div>
								<div class='col-sm-4 padding' style="width: 20%;">
									<div class="form-group">
										<label>申込日 : </label>
										<sx:datetimepicker name="startDate" displayFormat="yyyy-MM-dd"
											cssClass="form-control" style="width:123px;" />
									</div>
								</div>
						         <div class='col-sm-4 padding' style="width: 4%;">
							          <div class="form-group">
								             <img src="img/core-img/tide.jpg" width="20px" />
							          </div>
						         </div>
						         <div class='col-sm-4 padding' style="width: 20%;">
							        <div class="form-group">
										<sx:datetimepicker name="endDate" displayFormat="yyyy-MM-dd"
											cssClass="form-control" style="width:123px;" />
									</div>
								</div>
								<!-- Zan Yai Htet //Landing Page 画面へ行くボタン 
								<div class='col-sm-3 padding' style="width: 20%;">
							        <s:a href="LandingPageEdit" cssClass="btn btn-primary btn-lg" style="font-size:18px;font-weight:normal;">ホームページ設定</s:a>
								</div>　-->
							</div>							
							<br>
							<div class="row">
								<div class='col-sm-3'>
									<div class="form-group">
										<label><font color="red" style="font-weight:bold;font-size:large;">*</font> 受験場所 :&ensp;</label>
										<s:select name="ddlExamPlace" list="lstExamPlace"
											listKey="examPlaceID" headerKey="0" headerValue="Select"
											id="ddlExamPlace" listValue="examPlace"
											cssClass="form-control" style="width:123px;" />
										<p id="examPlaceError" style="color: red;"></p>		 
									</div>
								</div>
								 <div class='col-sm-5 padding' style="width: 20%;">
									<div class="form-group" >
										<label>受験ID :</label>
										<s:textfield name="startExamID" cssClass="form-control"
										id="sExamID" size="11" maxlength="11" style="width: 123px" />
										<p id="sExamIDError" style="color: red;"></p>
										</div>
						          </div>
								   <div class='col-sm-5 padding' style="width: 4%;">
							                 <div class="form-group">
								                <img src="img/core-img/tide.jpg" width="20px" />
							                  </div>
						            </div>
						           <div class='col-sm-5 padding' style="width: 20%;">
							           <div class="form-group">
										<s:textfield name="endExamID" cssClass="form-control"
										 id="eExamID" size="11" maxlength="11" style="width: 123px" />
										<p id="eExamIDError" style="color: red;"></p>
									</div>
								</div>
							</div>
							<br>
					  </div>
					  <div class="row">
							<div class="col-md-5" style="width: 45%;"></div>
							<div class="form-group">
							<button type="submit" name="btn" value="検索"
								class="btn btn-primary btn-md"
								onclick="return validateSearch();" id="btnSearch">検索</button>
							<button type="submit" name="btn" value="キャンセル"
								class="btn btn-danger btn-md">キャンセル</button>
						   </div>						
					 </div>
				   </fieldset>
					<br>
					<br>
					<div class="row" style="margin-left: 10px;">
						<label>登録者数:</label>
						<s:if test="%{lstApplicant.size()>0}">
							<s:property value="lstApplicant.size()" />
						</s:if>
						<s:else>0</s:else>
					</div>
					<div class="row">
						<div class="col-lg-12">
							<s:if test="hasActionMessages()">
								<div class="alert alert-success alert-dismissible"
									align="center">
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
					<div class='table-cont' id='table-cont'>
						<table class="table3 table table-striped">
							<thead>
								<tr>
									<th>受験ID</th>
									<th>名前</th>
									<th>個人番号</th>
									<th>性別</th>
									<th>年齢</th>
									<th>電話番号</th>
									<th>学位</th>
									<th>大学名</th>
									<th>大学市</th>
									<th>経験年数</th>
									<th>日本会社経験</th>
									<th>日本語スキル</th>
									<th>英語スキル</th>
									<th>メール</th>
									<th style="width:10% !important;">住所</th>
									<th>受験場所</th>
									<th>申込日</th>
									<th></th>
									<th></th>
								</tr>
							</thead>

							<s:iterator value="lstApplicant" var="row" status="status">
								<tbody id="myTable">
									<tr>
										<td><s:property value="exam_ID" /></td>
										<td><s:property value="app_Name" /></td>
										<td><s:property value="app_Nrc" /></td>
										<td><s:property value="app_Gender" /></td>
										<td><s:if test="%{app_Age.size()>0}">
												<s:subset start="%{#status.index}" count="1"
													source="app_Age">
													<s:iterator>
														<s:property />
													</s:iterator>
												</s:subset>
											</s:if></td>
										<td><s:property value="app_PhNo" /></td>
										<%-- <td><s:property value="app_Degree" /></td> --%>
										<td>
										    <s:if test="app_Education == 'Graduated'">
										        <s:property value="app_Degree" />
										    </s:if>
										    <s:else>
										        <s:property value="app_ACYear" />
										    </s:else>
										</td>
										<td><s:property value="app_University" /></td>
										<td><s:property value="app_CityofUniversity" /></td>
										<td><s:property value="app_Experience" /></td>
										<td><s:property value="jp_company_exp" /></td>
										<td><s:property value="app_JPSkill" /></td>
										<td><s:property value="app_ENGSkill" /></td>
										<td><s:property value="app_Email" /></td>
										<td><s:property value="app_Address" /></td>
										<td><s:property value="app_ExamPlace" /></td>
										<td><s:property value="app_RegDate.toString()" /></td>
										<td><s:url id="CVFormEdit" action="CVFormEdit">
												<s:param name="app_ID" value="app_ID" />
												<s:param name="app_ex_key" value="app_ex_key" />
											</s:url> <s:a href="%{CVFormEdit}" cssClass="btn btn-primary lnkbtn">
												<i class="fa fa-edit"> 編集</i>
											</s:a></td>
										<td><s:url id="appAdminRemove" action="appAdminRemove">
												<s:param name="app_ID" value="app_ID" />
												<s:param name="app_ex_key" value="app_ex_key" />
											</s:url> <s:a href="%{appAdminRemove}"
												cssClass="btn btn-danger lnkbtn"
												onclick="return confirm('削除しますか？');">
												<i class="fa fa-remove"> 削除</i>
											</s:a></td>
									</tr>
								</tbody>
								<s:hidden name="lstApplicantID[%{#status.index}]" value="%{#row.app_ID}" />
							</s:iterator>

						</table>
					</div>
					<br>
					<br>
					<div class="row" style="float: right;">
					   <div class="col-md-4">
						  <s:a href="appAdminDelete" cssClass="btn btn-primary btn-md">削除リスト</s:a>
					   </div>
					   <div class="col-md-4">
						  <s:a href="CVFormAdd" cssClass="btn btn-primary btn-md">新規登録</s:a>
					   </div>
					    <div class="col-md-4">
						   <button type="submit" name="btn" value="Excel出力"
						              class="btn btn-primary btn-md">Excel出力</button>
						</div>
					</div>
				</form>
			</div>
		</div>
</section>
</body>
</html>