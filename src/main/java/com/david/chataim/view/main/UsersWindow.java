package com.david.chataim.view.main;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.david.chataim.controller.ColorController;
import com.david.chataim.controller.Dimens;
import com.david.chataim.controller.ImageController;
import com.david.chataim.controller.LanguageController;
import com.david.chataim.controller.events.ExitMouseListener;
import com.david.chataim.controller.events.MoveWindowListener;
import com.david.chataim.model.Contact;
import com.david.chataim.view.components.TextField;
import com.david.chataim.view.components.scrollpane.ScrollPaneWin11;
import com.david.chataim.view.main.components.ContactsPanel;
import com.david.chataim.view.main.components.ProfilePanel;
import com.david.chataim.view.main.components.SearchPanel;

import lombok.Getter;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.BoxLayout;

public class UsersWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JPanel panelHeader;
	
	private JLabel lblExit;
	private ProfilePanel panelProfile;
	private JPanel panelChat;
	private TextField tfSearch;
	private JPanel panelSearch;
	private JButton btnFilterContacts;
	@Getter private ContactsPanel panelContacts;
	private ScrollPaneWin11 scrollPaneContacts;


	public UsersWindow() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, Dimens.WINDOW_W, Dimens.WINDOW_H);
		setLocationRelativeTo(null);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelHeader = new JPanel();
		panelHeader.setBackground(ColorController.PRIMARY_COLOR);
		panelHeader.setBounds(0, 0, Dimens.WINDOW_HEADER_W, Dimens.WINDOW_HEADER_H);
		panelHeader.setLayout(null);
		
		// Move window with the mouse
		MoveWindowListener moveWindowListener = new MoveWindowListener(this);
		panelHeader.addMouseMotionListener(moveWindowListener.new MouseDragged());
		panelHeader.addMouseListener(moveWindowListener.new MouseAdapter());
		contentPane.add(panelHeader);
		
		
		panelProfile = new ProfilePanel(new Contact(1, "David", "Descripcion", ImageController.getDefaultImageUser()));
		panelProfile.setBounds(10, 11, 30, 30);
		panelHeader.add(panelProfile);
		
		lblExit = new JLabel();
		lblExit.addMouseListener(new ExitMouseListener());
		lblExit.setIcon(new ImageIcon(UsersWindow.class.getResource(ImageController.EXIT_WHITE)));
		lblExit.setPreferredSize(new Dimension(30, 30));
		lblExit.setBounds(1000, 11, 30, 30);
		panelHeader.add(lblExit);
		
		panelSearch = new SearchPanel();
		panelSearch.setBackground(ColorController.PRIMARY_COLOR);
		panelSearch.setOpaque(false);
		panelSearch.setBounds(0, 52, 334, 548);
		contentPane.add(panelSearch);
		panelSearch.setLayout(null);
		
		btnFilterContacts = new JButton();
		btnFilterContacts.setContentAreaFilled(false);
		btnFilterContacts.setOpaque(false);
		btnFilterContacts.setBorderPainted(false);
		btnFilterContacts.setIcon(new ImageIcon(UsersWindow.class.getResource(ImageController.FILTER)));
		btnFilterContacts.setBounds(297, 16, 20, 20);
		panelSearch.add(btnFilterContacts);
		
		tfSearch = new TextField();
		tfSearch.setMargin(0, 30, 0, 0);
		tfSearch.setPlaceHolder(LanguageController.getWord(1));
		tfSearch.setBounds(10, 11, 274, 39);
		panelSearch.add(tfSearch);
		
		scrollPaneContacts = new ScrollPaneWin11();
		scrollPaneContacts.setOpaque(false);
		scrollPaneContacts.getViewport().setOpaque(false);
		scrollPaneContacts.setBorder(null);
		scrollPaneContacts.setBounds(0, 46, 334, 502);
		panelSearch.add(scrollPaneContacts);
		
		panelContacts = new ContactsPanel();
		scrollPaneContacts.setViewportView(panelContacts);
		panelContacts.setLayout(new BoxLayout(panelContacts, BoxLayout.Y_AXIS));
		panelContacts.setOpaque(false);
		
		panelContacts.addContact(new Contact(1, "David", "Descripcion", new ImageIcon("src/main/java/com/david/chataim/view/images/da.png").getImage()), "Ultimo mensaje de este usuario.", "12:34");
		panelContacts.addContact(new Contact(1, "Juan", "Descripcion de Juan", new ImageIcon("src/main/java/com/david/chataim/view/images/awp.png").getImage()), "Ultimo mensaje de este usuario.", "12:34");
//		panelContacts.addContact(new Contact(1, "David", "Descripcion", new ImageIcon("src/main/java/com/david/chataim/view/images/da.png").getImage()), "Ultimo mensaje de este usuario.", "12:34");
//		panelContacts.addContact(new Contact(1, "Juan", "Descripcion de Juan", new ImageIcon("src/main/java/com/david/chataim/view/images/awp.png").getImage()), "Ultimo mensaje de este usuario.", "12:34");
//		panelContacts.addContact(new Contact(1, "David", "Descripcion", new ImageIcon("src/main/java/com/david/chataim/view/images/da.png").getImage()), "Ultimo mensaje de este usuario.", "12:34");
//		panelContacts.addContact(new Contact(1, "Juan", "Descripcion de Juan", new ImageIcon("src/main/java/com/david/chataim/view/images/awp.png").getImage()), "Ultimo mensaje de este usuario.", "12:34");
//		panelContacts.addContact(new Contact(1, "David", "Descripcion", new ImageIcon("src/main/java/com/david/chataim/view/images/da.png").getImage()), "Ultimo mensaje de este usuario.", "12:34");
//		panelContacts.addContact(new Contact(1, "Juan", "Descripcion de Juan", new ImageIcon("src/main/java/com/david/chataim/view/images/awp.png").getImage()), "Ultimo mensaje de este usuario.", "12:34");
//		panelContacts.addContact(new Contact(1, "David", "Descripcion", new ImageIcon("src/main/java/com/david/chataim/view/images/da.png").getImage()), "Ultimo mensaje de este usuario.", "12:34");
//		panelContacts.addContact(new Contact(1, "Juan", "Descripcion de Juan", new ImageIcon("src/main/java/com/david/chataim/view/images/awp.png").getImage()), "Ultimo mensaje de este usuario.", "12:34");
//		panelContacts.addContact(new Contact(1, "David", "Descripcion", new ImageIcon("src/main/java/com/david/chataim/view/images/da.png").getImage()), "Ultimo mensaje de este usuario.", "12:34");
//		panelContacts.addContact(new Contact(1, "Juan", "Descripcion de Juan", new ImageIcon("src/main/java/com/david/chataim/view/images/awp.png").getImage()), "Ultimo mensaje de este usuario.", "12:34");
//		panelContacts.addContact(new Contact(1, "David", "Descripcion", new ImageIcon("src/main/java/com/david/chataim/view/images/da.png").getImage()), "Ultimo mensaje de este usuario.", "12:34");
//		panelContacts.addContact(new Contact(1, "Juan", "Descripcion de Juan", new ImageIcon("src/main/java/com/david/chataim/view/images/awp.png").getImage()), "Ultimo mensaje de este usuario.", "12:34");
//		panelContacts.addContact(new Contact(1, "David", "Descripcion", new ImageIcon("src/main/java/com/david/chataim/view/images/da.png").getImage()), "Ultimo mensaje de este usuario.", "12:34");
//		panelContacts.addContact(new Contact(1, "Juan", "Descripcion de Juan", new ImageIcon("src/main/java/com/david/chataim/view/images/awp.png").getImage()), "Ultimo mensaje de este usuario.", "12:34");
//		panelContacts.addContact(new Contact(1, "David", "Descripcion", new ImageIcon("src/main/java/com/david/chataim/view/images/da.png").getImage()), "Ultimo mensaje de este usuario.", "12:34");
//		panelContacts.addContact(new Contact(1, "Juan", "Descripcion de Juan", new ImageIcon("src/main/java/com/david/chataim/view/images/awp.png").getImage()), "Ultimo mensaje de este usuario.", "12:34");
		
		panelChat = new JPanel();
		panelChat.setLocation(334, 52);
		panelChat.setSize(706, 548);
		panelChat.setBackground(Color.white);
		panelChat.setLayout(null);
		contentPane.add(panelChat);
	}//Constructor
}//CLASS