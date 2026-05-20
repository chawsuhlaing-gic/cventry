/**
 * CV_A_042_会社別合格者情報登録画面
 * 作成履歴：2019/2/5 Saw Yu Nwe 
 * 作成概要：
 * 新規作成：　会社別合格者情報登録処理
 * 
 * 更新履歴：dd/mm/yyyy name
 * 更新概要：XXXXXXXX	
 */
package mm.com.gic.ces.application.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import mm.com.gic.ces.application.common.CommonRegister;
import mm.com.gic.ces.application.common.CommonUtility;
import mm.com.gic.ces.application.model.ApplicantInfo;
import mm.com.gic.ces.application.model.Company;
import mm.com.gic.ces.application.model.Examinee;
import mm.com.gic.ces.application.model.Interview;
import mm.com.gic.ces.application.model.Pagination;
import mm.com.gic.ces.application.model.RoleSetting;
import mm.com.gic.ces.application.property.ExamPlace;
import mm.com.gic.ces.base.common.CommonService;
import mm.com.gic.ces.base.service.admin.IntervieweeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 会社別合格者情報を登録する
 */
public class IntervieweeRegistrationAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = ServletActionContext.getRequest();         // サーブレットリクエスト
	HttpSession httpSession = request.getSession();                         // サーブレットリクエストセッション
	static Map<String, Object> mapSession;                                  // マップセッション
	private Interview interview = new Interview();                          // 面接者オブジェクト
	private RoleSetting roleSetting = new RoleSetting();                    // ロール設定オブジェクト
	private ApplicantInfo applicantInfo = new ApplicantInfo();              // 結合オブジェクト
	private CommonService commonService = new CommonService();              // 共通サービスオブジェクト
	private IntervieweeService inwService = new IntervieweeService();       // 面接サービスオブジェクト
	private List<ApplicantInfo> examinee_List = new ArrayList<ApplicantInfo>();//　面接者リスト
	private List<Integer> lstYear = new ArrayList<Integer>();               //　JobFair年リスト
	private List<ExamPlace> lstExamPlace = new ArrayList<ExamPlace>();    // 受験場所リスト
	private List<Company> lstCompany = new ArrayList<Company>();            //　会社リスト
	private List<Integer> lstAge = new ArrayList<Integer>();                // 年齢リスト
	private String interviewDate;                                           //　面接日
	private String startTime;                                               //　開始時間
	private String endTime;                                                 //　終了時間
	private int examinee_ID;                                                //　受験ID
	private String ddlFirstCompany;                                         //　第一希望会社名
	private String ddlSecondCompany;                                        //　第二希望会社名
	private int ddlJFYear;                                                  // JobFair年
	private String ddlExamPlace;                                            // 受験場所
	private Date dtpStartDate;                                              // 開始日
	private Date dtpEndDate;                                                // 終了日
	private String txtStartExamID;                                          // 開始受験ID
	private String txtEndExamID;                                            // 終了受験ID
	private String btn;                                                     // ボタン
	@SuppressWarnings("rawtypes")
	private Pagination pagination = new Pagination();                       //ページネーションオブジェクト
	private int noOfRecordsPerPage;                                         //1ページあたりのレコード数
	private String errorMsg = null;                                         //エラーメッセージ
	private String successMsg = null;                                       //成功メッセージ

	/**
	 * ...受験者情報を取る...
	 * @return success
	 * @throws IOException
	 */
	public String getExamineeList() throws IOException {
		lstYear = commonService.selectJFYear();
		lstExamPlace=CommonUtility.getExamPlace();
		return "success";
	}

	/**
	 * ...受験者を検索する。...
	 * @return success
	 * @throws SQLException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String searchExaminee() throws SQLException, IOException {
		if (btn == null) {
			if (ddlJFYear != 0) {
				lstCompany = commonService.getCompany(ddlJFYear);
			} else {
				lstCompany = new ArrayList<Company>();
			}
			lstYear = commonService.selectJFYear();
			lstExamPlace=CommonUtility.getExamPlace();
		} else if (getBtn().equals("検索")) {
			lstYear = commonService.selectJFYear();
			lstExamPlace=CommonUtility.getExamPlace();
			int appCount = inwService.getExamCount(getTxtStartExamID(), getTxtEndExamID(), getDtpStartDate(), 
					getDtpEndDate(), getDdlExamPlace(), getDdlJFYear(), getDdlFirstCompany(), getDdlSecondCompany());
			if(appCount != 0) {
			if(appCount <= 30) {
				examinee_List = inwService.searchExaminee(getDdlJFYear(), getDdlExamPlace(),
						getDtpStartDate(), getDtpEndDate(), getTxtStartExamID(), getTxtEndExamID(),
						getDdlFirstCompany(), getDdlSecondCompany());
				//pagination
				pagination = getInterviewPagination(pagination, examinee_List);
				examinee_List = new ArrayList<ApplicantInfo>();
				examinee_List = pagination.getTList();
				for (ApplicantInfo exam : examinee_List) {
					lstAge.add(CommonRegister.calculateAge(exam.getExaminee().getApplicant().getApp_DOB()));
				}
			} else {
				Properties prop = CommonUtility.getValue("Message.properties");
				errorMsg = prop.getProperty(Integer.toString(39));
				addActionError(errorMsg);
			}
		} else {
			Properties prop = CommonUtility.getValue("Message.properties");
			errorMsg = prop.getProperty(Integer.toString(19));
			addActionError(errorMsg);
			}
			httpSession.setAttribute("jfyear", getDdlJFYear());
			httpSession.setAttribute("examPlace", getDdlExamPlace());
			httpSession.setAttribute("sDate", getDtpStartDate());
			httpSession.setAttribute("eDate", getDtpEndDate());
			httpSession.setAttribute("sExamId", getTxtStartExamID());
			httpSession.setAttribute("eExamId", getTxtEndExamID());
			httpSession.setAttribute("firstCom", getDdlFirstCompany());
			httpSession.setAttribute("secondCom", getDdlSecondCompany());
			lstCompany = commonService.getCompany((Integer)httpSession.getAttribute("jfyear"));
		} else if (getBtn().equals("キャンセル")) {
			lstYear = commonService.selectJFYear();
			lstExamPlace=CommonUtility.getExamPlace();
			setDdlJFYear(0);
			setDdlExamPlace(null);
			setTxtStartExamID("");
			setTxtEndExamID("");
			setDtpStartDate(null);
			setDtpEndDate(null);
			setDdlFirstCompany(null);
			setDdlSecondCompany(null);
			applicantInfo = new ApplicantInfo();
		}
		return "success";
	}

	/**
	 * ...受験IDをチェックする...
	 * @param jfExamPlace
	 * @param sExamID 開始受験ID
	 * @param eExamID 終了受験ID
	 * @param sRegDate 登録開始日
	 * @param eRegDate 登録終了日
	 * @param examPlace 受験場所
	 * @param jfYear   JobFair年
	 * @param firstCom 第一希望会社名
	 * @param secondCom　第二希望会社名
	 * @return lstExaminee 受験者リスト
	 */
	public List<ApplicantInfo> validateExamID(boolean jfExamPlace,
			String sExamID, String eExamID, Date sRegDate, Date eRegDate,
			String examPlace, int jfYear, String firstCom, String secondCom) {
		List<ApplicantInfo> lstExaminee = new ArrayList<ApplicantInfo>();
		if (examPlace.toUpperCase().equals("Yangon".toUpperCase())
				&& jfExamPlace == true) {
			lstExaminee = inwService.searchExaminee(jfYear, examPlace,
					sRegDate, eRegDate, sExamID, eExamID, firstCom, secondCom);
		} else if (examPlace.toUpperCase().equals("Mandalay".toUpperCase())
				&& jfExamPlace == true) {
			lstExaminee = inwService.searchExaminee(jfYear, examPlace,
					sRegDate, eRegDate, sExamID, eExamID, firstCom, secondCom);
		} else {
			lstExaminee = new ArrayList<ApplicantInfo>();
		}
		return lstExaminee;
	}

	/**
	 * ...面接者情報を登録するまたは更新する...
	 * @return success
	 * @throws SQLException
	 * @throws ParseException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String insertIntervieweeInfo() throws SQLException, ParseException, IOException {
		if(btn == null) {
			lstYear = commonService.selectJFYear();
			lstExamPlace=CommonUtility.getExamPlace();
		}
		else if (getBtn().equals("登録")){
		Interview interviewObj = null;
		Date date = new Date();
		mapSession = ActionContext.getContext().getSession();
		roleSetting = (RoleSetting) mapSession.get("loggedInadmin");
		Company company = inwService.searchCompany(interview.getCompany().getCom_ID());
		interviewObj = inwService.checkInterview(getExaminee_ID());
		if (interviewObj == null) {
			Examinee examinee = new Examinee();
			examinee = inwService.searchExaminee(getExaminee_ID());
			interview.setInterviewer(interview.getInterviewer());
			interview.setInterview_Pass("N");
			interview.setInterview_Date(getInterviewDate());
			interview.setInterview_StartTime(getStartTime());
			interview.setInterview_EndTime(getEndTime());
			interview.setInterview_Place(interview.getInterview_Place());
			interview.setInterview_ex_key(1);
			interview.setLast_updateUser(roleSetting.getRole_Name());
			interview.setLast_updateTime(date);
			interview.setCompany(company);
			interview.setExaminee(examinee);
			inwService.insertInterview(interview);
			Properties prop = CommonUtility.getValue("Message.properties");
			successMsg = prop.getProperty(Integer.toString(2));
			addActionMessage(successMsg);
		} else {
			if (interviewObj.getInterview_ex_key() == interview.getInterview_ex_key()) {
				Interview interviewTemp = inwService.searchInterview(interviewObj.getInterview_ID());
				if (interviewTemp != null) {
					interviewTemp.setInterview_ID(interviewObj.getInterview_ID());
					interviewTemp.setInterviewer(interview.getInterviewer());
					interviewTemp.setInterview_Date(getInterviewDate());
					interviewTemp.setInterview_StartTime(getStartTime());
					interviewTemp.setInterview_EndTime(getEndTime());
					interviewTemp.setInterview_Place(interview.getInterview_Place());
					interviewTemp.setInterview_ex_key(interview.getInterview_ex_key() + 1);
					interviewTemp.setLast_updateUser(roleSetting.getRole_Name());
					interviewTemp.setLast_updateTime(date);
					interviewTemp.setCompany(company);
					inwService.updateInterview(interviewTemp);
					Properties prop = CommonUtility.getValue("Message.properties");
					successMsg = prop.getProperty(Integer.toString(1));
					addActionMessage(successMsg);
				}
			} else {
				Properties prop = CommonUtility.getValue("Message.properties");
				errorMsg = prop.getProperty(Integer.toString(21));
				addActionError(errorMsg);
			}
		}
	}
		examinee_List = inwService.searchExaminee(
				(Integer) httpSession.getAttribute("jfyear"),
				(String) httpSession.getAttribute("examPlace"),
				(Date) httpSession.getAttribute("sDate"),
				(Date) httpSession.getAttribute("eDate"),
				(String) httpSession.getAttribute("sExamId"),
				(String) httpSession.getAttribute("eExamId"),
				(String) httpSession.getAttribute("firstCom"),
				(String) httpSession.getAttribute("secondCom"));
		lstYear = commonService.selectJFYear();
		lstExamPlace=CommonUtility.getExamPlace();
		lstCompany = commonService.getCompany((Integer) httpSession.getAttribute("jfyear"));
		if (examinee_List.size() != 0) {
			//pagination
			pagination = getInterviewPagination(pagination, examinee_List);
			examinee_List = new ArrayList<ApplicantInfo>();
			examinee_List = pagination.getTList();
			for (ApplicantInfo exam : examinee_List) {
				lstAge.add(CommonRegister.calculateAge(exam.getExaminee().getApplicant().getApp_DOB()));
			}
		}
		setDdlJFYear((Integer) (httpSession.getAttribute("jfyear")));
		setDdlExamPlace((String) httpSession.getAttribute("examPlace"));
		setDtpStartDate((Date) httpSession.getAttribute("sDate"));
		setDtpEndDate((Date) httpSession.getAttribute("eDate"));
		setTxtStartExamID((String) httpSession.getAttribute("sExamId"));
		setTxtEndExamID((String) httpSession.getAttribute("eExamId"));
		setDdlFirstCompany((String) httpSession.getAttribute("firstCom"));
		setDdlSecondCompany((String) httpSession.getAttribute("secondCom"));
		return "success";
		
	}

	/**
	 * ...ページネーションボタン処理...
	 * @return　SUCCESS
	 * @throws SQLException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String paginateInterviewList() throws SQLException, IOException{
		int jfYear=(int) httpSession.getAttribute("jfyear");
		String examPlace=(String) httpSession.getAttribute("examPlace");
		String sExamID=(String) httpSession.getAttribute("sExamId");
		String eExamID=(String) httpSession.getAttribute("eExamId");
		Date regSDate=(Date) httpSession.getAttribute("sDate");
		Date regEDate=(Date) httpSession.getAttribute("eDate");
		String firstCom=(String) httpSession.getAttribute("firstCom");
		String secondCom=(String) httpSession.getAttribute("secondCom");
		examinee_List = inwService.searchExaminee(jfYear, examPlace,regSDate, regEDate,
				sExamID, eExamID, firstCom, secondCom);
		if (examinee_List.size() != 0) {
			//pagination
			pagination = getInterviewPagination(pagination, examinee_List);
			examinee_List = new ArrayList<ApplicantInfo>();
			examinee_List = pagination.getTList();
			for (ApplicantInfo exam : examinee_List) {
				lstAge.add(CommonRegister.calculateAge(exam.getExaminee().getApplicant().getApp_DOB()));
			}
		}
		lstYear = commonService.selectJFYear();
		lstExamPlace=CommonUtility.getExamPlace();
		lstCompany = commonService.getCompany((Integer)httpSession.getAttribute("jfyear"));
		setDdlJFYear(jfYear);
		setDdlExamPlace(examPlace);
		setDtpStartDate(regSDate);
		setDtpEndDate(regEDate);
		setTxtStartExamID(sExamID);
		setTxtEndExamID(eExamID);
		setDdlFirstCompany(firstCom);
		setDdlSecondCompany(secondCom);
		return SUCCESS;
	}

	/**
	 *  ...合格者リストをページ付ける...
	 * @param pagination    ページネーションオブジェクト
	 * @param interviewList  合格者リスト
	 * @return pagination   ページネーションした合格者情報
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Pagination getInterviewPagination(Pagination pagination,List<ApplicantInfo> interviewList) throws IOException {
		Properties prop= CommonUtility.getValue("PaginationRecord.properties");		
		noOfRecordsPerPage =Integer.parseInt(prop.getProperty(Integer.toString(2)));
		List<ApplicantInfo> selectedUser = new ArrayList<ApplicantInfo>();
		int totalNoOfRecords = interviewList.size();
		int startIndex = 0;
		int totalPages = 1;
		if (totalNoOfRecords >noOfRecordsPerPage) {
			double noOfPages = (double) totalNoOfRecords / (double) noOfRecordsPerPage;
			totalPages = (int) noOfPages;
			if (noOfPages % totalPages > 0.0) {
				totalPages++;
			}
		}
		if (pagination.getSelectedPageNumber() > 1) {
			startIndex =noOfRecordsPerPage * (pagination.getSelectedPageNumber() - 1);
			pagination.setSerialNumberAddfactor(noOfRecordsPerPage 
					* (pagination.getSelectedPageNumber() - 1));
		}
		pagination.setTotalPages(totalPages);
		if (pagination.getSelectedPageNumber() == 0) {
			pagination.setSelectedPageNumber(1);
		}
		if (totalPages > 1 && pagination.getSelectedPageNumber() != totalPages) {
			for (int i = startIndex; i < noOfRecordsPerPage 
					* pagination.getSelectedPageNumber(); i++) {
				selectedUser.add(interviewList.get(i));
			}
		} else if (totalPages == pagination.getSelectedPageNumber()) {
			for (int i = startIndex; i < interviewList.size(); i++) {
				if (interviewList.get(i) != null) {
					ApplicantInfo obj_applicantInfo = new ApplicantInfo();
					obj_applicantInfo = interviewList.get(i);
					selectedUser.add(obj_applicantInfo);
				} else {
					break;
				}
			}
		} else {
			selectedUser.addAll(interviewList);
		}
		pagination.setTList(selectedUser);
		return pagination;
	}

	//ページネーションオブジェクト
	@SuppressWarnings("rawtypes")
	public Pagination getPagination() {
		return pagination;
	}

	@SuppressWarnings("rawtypes")
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	// 面接者オブジェクト
	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}

	// 結合オブジェクト
	public ApplicantInfo getApplicantInfo() {
		return applicantInfo;
	}

	public void setApplicantInfo(ApplicantInfo applicantInfo) {
		this.applicantInfo = applicantInfo;
	}

	//　面接者リスト
	public List<ApplicantInfo> getExaminee_List() {
		return examinee_List;
	}

	public void setExaminee_List(List<ApplicantInfo> examinee_List) {
		this.examinee_List = examinee_List;
	}

	//　JobFair年リスト
	public List<Integer> getLstYear() {
		return lstYear;
	}

	public void setLstYear(List<Integer> lstYear) {
		this.lstYear = lstYear;
	}

	// 受験場所リスト
	public List<ExamPlace> getLstExamPlace() {
		return lstExamPlace;
	}

	public void setLstExamPlace(List<ExamPlace> lstExamPlace) {
		this.lstExamPlace = lstExamPlace;
	}

	//　会社リスト
	public List<Company> getLstCompany() {
		return lstCompany;
	}

	public void setLstCompany(List<Company> lstCompany) {
		this.lstCompany = lstCompany;
	}

	// 年齢リスト
	public List<Integer> getLstAge() {
		return lstAge;
	}

	public void setLstAge(List<Integer> lstAge) {
		this.lstAge = lstAge;
	}

	//　面接日
	public String getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(String interviewDate) {
		this.interviewDate = interviewDate;
	}

	//　開始時間
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	//　終了時間
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	//　受験ID
	public int getExaminee_ID() {
		return examinee_ID;
	}

	public void setExaminee_ID(int examinee_ID) {
		this.examinee_ID = examinee_ID;
	}

	//第一希望会社名
	public String getDdlFirstCompany() {
		return ddlFirstCompany;
	}

	public void setDdlFirstCompany(String ddlFirstCompany) {
		this.ddlFirstCompany = ddlFirstCompany;
	}

	//第二希望会社名
	public String getDdlSecondCompany() {
		return ddlSecondCompany;
	}

	public void setDdlSecondCompany(String ddlSecondCompany) {
		this.ddlSecondCompany = ddlSecondCompany;
	}

	//　JobFair年
	public int getDdlJFYear() {
		return ddlJFYear;
	}

	public void setDdlJFYear(int ddlJFYear) {
		this.ddlJFYear = ddlJFYear;
	}

	//　受験場所
	public String getDdlExamPlace() {
		return ddlExamPlace;
	}

	public void setDdlExamPlace(String ddlExamPlace) {
		this.ddlExamPlace = ddlExamPlace;
	}

	//　開始日
	public Date getDtpStartDate() {
		return dtpStartDate;
	}

	public void setDtpStartDate(Date dtpStartDate) {
		this.dtpStartDate = dtpStartDate;
	}

	//　終了日
	public Date getDtpEndDate() {
		return dtpEndDate;
	}

	public void setDtpEndDate(Date dtpEndDate) {
		this.dtpEndDate = dtpEndDate;
	}

	//　開始受験ID
	public String getTxtStartExamID() {
		return txtStartExamID;
	}

	public void setTxtStartExamID(String txtStartExamID) {
		this.txtStartExamID = txtStartExamID;
	}

	//　終了受験ID
	public String getTxtEndExamID() {
		return txtEndExamID;
	}

	public void setTxtEndExamID(String txtEndExamID) {
		this.txtEndExamID = txtEndExamID;
	}

	//　ボータン
	public String getBtn() {
		return btn;
	}

	public void setBtn(String btn) {
		this.btn = btn;
	}
}