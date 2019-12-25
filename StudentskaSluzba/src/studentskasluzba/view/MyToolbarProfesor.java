package studentskasluzba.view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class MyToolbarProfesor extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2468238507220547278L;
	
	public static final JButton addProf = new JButton();
	public static final JButton editProf = new JButton();
	public static final JButton removeProf = new JButton();
	
	public MyToolbarProfesor(final JFrame parent) {
		
		super(JToolBar.HORIZONTAL);
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setRollover(false);
		setFloatable(false);
		
		// Pomocni panel za popunjavanje mesta pored buttonsa
		CustomPanel customHelp = new CustomPanel(1440-3*55, 55, Color.CYAN);
	
		addProf.setIcon(new ImageIcon("images\\add-55x55.png"));
		addProf.setToolTipText("Dodaj profesora");
		addProf.setBorderPainted(true);
		addProf.setFocusPainted(false);

		editProf.setIcon(new ImageIcon("images\\edit-55x55.png"));
		editProf.setToolTipText("Izmeni profesora");
		editProf.setBorderPainted(true);
		editProf.setFocusPainted(false);
		
		removeProf.setIcon(new ImageIcon("images\\delete-55x55.png"));
		removeProf.setToolTipText("Obrisi profesora");
		removeProf.setBorderPainted(true);
		removeProf.setFocusPainted(false);
		
		add(addProf);
		add(editProf);
		add(removeProf);
		
		//TODO: Slike za JButtone zameniti 
		
		
		// searchbar
		
		JTextField search = new JTextField (15);
		JButton magny = new JButton();
		
		magny.setIcon(new ImageIcon("images\\search-35x35.png"));
		magny.setBorderPainted(false);
		magny.setBackground(Color.CYAN);
		magny.setToolTipText("Pretrazi");
		
		customHelp.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		customHelp.add(search);
		customHelp.add(magny);
		
		add(customHelp);
		
	}
	
}
