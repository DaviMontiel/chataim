package com.david.chataim.view.mainFrame.components;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.david.chataim.model.Contact;

public class ListContactsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	
	public ListContactsPanel() {}//Constructor
	
	public void addContact(Contact contact) {
		add(new ContactPanel(contact), BorderLayout.CENTER);
		revalidate();
	}//FUN
}//CLASS