package com.david.chataim.controller.events.chat;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;

import com.david.chataim.controller.Controller;
import com.david.chataim.controller.LanguageController;
import com.david.chataim.controller.events.GetWithDialog;
import com.david.chataim.model.ChatMessage;
import com.david.chataim.view.components.Message;
import com.david.chataim.view.mainFrame.components.chat.ChatPanel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SendMessage implements ChatEvent {
	
	private ChatPanel panelChat;
	

	@Override
    public void mousePressedSendButton(ActionEvent evt) {
		new Thread() {
    		@Override
    		public void run() {
    			sendMessage(panelChat.getChatArea().getText());
    		}
    	}.start();
    }//EVENT

    @Override
    public void mousePressedFileButton(ActionEvent evt) {
    	new Thread() {
    		@Override
    		public void run() {
    			// GET FILE
    	    	File file = GetWithDialog.showFileDialog();
    	    	if (file != null) {
    	    		sendFile(file);
    	    	}//IF
    		}
    	}.start();
    }//EVENT
    
    public void mousePressedAsciiButton(ActionEvent evt) {
    	new Thread() {
    		@Override
    		public void run() {
    			// GET FILE
    	    	Object[] ascii = GetWithDialog.showAsciiDialog();
    	    	
    	    	if (ascii[0] != null) {
    	    		sendAscii((int) ascii[0]);
    	    	}//IF
    		}
    	}.start();
    }//EVENT

    @Override
    public void keyTyped(KeyEvent evt) {}//EVENT
    
    private void sendMessage(String text) {
    	if (!text.trim().isEmpty()) {
    		ChatMessage message = new ChatMessage(Controller.s().getCurrentContact(), text.trim(), getTime());
    		
    		// SHARE MESSAGE
        	boolean wasSent = Controller.s().sendMessageTo(panelChat.getContact().getChat(), message, panelChat.getContact().getId());
        	
    		if (wasSent) {
        		panelChat.addMessage(message);
        	} else {
        		Controller.s().showMessage(Message.MessageType.ERROR, LanguageController.getWord(50));
        	}//IF
    	}//IF
    }//FUN
    
    private void sendFile(File file) {
    	ChatMessage message = new ChatMessage(Controller.s().getCurrentContact(), new ImageIcon(file.getAbsolutePath()).getImage(), getTime());
    	
    	// SHARE MESSAGE
    	boolean wasSent = Controller.s().sendImageTo(panelChat.getContact().getChat(), message, panelChat.getContact().getId());
    	
    	if (wasSent) {
    		panelChat.addMessage(message);
    	} else {
    		Controller.s().showMessage(Message.MessageType.ERROR, LanguageController.getWord(51));
    	}//IF
    }//FUN
    
    private void sendAscii(int idAscii) {
    	ChatMessage message = new ChatMessage(Controller.s().getCurrentContact(), idAscii, getTime());
    	
    	// SHARE MESSAGE
    	boolean wasSent = Controller.s().sendAsciiTo(panelChat.getContact().getChat(), message, panelChat.getContact().getId());
    	
    	if (wasSent) {
    		panelChat.addMessage(message);
    	} else {
    		Controller.s().showMessage(Message.MessageType.ERROR, LanguageController.getWord(50));
    	}//IF
    }//FUN
    
    private Timestamp getTime() {
    	try {
    		String now = new SimpleDateFormat("dd/MM/yyyy, hh:mmaa").format(new Date());
    		return new Timestamp(new SimpleDateFormat("dd/MM/yyyy, hh:mmaa").parse(now).getTime());
		}//TRY
    	catch (ParseException e) { return null; }//CATCH
    }//FUN
}//CLASS