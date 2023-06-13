package com.david.chataim.view.mainFrame.components;

import java.awt.BorderLayout;
import java.util.LinkedHashMap;

import javax.swing.JPanel;

import com.david.chataim.controller.PanelContactsController;
import com.david.chataim.model.Contact;

public class ListContactsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public static LinkedHashMap<Integer, ContactPanel> contacts;
	
	
	public ListContactsPanel() {
		PanelContactsController.s().addContactsPanel(this);
	}//Constructor
	
	public void addContact(Contact contact) {
		ContactPanel panelContact = new ContactPanel(contact);
		PanelContactsController.s().addContact(contact.getChat(), panelContact);
		
		add(panelContact, BorderLayout.CENTER);
		revalidate();
	}//FUN
}//CLASS