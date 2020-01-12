package studentskasluzba.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import studentskasluzba.controller.ProfesoriController;
import studentskasluzba.controller.StudentiController;

public class BazaPredmeta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5001161040265455442L;

	private static BazaPredmeta instance = null;

	public static BazaPredmeta getInstance() {
		if (instance == null)
			instance = new BazaPredmeta();
		return instance;
	}

	private List<Predmet> predmeti = new ArrayList<Predmet>();

	private BazaPredmeta() {
		initPredmete();
	}

	public List<Predmet> getPredmeti() {
		return predmeti;
	}

	public Predmet getPredmet(String sifra) {

		for (Predmet p : predmeti) {
			if (p.getSifra().equals(sifra))
				return p;
		}
		// test
		System.out.println("Ne postoji predmet sa tom sifrom" + " " + this.getClass().getSimpleName());
		return null;
	}

	public boolean dodajPredmet(String sifra, String naziv, int semestar, int godIzv, Profesor predProf,ArrayList<Student> studenti) {

		boolean flag = false; // flag da znamo da li smo uspesno dodali ili ne

		for (Predmet p : predmeti) {
			if (p.getSifra().equals(sifra)) {
				// test
				System.out.println("Predmet vec postoji u bazi" + " " + this.getClass().getSimpleName());
				flag = true;
				return flag;
			}
		}

		predmeti.add(new Predmet(sifra,naziv,semestar,godIzv,predProf,studenti));
		
		// ako dodajemo novi predmet sa predmetnim profesorom, i taj profesor vec postoji - dodaj ovaj predmet na njegove predmete
		if (predProf != null && ProfesoriController.getInstance().getProfesor(predProf.getBrojLK()).getBrojLK().equals(predProf.getBrojLK())) {
			 
			String brojLK = predProf.getBrojLK();
			Profesor prof = BazaProfesora.getInstance().getProfesor(brojLK);
			Predmet predm = BazaPredmeta.getInstance().getPredmet(sifra);
			
			prof.getPredmeti().add(predm);
			predm.setPredProf(prof);
			// test 
			System.out.println("---------------------------------------");
			System.out.println(predm.getNaziv() + " predaje " + predProf.getEmail());
			System.out.println(predProf.getEmail() + " predaje " + predProf.getPredmeti().size() + " predmeta");
			System.out.println("---------------------------------------");
			ProfesoriController.getInstance().snimiProfesore();	
		}
		
		snimiPredmete();
		return flag;
	}

	public void izbrisiPredmet(String sifra) {
		
		// ako izbrisemo predmet, izbrisemo ga i sa liste predmeta predmetnog profesora
		for (Profesor prof : ProfesoriController.getInstance().getProfesori()) {
			for (Predmet pt : prof.getPredmeti()) {
				if (pt.getSifra().equals(sifra)) {
					prof.getPredmeti().remove(pt);
					break;
				}
			}
		}
		
		// izbrisanjem predmeta, izbrisemo taj predmet i sa liste studenata koji ga slusaju
		for (Student s : StudentiController.getInstance().getStudenti()) {
			for (Predmet pred : s.getPredmeti()) {
				if (pred.getSifra().equals(sifra)) {
					s.getPredmeti().remove(pred);
					break;
				}
			}
		}
		
		// brisanje predmeta
		for (Predmet p : predmeti) {
			if(p.getSifra().equals(sifra)) {

				predmeti.remove(p);
				break;
			}
		}
		
		snimiPredmete();
		ProfesoriController.getInstance().snimiProfesore();
		StudentiController.getInstance().snimiStudente();
			
	}
	
	public void izmeniPredmet(String sifra, String naziv, int semestar, int godIzv, Profesor predProf,ArrayList<Student> studenti) {
		
		boolean changed_professor = false;
		boolean year_changed = false;
		
		// ako promenimo godinu predavanja na predmetu onda cemo da brisemo taj predmet sa liste svih studenata koji su ga slusali
		if (godIzv != getPredmet(sifra).getGodIzv())
			year_changed = true;
		
		if (year_changed == true) {
			
			for (Student s : StudentiController.getInstance().getStudenti()) {
				
				if (s.getGodStud() != getPredmet(sifra).getGodIzv())
					continue;
				
				for (Predmet p : s.getPredmeti()) {
					
					if (p.getSifra().equals(sifra)) {
						s.getPredmeti().remove(p);	
						break;
					}
				}			
			}	
			// resetujemo listu studenata kod bas tog predmeta
			getPredmet(sifra).getStudenti().clear();
			StudentiController.getInstance().snimiStudente();
			
		}
		
		String newProf = null;
		// ako profesor nije zamenjen na drugi postojeci profesor, vec je samo izbrisan
		if (predProf == null)
			newProf = "null";
		else
			newProf = predProf.getBrojLK();
		
		String oldProf = null;
		
		if (getPredmet(sifra).getPredProf() == null)
			oldProf = "null";
		else
			oldProf= getPredmet(sifra).getPredProf().getBrojLK();	//predProf.getBrojLK()
		
		// ako smo izmenili predmetnog profesora na drugi postojeci profesor 
		if (!oldProf.equals(newProf))
			changed_professor = true; 
		
		if (changed_professor == true && getPredmet(sifra).getPredProf() != null) { // obrisi iz liste predmeta profesora samo ako profesor postoji
			
			for (Predmet pt : ProfesoriController.getInstance().
							  getProfesor(oldProf).getPredmeti()) {
				
				if (pt.getSifra().equals(sifra)) {
					ProfesoriController.getInstance().getProfesor(oldProf).getPredmeti().remove(pt);
					break;
				}
			}
			
		}
		
		// preuzimanje novih podataka kod izmene
		for (Predmet p : predmeti) {
			if (p.getSifra().equals(sifra)) {
				p.setSifra(sifra);
				p.setNaziv(naziv);
				p.setSemestar(semestar);
				p.setGodIzv(godIzv);
				p.setPredProf(predProf);
				p.setStudenti(studenti);
				snimiPredmete();
				break;
			}
		}
	}


	public void snimiPredmete() {

		try{
			  // Creating the object
			  // Creating stream and writing the object
			  FileOutputStream fout=new FileOutputStream("predmeti.txt");
			  ObjectOutputStream out=new ObjectOutputStream(fout);
			  out.writeObject(this.predmeti);
			  out.flush();
			  // closing the stream
			  out.close();
			  System.out.println("success");

			  } catch(Exception e){
				  System.out.println(e);
			  	}

	}

	@SuppressWarnings("unchecked")
	private void initPredmete() {
        // ako je prazan fajl zovemo return da izbegnemo EOF Exception
		File fajl = new File("predmeti.txt");
        if (fajl.length() == 0)
            return;

		try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("predmeti.txt");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            predmeti = (ArrayList<Predmet>)in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");

        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
            ex.printStackTrace();
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }

	}
	
	public ArrayList<Object> findPredmet(String sifra, String naziv) {
		
		ArrayList<Object> found = new ArrayList<>();
		
		sifra = sifra.toLowerCase();
		naziv = naziv.toLowerCase();
		
		
		for (Predmet i: predmeti) {
			
			if (i.getSifra().toLowerCase().equals(sifra)) {
				found.add(i);
				return found;
			} 
			if (i.getNaziv().toLowerCase().startsWith(naziv)  && i.getSifra().toLowerCase().startsWith(sifra)) 
				found.add(i);	
		}	
		return found;	
	}

}
