package com.david.chataim.controller.trayicon;

import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

public class AppBar {
	
	private TrayIcon trayIcon;
	

	public AppBar(TrayIcon trayIcon) {
		this.trayIcon = trayIcon;
	}//Constructor
	
	public void setTitle(String title) {
		trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip(title);
	}//SET
	
	public void showDisplayMessage(String title, String message, MessageType type) {
		trayIcon.displayMessage("Titulo", "Mensaje", type);
	}//FUN
	
	public void addPopupMenu(AppBarMenu popup) {
		trayIcon.setPopupMenu(popup);
	}//FUN
}//CLASS