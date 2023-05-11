package com.david.chataim.view.mainFrame.components.chat;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.david.chataim.controller.Controller;
import com.david.chataim.model.ChatMessage;
import com.david.chataim.model.Contact;
import com.david.chataim.view.mainFrame.components.chat.component.ChatArea;
import com.david.chataim.view.mainFrame.components.chat.component.ChatBox;
import com.david.chataim.view.mainFrame.components.chat.model.ModelMessage;
import com.david.chataim.view.mainFrame.components.chat.swing.Background;
import com.david.chataim.view.mainFrame.components.chat.swing.ChatEvent;

import lombok.Getter;

public class ChatPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private Background background;
    private ChatArea chatArea;
    
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
    	// BTNs EVENTs
        chatArea.addChatEvent(new ChatEvent() {
            @Override
            public void mousePressedSendButton(ActionEvent evt) {
            	try {
            		String now = new SimpleDateFormat("dd/MM/yyyy, hh:mmaa").format(new Date());
					Timestamp time = new Timestamp(new SimpleDateFormat("dd/MM/yyyy, hh:mmaa").parse(now).getTime());
					ChatMessage message = new ChatMessage(Controller.s().getCurrentContact(), chatArea.getText().trim(), time);
	            	addMessage(message);
	            	
	            	// SHARE MESSAGE
	            	Controller.s().sendMessageTo(contact.getChat(), message, contact.getId());
				} catch (ParseException e) {
					e.printStackTrace();
				}//CATCH
            }//EVENT

            @Override
            public void mousePressedFileButton(ActionEvent evt) {
//            	Icon icon = new ImageIcon(getClass().getResource(ImageController.DEFAULT_PROFILE));
//                String name = "David";
//                String date = df.format(new Date());
//                String message = chatArea.getText().trim();
//                chatArea.addChatBox(new ModelMessage(icon, name, date, message), ChatBox.BoxType.LEFT);
//                chatArea.clearTextAndGrabFocus();
            }//EVENT

            @Override
            public void keyTyped(KeyEvent evt) {}//EVENT
        });
    }//FUN
    
    public void setContact(Contact contact) {
    	this.contact = contact;
    	setTitle(contact);
    }//FUN
    
    private void setTitle(Contact title) {
    	if (contact.getOtherName() == null) {
        	chatArea.setTitle(contact.getOriginalName());
        } else {
        	chatArea.setTitle(contact.getOtherName());
        }//IF
    }//SET
    
    public void addMessage(ChatMessage chatMessage) {
        ChatBox.BoxType site;
        Contact messageContact = chatMessage.getContact();
        if (messageContact.getId() == contact.getId()) {
        	site = ChatBox.BoxType.LEFT;
        } else {
        	site = ChatBox.BoxType.RIGHT;
        }//IF
        
        Icon icon = new ImageIcon(messageContact.getImage());
        String date = new SimpleDateFormat("dd/MM/yyyy, hh:mmaa").format(chatMessage.getSend());
        String message = chatMessage.getText().trim();
        
        chatArea.addChatBox(new ModelMessage(icon, messageContact.getOriginalName(), date, message), site);
        chatArea.clearTextAndGrabFocus();
    }//FUN
    
    public void removeMessages() {
    	chatArea.clearChatBox();
    }//FUN
}//CLASS