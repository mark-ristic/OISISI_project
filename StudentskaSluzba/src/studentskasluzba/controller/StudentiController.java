package studentskasluzba.controller;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

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





}
