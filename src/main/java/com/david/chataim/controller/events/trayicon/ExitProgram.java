package com.david.chataim.controller.events.trayicon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.david.chataim.controller.Controller;

public class ExitProgram implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Controller.s().exitProgram();
	}//EVENT
}//CLASS