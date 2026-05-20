<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Examinee Registration</title>
<script type="text/javascript" src="jquery/jquery-1.10.2.min.js"></script>
<link href="src/customizeStyle.css" rel="stylesheet">
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	  $('#number').bind("cut copy paste drag drop", function(e) {
	      e.preventDefault();
	  });     
	});
	function isNumberKey(evt) {
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57))
	        return false;
	    return true;
	}
</script>
<style type="text/css">
input[type=number]::-webkit-inner-spin-button, input[type=number]::-webkit-outer-spin-button
	{
	-webkit-appearance: none;
	margin: 0;
}

input[type=number] {
	-moz-appearance: textfield;
}
</style>
<sx:head />
</head>
<section class="contact-area bg-gray section-padding">
<div class="container">
	<div class="row align-items-end">
		<form action="Search" method="post" name="form">
			<div class="form-inline" style="width: 90%;">
				<div>
					<div class='col-sm-5 padding' style="width: 30%;">
						<div class="form-group">
							<label><font color="red" style="font-size: large;">*</font>JobFair年 :</label>
							<s:select name="ddlJFYear" list="lstYear" headerKey=""
								id="ddlJFYear" headerValue="Select"
								cssClass="form-control textbox_width" style="width: 123px;" />
							<p id="jfYearError" style="color: red;"></p>
						</div>
					</div>
					<div class='col-sm-5 padding' style="width: 25%;">
						<div class="form-group">
							<label>申込日:</label>
							<sx:datetimepicker name="dtpStartDate" displayFormat="yyyy-MM-dd"
								id="dtpStartDate" cssClass="form-control" style="width: 123px;" />
						</div>
					</div>
					<div class='col-sm-5 padding' style="width: 8%;">
						<div class="form-group">
							<img src="img/core-img/tide.jpg" width="20px" />
						</div>
					</div>
					<div class='col-sm-5 padding' style="width: 20%;">
						<div class="form-group">
							<sx:datetimepicker name="dtpEndDate" displayFormat="yyyy-MM-dd"
								id="dtpEndDate" cssClass="form-control" style="width: 123px;" />
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
							<label style="margin-right: 15px;">
							<font color="red" style="font-size: large;">*</font>受験場所:</label>
							<s:select headerKey="" headerValue="Select" list="lstExamPlace"
								listKey="examPlace" listValue="examPlace" name="ddlExamPlace"
								id="ddlExamPlace" cssClass="form-control" style="width: 123px" />
							<p id="examPlaceError" style="color: red;"></p>
						</div>
					</div>
					<div class='col-sm-5 padding' style="width: 25%;">
						<div class="form-group">
							<label>受験ID:</label>
							<s:textfield name="txtStartExamID" cssClass="form-control"
								style="width: 123px;" id="sExamID" size="11" maxlength="11" />
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
					<div class='col-sm-5 padding'>
						<div class="form-group"></div>
					</div>
					<div class='col-sm-5 padding' style="width: 49%;">
						<div class="form-group"></div>
					</div>
				</div>
				<div>
					<div class='col-sm-5 padding' style="width: 60%;">
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
							<s:property value="app_List.size()"></s:property>
						</div>
					</div>
					<div class='col-sm-5 padding' style="width: 49%;">
						<div class="form-group"></div>
					</div>
				</div>
				<div>
					<div class='col-sm-10 padding' style="width: 112%;">
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
		<div class='table-cont' id='table-cont' id="demodemo">
			<table class="table1 table table-striped" id="example">
				<thead>
					<tr>
						<th>受験ID</th>
						<th width="10%">名前</th>
						<th width="5%">性別</th>
						<th width="3%">年齢</th>
						<th>学位</th>
						<th width="15%">大学名</th>
						<th>大学市</th>
						<th width="7%">申込日</th>
						<th width="10%">第一希望会社名</th>
						<th width="10%">第二希望会社名</th>
						<th width="5%">能力受験マーク</th>
						<th width="5%">個人特性マーク</th>
						<th width="13%"></th>
					</tr>
				</thead>
				<s:if test="%{app_List.size()>0}">
					<s:iterator value="app_List" var="row" status="status">
						<form action="InsertExaminee" method="post" id="formtable">
							<tbody id="myTable">
								<tr>
									<td><s:property value="applicant.exam_ID" /></td>
									<td><s:property value="applicant.app_Name" /></td>
									<td><s:property value="applicant.app_Gender" /></td>
									<td><s:if test="%{lstAge.size()>0}">
											<s:subset start="%{#status.index}" count="1" source="lstAge">
												<s:iterator>
													<s:property />
												</s:iterator>
											</s:subset>
										</s:if></td>
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
									<td><s:property value="applicant.app_CityofUniversity" /></td>
									<td><s:property value="applicant.app_RegDate.toString()" /></td>
									<td><select name="examinee.firstCompany.com_ID"
										id="第一希望会社名" class="form-control" required=""
										title="第一希望会社名 を選択してください。" oninvalid="InvalidSelect(this);"
										oninput="InvalidSelect(this);">
											<option value="">Select</option>
											<s:if test="%{com_List.size()>0}">
												<s:iterator value="com_List">
													<s:if test="%{com_ID==examinee.firstCompany.com_ID}">
														<option value="<s:property value="com_ID" />" Selected>
															<s:property value="com_Sname" />
														</option>
													</s:if>
													<s:else>
														<option value="<s:property value="com_ID" />">
															<s:property value="com_Sname" />
														</option>
													</s:else>
												</s:iterator>
											</s:if>
									</select></td>
									<td><select name="examinee.secondCompany.com_ID"
										id="第二希望会社名" class="form-control" required=""
										title="第二希望会社名 を選択してください。" oninvalid="InvalidSelect(this);"
										oninput="InvalidSelect(this);">
											<option value="">Select</option>
											<s:if test="%{com_List.size()>0}">
												<s:iterator value="com_List">
													<s:if test="%{com_ID==examinee.secondCompany.com_ID}">
														<option value="<s:property value="com_ID" />" Selected>
															<s:property value="com_Sname" />
														</option>
													</s:if>
													<s:else>
														<option value="<s:property value="com_ID" />">
															<s:property value="com_Sname" />
														</option>
													</s:else>
												</s:iterator>
											</s:if>
									</select></td>
									<td><input type="number" name="examinee.examinee_IQmark"
										id="能力受験マーク" onkeypress="return isNumberKey(event)"
										class="form-control" maxlength="3" min="0" max="100"
										value="<s:property value="examinee.examinee_IQmark" />"
										required="" title="能力受験マーク　を入力してください。"
										oninvalid="InvalidText(this);" oninput="InvalidText(this);"></td>
									<td><input type="number" name="examinee.examinee_EQmark"
										id="個人特性マーク" onkeypress="return isNumberKey(event)"
										class="form-control" maxlength="3" min="0" max="100"
										value="<s:property value="examinee.examinee_EQmark" />"
										required="" title="個人特性マーク　を入力してください。"
										oninvalid="InvalidText(this);" oninput="InvalidText(this);"></td>
									<td><input type="hidden" name="app_ID"
										value="<s:property value="applicant.app_ID"/>"> 
										<input type="hidden" name="examinee.examinee_ex_key"
										value="<s:property value="examinee.examinee_ex_key"/>">
										<button type="submit" name="btn" value="登録"
											class="btn btn-primary">
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
			</table>
		</div>
		<div class="pagination_align row"
			style="float: left; margin-left: 6px; display: flex;">
			<s:if test="pagination.totalPages > 1">
				<tr bgcolor="lightgrey">
					<td colspan="5">Page No :<s:iterator
							value="pagination.totalPages.{#this}" status="userStatus">

							<s:url id="paginate" action="paginateExamineeList">
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