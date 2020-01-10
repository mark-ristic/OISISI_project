package studentskasluzba.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Predmet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3967849100359102927L;
	
	private String sifra;
	private String naziv;
	private int semestar;
	private int godIzv;
	private Profesor predProf;
	private ArrayList<Student> studenti;
	
	public Predmet() {}
	
	public Predmet(String sifra, String naziv, int semestar, int godIzv, Profesor predProf, ArrayList<Student> studenti) {
			 
		this.sifra = sifra;
		this.naziv = naziv;
		this.semestar = semestar;
		this.godIzv = godIzv;
		this.predProf = predProf;
		this.studenti = studenti;
	}
	
	public String getSifra() {
		return sifra;
	}
	
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	
	public String getNaziv() {
		return naziv;
	}
	
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public int getSemestar() {
		return semestar;
	}
	
	public void setSemestar(int semestar) {
		this.semestar = semestar;
	}
	
	public int getGodIzv() {
		return godIzv;
	}
	
	public void setGodIzv(int godIzv) {
		this.godIzv = godIzv;
	}
	
	public Profesor getPredProf() {
		return predProf;
	}
	
	public void setPredProf(Profesor predProf) {
		this.predProf = predProf;
	}
	
	public ArrayList<Student> getStudenti() {
		return studenti;
	}
	
	public void setStudenti(ArrayList<Student> studenti) {
		this.studenti = studenti;
	}
	
}
