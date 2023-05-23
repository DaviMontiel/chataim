package com.david.chataim.view.mainFrame.components.menus;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.david.chataim.controller.ColorController;
import com.david.chataim.controller.Dimens;
import com.david.chataim.controller.ImageController;
import com.david.chataim.controller.LanguageController;
import com.david.chataim.controller.events.OnlyNumbersTf;
import com.david.chataim.controller.events.menus.CheckFieldsAddContact;
import com.david.chataim.controller.events.menus.ClosePanel;
import com.david.chataim.controller.events.menus.ShowPanel;
import com.david.chataim.view.components.button.Button;
import com.david.chataim.view.components.button.ButtonPanel;
import com.david.chataim.view.components.textField.MyTextField;

import javax.swing.SwingConstants;

public class NewContactPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ButtonPanel btnBack;
	private JLabel lblTitle;
	
	
	public NewContactPanel(ShowPanel listener) {
		setBackground(ColorController.TERCIARY_COLOR);
		setLayout(null);
		
		// EVET 
		ClosePanel closeListener = new ClosePanel(listener, this);
		
		// GO BACK
		btnBack = new ButtonPanel(new ImageIcon(this.getClass().getResource(ImageController.BACK)).getImage());
		btnBack.setBounds(10, 10, 48, 48);
		btnBack.addMouseListener(closeListener);
		add(btnBack);
				
		// TITLE
		lblTitle = new JLabel(LanguageController.getWord(42));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBackground(ColorController.PRIMARY_COLOR);
		lblTitle.setOpaque(true);
		lblTitle.setForeground(Color.white);
		lblTitle.setFont(new Font("SansSerif", 1, 20));
		lblTitle.setBounds(0, 0, Dimens.CONTACTS_PANEL_W, 100);
		add(lblTitle);
		
		// TFs
		MyTextField tfId = new MyTextField();
		tfId.setPrefixIcon(new ImageIcon(getClass().getResource(ImageController.USER_ICON)));
		tfId.setHint(LanguageController.getWord(43));
		tfId.setBounds(10, 120, Dimens.CONTACTS_PANEL_W-20, 40);
		tfId.addKeyListener(new OnlyNumbersTf(8));
		add(tfId);
		
		// BTN
		Button btnAdd = new Button();
		btnAdd.setText(LanguageController.getWord(44));
		btnAdd.setBounds(tfId.getX()+5, 180, tfId.getWidth()-10, 40);
		btnAdd.addActionListener(new CheckFieldsAddContact(tfId, closeListener));
		add(btnAdd);
	}//Constructor
}//CLASS