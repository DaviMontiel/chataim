package com.david.chataim.view.newAccount.events;

import java.awt.Dimension;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.david.chataim.view.components.MyTextArea;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CheckMtaSize implements DocumentListener {
	
	private final int maxHeight = 150;
	private MyTextArea mtaDescription;
    
	
	public void changedUpdate(DocumentEvent e) { checkHeight(e); }
    public void insertUpdate(DocumentEvent e) { checkHeight(e); }
    public void removeUpdate(DocumentEvent e) { checkHeight(e); }

    public void checkHeight(DocumentEvent e) {
        int height = mtaDescription.getPreferredSize().height;
        
        if (height > maxHeight) {
        	mtaDescription.setPreferredSize(new Dimension(mtaDescription.getPreferredSize().width, maxHeight));
        } else {
        	mtaDescription.setSize(new Dimension(mtaDescription.getPreferredSize().width, 50));
        }//IF
    }//FUN
}//CLASS