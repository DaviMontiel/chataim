package com.david.chataim.view.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import lombok.Getter;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	@Getter private Image image;
	@Getter private boolean isDistinct;
	
	
	public ImagePanel(Image image) {
		this.image = image;
		isDistinct = false;
	}//Constructor
	
	@Override
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D) g;
	    g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	    Area area = new Area(new Rectangle2D.Float(0, 0, getWidth(), getHeight()));
	    area.subtract(new Area(new Ellipse2D.Float(0, 0, getWidth(), getHeight())));
	    g2d.setClip(area);
	    g2d.setPaint(new Color(255, 255, 255, 128));
	    g2d.fillRect(0, 0, getWidth(), getHeight());
    }//FUN
	
	public void setImage(ImageIcon imageIcon) {
		isDistinct = true;
		image = imageIcon.getImage();
	}//SET
}//CLASS