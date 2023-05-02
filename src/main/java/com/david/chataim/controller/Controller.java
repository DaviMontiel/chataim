package com.david.chataim.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.david.chataim.controller.LanguageController.LANGUAGE;
import com.david.chataim.controller.events.AnimateMessage;
import com.david.chataim.controller.events.WindowAnimation;
import com.david.chataim.model.Contact;
import com.david.chataim.model.DataBase;
import com.david.chataim.model.Register;
import com.david.chataim.view.components.Message;
import com.david.chataim.view.login.LoginFrame;
import com.david.chataim.view.main.UsersWindow;
import com.david.chataim.view.newAccount.NewAccountFrame;

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
	
//	public void restartProgram() {
//		currentFrame.dispose();
//		Controller.s().setCurrentFrame(new UsersWindow());
//		currentFrame.setVisible(true);
//	}//FUN
	
	public void closeCurrentFrame() {
		try {
			Thread anim = WindowAnimation.closeWindowAnimation(currentFrame);
			anim.join();
			currentFrame.dispose();
		}//TRY
		catch (InterruptedException e) {e.printStackTrace();}//CATCH
	}//FUN
	
	public void exitProgram() {
		closeDatabase();
		closeCurrentFrame();
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
		if (database != null) database.close();
	}//FUN
	
	public boolean existAccount(String email) {
		if (database.existAccount(email, null) != -1) {
			return true;
		}//IF
		
		return false;
	}//FUN
	
	public int existAccount(String email, String passwd) {
		return database.existAccount(email, passwd);
	}//FUN
	
	private String getVerificationURL() {
		if (LanguageController.getLanguage() == LANGUAGE.ES) {
			return database.getFromApp(DataBase.APP.URL_EMAIL_VERIFICATION_CODE, "ES");
		} else {
			return database.getFromApp(DataBase.APP.URL_EMAIL_VERIFICATION_CODE, "EN");
		}//IF
	}//FUN
	
	private String getTermsAndConditionsURL() {
		if (LanguageController.getLanguage() == LANGUAGE.ES) {
			return database.getFromApp(DataBase.APP.URL_TERMS_AND_CODITIONS, "ES");
		} else {
			return database.getFromApp(DataBase.APP.URL_TERMS_AND_CODITIONS, "EN");
		}//IF
	}//FUN
	
	private int saveVerificacionCode(int code) {
		int id = database.saveVerificationCode(code);
		database.createVerificationTimer(id);
		
		return id;
	}//FUN
	
	public int getVerificationCode(int id) {
		return database.getVerificationCode(id);
	}//FUN
	
	public void createNewContact(Contact contact, Register register) {
		database.createContact(contact, register);
	}//FUN
	
	/*
	 * VIEW
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
	
	/*
	 * LOGIN & REGISTER
	 */
	
	public Contact singIn(String email, String passwd) {
		int idContact = existAccount(email, passwd);
		
		if (idContact != -1) {
			return database.getContact(idContact);
		}//IF
		
		return null;
	}//FUN
	
	public void validateNewUser(Register register) {
		// RANDOM VERIFICATION CODE
		int verificationCode = (int)(Math.random() * 900000000) + 100000000;
        
		// SEND EMAIL
		boolean isSent = sendVerificationEmail(register.getEmail(), verificationCode);
		
		if (isSent) {
			// SHOW SUCCESS MESSAGE
			Controller.s().showMessage(Message.MessageType.SUCCESS, LanguageController.getWord(26));
			
			// SAVE VERIFICATION CODE IN DATABASE
			int verificationCodeId = Controller.s().saveVerificacionCode(verificationCode);
			
			// DISPLAY PANEL TO WRITE THE CODE
			LoginFrame frame = (LoginFrame) currentFrame;
			register.setVerificationCodeId(verificationCodeId);
			ShowVerifyCodePanel.show(frame, register);
		} else {
			// SHOW ERROR MESSAGE
			Controller.s().showMessage(Message.MessageType.ERROR, LanguageController.getWord(25));
		}//IF
	}//FUN
	
	private boolean sendVerificationEmail(String email, int verificationCode) {
//		// GET URL FROM DATABASE
//		String urlEmail = getVerificationURL();
//		
//		// GET WEB PAGE CODE
//		String emailCode ="";
//		while (emailCode.isEmpty()) {
//			try {
//	        	URL url = new URL(urlEmail);
//	            URLConnection conexionURL = url.openConnection();
//	            InputStream is = conexionURL.getInputStream();
//	            int c = 0;
//
//	            while ((c = is.read()) != -1) {
//	            	emailCode += (char) c;
//	            }//WHILE
//	            
//	            // SET CODE INTO HTML CODE
//	            emailCode = emailCode.replace("HERE_CODE", String.valueOf(verificationCode));
//	            
//	            is.close();
//			}//TRY
//			catch (Exception e) { Controller.s().showMessage(Message.MessageType.WARNING, LanguageController.getWord(24)); }//CATCH
//		}//WHILE
//		
//		// SEND MAIL
//		return Mail.send(email, LanguageController.getWord(23), emailCode);
		return true;
	}//FUN
	
	public void changeToFrameContactChats(Contact contact) {
		closeCurrentFrame();
		
		try {
			Thread.sleep(500);
		}//TRY
		catch (InterruptedException e) { e.printStackTrace(); }//CATCH
		
		// SHOW PROFILE FRAME
		UsersWindow frame = new UsersWindow(contact);
		setCurrentFrame(frame);
		frame.setVisible(true);
	}//FUN
	
	/*
	 * CREATE ACCOUNT
	 */
	
	public void changeToFrameCreateAccount(Register register) {
		closeCurrentFrame();
		
		try {
			Thread.sleep(500);
		}//TRY
		catch (InterruptedException e) { e.printStackTrace(); }//CATCH
		
		// SHOW PROFILE FRAME
		NewAccountFrame frame = new NewAccountFrame(register);
		setCurrentFrame(frame);
		frame.setVisible(true);
	}//FUN
	
	public void showTermsAndConditions() throws IOException, URISyntaxException {
		java.awt.Desktop.getDesktop().browse(new URI(getTermsAndConditionsURL()));
	}//FUN
}//CLASS