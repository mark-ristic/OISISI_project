package studentskasluzba.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import studentskasluzba.model.BazaProfesora;
import studentskasluzba.model.Predmet;
import studentskasluzba.model.Profesor;
import studentskasluzba.model.Profesor.ProfesorType;
import studentskasluzba.view.MyDefaultProfesorTable;

public class ProfesoriController {

	private static ProfesoriController instance = null;

	public static ProfesoriController getInstance() {
		if (instance == null)
			instance = new ProfesoriController() ;
		return instance;
	}

	public Profesor getProfesor(String brojLK) {
		return BazaProfesora.getInstance().getProfesor(brojLK);
	}

    public ArrayList<Profesor> getProfesori() {
        return (ArrayList<Profesor>) BazaProfesora.getInstance().getProfesori();
    }

	public void dodajProfesora(String ime, String prezime, Date datRodj, String adresaStanovanja, String kontaktTel,
			String email, String adresaKanc, String brojLK, String titula, ProfesorType zvanje, ArrayList<Predmet> predmeti,
			Object[] row, MyDefaultProfesorTable mdpt) {

		boolean exists = BazaProfesora.getInstance().dodajProfesora(ime, prezime, datRodj, adresaStanovanja, kontaktTel, email,
						adresaKanc, brojLK, titula, zvanje, predmeti);
		
		if (exists == false) 
			mdpt.addRow(row);
	}

	public void izbrisiProfesora(String brojLK, DefaultTableModel mdpt, int selected) {
		
		BazaProfesora.getInstance().izbrisiProfesora(brojLK);
		
		if (selected<0) {
			System.out.println("Greska prilikom brisanja profesora" + " " + this.getClass().getSimpleName());
			return;
		}
		if (selected != -1) {
			mdpt.removeRow(selected);
			System.out.println("Profesor uspesno obrisan" + " " + this.getClass().getSimpleName()); 
		}
		
	}

	public void izmeniProfesora(String ime, String prezime, Date datRodj, String adresaStanovanja, String kontaktTel,
			String email, String adresaKanc, String brojLK, String titula, ProfesorType zvanje, ArrayList<Predmet> predmeti,
			MyDefaultProfesorTable mdpt, int selected) {

		BazaProfesora.getInstance().izmeniProfesora(ime, prezime, datRodj, adresaStanovanja, kontaktTel, email, adresaKanc,
													brojLK, titula, zvanje, predmeti);
		// izvucemo bas onog profesora koga smo izmenili i azuriramo podatke u tabeli
		Profesor p = BazaProfesora.getInstance().getProfesor(brojLK);
		
		/* String ime, String prezime, Date datRodj, String adresaStanovanja, int kontaktTel,
			String email, String adresaKanc, int brojLK, String titula, ProfesorType zvanje, ArrayList<Predmet> predmeti */
		
		mdpt.setValueAt(p.getIme(), selected, 0);
		mdpt.setValueAt(p.getPrezime(), selected, 1);
		mdpt.setValueAt(parseDate(p.getDatRodj()), selected, 2);
		mdpt.setValueAt(p.getAdresaStanovanja(), selected, 3);
		mdpt.setValueAt(p.getKontaktTel(), selected, 4);
		mdpt.setValueAt(p.getEmail(), selected, 5);
		mdpt.setValueAt(p.getAdresaKanc(), selected, 6);
		mdpt.setValueAt(p.getBrojLK(), selected, 7);
		mdpt.setValueAt(p.getTitula(), selected, 8);
		mdpt.setValueAt(p.getZvanje().toString(), selected, 9);
		mdpt.setValueAt("PREDMETI-" + p.getBrojLK(), selected, 10);	
	}
	
	public String parseDate(Date date) {
		// Sat Sep 02 00:00:00 CEST 2017
		String dan;
		String mesec = null;
		String godina;
		
		String[] data = date.toString().split(" ");
		
		dan = data[2];
		
		godina = data[5];
		
		if (data[1].equals("Jan"))
			mesec = "01";
		if (data[1].equals("Feb"))
			mesec = "02";
		if (data[1].equals("Mar"))
			mesec = "03";
		if (data[1].equals("Apr"))
			mesec = "04";
		if (data[1].equals("May"))
			mesec = "05";
		if (data[1].equals("Jun"))
			mesec = "06";
		if (data[1].equals("Jul"))
			mesec = "07";
		if (data[1].equals("Aug"))
			mesec = "08";
		if (data[1].equals("Sep"))
			mesec = "09";
		if (data[1].equals("Oct"))
			mesec = "10";
		if (data[1].equals("Nov"))
			mesec = "11";
		if (data[1].equals("Dec"))
			mesec = "12";
		
		return dan + "." + mesec + "." + godina + ".";
	}

	public void snimiProfesore() {
		 BazaProfesora.getInstance().snimiProfesore();
		
	}
	// inicijalno popunjavanje tabele profesora
	public Vector<Object> initiateTable(DefaultTableModel model) {
		Vector<Object> objects = new Vector<Object>();
		for (Profesor p : BazaProfesora.getInstance().getProfesori()) {
			
			/* String ime, String prezime, Date datRodj, String adresaStanovanja, int kontaktTel,
			String email, String adresaKanc, int brojLK, String titula, ProfesorType zvanje, ArrayList<Predmet> predmeti */
			
			Vector<String> red = new Vector<String>();
			red.add(p.getIme());
			red.add(p.getPrezime());
			red.add(parseDate(p.getDatRodj()));
			red.add(p.getAdresaStanovanja());
			red.add(p.getKontaktTel());
			red.add(p.getEmail());
			red.add(p.getAdresaKanc());
			red.add(p.getBrojLK());
			red.add(p.getTitula());
			red.add(p.getZvanje().toString());
			red.add("PREDMETI-" + p.getBrojLK());
			//model.addRow(red);		
			objects.add(red);
		}		
		return objects;
	}
	
	public ArrayList<Object> findProfesor(String ime, String prezime, String brojLK) {
		return BazaProfesora.getInstance().findProfesor(ime, prezime, brojLK);
	}
	
	
	public boolean checkRegex(String ime, String prezime, String datumRodj, String telefon, String brojLK, String email) {
		
		if(ime.trim().isEmpty() ||
				prezime.trim().isEmpty() ||
					datumRodj.trim().isEmpty() ||
						brojLK.trim().isEmpty() /*||
							email.trim().isEmpty()*/ )
		{
			JOptionPane.showMessageDialog(null, "Polja sa oznakom '*' moraju biti popunjena");
			return true;
		}
		/*
		// regexMatch za ime
		
		String imepattern = "[A-Z]{1}[a-z]{0,30}";
		Pattern imePatt = Pattern.compile(imepattern);
		Matcher imeMatcher = imePatt.matcher(ime);
		
		if(!imeMatcher.matches()) {
			JOptionPane.showMessageDialog(null, "Neodgovarajuce ime");
			return true;
		}
		
		// regex za prezime - ista sema kao i za ime
		
		Matcher prezimeMatcher = imePatt.matcher(prezime);
		
		if(!prezimeMatcher.matches()) {
			JOptionPane.showMessageDialog(null, "Neodgovarajuce prezime");
			return true;
		}		 */  // ZAKOMENTARISAN CHECKER ZA IME I PREZIME ZBOG LATINICNIH SLOVA SA KAPICOM
		
		// regexMatch za datum rodjenja 
		
		String datumpattern = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[012])\\.(19|20)\\d\\d\\.$";
		Pattern datePatt = Pattern.compile(datumpattern);
		Matcher datumMatcher = datePatt.matcher(datumRodj);
		
		if(!datumMatcher.matches()) {
			JOptionPane.showMessageDialog(null, "Pogresan format datuma");
			return true;
		}	
		
		
		// telefon format - na semu datih podataka	
		String mobPattern = "[0-9]{1,10}\\/?[0-9]{1,10}?[-]?[0-9]{1,10}?";
		Pattern mobpatt = Pattern.compile(mobPattern);
		Matcher mobMatcher = mobpatt.matcher(telefon);

		if(!mobMatcher.matches()) {
			JOptionPane.showMessageDialog(null, "Pogresan format broja telefona");
			return true;
		}	
		
		// brojLICNA regex
		
		String licnaPattern ="[0-9]{5,15}";
		Pattern licnapatt = Pattern.compile(licnaPattern);
		Matcher licnaMatcher = licnapatt.matcher(brojLK);
		
		if(!licnaMatcher.matches()) {
			JOptionPane.showMessageDialog(null, "Broj licne karte sadrzi samo brojeve (5 do 15 brojeva)");
			return true;
		}
		
		// email matching
		
		String emailPattern = "^[a-zA-Z0-9]{1,20}\\.?[a-zA-Z0-9]{1,20}?@[a-zA-Z0-9]{1,20}\\.[a-zA-Z]{2,3}$";
		Pattern pattern = Pattern.compile(emailPattern);
		Matcher regexMatcher = pattern.matcher(email);
		
		if(!regexMatcher.matches()) {
			JOptionPane.showMessageDialog(null, "Pogresan email format");
			return true;
		}
		
		
		
		
		
		
		return false;
	}
	
}
