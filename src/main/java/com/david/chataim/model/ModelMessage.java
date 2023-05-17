package com.david.chataim.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import lombok.Getter;

public class ModelMessage {

	@Getter private Icon icon;
    @Getter private String name;
    @Getter private String date;
    @Getter private String message;
    @Getter private ImageIcon image;
    

    public ModelMessage(Icon icon, String name, String date, String message) {
        this.icon = icon;
        this.name = name;
        this.date = date;
        this.message = message;
    }//Constructor
    
    public ModelMessage(Icon icon, String name, String date, ImageIcon image) {
        this.icon = icon;
        this.name = name;
        this.date = date;
        this.image = image;
    }//Constructor
}//CLASS