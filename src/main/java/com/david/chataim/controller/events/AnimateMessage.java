package com.david.chataim.controller.events;

import javax.swing.JPanel;

import com.david.chataim.view.components.Message;

public class AnimateMessage {

	public static void openMessage(JPanel messagePanel, Message ms) {
		try {
			for (int f=0; f<ms.getHeight(); f++) {
				ms.setLocation(ms.getX(), ms.getY()+1);
				messagePanel.repaint();
				
				Thread.sleep(10);
			}//FOR
			
			Thread.sleep(2000);
			
			exitMessage(messagePanel, ms);
		}//TRY
		catch (Exception e) {}//CATCH
	}//FUN
	
	public static void exitMessage(JPanel messagePanel, Message ms) throws Exception {
		for (int f=0; f<ms.getHeight(); f++) {
			ms.setLocation(ms.getX(), ms.getY()-1);
			messagePanel.repaint();
			
			Thread.sleep(10);
		}//FOR
	}//FUN
}//CLASS