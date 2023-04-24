package com.david.chataim.controller.events.login;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class ControlTfVerificationCode extends KeyAdapter {

	@Override
	public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c) || ((JTextField) e.getSource()).getText().length() > 8) {
            e.consume();
        }//IF
    }//EVENT
}//CLASS