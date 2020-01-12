package studentskasluzba.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;

import studentskasluzba.controller.PredmetiController;
import studentskasluzba.model.Predmet;

public class MyToolbarPredmet extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8029393810990944532L;

	public static final JButton addStudPr = new JButton();
	public static final JButton removeStudPr = new JButton();
	
	public static final JButton addProfPr = new JButton();
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
		CustomPanel customHelp = new CustomPanel(1440-7*55, 55, Color.WHITE);

		// dodaj, brisi studenta sa predmeta buttoni
		addStudPr.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Avatars\\student_final_add.png"));
		addStudPr.setToolTipText("Dodaj studenta");
		addStudPr.setBorderPainted(true);
		addStudPr.setFocusPainted(false);
		addStudPr.setBorder(new LineBorder(new Color(51, 153, 255, 90)));
		
		removeStudPr.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Avatars\\student_final_remove.png"));
		removeStudPr.setToolTipText("Obrisi studenta");
		removeStudPr.setBorderPainted(true);
		removeStudPr.setFocusPainted(false);
		removeStudPr.setBorder(new LineBorder(new Color(51, 153, 255, 90)));
		
		// dodaj, brisi profesora sa predmeta buttoni
		addProfPr.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Avatars\\teacher_final_add.png"));
		addProfPr.setToolTipText("Dodaj profesora");
		addProfPr.setBorderPainted(true);
		addProfPr.setFocusPainted(false);
		addProfPr.setBorder(new LineBorder(new Color(51, 153, 255, 90)));

		removeProfPr.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Avatars\\teacher_final_remove.png"));
		removeProfPr.setToolTipText("Obrisi profesora");
		removeProfPr.setBorderPainted(true);
		removeProfPr.setFocusPainted(false);
		removeProfPr.setBorder(new LineBorder(new Color(51, 153, 255, 90)));
		
		// dodaj,izmeni,brisi predmet
		addPredmet.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Avatars\\predmet_final_add.png"));
		addPredmet.setToolTipText("Dodaj predmet");
		addPredmet.setBorderPainted(true);
		addPredmet.setFocusPainted(false);
		addPredmet.setBorder(new LineBorder(new Color(51, 153, 255, 90)));

		editPredmet.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Avatars\\predmet_final_edit.png"));
		editPredmet.setToolTipText("Izmeni predmet");
		editPredmet.setBorderPainted(true);
		editPredmet.setFocusPainted(false);
		editPredmet.setBorder(new LineBorder(new Color(51, 153, 255, 90)));
		
		removePredmet.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Avatars\\predmet_final_remove.png"));
		removePredmet.setToolTipText("Obrisi predmet");
		removePredmet.setBorderPainted(true);
		removePredmet.setFocusPainted(false);
		removePredmet.setBorder(new LineBorder(new Color(51, 153, 255, 90)));
		
		//dodavanje na panel
		add(addStudPr);
		add(removeStudPr);
		
		add(addProfPr);
		add(removeProfPr);
		
		add(addPredmet);
		add(editPredmet);
		add(removePredmet);
	
		
		// Searchbar 
		
		JTextField search = new JTextField (30);
		search.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		// template za pretragy predmeta
		search.setText("sifra<>naziv<>");
		JButton magny = new JButton();
		
		magny.setIcon(new ImageIcon("StudentskaSluzba\\images\\search-35x35.png"));
		magny.setBorderPainted(false);
		magny.setFocusPainted(false);
		magny.setBackground(Color.WHITE);
		magny.setToolTipText("Pretrazi predmet");
		
		customHelp.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		customHelp.add(search);
		customHelp.add(magny);
		
		add(customHelp);

		// TODO: Ikone promeniti! 
		
		// dijalozi za dodavanje-editovanje predmeta
		addPredmet.addActionListener(evt -> {
			
			MyDialog addPredmet = new MyDialog(parent, "Dodaj predmet", true, 450, 550,"");	// namerno prazan string, ovaj panel je posebna klasa
			
			PredmetPanelAdd dodajPredmet = new PredmetPanelAdd(addPredmet, parent, 450, 550);
			
			addPredmet.add(BorderLayout.CENTER, dodajPredmet);
			addPredmet.setResizable(false);
			addPredmet.setMinimumSize(new Dimension(450, 550));
			addPredmet.setMaximumSize(new Dimension(450, 550));
			addPredmet.setVisible(true);
			
		});
		
		editPredmet.addActionListener(evt -> {
			// odabrati predmet koji zelimo da izmenimo
			if (MyPredmetTable.selected != -1) {
				
				MyDialog editPredmet = new MyDialog(parent, "Izmeni predmet", true, 450, 550,"");	// namerno prazan string, ovaj panel je posebna klasa
			
				PredmetPanelEdit izmeniPredmet = new PredmetPanelEdit(editPredmet, parent, 450, 550);
			
				editPredmet.add(BorderLayout.CENTER, izmeniPredmet);
				editPredmet.setResizable(false);
				editPredmet.setMinimumSize(new Dimension(450, 550));
				editPredmet.setMaximumSize(new Dimension(450, 550));
				editPredmet.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(parent, "Molim odaberite predmet!");
			}
	
			
		});
		
		removePredmet.addActionListener(evt -> {
			// mora da se odabere predmet koji zelimo obrisati
			if (MyPredmetTable.selected != -1) {
				MyDialog removePredmet = new MyDialog(parent, "Obrisi predmet", true, 650, 300 , "");	// namerno prazan string, ovaj panel je posebna klasa
			
				PredmetPanelRemove brisiPredmet = new PredmetPanelRemove(removePredmet, parent, 650, 300);
			
				removePredmet.add(BorderLayout.CENTER, brisiPredmet);
				removePredmet.setResizable(false);
				removePredmet.setMinimumSize(new Dimension(650, 300));
				removePredmet.setMaximumSize(new Dimension(650, 300));
				removePredmet.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(parent, "Molim odaberite predmet!");
			}
	
		});
		
		removeStudPr.addActionListener(evt -> {
			// dok ne izaberemo predmet ne mozemo obrisati studenta sa njega
			if (MyPredmetTable.selected != -1) {
				MyDialog brisiStudsaPredmeta = new MyDialog(parent, "Obrisi studenta sa predmeta", true, 650, 500, "removeStudentFromPredmet");
				brisiStudsaPredmeta.setResizable(false);
				brisiStudsaPredmeta.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(parent, "Molim odaberite predmet!");
			}
					
		});
				
		addStudPr.addActionListener(evt -> {
			// izabrati prvo na koji predmet zelimo dodati studenta		
			if (MyPredmetTable.selected != -1) {		
				MyDialog dodajStudnaPredmet = new MyDialog(parent, "Dodaj studenta na predmet", true, 650, 500, "addStudentToPredmet");
				dodajStudnaPredmet.setResizable(false);
				dodajStudnaPredmet.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(parent, "Molim odaberite predmet!");
			}
					
		});

		removeProfPr.addActionListener(remove -> {
			// if (nema predmetnog profesora onda dialog)  else poruka o gresci da nema predmetnog profesora	
			
			if (MyPredmetTable.selected != -1) {
				if (PredmetiController.getInstance().getPredmet(MyPredmetTable.pw).getPredProf() != null) {
					MyDialog brisiProfsaPredmeta = new MyDialog(parent, "Obrisi profesora sa predmeta", true, 650, 300, "removeProfesorFromPredmet");
					brisiProfsaPredmeta.setResizable(false);
					brisiProfsaPredmeta.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(parent, "Ovaj predmet nema predmetnog profesora");
				}
					
			}
			else {
				JOptionPane.showMessageDialog(parent, "Molim odaberite predmet!");
			}
		
		});
				
		addProfPr.addActionListener(add -> {
			// dok ne selektujemo predmet - ne mozemo na njega dodati profesora		
			if (MyPredmetTable.selected != -1) {
				
				if (PredmetiController.getInstance().getPredmet(MyPredmetTable.pw).getPredProf() == null) {
					MyDialog dodajProfnaPredmet = new MyDialog(parent, "Dodaj profesora na predmet", true, 650, 500, "addProfesorToPredmet");
					dodajProfnaPredmet.setResizable(false);
					dodajProfnaPredmet.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(parent, "Ovaj predmet vec ima predmetnog profesora");
				}
			}
			else {
				JOptionPane.showMessageDialog(parent, "Molim odaberite predmet!");
			}
					
		});
		
		magny.addActionListener(listen -> {
			
			// substring
			// sifra<NANS1337>naziv<Nans>
			//split by <   ->   sifra, NANS1337>naziv Nans>
			//  
			
				
			String temp = search.getText();
			String[] splits = temp.split("<");
			String sifra, naziv;
			
			sifra = splits[1].substring(0, splits[1].length()-6);
			naziv = splits[2].substring(0, splits[2].length() -1);
			
			sifra = sifra.toLowerCase();
			naziv = naziv.toLowerCase();
			
			ArrayList<Object> objects = PredmetiController.getInstance().findPredmet(sifra, naziv);
			
			for (Object o : objects) {
				Predmet p = (Predmet) o;
				System.out.println(p.getNaziv() + " " + p.getSifra());
				
			}
			
			MyDialog findSubjects = new MyDialog(parent, "Pronadji Predmet" , true, 650, 500, "findSubjects", objects );
			
			findSubjects.setVisible(true);
			
			search.setText("sifra<>naziv<>");
		});
		
		
	}
	
}
