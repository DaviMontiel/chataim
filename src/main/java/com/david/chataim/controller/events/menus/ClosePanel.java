package com.david.chataim.controller.events.menus;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClosePanel extends MouseAdapter {

	private ShowPanel listener;
	private JPanel panel; 
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		new Thread() {
			@Override
			public void run() {
				listener.closeLeftPanel(panel);
			}
		}.start();
	}//EVENT
}//CLASS