package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import general.HandlerLanguage;
import model.AdminDao;
import model.Administrator;
import model.VetDao;
import view.DialogPet;
import view.DialogLoginAdministrator;
import view.FileChooser;
import view.MyFrame;
import view.PanelCreateConsult;
import view.PanelDeletePet;

public class Controller implements ActionListener {

	private static Controller controller = null;
	private DialogLoginAdministrator administrator;
	private DialogPet dialogPet;
	private PanelCreateConsult createConsult;
	private PanelDeletePet deleteConsult;
	private MyFrame frame;
	private VetDao dao;
	private AdminDao adminDao;
	private String xmlData;

	public Controller() {
		adminDao = new AdminDao();
		dao = new VetDao();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Commands.valueOf(e.getActionCommand())) {
		case START:
			this.administrator.setVisible(true);
			break;
		case LOGIN:
			if (this.administrator.getEmail().equals(adminDao.getAdmin().getEmail())
					&& this.administrator.getPassword().equals(adminDao.getAdmin().getPassword())) {
				this.frame.changeToPanelInitialAdmin();
				this.administrator.dispose();
			} else {
				this.administrator.dispose();
			}
			break;
		case LANG_US:
			this.changeLanguage("US");

			break;
		case LANG_ES:
			this.changeLanguage("ES");
			break;

		case CREATE:
			this.dialogPet.setTitle("Crear Consulta");
			this.dialogPet.changePanel(createConsult);
			this.dialogPet.setVisible(true);
			
			break;

		case LOGOUT:
			this.frame.changeToInitialPanel();
			break;

		case CONSULT_CREATE:
			System.out.println("Boton crear");
			//dao.createConsult(createConsult.getData());
			//dao.readFile(xmlData);
			//this.frame.setData(dao.getHistoryData());
			break;
		case FB:
			System.out.println("https://www.facebook.com/MascoCentroA.H/");
			break;
		case TW:
			System.out.println("Twitter");
			break;
		case WHTS:
			System.out.println("3115020656");
			break;
		case INSTA:
			System.out.println("insta");
			break;
		case GM:
			System.out.println("@gmail");
			break;
		case CONSULT:
			System.out.println("boton para abrir la consulta");
			break;
		case CONFIRM_OPEN:
			// int id = elemento.getIs();
			// Pet pet =dao.readPet(id);
			// initialAdmin.setPet(pet);
		case OPEN_FILE:
			FileChooser chooser = new FileChooser();
			//xmlData = chooser.getPath();
		//	dao.readFile(xmlData);
		//	this.frame.setData(dao.getHistoryData());
			break;
		case DELETE:
			//dao.deletePet(this.deleteConsult.getData());
			//this.frame.setData(dao.getHistoryData());
			break;
		case OPEN:
			this.dialogPet.setTitle("Borrar Mascota");
			this.dialogPet.changePanel(deleteConsult);
			this.dialogPet.setVisible(true);
			break;
		default:
			break;

		}
	}

	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	public void setFrame(MyFrame frame) {
		this.frame = frame;
	}

	public void setAdministrator(DialogLoginAdministrator administrator) {
		this.administrator = administrator;
	}

	private void changeLanguage(String key) {
		try {
			HandlerLanguage.getInstance().loadLanguage(key);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		this.frame.restartFrame();
	}

	public void setConsult(DialogPet consult) {
		this.dialogPet = consult;
	}

	public void setCreateConsult(PanelCreateConsult createConsult) {
		this.createConsult = createConsult;
	}

	public void setDeleteConsult(PanelDeletePet deleteConsult) {
		this.deleteConsult = deleteConsult;
	}
}