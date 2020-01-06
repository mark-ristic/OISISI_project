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

public class BazaPredmeta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5079750069711873L;

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
		System.out.println("Ne postoji predmet sa tom sifrom" + " " + this.getClass().getSimpleName());
		return null;
	}

	public boolean dodajPredmet(String sifra, String naziv, String semestar, int godIzv, Profesor predProf,ArrayList<Student> studenti) {

		boolean flag = false; // flag da znamo da li smo uspesno dodali ili ne

		for (Predmet p : predmeti) {
			if (p.getSifra().equals(sifra)) {
				System.out.println("Predmet vec postoji u bazi" + " " + this.getClass().getSimpleName());
				flag = true;
				return flag;
			}
		}

		predmeti.add(new Predmet(sifra,naziv,semestar,godIzv,predProf,studenti));
		snimiPredmete();
		return flag;
	}

	public void izbrisiPredmet(String sifra) {
		for (Predmet p : predmeti) {
			if(p.getSifra().equals(sifra)) {

				predmeti.remove(p);
				snimiPredmete();
				break;
			}
		}
	}
	public void izmeniPredmet(String sifra, String naziv, String semestar, int godIzv, Profesor predProf,ArrayList<Student> studenti) {
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

}
