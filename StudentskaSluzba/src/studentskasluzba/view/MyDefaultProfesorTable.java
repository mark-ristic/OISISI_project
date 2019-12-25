package studentskasluzba.view;

import javax.swing.table.DefaultTableModel;

public class MyDefaultProfesorTable extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8806567802729928904L;

	public MyDefaultProfesorTable() { 
		
		Object[] columns = { "Broj licne karte", "Ime", "Prezime", "Stalni/Vanredni", "Titula", "Spisak predmeta" };
	
		this.setColumnIdentifiers(columns);
	}
}
