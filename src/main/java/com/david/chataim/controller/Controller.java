package com.david.chataim.controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.david.chataim.controller.LanguageController.LANGUAGE;
import com.david.chataim.controller.events.AnimateMessage;
import com.david.chataim.controller.events.WindowAnimation;
import com.david.chataim.controller.events.chat.GetContactMessages;
import com.david.chataim.model.Contact;
import com.david.chataim.model.DataBase;
import com.david.chataim.model.ChatMessage;
import com.david.chataim.model.Register;
import com.david.chataim.view.components.ComponentResizer;
import com.david.chataim.view.components.Message;
import com.david.chataim.view.login.LoginFrame;
import com.david.chataim.view.mainFrame.UsersWindow;
import com.david.chataim.view.mainFrame.components.ListContactsPanel;
import com.david.chataim.view.mainFrame.components.chat.ChatPanel;
import com.david.chataim.view.newAccount.NewAccountFrame;

import lombok.Getter;
import lombok.Setter;

public class Controller {
	
	private static Controller controller;
	@Getter private JFrame currentFrame;
	@Setter private DataBase database;
	@Setter private JPanel messagePanel;
	
	@Getter private Contact currentContact;
	private LinkedHashMap<Integer, Contact> contacts;
	private LinkedHashMap<Integer, Object> chats;
	
	private boolean isChekingNewMessages;
	
	
	public static Controller s() {
		if (controller == null) {
			return controller = new Controller();
		}//IF
		return controller;
	}//FUN
	
	/*
	 * GENERAL 
	 */
	
	public void init() {
		String lang = System.getProperty("user.language");
		
		if (lang.equals("es")) {
			LanguageController.setLanguage(LanguageController.LANGUAGE.ES);
		} else {
			LanguageController.setLanguage(LanguageController.LANGUAGE.EN);
		}//IF
	}//FUN
	
	public void setCurrentFrame(JFrame frame) {
		this.currentFrame = frame;
		
		// Set window animation
		WindowAnimation.openWindowAnimation(frame);
	}//SET
	
//	public void restartProgram() {
//		currentFrame.dispose();
//		Controller.s().setCurrentFrame(new UsersWindow());
//		currentFrame.setVisible(true);
//	}//FUN
	
	public void closeCurrentFrame() {
		try {
			Thread anim = WindowAnimation.closeWindowAnimation(currentFrame);
			anim.join();
			currentFrame.dispose();
		}//TRY
		catch (InterruptedException e) {e.printStackTrace();}//CATCH
	}//FUN
	
	public void exitProgram() {
		closeDatabase();
		closeCurrentFrame();
		System.exit(0);
	}//FUN
	
	// INIT CHAT GAP AS FALSE
	public void initChatGapList(int chat) {
		if (chats == null) {
			chats = new LinkedHashMap<Integer, Object>();
		}//IF
		
		Object chatGap = false;
		chats.put(chat, chatGap);
	}//FUN
	
	public void sendMessageTo(int chat, ChatMessage message, int to) {
		// CHECK IF IS FOR ME
		boolean addToQueue = true;
		if (to == currentContact.getId()) {
			addToQueue = false;
		}//IF
		
		// SEND MESSAGE
		database.sendMessage(chat, message, to, addToQueue);
		
		// SAVE MESSAGE IN LIST
		saveMessageInList(chat, new ChatMessage[] {message});
	}//FUN
	
	private void saveMessageInList(int chat, ChatMessage[] messages) {
		if (chats == null) {
			chats = new LinkedHashMap<Integer, Object>();
		}//IF
		
		// GET MESSAGES OF CHAT
		ChatMessage[] oldMessages = (ChatMessage[]) chats.get(chat);
		
		if (oldMessages != null && messages != null) {
			// NEW EMPTY ARRAY
			ChatMessage[] newMessages = new ChatMessage[oldMessages.length + messages.length];
			
			// COPY VALUES TO THE NEW ARRAY
			System.arraycopy(oldMessages, 0, newMessages, 0, oldMessages.length);
			
			// SET THE NEW MESSAGES
			for (int f=0; f<messages.length; f++) {
				newMessages[oldMessages.length + f] = messages[f];
			}//FOR
			
			// UPDATE LIST
			chats.put(chat, newMessages);
		} else if (messages != null) {
			// SET 1 MESSAGE
			chats.put(chat, messages);
		}//IF
	}//FUN

	// SAVE MESSAGES IN LIST
	public void addMessagesToList(int chat, ChatMessage[] messages) {
		saveMessageInList(chat, messages);
	}//FUN
	
	public void setListGapReady(int chat) {
		chats.put(chat, new ChatMessage[] {});
	}//FUN
	
	public ChatMessage[] getMessagesOfList(int chatId) {
		Object chat = chats.get(chatId);
		
		// CHECK IF IS BOOLEAN
		if (chat instanceof Boolean) {
			return null;
		} else {
			// RETURN THE MESSAGES OF CHAT
			return (ChatMessage[]) chat;
		}//IF
	}//FUN
	
	/*
	 * CONTROLLERS
	 */
	
	public void changeLanguage() {
		LanguageController.setLanguage(LANGUAGE.EN);
		currentFrame.invalidate();
		currentFrame.validate();
		currentFrame.repaint();
	}//FUN
	
	/*
	 * BBDD
	 */
	
	public boolean isDatabaseConnected() {
		return database.isConnected();
	}//FUN
	
	private void closeDatabase() {
		if (database != null) database.close();
	}//FUN
	
	public boolean existAccount(String email) {
		if (database.existAccount(email, null) != -1) {
			return true;
		}//IF
		
		return false;
	}//FUN
	
	public int existAccount(String email, String passwd) {
		return database.existAccount(email, passwd);
	}//FUN
	
	private String getVerificationURL() {
		if (LanguageController.getLanguage() == LANGUAGE.ES) {
			return database.getFromApp(DataBase.APP.URL_EMAIL_VERIFICATION_CODE, "ES");
		} else {
			return database.getFromApp(DataBase.APP.URL_EMAIL_VERIFICATION_CODE, "EN");
		}//IF
	}//FUN
	
	private String getTermsAndConditionsURL() {
		if (LanguageController.getLanguage() == LANGUAGE.ES) {
			return database.getFromApp(DataBase.APP.URL_TERMS_AND_CODITIONS, "ES");
		} else {
			return database.getFromApp(DataBase.APP.URL_TERMS_AND_CODITIONS, "EN");
		}//IF
	}//FUN
	
	private int saveVerificacionCode(int code) {
		int id = database.saveVerificationCode(code);
		database.createVerificationTimer(id);
		
		return id;
	}//FUN
	
	public int getVerificationCode(int id) {
		return database.getVerificationCode(id);
	}//FUN
	
	public void createNewContact(Contact contact, Register register) {
		int idContact = database.createContact(contact, register);
		if (idContact != -1) {
			contact.setId(idContact);
			currentContact = contact;
			
			changeToFrameContactChats();
		} else {
			showMessage(Message.MessageType.ERROR, LanguageController.getWord(47));
		}//IF
	}//FUN
	
	// ADD NEW CONTACT TO THE ADDED LIST WITH CHAT
	public Contact addNewContact(int idContact) {
		if (contacts == null) {
			contacts = new LinkedHashMap<Integer, Contact>();
		}//IF
		
		if (!contacts.containsKey(idContact)) {
			Contact newContact = database.getContact(idContact);
			
			if (newContact != null) {
				int chat = database.getChat(currentContact.getId(), idContact);
				
				if (chat == -1) {
					chat = database.createNewChat(currentContact.getId(), idContact);
				}//IF
				
				newContact.setChat(chat);
				
				// SAVE CONTACT
				contacts.put(newContact.getId(), newContact);
				
				return newContact;
			}//IF
		}//IF
		
		return null;
	}//FUN
	
	// GET ALL CONTACTs
	private LinkedHashMap<Integer, Contact> getContacts() {
		return database.getContacts(currentContact.getId());
	}//FUN
	
	// GET MESSAGES OF CHAT
	public ChatMessage[] getMessagesOfServer(Contact contact) {
		return database.getAllMessagesOf(contact, currentContact);
	}//FUN
	
	// CHECK FOR NEW MESSAGEs
	public void checkForNewMessages() {
		if (!isChekingNewMessages) {
			isChekingNewMessages = true;
			
			// CKECK FOREVER IF HAVE MESSAGEs
			new Thread() {
				@Override
				public void run() {
					while(true) {
						// CKECK SERVER LIST
						Controller.s().getMessagingQueue();
						
						// SLEEP
						try { Thread.sleep(1000); }//TRY
						catch (InterruptedException e) {}//CATCH
					}//WHILE
				}
			}.start();
		}//IF
	}//FUN

	// GET MESSAGES OF MESSAGING_QUEUE
	public void getMessagingQueue() {
		// GET MESSAGES
		ChatMessage[] messages = database.getMessagesOf(currentContact.getId());
		
		// WHO IS THE PROPIETARY
		if (messages != null) {
			// CLEAR STACK FROM SERVER
			database.removeMessagesQueue(messages);
			
			// ARRAY FOR SAVE ID CONTACTs NOT FOUND
			LinkedHashMap<Integer, Vector<ChatMessage>> contactsNotFound = new LinkedHashMap<Integer, Vector<ChatMessage>>();
			LinkedHashMap<Contact, Vector<ChatMessage>> contactsFound = new LinkedHashMap<Contact, Vector<ChatMessage>>();
			
			if (contacts == null) {
				contacts = new LinkedHashMap<Integer, Contact>();
			}//IF
			
			for (int f=0; f<messages.length; f++) {
				if (contacts.containsKey(messages[f].getContact().getId())) {
					Contact contactFound = contacts.get(messages[f].getContact().getId());
					
					// SET CONTACT IN TO MESSAGE
					messages[f].setContact(contactFound);
					
					// ADD CONTACT WITH MESSAGE TO LIST
					if (!contactsFound.containsKey(contactFound)) {
						contactsFound.put(contactFound, new Vector<ChatMessage>());
					}//IF
					contactsFound.get(contactFound).add(messages[f]);
				} else {
					int contactNotFound = messages[f].getContact().getId();
					
					if (!contactsNotFound.containsKey(contactNotFound)) {
						contactsNotFound.put(contactNotFound, new Vector<ChatMessage>());
					}//IF
					contactsNotFound.get(contactNotFound).add(messages[f]);
				}//IF
			}//FOR
			
			// APPLY CONTACTs NOT FOUND IN MESSAGEs
			for (Map.Entry<Integer, Vector<ChatMessage>> entry : contactsNotFound.entrySet()) {
				Contact contactFound =  database.getContact(entry.getKey());
				contactFound.setChat(entry.getValue().get(0).getContact().getChat());
				
				// SET CONTACT TO ALL MESSAGEs
				ChatMessage[] messagesToSave = new ChatMessage[entry.getValue().size()];
				for (int f=0; f<entry.getValue().size(); f++) {
					messagesToSave[f] = entry.getValue().get(f);
					messagesToSave[f].setContact(contactFound);
					
					// SEND NOTIFICATION
					if (!currentFrame.isVisible()) {
						// TODO
					}//IF
				}//FOR
				
				// SAVE MESSAGES
				saveMessageInList(contactFound.getChat(), messagesToSave);

				addContactToMenu(contactFound);
				addNewContact(contactFound.getId());
			}//FOR
			
			// ADD MESSAGES TO MY LIST
			for (Map.Entry<Contact, Vector<ChatMessage>> entry : contactsFound.entrySet()) {
				ChatMessage[] array = new ChatMessage[entry.getValue().size()];
				
				// IF IS LOOKING THE CHAT
				UsersWindow frame = (UsersWindow) currentFrame;
				ChatPanel panelChat = frame.getPanelChat();
				
				for (int f=0; f<entry.getValue().size(); f++) {
					array[f] = entry.getValue().get(f);
					
					// SHOW MESSAGE IN CHAT
					if (panelChat != null) {
						if (array[f].getContact().getChat() == panelChat.getContact().getChat()) {
							if (panelChat != null) {
								panelChat.addMessage(array[f]);
							}//IF
						} else {
							// SEND NOTIFICATION
							// TODO
						}//IF
					}//IF
				}//FOR
				addMessagesToList(entry.getKey().getChat(), entry.getValue().toArray(array));
			}//FOR
		}//IF
	}//FUN
	
	/*
	 * VIEW
	 */
	
	public void showMessage(Message.MessageType messageType, String message) {
		final int width = 40 + message.length()*8;
		final int height = 25;
		
        Message ms = new Message();
        ms.setBounds(messagePanel.getWidth()/2-width/2, -25, width, height);
        ms.showMessage(messageType, message);
        ms.setVisible(true);
        
        messagePanel.add(ms, 0);
        messagePanel.repaint();
        
        AnimateMessage.openMessage(messagePanel, ms);
        
        messagePanel.remove(ms);
    }//FUN
	
	public void quitLeftEvents(boolean paint) {
		UsersWindow frame = (UsersWindow) currentFrame;
		frame.getPanelSearch().quitFocus(paint);
	}//FUN
	
	public void quitLeftVisibility(boolean paint) {
		UsersWindow frame = (UsersWindow) currentFrame;
		frame.getScrollPaneContacts().setVisible(!paint);
	}//FUN
	
	public void addContactToMenu(Contact contact) {
//		if (!contacts.containsKey(contact.getId())) {
			UsersWindow frame = (UsersWindow) currentFrame;
			frame.getPanelListContacts().addContact(contact);
//		}//IF
	}//FUN
	
	/*
	 * LOGIN & REGISTER
	 */
	
	public Contact singIn(String email, String passwd) {
		int idContact = existAccount(email, passwd);
		
		if (idContact != -1) {
			return currentContact = database.getContact(idContact);
		}//IF
		
		return null;
	}//FUN
	
	public void validateNewUser(Register register) {
		// RANDOM VERIFICATION CODE
		int verificationCode = (int)(Math.random() * 900000000) + 100000000;
        
		// SEND EMAIL
		boolean isSent = sendVerificationEmail(register.getEmail(), verificationCode);
		
		if (isSent) {
			// SHOW SUCCESS MESSAGE
			showMessage(Message.MessageType.SUCCESS, LanguageController.getWord(26));
			
			// SAVE VERIFICATION CODE IN DATABASE
			int verificationCodeId = Controller.s().saveVerificacionCode(verificationCode);
			
			// DISPLAY PANEL TO WRITE THE CODE
			LoginFrame frame = (LoginFrame) currentFrame;
			register.setVerificationCodeId(verificationCodeId);
			ShowVerifyCodePanel.show(frame, register);
		} else {
			// SHOW ERROR MESSAGE
			showMessage(Message.MessageType.ERROR, LanguageController.getWord(25));
		}//IF
	}//FUN
	
	private boolean sendVerificationEmail(String email, int verificationCode) {
//		// GET URL FROM DATABASE
//		String urlEmail = getVerificationURL();
//		
//		// GET WEB PAGE CODE
//		String emailCode ="";
//		while (emailCode.isEmpty()) {
//			try {
//	        	URL url = new URL(urlEmail);
//	            URLConnection conexionURL = url.openConnection();
//	            InputStream is = conexionURL.getInputStream();
//	            int c = 0;
//
//	            while ((c = is.read()) != -1) {
//	            	emailCode += (char) c;
//	            }//WHILE
//	            
//	            // SET CODE INTO HTML CODE
//	            emailCode = emailCode.replace("HERE_CODE", String.valueOf(verificationCode));
//	            
//	            is.close();
//			}//TRY
//			catch (Exception e) { Controller.s().showMessage(Message.MessageType.WARNING, LanguageController.getWord(24)); }//CATCH
//		}//WHILE
//		
//		// SEND MAIL
//		return Mail.send(email, LanguageController.getWord(23), emailCode);
		return true;
	}//FUN
	
	public void changeToFrameContactChats() {
		closeCurrentFrame();
		
		try {
			Thread.sleep(500);
		}//TRY
		catch (InterruptedException e) { e.printStackTrace(); }//CATCH
		
		// SHOW PROFILE FRAME
		UsersWindow frame = new UsersWindow(currentContact);
		
		// SET FRAME RESIZABLE
		ComponentResizer cr = new ComponentResizer();
		cr.setMinimumSize(new Dimension(Dimens.WINDOW_W_MIN, Dimens.WINDOW_H_MIN));
//        cr.setMaximumSize(new Dimension(Dimens.WINDOW_W_PRE+500, Dimens.WINDOW_H_PRE+500));
        cr.registerComponent(frame);
        cr.setSnapSize(new Dimension(1, 1));
        
		setCurrentFrame(frame);
		setMessagePanel(frame.getPanelBorder());
		
		// GET CONTACTs
		contacts = getContacts();
		
		if (contacts != null) {
			ListContactsPanel panelListContacts = frame.getPanelListContacts();
			for (Map.Entry<Integer, Contact> entry : contacts.entrySet()) {
				panelListContacts.addContact(entry.getValue());
			}//FOR
		}//IF
		
		// SHOW FRAME
		frame.setVisible(true);
		
		// LOOP
		checkForNewMessages();
	}//FUN
	
	/*
	 * CREATE ACCOUNT
	 */
	
	public void changeToFrameCreateAccount(Register register) {
		closeCurrentFrame();
		
		try {
			Thread.sleep(500);
		}//TRY
		catch (InterruptedException e) { e.printStackTrace(); }//CATCH
		
		// SHOW PROFILE FRAME
		NewAccountFrame frame = new NewAccountFrame(register);
		setCurrentFrame(frame);
		
		// RESIZE FRAME
		ComponentResizer cr = new ComponentResizer();
        cr.setMinimumSize(new Dimension(Dimens.WINDOW_W_MIN, Dimens.WINDOW_H_MIN));
//      cr.setMaximumSize();
        cr.registerComponent(frame);
        cr.setSnapSize(new Dimension(1, 1));
        
        frame.setVisible(true);
	}//FUN
	
	public void showTermsAndConditions() throws IOException, URISyntaxException {
		java.awt.Desktop.getDesktop().browse(new URI(getTermsAndConditionsURL()));
	}//FUN
	
	/*
	 * CHAT
	 */
	
	private boolean isChatCreated;
	
	public void showChat(Contact contact) {
		UsersWindow frame = (UsersWindow) currentFrame;
		ChatPanel panelChat = frame.getPanelChat();
		
		if (panelChat == null && !isChatCreated) {
			isChatCreated = true;
			panelChat = new ChatPanel(contact);
			frame.setPanelChat(panelChat);
			frame.resizeChat();
			frame.getContentPane().add(panelChat, BorderLayout.EAST);
			
			if (chats == null) {
				// INIT CHAT
				initChatGapList(contact.getChat());
			}//IF
		} else {
			panelChat.setContact(contact);
		}//IF
		
		// SHOW MESSAGES IN CHAT
    	GetContactMessages.setMessages(panelChat, contact.getChat());
	}//FUN
}//CLASS