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
	
	/*
	 * CREATE TRAYICON
	 */
	
	private void init() {
		if (SystemTray.isSupported()) {
			trayIcon.addActionListener(new ShowActionListener());
            // CREATE APPBAR
			AppBar appBar = new AppBar(trayIcon);
			appBar.setTitle("Chataim");
			//appBar.showDisplayMessage("TITULO", "Mensaje", MessageType.INFO);
			
			// CREATE MENU
			AppBarMenu appBarMenu = new AppBarMenu();
//			appBarMenu.addMenuItem(LanguageController.getWord(57), new ShowDisplayMessage(trayIcon, "About", "", MessageType.NONE));
			//appBarMenu.addMenuItem("Display", new ShowDisplayMessage("", null, null));
        	appBarMenu.addMenuItem(LanguageController.getWord(57), new ExitProgram());

			appBar.addPopupMenu(appBarMenu);
			
			// Create a Listener for the popup message
//                trayIcon.addActionListener(new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        System.out.println("In here");
//                        trayIcon.displayMessage("Tester!", "Some action performed", TrayIcon.MessageType.INFO);
//                       }
//                      });
//			tray.add(trayIcon);
			
			// Create a pop-up menu components
//			PopupMenu popup = new PopupMenu();
//			MenuItem aboutItem = new MenuItem("About");
//			CheckboxMenuItem cb1 = new CheckboxMenuItem("Set auto size");
//			CheckboxMenuItem cb2 = new CheckboxMenuItem("Set tooltip");
//			Menu displayMenu = new Menu("Display");
//			MenuItem errorItem = new MenuItem("Error");
//			MenuItem warningItem = new MenuItem("Warning");
//			MenuItem infoItem = new MenuItem("Info");
//			MenuItem noneItem = new MenuItem("None");
//			MenuItem exitItem = new MenuItem("Exit");
//			
//			// Close the program for the exitItem
//			exitItem.addActionListener(new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					System.exit(0);
//				}
//			});
//      
//			//Add components to pop-up menu
//			popup.add(aboutItem);
//			popup.addSeparator();
//			popup.add(cb1);
//			popup.add(cb2);
//			popup.addSeparator();
//			popup.add(displayMenu);
//			displayMenu.add(errorItem);
//			displayMenu.add(warningItem);
//			displayMenu.add(infoItem);
//			displayMenu.add(noneItem);
//			popup.add(exitItem);
        } else {
            System.err.println("System tray not supported!");
        }//IF
	}//INIT
	
	/*
	 * DISPLAY MESSAGE
	 */
	
	public void displayMessage(String user, String text) {
		ShowDisplayMessage.show(trayIcon, user, text, MessageType.NONE);
	}//FUN
}//CLASS