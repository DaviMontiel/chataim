package com.david.chataim.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.david.chataim.model.Register;
import com.david.chataim.view.login.LoginFrame;
import com.david.chataim.view.login.components.VerifyCodePanel;

public class ShowVerifyCodePanel {
	
	public static void show(LoginFrame loginFrame, Register register) {
		// GET CONTENTPANE
		JPanel contentPane = loginFrame.getContentPane();
		
		// ADD NEW PANEL
		VerifyCodePanel newPanel = new VerifyCodePanel();
		newPanel.setBounds(0, 0, contentPane.getWidth(), contentPane.getHeight());
		newPanel.setCheckCodeListener(register);
		
		// CLOSE PANEL EVENT
		newPanel.setCloseListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(newPanel);
				Controller.s().getCurrentFrame().repaint();
				loginFrame.blockInputs(false);
			}//EVENT
		});
		contentPane.add(newPanel, 0);
		contentPane.repaint();
		
		// BLOCK INPUTS
		loginFrame.blockInputs(true);
	}//Constructor
}//CLASS