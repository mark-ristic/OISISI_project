package studentskasluzba.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Student extends Osoba implements Serializable {
	// TODO : read this
	// IZBACUJE GRESKU JER MORAS TI DA DODAS PREDMET KLASU ZATO JE SVE SA PREDMETOM ZAKOMENTARISANO
	// nemoj pisati celu klasu profesor nasledi Osobu
	/**
	 * 
	 */
	private static final long serialVersionUID = -3731446794215616771L;
	
	public enum Status {B, S;}; 
	private String indeks;
	private DateFormat datumUpisa = new SimpleDateFormat("dd-mm-yyyy");
	private int godStud; 
	private Status status; 
	private double prosek;
//	private ArrayList<Predmet> predmeti;
	
	public Student(String ime, String prezime, DateFormat datRodj, String adresaStanovanja, int kontaktTel,
			String email, String indeks, DateFormat datumUpisa, int godStud, Status status, double prosek //,
			/* ArrayList<Predmet> predmeti */ ) {
		super(ime, prezime, datRodj, adresaStanovanja, kontaktTel, email);
		this.indeks = indeks;
		this.datumUpisa = datumUpisa;
		this.godStud = godStud;
		this.status = status;
		this.prosek = prosek;
		//this.predmeti = predmeti;
	}
	
	public Student() {}
	
	public String getIndeks() {
		return indeks;
	}
	public void setIndeks(String indeks) {
		this.indeks = indeks;
	}
	public DateFormat getDatumUpisa() {
		return datumUpisa;
	}
	public void setDatumUpisa(DateFormat datumUpisa) {
		this.datumUpisa = datumUpisa;
	}
	public int getGodStud() {
		return godStud;
	}
	public void setGodStud(int godStud) {
		this.godStud = godStud;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public double getProsek() {
		return prosek;
	}
	public void setProsek(double prosek) {
		this.prosek = prosek;
	}
	/*public ArrayList<Predmet> getPredmeti() {
		return predmeti;
	}
	public void setPredmeti(ArrayList<Predmet> predmeti) {
		this.predmeti = predmeti;
	}  */
	
	
}
