package com.david.chataim.controller;

import java.awt.Image;

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
	public static final String FILTER = PATH_I+"Filter.png";
	public static final String SEARCH = PATH_P+PATH_I+"Search.png";
	public static final String BBDD = PATH_I+"BBDD.png";
	
	public static final String USER_ICON = PATH_I+"miniUser.png";
	public static final String EMAIL_ICON = PATH_I+"mail.png";
	public static final String PASSWD_ICON = PATH_I+"pass.png";
	
	public static final String DEFAULT_PROFILE = PATH_I+"User.png";
	
	
	// GIFs
	public static final String GIF_LOADING = PATH_I+PATH_G+"loading.gif";
	
	
	public static Image getDefaultImageUser() {
		return new ImageIcon(ImageController.DEFAULT_PROFILE).getImage();
	}//FUN
}//CLASS