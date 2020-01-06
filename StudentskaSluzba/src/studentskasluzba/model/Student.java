package studentskasluzba.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Student extends Osoba implements Serializable {
	// TODO: read that 
	/**
	 * 
	 */
	private static final long serialVersionUID = -3731446794215616771L;
	
	public enum Status {B {
			@Override
			public String toString() {
				return "B";
			}
		}

		, S {
			@Override
			public String toString() {
				return "S";
			}
		}
		;};

	private String indeks;
	private Date datumUpisa;
	private int godStud; 
	private Status status; 
	private double prosek;
	private ArrayList<Predmet> predmeti;
	
	public Student(String ime, String prezime, Date datRodj, String adresaStanovanja, int kontaktTel,
			String email, String indeks, Date datumUpisa, int godStud, Status status, double prosek,
			ArrayList<Predmet> predmeti  ) {
		super(ime, prezime, datRodj, adresaStanovanja, kontaktTel, email);
		this.indeks = indeks;
		this.datumUpisa = datumUpisa;
		this.godStud = godStud;
		this.status = status;
		this.prosek = prosek;
		this.predmeti = predmeti;
	}
	
	public Student() {}
	
	public String getIndeks() {
		return indeks;
	}
	public void setIndeks(String indeks) {
		this.indeks = indeks;
	}
	public Date getDatumUpisa() {
		return datumUpisa;
	}
	public void setDatumUpisa(Date datumUpisa) {
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
	public ArrayList<Predmet> getPredmeti() {
		return predmeti;
	}
	public void setPredmeti(ArrayList<Predmet> predmeti) {
		this.predmeti = predmeti;
	}  
	
	
}