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

	public Profesor getProfesor(int brojLK) {
		return BazaProfesora.getInstance().getProfesor(brojLK);
	}

    public ArrayList<Profesor> getProfesori() {
        return (ArrayList<Profesor>) BazaProfesora.getInstance().getProfesori();
    }

	public void dodajProfesora(String ime, String prezime, Date datRodj, String adresaStanovanja, int kontaktTel,
			String email, String adresaKanc, int brojLK, String titula, ProfesorType zvanje, ArrayList<Predmet> predmeti) {

		boolean exists = BazaProfesora.getInstance().dodajProfesora(ime, prezime, datRodj, adresaStanovanja, kontaktTel, email,
						adresaKanc, brojLK, titula, zvanje, predmeti);
	}

	public void izbrisiProfesora(int brojLK) {
		BazaProfesora.getInstance().izbrisiProfesora(brojLK);
	}

	public void izmeniProfesora(String ime, String prezime, Date datRodj, String adresaStanovanja, int kontaktTel,
			String email, String adresaKanc, int brojLK, String titula, ProfesorType zvanje, ArrayList<Predmet> predmeti) {

		BazaProfesora.getInstance().izmeniProfesora(ime, prezime, datRodj, adresaStanovanja, kontaktTel, email, adresaKanc,
													brojLK, titula, zvanje, predmeti);
	}
}
