package studentskasluzba.view;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import studentskasluzba.controller.PredmetiController;

public class MyDefaultPredmetTable extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4987264705803030397L;

	MyDefaultPredmetTable() {

		Vector<Object> objekti = PredmetiController.getInstance().initiateTable(this);
		Vector<Object> kolone = new Vector<Object>();
		
		kolone.add("sifra");
		kolone.add("naziv");
		kolone.add("semestar");
		kolone.add("godina");
		kolone.add("pred prof");
		kolone.add("Spisak studenata");
		
		this.setDataVector(objekti, kolone); 
		
	}
	
	
}
