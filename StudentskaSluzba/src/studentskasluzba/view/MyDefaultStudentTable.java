package studentskasluzba.view;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import studentskasluzba.controller.StudentiController;


public class MyDefaultStudentTable extends DefaultTableModel {

	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 345867893678087840L;

	public MyDefaultStudentTable() { 
		
		Object[] columns = {"indeks",
				"ime", 
				"prezime",
				"godina studija", 
				"status",
				"prosek"};
		
		//this.setColumnIdentifiers(columns);
		
		/* String ime, String prezime, DateFormat datRodj, String adresaStanovanja, int kontaktTel,
		String email, String indeks, DateFormat datumUpisa, int godStud, Status status, double prosek,
		ArrayList<Predmet> predmeti */
	
		Vector<Object> objekti = StudentiController.getInstance().initiateTable(this);
		Vector<Object> columnz = new Vector<Object>();
	
		for (Object o : columns)
			columnz.add((String) o);
	
		this.setDataVector(objekti, columnz);
	}

}
