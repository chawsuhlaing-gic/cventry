/**
 * 
 *  CV_Entryã€�CV_A_011ã€‘	
 *	ä½œæˆ�å±¥æ­´ï¼š2019/01/22 Jar Moon Taung 		
 *	ä½œæˆ�æ¦‚è¦�ï¼šæ–°è¦�ä½œæˆ�ã€€ç™»éŒ²å…±é€šå‡¦ç�†
 * 
 *  æ›´æ–°å±¥æ­´ï¼šdd/mm/yyyy name	
 *  æ›´æ–°æ¦‚è¦�ï¼šXXXXXXXX
 *
 */

package mm.com.gic.ces.application.common;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import mm.com.gic.ces.base.common.CommonService;

public class CommonRegister {

	CommonUtility comUtility = new CommonUtility();       //å‡¦ç�†
    CommonService comService = new CommonService();       //å‡¦ç�†
	
	/***
	 * ä½œæˆ�å±¥æ­´ï¼š04/04/2019 Jar Moon Taung 
	 * ä½œæˆ�æ¦‚è¦�ï¼šå¹´é½¢ã‚’è¨ˆç®—ã�™ã‚‹
	 * 
	 * æ›´æ–°å±¥æ­´ï¼šdd/mm/yyyy name 
	 * æ›´æ–°æ¦‚è¦�ï¼šXXXXXXXX
	 * 
	 * @param dob
	 * @return ç„¡ã�—
	 */
	@SuppressWarnings("deprecation")
	public static int calculateAge(Date dob) {
		Date today = new Date();
		int age = today.getYear() - dob.getYear();
		if (today.getMonth() >= dob.getMonth()) {
			if (today.getDay() < dob.getDay()) {
				age++;
			}
		} else {
			age--;
		}

		return age;
	}
    	
	public byte[] resizeImage(File image)
	{
		long size= image.length()/1024;
		byte[] imageInByte = null;
		try {
			BufferedImage originalImage = ImageIO.read(image);
            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();   
            BufferedImage resizeImageJpg = resizeImage(originalImage, type, size);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
        	ImageIO.write( resizeImageJpg, "png", baos );
        	baos.flush();
        	imageInByte = baos.toByteArray();
        	baos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {	
			e.printStackTrace();
		}
		return imageInByte;
	}

	/**
	 * ç”»åƒ�ã‚’ãƒªã‚µã‚¤ã‚ºã�™ã‚‹
	 * @param originalImage
	 * @param type
	 * @return
	 */
    private static BufferedImage resizeImage(BufferedImage originalImage, int type, long size) {
    	
    	int scaledWidth=0;
        int scaledHeight=0;
        
    	if(size<=300) {
    		scaledWidth=originalImage.getWidth();
    		scaledHeight=originalImage.getHeight();
    	}else if(size<=1024*2){
    		scaledWidth=(int) (originalImage.getWidth()*0.5);
    		scaledHeight=(int) (originalImage.getHeight()*0.5);
    	}else if(size<=1024*3){
    		scaledWidth=(int) (originalImage.getWidth()*0.55);
    		scaledHeight=(int) (originalImage.getHeight()*0.55);
    	}else if(size<=1024*4){
    		scaledWidth=(int) (originalImage.getWidth()*0.37);
    		scaledHeight=(int) (originalImage.getHeight()*0.37);
    	} else if(size<=1024*5){
    		scaledWidth=(int) (originalImage.getWidth()*0.35);
    		scaledHeight=(int) (originalImage.getHeight()*0.35);
    	}
    		   	      
    	BufferedImage resizedImage = new BufferedImage(scaledWidth, scaledHeight, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
 
        return resizedImage;
    }
	
}
