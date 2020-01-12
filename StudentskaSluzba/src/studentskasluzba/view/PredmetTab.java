package studentskasluzba.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;

public class PredmetTab extends CustomPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6503754488827263187L;
	
	// kad izbrisemo licnu kartu profesora moramo izmeniti tabelu predmeta 
	public static MyPredmetTable myPredmetTable;

	PredmetTab() {
		
		CustomPanel top_inset = new CustomPanel(1440,120, Color.WHITE);
		CustomPanel bot_inset = new CustomPanel(1440,120, Color.WHITE);
		CustomPanel centerPanel = new CustomPanel(1440, 630, Color.WHITE);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(top_inset);
		this.add(centerPanel);
		this.add(bot_inset);
		
		myPredmetTable = new MyPredmetTable();
		
		centerPanel.setLayout(new GridLayout(1,1));
		myPredmetTable.getTableHeader().setReorderingAllowed(false);
		myPredmetTable.getTableHeader().setResizingAllowed(false);
		centerPanel.add(myPredmetTable.getJScrollPane());
		
	}
	
}
