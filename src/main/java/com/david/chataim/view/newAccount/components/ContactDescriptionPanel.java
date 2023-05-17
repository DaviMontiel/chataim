package com.david.chataim.view.newAccount.components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.david.chataim.controller.ImageController;
import com.david.chataim.controller.LanguageController;
import com.david.chataim.controller.events.ShowMessagePanel;
import com.david.chataim.controller.events.newAccount.CheckFieldsOfNewContact;
import com.david.chataim.controller.events.newAccount.ShowTermsAndCoditions;
import com.david.chataim.view.components.MyCheckBox;
import com.david.chataim.view.components.MyTextArea;
import com.david.chataim.view.components.button.Button;
import com.david.chataim.view.newAccount.events.CheckMtaSize;

import lombok.Getter;

public class ContactDescriptionPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblDescription;
	private JPanel panelDescription;
	@Getter private MyTextArea mtaDescription;
	@Getter private MyCheckBox mcbAnonymous;
	@Getter private MyCheckBox mcbConditions;
	private Button btnContinue;
	
	
	public ContactDescriptionPanel() {
		setOpaque(false);
		setLayout(new GridBagLayout());
		
		// TITLE DESCRIPTION
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 0;
		lblDescription = new JLabel(LanguageController.getWord(33));
		lblDescription.setFont(new Font("SansSerif", 1, 16));
		add(lblDescription, gbc_lblDescription);
		
		// TA DESCRIPTION IN A PANEL (FOR CHECK THE TA SIZE)
		GridBagConstraints gbc_mtfDescription = new GridBagConstraints();
		gbc_mtfDescription.gridx = 0;
		gbc_mtfDescription.gridy = 1;
		panelDescription = new JPanel();
		panelDescription.setOpaque(false);
		mtaDescription = new MyTextArea();
		mtaDescription.countCharacters(LanguageController.getWord(34));
		mtaDescription.getDocument().addDocumentListener(new CheckMtaSize(mtaDescription));
		panelDescription.add(mtaDescription);
		add(panelDescription, gbc_mtfDescription);
		
		// ANONYMOUS CB
		GridBagConstraints gbc_mcbAnonymous = new GridBagConstraints();
		gbc_mcbAnonymous.gridx = 0;
		gbc_mcbAnonymous.gridy = 2;
		mcbAnonymous = new MyCheckBox();
		mcbAnonymous.setIcon(new ImageIcon(getClass().getResource(ImageController.INFO)));
		mcbAnonymous.addMouseListenerToIcon(new ShowMessagePanel(LanguageController.getWord(36)));
		mcbAnonymous.setText(LanguageController.getWord(35));
		add(mcbAnonymous, gbc_mcbAnonymous);
		
		// TERMS AND CODITIONS CB
		GridBagConstraints gbc_mcbConditions = new GridBagConstraints();
		gbc_mcbConditions.insets = new Insets(10, 0, 0, 0);
		gbc_mcbConditions.gridx = 0;
		gbc_mcbConditions.gridy = 3;
		mcbConditions = new MyCheckBox();
		mcbConditions.setText(LanguageController.getWord(37));
		mcbConditions.addActionListener(new ShowTermsAndCoditions());
		add(mcbConditions, gbc_mcbConditions);
		
		// VALID DATA AND SEND
		GridBagConstraints gbc_btnX = new GridBagConstraints();
		gbc_btnX.insets = new Insets(20, 0, 0, 0);
		gbc_btnX.gridx = 0;
		gbc_btnX.gridy = 4;
		btnContinue = new Button();
		btnContinue.setText(LanguageController.getWord(38));
		add(btnContinue, gbc_btnX);
		
		// MARGIN RIGHT
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 22, 0, 0);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		JPanel panelMargin = new JPanel();
		panelMargin.setOpaque(false);
		add(panelMargin, gbc_panel);
	}//Constructor
	
	public void setComponentsToListener(CheckFieldsOfNewContact listener) {
		listener.setMtaDescription(mtaDescription);
		listener.setMcbAnonymous(mcbAnonymous);
		listener.setMcbConditions(mcbConditions);
		
		// APPLY EVENT METHOD TO BTN
		btnContinue.addActionListener(listener);
	}//FUN
	
	public void setSize() {
		lblDescription.setPreferredSize(new Dimension((int) (getWidth()/1.1), (int) (getHeight()/14)));
		panelDescription.setPreferredSize(new Dimension((int) (getWidth()/1.1), (int) (getHeight()/2)));
		mtaDescription.setSize(new Dimension((int) (getWidth()/1.1), (int) (getHeight()/2)));
		
		mcbAnonymous.setPreferredSize(new Dimension((int) (getWidth()/1.1), (int) (getHeight()/16)));
		mcbConditions.setPreferredSize(new Dimension((int) (getWidth()/1.1), (int) (getHeight()/16)));

		btnContinue.setPreferredSize(new Dimension((int) (getWidth()/2), (int) (getHeight()/11)));
	}//FUN
}//CLASS