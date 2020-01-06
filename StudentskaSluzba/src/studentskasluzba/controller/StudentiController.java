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

	public void dodajStudenta(String ime, String prezime, Date datRodj, String adresaStanovanja, int kontaktTel,
			String email, String indeks, Date datumUpisa, int godStud, Status status, double prosek,
			ArrayList<Predmet> predmeti) {

		boolean exists = BazaStudenata.getInstance().dodajStudenta(ime, prezime, datRodj, adresaStanovanja, kontaktTel, email,
												indeks, datumUpisa, godStud, status, prosek, predmeti);
	}

	public void izbrisiStudenta(String index) {
		BazaStudenata.getInstance().izbrisiStudenta(index);
	}

	public void izmeniStudenta(String ime, String prezime, Date datRodj, String adresaStanovanja, int kontaktTel,
			String email, String indeks, Date datumUpisa, int godStud, Status status, double prosek,
			ArrayList<Predmet> predmeti) {

		BazaStudenata.getInstance().izmeniStudenta(ime, prezime, datRodj, adresaStanovanja, kontaktTel, email, indeks, datumUpisa,
													godStud, status, prosek, predmeti);
	}

	public Student getStudent(String indeks) {
		return BazaStudenata.getInstance().getStudent(indeks);
	}

	public ArrayList<Student> getStudenti() {
		return (ArrayList<Student>) BazaStudenata.getInstance().getStudenti();
	}











}
