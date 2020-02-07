package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import controller.Commands;
import controller.Controller;
import general.HandlerLanguage;

public class PanelMainWindow extends JPanel{
	private static final long serialVersionUID = 1L;
	private Image img;
	private int WIDTH, HEIGHT;
	private JPanel panelDown;
	private JPanel panelUp;
	private RoundedButton buttonAdministrator;
	private JButton buttonInfo;
	

	public PanelMainWindow(int width, int height) {
		this.WIDTH = width;
		this.HEIGHT = height;
		img = new ImageIcon(getClass().getResource("/img/animales.png")).getImage();
		setLayout(new BorderLayout());
		
		InitComponents();
	}
	
	public void InitComponents() {
		panelUp = new JPanel();
		panelUp.setOpaque(false);
		panelUp.setPreferredSize(new Dimension(0, (int)(HEIGHT*20/100)));
		panelUp.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		add(panelUp, BorderLayout.PAGE_START);
		
		panelLanguages();
		
		JPanel panelLeft = new JPanel();
		panelLeft.setOpaque(false);
		panelLeft.setPreferredSize(new Dimension((int)(WIDTH*20/100), 0));
		add(panelLeft, BorderLayout.LINE_START);
		
		panelPlatforms();
		
		JPanel panelRight = new JPanel();
		panelRight.setOpaque(false);
		panelRight.setPreferredSize(new Dimension((int)(WIDTH*20/100), 0));
		add(panelRight, BorderLayout.LINE_END);
		
		panelDown = new JPanel();
		panelDown.setOpaque(false);
		panelDown.setPreferredSize(new Dimension(0, (int)(HEIGHT*30/100)));
		panelDown.setLayout(new BoxLayout(panelDown, BoxLayout.Y_AXIS));
		
		JPanel panelSpace = new JPanel();
		panelSpace.setOpaque(false);
		panelSpace.setPreferredSize(new Dimension(0, (int)(HEIGHT*20/100)));
		panelDown.add(panelSpace);
		
		footerPanel();
		
		add(panelDown, BorderLayout.PAGE_END);
	}
	
	public void panelLanguages() {
		JButton spanishButton = new JButton(new ImageIcon(getClass().getResource("/img/spanish.png")));
		preferencesLanguageButtons(spanishButton);
		panelUp.add(spanishButton);
		spanishButton.addActionListener(Controller.getInstance());
		spanishButton.setActionCommand(Commands.LANG_ES.toString());
		
		JButton englishButton = new JButton(new ImageIcon(getClass().getResource("/img/english.png")));
		preferencesLanguageButtons(englishButton);
		panelUp.add(englishButton);
		englishButton.addActionListener(Controller.getInstance());
		englishButton.setActionCommand(Commands.LANG_US.toString());
	}
	
	public void panelPlatforms() {
		JPanel panelPlatforms = new RoundedPanel(70, new Color(0, 0, 0, 0));
		panelPlatforms.setBackground(VetColors.PANEL_PLATAFORMS_COLOR);
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
		layout.setHgap((int)(WIDTH*5/100));
		layout.setVgap((int)(HEIGHT*10/100));
		panelPlatforms.setLayout(layout);
			
		buttonAdministrator = new RoundedButton(150);
		buttonPreferences(buttonAdministrator,HandlerLanguage.getInstance().getValue("Admin") ,"/img/veterinario.png");
		panelPlatforms.add(buttonAdministrator);
		
		
		add(panelPlatforms, BorderLayout.CENTER);
	}
	
	public void footerPanel() {
		UIManager.put("ToolTip.background", Color.decode("#595959"));
		UIManager.put("ToolTip.foreground", Color.decode("#FFFFFF"));
		UIManager.put("ToolTip.border", BorderFactory.createLineBorder(Color.decode("#FFFFFF")));
		
		JPanel panelInfo = new JPanel();
		panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.X_AXIS));
		panelInfo.setBackground(VetColors.FOOTER_COLOR);
		panelInfo.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		
		JPanel panelCopyRight = new JPanel();
		panelCopyRight.setOpaque(false);

		JLabel copyRightText = new JLabel("Angarita Herrera 2019");
		copyRightText.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		copyRightText.setFont(new Font("Helvetica", Font.PLAIN, 15));
		panelCopyRight.add(copyRightText);

		panelInfo.add(panelCopyRight);

		JPanel panelContacts = new JPanel();
		panelContacts.setOpaque(false);
		FlowLayout layout = new FlowLayout();
		layout.setHgap(10);
		panelContacts.setLayout(layout);

		buttonInfo = new JButton();
		buttonInfo.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		preferencesFooterButtons(buttonInfo, "<html>Want to know more<br></br>about our products and<br></br>supportofferings or just<br></br>have a question?</html>");
		buttonInfo.setFont(VetFonts.FOOTER_BUTTONS_FONT);
		panelContacts.add(buttonInfo);
		
		JButton buttonFacebook = new JButton(new ImageIcon(getClass().getResource("/img/facebook.png")));
		preferencesFooterButtons(buttonFacebook, "https://www.facebook.com/MascoCentroA.H/");
		panelContacts.add(buttonFacebook);
		buttonFacebook.addActionListener(Controller.getInstance());
		buttonFacebook.setActionCommand(Commands.FB.toString());

		JButton buttonTwitter = new JButton(new ImageIcon(getClass().getResource("/img/twitter.png")));
		preferencesFooterButtons(buttonTwitter, "https://twitter.com");
		panelContacts.add(buttonTwitter);
		buttonTwitter.addActionListener(Controller.getInstance());
		buttonTwitter.setActionCommand(Commands.TW.toString());
		
		JButton buttonInstagram = new JButton(new ImageIcon(getClass().getResource("/img/instagram.png")));
		preferencesFooterButtons(buttonInstagram, "@MusestackX");
		panelContacts.add(buttonInstagram);
		buttonInstagram.addActionListener(Controller.getInstance());
		buttonInstagram.setActionCommand(Commands.INSTA.toString());

		JButton buttonWhatsapp = new JButton(new ImageIcon(getClass().getResource("/img/whatsapp.png")));
		preferencesFooterButtons(buttonWhatsapp, "3228823147");
		panelContacts.add(buttonWhatsapp);
		buttonWhatsapp.addActionListener(Controller.getInstance());
		buttonWhatsapp.setActionCommand(Commands.WHTS.toString());

		JButton buttonMail = new JButton(new ImageIcon(getClass().getResource("/img/mail.png")));
		preferencesFooterButtons(buttonMail, "musestack@mymail.com");
		panelContacts.add(buttonMail);
		buttonMail.addActionListener(Controller.getInstance());
		buttonMail.setActionCommand(Commands.GM.toString());

		panelInfo.add(panelContacts);


		panelDown.add(panelInfo, BorderLayout.PAGE_END);
	}
	
	public void buttonPreferences(JButton button, String text, String icon) {
		button.setText(text);
		button.setIcon(new ImageIcon(getClass().getResource(icon)));
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		button.setBackground(VetColors.BUTTON_PLATAFORMS_COLOR);
		button.setFont(VetFonts.FOOTER_BUTTONS_FONT);
		button.setForeground(VetColors.MAIN_WINDOW_BUTTONS_FOREGROUND_COLOR);
		button.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setVerticalTextPosition(SwingConstants.BOTTOM);
		button.addActionListener(Controller.getInstance());
		button.setActionCommand(Commands.START.toString());
	}
	
	public void preferencesLanguageButtons(JButton button) {
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		button.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	}
	
	public void preferencesFooterButtons(JButton button, String text) {
		button.setFocusPainted(false);
		button.setBackground(VetColors.FOOTER_COLOR);
		button.setToolTipText(text);		
		button.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
	}
	
	public void changeLanguage() {
		
		buttonInfo.setText(HandlerLanguage.languageProperties.getProperty(""));
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setMinimumSize(getParent().getSize());
		g.drawImage(img, 0, 0, this);
		repaint();
	}
}