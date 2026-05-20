<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="src/customizeStyle.css" rel="stylesheet">
<script type="text/javascript" src="jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/scripts.js"></script>
<script type="text/javascript" src="jquery/CVForm.js"></script>
<link href="css/CVForm.css" rel="stylesheet">
</head>
<body>
	<section class="contact-area bg-gray section-padding">
		<div class="container">
			<div class="row align-items-end">
				<form action="appAdminRSearch" method="post" id="search">
					<div class="form-inline">
						<div class='col-sm-3'>
							<div class="form-group">
								<label><font color="red" style="font-weight:bold;font-size:large;">*</font> JobFair年 : </label>
								<s:select name="ddlJobFairYear" list="lstJFYear" headerKey=" "
									headerValue="Select" cssClass="form-control" id="ddlJFYear"
									style="width:150px;" />
							</div>
						</div>

						<div class='col-sm-3'>
							<div class="form-group">
								<label><font color="red" style="font-weight:bold;font-size:large;">*</font> 受験場所 : </label>
								<s:select name="ddlExamPlace" list="lstExamPlace"
									listKey="examPlaceID" headerKey=" " headerValue="Select"
									listValue="examPlace" cssClass="form-control"
									id="ddlJExamPlace" style="width:150px;" />
							</div>
						</div>
						<div class="form-group">
							<button type="submit" name="btn" value="検索"
								class="btn btn-primary btn-md">検索</button>
						</div>
					</div>
					<br>
					<br>
					<div class="row" style="margin-left: 10px;">
						<label>削除者人数 :</label>
						<s:if test="lstApplicant.size()>0">
							<s:property value="lstApplicant.size()" />
						</s:if>
						<s:else> 0 </s:else>

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
						<table class="table1 table table-striped">
							<thead>
								<tr>
									<th>受験ID</th>
									<th>名前</th>
									<th>個人番号</th>
									<th>性別</th>
									<th>年齢</th>
									<th>教育</th>
									<th>大学名</th>
									<th>大学市</th>
									<th>日本語スキル</th>
									<th>英語スキル</th>
									<th>申込日</th>
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
										<td><s:property value="app_Education" /></td>
										<td><s:property value="app_University" /></td>
										<td><s:property value="app_CityofUniversity" /></td>
										<td><s:property value="app_JPSkill" /></td>
										<td><s:property value="app_ENGSkill"/></td>
										<td><s:property value="app_RegDate.toString()"/></td>
										<td align="center"><s:url id="appAdminRAdd"
												action="appAdminRAdd">
												<s:param name="app_ID"><s:property value="app_ID" /></s:param>
												<s:param name="app_ex_key" value="app_ex_key" />
											</s:url> <s:a href="%{appAdminRAdd}" cssClass="btn btn-primary">追加　<em
													class="fa fa-plus"></em>
											</s:a></td>
									</tr>
								</tbody>
							</s:iterator>
						</table>
					</div>
				</form>
				<div class="row" style="float: right;">
					<s:url id="appAdminRBack" action="appAdminRBack"></s:url>
					<s:a href="%{appAdminRBack}" cssClass="btn btn-primary btn-md">戻る</s:a>
				</div>

			</div>
		</div>
	</section>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#search').bootstrapValidator({
				fields : {
					ddlJFYear : {
						selector : '#ddlJFYear',
						validators : {
							notEmpty : {
								message : 'JobFair年を選択してください。'
							}
						}
					},
					ddlJExamPlace : {
						selector : '#ddlJExamPlace',
						validators : {
							notEmpty : {
								message : '受験場所を選択してください。'
							}
						}
					}

				}
			});

			// Validate the form manually
			$('#validateBtn').click(function() {
				$('#search').bootstrapValidator('validate');
			});

		});
	</script>
</body>
</html>