package com.david.chataim.view.mainFrame.components.chat.swing;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

import com.david.chataim.controller.ColorController;

import lombok.Getter;

public class RoundPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    
	@Getter private int round;
    

    public RoundPanel() {
        setOpaque(false);
    }//Constructor

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Crea un objeto GradientPaint con los colores y la dirección del degradado
        GradientPaint gradient = new GradientPaint(0, 0, ColorController.SECONDARY_COLOR, getWidth(), 0, ColorController.PRIMARY_COLOR);
        
        // Establece el objeto GradientPaint como el Paint actual para el objeto Graphics2D
        g2.setPaint(gradient);
        
        // Dibuja el rectángulo redondeado con el degradado
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), round, round);
        
        g2.dispose();
        super.paintComponent(grphcs);
        
//        Graphics2D g2 = (Graphics2D) grphcs.create();
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2.setColor(getBackground());
//        g2.fillRoundRect(0, 0, getWidth(), getHeight(), round, round);
//        g2.dispose();
//        super.paintComponent(grphcs);
    }//EVENT
    
    public void setRound(int round) {
        this.round = round;
        repaint();
    }//SET
}//CLASS