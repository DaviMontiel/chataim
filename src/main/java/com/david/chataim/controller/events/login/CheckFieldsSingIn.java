package com.david.chataim.controller.events.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import com.david.chataim.controller.Controller;
import com.david.chataim.controller.ImageController;
import com.david.chataim.controller.LanguageController;
import com.david.chataim.model.Contact;
import com.david.chataim.view.components.Message;
import com.david.chataim.view.components.button.Button;
import com.david.chataim.view.login.components.LoginPanel;

public class CheckFieldsSingIn implements ActionListener {
	
	private LoginPanel loginPanel;
	private final String emailPattern ="[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
	private final String passwdPattern = "^[a-zA-Z0-9@!-_]{8,50}$";
	
	private boolean isClicked;
	
	
	public CheckFieldsSingIn(LoginPanel loginPanel) {
		this.loginPanel = loginPanel;
		isClicked = false;
	}//Constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!isClicked) {
			new Thread() {
				@Override
				public void run() {
					isClicked = true;
					
					// ANIM BTN
					Button btn = (Button) e.getSource();
					String oldText = btn.getText();
					btn.setText("");
					btn.setIcon(new ImageIcon(getClass().getResource(ImageController.GIF_LOADING)));
					
					if (checkTexts()) {
						Contact contact = Controller.s().singIn(loginPanel.getTfEmail().getText(), String.valueOf(loginPanel.getTfPasswd().getPassword()));
						
						if (contact != null) {
							Controller.s().changeToFrameContactChats();
						} else {
							Controller.s().showMessage(Message.MessageType.ERROR, LanguageController.getWord(41));
						}//IF
					}//IF
					
					btn.setIcon(null);
					btn.setText(oldText);
					isClicked = false;
				}
			}.start();
		}//IF
	}//EVENT
	
	private boolean checkTexts() {
		String email = loginPanel.getTfEmail().getText();
		String passwd = String.valueOf(loginPanel.getTfPasswd().getPassword());
		
		// CHECK
		if (email.isEmpty() || passwd.isEmpty()) {
			Controller.s().showMessage(Message.MessageType.ERROR, LanguageController.getWord(18));
			return false;
			
		} else if (!completePattern(emailPattern, email)) {
			Controller.s().showMessage(Message.MessageType.WARNING, LanguageController.getWord(20));
			return false;
			
		} else if (!completePattern(passwdPattern, passwd)) {
			Controller.s().showMessage(Message.MessageType.WARNING, LanguageController.getWord(21));
			return false;
		}//IF
		
		return true;
	}//FUN
	
	private static boolean completePattern(String pattern, String word) {
	    java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
	    java.util.regex.Matcher m = p.matcher(word);
	    
	    return m.matches();
	}//FUN
}//CLASS