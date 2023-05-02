package com.david.chataim.model;

import java.awt.Image;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class Contact {
	
	@Setter @Getter private int id;
	@Setter @Getter private String name;
	@Setter @Getter private String description;
	@Setter @Getter private Image image;
	@Setter @Getter private boolean anonymous;
	@Setter @Getter private Timestamp last_connection;
	
	
	public Contact(String name, String description, Image image, boolean anonymous) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.anonymous = anonymous;
	}//Constructor
	
	public Contact(int id, String name, String description, Image image, boolean anonymous, Timestamp last_connection) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.anonymous = anonymous;
		this.last_connection = last_connection;
	}//Constructor
}//CLASS