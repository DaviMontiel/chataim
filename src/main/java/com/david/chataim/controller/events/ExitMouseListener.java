package com.david.chataim.controller.events;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.david.chataim.controller.Controller;

public class ExitMouseListener extends MouseAdapter {
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Controller.s().minimizeProgram();
	}//EVENT
}//CLASS