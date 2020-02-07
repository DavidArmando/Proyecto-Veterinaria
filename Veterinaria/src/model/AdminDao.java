package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AdminDao {

	private File file;
	private Document document;
	private Administrator admin;
	private BufferedWriter writer;

	public AdminDao() {
		admin = new Administrator("Lina Angarita H.", "lina@mail.com", "1234");
		file = new File("./user.xml");

		if (!file.exists()) {
			try {
				writer = new BufferedWriter(new FileWriter(file));
				writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
				writer.write("<usuario></usuario>");
				writer.close();
			} catch (IOException e) {
			}
		}
	}

	/*
	 * Lee el archivo
	 * 
	 * @param ruta del archivo
	 */
	public void readFile(String fileName) {
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			document = dBuilder.parse(new File(fileName));

			createAdmin();
		} catch (IOException | ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
	}

	private void createAdmin() {
		NodeList admin = document.getElementsByTagName("Usuario");
		if (admin.getLength() != 0) {
			Element adminElement = null;
			for (int i = 0; i < admin.getLength(); i++) {
				System.out.println(admin.item(i));
				adminElement = (Element) admin.item(i);
				this.admin = new Administrator(adminElement.getAttribute("nombre"), adminElement.getAttribute("email"),
						adminElement.getAttribute("password"));
			}
		}
	}
	
	public Administrator getAdmin() {
		return admin;
	}
}