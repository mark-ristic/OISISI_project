package studentskasluzba.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import studentskasluzba.model.BazaStudenata;
import studentskasluzba.model.Predmet;
import studentskasluzba.model.Student;
import studentskasluzba.model.Student.Status;
import studentskasluzba.view.MyDefaultStudentTable;

public class StudentiController {

	private static StudentiController instance = null;

	public static StudentiController getInstance() {
		if (instance == null)
			instance = new StudentiController();
		return instance;
	}

	// String ime, String prezime, DateFormat datRodj, String adresaStanovanja, int kontaktTel,
	//String email, String indeks, DateFormat datumUpisa, int godStud, Status status, double prosek,
	//ArrayList<Predmet> predmeti

	public void dodajStudenta(String ime, String prezime, Date datRodj, String adresaStanovanja, String kontaktTel,
			String email, String indeks, Date datumUpisa, int godStud, Status status, double prosek,
			ArrayList<Predmet> predmeti, Object[] row, MyDefaultStudentTable mdst) {

		boolean exists = BazaStudenata.getInstance().dodajStudenta(ime, prezime, datRodj, adresaStanovanja, kontaktTel, email,
												indeks, datumUpisa, godStud, status, prosek, predmeti);
		
		if (exists == false) 
			mdst.addRow(row);   // ako ne postoji taj vec u bazi dodaj ga u tabelu
	}

	public void izbrisiStudenta(String index, MyDefaultStudentTable mdst, int selected) {
		
		BazaStudenata.getInstance().izbrisiStudenta(index);
		
		if (selected<0) {
			System.out.println("ERROR WHILE DELETING ROW" + " " + this.getClass().getSimpleName());
			return;
		}
		if (selected != -1) {
			mdst.removeRow(selected);
			
			System.out.println("Student uspesno obrisan" + " " + this.getClass().getSimpleName()); 
		}
		
	}

	public void izmeniStudenta(String ime, String prezime, Date datRodj, String adresaStanovanja, String kontaktTel,
			String email, String indeks, Date datumUpisa, int godStud, Status status, double prosek,
			ArrayList<Predmet> predmeti , MyDefaultStudentTable mdst, int selected) {
		
		BazaStudenata.getInstance().izmeniStudenta(ime, prezime, datRodj, adresaStanovanja, kontaktTel, email, indeks, datumUpisa,
													godStud, status, prosek, predmeti);
		
		Student st = BazaStudenata.getInstance().getStudent(indeks);
		String god = Integer.toString(st.getGodStud());
		
		mdst.setValueAt(st.getIme(), selected, 0);
		mdst.setValueAt(st.getPrezime(), selected, 1);
		mdst.setValueAt(parseDate(st.getDatRodj()), selected, 2);
		mdst.setValueAt(st.getAdresaStanovanja(), selected, 3);
		mdst.setValueAt(st.getKontaktTel(), selected, 4);
		mdst.setValueAt(st.getEmail(), selected, 5);
		mdst.setValueAt(st.getIndeks(), selected, 6);
		mdst.setValueAt(parseDate(st.getDatumUpisa()), selected, 7);
		mdst.setValueAt(god, selected, 8);
		mdst.setValueAt(st.getStatus().toString(), selected, 9);
		mdst.setValueAt(Double.toString(st.getProsek()), selected, 10);
		mdst.setValueAt("PREDMETI-" + st.getIndeks(), selected, 11);
		
	}

	public Student getStudent(String indeks) {
		return BazaStudenata.getInstance().getStudent(indeks);
	}

	public ArrayList<Student> getStudenti() {
		return (ArrayList<Student>) BazaStudenata.getInstance().getStudenti();
	}

	public Vector<Object> initiateTable(DefaultTableModel model) {
		
		Vector<Object> objects = new Vector<>();
		for (Student i : BazaStudenata.getInstance().getStudenti()) {
			
			Vector<String> red = new Vector<String>();
			red.add(i.getIme());	
			red.add(i.getPrezime());
			//System.out.println(i.getDatRodj());
			red.add(parseDate(i.getDatRodj()));
			red.add(i.getAdresaStanovanja());
			red.add(i.getKontaktTel());
			red.add(i.getEmail());
			red.add(i.getIndeks());
			red.add(parseDate(i.getDatumUpisa()));
			
			
			red.add(Integer.toString(i.getGodStud()));
			red.add(i.getStatus().toString());
			red.add(Double.toString(i.getProsek()));
			red.add("PREDMETI-" + i.getIndeks());
			
			objects.add(red);
		}
		
		return objects;
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

	public void snimiStudente() {
		// TODO Auto-generated method stub
		BazaStudenata.getInstance().snimiStudente();
	}

	public ArrayList<Object> findStudent(String ime, String prezime, String index) {
		
		return BazaStudenata.getInstance().findStudent(ime, prezime, index);
	}

	
	public boolean checkRegex(String ime, String prezime, String datumRodj, String adresa, String telefon,
								String email, String indeks, String datumUpisa,  String prosek) {
		
		//Prvo provera obaveznih polja
		if(ime.trim().isEmpty() ||
				prezime.trim().isEmpty() ||
					datumRodj.trim().isEmpty() ||
						/*adresa.trim().isEmpty() ||*/
							/*telefon.trim().isEmpty() || */
								email.trim().isEmpty() ||
								indeks.trim().isEmpty() ||
									datumUpisa.trim().isEmpty() ||
									
											prosek.trim().isEmpty())
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
		} */ // KOMENTARISANO ZBOG LATINICNIH SLOVA SA KAPICOM
			
		// regexMatch za datum rodjenja 
		
		String datumpattern = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[012])\\.(19|20)\\d\\d\\.$";
		Pattern datePatt = Pattern.compile(datumpattern);
		Matcher datumMatcher = datePatt.matcher(datumRodj);
		
		if(!datumMatcher.matches()) {
			JOptionPane.showMessageDialog(null, "Los format datuma");
			return true;
		}
		
		// email matching
	
		String emailPattern = "^[a-zA-Z0-9]{1,20}\\.?[a-zA-Z0-9]{1,20}?@[a-zA-Z0-9]{1,20}\\.[a-zA-Z]{2,3}$";
		Pattern pattern = Pattern.compile(emailPattern);
		Matcher regexMatcher = pattern.matcher(email);
		
		if(!regexMatcher.matches()) {
			JOptionPane.showMessageDialog(null, "Los email format");
			return true;
		}
		
		// indeks match
		
		String indeksPattern = "[A-Z]{2}[ ][1-9]{1}[0-9]{0,2}\\/(19|20)\\d\\d";
		Pattern indexpatt = Pattern.compile(indeksPattern);
		Matcher indexMatcher = indexpatt.matcher(indeks);
		
		if(!indexMatcher.matches()) {
			JOptionPane.showMessageDialog(null, "Los indeks format");
			return true;
		}
		
		// telefon format - na semu datih podataka	
		String mobPattern = "[0-9]{1,10}\\/?[0-9]{1,10}?[-]?[0-9]{1,10}?";
		Pattern mobpatt = Pattern.compile(mobPattern);
		Matcher mobMatcher = mobpatt.matcher(telefon);

		if(!mobMatcher.matches()) {
			JOptionPane.showMessageDialog(null, "Los broj telefona");
			return true;
		} 

		// datum upisa
		
		Matcher datUpisMatcher = datePatt.matcher(datumUpisa);
		
		if(!datUpisMatcher.matches()) {
			JOptionPane.showMessageDialog(null, "Los format datuma upisa");
			return true;
		}
		
		// prosek match - broj izmedju 6.00-10.00
		//String prosekPattern = "[[6-9]|10]{1}\\.[0-9]{2}";
		String prosekPattern = "\\d{0,2}\\.\\d{1,2}" ;
		Pattern prosekpatt = Pattern.compile(prosekPattern);
		Matcher prosekMatcher = prosekpatt.matcher(prosek);
		
		//String prosekNull = "\\0";
		//Pattern prosekNullpatt = Pattern.compile(prosekNull);
		//Matcher prosekNullMatcher = prosekNullpatt.matcher(prosek);
		
		if(!prosekMatcher.matches()) {
			JOptionPane.showMessageDialog(null, "Prosek mora biti izmedju 6.00 i 10.00!");
			return true;
		}
		
		double d = Double.parseDouble(prosek);
		
		if (d < 6 || d>10) {
			JOptionPane.showMessageDialog(null, "Prosek mora biti izmedju 6.00 i 10.00!!!!!!");
			return true;		
		}
		
		//if(!prosekNullMatcher.matches()) {
		//	JOptionPane.showMessageDialog(null, "Prosek mora biti izmedju 6.00 i 10.00 ili 0 ako nema prosek!");
		//	return true;
		//}
		
		return false;
	}
	

}
