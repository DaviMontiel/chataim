package com.david.chataim.controller.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.david.chataim.controller.Controller;
import com.david.chataim.model.Contact;

public class ShowChatPanel implements MouseListener {

	private Contact contact;
	
	private boolean show;
	
	
	public ShowChatPanel(Contact contact) {
		this.contact = contact;
		show = false;
	}//Constructor
	
	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	
	@Override
	public void mousePressed(MouseEvent e) {}//EVENT

	@Override
	public void mouseReleased(MouseEvent e) {
		new Thread() {
			@Override
			public void run() {
				if (!show) {
					show = true;
					
					// GET POSITION OF LBL
					JPanel panel = (JPanel) e.getSource();
					
					// El mouse ha sido soltado dentro o fuera del JPanel
			        if (MouseClicked.click(e, panel)) {
			        	Controller.s().showChat(contact);
			        }//IF
			        
			        // CAN REPEAT THIS
					show = false;
				}//IF
			}
		}.start();
	}//EVENT
}//CLASS