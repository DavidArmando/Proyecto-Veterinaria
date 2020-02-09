import java.util.Date;

import model.Pet;
import model.VetDao;

public class TestDao2 {

	public static void main(String[] args) {
		VetDao vd=new VetDao();
		Pet pet=new Pet("Pepe", "Schnauzer", 1, "Muerto", new Date());
		
		if(vd.createPet(pet))
			System.out.println(vd.readPet(1).getState());
		
		if(vd.updatePet(new String[] {1+"","Pepe","Schnauzer","Vivo","20/10/2015"}))
			System.out.println(vd.readPet(1).getState());
		
		if(vd.createConsult(new String[] {"1","0","23/03/2016"}))
			System.out.println(vd.readConsult(1, 0));
		
		if(vd.updateConsult(new String[] {"1","0","20/03/2016","Cuchillo","Se murió","Vacuna"}))
			System.out.println(vd.readConsult(1, 0).getDiagnosis());
		
		if(vd.deleteConsult(1, 0))
			System.out.println(vd.readConsult(1, 0));
	}
}