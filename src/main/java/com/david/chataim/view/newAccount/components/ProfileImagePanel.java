package com.david.chataim.view.newAccount.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import com.david.chataim.controller.ImageController;
import com.david.chataim.controller.LanguageController;
import com.david.chataim.controller.events.newAccount.CheckFieldsOfNewContact;
import com.david.chataim.controller.events.newAccount.DragImage;
import com.david.chataim.controller.events.newAccount.GetImageWithDialog;
import com.david.chataim.view.components.ImagePanel;
import com.david.chataim.view.components.button.Button;

public class ProfileImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ImagePanel panelImage;
	private Button btnChooseFile;
	

	public ProfileImagePanel() {
		setOpaque(false);
		setLayout(new GridBagLayout());
		
		// SHOW PROFILE USER IMAGE
		GridBagConstraints gbc_lblImage = new GridBagConstraints();
		gbc_lblImage.gridx = 0;
		gbc_lblImage.gridy = 0;
		panelImage = new ImagePanel(ImageController.getDefaultImageUser());
		panelImage.setDropTarget(new DragImage(panelImage));
		add(panelImage, gbc_lblImage);
		
		// BTN CHOOSER FILE
		GridBagConstraints gbc_btnChooseFile = new GridBagConstraints();
		gbc_btnChooseFile.insets = new Insets(20, 0, 0, 0);
		gbc_btnChooseFile.gridx = 0;
		gbc_btnChooseFile.gridy = 1;
		btnChooseFile = new Button();
		btnChooseFile.setBackground(Color.gray);
		btnChooseFile.addActionListener(new GetImageWithDialog(panelImage));
		btnChooseFile.setText(LanguageController.getWord(32));
		add(btnChooseFile, gbc_btnChooseFile);
	}//Constructor
	
	public void setComponentsToListener(CheckFieldsOfNewContact listener) {
		listener.setPanelImage(panelImage);
	}//FUN
	
	public void setSize() {
		panelImage.setPreferredSize(new Dimension((int) (getWidth()/1.2), (int) (getHeight()/1.3)));
		btnChooseFile.setPreferredSize(new Dimension((int) (getWidth()/2), (int) (getHeight()/11)));
	}//FUN
}//CLASS