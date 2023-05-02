package com.david.chataim.controller.events.register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import com.david.chataim.controller.Controller;
import com.david.chataim.controller.ImageController;
import com.david.chataim.controller.LanguageController;
import com.david.chataim.model.Register;
import com.david.chataim.view.components.Message;
import com.david.chataim.view.components.button.Button;
import com.david.chataim.view.login.components.RegisterPanel;

public class CheckFieldsOfRegister implements ActionListener {

	private RegisterPanel registerPanel;
	private final String namePattern = "^[a-zA-Z0-9]{0,25}$";
	private final String emailPattern ="[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
	private final String passwdPattern = "^[a-zA-Z0-9@!-_]{8,50}$";
	
	private boolean isClicked;
	
	
	public CheckFieldsOfRegister(RegisterPanel registerPanel) {
		this.registerPanel = registerPanel;
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
					
					// CHECK FIELDS
					Register register = checkTexts();
					
					// SEND EMAIL IF IS OK
					if (register != null) {
						if (!Controller.s().existAccount(register.getEmail())) {
							Controller.s().validateNewUser(register);
						} else {
							Controller.s().showMessage(Message.MessageType.ERROR, LanguageController.getWord(22));
						}//IF
					}//IF
					
					// "FIX" VISUAL BUG
					new Thread() {
						@Override
						public void run () {
							try {
								Thread.sleep(100);
								Controller.s().getCurrentFrame().repaint();
							} catch (InterruptedException e) { e.printStackTrace(); }
						}
					}.start();
					
					btn.setText(oldText);
					btn.setIcon(null);
					isClicked = false;
				}
			}.start();
		}//IF
	}//EVENT
	
	private Register checkTexts() {
		String name = registerPanel.getTfUserName().getText();
		String email = registerPanel.getTfEmail().getText();
		String passwd = String.valueOf(registerPanel.getTfPasswd().getPassword());
		
		// CHECK
		if (name.isEmpty() || email.isEmpty() || passwd.isEmpty()) {
			Controller.s().showMessage(Message.MessageType.ERROR, LanguageController.getWord(18));
			return null;
			
		} else if (!completePattern(namePattern, name)) {
			Controller.s().showMessage(Message.MessageType.WARNING, LanguageController.getWord(19));
			return null;
			
		} else if (!completePattern(emailPattern, email)) {
			Controller.s().showMessage(Message.MessageType.WARNING, LanguageController.getWord(20));
			return null;
			
		} else if (!completePattern(passwdPattern, passwd)) {
			Controller.s().showMessage(Message.MessageType.WARNING, LanguageController.getWord(21));
			return null;
		}//IF
		
		return new Register(name, email, passwd, 0);
	}//FUN
	
	private static boolean completePattern(String pattern, String word) {
	    java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
	    java.util.regex.Matcher m = p.matcher(word);
	    
	    return m.matches();
	}//FUN
}//CLASS