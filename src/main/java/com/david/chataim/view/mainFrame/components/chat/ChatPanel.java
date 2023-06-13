package com.david.chataim.view.mainFrame.components.chat;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

import com.david.chataim.controller.events.chat.SendMessage;
import com.david.chataim.model.ChatMessage;
import com.david.chataim.model.Contact;
import com.david.chataim.view.mainFrame.components.chat.component.ChatArea;
import com.david.chataim.view.mainFrame.components.chat.component.ChatBox;
import com.david.chataim.view.mainFrame.components.chat.swing.Background;

import lombok.Getter;

public class ChatPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private Background background;
    @Getter private ChatArea chatArea;
    
    @Getter private Contact contact;
    
    
    public ChatPanel(Contact contact) {
    	this.contact = contact;
    	
    	initComponents();
    	initEvents();
    }//Constructor
    
    public void initComponents() {
    	background = new Background();
        chatArea = new ChatArea();
        
        // SET CONTACT NAME OF TITLE
        setTitle(contact);
        
        // SET CONTACT DESCRIPTION TO HEADER
        chatArea.setUserDescription(contact.getDescription());

        GroupLayout background1Layout = new GroupLayout(background);
        background.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chatArea, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addContainerGap())
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chatArea, GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(background, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(background, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }//FUN
    
    public void initEvents() {
        chatArea.addChatEvent(new SendMessage(this));
    }//FUN
    
    public void setContact(Contact contact) {
    	this.contact = contact;
    	setTitle(contact);
    	
    	// SET CONTACT DESCRIPTION TO HEADER
        chatArea.setUserDescription(contact.getDescription());
    }//FUN
    
    private void setTitle(Contact title) {
    	if (contact.getOtherName() == null) {
        	chatArea.setTitle(contact.getOriginalName());
        } else {
        	chatArea.setTitle(contact.getOtherName());
        }//IF
    	
    	chatArea.setTitle(chatArea.getTitle()+" ("+contact.getId()+")");
    }//SET
    
    public void addMessage(ChatMessage chatMessage) {
        ChatBox.BoxType site;
        Contact messageContact = chatMessage.getContact();
        if (messageContact.getId() == contact.getId()) {
        	site = ChatBox.BoxType.LEFT;
        } else {
        	site = ChatBox.BoxType.RIGHT;
        }//IF
        
        chatArea.addChatBox(chatMessage, site);
        chatArea.clearTextAndGrabFocus();
    }//FUN
    
    public void removeMessages() {
    	chatArea.clearChatBox();
    }//FUN
    
    public void removeContact() {
    	this.contact = null;
    }//FUN
}//CLASS