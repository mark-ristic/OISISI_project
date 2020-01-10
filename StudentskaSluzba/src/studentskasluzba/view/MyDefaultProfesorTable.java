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
		
		Object[] columns = { "Broj licne karte", "Ime", "Prezime", "Stalni/Vanredni", "Titula", "Spisak predmeta" };
	
		//this.setColumnIdentifiers(columns);
		
		Vector<Object> objekti = ProfesoriController.getInstance().initiateTable(this);
		
		Vector<String> columnz = new Vector<String>();
		
		for (Object o : columns)
			columnz.add((String) o);
		
		this.setDataVector(objekti, columnz);
	}
}
