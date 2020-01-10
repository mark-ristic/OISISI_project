package studentskasluzba.model;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Osoba implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5208641147102905436L;
	private String ime;
	private String prezime;
	private Date datRodj; 
	private String adresaStanovanja; 
	private String kontaktTel; 
	private String email;
	

	public Osoba(String ime, String prezime, Date datRodj, String adresaStanovanja, String kontaktTel, String email) {
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
	public Date getDatRodj() {
		return datRodj;
	}
	public void setDatRodj(Date datRodj) {
		this.datRodj = datRodj;
	}
	public String getAdresaStanovanja() {
		return adresaStanovanja;
	}
	public void setAdresaStanovanja(String adresaStanovanja) {
		this.adresaStanovanja = adresaStanovanja;
	}
	public String getKontaktTel() {
		return kontaktTel;
	}
	public void setKontaktTel(String kontaktTel) {
		this.kontaktTel = kontaktTel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	} 
	
	
}
