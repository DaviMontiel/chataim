package com.david.chataim.controller.events;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OnlyTextTf extends KeyAdapter {

	private int max;
	
	
	@Override
	public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isLetter(c) || ((JTextField) e.getSource()).getText().length() +1 > max) {
            e.consume();
        }//IF
    }//EVENT
}//CLASS