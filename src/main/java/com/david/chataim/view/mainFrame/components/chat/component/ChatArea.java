package com.david.chataim.view.mainFrame.components.chat.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.david.chataim.controller.ColorController;
import com.david.chataim.controller.LanguageController;
import com.david.chataim.controller.events.chat.ChatEvent;
import com.david.chataim.controller.events.chat.ShowDescription;
import com.david.chataim.model.ChatMessage;
import com.david.chataim.view.mainFrame.components.chat.animation.AnimationFloatingButton;
import com.david.chataim.view.mainFrame.components.chat.animation.AnimationScroll;
import com.david.chataim.view.mainFrame.components.chat.swing.Button;
import com.david.chataim.view.mainFrame.components.chat.swing.RoundPanel;
import com.david.chataim.view.mainFrame.components.chat.swing.TextField;
import com.david.chataim.view.mainFrame.components.chat.swing.scroll.ScrollBar;

import javaswingdev.FontAwesome;
import javaswingdev.FontAwesomeIcon;
import javaswingdev.GoogleMaterialDesignIcon;
import javaswingdev.GoogleMaterialIcon;
import javaswingdev.GradientType;
import net.miginfocom.swing.MigLayout;

public class ChatArea extends JPanel {

    private static final long serialVersionUID = 1L;
    
	private AnimationScroll animationScroll;
    private AnimationFloatingButton animationFloatingButton;
    private List<ChatEvent> events = new ArrayList<>();

    private MigLayout layout;
    private MigLayout layoutLayered;
    private JLayeredPane layeredPane;
    private JPanel header;
    private JPanel body;
    private JPanel bottom;
    private TextField textMessage;
    private JScrollPane scrollBody;
    private Button floatingButton;
    private JLabel labelTitle;
    
    
    public ChatArea() {
        init();
        initAnimator();
    }//Constructor
    
    public void addChatEvent(ChatEvent event) {
        events.add(event);
    }//FUN

    private void init() {
        setOpaque(false);
        layout = new MigLayout("fill, wrap, inset 0", "[fill]", "[fill,40!][fill, 100%][shrink 0,::30%]");
        header = createHeader();
        body = createBody();
        bottom = createBottom();
        layeredPane = createLayeredPane();
        scrollBody = createScroll();
        scrollBody.setViewportView(body);
        scrollBody.setVerticalScrollBar(new ScrollBar());
        scrollBody.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollBody.getViewport().setOpaque(false);
        scrollBody.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            private int oldValues;

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                int value = scrollBody.getVerticalScrollBar().getValue();
                int extent = scrollBody.getVerticalScrollBar().getModel().getExtent();
                if ((value + extent) >= scrollBody.getVerticalScrollBar().getMaximum() - 300) {
                    animationFloatingButton.hide();
                } else if (oldValues <= e.getValue()) {
                    if (!animationScroll.isRunning()) {
                        animationFloatingButton.show();
                    }//IF
                }//IF
            }
        });
        floatingButton = createFloatingButton();
        layeredPane.setLayer(floatingButton, JLayeredPane.POPUP_LAYER);
        layeredPane.add(floatingButton, "pos 100%-50 100%,h 40,w 40");
        layeredPane.add(scrollBody);
        setLayout(layout);
        add(header);
        add(layeredPane);
        add(bottom);
    }//FUN

    private void initAnimator() {
        animationScroll = new AnimationScroll(body);
        animationFloatingButton = new AnimationFloatingButton(layoutLayered, floatingButton);
    }//FUN
    
    public void setUserDescription(String description) {
    	MouseListener[] mouseListeners = header.getMouseListeners();
    	for (MouseListener listener : mouseListeners) {
    	    header.removeMouseListener(listener);
    	}//FOR
    	
    	header.addMouseListener(new ShowDescription(description));
    }//V

    private JPanel createHeader() {
        RoundPanel panel = new RoundPanel();
        panel.setLayout(new MigLayout("fill, inset 2"));
        panel.setBackground(ColorController.PRIMARY_COLOR_A);
        panel.setOpaque(true);
        labelTitle = new JLabel();
        labelTitle.setFont(labelTitle.getFont().deriveFont(14f));
        labelTitle.setBorder(new EmptyBorder(2, 10, 2, 2));
        labelTitle.setForeground(new Color(240, 240, 240));
        panel.add(labelTitle);
        
        return panel;
    }//FUN

    private JPanel createBody() {
        RoundPanel panel = new RoundPanel();
        panel.setBackground(new Color(0, 0, 0, 0));
        panel.setLayout(new MigLayout("wrap,fillx"));
        return panel;
    }//FUN

    private JPanel createBottom() {
        RoundPanel panel = new RoundPanel();
        panel.setBackground(new Color(255, 255, 255, 20));
        panel.setOpaque(true);
        panel.setLayout(new MigLayout("fill, inset 2", "[fill,34!]2[fill,34!]2[fill]2[fill,34!]", "[bottom]"));
        
        GoogleMaterialIcon iconFile = new GoogleMaterialIcon(GoogleMaterialDesignIcon.ATTACH_FILE, GradientType.VERTICAL, new Color(210, 210, 210), new Color(255, 255, 255), 20);
        GoogleMaterialIcon iconSend = new GoogleMaterialIcon(GoogleMaterialDesignIcon.SEND, GradientType.VERTICAL, new Color(0, 133, 237), new Color(90, 182, 255), 20);
        GoogleMaterialIcon iconAscii = new GoogleMaterialIcon(GoogleMaterialDesignIcon.INSERT_EMOTICON, GradientType.VERTICAL, new Color(210, 210, 210), new Color(255, 255, 255), 20);
        Button btnFile = new Button();
        Button btnSend = new Button();
        Button btnAscii = new Button();
        btnFile.setFocusable(false);
        btnSend.setFocusable(false);
        btnAscii.setFocusable(false);
        btnFile.setIcon(iconFile.toIcon());
        btnSend.setIcon(iconSend.toIcon());
        btnAscii.setIcon(iconAscii.toIcon());
        textMessage = new TextField();
        textMessage.setHint(LanguageController.getWord(45));
        textMessage.setCaretColor(Color.white);
        
        textMessage.addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) { revalidate(); }
            @Override public void keyTyped(KeyEvent ke) { runEventKeyTyped(ke); }
        });
        
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { runEventMousePressedSendButton(e); }
        });
        btnAscii.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { runEventMousePressedAsciiButton(e); }
        });
        btnFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { runEventMousePressedFileButton(e); }
        });
        
        JScrollPane scroll = createScroll();
        scroll.setViewportView(textMessage);
        scroll.getViewport().setOpaque(false);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        
        panel.add(btnFile, "height 34!");
        panel.add(btnAscii, "height 34!");
        panel.add(scroll);
        panel.add(btnSend, "height 34!");
        
        return panel;
    }//FUN

    private JLayeredPane createLayeredPane() {
        JLayeredPane layer = new JLayeredPane();
        layoutLayered = new MigLayout("fill,inset 0", "[fill]", "[fill]");
        layer.setLayout(layoutLayered);
        
        return layer;
    }//FUN

    private Button createFloatingButton() {
        Button button = new Button();
        button.setBorder(null);
        FontAwesomeIcon icon = new FontAwesomeIcon(FontAwesome.ANGLE_DOWN, GradientType.VERTICAL, new Color(79, 79, 79, 240), new Color(248, 248, 248, 240), 35);
        button.setIcon(icon.toIcon());
        button.setRound(40);
        button.setBackground(new Color(100, 100, 100, 100));
        button.setPaintBackground(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animationScroll.scrollVertical(scrollBody, scrollBody.getVerticalScrollBar().getMaximum());
            }
        });
        
        return button;
    }//FUN

    private JScrollPane createScroll() {
        JScrollPane scroll = new JScrollPane();
        scroll.setBorder(null);
        scroll.setViewportBorder(null);
        scroll.setOpaque(false);
        
        return scroll;
    }//FUN

    public void addChatBox(ChatMessage message, ChatBox.BoxType type) {
        int values = scrollBody.getVerticalScrollBar().getValue();
        if (type == ChatBox.BoxType.LEFT) {
            body.add(new ChatBox(type, message), "width ::80%");
        } else {
            body.add(new ChatBox(type, message), "al right,width ::80%");
        }//IF
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                body.revalidate();
                scrollBody.getVerticalScrollBar().setValue(values);
                bottom.revalidate();
            }
        });
        
        body.repaint();
        body.revalidate();
        scrollBody.revalidate();
        scrollToBottom();
    }//FUN

    public void clearChatBox() {
        body.removeAll();
        body.repaint();
        body.revalidate();
    }//FUN

    private void scrollToBottom() {
        animationScroll.scrollVertical(scrollBody, scrollBody.getVerticalScrollBar().getMaximum());
    }//FUN

    private void runEventMousePressedSendButton(ActionEvent evt) {
        for (ChatEvent event : events) {
            event.mousePressedSendButton(evt);
        }//FOR
    }//FUN

    private void runEventMousePressedFileButton(ActionEvent evt) {
        for (ChatEvent event : events) {
            event.mousePressedFileButton(evt);
        }//FOR
    }//FUN
    
    private void runEventMousePressedAsciiButton(ActionEvent evt) {
        for (ChatEvent event : events) {
            event.mousePressedAsciiButton(evt);
        }//FOR
    }//FUN
    
    private void runEventKeyTyped(KeyEvent evt) {
        for (ChatEvent event : events) {
            event.keyTyped(evt);
        }//FOR
    }//FUN

    public String getText() {
        return textMessage.getText();
    }//GET

    public void setTitle(String title) {
        labelTitle.setText(title);
    }//SET

    public String getTitle() {
        return labelTitle.getText();
    }//GET

    public void setText(String text) {
        textMessage.setText(text);
    }//SET

    public void textGrabFocus() {
        textMessage.grabFocus();
    }//FUN

    public void clearTextAndGrabFocus() {
        textMessage.setText("");
        textMessage.grabFocus();
    }//FUN
}//CLASS