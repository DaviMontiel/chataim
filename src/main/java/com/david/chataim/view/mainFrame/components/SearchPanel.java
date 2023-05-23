package com.david.chataim.view.mainFrame.components;

import java.awt.BorderLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.david.chataim.controller.ColorController;
import com.david.chataim.controller.LanguageController;
import com.david.chataim.view.components.textField.TextField;

public class SearchPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private TextField tfSearch;
	
	
	public SearchPanel() {
		setLayout(new BorderLayout());
		
		// SEARCH CONTACT
		addSearch(true);
	}//Constructor
	
	private void addSearch(boolean setFocusable) {
		tfSearch = new TextField();
		tfSearch.setMargin(0, 30, 0, 0);
		tfSearch.setPlaceHolder(LanguageController.getWord(1));
		tfSearch.setBounds(10, 11, 274, 39);
		tfSearch.setFocusable(setFocusable);
		add(tfSearch, BorderLayout.NORTH);
	}//FUN
	
	@Override
    protected void paintComponent(Graphics grphcs) {
		Graphics2D g2 = (Graphics2D) grphcs;
        GradientPaint gra = new GradientPaint(0, 0, ColorController.PRIMARY_COLOR, 0, getHeight(), ColorController.SECONDARY_COLOR);
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }//FUN
	
	public void quitFocus(boolean paint) {
		remove(tfSearch);
		addSearch(!paint);
		revalidate();
	}//FUN
}//CLASS