package studentskasluzba.controller;

import java.util.ArrayList;
import java.util.Vector;

import studentskasluzba.model.BazaPredmeta;
import studentskasluzba.model.Predmet;
import studentskasluzba.model.Profesor;
import studentskasluzba.model.Student;
import studentskasluzba.view.MyDefaultPredmetTable;
import studentskasluzba.view.MyPredmetTable;

public class PredmetiController {

	private static PredmetiController instance = null;

	public static PredmetiController getInstance() {
		if (instance == null)
			instance = new PredmetiController();
		return instance;
	}

	// dodajPredmet(String sifra, String naziv, String semestar, int godIzv, Profesor predProf,ArrayList<Student> studenti)
	public void dodajPredmet(String sifra, String naziv, int semestar, int godIzv,
                             Profesor predProf,ArrayList<Student> studenti, Object[] row, MyDefaultPredmetTable mdpt) {

		boolean postoji = BazaPredmeta.getInstance().dodajPredmet(sifra, naziv, semestar, godIzv, predProf, studenti);
		
		if(!postoji) {
			mdpt.addRow(row);
		}
	}

	public void izbrisiPredmet(String sifra, MyDefaultPredmetTable mdpt, int selected) {
		BazaPredmeta.getInstance().izbrisiPredmet(sifra);
	
		if(selected < 0) {
			
			System.out.println("greska kod brisanja" + " " + this.getClass().getSimpleName());
			
		}
		
		if (selected != -1) {
			
			mdpt.removeRow(selected);
			System.out.println("Uspesno izbrisan predmet" + " " + this.getClass().getSimpleName());
		}
	
	}

	public void izmeniPredmet(String sifra, String naziv, int semestar, int godIzv,
                              Profesor predProf,ArrayList<Student> studenti, Object[] row, MyDefaultPredmetTable mdpt, int selected) {

		BazaPredmeta.getInstance().izmeniPredmet(sifra, naziv, semestar, godIzv, predProf, studenti);
		
		Predmet p = BazaPredmeta.getInstance().getPredmet(sifra);
		
		mdpt.setValueAt(p.getSifra(), selected, 0);
		mdpt.setValueAt(p.getNaziv(), selected, 1);
		

		
		mdpt.setValueAt(Integer.toString(p.getGodIzv()), selected, 2);
		
		
		mdpt.setValueAt(Integer.toString(p.getSemestar()), selected, 3);
		
		if(p.getPredProf() == null) {
			mdpt.setValueAt("null", selected, 4);;
		}
		else {
			mdpt.setValueAt(p.getPredProf().getBrojLK(), selected, 4);
		}
		
		mdpt.setValueAt("STUDENTI-" + p.getSifra(), selected, 5);	
	}

	public Predmet getPredmet(String sifra) {
		return BazaPredmeta.getInstance().getPredmet(sifra);
	}

	public ArrayList<Predmet> getPredmeti() {
		return (ArrayList<Predmet>) BazaPredmeta.getInstance().getPredmeti();
	}

	public void removeProfesorFromPredmet(Profesor prof, Predmet pred) {

		if (pred.getSifra().equals(pred.getSifra())) 
			pred.setPredProf(null);
			
		if (pred.getPredProf() == null)
			System.out.println("USPESNO OBRISAN PROFESOR SA PREDMETA " + pred.getSifra());
		else
			System.out.println("NEUSPESNO BRISANJE ");
			
		
		
		for (Predmet pt : ProfesoriController.getInstance().getProfesor(prof.getBrojLK()).getPredmeti()) {
			
			if (pt.getSifra().equals(pred.getSifra())) {
				ProfesoriController.getInstance().getProfesor(prof.getBrojLK()).getPredmeti().remove(pt);
				System.out.println(ProfesoriController.getInstance().getProfesor(prof.getBrojLK()).getPredmeti().size());	
				break;
			}
		}
		
		snimiPredmete();
		ProfesoriController.getInstance().snimiProfesore();
	}

	public void removeStudentFromPredmet(Student s, Predmet p ) {
        
		for (Student st : p.getStudenti()) {
			if (st.getIndeks().equals(s.getIndeks())) {
				p.getStudenti().remove(st);
				System.out.println(p.getStudenti().size());
				break;
			}
		}
		
		for (Predmet pt : s.getPredmeti()) { 
			
			if (pt.getSifra().equals(p.getSifra())) {
				
				s.getPredmeti().remove(pt);
				System.out.println(s.getPredmeti().size());	
				break;
			}
			
		}	
		
		 StudentiController.getInstance().snimiStudente();
		 snimiPredmete();
		
	}
	
	public void addStudentToPredmet(Student ss, Predmet p) {
		
		ss.getPredmeti().add(p);
		p.getStudenti().add(ss);
		
		StudentiController.getInstance().snimiStudente();
		snimiPredmete();
		
	}
	
	public void addProfesorToPredmet(Profesor prof, Predmet predm) {
		
		 prof.getPredmeti().add(predm);
		 
		 if(predm == null)
			 System.out.println("Predmet je nulll");
		 else
			 predm.setPredProf(prof);
		 	 
		 PredmetiController.getInstance().snimiPredmete();
		 ProfesoriController.getInstance().snimiProfesore();
		
		
	}
	
	public void snimiPredmete() {
		BazaPredmeta.getInstance().snimiPredmete();
		
	}
    
	public Vector<Object> initiateTable(MyDefaultPredmetTable model) {
		
		
		Vector<Object> objects = new Vector<>();
		int i = 0;
		for (Predmet p : BazaPredmeta.getInstance().getPredmeti() ) {
			
			Vector<String> red = new Vector<String>();
			red.add(p.getSifra());
			red.add(p.getNaziv());
			
				
			red.add(Integer.toString(p.getSemestar()));
			red.add(Integer.toString(p.getGodIzv()));
			
			if(p.getPredProf() == null )
				red.add("null");
			else {
				red.add(p.getPredProf().getBrojLK());
				//System.out.println(p.getPredProf().getBrojLK() + " " + p.getPredProf().getPredmeti().size() + " " + p.getPredProf().getPredmeti().get(0).getNaziv());
				
			}	
			
			
			red.add("STUDENTI-" + p.getSifra());
			i++;
			objects.add(red);
			//model.addRow(red);

		}	
			return objects;
	}
	
	public void updateTable(MyPredmetTable mpt, String brojLK) {
		// u slucaju promene licne karte profesora azuriramo tabelu ili ako brisemo profesor
		for (int i = 0; i< mpt.getRowCount(); i++) {
			
			String oldID = (String) mpt.getValueAt(i, 4);
			
			if (oldID.equals(brojLK) == true) {
				mpt.setValueAt("null", i, 4);
				break;
			}		
		}		
	}
	
	
}