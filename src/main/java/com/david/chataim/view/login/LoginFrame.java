package com.david.chataim.view.login;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.david.chataim.controller.Controller;
import com.david.chataim.controller.Dimens;
import com.david.chataim.controller.ImageController;
import com.david.chataim.controller.events.ExitMouseListener;
import com.david.chataim.controller.events.MoveWindowListener;
import com.david.chataim.view.login.components.CoverPanel;
import com.david.chataim.view.login.components.LoginPanel;
import com.david.chataim.view.login.components.RegisterPanel;
import com.david.chataim.view.main.UsersWindow;

import lombok.Getter;

import java.awt.Color;
import java.awt.Dimension;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	@Getter private JPanel contentPane;
	private JPanel panelHeader;
	@Getter private JLabel lblExit;
	private CoverPanel panelCover;
	@Getter private LoginPanel panelLogin;
	@Getter private RegisterPanel panelRegister;


	public LoginFrame() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Dimens.LOGIN_W, Dimens.LOGIN_H);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		panelHeader = new JPanel();
		panelHeader.setOpaque(false);
		panelHeader.setLayout(null);
		panelHeader.setBounds(0, 0, Dimens.LOGIN_HEADER_W, Dimens.LOGIN_HEADER_H);
		Controller.s().setMessagePanel(panelHeader);
		
		// Move window with the mouse
		MoveWindowListener moveWindowListener = new MoveWindowListener(this);
		panelHeader.addMouseMotionListener(moveWindowListener.new MouseDragged());
		panelHeader.addMouseListener(moveWindowListener.new MouseAdapter());
		
		lblExit = new JLabel();
		lblExit.addMouseListener(new ExitMouseListener());
		lblExit.setIcon(new ImageIcon(UsersWindow.class.getResource(ImageController.EXIT_BLACK)));
		lblExit.setPreferredSize(new Dimension(30, 30));
		lblExit.setBounds(875, 11, 30, 30);
		panelHeader.add(lblExit);
		
		panelLogin = new LoginPanel();
		panelLogin.setBounds(0, 0, 554, 525);
		
		panelRegister = new RegisterPanel(this);
		panelRegister.setBounds(Dimens.LOGIN_W-554, -Dimens.LOGIN_H, 554, 525);
		
		panelCover = new CoverPanel(this);
		panelCover.setBounds(554, 0, 371, 525);
		
		contentPane.add(panelHeader);
		contentPane.add(panelCover);
		contentPane.add(panelLogin);
		contentPane.add(panelRegister);
		
		setLocationRelativeTo(null);
	}//Constructor
	
	public void blockInputs(boolean block) {
		block = !block;
		panelCover.blockInputs(block);
		panelRegister.blockInputs(block);
	}//FUN
}//CLASS