/**
 * CV_A_021_メール送信画面
 * 作成履歴：2019/02/05 Aye Chan Moe
 * 作成概要：新規作成　メール送信処理,登録処理
 * 
 * 更新履歴：2019/05/09 Thet Ngon Tun 
 * 更新概要：新規作成,　メール送信処理, 登録処理
 */

package mm.com.gic.ces.application.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import mm.com.gic.ces.application.common.CommonUtility;
import mm.com.gic.ces.application.model.Applicant;
import mm.com.gic.ces.application.model.Interview;
import mm.com.gic.ces.application.property.ExamPlace;
import mm.com.gic.ces.base.common.CommonService;
import mm.com.gic.ces.base.service.admin.MailSendService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 申請者と面接者に試験情報と面接情報をメールで送る
 */
@SuppressWarnings("serial")
public class MailSendAction extends ActionSupport {

	private List<Applicant> applicantList;   // 申請者一覧
	private List<Integer> IDList;            // 申請者番号一覧
	private List<Integer> lstJfYear;         // JobFair年一覧
	private int jfYear;                      // JobFair年
	private String examPlace;                // 受検場所
	private List<ExamPlace> lstExamPlace;    // 受験場所一覧
	private String startExamID;              // 開始受験ID
	private String endExamID;                // 終了受験ID
	private int sendID;                      // 送れるID
	private String selectedMailType;         //選択したメールタイプ
	private String selectedExamPlace;		 //選択した受験場所
	private String rbt;                      // ラジオボタン
	private String btn;                      // ボタンのテキスト
	static Map<String, Object> mapSession;   // サーブレットリクエスト
	MailSendService mailService = new MailSendService();
	CommonUtility comUtility = new CommonUtility();
	CommonService comService = new CommonService();

	/**
	 * 画面を フォーム初期化に設定する
	 * 
	 * @return
	 * @throws IOException
	 */
	public String sendMail() throws IOException {
		try {
			applicantList();

		} catch (SQLException e) {
			return "error";
		}
		return "success";
	}

	/**
	 * 画面の初期化にリセットする
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	public void applicantList() throws SQLException, IOException {
		setJfYear(0);
		setExamPlace(null);
		setStartExamID(null);
		setEndExamID(null);
		applicantList = new ArrayList<Applicant>();
		CommonService commonService = new CommonService();
		lstExamPlace = CommonUtility.getExamPlace();
		lstJfYear = commonService.selectJFYear();
	}

	/**
	 * 　入力した検索条件によって申請者のデータを検索する
	 * 
	 * @throws IOException
	 */
	public void getSearchList() throws SQLException, IOException {
		CommonUtility comUtility = new CommonUtility();
		@SuppressWarnings("static-access")
		Properties prop = comUtility.getValue("Message.properties");
		List<Integer> searchList= mailService.getSearchCount(jfYear, examPlace, startExamID,
				endExamID, rbt);

		if (!searchList.isEmpty()) {
			if (searchList.size() > 30) {
				addActionError(prop.getProperty(Integer.toString(39)));
				applicantList = new ArrayList<Applicant>();
			}else{
				applicantList=mailService.getSearchList(searchList, rbt);
			}
		} else {
			addActionError(prop.getProperty(Integer.toString(19)));
		}
	}

	/**
	 * 個別のメールを送信する
	 * 
	 * @throws IOException
	 * 
	 */
	@SuppressWarnings("static-access")
	public void sendIndividualMail() throws IOException {
		try {
			Properties prop = comUtility.getValue("Message.properties");
			// 申請者にメールを送信する
			if (selectedMailType.equals("申請者")) {
				String jobFairPlace=comService.getJobFairPlace(selectedExamPlace);
				String jobFairDate = comService.getJobFairDate(selectedExamPlace);
				if (jobFairPlace.equals("")) {
					addActionError("JobFair場所"
							+ prop.getProperty(Integer.toString(31)));
				} else if (jobFairDate.equals("")) {
					addActionError("JobFair日"
							+ prop.getProperty(Integer.toString(31)));
				} else {
					Applicant applicant = mailService.getApplicantData(sendID);
					if (applicant != null) {
						String status = mailService.sentExamInfo(applicant,
								jobFairPlace, jobFairDate);
						if (status.equals("success")) {
							addActionMessage(applicant.getExam_ID()
									+ prop.getProperty(Integer.toString(33)));
						} else {
							addActionError(applicant.getExam_ID()
									+ prop.getProperty(Integer.toString(34)));
						}
					} else {
						addActionError(prop.getProperty(Integer.toString(38)));
					}
				}
			}
			// 面接者にメールを送信する
			else if (selectedMailType.equals("面接者")) {
				Interview interview = mailService.getInterviewData(sendID);
				if (interview != null) {
					String status = mailService.sentInterviewInfo(interview);
					if (status.equals("success")) {
						addActionMessage(interview.getExaminee().getApplicant()
								.getExam_ID()
								+ prop.getProperty(Integer.toString(33)));
					} else {
						addActionError(interview.getExaminee().getApplicant()
								.getExam_ID()
								+ prop.getProperty(Integer.toString(34)));
					}
				} else {
					addActionError(prop.getProperty(Integer.toString(38)));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(comUtility.getValue("Message.properties")
					.getProperty(Integer.toString(38)));
		}
	}

	/**
	 * 一覧に表示されている申請者と面接者にメールを送信する
	 * 
	 * @throws IOException
	 * 
	 */

	@SuppressWarnings("static-access")
	public void sendToAll() throws IOException {
		// 一覧に表示されている申請者にメールを送信する
		String SentExamID = "";
		try {
			Properties prop = comUtility.getValue("Message.properties");
			if (selectedMailType.equals("申請者")) {
				String jobFairPlace=comService.getJobFairPlace(selectedExamPlace);
				String jobFairDate = comService.getJobFairDate(selectedExamPlace);
				if (jobFairPlace.equals("")) {
					addActionError("JobFair場所"
							+ prop.getProperty(Integer.toString(31)));
				} else if (jobFairDate.equals("")) {
					addActionError("JobFair日"
							+ prop.getProperty(Integer.toString(31)));
				} else {
					for (int i = 0; i < IDList.size(); i++) {
						Applicant applicant = mailService
								.getApplicantData(IDList.get(i));
						if (applicant != null) {
							String status = mailService.sentExamInfo(applicant,
									jobFairPlace, jobFairDate);
							if (status.equals("success")) {
								SentExamID = applicant.getExam_ID();
							}
							if (status.equals("success")
									&& i == (IDList.size() - 1)) {
								addActionMessage(prop.getProperty(Integer
										.toString(35)));
							} else if (status.equals("error")) {
								addActionError(applicant.getExam_ID()
										+ prop.getProperty(Integer.toString(37)));
								i = IDList.size();
							}
						} else {
							i = IDList.size();
							if (SentExamID.equals("")) {
								addActionError(prop.getProperty(Integer
										.toString(32)));
							} else {
								addActionError(SentExamID
										+ prop.getProperty(Integer.toString(40)));
							}
						}
					}
				}
			}
			// 一覧に表示されている面接者にメールを送信する
			else if (selectedMailType.equals("面接者")) {
				for (int i = 0; i < IDList.size(); i++) {
					Interview interview = mailService.getInterviewData(IDList
							.get(i));
					if (interview != null) {
						String status = mailService
								.sentInterviewInfo(interview);
						if (status.equals("success")) {
							SentExamID = interview.getExaminee().getApplicant()
									.getExam_ID();
						}
						if (status.equals("success")
								&& i == (IDList.size() - 1)) {
							addActionMessage(prop.getProperty(Integer
									.toString(36)));
						} else if (status.equals("error")) {
							addActionError(interview.getExaminee()
									.getApplicant().getExam_ID()
									+ prop.getProperty(Integer.toString(37)));
							i = IDList.size();
						}
					} else {
						i = IDList.size();
						if (SentExamID.equals("")) {
							addActionError(prop.getProperty(Integer
									.toString(32)));
						} else {
							addActionError(SentExamID
									+ prop.getProperty(Integer.toString(40)));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(comUtility.getValue("Message.properties")
					.getProperty(Integer.toString(38)));
		}
	}

	/**
	 * ユーザーが押したボタンをチェックする
	 * 
	 * @return success
	 */
	public String mailSendForm() {
		try {
			if (btn.equals("キャンセル")) {
				applicantList();
			} else if (btn.equals("検索")) {
				getSearchList();
				setSelectedMailType(rbt);
				setSelectedExamPlace(examPlace);
			} else if (btn.equals("送信")) {
				sendIndividualMail();
				applicantList = mailService.applicantList(IDList);
			} else if (btn.equals("全員へ送信")) {
				sendToAll();
				applicantList = mailService.applicantList(IDList);
			}
			lstExamPlace = CommonUtility.getExamPlace();
			lstJfYear = comService.selectJFYear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	// 申請者一覧
	public List<Applicant> getApplicantList() {
		return applicantList;
	}

	public void setApplicantList(List<Applicant> applicantList) {
		this.applicantList = applicantList;
	}

	// 申請者番号一覧
	public List<Integer> getIDList() {
		return IDList;
	}

	public void setIDList(List<Integer> iDList) {
		IDList = iDList;
	}

	// JobFair年一覧
	public List<Integer> getLstJfYear() {
		return lstJfYear;
	}

	public void setLstJfYear(List<Integer> lstJfYear) {
		this.lstJfYear = lstJfYear;
	}

	// JobFairｆ年
	public int getJfYear() {
		return jfYear;
	}

	public void setJfYear(int jfYear) {
		this.jfYear = jfYear;
	}

	// 受検場所
	public String getExamPlace() {
		return examPlace;
	}

	public void setExamPlace(String examPlace) {
		this.examPlace = examPlace;
	}

	// 受検場所一覧
	public List<ExamPlace> getLstExamPlace() {
		return lstExamPlace;
	}

	public void setLstExamPlace(List<ExamPlace> lstExamPlace) {
		this.lstExamPlace = lstExamPlace;
	}

	// 開始受験ID
	public String getStartExamID() {
		return startExamID;
	}

	public void setStartExamID(String startExamID) {
		this.startExamID = startExamID;
	}

	// 終了受験ID
	public String getEndExamID() {
		return endExamID;
	}

	public void setEndExamID(String endExamID) {
		this.endExamID = endExamID;
	}

	// 送れるID
	public int getSendID() {
		return sendID;
	}

	public void setSendID(int sendID) {
		this.sendID = sendID;
	}

	// 選択したメールタイプ
	public String getSelectedMailType() {
		return selectedMailType;
	}

	public void setSelectedMailType(String selectedMailType) {
		this.selectedMailType = selectedMailType;
	}

	//選択した受験場所
	public String getSelectedExamPlace() {
		return selectedExamPlace;
	}

	public void setSelectedExamPlace(String selectedExamPlace) {
		this.selectedExamPlace = selectedExamPlace;
	}

	// ラジオボタン
	public String getRbt() {
		return rbt;
	}

	public void setRbt(String rbt) {
		this.rbt = rbt;
	}

	// ボタンのテキスト
	public String getBtn() {
		return btn;
	}

	public void setBtn(String btn) {
		this.btn = btn;
	}
}
