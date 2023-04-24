package com.david.chataim.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

public class Message {

	@Setter @Getter private int contactId;
	@Setter @Getter private int text;
	@Setter @Getter private LocalDateTime date;
	
}//CLASS