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
<title>メール送信</title>
<link href="src/customizeStyle.css" rel="stylesheet">
<script type="text/javascript" src="jquery/jquery-1.10.2.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>

<script>
	function validateSend() {
		var size = "${applicantList.size()}";
		var rtValue=false;
		if (size > 0) {
			var answer;
			answer = window.confirm("全員へ送信しますか？");
			if (answer == true) {
				rtValue=true;
			} 
		}
		return rtValue;
	}
</script>

<script>
	function setID(btnId) {
		var selectID = document.getElementById("selectID" + btnId).value;
		document.getElementById("sendID").value = selectID;
	}
</script>

<script>
	function validateSearch() {
		var rbt = "rbt";
		var jfYear = document.getElementById("ddlJFYear").value;
		var examPlace = document.getElementById("ddlExamPlace").selectedIndex;
		var returnValue = true;
		if (jfYear == 0) {
			document.getElementById('jfYearError').innerHTML = "JobFair年を選択してください。";
			returnValue = false;
		} else {
			document.getElementById('jfYearError').innerHTML = "";
		}

		if (examPlace == 0) {
			document.getElementById('examPlaceError').innerHTML = "受験場所を選択してください。";
			returnValue = false;
		} else {
			document.getElementById('examPlaceError').innerHTML = "";
		}

		var patt = /JF\d{4}[Y,M]{1}\d{4}/;
		var sID = document.getElementById("sExamID").value;
		if (sID.match(patt) || sID == "") {
			document.getElementById("sExamIDError").innerHTML = "";
		} else {
			document.getElementById("sExamIDError").innerHTML = "フォーマットが間違っています。 例.JF0000X0000 !";
			returnValue = false;
		}

		var eID = document.getElementById("eExamID").value;
		if (eID.match(patt) || eID == "") {
			document.getElementById("eExamIDError").innerHTML = "";
		} else {
			document.getElementById("eExamIDError").innerHTML = "フォーマットが間違っています。 例.JF0000X0000 !";
			returnValue = false;
		}

		var selectedRadio = $('input[name=' + rbt + ']:radio:checked').val();
		if (selectedRadio == '申請者' || selectedRadio == '面接者') {
			document.getElementById('radioError').innerHTML = "";
		} else {
			document.getElementById('radioError').innerHTML = "選択してください。";
			returnValue = false;
		}
		return returnValue;
	}
</script>
</head>
<body>
	<section class="contact-area bg-gray section-padding">
		<div class="container">
			<div class="row align-items-end">
				<form action="mailSendAction" method="POST">
					<div class="form-inline">
						<div class="row">
							<div class='col-lg-3'>
								<label style="margin-right: 19px;"><font color="red"
									style="font-weight: bold; font-size: large;">*</font>JobFair年:
								</label>

								<div class="form-group">
									<s:select headerKey="0" name="jfYear" list="lstJfYear"
										headerValue="Select" id="ddlJFYear" cssClass="form-control"
										style="width: 123px;" />
								</div>
							</div>

							<div class='col-lg-3'>
								<div class="form-group">
									<label style="margin-right: 29px;"><font color="red"
									style="font-weight: bold; font-size: large;">*</font>受験場所
										: </label>
								</div>
								<div class="form-group">									
									<s:select headerKey="0" headerValue="Select"
										list="lstExamPlace" listKey="examPlace" listValue="examPlace"
										name="examPlace" id="ddlExamPlace" cssClass="form-control"
										style="width: 123px;margin-right: 15px;" />
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
										cssClass="form-control" style="width: 123px;"  size="11" maxlength="11"/>
								</div>
							</div>

							<div class='col-lg-1'>
								<div class="form-group">
									<img src="img/core-img/tide.jpg" width="20px" />
								</div>
							</div>
							<div class='col-lg-2'>
								<div class="form-group">
									<s:textfield name="endExamID" id="eExamID"
										cssClass="form-control" style="width: 123px;"  size="11" maxlength="11"/>
								</div>
							</div>
						</div>

						<div class="row">
							<div class='col-lg-3'>
								<p id="jfYearError" style="color: red;"></p>
							</div>
							<div class='col-lg-3'>
								<p id="examPlaceError" style="color: red;"></p>
							</div>
							<div class='col-lg-3'>
								<p id="sExamIDError" style="color: red;"></p>
							</div>
							<div class='col-lg-3'>
								<p id="eExamIDError" style="color: red;"></p>
							</div>
						</div>

						<div class="row">

							<div class='col-lg-3'>
								<font color="red" style="font-weight: bold; font-size: large;">*</font>
								<s:radio list="#{'申請者':'申請者','面接者':'面接者'}" name="rbt"
									id="mailType"></s:radio>
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

						<div class="row">
							<div class='col-lg-3'>
								<p id="radioError" style="color: red;"></p>
							</div>
						</div>
					</div>

					<br> <br>
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
					<div class="row">
						<div class="form-group">
							<label>申請者数:</label>
							<s:property value="applicantList.size()"></s:property>
						</div>
					</div>

					<div class='table-cont' id='table-cont' style="margin-top: -10px;">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>受験ID</th>
									<th>名前</th>
									<th>メール</th>
									<th></th>
								</tr>
							</thead>
							<s:if test="%{applicantList.size()>0}">
								<s:iterator value="applicantList" var="row" status="status">
									<tbody id="myTable">
										<tr>
											<td><s:property value="exam_ID" /></td>
											<td><s:property value="app_Name" /></td>
											<td><s:property value="app_Email" /></td>
											<td><input type="hidden" name="selectID"
												value="<s:property value="app_ID" />"
												id="selectID<s:property value="%{#status.index}" />" />
												<button type="submit" name="btn" value="送信"
													id="<s:property value="%{#status.index}" />"
													onclick="setID(this.id);" class="btn btn-primary">送信</button>

											</td>
										</tr>
										<s:hidden name="IDList[%{#status.index}]"
											value="%{#row.app_ID}" />
									</tbody>
								</s:iterator>
							</s:if>
						</table>
					</div>
					<div class="row" style="float: right;">
						<button type="submit" name="btn" value="全員へ送信"
							onclick="return validateSend();" class="btn btn-primary">全員へ送信</button>
					</div>
					<input type="hidden" name="sendID" id="sendID" /> <input
						type="hidden" name="selectedMailType"
						value="<s:property value="selectedMailType" />" /> <input
						type="hidden" name="selectedExamPlace"
						value="<s:property value="selectedExamPlace" />" />
				</form>
			</div>
		</div>
	</section>
</body>
</html>