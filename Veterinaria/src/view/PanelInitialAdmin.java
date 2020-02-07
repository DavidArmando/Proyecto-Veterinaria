package view;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.Commands;
import controller.Controller;

public class PanelInitialAdmin extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image img;
	private JPanel panelInitial;
	private JTextArea pgd;

	public PanelInitialAdmin() {
		img = new ImageIcon(getClass().getResource("/img/lucky.png")).getImage();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		initComponents();
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 0));
		add(panel);
	}

	public void initComponents() {
		panelInitial = new JPanel();
		panelInitial.setOpaque(false);
		panelInitial.setLayout(new BorderLayout());

		panelInfo();

		panelHistory();
		panelReports();

		JScrollPane jspHistory = new JScrollPane();
		jspHistory.setBackground(new Color(0, 0, 0, 0));
		jspHistory.getViewport().setOpaque(false);
		jspHistory.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jspHistory.setViewportView(panelInitial);
		add(jspHistory);
	}

	public void panelInfo() {
		JPanel panelInfoC = new JPanel();
		panelInfoC.setOpaque(false);
		panelInfoC.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		panelInfoC.setLayout(new BoxLayout(panelInfoC, BoxLayout.Y_AXIS));

		CardProfile cardProfile = new CardProfile("woman6.png", "Dra. Lina Angarita", Color.decode("#FFF34C"),
				Color.decode("#FFFFFF"));
		cardProfile.setOpaque(false);
		panelInfoC.add(cardProfile);

		JPanel panelButtonCreate = new JPanel();
		panelButtonCreate.setOpaque(false);
		panelButtonCreate.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 5));

		JButton buttonCreate = new RoundedButton(50);
		buttonCreate.setText("Crear Consulta");
		buttonCreate.setFocusable(false);
		buttonCreate.setBorderPainted(false);
		buttonCreate.setPreferredSize(new Dimension(150, 50));
		buttonCreate.setBackground(new Color(0, 0, 0, 230));
		buttonCreate.setForeground(Color.decode("#FFFFFF"));
		buttonCreate.setFont(new Font("Helvetica", Font.PLAIN, 15));
		buttonCreate.addActionListener(Controller.getInstance());
		buttonCreate.setActionCommand(Commands.CREATE.toString());
		panelButtonCreate.add(buttonCreate);

		panelInfoC.add(panelButtonCreate);

		JPanel panelButtonDelete = new JPanel();
		panelButtonDelete.setOpaque(false);
		panelButtonDelete.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 5));

		JButton buttonDelete = new RoundedButton(50);
		buttonDelete.setText("Eliminar mascota");
		buttonDelete.setFocusable(false);
		buttonDelete.setBorderPainted(false);
		buttonDelete.setPreferredSize(new Dimension(150, 50));
		buttonDelete.setBackground(new Color(0, 0, 0, 230));
		buttonDelete.setForeground(Color.decode("#FFFFFF"));
		buttonDelete.setFont(new Font("Helvetica", Font.PLAIN, 15));
		buttonDelete.addActionListener(Controller.getInstance());
		buttonDelete.setActionCommand(Commands.OPEN.toString());
		panelButtonDelete.add(buttonDelete);

		panelInfoC.add(panelButtonDelete);

		JPanel panelEvents = new JPanel();
		panelEvents.setOpaque(false);
		panelEvents.setLayout(new BorderLayout());

		JPanel panelLabel = new JPanel();
		panelLabel.setBackground(new Color(0, 0, 0, 230));
		panelLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		JLabel title = new JLabel("Historia Veterinaria");
		title.setForeground(Color.decode("#FFFFFF"));
		title.setFont(new Font("Helvetica", Font.BOLD, 17));
		panelLabel.add(title);

		panelEvents.add(panelLabel, BorderLayout.PAGE_START);

		JPanel panelContainPictures = new JPanel();
		panelContainPictures.setBackground(new Color(0, 0, 0, 200));
		int maxPictures = 3;
		if (maxPictures > 0) {
			panelContainPictures.setLayout(new GridLayout(maxPictures, 1, 0, 30));
			panelContainPictures.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

			for (int i = 0; i < maxPictures; i++) {
				JButton buttonConsult = new JButton();
				buttonConsult.setFocusable(false);
				buttonConsult.setLayout(new BorderLayout());
				buttonConsult.setBackground(new Color(0, 0, 0, 0));
				buttonConsult.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
				CardEvents cardEvent = new CardEvents("/img/fondoEvento.png", "	Consulta", "xxxxxxxx", "Calle X #123",
						"xxxxx", "05/12/2017", "07/12/2017");
				buttonConsult.addActionListener(Controller.getInstance());
				buttonConsult.setActionCommand(Commands.CONSULT.toString());
				buttonConsult.add(cardEvent, BorderLayout.CENTER);
				panelContainPictures.add(buttonConsult);
			}
		} else {
			panelContainPictures.setLayout(new GridLayout(3, 1, 30, 30));
			panelContainPictures.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

			JLabel message = new JLabel("No tienes consultas", SwingConstants.CENTER);
			message.setFont(new Font("Helvetica", Font.BOLD, 17));
			message.setForeground(Color.decode("#000000"));
			panelContainPictures.add(message);
		}

		JScrollPane jspPictures = new JScrollPane();
		jspPictures.getViewport().setOpaque(false);
		jspPictures.setBackground(new Color(0, 0, 0, 0));
		jspPictures.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 200)));
		jspPictures.setPreferredSize(new Dimension(0, 700));
		jspPictures.setViewportView(panelContainPictures);

		panelEvents.add(jspPictures, BorderLayout.CENTER);

		panelInfoC.add(panelEvents);

		panelInitial.add(panelInfoC, BorderLayout.LINE_START);
	}

	public void panelHistory() {
		JPanel panelEvents = new JPanel();
		panelEvents.setOpaque(false);
		panelEvents.setBorder(BorderFactory.createEmptyBorder(1, 10, 0, 10));

		JPanel events = new JPanel();
		events.setBackground(new Color(0, 0, 0, 200));
		events.setLayout(new BorderLayout(0, 10));
		events.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

		JPanel panelLabelEventsTitle = new JPanel();
		panelLabelEventsTitle.setOpaque(false);
		panelLabelEventsTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		JPanel panelButton = new JPanel();
		panelButton.setOpaque(false);

		events.add(panelButton, BorderLayout.PAGE_END);

		panelEvents.add(events);

		panelInitial.add(panelEvents, BorderLayout.LINE_END);
	}

	public void panelReports() {
		JPanel panelHisotry = new JPanel();
		panelHisotry.setOpaque(false);
		panelHisotry.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
		panelHisotry.setLayout(new BorderLayout(0, 10));

		JPanel menuReports = new JPanel();
		menuReports.setOpaque(false);
		menuReports.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
		menuReports.setLayout(new BorderLayout(0, 10));

		JTextField pgb = new JTextField("Historial");
		JMenuBar bar = new JMenuBar();
		JMenu fileMenu = new JMenu("Archivo");
		JMenuItem open = new JMenuItem("Abrir");
		open.addActionListener(Controller.getInstance());
		open.setActionCommand(Commands.OPEN_FILE.toString());
		fileMenu.add(open);
		bar.add(fileMenu);

		menuReports.add(pgb, BorderLayout.PAGE_START);
		menuReports.add(bar, BorderLayout.PAGE_END);

		pgd = new JTextArea();
		pgd.setFont(new Font("Arial", Font.ITALIC, 12));

		panelHisotry.add(menuReports, BorderLayout.PAGE_START);
		panelHisotry.add(pgd, BorderLayout.CENTER);

		panelInitial.add(panelHisotry, BorderLayout.CENTER);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setMinimumSize(getParent().getSize());
		g.drawImage(img, 0, 0, this);
		revalidate();
		repaint();
	}

	public void setData(String data) {
		pgd.setText(data);
	}
}