package com.david.chataim.controller.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.david.chataim.controller.Controller;

public class ShowActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Controller.s().showProgram();
	}//EVENT
}//CLASS