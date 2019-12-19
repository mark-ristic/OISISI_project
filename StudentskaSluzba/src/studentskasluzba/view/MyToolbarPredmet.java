package studentskasluzba.view;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

public class MyToolbarPredmet extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8029393810990944532L;

	public static final JButton addStudPr = new JButton();
	public static final JButton editStudPr = new JButton();
	public static final JButton removeStudPr = new JButton();
	public static final JButton addProfPr = new JButton();
	public static final JButton editProfPr = new JButton();
	public static final JButton removeProfPr = new JButton();
	public static final JButton addPredmet = new JButton();
	public static final JButton editPredmet = new JButton();
	public static final JButton removePredmet = new JButton();
	
public MyToolbarPredmet(final JFrame parent) {
		
		super(JToolBar.HORIZONTAL);
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setRollover(false);
		setFloatable(false);
		
		// Pomocni panel za popunjavanje mesta pored buttonsa
		CustomPanel customHelp = new CustomPanel(1440-9*55, 55, Color.CYAN);
	
		// dodaj,izmeni, brisi studenta sa predmeta buttoni
		addStudPr.setIcon(new ImageIcon("images\\add-55x55.png"));
		addStudPr.setToolTipText("Dodaj studenta");
		addStudPr.setBorderPainted(true);
		addStudPr.setFocusPainted(false);

		editStudPr.setIcon(new ImageIcon("images\\edit-55x55.png"));
		editStudPr.setToolTipText("Izmeni studenta");
		editStudPr.setBorderPainted(true);
		editStudPr.setFocusPainted(false);
		
		removeStudPr.setIcon(new ImageIcon("images\\delete-55x55.png"));
		removeStudPr.setToolTipText("Obrisi studenta");
		removeStudPr.setBorderPainted(true);
		removeStudPr.setFocusPainted(false);
		
		// dodaj, izmeni, brisi profesora sa predmeta buttoni
		addProfPr.setIcon(new ImageIcon("images\\add-55x55.png"));
		addProfPr.setToolTipText("Dodaj profesora");
		addProfPr.setBorderPainted(true);
		addProfPr.setFocusPainted(false);

		editProfPr.setIcon(new ImageIcon("images\\edit-55x55.png"));
		editProfPr.setToolTipText("Izmeni profesora");
		editProfPr.setBorderPainted(true);
		editProfPr.setFocusPainted(false);
		
		removeProfPr.setIcon(new ImageIcon("images\\delete-55x55.png"));
		removeProfPr.setToolTipText("Obrisi profesora");
		removeProfPr.setBorderPainted(true);
		removeProfPr.setFocusPainted(false);
		
		// dodaj,izmeni,brisi predmet
		addPredmet.setIcon(new ImageIcon("images\\add-55x55.png"));
		addPredmet.setToolTipText("Dodaj predmet");
		addPredmet.setBorderPainted(true);
		addPredmet.setFocusPainted(false);

		editPredmet.setIcon(new ImageIcon("images\\edit-55x55.png"));
		editPredmet.setToolTipText("Izmeni predmet");
		editPredmet.setBorderPainted(true);
		editPredmet.setFocusPainted(false);
		
		removePredmet.setIcon(new ImageIcon("images\\delete-55x55.png"));
		removePredmet.setToolTipText("Obrisi predmet");
		removePredmet.setBorderPainted(true);
		removePredmet.setFocusPainted(false);
		
		//dodavanje na panel
		add(addStudPr);
		add(editStudPr);
		add(removeStudPr);
		add(addProfPr);
		add(editProfPr);
		add(removeProfPr);
		add(addPredmet);
		add(editPredmet);
		add(removePredmet);
		
		add(customHelp);
		
		// TODO: Ikone promeniti! 
		
	}
	
}
