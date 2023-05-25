package com.david.chataim.controller.events.menus;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import com.david.chataim.controller.Controller;
import com.david.chataim.model.MyFile;

public class LblCloseSession extends MouseAdapter {

	@Override public void mouseEntered(MouseEvent e) { ((JLabel) e.getSource()).setForeground(Color.blue); }//EVENT
    
    @Override public void mouseExited(MouseEvent e) { ((JLabel) e.getSource()).setForeground(Color.lightGray); }//EVENT
    
    @Override
    public void mouseReleased(MouseEvent e) {
    	Controller.s().closeSession();
    }//EVENT
    
}//CLASS