package com.david.chataim.controller.events.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import com.david.chataim.controller.Controller;
import com.david.chataim.controller.ImageController;
import com.david.chataim.controller.LanguageController;
import com.david.chataim.view.components.Button;
import com.david.chataim.view.login.LoginFrame;
import com.david.chataim.view.login.components.Message;
import com.david.chataim.view.login.components.RegisterPanel;

public class CheckFieldsForSendEmail implements ActionListener {

	private LoginFrame frame;
	
	private RegisterPanel registerPanel;
	private final String namePattern ="^[a-zA-Z0-9]*$";
	private final String emailPattern ="[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
	private final String passwdPattern ="^[a-zA-Z0-9@!-_]*$";
	
	private boolean isClicked;
	
	
	public CheckFieldsForSendEmail(RegisterPanel registerPanel, LoginFrame frame) {
		this.registerPanel = registerPanel;
		this.frame = frame;
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
					boolean fieldsOk = checkTexts();
					
					// SEND EMAIL IF IS OK
					if (fieldsOk) {
						int code = Controller.s().sendVerificationEmail(registerPanel.getTfEmail().getText());
						
						if (code != -1) {
							ShowVerifyCodePanel.show(frame, code);
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
	}//EVNT
	
	private boolean checkTexts() {
		String name = registerPanel.getTfUserName().getText();
		String email = registerPanel.getTfEmail().getText();
		String passwd = String.valueOf(registerPanel.getTfPasswd().getPassword());
		
		// CHECK
		if (name.isEmpty() || email.isEmpty() || passwd.isEmpty()) {
			Controller.s().showMessage(Message.MessageType.ERROR, LanguageController.getWord(18));
			return false;
			
		} else if (!completePattern(namePattern, name)) {
			Controller.s().showMessage(Message.MessageType.ERROR, LanguageController.getWord(19));
			return false;
			
		} else if (!completePattern(emailPattern, email)) {
			Controller.s().showMessage(Message.MessageType.ERROR, LanguageController.getWord(20));
			return false;
			
		} else if (!completePattern(passwdPattern, passwd)) {
			Controller.s().showMessage(Message.MessageType.ERROR, LanguageController.getWord(21));
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