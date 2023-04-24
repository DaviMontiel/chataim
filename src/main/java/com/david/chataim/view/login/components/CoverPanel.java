package com.david.chataim.view.login.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.david.chataim.controller.ColorController;
import com.david.chataim.controller.LanguageController;
import com.david.chataim.view.components.ButtonOutLine;
import com.david.chataim.view.login.LoginFrame;
import com.david.chataim.view.login.events.AnimateLogin;

import lombok.Getter;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class CoverPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	@Getter private JLabel lblTitle;
	@Getter private JLabel lblDescription2;
	@Getter private JLabel lblDescription;
	@Getter private ButtonOutLine btn;
	

	public CoverPanel(LoginFrame frame) {
		setOpaque(false);
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		lblTitle = new JLabel(LanguageController.getWord(2));
		lblTitle.setFont(new Font("sansserif", 1, 30));
		lblTitle.setForeground(new Color(245, 245, 245));
		add(lblTitle, gbc_lblTitle);
		
		GridBagConstraints gbc_lblDdescription = new GridBagConstraints();
		gbc_lblDdescription.insets = new Insets(15, 0, 0, 0);
		gbc_lblDdescription.gridx = 0;
		gbc_lblDdescription.gridy = 1;
		lblDescription = new JLabel(LanguageController.getWord(3));
		lblDescription.setForeground(new Color(245, 245, 245));
		add(lblDescription, gbc_lblDdescription);
		
		GridBagConstraints gbc_lblDdescription2 = new GridBagConstraints();
		gbc_lblDdescription2.gridx = 0;
		gbc_lblDdescription2.gridy = 2;
		lblDescription2 = new JLabel(LanguageController.getWord(4));
		lblDescription2.setForeground(new Color(245, 245, 245));
        add(lblDescription2, gbc_lblDdescription2);
        
        GridBagConstraints gbc_lblbutton = new GridBagConstraints();
        gbc_lblbutton.insets = new Insets(20, 0, 0, 0);
        gbc_lblbutton.gridx = 0;
        gbc_lblbutton.gridy = 3;
        btn = new ButtonOutLine();
        btn.setPreferredSize(new Dimension(250,40));
        btn.setText(LanguageController.getWord(5));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(255, 255, 255));
        btn.setForeground(new Color(255, 255, 255));
        btn.addActionListener(new AnimateLogin(this, frame));
        add(btn, gbc_lblbutton);
	}//Constructor
	
	@Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        GradientPaint gra = new GradientPaint(0, 0, ColorController.PRIMARY_COLOR, 0, getHeight(), ColorController.SECONDARY_COLOR);
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        
        super.paintComponent(grphcs);
    }//FUN
	
	public void blockInputs(boolean block) {
		btn.setEnabled(block);
	}//FUN
}//CLASS