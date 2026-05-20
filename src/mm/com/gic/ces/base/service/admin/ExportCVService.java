/**
 * CV_A_012_履歴書出力画面
 * 作成履歴：01/04/2019 Thet Ngon Tun
 * 作成概要：フォーム初期化処理 ,JobFair年ドロップダウン処理,キャンセルボタン処理,検索ボタン処理,履歴書出力ボタン処理
 * 
 * 更新履歴：16/05/2019 Thet Ngon Tun
 * 更新概要：履歴書出力ボタン処理	
 */

package mm.com.gic.ces.base.service.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import mm.com.gic.ces.application.model.Applicant;
import mm.com.gic.ces.application.model.ApplicantInfo;
import mm.com.gic.ces.application.model.Examinee;
import mm.com.gic.ces.base.common.HibernateUtil;

/**
 * 申請者の履歴書を出力する
 */
public class ExportCVService extends HibernateUtil {
	List<ApplicantInfo> applicantList;

	/**
	 * 申請者のデータをデータをベースから取得する
	 * 
	 * @param IDList
	 * @return applicantList
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<ApplicantInfo> applicantList(List<Integer> IDList)
			throws SQLException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			applicantList = new ArrayList<ApplicantInfo>();
			if (IDList != null) {
				String query = "FROM Applicant WHERE app_ID IN (:IDList)";
				Query queryResult = session.createQuery(query);
				queryResult.setParameterList("IDList", IDList);
				List<Applicant> appList = queryResult.list();
				for (int i = 0; i < appList.size(); i++) {
					Criteria cr = session.createCriteria(Examinee.class).add(
							Restrictions.eq("applicant.app_ID", appList.get(i)
									.getApp_ID()));
					List<Examinee> examinees = cr.list();
					ApplicantInfo appInfo = new ApplicantInfo();
					appInfo.setApplicant(appList.get(i));
					if (!examinees.isEmpty()) {
						appInfo.setExaminee(examinees.get(0));
					} else {
						appInfo.setExaminee(null);
					}
					applicantList.add(appInfo);
				}
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

		return applicantList;
	}

	/**
	 * 入力した検索条件によって申請者のデータをデータベースから取得する
	 * 
	 * @param startDate
	 * @param endDate
	 * @param jfYear
	 * @param firstCompany
	 * @param secondCompany
	 * @param examPlace
	 * @param sExamID
	 * @param eExamID
	 * @param isExaminee
	 * @param isInterview
	 * @param isEmployee
	 * @return applicantList
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<ApplicantInfo> searchList(Date startDate, Date endDate,
			int jfYear, int firstCompany, int secondCompany, String examPlace,
			String sExamID, String eExamID, boolean isExaminee,
			boolean isInterview, boolean isEmployee) throws SQLException {

		applicantList = new ArrayList<ApplicantInfo>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			boolean search = true;

			// 受験番号を入力する場合、JobFair年をチェクするためSQLを作成する
			if (!sExamID.equals("") && !sExamID.trim().isEmpty()
					&& !eExamID.equals("") && !eExamID.trim().isEmpty()) {
				int sJFYear = Integer.parseInt(sExamID.substring(2, 6));
				int eJFYear = Integer.parseInt(eExamID.substring(2, 6));
				String sExamPlace = sExamID.substring(6, 7).toUpperCase();
				String eExamPlace = eExamID.substring(6, 7).toUpperCase();

				if (sJFYear != jfYear || eJFYear != jfYear
						|| !sExamPlace.equals(examPlace.substring(0, 1))
						|| !eExamPlace.equals(examPlace.substring(0, 1))) {
					search = false;
				}
			} else {
				if (!sExamID.equals("") && !sExamID.trim().isEmpty()) {
					int sJFYear = Integer.parseInt(sExamID.substring(2, 6));
					String sExamPlace = sExamID.substring(6, 7).toUpperCase();
					if (sJFYear != jfYear
							|| !sExamPlace.equals(examPlace.substring(0, 1))) {
						search = false;
					}
				}
				if (!eExamID.equals("") && !eExamID.trim().isEmpty()) {
					int eJFYear = Integer.parseInt(eExamID.substring(2, 6));
					String eExamPlace = eExamID.substring(6, 7).toUpperCase();
					if (eJFYear != jfYear
							|| !eExamPlace.equals(examPlace.substring(0, 1))) {
						search = false;
					}
				}
			}
			List<ApplicantInfo> appInfoList=new ArrayList<ApplicantInfo>();
			if (search == true) {
				StringBuilder sb = new StringBuilder(
						"SELECT app_ID FROM Applicant WHERE del_flag=0 AND app_ExamPlace=:examPlace AND app_JFYear=:jfYear");
				// 申請日を入力する場合、入力した申請日によって取得するためSQLを作成する
				if (startDate != null && endDate != null) {
					sb.append(" AND app_RegDate>=:startDate AND app_RegDate<=:endDate");
				} else {
					if (startDate != null) {
						sb.append(" AND app_RegDate>=:startDate");
					}
					if (endDate != null) {
						sb.append(" AND app_RegDate<=:endDate");
					}
				}

				// 受験番号を入力する場合、入力した受験番号によって取得するためSQLを作成する
				if (!sExamID.equals("") && !sExamID.trim().isEmpty()
						&& !eExamID.equals("") && !eExamID.trim().isEmpty()) {
					sb.append(" AND exam_ID>=:sExamID AND exam_ID<=:eExamID");
				} else {
					if (!sExamID.equals("") && !sExamID.trim().isEmpty()) {
						sb.append(" AND exam_ID>=:sExamID ");
					}
					if (!eExamID.equals("") && !eExamID.trim().isEmpty()) {
						sb.append(" AND exam_ID<=:eExamID");
					}
				}

				// 受験者を検索する場合、受験者を取得するためSQLを作成する
				if ((firstCompany != 0 || secondCompany != 0 || isExaminee == true)) {
					sb.append(" AND app_Check=:appCheck");
				}

				Query queryResult = session.createQuery(sb.toString());
				queryResult.setParameter("jfYear", jfYear);
				queryResult.setParameter("examPlace", examPlace);

				// 申請日を入力する場合、入力した申請日をSQLに設定する
				if (startDate != null && endDate != null) {
					queryResult.setParameter("startDate", startDate);
					queryResult.setParameter("endDate", endDate);
				} else {
					if (startDate != null) {
						queryResult.setParameter("startDate", startDate);
					}
					if (endDate != null) {
						queryResult.setParameter("endDate", endDate);
					}
				}

				// 受験番号を入力する場合、入力した受験番号をSQLに設定する
				if (!sExamID.equals("") && !eExamID.equals("")) {
					queryResult.setParameter("sExamID", sExamID);
					queryResult.setParameter("eExamID", eExamID);
				} else {
					if (!sExamID.equals("")) {
						queryResult.setParameter("sExamID", sExamID);
					}
					if (!eExamID.equals("")) {
						queryResult.setParameter("eExamID", eExamID);
					}
				}

				// 会社名を選択した場合や受験者チェックボックスをチェクする場合、受験者を取得するためSQLを作成する
				if ((firstCompany != 0 || secondCompany != 0 || isExaminee == true)) {
					queryResult.setParameter("appCheck", "Y");
				}

				List<Integer> applicants;
				applicants = queryResult.list();

				// 取得された申請者がいる場合、以下のチェクをする
				for (int i = 0; i < applicants.size(); i++) {
					Applicant applicant = new Applicant();
					applicant.setApp_ID(applicants.get(i));
					
					StringBuilder examineeQuery = new StringBuilder(
							"select examinee_ID FROM Examinee WHERE applicant.app_ID=:appID");

					// 採用者チェックボックスをチェクする場合、採用者を取得するためSQLを作成する
					if (isEmployee == true) {
						examineeQuery
								.append(" AND examinee_ID=(select examinee.examinee_ID FROM Interview WHERE interview_Pass=:interviewCheck AND examinee.applicant.app_ID=:ID)");
					}
					// 合格者チェックボックスをチェクする場合、合格者を取得するためSQLを作成する
					else if (isInterview == true) {
						examineeQuery
								.append(" AND examinee_Pass=:examineeCheck");
					}

					// 第一希望会社を選択した場合、選択した会社名によって取得するためSQLを作成する
					if (firstCompany != 0) {
						examineeQuery
								.append(" AND firstCompany.com_ID=:firstCompany ");
					}

					// 第二希望会社を選択した場合、選択した会社名によって取得するためSQLを作成する
					if (secondCompany != 0) {
						examineeQuery
								.append(" AND secondCompany.com_ID=:secondCompany ");
					}

					Query examineeResult = session.createQuery(examineeQuery
							.toString());
					examineeResult.setParameter("appID", applicant.getApp_ID());

					// 採用者チェックボックスをチェクする場合、採用者チェクをYに設定する
					if (isEmployee == true) {
						examineeResult.setParameter("interviewCheck", "Y");
						examineeResult
								.setParameter("ID", applicant.getApp_ID());
					}
					// 合格者チェックボックスをチェクする場合、合格者チェクをYに設定する
					else if (isInterview == true) {
						examineeResult.setParameter("examineeCheck", "Y");
					}
					
					// 第一希望会社を選択した場合、選択した会社名をSQLを作成する
					if (firstCompany != 0) {
						examineeResult.setParameter("firstCompany",
								firstCompany);
					}
					// 第二希望会社を選択した場合、選択した会社名をSQLを作成する
					if (secondCompany != 0) {
						examineeResult.setParameter("secondCompany",
								secondCompany);
					}

					ApplicantInfo appInfo = new ApplicantInfo();
					List<Integer> examinees = examineeResult.list();

					if (examinees.isEmpty()) {
						if (firstCompany == 0 && secondCompany == 0
								&& isInterview == false && isEmployee == false) {
							appInfo.setApplicant(applicant);
							appInfo.setExaminee(null);
						} else
							appInfo = null;
					} else {
						appInfo.setApplicant(applicant);
						Examinee examinee=new Examinee();
						examinee.setExaminee_ID(examinees.get(0));
						appInfo.setExaminee(examinee);
					}
					if (appInfo != null) {
						appInfoList.add(appInfo);
					}
				}
			}
			
			if(appInfoList.size()<=50){
				for(int i=0;i<appInfoList.size();i++){		
					ApplicantInfo appInfo=new ApplicantInfo();					
					Criteria cr = session.createCriteria(Applicant.class)
						    .setProjection(Projections.projectionList()
						      .add(Projections.property("app_ID"), "app_ID")
						      .add(Projections.property("exam_ID"), "exam_ID")
						      .add(Projections.property("app_Name"), "app_Name")
						      .add(Projections.property("app_Gender"), "app_Gender")
						      .add(Projections.property("app_Email"), "app_Email")
						      .add(Projections.property("app_DOB"), "app_DOB")
						      .add(Projections.property("app_Nrc"), "app_Nrc")
						      .add(Projections.property("app_Degree"), "app_Degree")
						      .add(Projections.property("app_ACYear"), "app_ACYear")
						      .add(Projections.property("app_Education"), "app_Education")
						      .add(Projections.property("app_University"), "app_University")
						      .add(Projections.property("app_ExamPlace"), "app_ExamPlace"))
						      .setResultTransformer(Transformers.aliasToBean(Applicant.class));
					
			        cr.add(Restrictions.eq("app_ID", appInfoList.get(i).getApplicant().getApp_ID()));
			        									
					Applicant applicant=(Applicant)cr.uniqueResult();
					appInfo.setApplicant(applicant);
					
					if(appInfoList.get(i).getExaminee()!=null){
						StringBuilder sbExaminee=new StringBuilder("FROM Examinee WHERE examinee_ID=:examineeID");
						Query examineeQuery = session.createQuery(sbExaminee.toString());
						examineeQuery.setParameter("examineeID", appInfoList.get(i).getExaminee().getExaminee_ID());
						Examinee examinee=(Examinee)examineeQuery.uniqueResult();
						appInfo.setExaminee(examinee);
					}
					
					applicantList.add(appInfo);
				}
			}else{
				applicantList=appInfoList;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return applicantList;
	}

	/**
	 * 特定申請者IDによって申請者のデータをデータベースから取得する
	 * 
	 * @param id
	 * @return　申請者
	 */
	public Applicant getApplicantData(int id) {
		Applicant applicant = new Applicant();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{		
		session.beginTransaction();
		Query query=session.createQuery("FROM Applicant WHERE app_ID=:id");
		query.setParameter("id", id);
		applicant = (Applicant) query.uniqueResult();
		session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			session.close();
		}	
		return applicant;
	}

}
