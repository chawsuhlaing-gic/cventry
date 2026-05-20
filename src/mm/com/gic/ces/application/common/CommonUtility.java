/**
 * 
 *  Enumã€€ãƒ‡ãƒ¼ã‚¿	
 *	ä½œæˆ�å±¥æ­´ï¼š2019/01/22 Jar Moon Taungã€€+ Khin Myo Wai		
 *	ä½œæˆ�æ¦‚è¦�ï¼šæ–°è¦�ä½œæˆ�ã€€ç™»éŒ²å…±é€šå‡¦ç�†
 * 
 *  æ›´æ–°å±¥æ­´ï¼šdd/mm/yyyy name	
 *  æ›´æ–°æ¦‚è¦�ï¼šXXXXXXXX
 *
 */

package mm.com.gic.ces.application.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpSession;
import mm.com.gic.ces.application.property.*;
import mm.com.gic.ces.base.common.CommonService;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CommonUtility {
	public static String filePath = ServletActionContext.getServletContext()
			.getRealPath("/") + "images" + "\\";
	public static ActionSupport acionClass;
	static Map<String, Object> mapSession = ActionContext.getContext()
			.getSession();
	HttpSession session = ServletActionContext.getRequest().getSession();
	CommonService comService = new CommonService();       //å‡¦ç�†

	/**
     * ...propertiesãƒ•ã‚¡ã‚¤ãƒ«ã‚’èª­ã‚€...
     * @param propFileName propertiesãƒ•ã‚¡ã‚¤ãƒ«å��
     * @return prop properties
     * @throws IOException
     */
	public static Properties getValue(String propFileName) throws IOException {

		try (InputStream inputStream = CommonUtility.class.getClassLoader().getResourceAsStream(propFileName);) {
			Properties prop = new Properties();
			if (inputStream != null) {
				prop.load(new InputStreamReader(inputStream, "UTF8"));
			} else {
				throw new FileNotFoundException("Property file '" + propFileName + "' not found in the classpath");
			}
			return prop;
		}
	}
	/***
	 * ä½œæˆ�å±¥æ­´ï¼š04/04/2019 Khin Myo Wai
	 * ä½œæˆ�æ¦‚è¦�ï¼šå�—é¨“æ‰€ãƒ€ã‚¦ãƒ³ãƒ­ãƒ¼ãƒ‰
	 * 
	 * æ›´æ–°å±¥æ­´ï¼šdd/mm/yyyy name 
	 * æ›´æ–°æ¦‚è¦�ï¼šXXXXXXXX
	 */
	public static List<ExamPlace> getExamPlace() throws IOException {
		Properties prop;
			prop = CommonUtility.getValue("ExamPlace.properties");
			List<ExamPlace> lstExamPlace = new ArrayList<ExamPlace>();
			for (int i = 1; i <= prop.size(); i++) {
				ExamPlace examPlace = new ExamPlace();
				examPlace.setExamPlaceID((prop.getProperty(Integer.toString(i))));
				examPlace.setExamPlace((prop.getProperty(Integer.toString(i))));
				lstExamPlace.add(examPlace);
			}
			return 	lstExamPlace;
	}

	/***
	 * ä½œæˆ�å±¥æ­´ï¼š04/04/2019 Jar Moon Taung 
	 * ä½œæˆ�æ¦‚è¦�ï¼š
	 * 
	 * æ›´æ–°å±¥æ­´ï¼šdd/mm/yyyy name 
	 * æ›´æ–°æ¦‚è¦�ï¼šXXXXXXXX
	 * 
	 */
	public List<NRCCity> getNRCCityCombo() throws IOException {
		Properties prop = CommonUtility.getValue("NRCCity.properties");
		List<NRCCity> lstNRCCity = new ArrayList<NRCCity>();
		for (int i = 1; i <= prop.size(); i++) {
			NRCCity nrcCity = new NRCCity();
			nrcCity.setNrcCityID(Integer.toString(i));
			nrcCity.setNrcCity((prop.getProperty(Integer.toString(i))));
			lstNRCCity.add(nrcCity);		
		}
		
		return lstNRCCity;

	}

	/***
	 * ä½œæˆ�å±¥æ­´ï¼š04/04/2019 Jar Moon Taung 
	 * ä½œæˆ�æ¦‚è¦�ï¼š
	 * 
	 * æ›´æ–°å±¥æ­´ï¼šdd/mm/yyyy name 
	 * æ›´æ–°æ¦‚è¦�ï¼šXXXXXXXX
	 * 
	 */
	public List<NRCType> getNRCTypeCombo() throws IOException {
		Properties prop = CommonUtility.getValue("NRCType.properties");
		List<NRCType> lstNRCType = new ArrayList<NRCType>();
		for (int i = 1; i <= prop.size(); i++) {
			NRCType nrcType = new NRCType();
			nrcType.setNrcTypeID(Integer.toString(i));
			nrcType.setNrcType((prop.getProperty(Integer.toString(i))));
			lstNRCType.add(nrcType);
		}
		return lstNRCType;

	}

	/***
	 * ä½œæˆ�å±¥æ­´ï¼š04/04/2019 Khin Myo Wai
	 * ä½œæˆ�æ¦‚è¦�ï¼š
	 * 
	 * æ›´æ–°å±¥æ­´ï¼šdd/mm/yyyy name 
	 * æ›´æ–°æ¦‚è¦�ï¼šXXXXXXXX
	 * 
	 */
	public List<CityofUniversity> getCityOfUniversityCombo() throws IOException {
		Properties prop = CommonUtility.getValue("CityofUniversity.properties");
		List<CityofUniversity> lstCityofUniversity = new ArrayList<CityofUniversity>();
		for (int i = 1; i <= prop.size(); i++) {
			CityofUniversity cityOfUniversity = new CityofUniversity();
			cityOfUniversity.setCityofUniID(Integer.toString(i));
			cityOfUniversity.setCityofUniName((prop.getProperty(Integer
					.toString(i))));
			lstCityofUniversity.add(cityOfUniversity);
		}
		return lstCityofUniversity;

	}

	/***
	 * ä½œæˆ�å±¥æ­´ï¼š04/04/2019 Jar Moon Taung
	 * ä½œæˆ�æ¦‚è¦�ï¼š
	 * 
	 * æ›´æ–°å±¥æ­´ï¼šdd/mm/yyyy name 
	 * æ›´æ–°æ¦‚è¦�ï¼šXXXXXXXX
	 * 
	 */
	public String getygnUniCombo() throws IOException {
		Properties prop = CommonUtility.getValue("Yangon.properties");
		StringBuilder yangon = new StringBuilder();
		yangon.append("[");
		for (int i = 1; i < prop.size(); i++) {
			yangon.append("{UniID:" + i + ",UniName:'"
					+ (prop.getProperty(Integer.toString(i))) + "'},");
		}
		yangon.append("{UniID:" + (prop.size()) + ",UniName:'"
				+ (prop.getProperty(Integer.toString(prop.size()))) + "'}]");

		String lstygnUni = yangon.toString();
		return lstygnUni;

	}
	
	/*Mandalay University Type Combo Data*/
	public  String getMdyUniCombo() throws IOException{
		Properties prop= CommonUtility.getValue("Mandalay.properties");
		StringBuilder mandalay= new StringBuilder();
		mandalay.append("[");
		for (int i=1;i < prop.size(); i++){
			mandalay.append("{UniID:" + i + 
					",UniName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		mandalay.append("{UniID:" + (prop.size()) +
				",UniName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstmdyUni=mandalay.toString();
		return lstmdyUni;
	}
	
	/*Ayeyarwaddy University Type Combo Data*/
	public String getAyaUniCombo() throws IOException{
		Properties prop= CommonUtility.getValue("Ayeyarwaddy.properties");
		StringBuilder ayeyarwaddy= new StringBuilder();
		ayeyarwaddy.append("[");
		for (int i=1;i < prop.size(); i++){
			ayeyarwaddy.append("{UniID:" + i + 
					",UniName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		ayeyarwaddy.append("{UniID:" + (prop.size()) +
				",UniName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstayaUni=ayeyarwaddy.toString();
		return lstayaUni;
	}

	/*Bago University Type Combo Data*/
	public String getBagoUniCombo() throws IOException{
		Properties prop= CommonUtility.getValue("Bago.properties");
		StringBuilder bago= new StringBuilder();
		bago.append("[");
		for (int i=1;i < prop.size(); i++){
			bago.append("{UniID:" + i + 
					",UniName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		bago.append("{UniID:" + (prop.size()) +
				",UniName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstbagoUni=bago.toString();
		return lstbagoUni;
	}

	/*Kachin University Type Combo Data*/
	public String getKachinUniCombo() throws IOException{
		Properties prop= CommonUtility.getValue("Kachin.properties");
		StringBuilder kachin= new StringBuilder();
		kachin.append("[");
		for (int i=1;i < prop.size(); i++){
			kachin.append("{UniID:" + i + 
					",UniName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		kachin.append("{UniID:" + (prop.size()) +
				",UniName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstkachinUni=kachin.toString();
		return lstkachinUni;
	}

	/*Kayah University Type Combo Data*/
	public String getKayahUniCombo() throws IOException{
		Properties prop= CommonUtility.getValue("Kayah.properties");
		StringBuilder kayah= new StringBuilder();
		kayah.append("[");
		for (int i=1;i < prop.size(); i++){
			kayah.append("{UniID:" + i + 
					",UniName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		kayah.append("{UniID:" + (prop.size()) +
				",UniName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstkayahUni=kayah.toString();
		return lstkayahUni;
	}

	/*Kayin University Type Combo Data*/
	public String getKayinUniCombo() throws IOException{
		Properties prop= CommonUtility.getValue("Kayin.properties");
		StringBuilder kayin= new StringBuilder();
		kayin.append("[");
		for (int i=1;i < prop.size(); i++){
			kayin.append("{UniID:" + i + 
					",UniName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		kayin.append("{UniID:" + (prop.size()) +
				",UniName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstkayinUni=kayin.toString();
		return lstkayinUni;
	}

	/*Mon University Type Combo Data*/
	public  String getMonUniCombo() throws IOException{
		Properties prop= CommonUtility.getValue("Mon.properties");
		StringBuilder mon= new StringBuilder();
		mon.append("[");
		for (int i=1;i < prop.size(); i++){
			mon.append("{UniID:" + i + 
					",UniName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		mon.append("{UniID:" + (prop.size()) +
				",UniName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstmonUni=mon.toString();
		return lstmonUni;
	}

	/*Rakhine University Type Combo Data*/
	public String getRakhineUniCombo() throws IOException{
		Properties prop= CommonUtility.getValue("Rakhine.properties");
		StringBuilder rakhine= new StringBuilder();
		rakhine.append("[");
		for (int i=1;i < prop.size(); i++){
			rakhine.append("{UniID:" + i + 
					",UniName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		rakhine.append("{UniID:" + (prop.size()) +
				",UniName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstrakhineUni=rakhine.toString();
		return lstrakhineUni;
	}

	/*Shan University Type Combo Data*/
	public  String getShanUniCombo() throws IOException{
		Properties prop= CommonUtility.getValue("Shan.properties");
		StringBuilder shan= new StringBuilder();
		shan.append("[");
		for (int i=1;i < prop.size(); i++){
			shan.append("{UniID:" + i + 
					",UniName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		shan.append("{UniID:" + (prop.size()) +
				",UniName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstshanUni=shan.toString();
		return lstshanUni;
	}

	/*Thanintharyi University Type Combo Data*/
	public String getThanintharyiUniCombo() throws IOException{
		Properties prop= CommonUtility.getValue("Thanintharyi.properties");
		StringBuilder thanintharyi= new StringBuilder();
		thanintharyi.append("[");
		for (int i=1;i < prop.size(); i++){
			thanintharyi.append("{UniID:" + i + 
					",UniName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		thanintharyi.append("{UniID:" + (prop.size()) +
				",UniName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstthanintharyiUni=thanintharyi.toString();
		return lstthanintharyiUni;
	}

	/*Sagaing University Type Combo Data*/
	public  String getSagaingUniCombo() throws IOException{
		Properties prop= CommonUtility.getValue("Sagaing.properties");
		StringBuilder sagaing= new StringBuilder();
		sagaing.append("[");
		for (int i=1;i < prop.size(); i++){
			sagaing.append("{UniID:" + i + 
					",UniName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		sagaing.append("{UniID:" + (prop.size()) +
				",UniName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstsagaingUni=sagaing.toString();
		return lstsagaingUni;
	}

	/*Magway University Type Combo Data*/
	public  String getMagwayUniCombo() throws IOException{
		Properties prop= CommonUtility.getValue("Magway.properties");
		StringBuilder magway= new StringBuilder();
		magway.append("[");
		for (int i=1;i < prop.size(); i++){
			magway.append("{UniID:" + i + 
					",UniName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		magway.append("{UniID:" + (prop.size()) +
				",UniName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstmagwayUni=magway.toString();
		return lstmagwayUni;
	}


	/***
	 * ä½œæˆ�å±¥æ­´ï¼š18/04/2019 Jar Moon Taung
	 * ä½œæˆ�æ¦‚è¦�ï¼š
	 * 
	 * æ›´æ–°å±¥æ­´ï¼šdd/mm/yyyy name 
	 * æ›´æ–°æ¦‚è¦�ï¼šXXXXXXXX
	 * 
	 */
	public String getNRCOneCombo() throws IOException {
		Properties prop = CommonUtility.getValue("NRCOne.properties");
		StringBuilder nrcOne = new StringBuilder();
		nrcOne.append("[");
		for (int i = 1; i < prop.size(); i++) {
			nrcOne.append("{NRCID:" + i + ",NRCName:'"
					+ (prop.getProperty(Integer.toString(i))) + "'},");
		}
		nrcOne.append("{NRCID:" + (prop.size()) + ",NRCName:'"
				+ (prop.getProperty(Integer.toString(prop.size()))) + "'}]");

		String lstnrcOne = nrcOne.toString();
		return lstnrcOne;

	}
	
	/*NRCTwo Type Combo Data*/
	public  String getNRCTwoCombo() throws IOException{
		Properties prop= CommonUtility.getValue("NRCTwo.properties");
		StringBuilder NRCTwo= new StringBuilder();
		NRCTwo.append("[");
		for (int i=1;i < prop.size(); i++){
			NRCTwo.append("{NRCID:" + i + 
					",NRCName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		NRCTwo.append("{NRCID:" + (prop.size()) +
				",NRCName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstNRCTwo=NRCTwo.toString();
		return lstNRCTwo;
	}

	/*NRCThree Type Combo Data*/
	public  String getNRCThreeCombo() throws IOException{
		Properties prop= CommonUtility.getValue("NRCThree.properties");
		StringBuilder NRCThree= new StringBuilder();
		NRCThree.append("[");
		for (int i=1;i < prop.size(); i++){
			NRCThree.append("{NRCID:" + i + 
					",NRCName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		NRCThree.append("{NRCID:" + (prop.size()) +
				",NRCName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstNRCThree=NRCThree.toString();
		return lstNRCThree;
	}

	/*NRCFour Type Combo Data*/
	public String getNRCFourCombo() throws IOException{
		Properties prop= CommonUtility.getValue("NRCFour.properties");
		StringBuilder NRCFour= new StringBuilder();
		NRCFour.append("[");
		for (int i=1;i < prop.size(); i++){
			NRCFour.append("{NRCID:" + i + 
					",NRCName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		NRCFour.append("{NRCID:" + (prop.size()) +
				",NRCName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstNRCFour=NRCFour.toString();
		return lstNRCFour;
	}

	/*NRCFive Type Combo Data*/
	public String getNRCFiveCombo() throws IOException{
		Properties prop= CommonUtility.getValue("NRCFive.properties");
		StringBuilder NRCFive= new StringBuilder();
		NRCFive.append("[");
		for (int i=1;i < prop.size(); i++){
			NRCFive.append("{NRCID:" + i + 
					",NRCName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		NRCFive.append("{NRCID:" + (prop.size()) +
				",NRCName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstNRCFive=NRCFive.toString();
		return lstNRCFive;
	}

	/*NRCSix Type Combo Data*/
	public  String getNRCSixCombo() throws IOException{
		Properties prop= CommonUtility.getValue("NRCSix.properties");
		StringBuilder NRCSix= new StringBuilder();
		NRCSix.append("[");
		for (int i=1;i < prop.size(); i++){
			NRCSix.append("{NRCID:" + i + 
					",NRCName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		NRCSix.append("{NRCID:" + (prop.size()) +
				",NRCName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstNRCSix=NRCSix.toString();
		return lstNRCSix;
	}

	/*NRCSeven Type Combo Data*/
	public String getNRCSevenCombo() throws IOException{
		Properties prop= CommonUtility.getValue("NRCSeven.properties");
		StringBuilder NRCSeven= new StringBuilder();
		NRCSeven.append("[");
		for (int i=1;i < prop.size(); i++){
			NRCSeven.append("{NRCID:" + i + 
					",NRCName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		NRCSeven.append("{NRCID:" + (prop.size()) +
				",NRCName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstNRCSeven=NRCSeven.toString();
		return lstNRCSeven;
	}

	/*NRCEight Type Combo Data*/
	public String getNRCEightCombo() throws IOException{
		Properties prop= CommonUtility.getValue("NRCEight.properties");
		StringBuilder NRCEight= new StringBuilder();
		NRCEight.append("[");
		for (int i=1;i < prop.size(); i++){
			NRCEight.append("{NRCID:" + i + 
					",NRCName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		NRCEight.append("{NRCID:" + (prop.size()) +
				",NRCName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstNRCEight=NRCEight.toString();
		return lstNRCEight;
	}

	/*NRCNine Type Combo Data*/
	public String getNRCNineCombo() throws IOException{
		Properties prop= CommonUtility.getValue("NRCNine.properties");
		StringBuilder NRCNine= new StringBuilder();
		NRCNine.append("[");
		for (int i=1;i < prop.size(); i++){
			NRCNine.append("{NRCID:" + i + 
					",NRCName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		NRCNine.append("{NRCID:" + (prop.size()) +
				",NRCName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstNRCNine=NRCNine.toString();
		return lstNRCNine;
	}

	/*NRCTen Type Combo Data*/
	public String getNRCTenCombo() throws IOException{
		Properties prop= CommonUtility.getValue("NRCTen.properties");
		StringBuilder NRCTen= new StringBuilder();
		NRCTen.append("[");
		for (int i=1;i < prop.size(); i++){
			NRCTen.append("{NRCID:" + i + 
					",NRCName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		NRCTen.append("{NRCID:" + (prop.size()) +
				",NRCName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstNRCTen=NRCTen.toString();
		return lstNRCTen;
	}

	/*NRCEleven Type Combo Data*/
	public String getlstNRCElevenCombo() throws IOException{
		Properties prop= CommonUtility.getValue("NRCEleven.properties");
		StringBuilder NRCEleven= new StringBuilder();
		NRCEleven.append("[");
		for (int i=1;i < prop.size(); i++){
			NRCEleven.append("{NRCID:" + i + 
					",NRCName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		NRCEleven.append("{NRCID:" + (prop.size()) +
				",NRCName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstNRCEleven=NRCEleven.toString();
		return lstNRCEleven;
	}

	/*NRCTwelve Type Combo Data*/
	public String getlstNRCTwelveCombo() throws IOException{
		Properties prop= CommonUtility.getValue("NRCTwelve.properties");
		StringBuilder NRCTwelve= new StringBuilder();
		NRCTwelve.append("[");
		for (int i=1;i < prop.size(); i++){
			NRCTwelve.append("{NRCID:" + i + 
					",NRCName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		NRCTwelve.append("{NRCID:" + (prop.size()) +
				",NRCName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstNRCTwelve=NRCTwelve.toString();
		return lstNRCTwelve;
	}

	/*NRCThirteen Type Combo Data*/
	public String getlstNRCThirteenCombo() throws IOException{
		Properties prop= CommonUtility.getValue("NRCThirteen.properties");
		StringBuilder NRCThirteen= new StringBuilder();
		NRCThirteen.append("[");
		for (int i=1;i < prop.size(); i++){
			NRCThirteen.append("{NRCID:" + i + 
					",NRCName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		NRCThirteen.append("{NRCID:" + (prop.size()) +
				",NRCName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstNRCThirteen=NRCThirteen.toString();
		return lstNRCThirteen;
	}

	/*NRCFourteen Type Combo Data*/
	public String getlstNRCFourteenCombo() throws IOException{
		Properties prop= CommonUtility.getValue("NRCFourteen.properties");
		StringBuilder NRCFourteen= new StringBuilder();
		NRCFourteen.append("[");
		for (int i=1;i < prop.size(); i++){
			NRCFourteen.append("{NRCID:" + i + 
					",NRCName:'"+ (prop.getProperty(Integer.toString(i)))+ "'},");
		}
		NRCFourteen.append("{NRCID:" + (prop.size()) +
				",NRCName:'"+ (prop.getProperty(Integer.toString(prop.size())))+ "'}]");
		
		String lstNRCFourteen=NRCFourteen.toString();
		return lstNRCFourteen;
	}


	/***
	 * ä½œæˆ�å±¥æ­´ï¼š04/04/2019 Khin Myo Wai
	 * ä½œæˆ�æ¦‚è¦�ï¼š
	 * 
	 * æ›´æ–°å±¥æ­´ï¼šdd/mm/yyyy name 
	 * æ›´æ–°æ¦‚è¦�ï¼šXXXXXXXX
	 * 
	 */
	public List<Degree> getDegreeCombo() throws IOException {
		Properties prop = CommonUtility.getValue("Degree.properties");
		List<Degree> lstDegree = new ArrayList<Degree>();
		for (int i = 1; i <= prop.size(); i++) {
			Degree degree = new Degree();
			degree.setDegreeID((prop.getProperty(Integer.toString(i))));
			degree.setDegreeName((prop.getProperty(Integer.toString(i))));
			lstDegree.add(degree);
		}
		return lstDegree;
	}

	/***
	 * ä½œæˆ�å±¥æ­´ï¼š04/04/2019 Khin Myo Wai
	 * ä½œæˆ�æ¦‚è¦�ï¼š
	 * 
	 * æ›´æ–°å±¥æ­´ï¼šdd/mm/yyyy name 
	 * æ›´æ–°æ¦‚è¦�ï¼šXXXXXXXX
	 * 
	 */
	public List<AttendYear> getAttendYearCombo() throws IOException {
		Properties prop = CommonUtility.getValue("AttendYear.properties");
		List<AttendYear> lstAttendYrs = new ArrayList<AttendYear>();
		for (int i = 1; i <= prop.size(); i++) {
			AttendYear attendYrs = new AttendYear();
			attendYrs.setAttendYearID((prop.getProperty(Integer.toString(i))));
			attendYrs.setAttendYear((prop.getProperty(Integer.toString(i))));
			lstAttendYrs.add(attendYrs);
		}
		return lstAttendYrs;

	}

	/***
	 * ä½œæˆ�å±¥æ­´ï¼š04/04/2019 Khin Myo Wai
	 * ä½œæˆ�æ¦‚è¦�ï¼š
	 * 
	 * æ›´æ–°å±¥æ­´ï¼šdd/mm/yyyy name 
	 * æ›´æ–°æ¦‚è¦�ï¼šXXXXXXXX
	 * 
	 */
	public List<Programming> getITProgrammingCombo() throws IOException {
		Properties prop = CommonUtility.getValue("Programming.properties");
		List<Programming> lstITProgramming = new ArrayList<Programming>();
		for (int i = 1; i <= prop.size(); i++) {
			Programming programming = new Programming();
			programming
					.setProgrammingID((prop.getProperty(Integer.toString(i))));
			programming.setProgrammingName((prop.getProperty(Integer
					.toString(i))));
			lstITProgramming.add(programming);
		}
		return lstITProgramming;

	}

	/***
	 * ä½œæˆ�å±¥æ­´ï¼š04/04/2019 Khin Myo Wai
	 * ä½œæˆ�æ¦‚è¦�ï¼š
	 * 
	 * æ›´æ–°å±¥æ­´ï¼šdd/mm/yyyy name 
	 * æ›´æ–°æ¦‚è¦�ï¼šXXXXXXXX
	 * 
	 */
	public List<Design> getITDesignCombo() throws IOException {
		Properties prop = CommonUtility.getValue("Design.properties");
		List<Design> lstITDesign = new ArrayList<Design>();
		for (int i = 1; i <= prop.size(); i++) {
			Design design = new Design();
			design.setDesignID((prop.getProperty(Integer.toString(i))));
			design.setDesignName((prop.getProperty(Integer.toString(i))));
			lstITDesign.add(design);
		}
		return lstITDesign;

	}
	/***
	 * ä½œæˆ�å±¥æ­´ï¼š04/04/2019 Khin Myo Wai
	 * ä½œæˆ�æ¦‚è¦�ï¼š
	 * 
	 * æ›´æ–°å±¥æ­´ï¼šdd/mm/yyyy name 
	 * æ›´æ–°æ¦‚è¦�ï¼šXXXXXXXX
	 * 
	 */
	public List<DBServer> getITDatabaseCombo() throws IOException {
		Properties prop = CommonUtility.getValue("DBServer.properties");
		List<DBServer> lstITDatabase = new ArrayList<DBServer>();
		for (int i = 1; i <= prop.size(); i++) {
			DBServer dbserver = new DBServer();
			dbserver.setDbServerID((prop.getProperty(Integer.toString(i))));
			dbserver.setDbServerName((prop.getProperty(Integer.toString(i))));
			lstITDatabase.add(dbserver);
		}

		return lstITDatabase;

	}

	/***
	 * ä½œæˆ�å±¥æ­´ï¼š04/04/2019 Khin Myo Wai
	 * ä½œæˆ�æ¦‚è¦�ï¼š
	 * 
	 * æ›´æ–°å±¥æ­´ï¼šdd/mm/yyyy name 
	 * æ›´æ–°æ¦‚è¦�ï¼šXXXXXXXX
	 * 
	 */
	public List<Others> getITOtherCombo() throws IOException {
		Properties prop = CommonUtility.getValue("Others.properties");
		List<Others> lstITOthers = new ArrayList<Others>();
		for (int i = 1; i <= prop.size(); i++) {
			Others others = new Others();
			others.setOthersID((prop.getProperty(Integer.toString(i))));
			others.setOthersName((prop.getProperty(Integer.toString(i))));
			lstITOthers.add(others);
		}
		return lstITOthers;

	}

	/***
	 * ä½œæˆ�å±¥æ­´ï¼š04/04/2019 Khin Myo Wai
	 * ä½œæˆ�æ¦‚è¦�ï¼š
	 * 
	 * æ›´æ–°å±¥æ­´ï¼šdd/mm/yyyy name 
	 * æ›´æ–°æ¦‚è¦�ï¼šXXXXXXXX
	 * 
	 */
	public List<English> getEngSkillCombo() throws IOException {
		Properties prop = CommonUtility.getValue("English.properties");
		List<English> lstEngSkill = new ArrayList<English>();
		for (int i = 1; i <= prop.size(); i++) {
			English english = new English();
			english.setEnglishID((prop.getProperty(Integer.toString(i))));
			english.setEnglishLevel((prop.getProperty(Integer.toString(i))));
			lstEngSkill.add(english);
		}
		return lstEngSkill;

	}

	/***
	 * ä½œæˆ�å±¥æ­´ï¼š04/04/2019 Khin Myo Wai
	 * ä½œæˆ�æ¦‚è¦�ï¼š
	 * 
	 * 
	 */
	public List<Japanese> getJPSkillCombo() throws IOException {
		Properties prop = CommonUtility.getValue("Japanese.properties");
		List<Japanese> lstJPSkill = new ArrayList<Japanese>();
		for (int i = 1; i <= prop.size(); i++) {
			Japanese japan = new Japanese();
			japan.setJapaneseID((prop.getProperty(Integer.toString(i))));
			japan.setJapaneseLevel((prop.getProperty(Integer.toString(i))));
			lstJPSkill.add(japan);
		}
		return lstJPSkill;

	}

	/**
	 * ä½œæˆ�å±¥æ­´ï¼š04/04/2019 Cho Cho Lwin
	 * ä½œæˆ�æ¦‚è¦�ï¼špropertiesãƒ•ã‚¡ã‚¤ãƒ«ã�‹ã‚‰æ¨©é™�è¨­å®šç”»é�¢å��ã‚’å�–å¾—ã�™ã‚‹
	 * 
     *
     * @return
     * @throws IOException
     */
	public static List<RolePermission> getRolePermissionChk() throws IOException {
        Properties prop;
        prop = CommonUtility.getValue("RolePermission.properties");
        List<RolePermission> lstRolePermission = new ArrayList<RolePermission>();
        for (int i = 1; i <= prop.size(); i++) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRolePermissionID((prop.getProperty(Integer.toString(i))));
            rolePermission.setRolePermissionName((prop.getProperty(Integer.toString(i))));
            lstRolePermission.add(rolePermission);
        }
        return lstRolePermission;
    }

	/**
	 * ä½œæˆ�å±¥æ­´ï¼š01/04/2019 Cho Cho Lwin
     * ä½œæˆ�æ¦‚è¦�ï¼šæ–°è¦�ä½œæˆ�
	 *
	 * ...æ¤œç´¢æƒ…å ±ã�«ã‚ˆã�£ã�¦ç”³è«‹è€…æƒ…å ±ã‚’æ¤œç´¢ã�™ã‚‹...
	 * @param sExamID é–‹å§‹å�—é¨“ID
	 * @param eExamID çµ‚äº†å�—é¨“ID
	 * @param sRegDate ç™»éŒ²é–‹å§‹æ—¥
	 * @param eRegDate ç™»éŒ²çµ‚äº†æ—¥
	 * @param examPlace å�—é¨“å ´æ‰€
	 * @param jfYear JobFairå¹´
	 * @return lstApplicant ç”³è«‹è€…ãƒªã‚¹ãƒˆ
	 */
	public int getAppCount(String sExamID,String eExamID,Date sRegDate,Date eRegDate,
			String examPlace,int jfYear, int screenID){

		boolean s_exam_year = false;     //é–‹å§‹JobFairå¹´
		boolean s_exam_place = false;    //é–‹å§‹å�—é¨“å ´æ‰€
		boolean e_exam_year = false;     //çµ‚äº†JobFairå¹´
		boolean e_exam_place = false;    //çµ‚äº†å�—é¨“å ´æ‰€
		int appCount = 0;
		if (sExamID != null && !sExamID.trim().isEmpty()) {
			int s_exam_yr = Integer.parseInt(sExamID.substring(2,6));
			if(s_exam_yr == jfYear){
				s_exam_year = true;
			}
			s_exam_place = sExamID.contains(examPlace.substring(0,1));
		}
		if (eExamID != null && !eExamID.trim().isEmpty()) {
			int e_exam_yr = Integer.parseInt(eExamID.substring(2,6));
			if(e_exam_yr == jfYear){
				e_exam_year = true;
			}
			e_exam_place = eExamID.contains(examPlace.substring(0,1));
		}
        if ((sExamID != null && !sExamID.trim().isEmpty())
            && (eExamID != null && !eExamID.trim().isEmpty())) {
            if (s_exam_year == true && e_exam_year == true) {
				if (examPlace.trim().toUpperCase().equals("Yangon".toUpperCase())
						&& s_exam_place == true 
	                    && (examPlace.trim().toUpperCase().equals("Yangon".toUpperCase()) 
	                            && e_exam_place == true)){
					appCount = comService.getApplicantCount(jfYear, examPlace, sRegDate, eRegDate, sExamID, eExamID, screenID);
	        	} else if (examPlace.trim().toUpperCase().equals("Mandalay".toUpperCase()) 
	        			&& s_exam_place == true 
	                    && (examPlace.trim().toUpperCase().equals("Mandalay".toUpperCase()) 
	                            && e_exam_place == true)){
	        		appCount = comService.getApplicantCount(jfYear, examPlace, sRegDate, eRegDate, sExamID, eExamID, screenID);
	        	} else{
	        		appCount = 0;
	        	}
			} else {
			}
		} else if (sExamID != null && !sExamID.trim().isEmpty()) {
			if (s_exam_year == true) {
				if (examPlace.trim().toUpperCase().equals("Yangon".toUpperCase()) && s_exam_place == true) {
					appCount = comService.getApplicantCount(jfYear, examPlace, sRegDate, eRegDate, sExamID, eExamID, screenID);
			    } else if (examPlace.trim().toUpperCase().equals("Mandalay".toUpperCase()) 
			            && s_exam_place == true) {
			    	appCount = comService.getApplicantCount(jfYear, examPlace, sRegDate, eRegDate, sExamID, eExamID, screenID);
		        } else {
		        	appCount = 0;
			    }
			} else {
				appCount = 0;
			}
		} else if (eExamID != null && !eExamID.trim().isEmpty()) {
			if (e_exam_year == true) {
				if (examPlace.trim().toUpperCase().equals("Yangon".toUpperCase()) && e_exam_place == true) {
					appCount = comService.getApplicantCount(jfYear, examPlace, sRegDate, eRegDate, sExamID, eExamID, screenID);
			    } else if (examPlace.trim().toUpperCase().equals("Mandalay".toUpperCase()) 
			            && e_exam_place == true) {
			    	appCount = comService.getApplicantCount(jfYear, examPlace, sRegDate, eRegDate, sExamID, eExamID, screenID);
		        } else {
		        	appCount = 0;
			    }
			} else {
				appCount = 0;
			}
		} else {
			appCount = comService.getApplicantCount(jfYear, examPlace, sRegDate, eRegDate, sExamID, eExamID, screenID);
		}
		return appCount;
	}
	
	/**
	 * ä½œæˆ�å±¥æ­´ï¼š03/06/2019 Cho Cho Lwin
     * ä½œæˆ�æ¦‚è¦�ï¼šæ–°è¦�ä½œæˆ�
     * 
	 * ...ãƒ•ã‚©ãƒ«ãƒ€ã‚’å‰Šé™¤ã�™ã‚‹...
	 * @param fileã€€ãƒ•ã‚¡ã‚¤ãƒ«
	 */
    public static void recursiveDelete(File file) {
        //å†�å¸°ãƒ«ãƒ¼ãƒ—ã‚’çµ‚äº†ã�™ã‚‹
        if (!file.exists()) {
            return;
        }
	    //å†�å¸°çš„ã�«å‘¼ã�³å‡ºã�™
	    if (file.isDirectory()) {
	        for (File f : file.listFiles()) {
	            recursiveDelete(f);
	        }
	    }
	    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date currentDate = new Date();
		String formatted_date = dateFormat.format(currentDate);
	    //å†�å¸°çš„ã�«å‘¼ã�³å‡ºã�™
	    if(!file.getParent().contains(formatted_date)){
	        file.delete();
	    }
	}
    
    /**
     * ä½œæˆ�å±¥æ­´ï¼š03/06/2019 Cho Cho Lwin
     * ä½œæˆ�æ¦‚è¦�ï¼šæ–°è¦�ä½œæˆ�
     * 
	 * ...ãƒ•ã‚©ãƒ«ãƒ€ã‚’ä½œæˆ�ã�™ã‚‹...
     * @param folderPath ãƒ•ã‚©ãƒ«ãƒ€ã�®ãƒ‘ã‚¹
     * @param formattedDate ãƒ•ã‚©ãƒ¼ãƒžãƒƒãƒˆæ¸ˆã�¿æ—¥ä»˜
     */
    public static void createFolder(String folderPath, String formattedDate) {
    	File files = new File(folderPath+"/"+formattedDate);
        if (!files.exists()) {
        	files.mkdirs();
        	try {
				files.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
}
