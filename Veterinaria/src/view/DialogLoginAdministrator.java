package view;

import javax.swing.JDialog;


public class DialogLoginAdministrator extends JDialog{
	private static final long serialVersionUID = 1L;
	private PanelLoginAdministrator panelLoginAdministrator;

	public DialogLoginAdministrator() {
		setModal(true);
		setResizable(false);
		setSize(300, 320);
		setLocationRelativeTo(null);
		setTitle("");
		
		panelLoginAdministrator = new PanelLoginAdministrator();
		add(panelLoginAdministrator);	
	}
	
	public String getEmail() {
		return panelLoginAdministrator.getEmail();
	}
	
	public String getPassword() {
		return panelLoginAdministrator.getPassword();
	}
	
	public void changeLanguage() {
		setTitle("Titulo");
		panelLoginAdministrator.changeLanguage();
	}
}