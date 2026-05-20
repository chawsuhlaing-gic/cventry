/**
 * CV_A_061_顧客情報登録画面
 * 作成履歴： 2019/2/8 Saw Yu Nwe
 * 作成概要：
 * 新規作成：　顧客情報登録処理
 * 
 * 更新履歴：dd/mm/yyyy name
 * 更新概要：XXXXXXXX	
 */
package mm.com.gic.ces.application.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import mm.com.gic.ces.application.common.CommonUtility;
import mm.com.gic.ces.application.model.Company;
import mm.com.gic.ces.application.model.Pagination;
import mm.com.gic.ces.application.model.RoleSetting;
import mm.com.gic.ces.base.service.admin.CompanyService;

/**
 * 顧客情報を登録し、更新し、削除する
 */
public class CompanyRegistrationAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Company company = new Company();                                        // 　会社オブジェクト
	private List<Integer> lstYear = new ArrayList<Integer>();                       // 会社登録年リスト
	private List<Company> com_List = new ArrayList<Company>();                      // 会社リスト
	private CompanyService comService = new CompanyService();                       // 会社サービス
	private RoleSetting roleSetting = new RoleSetting();                            // ロール設定オブジェクト
	static Map<String, Object> mapSession;// マップセッション
	HttpServletRequest request = ServletActionContext.getRequest();                 // サーブレットリクエスト
	HttpSession httpSession = request.getSession();                                 // セッション
	private int com_ex_key;                                                         // 更新Key
	@SuppressWarnings("rawtypes")
	private Pagination pagination = new Pagination();                               // ページネーションオブジェクト
	private int noOfRecordsPerPage;                                                 // 1ページあたりのレコード数
	private String errorMsg = null;                                                 // エラーメッセージ
	private String successMsg = null;                                               // 成功メッセージ

	/**
	 * ...顧客情報を取得する...
	 * @return　companyList　//会社リスト
	 * @throws SQLException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String getCompanyList() throws SQLException, IOException {
		Calendar now = Calendar.getInstance();
		for (int i = 0; i < 5; i++) {
			lstYear.add(((Integer) now.get(Calendar.YEAR)) - i);
		}
		com_List = comService.getCompanyList((Integer) now.get(Calendar.YEAR),
				(Integer) now.get(Calendar.YEAR)-4);
		// pagination
		pagination = getCompanyPagination(pagination, com_List);
		com_List = new ArrayList<Company>();
		com_List = pagination.getTList();
		String errorMessage = (String) httpSession.getAttribute("comError");
		String successMessage = (String) httpSession.getAttribute("comSuccess");
		if (errorMessage != null) {
			addActionError(errorMessage);
		} else if (successMessage != null) {
			addActionMessage(successMessage);
		}
		httpSession.removeAttribute("comError");
		httpSession.removeAttribute("comSuccess");
		return "companyList";
	}

	/**
	 * ...顧客情報を編集する...
	 * @return success
	 * @throws SQLException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String editCompany() throws SQLException, IOException {
		Company companyTemp = comService.editCompany(company.getCom_ID());
		if (companyTemp.getCom_ex_key() != getCom_ex_key()) {
			Properties prop = CommonUtility.getValue("Message.properties");
			errorMsg = prop.getProperty(Integer.toString(21));
			httpSession.setAttribute("comError", errorMsg);
			httpSession.setAttribute("comSuccess", null);
		} else if (companyTemp.getDel_flag() == 1) {
			Properties prop = CommonUtility.getValue("Message.properties");
			errorMsg = prop.getProperty(Integer.toString(20));
			httpSession.setAttribute("comError", errorMsg);
			httpSession.setAttribute("comSuccess", null);
		} else {
			company = comService.editCompany(company.getCom_ID());
		}
		Calendar now = Calendar.getInstance();
		for (int i = 0; i < 5; i++) {
			lstYear.add(((Integer) now.get(Calendar.YEAR)) - i);
		}
		com_List = comService.getCompanyList((Integer) now.get(Calendar.YEAR),
				(Integer) now.get(Calendar.YEAR)-4);
		// pagination
		pagination = getCompanyPagination(pagination, com_List);
		com_List = new ArrayList<Company>();
		com_List = pagination.getTList();
		String errorMessage = (String) httpSession.getAttribute("comError");
		String successMessage = (String) httpSession.getAttribute("comSuccess");
		if (errorMessage != null) {
			addActionError(errorMessage);
		} else if (successMessage != null) {
			addActionMessage(successMessage);
		}
		httpSession.removeAttribute("comError");
		httpSession.removeAttribute("comSuccess");
		return "success";
	}

	/**
	 * ...顧客情報を登録し、更新する...
	 * @return success
	 * @throws SQLException
	 * @throws IOException
	 */
	public String saveCompanyInfo() throws SQLException, IOException {
		com_List = comService.getAllCompanyList(company.getCom_Reg_Year());
		Date date = new Date();
		mapSession = ActionContext.getContext().getSession();
		roleSetting = (RoleSetting) mapSession.get("loggedInadmin");
		int result = 0;
		int doubleInsert = 0;
		if (com_List != null) {
			for (Company com : com_List) {
				if (com.getCom_Lname().equalsIgnoreCase(company.getCom_Lname().trim())
						&& com.getCom_Sname().equalsIgnoreCase(company.getCom_Sname().trim())
						&& com.getCom_ID() != company.getCom_ID()
						&& com.getDel_flag()==1) {
					doubleInsert = com.getCom_ID();
					break;
				}
				if (com.getCom_Lname().equalsIgnoreCase(company.getCom_Lname().trim())
						&& com.getCom_ID() != company.getCom_ID()) {
					Properties prop = CommonUtility.getValue("Message.properties");
					errorMsg = prop.getProperty(Integer.toString(22));
					httpSession.setAttribute("comError", errorMsg);
					httpSession.setAttribute("comSuccess", null);
					break;
				} else if (com.getCom_Sname().equalsIgnoreCase(company.getCom_Sname().trim())
						&& com.getCom_ID() != company.getCom_ID()) {
					Properties prop = CommonUtility.getValue("Message.properties");
					errorMsg = prop.getProperty(Integer.toString(22));
					httpSession.setAttribute("comError", errorMsg);
					httpSession.setAttribute("comSuccess", null);
					break;
				} else {
					result++;
				}
			}
		}
		if (com_List.size() == result) {
			if (company.getCom_ID() == 0) {
				company.setCom_Lname(company.getCom_Lname().trim());
				company.setCom_Sname(company.getCom_Sname().trim());
				company.setCom_Reg_Year(company.getCom_Reg_Year());
				company.setLast_updateUser(roleSetting.getRole_Name());
				company.setLast_updateTime(date);
				company.setDel_flag(0);
				company.setCom_ex_key(1);
				comService.insertCompany(company);
				Properties prop = CommonUtility.getValue("Message.properties");
				successMsg = prop.getProperty(Integer.toString(2));
				httpSession.setAttribute("comError", null);
				httpSession.setAttribute("comSuccess", successMsg);
				company = new Company();
			} else {
				Company companyTemp = comService.editCompany(company.getCom_ID());
				if (companyTemp != null) {
					if (companyTemp.getCom_ex_key() != company.getCom_ex_key()) {
						Properties prop = CommonUtility.getValue("Message.properties");
						errorMsg = prop.getProperty(Integer.toString(21));
						httpSession.setAttribute("comError", errorMsg);
						httpSession.setAttribute("comSuccess", null);
					}else if (companyTemp.getDel_flag() == 1) {
						Properties prop = CommonUtility.getValue("Message.properties");
						errorMsg = prop.getProperty(Integer.toString(20));
						httpSession.setAttribute("comError", errorMsg);
						httpSession.setAttribute("comSuccess", null);
					} else if (companyTemp.getCom_ex_key() == company.getCom_ex_key()
							&& companyTemp.getDel_flag() == 0) {
						companyTemp.setCom_Lname(company.getCom_Lname().trim());
						companyTemp.setCom_Sname(company.getCom_Sname().trim());
						companyTemp.setLast_updateUser(roleSetting.getRole_Name());
						companyTemp.setLast_updateTime(date);
						companyTemp.setCom_ex_key(company.getCom_ex_key() + 1);
						comService.updateCompany(companyTemp);
						Properties prop = CommonUtility.getValue("Message.properties");
						successMsg = prop.getProperty(Integer.toString(1));
						httpSession.setAttribute("comError", null);
						httpSession.setAttribute("comSuccess", successMsg);
						company = new Company();
					}
				}
			}
		} else {
			if (doubleInsert != 0) {
				Company companyTemp = comService.editCompany(doubleInsert);
				companyTemp.setCom_Lname(company.getCom_Lname().trim());
				companyTemp.setCom_Sname(company.getCom_Sname().trim());
				companyTemp.setLast_updateUser(roleSetting.getRole_Name());
				companyTemp.setLast_updateTime(date);
				companyTemp.setCom_ex_key(companyTemp.getCom_ex_key() + 1);
				companyTemp.setDel_flag(0);
				comService.updateCompany(companyTemp);
				Properties prop = CommonUtility.getValue("Message.properties");
				successMsg = prop.getProperty(Integer.toString(2));
				httpSession.setAttribute("comError", null);
				httpSession.setAttribute("comSuccess", successMsg);
				company = new Company();
			}
		}
		return "success";
	}

	/**
	 * ...顧客情報を削除する...
	 * @return success
	 * @throws IOException
	 * @throws SQLException
	 */
	public String deleteCompany() throws IOException, SQLException {
		company = comService.getCompanyByID(company.getCom_ID());
		Date date = new Date();
		mapSession = ActionContext.getContext().getSession();
		roleSetting = (RoleSetting) mapSession.get("loggedInadmin");
		if (company == null) {
			company = new Company();
			company.setCom_ID(0);
			Properties prop = CommonUtility.getValue("Message.properties");
			errorMsg = prop.getProperty(Integer.toString(23));
			httpSession.setAttribute("comError", errorMsg);
			httpSession.setAttribute("comSuccess", null);
		} else {
			if (company.getCom_ex_key() == getCom_ex_key()) {
				company.setLast_updateUser(roleSetting.getRole_Name());
				company.setLast_updateTime(date);
				company.setDel_flag(1);
				company.setCom_ex_key(getCom_ex_key() + 1);
				comService.updateCompany(company);
				Properties prop = CommonUtility.getValue("Message.properties");
				successMsg = prop.getProperty(Integer.toString(8));
				httpSession.setAttribute("comError", null);
				httpSession.setAttribute("comSuccess", successMsg);
			} else {
				Properties prop = CommonUtility.getValue("Message.properties");
				errorMsg = prop.getProperty(Integer.toString(24));
				httpSession.setAttribute("comError", errorMsg);
				httpSession.setAttribute("comSuccess", null);
			}
		}
		return "success";
	}

	/**
	 * ...ページネーションボタン処理...
	 * @return SUCCESS
	 * @throws SQLException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String paginateCompanyList() throws SQLException, IOException {
		Calendar now = Calendar.getInstance();
		for (int i = 0; i < 5; i++) {
			lstYear.add(((Integer) now.get(Calendar.YEAR)) - i);
		}
		com_List = comService.getCompanyList((Integer) now.get(Calendar.YEAR),
				(Integer) now.get(Calendar.YEAR)-4);
		if (com_List.size() != 0) {
			// pagination
			pagination = getCompanyPagination(pagination, com_List);
			com_List = new ArrayList<Company>();
			com_List = pagination.getTList();
		}
		return SUCCESS;
	}

	/**
	 * ...会社リストをページ付ける...
	 * @param pagination　　　ページネーションオブジェクト
	 * @param com_List      会社リスト
	 * @return pagination   ページネーションした合格者情報
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Pagination getCompanyPagination(Pagination pagination,
			List<Company> com_List) throws IOException {
		Properties prop = CommonUtility.getValue("PaginationRecord.properties");
		noOfRecordsPerPage = Integer.parseInt(prop.getProperty(Integer
				.toString(1)));
		List<Company> selectedCompany = new ArrayList<Company>();
		int totalNoOfRecords = com_List.size();
		int startIndex = 0;
		int totalPages = 1;
		if (totalNoOfRecords > noOfRecordsPerPage) {
			double noOfPages = (double) totalNoOfRecords
					/ (double) noOfRecordsPerPage;
			totalPages = (int) noOfPages;
			if (noOfPages % totalPages > 0.0) {
				totalPages++;
			}
		}
		if (pagination.getSelectedPageNumber() > 1) {
			startIndex = noOfRecordsPerPage
					* (pagination.getSelectedPageNumber() - 1);
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
				selectedCompany.add(com_List.get(i));
			}
		} else if (totalPages == pagination.getSelectedPageNumber()) {
			for (int i = startIndex; i < com_List.size(); i++) {
				if (com_List.get(i) != null) {
					Company obj_company = new Company();
					obj_company = com_List.get(i);
					selectedCompany.add(obj_company);
				} else {
					break;
				}
			}
		} else {
			selectedCompany.addAll(com_List);
		}
		pagination.setTList(selectedCompany);
		return pagination;
	}

	// 　会社オブジェクト
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	// 会社登録年リスト
	public List<Integer> getLstYear() {
		return lstYear;
	}

	public void setLstYear(List<Integer> lstYear) {
		this.lstYear = lstYear;
	}

	// 会社リスト
	public List<Company> getCom_List() {
		return com_List;
	}

	public void setCom_List(List<Company> com_List) {
		this.com_List = com_List;
	}

	// ロール設定オブジェクト
	public RoleSetting getRoleSetting() {
		return roleSetting;
	}

	public void setRoleSetting(RoleSetting roleSetting) {
		this.roleSetting = roleSetting;
	}

	// 更新Key
	public int getCom_ex_key() {
		return com_ex_key;
	}

	public void setCom_ex_key(int com_ex_key) {
		this.com_ex_key = com_ex_key;
	}

	// ページネーションオブジェクト
	@SuppressWarnings("rawtypes")
	public Pagination getPagination() {
		return pagination;
	}

	@SuppressWarnings("rawtypes")
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

}