package com.david.chataim.controller.events.newAccount;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import com.david.chataim.controller.Controller;
import com.david.chataim.controller.ImageController;
import com.david.chataim.controller.LanguageController;
import com.david.chataim.model.Contact;
import com.david.chataim.model.Register;
import com.david.chataim.view.components.ImagePanel;
import com.david.chataim.view.components.Message;
import com.david.chataim.view.components.MyCheckBox;
import com.david.chataim.view.components.button.Button;
import com.david.chataim.view.components.textField.MyTextArea;

import lombok.Setter;

public class CheckFieldsOfNewContact implements ActionListener {

	@Setter private Register register;
	
	@Setter private ImagePanel panelImage;
	@Setter private MyTextArea mtaDescription;
	@Setter private MyCheckBox mcbAnonymous;
	@Setter private MyCheckBox mcbConditions;
	
	private final String descriptionPattern = "^(?=.{0,255}$)[a-zA-Z0-9@!-_ ]*$";

	private boolean isClicked;
	
	
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
					
					// CREATE CONTACT IF IS OK
					if (checkFields()) {
						Contact contact = new Contact();
						contact.setOriginalName(register.getName());
						contact.setDescription(mtaDescription.getText());
						contact.setAnonymous(mcbAnonymous.isSelected());
						
						if (panelImage.isDistinct()) {
							contact.setImage(panelImage.getImage());
						} else {
							contact.setImage(ImageController.getDefaultImageUser());
						}//IF
						
						Controller.s().createNewContact(contact, register);
					} else {
						btn.setText(oldText);
						btn.setIcon(null);
						isClicked = false;
					}//IF
				}
			}.start();
		}//IF
	}//EVENT
	
	private boolean checkFields() {
		// CHECK
		if (!completePattern(descriptionPattern, mtaDescription.getText())) {
			Controller.s().showMessage(Message.MessageType.ERROR, LanguageController.getWord(39));
			return false;
			
		} else if (!mcbConditions.isSelected()) {
			Controller.s().showMessage(Message.MessageType.WARNING, LanguageController.getWord(40));
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