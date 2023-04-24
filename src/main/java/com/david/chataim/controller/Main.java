package com.david.chataim.controller;

import javax.swing.SwingUtilities;

import com.david.chataim.model.DataBase;
import com.david.chataim.view.login.LoginFrame;

public class Main {

	public static void main(String[] args) {
		try {
			Controller.s().setDatabase(new DataBase());
		}//TRY
		catch (Exception e) {e.printStackTrace();}//CATCH
		
		SwingUtilities.invokeLater(() -> {
			Controller.s().init();
			
			LoginFrame frame = new LoginFrame();
			Controller.s().setCurrentFrame(frame);
			frame.setVisible(true);
			
//			UsersWindow frame = new UsersWindow();
//			Controller.s().setFrame(frame);
//			frame.setVisible(true);
		});
	}//MAIN
}//CLASS