/**
 * CV_A_012_履歴書出力画面
 * 作成履歴：01/04/2019 Thet Ngon Tun
 * 作成概要：フォーム初期化処理 ,JobFair年ドロップダウン処理,キャンセルボタン処理,検索ボタン処理,履歴書出力ボタン
 * 
 * 更新履歴：24/06/2019 Thet Ngon Tun
 * 更新概要：画像フォーマットを修正する	
 */

package mm.com.gic.ces.application.controller.admin;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.fontbox.ttf.TrueTypeCollection;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import mm.com.gic.ces.application.common.CommonRegister;
import mm.com.gic.ces.application.common.CommonUtility;
import mm.com.gic.ces.application.model.Applicant;
import mm.com.gic.ces.application.model.ApplicantInfo;
import mm.com.gic.ces.application.model.Company;
import mm.com.gic.ces.application.property.DBServer;
import mm.com.gic.ces.application.property.Design;
import mm.com.gic.ces.application.property.ExamPlace;
import mm.com.gic.ces.application.property.Others;
import mm.com.gic.ces.application.property.Programming;
import mm.com.gic.ces.base.common.CommonService;
import mm.com.gic.ces.base.service.admin.ExportCVService;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 申請者の履歴書を出力する
 */
@SuppressWarnings("serial")
public class ExportCVAction extends ActionSupport {
	private List<ApplicantInfo> applicantList;   // 申請者一覧
	private List<Integer> IDList;                // 申請者番号一覧
	private Date startDate;                      // 申込日（開始日）
	private Date endDate;                        // 申込日（終了日）
	private String examPlace; 					 // 受検所
	private List<Integer> lstJfYear;             // JobFair年一覧
	private List<ExamPlace> lstExamPlace;        //受験場所一覧
	private int jfYear;                          // JobFair年
	private List<Company> lstCompany;            // 会社名一覧
	private int firstCompany;                    // 第一希望会社名
	private int secondCompany;                   // 第二希望会社名
	private String startExamID;                  // 開始受験ID
	private String endExamID;                    // 終了受験ID
	private boolean chkExaminee;                 // 受験者
	private boolean chkInterview;                // 合格者
	private boolean chkEmployee;                 // 採用者
	private List<Integer> lstAge;                // 年齢
	private String btn;                          // ボタンのテキスト
	private String fileName;                     //ファイル名
	private InputStream inputStream;             //入力ストリーム
	ExportCVService exportCVService = new ExportCVService();
	CommonUtility comUtility=new CommonUtility();

	/**
	 *画面を フォーム初期化に設定する
	 * @return 
	 * @throws IOException
	 */
	public String getApplicantlist() throws IOException {
	
		try {			
			applicantList();
		} catch (SQLException e) {
			return "error";
		}
		return "applicantList";
	}
	
	/**
	 * 履歴書出力ボタンを押す場合、一覧に表示されている申請者の履歴書を出力する
	 * @throws IOException 
	 */
	@SuppressWarnings({ "deprecation", "unused", "resource", "static-access" })
	public String printPDF() throws IOException {
		CommonUtility comUtility=new CommonUtility();
		String ExportedID="";		
		String returnStatus="export";
		try {
			if (IDList.size() > 0) {
				applicantList = exportCVService.applicantList(IDList);
				
			    DateFormat date_format = new SimpleDateFormat("yyyyMMddHHmmss");
			    Date current_date = new Date();
			    String formattedDate = date_format.format(current_date);
			    
			    //フォルダを作成する 
			    Properties properties = comUtility.getValue("config.properties");
			    String exportPath = properties.getProperty("CVExportPath");
			    String folderPath=exportPath+"/"+formattedDate;
			    File folder = new File(folderPath);
		        if (!folder.exists()) {
		        	folder.mkdirs();
		        }
		        
					for (int i = 0; i < applicantList.size(); i++) {
						Applicant applicant=applicantList.get(i).getApplicant();
						String direct = folderPath + "/"
									+ applicant.getExam_ID();
						String file = direct + "/"
									+ applicant.getExam_ID() + ".pdf";
						File directory = new File(direct);
						boolean export;
						if (!directory.exists()) {
								directory.mkdir();
								export = true;
						}
						if (export = true) {
							InputStream in = new ByteArrayInputStream(
										applicant.getApp_Photo());
							BufferedImage profileImage = ImageIO.read(in);
							
							if (profileImage != null) {
								File outputfile = new File(direct
											+ "/profilePhoto.png");
								ImageIO.write(profileImage, "png",
											outputfile);
							}
							exportCertificate(applicant.getGrad_Flag(),
										applicant.getApp_AttFileGrad(), direct,
										"/GraduationCertificate");
								exportCertificate(applicant.getIT1_Flag(),
										applicant.getApp_AttFileIT1(), direct,
										"/IT Certificate 1");
								exportCertificate(applicant.getIT2_Flag(),
										applicant.getApp_AttFileIT2(), direct,
										"/IT Certificate 2");
								exportCertificate(applicant.getIT3_Flag(),
										applicant.getApp_AttFileIT3(), direct,
										"/IT Certificate 3");
								exportCertificate(applicant.getLan1_Flag(),
										applicant.getApp_AttFileLan1(), direct,
										"/English Certificate");
								exportCertificate(applicant.getLan2_Flag(),
										applicant.getApp_AttFileLan2(), direct,
										"/Japanese Certificate");

								PDDocument document = new PDDocument();
								PDPage page = new PDPage();
								document.addPage(page);
								PDPageContentStream contentStream = new PDPageContentStream(
										document, page);
								
								String filePath = ServletActionContext
										.getServletContext().getRealPath("/")
										+ "file/meiryo.ttc";
								File fil = new File(filePath);
								TrueTypeCollection collection = new TrueTypeCollection(
										fil);
								PDFont font = PDType0Font.load(document,
										collection.getFontByName("Meiryo"),
										true);

								float yCoordinate = 775;
								float headerLeading = 25;
								float TextLeading = 19;
								float chkLength = 90;
								float chkHeight = 15;
								int bodyText = 10;

								contentStream.beginText();
								contentStream.newLineAtOffset(250, yCoordinate);
								contentStream.setFont(font, 16);
								contentStream.showText("履歴書");

								yCoordinate -= headerLeading;
								contentStream.newLineAtOffset(-200,
										-headerLeading);
								contentStream.setFont(font, 14);
								contentStream.showText("個人情報");

								yCoordinate -= headerLeading;
								contentStream
										.newLineAtOffset(0, -headerLeading);
								contentStream.setFont(font, bodyText);
								contentStream.showText("受験ID");
								contentStream.moveTextPositionByAmount(100, 0);
								contentStream.showText(":");
								contentStream.moveTextPositionByAmount(50, 0);
								contentStream.showText(applicant.getExam_ID());

								yCoordinate -= TextLeading;
								contentStream.newLineAtOffset(-150,
										-TextLeading);
								contentStream.setFont(font, bodyText);
								contentStream.showText("受検所");
								contentStream.moveTextPositionByAmount(100, 0);
								contentStream.showText(":");
								contentStream.moveTextPositionByAmount(50, 0);
								contentStream.showText(applicant
										.getApp_ExamPlace());

								yCoordinate -= TextLeading;
								contentStream.newLineAtOffset(-150,
										-TextLeading);
								contentStream.showText("名前");
								contentStream.moveTextPositionByAmount(100, 0);
								contentStream.showText(":");
								contentStream.moveTextPositionByAmount(50, 0);
								contentStream.showText(applicant.getApp_Name());

								yCoordinate -= TextLeading;
								contentStream.newLineAtOffset(-150,
										-TextLeading);
								contentStream.showText("個人番号");
								contentStream.moveTextPositionByAmount(100, 0);
								contentStream.showText(":");
								contentStream.moveTextPositionByAmount(50, 0);
								contentStream.showText(applicant.getApp_Nrc());

								yCoordinate -= TextLeading;
								contentStream.newLineAtOffset(-150,
										-TextLeading);
								contentStream.showText("電話番号");
								contentStream.moveTextPositionByAmount(100, 0);
								contentStream.showText(":");
								contentStream.moveTextPositionByAmount(50, 0);
								contentStream.showText(applicant.getApp_PhNo());

								yCoordinate -= TextLeading;
								contentStream.newLineAtOffset(-150,
										-TextLeading);
								contentStream.showText("性別");
								contentStream.moveTextPositionByAmount(100, 0);
								contentStream.showText(":");
								contentStream.moveTextPositionByAmount(50, 0);
								contentStream.showText(applicant
										.getApp_Gender());

								yCoordinate -= TextLeading;
								contentStream.newLineAtOffset(-150,
										-TextLeading);
								contentStream.showText("生年月日");
								contentStream.moveTextPositionByAmount(100, 0);
								contentStream.showText(":");
								SimpleDateFormat formatter = new SimpleDateFormat(
										"dd/MM/yyyy");
								contentStream.moveTextPositionByAmount(50, 0);
								contentStream.showText(formatter
										.format(applicant.getApp_DOB())
										+ " ( "
										+ CommonRegister.calculateAge(applicant.getApp_DOB())
										+ " yrs )");

								yCoordinate -= TextLeading;
								contentStream.newLineAtOffset(-150,
										-TextLeading);
								contentStream.showText("メール");
								contentStream.moveTextPositionByAmount(100, 0);
								contentStream.showText(":");
								contentStream.moveTextPositionByAmount(50, 0);
								contentStream
										.showText(applicant.getApp_Email());

								yCoordinate -= TextLeading;
								contentStream.newLineAtOffset(-150,
										-TextLeading);
								contentStream.showText("住所");
								contentStream.moveTextPositionByAmount(100, 0);
								contentStream.showText(":");
								contentStream.moveTextPositionByAmount(50, 0);
								String address=applicant.getApp_Address();
								address=address.replace("\n", " ").replace("\r", " ");
								if(address.length()>78){
									contentStream.showText(address.substring(0, 78));
									yCoordinate -= TextLeading;
									contentStream.newLineAtOffset(-150,-TextLeading);
									contentStream.moveTextPositionByAmount(150, 0);
									contentStream.showText(address.substring(78, address.length()));
								}else{
									contentStream.showText(address);
								}
								contentStream.endText();
								
								if (profileImage != null) {
									PDImageXObject pdImage = PDImageXObject
											.createFromFile(direct
													+ "/profilePhoto.png",
													document);
									contentStream.drawImage(pdImage, 450, 675,
											70, 70);
									contentStream
											.setNonStrokingColor(java.awt.Color.BLACK);
									contentStream.addRect(449, 674, 72, 72);
									contentStream.stroke();
								}

								yCoordinate -= TextLeading;
								contentStream.moveTo(1, yCoordinate);
								contentStream.setLineWidth((float) 0.5);
								contentStream.lineTo(700, yCoordinate);
								contentStream.stroke();

								contentStream.beginText();
								yCoordinate -= TextLeading;
								contentStream.newLine();
								contentStream.newLineAtOffset(50, yCoordinate);
								contentStream.setFont(font, 14);
								contentStream.showText("教育");

								yCoordinate -= headerLeading;
								contentStream
										.newLineAtOffset(0, -headerLeading);
								contentStream.setFont(font, bodyText);
								contentStream.showText("教育");
								contentStream.moveTextPositionByAmount(100, 0);
								contentStream.showText(":");
								contentStream.moveTextPositionByAmount(50, 0);
								
								String degree = applicant.getApp_Degree();
								String ac_year=applicant.getApp_ACYear();
								String education=applicant.getApp_Education();
								if(education.equals("Graduated")) {
									contentStream.showText(degree);
								}
								else if(education.equals("Ungraduated")){
									contentStream.showText(ac_year);
								}
								yCoordinate -= TextLeading;
								contentStream.newLineAtOffset(-150,
										-TextLeading);
								contentStream.setFont(font, bodyText);
								contentStream.showText("大学");
								contentStream.moveTextPositionByAmount(100, 0);
								contentStream.showText(":");
								contentStream.moveTextPositionByAmount(50, 0);
								contentStream.showText(applicant
										.getApp_University());

								yCoordinate -= TextLeading;
								contentStream.newLineAtOffset(-150,
										-TextLeading);
								contentStream.showText("大学の市");
								contentStream.moveTextPositionByAmount(100, 0);
								contentStream.showText(":");
								contentStream.moveTextPositionByAmount(50, 0);
								contentStream.showText(applicant
										.getApp_CityofUniversity());
								contentStream.endText();

								yCoordinate -= TextLeading;
								contentStream.moveTo(1, yCoordinate);
								contentStream.setLineWidth((float) 0.5);
								contentStream.lineTo(700, yCoordinate);
								contentStream.stroke();

								contentStream.beginText();
								yCoordinate -= TextLeading;
								contentStream.newLineAtOffset(50, yCoordinate);
								contentStream.setFont(font, 14);
								contentStream.showText("経験");

								yCoordinate -= headerLeading;
								contentStream
										.newLineAtOffset(0, -headerLeading);
								contentStream.setFont(font, bodyText);
								contentStream.showText("経験");
								contentStream.moveTextPositionByAmount(100, 0);
								contentStream.showText(":");
								contentStream.moveTextPositionByAmount(50, 0);
								contentStream.showText(applicant
										.getApp_Experience());

								yCoordinate -= TextLeading;
								contentStream.newLineAtOffset(-150,
										-TextLeading);
								contentStream.setFont(font, bodyText);
								contentStream.showText("現在会社");
								contentStream.moveTextPositionByAmount(100, 0);
								contentStream.showText(":");
								contentStream.moveTextPositionByAmount(50, 0);
								if (applicant.getApp_CurrentCom() != ""
										&& applicant.getApp_CurrentCom() != null) {
									contentStream.showText(applicant
											.getApp_CurrentCom());
								} else
									contentStream.showText("-");

								yCoordinate -= TextLeading;
								contentStream.newLineAtOffset(-150,
										-TextLeading);
								contentStream.showText("現在位置");
								contentStream.moveTextPositionByAmount(100, 0);
								contentStream.showText(":");
								contentStream.moveTextPositionByAmount(50, 0);
								if (applicant.getApp_CurrentPos() != ""
										&& applicant.getApp_CurrentPos() != null) {
									contentStream.showText(applicant
											.getApp_CurrentPos());
								} else
									contentStream.showText("-");

								contentStream.endText();

								yCoordinate -= TextLeading;
								contentStream.setFont(font, 5);
								contentStream.drawLine(1, yCoordinate, 700,
										yCoordinate);
								contentStream.setFont(font, bodyText);

								contentStream.beginText();
								yCoordinate -= TextLeading;
								contentStream.newLineAtOffset(50, yCoordinate);
								contentStream.setFont(font, 12);
								contentStream.showText("スキル");

								yCoordinate -= headerLeading;
								contentStream
										.newLineAtOffset(0, -headerLeading);
								contentStream.setFont(font, bodyText);
								contentStream.showText("日本語");
								contentStream.moveTextPositionByAmount(100, 0);
								contentStream.showText(":");
								contentStream.moveTextPositionByAmount(50, 0);
								contentStream.showText(applicant
										.getApp_JPSkill());

								yCoordinate -= TextLeading;
								contentStream.newLineAtOffset(-150,
										-TextLeading);
								contentStream.setFont(font, bodyText);
								contentStream.showText("英語");
								contentStream.moveTextPositionByAmount(100, 0);
								contentStream.showText(":");
								contentStream.moveTextPositionByAmount(50, 0);
								contentStream.showText(applicant
										.getApp_ENGSkill());

								yCoordinate -= TextLeading;
								contentStream.newLineAtOffset(-150,
										-TextLeading);
								contentStream.setFont(font, bodyText);
								contentStream.showText("IT");
								contentStream.endText();

								yCoordinate -= 10;
								contentStream
										.setNonStrokingColor(java.awt.Color.BLACK);
								contentStream.addRect(50, yCoordinate - 60,
										500, 60);
								contentStream.stroke();

								CommonUtility comUlt = new CommonUtility();

								List<Programming> programming = comUlt
										.getITProgrammingCombo();
								float xCoordinate = 0;
								yCoordinate -= 15;

								contentStream.beginText();
								contentStream.newLineAtOffset(60, yCoordinate);
								contentStream.showText("プログラミング");
								contentStream.moveTextPositionByAmount(90, 0);
								contentStream.showText(":");
								contentStream.endText();

								for (int a = 0; a < programming.size(); a++) {
									if (a % 4 == 0 && a != 0) {
										yCoordinate -= chkHeight;
										xCoordinate = 0;
									}

									contentStream.addRect(
											(xCoordinate * chkLength) + 175,
											yCoordinate, 8, 8);
									contentStream.stroke();
									contentStream.beginText();
									contentStream.newLineAtOffset(
											(xCoordinate * chkLength) + 185,
											yCoordinate);
									contentStream.showText(programming.get(a)
											.getProgrammingName());
									contentStream.endText();

									if (applicant.getApp_ITSkill() != ""
											&& applicant.getApp_ITSkill() != null) {
										if (applicant
												.getApp_ITSkill()
												.contains(
														programming
																.get(a)
																.getProgrammingName()+",")) {
											contentStream.moveTo(0, 0);
											contentStream.beginText();
											contentStream
													.newLineAtOffset(
															(xCoordinate * chkLength) + 176,
															yCoordinate);
											contentStream.showText("✔");
											contentStream.endText();
										}
									}
									xCoordinate++;
								}

								yCoordinate -= 16;
								contentStream
										.setNonStrokingColor(java.awt.Color.BLACK);
								contentStream.drawLine(50, yCoordinate, 50,
										yCoordinate - 60);
								contentStream.drawLine(550, yCoordinate, 550,
										yCoordinate - 60);
								contentStream.drawLine(50, yCoordinate - 60,
										550, yCoordinate - 60);
								contentStream.stroke();

								List<Design> design = comUlt.getITDesignCombo();
								xCoordinate = 0;
								yCoordinate -= 15;

								contentStream.beginText();
								contentStream.newLineAtOffset(60, yCoordinate);
								contentStream.showText("デザイン");
								contentStream.moveTextPositionByAmount(90, 0);
								contentStream.showText(":");
								contentStream.endText();

								for (int a = 0; a < design.size(); a++) {
									if (a % 4 == 0 && a != 0) {
										yCoordinate -= chkHeight;
										xCoordinate = 0;
									}

									contentStream.addRect(
											(xCoordinate * chkLength) + 175,
											yCoordinate, 8, 8);
									contentStream.stroke();

									contentStream.beginText();
									contentStream.newLineAtOffset(
											(xCoordinate * chkLength) + 185,
											yCoordinate);
									contentStream.showText(design.get(a)
											.getDesignName());
									contentStream.endText();
									if (applicant.getApp_ITSkill() != ""
											&& applicant.getApp_ITSkill() != null) {
										if (applicant
												.getApp_ITSkill()
												.contains(
														design.get(a)
																.getDesignName()+",")) {
											contentStream.moveTo(0, 0);
											contentStream.beginText();
											contentStream
													.newLineAtOffset(
															(xCoordinate * chkLength) + 176,
															yCoordinate);
											contentStream.showText("✔");
											contentStream.endText();
										}
									}
									xCoordinate++;
								}

								yCoordinate -= 16;
								contentStream
										.setNonStrokingColor(java.awt.Color.BLACK);
								contentStream.drawLine(50, yCoordinate, 50,
										yCoordinate - 60);
								contentStream.drawLine(550, yCoordinate, 550,
										yCoordinate - 60);
								contentStream.drawLine(50, yCoordinate - 60,
										550, yCoordinate - 60);
								contentStream.stroke();

								List<DBServer> database = comUlt
										.getITDatabaseCombo();
								xCoordinate = 0;
								yCoordinate -= 15;

								contentStream.beginText();
								contentStream.newLineAtOffset(60, yCoordinate);
								contentStream.showText("データベース");
								contentStream.moveTextPositionByAmount(90, 0);
								contentStream.showText(":");
								contentStream.endText();

								for (int a = 0; a < database.size(); a++) {
									if (a % 4 == 0 && a != 0) {
										yCoordinate -= chkHeight;
										xCoordinate = 0;
									}

									contentStream.addRect(
											(xCoordinate * chkLength) + 175,
											yCoordinate, 8, 8);
									contentStream.stroke();
									contentStream.beginText();
									contentStream.newLineAtOffset(
											(xCoordinate * chkLength) + 185,
											yCoordinate);
									contentStream.showText(database.get(a)
											.getDbServerName());
									contentStream.endText();
									if (applicant.getApp_ITSkill() != ""
											&& applicant.getApp_ITSkill() != null) {
										if (applicant
												.getApp_ITSkill()
												.contains(
														database.get(a)
																.getDbServerName()+",")) {
											contentStream.moveTo(0, 0);
											contentStream.beginText();
											contentStream
													.newLineAtOffset(
															(xCoordinate * chkLength) + 176,
															yCoordinate);
											contentStream.showText("✔");
											contentStream.endText();
										}
									}
									xCoordinate++;
								}

								yCoordinate -= 16;
								contentStream
										.setNonStrokingColor(java.awt.Color.BLACK);
								contentStream.drawLine(50, yCoordinate, 50,
										yCoordinate - 30);
								contentStream.drawLine(550, yCoordinate, 550,
										yCoordinate - 30);
								contentStream.drawLine(50, yCoordinate - 30,
										550, yCoordinate - 30);
								contentStream.stroke();

								List<Others> otherSkill = comUlt
										.getITOtherCombo();
								xCoordinate = 0;
								yCoordinate -= 15;

								contentStream.beginText();
								contentStream.newLineAtOffset(60, yCoordinate);
								contentStream.showText("その他   :");
								contentStream.moveTextPositionByAmount(90, 0);
								contentStream.showText(":");
								contentStream.endText();

								for (int a = 0; a < otherSkill.size(); a++) {
									if (a % 4 == 0 && a != 0) {
										yCoordinate -= chkHeight;
										xCoordinate = 0;
									}

									contentStream.addRect(
											(xCoordinate * chkLength) + 175,
											yCoordinate, 8, 8);
									contentStream.stroke();
									contentStream.beginText();
									contentStream.newLineAtOffset(
											(xCoordinate * chkLength) + 185,
											yCoordinate);
									contentStream.showText(otherSkill.get(a)
											.getOthersName());
									contentStream.endText();
									if (applicant.getApp_ITSkill() != ""
											&& applicant.getApp_ITSkill() != null) {
										if (applicant
												.getApp_ITSkill()
												.contains(
														otherSkill
																.get(a)
																.getOthersName()+",")) {
											contentStream.moveTo(0, 0);
											contentStream.beginText();
											contentStream
													.newLineAtOffset(
															(xCoordinate * chkLength) + 176,
															yCoordinate);
											contentStream.showText("✔");
											contentStream.endText();
										}
									}
									xCoordinate++;
								}
								contentStream.close();								
								document.save(file);
								document.close();
								ExportedID=applicant.getExam_ID();
							}		
							if(i==(IDList.size()-1)){
								String FileName= "CV_A_012_履歴書出力画面_" + formattedDate+".zip";
								String zipFileName = URLEncoder.encode(FileName, "UTF-8");
								String zipFilePath=exportPath+"/"+ zipFileName;
								zipDir(zipFilePath,folderPath);				    
								File zipFile=new File(zipFilePath);
								downloadFile(zipFile,zipFileName);
								File dir = new File(folderPath);
								FileUtils.cleanDirectory(dir);
								FileUtils.deleteDirectory(dir);		
							    //フォルダを削除する
						        CommonUtility.recursiveDelete(new File(exportPath));
								returnStatus="exportCV";
							}
						}
				}else{
					applicantList = exportCVService.applicantList(IDList);
					returnStatus="success";
				}			
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(comUtility.getValue("Message.properties").getProperty(Integer
						.toString(43)));
			returnStatus="success";
			
		}
		return returnStatus;
	}

	/**
	 * 　入力した検索条件によって申請者のデータを検索する
	 * @throws IOException 
	 */
	public void getSearchList() throws SQLException, IOException {
		CommonUtility comUtility= new CommonUtility();		
		@SuppressWarnings("static-access")
		Properties prop = comUtility.getValue("Message.properties");
		applicantList = exportCVService.searchList(startDate, endDate, jfYear,
				firstCompany, secondCompany, examPlace, startExamID, endExamID,
				chkExaminee, chkInterview, chkEmployee);
		lstAge=new ArrayList<Integer>();
		if (!applicantList.isEmpty()) {
			if (applicantList.size() > 50) {
				addActionError(prop.getProperty(Integer.toString(5)));
				applicantList=new ArrayList<ApplicantInfo>();
			}else{
				for (ApplicantInfo appInfo: applicantList) {
					lstAge.add(CommonRegister.calculateAge(appInfo.getApplicant().getApp_DOB()));					
				}
			}
		}else{
			addActionError(prop.getProperty(Integer.toString(19)));
		}
	}
		
	/**
	 * ユーザーが押したボタンをチェックする
	 * 
	 * @return success
	 */
	public String exportCVForm() {
		String returnStatus="success";
		try {
			CommonService commonService = new CommonService();
			if (btn == null) {
				if (jfYear != 0) {
					lstCompany = commonService.getCompany(jfYear);
				} else {
					lstCompany = new ArrayList<Company>();
				}				
				applicantList = exportCVService.applicantList(IDList);

				lstAge=new ArrayList<Integer>();
				if(applicantList.size()>0){
					for (ApplicantInfo appInfo: applicantList) {
						lstAge.add(CommonRegister.calculateAge(appInfo.getApplicant().getApp_DOB()));
					}					
				}
				
			} else if (btn.equals("キャンセル")) {
				applicantList();
			} else if (btn.equals("検索")) {
				lstCompany = commonService.getCompany(jfYear);
				getSearchList();
			} else if (btn.equals("履歴書出力")) {
				lstCompany = commonService.getCompany(jfYear);
				returnStatus=printPDF();
				lstAge=new ArrayList<Integer>();
				if(!applicantList.isEmpty()){
					for (ApplicantInfo appInfo: applicantList) {
						lstAge.add(CommonRegister.calculateAge(appInfo.getApplicant().getApp_DOB()));					
					}
				}
			}		
			
			lstExamPlace=CommonUtility.getExamPlace();
			lstJfYear = commonService.selectJFYear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnStatus;
	}

	/***
	 * 画像とPDFファイルを出力する
	 * 
	 * @param extension
	 * @param file
	 * @param directory
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public void exportCertificate(String extension, byte[] file,
			String directory, String fileName) throws FileNotFoundException {
		try {
			if (extension != "" && extension != null && file!=null) {
					InputStream in = new ByteArrayInputStream(file);
					BufferedImage image = ImageIO.read(in);
					if (image != null) {
						File outputfile = new File(directory + fileName+ ".png");
						ImageIO.write(image, "png", outputfile);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	/**
	 * 画面の初期化にリセットする
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	public void applicantList() throws SQLException, IOException {
		setJfYear(0);
		setExamPlace(null);
		setStartDate(null);
		setEndDate(null);
		setStartExamID(null);
		setEndExamID(null);
		setChkEmployee(false);
		setChkExaminee(false);
		setChkInterview(false);
		applicantList = new ArrayList<ApplicantInfo>();
		CommonService commonService = new CommonService();
		lstExamPlace=CommonUtility.getExamPlace();
		lstJfYear = commonService.selectJFYear();
		lstCompany = new ArrayList<Company>();
	}
	
	 private static void zipDir(String zipFileName, String dir) throws Exception {
		    File dirObj = new File(dir);
		    ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
		    addDir(dirObj, out);
		    out.close();		    
	}

	private static void addDir(File dirObj, ZipOutputStream out) throws IOException {
		    File[] files = dirObj.listFiles();
		    byte[] tmpBuf = new byte[1024];

		    for (int i = 0; i < files.length; i++) {
		      if (files[i].isDirectory()) {
		        addDir(files[i], out);
		        continue;
		      }
		      FileInputStream in = new FileInputStream(files[i].getAbsolutePath());
		      out.putNextEntry(new ZipEntry(files[i].getAbsolutePath()));
		      int len;
		      while ((len = in.read(tmpBuf)) > 0) {
		        out.write(tmpBuf, 0, len);
		      }
		      out.closeEntry();
		      in.close();
		    }
		  }
	
		  public void downloadFile(File fileToDownload, String fName) throws FileNotFoundException, UnsupportedEncodingException{
		       inputStream = new FileInputStream(fileToDownload);
		       fileName = fName;
		    }

		
	// 申請者一覧
	public List<ApplicantInfo> getApplicantList() {
		return applicantList;
	}

	public void setApplicantList(List<ApplicantInfo> applicantList) {
		this.applicantList = applicantList;
	}

	// 申請者番号一覧
	public List<Integer> getIDList() {
		return IDList;
	}

	public void setIDList(List<Integer> iDList) {
		IDList = iDList;
	}

	// 申込日（開始日）
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	// 申込日（終了日）
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	//受験場所
	public String getExamPlace() {
		return examPlace;
	}

	public void setExamPlace(String examPlace) {
		this.examPlace = examPlace;
	}

	// JobFair年一覧
	public List<Integer> getLstJfYear() {
		return lstJfYear;
	}

	public void setLstJfYear(List<Integer> lstJfYear) {
		this.lstJfYear = lstJfYear;
	}

	// JobFair年
	public int getJfYear() {
		return jfYear;
	}

	public void setJfYear(int jfYear) {
		this.jfYear = jfYear;
	}

	//受験場所一覧
	public List<ExamPlace> getLstExamPlace() {
		return lstExamPlace;
	}

	public void setLstExamPlace(List<ExamPlace> lstExamPlace) {
		this.lstExamPlace = lstExamPlace;
	}

	// 会社名一覧
	public List<Company> getLstCompany() {
		return lstCompany;
	}

	public void setLstCompany(List<Company> lstCompany) {
		this.lstCompany = lstCompany;
	}

	// 第一希望会社名
	public int getFirstCompany() {
		return firstCompany;
	}

	public void setFirstCompany(int firstCompany) {
		this.firstCompany = firstCompany;
	}

	// 第二希望会社名
	public int getSecondCompany() {
		return secondCompany;
	}

	public void setSecondCompany(int secondCompany) {
		this.secondCompany = secondCompany;
	}

	// 開始受験ID
	public String getStartExamID() {
		return startExamID;
	}

	public void setStartExamID(String startExamID) {
		this.startExamID = startExamID;
	}

	// 終了受験ID
	public String getEndExamID() {
		return endExamID;
	}

	public void setEndExamID(String endExamID) {
		this.endExamID = endExamID;
	}

	// 受験者
	public boolean isChkExaminee() {
		return chkExaminee;
	}

	public void setChkExaminee(boolean chkExaminee) {
		this.chkExaminee = chkExaminee;
	}

	// 合格者
	public boolean isChkInterview() {
		return chkInterview;
	}

	public void setChkInterview(boolean chkInterview) {
		this.chkInterview = chkInterview;
	}

	// 採用者
	public boolean isChkEmployee() {
		return chkEmployee;
	}

	public void setChkEmployee(boolean chkEmployee) {
		this.chkEmployee = chkEmployee;
	}

	//年齢
	public List<Integer> getLstAge() {
		return lstAge;
	}

	public void setLstAge(List<Integer> lstAge) {
		this.lstAge = lstAge;
	}

	// ボタンのテキスト
	public String getBtn() {
		return btn;
	}

	public void setBtn(String btn) {
		this.btn = btn;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
