/**
 * Ã¤Â½Å“Ã¯Â¿Â½Ã¥Â±Â¥Ã¦Â­Â´Ã¯Â¿Â½09/04/2019 Jar Moon Taung
 * Ã¤Â½Å“Ã¯Â¿Â½Ã¦Â¦â€šÃ¨Â¦Â¼Ã¯Â¿Â½Ã¦â€“Â°Ã¨Â¦ï¿½Ã¤Â½Å“Ã¯Â¿Â½Ã¯Â¿Â½
 * 
 * Ã¦â€ºÂ´Ã¦â€“Â°Ã¥Â±Â¥Ã¦Â­Â´Ã¯Â¿Â½Ã¯Â¿Â½dd/mm/yyyy name
 * Ã¦â€ºÂ´Ã¦â€“Â°Ã¦Â¦â€šÃ¨Â¦Â¼Ã¯Â¿Â½XXXXXXXX	
 */
package mm.com.gic.ces.application.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "tbl_applicant")
public class Applicant implements Serializable {

	private static final long serialVersionUID = 1L;      //SerialID
	private int app_ID;                                   //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½ID	
	private String exam_ID;                               //Ã¥ï¿½â€”Ã©Â¨â€œID
	private String app_Name;                              //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¥ï¿½ï¿½Ã¥â€°ï¿½
	private String app_Nrc;                               //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¥â‚¬â€¹Ã¤ÂºÂºÃ§â€¢ÂªÃ¥ï¿½Â·
	private Date app_DOB;                                 //Ã§â€�Å¸Ã¥Â¹Â´Ã¦Å“Ë†Ã¦â€”Â¥
	private String app_Gender;                            //Ã¦â‚¬Â§Ã¥Ë†Â¥
	private String app_Email;                             //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã£Æ’Â¡Ã£Æ’Â¼Ã£Æ’Â«	
	private String app_PhNo;                              //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã©â€ºÂ»Ã¨Â©Â±Ã§â€¢ÂªÃ¥ï¿½Â·
	private String app_Address;                           //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¤Â½ï¿½Ã¦â€°â‚¬
	private String app_Education;                         //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¦â€¢â„¢Ã¨â€šÂ²
	private String app_CityofUniversity;                  //Ã¥Â¤Â§Ã¥Â­Â¦Ã£ï¿½Â®Ã¯Â¿Â½	
	private String app_University;                        //Ã¥Â¤Â§Ã¥Â­Â¦Ã¯Â¿Â½
	private String app_Degree;                            //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¥Â­Â¦Ã¯Â¿Â½
	private String app_ACYear;
	private String app_ACPlan;
	private Date app_EGD;
	private String app_FEC;
	private String app_FEPlan;
	private String app_GICAcademy;
	private String app_Experience;                        //Ã§ÂµÅ’Ã¯Â¿Â½
	private String app_CurrentCom;                        //Ã§ï¿½Â¾Ã¥Å“Â¨Ã¤Â¼Å¡Ã§Â¤Â¾
	private String app_CurrentPos;                        //Ã§ï¿½Â¾Ã¥Å“Â¨Ã¥Â½Â¹
	private String app_JPSkill;                           //JPÃ£â€šÂ¹Ã£â€šÂ­Ã£Æ’Â«
    private String app_ENGSkill;                          //EngÃ£â€šÂ¹Ã£â€šÂ­Ã£Æ’Â«
	private String app_ITSkill;                           //ITÃ£â€šÂ¹Ã£â€šÂ­Ã£Æ’Â«
	private String app_ExamPlace;                         //Ã¥ï¿½â€”Ã©Â¨â€œÃ¦â€°â‚¬
	private byte[] app_Photo;                             //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¥â€ â„¢Ã§Å“Å¸
	private byte[] app_AttFileGrad;                       //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¦Â·Â»Ã¤Â»ËœÃ£Æ’â€¢Ã£â€šÂ¡Ã£â€šÂ¤Ã£Æ’Â«
	private byte[] app_AttFileLan1;                       //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¦Â·Â»Ã¤Â»ËœÃ£Æ’â€¢Ã£â€šÂ¡Ã£â€šÂ¤Ã£Æ’Â«
	private byte[] app_AttFileLan2;                       //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¦Â·Â»Ã¤Â»ËœÃ£Æ’â€¢Ã£â€šÂ¡Ã£â€šÂ¤Ã£Æ’Â«
	private byte[] app_AttFileIT1;                        //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¦Â·Â»Ã¤Â»ËœÃ£Æ’â€¢Ã£â€šÂ¡Ã£â€šÂ¤Ã£Æ’Â«
	private byte[] app_AttFileIT2;                        //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¦Â·Â»Ã¤Â»ËœÃ£Æ’â€¢Ã£â€šÂ¡Ã£â€šÂ¤Ã£Æ’Â«
	private byte[] app_AttFileIT3;                        //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¦Â·Â»Ã¤Â»ËœÃ£Æ’â€¢Ã£â€šÂ¡Ã£â€šÂ¤Ã£Æ’Â«
	private String grad_Flag;	                          //Ã¦â€¢â„¢Ã¨â€šÂ²Ã£Æ’â€¢Ã£Æ’Â©Ã£â€šÂ°
	private String lan1_Flag;	                          //Lan1Ã£Æ’â€¢Ã£Æ’Â©Ã£â€šÂ°
	private String lan2_Flag;	                          //Lan2Ã£Æ’â€¢Ã£Æ’Â©Ã£â€šÂ°
	private String IT1_Flag;	                          //IT1Ã£Æ’â€¢Ã£Æ’Â©Ã£â€šÂ°	
	private String IT2_Flag;	                          //IT2Ã£Æ’â€¢Ã£Æ’Â©Ã£â€šÂ°	
	private String IT3_Flag;	                          //IT3Ã£Æ’â€¢Ã£Æ’Â©Ã£â€šÂ°		
	private int app_JFYear;	                              //Job FairÃ¥Â¹Â´	
	private Date app_RegDate;	                          //Ã§â€�Â³Ã¨Â¾Â¼Ã¦â€”Â¥	
	private String app_Check;	                          //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã£Æ’â€¢Ã£Æ’Â©Ã£â€šÂ°
	private String mail_Check;	                          //Ã£Æ’Â¡Ã£Æ’Â¼Ã£Æ’Â«Ã£Æ’â€¢Ã£Æ’Â©Ã£â€šÂ°
	private int del_flag;	                              //Ã¥â€°Å Ã©â„¢Â¤Ã£Æ’â€¢Ã£Æ’Â©Ã£â€šÂ°
	private int app_ex_key;	                              //Ã¦Å½â€™Ã¤Â»â€“Ã£â€šÂ­Ã£Æ’Â¼	
	private String zero_yen_study;
	private String jp_company_exp;
    
	//Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½ID	
	@Id
	@GeneratedValue
	@Column(name = "APP_ID", unique = true)
	public int getApp_ID() {
		return app_ID;
	}

	public void setApp_ID(int app_ID) {
		this.app_ID = app_ID;
	}
	//Ã¥ï¿½â€”Ã©Â¨â€œID
	@Column(name = "EXAM_ID", unique = true,length=50)
	public String getExam_ID() {
		return exam_ID;
	}

	public void setExam_ID(String exam_ID) {
		this.exam_ID = exam_ID;
	}
    //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¥ï¿½ï¿½Ã¥â€°ï¿½
	@Column(name = "APP_NAME",length=150)
	public String getApp_Name() {
		return app_Name;
	}

	public void setApp_Name(String app_Name) {
		this.app_Name = app_Name;
	}
	//Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¥â‚¬â€¹Ã¤ÂºÂºÃ§â€¢ÂªÃ¥ï¿½Â·
	@Column(name = "APP_NRC",length=150)
	public String getApp_Nrc() {
		return app_Nrc;
	}

	public void setApp_Nrc(String app_Nrc) {
		this.app_Nrc = app_Nrc;
	}
	//Ã§â€�Å¸Ã¥Â¹Â´Ã¦Å“Ë†Ã¦â€”Â¥
	@Column(name = "APP_DOB")
	@Type(type="date")
	public Date getApp_DOB() {
		return app_DOB;
	}

	public void setApp_DOB(Date app_DOB) {
		this.app_DOB = app_DOB;
	}
    //Ã¦â‚¬Â§Ã¥Ë†Â¥	
	@Column(name = "APP_GENDER",length=10)
	public String getApp_Gender() {
		return app_Gender;
	}

	public void setApp_Gender(String app_Gender) {
		this.app_Gender = app_Gender;
	}

	//Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã£Æ’Â¡Ã£Æ’Â¼Ã£Æ’Â«	
	@Column(name = "APP_EMAIL",length=100)
	public String getApp_Email() {
		return app_Email;
	}
	
	public void setApp_Email(String app_Email) {
		this.app_Email = app_Email;
	}
	//Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã©â€ºÂ»Ã¨Â©Â±Ã§â€¢ÂªÃ¥ï¿½Â· 	
	@Column(name = "APP_PHONE_NUMBER",length=20)
	public String getApp_PhNo() {
		return app_PhNo;
	}

	public void setApp_PhNo(String app_PhNo) {
		this.app_PhNo = app_PhNo;
	}
	//Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¤Â½ï¿½Ã¦â€°â‚¬
	@Column(name = "APP_ADDRESS")
	public String getApp_Address() {
		return app_Address;
	}

	public void setApp_Address(String app_Address) {
		this.app_Address = app_Address;
	}
    //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¦â€¢â„¢Ã¨â€šÂ²
	@Column(name = "APP_EDUCATION",length=15)
	public String getApp_Education() {
		return app_Education;
	}

	public void setApp_Education(String app_Education) {
		this.app_Education = app_Education;
	}
    //Ã¥Â¤Â§Ã¥Â­Â¦Ã£ï¿½Â®Ã¯Â¿Â½
	@Column(name = "APP_CITY_OF_UNIVERSITY",length=150)
	public String getApp_CityofUniversity() {
		return app_CityofUniversity;
	}

	public void setApp_CityofUniversity(String app_CityofUniversity) {
		this.app_CityofUniversity = app_CityofUniversity;
	}
    //Ã¥Â¤Â§Ã¥Â­Â¦Ã¯Â¿Â½
	@Column(name = "APP_UNIVERSITY",length=255)
	public String getApp_University() {
		return app_University;
	}

	public void setApp_University(String app_University) {
		this.app_University = app_University;
	}
    //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¥Â­Â¦Ã¯Â¿Â½
	@Column(name = "APP_DEGREE",length=150)
	public String getApp_Degree() {
		return app_Degree;
	}

	public void setApp_Degree(String app_Degree) {
		this.app_Degree = app_Degree;
	}
	@Column(name = "APP_AC_YEARS",length=20)
    public String getApp_ACYear() {
		return app_ACYear;
	}

	public void setApp_ACYear(String app_ACYear) {
		this.app_ACYear = app_ACYear;
	}
	@Column(name = "APP_AC_PLAN",length=50)
	public String getApp_ACPlan() {
		return app_ACPlan;
	}

	public void setApp_ACPlan(String app_ACPlan) {
		this.app_ACPlan = app_ACPlan;
	}
	@Column(name = "APP_EGD")
	@Type(type="date")
	public Date getApp_EGD() {
		return app_EGD;
	}

	public void setApp_EGD(Date app_EGD) {
		this.app_EGD = app_EGD;
	}
	@Column(name = "APP_FEC",length=50)
	public String getApp_FEC() {
		return app_FEC;
	}

	public void setApp_FEC(String app_FEC) {
		this.app_FEC = app_FEC;
	}
	@Column(name = "APP_FE_PLAN",length=50)
	public String getApp_FEPlan() {
		return app_FEPlan;
	}

	public void setApp_FEPlan(String app_FEPlan) {
		this.app_FEPlan = app_FEPlan;
	}
	@Column(name = "APP_GIC_ACADEMY",length=50)
	public String getApp_GICAcademy() {
		return app_GICAcademy;
	}

	public void setApp_GICAcademy(String app_GICAcademy) {
		this.app_GICAcademy = app_GICAcademy;
	}

    //Ã§ÂµÅ’Ã¯Â¿Â½
	@Column(name = "APP_EXPERIENCE",length=50)
	public String getApp_Experience() {
		return app_Experience;
	}

	public void setApp_Experience(String app_Experience) {
		this.app_Experience = app_Experience;
	}
    //Ã§ï¿½Â¾Ã¥Å“Â¨Ã¤Â¼Å¡Ã§Â¤Â¾	
	@Column(name = "APP_CURRENT_COMPANY",length=150)
	public String getApp_CurrentCom() {
		return app_CurrentCom;
	}

	public void setApp_CurrentCom(String app_CurrentCom) {
		this.app_CurrentCom = app_CurrentCom;
	}
    //Ã§ï¿½Â¾Ã¥Å“Â¨Ã¥Â½Â¹
	@Column(name = "APP_CURRENT_POSITION",length=150)
	public String getApp_CurrentPos() {
		return app_CurrentPos;
	}

	public void setApp_CurrentPos(String app_CurrentPos) {
		this.app_CurrentPos = app_CurrentPos;
	}
	  //JPÃ£â€šÂ¹Ã£â€šÂ­Ã£Æ’Â«
	@Column(name = "APP_JP_SKILL",length=50)
	public String getApp_JPSkill() {
		return app_JPSkill;
	}

	public void setApp_JPSkill(String app_JPSkill) {
		this.app_JPSkill = app_JPSkill;
	}
	//EngÃ£â€šÂ¹Ã£â€šÂ­Ã£Æ’Â«
	@Column(name = "APP_ENG_SKILL",length=50)
	public String getApp_ENGSkill() {
		return app_ENGSkill;
	}

	public void setApp_ENGSkill(String app_ENGSkill) {
		this.app_ENGSkill = app_ENGSkill;
	}
	 //ITÃ£â€šÂ¹Ã£â€šÂ­Ã£Æ’Â«
	@Column(name = "APP_IT_SKILL",length=1024)
	public String getApp_ITSkill() {
		return app_ITSkill;
	}

	public void setApp_ITSkill(String app_ITSkill) {
		this.app_ITSkill = app_ITSkill;
	}
    //Ã¥ï¿½â€”Ã©Â¨â€œÃ¦â€°â‚¬
	@Column(name = "EXAM_PLACE",length=15)
	public String getApp_ExamPlace() {
		return app_ExamPlace;
	}

	public void setApp_ExamPlace(String app_ExamPlace) {
		this.app_ExamPlace = app_ExamPlace;
	}
	//Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¥â€ â„¢Ã§Å“Å¸
	@Column(name = "APP_PHOTO", columnDefinition = "LONGBLOB")
	public byte[] getApp_Photo() {
		return app_Photo;
	}

	public void setApp_Photo(byte[] app_Photo) {
		this.app_Photo = app_Photo;
	}
	 //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¦Â·Â»Ã¤Â»ËœÃ£Æ’â€¢Ã£â€šÂ¡Ã£â€šÂ¤Ã£Æ’Â«
	@Column(name = "APP_ATT_FILE_GRAD", columnDefinition = "LONGBLOB")
	public byte[] getApp_AttFileGrad() {
		return app_AttFileGrad;
	}

	public void setApp_AttFileGrad(byte[] app_AttFileGrad) {
		this.app_AttFileGrad = app_AttFileGrad;
	}
	 //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¦Â·Â»Ã¤Â»ËœÃ£Æ’â€¢Ã£â€šÂ¡Ã£â€šÂ¤Ã£Æ’Â«
	@Column(name = "APP_ATT_FILE_LAN1", columnDefinition = "LONGBLOB")
	public byte[] getApp_AttFileLan1() {
		return app_AttFileLan1;
	}

	public void setApp_AttFileLan1(byte[] app_AttFileLan1) {
		this.app_AttFileLan1 = app_AttFileLan1;
	}
	 //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¦Â·Â»Ã¤Â»ËœÃ£Æ’â€¢Ã£â€šÂ¡Ã£â€šÂ¤Ã£Æ’Â«
	@Column(name = "APP_ATT_FILE_LAN2", columnDefinition = "LONGBLOB")
	public byte[] getApp_AttFileLan2() {
		return app_AttFileLan2;
	}

	public void setApp_AttFileLan2(byte[] app_AttFileLan2) {
		this.app_AttFileLan2 = app_AttFileLan2;
	}
	 //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¦Â·Â»Ã¤Â»ËœÃ£Æ’â€¢Ã£â€šÂ¡Ã£â€šÂ¤Ã£Æ’Â«
	@Column(name = "APP_ATT_FILE_IT1", columnDefinition = "LONGBLOB")
	public byte[] getApp_AttFileIT1() {
		return app_AttFileIT1;
	}

	public void setApp_AttFileIT1(byte[] app_AttFileIT1) {
		this.app_AttFileIT1 = app_AttFileIT1;
	}
	 //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¦Â·Â»Ã¤Â»ËœÃ£Æ’â€¢Ã£â€šÂ¡Ã£â€šÂ¤Ã£Æ’Â«
	@Column(name = "APP_ATT_FILE_IT2", columnDefinition = "LONGBLOB")
	public byte[] getApp_AttFileIT2() {
		return app_AttFileIT2;
	}

	public void setApp_AttFileIT2(byte[] app_AttFileIT2) {
		this.app_AttFileIT2 = app_AttFileIT2;
	}
	 //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã¦Â·Â»Ã¤Â»ËœÃ£Æ’â€¢Ã£â€šÂ¡Ã£â€šÂ¤Ã£Æ’Â«
	@Column(name = "APP_ATT_FILE_IT3", columnDefinition = "LONGBLOB")
	public byte[] getApp_AttFileIT3() {
		return app_AttFileIT3;
	}

	public void setApp_AttFileIT3(byte[] app_AttFileIT3) {
		this.app_AttFileIT3 = app_AttFileIT3;
	}
	 //Ã¦â€¢â„¢Ã¨â€šÂ²Ã£Æ’â€¢Ã£Æ’Â©Ã£â€šÂ°
	@Column(name = "GRAND_FLAG",length=3)
	public String getGrad_Flag() {
		return grad_Flag;
	}

	public void setGrad_Flag(String grad_Flag) {
		this.grad_Flag = grad_Flag;
	}
	 //Ã¦â€¢â„¢Ã¨â€šÂ²Ã£Æ’â€¢Ã£Æ’Â©Ã£â€šÂ°
	@Column(name = "LAN1_FLAG",length=3)
	public String getLan1_Flag() {
		return lan1_Flag;
	}

	public void setLan1_Flag(String lan1_Flag) {
		this.lan1_Flag = lan1_Flag;
	}
	 //Ã¦â€¢â„¢Ã¨â€šÂ²Ã£Æ’â€¢Ã£Æ’Â©Ã£â€šÂ°
	@Column(name = "LAN2_FLAG",length=3)
	public String getLan2_Flag() {
		return lan2_Flag;
	}

	public void setLan2_Flag(String lan2_Flag) {
		this.lan2_Flag = lan2_Flag;
	}
	 //Ã¦â€¢â„¢Ã¨â€šÂ²Ã£Æ’â€¢Ã£Æ’Â©Ã£â€šÂ° 
	@Column(name = "IT1_FLAG",length=3)
	public String getIT1_Flag() {
		return IT1_Flag;
	}

	public void setIT1_Flag(String iT1_Flag) {
		IT1_Flag = iT1_Flag;
	}
	 //Ã¦â€¢â„¢Ã¨â€šÂ²Ã£Æ’â€¢Ã£Æ’Â©Ã£â€šÂ°
	@Column(name = "IT2_FLAG",length=3)
	public String getIT2_Flag() {
		return IT2_Flag;
	}

	public void setIT2_Flag(String iT2_Flag) {
		IT2_Flag = iT2_Flag;
	}
	 //Ã¦â€¢â„¢Ã¨â€šÂ²Ã£Æ’â€¢Ã£Æ’Â©Ã£â€šÂ°
	@Column(name = "IT3_FLAG",length=3)
	public String getIT3_Flag() {
		return IT3_Flag;
	}

	public void setIT3_Flag(String iT3_Flag) {
		IT3_Flag = iT3_Flag;
	}
	 //Job FairÃ¥Â¹Â´	
	@Column(name = "APP_JF_YEAR",length=11)
	public int getApp_JFYear() {
		return app_JFYear;
	}

	public void setApp_JFYear(int app_JFYear) {
		this.app_JFYear = app_JFYear;
	}
    //Ã§â€�Â³Ã¨Â¾Â¼Ã¦â€”Â¥
	@Column(name = "APP_REG_DATE")
	@Type(type="date")
	public Date getApp_RegDate() {
		return app_RegDate;
	}

	public void setApp_RegDate(Date app_RegDate) {
		this.app_RegDate = app_RegDate;
	}
	 //Ã§â€�Â³Ã¨Â«â€¹Ã¯Â¿Â½Ã£Æ’â€¢Ã£Æ’Â©Ã£â€šÂ°
	@Column(name = "APP_CHECK",columnDefinition = "varchar(3) default 'N'")
	public String getApp_Check() {
		return app_Check;
	}

	public void setApp_Check(String app_Check) {
		this.app_Check = app_Check;
	}
	
	//Ã£Æ’Â¡Ã£Æ’Â¼Ã£Æ’Â«Ã£Æ’â€¢Ã£Æ’Â©Ã£â€šÂ°
	@Column(name = "MAIL_CHECK",columnDefinition = "varchar(10)")
	public String getMail_Check() {
		return mail_Check;
	}

	public void setMail_Check(String mail_Check) {
		this.mail_Check = mail_Check;
	}

	//Ã¥â€°Å Ã©â„¢Â¤Ã£Æ’â€¢Ã£Æ’Â©Ã£â€šÂ°
	@Column(name = "DEL_FLAG", columnDefinition = "int(1) default 0")
	public int getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(int del_flag) {
		this.del_flag = del_flag;
	}
    //Ã¦Å½â€™Ã¤Â»â€“Ã£â€šÂ­Ã£Æ’Â¼	  
	@Column(name = "APP_EX_KEY")
	public int getApp_ex_key() {
		return app_ex_key;
	}

	public void setApp_ex_key(int app_ex_key) {
		this.app_ex_key = app_ex_key;
	}
	
	//zero yen study
	@Column(name = "ZERO_YEN_STUDY", length=50)
	public String getZero_yen_study() {
		return zero_yen_study;
	}

	public void setZero_yen_study(String zero_yen_study) {
		this.zero_yen_study = zero_yen_study;
	}
	
	//Japan company experience
	@Column(name = "JP_COMPANY_EXP", length=50)
	public String getJp_company_exp() {
		return jp_company_exp;
	}

	public void setJp_company_exp(String jp_company_exp) {
		this.jp_company_exp = jp_company_exp;
	}  	
}