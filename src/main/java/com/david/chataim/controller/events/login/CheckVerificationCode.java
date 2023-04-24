package com.david.chataim.controller.events.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class CheckVerificationCode implements ActionListener {
	
	private JTextField tf;
	private int code;
	
	
	public CheckVerificationCode(JTextField tf, int code) {
		this.tf = tf;
		this.code = code;
	}//Constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		if (tf.getText().equals(String.valueOf(code))) {
			System.out.println(true);
		}//IF
	}//EVENT
}//CLASS