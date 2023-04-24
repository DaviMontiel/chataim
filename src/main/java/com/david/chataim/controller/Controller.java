package com.david.chataim.controller;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.david.chataim.controller.LanguageController.LANGUAGE;
import com.david.chataim.controller.events.AnimateMessage;
import com.david.chataim.controller.events.WindowAnimation;
import com.david.chataim.model.DataBase;
import com.david.chataim.view.login.components.Message;
import com.david.chataim.view.main.UsersWindow;

import lombok.Getter;
import lombok.Setter;

public class Controller {
	
	private static Controller controller;
	@Getter private JFrame currentFrame;
	@Setter private DataBase database;
	@Setter private JPanel messagePanel;
	
	
	public static Controller s() {
		if (controller == null) {
			return controller = new Controller();
		}//IF
		return controller;
	}//FUN
	
	/*
	 * GENERAL 
	 */
	
	public void init() {
		String lang = System.getProperty("user.language");
		
		if (lang.equals("es")) {
			LanguageController.setLanguage(LanguageController.LANGUAGE.ES);
		} else {
			LanguageController.setLanguage(LanguageController.LANGUAGE.EN);
		}//IF
	}//FUN
	
	public void setCurrentFrame(JFrame frame) {
		this.currentFrame = frame;
		
		// Set window animation
		WindowAnimation.openWindowAnimation(frame);
	}//SET
	
	public void restartProgram() {
		currentFrame.dispose();
		Controller.s().setCurrentFrame(new UsersWindow());
		currentFrame.setVisible(true);
	}//FUN
	
	public void exitProgram() {
		closeDatabase();
		try {
			Thread anim = WindowAnimation.closeWindowAnimation(currentFrame);
			anim.join();
		}//TRY
		catch (InterruptedException e) {e.printStackTrace();}//CATCH
		System.exit(0);
	}//FUN
	
	/*
	 * CONTROLLERS
	 */
	
	public void changeLanguage() {
		LanguageController.setLanguage(LANGUAGE.EN);
		currentFrame.invalidate();
		currentFrame.validate();
		currentFrame.repaint();
	}//FUN
	
	/*
	 * BBDD
	 */
	
	public boolean isDatabaseConnected() {
		return database.isConnected();
	}//FUN
	
	private void closeDatabase() {
		database.close();
	}//FUN
	
	private String getVerificationURL(LANGUAGE lang) {
		if (lang == LANGUAGE.ES) {
			return database.getVerificationURL("ES");
		} else {
			return database.getVerificationURL("EN");
		}//IF
	}//FUN
	
	public boolean saveVerificacionCode(int code) {
		int id = database.saveVerificationCode(code);
		return database.createVerificationTimer(id);
	}//FUN
	
//	public void createContact() {
//		
//	}
	
	/*
	 * SPECIFICs
	 */
	
	public void showMessage(Message.MessageType messageType, String message) {
		final int width = 40 + message.length()*8;
		final int height = 25;
		
        Message ms = new Message();
        ms.setBounds(messagePanel.getWidth()/2-width/2, -25, width, height);
        ms.showMessage(messageType, message);
        ms.setVisible(true);
        
        messagePanel.add(ms);
        messagePanel.repaint();
        
        AnimateMessage.openMessage(messagePanel, ms);
        
        messagePanel.remove(ms);
    }//FUN
	
	public int sendVerificationEmail(String email) {
		// GET URL FROM DATABASE
		String urlEmail = getVerificationURL(LanguageController.getLanguage());
		
		// GET WEB PAGE CODE
		String emailCode ="";
		int verificationCode = 0;
		while (emailCode.isEmpty()) {
			try {
	        	URL url = new URL(urlEmail);
	            URLConnection conexionURL = url.openConnection();
	            InputStream is = conexionURL.getInputStream();
	            int c = 0;

	            while ((c = is.read()) != -1) {
	            	emailCode += (char) c;
	            }//WHILE
	            
	            // RANDOM VERIFICATION CODE
	            verificationCode = (int)(Math.random() * 900000000) + 100000000;
	            emailCode = emailCode.replace("HERE_CODE", String.valueOf(verificationCode));

	            is.close();
			}//TRY
			catch (Exception e1) { Controller.s().showMessage(Message.MessageType.WARNING, LanguageController.getWord(23)); }//CATCH
		}//WHILE
		
		// SEND MAIL
		boolean isSend = Mail.send(email, LanguageController.getWord(22), emailCode);
		
		// CREATE CONTACT AND SAVE VERIFICATION CODE
		if (isSend) {
			boolean saved = Controller.s().saveVerificacionCode(verificationCode);
			
			if (saved) {
				
				
				return verificationCode;
			}//IF
		}//IF
		
		return -1;
	}//FUN
}//CLASS