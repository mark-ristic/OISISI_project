package studentskasluzba.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;

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
		CustomPanel customHelp = new CustomPanel(1440-3*55, 55, Color.WHITE);
	
		addProf.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Avatars\\teacher_final_add.png"));
		addProf.setToolTipText("Dodaj profesora");
		addProf.setBorderPainted(true);
		addProf.setFocusPainted(false);
		addProf.setBorder(new LineBorder(new Color(51, 153, 255, 90)));

		editProf.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Avatars\\teacher_final_edit.png"));
		editProf.setToolTipText("Izmeni profesora");
		editProf.setBorderPainted(true);
		editProf.setFocusPainted(false);
		editProf.setBorder(new LineBorder(new Color(51, 153, 255, 90)));
		
		removeProf.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Avatars\\teacher_final_remove.png"));
		removeProf.setToolTipText("Obrisi profesora");
		removeProf.setBorderPainted(true);
		removeProf.setFocusPainted(false);
		removeProf.setBorder(new LineBorder(new Color(51, 153, 255, 90)));
		
		add(addProf);
		add(editProf);
		add(removeProf);
		
		
		// searchbar
		
		JTextField search = new JTextField (30);
		search.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		// template za pretragu profesora
		search.setText("broj licne karte<>ime<>prezime<>");
		JButton magny = new JButton();
		
		magny.setIcon(new ImageIcon("StudentskaSluzba\\images\\search-35x35.png"));
		magny.setBorderPainted(false);
		magny.setFocusPainted(false);
		magny.setBackground(Color.WHITE);
		magny.setToolTipText("Pretrazi profesora");
		
		customHelp.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		customHelp.add(search);
		customHelp.add(magny);
		
		add(customHelp);
		
		addProf.addActionListener(evt -> {
			
			MyDialog dodaj = new MyDialog(parent, "Dodaj Profesora", true,650,675+205-55, "");
			
			ProfesorPanelAdd pp = new ProfesorPanelAdd(dodaj, parent,650,675+205-55);
			dodaj.add(BorderLayout.CENTER, pp);
			dodaj.setResizable(false);
			dodaj.setMinimumSize(new Dimension(650,675+205));
			dodaj.setMaximumSize(new Dimension(650,675+205));
			dodaj.setVisible(true); // mora na kraj
			
			
		});
		
		editProf.addActionListener( evt1 -> { 
			
			if (MyProfesorTable.selected != -1) {
				
				MyDialog izmeni = new MyDialog(parent, "Izmeni Profesora", true,650,675+205-55, "");
			
				ProfesorPanelEdit pp = new ProfesorPanelEdit(izmeni, parent,650,675+205-55);
				izmeni.add(BorderLayout.CENTER, pp);
				izmeni.setResizable(false);
				izmeni.setMinimumSize(new Dimension(650,675+205-55));
				izmeni.setMaximumSize(new Dimension(650,675+205-55));
				izmeni.setVisible(true); // mora na kraj
				
			}
			else {
				
				JOptionPane.showMessageDialog(parent, "Molim odaberite Profesora!");
				
			}
			
		});
		
		removeProf.addActionListener(remove -> {
			
			if (MyProfesorTable.selected != -1) {
				
				MyDialog obrisi = new MyDialog(parent, "Obrisi Profesora", true, 650,300, "");
			
				ProfesorPanelRemove pp = new ProfesorPanelRemove(obrisi, parent, 650, 300);
			
				obrisi.add(BorderLayout.CENTER, pp);
				obrisi.setResizable(false);
				obrisi.setMinimumSize(new Dimension(650,300));
				obrisi.setMaximumSize(new Dimension(650,300));
				obrisi.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(parent, "Molim odaberite Profesora!");		
			}
			
		});
		
	}
	
}
