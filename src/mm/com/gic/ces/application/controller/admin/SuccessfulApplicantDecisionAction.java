/**
 * CV_A_041_会社別合格者決定画面
 * 作成履歴：19/04/2019 Khin Myo Wai
 * 作成概要：成功面接官を決定する処理
 * 
 * 更新履歴：10/05/2019 Khin Myo Wai
 * 更新概要：lowestIQMarkとhighestIQMarkをStringに変化する
 * 保存のメッセージとチェックボックスサイズ0場合のメッセージ追加	
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
import mm.com.gic.ces.application.model.Company;
import mm.com.gic.ces.application.model.Examinee;
import mm.com.gic.ces.application.property.ExamPlace;
import mm.com.gic.ces.base.common.CommonService;
import mm.com.gic.ces.base.service.admin.SuccessfulApplicantService;

@SuppressWarnings("serial")
public class SuccessfulApplicantDecisionAction extends ActionSupport {

	public static Map<String, Object> mapSession = ActionContext.getContext()                //mapセッション
			.getSession();     
	public SuccessfulApplicantService ExamineeService = new SuccessfulApplicantService();    //成功申請者サービスオブジェクト
	public CommonService comService = new CommonService();                                   //アクセスサービス
	private List<Examinee> Examineelist;                                                     //成功申請者リスト
	private List<Examinee> SearchExamineelist;                                               //検索できた成功申請者リスト
	private List<Company> lstCompany;                                                        //会社リスト
	private int count;                                                                       //成功申請者カウント
	private String firstCompany;                                                             //第一希望会社
	private String secondCompany;                                                            //第二希望会社
	private String btn;                                                                      //ボタン名受ける
	private Date startDate;                                                                  //開始日
	private Date endDate;                                                                    //終了日
	private String txtStartExamID;                                                           //開始受験ID
	private String txtEndExamID;                                                             //終了受験ID
	private String ddlExamPlace;                                                             //受験所                                                
	private int ddlJobFairYear;                                                              //JobFair年
	private String lowestIQMark;                                                             //最低IQマーク
	private String highestIQMark;                                                            //最高IQマーク
	private List<String> CheckedList;                                                        //チェックボックスからデータをチェック
	private List<Integer> jfYear;                                                            //JobFair年リスト
	private List<Examinee> examineelisttocount;                                              //成功申請者カウントリスト
	private List<ExamPlace> lstExamPlace = new ArrayList<ExamPlace>();                       //受験所 リスト  
	private boolean isModelDisplay;                                                          //ポップアップフォーム表示
	private String chkSelect;                                                                //チェックボックス選択
	private List<Examinee> checkedExamineeList = new ArrayList<Examinee>();                  //チェックリスト  
	private String checkedExamID;                                                            //試験IDチェック
	private Properties prop=new Properties();                                                //プロパティ宣言
	private String Msg;                                                                      //保存のメッセージ
	private String selectSize;                                                               //チェックボックスサイズ0場合のメッセージ
	private String warmMsg;                                                                  //一人管理者使うメッセージ

	/**
	 * メニューからフォームを読み込む
	 * @throws IOException
	 *
	 */
	public String ExamineeList() throws IOException {
		isModelDisplay = false;
		jfYear = comService.selectJFYear();
		lstCompany = new ArrayList<Company>();
		lstExamPlace=CommonUtility.getExamPlace();
		return "Interviewerlist";
	}

	/**
	 *成功申請者を検索して決定する
	 * 
	 * @return SUCCESS
	 * @throws SQLException
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public String SuccessfulApplicantListAction() throws SQLException, IOException {
		mapSession = ActionContext.getContext().getSession();
		isModelDisplay = false;
		jfYear = comService.selectJFYear();
		lstCompany = comService.getCompany(ddlJobFairYear);
		lstExamPlace=CommonUtility.getExamPlace();
		prop = CommonUtility.getValue("Message.properties");

		if (getBtn() == null) {
			if (ddlJobFairYear!= 0) {
				lstCompany = comService.getCompany(ddlJobFairYear);
			} else {
				lstCompany = new ArrayList<Company>();
			}
		 }
		else if(getBtn().equals("検索")) {
			int low = 0;
			int high = 0;
			int examineeCount = 0;
			if(getLowestIQMark().equals("") && getHighestIQMark().equals("")) {
				 low = 0;
				 high = 0;
			} else if(getLowestIQMark().equals("")) {
				low = 0;
				high = Integer.parseInt(getHighestIQMark().trim());
			} else if(getHighestIQMark().equals("")) {
				high = 0;
				low = Integer.parseInt(getLowestIQMark().trim());
			} else {
				low = Integer.parseInt(getLowestIQMark().trim());
				high = Integer.parseInt(getHighestIQMark().trim());
			}
			examineeCount = this.getExamineeCount();
			if(examineeCount != 0) {
				if(examineeCount <= 30) {
					Examineelist = ExamineeService.SearchSuccessfulApplicant(
							getDdlJobFairYear(), getDdlExamPlace(), getStartDate(),
							getEndDate(), getTxtStartExamID(), getTxtEndExamID(),
							getFirstCompany(), getSecondCompany(),low ,high);
					count=examineeCount;
					mapSession.put("SearchListExaminee", Examineelist);
				} else {
					addActionError(prop.getProperty(Integer.toString(39)));
					Examineelist = new ArrayList<Examinee>();
				}
			} else {
				addActionError(prop.getProperty(Integer.toString(19)));
			}
	  } else if (getBtn().equals("キャンセル")) {
			this.clearForm();
			
	  } else if (getBtn().equals("はい")) {
			SearchExamineelist = (List<Examinee>) mapSession.get("SearchListExaminee");

			if (checkedExamID == null) {
				ExamineeService.checkListNullUpdate(SearchExamineelist);
				this.clearForm();
				Examineelist = null;

			} else {
				List<String> selectedList = new ArrayList<String>();
				for (String strCut : checkedExamID.split(",")) {
					selectedList.add(strCut);
				}
				ExamineeService.checkListNullUpdate(SearchExamineelist);
				ExamineeService.checkListUpdate(selectedList);
				this.clearForm();
				Examineelist = null;
			}
			addActionMessage(prop.getProperty(Integer.toString(7)));
			mapSession.put("SearchListExaminee", null);
		}
		return "SUCCESS";
	}

	/**
	 *  成功申請者を検索する
	 * @return SUCCESS
	 * @throws SQLException
	 */
	public int getExamineeCount() throws SQLException {
		mapSession = ActionContext.getContext().getSession();
		int low = 0;
		int high = 0;
		int examineeCount = 0;
		if(getLowestIQMark().equals("") && getHighestIQMark().equals("")) {
			 low = 0;
			 high = 0;
		} else if(getLowestIQMark().equals("")) {
			low = 0;
			high = Integer.parseInt(getHighestIQMark().trim());
		} else if(getHighestIQMark().equals("")) {
			high = 0;
			low = Integer.parseInt(getLowestIQMark().trim());
		} else {
			low = Integer.parseInt(getLowestIQMark().trim());
			high = Integer.parseInt(getHighestIQMark().trim());
		}
		boolean s_exam_year = false;     //開始JobFair年
		boolean s_exam_place = false;    //開始受験場所
		boolean e_exam_year = false;     //終了JobFair年
		boolean e_exam_place = false;    //終了受験場所
		if (txtStartExamID != null && !txtStartExamID.trim().isEmpty()) {
			int s_exam_yr = Integer.parseInt(txtStartExamID.substring(2,6));
			if(s_exam_yr == ddlJobFairYear) {
				s_exam_year = true;
			}
			s_exam_place = txtStartExamID.contains(ddlExamPlace.substring(0,1));
		}
		if (txtEndExamID != null && !txtEndExamID.trim().isEmpty()) {
			int e_exam_yr = Integer.parseInt(txtEndExamID.substring(2,6));
			if(e_exam_yr == ddlJobFairYear){
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
		                            && e_exam_place == true)) {
						examineeCount = ExamineeService.getExamineeCount(getDdlJobFairYear(), getDdlExamPlace(), 
								getStartDate(), getEndDate(), getTxtStartExamID(), getTxtEndExamID(), 
								getFirstCompany(), getSecondCompany(), low, high);
		        	} else if (ddlExamPlace.trim().toUpperCase().equals("Mandalay".toUpperCase()) 
		        			&& s_exam_place == true 
		                    && (ddlExamPlace.trim().toUpperCase().equals("Mandalay".toUpperCase()) 
		                            && e_exam_place == true)){
		        		examineeCount = ExamineeService.getExamineeCount(getDdlJobFairYear(), getDdlExamPlace(), 
								getStartDate(), getEndDate(), getTxtStartExamID(), getTxtEndExamID(), 
								getFirstCompany(), getSecondCompany(), low, high);
		        	} else {
		        		examineeCount = 0;
		        	}
				} else {
					examineeCount = 0;
				}
			} else if (txtStartExamID != null && !txtStartExamID.trim().isEmpty()) {
				if (s_exam_year == true) {
					if (getDdlExamPlace().trim().toUpperCase().equals("Yangon".toUpperCase()) && s_exam_place == true) {
						examineeCount = ExamineeService.getExamineeCount(getDdlJobFairYear(), getDdlExamPlace(), 
								getStartDate(), getEndDate(), getTxtStartExamID(), getTxtEndExamID(), 
								getFirstCompany(), getSecondCompany(), low, high);
				    } else if (getDdlExamPlace().trim().toUpperCase().equals("Mandalay".toUpperCase()) 
				            && s_exam_place == true) {
				    	examineeCount = ExamineeService.getExamineeCount(getDdlJobFairYear(), getDdlExamPlace(), 
								getStartDate(), getEndDate(), getTxtStartExamID(), getTxtEndExamID(), 
								getFirstCompany(), getSecondCompany(), low, high);
			        } else {
			        	examineeCount = 0;
				    }
					} else {
						examineeCount = 0;
				}
			} else if (txtEndExamID != null && !txtEndExamID.trim().isEmpty()) {
				if (e_exam_year == true) {
					if (getDdlExamPlace().trim().toUpperCase().equals("Yangon".toUpperCase()) && e_exam_place == true) {
						examineeCount = ExamineeService.getExamineeCount(getDdlJobFairYear(), getDdlExamPlace(), 
								getStartDate(), getEndDate(), getTxtStartExamID(), getTxtEndExamID(), 
								getFirstCompany(), getSecondCompany(), low, high);
				    } else if (getDdlExamPlace().trim().toUpperCase().equals("Mandalay".toUpperCase()) 
				            && e_exam_place == true) {
				    	examineeCount = ExamineeService.getExamineeCount(getDdlJobFairYear(), getDdlExamPlace(), 
								getStartDate(), getEndDate(), getTxtStartExamID(), getTxtEndExamID(), 
								getFirstCompany(), getSecondCompany(), low, high);
			        } else {
			        	examineeCount = 0;
				    }
					} else {
						examineeCount = 0;
				}
			} else {
				examineeCount = ExamineeService.getExamineeCount(getDdlJobFairYear(), getDdlExamPlace(), 
						getStartDate(), getEndDate(), getTxtStartExamID(), getTxtEndExamID(), 
						getFirstCompany(), getSecondCompany(), low, high);
				}
		return examineeCount;
	}

	/**
	 * 選択した 成功申請者リストを表示する
	 * 
	 * 
	 * @return SUCCESS
	 * @throws SQLException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String CheckSelectSave() throws SQLException, IOException {
		jfYear = comService.selectJFYear();
		lstCompany = comService.getCompany(ddlJobFairYear);
		lstExamPlace=CommonUtility.getExamPlace();
		prop = CommonUtility.getValue("Message.properties");
		Msg=prop.getProperty(Integer.toString(4));
		Examineelist = (List<Examinee>) mapSession
				.get("SearchListExaminee");
		if(Examineelist!=null && Examineelist.size()!=0)
		{
	     isModelDisplay = true;
		count=Examineelist.size();
		ResetAllCheckToNo(Examineelist);
		checkedExamID = chkSelect;
		for (String strCut : chkSelect.split(",")) {
			for (int i = 0; i<Examineelist.size(); i++) {
				if (Examineelist.get(i).getApplicant().getExam_ID().equals(strCut)){
					checkedExamineeList.add(Examineelist.get(i));
					Examineelist.get(i).setExaminee_Pass("Y");
					break;
				}
			}
		}
		selectSize=prop.getProperty(Integer.toString(30));
		warmMsg=prop.getProperty(Integer.toString(44));
		}
	
	else
	{
		isModelDisplay = false;
		addActionError(prop.getProperty(Integer.toString(19)));
	}
	return "SUCCESS";
}

	/**
	 * すべてのExaminee_Passを「N」にリセットする
	 * 
	 * @param list
	 * @return list
	 */
	public List<Examinee> ResetAllCheckToNo(List<Examinee> list) {
		for (Examinee ex : list) {
			ex.setExaminee_Pass("N");
		}
		return list;
	}

	/**
	 * 入力データをクリアする
	 * 
	 */
	public void clearForm() {
		setDdlExamPlace(null);
		setDdlJobFairYear(0);
		setStartDate(null);
		setEndDate(null);
		setTxtStartExamID(null);
		setTxtEndExamID(null);
		setFirstCompany(null);
		setSecondCompany(null);
		setLowestIQMark(null);
	    setHighestIQMark(null);
	}

	   //JobFair年リスト
		public List<Integer> getJfYear() {
			return jfYear;
		}

		public void setJfYear(List<Integer> jfYear) {
			this.jfYear = jfYear;
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

		//受験所
		public String getDdlExamPlace() {
			return ddlExamPlace;
		}

		public void setDdlExamPlace(String ddlExamPlace) {
			this.ddlExamPlace = ddlExamPlace;
		}

		//JobFair年
		public int getDdlJobFairYear() {
			return ddlJobFairYear;
		}

		public void setDdlJobFairYear(int ddlJobFairYear) {
			this.ddlJobFairYear = ddlJobFairYear;
		}

		//受験所 リスト  
		public List<ExamPlace> getLstExamPlace() {
			return lstExamPlace;
		}

		public void setLstExamPlace(List<ExamPlace> lstExamPlace) {
			this.lstExamPlace = lstExamPlace;
		}

		//チェックボックスからデータをチェック
		public List<String> getCheckedList() {
			return CheckedList;
		}

		public void setCheckedList(List<String> checkedList) {
			CheckedList = checkedList;
		}

		//成功申請者リスト
		public List<Examinee> getExamineelist() {
			return Examineelist;
		}

		public void setExamineelist(List<Examinee> examineelist) {
			Examineelist = examineelist;
		}

		//検索できた成功申請者リスト
		public List<Examinee> getSearchExamineelist() {
			return SearchExamineelist;
		}

		public void setSearchExamineelist(List<Examinee> searchExamineelist) {
			SearchExamineelist = searchExamineelist;
		}

		//成功申請者カウントリスト
		public List<Examinee> getExamineelisttocount() {
			return examineelisttocount;
		}

		public void setExamineelisttocount(List<Examinee> examineelisttocount) {
			this.examineelisttocount = examineelisttocount;
		}

		//成功申請者カウント
		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
		

		//ボタン名受ける
		public String getBtn() {
			return btn;
		}

		public void setBtn(String btn) {
			this.btn = btn;
		}

		//第一希望会社
		public String getFirstCompany() {
			return firstCompany;
		}

		public void setFirstCompany(String firstCompany) {
			this.firstCompany = firstCompany;
		}

		//第二希望会社
		public String getSecondCompany() {
			return secondCompany;
		}

		public void setSecondCompany(String secondCompany) {
			this.secondCompany = secondCompany;
		}

		//会社リスト
		public List<Company> getLstCompany() {
			return lstCompany;
		}

		public void setLstCompany(List<Company> lstCompany) {
			this.lstCompany = lstCompany;
		}

		//最低IQマーク
		public String getLowestIQMark() {
			return lowestIQMark;
		}

		public void setLowestIQMark(String lowestIQMark) {
			this.lowestIQMark = lowestIQMark;
		}

		//最高IQマーク
		public String getHighestIQMark() {
			return highestIQMark;
		}

		public void setHighestIQMark(String hightestIQMark) {
			this.highestIQMark = hightestIQMark;
		}

		//開始日
		public Date getStartDate() {
			return startDate;
		}

		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

		//終了日
		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}

		//試験IDチェック
		public String getCheckedExamID() {
			return checkedExamID;
		}

		public void setCheckedExamID(String checkedExamID) {
			this.checkedExamID = checkedExamID;
		}

		//ポップアップフォーム表示
		public boolean getIsModelDisplay() {
			return isModelDisplay;
		}

		public void setIsModelDisplay(boolean isModelDisplay) {
			this.isModelDisplay = isModelDisplay;
		}

		//チェックボックス選択
		public String getChkSelect() {
			return chkSelect;
		}

		public void setChkSelect(String chkSelect) {
			this.chkSelect = chkSelect;
		}

        //チェックリスト
		public List<Examinee> getCheckedExamineeList() {
			return checkedExamineeList;
		}

		public void setCheckedExamineeList(List<Examinee> checkedExamineeList) {
			this.checkedExamineeList = checkedExamineeList;
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
		public String getWarmMsg() {
				return warmMsg;
		}

		public void setWarmMsg(String warmMsg) {
				this.warmMsg = warmMsg;
		}
}