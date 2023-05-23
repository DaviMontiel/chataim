package com.david.chataim.view.mainFrame.components.chat.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import com.david.chataim.controller.ColorController;
import com.david.chataim.controller.Controller;
import com.david.chataim.model.ChatMessage;
import com.david.chataim.view.mainFrame.components.chat.swing.AutoWrapText;
import com.david.chataim.view.mainFrame.components.chat.swing.ImageAvatar;

import net.miginfocom.swing.MigLayout;

public class ChatBox extends JComponent {

    private static final long serialVersionUID = 1L;
    
	private final BoxType boxType;
    private final ChatMessage message;
    
    public static enum BoxType { LEFT, RIGHT }//ENUM
    

    public ChatBox(BoxType boxType, ChatMessage message) {
        this.boxType = boxType;
        this.message = message;
        initBox();
    }//Constructor
    
    private JLabel createImageLabel(Image image) {
        ImageIcon icon = new ImageIcon(image);
        Image scaledImage = icon.getImage();
    	if (icon.getIconWidth() > 400) {
    		scaledImage = icon.getImage().getScaledInstance(400, calcularProporcional(icon.getIconWidth(), icon.getIconHeight(), 400), Image.SCALE_SMOOTH);
    	}//IF
    	
    	icon = new ImageIcon(scaledImage);
    	
    	if (icon.getIconHeight() > 300) {
    		scaledImage = icon.getImage().getScaledInstance(calcularProporcional(icon.getIconHeight(), icon.getIconWidth(), 300), 300, Image.SCALE_SMOOTH);
    	}//IF
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setPreferredSize(new Dimension(scaledIcon.getIconWidth(), scaledIcon.getIconHeight())); // Establece el tamaño preferido del JLabel a las dimensiones de la imagen
        return imageLabel;
    }//FUN
    
    public static int calcularProporcional(int anchoOriginal, int altoOriginal, int nuevoAncho) {
        double proporcion = (double) nuevoAncho / anchoOriginal;
        int nuevoAlto = (int) (altoOriginal * proporcion);
        
        return nuevoAlto;
    }//FUN

    private void initBox() {
        String rightToLeft = boxType == BoxType.RIGHT ? ",rtl" : "";
        setLayout(new MigLayout("inset 5" + rightToLeft, "[40!]5[]", "[top]"));
        ImageAvatar avatar = new ImageAvatar();
        avatar.setBorderSize(1);
        avatar.setBorderSpace(1);
        
        avatar.setImage(new ImageIcon(message.getContact().getImage()));
        
        String date = new SimpleDateFormat("yyyy/MM/dd, HH:mm").format(message.getSend());
        JLabel labelDate = new JLabel(message.getContact().getOriginalName() + " | " + date);
        labelDate.setForeground(ColorController.MESSAGE_RIGHT_DATE);
        add(avatar, "height 40,width 40");
        
        // SHOW TEXT / IMAGE
        if (message.getText() != null) {
        	add(createTextPane(message.getText()), "gapy 20, wrap");
        } else if (message.getIdAscii() != 0) {
        	add(createTextPane(Controller.s().getAscii(message.getIdAscii())), "gapy 20, wrap");
        } else {
        	JLabel imageLabel = createImageLabel(message.getImage());
            imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            add(imageLabel, "gapy 20, grow, wrap");
        }//IF
        
        add(labelDate, "gapx 20,span 2");
    }//FUN
    
    private JTextPane createTextPane(String texto) {
    	JTextPane text = new JTextPane();
        text.setEditorKit(new AutoWrapText());
        text.setText(texto);
        text.setBackground(new Color(0, 0, 0, 0));
        text.setForeground(ColorController.MESSAGE_TEXT);
        text.setSelectionColor(new Color(200, 200, 200, 100));
        text.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        text.setOpaque(false);
        text.setEditable(false);
        
        return text;
    }//FUN

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int width = getWidth();
        int height = getHeight();
        
        if (boxType == BoxType.LEFT) {
            Area area = new Area(new RoundRectangle2D.Double(25, 25, width - 25, height - 25 - 16 - 10, 5, 5));
            area.subtract(new Area(new Ellipse2D.Double(5, 5, 45, 45)));
            g2.setPaint(new GradientPaint(0, 0, new Color(255, 94, 98, 240), width, 0, new Color(255, 153, 102, 240)));
            g2.fill(area);
        } else {
            Area area = new Area(new RoundRectangle2D.Double(0, 25, width - 25, height - 25 - 16 - 10, 5, 5));
            area.subtract(new Area(new Ellipse2D.Double(width - 50, 5, 45, 45)));
            g2.setColor(ColorController.MESSAGE_RIGHT);
            g2.fill(area);
        }//IF
        
        g2.dispose();
        super.paintComponent(g);
    }//FUN
}//CLASS