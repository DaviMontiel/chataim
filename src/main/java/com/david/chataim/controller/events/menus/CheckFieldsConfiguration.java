package com.david.chataim.controller.events.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import com.david.chataim.controller.ColorController;
import com.david.chataim.controller.Controller;
import com.david.chataim.controller.ImageController;
import com.david.chataim.controller.LanguageController;
import com.david.chataim.view.components.Message;
import com.david.chataim.view.components.button.Button;
import com.david.chataim.view.mainFrame.components.menus.NewConfigurationPanel;

public class CheckFieldsConfiguration implements ActionListener{

	private NewConfigurationPanel panel;
	
	private final String descriptionPattern = "^(?=.{0,255}$)[a-zA-Z0-9@!-_ ]*$";
	
	private boolean isClicked;

	
	public CheckFieldsConfiguration(NewConfigurationPanel panel) {
		this.panel = panel;
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
					
					if (checkFields()) {
						Controller.s().updateConfiguration(panel);
						Controller.s().restartProgram();
					}//IF
					
					btn.setIcon(null);
					btn.setText(oldText);
					isClicked = false;
				}//EVENT
			}.start();
		}//IF
	}//EVENT
	
	private boolean checkFields() {
		String name = panel.getMtfName().getText();
		String description = panel.getMtfDescription().getText();
		boolean isAnonymous = panel.getMbAnonymous().isSelected();
		boolean lightTheme = !panel.getMbTheme().isSelected();
		
		// EQUAL
		if (!Controller.s().getCurrentContact().getOriginalName().equals(name) ||
				!Controller.s().getCurrentContact().getDescription().equals(description) ||
				Controller.s().getCurrentContact().isAnonymous() != isAnonymous) {
			
			return true;
		}//IF
		
		// CHECK
		if (name.isEmpty() || description.isEmpty()) {
			Controller.s().showMessage(Message.MessageType.ERROR, LanguageController.getWord(18));
			return false;
		} else if (!completePattern(descriptionPattern, description)) {
			Controller.s().showMessage(Message.MessageType.WARNING, LanguageController.getWord(39));
			return false;
		} else if (ColorController.isLightTheme() != lightTheme) {
			return true;
		}//IF
		
		return false;
	}//BOOL
	
	private static boolean completePattern(String pattern, String word) {
	    java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
	    java.util.regex.Matcher m = p.matcher(word);
	    
	    return m.matches();
	}//FUN
}//CLASS