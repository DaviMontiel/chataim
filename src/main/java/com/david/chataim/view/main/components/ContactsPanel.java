package com.david.chataim.view.main.components;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.david.chataim.model.Contact;

public class ContactsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	
	public ContactsPanel() {
//		setLayout(new BorderLayout());
	}//Constructor
	
	public void addContact(Contact contact, String lastMessage, String lastMessageTime) {
		new Thread() {
			@Override
			public void run() {
				for (int f=0; f<100; f++) {
					add(new ContactPanel(contact, lastMessage, lastMessageTime), BorderLayout.CENTER);
					revalidate();
					try {
						Thread.sleep(600);
					}//TRY
					catch (InterruptedException e) {e.printStackTrace();}					
				}//FOR
			}
		}.start();
	}//FUN
}//CLASS