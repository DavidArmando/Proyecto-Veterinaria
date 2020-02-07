package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import controller.Controller;

public class MyFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private DialogLoginAdministrator administrator;
	private PanelMainWindow panelMainWindow;
	private PanelInitialAdmin panelInitialAdmin;
	private JPanel panelActual;
	private MyFrame frame;
	private DialogPet consult;

	public MyFrame() {
		this.administrator = new DialogLoginAdministrator();
		this.consult = new DialogPet();
		Controller.getInstance().setAdministrator(administrator);
		Controller.getInstance().setFrame(frame);
		Controller.getInstance().setConsult(consult);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(1300, 700));
		setResizable(false);
		this.setLocationRelativeTo(null);
		setTitle("MascoCentroAH");
		setIconImage(new ImageIcon(getClass().getResource("/img/IconoMc.png")).getImage());
		this.setLayout(new BorderLayout());
		initComponents();
	}

	public void initComponents() {
		panelMainWindow = new PanelMainWindow(getWidth(), getHeight());
		panelInitialAdmin = new PanelInitialAdmin();
		add(panelMainWindow, BorderLayout.CENTER);
		panelActual = panelMainWindow;
	}

	public void changePanel(JPanel panel) {
		getContentPane().remove(panelActual);
		panelActual = panel;
		add(panelActual, BorderLayout.CENTER);
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	public void changeToInitialPanel() {
		changePanel(panelMainWindow);
	}

	public void changeToPanelInitialAdmin() {
		changePanel(panelInitialAdmin);
	}

	public void changeLanguage() {
		setTitle("");
		panelMainWindow.changeLanguage();
	}

	public void startFrame() {
		this.frame = this;
		Controller.getInstance().setFrame(frame);
		this.frame.setVisible(true);
	}

	public void restartFrame() {
		this.frame = this;
		this.frame.dispose();
		this.frame = new MyFrame();
		Controller.getInstance().setFrame(frame);
		this.frame.setVisible(true);
	}
	
	public void setData(String data) {
		panelInitialAdmin.setData(data); 
	}
}