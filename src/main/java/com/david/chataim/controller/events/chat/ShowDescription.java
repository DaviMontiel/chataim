package com.david.chataim.controller.events.chat;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import com.david.chataim.controller.LanguageController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowDescription extends MouseAdapter {

	private String description;

    
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        
        if (!description.isEmpty()) {
        	JOptionPane.showMessageDialog(null, LanguageController.getWord(64) + " " + description);
        }//IF
    }//EVENT
}//CLASS