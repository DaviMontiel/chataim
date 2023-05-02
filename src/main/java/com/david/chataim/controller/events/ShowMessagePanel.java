package com.david.chataim.controller.events;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.david.chataim.controller.Controller;

import lombok.Setter;

public class ShowMessagePanel extends MouseMotionAdapter {
	
	@Setter private JComponent component;
	private String text;
	@Setter private Rectangle iconBounds;
	
	private JTextArea taText; 
	private boolean isInSite;
	
	
	public ShowMessagePanel(String text) {
		this.text = text;
		isInSite = false;
	}//Constructor

	@Override
    public void mouseMoved(MouseEvent e) {
        if (iconBounds.contains(e.getPoint())) {
            if (!isInSite) {
            	initComponents();
				isInSite = true;
				Controller.s().getCurrentFrame().getContentPane().add(taText, 0);
				Controller.s().getCurrentFrame().getContentPane().repaint();
            }//IF
        } else {
        	if (taText != null) {
        		Controller.s().getCurrentFrame().getContentPane().remove(taText);
            	Controller.s().getCurrentFrame().getContentPane().repaint();
            	taText = null;
            	isInSite = false;
        	}//IF
        }//IF
    }//EVENT
	
	private void initComponents() {
        // Ajustar la posición del JPanel
        Point panelPosition = SwingUtilities.convertPoint(component, iconBounds.getLocation(), component.getRootPane());

        taText = new JTextArea(text);
        taText.setBorder(BorderFactory.createLineBorder(Color.black));
        taText.setFont(new Font("Courier New", Font.PLAIN, 12));
        taText.setLineWrap(true);
        taText.setWrapStyleWord(true);
        taText.setEditable(false);
        taText.setSelectionColor(new Color(75, 175, 152));
        taText.setBackground(new Color(230, 245, 241));
        taText.setForeground(Color.decode("#7A8C8D"));

        // POSITION ON THE FRAME
        taText.setBounds(panelPosition.x + iconBounds.width, panelPosition.y - 20, 200, 15*((int) Math.ceil((double) text.length()/27)));
        taText.setForeground(Color.gray);
	}//FUN
}//CLASS