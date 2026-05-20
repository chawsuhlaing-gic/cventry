<%@ page trimDirectiveWhitespaces="true" %>
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
<title>Export Examinee</title>
<script type="text/javascript" src="assets/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<link href="src/customizeStyle.css" rel="stylesheet">
<link href="css/CVForm.css" rel="stylesheet">
<sx:head />
<style type="text/css">
.errors {
	background-color: #FFCCCC;
	width: 100%;
	margin-bottom: 8px;
}

.errors li {
	list-style: none;
}

.padding {
	padding-right: 0;
}
</style>
</head>
<body>
<section class="contact-area bg-gray section-padding">
	<div class="container">
		<div class="row align-items-end">
			<form action="exportExaminee"  method="post" id="exportExamineeForm" name="form" >
				<div class="form-inline">
					<div class="row">
					<div class='col-lg-3'>
						<div class="form-group">
								<label style="width: 110px;"><font color="red">*</font>JobFair年:</label>
								<s:select name="ddlJobFairYear" list="lstYear" headerKey=""
									headerValue="Select" cssClass="form-control textbox_width"
									style="width: 123px;" id="ddlJFYear" onChange="this.form.submit()"/>
								<p id="jfYearError" style="color: red;"></p>
						</div>
					</div>
					<div class='col-md-3'>
						<div class="form-group">
								<label style="width: 110px;">申込日:</label>
								<sx:datetimepicker name="regStartDate" displayFormat="yyyy-MM-dd"
									id="startDate" cssClass="form-control" style="width: 123px;" />
						</div>
					</div>
					<div class='col-sm-5 padding' style="width: 8%;">
						<div class="form-group">
								<img src="img/core-img/tide.jpg" width="20px" />
						</div>
					</div>
					<div class='col-md-3'>
						<div class="form-group">
								<sx:datetimepicker name="regEndDate" displayFormat="yyyy-MM-dd"
									id="endDate" cssClass="form-control" style="width: 123px;" />
								<p></p>
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
					<div class="row">
						<div class='col-lg-3'>
							<div class="form-group">
								<label style="width: 110px;"><font color="red">*</font>受験場所:</label>
								<s:select headerKey="" headerValue="Select"
									list="lstExamPlace" listKey="examPlaceID"
									listValue="examPlace" name="ddlExamPlace"
									cssClass="form-control" style="width: 123px;"
									id="ddlExamPlace" />
								<p id="examPlaceError" style="color: red;"></p>
							</div>
						</div>
						<div class='col-md-3'>
							<div class="form-group">
								<label style="width: 110px;">受験ID:</label>
								<s:textfield name="txtStartExamID" cssClass="form-control"
									style="width: 123px;" id="sExamID"
									size="11" maxlength="11" />
								<p id="sExamIDError" style="color: red;"></p>
							</div>
						</div>
						<div class='col-sm-5 padding' style="width: 8%;">
							<div class="form-group">
								<img src="img/core-img/tide.jpg" width="20px" />
							</div>
						</div>
						<div class='col-md-3'>
							<div class="form-group">
								<s:textfield name="txtEndExamID" cssClass="form-control"
									style="width: 123px" id="eExamID"
									size="11" maxlength="11" />
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
					<div class="row">
					<div class='col-lg-3'>
						<div class="form-group">
								<label style="width: 110px;">第一希望会社名:</label>
								<s:if test="%{lstCompany.size()>0}">
								<s:select name="ddlFirstCompany" list="lstCompany"
										listKey="com_ID" listValue="com_Sname" headerKey="0"
										headerValue="Select" cssClass="form-control" 
										style="width: 123px"/>
								</s:if>
								<s:else>
								<s:select name="ddlFirstCompany" list="lstCompany"
										  headerKey="0" headerValue="Select" 
										  cssClass="form-control"
										  style="width: 123px" />
								</s:else>		
						</div>
					</div>
					<div class='col-lg-9'>
						<div class="form-group">
								<label style="width: 110px;">第二希望会社名:</label>
								<s:if test="%{lstCompany.size()>0}">
								<s:select name="ddlSecondCompany" list="lstCompany"
										  listKey="com_ID" listValue="com_Sname" headerKey="0"
										  headerValue="Select" cssClass="form-control" 
										  style="width: 123px" />
								</s:if>
								<s:else>
								<s:select name="ddlSecondCompany" list="lstCompany"
										  headerKey="0" headerValue="Select" 
										  cssClass="form-control" style="width: 123px" />
								</s:else>
						</div>
					</div>
					</div>
					<br>
					<div class="row">
					<div class='col-md-6' style="float: right;">
						<div class="form-group">
				     		  <s:submit type="button" value="検索"
										class="btn btn-primary btn_search" name="btn"
										onclick="return validateSearch();" style="width: 100px;"
										id="btnSearch" />
						</div>
						<div class="form-group">
							<s:submit type="button" value="キャンセル" class="btn btn-danger"
									  name="btn" />
						</div>
					</div>
					</div>					
				</div>		
				<div>
					<div class='col-sm-5 padding'>
						<div class="form-group">
							<label>申請者数:</label>
							<s:property value="lstApplicantInfo.size()"></s:property>
						</div>
					</div>
					<div class='col-sm-5 padding' style="width: 49%;">
						<div class="form-group"></div>
					</div>
				</div>	
				<div class="row">
				<div class="col-lg-12" style="margin-left: 10px;">
	            <s:if test="hasActionMessages()">
					<div class="alert alert-success alert-dismissible" align="center">
						<strong><s:actionmessage /></strong>
					</div>
				</s:if>
			    <s:if test="hasActionErrors()">
					<div  class="alert alert-danger alert-dismissible" align="center">
						<strong><s:actionerror /></strong>
					</div>
	            </s:if>
     		    </div>
  				</div>
				<div class='table-cont' id='table-cont'>
				<table class="table2 table table-striped">
				<thead>
					<tr>						
						<th>受験ID</th>
						<th>名前</th>                                 				
						<th>第一希望会社名</th>
						<th>第二希望会社名</th>
						<th>能力試験マーク	</th>
						<th>個人特性マーク	</th>
						<th>性別</th>
						<th>年齢</th>
						<th>個人番号</th>
						<th>電話番号</th>						
						<th>学位</th>
						<th>大学名</th>
						<th>大学市</th>						
						<th>メール</th>
						<th>申込日</th>						
					</tr>
				</thead>
                <s:if test="%{lstApplicantInfo.size()>0}">
					<s:iterator value="lstApplicantInfo" var="row" status="status">							 
						<tbody id="myTable">
					    <tr>
							<td id="lstApplicantID"><s:property value="applicant.exam_ID" /></td>
							<td><s:property value="applicant.app_Name" /></td>
							<td><s:property value="examinee.firstCompany.com_Sname" /></td>
							<td><s:property value="examinee.secondCompany.com_Sname" /></td>
							<td><s:property value="examinee.examinee_IQmark" /></td>
							<td><s:property value="examinee.examinee_EQmark" /></td>
							<td><s:property value="applicant.app_Gender" /></td>
							<td><s:if test="%{lstAge.size()>0}">
												<s:subset start="%{#status.index}" count="1" source="lstAge">
													<s:iterator>
														<s:property />
													</s:iterator>
												</s:subset>
											</s:if>
							</td>
							<td><s:property value="applicant.app_Nrc" /></td>
							<td><s:property value="applicant.app_PhNo" /></td>
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
							<td><s:property value="applicant.app_Email" /></td>
							<td><s:property value="applicant.app_RegDate.toString()" /></td>
					 </tr>
					 <s:hidden name="lstApplicantID[%{#status.index}]"
							   value="%{#row.applicant.app_ID}" />
					 </tbody>
				</s:iterator>
				</s:if>	
				</table>
		</div>		
		<div class="row" style="float: right;">
			<s:submit type="button" name="btn" value="Excel出力" class="btn btn-primary btn-md"/>
		</div>		
	</form>
	</div>
</div>
</section>
<script type="text/javascript">
		window.onload = function() {
			var tableCont = document.querySelector('#table-cont')
			function scrollHandle(e) {
				var scrollTop = this.scrollTop;
				this.querySelector('thead').style.transform = 'translateY('
						+ scrollTop + 'px)';
			}
			tableCont.addEventListener('scroll', scrollHandle)
		}
</script>
</body>
</html> 