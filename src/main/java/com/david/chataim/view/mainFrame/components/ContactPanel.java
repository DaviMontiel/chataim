package com.david.chataim.view.mainFrame.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.david.chataim.controller.ColorController;
import com.david.chataim.controller.events.ChangeJPanelColor;
import com.david.chataim.controller.events.ShowChatPanel;
import com.david.chataim.controller.events.chat.GetContactMessages;
import com.david.chataim.model.Contact;

public class ContactPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ProfilePanel profilePanel;
	

	public ContactPanel(Contact contact) {
		initComponents(contact);
		initEvents(contact);
	}//Constructor
	
	private void initComponents(Contact contact) {
		setBackground(ColorController.PRIMARY_COLOR);
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		setPreferredSize(new Dimension(0, 50));
		setMaximumSize(new Dimension(32767, 50));
		setLayout(null);
		setOpaque(false);
		
		// PROFILE IMAGE
		profilePanel = new ProfilePanel(contact.getImage());
		profilePanel.setBounds(10, 5, 40, 40);
		
		// PROFILE NAME
		JLabel lblName = new JLabel(contact.getOriginalName());
		lblName.setFont(new Font("sansserif", 1, 14));
		lblName.setForeground(Color.white);
		lblName.setBounds(55, 5, 100, 40);
		
		add(profilePanel);
		add(lblName);
	}//FUN
	
	private void initEvents(Contact contact) {
		// GET MESSAGES
    	GetContactMessages.get(contact);
		
    	// MOUSE EVENT PANEL
		addMouseListener(new ChangeJPanelColor());
		addMouseListener(new ShowChatPanel(contact));
	}//FUN
}//CLASS