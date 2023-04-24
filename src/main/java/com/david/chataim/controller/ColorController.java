package com.david.chataim.controller;

import java.awt.Color;

public class ColorController {

	// GENERAL
	public static final Color PRIMARY_COLOR = new Color(35, 166, 97);
	public static final Color SECONDARY_COLOR = new Color(22, 116, 66);
	public static final Color TERCIARY_COLOR = new Color(22, 116, 66);
	
	
	// LOGIN
	public static final Color PROGRESSBAR_FRONT_COLOR = Color.DARK_GRAY;
	public static final Color PROGRESSBAR_BACK_COLOR = Color.DARK_GRAY;
	
	public static final String[] BACKGROUNDS = {
			"#402c2c", "#403c2c", "#36402c",
			"#2c4033", "#2c3740", "#2f2c40",
			"#3d2c40"};
	
	
	public static String getRandomBackgroundLogin() {
		return BACKGROUNDS[new java.util.Random().nextInt(0, BACKGROUNDS.length)];
	}//FUN
	
//	public static void setPrimaryColor() {
//		
//	}
}//CLASS