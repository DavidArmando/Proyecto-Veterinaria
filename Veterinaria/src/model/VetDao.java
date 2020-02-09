package model;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import general.Utilities;

public class VetDao {

	private File file;
	private BufferedWriter bufferedWriter;
	private Document document;

	public VetDao() {
		file = new File("./data.xml");

		if (!file.exists()) {
			try {
				bufferedWriter = new BufferedWriter(new FileWriter(file));
				bufferedWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
				bufferedWriter.write("<mascotas></mascotas>");
				bufferedWriter.close();
			} catch (IOException e) {
			}
		}

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;

		try {
			db = dbf.newDocumentBuilder();

			try {
				document = db.parse(file);
			} catch (SAXException | IOException e) {
				document = db.newDocument();
			}
		} catch (ParserConfigurationException e1) {
		}
	}

	public Element createPet(Pet pet) {

		try {
			Element pets = (Element) document.getElementsByTagName("mascotas").item(0);
			Element petElement = document.createElement("Mascota");
			Element historial = document.createElement("Historial");
			petElement.appendChild(historial);
			petElement.setAttribute("Nombre", pet.getName());
			petElement.setAttribute("Raza", pet.getRace());
			petElement.setAttribute("Estado", pet.getState());
			petElement.setAttribute("Id", pet.getId() + "");
			petElement.setAttribute("Fecha", Utilities.convertDate(pet.getBirthdate()));
			pets.appendChild(petElement);

			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult result = new StreamResult(file);
			transformer.transform(domSource, result);

			return historial;
		} catch (TransformerException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	public Pet readPet(int id) {
		Pet pet = null;
		NodeList pets = document.getElementsByTagName("Mascota");
		if (pets.getLength() != 0) {
			Element petElement = null;

			for (int i = 0; i < pets.getLength(); i++) {
				int index = Integer.parseInt(pets.item(i).getAttributes().getNamedItem("Id").getNodeValue());
				if (index == id) {
					petElement = (Element) pets.item(i);
					break;
				}
			}

			pet = new Pet(petElement.getAttribute("Nombre"), petElement.getAttribute("Raza"), id,
					petElement.getAttribute("Estado"), Utilities.convertString(petElement.getAttribute("Fecha")));

		}
		return pet;
	}

	public boolean updatePet(String[] data) {
		int id = Integer.parseInt(data[0]);
		Element petElement = null;
		NodeList pets = document.getElementsByTagName("Mascota");
		if (pets.getLength() != 0) {
			for (int i = 0; i < pets.getLength(); i++) {
				int index = Integer.parseInt(pets.item(i).getAttributes().getNamedItem("Id").getNodeValue());
				if (index == id) {
					petElement = ((Element) pets.item(i));

				}
			}
			petElement.setAttribute("Nombre", data[1]);
			petElement.setAttribute("Raza", data[2]);
			petElement.setAttribute("Estado", data[3]);
			petElement.setAttribute("Fecha", data[4]);
			
			return true;
		}
		
		return false;
		
	}

	public boolean deletePet(int id) {

		NodeList pets = document.getElementsByTagName("Mascota");
		if (pets.getLength() != 0) {

			for (int i = 0; i < pets.getLength(); i++) {
				int index = Integer.parseInt(pets.item(i).getAttributes().getNamedItem("Id").getNodeValue());
				if (index == id) {
					document.getElementsByTagName("Mascotas").item(0).removeChild(pets.item(i));

					return true;
				}

			}

		}
		return false;

	}

	public void createConsult(String[] data) {
		System.out.println(data[0]);
		Consult consult = new Consult(Utilities.convertString(data[0]),
				new Pet(data[1], data[2], Integer.parseInt(data[3]), data[5], Utilities.convertString(data[6])), null,
				null, null);

		try {

			Element petHistory = null;
			NodeList pets = document.getElementsByTagName("Mascota");
			if (pets.getLength() != 0) {
				for (int i = 0; i < pets.getLength(); i++) {
					int index = Integer.parseInt(pets.item(i).getAttributes().getNamedItem("Id").getNodeValue());
					if (index == consult.getPet().getId()) {
						petHistory = (Element) ((Element) pets.item(i)).getElementsByTagName("Historial").item(0);
					}
				}
			}
			if (petHistory == null) {
				petHistory = this.createPet(consult.getPet());
			}

			Element consultElement = document.createElement("Consulta");
			consultElement.setAttribute("Fecha", Utilities.convertDate(consult.getDate()));
			consultElement.setAttribute("Administrador", "Lina Angarita H.");

			petHistory.appendChild(consultElement);

			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult result = new StreamResult(file);
			transformer.transform(domSource, result);
		} catch (TransformerException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
