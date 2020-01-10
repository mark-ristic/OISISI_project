package studentskasluzba.controller;

import java.util.ArrayList;

import studentskasluzba.model.BazaPredmeta;
import studentskasluzba.model.Predmet;
import studentskasluzba.model.Profesor;
import studentskasluzba.model.Student;

public class PredmetiController {

	private static PredmetiController instance = null;

	public static PredmetiController getInstance() {
		if (instance == null)
			instance = new PredmetiController();
		return instance;
	}

	// dodajPredmet(String sifra, String naziv, String semestar, int godIzv, Profesor predProf,ArrayList<Student> studenti)
	public void dodajPredmet(String sifra, String naziv, String semestar, int godIzv,
                             Profesor predProf,ArrayList<Student> studenti) {

		boolean postoji = BazaPredmeta.getInstance().dodajPredmet(sifra, naziv, semestar, godIzv, predProf, studenti);
	}

	public void izbrisiPredmet(String sifra) {
		BazaPredmeta.getInstance().izbrisiPredmet(sifra);
	}

	public void izmeniPredmet(String sifra, String naziv, String semestar, int godIzv,
                              Profesor predProf,ArrayList<Student> studenti) {

		BazaPredmeta.getInstance().izmeniPredmet(sifra, naziv, semestar, godIzv, predProf, studenti);
	}

	public Predmet getPredmet(String sifra) {
		return BazaPredmeta.getInstance().getPredmet(sifra);
	}

	public ArrayList<Predmet> getPredmeti() {
		return (ArrayList<Predmet>) BazaPredmeta.getInstance().getPredmeti();
	}

	public void removeProfesorFromPredmet(Profesor prof, Predmet pred) {
        // TODO: finish this
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
	
	private void snimiPredmete() {
		BazaPredmeta.getInstance().snimiPredmete();
		
	}
    
	
	
}