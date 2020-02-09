package view;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class DialogPet extends JDialog{

	private static final long serialVersionUID = 1L;
	private JPanel panelActual;
	
	public DialogPet() {
		setModal(true);
		setResizable(false);
		setSize(400, 480);
		setLocationRelativeTo(null);
		setTitle("Masco Centro AH.");
		
		PanelCreateConsult panelCreateEvent = new PanelCreateConsult();
		PanelDeletePet panelDeleteConsult = new PanelDeletePet();
		PanelShowConsult panelShowConsult = new PanelShowConsult();
		add(panelCreateEvent);
		panelActual = panelCreateEvent;
	}
	
	public void changePanel(JPanel panel) {
		getContentPane().remove(panelActual);
		panelActual = panel;
		add(panelActual, BorderLayout.CENTER);
		getContentPane().revalidate();
		getContentPane().repaint();
	}
}
