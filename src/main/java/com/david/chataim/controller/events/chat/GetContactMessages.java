package com.david.chataim.controller.events.chat;

import com.david.chataim.controller.Controller;
import com.david.chataim.model.Contact;
import com.david.chataim.view.mainFrame.components.chat.ChatPanel;
import com.david.chataim.model.ChatMessage;

public class GetContactMessages {

	// PREPARE MESSAGES INTO THE LIST
	public static void get(Contact contact) {
		new Thread() {
			@Override
			public void run() {
				// INIT CHAT IN LIST
				Controller.s().initChatGapList(contact.getChat());
				
				// GET MESSAGES FROM SERVER
				ChatMessage[] messages = Controller.s().getMessagesOfServer(contact);
				
				// SET MESSAGES TO THE LIST
				Controller.s().setListGapReady(contact.getChat());
				Controller.s().addMessagesToList(contact.getChat(), messages, false);
			}
		}.start();
	}//FUN
	
	// SET MESSAGES IF ARE PREPARED
	public static void setMessages(ChatPanel panelChat, int chat) {
		// CLEAR CHAT
		panelChat.removeMessages();
		
		// SET MESSAGES
		new Thread() {
			@Override
			public void run() {
				while(true) {
					// CKECK LIST, HAVE THE MESSAGES?
					ChatMessage[] messages = Controller.s().getMessagesOfList(chat);
					
					// SHOW MESSAGES IN CHAT
					if (messages != null) {
						for (ChatMessage message:messages) {
							panelChat.addMessage(message);
						}//FOR
						
						break;
					}//IF
					
					// SLEEP
					try { Thread.sleep(50); }//TRY
					catch (InterruptedException e) {}//CATCH
				}//WHILE
			}
		}.start();
	}//FUN
}//CLASS