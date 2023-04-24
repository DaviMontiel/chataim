package com.david.chataim.controller.events.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.david.chataim.controller.Controller;
import com.david.chataim.view.login.LoginFrame;
import com.david.chataim.view.login.components.VerifyCodePanel;

public class ShowVerifyCodePanel {
	
	public static void show(LoginFrame loginFrame, int code) {
		// GET CONTENTPANE
		JPanel contentPane = loginFrame.getContentPane();
		
		// ADD NEW PANEL
		VerifyCodePanel newPanel = new VerifyCodePanel(code);
		newPanel.setBounds(0, 0, contentPane.getWidth(), contentPane.getHeight());
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