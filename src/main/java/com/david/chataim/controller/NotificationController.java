package com.david.chataim.controller;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

import com.david.chataim.controller.events.ShowActionListener;
import com.david.chataim.controller.events.trayicon.ExitProgram;
import com.david.chataim.controller.events.trayicon.ShowDisplayMessage;
import com.david.chataim.controller.trayicon.AppBar;
import com.david.chataim.controller.trayicon.AppBarMenu;

public class NotificationController {

	private static NotificationController controller;
	
	private final SystemTray systemTray;
	private TrayIcon trayIcon;
	private final Image ICON;
	
	
	public NotificationController() throws AWTException {
		systemTray = SystemTray.getSystemTray();
		ICON = Toolkit.getDefaultToolkit().getImage(getClass().getResource(ImageController.TRAYICON));
		trayIcon = new TrayIcon(ICON);
		
		systemTray.add(trayIcon);
		
		init();
	}//Constructor
	
	public static NotificationController c() {
		if (controller != null) {
			return controller;
		}//IF
		
		try {
			return controller = new NotificationController();
		}//TRY
		catch (Exception e) { return null; }//CATCH
	}//GET
	
	public void dispose() {
	    systemTray.remove(trayIcon);
	}//V
	
	/*
	 * CREATE TRAYICON
	 */
	
	private void init() {
		if (SystemTray.isSupported()) {
			trayIcon.addActionListener(new ShowActionListener());
            // CREATE APPBAR
			AppBar appBar = new AppBar(trayIcon);
			appBar.setTitle("Chataim");
			
			// CREATE MENU
			AppBarMenu appBarMenu = new AppBarMenu();
        	appBarMenu.addMenuItem(LanguageController.getWord(61), new ExitProgram());

			appBar.addPopupMenu(appBarMenu);
        } else {}//IF
	}//INIT
	
	/*
	 * DISPLAY MESSAGE
	 */
	
	public void displayMessage(String user, String text) {
		ShowDisplayMessage.show(trayIcon, user, text, MessageType.NONE);
	}//FUN
}//CLASS