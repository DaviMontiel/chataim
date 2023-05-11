package com.david.chataim.view.mainFrame.components;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import lombok.Setter;

public class ProfilePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	@Setter private Image image;
	
	
	public ProfilePanel(Image image) {
		this.image = image;
		setOpaque(false);
	}//Constructor
	
	@Override
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, getWidth(), getHeight()));
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }//EVENT
}//CLASS