package com.david.chataim.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/*
 * CONSTANTS
 */

public class ImageController {

	// PROYECT
	public static final String PATH_P ="src/main/java";
	public static final String PATH_I ="/com/david/chataim/view/images/";
	public static final String PATH_G ="gif/";
	
	
	// ICONs
	public static final String EXIT_WHITE = PATH_I+"Exit_White.png";
	public static final String EXIT_BLACK = PATH_I+"Exit_Black.png";
	
	public static final String INFO = PATH_P+PATH_I+"info.png";
	public static final String FILTER = PATH_I+"Filter.png";
	public static final String SEARCH = PATH_P+PATH_I+"Search.png";
	public static final String BBDD = PATH_I+"BBDD.png";
	
	public static final String USER_ICON = PATH_I+"miniUser.png";
	public static final String EMAIL_ICON = PATH_I+"mail.png";
	public static final String PASSWD_ICON = PATH_I+"pass.png";
	
	public static final String DEFAULT_PROFILE = PATH_P+PATH_I+"User.png";
	
	
	// GIFs
	public static final String GIF_LOADING = PATH_I+PATH_G+"loading.gif";
	
	
	// TO SHOW
	private static Image default_profile;
	
	
	public static Image getDefaultImageUser() {
		if (default_profile != null) {
			return default_profile;
		}//IF
		
		return default_profile = new ImageIcon(ImageController.DEFAULT_PROFILE).getImage();
	}//FUN
	
	public static ByteArrayInputStream convertToInputStream(Image image, String format) {
		BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		
		ByteArrayOutputStream baos = null;
		try {
		    baos = new ByteArrayOutputStream();
		    ImageIO.write(bi, format, baos);
		}//TRY
		catch (IOException e) { e.printStackTrace(); }
		finally {
		    try {
		        baos.close();
		    }//TRY
		    catch (Exception e) {}
		}//FINALLY

		return new ByteArrayInputStream(baos.toByteArray());
	}//FUN
	
	public static Image convertToImage(InputStream inputStream) {
		try {
			Image imagen = ImageIO.read(inputStream);
			
			return new ImageIcon(imagen).getImage();
		}//TRY
		catch (IOException e) { e.printStackTrace(); }//CATCH
		
		return null;
	}//FUN
	
//	public static String getFormatBlob(byte[] bytes) throws IOException {
//	    InputStream stream = new ByteArrayInputStream(bytes);
//	    TikaInputStream tikaStream = TikaInputStream.get(stream);
//	    Detector detector = new DefaultDetector();
//	    Metadata metadata = new Metadata();
//	    MediaType mediaType = detector.detect(tikaStream, metadata);
//	 
//	    return mediaType.getSubtype();
//	}//FUN
}//CLASS