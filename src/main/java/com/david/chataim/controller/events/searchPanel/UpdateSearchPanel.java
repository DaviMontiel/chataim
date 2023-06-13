package com.david.chataim.controller.events.searchPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import com.david.chataim.controller.PanelContactsController;

public class UpdateSearchPanel extends KeyAdapter {

	@Override
	public void keyTyped(KeyEvent e) {
        char letter = e.getKeyChar();
        String contact = ((JTextField) e.getSource()).getText();
        
        if (letter != '\b' && letter != '\u007F') {
        	contact += letter;
        }//IF
        
        PanelContactsController.s().filter(contact);
    }//EVENT
}//CLASS