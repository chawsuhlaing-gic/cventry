/**
 * CV_A_033_会社別受験者情報照会画面			
 *	作成履歴：21/1/2019 Nwe Ni Hlaing			
 *	作成概要：新規作成　Excel出力処理			
 *				
 *	更新履歴：1/4/2019 Nwe Ni Hlaing	
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

import javax.persistence.criteria.SetJoin;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import mm.com.gic.ces.application.common.CommonRegister;
import mm.com.gic.ces.application.common.CommonUtility;
import mm.com.gic.ces.application.model.Applicant;
import mm.com.gic.ces.application.model.ApplicantInfo;
import mm.com.gic.ces.application.model.Company;
import mm.com.gic.ces.application.model.Examinee;
import mm.com.gic.ces.application.property.ExamPlace;
import mm.com.gic.ces.base.common.CommonService;
import mm.com.gic.ces.base.common.HibernateUtil;
import mm.com.gic.ces.base.service.admin.ExamineeInfoService;

/**
 *申請者の情報を出力する
 */
@SuppressWarnings({ "unused", "serial" })
public class ExamineeInfoAction extends ActionSupport implements Serializable{
	private static final String SUCCESS = null;
	public Applicant applicant = new Applicant();									//申請者オブジェクト
  	public Company company = new Company();											//会社オブジェクト
	public Examinee examinee = new Examinee();										//受験者オブジェクト
	public ApplicantInfo applicantInfo = new ApplicantInfo();						//申請者情報オブジェクト
	public CommonUtility commonUtility;												//共通ユーティリティオブジェクト
	private CommonService commonService = new CommonService();						//共通サービスオブジェクト
	private ExamineeInfoService examineeInfoService=new ExamineeInfoService();		//受験者情報サービスオブジェクト
	private List<ApplicantInfo> lstApplicantInfo = new ArrayList<ApplicantInfo>();	//申請者情報のリスト
	private List<Integer> lstApplicantID = new ArrayList<Integer>();				//申請者IDリスト
	private List<Integer> lstYear = new ArrayList<Integer>();						//JobFair年リスト
	private List<Integer> lstAge = new ArrayList<Integer>();						//年齢リスト
	private List<Applicant> lstApplicant = new ArrayList<Applicant>();				//申請者のリスト
	private List<Company> lstCompany = new ArrayList<Company>();					//会社のリスト
	private String btn;																//ボタン
	private int ddlJobFairYear;														//jobFair年
	private List<ExamPlace> lstExamPlace = new ArrayList<ExamPlace>();				//受験場所リスト
	private String ddlExamPlace;													//受験場所
	private Date regStartDate;														//登録開始日
	private Date regEndDate;														//登録終了日
	private int ddlFirstCompany;													//第一希望会社名
	private int ddlSecondCompany;													//第二希望会社名
	private String txtStartExamID;													//開始受験ID
	private String txtEndExamID;													//終了受験ID
	private Properties properties=new Properties();									//プロパティ
	private String fileName;                                                  		//ファイル名
	private InputStream inputStream;                                          		//入力ストリーム

	/**
	 * ...申請者情報を取る...
	 * @return examineeList 申請者リスト
	 * @throws SQLException
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	public String examineeList() throws IOException {
		lstYear = commonService.selectJFYear();
		lstExamPlace = commonUtility.getExamPlace();
	    Properties properties = commonUtility.getValue("config.properties");
	    String folderPath = properties.getProperty("ExportExcel");
	    CommonUtility.recursiveDelete(new File(folderPath));
		return "examineeList";
	}

	/**
	 * ...申請者情報を検索する...
	 * @param 
	 * @return 
	 * @throws SQLException
	 * @throws IOException 
	 */
	public void searchExaminee() throws SQLException, IOException {
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
						lstApplicantInfo = examineeInfoService.searchApplicant(ddlJobFairYear,regStartDate,
						           regEndDate,getDdlExamPlace(),txtStartExamID,txtEndExamID,ddlFirstCompany,ddlSecondCompany);
		        	} else if (ddlExamPlace.trim().toUpperCase().equals("Mandalay".toUpperCase()) 
		        			&& s_exam_place == true 
		                    && (ddlExamPlace.trim().toUpperCase().equals("Mandalay".toUpperCase()) 
		                            && e_exam_place == true)){
		        		lstApplicantInfo = examineeInfoService.searchApplicant(ddlJobFairYear,regStartDate,
						           regEndDate,getDdlExamPlace(),txtStartExamID,txtEndExamID,ddlFirstCompany,ddlSecondCompany);
		        	} else{
		        		lstApplicantInfo = new ArrayList<ApplicantInfo>();
		        	}
				} else {
					lstApplicantInfo = new ArrayList<ApplicantInfo>();
				}
			} else if (txtStartExamID != null && !txtStartExamID.trim().isEmpty()) {
				if (s_exam_year == true) {
					lstApplicantInfo = examineeInfoService.searchApplicant(ddlJobFairYear,regStartDate,
					           regEndDate,getDdlExamPlace(),txtStartExamID,txtEndExamID,ddlFirstCompany,ddlSecondCompany);
				} else {
					lstApplicantInfo = new ArrayList<ApplicantInfo>();
				}
			} else if (txtEndExamID != null && !txtEndExamID.trim().isEmpty()) {
				if (e_exam_year == true) {
					lstApplicantInfo = examineeInfoService.searchApplicant(ddlJobFairYear,regStartDate,
					           regEndDate,getDdlExamPlace(),txtStartExamID,txtEndExamID,ddlFirstCompany,ddlSecondCompany);
				} else {
					lstApplicantInfo = new ArrayList<ApplicantInfo>();
				}
			} else {
				lstApplicantInfo = examineeInfoService.searchApplicant(ddlJobFairYear,regStartDate,
				           regEndDate,getDdlExamPlace(),txtStartExamID,txtEndExamID,ddlFirstCompany,ddlSecondCompany);
			}
		lstYear = commonService.selectJFYear();
		lstExamPlace=CommonUtility.getExamPlace();
	}

	/**
	 * ...申請者の情報を出力する...
	 * @param lstApplicantInfo 申請者情報リスト
	 * @return 
	* @throws SQLException
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	public boolean printExcel(List<ApplicantInfo> lstApplicantInfo)throws SQLException, IOException {
		DateFormat format = new SimpleDateFormat("YYYY MMM dd");
		properties= CommonUtility.getValue("ExcelFileName.properties");		
		String fName =properties.getProperty(Integer.toString(4));
		String excelFilePath = ServletActionContext.getServletContext().getRealPath("/") + "file" + "/" + fName;
		try {
			FileInputStream inputStream = new FileInputStream(new File(excelFilePath+".xlsx"));
			Workbook workbook = WorkbookFactory.create(inputStream);
			Sheet sheet=workbook.getSheetAt(0);
			for (int k = 0; k < lstApplicantInfo.size(); k = k + 1) {
				if (k < sheet.getLastRowNum()) {			
					Cell cell1 = sheet.getRow(k+4).getCell(0);			
					cell1.setCellValue(lstApplicantInfo.get(k).getApplicant().getExam_ID());		
								
					Cell cell2 = sheet.getRow(k+4).getCell(1);								
					cell2.setCellValue(lstApplicantInfo.get(k).getApplicant().getApp_Name());			
								
					Cell cell3 = sheet.getRow(k+4).getCell(2);
								
					if(lstApplicantInfo.get(k).getExaminee() == null ){		   
						cell3.setCellValue("");
					}else{			
						cell3.setCellValue(lstApplicantInfo.get(k).getExaminee().getFirstCompany().getCom_Sname());
					}
								
					Cell cell4 = sheet.getRow(k+4).getCell(3);		
					if(lstApplicantInfo.get(k).getExaminee() == null ){
						cell4.setCellValue("");		
					}else{			
						cell4.setCellValue(lstApplicantInfo.get(k).getExaminee().getSecondCompany().getCom_Sname());
					}
								
					Cell cell5 = sheet.getRow(k+4).getCell(4);		
					if(lstApplicantInfo.get(k).getExaminee() == null){		
						cell5.setCellValue(0);	
					}else{			
						cell5.setCellValue(lstApplicantInfo.get(k).getExaminee().getExaminee_IQmark());		
					}			
								
					Cell cell6 = sheet.getRow(k+4).getCell(5);			
					if(lstApplicantInfo.get(k).getExaminee() == null){				
						cell6.setCellValue(0);	
					}else{			
						cell6.setCellValue(lstApplicantInfo.get(k).getExaminee().getExaminee_EQmark());		
					}		
								
					Cell cell7 = sheet.getRow(k+4).getCell(6);		
					cell7.setCellValue(lstApplicantInfo.get(k).getApplicant().getApp_Gender());
											
					Cell cell8 = sheet.getRow(k+4).getCell(7);		
					cell8.setCellValue(CommonRegister.calculateAge(lstApplicantInfo.get(k).getApplicant().getApp_DOB()));
								
								
					Cell cell9 = sheet.getRow(k+4).getCell(8);
					cell9.setCellValue(lstApplicantInfo.get(k).getApplicant().getApp_Nrc());	
								
					Cell cell10 = sheet.getRow(k+4).getCell(9);
					cell10.setCellValue(lstApplicantInfo.get(k).getApplicant().getApp_PhNo());
								
								
					Cell cell11 = sheet.getRow(k+4).getCell(10);
					cell11.setCellValue(lstApplicantInfo.get(k).getApplicant().getApp_Degree());
								
					Cell cell12 = sheet.getRow(k+4).getCell(11);
					cell12.setCellValue(lstApplicantInfo.get(k).getApplicant().getApp_University());
								
					Cell cell13 = sheet.getRow(k+4).getCell(12);
					cell13.setCellValue(lstApplicantInfo.get(k).getApplicant().getApp_CityofUniversity());
								
					Cell cell14 = sheet.getRow(k+4).getCell(13);
					cell14.setCellValue(lstApplicantInfo.get(k).getApplicant().getApp_Email());
								
					Cell cell15 = sheet.getRow(k+4).getCell(14);
					cell15.setCellValue(lstApplicantInfo.get(k).getApplicant().getApp_RegDate().toString());
				}
			}				
			inputStream.close();
			DateFormat date_format = new SimpleDateFormat("yyyyMMddHHmmss");
			Date current_date=new Date();
			String formattedDate=date_format.format(current_date);
			String fileName=fName+"_"+formattedDate+".xlsx";
		    String excelFileName = URLEncoder.encode(fileName, "UTF-8");
		    
		    //フォルダを作成する 
		    Properties properties = commonUtility.getValue("config.properties");
		    String folderPath = properties.getProperty("ExportExcel");
		    CommonUtility.createFolder(folderPath, formattedDate); 
	        
		    File f = new File(folderPath+"/"+formattedDate+"/"+fileName);
			FileOutputStream out = new FileOutputStream(f);
			workbook.write(out);
			out.close();
			downloadFile(f,excelFileName);
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
	 * ...ユーザーが押したボタンをチェックする...
	 * @return examinee
	 * @throws SQLException
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	public String printExaminee() throws SQLException, IOException {
		properties= CommonUtility.getValue("Message.properties");
		lstYear = commonService.selectJFYear();
		lstExamPlace = commonUtility.getExamPlace();
		try {
		if (btn == null) {
				if (ddlJobFairYear != 0) {
					lstCompany = examineeInfoService.getCompany(ddlJobFairYear);
				} else {
					lstCompany = new ArrayList<Company>();
				}
			} else if ( btn.equals("検索")) {
				lstCompany = examineeInfoService.getCompany(ddlJobFairYear);
				searchExaminee();
				
				
				if (!lstApplicantInfo.isEmpty()) {
					if (lstApplicantInfo.size() > 50 ) {
						addActionError(properties.getProperty(Integer.toString(5)));
						lstApplicantInfo=new ArrayList<ApplicantInfo>();
					} else {				
						for (ApplicantInfo appInfo: lstApplicantInfo) {
							lstAge.add(CommonRegister.calculateAge(appInfo.getApplicant().getApp_DOB()));		
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
			    	lstApplicantInfo=examineeInfoService.getApplicantID(lstApplicantID);
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
					this.searchExaminee();
					if (!lstApplicantInfo.isEmpty()) {
						if (lstApplicantInfo.size() > 50 ) {
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
		return "examinee";
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

		//年齢リスト
		public List<Integer> getLstAge() {
			return lstAge;
		}

		public void setLstAge(List<Integer> lstAge) {
			this.lstAge = lstAge;
		}

		//申請者IDリスト
		public List<Integer> getLstApplicantID() {
			return lstApplicantID;
		}

		public void setLstApplicantID(List<Integer> lstApplicantID) {
			this.lstApplicantID = lstApplicantID;
		}

		//申請者のリスト
		public List<Applicant> getLstApplicant() {
			return lstApplicant;
		}

		public void setLstApplicant(List<Applicant> lstApplicant) {
			this.lstApplicant = lstApplicant;
		}

		//受験者情報サービスオブジェクト
		public ExamineeInfoService getExamineeInfoService() {
			return examineeInfoService;
		}

		public void setExamineeInfoService(ExamineeInfoService examineeInfoService) {
			this.examineeInfoService = examineeInfoService;
		}

		//共通サービスオブジェクト
		public CommonService getCommonService() {
			return commonService;
		}

		public void setCommonService(CommonService commonService) {
			this.commonService = commonService;
		}

		//共通ユーティリティオブジェクト
		public CommonUtility getCommonUtility() {
			return commonUtility;
		}

		public void setCommonUtility(CommonUtility commonUtility) {
			this.commonUtility = commonUtility;
		}

		//申請者オブジェクト
		public Applicant getApplicant() {
			return applicant;
		}

		public void setApplicant(Applicant applicant) {
			this.applicant = applicant;
		}

		//ボタン
		public String getBtn() {
			return btn;
		}

		public void setBtn(String btn) {
			this.btn = btn;
		}

		//申請者情報オブジェクト
		public ApplicantInfo getApplicantInfo() {
			return applicantInfo;
		}

		public void setApplicantInfo(ApplicantInfo applicantInfo) {
			this.applicantInfo = applicantInfo;
		}

		//受験者オブジェクト
		public Examinee getExaminee() {
			return examinee;
		}
		
		public void setExaminee(Examinee examinee) {
			this.examinee = examinee;
		}

		//会社オブジェクト
		public Company getCompany() {
			return company;
		}

		public void setCompany(Company company) {
			this.company = company;
		}

		//会社のリスト
		public List<Company> getLstCompany() {
			return lstCompany;
		}

		public void setLstCompany(List<Company> lstCompany) {
			this.lstCompany = lstCompany;
		}

		//JobFair年リスト
		public List<Integer> getLstYear() {
			return lstYear;
		}

		public void setLstYear(List<Integer> lstYear) {
			this.lstYear = lstYear;
		}

		//申請者情報のリスト
		public List<ApplicantInfo> getLstApplicantInfo() {
			return lstApplicantInfo;
		}

		public void setLstApplicantInfo(List<ApplicantInfo> lstApplicantInfo) {
			this.lstApplicantInfo = lstApplicantInfo;
		}

		//JobFair年
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

		//第一希望会社名
		public int getDdlFirstCompany() {
			return ddlFirstCompany;
		}

		public void setDdlFirstCompany(int ddlFirstCompany) {
			this.ddlFirstCompany = ddlFirstCompany;
		}

		//第二希望会社名
		public int getDdlSecondCompany() {
			return ddlSecondCompany;
		}

		public void setDdlSecondCompany(int ddlSecondCompany) {
			this.ddlSecondCompany = ddlSecondCompany;
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