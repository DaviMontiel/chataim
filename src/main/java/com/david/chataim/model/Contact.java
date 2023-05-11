package com.david.chataim.model;

import java.awt.Image;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class Contact {
	
	@Setter @Getter private int id;
	@Setter @Getter private String originalName;
	@Setter @Getter private String description;
	@Setter @Getter private Image image;
	@Setter @Getter private boolean anonymous;
	@Setter @Getter private Timestamp last_connection;
	
	@Setter @Getter private int chat;
	@Setter @Getter private String otherName;
	@Setter @Getter private boolean connected;
	
}//CLASS