package com.david.chataim.controller.events.register;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OnlyNumbersTf extends KeyAdapter {

	private int max;
	
	
	@Override
	public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c) || ((JTextField) e.getSource()).getText().length() > max) {
            e.consume();
        }//IF
    }//EVENT
}//CLASS