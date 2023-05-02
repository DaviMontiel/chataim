package com.david.chataim.view.login.components;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.david.chataim.controller.LanguageController;
import com.david.chataim.controller.events.register.CheckVerificationCode;
import com.david.chataim.controller.events.register.ControlTfVerificationCode;
import com.david.chataim.model.Register;
import com.david.chataim.view.components.MyTextField;
import com.david.chataim.view.components.PanelRound;
import com.david.chataim.view.components.button.ButtonOutLine;

public class VerifyCodePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel lblTitle;
	private JLabel lblDescription;
	private MyTextField tfCode;
	private ButtonOutLine btnOk;
	private ButtonOutLine btnCancel;
	private PanelRound contentPanel;
	

	public VerifyCodePanel() {
		setOpaque(false);
		setLayout(new GridBagLayout());
		
		// PANEL VERIFICATION
		contentPanel = new PanelRound();
		contentPanel.setLayout(new GridBagLayout());
		contentPanel.setPreferredSize(new Dimension(308, 175));
		add(contentPanel);
	    
		// TXTs
	    GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		lblTitle = new JLabel(LanguageController.getWord(27));
		lblTitle.setFont(new Font("sansserif", 1, 24));
		lblTitle.setForeground(new Color(63, 63, 63));
		contentPanel.add(lblTitle, gbc_lblTitle);
		
		GridBagConstraints gbc_lblDdescription = new GridBagConstraints();
		gbc_lblDdescription.insets = new Insets(5, 0, 0, 0);
		gbc_lblDdescription.gridx = 0;
		gbc_lblDdescription.gridy = 1;
		lblDescription = new JLabel(LanguageController.getWord(28));
		lblDescription.setFont(new Font("sansserif", 0, 11));
		lblDescription.setForeground(new Color(63, 63, 63));
		contentPanel.add(lblDescription, gbc_lblDdescription);
		
		// TF
		GridBagConstraints gbc_tfCode = new GridBagConstraints();
		gbc_tfCode.insets = new Insets(5, 0, 0, 0);
		gbc_tfCode.gridx = 0;
		gbc_tfCode.gridy = 2;
		tfCode = new MyTextField();
		tfCode.setHorizontalAlignment(JLabel.CENTER);
		tfCode.setPreferredSize(new Dimension(250, 38));
		tfCode.setFont(new Font("sansserif", 0, 11));
		tfCode.setForeground(new Color(63, 63, 63));
		tfCode.addKeyListener(new ControlTfVerificationCode());
		contentPanel.add(tfCode, gbc_tfCode);
		
		// BTNs
		GridBagConstraints gbc_panelBtns = new GridBagConstraints();
		gbc_panelBtns.insets = new Insets(15, 0, 0, 0);
		gbc_panelBtns.gridx = 0;
		gbc_panelBtns.gridy = 3;
		JPanel panelBtns = new JPanel();
		panelBtns.setOpaque(false);
		contentPanel.add(panelBtns, gbc_panelBtns);
		
		btnOk = new ButtonOutLine();
		btnOk.setText(LanguageController.getWord(29));
		btnOk.setPreferredSize(new Dimension(100, 32));
		btnOk.setBackground(new java.awt.Color(18, 138, 62));
		btnOk.setFocusPainted(false);
		panelBtns.add(btnOk);
		
		btnCancel = new ButtonOutLine();
		btnCancel.setText(LanguageController.getWord(30));
		btnCancel.setPreferredSize(new Dimension(100, 32));
		btnCancel.setBackground(new java.awt.Color(192, 25, 25));
		btnCancel.setFocusPainted(false);
		panelBtns.add(btnCancel);
	}//Constructor
	
	@Override
	protected void paintComponent(Graphics grphcs) {
	    Graphics2D g2 = (Graphics2D) grphcs;
	    g2.setColor(new Color(50, 50, 50));
	    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
	    g2.fillRect(0, 0, getWidth(), getHeight());
	    g2.setComposite(AlphaComposite.SrcOver);
	    super.paintComponent(grphcs);
	}//FUN
	
	public void setCheckCodeListener(Register register) {
		btnOk.addActionListener(new CheckVerificationCode(tfCode, register));
	}//FUN
	
	public void setCloseListener(ActionListener closeListener) {
		btnCancel.addActionListener(closeListener);
	}//FUN
}//CLASS