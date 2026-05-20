/**
 * CV_A_061_顧客情報登録画面
 * 作成履歴： 2019/2/8 Saw Yu Nwe
 * 作成概要：
 * 新規作成：　顧客情報登録処理
 * 
 * 更新履歴：dd/mm/yyyy name
 * 更新概要：XXXXXXXX	
 */
package mm.com.gic.ces.base.service.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import mm.com.gic.ces.application.model.Company;
import mm.com.gic.ces.base.common.HibernateUtil;

/**
 * 顧客情報を登録し、更新し、削除する
 */
public class CompanyService {
	private List<Company> com_List = new ArrayList<Company>();
	Session session = null;

	/**
	 * ...顧客情報を取得する...
	 * @return com_List
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<Company> getCompanyList(int sYear, int eYear)
			throws SQLException {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		com_List = new ArrayList<Company>();
		com_List = session.createQuery("FROM Company WHERE del_flag=0 AND com_Reg_Year<= "
				+ sYear + " AND com_Reg_Year>= " + eYear + " ORDER BY com_Reg_Year DESC, com_Lname ASC").list();
		session.getTransaction().commit();
		session.close();
		return com_List;
	}

	/**
	 * ...全部の顧客情報を取得する...
	 * @return com_List
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<Company> getAllCompanyList(int regYear) throws SQLException {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		com_List = new ArrayList<Company>();
		com_List = session.createQuery("FROM Company WHERE com_Reg_Year = " + regYear).list();
		session.getTransaction().commit();
		session.close();
		return com_List;
	}

	/**
	 * ...顧客情報を登録する...
	 * 
	 * @param company
	 * @throws SQLException
	 */
	public void insertCompany(Company company) throws SQLException {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(company);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * ...顧客情報を編集する...
	 * 
	 * @param com_ID
	 * @return company
	 * @throws SQLException
	 */
	public Company editCompany(int com_ID) throws SQLException {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Company company = (Company) session.get(Company.class, com_ID);
		session.getTransaction().commit();
		session.close();
		return company;
	}

	/**
	 * ...顧客情報を更新する...
	 * 
	 * @param company
	 * @throws SQLException
	 */
	public void updateCompany(Company company) throws SQLException {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(company);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * ...Company IDによって顧客情報を取得する...
	 * 
	 * @param com_ID
	 * @return
	 */
	public Company getCompanyByID(int com_ID) {
		session = HibernateUtil.getSessionFactory().openSession();
		Company company = new Company();
		session.beginTransaction();
		Query q = session
				.createQuery("FROM Company WHERE del_flag=0 AND com_ID=:comID");
		q.setParameter("comID", com_ID);
		company = (Company) q.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return company;
	}
}