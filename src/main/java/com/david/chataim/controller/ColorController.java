package com.david.chataim.controller;

import java.awt.Color;

public class ColorController {

	// GENERAL
	public static Color PRIMARY_COLOR;
	public static Color SECONDARY_COLOR;
	public static Color TERCIARY_COLOR;
	public static Color PRIMARY_COLOR_A;
	
	// THEME
	public static String theme;
	
	
	// LOGIN
	public static Color LOGIN_BG = Color.white;
	public static Color LOGIN_TF_FOREGROUND = Color.white;
	

	// CHAT
	public static Color MESSAGE_TEXT = new Color(242, 242, 242);
	public static Color MESSAGE_RIGHT = new Color(127, 127, 127, 180);
	public static Color MESSAGE_RIGHT_DATE = new Color(230, 230, 230);
	
	
	public static void setTheme(String theme) {
		ColorController.theme = theme;
		if (theme.equals("light")) {
			PRIMARY_COLOR = new Color(35, 166, 97);
			SECONDARY_COLOR = new Color(22, 116, 66);
			TERCIARY_COLOR = new Color(29, 137, 80);
			PRIMARY_COLOR_A = new Color(35, 166, 97, 40);
		} else {
			PRIMARY_COLOR = new Color(52, 65, 78);
			SECONDARY_COLOR = new Color(68, 81, 92);
			TERCIARY_COLOR = new Color(172, 184, 193);
			PRIMARY_COLOR_A = new Color(52, 65, 97, 40);
		}//IF
	}//FUN
	
	public static boolean isLightTheme() {
		return theme.equals("light") ? true : false;
	}//BOOL
}//CLASS