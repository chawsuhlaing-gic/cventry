/**
 * Ã¨ï¿½Â´Ã¦â€¡Ë†Ã¯Â¿Â½Ã¤Â»â„¢Ã¯Â½Â±Ã¯Â½Â¥Ã¨Â±ï¿½Ã¯Â½Â´Ã¯Â¿Â½Ã¯Â½Â¼Ã¯Â¿Â½2019/05/23 Jar Moon Taung
 * Ã¨ï¿½Â´Ã¦â€¡Ë†Ã¯Â¿Â½Ã¥â€¦Ë†Ã¯Â½Â¦Ã£â€šÅ Ã¯Â½Â¦Ã¯Â¿Â½Ã¯Â½Â¼Ã¥Â£Â½Ã§Å“Â Ã©Å¡â€¢Ã¤Â¸Ë†Ã¯Â½Â½Ã¦â€¡Ë†Ã¯Â¿Â½Ã¨Ë†Å’Ã¯Â¿Â½Ã¯Â¿Â½Ã©â‚¬â€¢Ã¯Â½Â³Ã©Å¡Â²Ã§Å½â€“Ã¯Â¿Â½Ã¯Â¿Â½Ã§Â¸ÂºÃ¯Â½Â«Ã§Â¹ï¿½Ã¯Â½Â¡Ã§Â¹ï¿½Ã¯Â½Â¼Ã§Â¹ï¿½Ã¯Â½Â«Ã©Â¨Â¾Ã¢Ë†Â½Ã¯Â½Â¿Ã¯Â½Â¡Ã¨Å“Æ’Ã¯Â½Â¦Ã©â‚¬â€¦Ã¯Â¿Â½
 * 
 * Ã¨Â­â€“Ã¯Â½Â´Ã¨Â­ï¿½Ã¯Â½Â°Ã¨Å¾Â»Ã¯Â½Â¥Ã¨Â±ï¿½Ã¯Â½Â´Ã¯Â¿Â½Ã¯Â½Â¼Ã¯Â¿Â½ 
 * Ã¨Â­â€“Ã¯Â½Â´Ã¨Â­ï¿½Ã¯Â½Â°Ã¨Â®Å½Ã£â€šÅ Ã¯Â½Â¦Ã¯Â¿Â½Ã¯Â½Â¼Ã¥Â£Â½Ã§Å“Â Ã©Å¡â€¢Ã¤Â¸Ë†Ã¯Â½Â½Ã¦â€¡Ë†Ã¯Â¿Â½Ã¯Â¿Â½,Ã§Â¸Â²Ã¯Â¿Â½
 */

package mm.com.gic.ces.application.controller.admin;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import mm.com.gic.ces.application.model.Dump;
import mm.com.gic.ces.base.service.user.HomeViewService;

//import mm.com.gic.ces.base.common.CommonService;

public class SendMailAction {
	
	private String mailStatus;                    //Ã§Â¹ï¿½Ã¯Â½Â¡Ã§Â¹ï¿½Ã¯Â½Â¼Ã§Â¹ï¿½Ã¯Â½Â«Ã©Â¨Â¾Ã¢Ë†Â½Ã¯Â½Â¿Ã¯Â½Â¡Ã§Â¹ï¿½Ã¦â€šÂ¶ÃŽâ€ºÃ§Â¹ï¿½Ã¯Â¿Â½Ã§Â¹Â§Ã¯Â½Â¯
	HomeViewService hv = new HomeViewService();
	Dump dump_data;
	String dumpExamTimeYear;										//Exam Time Year
	String dumpYgnExamTimeMonth;									//Yangon Exam Time Month
	String dumpYgnExamTimeDay;										//Yangon Exam Time Day
	String dumpYgnExamTimeAlias;									//Yangon Exam Time Alias
	String dumpYgnExamTimeDate;										//Yangon Exam Time Date
	String dumpYgnExamPlace;										//Yangon Exam Place
	String dumpMdyExamTimeMonth;									//Mandalay Exam Time Month
	String dumpMdyExamTimeDay;										//Mandalay Exam Time Day
	String dumpMdyExamTimeAlias;									//Mandalay Exam Time Alias
	String dumpMdyExamTimeDate;										//Mandalay Exam Time Date
	String dumpMdyExamPlace;
	String dumpExamInformStartDate;									//To inform Exam Start Date
	String dumpExamInformEndDate;                                   //To inform Exam End Date
	DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d.MM.yyyy", Locale.ENGLISH);
	
	public void loadDumpData() {
        try {
            dump_data = hv.getDumpData();
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	
	/***
     * Mail Port Registration
     * @return Ã¨Â¾Å¸Ã¯Â½Â¡Ã§Â¸ÂºÃ¯Â¿Â½
     * @throws  
     */
	static Properties properties = new Properties();
	static {
		 /*properties.put("mail.smtp.host", "smtp.gmail.com");
		 properties.put("mail.smtp.starttls.enable", "true");
		 properties.put("mail.smtp.starttls.required", "true");
		 properties.put("mail.smtp.auth", "true");
		 properties.put("mail.smtp.port", "587");
		 properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		 properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		 properties.put("mail.debug","true");*/
		
		
		/*
		 * properties.put("mail.smtp.host", "email-smtp.ap-northeast-1.amazonaws.com");
		 * properties.put("mail.smtp.starttls.enable", "true");
		 * properties.put("mail.smtp.starttls.required", "true");
		 * properties.put("mail.smtp.auth", "true"); properties.put("mail.smtp.port",
		 * "587"); properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		 * properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		 * properties.put("mail.debug","true");
		 */
		
		properties.put("mail.smtp.host", "email-smtp.ap-northeast-1.amazonaws.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.starttls.required", "true");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		properties.put("mail.smtp.ssl.trust", "email-smtp.ap-northeast-1.amazonaws.com");
		properties.put("mail.debug", "true");
		 
		 
		/*
		 * MAIL_HOST: "email-smtp.ap-northeast-1.amazonaws.com" MAIL_PORT: "587"
		 * MAIL_USERNAME: "AKIATZU4H4YRUHT2SYVM" MAIL_PASSWORD:
		 * "BMAOpC8eSIH6SEdZQ2L61tH3iF8s2DdPk4genSqllrf9" MAIL_ENCRYPTION: "TLS"
		 * MAIL_FROM_ADDRESS: "noreply@gicjp.com" MAIL_FROM_NAME: "LASHIC少額短期保険株式会社"
		 */
//		properties.put("mail.smtp.host", "smtp.gmail.com");
//		properties.put("mail.smtp.socketFactory.port", "465");
//		properties.put("mail.smtp.socketFactory.class",
//				"javax.net.ssl.SSLSocketFactory");
//		properties.put("mail.smtp.auth", "true");
//		properties.put("mail.smtp.port", "465");
//		properties.put("mail.debug","true");
	}	
	
	/***
     * Sent Mail to Applicant
     * @return Ã¨Â¾Å¸Ã¯Â½Â¡Ã§Â¸ÂºÃ¯Â¿Â½
     * @throws 
     */
	public static String formatDateRange(LocalDate start, LocalDate end) {
        String startDay = getDayWithSuffix(start.getDayOfMonth());
        String endDay = getDayWithSuffix(end.getDayOfMonth());

        String startMonth = start.getMonth().name().substring(0, 1) + start.getMonth().name().substring(1).toLowerCase();
        String endMonth = end.getMonth().name().substring(0, 1) + end.getMonth().name().substring(1).toLowerCase();

        startMonth = startMonth.substring(0, 3); // e.g., October -> Oct
        endMonth = endMonth.substring(0, 3);

        if (start.getYear() == end.getYear()) {
            if (start.getMonth() == end.getMonth()) {
                return String.format("%s %s to %s %s, %d", startDay, startMonth, endDay, endMonth, start.getYear());
            } else {
                return String.format("%s %s to %s %s, %d", startDay, startMonth, endDay, endMonth, start.getYear());
            }
        } else {
            return String.format("%s %s, %d to %s %s, %d", startDay, startMonth, start.getYear(), endDay, endMonth, end.getYear());
        }
    }

    public static String getDayWithSuffix(int day) {
        if (day >= 11 && day <= 13) {
            return day + "th";
        }
        switch (day % 10) {
            case 1:  return day + "st";
            case 2:  return day + "nd";
            case 3:  return day + "rd";
            default: return day + "th";
        }
    }
	
	public String sendMail(String examID, String app_Name, String app_ExamPlace,
			String app_email) {
		// TODO Auto-generated method stub	
//		CommonService comService=new CommonService();
//		String jobFairDate=comService.getJobFairDate(app_ExamPlace);
//		String jobFairPlace=comService.getJobFairPlace(app_ExamPlace);
		loadDumpData();
		
		dumpExamTimeYear = dump_data.getDumpExamTimeYear();
    	dumpYgnExamTimeMonth = dump_data.getDumpYgnExamTimeMonth();
    	dumpYgnExamTimeDay = dump_data.getDumpYgnExamTimeDay();
    	dumpYgnExamTimeDate = dump_data.getDumpYgnExamTimeDate();
    	dumpYgnExamPlace = dump_data.getDumpYgnExamPlace();
    	dumpMdyExamTimeMonth = dump_data.getDumpMdyExamTimeMonth();
    	dumpMdyExamTimeDay = dump_data.getDumpMdyExamTimeDay();
    	dumpMdyExamTimeDate = dump_data.getDumpMdyExamTimeDate();
    	dumpMdyExamPlace = dump_data.getDumpMdyExamPlace();
    	dumpExamInformStartDate = dump_data.getDump_ExamInformStartDate();
    	dumpExamInformEndDate = dump_data.getDump_ExamInformEndDate();
    	
        LocalDate examInformStartDate = LocalDate.parse(dumpExamInformStartDate, inputFormatter);
        LocalDate examInformEndDate = LocalDate.parse(dumpExamInformEndDate, inputFormatter);
    	String formattedDate = formatDateRange(examInformStartDate, examInformEndDate);
		
		try {

			/*
			 * javax.mail.Session session = Session.getDefaultInstance(properties, new
			 * javax.mail.Authenticator() { protected PasswordAuthentication
			 * getPasswordAuthentication() { return new PasswordAuthentication(
			 * "giccvrecruitteam@gmail.com", "uuaoagollyqqxhuj"); } });
			 */
			
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			    protected PasswordAuthentication getPasswordAuthentication() {
			        return new PasswordAuthentication("AKIATZU4H4YRUHT2SYVM", "BMAOpC8eSIH6SEdZQ2L61tH3iF8s2DdPk4genSqllrf9");
			    }
			});

			/*
			 * MimeMessage message = new MimeMessage(session); message.setFrom(new
			 * InternetAddress("giccvrecruitteam@gmail.com"));
			 * message.setSubject("Welcome to Job Fair in Myanmar "+dumpExamTimeYear,
			 * "utf-8");
			 */
			
				String msg = "";
				String msgYgn = "<html><head></head><body>"
						+ "<b style='color:green;font-size: 18px;'><h1>Global Innovation Consulting Inc.</h1></b>\n"
						+ "<br> Dear "
						+ app_Name
						+ ",<br><br>"
						+ "Thank you for taking time to participate our <b style='color:skyblue;'> Job Fair in Myanmar "+dumpExamTimeYear+"</b>. <br>"
						+ "According to our program, you are cordially welcomed to take IQ test with the following ID. <br> "
						+ "<span style='color:red;'>We will not notice your ID again so please do not forget to take a note of it. </span><br>"
						+ "<br>**************"
						+ "<br> <b style='color:skyblue;font-size: 15px;'>Exam ID: </b> "
						+ examID
						+ "<br> <b style='color:skyblue;font-size: 15px;'>JobFair Date: "+dumpYgnExamTimeMonth+" "+dumpYgnExamTimeDay+","+dumpExamTimeYear+" ( "+dumpYgnExamTimeDate+" ) </b> "
						+ "<br> <b style='color:skyblue;font-size: 15px;'>Exam Place: "+dumpYgnExamPlace+"</b> " 
					//	+ app_ExamPlace	
					//	+ "<br> <b style='color:skyblue;font-size: 15px;'>JobFair Place: </b> " //(31.8.2022) removing Place and date due to job fair being online
					//  + jobFairPlace	
					    
					//	+ jobFairDate	
						+ "<br>**************"
						+ "<br><br>"
						+ "The exam is scheduled for <span style='color:red;'>"+dumpYgnExamTimeMonth+" "+dumpYgnExamTimeDay+", "+dumpExamTimeYear+". </span><br>"
						+ "We will inform you details regarding the exam time between "+formattedDate+". <br>"
						+ "<br>We will also announce from the <b><a href='https://www.facebook.com/JobFairInMyanmar/'> Job Fair in Myanmar</a></b> Facebook Page.<br>"
						+ "So, please make sure to check your email and <b><a href='https://www.facebook.com/JobFairInMyanmar/'> Job Fair in Myanmar</a></b> Facebook Page frequently. <br>"
						+ "<br>Thank you again for your interest in Job Fair in Myanmar "+dumpExamTimeYear+"."
						+ "We hope to see you on job fair day.<br><br>"
						+ "Kind regards,<br>"
						+ "<b>Global Innovation Consulting Inc.,</b>"
						+ "<br><br>"
						+ "<b style='color:skyblue;'>Yangon Branch : </b>"
						+ "<br>Room B PH4, Myawaddy Luxury Bank Complex, Corner of Bogyoke Aung San Road & Warden St, <br>"
						+ "Lanmadaw Township, Yangon, Myanmar. <br>"
						+ "Tel : 09-457086030, 09-972425310" + "</body></html>";
				
				String msgMdy = "<html><head></head><body>"
						+ "<b style='color:green;font-size: 18px;'><h1>Global Innovation Consulting Inc.</h1></b>\n"
						+ "<br> Dear "
						+ app_Name
						+ ",<br><br>"
						+ "Thank you for taking time to participate our <b style='color:skyblue;'> Job Fair in Myanmar "+dumpExamTimeYear+"</b>. <br>"
						+ "According to our program, you are cordially welcomed to take IQ test with the following ID. <br> "
						+ "<span style='color:red;'>We will not notice your ID again so please do not forget to take a note of it. </span><br>"
						+ "<br>**************"
						+ "<br> <b style='color:skyblue;font-size: 15px;'>Exam ID: </b> "
						+ examID
						+ "<br> <b style='color:skyblue;font-size: 15px;'>JobFair Date: "+dumpMdyExamTimeMonth+" "+dumpMdyExamTimeDay+","+dumpExamTimeYear+" ( "+dumpMdyExamTimeDate+" ) </b> "
						+ "<br> <b style='color:skyblue;font-size: 15px;'>Exam Place: "+dumpMdyExamPlace+"</b> " 
						+ "<br>**************"
						+ "<br><br>"
						+ "The exam is scheduled for <span style='color:red;'>"+dumpMdyExamTimeMonth+" "+dumpMdyExamTimeDay+", "+dumpExamTimeYear+". </span><br>"
						+ "We will inform you details regarding the exam time between "+formattedDate+". <br>"
						+ "<br>We will also announce from the <b><a href='https://www.facebook.com/JobFairInMyanmar/'> Job Fair in Myanmar</a></b> Facebook Page.<br>"
						+ "So, please make sure to check your email and <b><a href='https://www.facebook.com/JobFairInMyanmar/'> Job Fair in Myanmar</a></b> Facebook Page frequently. <br>"
						+ "<br>Thank you again for your interest in Job Fair in Myanmar "+dumpExamTimeYear+"."
						+ "We hope to see you on job fair day.<br><br>"
						+ "Kind regards,<br>"
						+ "<b>Global Innovation Consulting Inc.,</b>"
						+ "<br><br>"
						+ "<b style='color:skyblue;'>Mandalay Branch : </b>"
						+ "<br>Block(6-11), Mingalar Mandalay, 73rd Street, <br>"
						+ "Between Mingalar 1st Street x 2nd Street, <br>"
						+ "Myothit Quarter(1), Chanmyatharzi Township, <br>"
						+ "Mandalay, Myanmar. <br>"
						+ "Tel : 09-423385177 " + "</body></html>";
				
			/*
			 * message.setRecipients(Message.RecipientType.TO,
			 * InternetAddress.parse(app_email));
			 */
				
				// YangonÃƒÂ§Ã‚Â¸Ã‚ÂºÃƒÂ¯Ã‚Â½Ã‚Â¨MandalayÃƒÂ§Ã‚Â¹Ã‚Â§ÃƒÂ¨Ã‚Â²Ã…Â¾ÃƒÂ¦Ã…â€™Ã‚Â¨ÃƒÂ§Ã‚Â¸Ã‚ÂºÃƒÂ¯Ã‚Â½Ã‚Â«ÃƒÂ©Ã¢â‚¬â€œÃ‚Â¾ÃƒÂ¯Ã‚Â½Ã‚ÂªÃƒÂ¨Ã…â€œÃ¯Â¿Â½ÃƒÂ¦Ã¢â‚¬Â¦Ã¢â‚¬Â¢ÃƒÂ¥Ã¢â€žÂ¢Ã‚ÂªÃƒÂ§Ã‚Â¹Ã¯Â¿Â½ÃƒÂ¯Ã‚Â½Ã‚Â¡ÃƒÂ§Ã‚Â¹Ã¯Â¿Â½ÃƒÂ¯Ã‚Â½Ã‚Â¼ÃƒÂ§Ã‚Â¹Ã¯Â¿Â½ÃƒÂ¯Ã‚Â½Ã‚Â«ÃƒÂ©Ã…Â¡Ã‚ÂªÃƒÂ¯Ã‚Â½Ã‚Â­ÃƒÂ¨Ã…Â¾Ã‚Â³ÃƒÂ¯Ã‚Â¿Ã‚Â½
				if (app_ExamPlace.equals("Yangon")) msg = msgYgn;
				else msg = msgMdy;
				
			/*
			 * message.setContent(msg, "text/html; charset=utf-8"); Transport.send(message);
			 * mailStatus = "mailSuccess";
			 */
			/*
			 * try { Message message = new MimeMessage(session);
			 * 
			 */     MimeMessage message = new MimeMessage(session);
					//message.setFrom(new InternetAddress("giccvrecruitteam@gmail.com"));
					message.setSubject("Welcome to Job Fair in Myanmar "+dumpExamTimeYear, "utf-8");
				    message.setFrom(new InternetAddress("noreply@gicjp.com", "Welcome to Job Fair in Myanmar "+dumpExamTimeYear, "utf-8"));
				    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(app_email));
				    //message.setSubject("Test Email");
				    //message.setText(msg);
				    message.setContent(msg, "text/html; charset=utf-8");

				    Transport.send(message);
				    mailStatus = "mailSuccess";
				    System.out.println("Email sent successfully!");
				} catch (Exception e) {
					mailStatus = "mailError";
				    e.printStackTrace();
				}
			
	/*	}
		catch (Exception e) {
			mailStatus = "mailError";
			e.printStackTrace();*/
	//	}
		return mailStatus;
	}
}
