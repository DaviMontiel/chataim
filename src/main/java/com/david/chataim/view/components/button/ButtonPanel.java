package com.david.chataim.view.components.button;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;

public class ButtonPanel extends JLabel {

	private static final long serialVersionUID = 1L;

	private Image image;
	
	
	public ButtonPanel(Image image) {
		this.image = image;
		setOpaque(false);
	}//Constructor
	
	@Override
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }//EVENT
}//CLASS