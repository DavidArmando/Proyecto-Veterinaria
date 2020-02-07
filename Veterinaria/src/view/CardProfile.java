package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.Commands;
import controller.Controller;


public class CardProfile extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public CardProfile(String imgProfile, String name, Color colorPhoto, Color colorButton) {
		JPanel panelProfile = new JPanel();
		panelProfile.setBackground(Color.decode("#000000"));
		panelProfile.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
		panelProfile.setLayout(new BorderLayout(0, 10));
		
		JPanel panelUser = new JPanel();
		panelUser.setOpaque(false);
		panelUser.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		
		JButton userButton = new JButton(new ImageIcon(getClass().getResource("/img/"+imgProfile)));
		userButton.setFocusable(false);
		userButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
		panelUser.add(userButton);
		
		JLabel nameUser = new JLabel(name);
		nameUser.setForeground(Color.decode("#FFFFFF"));
		nameUser.setFont(new Font("Helvetica", Font.BOLD, 15));
		panelUser.add(nameUser);
		
		panelProfile.add(panelUser, BorderLayout.CENTER);
		
		JPanel panelButton = new JPanel();
		panelButton.setOpaque(false);
		
		JButton logoutButton = new RoundedButton(40);
		logoutButton.setText("Salir");
		logoutButton.setFocusable(false);
		logoutButton.setBorderPainted(false);
		logoutButton.setPreferredSize(new Dimension(100, 40));
		logoutButton.setBackground(colorButton);
		logoutButton.setForeground(Color.decode("#000000"));
		logoutButton.setFont(new Font("Helvetica", Font.BOLD, 15));
		logoutButton.addActionListener(Controller.getInstance());
		logoutButton.setActionCommand(Commands.LOGOUT.toString());
		panelButton.add(logoutButton);
		
		panelProfile.add(panelButton, BorderLayout.PAGE_END);
		
		add(panelProfile);
	}
}