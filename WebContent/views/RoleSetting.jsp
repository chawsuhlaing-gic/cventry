<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="src/customizeStyle.css" rel="stylesheet">
<script type="text/javascript" src="assets/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="jquery/CVForm.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<title>権限設定</title>
<style>
.grid-container {
	display: grid;
	grid-template-columns: auto auto auto auto;
	grid-gap: 10px;
	padding: 10px;
	border-radius: 3px;
	height: 158px;
	border: 1px solid #ccc;
	width: 100%;
}
.grid-container>div {
	background-color: rgba(255, 255, 255, 0.8);
	border: 1px solid black;
	text-align: center;
	font-size: 30px;
}
</style>
</head>
<body>
	<section class="contact-area bg-gray section-padding">
	<div class="container">
		<div class="row align-items-end">
			<form class="form-horizontal" action="saveRoleSetting" method="post"
				id="roleSetting" enctype="multipart/form-data">
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
				<s:if test="%{roleSetting.role_ID==0}">
					<div class="form-group">
					<label class="col-sm-2"></label>
						<label class="col-sm-2"><font color="red" style="font-weight:bold;font-size:large;">*</font>社員番号</label>
						<div class="col-sm-4">
							<s:textfield name="role_EmployeeID" maxlength="9"
								class="form-control" value="" minlength="6"
								oninput="this.val
								ue = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" />
						</div>
					</div>
				</s:if>
				<s:else>
					<div class="form-group ">
					<label class="col-sm-2"></label>
						<label class="col-sm-2"><font color="red" style="font-weight:bold;font-size:large;">*</font>社員番号</label>
						<div class="col-sm-4">
							<s:textfield name="role_EmployeeID" maxlength="9" minlength="6" readonly="true"
								class="form-control" value="%{roleSetting.role_EmployeeID}"
								oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" />
						</div>
					</div>
				</s:else>
				<div class="form-group ">
				<label class="col-sm-2"></label>
					<font color="black"><label class="col-sm-2"><font color="red" style="font-weight:bold;font-size:large;">*</font>名前</label></font>
					<div class="col-sm-4">
						<s:textfield name="role_Name" maxlength="45" class="form-control"
							value="%{roleSetting.role_Name}" />
					</div>
				</div>
				<s:if test="%{roleSetting.role_ID==0}">
					<div class="form-group ">
					<label class="col-sm-2"></label>
						<font color="black"><label class="col-sm-2"><font color="red" style="font-weight:bold;font-size:large;">*</font>パスワード</label></font>
						<div class="col-sm-4">
							<s:password name="role_Password" maxlength="20"
								class="form-control" />
						</div>
					</div>
					<div class="form-group ">
					<label class="col-sm-2"></label>
						<font color="black"><label class="col-sm-2"><font color="red" style="font-weight:bold;font-size:large;">*</font>パスワード確認 </label></font>
						<div class="col-sm-4">
							<s:password name="confirmPassword" maxlength="20"
								class="form-control" />
						</div>
					</div>
				</s:if>

				<div class="form-group ">
				<label class="col-sm-2"></label>
					<label class="col-sm-2"> 備考 </label>
					<div class="col-sm-4">
						<s:textarea name="role_Remark" class="form-control"
							value="%{roleSetting.role_Remark}" rows="4" cols="30"
							maxlength="200" />
					</div>
				</div>
				<div class="form-group"
					style="margin-top: 30px; margin-bottom: 30px;">
					<label class="col-sm-2"></label>
					<label class="col-sm-2"> <font color="red" style="font-weight:bold;font-size:large;">*</font>権限画面リスト
					</label>
					<div class="col-sm-4">
						<div class="grid-container">
						    <s:checkboxlist value="%{selectedCheckBoxList}"
								name="role_Permission" class="form-control" id="chkList"
								style="margin-top: -5px; width: 40%;" list="lstRolePermission"
								listKey="rolePermissionID" listValue="rolePermissionName" />
						</div>
					</div>
					<p id="check" style="color: red;"></p>
				</div>
				<div class='col-sm-6'>
					<div class="form-group ">
					<s:hidden value="%{roleSetting.role_ex_key}" name="role_ex_key"/>
						<s:submit type="button" value="登録"
							class="btn btn-primary btn_search" name="btn" id="btnSearch"
							style="width: 95px;" onclick="return confirm('保存しますか？');"/>
						<s:hidden value="%{roleSetting.role_ID}" name="roleSetting.role_ID"/>
					</div>
				</div>
				<div class='col-sm-2'
					style="padding-right: 30px; padding-bottom: 60px;">
					<div class="form-group">
						<s:reset type="button" value="キャンセル" class="btn btn-danger"
							name="btn" />
					</div>
				</div>
				<div class='table-cont' id='table-cont'>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>No</th>
								<th>社員番号</th>
								<th>名前</th>
								<th>権限画面リス</th>
								<th>備考</th>
								<th width="13%"><em class="fa fa-cog"></em></th>
							</tr>
						</thead>
						<s:iterator value="lstRoleSetting" status="status">
							<tbody>
								<tr>
									<td><s:property value="%{#status.count}"/></td>
									<td><s:property value="role_EmployeeID" /></td>
									<td><s:property value="role_Name" /></td>
									<td><s:property value="role_Permission" /></td>
									<td><s:property value="role_Remark" /></td>
									<td style="width: 17%;"><s:url id="editURL"
											action="editRoleSetting">
											<s:param name="roleSetting.role_ID" value="role_ID"></s:param>
											<s:param value="role_ex_key" name="role_ex_key"></s:param>
										</s:url> <s:a href="%{editURL}" class="btn btn-primary lnkbtn">
											<i class="fa fa-edit"> 編集</i>
										</s:a> <s:url id="deleteURL" action="deleteRoleSetting">
											<s:param name="roleSetting.role_ID" value="role_ID"></s:param>
											<s:param value="role_ex_key" name="role_ex_key"></s:param>
										</s:url> <s:a href="%{deleteURL}" class="btn btn-danger lnkbtn"
											onclick="return confirm('この社員を削除しますか？');">
											<i class="fa fa-remove"> 削除</i>
										</s:a></td>
								</tr>
							</tbody>
						</s:iterator>
					</table>
				</div>
			</form>
		</div>
	</div>
	</section>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#roleSetting')
									.bootstrapValidator(
											{
												fields : {
													role_EmployeeID : {
														validators : {
															notEmpty : {
																message : '社員番号を入力してください。 '
															},
															regexp : {
																regexp : /^[0-9]+$/,
																message : '社員番号を文字のみで入力してください。'
															},
															stringLength : {
																min : 6,
																max : 9,
																message : '社員番号は6文字以上9文字以下でなければなりません 。'
															}
														}
													},
													role_Name : {
														validators : {
															notEmpty : {
																message : '名前を入力してください。 '
															},
															regexp : {
																regexp : /^[a-zA-Z\s-, ]+$/,
																message : '名前を文字のみで入力してください。'
															}
														}
													},
													role_Password : {
														validators : {
															notEmpty : {
																message : 'パスワードを入力してください。'
															},
															stringLength : {
																min : 8,
																max : 20,
																message : 'パスワードは8文字以上20文字以下でなければなりません 。'
															},
															regexp : {
																regexp : /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}/,
																message : 'パスワードには少なくとも大文字1文字、小文字1文字、数字1文字のみで構成されます。'
															}
														}
													},
													confirmPassword : {
														validators : {
															notEmpty : {
																message : 'パスワード確認 を入力してください。'
															},
															identical : {
																field : 'role_Password',
																message : 'パスワードとパスワード確認が同じではありません。'
															}
														}
													},
													role_Permission : {
														validators : {
															notEmpty : {
																message : '権限画面リストを選択してください。'
															}
														}
													},
												}
											});
							// Validate the form manually
							$('#validateBtn').click(
									function() {
										$('#roleSetting').bootstrapValidator(
												'validate');
									});
						});
	</script>
</body>
</html>