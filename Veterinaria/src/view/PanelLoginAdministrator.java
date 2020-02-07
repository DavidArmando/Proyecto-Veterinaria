package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import controller.Commands;
import controller.Controller;
import general.HandlerLanguage;

public class PanelLoginAdministrator extends JPanel{
	private static final long serialVersionUID = 1L;
	private Image img;
	private JTextField ingressMail;
	private JPasswordField ingressPassword;
	private RoundedButton buttonIngress;
	private JLabel title;

	public PanelLoginAdministrator() {
		img = new ImageIcon(getClass().getResource("/img/loginAdmin.png")).getImage();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		initComponents();
	}

	public void initComponents() {
		JPanel panelLogin = new JPanel();
		panelLogin.setOpaque(false);
		panelLogin.setPreferredSize(new Dimension(0, 180));

		title = new JLabel(("Hola"), SwingConstants.CENTER);
		title.setPreferredSize(new Dimension(200, 70));
		title.setFont(VetFonts.USER_WINDOW_TITLE_LABELS_FONT);
		title.setForeground(Color.decode("#FFFFFF"));
		panelLogin.add(title);

		ingressMail = new JTextField();
		ingressMail.setText("ABCDEFGHIJK");
		ingressMail.setPreferredSize(new Dimension(210, 55));
		ingressMail.setBackground(Color.decode("#000000"));
		ingressMail.setForeground(Color.decode("#FFFFFF"));
		ingressMail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 9, 7, 9),
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#595959")),(""),
						TitledBorder.LEFT, TitledBorder.TOP, new Font("Helvetica", Font.PLAIN, 15), VetColors.BORDER_TITLED_FOREGROUND_COLOR)));
		panelLogin.add(ingressMail);

		ingressPassword = new JPasswordField("*********");
		ingressPassword.setPreferredSize(new Dimension(210, 55));
		ingressPassword.setBackground(Color.decode("#000000"));
		ingressPassword.setForeground(Color.decode("#595959"));
		ingressPassword.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 9, 7, 9),
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#595959")),
						(""),
						TitledBorder.LEFT, TitledBorder.TOP, new Font("Helvetica", Font.PLAIN, 15), VetColors.BORDER_TITLED_FOREGROUND_COLOR)));
		panelLogin.add(ingressPassword);
		
		JSeparator js = new JSeparator(SwingConstants.HORIZONTAL);
		js.setPreferredSize(new Dimension(220, 15));
		js.setBackground(new Color(0, 0, 0, 0));
		js.setForeground(new Color(0, 0, 0, 0));
		panelLogin.add(js);

		buttonIngress = new RoundedButton(50);
		ButtonPreferences(buttonIngress, ("Ingresar"),
				100, 50, Color.decode("#FFFFFF"), Color.decode("#000000"));
		panelLogin.add(buttonIngress);

		add(panelLogin);
	}
	
	public void ButtonPreferences(JButton button, String text, int width, int height, Color background, Color foreground) {
		button.setText(text);
		button.setFocusable(false);
		button.setBorderPainted(false);
		button.setPreferredSize(new Dimension(width, height));
		button.setBackground(background);
		button.setForeground(foreground);
		button.setFont(new Font("Helvetica", Font.BOLD, 15));
		button.addActionListener(Controller.getInstance());
		button.setActionCommand(Commands.LOGIN.toString());
	}
	
	public void changeLanguage() {
		title.setText(HandlerLanguage.languageProperties.getProperty(""));
		ingressMail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 9, 7, 9),
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#595959")),
						HandlerLanguage.languageProperties.getProperty(""),
						TitledBorder.LEFT, TitledBorder.TOP, new Font("Helvetica", Font.PLAIN, 15), VetColors.BORDER_TITLED_FOREGROUND_COLOR)));
		ingressPassword.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 9, 7, 9),
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#595959")),
						HandlerLanguage.languageProperties.getProperty(""),
						TitledBorder.LEFT, TitledBorder.TOP, new Font("Helvetica", Font.PLAIN, 15), VetColors.BORDER_TITLED_FOREGROUND_COLOR)));
		buttonIngress.setText(HandlerLanguage.languageProperties.getProperty(""));
	}
	 
	public String getEmail() {
		return ingressMail.getText();
	}
	
	public String getPassword() {
		return new String(ingressPassword.getPassword());
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setMinimumSize(getParent().getSize());
		g.drawImage(img, 0, 0, this);
	}
	
}