<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="jquery/jquery-1.10.2.min.js"></script>
<link href="src/customizeStyle.css" rel="stylesheet">
<script type="text/javascript" src="js/common.js"></script>
<title>Company Registration</title>
<script type="text/javascript">
	function InvalidInput() {
		var com_Reg_Year = document.forms["companyForm"]["company.com_Reg_Year"].value;
		var com_Lname = document.forms["companyForm"]["company.com_Lname"].value;
		var com_Sname = document.forms["companyForm"]["company.com_Sname"].value;
		var returnValue = true;
		if (com_Reg_Year == '') {
			document.getElementById("ddlRegYear").innerHTML = "会社登録年を選択してください。";
			returnValue = false;
		} else {
			document.getElementById("ddlRegYear").innerHTML = "";
		}
		if (/^[\s]*$/.test(com_Lname.toString())
				|| com_Lname.toString().trim() == '') {
			document.getElementById("txtLongName").innerHTML = "会社名（名称）を入力してください。";
			returnValue = false;
		} else {
			document.getElementById("txtLongName").innerHTML = "";
		}
		if (/^[\s]*$/.test(com_Sname.toString())
				|| com_Sname.toString().trim() == '') {
			document.getElementById("txtShortName").innerHTML = "会社名（略称）を入力してください。";
			returnValue = false;
		} else if (com_Sname.length > com_Lname.length) {
			document.getElementById("txtShortName").innerHTML = "会社名（略称）が不正です。";
			returnValue = false;
		} else {
			document.getElementById("txtShortName").innerHTML = "";
		}
		return returnValue;
	}
	function Cancel() {
		document.getElementById("ddlRegYear").innerHTML = "";
		document.getElementById("txtLongName").innerHTML = "";
		document.getElementById("txtShortName").innerHTML = "";
	}
</script>
<sx:head />
</head>
<body>
	<section class="contact-area bg-gray section-padding">
	<div class="container">
		<div class="row align-items-end">
			<div class="container smallpadding">
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
				<s:form action="SaveCompany" method="post" namespace="/"
					enctype="multipart/form-data" theme="bootstrap" name="companyForm"
					cssClass="form-horizontal" labelCssClass="col-sm-4"
					elementCssClass="col-sm-6" accept-charset="UTF-8">
					<div class="form-group">
						<label class="control-label col-sm-5"> <font color="red"
							style="font-size: large;">*</font>会社登録年:
						</label>
						<div class="col-sm-3">
							<s:if test="%{company.com_Reg_Year==0}">
								<s:select name="company.com_Reg_Year" list="lstYear"
									headerKey="" headerValue="Select"
									cssClass="form-control textbox_width" style="width: 123px;" />
							</s:if>
							<s:else>
								<s:select name="company.com_Reg_Year" list="lstYear"
									headerKey="" headerValue="Select"
									cssClass="form-control textbox_width" style="width: 123px;"
									readonly="true" />
							</s:else>
							<p id="ddlRegYear" style="color: red;"></p>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5"> <font color="red"
							style="font-size: large;">*</font>会社名（名称）:
						</label>
						<div class="col-sm-3">
							<s:textfield name="company.com_Lname" maxlength="70"
								placeholder="会社名（名称） を入力してください。" requiredLabel="true"
								id="longName" style="width: 400px;"></s:textfield>
							<p id="txtLongName" style="color: red;"></p>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5"> <font color="red"
							style="font-size: large;">*</font>会社名（略称）:
						</label>
						<div class="col-sm-3">
							<s:textfield name="company.com_Sname" maxlength="30"
								placeholder="会社名（略称） を入力してください。" requiredLabel="true"
								id="shortName" style="width: 400px;"></s:textfield>
							<p id="txtShortName" style="color: red;"></p>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-5 col-sm-10">
							<s:hidden name="company.com_ID" value="%{company.com_ID}" />
							<s:hidden name="company.com_ex_key" value="%{company.com_ex_key}" />
							<s:submit name="button" value="登録" cssClass="btn btn-primary"
								onclick="return InvalidInput();" />
							<button type="reset" class="btn btn-danger"
								onclick="return Cancel();">キャンセル</button>

						</div>
					</div>
					<div class='table-cont' id='table-cont'>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>会社登録年</th>
									<th>会社名（名称）</th>
									<th>会社名（略称）</th>
									<th></th>
								</tr>
							</thead>
							<s:iterator value="com_List" var="row" status="status">
								<tbody>
									<tr>
										<td><s:property value="com_Reg_Year" /></td>
										<td><s:property value="com_Lname" /></td>
										<td><s:property value="com_Sname" /></td>
										<td style="width: 17%;"><s:url id="editURL"
												action="EditCompany">
												<s:param name="company.com_ID" value="com_ID"></s:param>
												<s:param name="com_ex_key" value="com_ex_key"></s:param>
											</s:url> <s:a href="%{editURL}" class="btn btn-primary lnkbtn">
												<i class="fa fa-edit"> 編集</i>
											</s:a> <s:url id="deleteURL" action="DeleteCompany">
												<s:param name="company.com_ID" value="com_ID"></s:param>
												<s:param name="com_ex_key" value="com_ex_key"></s:param>
											</s:url> <s:a href="%{deleteURL}" class="btn btn-danger lnkbtn"
												onclick="return confirm('削除しますか？');">
												<i class="fa fa-remove"> 削除</i>
											</s:a></td>
									</tr>
								</tbody>
							</s:iterator>
						</table>
					</div>
					<div class="pagination_align row"
						style="float: left; margin-left: 6px; display: flex;">
						<s:if test="pagination.totalPages > 1">
							<tr bgcolor="lightgrey">
								<td colspan="5">Page No :<s:iterator
										value="pagination.totalPages.{#this}" status="userStatus">

										<s:url id="paginate" action="paginateCompanyList">
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
				</s:form>
			</div>
		</div>
	</div>
	</section>
</body>
</html>
