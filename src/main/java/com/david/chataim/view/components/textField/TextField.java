package com.david.chataim.view.components.textField;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.david.chataim.controller.ImageController;

import lombok.Getter;

public class TextField extends JTextField {

	private static final long serialVersionUID = 1L;

	@Getter private int round = 10;
    private Color shadowColor = new Color(170, 170, 170);
    private final Insets shadowSize = new Insets(2, 5, 8, 5);
    
    
    public void setPlaceHolder(String text) {
    	new TextPrompt(text, this);
    }//SET
    
    public void setRound(int round) {
        this.round = round;
        //createImageShadow();
        repaint();
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
        repaint();
    }

    public TextField() {
        setOpaque(false);
        setForeground(new Color(80, 80, 80));
        setSelectedTextColor(new Color(255, 255, 255));
        setSelectionColor(new Color(133, 209, 255));
        setBorder(new EmptyBorder(10, 12, 15, 12));
        setBackground(new Color(255, 255, 255));
    }
    
    public void setMargin(int top, int left, int bottom, int right) {
    	setBorder(BorderFactory.createCompoundBorder(this.getBorder(), BorderFactory.createEmptyBorder(top, left, bottom, right)));
    }//SET

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        double width = getWidth() - (shadowSize.left + shadowSize.right);
        double height = getHeight() - (shadowSize.top + shadowSize.bottom);
        double x = shadowSize.left;
        double y = shadowSize.top;
        g2.setColor(getBackground());
        Area area = new Area(new RoundRectangle2D.Double(x, y, width, height, round, round));
        g2.fill(area);
        try {
			g2.drawImage(ImageIO.read(getClass().getResource(ImageController.SEARCH)), 10, 7, null);
		}//TRY
        catch (IOException e) {e.printStackTrace();}//CATCH
        g2.dispose();
        super.paintComponent(grphcs);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
    }//SET
}//CLASS