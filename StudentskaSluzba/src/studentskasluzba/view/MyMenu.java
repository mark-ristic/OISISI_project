package studentskasluzba.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

public class MyMenu extends JMenuBar {

	/**
	 * 
	 */

	private static final long serialVersionUID = -1595761791693873356L;
	
	public MyMenu(final JFrame parent ) {
		// radi preglednosti sve ispod mFileide u File prozor itd.. , mneumonike sam radio kao sto smo na vezbama radili 
		JMenu mFile = new JMenu("File"); 
		mFile.setMnemonic(KeyEvent.VK_F);
		
		JMenuItem miNew   = new JMenuItem("New");
		miNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
		miNew.setMnemonic(KeyEvent.VK_N);
		miNew.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\menu_images\\add.png"));
		
		
		JMenuItem miClose = new JMenuItem("Close"); 
		miClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
		miClose.setMnemonic(KeyEvent.VK_C);
		miClose.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\menu_images\\close.png"));
		mFile.add(miNew);
		mFile.add(miClose);
		add(mFile);
		
		// mEdit - "m" ispred stavke znaci da je u pitanju menu , "mi" ispred stavke znaci da je u pitanju menu item
		JMenu mEdit = new JMenu("Edit");
		mEdit.setMnemonic(KeyEvent.VK_E);
		
		JMenuItem miEdit = new JMenuItem("Edit");
		miEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
		miEdit.setMnemonic(KeyEvent.VK_E);
		miEdit.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\menu_images\\edit.png"));
		
		JMenuItem miDelete = new JMenuItem("Delete");
		miDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
		miDelete.setMnemonic(KeyEvent.VK_D);
		miDelete.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\menu_images\\delete.png"));
		
		mEdit.add(miEdit);
		mEdit.add(miDelete);
		add(mEdit);
		
		JMenu mHelp = new JMenu("Help");
		mHelp.setMnemonic(KeyEvent.VK_H);
		
		JMenuItem miHelp = new JMenuItem("Help");
		miHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
		miHelp.setMnemonic(KeyEvent.VK_H);
		miHelp.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\menu_images\\help.png"));
		
		JMenuItem miAbout = new JMenuItem("About");
		miAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
		miAbout.setMnemonic(KeyEvent.VK_A);
		miAbout.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\menu_images\\about.png"));
		
		mHelp.add(miHelp);
		mHelp.add(miAbout);
		add(mHelp);
		
		
		miNew.addActionListener(add -> {
			
			if (MainFrame.tab == 0 ) {
				
				MyDialog dodaj = new MyDialog(parent, "Dodaj Studenta", true,650,675+55,"");
				
				StudentPanelAdd spa = new StudentPanelAdd(dodaj, parent,650,750+55);
				
				dodaj.add(BorderLayout.CENTER, spa);
				dodaj.setResizable(false);
				dodaj.setMinimumSize(new Dimension(650,750+55));
				dodaj.setMaximumSize(new Dimension(650,750+55));
				dodaj.setVisible(true); // mora na kraj	
			}
			
			if (MainFrame.tab == 1) {
				
				MyDialog dodaj = new MyDialog(parent, "Dodaj Profesora", true,650,675+205-55, "");
				
				ProfesorPanelAdd pp = new ProfesorPanelAdd(dodaj, parent,650,675+205-55);
				dodaj.add(BorderLayout.CENTER, pp);
				dodaj.setResizable(false);
				dodaj.setMinimumSize(new Dimension(650,675+205));
				dodaj.setMaximumSize(new Dimension(650,675+205));
				dodaj.setVisible(true); // mora na kraj
				
				
			}
			
			if (MainFrame.tab == 2) {
				
				MyDialog addPredmet = new MyDialog(parent, "Dodaj predmet", true, 450, 550,"");	// namerno prazan string, ovaj panel je posebna klasa
				
				PredmetPanelAdd dodajPredmet = new PredmetPanelAdd(addPredmet, parent, 450, 550);
				
				addPredmet.add(BorderLayout.CENTER, dodajPredmet);
				addPredmet.setResizable(false);
				addPredmet.setMinimumSize(new Dimension(450, 550));
				addPredmet.setMaximumSize(new Dimension(450, 550));
				addPredmet.setVisible(true);
	
			}
			
			
			
		});
		
		miEdit.addActionListener( edit -> {
			
			if (MainFrame.tab == 0 ) {
				
				if (MyStudentTable.selected != -1) {
					MyDialog izmeni = new MyDialog(parent, "Izmeni Studenta", true,650,675+55,"");
				
					StudentPanelEdit spe = new StudentPanelEdit(izmeni, parent,650,750+55);
				
					izmeni.add(BorderLayout.CENTER, spe);
					izmeni.setResizable(false);
					izmeni.setMinimumSize(new Dimension(650,750));
					izmeni.setMaximumSize(new Dimension(650,750));
					izmeni.setVisible(true); // mora na kraj
				}
				else {
					JOptionPane.showMessageDialog(parent, "Molim odaberite studenta!");
				}
				
			}
			
			if (MainFrame.tab == 1) {
				
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
				
			}
			
			if (MainFrame.tab == 2) {
				
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
			}
			
			
		});
		
		miDelete.addActionListener(delete -> {
			
			if (MainFrame.tab == 0) {
				
				if (MyStudentTable.selected != -1) {
					MyDialog obrisi = new MyDialog(parent, "Obrisi Studenta", true, 650,300, "");
				
					StudentPanelRemove spr = new StudentPanelRemove(obrisi, parent, 650,300);
				
					obrisi.add(BorderLayout.CENTER, spr);
					obrisi.setResizable(false);
					obrisi.setMinimumSize(new Dimension(650,300));
					obrisi.setMaximumSize(new Dimension(650,300));
					obrisi.setVisible(true);	
				}
				else {
					JOptionPane.showMessageDialog(parent, "Molim odaberite studenta!");	
				}
			}
			
			if (MainFrame.tab == 1) {
				
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
				
			}
			
			if (MainFrame.tab == 2) {
				
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
				
			}
			
		});
		
		miClose.addActionListener(event -> parent.dispose());

		miHelp.addActionListener( help -> {
			// MyDialog(JFrame parent, String title, boolean modal,int width,int height, String dialogType)
			MyDialog md = new MyDialog(parent, "Help sekcija",  true, 1280, 720, "");
			
			CustomPanel fullpanel = new CustomPanel(1280,720, Color.lightGray);
			
			fullpanel.setLayout(new GridLayout(1,1));
			
			
			
			JLabel label = new JLabel("<html><head></head><body><p style=\"text-align: center;\"><strong><span style=\"font-size: 18px;\">Pomocna dokumentacija za koriscenje programa</span></strong></p>\r\n" + 
					"<p style=\"text-align: center;\">\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p style=\"text-align: center;\">Aplikacija sluzi za upravljanje realnim sistemom Studentske Sluzbe</p>\r\n" + 
					"<p style=\"text-align: center;\">\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p style=\"text-align: left;\">Nas sistem sadrzi tri Entiteta - Student , Profesor i Predmet</p>\r\n" + 
					"<p style=\"text-align: left;\">Svaki entitet pripada odredjenoj bazi entiteta - Postoje tri baze entiteta &nbsp;: &nbsp;</p>\r\n" + 
					"<ol>\r\n" + 
					"  <li style=\"text-align: left;\">Baza Studenata</li>\r\n" + 
					"  <li style=\"text-align: left;\">Baza Profesora</li>\r\n" + 
					"  <li style=\"text-align: left;\">Baza Predmeta</li>\r\n" + 
					"</ol>\r\n" + 
					"<p>Rukovanje ovim entitetima vrsi se preko interfejsa za upravljanje.</p>\r\n" + 
					"<p style=\"text-align: center;\"><strong>INTERFEJS ZA RUKOVANJE ENTITETIMA</strong></p>\r\n" + 
					"<p style=\"text-align: center;\">\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p style=\"text-align: left;\">Kada otvorite aplikaciju docekace vas glavni prozor &nbsp;i na njemu panel za upravljanje studentima</p>\r\n" + 
					"<p style=\"text-align: left;\">Svaki veliki panel sadrzi sledece pod interfejs jedinice :&nbsp;</p>\r\n" + 
					"<p style=\"text-align: left;\">\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p style=\"text-align: left;\">\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p style=\"text-align: left;\">\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<ol>\r\n" + 
					"  <li><strong>Menu bar ----- Klasican menu bar sa stavkama koje su objasnjene ispod&nbsp;</strong></li>\r\n" + 
					"</ol>\r\n" + 
					"<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Akcija &nbsp; &nbsp; &nbsp; &nbsp; Mneumonik akcije &nbsp; &nbsp; &nbsp; &nbsp; Podakcija &nbsp; &nbsp; &nbsp; &nbsp; Mneumonik podakcije &nbsp; &nbsp; &nbsp; &nbsp; Akcelerator Podakcije &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</p>\r\n" + 
					"<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; File &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;F &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;New &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;N &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Alt + Ctrl + N</p>\r\n" + 
					"<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; File &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;F &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;Close &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;C &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;Alt + Ctrl + C</p>\r\n" + 
					"<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Edit &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; E &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;Edit &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; E &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;Alt + Ctrl + E</p>\r\n" + 
					"<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Edit &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; E &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;Delete &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; D &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;Alt + Ctrl + D</p>\r\n" + 
					"<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Help &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;H &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Help &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;H &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;Alt + Ctrl + H</p>\r\n" + 
					"<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Help &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;H &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; About &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;A &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;Alt + Ctrl + A</p>\r\n" + 
					"<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>\r\n" + 
					"<p>New --&gt; Sluzi za dodavanje novog entiteta&nbsp;</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>Close --&gt; Sluzi za gasenje aplikacije</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>Edit --&gt; Sluzi za izmenu entiteta</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>Delete --&gt; Sluzi za brisanje entiteta</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>Ova cetiri stavke menu bar-a sluze za upravljanje entiteta u zavisnosti od trenutno prikazanog panela.</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>Ako je trenutno prikazan panel za upravljanje studentima menu bar stavke se odnose na entitet Student</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p style=\"text-align: left;\">Ako je trenutno prikazan panel za upravljanje profesorima menu bar stavke se odnose na entitet Profesor</p>\r\n" + 
					"<p style=\"text-align: left;\">\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p style=\"text-align: left;\">Ako je trenutno prikazan panel za upravljanje predmetima menu bar stavke se odnose na entitet Predmet</p>\r\n" + 
					"<p>&nbsp; &nbsp; &nbsp;</p>\r\n" + 
					"<p>&nbsp; <strong>&nbsp; &nbsp;2. Toolbar <span style=\"color: rgb(0, 0, 0); font-family: &quot;Times New Roman&quot;; font-size: medium; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: left; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;\">-----</span> sadrzi dugmad za upravljanje Entitetima&nbsp;</strong></p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>Dugmad na sva tri panela sadrze slicne simbole :&nbsp;</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>&nbsp; &nbsp; + &nbsp;---&gt; dodavanje Entiteta</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>&nbsp; &nbsp; pticje pero &nbsp; ----&gt; izmena Entiteta</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>&nbsp; &nbsp;x &nbsp;-----&gt; brisanje Entiteta</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>NAPOMENA: &nbsp;Svaka posebna strana (Prozor za studente, profesore, predmete) sadrzi zasebne toolbar-ove i zasebnu dugmad&nbsp;</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>Lista dugmadi u toolbar-u za upravljanje studentima :&nbsp;</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>&nbsp; &nbsp; &nbsp;Ikona studenta sa simbolom + &nbsp; ----&gt; dodavanje studenta &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;(Mneumonik &nbsp; &nbsp; &nbsp;I - Insert )</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>&nbsp; &nbsp; &nbsp;Ikona studenta sa simbolom pticjeg pera ----&gt; izmena studenta &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; (Mneumonik &nbsp; &nbsp; &nbsp;M - Modify)</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>&nbsp; &nbsp; &nbsp;Ikona studenta sa simbolom X &nbsp; ----&gt; brisanje studenta &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; (Mneumonik &nbsp; &nbsp; &nbsp;R - Remove)</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>Lista dugmadi za upravljanje profesorima :</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>&nbsp; &nbsp; &nbsp;Ikona profesora sa simbolom + &nbsp; ----&gt; dodavanje <span style=\"color: rgb(0, 0, 0); font-family: &quot;Times New Roman&quot;; font-size: medium; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;\">profesora &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; (Mneumonik &nbsp; &nbsp;Alt + &nbsp;I - Insert )</span>&nbsp;</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>&nbsp; &nbsp; &nbsp;Ikona <span style=\"color: rgb(0, 0, 0); font-family: &quot;Times New Roman&quot;; font-size: medium; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;\">profesora</span>&nbsp; sa simbolom pticjeg pera ----&gt; izmena <span style=\"color: rgb(0, 0, 0); font-family: &quot;Times New Roman&quot;; font-size: medium; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;\">profesora &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;(Mneumonik &nbsp; &nbsp; &nbsp;Alt + &nbsp;M - Modify)</span></p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>&nbsp; &nbsp; &nbsp;Ikona <span style=\"color: rgb(0, 0, 0); font-family: &quot;Times New Roman&quot;; font-size: medium; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;\">profesora</span>&nbsp; &nbsp;sa simbolom X &nbsp; ----&gt; brisanje <span style=\"color: rgb(0, 0, 0); font-family: &quot;Times New Roman&quot;; font-size: medium; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;\">profesora<span style=\"color: rgb(0, 0, 0); font-family: &quot;Times New Roman&quot;; font-size: medium; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; (Mneumonik &nbsp; &nbsp; &nbsp;Alt + &nbsp;R - Remove)</span>&nbsp;</span></p>\r\n" + 
					"<p>&nbsp; &nbsp;&nbsp;</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>Lista dugmadi za upravljanje predmetima :</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>&nbsp; &nbsp; &nbsp;Ikona profesora sa simbolom + &nbsp; ----&gt; dodavanje <span style=\"color: rgb(0, 0, 0); font-family: &quot;Times New Roman&quot;; font-size: medium; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;\">profesora</span> na predmet<span style=\"color: rgb(0, 0, 0); font-family: &quot;Times New Roman&quot;; font-size: medium; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; (Mneumonik &nbsp; &nbsp; &nbsp;Alt + &nbsp;P )</span>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>\r\n" + 
					"<p>&nbsp; &nbsp; &nbsp;Ikona <span style=\"color: rgb(0, 0, 0); font-family: &quot;Times New Roman&quot;; font-size: medium; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;\">profesora</span>&nbsp; &nbsp;sa simbolom X &nbsp; ----&gt; brisanje <span style=\"color: rgb(0, 0, 0); font-family: &quot;Times New Roman&quot;; font-size: medium; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;\">profesora sa predmeta<span style=\"color: rgb(0, 0, 0); font-family: &quot;Times New Roman&quot;; font-size: medium; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; (Mneumonik &nbsp; &nbsp; &nbsp;Alt + &nbsp;B )</span>&nbsp; &nbsp;</span></p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>&nbsp; &nbsp;&nbsp;</p>\r\n" + 
					"<p>&nbsp; &nbsp; Ikona studenta sa simbolom + &nbsp; ----&gt; dodavanje studenta na predmet &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;(Mneumonik &nbsp; &nbsp; &nbsp;Alt + &nbsp;S )</p>\r\n" + 
					"<p>&nbsp; &nbsp; Ikona studenta sa simbolom X &nbsp; ----&gt; brisanje studenta sa predmet &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;(Mneumonik &nbsp; &nbsp; &nbsp;Alt +&nbsp; J )&nbsp;</p>\r\n" + 
					"<p style=\"text-align: left;\">\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p style=\"text-align: left;\">\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p style=\"text-align: left;\">&nbsp; &nbsp; &nbsp;Ikona predmeta sa simbolom + &nbsp; ----&gt; &nbsp; dodavanje predmeta &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;(Mneumonik &nbsp; &nbsp; &nbsp;Alt + &nbsp;I - Insert )</p>\r\n" + 
					"<p>&nbsp; &nbsp; &nbsp;Ikona predmeta &nbsp;sa simbolom pticjeg pera ----&gt; izmena predmeta &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;(Mneumonik &nbsp; &nbsp; &nbsp;Alt + &nbsp;M - Modify )</p>\r\n" + 
					"<p>&nbsp; &nbsp; &nbsp;Ikona predmeta &nbsp; sa simbolom X &nbsp; ----&gt; brisanje predmeta &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;(Mneumonik &nbsp; &nbsp; &nbsp;Alt + &nbsp;R - Remove )</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p><strong>3. Status Bar ---- Sadrzi naziv aplikacije , trenutno vreme i danasnji datum</strong></p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p><strong>4. Ta</strong><strong>bela entit</strong><strong>eta &nbsp;---- Sadrzi podatke o jednoj vrsti entiteta (Student, Predmet, Profesor)&nbsp;</strong></p>\r\n" + 
					"<p>&nbsp;</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>Tabela student sadrzi sledece kolone : &nbsp;&nbsp;</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<ol>\r\n" + 
					"  <li>ime</li>\r\n" + 
					"  <li>prezime</li>\r\n" + 
					"  <li>datum rodjenja</li>\r\n" + 
					"  <li>adresa stanovanja</li>\r\n" + 
					"  <li>kontakt telefon</li>\r\n" + 
					"  <li>email adresa</li>\r\n" + 
					"  <li>indeks</li>\r\n" + 
					"  <li>datum upisa</li>\r\n" + 
					"  <li>godina studiranja</li>\r\n" + 
					"  <li>status studenta</li>\r\n" + 
					"  <li>prosek prosek studenta</li>\r\n" + 
					"  <li>spisak predmeta</li>\r\n" + 
					"</ol>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>Tabela profesor sadrzi sledece kolone :</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<ol>\r\n" + 
					"  <li>ime</li>\r\n" + 
					"  <li>prezime</li>\r\n" + 
					"  <li>datum rodjenja</li>\r\n" + 
					"  <li>adresa stanovanja</li>\r\n" + 
					"  <li>kontakt telefon</li>\r\n" + 
					"  <li>email adresa</li>\r\n" + 
					"  <li>adresa kancelarije</li>\r\n" + 
					"  <li>broj licne karte</li>\r\n" + 
					"  <li>titula</li>\r\n" + 
					"  <li>zvanje&nbsp;</li>\r\n" + 
					"  <li>spisak predmeta</li>\r\n" + 
					"</ol>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>Tabela predmet sadrzi sledece kolone :&nbsp;</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<ol>\r\n" + 
					"  <li>sifra</li>\r\n" + 
					"  <li>naziv</li>\r\n" + 
					"  <li>semestar</li>\r\n" + 
					"  <li>godina</li>\r\n" + 
					"  <li>predmetni profesor</li>\r\n" + 
					"  <li>spisak studenata</li>\r\n" + 
					"</ol>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>Koriscenjem misa i klikom na bilo koji od redova u tabeli , selektujemo entitet (Studenta, Profesora ili Predmet) koji zelimo da menjamo</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>Kako bismo dodali novi Entitet koristimo &nbsp;ikonu za dodavanje entiteta (slika Entiteta i znak plus)&nbsp;</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>Kako bismo izmenili postojeci entitet potrebno je da selektujemo entitet iz tabele (klikom misa) i kliknemo na ikonu za izmenu entiteta (slika entiteta i znak pticjeg pera)</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p>Kako bismo obrisali postojeci entitet potrebno je da selektujemo entitet iz tabele (klikom misa) i kliknemo na ikonu za brisanje entiteta (slika entiteta i znak X)</p>\r\n" + 
					"<p>\r\n" + 
					"  <br>\r\n" + 
					"</p></body></html>");
			
			JScrollPane pane = new JScrollPane(label);
			md.setLayout(new GridLayout(1,1));
			fullpanel.add(pane);
		
			md.add(fullpanel);
			md.setVisible(true);
			
		});
		
		miAbout.addActionListener(about -> {
			
			MyDialog md = new MyDialog(parent, "About sekcija",  true, 1280, 720, "");
			
			CustomPanel fullpanel = new CustomPanel(1280,720, Color.white);
			
			fullpanel.setLayout(new GridLayout(1,1));
			md.add(fullpanel);
			
			JLabel label = new JLabel("<html><head></head><body><p style=\"text-align: center;\"><span style=\"font-size: 24px;\"><strong>ABOUT US - O NAMA I APLIKACIJI</strong></span></p>\r\n" + 
					"<p style=\"text-align: left;\">Verzija aplikacije : &nbsp;v2.020</p>\r\n" + 
					"<p style=\"text-align: center;\"><span style=\"font-size: 18px;\"><strong>Informacije o Aplikaciji</strong></span></p>\r\n" + 
					"<div style=\"text-align: left;\">\r\n" + 
					"  <br>\r\n" + 
					"</div>\r\n" + 
					"<p>Ova aplikacija je namenjena za fakultetske ustanove i omogucava im rad sa entitetima koji cine fakultet.</p>\r\n" + 
					"<p>Sta ova aplikacija omogucava ?&nbsp;</p>\r\n" + 
					"<ol>\r\n" + 
					"  <li>Dodavanje studenata</li>\r\n" + 
					"  <li>Izmenu studenata</li>\r\n" + 
					"  <li>Brisanje studenata</li>\r\n" + 
					"  <li>Dodavanje profesora</li>\r\n" + 
					"  <li>Izmenu profesora</li>\r\n" + 
					"  <li>Brisanje profesora</li>\r\n" + 
					"  <li>Dodavanje predmeta</li>\r\n" + 
					"  <li>Dodavanje studenata na predmet</li>\r\n" + 
					"  <li>Dodavanje profesora na predmet</li>\r\n" + 
					"  <li>Uklanjanje studenta sa predmeta</li>\r\n" + 
					"  <li>Uklanjanje profesora sa predmeta</li>\r\n" + 
					"  <li>Izmenu predmeta</li>\r\n" + 
					"  <li>Brisanje predmeta</li>\r\n" + 
					"  <li>Pracenje proseka studenata&nbsp;</li>\r\n" + 
					"  <li>Pracenje predmeta profesora</li>\r\n" + 
					"  <li>Pracenje predmeta studenata</li>\r\n" + 
					"</ol>\r\n" + 
					"<p style=\"text-align: left;\">\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p style=\"text-align: left;\">Autori aplikacije : <strong>Nikola Tomik</strong> i <strong>Mark Ristic&nbsp;</strong></p>\r\n" + 
					"<p style=\"text-align: left;\">\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p style=\"text-align: left;\"><strong>O AUTORIMA:</strong></p>\r\n" + 
					"<p style=\"text-align: left;\">-- Nikola Tomik &nbsp;- Rodjen 10.04.1998. u Backoj Topoli, zavrsio opstu gimnaziju u Backoj Topoli i upisao Fakultet Tehnickih Nauka 2017. godine. Trenutno uspesno studira i pohadja trecu godinu fakulteta na smeru Racunarstvo i Automatika.</p>\r\n" + 
					"<p style=\"text-align: left;\">\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p style=\"text-align: left;\">-- Mark Ristic - Rodjen 26.07.1998. takodje u Backoj Topoli, zavrsio opstu gimnaziju u Backoj Topoli i upisao Fakultet Tehnickih Nauka 2017. godine. Trenutno uspesno studira i pohadja trecu godinu fakulteta na smeru Racunarstvo i Automatika.</p>\r\n" + 
					"<p style=\"text-align: left;\">\r\n" + 
					"  <br>\r\n" + 
					"</p>\r\n" + 
					"<p style=\"text-align: left;\">C for Copyright , All rights reserved&nbsp;</p>\r\n" + 
					"<p style=\"text-align: left;\">\r\n" + 
					"  <br>\r\n" + 
					"</p></body></html>");
			
			
			JScrollPane pane = new JScrollPane(label);
			fullpanel.add(pane);
			md.setVisible(true);
			
			
		});
		
		
		
		
	}
		
	
}
