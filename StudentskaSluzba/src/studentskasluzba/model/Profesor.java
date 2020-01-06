package studentskasluzba.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Profesor  extends Osoba implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8157416288467881120L;

	public enum ProfesorType { Stalni {

		@Override
		public String toString() {
			return "Stalni";
		}

	}, Vanredni {

		@Override
		public String toString() {
			return "Vanredni";
		}

	}, Docent {

		@Override
		public String toString() {
			return "Docent";
		}
	} ;
	}
	
	private String adresaKanc;
	private int brojLK;
	private String titula;
	private ProfesorType zvanje;
	private List<Predmet> predmeti;
	
	public Profesor(String ime, String prezime, Date datRodj, String adresaStanovanja, int kontaktTel,
			String email, String adresaKanc, int brojLK, String titula, ProfesorType zvanje, ArrayList<Predmet> predmeti) {
		super(ime, prezime, datRodj, adresaStanovanja, kontaktTel, email);
		
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

	public ProfesorType getZvanje() {
		return zvanje;
	}

	public void setZvanje(ProfesorType zvanje) {
		this.zvanje = zvanje;
	}

	public List<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
	}
	
	
}
