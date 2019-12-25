package studentskasluzba.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

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
	public void dodajStudenta(String ime, String prezime, DateFormat datRodj, String adresaStanovanja, int kontaktTel,
			String email, String indeks, DateFormat datumUpisa, int godStud, Status status, double prosek //,
			/* ArrayList<Predmet> predmeti  */ ) {
		// TODO: CHECK
		boolean postoji = false;
		
		for (Student s : studenti) {
			if (studenti.isEmpty() == false ) {
				if (s.getIndeks().contentEquals(indeks))
					postoji = true;	
			}
		}
		if (postoji == false) { 
			this.studenti.add(new Student(ime, prezime, datRodj, adresaStanovanja, kontaktTel, email, indeks, datumUpisa, godStud, status, prosek /*,predmeti */));
			snimiStudente();
			
		}
	}
	
	public void izbrisiStudenta(String indeks) {
		for (Student i : studenti) {
			if (i.getIndeks().contentEquals(indeks)) {
				studenti.remove(i);	
				snimiStudente();
				break;
			}
		}
	}
	
	public void izmeniStudenta(String ime, String prezime, DateFormat datRodj, String adresaStanovanja, int kontaktTel,
			String email, String indeks, DateFormat datumUpisa, int godStud, Status status, double prosek //,
			/*ArrayList<Predmet>  predmeti*/) {
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
	
	
	
	public ArrayList<Student> findStudent(String ime, String prezime, String index) {
		
		ArrayList<Student> found = new ArrayList<>();
		
		ime = ime.toLowerCase();
		prezime = prezime.toLowerCase();
		index = index.toLowerCase();
		
		
		for (Student i : studenti) {
			
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
