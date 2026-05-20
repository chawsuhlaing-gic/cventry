/**
 * CV_A_052_会社別採用者情報照会画面			

 *	作成履歴：10/2/2019 Nwe Ni Hlaing			
 *	作成概要：新規作成　Excel出力処理			
 *				
 *	更新履歴：08/4/2019 Nwe Ni Hlaing	
 *	更新概要：XXXXXXXX
 */
package mm.com.gic.ces.application.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import mm.com.gic.ces.application.common.CommonRegister;
import mm.com.gic.ces.application.common.CommonUtility;
import mm.com.gic.ces.application.model.Applicant;
import mm.com.gic.ces.application.model.ApplicantInfo;
import mm.com.gic.ces.application.model.Company;
import mm.com.gic.ces.application.model.Examinee;
import mm.com.gic.ces.application.model.Interview;
import mm.com.gic.ces.application.property.ExamPlace;
import mm.com.gic.ces.base.common.CommonService;
import mm.com.gic.ces.base.service.admin.EmployeeInfoService;

/**
 *採用者の情報を出力する
 */
@SuppressWarnings("serial")
public class EmployeeInfoAction extends ActionSupport implements Serializable{
	@SuppressWarnings("unused")
	private static final String SUCCESS = null;
	public Applicant applicant = new Applicant();									//申請者オブジェクト
  	public Company company = new Company();											//会社オブジェクト
	public Examinee examinee = new Examinee();										//受験者オブジェクト
	public Interview interview = new Interview();									//面接者オブジェクト
	public ApplicantInfo applicantInfo = new ApplicantInfo();						//申請者情報オブジェクト
	public CommonUtility commonUtility;												//共通ユーティリティオブジェクト
	private CommonService commonService = new CommonService();						//共通サービスオブジェクト
	private EmployeeInfoService employeeInfoService = new EmployeeInfoService();	//採用者情報サービスオブジェクト
	private List<ApplicantInfo> lstApplicantInfo = new ArrayList<ApplicantInfo>();	//申請者情報のリスト
	private List<Integer> lstApplicantID = new ArrayList<Integer>();				//申請者IDリスト
	private List<Integer> lstYear = new ArrayList<Integer>();						//JobFair年リスト
	private List<Integer> lstAge = new ArrayList<Integer>();						//年齢リスト	
	private List<Company> lstCompany = new ArrayList<Company>();					//会社のリスト
	private List<ExamPlace> lstExamPlace = new ArrayList<ExamPlace>();				//受験場所リスト
	private String btn;																//ボタン
	private int ddlJobFairYear;														//jobFair年
	private String ddlExamPlace;													//受験場所
	private Date regStartDate;														//登録開始日
	private Date regEndDate;														//登録終了日
	private int ddlCompany;															//会社名
	private String txtStartExamID;													//開始受験ID
	private String txtEndExamID;													//終了受験ID
	private Properties properties=new Properties();									//プロパティ
	private String fileName;                                                  		//ファイル名
	private InputStream inputStream;                                          		//入力ストリーム

	/**
	 * ...採用者情報を取る...
	 * @return employeeList 採用者リスト
	 * @throws SQLException
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	public String employeeList() throws IOException {
		lstYear = commonService.selectJFYear();
		lstExamPlace = commonUtility.getExamPlace();
		Properties properties = commonUtility.getValue("config.properties");
		String folderPath = properties.getProperty("ExportExcel");
		CommonUtility.recursiveDelete(new File(folderPath));
		return "employeeList";
	}

	/**
	 * ...採用者の情報を出力する...
	 * @param lstApplicantInfo 申請者情報リスト
	 * @return 
	* @throws SQLException
	 * @throws IOException 
	 */
	public boolean printExcel(List<ApplicantInfo> lstEmployeeInfo)throws SQLException, IOException {
		@SuppressWarnings("unused")
		DateFormat format = new SimpleDateFormat("YYYY MMM dd");
		properties= CommonUtility.getValue("ExcelFileName.properties");		
		String fName =properties.getProperty(Integer.toString(6));
		String excelFilePath = ServletActionContext.getServletContext().getRealPath("/") + "file" + "/" + fName;
		try {
			FileInputStream inputStream = new FileInputStream(new File(excelFilePath+".xlsx"));
			Workbook workbook = WorkbookFactory.create(inputStream);
			Sheet sheet=workbook.getSheetAt(0);
			for (int k = 0; k < lstEmployeeInfo.size(); k = k + 1) {							
				if (k < sheet.getLastRowNum()) {		
					Cell cell1 = sheet.getRow(k+4).getCell(0);
					cell1.setCellValue(lstEmployeeInfo.get(k).getInterview().getExaminee().getApplicant()
							.getExam_ID());
								
					Cell cell2 = sheet.getRow(k+4).getCell(1);
					cell2.setCellValue(lstEmployeeInfo.get(k).getInterview().getExaminee().getApplicant()
										.getApp_Name());
								
					Cell cell3 = sheet.getRow(k+4).getCell(2);
					cell3.setCellValue(lstEmployeeInfo.get(k).getInterview().getCompany().getCom_Sname());

					Cell cell4 = sheet.getRow(k+4).getCell(3);
					cell4.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee()
										.getExaminee_IQmark());
								
					Cell cell5 = sheet.getRow(k+4).getCell(4);
					cell5.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee()
										.getExaminee_EQmark());
								
					Cell cell6 = sheet.getRow(k+4).getCell(5);
					cell6.setCellValue(lstApplicantInfo.get(k).getInterview()
										.getInterview_Date());
								
					Cell cell7 = sheet.getRow(k+4).getCell(6);
					String time=lstApplicantInfo.get(k).getInterview()
								.getInterview_StartTime()+"~"+lstApplicantInfo.get(k).getInterview()
								.getInterview_EndTime();
					cell7.setCellValue(time);
								
					Cell cell8 = sheet.getRow(k+4).getCell(7);
					cell8.setCellValue(lstApplicantInfo.get(k).getInterview()
										.getInterview_Place());
								
					Cell cell9 = sheet.getRow(k+4).getCell(8);
					cell9.setCellValue(lstApplicantInfo.get(k).getInterview()
										.getInterviewer());
													
					Cell cell10 = sheet.getRow(k+4).getCell(9);
					cell10.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee().getApplicant()
										.getApp_Gender());
								
					Cell cell11 = sheet.getRow(k+4).getCell(10);
					cell11.setCellValue(CommonRegister.calculateAge(lstApplicantInfo.get(k).getInterview()
							            .getExaminee().getApplicant().getApp_DOB()));
								
					Cell cell12 = sheet.getRow(k+4).getCell(11);
					cell12.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee().getApplicant()
										.getApp_Nrc());
								
					Cell cell13 = sheet.getRow(k+4).getCell(12);
					cell13.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee().getApplicant()
										.getApp_PhNo());
								
					Cell cell14 = sheet.getRow(k+4).getCell(13);
					cell14.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee().getApplicant()
										.getApp_Degree());
								
					Cell cell15 = sheet.getRow(k+4).getCell(14);
					cell15.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee().getApplicant()
										.getApp_University());
								
					Cell cell16 = sheet.getRow(k+4).getCell(15);
					cell16.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee().getApplicant()
										.getApp_CityofUniversity());
								
					Cell cell17 = sheet.getRow(k+4).getCell(16);
					cell17.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee().getApplicant()
										.getApp_Email());
								
					Cell cell18 = sheet.getRow(k+4).getCell(17);
					cell18.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee().getApplicant()
										.getApp_RegDate().toString());		
				}
			}				
			inputStream.close();
			DateFormat date_format = new SimpleDateFormat("yyyyMMddHHmmss");
			Date current_date=new Date();
			String formattedDate=date_format.format(current_date);
			String fileName=fName+"_"+formattedDate+".xlsx";
		    String excelFileName = URLEncoder.encode(fileName, "UTF-8");
		  //フォルダを作成する 
		    @SuppressWarnings("static-access")
			Properties properties = commonUtility.getValue("config.properties");
		    String folderPath = properties.getProperty("ExportExcel");
		    CommonUtility.createFolder(folderPath, formattedDate);
		    
		    //Excelにデータを書き込む
		    File file = new File(folderPath+"/"+formattedDate+"/"+fileName);
		    FileOutputStream out = new FileOutputStream(file);
		    workbook.write(out);
		    out.close();

		    //Excelファイルをダウンロードする 
		    this.downloadFile(file, excelFileName);
			return true;
		} catch (IOException | EncryptedDocumentException
				| InvalidFormatException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	 /**
     * ...Excelファイルをダウンロードする...
     * 
     * @param fileToDownload
     * @param fName
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
	public void downloadFile(File fileToDownload, String fName) throws FileNotFoundException, UnsupportedEncodingException{
        inputStream = new FileInputStream(fileToDownload);
        fileName = fName;
    }	
			
	/**
	 * ...採用者情報を検索する...
	 * @param 
	 * @return 
	 * @throws SQLException
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	public void searchEmployee() throws SQLException, IOException {
		boolean s_exam_year = false;     //開始JobFair年
		boolean s_exam_place = false;    //開始受験場所
		boolean e_exam_year = false;     //終了JobFair年
		boolean e_exam_place = false;    //終了受験場所
		if (txtStartExamID != null && !txtStartExamID.trim().isEmpty()) {
			int s_exam_yr = Integer.parseInt(txtStartExamID.substring(2,6));
			if(s_exam_yr == ddlJobFairYear){
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
		                            && e_exam_place == true)){
						lstApplicantInfo = employeeInfoService.searchEmployee(ddlJobFairYear,regStartDate,regEndDate,
						           ddlExamPlace,txtStartExamID,txtEndExamID,ddlCompany);
		        	} else if (ddlExamPlace.trim().toUpperCase().equals("Mandalay".toUpperCase()) 
		        			&& s_exam_place == true 
		                    && (ddlExamPlace.trim().toUpperCase().equals("Mandalay".toUpperCase()) 
		                            && e_exam_place == true)){
		        		lstApplicantInfo = employeeInfoService.searchEmployee(ddlJobFairYear,regStartDate,regEndDate,
						           ddlExamPlace,txtStartExamID,txtEndExamID,ddlCompany);
		        	} else{
		        		lstApplicantInfo = new ArrayList<ApplicantInfo>();
		        	}
				} else {
					lstApplicantInfo = new ArrayList<ApplicantInfo>();
				}
			} else if (txtStartExamID != null && !txtStartExamID.trim().isEmpty()) {
				if (s_exam_year == true) {
					lstApplicantInfo = employeeInfoService.searchEmployee(ddlJobFairYear,regStartDate,regEndDate,
					           ddlExamPlace,txtStartExamID,txtEndExamID,ddlCompany);
				} else {
					lstApplicantInfo = new ArrayList<ApplicantInfo>();
				}
			} else if (txtEndExamID != null && !txtEndExamID.trim().isEmpty()) {
				if (e_exam_year == true) {
					lstApplicantInfo = employeeInfoService.searchEmployee(ddlJobFairYear,regStartDate,regEndDate,
					           ddlExamPlace,txtStartExamID,txtEndExamID,ddlCompany);
				} else {
					lstApplicantInfo = new ArrayList<ApplicantInfo>();
				}
			} else {
				lstApplicantInfo = employeeInfoService.searchEmployee(ddlJobFairYear,regStartDate,regEndDate,
				           ddlExamPlace,txtStartExamID,txtEndExamID,ddlCompany);
			}
		lstYear = commonService.selectJFYear();
		lstExamPlace = commonUtility.getExamPlace();
	}

	/**
	 * ...ユーザーが押したボタンをチェックする...
	 * @return employee
	 * @throws SQLException
	 * @throws IOException 
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("static-access")
	public String printEmployee() throws SQLException, IOException {
		properties= CommonUtility.getValue("Message.properties");
		lstYear = commonService.selectJFYear();
		lstExamPlace = commonUtility.getExamPlace();
		try {
			if (btn == null) {
				if (ddlJobFairYear != 0) {
					lstCompany = employeeInfoService.getCompany(ddlJobFairYear);
				} else {
					lstCompany = new ArrayList<Company>();
				}
				
			}else if (btn.equals("検索")) {
				lstCompany = employeeInfoService.getCompany(ddlJobFairYear);
				int count=employeeInfoService.getCountList(ddlJobFairYear,regStartDate,regEndDate,
				           ddlExamPlace,txtStartExamID,txtEndExamID,ddlCompany);
				if (count != 0){
					if (count > 50 ) {
						addActionError(properties.getProperty(Integer.toString(5)));
						lstApplicantInfo=new ArrayList<ApplicantInfo>();
					} else {
						searchEmployee();
						for (ApplicantInfo appInfo: lstApplicantInfo) {
							lstAge.add(CommonRegister.calculateAge(appInfo.getInterview().getExaminee().getApplicant().getApp_DOB()));			
						
					}
					}
				}else{
					addActionError(properties.getProperty(Integer.toString(19)));
				}
				
			} else if( btn.equals("キャンセル")){
				lstYear = commonService.selectJFYear();
				lstExamPlace=CommonUtility.getExamPlace();
				this.clearForm();
			}else if ( btn.equals("Excel出力")){
			    lstApplicantInfo = new ArrayList<ApplicantInfo>();
			    if (lstApplicantID.size() != 0) {
				lstApplicantInfo=employeeInfoService.getApplicantID(lstApplicantID);
			    }else {
					addActionError(properties.getProperty(Integer.toString(19)));
					return "error";
				}
			    if (lstApplicantID.size() != 0) {
			    	boolean isExport=this.printExcel(lstApplicantInfo);
			    	if(isExport == true) {
			    		return "exportExcel";
					} else {
			    	
			    	try{
						this.printExcel(lstApplicantInfo);
					}catch(Exception e){
						e.printStackTrace();
				}
					}
			    }else {
					this.searchEmployee();
					if (!lstApplicantInfo.isEmpty()) {
						if (lstApplicantInfo.size() > 1000 ) {
							addActionError(properties.getProperty(Integer.toString(5)));
							lstApplicantInfo=new ArrayList<ApplicantInfo>();
						} else {				
							for (ApplicantInfo appInfo: lstApplicantInfo) {
								lstAge.add(CommonRegister.calculateAge(appInfo.getApplicant().getApp_DOB()));		
							}
						}
						return "error";
					}
			    }
					}else {
			    	addActionError(properties.getProperty(Integer.toString(19)));
					lstApplicantInfo = new ArrayList<>();
					}
			}catch (Exception e) {
			e.printStackTrace();
		}
		return "employee";
	}

	/**
     * ...入力フィールドをクリアする...
	 * @throws IOException 
     */
	public void clearForm() throws IOException{
		setTxtStartExamID("");
		setTxtEndExamID("");
		setRegStartDate(null);
		setRegEndDate(null);
		setDdlExamPlace(null);
		setDdlJobFairYear(0);
	}
	
	//受験場所リスト
	public List<ExamPlace> getLstExamPlace() {
		return lstExamPlace;
	}
	public void setLstExamPlace(List<ExamPlace> lstExamPlace) {
		this.lstExamPlace = lstExamPlace;
	}
	//面接者オブジェクト
	public Interview getInterview() {
		return interview;
	}
	public void setInterview(Interview interview) {
		this.interview = interview;
	}
	
	//申請者情報のリスト
	public List<ApplicantInfo> getLstApplicantInfo() {
		return lstApplicantInfo;
	}
	public void setLstApplicantInfo(List<ApplicantInfo> lstApplicantInfo) {
		this.lstApplicantInfo = lstApplicantInfo;
	}
	
	//共通サービスオブジェクト
	public CommonService getCommonService() {
		return commonService;
	}
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	
	//JobFair年リスト
	public List<Integer> getLstYear() {
		return lstYear;
	}
	public void setLstYear(List<Integer> lstYear) {
		this.lstYear = lstYear;
	}
	
	//共通ユーティリティオブジェクト
	public CommonUtility getCommonUtility() {
		return commonUtility;
	}
	public void setCommonUtility(CommonUtility commonUtility) {
		this.commonUtility = commonUtility;
	}
	
	//jobFair年
	public int getDdlJobFairYear() {
		return ddlJobFairYear;
	}
	public void setDdlJobFairYear(int ddlJobFairYear) {
		this.ddlJobFairYear = ddlJobFairYear;
	}
	
	//受験場所
	public String getDdlExamPlace() {
		return ddlExamPlace;
	}
	public void setDdlExamPlace(String ddlExamPlace) {
		this.ddlExamPlace = ddlExamPlace;
	}
	
	//登録開始日
	public Date getRegStartDate() {
		return regStartDate;
	}
	public void setRegStartDate(Date regStartDate) {
		this.regStartDate = regStartDate;
	}
	
	//登録終了日
	public Date getRegEndDate() {
		return regEndDate;
	}
	public void setRegEndDate(Date regEndDate) {
		this.regEndDate = regEndDate;
	}
	
	//申請者IDリスト
	public List<Integer> getLstApplicantID() {
		return lstApplicantID;
	}
	public void setLstApplicantID(List<Integer> lstApplicantID) {
		this.lstApplicantID = lstApplicantID;
	}
	
	//年齢リスト
	public List<Integer> getLstAge() {
		return lstAge;
	}
	public void setLstAge(List<Integer> lstAge) {
		this.lstAge = lstAge;
	}
	
	//会社名
	public int getDdlCompany() {
		return ddlCompany;
	}
	public void setDdlCompany(int ddlCompany) {
		this.ddlCompany = ddlCompany;
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
	
	//採用者情報サービスオブジェクト
	public EmployeeInfoService getEmployeeInfoService() {
		return employeeInfoService;
	}
	public void setEmployeeInfoService(EmployeeInfoService employeeInfoService) {
		this.employeeInfoService = employeeInfoService;
	}
	
	//会社のリスト
	public List<Company> getLstCompany() {
		return lstCompany;
	}
	public void setLstCompany(List<Company> lstCompany) {
		this.lstCompany = lstCompany;
	}
	
	//申請者オブジェクト
	public Applicant getApplicant() {
		return applicant;
	}
	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	
	//会社オブジェクト
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	//受験者オブジェクト
	public Examinee getExaminee() {
		return examinee;
	}
	public void setExaminee(Examinee examinee) {
		this.examinee = examinee;
	}
	
	//申請者情報オブジェクト
	public ApplicantInfo getApplicantInfo() {
		return applicantInfo;
	}
	public void setApplicantInfo(ApplicantInfo applicantInfo) {
		this.applicantInfo = applicantInfo;
	}
	
	//ボタン
	public String getBtn() {
		return btn;
	}
	public void setBtn(String btn) {
		this.btn = btn;
	}

	//プロパティ	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	//ファイル名
	public String getFileName() {
		return fileName;
	}

	//入力ストリーム
	public InputStream getInputStream() {
		return inputStream;
	}

}