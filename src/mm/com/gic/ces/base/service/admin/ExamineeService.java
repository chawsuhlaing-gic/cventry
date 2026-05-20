/***
 *  CV_A_032_受験者情報登録画面
 * 新規作成: 2019/1/22 Saw Yu Nwe 
 * 作成概要： 受験者情報登録処理
 * 
 * 更新履歴： 
 * 更新概要：
 *
 */
package mm.com.gic.ces.base.service.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import mm.com.gic.ces.application.model.Applicant;
import mm.com.gic.ces.application.model.ApplicantInfo;
import mm.com.gic.ces.application.model.Company;
import mm.com.gic.ces.application.model.Examinee;
import mm.com.gic.ces.base.common.HibernateUtil;

public class ExamineeService {
	
	private List<Company> com_List;            //会社リスト
	Session session = null;                    //セッション

	/**
	 * ...JobFair年によって会社名を検索する。...
	 * @param jfyear　　　　JobFair年
	 * @return com_List  会社名リスト
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<Company> getCompanyList(int jfyear) throws SQLException {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		com_List = new ArrayList<Company>();
		com_List = session.createQuery("FROM Company where del_flag=0 AND com_Reg_Year=" + jfyear).list();
		session.getTransaction().commit();
		session.close();
		return com_List;
	}

	/**
	 * ...申請IDで受験者を検索する。...
	 * @param app_ID　　　申請ID
	 * @return examinee 受験者
	 */
	public Examinee checkExaminee(int app_ID) {
		session = HibernateUtil.getSessionFactory().openSession();
		Examinee examinee = new Examinee();
		session.beginTransaction();
		Query query = session.createQuery("FROM Examinee where app_ID= " + app_ID);
		examinee = (Examinee) query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return examinee;
	}

	/**
	 * ...検索情報によって申請者を検索する。...
	 * @param jfYear     JobFair年
	 * @param examPlace　　受験場所
	 * @param sDate　　　　　開始日
	 * @param eDate　　　　　終了日
	 * @param sExamID　　　開始受験ID
	 * @param eExamID　　　終了受験ID
	 * @return　app_List
	 */
	@SuppressWarnings("unchecked")
	public List<ApplicantInfo> searchApplicant(int jfYear, String examPlace,
			Date sDate, Date eDate, String sExamID, String eExamID) {
		List<Applicant> lstApplicant = new ArrayList<Applicant>();
		List<ApplicantInfo> app_List = new ArrayList<ApplicantInfo>();      //申請者リスト
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(Applicant.class)
			    .setProjection(Projections.projectionList()
			      .add(Projections.property("app_ID"), "app_ID")
			      .add(Projections.property("exam_ID"), "exam_ID")
			      .add(Projections.property("app_Name"), "app_Name")
			      .add(Projections.property("app_Gender"), "app_Gender")
			      .add(Projections.property("app_DOB"), "app_DOB")
			      .add(Projections.property("app_Degree"), "app_Degree")
			      .add(Projections.property("app_ACYear"), "app_ACYear")
			      .add(Projections.property("app_Education"), "app_Education")
			      .add(Projections.property("app_University"), "app_University")
			      .add(Projections.property("app_CityofUniversity"), "app_CityofUniversity")
			      .add(Projections.property("app_RegDate"), "app_RegDate")
			      .add(Projections.property("del_flag"), "del_flag")
			      .add(Projections.property("app_Check"), "app_Check"))
			    .setResultTransformer(Transformers.aliasToBean(Applicant.class));
        cr.add(Restrictions.eq("del_flag", 0));
        cr.add(Restrictions.eq("app_Check", "Y"));
        cr.add(Restrictions.eq("app_JFYear", jfYear));
        cr.add(Restrictions.eq("app_ExamPlace", examPlace));
		if (sDate != null && eDate != null) {
			cr.add(Restrictions.ge("app_RegDate", sDate)) 
	          .add(Restrictions.le("app_RegDate", eDate));
		} else {
			if (sDate != null) {
				cr.add(Restrictions.ge("app_RegDate", sDate));
			}
			if (eDate != null) {
				cr.add(Restrictions.le("app_RegDate", eDate));
			}
		}
		if (sExamID != null && !sExamID.trim().isEmpty() && eExamID != null
				&& !eExamID.trim().isEmpty()) {
			cr.add(Restrictions.ge("exam_ID", sExamID)) 
	          .add(Restrictions.le("exam_ID", eExamID));
		} else {
			if (sExamID != null && !sExamID.trim().isEmpty()) {
				cr.add(Restrictions.ge("exam_ID", sExamID));
			}
			if (eExamID != null && !eExamID.trim().isEmpty()) {
				cr.add(Restrictions.le("exam_ID", eExamID));
			}
		}
		cr.addOrder(Order.asc("exam_ID"));
		lstApplicant = cr.list();
		for (int i = 0; i < lstApplicant.size(); i++) {
			Applicant applicant = (Applicant) lstApplicant.get(i);
			Criteria crite = session.createCriteria(Examinee.class).add(
					Restrictions.eq("applicant.app_ID", applicant.getApp_ID()));
			List<Examinee> examinees = crite.list();
			ApplicantInfo appInfo = new ApplicantInfo();
			appInfo.setApplicant(applicant);
			if (!examinees.isEmpty()) {
				appInfo.setExaminee(examinees.get(0));
			} else
				appInfo.setExaminee(null);
			app_List.add(appInfo);
		}
		session.getTransaction().commit();
		session.close();
		return app_List;
	}

	/**
	 * ...受験者IDで受験者情報を検索する...
	 * @param examinee_ID　受験ID
	 * @return examinee   受験者
	 * @throws SQLException
	 */
	public Examinee searchExaminee(int examinee_ID) throws SQLException {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Examinee examinee = (Examinee) session.get(Examinee.class, examinee_ID);
		session.getTransaction().commit();
		session.close();
		return examinee;
	}

	/**
	 * ...受験者をデータベースに更新する...
	 * @param examinee 受験者
	 * @throws SQLException
	 */
	public void updateExaminee(Examinee examinee) throws SQLException {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(examinee);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * ...受験者をデータベースに登録する...
	 * @param examinee 受験者
	 * @throws SQLException
	 */
	public void insertExaminee(Examinee examinee) throws SQLException {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(examinee);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * ...会社IDで会社情報を検索する....
	 * @param com_ID   会社ID
	 * @return company　会社
	 * @throws SQLException
	 */
	public Company searchCompany(int com_ID) throws SQLException {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Company company = (Company) session.get(Company.class, com_ID);
		session.getTransaction().commit();
		session.close();
		return company;
	}

	/**
	 * ...申請IDで申請者情報を検索する...
	 * @param app_ID　　　　申請ID
	 * @return applicant 申請者
	 * @throws SQLException
	 */
	public Applicant searchApplicant(int app_ID) throws SQLException {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Applicant applicant = (Applicant) session.get(Applicant.class, app_ID);
		session.getTransaction().commit();
		session.close();
		return applicant;
	}
}