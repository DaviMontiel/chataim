package com.david.chataim.view.mainFrame.components;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.david.chataim.controller.ColorController;
import com.david.chataim.controller.Dimens;
import com.david.chataim.controller.events.MoveWindowListener;
import com.david.chataim.view.mainFrame.UsersWindow;

public class HeaderPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	
	public HeaderPanel(UsersWindow frame) {
		setBackground(ColorController.PRIMARY_COLOR);
		setPreferredSize(new Dimension(Dimens.WINDOW_W_PRE, Dimens.WINDOW_HEADER_H));
		
		// Move window with the mouse
		MoveWindowListener moveWindowListener = new MoveWindowListener(frame);
		setLayout(new BorderLayout(0, 0));
		addMouseMotionListener(moveWindowListener.new MouseDragged());
		addMouseListener(moveWindowListener.new MouseAdapter());
	}//Constructor
}//CLASS