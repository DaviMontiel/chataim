package com.david.chataim.controller.events.register;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import com.david.chataim.controller.Controller;
import com.david.chataim.model.Register;

public class CheckVerificationCode implements ActionListener {
	
	private JTextField tf;
	private Register register;
	
	
	public CheckVerificationCode(JTextField tf, Register register) {
		this.tf = tf;
		this.register = register;
	}//Constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		int code = Controller.s().getVerificationCode(register.getVerificationCodeId());
		
		if (tf.getText().equals(String.valueOf(code))) {
			// CHANGE FRAME TO CREATE ACCOUNT FRAME
			Controller.s().changeToFrameCreateAccount(register);
		} else {
			// PAINT TF ERROR
			new Thread() {
				@Override
				public void run() {
					Color color = tf.getBackground();
					for (int d=0; d<3; d++) {
						tf.setBackground(new Color(236, 206, 213));
						
						try {
							Thread.sleep(100);
							tf.setBackground(color);
							Thread.sleep(100);
						}//TRY
						catch (InterruptedException e) { e.printStackTrace(); }//CATCH
					}//FOR
				}
			}.start();
		}//IF
	}//EVENT
}//CLASS