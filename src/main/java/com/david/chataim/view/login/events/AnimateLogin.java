package com.david.chataim.view.login.events;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

import com.david.chataim.controller.Dimens;
import com.david.chataim.controller.LanguageController;
import com.david.chataim.view.components.button.ButtonOutLine;
import com.david.chataim.view.login.LoginFrame;
import com.david.chataim.view.login.components.CoverPanel;
import com.david.chataim.view.login.components.LoginPanel;
import com.david.chataim.view.login.components.RegisterPanel;

public class AnimateLogin implements ActionListener {

	// BG PANELS
	private LoginPanel loginPanel;
	private RegisterPanel registerPanel;
	
	
	// COMPONENTS
	private CoverPanel coverPanel;
	
	private JLabel lblTitle;
	private JLabel lblDescription;
	private JLabel lblDescription2;
	private ButtonOutLine btn;
	
	
	// COVER PANEL
    private int coverX = 0;
    private int loginY = 0;
    private int registerY = 0;
    private final int registerX;
    private static boolean isMoving;
    private boolean isLogin;
    
    // START ALPHA FOR COMPONENTS
    private float alpha = 0.8f;
    
    // Milliseconds for the initial and between-event delay
    private final int delay = 10;
    private final int pixelsToMove = 14;
    
    
    public AnimateLogin(CoverPanel coverPanel, LoginFrame frame) {
    	this.coverPanel = coverPanel;
    	this.loginPanel = frame.getPanelLogin();
    	this.registerPanel = frame.getPanelRegister();
    	this.lblTitle = coverPanel.getLblTitle();
    	this.lblDescription = coverPanel.getLblDescription();
    	this.lblDescription2 = coverPanel.getLblDescription2();
    	this.btn = coverPanel.getBtn();
    	
    	this.registerX = registerPanel.getX();
    	
    	isLogin = true;
    }//Constructor
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!isMoving) {
    		isMoving = true;
    		coverX = coverPanel.getX();
    		loginY = loginPanel.getY();
    		registerY = registerPanel.getY();
    		
            hideAndShowComponents();
    		
    		// MOVE COVER LEFT / RIGHT
            if (coverX == 0) {
            	loginPanel.setVisible(true);
            	new Timer(delay, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	//MOVE
                    	coverX += pixelsToMove;
                        coverPanel.setLocation(coverX, 0);
                        
                        loginY -= pixelsToMove;
                        loginPanel.setLocation(0, loginY);
                        
                        registerY -= pixelsToMove;
                        registerPanel.setLocation(registerX, registerY);
                        
                        // CHECK LIMITS OF THE PANEL WITH THE FRAME
                        if (coverX + coverPanel.getWidth() > Dimens.LOGIN_W) {
                        	// ALIGN
                        	coverPanel.setLocation(Dimens.LOGIN_W - coverPanel.getWidth(), 0);
                        	
                        	((Timer) e.getSource()).stop();
                        	
                            isMoving = false;
                            isLogin = true;
                        }//IF
                    }
                }).start();
            } else {
            	new Timer(delay, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	//MOVE
                    	coverX -= pixelsToMove;
                        coverPanel.setLocation(coverX, 0);
                        
                        loginY += pixelsToMove;
                        loginPanel.setLocation(0, loginY);
                        
                        registerY += pixelsToMove;
                        registerPanel.setLocation(registerX, registerY);
                        
                        // CHECK LIMITS OF THE PANEL WITH THE FRAME
                        if (coverX < 0) {
                        	// ALIGN
                        	coverPanel.setLocation(0, 0);
                        	
                        	((Timer) e.getSource()).stop();
                        	
                        	loginPanel.setVisible(false);
                            isMoving = false;
                            isLogin = false;
                        }//IF
                    }
                }).start();
            }//IF
    	}//IF
	}//EVNT
	
	// TO HIDE
	private void hideAndShowComponents() {
		alpha = 1f;
		new Timer(6, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alpha += -0.05f;
                
                if (alpha < 0f) {
                	alpha = 0f;
                	((Timer) e.getSource()).stop();
                	
                	changeTextsOfComponents();
                    toShow();
                }//IF
                
                changeAlphaToComponents(alpha);
            }
		}).start();
	}//FUN
	
	private void toShow() {
		new Timer(6, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alpha += 0.05f;
                
                if (alpha > 1f) {
                    alpha = 1f;
                    ((Timer) e.getSource()).stop();
                    
                    changeTextsOfComponents();
                }//IF
                
                changeAlphaToComponents(alpha);
            }
		}).start();
	}//FUN
	
	public void changeAlphaToComponents(float alpha) {
		alpha *= 255;
		Color foreground = lblTitle.getForeground();
        lblTitle.setForeground(new Color(foreground.getRed(), foreground.getGreen(), foreground.getBlue(), (int) (alpha)));
        lblDescription.setForeground(new Color(foreground.getRed(), foreground.getGreen(), foreground.getBlue(), (int) (alpha)));
        lblDescription2.setForeground(new Color(foreground.getRed(), foreground.getGreen(), foreground.getBlue(), (int) (alpha)));
	}//FUN
	
	private void changeTextsOfComponents() {
		if (isLogin) {
        	lblTitle.setText(LanguageController.getWord(6));
        	lblDescription.setText(LanguageController.getWord(7));
        	lblDescription2.setText(LanguageController.getWord(8));
        	btn.setText(LanguageController.getWord(9));
        } else {
        	lblTitle.setText(LanguageController.getWord(2));
        	lblDescription.setText(LanguageController.getWord(3));
        	lblDescription2.setText(LanguageController.getWord(4));
        	btn.setText(LanguageController.getWord(5));
        }//IF
	}//FUN
}//CLASS