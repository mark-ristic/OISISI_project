package studentskasluzba.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class Profesor  extends Osoba implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8157416288467881120L;

	public enum ProfesorType { Stalni , Vanredni } ; 
	private ProfesorType profesorType;
	private String adresaKanc;
	private int brojLK;
	private String titula;
	private String zvanje;
	private List<Predmet> predmeti;
	
	public Profesor(ProfesorType profesorType, String ime, String prezime, DateFormat datRodj, String adresaStanovanja, int kontaktTel,
			String email, String adresaKanc, int brojLK, String titula, String zvanje, ArrayList<Predmet> predmeti) {
		super(ime, prezime, datRodj, adresaStanovanja, kontaktTel, email);
		this.profesorType = profesorType;
		this.adresaKanc = adresaKanc;
		this.brojLK = brojLK;
		this.titula = titula;
		this.zvanje = zvanje;
		this.predmeti = predmeti;
	}
	
	public Profesor() {}
	
	public String getAdresaKanc() {
		return adresaKanc;
	}

	public void setAdresaKanc(String adresaKanc) {
		this.adresaKanc = adresaKanc;
	}

	public int getBrojLK() {
		return brojLK;
	}

	public void setBrojLK(int brojLK) {
		this.brojLK = brojLK;
	}

	public String getTitula() {
		return titula;
	}

	public void setTitula(String titula) {
		this.titula = titula;
	}

	public String getZvanje() {
		return zvanje;
	}

	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}

	public List<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
	}
	
	public void setProfesorType(ProfesorType type) {
		this.profesorType = type;		
	}
	
	public ProfesorType getProfesorType() {
		return this.profesorType;
	}
	
}
