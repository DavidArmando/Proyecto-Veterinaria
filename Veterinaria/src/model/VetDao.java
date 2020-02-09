package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
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
	private BufferedWriter bufferedWriter;

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
	}

	/**
	 * Escribe el fichero
	 * @return exito
	 */
	private boolean writeFile() {
		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult result = new StreamResult(file);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(domSource, result);
			return true;

		} catch (TransformerException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Lee un fichero y lo devuelve como document
	 * @param file fichero
	 * @return document
	 */
	public Document readFile(File file) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			return document = dBuilder.parse(file);
		} catch (IOException | ParserConfigurationException | SAXException e) {
		}

		return null;
	}

	/**
	 * Actualiza el fichero
	 */
	private void readFile() {
		this.readFile(this.file);
	}

	/**
	 * Busca un elemento a partir de su identificador
	 * @param id identificador
	 * @param listName lista de elemntos
	 * @return ewlemento
	 */
	private Element searchElement(int id, String listName) {
		NodeList list = document.getElementsByTagName(listName);

		for (int i = 0; i < list.getLength(); i++) {
			int index = Integer.parseInt(list.item(i).getAttributes().getNamedItem("ID").getNodeValue());

			if (index == id) {
				return (Element) list.item(i);
			}
		}

		return null;
	}

	
	/**
	 * Busca un elemento con base en un elemento padre y una etiqueta
	 * @param index posicion
	 * @param element padre
	 * @param tag nombre
	 * @return elemento
	 */
	private Element searchElementByTagNameOf(int index, Element element, String tag) {
		NodeList list = element.getElementsByTagName(tag);
		return (Element) list.item(index);
	}

	/**
	 * Crea una mascota
	 * @param data informacion basica
	 * @return exito
	 */
	public boolean createPet(String[] data) {
		return createPet(
				new Pet(data[1], data[2], Integer.parseInt(data[0]), data[3], Utilities.convertString(data[4])));
	}

	/*
	 * Crea una mascota
	 * 
	 * @param objeto de tipo Pet
	 * 
	 * @return retorna un objeto de tipo Element
	 */
	public boolean createPet(Pet pet) {
		this.readFile();

		if (readPet(pet.getId()) == null) {
			Element pets = (Element) document.getElementsByTagName("mascotas").item(0);
			Element petElement = document.createElement("Mascota");
			Element historial = document.createElement("Historial");
			petElement.appendChild(historial);
			petElement.setAttribute("Nombre", pet.getName());
			petElement.setAttribute("Raza", pet.getRace());
			petElement.setAttribute("Estado", pet.getState());
			petElement.setAttribute("ID", pet.getId() + "");
			petElement.setAttribute("Fecha", Utilities.convertDate(pet.getBirthdate()));
			pets.appendChild(petElement);

			return this.writeFile();
		}

		return false;
	}

	/**
	 * Lee una mascota
	 * @param id identificador
	 * @return mascota
	 */
	public Pet readPet(int id) {
		this.readFile();
		Element petElement = searchElement(id, "Mascota");

		if (petElement != null)
			return new Pet(petElement.getAttribute("Nombre"), petElement.getAttribute("Raza"), id,
					petElement.getAttribute("Estado"), Utilities.convertString(petElement.getAttribute("Fecha")));

		return null;
	}

	/**
	 * actualiza una mascota
	 * @param data informacion
	 * @return exito
	 */
	public boolean updatePet(String[] data) {
		this.readFile();

		int id = Integer.parseInt(data[0]);

		Element petElement = searchElement(id, "Mascota");

		if (petElement != null) {
			petElement.setAttribute("Nombre", data[1]);
			petElement.setAttribute("Raza", data[2]);
			petElement.setAttribute("Estado", data[3]);
			petElement.setAttribute("Fecha", data[4]);
			return this.writeFile();
		}

		return false;
	}

	/**
	 * Borra una mascota
	 * @param id identificador
	 * @return exito
	 */
	public boolean deletePet(int id) {
		this.readFile();
		Element pet = this.searchElement(id, "Mascota");

		if (pet != null)
			document.getElementsByTagName("mascotas").item(0).removeChild(pet);

		return this.writeFile();
	}

	/**
	 * Genera una consulta
	 * @param pos posiciond de la consulta
	 * @param consult objeto consulta
	 * @return exito
	 */
	public boolean createConsult(int pos, Consult consult) {
		this.readFile();

		Element petElement = this.searchElement(consult.getPet().getId(), "Mascota");
		Element consultElement = searchElementByTagNameOf(pos, petElement, "Consulta");

		if (consultElement == null) {
			Element historyElement = (Element) petElement.getElementsByTagName("Historial").item(0);
			consultElement = document.createElement("Consulta");
			consultElement.setAttribute("Fecha", Utilities.convertDate(consult.getDate()));
			historyElement.appendChild(consultElement);
			return this.writeFile();
		}

		return false;
	}

	/**
	 * Genera una consulta
	 * @param data informacion basica
	 * @return exito
	 */
	public boolean createConsult(String[] data) {
		return this.createConsult(Integer.parseInt(data[1]),
				new Consult(Utilities.convertString(data[2]), new Pet(Integer.parseInt(data[0]))));
	}

	/**
	 * lee una consulta
	 * @param id identificador de mascota
	 * @param pos posicion consulta
	 * @return consulta
	 */
	public Consult readConsult(int id, int pos) {
		this.readFile();

		Element petElement = this.searchElement(id, "Mascota");
		Element consultElement = searchElementByTagNameOf(pos, petElement, "Consulta");

		if (consultElement != null)
			return new Consult(Utilities.convertString(consultElement.getAttribute("Fecha")), readPet(id),
					consultElement.getAttribute("Proceso"), consultElement.getAttribute("Diagnostico"),
					consultElement.getAttribute("Prescripcion"));

		return null;
	}

	/**
	 * Actualiza una consulta
	 * @param id identificador de mascota
	 * @param pos posicion de consulta
	 * @param date fecha consulta
	 * @param process proceso consulta
	 * @param diagnosis diagnostico consulta
	 * @param prescription precripcion consulta
	 * @return exito
	 */
	public boolean updateConsult(int id, int pos, String date, String process, String diagnosis, String prescription) {
		this.readFile();

		if (readConsult(id, pos) != null) {
			Element petElement = this.searchElement(id, "Mascota");
			Element consultElement = searchElementByTagNameOf(pos, petElement, "Consulta");
			consultElement.setAttribute("Fecha", date);
			consultElement.setAttribute("Proceso", process);
			consultElement.setAttribute("Diagnostico", diagnosis);
			consultElement.setAttribute("Prescripcion", prescription);

			return this.writeFile();
		}

		return false;
	}
	
	/**
	 * Actualiza una consulta
	 * @param data datos de consulta
	 * @return exito
	 */
	public boolean updateConsult(String[] data) {
		return this.updateConsult(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2], data[3], data[4],
				data[5]);
	}
	
	/**
	 * Borra una consulta de una mascota id
	 * @param id Identificador de la mascota
	 * @param pos Posiciond e la consulta
	 * @return exito
	 */
	public boolean deleteConsult(int id, int pos) {
		this.readFile();

		if (readConsult(id, pos) != null) {
			Element petElement = this.searchElement(id, "Mascota");
			Element consultElement = searchElementByTagNameOf(pos, petElement, "Consulta");
			petElement.getElementsByTagName("Historial").item(0).removeChild(consultElement);

			return this.writeFile();
		}

		return false;
	}
}