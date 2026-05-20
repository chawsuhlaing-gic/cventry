package mm.com.gic.ces.base.service.admin;

/***
 * 
 * 新規作成：2019/02/05 Aye Chan Moe 
 * 作成概要：新規作成,　メール送信処理, 登録処理
 * 
 * 更新履歴： 2019/05/09 Thet Ngon Tun 
 * 更新概要：新規作成,　メール送信処理, 登録処理
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import mm.com.gic.ces.application.common.CommonUtility;
import mm.com.gic.ces.application.model.Applicant;
import mm.com.gic.ces.application.model.Interview;
import mm.com.gic.ces.application.model.MailRecord;
import mm.com.gic.ces.application.model.RoleSetting;
import mm.com.gic.ces.base.common.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionContext;

public class MailSendService {
	static Map<String, Object> mapSession;// サーブレットリクエスト
	Session session = null;
	List<Applicant> applicantList;

	@SuppressWarnings("unchecked")
	public List<Integer> getSearchCount(int jfYear, String examPlace,
			String sExamID, String eExamID, String rbt) throws SQLException {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Integer> searchList=new ArrayList<Integer>();
		boolean search = true;

		// 受験番号を入力する場合、JobFair年をチェクするためSQLを作成する
		if (!sExamID.equals("") && !sExamID.trim().isEmpty()
				&& !eExamID.equals("") && !eExamID.trim().isEmpty()) {
			int sJFYear = Integer.parseInt(sExamID.substring(2, 6));
			int eJFYear = Integer.parseInt(eExamID.substring(2, 6));
			String sExamPlace = sExamID.substring(6, 7).toUpperCase();
			String eExamPlace = eExamID.substring(6, 7).toUpperCase();

			if (sJFYear != jfYear || eJFYear != jfYear
					|| !sExamPlace.equals(examPlace.substring(0, 1))
					|| !eExamPlace.equals(examPlace.substring(0, 1))) {
				search = false;
			}
		} else {
			if (!sExamID.equals("") && !sExamID.trim().isEmpty()) {
				int sJFYear = Integer.parseInt(sExamID.substring(2, 6));
				String sExamPlace = sExamID.substring(6, 7).toUpperCase();
				if (sJFYear != jfYear
						|| !sExamPlace.equals(examPlace.substring(0, 1))) {
					search = false;
				}
			}
			if (!eExamID.equals("") && !eExamID.trim().isEmpty()) {
				int eJFYear = Integer.parseInt(eExamID.substring(2, 6));
				String eExamPlace = eExamID.substring(6, 7).toUpperCase();
				if (eJFYear != jfYear
						|| !eExamPlace.equals(examPlace.substring(0, 1))) {
					search = false;
				}
			}
		}

		if (search == true) {
			StringBuilder sb = new StringBuilder();
			if (rbt.equals("申請者")) {
				sb.append("SELECT app_ID FROM Applicant WHERE del_flag=0 AND app_ExamPlace=:examPlace AND app_JFYear=:jfYear");
				// 受験番号を入力する場合、入力した受験番号によって取得するためSQLを作成する
				if (!sExamID.equals("") && !sExamID.trim().isEmpty()
						&& !eExamID.equals("") && !eExamID.trim().isEmpty()) {
					sb.append(" AND exam_ID>=:sExamID AND exam_ID<=:eExamID");
				} else {
					if (!sExamID.equals("") && !sExamID.trim().isEmpty()) {
						sb.append(" AND exam_ID>=:sExamID ");
					}
					if (!eExamID.equals("") && !eExamID.trim().isEmpty()) {
						sb.append(" AND exam_ID<=:eExamID");
					}
					sb.append(" ORDER BY exam_ID");
				}
			} else if (rbt.equals("面接者")) {
				sb.append("SELECT interview_ID FROM Interview WHERE examinee.applicant.del_flag=0 AND examinee.applicant.app_ExamPlace=:examPlace AND examinee.applicant.app_JFYear=:jfYear ");

				// 受験番号を入力する場合、入力した受験番号によって取得するためSQLを作成する
				if (!sExamID.equals("") && !sExamID.trim().isEmpty()
						&& !eExamID.equals("") && !eExamID.trim().isEmpty()) {
					sb.append(" AND examinee.applicant.exam_ID>=:sExamID AND examinee.applicant.exam_ID<=:eExamID");
				} else {
					if (!sExamID.equals("") && !sExamID.trim().isEmpty()) {
						sb.append(" AND examinee.applicant.exam_ID>=:sExamID ");
					}
					if (!eExamID.equals("") && !eExamID.trim().isEmpty()) {
						sb.append(" AND examinee.applicant.exam_ID<=:eExamID");
					}
				}
				sb.append(" ORDER BY examinee.applicant.exam_ID");
			}

			Query queryResult = session.createQuery(sb.toString());
			queryResult.setParameter("jfYear", jfYear);
			queryResult.setParameter("examPlace", examPlace);

			// 受験番号を入力する場合、入力した受験番号をSQLに設定する
			if (!sExamID.equals("") && !eExamID.equals("")) {
				queryResult.setParameter("sExamID", sExamID);
				queryResult.setParameter("eExamID", eExamID);
			} else {
				if (!sExamID.equals("")) {
					queryResult.setParameter("sExamID", sExamID);
				}
				if (!eExamID.equals("")) {
					queryResult.setParameter("eExamID", eExamID);
				}
			}
			
			searchList=queryResult.list();
			
			
		}

		session.getTransaction().commit();
		session.close();
		return searchList;
	}
	
	public List<Applicant> getSearchList(List<Integer> searchList,String rbt){		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		applicantList = new ArrayList<Applicant>();
		try{
			if (rbt.equals("申請者")) {
				//applicantList = queryResult.list();
				for(int app_ID: searchList){
					StringBuilder sbApplicant=new StringBuilder("FROM Applicant where app_ID=:appID");
					Query appQuery = session.createQuery(sbApplicant.toString());
					appQuery.setParameter("appID",app_ID);										
					Applicant applicant=(Applicant)appQuery.uniqueResult();
					applicantList.add(applicant);
				}
			}
			if (rbt.equals("面接者")) {
				for(int interview_ID: searchList){
					StringBuilder sbInterview=new StringBuilder("FROM Interview where interview_ID=:interviewID");
					Query interviewQuery = session.createQuery(sbInterview.toString());
					interviewQuery.setParameter("interviewID",interview_ID);										
					Interview interview=(Interview)interviewQuery.uniqueResult();
					applicantList.add(interview.getExaminee().getApplicant());
				}
			}
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		session.getTransaction().commit();
		session.close();
		return applicantList;
	}

	/**
	 * 特定申請者IDによって面接者のデータをデータベースから取得する
	 * 
	 * @param id
	 * @return　申請者
	 */
	public Interview getInterviewData(int id) {
		session = HibernateUtil.getSessionFactory().openSession();
		Interview interview = new Interview();
		try {
			Query query = session
					.createQuery("FROM Interview WHERE examinee.applicant.app_ID=:id");
			query.setParameter("id", id);
			interview = (Interview) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			interview = null;
		} finally {
			session.close();
		}
		return interview;
	}

	/**
	 * 特定申請者IDによって申請者のデータをデータベースから取得する
	 * 
	 * @param id
	 * @return　申請者
	 */
	public Applicant getApplicantData(int id) {
		Applicant applicant = new Applicant();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session
					.createQuery("FROM Applicant WHERE app_ID=:id");
			query.setParameter("id", id);
			applicant = (Applicant) query.uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			applicant = null;
			session.getTransaction().rollback();
		}finally{
			session.close();			
		}
		return applicant;
	}

	/**
	 * メールを送るため設定する
	 */
	static Properties properties = new Properties();
	static {
//		properties.put("mail.smtp.host", "smtp.gmail.com");
//		properties.put("mail.smtp.starttls.enable", "true");
//		properties.put("mail.smtp.starttls.required", "true");
//		properties.put("mail.smtp.auth", "true");
//		properties.put("mail.smtp.port", "587");
//		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
//		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");
	}

	/**
	 * メールを送信する
	 * 
	 * @param mailSubject
	 * @param mailContent
	 * @param userMailAddress
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String sendMail(String mailSubject, String mailContent,
			String userMailAddress) {
		String mailStatus = "success";
		try {
			CommonUtility comUtility = new CommonUtility();
			Properties prop = comUtility.getValue("MailAddress.properties");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");

			javax.mail.Session session = javax.mail.Session.getDefaultInstance(
					properties, new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setSubject(mailSubject, "utf-8");

			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(userMailAddress));
			message.setContent(mailContent, "text/html; charset=utf-8");
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			mailStatus = "error";
		}
		return mailStatus;

	}

	/**
	 * 申請者にメールを送信する
	 * 
	 * @param 申請者
	 * @param JobFair場所
	 * @param JobFair日
	 * @return
	 */
	public String sentExamInfo(Applicant applicant, String jobFairPlace,
			String jobFairDate) {
		MailSendService mailService = new MailSendService();
		String mailSubject = "【Important_GIC】 Important Notices of Job Fair in Myanmar 2023";
		String mailContent = "";
		String msgYgn = "<html><head></head><body style='color:black;'>"
//				+ "<br> Dear " + "<b style='color:skyblue;'>"
//				+ applicant.getApp_Name()
//				+ "</b>"
				+ "<br>This email is an important notice and guide for those who have registered for Job Fair in Myanmar 2023 to be held on October 3,2023."
				+ "<br><br>"
				+ "Please check the contents below for how to participate on the day.<br>"
				+ "<br>************************<br>"
				+ "<br><b style='font-size:18px;color:skyblue;'>Job Fair Yangon 2023</b>"
				+ "<br>・Date：October 3, 2023"
				+ "<br>・Time：9:00am～12:05pm"
				+ "<br>・Place：LIVE from the following private Facebook group"
				+ "<br>・LIVE Group Name： <b style='color:green;'>【LIVE】Job Fair Yangon 2023</b>"
				+ "<br>・Group URL："
				+ "<br><a href='https://www.facebook.com/groups/475845297777538'>https://www.facebook.com/groups/475845297777538</a><br>"
				+ "<br>************************<br>"
				+ "<br><b style='font-size:18px;color:skyblue;'>&lt;Way to participate&gt;</b>"
				+ "<br>1. Click the Group URL (or) search for<b style='color:green;'>【LIVE】Job Fair Yangon 2023</b> and join the group."
				+ "<br>2. Please do not forget to join the private group by October 2."
				+ "<br>3. This group can be joined for only those who have applied the event and we strictly prohibit from sharing the link or inviting the group to other else."
				+ "<br>4. After company presentations, there will be 【Q&A】 sessions for each company."
				+ "<br><span style='color:red;'>※However, all questions cannot be answered due to time limit.<br>Thank you for understanding.</span>"
				+ "<br>5. The questions can be sent through LIVE comment box with below format. e.g. 【Question to GIC】Can I go to Japan immediately after hiring?"
				+ "<br><span style='color:red;'>＜Notice＞<br>Please use Unicode font. If you don’t have, please type in English.<br>If you speak Japanese, please type in Japanese.</span>"
				+ "<br><br>"
				+ "<b style='font-size:18px;color:skyblue;'>&lt;Participating companies&gt;</b>"
				+ "<br>The following companies are those you will meet in 「Job Fair Yangon 2023」 and hiring the new employment from this event."
				+ "<br>1. Computer Science Corporation（CSC）"
				+ "<br>2. IX Knowledge Inc.（IKI）"
				+ "<br>3. Socio Diversity Co., Ltd.（SD）"
				+ "<br>4. GIC Myanmar Co., Ltd.（GIC）"
				+ "<br><br>"
				+ "<br>If you have any other questions, please feel free to contact us through <a href='https://m.me/1446562002252719'>Job Fair in Myanmar Facebook Page’s messenger</a> or you can make a call to the following addresses."
				+ "<br>Thank you."
				+ "<br>"
				+ "<br>GIC Myanmar Co., Ltd. <span style='color:skyblue;'>(Yangon Branch):</span>"
				+ "<br>Yangon Head Office"
				+ "<br>No.67, Khaing Shwe Wah, Baho Road,"
				+ "<br>Kamayut Township, Yangon, Myanmar."
				+ "<br>09-972425310, 09-457086030"
				+ "</body></html>";
		
		String msgMdy = "<html><head></head><body style='color:black;'>"
//				+ "<br> Dear " + "<b style='color:skyblue;'>"
//				+ applicant.getApp_Name()
//				+ "</b>"
				+ "<br>This email is an important notice and guide for those who have registered for Job Fair in Myanmar 2023 to be held on October 3,2023."
				+ "<br><br>"
				+ "Please check the contents below for how to participate on the day.<br>"
				+ "<br>************************<br>"
				+ "<br><b style='font-size:18px;color:skyblue;'>Job Fair Mandalay 2023</b>"
				+ "<br>・Date：October 3, 2023"
				+ "<br>・Time：1:30pm～3:15pm"
				+ "<br>・Place：LIVE from the following private Facebook group"
				+ "<br>・LIVE Group Name： <b style='color:green;'>【LIVE】Job Fair Mandalay 2023</b>"
				+ "<br>・Group URL："
				+ "<br><a href='https://www.facebook.com/groups/768037661087675'>https://www.facebook.com/groups/768037661087675</a><br>"
				+ "<br>************************<br>"
				+ "<br><b style='font-size:18px;color:skyblue;'>&lt;Way to participate&gt;</b>"
				+ "<br>1. Click the Group URL (or) search for<b style='color:green;'>【LIVE】Job Fair Mandalay 2023</b> and join the group."
				+ "<br>2. Please do not forget to join the private group by October 2."
				+ "<br>3. This group can be joined for only those who have applied the event and we strictly prohibit from sharing the link or inviting the group to other else."
				+ "<br>4. After company presentations, there will be 【Q&A】 sessions for each company."
				+ "<br><span style='color:red;'>※However, all questions cannot be answered due to time limit.<br>Thank you for understanding.</span>"
				+ "<br>5. The questions can be sent through LIVE comment box with below format. e.g.【Question to GIC】Can I go to Japan immediately after hiring?"
				+ "<br><span style='color:red;'>＜Notice＞<br>Please use Unicode font. If you don’t have, please type in English.<br>If you speak Japanese, please type in Japanese.</span>"
				+ "<br><br>"
				+ "<b style='font-size:18px;color:skyblue;'>&lt;Participating companies&gt;</b>"
				+ "<br>The following companies are those you will meet in 「Job Fair Mandalay 2023」 and hiring the new employment from this event."
				+ "<br>1. Resonant Systems, Inc."
				+ "<br>2. GIC Myanmar Co., Ltd."
				+ "<br><br>"
				+ "<br>If you have any other questions, please feel free to contact us through <a href='https://m.me/1446562002252719'>Job Fair in Myanmar Facebook Page’s messenger</a> or you can make a call to the following addresses."
				+ "<br>Thank you."
				+ "<br>"
				+ "<br>GIC Myanmar Co., Ltd. <span style='color:skyblue;'>(Mandalay Branch):</span>"
				+ "<br>No.816, Between 62nd Street x 63rd Street, "
				+ "<br>Between 30th Street x 32nd Street, Myittar Street, Pyi Gyi Myat Shin Quarter,  "
				+ "<br>Chan Aye Thar San Township, Mandalay, Myanmar."
				+ "<br>09-42338-5177"
				+ "</body></html>";
		
		// YangonとMandalayを別に自動的メール設定
		if (applicant.getApp_ExamPlace().equals("Yangon")) mailContent = msgYgn;
		else mailContent = msgMdy;

		String mailStatus = mailService.sendMail(mailSubject, mailContent,
				applicant.getApp_Email());

		// 申請者に送ったメール記録を設定する
		if (mailStatus.equals("success")) {
			mapSession = ActionContext.getContext().getSession();
			MailRecord mailRecord = new MailRecord();
			Date date = new Date();
			String content = applicant.getExam_ID() + "に JobFair日:"
					+ jobFairDate + "とJobFair場所 :" + jobFairPlace + "を連絡する";
			mailRecord.setApplicant(applicant);
			mailRecord.setMail_Subject(mailSubject);
			mailRecord.setMail_Content(content);
			mailRecord.setMail_SendDate(date);
			RoleSetting roleSetting = (RoleSetting) mapSession
					.get("loggedInadmin");
			System.out.println("Role Setting: " + roleSetting.getRole_Permission());
			mailRecord.setRolesetting(roleSetting);
			mailService.saveMailRecord(mailRecord);
		}
		return mailStatus;
	}

	/**
	 * 面接者にメールを送信する
	 * 
	 * @param 面接者
	 * @return
	 */
	public String sentInterviewInfo(Interview interview) {
		MailSendService mailService = new MailSendService();
		String mailSubject = "Invitation to Interview for " + interview.getCompany().getCom_Lname();
		String mailContent = "interview mail ok";

		String mailStatus = mailService.sendMail(mailSubject, mailContent,
				interview.getExaminee().getApplicant().getApp_Email());

		// 面接者に送ったメール記録を設定する
		if (mailStatus.equals("success")) {
			mapSession = ActionContext.getContext().getSession();
			MailRecord mailRecord = new MailRecord();
			Date date = new Date();
			String content = interview.getExaminee().getApplicant()
					.getExam_ID()
					+ "に"
					+ "面接の場所 : "
					+ interview.getInterview_Place()
					+ "と時間 : "
					+ interview.getInterview_Date()
					+ " "
					+ interview.getInterview_StartTime() + "を連絡する";
			mailRecord.setApplicant(interview.getExaminee().getApplicant());
			mailRecord.setMail_Subject("面接情報アナウンス");
			mailRecord.setMail_Content(content);
			mailRecord.setMail_SendDate(date);
			RoleSetting roleSetting = (RoleSetting) mapSession
					.get("loggedInadmin");
			mailRecord.setRolesetting(roleSetting);
			mailService.saveMailRecord(mailRecord);
		}
		return mailStatus;
	}

	/**
	 * メールレコードをデータベースに保存する
	 * 
	 * @param メールレコード
	 * @return 無し
	 */
	public void saveMailRecord(MailRecord mailRecord) {

		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(mailRecord);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * 申請者のデータをデータをベースから取得する
	 * 
	 * @param 一覧に表示されている申請者リスト
	 * @return 申請者一覧
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<Applicant> applicantList(List<Integer> IDList)
			throws SQLException {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Applicant> appList = new ArrayList<Applicant>();
		if (IDList != null) {
			String query = "FROM Applicant WHERE app_ID IN (:IDList)";
			Query queryResult = session.createQuery(query);
			queryResult.setParameterList("IDList", IDList);
			appList = queryResult.list();
		}
		session.getTransaction().commit();
		session.close();
		return appList;
	}
}
