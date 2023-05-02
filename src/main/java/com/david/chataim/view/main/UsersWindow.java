package com.david.chataim.view.main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

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
import com.david.chataim.view.main.components.HeaderPanel;
import com.david.chataim.view.main.components.ProfilePanel;
import com.david.chataim.view.main.components.SearchPanel;

import lombok.Getter;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.BorderLayout;

public class UsersWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	@Getter private HeaderPanel panelHeader;
	
	private JLabel lblExit;
	private ProfilePanel panelProfile;
	private JPanel panelChat;
	private TextField tfSearch;
	private JPanel panelSearch;
	private JButton btnFilterContacts;
	@Getter private ContactsPanel panelContacts;
	private ScrollPaneWin11 scrollPaneContacts;


	public UsersWindow(Contact contact) {
		//BORRAR
		contact = new Contact("david", "Descripcion de la cuenta", ImageController.getDefaultImageUser(), true);
		
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(Dimens.WINDOW_W, Dimens.WINDOW_H));
		setBounds(0, 0, Dimens.WINDOW_W, Dimens.WINDOW_H);
		setLocationRelativeTo(null);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelHeader = new HeaderPanel();
		panelHeader.setBackground(ColorController.PRIMARY_COLOR);
		panelHeader.setPreferredSize(new Dimension(Dimens.WINDOW_W, Dimens.WINDOW_HEADER_H));
		
		// Move window with the mouse
		MoveWindowListener moveWindowListener = new MoveWindowListener(this);
		panelHeader.setLayout(new BorderLayout(0, 0));
		panelHeader.addMouseMotionListener(moveWindowListener.new MouseDragged());
		panelHeader.addMouseListener(moveWindowListener.new MouseAdapter());
		contentPane.add(panelHeader, BorderLayout.NORTH);
		
		// PROFILE IMAGE
		panelProfile = new ProfilePanel(contact);
		panelProfile.setPreferredSize(new Dimension(55, 0));
		panelHeader.add(panelProfile, BorderLayout.WEST);
		
		// EXIT LBL
		lblExit = new JLabel();
		lblExit.addMouseListener(new ExitMouseListener());
		lblExit.setIcon(new ImageIcon(UsersWindow.class.getResource(ImageController.EXIT_WHITE)));
		lblExit.setBorder(new EmptyBorder(10, 0, 10, 10));
		panelHeader.add(lblExit, BorderLayout.EAST);
		
		// SEARCH PANEL
		panelSearch = new SearchPanel();
		panelSearch.setBackground(ColorController.PRIMARY_COLOR);
		panelSearch.setOpaque(false);
		panelSearch.setPreferredSize(new Dimension(334, 548));
		panelSearch.setLayout(null);
		contentPane.add(panelSearch, BorderLayout.WEST);
		
		// FILTER USERs
		btnFilterContacts = new JButton();
		btnFilterContacts.setContentAreaFilled(false);
		btnFilterContacts.setOpaque(false);
		btnFilterContacts.setBorderPainted(false);
		btnFilterContacts.setIcon(new ImageIcon(UsersWindow.class.getResource(ImageController.FILTER)));
		btnFilterContacts.setBounds(297, 16, 20, 20);
		panelSearch.add(btnFilterContacts);
		
		// SEARCH CONTACT
		tfSearch = new TextField();
		tfSearch.setMargin(0, 30, 0, 0);
		tfSearch.setPlaceHolder(LanguageController.getWord(1));
		tfSearch.setBounds(10, 11, 274, 39);
		panelSearch.add(tfSearch);
		
		// SCROLL
		scrollPaneContacts = new ScrollPaneWin11();
		scrollPaneContacts.setOpaque(false);
		scrollPaneContacts.getViewport().setOpaque(false);
		scrollPaneContacts.setBorder(null);
		scrollPaneContacts.setBounds(0, 46, 334, 502);
		panelSearch.add(scrollPaneContacts);
		
		// CONTACTs PANEL
		panelContacts = new ContactsPanel();
		scrollPaneContacts.setViewportView(panelContacts);
		panelContacts.setLayout(new BoxLayout(panelContacts, BoxLayout.Y_AXIS));
		panelContacts.setOpaque(false);
		
		// ADD CONTACTs
		panelContacts.addContact(contact, null,null);
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
//		panelContacts.addContact(new Contact(1, "David", "Descripcion", new ImageIcon("src/main/java/com/david/chataim/view/images/da.png").getImage()), "Ultimo mensaje de este usuario.", "12:34");
//		panelContacts.addContact(new Contact(1, "Juan", "Descripcion de Juan", new ImageIcon("src/main/java/com/david/chataim/view/images/awp.png").getImage()), "Ultimo mensaje de este usuario.", "12:34");
		
		panelChat = new JPanel();
		panelChat.setPreferredSize(new Dimension(706, 0));
		panelChat.setBackground(Color.white);
		panelChat.setLayout(new BorderLayout());
		contentPane.add(panelChat, BorderLayout.EAST);
		
		panelChat.add(new JLabel("Texto xD"), BorderLayout.WEST);
		
		addComponentListener(new ComponentAdapter() {
	        public void componentResized(ComponentEvent e) {
	            // Obtener el nuevo ancho de la ventana
	            int newWidth = getWidth();

	            // Establecer el nuevo ancho del panel rosa
	            panelChat.setPreferredSize(new Dimension(newWidth - panelSearch.getWidth(), 0));
	            contentPane.revalidate();
	        }
	    });
	}//Constructor
}//CLASS