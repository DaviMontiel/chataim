package com.david.chataim.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Register {

	@Getter @Setter private String name;
	@Getter @Setter private String email;
	@Getter @Setter private String passwd;
	@Getter @Setter private int verificationCodeId;
	
}//CLASS