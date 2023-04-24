package com.david.chataim.view.main.components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.david.chataim.controller.ColorController;
import com.david.chataim.controller.ImageController;
import com.david.chataim.controller.events.ChangeJPanelColor;
import com.david.chataim.model.Contact;

import lombok.Getter;
import lombok.Setter;

public class ContactPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	@Setter @Getter private ProfilePanel profilePanel;
	

	public ContactPanel(Contact contact, String lastMessage, String lastMessageTime) {
		super();
		addMouseListener(new ChangeJPanelColor());
		setBackground(ColorController.PRIMARY_COLOR);
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		setPreferredSize(new Dimension(0, 50));
		setMaximumSize(new Dimension(32767, 50));
		setLayout(null);
		setOpaque(false);
		
		// PROFILE IMAGE
		profilePanel = new ProfilePanel(contact);
		profilePanel.setProfileImage(new ImageIcon(ImageController.DEFAULT_PROFILE).getImage());
		profilePanel.setBounds(10, 5, 40, 40);
		
		// PROFILE NAME
//		profilePanel.get
		
		add(profilePanel);
	}//Constructor
}//CLASS