package com.david.chataim.controller;

import java.awt.Color;

public class ColorController {

	// GENERAL
	public static final Color PRIMARY_COLOR = new Color(35, 166, 97);
	public static final Color SECONDARY_COLOR = new Color(22, 116, 66);
	public static final Color TERCIARY_COLOR = new Color(29, 137, 80);
	public static final Color PRIMARY_COLOR_A = new Color(35, 166, 97, 40);
	
//	public static final Color PRIMARY_COLOR = new Color(52, 65, 78);
//	public static final Color SECONDARY_COLOR = new Color(68, 81, 92);
//	public static final Color TERCIARY_COLOR = new Color(29, 137, 80);
	
	
	// LOGIN
	public static final Color PROGRESSBAR_FRONT_COLOR = Color.DARK_GRAY;
	public static final Color PROGRESSBAR_BACK_COLOR = Color.DARK_GRAY;
	
	public static final String[] BACKGROUNDS = {
			"#402c2c", "#403c2c", "#36402c",
			"#2c4033", "#2c3740", "#2f2c40",
			"#3d2c40"};
	
	// CHAT
	public static final Color MESSAGE_TEXT = new Color(242, 242, 242);
	public static final Color MESSAGE_RIGHT = new Color(127, 127, 127, 180);
	public static final Color MESSAGE_RIGHT_DATE = new Color(230, 230, 230);
	
	
	public static String getRandomBackgroundLogin() {
		return BACKGROUNDS[new java.util.Random().nextInt(0, BACKGROUNDS.length)];
	}//FUN
	
//	public static void setPrimaryColor() {
//		
//	}
}//CLASS