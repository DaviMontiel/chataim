package com.david.chataim.controller.events;

import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MoveWindowListener {

	private Window window;
	private int xMouse, yMouse;
	

	public MoveWindowListener(Window window) {
		this.window = window;
	}//Constructor
	
	public class MouseDragged extends MouseMotionAdapter {
		@Override
		public void mouseDragged(MouseEvent e) {
			int xScreen = e.getXOnScreen();
			int yScreen = e.getYOnScreen();
			window.setLocation(xScreen - xMouse, yScreen - yMouse);
		}//EVNT
	}//CLASS
	
	public class MouseAdapter extends java.awt.event.MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			xMouse = e.getX();
			yMouse = e.getY();
		}//EVNT
	}//CLASS
}//CLASS