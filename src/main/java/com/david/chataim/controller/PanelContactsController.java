package com.david.chataim.controller;

import java.util.LinkedHashMap;

import com.david.chataim.view.mainFrame.components.ContactPanel;
import com.david.chataim.view.mainFrame.components.ListContactsPanel;

public class PanelContactsController {

	private static PanelContactsController controller;
	
	private ListContactsPanel panelContacts;
	private LinkedHashMap<Integer, ContactPanel> chats;
	
	
	public static PanelContactsController s() {
		if (controller == null) {
			return controller = new PanelContactsController();
		}//IF
		return controller;
	}//FUN
	
	public PanelContactsController() {
		chats = new LinkedHashMap<Integer, ContactPanel>();
	}//Constructor
	
	public void addContactsPanel(ListContactsPanel panel) {
		this.panelContacts = panel;
	}//V
	
	public void moveChatToUp(int idChat) {
		panelContacts.remove(chats.get(idChat));
		panelContacts.add(chats.get(idChat), 0);
		panelContacts.revalidate();
	}//V
	
	public void addContact(int idChat, ContactPanel panelContact) {
		chats.put(idChat, panelContact);
	}//V
	
	public void filter(String contactName) {
		for (ContactPanel contactPanel: chats.values()) {
			String name = contactPanel.getContactName();
			contactPanel.setVisible(name.contains(contactName));
		}//FOR
	}//V
	
//	public void dispose() {
//		controller = null;
//	}//V
}//CLASS