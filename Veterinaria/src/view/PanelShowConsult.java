package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import controller.Commands;
import controller.Controller;

public class PanelShowConsult extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image img;
	private JSpinner ingressId;

	public PanelShowConsult() {
		img = new ImageIcon(getClass().getResource("/img/loginAdmin.png")).getImage();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		initComponents();
	}

	public void initComponents() {
		JPanel panelDeleteConsult = new JPanel();
		Controller.getInstance().setShowConsult(this);;
		panelDeleteConsult.setOpaque(false);
		panelDeleteConsult.setBorder(BorderFactory.createEmptyBorder(55, 30, 5, 30));
		panelDeleteConsult.setLayout(new GridLayout(5,1,20,20));

		JLabel title = new JLabel("Registro de consultas", SwingConstants.CENTER);
		title.setPreferredSize(new Dimension(200, 70));
		title.setFont(new Font("Helvetica", Font.BOLD, 25));
		title.setForeground(Color.decode("#FFFFFF"));
		panelDeleteConsult.add(title);

		JLabel id = new JLabel("    Ingrese el id    ", SwingConstants.LEFT);
		id.setFont(new Font("Helvetica", Font.BOLD, 15));
		id.setForeground(Color.decode("#FFFFFF"));
		ingressId = new JSpinner(new SpinnerNumberModel(1.0, 1.0, 100.0, 1.0));
		panelDeleteConsult.add(id);
		panelDeleteConsult.add(ingressId);

		JSeparator js = new JSeparator(SwingConstants.HORIZONTAL);
		js.setPreferredSize(new Dimension(220, 15));
		js.setBackground(new Color(0, 0, 0, 0));
		js.setForeground(new Color(0, 0, 0, 0));
		panelDeleteConsult.add(js);

		JButton buttonIngress = new RoundedButton(50);
		ButtonPreferences(buttonIngress, "Mostrar consulta", 100, 50, Color.decode("#FFFFFF"), Color.decode("#000000"));
		buttonIngress.addActionListener(Controller.getInstance());
		buttonIngress.setActionCommand(Commands.DELETE.toString());
		buttonIngress.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelDeleteConsult.add(buttonIngress);

		add(panelDeleteConsult);
	}

	public void ButtonPreferences(JButton button, String text, int width, int height, Color background,
			Color foreground) {
		button.setText(text);
		button.setFocusable(false);
		button.setBorderPainted(false);
		button.setPreferredSize(new Dimension(width, height));
		button.setBackground(background);
		button.setForeground(foreground);
		button.setFont(new Font("Helvetica", Font.BOLD, 15));
	}

	public void preferencesTextField(JTextField textField, String text) {
		textField.setText(text);
		textField.setPreferredSize(new Dimension(210, 30));
		textField.setBackground(Color.decode("#000000"));
		textField.setForeground(Color.decode("#595959"));
		textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5),
				BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.decode("#595959")),
						BorderFactory.createEmptyBorder(5, 5, 5, 2))));
		textField.setFont(new Font("Helvetica", Font.PLAIN, 15));
	}

	public void preferencesDateChooser(JDateChooser dateChooser, String text) {
		dateChooser.setPreferredSize(new Dimension(210, 20));
		dateChooser.setBackground(Color.decode("#000000"));
		dateChooser.setForeground(Color.decode("#595959"));
		dateChooser.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 9, 7, 9),
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#595959")), text,
						TitledBorder.LEFT, TitledBorder.TOP, new Font("Helvetica", Font.PLAIN, 15),
						Color.decode("#595959"))));
		dateChooser.setFont(new Font("Microsoft PhagsPa", Font.PLAIN, 15));
		dateChooser.setFont(new Font("Helvetica", Font.PLAIN, 15));
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setMinimumSize(getParent().getSize());
		g.drawImage(img, 0, 0, this);
	}

	public String[] getData() {
		String[] data = new String[1];
		data[0] = (int) Float.parseFloat((ingressId.getValue() + "")) + "";
		return data;
	}
	
}
