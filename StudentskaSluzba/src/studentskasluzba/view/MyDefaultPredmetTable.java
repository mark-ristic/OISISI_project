package studentskasluzba.view;

import javax.swing.table.DefaultTableModel;

public class MyDefaultPredmetTable extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4987264705803030397L;

	MyDefaultPredmetTable() {
		
		Object[] columns = {"sifra",
				"naziv",
				"semestar",
				"godina",
				"pred prof",
				"spisak studenata"};
		
		this.setColumnIdentifiers(columns);
	
		
	}
	
	
}
