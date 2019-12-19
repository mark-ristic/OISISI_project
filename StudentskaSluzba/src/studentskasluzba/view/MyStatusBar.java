package studentskasluzba.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.swing.JLabel;


// extendujem CustomPanel posto cu sve uraditi ovde pa cu samo instancirati u mainframe

public class MyStatusBar extends CustomPanel {

	/**
	* 
	*/
	private static final long serialVersionUID = 929054814749373739L;

	public MyStatusBar(int width, int height, Color color) {
		super(width,height,color);
		// spaceovi pre labele su samo da se lepse pozicionira
		JLabel label1 = new JLabel("    Studentska sluzba");
		label1.setPreferredSize(new Dimension(205, 45));

		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();

		JLabel label2 = new JLabel();
		label2.setPreferredSize(new Dimension(210, 45));

		setLayout(new GridLayout(1, 9));
		
		// pomocne labele koje namestaju poziciju 
		Vector<JLabel> labele = new Vector<JLabel>(); 
		for (int i=0; i<6; i++) {
			labele.add(new JLabel());
		    labele.get(i).setPreferredSize(new Dimension(144,45));
		}
		
		add(label1);
		for (int i1 = 0; i1<labele.size(); i1++) 
			add(labele.get(i1));
		add(label2); 
		// ovako sam nasao da se pravi timer na stackOverflow
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				String string = new SimpleDateFormat("HH:mm").format(new Date());
				label2.setText(string + "                    " + dateformat.format(date));
			}
		}, 0, 1000);
	}
}

