/**
 * CV_A_043_会社別合格者情報照会画面
 * 作成履歴：2019/2/8 Nwe Ni Hlaing
 * 作成概要：新規作成
 * 
 * 更新履歴：08/04/2019 Nwe Ni Hlaing
 * 更新概要：XXXXXXXX	
 */
package mm.com.gic.ces.base.service.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mm.com.gic.ces.application.model.ApplicantInfo;
import mm.com.gic.ces.application.model.Company;
import mm.com.gic.ces.application.model.Examinee;
import mm.com.gic.ces.application.model.Interview;
import mm.com.gic.ces.base.common.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *データベースにある合格者情報を取る
 */
public class PassExamineeInfoService {
	
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
		StringBuilder sb=new StringBuilder("FROM Examinee WHERE applicant.app_ID IN (:IDList)");
		sb.append( " ORDER BY applicant.exam_ID");
		query=sb.toString();
		Query queryResult = session.createQuery(query);
		queryResult.setParameterList("IDList", appIDList);
		List<Examinee> examinees;
		examinees = queryResult.list();		
		for (int i = 0; i < examinees.size(); i++) {
			Examinee examinee = (Examinee) examinees.get(i);
			Criteria cr = session.createCriteria(Interview.class).add(
					Restrictions.eq("examinee.examinee_ID", examinee.getExaminee_ID()));
			List<Interview> interviewees = cr.list();
			ApplicantInfo appInfo = new ApplicantInfo();
			appInfo.setExaminee(examinee);
			if (!interviewees.isEmpty()) {
				appInfo.setInterview(interviewees.get(0));
			} else
				appInfo.setInterview(null);
			lstApplicantInfo.add(appInfo);
		}
		session.getTransaction().commit();
		session.close();
		return lstApplicantInfo;		
	}
		
	/**
	 * ...検索情報によって合格者情報を取得する...
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
	public List<ApplicantInfo> searchPassExaminee(int jfYear,Date startDate,
	          Date endDate,String examPlace,String sExamID,String eExamID,int firstCompany,int secondCompany) throws SQLException {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		lstApplicantInfo = new ArrayList<ApplicantInfo>();
		
		String query;
		StringBuilder sb=new StringBuilder("SELECT examinee_ID FROM Examinee WHERE applicant.app_JFYear=:jfYear AND examinee_Pass='Y' AND applicant.del_flag=0"
				        + "AND applicant.app_ExamPlace=:examPlace");
		
		if (startDate != null && endDate != null) {
			sb.append(" AND applicant.app_RegDate>=:startDate and applicant.app_RegDate<=:endDate");
		} else {
			if (startDate != null) {
				sb.append(" AND applicant.app_RegDate>=:startDate");
			} else if (endDate != null) {
				sb.append(" AND applicant.app_RegDate<=:endDate");
			}
		}
		
		if (sExamID != null && !sExamID.trim().isEmpty() && eExamID != null
				&& !eExamID.trim().isEmpty()) {
			sb.append(" AND applicant.exam_ID>=:sExamID AND applicant.exam_ID<=:eExamID");
		} else {
			if (sExamID != null && !sExamID.trim().isEmpty()) {
				sb.append(" AND applicant.exam_ID>=:sExamID");
			}
			if (eExamID != null && !eExamID.trim().isEmpty()) {
				sb.append(" AND applicant.exam_ID<=:eExamID");
			}
		}
		
		if (firstCompany!=0 ){
			sb.append(" AND firstCompany.com_ID=:firstCompany");
		}
		if (secondCompany!=0){
			sb.append(" AND secondCompany.com_ID=:secondCompany");
		}
		sb.append( " ORDER BY applicant.exam_ID");
		query=sb.toString();
		Query q = session.createQuery(query);
		q.setParameter("jfYear", jfYear);
		q.setParameter("examPlace", examPlace);	
		
		if (startDate != null && endDate != null) {
			q.setParameter("startDate", startDate);
			q.setParameter("endDate", endDate);
		} else {
			if (startDate != null) {
				q.setParameter("startDate", startDate);
			} else if (endDate != null) {
				q.setParameter("endDate", endDate);
			}
		}
		
		if (firstCompany!=0){
			q.setParameter("firstCompany", firstCompany);
		}
		if (secondCompany!=0){
			q.setParameter("secondCompany", secondCompany);
		}
		
		if (sExamID != null && !sExamID.trim().isEmpty()) {
			q.setParameter("sExamID", sExamID);
		}
		if (eExamID != null && !eExamID.trim().isEmpty()) {
			q.setParameter("eExamID", eExamID);
		}
	
		List<Integer> examinees;
		examinees = q.list();
		List<ApplicantInfo> appInfoList=new ArrayList<ApplicantInfo>();
		for(int i=0;i<examinees.size();i++){
			ApplicantInfo appInfo=new ApplicantInfo();
			Examinee examinee=new Examinee();
			examinee.setExaminee_ID(examinees.get(i));
			appInfo.setExaminee(examinee);
			appInfoList.add(appInfo);
		}
		
		if(examinees.size()<= 50){
			for (int i = 0; i < examinees.size(); i++) {
				int examineeID = examinees.get(i);
				
				StringBuilder examineeQuery=new StringBuilder("FROM Examinee WHERE examinee_ID=:examineeID");
				Query queryResult=session.createQuery(examineeQuery.toString());
				queryResult.setParameter("examineeID", examineeID);
				Examinee examinee=(Examinee) queryResult.uniqueResult();
						
				Criteria cr = session.createCriteria(Interview.class).add(
						Restrictions.eq("examinee.examinee_ID", examineeID));
				List<Interview> interviewees = cr.list();
				ApplicantInfo appInfo = new ApplicantInfo();
				appInfo.setExaminee(examinee);
				if (!interviewees.isEmpty()) {
					appInfo.setInterview(interviewees.get(0));
				} else
					appInfo.setInterview(null);
				lstApplicantInfo.add(appInfo);
			}
		}else{
			lstApplicantInfo=appInfoList;
		}
		
		
		session.getTransaction().commit();
		session.close();
		return lstApplicantInfo;
	}

	/**
	 * 選んだJobFair年によって登録した会社名一覧をデータベースから取得する
	 * @param jfYear　JobFair年
	 * @return lstCompany　会社リスト
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