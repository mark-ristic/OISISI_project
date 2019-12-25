package studentskasluzba.model;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Osoba implements Serializable {

	private String ime;
	private String prezime;
	private DateFormat datRodj = new SimpleDateFormat("dd-mm-yyyy"); 
	private String adresaStanovanja; 
	private int kontaktTel; 
	private String email;
	

	public Osoba(String ime, String prezime, DateFormat datRodj, String adresaStanovanja, int kontaktTel, String email) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.datRodj = datRodj;
		this.adresaStanovanja = adresaStanovanja;
		this.kontaktTel = kontaktTel;
		this.email = email;
	}
	
	public Osoba() {}
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public DateFormat getDatRodj() {
		return datRodj;
	}
	public void setDatRodj(DateFormat datRodj) {
		this.datRodj = datRodj;
	}
	public String getAdresaStanovanja() {
		return adresaStanovanja;
	}
	public void setAdresaStanovanja(String adresaStanovanja) {
		this.adresaStanovanja = adresaStanovanja;
	}
	public int getKontaktTel() {
		return kontaktTel;
	}
	public void setKontaktTel(int kontaktTel) {
		this.kontaktTel = kontaktTel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	} 
	
	
}
