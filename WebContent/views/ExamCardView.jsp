<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja-jp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>受験番号出力</title>
<script type="text/javascript" src="assets/js/jquery-1.11.1.min.js"></script>
<link href="src/customizeStyle.css" rel="stylesheet">
<link href="css/CVForm.css" rel="stylesheet">
<script type="text/javascript" src="jquery/CVForm.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<sx:head />
</head>
<body>
	<section class="contact-area bg-gray section-padding">
	<div class="container">
		<div class="row align-items-end">
			<form action="printExamCard" method="post" id="examCardForm"
				name="form">
				<div class="form-inline" style="width: 90%;">
					<div class="row">
						<div class='col-sm-5 padding' style="width: 30%;">
							<div class="form-group">
								<label><font color="red">*</font>JobFair年 :</label>
								<s:select name="ddlJobFairYear" list="lstYear" headerKey=""
									headerValue="Select" cssClass="form-control textbox_width"
									style="width: 123px;" id="ddlJFYear" />
								<p id="jfYearError" style="color: red;"></p>
							</div>
						</div>
						<div class='col-sm-5 padding' style="width: 25%;">
							<div class="form-group">
								<label>申込日 :</label>
								<sx:datetimepicker name="regSDate" displayFormat="yyyy-MM-dd"
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
								<sx:datetimepicker name="regEDate" displayFormat="yyyy-MM-dd"
									id="endDate" cssClass="form-control" style="width: 123px;" />
								<p></p>
							</div>
						</div>
					</div>
					<div class="row">
						<div class='col-sm-5 padding' style="width: 30%;">
							<div class="form-group">
								<label style="margin-right: 15px;"><font color="red">*</font>受験場所:</label>
								<s:select headerKey="" headerValue="Select" list="lstExamPlace"
									listKey="examPlaceID" listValue="examPlace" name="ddlExamPlace"
									cssClass="form-control" style="width: 123px;" id="ddlExamPlace" />
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
					<div class="row">
						<div class='col-sm-5' style="width: 55%;"></div>
						<div class='col-sm-5 padding' style="width: 10%;">
							<div class="form-group">
							    <button type="submit" name="btn" value="Search" style="width: 100px;"
							        onclick="return validateSearch();" class="btn btn-primary btn_search" 
							        id="btnSearch">検索</button>
							</div>
						</div>
						<div class='col-sm-5 padding' style="width: 10%;">
							<div class="form-group">
								<button type="submit" name="btn" value="Cancel"
						            class="btn btn-danger">キャンセル</button>
							</div>
						</div>
					</div>
					<div class="row">
						<div class='col-sm-3 padding'>
							<div class="form-group">
								<label>申請者数:</label>
								<s:property value="lstApplicant.size()"></s:property>
							</div>
						</div>
						<s:if test="pagination.totalPages > 1">
						    <div class='col-sm-5 padding'>
							    <div class="form-group">
								    <label>現在のページ番号:</label>
								    <s:property value="pagination.selectedPageNumber"></s:property>
							    </div>
						</div>
						</s:if>
					</div>
				</div>
				<div class="col-lg-12">
					<s:if test="hasActionErrors()">
						<div class="alert alert-danger alert-dismissible" align="center" style="width: 102%;">
							<strong><s:actionerror /></strong>
						</div>
					</s:if>
				</div>
				<div class='table-cont' id='table-cont'>
					<table class="table1 table table-striped">
						<thead>
							<tr>
								<th>受験ID</th>
								<th>名前</th>
								<th>性別</th>
								<th style="width: 3%;">年齢</th>
								<th>個人番号</th>
								<th>電話番号</th>
								<th>学位</th>
								<th>大学名</th>
								<th>大学市</th>
								<th>メール</th>
								<th>申込日</th>
							</tr>
						</thead>
						<s:if test="%{lstApplicant.size()>0}">
							<s:iterator value="lstApplicant" var="row" status="status">
								<tbody id="myTable">
									<tr>
										<td id="lstApplicantID"><s:property value="exam_ID" /></td>
										<td><s:property value="app_Name" /></td>
										<td><s:property value="app_Gender" /></td>
										<td><s:if test="%{lstAge.size()>0}">
												<s:subset start="%{#status.index}" count="1" source="lstAge">
													<s:iterator>
														<s:property />
													</s:iterator>
												</s:subset>
											</s:if></td>
										<td><s:property value="app_Nrc" /></td>
										<td><s:property value="app_PhNo" /></td>
									<%-- 	<td><s:property value="app_Degree" /></td> --%>
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
										<td><s:property value="app_Email" /></td>
										<td><s:property value="app_RegDate.toString()" /></td>
									</tr>
									<s:hidden name="lstApplicantID[%{#status.index}]"
										value="%{#row.app_ID}" />
								</tbody>
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
									<s:submit value="%{#userStatus.count}" name="btn" id="button"
										style="padding: 0px 5px 0px 5px;border: 1px solid;margin-left: 5px;border-radius: 4px;">
										<s:param value="%{#userStatus.count}"
											name="pagination.selectedPageNumber" />
									</s:submit>
								</s:iterator></td>
						</tr>
					</s:if>
				</div>
				<div class="row" style="float: right;">
				    <button type="submit" name="btn" value="ExportExamCard"
					    class="btn btn-primary">現在のページ受験番号出力</button>
				</div>
			</form>
		</div>
	</div>
	</section>
</body>
</html>