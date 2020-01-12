package studentskasluzba.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import studentskasluzba.controller.PredmetiController;
import studentskasluzba.model.Student.Status;

public class BazaStudenata implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2639913166179638640L;
	private static BazaStudenata instance = null;
	
	
	public static BazaStudenata getInstance() {
		
		if (instance == null) 
			instance = new BazaStudenata();
		return instance;
	}
	
	private List<Student> studenti = new ArrayList<Student>();
	
	private BazaStudenata() {
		 initStudente();
	}
	
	public List<Student> getStudenti() {
		return studenti;	
	}
	
	public Student getStudent(String indeks) {
		
		for (Student i : studenti) {
			if (i.getIndeks().equals(indeks))
				return i;	
		}
		System.out.println("Ne postoji student u bazi sa tim indeksom");
		return null;
	}
	public boolean dodajStudenta(String ime, String prezime, Date datRodj, String adresaStanovanja, String kontaktTel,
			String email, String indeks, Date datumUpisa, int godStud, Status status, double prosek ,
			ArrayList<Predmet> predmeti  ) {
		
		boolean postoji = false;
		
		for (Student s : studenti) {
			if (studenti.isEmpty() == false ) {
				if (s.getIndeks().contentEquals(indeks))
					postoji = true;	
			}
		}
		// samo ako vec ne postoji student u bazi ga dodaj u bazu
		if (postoji == false) { 
			this.studenti.add(new Student(ime, prezime, datRodj, adresaStanovanja, kontaktTel, email, indeks, datumUpisa, godStud, status, prosek ,predmeti ));
			snimiStudente();
			
		}
		return postoji;
		
	}
	
	public void izbrisiStudenta(String indeks) {
			
		// iteriramo preko svakog predmeta studenta kojeg brisemo i sa liste svakog tog predmeta brisemo trenutnog studenta
		for (Predmet p : getStudent(indeks).getPredmeti()) {
			boolean predmetChanged = false;
			for (Student s : p.getStudenti()) {
				
				if (s.getIndeks().equals(indeks) == true)  {
					p.getStudenti().remove(s);	
					predmetChanged = true;
					break;
				}
			}
			
			if (predmetChanged == true)
				PredmetiController.getInstance().snimiPredmete();
			
		}
		
		for (Student i : studenti) {
			if (i.getIndeks().contentEquals(indeks)) {
				studenti.remove(i);	
				snimiStudente();
				break;
			}
		}
	}
	
	public void izmeniStudenta(String ime, String prezime, Date datRodj, String adresaStanovanja, String kontaktTel,
			String email, String indeks, Date datumUpisa, int godStud, Status status, double prosek,
			ArrayList<Predmet>  predmeti) {
		
		boolean year_changed = false;
		
		if (godStud != getStudent(indeks).getGodStud())
			year_changed = true;
		// ako je promenjena godina studenta skini ga sa liste svakog predmeta
		if (year_changed == true) {
			
			for (Predmet p : PredmetiController.getInstance().getPredmeti()) {
				
				if (p.getGodIzv() != getStudent(indeks).getGodStud())
					continue;
				
				for (Student s : p.getStudenti()) {
					
					if (s.getIndeks().equals(indeks)) {
						
						p.getStudenti().remove(s);
						break;					
					}					
				}			
			}
			// ocisti listu predmeta bas tog studenta zbog promene godine
			getStudent(indeks).getPredmeti().clear(); 
			PredmetiController.getInstance().snimiPredmete();
		}
		
		for (Student i : studenti) {
			if (i.getIndeks().equals(indeks)) {
				i.setIme(ime);
				i.setPrezime(prezime);
				i.setDatRodj(datRodj);
				i.setAdresaStanovanja(adresaStanovanja);
				i.setKontaktTel(kontaktTel);
				i.setEmail(email);
				i.setIndeks(indeks);
				i.setDatumUpisa(datumUpisa);
				i.setGodStud(godStud);
				i.setStatus(status);
				i.setProsek(prosek);
				snimiStudente();
				break;
			}
		}
	}
	
	public void snimiStudente() { 
		
		try{  
			  //Creating the object  
			  ;  
			  //Creating stream and writing the object  
			  FileOutputStream fout=new FileOutputStream("studenti.txt");  
			  ObjectOutputStream out=new ObjectOutputStream(fout);  
			  out.writeObject(this.studenti);  
			  out.flush();  
			  //closing the stream  
			  out.close();  
			  System.out.println("success"); 
			  
			  } catch(Exception e){
				  System.out.println(e);
			  	}  
	}   
		
	
	
	private void initStudente() {

		
		// check if empty 
			File fajl = new File("studenti.txt");
			if (fajl.length() == 0)
				return;
		
		try
        {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream("studenti.txt"); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            studenti = (ArrayList<Student>)in.readObject(); 
              
            in.close(); 
            file.close(); 
              
            System.out.println("Object has been deserialized "); 
             
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
          
        catch(ClassNotFoundException ex) 
        { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
  
    } 
	
	
	
	public ArrayList<Object> findStudent(String ime, String prezime, String index) {
		
		ArrayList<Object> found = new ArrayList<>(); 
		
		ime = ime.toLowerCase();
		prezime = prezime.toLowerCase();
		index = index.toLowerCase();
		
		
		for (Student i : studenti) {
			// ako nadjemo bas tog jednog po indeksu onda smo zavrsili pretragu
			if (i.getIndeks().toLowerCase().equals(index)) {
				found.add(i);
				return found;
			} 
			if (i.getIme().toLowerCase().startsWith(ime) && i.getPrezime().toLowerCase().startsWith(prezime) && i.getIndeks().toLowerCase().startsWith(index)) 
				found.add(i);	
		}	
		return found;	
	}
	
		
	
	
}