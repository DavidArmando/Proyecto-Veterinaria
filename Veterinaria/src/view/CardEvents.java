package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardEvents extends JPanel{
	private static final long serialVersionUID = 1L;
	private Image img;

	public CardEvents(String image, String event, String consult, String address, 
			String genreEvent, String initialDateEvent, String finalDateEvent) {
		img = new ImageIcon(getClass().getResource(image)).getImage();
		this.setPreferredSize(new Dimension(200, 150));
		this.setOpaque(false);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(30, 3, 0, 0));
		
		JLabel nameEvent = new JLabel(event);
		nameEvent.setForeground(Color.decode("#000000"));
		nameEvent.setFont(new Font("Helvetiva", Font.BOLD, 15));
		add(nameEvent);
		
		JLabel exhibithionPlace = new JLabel("Consulta:  "+consult);
		labelPreferences(exhibithionPlace);
		add(exhibithionPlace);
		
		JLabel addressPlace = new JLabel("Datos:  "+address);
		labelPreferences(addressPlace);
		add(addressPlace);
		
		JLabel genre = new JLabel("Raza:  "+genreEvent);
		labelPreferences(genre);
		add(genre);
		
		JLabel initialDate = new JLabel("Fecha de la cita:  "+initialDateEvent);
		labelPreferences(initialDate);
		add(initialDate);
		
		JLabel finalDate = new JLabel("Edad:  "+finalDateEvent);
		labelPreferences(finalDate);
		add(finalDate);
	}
	
	public void labelPreferences(JLabel label) {
		label.setForeground(Color.decode("#000000"));
		label.setFont(new Font("Helvetica", Font.PLAIN, 13));
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setMinimumSize(getParent().getSize());
		g.drawImage(img, 0, 0, this);
	}
}
