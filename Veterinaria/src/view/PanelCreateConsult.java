package view;

import java.awt.Color;

import java.awt.Component;
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
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import controller.Commands;
import controller.Controller;
import general.Utilities;

public class PanelCreateConsult extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image img;
	private JDateChooser ingresDate;
	private JTextField ingressName;
	private JTextField ingressRace;
	private JSpinner ingressId;
	private JLabel veterinarian;
	private JTextField state;
	private JDateChooser ingressBirthdate;

	public PanelCreateConsult() {
		img = new ImageIcon(getClass().getResource("/img/loginAdmin.png")).getImage();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		initComponents();
	}

	public void initComponents() {
		JPanel panelCreateConsult = new JPanel();
		Controller.getInstance().setCreateConsult(this);
		panelCreateConsult.setOpaque(false);
		panelCreateConsult.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		panelCreateConsult.setLayout(new BoxLayout(panelCreateConsult, BoxLayout.Y_AXIS));

		JLabel title = new JLabel("Crear consulta", SwingConstants.CENTER);
		title.setPreferredSize(new Dimension(200, 70));
		title.setFont(new Font("Helvetica", Font.BOLD, 25));
		title.setForeground(Color.decode("#FFFFFF"));
		panelCreateConsult.add(title);

		ingresDate = new JDateChooser();
		preferencesDateChooser(ingresDate, "Fecha de la Consulta");
		panelCreateConsult.add(ingresDate);

		ingressName = new JTextField();
		preferencesTextField(ingressName, "Nombre");
		panelCreateConsult.add(ingressName);
		
		ingressRace = new JTextField();
		preferencesTextField(ingressRace, "Raza");
		panelCreateConsult.add(ingressRace);

		JLabel id = new JLabel("    Ingrese el id    ",SwingConstants.LEFT);
		id.setFont(new Font("Helvetica", Font.BOLD, 15));
		id.setForeground(Color.decode("#FFFFFF"));
		ingressId = new JSpinner(new SpinnerNumberModel(1.0, 1.0, 100.0, 1.0));
		panelCreateConsult.add(id);
		panelCreateConsult.add(ingressId);

		veterinarian = new JLabel("Veterinario: Lina Angarita Herrera");
		veterinarian.setFont(new Font("Helvetica", Font.BOLD, 15));
		veterinarian.setForeground(Color.decode("#FFFFFF"));
		panelCreateConsult.add(veterinarian);

		state = new JTextField();
		preferencesTextField(state, "Estado");
		panelCreateConsult.add(state);

		ingressBirthdate = new JDateChooser();
		preferencesDateChooser(ingressBirthdate, "Fecha de Cumpleaños");
		panelCreateConsult.add(ingressBirthdate);

		JSeparator js = new JSeparator(SwingConstants.HORIZONTAL);
		js.setPreferredSize(new Dimension(220, 15));
		js.setBackground(new Color(0, 0, 0, 0));
		js.setForeground(new Color(0, 0, 0, 0));
		panelCreateConsult.add(js);

		JButton buttonIngress = new RoundedButton(50);
		ButtonPreferences(buttonIngress, "Crear", 100, 50, Color.decode("#FFFFFF"), Color.decode("#000000"));
		buttonIngress.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonIngress.addActionListener(Controller.getInstance());
		buttonIngress.setActionCommand(Commands.CONSULT_CREATE.toString());
		panelCreateConsult.add(buttonIngress);

		add(panelCreateConsult);
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
		dateChooser.setPreferredSize(new Dimension(210, 50));
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
		String[] data = new String[7];
		data[0] = Utilities.convertDate(ingresDate.getDate());
		data[1] = ingressName.getText();
		data[2] = ingressRace.getText();
		data[3] = (int)Float.parseFloat((ingressId.getValue()+""))+"";
		data[4] = veterinarian.getText();
		data[5] = state.getText();
		data[6] = Utilities.convertDate(ingressBirthdate.getDate());
		return data;
	}
}