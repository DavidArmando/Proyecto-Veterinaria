package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
	private Document document;
	private Management management;
	private BufferedWriter bufferedWriter;

	public VetDao() {
		management = new Management();
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

			petsFile();
		} catch (IOException | ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Crea una mascota
	 * 
	 * @param objeto de tipo Pet
	 * 
	 * @return retorna un objeto de tipo Element
	 */
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

	private void petsFile() {
		management.clearList();
		NodeList pets = document.getElementsByTagName("Mascota");
		if (pets.getLength() != 0) {
			Element petElement = null;
			for (int i = 0; i < pets.getLength(); i++) {
				int index = Integer.parseInt(pets.item(i).getAttributes().getNamedItem("Id").getNodeValue());
				petElement = (Element) pets.item(i);
				Pet pet = new Pet(petElement.getAttribute("Nombre"), petElement.getAttribute("Raza"), index,
						petElement.getAttribute("Estado"), Utilities.convertString(petElement.getAttribute("Fecha")));
				History history = new History(pet);

				NodeList historyList = petElement.getElementsByTagName("Consulta");
				Element historyElement = null;
				for (int j = 0; j < historyList.getLength(); j++) {
					historyElement = (Element) historyList.item(j);
					history.addConsult(new Consult(Utilities.convertString(historyElement.getAttribute("Fecha")), pet,
							"", "", ""));

				}
				management.addHistory(history);
			}
		}
	}

	public String getHistoryData() {
		String data = "";
		ArrayList<History> list = management.getHistories();
		for (int i = 0; i < list.size(); i++) {
			data += (list.get(i));
		}
		return data;
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

	public void deletePet(String[] id) {
		int index = Integer.parseInt(id[0]);
		ArrayList<History> list = management.getHistories();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPet().getId() == index) {
				list.remove(i);
			}
		}

		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Element root = doc.createElement("Mascota");
			doc.appendChild(root);

			for (int i = 0; i < list.size(); i++) {

				Element race = doc.createElement("Raza");
				race.appendChild(doc.createTextNode(list.get(i).getPet().getRace()));
				root.appendChild(race);

				Element name = doc.createElement("Nombre");
				name.appendChild(doc.createTextNode(list.get(i).getPet().getName()));
				root.appendChild(name);

				Element idElement = doc.createElement("Id");
				idElement.appendChild(doc.createTextNode(String.valueOf(list.get(i).getPet().getId())));
				root.appendChild(idElement);

				Element state = doc.createElement("Estado");
				state.appendChild(doc.createTextNode(list.get(i).getPet().getState()));
				root.appendChild(state);

				Element date = doc.createElement("Fecha");
				date.appendChild(doc.createTextNode(Utilities.convertDate(list.get(i).getPet().getBirthdate())));
				root.appendChild(date);

				for (int j = 0; j < list.get(i).getConsults().size(); j++) {
					Element history = doc.createElement("Historial");

					Element consult = doc.createElement("Consulta");

					Element consultDate = doc.createElement("Fecha");
					consultDate.appendChild(
							doc.createTextNode(Utilities.convertDate(list.get(i).getConsults().get(j).getDate())));
					consult.appendChild(consultDate);

					Element admin = doc.createElement("Administrador");
					admin.appendChild(
							doc.createTextNode(list.get(i).getConsults().get(j).getAdministrator().getName()));
					consult.appendChild(admin);

					history.appendChild(consult);
					root.appendChild(history);
				}
			}

			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(new DOMSource(doc), new StreamResult(new File("./data2.xml")));

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	public void createConsult(String[] data) {
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

	public Consult readConsult(int id, int index) {

		Element pet = document.getElementById(id + "");
		NodeList consults = pet.getElementsByTagName("Consulta");
		Element consultElement = (Element) consults.item(index);

		Consult consult = new Consult(Utilities.convertString(consultElement.getAttribute("Fecha")), readPet(id), null,
				null, null);

		return consult;

	}
}