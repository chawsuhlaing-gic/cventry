/**
 * CV_A_053_会社別不採用者情報照会画面			

 *	作成履歴：21/2/2019 Nwe Ni Hlaing			
 *	作成概要：新規作成　Excel出力処理			
 *				
 *	更新履歴：10/4/2019 Nwe Ni Hlaing	
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
import mm.com.gic.ces.base.service.admin.UnemployeeInfoService;

/**
 *不採用者の情報を出力する
 */
@SuppressWarnings("serial")
public class UnemployeeInfoAction extends ActionSupport implements Serializable{
	@SuppressWarnings("unused")
	private static final String SUCCESS = null;
	public Applicant applicant = new Applicant();										//申請者オブジェクト
  	public Company company = new Company();												//会社オブジェクト
	public Examinee examinee = new Examinee();											//受験者オブジェクト
	public Interview interview = new Interview();										//面接者オブジェクト
	public ApplicantInfo applicantInfo = new ApplicantInfo();							//申請者情報オブジェクト
	public CommonUtility commonUtility;													//共通ユーティリティオブジェクト
	private CommonService commonService = new CommonService();							//共通サービスオブジェクト
	private UnemployeeInfoService unemployeeInfoService = new UnemployeeInfoService();	//不採用者情報サービスオブジェクト
	private List<ApplicantInfo> lstApplicantInfo = new ArrayList<ApplicantInfo>();		//申請者情報リスト
	private List<Integer> lstApplicantID = new ArrayList<Integer>();					//申請者IDリスト
	private List<Integer> lstYear = new ArrayList<Integer>();							//JobFair年リスト
	private List<Integer> lstAge = new ArrayList<Integer>();							//年齢リスト	
	private List<Company> lstCompany = new ArrayList<Company>();						//会社のリスト
	private List<ExamPlace> lstExamPlace = new ArrayList<ExamPlace>();					//受験場所リスト
	private String btn;																	//ボタン
	private int ddlJobFairYear;															//jobFair年
	private String ddlExamPlace;														//受験場所
	private Date regStartDate;															//登録開始日
	private Date regEndDate;															//登録終了日
	private String txtStartExamID;														//開始受験ID
	private String txtEndExamID;														//終了受験ID
	private Properties properties=new Properties();										////プロパティ
	private String fileName;                                                  			//ファイル名
	private InputStream inputStream;                                          			//入力ストリーム

	/**
	 * ...不採用者情報を取る...
	 * @return employeeList 不採用者リスト
	 * @throws SQLException
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	public String unemployeeList() throws IOException {
		lstYear = commonService.selectJFYear();
		lstExamPlace = commonUtility.getExamPlace();
		Properties properties = commonUtility.getValue("config.properties");
		String folderPath = properties.getProperty("ExportExcel");
		CommonUtility.recursiveDelete(new File(folderPath));
		return "unemployeeList";
	}

	/**
	 * ...不採用者の情報を出力する...
	 * @param lstApplicantInfo 申請者情報リスト
	 * @return 
	* @throws SQLException
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	public boolean printExcel(List<ApplicantInfo> lstApplicantInfo)throws SQLException, IOException {
		@SuppressWarnings("unused")
		DateFormat format = new SimpleDateFormat("YYYY MMM dd");
		properties= CommonUtility.getValue("ExcelFileName.properties");		
		String fName =properties.getProperty(Integer.toString(7));
		String excelFilePath = ServletActionContext.getServletContext().getRealPath("/") + "file" + "/" + fName;
		try {
			FileInputStream inputStream = new FileInputStream(new File(excelFilePath+".xlsx"));
			Workbook workbook = WorkbookFactory.create(inputStream);
			Sheet sheet=workbook.getSheetAt(0);
			for (int k = 0; k < lstApplicantInfo.size(); k = k + 1) {
				if (k < sheet.getLastRowNum()) {
					Cell cell1 = sheet.getRow(k+4).getCell(0);
					cell1.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee().getApplicant()
										.getApp_Check());
								
					Cell cell2 = sheet.getRow(k+4).getCell(1);
					cell2.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee()
										.getExaminee_Pass());
								
					Cell cell3 = sheet.getRow(k+4).getCell(2);
					cell3.setCellValue(lstApplicantInfo.get(k).getInterview()
										.getInterview_Pass());
								
					Cell cell4 = sheet.getRow(k+4).getCell(3);
					cell4.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee().getApplicant()
										.getExam_ID());
								
					Cell cell5 = sheet.getRow(k+4).getCell(4);
					cell5.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee().getApplicant()
										.getApp_Name());
								
					Cell cell6 = sheet.getRow(k+4).getCell(5);
					cell6.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee()
										.getFirstCompany().getCom_Sname());

					Cell cell7 = sheet.getRow(k+4).getCell(6);
					cell7.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee()
										.getSecondCompany().getCom_Sname());
								
					Cell cell8 = sheet.getRow(k+4).getCell(7);
					cell8.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee()
										.getExaminee_IQmark());
								
					Cell cell9 = sheet.getRow(k+4).getCell(8);
					cell9.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee()
										.getExaminee_EQmark());
								
					Cell cell10 = sheet.getRow(k+4).getCell(9);
								cell10.setCellValue(lstApplicantInfo.get(k).getInterview()
										.getInterview_Date());
								
					Cell cell11 = sheet.getRow(k+4).getCell(10);
					String time=lstApplicantInfo.get(k).getInterview()
								.getInterview_StartTime()+"~"+lstApplicantInfo.get(k).getInterview()
								.getInterview_EndTime();
					cell11.setCellValue(time);
								
					Cell cell12 = sheet.getRow(k+4).getCell(11);
					cell12.setCellValue(lstApplicantInfo.get(k).getInterview()
										.getInterview_Place());
								
					Cell cell13 = sheet.getRow(k+4).getCell(12);
					cell13.setCellValue(lstApplicantInfo.get(k).getInterview()
										.getInterviewer());
								
					Cell cell14 = sheet.getRow(k+4).getCell(13);
					cell14.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee().getApplicant()
										.getApp_Gender());
								
					Cell cell15 = sheet.getRow(k+4).getCell(14);
					cell15.setCellValue(CommonRegister.calculateAge(lstApplicantInfo.get(k).getInterview().getExaminee().getApplicant()
										.getApp_DOB()));
								
					Cell cell16 = sheet.getRow(k+4).getCell(15);
					cell16.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee().getApplicant()
										.getApp_Nrc());
								
					Cell cell17 = sheet.getRow(k+4).getCell(16);
					cell17.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee().getApplicant()
										.getApp_PhNo());
								
					Cell cell18 = sheet.getRow(k+4).getCell(17);
					cell18.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee().getApplicant()
										.getApp_Degree());
								
					Cell cell19 = sheet.getRow(k+4).getCell(18);
					cell19.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee().getApplicant()
										.getApp_University());
								
					Cell cell20 = sheet.getRow(k+4).getCell(19);
					cell20.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee().getApplicant()
										.getApp_CityofUniversity());
								
					Cell cell21 = sheet.getRow(k+4).getCell(20);
					cell21.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee().getApplicant()
										.getApp_Email());
								
					Cell cell22 = sheet.getRow(k+4).getCell(21);
					cell22.setCellValue(lstApplicantInfo.get(k).getInterview().getExaminee().getApplicant()
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
	 * ...不採用者情報を検索する...
	 * @param 
	 * @return 
	 * @throws SQLException
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	public void searchUnemployee() throws SQLException, IOException {
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
						lstApplicantInfo = unemployeeInfoService.searchUnemployee(ddlJobFairYear,regStartDate,
						           regEndDate,getDdlExamPlace(),txtStartExamID,txtEndExamID);
		        	} else if (ddlExamPlace.trim().toUpperCase().equals("Mandalay".toUpperCase()) 
		        			&& s_exam_place == true 
		                    && (ddlExamPlace.trim().toUpperCase().equals("Mandalay".toUpperCase()) 
		                            && e_exam_place == true)){
		        		lstApplicantInfo = unemployeeInfoService.searchUnemployee(ddlJobFairYear,regStartDate,
		     		           regEndDate,getDdlExamPlace(),txtStartExamID,txtEndExamID);
		        	} else{
		        		lstApplicantInfo = new ArrayList<ApplicantInfo>();
		        	}
				} else {
					lstApplicantInfo = new ArrayList<ApplicantInfo>();
				}
			} else if (txtStartExamID != null && !txtStartExamID.trim().isEmpty()) {
				if (s_exam_year == true) {
					lstApplicantInfo = unemployeeInfoService.searchUnemployee(ddlJobFairYear,regStartDate,
					           regEndDate,getDdlExamPlace(),txtStartExamID,txtEndExamID);
				} else {
					lstApplicantInfo = new ArrayList<ApplicantInfo>();
				}
			} else if (txtEndExamID != null && !txtEndExamID.trim().isEmpty()) {
				if (e_exam_year == true) {
					lstApplicantInfo = unemployeeInfoService.searchUnemployee(ddlJobFairYear,regStartDate,
					           regEndDate,getDdlExamPlace(),txtStartExamID,txtEndExamID);
				} else {
					lstApplicantInfo = new ArrayList<ApplicantInfo>();
				}
			} else {
				lstApplicantInfo = unemployeeInfoService.searchUnemployee(ddlJobFairYear,regStartDate,
				           regEndDate,getDdlExamPlace(),txtStartExamID,txtEndExamID);
			}
		lstYear = commonService.selectJFYear();
		lstExamPlace = commonUtility.getExamPlace();
	}

	/**
	 * ...ユーザーが押したボタンをチェックする...
	 * @return unemployee
	 * @throws SQLException
	 * @throws IOException 
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("static-access")
	public String printUnemployee() throws SQLException, IOException {
		properties= CommonUtility.getValue("Message.properties");
		lstYear = commonService.selectJFYear();
		lstExamPlace = commonUtility.getExamPlace();
		try {
			if (btn == null) {
				if (ddlJobFairYear != 0) {
					lstCompany = unemployeeInfoService.getCompany(ddlJobFairYear);
				} else {
					lstCompany = new ArrayList<Company>();
				}
				
			}else if (btn.equals("検索")) {
				lstCompany = unemployeeInfoService.getCompany(ddlJobFairYear);
				int count=unemployeeInfoService.getCountList(ddlJobFairYear,regStartDate,
				           regEndDate,getDdlExamPlace(),txtStartExamID,txtEndExamID);
				if (count != 0){
					if (count > 50 ) {
						addActionError(properties.getProperty(Integer.toString(5)));
						lstApplicantInfo=new ArrayList<ApplicantInfo>();
					} else {
						searchUnemployee();
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
				    	lstApplicantInfo=unemployeeInfoService.getApplicantID(lstApplicantID);
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
						this.searchUnemployee();
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
			return "unemployee";
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
	
	//共通ユーティリティオブジェクト
	public CommonUtility getCommonUtility() {
		return commonUtility;
	}
	public List<ExamPlace> getLstExamPlace() {
		return lstExamPlace;
	}
	
	//受験場所リスト
	public void setLstExamPlace(List<ExamPlace> lstExamPlace) {
		this.lstExamPlace = lstExamPlace;
	}
	public void setCommonUtility(CommonUtility commonUtility) {
		this.commonUtility = commonUtility;
	}
	
	//共通サービスオブジェクト
	public CommonService getCommonService() {
		return commonService;
	}
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	
	//申請者情報リスト
	public List<ApplicantInfo> getLstApplicantInfo() {
		return lstApplicantInfo;
	}
	public void setLstApplicantInfo(List<ApplicantInfo> lstApplicantInfo) {
		this.lstApplicantInfo = lstApplicantInfo;
	}
	
	//申請者IDリスト
	public List<Integer> getLstApplicantID() {
		return lstApplicantID;
	}
	public void setLstApplicantID(List<Integer> lstApplicantID) {
		this.lstApplicantID = lstApplicantID;
	}
	
	//JobFair年リスト
	public List<Integer> getLstYear() {
		return lstYear;
	}
	public void setLstYear(List<Integer> lstYear) {
		this.lstYear = lstYear;
	}
	
	//年齢リスト
	public List<Integer> getLstAge() {
		return lstAge;
	}
	public void setLstAge(List<Integer> lstAge) {
		this.lstAge = lstAge;
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
	
	//不採用者情報サービスオブジェクト
	public UnemployeeInfoService getUnemployeeInfoService() {
		return unemployeeInfoService;
	}
	public void setUnemployeeInfoService(UnemployeeInfoService unemployeeInfoService) {
		this.unemployeeInfoService = unemployeeInfoService;
	}
	
	//面接者オブジェクト
	public Interview getInterview() {
		return interview;
	}
	public void setInterview(Interview interview) {
		this.interview = interview;
	}
	
	//会社リスト
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