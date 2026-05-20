<%@page import="mm.com.gic.ces.application.model.Company"%>
<%@page import="java.util.List"%>
<%@page import="mm.com.gic.ces.base.service.admin.ExportCVService"%>
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
<sx:head />
<title>履歴書出力</title>
<link href="src/customizeStyle.css" rel="stylesheet">
<script type="text/javascript" src="jquery/jquery-1.10.2.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>

<script>
	function validateExport() {
		var size = "${applicantList.size()}";
		var rtValue=false;
		if (size > 0) {
			var answer;
			answer = window.confirm("出力しますか？");
			if (answer == true) {
				rtValue=true;
			} 
		}
		return rtValue;
	}
</script>

</head>
<body>
	<section class="contact-area bg-gray section-padding">
		<div class="container">
			<div class="row align-items-end">
				<form action="exportCVAction" method="POST">
					<div class="form-inline">
						<div class="row">
							<div class='col-lg-3'>
								<label style="margin-right: 19px;"><font color="red"
									style="font-weight: bold; font-size: large;">*</font>JobFair年:
								</label>
								<div class="form-group">
									<s:select headerKey="0" name="jfYear" list="lstJfYear"
										headerValue="Select" id="ddlJFYear" cssClass="form-control"
										style="width: 123px;" onChange="this.form.submit()" />

								</div>
								<p id="jfYearError" style="color: red;"></p>
							</div>

							<div class='col-lg-3'>
								<div class="form-group">
									<label style="margin-right: 29px;"><font color="red"
										style="font-weight: bold; font-size: large;">*</font>受験場所 : </label>
								</div>
								<div class="form-group">
									<s:select headerKey="0" headerValue="Select"
										list="lstExamPlace" listKey="examPlace" listValue="examPlace"
										name="examPlace" id="ddlExamPlace" cssClass="form-control"
										style="width: 123px;margin-right: 15px;" />
								</div>
								<p id="examPlaceError" style="color: red;"></p>
							</div>

							<div class='col-lg-1'>
								<div class="form-group">
									<label>申込日 : </label>
								</div>
							</div>
							<div class='col-lg-2'>
								<div class="form-group">
									<sx:datetimepicker name="startDate" displayFormat="yyyy-MM-dd"
										cssClass="form-control" style="width: 123px;" />
								</div>
							</div>
							<div class='col-lg-1'>
								<div class="form-group">
									<img src="img/core-img/tide.jpg" width="20px" />
								</div>
							</div>

							<div class='col-lg-2'>
								<div class="form-group">
									<sx:datetimepicker name="endDate" displayFormat="yyyy-MM-dd"
										cssClass="form-control" style="width: 123px;" />
									<p></p>
								</div>
							</div>
						</div>
						<div class="row">
							<div class='col-lg-3'>
								<div class="form-group">
									<label style="margin-right: 4px;">第一希望会社 :</label>
								</div>
								<div class="form-group">
									<s:if test="%{lstCompany.size()>0}">
										<s:select name="firstCompany" list="lstCompany"
											listKey="com_ID" listValue="com_Sname" headerKey="0"
											headerValue="Select" cssClass="form-control"
											style="width: 123px" />
									</s:if>
									<s:else>
										<s:select name="firstCompany" list="lstCompany" headerKey="0"
											headerValue="Select" cssClass="form-control"
											style="width: 123px" />
									</s:else>
								</div>
							</div>

							<div class='col-lg-3'>
								<div class="form-group">
									<label style="margin-right: 7px;">第二希望会社 :</label>
								</div>
								<div class="form-group">
									<s:if test="%{lstCompany.size()>0}">
										<s:select name="secondCompany" list="lstCompany"
											listKey="com_ID" listValue="com_Sname" headerKey="0"
											headerValue="Select" cssClass="form-control"
											style="width: 123px" />
									</s:if>
									<s:else>
										<s:select name="secondCompany" list="lstCompany" headerKey="0"
											headerValue="Select" cssClass="form-control"
											style="width: 123px" />
									</s:else>
								</div>
							</div>
							<div class='col-lg-1'>
								<div class="form-group">
									<label>受験ID : </label>
								</div>
							</div>
							<div class='col-lg-2'>
								<div class="form-group">
									<s:textfield name="startExamID" id="sExamID"
										cssClass="form-control" style="width: 123px;" size="11" maxlength="11"/>
								</div>
								<p id="sExamIDError" style="color: red;"></p>
							</div>

							<div class='col-lg-1'>
								<div class="form-group">
									<img src="img/core-img/tide.jpg" width="20px" />
								</div>
							</div>
							<div class='col-lg-2'>
								<div class="form-group">
									<s:textfield name="endExamID" id="eExamID"
										cssClass="form-control" style="width: 123px;" size="11" maxlength="11"/>
								</div>
								<p id="eExamIDError" style="color: red;"></p>
							</div>
						</div>
						<div class="row">
							<div class='col-lg-1'>
								<s:checkbox name="chkExaminee" label=" 受験者">
								</s:checkbox>
							</div>
							<div class='col-lg-1'>
								<s:checkbox name="chkInterview" label="合格者">
								</s:checkbox>
							</div>
							<div class='col-lg-1'>
								<s:checkbox name="chkEmployee" label="採用者">
								</s:checkbox>
							</div>
							<div class='col-lg-3'></div>
							<div class='col-lg-3'></div>

							<div class='col-lg-3' style="padding-left: 34px;">
								<div class="form-group">
									<button type="submit" name="btn" value="検索" id="btnSearch"
										onclick="return validateSearch();" class="btn btn-primary"
										style="width: 100px;">検索</button>
								</div>
								<div class="form-group">
									<button type="submit" name="btn" value="キャンセル"
										class="btn btn-danger" style="width: 100px;">キャンセル</button>
								</div>
							</div>
							<div class='col-lg-1'></div>
						</div>
					</div>

					<br> <br>
					<div class="row">
						<div class="form-group">
							<label>申請者数:</label>
							<s:property value="applicantList.size()"></s:property>
						</div>
					</div>

					<s:if test="hasActionMessages()">
						<div class="row" style="margin-bottom: 10px;">
							<div class="alert alert-success alert-dismissible" align="center">
								<strong><s:actionmessage /></strong>
							</div>
						</div>
					</s:if>
					<s:if test="hasActionErrors()">
						<div class="row" style="margin-bottom: 10px;">
							<div class="alert alert-danger alert-dismissible" align="center">
								<strong><s:actionerror /></strong>
							</div>
						</div>
					</s:if>

					<div class='table-cont' id='table-cont' style="margin-top: -10px;">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>受験ID</th>
									<th>名前</th>
									<th>性別</th>
									<th>年齢</th>
									<th>個人番号</th>
									<th>学位</th>
									<th>大学</th>
									<th>メール</th>
									<th>受験場所</th>
									<th>第一希望会社</th>
									<th>第二希望会社</th>
								</tr>
							</thead>
							<s:if test="%{applicantList.size()>0}">
								<s:iterator value="applicantList" var="row" status="status">
									<tbody id="myTable">
										<tr>
											<td><s:property value="applicant.exam_ID" /></td>
											<td><s:property value="applicant.app_Name" /></td>
											<td><s:property value="applicant.app_Gender" /></td>
											<td><s:subset start="%{#status.index}" count="1"
													source="lstAge">
													<s:iterator>
														<s:property />
													</s:iterator>
												</s:subset></td>
											<td><s:property value="applicant.app_Nrc" /></td>
											<%-- <td><s:property value="applicant.app_Degree" /></td> --%>
											<td>
										    <s:if test="applicant.app_Education == 'Graduated'">
										        <s:property value="applicant.app_Degree" />
										    </s:if>
										    <s:else>
										        <s:property value="applicant.app_ACYear" />
										    </s:else>
										</td>
											<td><s:property value="applicant.app_University" /></td>
											<td><s:property value="applicant.app_Email" /></td>
											<td><s:property value="applicant.app_ExamPlace" /></td>
											<td><s:property value="examinee.firstCompany.com_Sname" /></td>
											<td><s:property value="examinee.secondCompany.com_Sname" /></td>
										</tr>
										<s:hidden name="IDList[%{#status.index}]"
											value="%{#row.applicant.app_ID}" />
									</tbody>
								</s:iterator>
							</s:if>
						</table>
					</div>
					<div class="row" style="float: right;">
						<button type="submit" name="btn" value="履歴書出力"
							onclick="return validateExport();" class="btn btn-primary">履歴書出力</button>
					</div>
				</form>
			</div>
		</div>
	</section>
</body>

</html>