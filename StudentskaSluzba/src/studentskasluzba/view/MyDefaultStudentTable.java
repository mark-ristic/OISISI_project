package studentskasluzba.view;

import javax.swing.table.DefaultTableModel;


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
		this.setColumnIdentifiers(columns);
		
	}

}
