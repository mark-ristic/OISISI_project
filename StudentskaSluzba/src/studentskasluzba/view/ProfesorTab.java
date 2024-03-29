package studentskasluzba.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;

public class ProfesorTab extends CustomPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4803294274046341734L;
	
	ProfesorTab() {
		
		CustomPanel top_inset = new CustomPanel(1440,120, Color.WHITE);
		CustomPanel bot_inset = new CustomPanel(1440,120, Color.WHITE);
		CustomPanel centerPanel = new CustomPanel(1440, 630, Color.WHITE);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(top_inset);
		this.add(centerPanel);
		this.add(bot_inset);
		
		centerPanel.setLayout(new GridLayout(1,1));
		MyProfesorTable myProfesorTable = new MyProfesorTable();
		myProfesorTable.getTableHeader().setReorderingAllowed(false);
		myProfesorTable.getTableHeader().setResizingAllowed(false);
		centerPanel.add(myProfesorTable.getJScrollPane());		
	}
	
	// TODO: uraditi proftab
}
