package com.david.chataim.view.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.JCheckBox;

import com.david.chataim.controller.ColorController;
import com.david.chataim.controller.events.ShowMessagePanel;

import lombok.Setter;

public class MyCheckBox extends JCheckBox {

	private static final long serialVersionUID = 1L;
	
	@Setter private Icon icon;
	private final int border = 4;

	
    public MyCheckBox() {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setOpaque(false);
        setFocusPainted(false);
        setBackground(ColorController.PRIMARY_COLOR);
    }//Constructor

    public void addMouseListenerToIcon(ShowMessagePanel lis) {
    	MyCheckBox checkBox = this;
    	new Thread() {
    		@Override
    		public void run() {
    			try {
    				Thread.sleep(1000);
    		    	
    		    	// Obtener la posición del Icon en el JCheckBox
    				Rectangle iconBounds = getIconBounds();

    				lis.setComponent(checkBox);
    		    	lis.setIconBounds(iconBounds);
    		    	addMouseMotionListener(lis);
    			} catch (InterruptedException e) {}
    		}
    	}.start();
    }//FUN
    
    private Rectangle getIconBounds() {
        FontMetrics fm = getFontMetrics(getFont());
        
        int textWidth = fm.stringWidth(getText());
        int iconWidth = icon.getIconWidth();
        int iconHeight = icon.getIconHeight();
        int x = getInsets().left + textWidth + 20;
        int y = (getHeight() - iconHeight) / 2;
        
        return new Rectangle(x, y, iconWidth, iconHeight);
    }//FUN
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (icon != null) {
        	FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(getText());
            int iconHeight = icon.getIconHeight();
            int x = getInsets().left + textWidth + 20;
            int y = (getHeight() - iconHeight) / 2;
            icon.paintIcon(this, g, x, y);
        }//IF
    }//FUN
    
    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int ly = (getHeight() - 16) / 2;
        
        if (isSelected()) {
            if (isEnabled()) {
                g2.setColor(getBackground());
            } else {
                g2.setColor(Color.GRAY);
            }
            g2.fillRoundRect(1, ly, 16, 16, border, border);
            
            //  Draw Check icon
            int px[] = {4, 8, 14, 12, 8, 6};
            int py[] = {ly + 8, ly + 14, ly + 5, ly + 3, ly + 10, ly + 6};
            g2.setColor(Color.WHITE);
            g2.fillPolygon(px, py, px.length);
        } else {
            g2.setColor(Color.GRAY);
            g2.fillRoundRect(1, ly, 16, 16, border, border);
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(2, ly + 1, 14, 14, border, border);
        }
        g2.dispose();
    }//FUN
}//CLASS