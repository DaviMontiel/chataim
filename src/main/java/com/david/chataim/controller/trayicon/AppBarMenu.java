package com.david.chataim.controller.trayicon;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionListener;

public class AppBarMenu extends PopupMenu{

	private static final long serialVersionUID = 1L;
	

	public void addMenuItem(String title) {
		add(new MenuItem(title));
	}//FUN
	
	public void addMenuItem(String title, ActionListener listener) {
		MenuItem menuItem = new MenuItem(title);
		menuItem.addActionListener(listener);
		add(menuItem);
	}//FUN
}//CLASS