/**
 * CV_A_033_会社別受験者情報照会画面
 * 作成履歴：21/1/2019 Nwe Ni Hlaing
 * 作成概要：新規作成
 * 
 * 更新履歴：01/04/2019 Nwe Ni Hlaing
 * 更新概要：XXXXXXXX	
 */
package mm.com.gic.ces.base.service.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import mm.com.gic.ces.application.model.Applicant;
import mm.com.gic.ces.application.model.ApplicantInfo;
import mm.com.gic.ces.application.model.Company;
import mm.com.gic.ces.application.model.Examinee;
import mm.com.gic.ces.base.common.HibernateUtil;

/**
 *データベースにある申請者情報を取る
 */
public class ExamineeInfoService {
	Session session =null;					//セッション
	List<ApplicantInfo> lstApplicantInfo;	//申請者情報のリスト

	/**
	 * ...申請者IDによって申請者情報を取る ...
	 * @param appIDList 申請者IDリスト
	 * @return　lstApplicantInfo 申請者情報リスト
	 * @throws SQLException
	 */	
	@SuppressWarnings("unchecked")
	public List<ApplicantInfo> getApplicantID(List<Integer> appIDList)
			throws SQLException {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		lstApplicantInfo = new ArrayList<ApplicantInfo>();
		String query;
		StringBuilder sb=new StringBuilder("FROM Applicant WHERE app_ID IN (:IDList)");
		sb.append( " ORDER BY exam_ID");
		query=sb.toString();
		Query queryResult = session.createQuery(query);
		queryResult.setParameterList("IDList", appIDList);
		List<Applicant> appList=queryResult.list();
		for(int i=0;i<appList.size();i++){
			Criteria cr = session.createCriteria(Examinee.class).add(
					Restrictions.eq("applicant.app_ID", appList.get(i).getApp_ID()));
			List<Examinee> examinees = cr.list();
			ApplicantInfo appInfo = new ApplicantInfo();
			appInfo.setApplicant( appList.get(i));
			if (!examinees.isEmpty()) {
				appInfo.setExaminee(examinees.get(0));
			} else{
				appInfo.setExaminee(null);
			}
			lstApplicantInfo.add(appInfo);
		}
		session.getTransaction().commit();
		session.close();
		return lstApplicantInfo;
	}
	
	/**
	 * ...検索情報によって申請者情報を取得する...
	 * @param jfYear JobFair年
	 * @param examPlace 受験場所
	 * @param sDate 登録開始日
	 * @param eDate 登録終了日
	 * @param sExamID 開始受験ID
	 * @param eExamID 終了受験ID
	 * @param firstCompany 第一希望会社名
	 * @param secondCompany 第二希望会社名
	 * @return lstApplicantInfo 申請者情報リスト
	 */
	@SuppressWarnings("unchecked")
	public List<ApplicantInfo> searchApplicant(int jfYear,Date sDate,
	          Date eDate,String examPlace,String sExamID,String eExamID,int firstCompany,int secondCompany)
			throws SQLException {
		session= HibernateUtil.getSessionFactory().openSession();
		try {
		session.beginTransaction();
		lstApplicantInfo = new ArrayList<ApplicantInfo>();
		String query;
		List<ApplicantInfo> appInfoList=new ArrayList<ApplicantInfo>();
		StringBuilder sb=new StringBuilder("SELECT app_ID FROM Applicant app WHERE app.del_flag=0 AND app.app_Check='Y' AND app.app_JFYear=:jfYear "
				        + "AND app.app_ExamPlace=:examPlace");
		
		if (sDate != null && eDate != null) {
			sb.append(" AND app_RegDate>=:sDate AND app_RegDate<=:eDate");
		} else {
			if (sDate != null) {
				sb.append(" AND app_RegDate>=:sDate");
			}
			if (eDate != null) {
				sb.append(" AND app_RegDate<=:eDate");
			}
		}
		
		if (sExamID != null && !sExamID.trim().isEmpty() && eExamID != null
				&& !eExamID.trim().isEmpty()) {
			sb.append(" AND app.exam_ID>=:sExamID AND app.exam_ID<=:eExamID");
		} else {
			if (sExamID != null && !sExamID.trim().isEmpty()) {
				sb.append(" AND app.exam_ID>=:sExamID");
			}
			if (eExamID != null && !eExamID.trim().isEmpty()) {
				sb.append(" AND app.exam_ID<=:eExamID");
			}
		}
		sb.append( " ORDER BY exam_ID");
		query=sb.toString();
	    Query queryResult = session.createQuery(query);
	    queryResult.setParameter("jfYear", jfYear);
	    queryResult.setParameter("examPlace", examPlace);
	   
	    if (sDate != null) {
	    	queryResult.setParameter("sDate", sDate);
		}
		if (eDate != null) {
			queryResult.setParameter("eDate", eDate);
		}
		
		if (sExamID != null && !sExamID.trim().isEmpty()) {
			queryResult.setParameter("sExamID", sExamID);
		}
		if (eExamID != null && !eExamID.trim().isEmpty()) {
			queryResult.setParameter("eExamID", eExamID);
		}
		
		List<Integer> applicants;
		applicants = queryResult.list();
		for (int i = 0; i < applicants.size(); i++) {
			Applicant applicant = new Applicant();
			applicant.setApp_ID(applicants.get(i));
			String examineeQuery = "SELECT examinee_ID FROM Examinee WHERE applicant.app_ID=:appID";
			if (firstCompany != 0) {
				examineeQuery = examineeQuery
						+ " AND firstCompany.com_ID=:firstCompany ";
			}
			if (secondCompany != 0) {
				examineeQuery = examineeQuery
						+ " AND secondCompany.com_ID=:secondCompany ";
			}
			Query examineeResult = session.createQuery(examineeQuery);
			examineeResult.setParameter("appID", applicant.getApp_ID());
			if (firstCompany != 0) {
				examineeResult.setParameter("firstCompany", firstCompany);
			}
			if (secondCompany != 0) {
				examineeResult.setParameter("secondCompany", secondCompany);
			}
			ApplicantInfo appInfo = new ApplicantInfo();
			List<Integer> examinees = examineeResult.list();
			if (examinees.isEmpty()) {
				if (firstCompany == 0 && secondCompany == 0 ) {
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
			if(appInfo!=null){
				appInfoList.add(appInfo);
			}
		}
		if(appInfoList.size() <= 50){
			for(int i=0;i<appInfoList.size();i++){
				
				ApplicantInfo appInfo=new ApplicantInfo();
				
				StringBuilder sbApplicant=new StringBuilder("FROM Applicant where app_ID=:appID");
				
				Query appQuery = session.createQuery(sbApplicant.toString());
				appQuery.setParameter("appID", appInfoList.get(i).getApplicant().getApp_ID());				
				Applicant applicant=(Applicant)appQuery.uniqueResult();
				appInfo.setApplicant(applicant);
				
				if(appInfoList.get(i).getExaminee()!=null){
					StringBuilder sbExaminee=new StringBuilder("FROM Examinee WHERE examinee_ID=:examineeID");
					Query examineeQuery = session.createQuery(sbExaminee.toString());
					examineeQuery.setParameter("examineeID", appInfoList.get(i).getExaminee().getExaminee_ID());
					Examinee examinee=(Examinee)examineeQuery.uniqueResult();
					appInfo.setExaminee(examinee);
				}
				
				lstApplicantInfo.add(appInfo);
			}
		}else{
			lstApplicantInfo=appInfoList;
		}	} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}	
		
		return lstApplicantInfo;
	}
	
	/**
	 * 選んだJobFair年によって登録した会社名一覧をデータベースから取得する
	 * @param jfYear JobFair年
	 * @return lstCompany 会社リスト
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public List<Company> getCompany(int jfYear) throws IOException {
		session = HibernateUtil.getSessionFactory().openSession();
		List<Company> lstCompany = new ArrayList<Company>();
		session.beginTransaction();
		Query q = session.createQuery("FROM Company WHERE del_flag=0 AND com_Reg_Year=:jfYear");
		q.setParameter("jfYear", jfYear);
		lstCompany = q.list();
		session.getTransaction().commit();
		session.close();
		return lstCompany;
	}
	
}