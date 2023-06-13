package com.david.chataim.view.login.components;

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
import com.david.chataim.controller.events.login.CheckFieldsSingIn;
import com.david.chataim.view.components.button.Button;
import com.david.chataim.view.components.textField.MyPasswordField;
import com.david.chataim.view.components.textField.MyTextField;

import lombok.Getter;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel lblTitle;
	@Getter private MyTextField tfEmail;
	@Getter private MyPasswordField tfPasswd;
//	private JLabel lblForgotPasswd;
	

	public LoginPanel(String email, String passwd) {
		setOpaque(false);
		setLayout(new GridBagLayout());

		// TITLE
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		lblTitle = new JLabel(LanguageController.getWord(10));
		lblTitle.setFont(new Font("sansserif", 1, 30));
		lblTitle.setForeground(ColorController.PRIMARY_COLOR);
		add(lblTitle, gbc_lblTitle);
		
		// FIELDs
		GridBagConstraints gbc_mtfEmail = new GridBagConstraints();
		gbc_mtfEmail.insets = new Insets(30, 0, 0, 0);
		gbc_mtfEmail.gridx = 0;
		gbc_mtfEmail.gridy = 1;
		tfEmail = new MyTextField();
		tfEmail.setText(email);
		tfEmail.setPrefixIcon(new ImageIcon(getClass().getResource(ImageController.EMAIL_ICON)));
		tfEmail.setHint(LanguageController.getWord(11));
		tfEmail.setPreferredSize(new Dimension(350, 40));
		add(tfEmail, gbc_mtfEmail);
		
		GridBagConstraints gbc_mtfPasswd = new GridBagConstraints();
		gbc_mtfPasswd.insets = new Insets(20, 0, 0, 0);
		gbc_mtfPasswd.gridx = 0;
		gbc_mtfPasswd.gridy = 2;
		tfPasswd = new MyPasswordField();
		tfPasswd.setText(passwd);
		tfPasswd.setPrefixIcon(new ImageIcon(getClass().getResource(ImageController.PASSWD_ICON)));
		tfPasswd.setHint(LanguageController.getWord(12));
		tfPasswd.setPreferredSize(new Dimension(350, 40));
		add(tfPasswd, gbc_mtfPasswd);
		
		// FORGOT
//		GridBagConstraints gbc_lblForgotPasswd = new GridBagConstraints();
//		gbc_lblForgotPasswd.insets = new Insets(10, 0, 0, 0);
//		gbc_lblForgotPasswd.gridx = 0;
//		gbc_lblForgotPasswd.gridy = 3;
//		lblForgotPasswd = new JLabel(LanguageController.getWord(13));
//		lblForgotPasswd.addMouseListener(new MouseAdapter() {
//            public void mouseEntered(MouseEvent evt) {
//            	lblForgotPasswd.setForeground(Color.blue);
//            }
//            
//            public void mouseExited(MouseEvent evt) {
//            	lblForgotPasswd.setForeground(Color.black);
//            }
//        });
//		add(lblForgotPasswd, gbc_lblForgotPasswd);
		
		// BTN
		GridBagConstraints gbc_btn = new GridBagConstraints();
		gbc_btn.insets = new Insets(20, 0, 0, 0);
		gbc_btn.gridx = 0;
		gbc_btn.gridy = 4;
		Button btnSingIn = new Button();
		btnSingIn.setPreferredSize(new Dimension(250,40));
		
		// IF IS DB CONECTED SHOW TEXT
		btnSingIn.setIcon(new ImageIcon(getClass().getResource(ImageController.GIF_LOADING)));
		new Thread() {
			@Override
			public void run() {
				while (!Controller.s().isDatabaseConnected()) {
					try { Thread.sleep(500); }//TRY
					catch (Exception e) {}//CATCH
				}//WHILE
				
				btnSingIn.setIcon(null);
				btnSingIn.setText(LanguageController.getWord(9));
				btnSingIn.addActionListener(new CheckFieldsSingIn(LoginPanel.this));
				
				// LOGIN
				if (!email.isEmpty() && !passwd.isEmpty()) {
					btnSingIn.doClick();
				}//IF
			}
		}.start();

		btnSingIn.setFocusPainted(false);
		btnSingIn.setBackground(ColorController.PRIMARY_COLOR);
		btnSingIn.setForeground(ColorController.LOGIN_TF_FOREGROUND);
  		add(btnSingIn, gbc_btn);
	}//Constructor
}//CLASS