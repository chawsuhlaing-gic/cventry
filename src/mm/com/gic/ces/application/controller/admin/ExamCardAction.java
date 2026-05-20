/**
 * CV_A_013_å�—é¨“ç•ªå�·å‡ºåŠ›ç”»é�¢
 * ä½œæˆ�å±¥æ­´ï¼š01/04/2019 Cho Cho Lwin
 * ä½œæˆ�æ¦‚è¦�ï¼šæ–°è¦�ä½œæˆ�ã€€å�—é¨“ç•ªå�·å‡ºåŠ›å‡¦ç�†
 * 
 * æ›´æ–°å±¥æ­´ï¼š03/05/2019 Cho Cho Lwin
 * æ›´æ–°æ¦‚è¦�ï¼šãƒ¡ãƒƒã‚»ãƒ¼ã‚¸ã‚„Excelãƒ•ã‚¡ã‚¤ãƒ«å��ã�ªã�©ã�®æ›¸ã��æ–¹ã‚’ä¿®æ­£ã�™ã‚‹
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mm.com.gic.ces.application.common.CommonRegister;
import mm.com.gic.ces.application.common.CommonUtility;
import mm.com.gic.ces.application.model.Applicant;
import mm.com.gic.ces.application.model.Pagination;
import mm.com.gic.ces.application.property.ExamPlace;
import mm.com.gic.ces.base.common.CommonService;
import mm.com.gic.ces.base.service.admin.ExamCardService;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 *ç”³è«‹è€…ã�®å�—é¨“ç•ªå�·ã‚’å‡ºåŠ›ã�™ã‚‹
 */
public class ExamCardAction extends ActionSupport implements Serializable {

	private static final long serialVersionUID = 1L;
	HttpServletRequest request = ServletActionContext.getRequest();           //ã‚µãƒ¼ãƒ–ãƒ¬ãƒƒãƒˆãƒªã‚¯ã‚¨ã‚¹ãƒˆ
	HttpSession httpSession = request.getSession();                           //ã‚µãƒ¼ãƒ–ãƒ¬ãƒƒãƒˆãƒªã‚¯ã‚¨ã‚¹ãƒˆã‚»ãƒƒã‚·ãƒ§ãƒ³
    public CommonService comService = new CommonService();                    //å…±é€šã‚µãƒ¼ãƒ“ã‚¹ã‚ªãƒ–ã‚¸ã‚§ã‚¯ãƒˆ
	public ExamCardService examCardServices = new ExamCardService();          //å�—é¨“ç•ªå�·ã‚µãƒ¼ãƒ“ã‚¹ã‚ªãƒ–ã‚¸ã‚§ã‚¯ãƒˆ
	private List<Applicant> lstApplicant = new ArrayList<Applicant>();        //ç”³è«‹è€…ã�®ãƒªã‚¹ãƒˆ
	private List<Integer> lstYear = new ArrayList<Integer>();                 //JobFairå¹´ãƒªã‚¹ãƒˆ
	private List<Integer> lstApplicantID = new ArrayList<Integer>();          //ç”³è«‹è€…IDãƒªã‚¹ãƒˆ
	private List<Integer> lstAge = new ArrayList<Integer>();                  //å¹´é½¢ãƒªã‚¹ãƒˆ
	private CommonUtility commonUtility = new CommonUtility();                  //å…±é€šã‚µãƒ¼ãƒ“ã‚¹ã‚ªãƒ–ã‚¸ã‚§ã‚¯ãƒˆ
    private List<ExamPlace> lstExamPlace = new ArrayList<ExamPlace>();          //å�—é¨“å ´æ‰€ãƒªã‚¹ãƒˆ
	@SuppressWarnings("rawtypes")
	private Pagination pagination = new Pagination();                         //ãƒšãƒ¼ã‚¸ãƒ�ãƒ¼ã‚·ãƒ§ãƒ³ã‚ªãƒ–ã‚¸ã‚§ã‚¯ãƒˆ
	private Date regSDate;                                                    //ç™»éŒ²é–‹å§‹æ—¥
	private Date regEDate;                                                    //ç™»éŒ²çµ‚äº†æ—¥
	private String ddlExamPlace;                                              //å�—é¨“å ´æ‰€
	private int ddlJobFairYear;                                               //JobFairå¹´
	private String txtStartExamID;                                            //é–‹å§‹å�—é¨“ID
	private String txtEndExamID;                                              //çµ‚äº†å�—é¨“ID
	private String btn;                                                       //ãƒœã‚¿ãƒ³
	private String fileName;                                                  //ãƒ•ã‚¡ã‚¤ãƒ«å��
	private InputStream inputStream;                                          //å…¥åŠ›ã‚¹ãƒˆãƒªãƒ¼ãƒ 

    /**
	 * ä½œæˆ�å±¥æ­´ï¼š01/04/2019 Cho Cho Lwin
     * ä½œæˆ�æ¦‚è¦�ï¼šæ–°è¦�ä½œæˆ�
	 *
	 * ...ç”³è«‹è€…æƒ…å ±ã‚’å�–ã‚‹...
	 * @return SUCCESS
	 * @throws SQLException
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	public String getApplicantList() throws SQLException, IOException {
		lstYear = comService.selectJFYear();
		lstExamPlace = CommonUtility.getExamPlace();
		//ãƒ•ã‚©ãƒ«ãƒ€ã‚’å‰Šé™¤ã�™ã‚‹
	    Properties properties = commonUtility.getValue("config.properties");
	    String folderPath = properties.getProperty("ExportExamCard");
	    CommonUtility.recursiveDelete(new File(folderPath));
		return SUCCESS;
	}

	/**
	 * ä½œæˆ�å±¥æ­´ï¼š01/04/2019 Cho Cho Lwin
     * ä½œæˆ�æ¦‚è¦�ï¼šæ–°è¦�ä½œæˆ�
     * 
     * æ›´æ–°å±¥æ­´ï¼š03/05/2019 Cho Cho Lwin
     *    æ›´æ–°æ¦‚è¦�ï¼šãƒ¡ãƒƒã‚»ãƒ¼ã‚¸ã‚’ä¿®æ­£ã�™ã‚‹
	 *
	 * ...ãƒœã‚¿ãƒ³å��ã‚ˆã�£ã�¦è©²å½“å‡¦ç�†ã‚’è¡Œã�†...
	 * @return SUCCESS
	 * @throws SQLException
	 * @throws IOException 
	 */
	public String printExcel() throws SQLException, IOException {
		@SuppressWarnings("static-access")
	    Properties prop = commonUtility.getValue("Message.properties");
		lstYear = comService.selectJFYear();
		lstExamPlace = CommonUtility.getExamPlace();
		if (getBtn() != null) {
			if (getBtn().equals("ExportExamCard")) {
				lstApplicant = new ArrayList<Applicant>();
				if (lstApplicantID.size() != 0) {
					lstApplicant = comService.excelFile(lstApplicantID);
				} else {
					addActionError(prop.getProperty(Integer.toString(19)));
					return "error";
				}
				if (lstApplicant.size() != 0) {
					boolean isExport = this.printExamIDCard(lstApplicant, lstApplicant.get(0).getApp_ExamPlace().trim());
					if(isExport == true) {
						return "exportExcel";
					} else {
						lstApplicant = comService.getApplicantList(getDdlJobFairYear(), getDdlExamPlace(), getRegSDate(),
    							getRegEDate(), getTxtStartExamID(), getTxtEndExamID(), 13);
						lstAge = addAgeIntoList(lstApplicant);
						addActionError("JobFair場所とJobFair日"+prop.getProperty(Integer.toString(31)));
						return "error";
					}
				} else {
					addActionError(prop.getProperty(Integer.toString(19)));
					return "error";
				}
			} else if (getBtn().equals("Search")) {
				int appCount = commonUtility.getAppCount(getTxtStartExamID(), getTxtEndExamID(), getRegSDate(), 
						getRegEDate(), getDdlExamPlace(), getDdlJobFairYear(), 13);
                if(appCount != 0) {
                	if(appCount <= 50) {
    					lstApplicant = comService.getApplicantList(getDdlJobFairYear(), getDdlExamPlace(), getRegSDate(),
    							getRegEDate(), getTxtStartExamID(), getTxtEndExamID(), 13);
    					lstAge = addAgeIntoList(lstApplicant);
    		            setTxtStartExamID(getTxtStartExamID());

    		            //ã‚»ãƒƒã‚·ãƒ§ãƒ³ã�«ç”»é�¢ã�®ãƒ‡ãƒ¼ã‚¿ã‚’è¨­å®šã�™ã‚‹
    		            httpSession.setAttribute("jobFairYear", getDdlJobFairYear());
    		            httpSession.setAttribute("examPlace", getDdlExamPlace());
    		            httpSession.setAttribute("regSDate", getRegSDate());
    		            httpSession.setAttribute("regEDate", getRegEDate());
    		            httpSession.setAttribute("sExamID", getTxtStartExamID());
    		            httpSession.setAttribute("eExamID", getTxtEndExamID());
    		            return SUCCESS;
    				} else {
    					addActionError(prop.getProperty(Integer.toString(5)));
    	            	lstApplicant = new ArrayList<Applicant> ();
    	                return "error";
    				}
                } else {
	                addActionError(prop.getProperty(Integer.toString(19)));
	                return "error";
	            }
			} else if (getBtn().equals("Cancel")) {
			    this.clearForm();
			    return SUCCESS;
			}
			return SUCCESS;
		} else {
			addActionError(prop.getProperty(Integer.toString(26)));
			return "error";
		}
	}

   /**
    * ä½œæˆ�å±¥æ­´ï¼š14/05/2019 Cho Cho Lwin
    * ä½œæˆ�æ¦‚è¦�ï¼šæ–°è¦�ä½œæˆ�
    * 
    * ...ç”³è«‹è€…ã�®å¹´é½¢ã‚’ãƒªã‚¹ãƒˆã�«è¿½åŠ ã�™ã‚‹...
    * @param lstApplicant
    * @return
    */
	public List<Integer> addAgeIntoList(List<Applicant> lstApplicant) {
		List<Integer> lstAge = new ArrayList<Integer> ();
		if (lstApplicant != null){
			for (Applicant app: lstApplicant){
				lstAge.add(CommonRegister.calculateAge(app.getApp_DOB()));
			}
			return lstAge;
		}
		else {
			return new ArrayList<Integer>();
		}
	}

	/**
	 * ä½œæˆ�å±¥æ­´ï¼š04/04/2019 Cho Cho Lwin
     * ä½œæˆ�æ¦‚è¦�ï¼šæ–°è¦�ä½œæˆ�
     * 
     * æ›´æ–°å±¥æ­´ï¼š03/05/2019 Cho Cho Lwin
     * æ›´æ–°æ¦‚è¦�ï¼šExcelãƒ•ã‚¡ã‚¤ãƒ«å��ã�®æ›¸ã��æ–¹ã‚’ä¿®æ­£ã�™ã‚‹
	 *
	 * ...ç”³è«‹è€…ã�®å�—é¨“ç•ªå�·ã‚’å‡ºåŠ›ã�™ã‚‹...
	 * @param lstApplicant ç”³è«‹è€…ãƒªã‚¹ãƒˆ
	 * @param jobFairPlace JobFairå ´æ‰€
	 * @throws SQLException
	 * @throws IllegalStateException
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	public boolean printExamIDCard(List<Applicant> lstApplicant, String jobFairPlace) throws SQLException, 
			IllegalStateException, IOException {
		String fName;             //ãƒ•ã‚¡ã‚¤ãƒ«å��
		String excelFName;        //Excelãƒ•ã‚¡ã‚¤ãƒ«å��
		String jfPlace = comService.getJobFairPlace(jobFairPlace);
		String jfDate = comService.getJobFairDate(jobFairPlace);
		if (!jfPlace.equals("") && !jfDate.equals("")) {
	        Properties prop = commonUtility.getValue("ExcelFileName.properties");
	        Properties property = commonUtility.getValue("PaginationRecord.properties");
		    if (lstApplicant.get(0).getApp_ExamPlace().trim().toUpperCase().equals("YANGON")) {
			    fName = prop.getProperty(Integer.toString(3));
		    } else {
			    fName = prop.getProperty(Integer.toString(2));
		    }
		    excelFName = fName + ".xlsx";
		    String excelFilePath = ServletActionContext.getServletContext()
				    .getRealPath("/") + "file" + "/" + excelFName;
		    httpSession.setAttribute("applicantCount", 0);
		    httpSession.setAttribute("rowCount", 0);
		    try {
			    FileInputStream inputStream = new FileInputStream(new File(
					    excelFilePath));
			    Workbook workbook = WorkbookFactory.create(inputStream);
			    int totalAppCount = 0;    //ç”³è«‹è€…æ•°
			    
			    //Excelã‚·ãƒ¼ãƒˆã‚’å‰Šé™¤é–‹å§‹
			    double uncutSheetCount = 0;
			    int totalSheetCount = 0;
			    int sheetCount = workbook.getNumberOfSheets();
			    int appCount = lstApplicant.size();
			    int noOfAppPerSheet = Integer.parseInt(property.getProperty(Integer.toString(1)));
			    if((appCount % noOfAppPerSheet) == 0) {
			    	uncutSheetCount = (appCount / noOfAppPerSheet);
		        } else{
		    	    uncutSheetCount = (appCount / noOfAppPerSheet)+1;
		        }
			    totalSheetCount = (int)uncutSheetCount;
			    for(int i = sheetCount; i > totalSheetCount; i-- ) {
			    	workbook.removeSheetAt(totalSheetCount);
			    }
			    //Excelã‚·ãƒ¼ãƒˆã‚’å‰Šé™¤çµ‚äº†
			    
			    for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				    int applicantCount = 0;
				    if (httpSession.getAttribute("applicantCount") == null) {
					    applicantCount = 0;
				    } else {
					    applicantCount = Integer.parseInt(httpSession.getAttribute(
							    "applicantCount").toString());
				    }
				    int count = 0;
				    for (int j = applicantCount; j < lstApplicant.size(); j++) {
				    	count++;
					    if (count <= Integer.parseInt(property.getProperty(Integer.toString(1)))) {
					    	totalAppCount++;
					    	Sheet sheet = workbook.getSheetAt(i);
						    int rowCount = 0;
						    if (httpSession.getAttribute("rowCount") == null) {
							    rowCount = 0;
						    } else {
							    rowCount = Integer.parseInt(httpSession
									    .getAttribute("rowCount").toString());
						    }
						    for (int k = rowCount; k <= rowCount; k = k + 13) {
							    if (k < sheet.getLastRowNum()) {
								    Cell cell2Update = sheet.getRow(k).getCell(5);
								    cell2Update.setCellValue(lstApplicant.get(j).getExam_ID());

								    Cell cell3Update = sheet.getRow(++k).getCell(1);
								    cell3Update.setCellValue(lstApplicant.get(j)
										    .getApp_Name().toUpperCase());

								    Cell cell4Update = sheet.getRow(k).getCell(4);
								    cell4Update.setCellValue(lstApplicant.get(j)
										   .getApp_Nrc().toUpperCase());
								
								    Cell cell5Update = sheet.getRow(3 + k).getCell(0);
								    cell5Update.setCellValue(jfPlace);

								    Cell cell6Update = sheet.getRow(5 + k).getCell(0);
								    cell6Update.setCellValue("  Time of Examination : "
												    + jfDate);
                                
								    httpSession.setAttribute("rowCount", k + 13);
							    }
						    }
					    } else {
						    httpSession.setAttribute("rowCount", 0);
						    break;
					    }
					    httpSession.setAttribute("applicantCount", j + 1);
				    }
				    if (totalAppCount == lstApplicant.size()) {
					    break;
				    }
			    }
			    inputStream.close();
			    //ãƒ‡ãƒ¼ãƒˆãƒ•ã‚©ãƒ¼ãƒžãƒƒãƒˆã‚’å¤‰æ›´ã�™ã‚‹
			    DateFormat date_format = new SimpleDateFormat("yyyyMMddHHmmss");
			    Date current_date = new Date();
			    String formattedDate = date_format.format(current_date);
			    String fileName = fName + "_" + formattedDate + ".xlsx";
			    String excelFileName = URLEncoder.encode(fileName, "UTF-8");

			    //ãƒ•ã‚©ãƒ«ãƒ€ã‚’ä½œæˆ�ã�™ã‚‹ 
			    Properties properties = commonUtility.getValue("config.properties");
			    String folderPath = properties.getProperty("ExportExamCard");
			    CommonUtility.createFolder(folderPath, formattedDate);
			    
			    //Excelã�«ãƒ‡ãƒ¼ã‚¿ã‚’æ›¸ã��è¾¼ã‚€
			    File file = new File(folderPath+"/"+formattedDate+"/"+fileName);
			    FileOutputStream out = new FileOutputStream(file);
			    workbook.write(out);
			    out.close();

			    //Excelãƒ•ã‚¡ã‚¤ãƒ«ã‚’ãƒ€ã‚¦ãƒ³ãƒ­ãƒ¼ãƒ‰ã�™ã‚‹ 
			    this.downloadFile(file, excelFileName);

			    return true;
		    } catch (IOException | EncryptedDocumentException
				   | InvalidFormatException ex) {
			    ex.printStackTrace();
			    return false;
		    }
		} else {
			return false;
		}
	}

    /**
     * ä½œæˆ�å±¥æ­´ï¼š09/05/2019 Cho Cho Lwin
     * ä½œæˆ�æ¦‚è¦�ï¼šæ–°è¦�ä½œæˆ�
     * 
     * ...Excelãƒ•ã‚¡ã‚¤ãƒ«ã‚’ãƒ€ã‚¦ãƒ³ãƒ­ãƒ¼ãƒ‰ã�™ã‚‹...
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
	 * ä½œæˆ�å±¥æ­´ï¼š03/04/2019 Cho Cho Lwin
     * ä½œæˆ�æ¦‚è¦�ï¼šæ–°è¦�ä½œæˆ�
	 *
     * ...å…¥åŠ›ãƒ•ã‚£ãƒ¼ãƒ«ãƒ‰ã‚’ã‚¯ãƒªã‚¢ã�™ã‚‹...
     */
	public void clearForm() {
		setTxtStartExamID("");
		setTxtEndExamID("");
		setRegSDate(null);
		setRegEDate(null);
		setDdlExamPlace(null);
		setDdlJobFairYear(0);
	}

    //ãƒ•ã‚¡ã‚¤ãƒ«å��
	public String getFileName() {
        return fileName;
    }
	
	//å…¥åŠ›ã‚¹ãƒˆãƒªãƒ¼ãƒ 
	public InputStream getInputStream() {
        return inputStream;
    }
	
    //å�—é¨“å ´æ‰€ãƒªã‚¹ãƒˆ
    public List<ExamPlace> getLstExamPlace() {
		return lstExamPlace;
	}

	public void setLstExamPlace(List<ExamPlace> lstExamPlace) {
		this.lstExamPlace = lstExamPlace;
	}

	//å¹´é½¢ãƒªã‚¹ãƒˆ
	public List<Integer> getLstAge() {
		return lstAge;
	}

	public void setLstAge(List<Integer> lstAge) {
		this.lstAge = lstAge;
	}

    //å�—é¨“å ´æ‰€
	public String getDdlExamPlace() {
		return ddlExamPlace;
	}

	public void setDdlExamPlace(String ddlExamPlace) {
		this.ddlExamPlace = ddlExamPlace;
	}

    //JobFairå¹´
	public int getDdlJobFairYear() {
		return ddlJobFairYear;
	}

	public void setDdlJobFairYear(int ddlJobFairYear) {
		this.ddlJobFairYear = ddlJobFairYear;
	}

    //ç”³è«‹è€…IDãƒªã‚¹ãƒˆ
	public List<Integer> getLstApplicantID() {
		return lstApplicantID;
	}

	public void setLstApplicantID(List<Integer> lstApplicantID) {
		this.lstApplicantID = lstApplicantID;
	}

    //çµ‚äº†å�—é¨“ID
	public String getTxtEndExamID() {
		return txtEndExamID;
	}

	public void setTxtEndExamID(String txtEndExamID) {
		this.txtEndExamID = txtEndExamID;
	}

    //é–‹å§‹å�—é¨“ID
	public String getTxtStartExamID() {
		return txtStartExamID;
	}

	public void setTxtStartExamID(String txtStartExamID) {
		this.txtStartExamID = txtStartExamID;
	}

    //ãƒšãƒ¼ã‚¸ãƒ�ãƒ¼ã‚·ãƒ§ãƒ³ã‚ªãƒ–ã‚¸ã‚§ã‚¯ãƒˆ
	@SuppressWarnings("rawtypes")
	public Pagination getPagination() {
		return pagination;
	}

	@SuppressWarnings("rawtypes")
	public void setPagination(Pagination pagination) {
		pagination.setSelectedPageNumber(Integer.parseInt(getBtn()));
		this.pagination = pagination;
	}

    //ãƒœã‚¿ãƒ³
	public String getBtn() {
		return btn;
	}

	public void setBtn(String btn) {
		this.btn = btn;
	}
    
    //ç™»éŒ²é–‹å§‹æ—¥
	public Date getRegSDate() {
		return regSDate;
	}

	public void setRegSDate(Date regSDate) {
		this.regSDate = regSDate;
	}

    //ç™»éŒ²çµ‚äº†æ—¥
	public Date getRegEDate() {
		return regEDate;
	}

	public void setRegEDate(Date regEDate) {
		this.regEDate = regEDate;
	}

    //JobFairå¹´ãƒªã‚¹ãƒˆ
	public List<Integer> getLstYear() {
		return lstYear;
	}

	public void setLstYear(List<Integer> lstYear) {
		this.lstYear = lstYear;
	}

    //ç”³è«‹è€…ãƒªã‚¹ãƒˆ
	public List<Applicant> getLstApplicant() {
		return lstApplicant;
	}

	public void setLstApplicant(List<Applicant> lstApplicant) {
		this.lstApplicant = lstApplicant;
	}
}