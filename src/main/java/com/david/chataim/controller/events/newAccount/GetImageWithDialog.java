package com.david.chataim.controller.events.newAccount;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import com.david.chataim.view.components.ImagePanel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetImageWithDialog implements ActionListener {
	
	private ImagePanel imagePanel;

	@Override
	public void actionPerformed(ActionEvent e) {
    	FileDialog fileDialog = new FileDialog(new Frame(), "Selecciona un archivo");
    	fileDialog.setMode(FileDialog.LOAD);
    	fileDialog.setFile("*.png;*.jpg;*.jpeg");
    	fileDialog.setVisible(true);
    	String file = fileDialog.getFile();
    	if (file != null) {
    	    // SET IMAGE
    	    imagePanel.setImage(new ImageIcon(fileDialog.getDirectory() + file));
    	    imagePanel.repaint();
    	}//IF
	}//EVENT
}//CLASS