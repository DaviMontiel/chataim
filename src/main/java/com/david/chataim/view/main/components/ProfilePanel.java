package com.david.chataim.view.main.components;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.david.chataim.controller.ImageController;
import com.david.chataim.model.Contact;

import lombok.Getter;
import lombok.Setter;

public class ProfilePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	@Getter private Contact contact;
	@Setter private Image profileImage;
	
	
	@Override
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, getWidth(), getHeight()));
        g.drawImage(contact.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
	
	public ProfilePanel(Contact contact) {
		this.contact = contact;
		this.profileImage = ImageController.getDefaultImageUser();
		setOpaque(false);
	}//Constructor
}//CLASS