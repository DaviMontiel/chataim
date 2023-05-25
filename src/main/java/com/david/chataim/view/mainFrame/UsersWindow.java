package com.david.chataim.view.mainFrame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.david.chataim.controller.ColorController;
import com.david.chataim.controller.Dimens;
import com.david.chataim.controller.ImageController;
import com.david.chataim.controller.events.ExitMouseListener;
import com.david.chataim.controller.events.menus.ShowPanel;
import com.david.chataim.model.Contact;
import com.david.chataim.view.components.button.ButtonPanel;
import com.david.chataim.view.components.scrollpane.ScrollPaneWin11;
import com.david.chataim.view.mainFrame.components.HeaderPanel;
import com.david.chataim.view.mainFrame.components.ListContactsPanel;
import com.david.chataim.view.mainFrame.components.ProfilePanel;
import com.david.chataim.view.mainFrame.components.SearchPanel;
import com.david.chataim.view.mainFrame.components.chat.ChatPanel;

import lombok.Getter;
import lombok.Setter;

import javax.swing.JLabel;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.BorderLayout;

public class UsersWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	@Getter private JPanel panelBorder;
	@Getter private JPanel contentPane;
	@Getter private HeaderPanel panelHeader;
//	private ButtonPanel btnGroup;
	private ButtonPanel btnContact;
	private ButtonPanel btnMenu;
	private JLabel lblExit;
	
	private ProfilePanel panelProfile;
	@Getter private SearchPanel panelSearch;
	@Getter private ListContactsPanel panelListContacts;
	@Getter private ScrollPaneWin11 scrollPaneContacts;
	@Setter @Getter private ChatPanel panelChat;


	public UsersWindow(Contact contact) {
		initComponents(contact);
		initEvents();
	}//Constructor
	
	private void initComponents(Contact contact) {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, Dimens.WINDOW_W_PRE, Dimens.WINDOW_H_PRE);
		setIconImage(new ImageIcon(getClass().getResource(ImageController.LOGO)).getImage());
		setLocationRelativeTo(null);
		
		// BORDER PANEL
		panelBorder = new JPanel();
		panelBorder.setLayout(null);
		setContentPane(panelBorder);
		
		// CONTENT PANEL
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBounds(1, 1, Dimens.WINDOW_W_PRE-2, Dimens.WINDOW_H_PRE-2);
		panelBorder.add(contentPane);
		
		// HEADER
		panelHeader = new HeaderPanel(this);
		contentPane.add(panelHeader, BorderLayout.NORTH);
		
		// TOOLS/MENUs PANEL
		JPanel panelMenu = new JPanel();
		panelMenu.setLayout(null);
		panelMenu.setOpaque(false);
		panelMenu.setPreferredSize(new Dimension(334, panelHeader.getPreferredSize().height));
		panelHeader.add(panelMenu, BorderLayout.WEST);
		
		// PROFILE IMAGE
		final byte panelProfileSize = 45; 
		panelProfile = new ProfilePanel(contact.getImage());
		panelProfile.setBounds(25 - panelProfileSize/2, panelMenu.getPreferredSize().height/2 - panelProfileSize/2, panelProfileSize, panelProfileSize);
		panelMenu.add(panelProfile);
		
		// GROUP BTN
//		btnGroup = new ButtonPanel(new ImageIcon(getClass().getResource(ImageController.GROUP)).getImage());
//		btnGroup.setBounds(175, 15, 30, 30);
//		panelMenu.add(btnGroup);
		
		// NEW CONTACT BTN
		btnContact = new ButtonPanel(new ImageIcon(getClass().getResource(ImageController.CHAT)).getImage());
		btnContact.setBounds(235, 18, 25, 25);
		panelMenu.add(btnContact);

		// VERTICAL MENU BTN
		btnMenu = new ButtonPanel(new ImageIcon(getClass().getResource(ImageController.VERTICAL_MENU)).getImage());
		btnMenu.setBounds(285, 18, 25, 25);
		panelMenu.add(btnMenu);
		
		// EXIT LBL
		lblExit = new JLabel();
		lblExit.addMouseListener(new ExitMouseListener());
		lblExit.setIcon(new ImageIcon(getClass().getResource(ImageController.EXIT_WHITE)));
		lblExit.setBorder(new EmptyBorder(10, 0, 10, 10));
		panelHeader.add(lblExit, BorderLayout.EAST);
		
		
		// CONSTACTs PANEL
		panelSearch = new SearchPanel();
		panelSearch.setBackground(ColorController.PRIMARY_COLOR);
		panelSearch.setOpaque(false);
		panelSearch.setPreferredSize(new Dimension(Dimens.CONTACTS_PANEL_W, Dimens.CONTACTS_PANEL_H));
		contentPane.add(panelSearch, BorderLayout.WEST);
		
		// SCROLL
		scrollPaneContacts = new ScrollPaneWin11();
		scrollPaneContacts.getVerticalScrollBar().setUnitIncrement(10);
		scrollPaneContacts.setOpaque(false);
		scrollPaneContacts.getViewport().setOpaque(false);
		scrollPaneContacts.setBorder(null);
		scrollPaneContacts.setBounds(0, 46, 334, 502);
		panelSearch.add(scrollPaneContacts);
		
		// LIST CONTACTs PANEL
		panelListContacts = new ListContactsPanel();
		scrollPaneContacts.setViewportView(panelListContacts);
		panelListContacts.setLayout(new BoxLayout(panelListContacts, BoxLayout.Y_AXIS));
		panelListContacts.setOpaque(false);
		
//		panelChat = new JPanel();
//		panelChat.setPreferredSize(new Dimension(706, 0));
//		panelChat.setBackground(Color.white);
//		panelChat.setLayout(new BorderLayout());
//		contentPane.add(panelChat, BorderLayout.EAST);
//		
//		panelChat.add(new JLabel("Texto xD"), BorderLayout.WEST);
	}//FUN
	
	private void initEvents() {
		// SHOW PANEL FOR ADD NEW USER
//		btnGroup.addMouseListener(new ShowPanel(ShowPanel.NEW_GROUP));
		btnContact.addMouseListener(new ShowPanel(ShowPanel.NEW_CONTACT));
		btnMenu.addMouseListener(new ShowPanel(ShowPanel.CONFIGURATION_MENU));
		
		// RESIZE CHAT PANEL
		addComponentListener(new ComponentAdapter() {
	        public void componentResized(ComponentEvent e) {
	            if (panelChat != null) {
	            	resizeChat();
	            }//IF
	            
	            // KEEP PROPORTIONS OF THE CONTENTPANEL
	            contentPane.setBounds(1, 1, panelBorder.getWidth()-2, panelBorder.getHeight()-2);
	        }//EVENT
	    });
	}//FUN
	
	public void resizeChat() {
		// GET FRAME WIDTH
        int newWidth = getWidth();
        
    	// STABLISHED NEW DIMENS OF CHAT PANEL
    	panelChat.setPreferredSize(new Dimension(newWidth - panelSearch.getWidth(), 0));
        contentPane.revalidate();
	}//FUN
	
	public void disposeChat() {
		if (panelChat != null) {
			remove(panelChat);
			this.panelChat = null;	
		}//IF
	}//FUN
}//CLASS