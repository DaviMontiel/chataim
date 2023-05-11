package com.david.chataim.controller.events.menus;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.david.chataim.controller.Controller;
import com.david.chataim.controller.Dimens;
import com.david.chataim.controller.events.MouseClicked;
import com.david.chataim.view.mainFrame.UsersWindow;
import com.david.chataim.view.mainFrame.components.NewContactPanel;

public class ShowPanel implements MouseListener {
	
	public static final byte NEW_GROUP = 0;
	public static final byte NEW_CONTACT = 1;
	public static final byte CONFIGURATION_MENU = 2;
	
	private byte numPanel;
	private boolean show;
	

	public ShowPanel(final byte numPanel) {
		this.numPanel = numPanel;
		show = false;
	}//Constructor

	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	
	@Override
	public void mousePressed(MouseEvent e) {}//EVENT

	@Override
	public void mouseReleased(MouseEvent e) {
		new Thread() {
			@Override
			public void run() {
				if (!show) {
					show = true;
					
					// GET POSITION OF LBL
					JLabel label = (JLabel) e.getSource();
					
					// El mouse ha sido soltado dentro o fuera del JLabel
			        if (MouseClicked.click(e, label)) {
			        	JPanel panel = ((UsersWindow) Controller.s().getCurrentFrame()).getPanelBorder();
			        	
			            switch (numPanel) {
			            	case NEW_GROUP:
			            		
			            		break;
			            		
			            	case NEW_CONTACT:
			            		showLeftPanel(panel, new NewContactPanel(ShowPanel.this));
			            		break;
			            		
			            	case CONFIGURATION_MENU:
			            		
			            		break;
			            }//SWITCH
			        }//IF
			        
			        // CAN REPEAT THIS
					show = false;
				}//IF
			}
		}.start();
	}//EVENT
	
	private final byte MOVE = 9;
	private final byte AT = 10;
	
	
	private void showLeftPanel(JPanel basePanel, JPanel newPanel) {
		// QUIT EVENTS
		UsersWindow frame = (UsersWindow) Controller.s().getCurrentFrame();
		Controller.s().quitLeftEvents(true);
        
		// SET INIT BOUNDS
		newPanel.setBounds(1, 1, -8, basePanel.getHeight()*2 -2);
		frame.getPanelBorder().add(newPanel, 0);
		
		// MOVE PANEL
		move(MOVE, newPanel);
		
		// QUIT VISIBILITY OF ANY COMPONENTS
		Controller.s().quitLeftVisibility(true);
		frame.repaint();
	}//FUN
	
	public void closeLeftPanel(JPanel newPanel) {
		// MOVE PANEL
		move(-MOVE, newPanel);
		
		// PUT EVENTS
		Controller.s().quitLeftEvents(false);
		
		// PUT VISIBILITY OF ANY COMPONENTS
		Controller.s().quitLeftVisibility(false);
		Controller.s().getCurrentFrame().repaint();
	}//FUN
	
	private void move(int move, JPanel newPanel) {
		for (int f=0; f<Dimens.CONTACTS_PANEL_W; f+=MOVE) {
			newPanel.setBounds(1,1, newPanel.getWidth()+move, newPanel.getHeight());

			try { Thread.sleep(AT); }//TRY
			catch (InterruptedException e) { e.printStackTrace(); }//CATCH
		}//FOR
	}//FUN
}//CLASS