package com.david.chataim.model;

import java.awt.Image;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Contact {
	
	@Setter @Getter private int id;
	@Setter @Getter private String name;
	@Setter @Getter private String description;
	@Setter @Getter private Image image;
//	@Setter @Getter private boolean anonymous;
//	@Setter @Getter private Timestamp last_connection;
	
}//CLASS