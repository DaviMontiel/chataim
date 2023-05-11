package com.david.chataim.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class ChatMessage {

	@Setter @Getter private int id;
	@Setter @Getter private String text;
	@Setter @Getter private boolean isFile;
	@Setter @Getter private boolean isAscii;
	@Setter @Getter private Timestamp send;
	
	@Setter @Getter private Contact contact;

	
	// FOR SEND MESSAGE
	public ChatMessage(Contact contact, String text, Timestamp send) {
		this.contact = contact;
		this.text = text;
		this.send = send;
	}//Constructor

	// FOR GET MESSAGE
	public ChatMessage(int id, String text, boolean isFile, Timestamp send, boolean isAscii) {
		this.id = id;
		this.text = text;
		this.isFile = isFile;
		this.send = send;
	}//Constructor
}//CLASS