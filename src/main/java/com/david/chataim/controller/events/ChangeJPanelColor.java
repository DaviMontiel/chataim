package com.david.chataim.controller.events;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import com.david.chataim.controller.ColorController;

public class ChangeJPanelColor extends MouseAdapter {

	@Override
	public void mouseEntered(MouseEvent e) {
		((JPanel) e.getSource()).setBackground(ColorController.PRIMARY_COLOR);
		((JPanel) e.getSource()).setOpaque(true);
	}//EVT
	
	@Override
	public void mouseExited(MouseEvent e) {
		((JPanel) e.getSource()).setBackground(null);
		((JPanel) e.getSource()).setOpaque(false);
	}//EVT
}//CLASS