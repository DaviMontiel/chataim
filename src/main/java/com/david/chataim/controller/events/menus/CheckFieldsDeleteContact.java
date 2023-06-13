package com.david.chataim.controller.events.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import com.david.chataim.controller.Controller;
import com.david.chataim.controller.ImageController;
import com.david.chataim.controller.LanguageController;
import com.david.chataim.view.components.Message;
import com.david.chataim.view.components.button.Button;
import com.david.chataim.view.components.textField.MyTextField;

public class CheckFieldsDeleteContact implements ActionListener {

	private MyTextField tfId;
//	private ClosePanel closeListener;
	
	private boolean isClicked;
	
	
	public CheckFieldsDeleteContact(MyTextField tfId) {
		this.tfId = tfId;
//		this.closeListener = closeListener;
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
					
					String id = tfId.getText();
					if (!id.isEmpty()) {
						boolean deleted = Controller.s().deleteContact(Integer.parseInt(id));
						
						if (deleted) {
							// RESTART PROGRAM
							Controller.s().restartProgram();
						} else {
							Controller.s().showMessage(Message.MessageType.ERROR, LanguageController.getWord(46));
						}//IF
					} else {
						Controller.s().showMessage(Message.MessageType.ERROR, LanguageController.getWord(45));
					}//IF
					
					btn.setIcon(null);
					btn.setText(oldText);
					isClicked = false;
				}
			}.start();
		}//IF
	}//EVENT
}//CLASS