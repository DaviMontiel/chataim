package com.david.chataim.controller.events.newAccount;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.david.chataim.controller.Controller;
import com.david.chataim.view.components.Message.MessageType;

public class ShowTermsAndCoditions implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Controller.s().showTermsAndConditions();
						break;
					}//TRY
					catch (Exception e) { Controller.s().showMessage(MessageType.ERROR, "ERROR: DATABASE TERMINS"); }//CATCH
				}//WHILE
			}
		}.start();
	}//EVENT
}//CLASS