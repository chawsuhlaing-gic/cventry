<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="/struts-tags" prefix="s"%> <%@ taglib
uri="/struts-dojo-tags" prefix="sx"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="src/customizeStyle.css" rel="stylesheet" />
    <link href="src/landingpage.css" rel="stylesheet" />
    <script
      type="text/javascript"
      src="assets/js/jquery-1.11.1.min.js"
    ></script>
    <script type="text/javascript" src="js/common.js"></script>
    <title>ホームページ設定</title>
    <sx:head />
  </head>
  <body>
    <section class="contact-area bg-gray section-padding">
      <div class="container">
        <div class="row align-items-end">
          <form
            class="form-horizontal"
            action="saveLandingPage"
            method="post"
            id="landingPage"
            enctype="multipart/form-data"
          >
            <div class="col-lg-12">
              <s:if test="hasActionMessages()">
                <div
                  class="alert alert-success alert-dismissible"
                  align="center"
                >
                  <strong><s:actionmessage /></strong>
                </div>
              </s:if>
              <s:if test="hasActionErrors()">
                <div
                  class="alert alert-danger alert-dismissible"
                  align="center"
                >
                  <strong><s:actionerror /></strong>
                </div>
              </s:if>
            </div>
            <div class="form-group">
              <label class="col-sm-1"></label>
              <label class="col-sm-2"
                ><font color="red" style="font-weight: bold; font-size: large"
                  >*</font
                >ヘッダー1行目</label
              >
              <div class="col-sm-3">
                <input
                  type="text"
                  name="dumpHeaderFirstLine"
                  maxlength="75"
                  class="form-control"
                  value="${dumpHeaderFirstLine}"
                  required
                />
              </div>
              <div class="col-sm-5 text-right">
                <input type="checkbox" name="dumpDelFlag" id="delFlagCheck" />
                <label for="delFlagCheck">Recruitフォームコントロール</label>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-1"></label>
              <label class="col-sm-2"
                ><font color="red" style="font-weight: bold; font-size: large"
                  >*</font
                >ヘッダー 2行目</label
              >
              <div class="col-sm-3">
                <input
                  type="text"
                  name="dumpHeaderSecondLine"
                  maxlength="75"
                  class="form-control"
                  value="${dumpHeaderSecondLine}"
                  required
                />
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-1"></label>
              <label class="col-sm-2"
                ><font color="red" style="font-weight: bold; font-size: large"
                  >*</font
                >試験年</label
              >
              <div class="col-sm-3">
                <input
                  type="text"
                  name="dumpExamTimeYear"
                  maxlength="4"
                  minlength="4"
                  class="form-control"
                  value="${dumpExamTimeYear}"
                  oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
                  required
                />
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-1"></label>
              <label class="col-sm-2"
                ><font color="red" style="font-weight: bold; font-size: large"
                  >*</font
                >オフィスメール</label
              >
              <div class="col-sm-3">
                <input
                  type="email"
                  name="dumpOfficeMail"
                  maxlength="50"
                  class="form-control"
                  value="${dumpOfficeMail}"
                  required
                />
              </div>
            </div>
            <div class="form-group form-inline">
              <label class="col-sm-1"></label>
              <label class="col-sm-2"
                ><font color="red" style="font-weight: bold; font-size: large"
                  >*</font
                >申込開始日</label
              >
              <div class="col-sm-3">
                <sx:datetimepicker
                  name="dumpApliStartDate"
                  displayFormat="yyyy-MM-dd"
                  cssClass="form-control"
                  style="width: 50%"
                />
              </div>
            </div>
            <div class="form-group form-inline">
              <label class="col-sm-1"></label>
              <label class="col-sm-2"
                ><font color="red" style="font-weight: bold; font-size: large"
                  >*</font
                >申込終了日</label
              >
              <div class="col-sm-3">
                <sx:datetimepicker
                  name="dumpApliEndDate"
                  displayFormat="yyyy-MM-dd"
                  cssClass="form-control"
                  style="width: 50%"
                />
              </div>
            </div>     
            <div class="form-group form-inline">
              <label class="col-sm-1"></label>
              <label class="col-sm-2"
                ><font color="red" style="font-weight: bold; font-size: large"
                  >*</font
                >試験の詳細日程</label
              >
              <div class="col-sm-2">
                <sx:datetimepicker
                  name="dumpExamInformStartDate"
                  displayFormat="yyyy-MM-dd"
                  cssClass="form-control"
                  style="width: 100%"
                />
              </div>
				<div class="col-sm-2 d-flex align-items-center justify-content-end" style="width: 35px; padding: 0;">
	  				<span style="display: flex; align-items: center; justify-content: flex-end; font-weight: bold; font-size: large;">~</span>
				</div>
				<div class="col-sm-2">
	                <sx:datetimepicker
	                  name="dumpExamInformEndDate"
	                  displayFormat="yyyy-MM-dd"
	                  cssClass="form-control"
	                  style="width: 100%"
	                />
              </div>
            </div>
            
            <hr />
            <h4>Yangon情報</h4>
            <div class="form-group form-inline">
              <label class="col-sm-1"></label>
              <label class="col-sm-2"
                ><font color="red" style="font-weight: bold; font-size: large"
                  >*</font
                >試験日</label
              >
              <div class="col-sm-3">
                <sx:datetimepicker
                  name="dumpYgnTime"
                  displayFormat="yyyy-MM-dd"
                  cssClass="form-control"
                  style="width: 50%"
                />
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-1"></label>
              <label class="col-sm-2"
                ><font color="red" style="font-weight: bold; font-size: large"
                  >*</font
                >試験場所</label
              >
              <div class="col-sm-3">
                <input
                  type="text"
                  name="dumpYgnExamPlace"
                  maxlength="50"
                  class="form-control"
                  value="${dumpYgnExamPlace}"
                  required
                />
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-1"></label>
              <label class="col-sm-2"
                ><font color="red" style="font-weight: bold; font-size: large"
                  >*</font
                >ビデオURL</label
              >
              <div class="col-sm-3">
                <input
                  type="text"
                  name="dumpOfficeUrl"
                  maxlength="50"
                  class="form-control"
                  value="${dumpOfficeUrl}"
                  required
                />
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-1"></label>
              <label class="col-sm-2"
                ><font color="red" style="font-weight: bold; font-size: large"
                  >*</font
                >PDFファイル</label
              >
              <div class="col-sm-3">
                <!-- <input
                  type="text"
                  name="dumpPdf"
                  maxlength="50"
                  class="form-control"
                  value="${dumpPdf}"
                  required
                />  -->
                <textarea name="dumpPdf" class="form-control" required>${dumpPdf}</textarea>
              </div>
            </div>
            <hr />
            <h4>Mandalay情報</h4>
            <div class="form-group form-inline">
              <label class="col-sm-1"></label>
              <label class="col-sm-2"
                ><font color="red" style="font-weight: bold; font-size: large"
                  >*</font
                >試験日</label
              >
              <div class="col-sm-3">
                <sx:datetimepicker
                  name="dumpMdyTime"
                  displayFormat="yyyy-MM-dd"
                  cssClass="form-control"
                  style="width: 50%"
                />
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-1"></label>
              <label class="col-sm-2"
                ><font color="red" style="font-weight: bold; font-size: large"
                  >*</font
                >試験場所</label
              >
              <div class="col-sm-3">
                <input
                  type="text"
                  name="dumpMdyExamPlace"
                  maxlength="50"
                  class="form-control"
                  value="${dumpMdyExamPlace}"
                  required
                />
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-6 text-right">
                <s:a href="registerManagement" cssClass="btn btn-primary btn-md"
                  >戻る</s:a
                >
                <button type="submit" name="btn" class="btn btn-primary btn-md">
                  編集
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </section>
    <script>
      $(document).ready(function () {
        if (!(<s:property value="dumpDelFlag" />)) {
          $("#delFlagCheck").prop("checked", false);
          $("#delFlagCheck").val(0);
        } else {
          $("#delFlagCheck").prop("checked", true);
          $("#delFlagCheck").val(1);
        }
        $("#delFlagCheck").click(function () {
          if ($("#delFlagCheck").is(":checked")) {
            $("#delFlagCheck").val(1);
          } else {
            $("#delFlagCheck").val(0);
          }
        });
      });
    </script>
  </body>
</html>
