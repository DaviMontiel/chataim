package com.david.chataim.view.newAccount;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.david.chataim.controller.ColorController;
import com.david.chataim.controller.Controller;
import com.david.chataim.controller.Dimens;
import com.david.chataim.controller.ImageController;
import com.david.chataim.controller.LanguageController;
import com.david.chataim.controller.events.ExitMouseListener;
import com.david.chataim.controller.events.MoveWindowListener;
import com.david.chataim.controller.events.newAccount.CheckFieldsOfNewContact;
import com.david.chataim.model.Register;
import com.david.chataim.view.mainFrame.UsersWindow;
import com.david.chataim.view.newAccount.components.ContactDescriptionPanel;
import com.david.chataim.view.newAccount.components.ProfileImagePanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Font;

public class NewAccountFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private ProfileImagePanel panelProfileImage;
	private ContactDescriptionPanel panelContactDescription;
	

	public NewAccountFrame(Register register) {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Dimens.NEW_ACCOUNT_W, Dimens.NEW_ACCOUNT_H);
		
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		// HEADER BG
		JPanel panelHeaderBG = new JPanel();
		panelHeaderBG.setOpaque(true);
		panelHeaderBG.setBackground(ColorController.PRIMARY_COLOR);
		panelHeaderBG.setBounds(0, 0, Dimens.NEW_ACCOUNT_W, Dimens.NEW_ACCOUNT_HEADER_H);
		
		// HEADER MOVE & MESSAGE
		JPanel panelHeader = new JPanel();
		panelHeader.setOpaque(false);
		panelHeader.setLayout(null);
		panelHeader.setBounds(0, 0, Dimens.NEW_ACCOUNT_W, Dimens.NEW_ACCOUNT_HEADER_H);
		Controller.s().setMessagePanel(panelHeader);
		
		// Move window with the mouse
		MoveWindowListener moveWindowListener = new MoveWindowListener(this);
		panelHeader.addMouseMotionListener(moveWindowListener.new MouseDragged());
		panelHeader.addMouseListener(moveWindowListener.new MouseAdapter());
		
		// EXIT BTN
		JLabel lblExit = new JLabel();
		lblExit.setIcon(new ImageIcon(UsersWindow.class.getResource(ImageController.EXIT_BLACK)));
		lblExit.setPreferredSize(new Dimension(30, 30));
		lblExit.setBounds(panelHeader.getWidth() - 40, 10, 30, 30);
		lblExit.addMouseListener(new ExitMouseListener());
		panelHeader.add(lblExit);
		
		JLabel lblTitle = new JLabel(LanguageController.getWord(31));
		lblTitle.setFont(new Font("SansSerif", 1, 30));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.white);
		lblTitle.setBounds(0, 0, getWidth(), getHeight()/5);
		lblTitle.setVerticalAlignment(SwingConstants.TOP);
		lblTitle.setBorder(new EmptyBorder(lblTitle.getHeight()/10, 0, 0, 0));
		
		// PROFILE IMAGE
		panelProfileImage = new ProfileImagePanel();
		panelProfileImage.setBounds(0, getHeight()/5 - lblTitle.getHeight()/5, getWidth()/2, getHeight() - lblTitle.getHeight() + lblTitle.getHeight()/5);
		panelProfileImage.setSize();
		
		// ACCOUNT OPTIONS
		panelContactDescription = new ContactDescriptionPanel();
		panelContactDescription.setBounds(panelProfileImage.getWidth(), panelProfileImage.getY(), panelProfileImage.getWidth(), panelProfileImage.getHeight());
		panelContactDescription.setSize();
		
		contentPane.add(lblTitle);
		contentPane.add(panelContactDescription);
		contentPane.add(panelProfileImage);
		contentPane.add(panelHeader);
		contentPane.add(panelHeaderBG);
		
		// SET BTN LISTENER
		setComponentsToListener(register);
		
		setLocationRelativeTo(null);
	}//Constructor
	
	public void setComponentsToListener(Register register) {
		CheckFieldsOfNewContact checkFieldsOfNewContact = new CheckFieldsOfNewContact();
		checkFieldsOfNewContact.setRegister(register);
		panelProfileImage.setComponentsToListener(checkFieldsOfNewContact);
		panelContactDescription.setComponentsToListener(checkFieldsOfNewContact);
	}//FUN
}//CLASS