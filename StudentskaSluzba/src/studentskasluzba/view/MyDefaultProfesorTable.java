package studentskasluzba.view;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import studentskasluzba.controller.ProfesoriController;

public class MyDefaultProfesorTable extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8806567802729928904L;

	public MyDefaultProfesorTable() { 
		
		Object[] columns = { "ime",
				 "prezime",
				 "datum rodjenja",
				 "adresa stanovanja",
				 "kontakt telefon",
				 "email",
				 "adresa kancelarije",
				 "broj licne karte",
				 "titula",
				 "zvanje",
				 "Spisak predmeta"};
	
		//this.setColumnIdentifiers(columns);
		
		Vector<Object> objekti = ProfesoriController.getInstance().initiateTable(this);
		
		Vector<String> columnz = new Vector<String>();
		
		for (Object o : columns)
			columnz.add((String) o);
		
		this.setDataVector(objekti, columnz);
	}
}
