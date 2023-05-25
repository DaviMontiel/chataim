package com.david.chataim.view.mainFrame.components.menus;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.david.chataim.controller.ColorController;
import com.david.chataim.controller.Controller;
import com.david.chataim.controller.Dimens;
import com.david.chataim.controller.ImageController;
import com.david.chataim.controller.LanguageController;
import com.david.chataim.controller.events.OnlyTextTf;
import com.david.chataim.controller.events.menus.CheckFieldsConfiguration;
import com.david.chataim.controller.events.menus.ClosePanel;
import com.david.chataim.controller.events.menus.LblCloseSession;
import com.david.chataim.controller.events.menus.ShowPanel;
import com.david.chataim.view.components.button.Button;
import com.david.chataim.view.components.button.ButtonPanel;
import com.david.chataim.view.components.button.SwitchButton;
import com.david.chataim.view.components.textField.MyTextField;

import lombok.Getter;

public class NewConfigurationPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	@Getter private MyTextField mtfName;
	@Getter private MyTextField mtfDescription;
	@Getter private SwitchButton mbAnonymous;
	@Getter private SwitchButton mbTheme;
	@Getter private Button btnApply;
	
	
	public NewConfigurationPanel(ShowPanel listener) {
		setBackground(ColorController.SECONDARY_COLOR);
		setLayout(null);
		
		// EVET 
		ClosePanel closeListener = new ClosePanel(listener, this);
		
		// GO BACK
		ButtonPanel btnBack = new ButtonPanel(new ImageIcon(this.getClass().getResource(ImageController.BACK)).getImage());
		btnBack.setBounds(10, 10, 48, 48);
		btnBack.addMouseListener(closeListener);
		add(btnBack);
				
		// TITLE
		JLabel lblTitle = new JLabel(LanguageController.getWord(52));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBackground(ColorController.PRIMARY_COLOR);
		lblTitle.setOpaque(true);
		lblTitle.setForeground(Color.white);
		lblTitle.setFont(new Font("SansSerif", 1, 20));
		lblTitle.setBounds(0, 0, Dimens.CONTACTS_PANEL_W, 100);
		add(lblTitle);
		
		
		// USERNAME
		JLabel lblUserName = new JLabel(LanguageController.getWord(53));
		lblUserName.setForeground(Color.white);
		lblUserName.setFont(new Font("SansSerif", 1, 14));
		lblUserName.setBounds(10, 100, 150, 50);
		add(lblUserName);
		
		mtfName = new MyTextField();
		mtfName.setText(Controller.s().getCurrentContact().getOriginalName());
		mtfName.setPrefixIcon(new ImageIcon(getClass().getResource(ImageController.USER_ICON)));
		mtfName.setHint(LanguageController.getWord(54));
		mtfName.setBounds(10, lblUserName.getY()+40, Dimens.CONTACTS_PANEL_W-20, 40);
		mtfName.addKeyListener(new OnlyTextTf(25));
		add(mtfName);
		
		
		// DESCRIPTION
		JLabel lblDescription = new JLabel(LanguageController.getWord(55));
		lblDescription.setForeground(Color.white);
		lblDescription.setFont(new Font("SansSerif", 1, 14));
		lblDescription.setBounds(10, mtfName.getY()+40, 150, 50);
		add(lblDescription);
		
		mtfDescription = new MyTextField();
		mtfDescription.setText(Controller.s().getCurrentContact().getDescription());
		mtfDescription.setPrefixIcon(new ImageIcon(getClass().getResource(ImageController.USER_ICON)));
		mtfDescription.setHint(LanguageController.getWord(56));
		mtfDescription.setBounds(10, lblDescription.getY()+40, Dimens.CONTACTS_PANEL_W-20, 40);
		add(mtfDescription);
		
		
		// ANONYMOUS
		JLabel lblAnonymous = new JLabel(LanguageController.getWord(57));
		lblAnonymous.setForeground(Color.white);
		lblAnonymous.setFont(new Font("SansSerif", 1, 13));
		lblAnonymous.setBounds(10, mtfDescription.getY()+50, 100, 50);
		add(lblAnonymous);
		
		mbAnonymous = new SwitchButton();
		mbAnonymous.setSelected(Controller.s().getCurrentContact().isAnonymous());
		mbAnonymous.setBackground(ColorController.PRIMARY_COLOR);
		mbAnonymous.setBounds(lblAnonymous.getWidth() + 12, lblAnonymous.getY() + 14, 50, 25);
		add(mbAnonymous);
		
		
		// THEME
		JLabel lblDarkMode = new JLabel(LanguageController.getWord(58));
		lblDarkMode.setForeground(Color.white);
		lblDarkMode.setFont(new Font("SansSerif", 1, 13));
		lblDarkMode.setBounds(10, mbAnonymous.getY()+20, 100, 50);
		add(lblDarkMode);
		
		mbTheme = new SwitchButton();
		mbTheme.setSelected(!ColorController.isLightTheme());
		mbTheme.setBackground(ColorController.PRIMARY_COLOR);
		mbTheme.setBounds(lblDarkMode.getWidth() + 12, lblDarkMode.getY() + 14, 50, 25);
		add(mbTheme);
		
		
		// CLOSE SESSION
		JLabel closeSession = new JLabel(LanguageController.getWord(59));
		closeSession.setForeground(Color.lightGray);
		closeSession.setFont(new Font("SansSerif", 1, 13));
		closeSession.setBounds(15, mbTheme.getY()+30, 95, 20);
		closeSession.addMouseListener(new LblCloseSession());
		add(closeSession);
		
		
		// BTN
		btnApply = new Button();
		btnApply.setBackground(ColorController.PRIMARY_COLOR);
		btnApply.setForeground(Color.white);
		btnApply.setText(LanguageController.getWord(60));
		btnApply.setBounds(10, lblDarkMode.getY() + 90, 310, 40);
		btnApply.addActionListener(new CheckFieldsConfiguration(this));
		add(btnApply);
	}//Constructor
}//CLASS