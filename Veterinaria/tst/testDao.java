import java.util.Date;

import model.Pet;
import model.VetDao;

public class testDao {

	public static void main(String[] args) {
	//	new VetDao().createPet(new Pet("Naruto jose", "Pincher", 1, "Enfermo", new Date()));;
		VetDao vd=new VetDao();
		vd.readFile("./data.xml");
		vd.readConsult(1, 0);
	}
}