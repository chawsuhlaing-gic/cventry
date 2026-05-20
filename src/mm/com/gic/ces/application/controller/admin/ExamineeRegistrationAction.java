/**
 * CV_A_032_受験者情報登録画面
 * 作成履歴：2019/1/22 Saw Yu Nwe 
 * 作成概要：
 * 新規作成：　受験者情報登録処理
 * 
 * 更新履歴：dd/mm/yyyy name
 * 更新概要：XXXXXXXX	
 */
package mm.com.gic.ces.application.controller.admin;
import java.io.IOException;
import java.sql.SQLException;
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
import mm.com.gic.ces.application.model.Applicant;
import mm.com.gic.ces.application.model.ApplicantInfo;
import mm.com.gic.ces.application.model.Company;
import mm.com.gic.ces.application.model.Examinee;
import mm.com.gic.ces.application.model.Pagination;
import mm.com.gic.ces.application.model.RoleSetting;
import mm.com.gic.ces.application.property.ExamPlace;
import mm.com.gic.ces.base.common.CommonService;
import mm.com.gic.ces.base.service.admin.ExamineeService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 受験者の受験情報を登録する
 */
@SuppressWarnings("serial")
public class ExamineeRegistrationAction extends ActionSupport {
	HttpServletRequest request = ServletActionContext.getRequest();       // サーブレットリクエスト
	HttpSession httpSession = request.getSession();                       // サーブレットリクエストセッション
	public CommonService commonService = new CommonService();             // 共通サービスオブジェクト
	private ExamineeService examineeService = new ExamineeService();      // 受験者サービスオブジェクト
	private Examinee examinee = new Examinee();                           // 受験者オブジェクト
	private Applicant applicant = new Applicant();                        // 申請者オブジェクト
	private RoleSetting roleSetting = new RoleSetting();                  // ロール設定オブジェクト
	private Company firstCom = new Company();                             // 第一希望会社オブジェクト
	private Company secondCom = new Company();                             // 第二希望会社オブジェクト
	private ApplicantInfo applicantInfo = new ApplicantInfo();            // 結合オブジェクト
	private List<ApplicantInfo> app_List = new ArrayList<ApplicantInfo>();// 受験者リスト
	private List<Company> com_List = new ArrayList<Company>();            // 会社リスト
	private List<Integer> lstYear = new ArrayList<Integer>();             // JobFair年リスト
	private List<ExamPlace> lstExamPlace = new ArrayList<ExamPlace>();    // 受験場所リスト
	private List<ApplicantInfo> lstAppPagination = new ArrayList<ApplicantInfo>();// ページ区切り申請者リスト
	private List<Integer> lstAge = new ArrayList<Integer>();                // 年齢リスト
	static Map<String, Object> mapSession;                                  // マップセッション
	private int ddlJFYear;                                                  // JobFair年
	private String ddlExamPlace;                                            // 受験場所
	private Date dtpStartDate;                                              // 開始日
	private Date dtpEndDate;                                                // 終了日
	private String txtStartExamID;                                          // 開始受験ID
	private String txtEndExamID;                                            // 終了受験ID
	private int app_ID;                                                   // 申請者ID
	private String btn;                                                     // ボタン
	@SuppressWarnings("rawtypes")
	private Pagination pagination = new Pagination();                     //ページネーションオブジェクト
	private int noOfRecordsPerPage;                                       //1ページあたりのレコード数
	private String errorMsg = null;                                       //エラーメッセージ
	private String successMsg = null;                                     //成功メッセージ
    private CommonUtility comUtility = new CommonUtility();
	
	/**
	 * ...申請者情報を取る...
	 * @return SUCCESS
	 * @throws IOException
	 * @throws SQLException
	 */
	public String getApplicantList() throws IOException, SQLException {
		lstYear = commonService.selectJFYear();
		lstExamPlace=CommonUtility.getExamPlace();
		return SUCCESS;
	}

	/**
	 * ...申請者を検索する。...
	 * @return SUCCESS
	 * @throws SQLException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String searchApplicant() throws SQLException, IOException {
		if(btn == null) {
			lstYear = commonService.selectJFYear();
			lstExamPlace=CommonUtility.getExamPlace();
		}
		else if (getBtn().equals("検索")) {
			lstYear = commonService.selectJFYear();
			lstExamPlace = CommonUtility.getExamPlace();
			int appCount = comUtility.getAppCount(getTxtStartExamID(), getTxtEndExamID(), getDtpStartDate(), 
					getDtpEndDate(), getDdlExamPlace(), getDdlJFYear(), 32);
			if(appCount != 0) {
				if(appCount <= 30) {
					app_List = examineeService.searchApplicant(getDdlJFYear(), getDdlExamPlace(),
							getDtpStartDate(), getDtpEndDate(), getTxtStartExamID(), getTxtEndExamID());
					//pagination
					pagination = getExamineePagination(pagination, app_List);
					app_List = new ArrayList<ApplicantInfo>();
					app_List = pagination.getTList();
					for (ApplicantInfo app : app_List) {
						lstAge.add(CommonRegister.calculateAge(app.getApplicant().getApp_DOB()));
					}
					httpSession.setAttribute("jfyear", getDdlJFYear());
					httpSession.setAttribute("examPlace", getDdlExamPlace());
					httpSession.setAttribute("sDate", getDtpStartDate());
					httpSession.setAttribute("eDate", getDtpEndDate());
					httpSession.setAttribute("sExamId", getTxtStartExamID());
					httpSession.setAttribute("eExamId", getTxtEndExamID());
					com_List = examineeService.getCompanyList((Integer) (httpSession.getAttribute("jfyear")));
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
		} else if (getBtn().equals("キャンセル")) {
			lstYear = commonService.selectJFYear();
			lstExamPlace=CommonUtility.getExamPlace();
			setDdlJFYear(0);
			setDdlExamPlace(null);
			setTxtStartExamID("");
			setTxtEndExamID("");
			setDtpStartDate(null);
			setDtpEndDate(null);
			applicantInfo = new ApplicantInfo();
		}
		return SUCCESS;
	}

	/**
	 * ページネーション ボタン処理
	 * @return SUCCESS
	 * @throws SQLException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String paginateExamineeList() throws SQLException, IOException{
		int jfYear=(int) httpSession.getAttribute("jfyear");
		String examPlace=(String) httpSession.getAttribute("examPlace");
		String sExamID=(String) httpSession.getAttribute("sExamId");
		String eExamID=(String) httpSession.getAttribute("eExamId");
		Date regSDate=(Date) httpSession.getAttribute("sDate");
		Date regEDate=(Date) httpSession.getAttribute("eDate");
		app_List = examineeService.searchApplicant(jfYear, examPlace,
				regSDate, regEDate, sExamID, eExamID);
		if (app_List.size() != 0) {
			//pagination
			pagination = getExamineePagination(pagination, app_List);
			app_List = new ArrayList<ApplicantInfo>();
			app_List = pagination.getTList();
			for (ApplicantInfo app : app_List) {
				lstAge.add(CommonRegister.calculateAge(app.getApplicant().getApp_DOB()));
			}
		} 
		com_List = examineeService.getCompanyList(jfYear);
		lstYear = commonService.selectJFYear();
		lstExamPlace=CommonUtility.getExamPlace();
		setDdlJFYear(jfYear);
		setDdlExamPlace(examPlace);
		setTxtStartExamID(sExamID);
		setTxtEndExamID(eExamID);
		setDtpStartDate(regSDate);
		setDtpEndDate(regEDate);
		return SUCCESS;
	}

	/**
	 * ...申請者リストをページ付ける...
	 * @param pagination ページネーションオブジェクト
	 * @param applicantList 申請者リスト
	 * @return pagination ページネーションした申請者情報
	 * @throws IOException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Pagination getExamineePagination(Pagination pagination,List<ApplicantInfo> examineeList) throws IOException {
		Properties prop= CommonUtility.getValue("PaginationRecord.properties");		
		noOfRecordsPerPage =Integer.parseInt(prop.getProperty(Integer.toString(2)));
		List<ApplicantInfo> selectedUser = new ArrayList<ApplicantInfo>();
		int totalNoOfRecords = examineeList.size();
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
				selectedUser.add(examineeList.get(i));
			}
		} else if (totalPages == pagination.getSelectedPageNumber()) {
			for (int i = startIndex; i < examineeList.size(); i++) {
				if (examineeList.get(i) != null) {
					ApplicantInfo obj_applicantInfo = new ApplicantInfo();
					obj_applicantInfo = examineeList.get(i);
					selectedUser.add(obj_applicantInfo);
				} else {
					break;
				}
			}
		} else {
			selectedUser.addAll(examineeList);
		}
		pagination.setTList(selectedUser);
		return pagination;
	}

	/**
	 *...受験者を登録するまたは更新する。...
	 * @return SUCCESS
	 * @throws SQLException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String insertExamineeInfo() throws SQLException, IOException {
		if(btn == null) {
			lstYear = commonService.selectJFYear();
			lstExamPlace=CommonUtility.getExamPlace();
		}
		else if (getBtn().equals("登録")) {
		Examinee examineeObj = null;
		Date date = new Date();
		mapSession = ActionContext.getContext().getSession();
		examineeObj = examineeService.checkExaminee(getApp_ID());
		if (examineeObj == null) {
			if (examinee.getFirstCompany().getCom_ID() == examinee.getSecondCompany().getCom_ID()) {
				firstCom = examineeService.searchCompany(examinee.getFirstCompany().getCom_ID());
				examinee.setFirstCompany(firstCom);
				examinee.setSecondCompany(firstCom);
			} else {
				firstCom = examineeService.searchCompany(examinee.getFirstCompany().getCom_ID());
				secondCom = examineeService.searchCompany(examinee.getSecondCompany().getCom_ID());
				examinee.setFirstCompany(firstCom);
				examinee.setSecondCompany(secondCom);
			}
			applicant = examineeService.searchApplicant(getApp_ID());
			roleSetting = (RoleSetting) mapSession.get("loggedInadmin");
			examinee.setExaminee_EQmark(examinee.getExaminee_EQmark());
			examinee.setExaminee_IQmark(examinee.getExaminee_IQmark());
			examinee.setExaminee_Pass("N");
			examinee.setExaminee_ex_key(1);
			examinee.setLast_updateUser(roleSetting.getRole_Name());
			examinee.setLast_updateTime(date);
			examinee.setApplicant(applicant);
			examineeService.insertExaminee(examinee);
			Properties prop = CommonUtility.getValue("Message.properties");
			successMsg = prop.getProperty(Integer.toString(2));
			addActionMessage(successMsg);
		} else {
			if (examineeObj.getExaminee_ex_key() == examinee.getExaminee_ex_key()) {
				Examinee examineeTemp = examineeService.searchExaminee(examineeObj.getExaminee_ID());
				if (examineeTemp != null) {
					if (examinee.getFirstCompany().getCom_ID() == examinee.getSecondCompany().getCom_ID()) {
						firstCom = examineeService.searchCompany(examinee.getFirstCompany().getCom_ID());
						examineeTemp.setFirstCompany(firstCom);
						examineeTemp.setSecondCompany(firstCom);
					} else {
						firstCom = examineeService.searchCompany(examinee.getFirstCompany().getCom_ID());
						secondCom = examineeService.searchCompany(examinee.getSecondCompany().getCom_ID());
						examineeTemp.setFirstCompany(firstCom);
						examineeTemp.setSecondCompany(secondCom);
					}
					roleSetting = (RoleSetting) mapSession.get("loggedInadmin");
					examineeTemp.setExaminee_EQmark(examinee.getExaminee_EQmark());
					examineeTemp.setExaminee_IQmark(examinee.getExaminee_IQmark());
					examineeTemp.setExaminee_ex_key(examinee.getExaminee_ex_key() + 1);
					examineeTemp.setLast_updateUser(roleSetting.getRole_Name());
					examineeTemp.setLast_updateTime(date);
					examineeService.updateExaminee(examineeTemp);
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
		app_List = examineeService.searchApplicant(
				(Integer) (httpSession.getAttribute("jfyear")),
				(String) httpSession.getAttribute("examPlace"),
				(Date) httpSession.getAttribute("sDate"),
				(Date) httpSession.getAttribute("eDate"),
				(String) httpSession.getAttribute("sExamId"),
				(String) httpSession.getAttribute("eExamId"));
		if (app_List.size() != 0) {
			//pagination
			pagination = getExamineePagination(pagination, app_List);
			app_List = new ArrayList<ApplicantInfo>();
			app_List = pagination.getTList();
			for (ApplicantInfo app : app_List) {
				lstAge.add(CommonRegister.calculateAge(app.getApplicant().getApp_DOB()));
			}
		} 
		com_List = examineeService.getCompanyList((Integer) (httpSession.getAttribute("jfyear")));
		lstYear = commonService.selectJFYear();
		lstExamPlace=CommonUtility.getExamPlace();
		setDdlJFYear((Integer) (httpSession.getAttribute("jfyear")));
		setDdlExamPlace((String) httpSession.getAttribute("examPlace"));
		setDtpStartDate((Date) httpSession.getAttribute("sDate"));
		setDtpEndDate((Date) httpSession.getAttribute("eDate"));
		setTxtStartExamID((String) httpSession.getAttribute("sExamId"));
		setTxtEndExamID((String) httpSession.getAttribute("eExamId"));
		return SUCCESS;
	}

	/**
	 * ...受験IDをチェックする...
	 * @param jfExamPlace
	 * @param sExamID       開始受験ID
	 * @param eExamID       終了受験ID
	 * @param sRegDate      登録開始日
	 * @param eRegDate      登録終了日
	 * @param examPlace     受験場所
	 * @param jfYear        JobFair年
	 * @return lstApplicant 申請者リスト
	 */
	public List<ApplicantInfo> validateExamID(boolean jfExamPlace,String sExamID, String eExamID,
			Date sRegDate, Date eRegDate,String examPlace, int jfYear) {
		List<ApplicantInfo> lstApplicant = new ArrayList<ApplicantInfo>();
		if (examPlace.toUpperCase().equals("Yangon".toUpperCase())&& jfExamPlace == true) {
			lstApplicant = examineeService.searchApplicant(jfYear, examPlace,sRegDate, eRegDate,
					sExamID, eExamID);
		} else if (examPlace.toUpperCase().equals("Mandalay".toUpperCase())&& jfExamPlace == true) {
			lstApplicant = examineeService.searchApplicant(jfYear, examPlace,sRegDate, eRegDate,
					sExamID, eExamID);
		} else {
			lstApplicant = new ArrayList<ApplicantInfo>();
		}
		return lstApplicant;
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

	// 受験者オブジェクト
	public Examinee getExaminee() {
		return examinee;
	}

	public void setExaminee(Examinee examinee) {
		this.examinee = examinee;
	}

	// 申請者オブジェクト
	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	// 第一希望会社オブジェクト1
	public Company getFirstCom() {
		return firstCom;
	}

	public void setFirstCom(Company firstCom) {
		this.firstCom = firstCom;
	}

	// 第二希望会社オブジェクト
	public Company getSecondCom() {
		return secondCom;
	}

	public void setSecondCom(Company secondCom) {
		this.secondCom = secondCom;
	}

	// 結合オブジェクト
	public ApplicantInfo getApplicantInfo() {
		return applicantInfo;
	}

	public void setApplicantInfo(ApplicantInfo applicantInfo) {
		this.applicantInfo = applicantInfo;
	}

	// 受験者リスト
	public List<ApplicantInfo> getApp_List() {
		return app_List;
	}

	public void setApp_List(List<ApplicantInfo> app_List) {
		this.app_List = app_List;
	}

	// 会社リスト
	public List<Company> getCom_List() {
		return com_List;
	}

	public void setCom_List(List<Company> com_List) {
		this.com_List = com_List;
	}

	// JobFair年リスト
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

	// 年齢リスト
	public List<Integer> getLstAge() {
		return lstAge;
	}

	public void setLstAge(List<Integer> lstAge) {
		this.lstAge = lstAge;
	}

	// ページ区切り申請者リスト
	public List<ApplicantInfo> getLstAppPagination() {
		return lstAppPagination;
	}

	public void setLstAppPagination(List<ApplicantInfo> lstAppPagination) {
		this.lstAppPagination = lstAppPagination;
	}

	// JobFair年
	public int getDdlJFYear() {
		return ddlJFYear;
	}

	public void setDdlJFYear(int ddlJFYear) {
		this.ddlJFYear = ddlJFYear;
	}

	// 受験場所	
	public String getDdlExamPlace() {
		return ddlExamPlace;
	}

	public void setDdlExamPlace(String ddlExamPlace) {
		this.ddlExamPlace = ddlExamPlace;
	}

	// 開始日
	public Date getDtpStartDate() {
		return dtpStartDate;
	}

	public void setDtpStartDate(Date dtpStartDate) {
		this.dtpStartDate = dtpStartDate;
	}

	// 終了日
    public Date getDtpEndDate() {
		return dtpEndDate;
	}

	public void setDtpEndDate(Date dtpEndDate) {
		this.dtpEndDate = dtpEndDate;
	}

	// 開始受験ID
	public String getTxtStartExamID() {
		return txtStartExamID;
	}

	public void setTxtStartExamID(String txtStartExamID) {
		this.txtStartExamID = txtStartExamID;
	}

	// 終了受験ID
	public String getTxtEndExamID() {
		return txtEndExamID;
	}

	public void setTxtEndExamID(String txtEndExamID) {
		this.txtEndExamID = txtEndExamID;
	}

	// 申請者ID
	public int getApp_ID() {
		return app_ID;
	}

	public void setApp_ID(int app_ID) {
		this.app_ID = app_ID;
	}

	// ボータン
	public String getBtn() {
		return btn;
	}

	public void setBtn(String btn) {
		this.btn = btn;
	}
}