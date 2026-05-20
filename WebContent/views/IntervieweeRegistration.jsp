<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Interview Registration</title>
<script type="text/javascript" src="jquery/jquery-1.10.2.min.js"></script>
<link href="src/customizeStyle.css" rel="stylesheet">
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
	function checkDate(input) {
		var inputDate = new Date(input.value);
		var CurrentDate = new Date();
		if (input.value == '') {
			input.setCustomValidity('面接日 を選択してください。')
			return false;
		} else if (inputDate <= CurrentDate) {
			input.setCustomValidity('当日以降の日付を選択してください。')
			return false;
		} else {
			input.setCustomValidity('')
			return true;
		}
	}
	function checkSTime(startTime) {
		var sTime = startTime.value;
		if (startTime.value == '') {
			startTime.setCustomValidity('開始時間 を入力してください。')
			return false;
		} else if (sTime < "08:00") {
			startTime.setCustomValidity('午前8時から午後5時の間の入力してください。')
			return false;
		} else if (sTime >= "17:00") {
			startTime.setCustomValidity('午前8時から午後5時の間の入力してください。')
			return false;
		} else {
			startTime.setCustomValidity('')
			return true;
		}
	}
	function CheckValid(btnId) {
		var startTime = document.getElementById("ST" + btnId).value;
		var endTime = document.getElementById("ET" + btnId).value;
		if (endTime == '') {
			document.getElementById("ET" + btnId).setCustomValidity('終了時間 を入力してください。')
			return false;
		} else if (endTime <= "08:00") {
			document.getElementById("ET" + btnId).setCustomValidity('午前8時から午後5時の間の入力してください。')
			return false;
		} else if (endTime > "17:00") {
			document.getElementById("ET" + btnId).setCustomValidity('午前8時から午後5時の間の入力してください。')
			return false;
		} else if (startTime >= endTime) {
			document.getElementById("ET" + btnId).setCustomValidity('終了時刻が不正です。');
			return false;
		} else {
			document.getElementById("ET" + btnId).setCustomValidity('');
			return true;
		}
	}
</script>
<sx:head />
</head>
<section class="contact-area bg-gray section-padding">
<div class="container">
	<div class="row align-items-end">
		<form action="SearchExaminee" method="post" name="form">
			<div class="form-inline" style="width: 100%;">
				<div>
					<div class='col-sm-5 padding' style="width: 25%;">
						<div class="form-group">
							<label>
							<font color="red" style="font-size: large;">*</font>JobFair年:</label>
							<s:select headerKey="0" name="ddlJFYear" list="lstYear"
								headerValue="Select" id="ddlJFYear"
								cssClass="form-control textbox_width" style="width: 123px;"
								onChange="this.form.submit()" />
							<p id="jfYearError" style="color: red;"></p>
						</div>
					</div>
					<div class='col-sm-5 padding' style="width: 22%;">
						<div class="form-group">
							<label>申込日:</label>
							<sx:datetimepicker name="dtpStartDate" displayFormat="yyyy-MM-dd"
								id="dtpStartDate" cssClass="form-control" style="width: 123px;" />
						</div>
					</div>
					<div class='col-sm-3 padding' style="width: 5%;">
						<div class="form-group">
							<img src="img/core-img/tide.jpg" width="20px" />
						</div>
					</div>
					<div class='col-sm-5 padding' style="width: 22%;">
						<div class="form-group">
							<sx:datetimepicker name="dtpEndDate" displayFormat="yyyy-MM-dd"
								id="dtpEndDate" cssClass="form-control" style="width: 123px;" />
						</div>
					</div>
					<div class='col-sm-5 padding' style="width: 25%;">
						<div class="form-group">
							<label>第一希望会社名:</label>
							<s:if test="%{lstCompany.size()>0}">
								<s:select name="ddlFirstCompany" list="lstCompany"
									listKey="com_Sname" listValue="com_Sname" headerKey=""
									headerValue="Select" cssClass="form-control"
									style="width: 123px" />
							</s:if>
							<s:else>
								<s:select name="ddlFirstCompany" list="lstCompany" headerKey=""
									headerValue="Select" cssClass="form-control"
									style="width: 123px" />
							</s:else>
						</div>
					</div>
				</div>
				<div>
					<div class='col-sm-5 padding'>
						<div class="form-group"></div>
					</div>
					<div class='col-sm-5 padding' style="width: 100%;">
						<div class="form-group"></div>
					</div>
				</div>
				<div>
					<div class='col-sm-5 padding' style="width: 25%;">
						<div class="form-group">
							<label style="margin-right: 15px;"> 
							<font color="red" style="font-size: large;">*</font>受験場所:
							</label>
							<s:select headerKey="" headerValue="Select" list="lstExamPlace"
								listKey="examPlace" listValue="examPlace" name="ddlExamPlace"
								id="ddlExamPlace" cssClass="form-control" style="width: 123px" />
							<p id="examPlaceError" style="color: red;"></p>
						</div>
					</div>
					<div class='col-sm-5 padding' style="width: 22%;">
						<div class="form-group">
							<label>受験ID:</label>
							<s:textfield name="txtStartExamID" cssClass="form-control"
								style="width: 123px;" id="sExamID" size="11" maxlength="11" />
							<p id="sExamIDError" style="color: red;"></p>
						</div>
					</div>
					<div class='col-sm-3 padding' style="width: 5%;">
						<div class="form-group">
							<img src="img/core-img/tide.jpg" width="20px" />
						</div>
					</div>
					<div class='col-sm-5 padding' style="width: 22%;">
						<div class="form-group">
							<s:textfield name="txtEndExamID" cssClass="form-control"
								style="width: 123px" id="eExamID" size="11" maxlength="11" />
							<p id="eExamIDError" style="color: red;"></p>
						</div>
					</div>
					<div class='col-sm-5 padding' style="width: 25%;">
						<div class="form-group">
							<label>第二希望会社名:</label>
							<s:if test="%{lstCompany.size()>0}">
								<s:select name="ddlSecondCompany" list="lstCompany"
									listKey="com_Sname" listValue="com_Sname" headerKey=""
									headerValue="Select" cssClass="form-control"
									style="width: 123px" />
							</s:if>
							<s:else>
								<s:select name="ddlSecondCompany" list="lstCompany" headerKey=""
									headerValue="Select" cssClass="form-control"
									style="width: 123px" />
							</s:else>
						</div>
					</div>
				</div>
				<div>
					<div class='col-sm-5 padding'>
						<div class="form-group"></div>
					</div>
					<div class='col-sm-5 padding' style="width: 100%;">
						<div class="form-group"></div>
					</div>
				</div>
				<div>
					<div class='col-sm-5 padding' style="width: 75%;">
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
				<div>
					<div class='col-sm-5 padding'>
						<div class="form-group">
							<label>申請者数:</label>
							<s:property value="examinee_List.size()"></s:property>
						</div>
					</div>
					<div class='col-sm-5 padding' style="width: 49%;">
						<div class="form-group"></div>
					</div>
				</div>
				<div>
					<div class='col-sm-10 padding' style="width: 100%;">
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
			</div>
		</form>
		<div class='table-cont' id='table-cont' style="margin-left: 3px;">
			<table class="table1 table table-striped">
				<thead>
					<tr>
						<th>受験ID</th>
						<th>名前</th>
						<th>性別</th>
						<th>年齢</th>
						<th>学位</th>
						<th width="4%">申込日</th>
						<th width="4%">能力受験マーク</th>
						<th width="4%">個人特性マーク</th>
						<th width="9%">会社名</th>
						<th width="6%">面接日</th>
						<th>開始時間</th>
						<th>終了時間</th>
						<th width="8%">面接者</th>
						<th width="8%">面接場所</th>
						<th width="13%"></th>
					</tr>
				</thead>
				<s:if test="%{examinee_List.size()>0}">
					<s:iterator value="examinee_List" var="row" status="status">
						<form action="InsertInterviewee" method="post"
							name="interviewForm" onsubmit="return interviewValidateForm()">
							<tbody id="myTable">
								<tr>
									<td><s:property value="examinee.applicant.exam_ID" /></td>
									<td><s:property value="examinee.applicant.app_Name" /></td>
									<td><s:property value="examinee.applicant.app_Gender" /></td>
									<td><s:if test="%{lstAge.size()>0}">
											<s:subset start="%{#status.index}" count="1" source="lstAge">
												<s:iterator>
													<s:property />
												</s:iterator>
											</s:subset>
										</s:if></td>
								<%-- 	<td><s:property value="examinee.applicant.app_Degree" /></td> --%>
								  		<td>
										    <s:if test="examinee.applicant.app_Education == 'Graduated'">
										        <s:property value="examinee.applicant.app_Degree" />
										    </s:if>
										    <s:else>
										        <s:property value="examinee.applicant.app_ACYear" />
										    </s:else>
										</td>
									<td><s:property value="examinee.applicant.app_RegDate.toString()" /></td>
									<td><s:property value="examinee.examinee_IQmark" /></td>
									<td><s:property value="examinee.examinee_EQmark" /></td>
									<td><select name="interview.company.com_ID"
										class="form-control" id="会社名" required=""
										title="会社名 を選択してください。" oninvalid="InvalidSelect(this);"
										oninput="InvalidSelect(this);">
											<s:if test="%{interview.company.com_ID==examinee.firstCompany.com_ID}">
												<option value="">Select</option>
												<option value="<s:property value="examinee.firstCompany.com_ID"/>"
													Selected>
													<s:property value="examinee.firstCompany.com_Sname" />
												</option>
												<option value="<s:property value="examinee.secondCompany.com_ID" />">
													<s:property value="examinee.secondCompany.com_Sname" />
												</option>
											</s:if>
											<s:elseif test="%{interview.company.com_ID==examinee.secondCompany.com_ID}">
												<option value="">Select</option>
												<option value="<s:property value="examinee.firstCompany.com_ID"/>">
													<s:property value="examinee.firstCompany.com_Sname" />
												</option>
												<option value="<s:property value="examinee.secondCompany.com_ID"/>"
													Selected>
													<s:property value="examinee.secondCompany.com_Sname" />
												</option>
											</s:elseif>
											<s:else>
												<option value="">Select</option>
												<option value="<s:property value="examinee.firstCompany.com_ID"/>">
													<s:property value="examinee.firstCompany.com_Sname" />
												</option>
												<option value="<s:property value="examinee.secondCompany.com_ID" />">
													<s:property value="examinee.secondCompany.com_Sname" />
												</option>
											</s:else>
									</select></td>
									<td><input type="date" id="面接日" class="form-control"
										value="<s:property value="interview.interview_Date" />"
										name="interviewDate" required="" title="面接日 を選択してください。"
										oninvalid="checkDate(this);" oninput="return checkDate(this);"></td>
									<td><input type="time" id="ST<s:property value="%{#status.index}" />"
										class="form-control"
										value="<s:property value="interview.interview_StartTime" />"
										name="startTime" required="" title="開始時間 を入力してください。"
										oninvalid="checkSTime(this);"
										oninput="return checkSTime(this);"></td>
									<td><input type="time" class="form-control"
										id="ET<s:property value="%{#status.index}" />"
										value="<s:property value="interview.interview_EndTime" />"
										name="endTime" required="" title="終了時間 を入力してください。"
										autocomplete="off"></td>
									<td><input type="text" name="interview.interviewer"
										class="form-control" class="txtBox" maxlength="40"
										title="面接者 を入力してください。"
										value="<s:property value="interview.interviewer" />" id="面接者"
										required="" oninvalid="Invalid(this);" autocomplete="off"
										oninput="return Invalid(this);"></td>
									<td><input type="text" name="interview.interview_Place"
										class="form-control" class="txtBox" maxlength="40"
										value="<s:property value="interview.interview_Place" />"
										id="面接場所" required="" title="面接場所 を入力してください。"
										oninvalid="Invalid(this);" autocomplete="off"
										oninput="return Invalid(this);"></td>
									<td><input type="hidden" name="examinee_ID"
										class="form-control"
										value="<s:property value="examinee.examinee_ID"/>"> <input
										type="hidden" name="interview.interview_ex_key"
										value="<s:property value="interview.interview_ex_key"/>">
										<button type="submit" name="btn" value="登録"
											id="<s:property value="%{#status.index}" />"
											class="btn btn-primary" onclick="CheckValid(this.id);">
											<i class="fa fa-edit">登録</i>
										</button>
										<button type="reset" name="btn" value="キャンセル"
											class="btn btn-danger">
											<i class="fa fa-remove">キャンセル</i>
										</button></td>
								</tr>
							</tbody>
						</form>
					</s:iterator>
				</s:if>
				<s:else>
				</s:else>
			</table>
		</div>
		<div class="pagination_align row"
			style="float: left; margin-left: 6px; display: flex;">
			<s:if test="pagination.totalPages > 1">
				<tr bgcolor="lightgrey">
					<td colspan="5">Page No :<s:iterator
							value="pagination.totalPages.{#this}" status="userStatus">
							<s:url id="paginate" action="paginateInterviewList">
								<s:param value="%{#userStatus.count}"
									name="pagination.selectedPageNumber" />
							</s:url>
							<s:a href="%{paginate}" class="btn"
								style="padding: 0px 5px 0px 5px;border: 1px solid;margin-left: 5px;">
								<s:property value="%{#userStatus.count}" />
							</s:a>
						</s:iterator></td>
				</tr>
			</s:if>
		</div>
	</div>
</div>
</section>
</body>
</html>
