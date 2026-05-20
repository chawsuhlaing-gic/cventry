/**
 * CV_A_031_受験社決定画面
 * 作成履歴：19/04/2019 Khin Myo Wai
 * 作成概要：申請者出席を決定決定する処理
 * 
 * 更新履歴：10/05/2019 Khin Myo Wai
 * 更新概要：保存のメッセージとチェックボックスサイズ0場合のメッセージ追加	
 */

package mm.com.gic.ces.application.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import mm.com.gic.ces.application.common.CommonUtility;
import mm.com.gic.ces.application.model.Applicant;
import mm.com.gic.ces.application.property.ExamPlace;
import mm.com.gic.ces.base.common.CommonService;
import mm.com.gic.ces.base.service.admin.AttendedApplicantService;

public class AttendedApplicantDecisionAction extends ActionSupport {
	private static final long serialVersionUID = 1L;  
	public static Map<String, Object> mapSession = ActionContext.getContext().getSession();      // mapセッション
	public CommonService comService = new CommonService();                                       // 共通のサービスオブジェクト
	public AttendedApplicantService AttendedApplicantService = new AttendedApplicantService();   // 申請者サービスオブジェクト
	private List<Applicant> Applicantlist;                                                       // 申請者リスト
	private List<Applicant> searchApplicantList;                                                 // 検索申請者リスト
	private List<Applicant> attendedApplicantlisttocount;                                        // 申請者出席リストのカウント
	private List<Integer> jfYear;                                                                // Job Fair年リスト
	private Applicant applicant = new Applicant();                                               // 申請者物体
	private int ddlJFYear;                                                                       // JobFair年
	private String ddlExamPlace;                                                                 // 受験所 
	private Date dtpStartDate;                                                                   // 開始日
	private Date dtpEndDate;                                                                     // 終了日
	private String btn;                                                                          // ボタン名受ける
	private String txtStartExamID;                                                               // 開始受験ID
	private String txtEndExamID;                                                                 // 終了受験ID
	private boolean isModelDisplay;                                                              // ポップアップフォーム表示
	private String chkSelect;                                                                    // チェックボックス選択
	private List<Applicant> checkedApplicantList = new ArrayList<Applicant>();                   // チェックリスト
	private String checkedExamID;                                                                // 試験IDをチェック
	private int count;                                                                           // 申請者カウント
	private List<ExamPlace> lstExamPlace = new ArrayList<ExamPlace>();                           // 受験所 リスト    
    private Properties prop=new Properties();                                                    // プロパティ宣言   
    private String Msg;                                                                          //保存のメッセージ
    private String selectSize;                                                                   //チェックボックスサイズ0場合のメッセージ
    private String warnMsg;                                                                      //一人管理者使うメッセージ
    private CommonUtility commonUtility = new CommonUtility();
    
	/**
	 * メニューからフォームを読み込む
	 * @throws IOException
	 */
	public String AttendedApplicantList() throws SQLException, IOException {
		isModelDisplay = false;
		jfYear = comService.selectJFYear();
		lstExamPlace = CommonUtility.getExamPlace();
		return "AttendedApplicantList";
	}

	/**
	 * 申請者出席を決定決定する
	 * @return SUCCESS
	 * @throws SQLException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String AttendedApplicantDecision() throws SQLException, IOException {
		mapSession = ActionContext.getContext().getSession();
		isModelDisplay = false;
		prop = CommonUtility.getValue("Message.properties");
        if (getBtn().equals("検索")) {
        	int appCount = commonUtility.getAppCount(getTxtStartExamID(), getTxtEndExamID(), getDtpStartDate(), 
        			getDtpEndDate(), getDdlExamPlace(), getDdlJFYear(), 31);
			if(appCount != 0) {
				if(appCount <= 30) {
					Applicantlist = comService.getApplicantList(getDdlJFYear(), getDdlExamPlace(), getDtpStartDate(),
							getDtpEndDate(), getTxtStartExamID(), getTxtEndExamID(), 31);
					count=appCount;
					mapSession.put("SearchList", Applicantlist);
				} else {
					addActionError(prop.getProperty(Integer.toString(39)));
					Applicantlist = new ArrayList<Applicant> ();
				}
			} else {
				addActionError(prop.getProperty(Integer.toString(19)));
			}
        } else if (getBtn().equals("キャンセル")) {
			this.clearForm();
		} else if (getBtn().equals("はい")) {
		    searchApplicantList = (List<Applicant>) mapSession.get("SearchList");
			if (checkedExamID == null) {
				AttendedApplicantService.checkListNullUpdate(searchApplicantList);
				this.clearForm();
				Applicantlist = null;
			} else {
				List<String> selectedList = new ArrayList<String>();
				for (String strCut : checkedExamID.split(",")) {
					selectedList.add(strCut);
				}
				AttendedApplicantService.checkListNullUpdate(searchApplicantList);
				AttendedApplicantService.checkListUpdate(selectedList);
				this.clearForm();
				Applicantlist = null;
			}
			addActionMessage(prop.getProperty(Integer.toString(7)));
			mapSession.put("SearchList", null);
		}
		jfYear = comService.selectJFYear();
		lstExamPlace = CommonUtility.getExamPlace();
		return "SUCCESS";
	}

	/**
	 * 
	 * 出席申請者を検索する
	 * @return SUCCESS
	 * @throws SQLException
	 */
	/*public String getApplicantCount() throws SQLException {
		mapSession = ActionContext.getContext().getSession();
		boolean s_exam_year = false;     //開始JobFair年
		boolean s_exam_place = false;    //開始受験場所
		boolean e_exam_year = false;     //終了JobFair年
		boolean e_exam_place = false;    //終了受験場所
		if (txtStartExamID != null && !txtStartExamID.trim().isEmpty()) {
			int s_exam_yr = Integer.parseInt(txtStartExamID.substring(2,6));
			if(s_exam_yr == ddlJFYear){
				s_exam_year = true;
			}
			s_exam_place = txtStartExamID.contains(ddlExamPlace.substring(0,1));
		}
		if (txtEndExamID != null && !txtEndExamID.trim().isEmpty()) {
			int e_exam_yr = Integer.parseInt(txtEndExamID.substring(2,6));
			if(e_exam_yr == ddlJFYear){
				e_exam_year = true;
			}
			e_exam_place = txtEndExamID.contains(ddlExamPlace.substring(0,1));
		}
		if ((txtStartExamID != null && !txtStartExamID.trim().isEmpty())
	            && (txtEndExamID != null && !txtEndExamID.trim().isEmpty())) {
	            if (s_exam_year == true && e_exam_year == true) {
					if (ddlExamPlace.trim().toUpperCase().equals("Yangon".toUpperCase())
							&& s_exam_place == true 
		                    && (ddlExamPlace.trim().toUpperCase().equals("Yangon".toUpperCase()) 
		                            && e_exam_place == true)){
						Applicantlist = AttendedApplicantService.getApplicantList(getDdlJFYear(), getDdlExamPlace(), getDtpStartDate(),
								getDtpEndDate(), getTxtStartExamID(), getTxtEndExamID());
		        	} else if (ddlExamPlace.trim().toUpperCase().equals("Mandalay".toUpperCase()) 
		        			&& s_exam_place == true 
		                    && (ddlExamPlace.trim().toUpperCase().equals("Mandalay".toUpperCase()) 
		                            && e_exam_place == true)){
		        		Applicantlist = AttendedApplicantService.getApplicantList(getDdlJFYear(), getDdlExamPlace(), getDtpStartDate(),
								getDtpEndDate(), getTxtStartExamID(), getTxtEndExamID());
		        	} else{
		        		Applicantlist = new ArrayList<Applicant>();
		        	}
				} else {
					Applicantlist = new ArrayList<Applicant>();
				}
			} else if (txtStartExamID != null && !txtStartExamID.trim().isEmpty()) {
				if (s_exam_year == true) {
					Applicantlist = AttendedApplicantService.getApplicantList(getDdlJFYear(), getDdlExamPlace(), getDtpStartDate(),
							getDtpEndDate(), getTxtStartExamID(), getTxtEndExamID());
					} else {
						Applicantlist = new ArrayList<Applicant>();
				}
			} else if (txtEndExamID != null && !txtEndExamID.trim().isEmpty()) {
				if (e_exam_year == true) {
					Applicantlist = AttendedApplicantService.getApplicantList(getDdlJFYear(), getDdlExamPlace(), getDtpStartDate(),
							getDtpEndDate(), getTxtStartExamID(), getTxtEndExamID());
					} else {
						Applicantlist = new ArrayList<Applicant>();
				}
			} else {
				Applicantlist = AttendedApplicantService.getApplicantList(getDdlJFYear(), getDdlExamPlace(), getDtpStartDate(),
						getDtpEndDate(), getTxtStartExamID(), getTxtEndExamID());
				}

		mapSession.put("SearchList", Applicantlist);
		
		return "SUCCESS";
	}*/

	/**
	 * 入力データをクリアする
	 * 
	 */
	public void clearForm() {
		setTxtStartExamID("");
		setTxtEndExamID("");
		setDtpStartDate(null);
		setDtpEndDate(null);
		setDdlExamPlace(null);
		setDdlJFYear(0);
	}

	/**
	 * 選択した申請者リストを表示する
	 * 
	 * 
	 * @return SUCCESS
	 * @throws SQLException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String CheckSelectSave() throws SQLException, IOException {
		jfYear = comService.selectJFYear();
		lstExamPlace = CommonUtility.getExamPlace();
		prop = CommonUtility.getValue("Message.properties");
		Msg=prop.getProperty(Integer.toString(4));
		Applicantlist = (List<Applicant>) mapSession.get("SearchList");
		if(Applicantlist!=null && Applicantlist.size()!=0)
		{
	     isModelDisplay = true;
		count=Applicantlist.size();
		ResetAllAppCheckToNo(Applicantlist);
		checkedExamID = chkSelect;
		for (String strCut : chkSelect.split(",")) {
			for (int i = 0; i < Applicantlist.size(); i++) {
				if (Applicantlist.get(i).getExam_ID().equals(strCut)) {
					checkedApplicantList.add(Applicantlist.get(i));
					Applicantlist.get(i).setApp_Check("Y");
					break;
				}
			}
		}
		selectSize=prop.getProperty(Integer.toString(30));
		warnMsg=prop.getProperty(Integer.toString(44));
		}
		else
		{
			isModelDisplay = false;
			addActionError(prop.getProperty(Integer.toString(19)));
		}
		return "SUCCESS";
	}

	/**
	 * すべてのApp_Checkを「N」にリセットする
	 * 
	 * 
	 * @param list
	 * @return list
	 */
	public List<Applicant> ResetAllAppCheckToNo(List<Applicant> list) {
		for (Applicant app : list) {
			app.setApp_Check("N");
		}
		return list;
	}

	//受験所
	public String getDdlExamPlace() {
		return ddlExamPlace;
	}

	public void setDdlExamPlace(String ddlExamPlace) {
		this.ddlExamPlace = ddlExamPlace;
	}

	//JobFair年
	public int getDdlJFYear() {
		return ddlJFYear;
	}

	public void setDdlJFYear(int ddlJFYear) {
		this.ddlJFYear = ddlJFYear;
	}

	//開始受験ID 
	public String getTxtStartExamID() {
		return txtStartExamID;
	}

	public void setTxtStartExamID(String txtStartExamID) {
		this.txtStartExamID = txtStartExamID;
	}

	//終了受験ID
	public String getTxtEndExamID() {
		return txtEndExamID;
	}

	public void setTxtEndExamID(String txtEndExamID) {
		this.txtEndExamID = txtEndExamID;
	}

	// ポップアップフォーム表示
	public boolean getIsModelDisplay() {
		return isModelDisplay;
	}

	public void setIsModelDisplay(boolean isModelDisplay) {
		this.isModelDisplay = isModelDisplay;
	}

	// チェックボックス選択
	public String getChkSelect() {
		return chkSelect;
	}

	public void setChkSelect(String chkSelect) {
		this.chkSelect = chkSelect;
	}

	// チェックリスト
	public List<Applicant> getCheckedApplicantList() {
		return checkedApplicantList;
	}

	public void setCheckedApplicantList(List<Applicant> checkedApplicantList) {
		this.checkedApplicantList = checkedApplicantList;
	}

	// 試験IDチェック
	public String getCheckedExamID() {
		return checkedExamID;
	}

	public void setCheckedExamID(String checkedExamIDList) {
		this.checkedExamID = checkedExamIDList;
	}

	// 申請者出席リストのカウント
	public List<Applicant> getAttendedApplicantlisttocount() {
		return attendedApplicantlisttocount;
	}

	public void setAttendedApplicantlisttocount(List<Applicant> attendedApplicantlisttocount) {
		this.attendedApplicantlisttocount = attendedApplicantlisttocount;
	}

	// 申請者リスト
	public List<Applicant> getApplicantlist() {
		return Applicantlist;
	}

	public void setApplicantlist(List<Applicant> applicantlist) {
		Applicantlist = applicantlist;
	}

	// 申請者カウント
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	// 申請者物体
	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	//申請者物体
	public List<Integer> getJfYear() {
		return jfYear;
	}

	public void setJfYear(List<Integer> jfYear) {
		this.jfYear = jfYear;
	}

	// ボタン名受ける
	public String getBtn() {
		return btn;
	}

	public void setBtn(String btn) {
		this.btn = btn;
	}

	//受験所 リスト
	public List<ExamPlace> getLstExamPlace() {
		return lstExamPlace;
	}

	public void setLstExamPlace(List<ExamPlace> lstExamPlace) {
		this.lstExamPlace = lstExamPlace;
	}

	//保存のメッセージ
	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

    //チェックボックスサイズ0場合のメッセージ
	public String getSelectSize() {
		return selectSize;
	}

	public void setSelectSize(String selectSize) {
		this.selectSize = selectSize;
	}

	//一人管理者使うメッセージ
	public String getWarnMsg() {
			return warnMsg;
	}

	public void setWarnMsg(String warmMsg) {
			this.warnMsg = warmMsg;
	}
}