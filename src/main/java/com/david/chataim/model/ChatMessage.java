package com.david.chataim.model;

import java.awt.Image;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class ChatMessage {

	@Setter @Getter private int id;
	@Setter @Getter private String text;
	@Setter @Getter private Image image;
	@Setter @Getter private int idAscii;
	@Setter @Getter private Timestamp send;
	
	@Setter @Getter private Contact contact;

	
	// FOR SEND MESSAGE
	public ChatMessage(Contact contact, String text, Timestamp send) {
		this.contact = contact;
		this.text = text;
		this.send = send;
	}//Constructor
	
	// FOR SEND FILE
	public ChatMessage(Contact contact, Image image, Timestamp send) {
		this.contact = contact;
		this.image = image;
		this.send = send;
	}//Constructor
	
	// FOR SEND ASCII
	public ChatMessage(Contact contact, int idAscii, Timestamp send) {
		this.contact = contact;
		this.idAscii = idAscii;
		this.send = send;
	}//Constructor

	// FOR GET MESSAGE
	public ChatMessage(int id, String text, Timestamp send) {
		this.id = id;
		this.text = text;
		this.send = send;
	}//Constructor
	
	// FOR GET FILE
	public ChatMessage(int id, Image image, Timestamp send) {
		this.id = id;
		this.image =image;
		this.send = send;
	}//Constructor
	
	// FOR GET MESSAGE
	public ChatMessage(int id, int idAscii, Timestamp send) {
		this.id = id;
		this.idAscii = idAscii;
		this.send = send;
	}//Constructor
}//CLASS