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
import studentskasluzba.model.Profesor.ProfesorType;
import studentskasluzba.view.PredmetTab;

public class BazaProfesora implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5970207892013571389L;

	private static BazaProfesora instance = null;
	
	
	public static BazaProfesora getInstance() {
		
		if (instance == null) 
			instance = new BazaProfesora();
		return instance;
	}
	
	private List<Profesor> Profesori = new ArrayList<Profesor>();
	
	private BazaProfesora() {
		 initProfesore();
	}
	
	public List<Profesor> getProfesori() {
		return Profesori;	
	}
	
	public Profesor getProfesor(String brojLK) {
		
		for (Profesor i : Profesori) {
			if (i.getBrojLK().equals(brojLK))
				return i;	
		}
		System.out.println("Ne postoji Profesor u bazi sa tim brojLKom");
		return null;
	}
	public boolean dodajProfesora(String ime, String prezime, Date datRodj, String adresaStanovanja, String kontaktTel,
			String email, String adresaKanc, String brojLK, String titula, ProfesorType zvanje, ArrayList<Predmet> predmeti) {
		// TODO: CHECK
		boolean postoji = false;
		for (Profesor p : Profesori) {
			if (p.getBrojLK() == brojLK)
				postoji = true;	 
		}
		
		//ProfesorType profesorType, String ime, String prezime, DateFormat datRodj, String adresaStanovanja, int kontaktTel,
		//String email, String adresaKanc, int brojLK, String titula, String zvanje, List<Predmet> predmeti
		
		
		if (postoji == false) { 
			this.Profesori.add(new Profesor(ime, prezime, datRodj, adresaStanovanja, kontaktTel, email, adresaKanc, brojLK, titula, zvanje,predmeti));
			snimiProfesore();
			
		}
		return postoji; 
		
	}
	
	public void izbrisiProfesora(String brojLK) {
		
		// nulliranje svih predProf atributa predmeta koje profesor kog brisemo predaje 
		
		for (Predmet p : getProfesor(brojLK).getPredmeti()) {
			
			PredmetiController.getInstance().getPredmet(p.getSifra()).setPredProf(null);
			
			System.out.println(PredmetiController.getInstance().getPredmet(p.getSifra()).getPredProf());
	
			PredmetiController.getInstance().snimiPredmete();
			
			// kad izbrisemo profesora, moramo azurirati i tabelu predmeta
			PredmetiController.getInstance().updateTable(PredmetTab.myPredmetTable, brojLK);
		}
		
		for (Profesor i : Profesori) {
			if (i.getBrojLK().equals(brojLK)) {
				Profesori.remove(i);	
				snimiProfesore();
				break;
			}
		}
	}
	
	public void izmeniProfesora(String ime, String prezime, Date datRodj, String adresaStanovanja, String kontaktTel,
			String email, String adresaKanc, String brojLK, String titula, ProfesorType zvanje, List<Predmet> predmeti) {
		for (Profesor i : Profesori) {
			if (i.getBrojLK().equals(brojLK)) {
				i.setIme(ime);
				i.setPrezime(prezime);
				i.setDatRodj(datRodj);
				i.setAdresaStanovanja(adresaStanovanja);
				i.setKontaktTel(kontaktTel);
				i.setEmail(email);
				i.setAdresaKanc(adresaKanc);
				i.setBrojLK(brojLK);
				i.setTitula(titula);
				i.setZvanje(zvanje);
				i.setPredmeti(predmeti);
				snimiProfesore();
				break;
			}
		}
	}
	
	public void snimiProfesore() {
		// TODO Auto-generated method stub
		try{  

			  //Creating stream and writing the object  
			  FileOutputStream fout=new FileOutputStream("profesori.txt");  
			  ObjectOutputStream out=new ObjectOutputStream(fout);  
			  out.writeObject(this.Profesori);  
			  out.flush();  
			  //closing the stream  
			  out.close();  
			  System.out.println("success"); 
			  
			  } catch(Exception e){
				  System.out.println(e);
			  	}
	}

	private void initProfesore() {

		// provera da li je prazan fajl
				File fajl = new File("profesori.txt");
				if (fajl.length() == 0)
					return;
		
		try
        {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream("profesori.txt"); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            Profesori = (ArrayList<Profesor>)in.readObject(); 
              
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
	
	public ArrayList<Object> findProfesor(String ime, String prezime, String brojLK) {
		
		ArrayList<Object> found = new ArrayList<>();
		
		ime = ime.toLowerCase();
		prezime = prezime.toLowerCase();
		brojLK = brojLK.toLowerCase();
		
		
		for (Profesor i: Profesori) {
			
			if (i.getBrojLK().toLowerCase().equals(brojLK)) {
				found.add(i);
				return found;
			} 
			if (i.getIme().toLowerCase().startsWith(ime) && i.getPrezime().toLowerCase().startsWith(prezime) && i.getBrojLK().toLowerCase().startsWith(brojLK)) 
				found.add(i);	
		}	
		return found;	
	}
	
	
}
