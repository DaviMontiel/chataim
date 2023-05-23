package com.david.chataim.view.components.textField;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import lombok.Getter;

public class MyTextArea extends JTextArea {

    private static final long serialVersionUID = 1L;

    private Icon prefixIcon;
    private Icon suffixIcon;
    private String hint = "";
    @Getter private JLabel lblCharCount;


    public MyTextArea() {
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(0, 0, 0, 0));
        setForeground(Color.decode("#7A8C8D"));
        setFont(new java.awt.Font("sansserif", 0, 13));
        setSelectionColor(new Color(75, 175, 152));
        setLineWrap(true);
        setWrapStyleWord(true);
    }//Constructor

    @Override
    protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(230, 245, 241));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
        paintIcon(g);
        super.paintComponent(g);
        lblCharCount.setBounds(getWidth() - 100, getHeight() - 18, 90, 20);
    }//FUN

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (getText().length() == 0) {
            int h = getHeight();
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            g.setColor(new Color(200, 200, 200));
            g.drawString(hint, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }//IF
    }//FUN
    
    public void countCharacters(String text) {
    	lblCharCount = new JLabel("0 "+text);
        lblCharCount.setForeground(Color.GRAY);
        add(lblCharCount);
        getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { updateCharCount(text); }
            public void removeUpdate(DocumentEvent e) { updateCharCount(text); }
            public void insertUpdate(DocumentEvent e) { updateCharCount(text); }
        });
    }//FUN
    
    private void paintIcon(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (prefixIcon != null) {
            Image prefix = ((ImageIcon) prefixIcon).getImage();
            int y = (getHeight() - prefixIcon.getIconHeight()) / 2;
            g2.drawImage(prefix, 10, y, this);
        }//IF
        if (suffixIcon != null) {
            Image suffix = ((ImageIcon) suffixIcon).getImage();
            int y = (getHeight() - suffixIcon.getIconHeight()) / 2;
            g2.drawImage(suffix, getWidth() - suffixIcon.getIconWidth() - 10, y, this);
        }//IF
    }//FUN
    
    private void updateCharCount(String text) {
        int count = getText().length();
        if (count > 255) {
        	lblCharCount.setForeground(Color.red);
        } else {
        	lblCharCount.setForeground(Color.decode("#7A8C8D"));
        }//IF
        lblCharCount.setText(count +" "+ text);
    }//FUN
}//CLASS