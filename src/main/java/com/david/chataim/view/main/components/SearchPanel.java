package com.david.chataim.view.main.components;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.david.chataim.controller.ColorController;

public class SearchPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	

	@Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        GradientPaint gra = new GradientPaint(0, 0, ColorController.PRIMARY_COLOR, 0, getHeight(), ColorController.SECONDARY_COLOR);
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }//FUN
}//CLASS