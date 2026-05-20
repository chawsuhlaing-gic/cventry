<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja-jp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CV Entry</title>
<link href="src/customizeStyle.css" rel="stylesheet">
<link href="css/CVForm.css" rel="stylesheet">
<script type="text/javascript" src="jquery/CVForm.js"></script>
<script type="text/javascript" src="jquery/jquery-1.10.2.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script type="text/javascript" src="modal/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="modal/js/popper.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/list.js/1.5.0/list.min.js"></script>
<script type="text/javascript" src="js/paginathing.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/JqueryCommon.js"></script>
<sx:head />

<script type="text/javascript">
	$(document).ready(function() {

		var model = document.getElementById("basicExampleModal");
		var isOkToDisplay = "<s:property value="isModelDisplay" />";
		if (isOkToDisplay === "true") {
			model.className = "modal";
			model.style.display = "block";
		} else {
			model.className = "modal fade";
			model.style.display = "none";
		}
	});
	function CheckSelect() {
		document.getElementById("app_form").action = "SuccessfulInterviewerSelectAction";
	}

	function SubmitActionReset() {
		document.getElementById("app_form").action = "SuccessfulInterviewerAction";
	}
</script>
</head>
<body>
	<section class="contact-area bg-gray section-padding">
	<div id="users">
		<div class="container">
			<div class="row align-items-end">
				<form action="SuccessfulInterviewerAction" id="app_form"
					method="post" name="form">
					<input type="hidden" name="chkSelect" id="hiddenField" /> <br>
					<input type="hidden" name="checkedExamID"
						value="<s:property value='checkedExamID' />" /> <br>
					<div class="form-inline" style="width: 90%;">
						<div>
							<div class='col-sm-5 padding' style="width: 30%;">
								<div class="form-group">
									<label><font color="red" style="font-weight:bold;font-size:large;">*</font>JobFair年
										:</label>
									<s:select name="ddlJobFairYear" list="jfYear" headerKey=""
										headerValue="Select" cssClass="form-control textbox_width"
										style="width: 123px;margin-left: 5px;" id="ddlJFYear"
										onChange="this.form.submit()" />
									<p id="jfYearError" style="color: red;"></p>
								</div>
							</div>
							<div class='col-sm-5 padding' style="width: 25%;">
								<div class="form-group">
									<label>申込日:</label>
									<sx:datetimepicker name="startDate" displayFormat="yyyy-MM-dd"
										id="startDate" cssClass="form-control" style="width: 123px;" />
								</div>
							</div>
							<div class='col-sm-5 padding' style="width: 8%;">
								<div class="form-group">
									<img src="img/core-img/tide.jpg" width="20px" />
								</div>
							</div>
							<div class='col-sm-5 padding' style="width: 20%;">
								<div class="form-group">
									<sx:datetimepicker name="endDate" displayFormat="yyyy-MM-dd"
										id="endDate" cssClass="form-control" style="width: 123px;" />
									<p></p>
								</div>
							</div>
						</div>
						<div>
							<div class='col-sm-5 padding'>
								<div class="form-group"></div>
							</div>
							<div class='col-sm-5 padding' style="width: 49%;">
								<div class="form-group"></div>
							</div>
						</div>
						<div>
							<div class='col-sm-5 padding' style="width: 30%;">
								<div class="form-group">
									<label><font color="red" style="font-weight:bold;font-size:large;">*</font>受験場所:</label>
									<s:select headerKey="" headerValue="Select" list="lstExamPlace"
										listKey="examPlace" listValue="examPlace" name="ddlExamPlace"
										cssClass="form-control" style="width: 123px;margin-left: 20px;"
										id="ddlExamPlace"/>
									<p id="examPlaceError" style="color: red;"></p>
								</div>
							</div>
							<div class='col-sm-5 padding' style="width: 25%;">
								<div class="form-group">
									<label>受験ID:</label>
									<s:textfield name="txtStartExamID" cssClass="form-control"
										style="width: 123px;" 
										id="sExamID" size="11" maxlength="11" />
									<p id="sExamIDError" style="color: red;"></p>
								</div>
							</div>
							<div class='col-sm-5 padding' style="width: 8%;">
								<div class="form-group">
									<img src="img/core-img/tide.jpg" width="20px" />
								</div>
							</div>
							<div class='col-sm-5 padding' style="width: 20%;">
								<div class="form-group">
									<s:textfield name="txtEndExamID" cssClass="form-control"
										style="width: 123px" id="eExamID" size="11" maxlength="11" />
									<p id="eExamIDError" style="color: red;"></p>
								</div>
							</div>
						</div>
						<div>
							<div class='col-sm-5 padding' style="width: 49%;">
								<div class="form-group"></div>
							</div>
							<div class='col-sm-5 padding' style="width: 49%;">
								<div class="form-group"></div>
							</div>
						</div>
						<div>
							<div class='col-sm-5 padding' style="width: 30%;">
								<div class="form-group">
									<label>第一希望会社:</label>
									<s:if test="%{lstCompany.size()>0}">
										<s:select headerKey="" name="firstCompany" list="lstCompany"
											listKey="com_Sname" listValue="com_Sname"
											headerValue="Select" cssClass="form-control"
											style="width: 123px;" />
									</s:if>
									<s:else>
										<s:select name="firstCompany" list="lstCompany" headerKey="0"
											headerValue="Select" cssClass="form-control"
											style="width: 123px" />
									</s:else>
									<p ></p>

								</div>
							</div>
							<div class='col-sm-5 padding' style="width: 25%;">
								<div class="form-group">
									<label>IQ点数:</label>
									<s:textfield name="lowestIQMark" cssClass="form-control"
										style="width: 123px;margin-left: 2px;" size="11" maxlength="3" onkeypress='return restrictAlphabets(event)' />
									<p id="" style="color: red;"></p>
								</div>
							</div>
							<div class='col-sm-5 padding' style="width: 8%;">
								<div class="form-group">
									<img src="img/core-img/tide.jpg" width="20px" />
								</div>
							</div>
							<div class='col-sm-5 padding' style="width: 20%;">
								<div class="form-group">
									<s:textfield name="highestIQMark" cssClass="form-control"
										style="width: 123px" size="11" maxlength="3" onkeypress='return restrictAlphabets(event)' />
									<p ></p>
								</div>
							</div>
						</div>
						<div>
							<div class='col-sm-5 padding'>
								<div class="form-group"></div>
							</div>
							<div class='col-sm-5 padding' style="width: 49%;">
								<div class="form-group"></div>
							</div>
						</div>
						<div>
							<div class='col-sm-5 padding' style="width: 30%;">
								<div class="form-group">
									<label>第二希望会社:</label>
									<s:if test="%{lstCompany.size()>0}">
										<s:select headerKey="" name="secondCompany" list="lstCompany"
											listKey="com_Sname" listValue="com_Sname"
											headerValue="Select" cssClass="form-control"
											style="width: 123px;" />
									</s:if>
									<s:else>
										<s:select name="secondCompany" list="lstCompany" headerKey="0"
											headerValue="Select" cssClass="form-control"
											style="width: 123px" />
									</s:else>
									<p></p>
								</div>
							</div>
							<div class='col-sm-5 padding' style="width: 25%;">
								<div class="form-group"></div>
							</div>
							<div class='col-sm-5 padding' style="width: 10%;">
								<div class="form-group">
									<s:submit type="button" value="検索"
										class="btn btn-primary btn_search" name="btn"
										onclick="return validateSearch();" style="width: 175%;"
										id="btnSearch" />
								</div>
							</div>
							<div class='col-sm-5 padding' style="width: 10%;">
								<div class="form-group">
									<s:submit type="button" value="キャンセル" class="btn btn-danger"
										name="btn" />
								</div>
							</div>
						</div>
						<div class='col-sm-5 padding' style="width: 49%;">
							<div class="form-group"></div>
						</div>

						<div class="row">
							<div class="col-sm-3">
								<input type="checkbox" id="checkAll"><label>すべて選択</label>
							</div>
							<div class="col-sm-3">
								<label>画面上チェック数  :<b style="margin-left: 10px;"></b></label>
							</div>
							<div class="col-sm-3">
								<label >合格者人数  :<span style="margin-left: 10px;"><s:property value="count" /></span></label>
							</div>
							<div class="col-sm-4"></div>
						</div>
						<br>
					</div>
					<div class="grid-container">
						<s:if test="hasActionMessages()">
							<div class="alert alert-success alert-dismissible" align="center">
								<strong><s:actionmessage /></strong>
							</div>
						</s:if>
						<ul class="list">
							<s:iterator value="Interviewerlist" status="stat">
								<s:if test="interview_Pass.charAt(0) == 'Y'">
									<s:set var="YesOrNo" value="true" />
								</s:if>
								<s:else>
									<s:set var="YesOrNo" value="false" />
								</s:else>
								<li class="list--list-item"><s:checkbox
										fieldValue="%{examinee.applicant.exam_ID}"
										name="CheckedIDList" id="checkcount" theme="simple"
										value="YesOrNo" /><label class="search"> <s:property
											value="examinee.applicant.exam_ID" /></label></li>
							</s:iterator>
						</ul>
						<s:else>
							<s:if test="hasActionErrors()">
								<div class="alert alert-danger alert-dismissible" align="center">
									<strong><s:actionerror /></strong>
								</div>
							</s:if>
						</s:else>
					</div>
					<br>
					<div class="row">
						<div class="col-sm-12">
							 <span class="pull-right"> 
								<button type="submit" value="保存" name="btn"
									class="btn btn-primary" onclick="CheckSelect();" style="width: 100px;margin-left: 25%;">保存</button>
							</span>
						</div>
					</div>
					<!-- Modal -->
					<input type="hidden" name="checkvalue" id="checkvalue" />
					<div class="modal fade" id="basicExampleModal" tabindex="-1"
						role="dialog" aria-labelledby="exampleModalLabel"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">
										<center><s:property value="Msg" /></center>
									</h5>
									<button type="button" class="close" onclick="ModalClose();"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<font color="red"><s:property value="warmMsg" /></font>
								</div>
								<div class="modal-body">
								<s:if test="checkedInterviewList.size()>0">
									<div class="row">
										<div class="panel panel-default panel-table"
											style="padding-top: -12px; margin-top: -15px;">
											<div class="panel-body">
												<table id="t1" class="table table-bordered table-list">
													<thead id="t2" >
											<tr>
												<th style="width: 195px;">ID</th>
												<th style="width: 195px;">Name</th>
												<th>NRC</th>
											</tr>
										</thead>
													<tbody>
														<s:iterator value="checkedInterviewList" status="stat">
															<tr id="t2" >
																<td><s:property value="examinee.applicant.exam_ID" /></td>
																<td><s:property value="examinee.applicant.app_Name" /></td>
																<td><s:property value="examinee.applicant.app_Nrc" /></td>
															</tr>
														</s:iterator>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									</s:if>
					               <s:else>
			                         <s:property value="selectSize" />
						       </s:else>
								</div>
								<div class="modal-footer">
									<button type="submit" class="btn btn-primary" name="btn"
										value="はい" onclick="SubmitActionReset();">はい</button>
									<button type="button" class="btn btn-secondary"
										onclick="ModalClose();">いいえ</button>
								</div>
							</div>
						</div>
					</div>
					<!-- Modal -->
				</form>
			</div>
		</div>
	</div>
	</section>
</body>
</html>

