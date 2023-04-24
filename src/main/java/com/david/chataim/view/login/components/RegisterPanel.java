package com.david.chataim.view.login.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.david.chataim.controller.ColorController;
import com.david.chataim.controller.Controller;
import com.david.chataim.controller.ImageController;
import com.david.chataim.controller.LanguageController;
import com.david.chataim.controller.events.login.CheckFieldsForSendEmail;
import com.david.chataim.view.components.Button;
import com.david.chataim.view.components.MyPasswordField;
import com.david.chataim.view.components.MyTextField;
import com.david.chataim.view.login.LoginFrame;

import lombok.Getter;

public class RegisterPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private RegisterPanel own = this;
	private JLabel lblTitle;
	@Getter private MyTextField tfUserName;
	@Getter private MyTextField tfEmail;
	@Getter private MyPasswordField tfPasswd;
	private Button btnLoginUp;
	
	private String[] textSaved;
	

	public RegisterPanel(LoginFrame frame) {
		setOpaque(false);
		setLayout(new GridBagLayout());
		textSaved = new String[3];

		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		lblTitle = new JLabel(LanguageController.getWord(14));
		lblTitle.setFont(new Font("sansserif", 1, 30));
		lblTitle.setForeground(ColorController.PRIMARY_COLOR);
		add(lblTitle, gbc_lblTitle);
		
		GridBagConstraints gbc_mtfUserName = new GridBagConstraints();
		gbc_mtfUserName.insets = new Insets(30, 0, 0, 0);
		gbc_mtfUserName.gridx = 0;
		gbc_mtfUserName.gridy = 1;
		tfUserName = new MyTextField();
		tfUserName.setPrefixIcon(new ImageIcon(getClass().getResource(ImageController.USER_ICON)));
		tfUserName.setHint(LanguageController.getWord(15));
		tfUserName.setPreferredSize(new Dimension(350, 40));
		add(tfUserName, gbc_mtfUserName);
		
		GridBagConstraints gbc_mtfEmail = new GridBagConstraints();
		gbc_mtfEmail.insets = new Insets(10, 0, 0, 0);
		gbc_mtfEmail.gridx = 0;
		gbc_mtfEmail.gridy = 2;
		tfEmail = new MyTextField();
		tfEmail.setPrefixIcon(new ImageIcon(getClass().getResource(ImageController.EMAIL_ICON)));
		tfEmail.setHint(LanguageController.getWord(16));
		tfEmail.setPreferredSize(new Dimension(350, 40));
		add(tfEmail, gbc_mtfEmail);
		
		GridBagConstraints gbc_mtfPasswd = new GridBagConstraints();
		gbc_mtfPasswd.insets = new Insets(10, 0, 0, 0);
		gbc_mtfPasswd.gridx = 0;
		gbc_mtfPasswd.gridy = 3;
		tfPasswd = new MyPasswordField();
		tfPasswd.setPrefixIcon(new ImageIcon(getClass().getResource(ImageController.PASSWD_ICON)));
		tfPasswd.setHint(LanguageController.getWord(17));
		tfPasswd.setPreferredSize(new Dimension(350, 40));
		add(tfPasswd, gbc_mtfPasswd);
		
		GridBagConstraints gbc_lblbutton = new GridBagConstraints();
		gbc_lblbutton.insets = new Insets(20, 0, 0, 0);
		gbc_lblbutton.gridx = 0;
		gbc_lblbutton.gridy = 4;
		btnLoginUp = new Button();
		btnLoginUp.setPreferredSize(new Dimension(250,40));
		
		// IF IS DB CONECTED SHOW TEXT
		btnLoginUp.setIcon(new ImageIcon(getClass().getResource(ImageController.GIF_LOADING)));
		new Thread() {
			@Override
			public void run() {
				while (!Controller.s().isDatabaseConnected()) {
					try { Thread.sleep(500); }//TRY
					catch (Exception e) {}//CATCH
				}//WHILE
				btnLoginUp.setIcon(null);
				btnLoginUp.setText(LanguageController.getWord(5));
				btnLoginUp.addActionListener(new CheckFieldsForSendEmail(own, frame));
			}
		}.start();
				
		btnLoginUp.setFocusPainted(false);
		btnLoginUp.setBackground(new Color(7, 164, 121));
		btnLoginUp.setForeground(new Color(250, 250, 250));
  		add(btnLoginUp, gbc_lblbutton);
	}//Constructor
	
	public void blockInputs(boolean block) {
		tfUserName.setEnabled(block);
		tfEmail.setEnabled(block);
		tfPasswd.setEnabled(block);
		
		// SAVE TEXTs FOR THE VISUAL BUG
		if (block) {
			tfUserName.setText(textSaved[0]);
			tfEmail.setText(textSaved[1]);
			tfPasswd.setText(textSaved[2]);
			
		} else {
			textSaved[0] = tfUserName.getText();
			textSaved[1] = tfEmail.getText();
			textSaved[2] = new String(tfPasswd.getPassword());
			
			tfUserName.setText("");
			tfEmail.setText("");
			tfPasswd.setText("");
			
			tfUserName.setCaretPosition(0);
			tfEmail.setCaretPosition(0);
			tfPasswd.setCaretPosition(0);
		}//IF
		
		btnLoginUp.setEnabled(block);
	}//FUN
}//CLASS