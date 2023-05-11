package com.david.chataim.controller.events;

import java.awt.event.MouseEvent;

import javax.swing.JComponent;

public class MouseClicked {

	public static boolean click(MouseEvent e, JComponent component) {
		return e.getPoint().x < component.getWidth() && e.getPoint().x > 0 && e.getPoint().y > 0 && e.getPoint().y < component.getHeight();
	}//FUN
}//CLASS