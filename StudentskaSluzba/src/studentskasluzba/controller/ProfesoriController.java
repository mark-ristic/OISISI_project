package studentskasluzba.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

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

	public void izbrisiProfesora(String brojLK) {
		BazaProfesora.getInstance().izbrisiProfesora(brojLK);
		//TODO : brisanje profesora iz tabele 
		
		
	}

	public void izmeniProfesora(String ime, String prezime, Date datRodj, String adresaStanovanja, String kontaktTel,
			String email, String adresaKanc, String brojLK, String titula, ProfesorType zvanje, ArrayList<Predmet> predmeti,
			MyDefaultProfesorTable mdpt, int selected) {

		BazaProfesora.getInstance().izmeniProfesora(ime, prezime, datRodj, adresaStanovanja, kontaktTel, email, adresaKanc,
													brojLK, titula, zvanje, predmeti);
		
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
	
}
