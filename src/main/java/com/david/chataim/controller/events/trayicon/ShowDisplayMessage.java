package com.david.chataim.controller.events.trayicon;

import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowDisplayMessage implements ActionListener {
	
	private static TrayIcon trayIcon;
	private static String title;
	private static String message;
	private static TrayIcon.MessageType messageType;
	
	
	public static void show(TrayIcon trayIcon, String title, String message, TrayIcon.MessageType messageType) {
		trayIcon.displayMessage(title, message, messageType);
	}//FUN
	
	@Override
	public void actionPerformed(ActionEvent e) {
		trayIcon.displayMessage(title, message, messageType);
	}//EVT
}//CLASS