package com.david.chataim.view.mainFrame.components.chat.swing;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import com.david.chataim.controller.ColorController;

public class Background extends JPanel {

    private static final long serialVersionUID = 1L;

    
	public Background() {
        setOpaque(false);
    }//Constructor

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        int width = getWidth();
        int height = getHeight();
//        g2.setPaint(new GradientPaint(0, 0, new Color(58, 72, 85), width, 0, new Color(28, 38, 50)));
//        g2.setPaint(new GradientPaint(0, 0, new Color(37, 81, 149), width, 0, new Color(9, 35, 75)));
        g2.setPaint(new GradientPaint(0, 0, ColorController.PRIMARY_COLOR, width, 0, Color.white));
        g2.fillRect(0, 0, width, height);
        g2.dispose();
        super.paintComponent(grphcs);
    }//EVENT
}//CLASS