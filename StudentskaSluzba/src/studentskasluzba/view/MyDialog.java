package studentskasluzba.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import studentskasluzba.controller.PredmetiController;
import studentskasluzba.controller.ProfesoriController;
import studentskasluzba.controller.StudentiController;
import studentskasluzba.model.BazaPredmeta;
import studentskasluzba.model.BazaProfesora;
import studentskasluzba.model.Predmet;
import studentskasluzba.model.Profesor;
import studentskasluzba.model.Student;



public class MyDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3919027807831445056L;

	MyDialog(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
	
		setSize(850,450);
		setLocationRelativeTo(parent);
		
	}
	
	MyDialog(JFrame parent, String title, boolean modal, int width, int height, String dialogType, ArrayList<Object> list) {
		
		super(parent, title, modal);
		setSize(width,height);
		setLocationRelativeTo(parent);
		
		if (dialogType.equals("findStudents"))
			findStudents(list);
		
		if (dialogType.equals("findProfesors"))
			findProfesors(list);
		
		if (dialogType.equals("findSubjects"))
			findSubjects(list);
		
	}
	
	
	MyDialog(JFrame parent, String title, boolean modal,int width,int height, String dialogType) {
		
		super(parent, title, modal);
		
		setSize(width,height);
		setLocationRelativeTo(parent);
		
		if(dialogType.equals("removeStudentFromPredmet")) {
			removeStudentFromPredmet();
		}

		if(dialogType.equals("addStudentToPredmet")) {
			addStudentToPredmet();
		}
		
		if(dialogType.equals("addProfesorToPredmet")) {
			addProfesorToPredmet(PredmetTab.myPredmetTable.getMyDefaultPredmetTable());
		}

		if(dialogType.equals("removeProfesorFromPredmet")) {
			removeProfesorFromPredmet(PredmetTab.myPredmetTable.getMyDefaultPredmetTable());
		}
		// prikaz studenata na selektovanom predmetu
		if(dialogType.equals("showStudents")) {
			showStudents();
		}
		// prikaz predmeta selektovanog studenta
		if(dialogType.equals("showSubjects"))
			showSubjects(); 
		// prikaz predmeta selektovanog profesora
		if(dialogType.equals("showSubjectsForProfs"))
			showSubjectsForProfs();

		
		
	}
	
	private void showStudents() {
		
		if (MyPredmetTable.pw == null) {
			JOptionPane.showMessageDialog(null, "Molim vas selektuje Predmet");
			return;
		}
		
		if (PredmetiController.getInstance().getPredmet(MyPredmetTable.pw).getStudenti().size() == 0) {
			JOptionPane.showMessageDialog(null, "Ne postoje Studenti koji slusaju ovaj predmet");
			return;
		}
			
		
		CustomPanel upperLR = new CustomPanel(300,450, Color.white);
		CustomPanel upperT = new CustomPanel(270,450, Color.white);
		upperLR.setLayout(new BoxLayout(upperLR, BoxLayout.X_AXIS));
		upperLR.add(new CustomPanel(15,450, Color.WHITE));
		upperLR.add(upperT);
		upperLR.add(new CustomPanel(15,450, Color.white));
		CustomPanel upper = new CustomPanel(240+30,420, Color.white); // na ovo bacamo sve
		upperT.setLayout(new BoxLayout(upperT, BoxLayout.Y_AXIS));
		upperT.add(new CustomPanel(240+30, 30, Color.white));
		upperT.add(upper);
		
		CustomPanel lower_full = new CustomPanel(300,30, Color.white);
		
		
		CustomPanel full = new CustomPanel(300,950, Color.white);
		
		
		full.setLayout(new BoxLayout(full, BoxLayout.Y_AXIS));
		full.add(upperLR);
		full.add(lower_full);
		
		String[] kolona = {"STUDENTI NA PREDMETU"};
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(kolona);
		
		JTable studentTable = new JTable() {
		 /**
			 * 
			 */
			private static final long serialVersionUID = -3616267117240262552L;

		public boolean isCellEditable(int row, int column) { 
			return false; 
			} 
		 };
		 
		 studentTable.setModel(dtm);
		 TableColumnModel cm = studentTable.getColumnModel();
		 studentTable.setRowHeight(30);
		 
		 for(Student s : PredmetiController.getInstance().getPredmet(MyPredmetTable.pw).getStudenti()) {
			 
			 Vector<String> red = new Vector<>();
			 red.add(s.getIme() + " " + s.getPrezime() + " (" + s.getIndeks() + ")");
			 dtm.addRow(red);
 
		 }
		 
		 JScrollPane pane = new JScrollPane(studentTable);
		 pane.setPreferredSize(new Dimension(270,420));
		 upper.setLayout(new GridLayout(1,1));
		 upper.add(pane);
		 
		
		
		this.add(BorderLayout.CENTER, full);
		this.setVisible(true);
		 
	}
	
	private void showSubjects() {
		if (MyStudentTable.indexGlobal == null) {
			JOptionPane.showMessageDialog(null, "Molim vas selektuje Studenta");
			return;
		}
		
		if (StudentiController.getInstance().getStudent(MyStudentTable.indexGlobal).getPredmeti().size() == 0) {
			JOptionPane.showMessageDialog(null, "Student ne slusa nijedan predmet");
			return;
		}
			
		
		CustomPanel upperLR = new CustomPanel(300,450, Color.white);
		CustomPanel upperT = new CustomPanel(270,450, Color.white);
		upperLR.setLayout(new BoxLayout(upperLR, BoxLayout.X_AXIS));
		upperLR.add(new CustomPanel(15,450, Color.WHITE));
		upperLR.add(upperT);
		upperLR.add(new CustomPanel(15,450, Color.white));
		CustomPanel upper = new CustomPanel(240+30,420, Color.white); // na ovo bacamo sve
		upperT.setLayout(new BoxLayout(upperT, BoxLayout.Y_AXIS));
		upperT.add(new CustomPanel(240+30, 30, Color.white));
		upperT.add(upper);
		
		CustomPanel lower_full = new CustomPanel(300,30, Color.white);
		
		
		CustomPanel full = new CustomPanel(300,950, Color.white);
		
		
		full.setLayout(new BoxLayout(full, BoxLayout.Y_AXIS));
		full.add(upperLR);
		full.add(lower_full);
		
		String[] kolona = {"SPISAK PREDMETA STUDENTA"};
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(kolona);
		
		JTable predTable = new JTable() {
		 /**
			 * 
			 */
			private static final long serialVersionUID = -3616267117240262552L;

		public boolean isCellEditable(int row, int column) { 
			return false; 
			} 
		 };
		 
		 predTable.setModel(dtm);
		 TableColumnModel cm = predTable.getColumnModel();
		 predTable.setRowHeight(30);
		 
		 for(Predmet p : StudentiController.getInstance().getStudent(MyStudentTable.indexGlobal).getPredmeti()) {
			 
			 Vector<String> red = new Vector<>();
			 red.add(p.getNaziv() + " (" + p.getSifra() + ")");
			 dtm.addRow(red);
 
		 }
		 
		 JScrollPane pane = new JScrollPane(predTable);
		 pane.setPreferredSize(new Dimension(270,420));
		 upper.setLayout(new GridLayout(1,1));
		 upper.add(pane);
		 
		
		
		this.add(BorderLayout.CENTER, full);
		this.setVisible(true);
		
	}
	
	private void showSubjectsForProfs() {
		
		if (MyProfesorTable.brojLKGlobal == null) {
			JOptionPane.showMessageDialog(null, "Molim vas selektujte Profesora");
			return;
		}
		
		if (ProfesoriController.getInstance().getProfesor(MyProfesorTable.brojLKGlobal).getPredmeti().size() == 0) {
			JOptionPane.showMessageDialog(null, "Profesor ne predaje nijedan predmet");
			return;
		}
			
		
		CustomPanel upperLR = new CustomPanel(300,450, Color.white);
		CustomPanel upperT = new CustomPanel(270,450, Color.white);
		upperLR.setLayout(new BoxLayout(upperLR, BoxLayout.X_AXIS));
		upperLR.add(new CustomPanel(15,450, Color.WHITE));
		upperLR.add(upperT);
		upperLR.add(new CustomPanel(15,450, Color.white));
		CustomPanel upper = new CustomPanel(240+30,420, Color.white); // na ovo bacamo sve
		upperT.setLayout(new BoxLayout(upperT, BoxLayout.Y_AXIS));
		upperT.add(new CustomPanel(240+30, 30, Color.white));
		upperT.add(upper);
		
		CustomPanel lower_full = new CustomPanel(300,30, Color.white);
		
		
		CustomPanel full = new CustomPanel(300,950, Color.white);
		
		
		full.setLayout(new BoxLayout(full, BoxLayout.Y_AXIS));
		full.add(upperLR);
		full.add(lower_full);
		
		String[] kolona = {"SPISAK PREDMETA PROFESORA"};
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(kolona);
		
		JTable predTable = new JTable() {
		 /**
			 * 
			 */
			private static final long serialVersionUID = -3616267117240262552L;

		public boolean isCellEditable(int row, int column) { 
			return false; 
			} 
		 };
		 
		 predTable.setModel(dtm);
		 TableColumnModel cm = predTable.getColumnModel();
		 predTable.setRowHeight(30);
		 
		 System.out.println("KOLIKO PREDMETA IMA U BAZI ??  " + PredmetiController.getInstance().getPredmeti().size());
		 System.out.println("BROJLKGLOBAL IS WHAT  " + MyProfesorTable.brojLKGlobal);
		 System.out.println("------------------------------------------");
		 
		 for(Predmet p : ProfesoriController.getInstance().getProfesor(MyProfesorTable.brojLKGlobal).getPredmeti()) {
			 Vector<String> red = new Vector<>();
			 
			 if (p.getPredProf() == null) {
				 System.out.println("DID I CONTINUE? ");
				 continue;
			 }
			 red.add(p.getNaziv() + " (" + p.getSifra() + ")");
			 //if (p.getPredProf().getBrojLK().equals(MyProfesorTable.brojLKGlobal)) {
			 	dtm.addRow(red);
			 //	System.out.println("I FOUND A SUBJECT");
			 //}
		 }
		 
		 JScrollPane pane = new JScrollPane(predTable);
		 pane.setPreferredSize(new Dimension(270,420));
		 upper.setLayout(new GridLayout(1,1));
		 upper.add(pane);
		 
		
		
		this.add(BorderLayout.CENTER, full);
		this.setVisible(true);
	}
	
	private void findStudents(ArrayList<Object> list) {
		
		
		
		if (list.size() == 1) {
			
			Student s = (Student) list.get(0);
			String indeks = s.getIndeks();
			
			findExactlyOne(indeks);
			return;
		}
		
		CustomPanel top_inset = new CustomPanel(650, 50, Color.WHITE);
		CustomPanel bot_inset = new CustomPanel(650,60, Color.WHITE);
		CustomPanel panel = new CustomPanel(650,390, Color.WHITE);
		
		bot_inset.setLayout(new BoxLayout(bot_inset, BoxLayout.Y_AXIS));
		bot_inset.add(new CustomPanel(650,60,Color.WHITE));
	
		this.add(BorderLayout.NORTH, top_inset);
		this.add(BorderLayout.CENTER, panel);
		this.add(BorderLayout.SOUTH, bot_inset);
		
		JLabel question = new JLabel("Broj indeksa studenta: ");
		JTextField indeks = new JTextField(20);
		
		CustomPanel paneltop = new CustomPanel(650,95, Color.WHITE);
		CustomPanel tablepanel = new CustomPanel(650, 200, Color.WHITE);
		CustomPanel panelbot = new CustomPanel(650, 95, Color.WHITE); 
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(paneltop);
		panel.add(tablepanel);
		panel.add(panelbot);
		
		paneltop.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
		paneltop.add(question);
		paneltop.add(indeks);
		
		JButton prikazi = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\prikazi.png"));
		JButton odustani = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		
		prikazi.setFocusPainted(false);
		prikazi.setBorderPainted(false);				
		prikazi.setOpaque(false);
		prikazi.setContentAreaFilled(false);
		odustani.setFocusPainted(false);
		odustani.setBorderPainted(false);
		odustani.setOpaque(false);
		odustani.setContentAreaFilled(false);
		
		panelbot.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
		
		panelbot.add(prikazi);
		panelbot.add(odustani);
		
		// Tabela u tablepanel
		
		String[] kolona = {"Studenti"};
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(kolona);
		
		JTable studTable = new JTable() {
		 /**
			 * 
			 */
			private static final long serialVersionUID = -3616267117240262552L;

		public boolean isCellEditable(int row, int column) { 
			return false; 
			} 
		 };
		 
		 studTable.setModel(dtm);
		 TableColumnModel cm = studTable.getColumnModel();
		 studTable.setRowHeight(30);
		 
		 JScrollPane scrollPane = new JScrollPane(studTable);
		 scrollPane.setPreferredSize(new Dimension(350,160));
		 scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		 
		 tablepanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		 tablepanel.add(scrollPane);
		 
		 for (Object o : list ) {
			  
			 Student s = (Student) o;
					 
			 Vector<String> vek = new Vector<String>();
			 vek.add(s.getIme() + " " + s.getPrezime() + " " + s.getIndeks());
			 dtm.addRow(vek);
		 }
	
		 studTable.addMouseListener(new MouseAdapter() { 
		 
		 public void mouseClicked(MouseEvent e) {
			 
			 // Luka Jovanovic RA 1/2019
			 //   0       1     2    3  
			 
			 String string = (String) studTable.getValueAt(studTable.getSelectedRow(), 0);
			 String[] strings = string.split(" ");
			 String index = strings[2] + " " + strings[3];
			 indeks.setText(index);
	 
		 }
		 
		 } );
		 
		 prikazi.addActionListener(add -> { 
			 //index = indeks.getText();
			 String indexZZ = indeks.getText();
			 
			 if (indexZZ.length() == 0) 
				 return;
			 
			 if (StudentiController.getInstance().getStudent(indexZZ) == null) {
				
				 System.out.println("NE POSTOJI STUDENT SA TIM INDEKSOM");
				 //this.dispose();
				 return;
			 }
			 this.remove(bot_inset);
			 this.remove(top_inset);
			 this.remove(panel);
			 this.setPreferredSize(new Dimension(300,950));
			 findExactlyOne(indexZZ); 
			 
			 
		 });
		 
		//odustani.addActionListener(evt -> this.dispose());
		 
		
		 prikazi.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	prikazi.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\prikazi.png"));
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	prikazi.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\prikazi_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	prikazi.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\prikazi.png"));
		    }
		});
		
		odustani.addActionListener(close -> this.dispose());
		
		odustani.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	//this.dispose();
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		    }
		});
		
	}
	
	private void findExactlyOne(String indeksXX) {
		
		/* "ime",
		"prezime",
		"datum rodjenja",
		"adresa stanovanja",
		"kontakt telefon",
		"email adresa",
		"indeks",
		"datum upisa",
		"godina studiranja",
		"status",
		"prosek",
		"Spisak predmeta" */

		JLabel imelab =        new JLabel("    Ime:    ");
		JLabel prezimelab =    new JLabel("    Prezime:    ");
		JLabel datRodjlab =    new JLabel("    Datum rodjenja:    ");
		JLabel adresalab =     new JLabel("    Adresa stanovanja:    ");
		JLabel brtellab =      new JLabel("    Broj telefona:    ");
		JLabel indekslab =     new JLabel("    Broj indeksa:    ");
		JLabel trenGodlab =    new JLabel("    Trenutna godina studija:   ");
		JLabel emaillab =      new JLabel("    E-mail adresa:   ");
		JLabel datumUpisalab = new JLabel("    Datum upisa:   ");
		JLabel proseklab =     new JLabel("    Prosek:   ");
		JLabel statuslab =     new JLabel("    Status:   ");
	
		Student s = StudentiController.getInstance().getStudent(indeksXX);
		
		JTextField imetxt = new JTextField(50);
		imetxt.setText(s.getIme());
		JTextField prezimetxt = new JTextField(50);
		prezimetxt.setText(s.getPrezime());
		JTextField datRodjtxt = new JTextField(50);
		datRodjtxt.setText(StudentiController.getInstance().parseDate(s.getDatRodj()));
		JTextField adresatxt = new JTextField(50);
		adresatxt.setText(s.getAdresaStanovanja());
		JTextField brteltxt = new JTextField(50);
		brteltxt.setText(s.getKontaktTel());
		JTextField indekstxt = new JTextField(50);
		indekstxt.setText(s.getIndeks());
		JTextField trenGodtxt = new JTextField(50);
		trenGodtxt.setText(Integer.toString(s.getGodStud()));
		JTextField emailtxt = new JTextField(50);
		emailtxt.setText(s.getEmail());
		JTextField datumUpisatxt = new JTextField(50);
		datumUpisatxt.setText(StudentiController.getInstance().parseDate(s.getDatumUpisa()));
		JTextField prosektxt = new JTextField(50);
		prosektxt.setText(Double.toString(s.getProsek()));
		JTextField statustxt = new JTextField(50);
		statustxt.setText(s.getStatus().toString());
	
		Vector<JTextField> textFields = new Vector<JTextField>();
		textFields.add(imetxt);
		textFields.add(prezimetxt);
		textFields.add(datRodjtxt);
		textFields.add(adresatxt);
		textFields.add(brteltxt);
		textFields.add(indekstxt);
		textFields.add(trenGodtxt);
		textFields.add(emailtxt);
		textFields.add(datumUpisatxt);
		textFields.add(prosektxt);
		textFields.add(statustxt);
	
		for (JTextField tf : textFields)
			tf.setEditable(false);
	
		CustomPanel ime = new CustomPanel (635, 40, Color.WHITE);
		CustomPanel prezime = new CustomPanel(635,40, Color.WHITE);
		CustomPanel datRodj = new CustomPanel(635,40, Color.WHITE);
		CustomPanel adresa = new CustomPanel(635,40, Color.WHITE);
		CustomPanel brtel = new CustomPanel(635,40, Color.WHITE);
		CustomPanel indeks = new CustomPanel(635,40, Color.WHITE);
		CustomPanel trenGod = new CustomPanel(635,40, Color.WHITE);
		CustomPanel email = new CustomPanel(635,40, Color.WHITE);
		CustomPanel datumUpisa = new CustomPanel(635,40, Color.WHITE);
		CustomPanel prosek = new CustomPanel(635,40, Color.WHITE);
		CustomPanel status = new CustomPanel(635,40, Color.WHITE);
	
		Vector<CustomPanel> fieldPanels = new Vector<CustomPanel>();
		fieldPanels.add(ime);
		fieldPanels.add(prezime);
		fieldPanels.add(datRodj);
		fieldPanels.add(adresa);
		fieldPanels.add(brtel);
		fieldPanels.add(indeks);
		fieldPanels.add(trenGod);
		fieldPanels.add(email);
		fieldPanels.add(datumUpisa);
		fieldPanels.add(prosek);
		fieldPanels.add(status);
	
		for (CustomPanel cp : fieldPanels)
		cp.setLayout(new BoxLayout(cp, BoxLayout.X_AXIS));
	
		CustomPanel upperLR = new CustomPanel(300,450, Color.WHITE);
		CustomPanel upperT = new CustomPanel(270,450, Color.WHITE);
		upperLR.setLayout(new BoxLayout(upperLR, BoxLayout.X_AXIS));
		upperLR.add(new CustomPanel(15,450, Color.WHITE));
		upperLR.add(upperT);
		upperLR.add(new CustomPanel(15,450, Color.WHITE));
		CustomPanel upper = new CustomPanel(240+30,420, Color.WHITE); // na ovo bacamo sve
			upperT.setLayout(new BoxLayout(upperT, BoxLayout.Y_AXIS));
		upperT.add(new CustomPanel(240+30, 30, Color.WHITE));
		upperT.add(upper);
	
		CustomPanel lower_full = new CustomPanel(300,150, Color.WHITE);
	
	
		CustomPanel full = new CustomPanel(300,950, Color.WHITE);
	
	
		upper.setLayout(new GridLayout(11,1,0,5));
		upper.add(ime);
		upper.add(prezime);
		upper.add(datRodj);
		upper.add(adresa);
		upper.add(brtel);
		upper.add(indeks);
		upper.add(trenGod);
		upper.add(email);
		upper.add(datumUpisa);
		upper.add(prosek);
		upper.add(status);
		
		ime.add(imelab);
		ime.add(imetxt);
		prezime.add(prezimelab);
		prezime.add(prezimetxt);
		datRodj.add(datRodjlab);
		datRodj.add(datRodjtxt);
		adresa.add(adresalab);
		adresa.add(adresatxt);
		brtel.add(brtellab);
		brtel.add(brteltxt);
		indeks.add(indekslab);
		indeks.add(indekstxt);
		trenGod.add(trenGodlab);
		trenGod.add(trenGodtxt);
		email.add(emaillab);
		email.add(emailtxt);
		datumUpisa.add(datumUpisalab);
		datumUpisa.add(datumUpisatxt);
		prosek.add(proseklab);
		prosek.add(prosektxt);
		status.add(statuslab);
		status.add(statustxt);
	
	
		full.setLayout(new BoxLayout(full, BoxLayout.Y_AXIS));
		full.add(upperLR);
		full.add(lower_full);
	
		this.add(BorderLayout.CENTER, full);
		this.setVisible(true);
	}
		
		
	
	private void findSubjects(ArrayList<Object> list) {
		
		CustomPanel top_inset = new CustomPanel(650, 50, Color.WHITE);
		CustomPanel bot_inset = new CustomPanel(650,60, Color.WHITE);
		CustomPanel panel = new CustomPanel(650,390, Color.WHITE);
		
		bot_inset.setLayout(new BoxLayout(bot_inset, BoxLayout.Y_AXIS));
		bot_inset.add(new CustomPanel(650,60,Color.WHITE));
	
		this.add(BorderLayout.NORTH, top_inset);
		this.add(BorderLayout.CENTER, panel);
		this.add(BorderLayout.SOUTH, bot_inset);
		
		JLabel question = new JLabel("Sifra Predmeta : ");
		JTextField indeks = new JTextField(20);
		
		CustomPanel paneltop = new CustomPanel(650,95, Color.WHITE);
		CustomPanel tablepanel = new CustomPanel(650, 200, Color.WHITE);
		CustomPanel panelbot = new CustomPanel(650, 95, Color.WHITE); 
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(paneltop);
		panel.add(tablepanel);
		panel.add(panelbot);
		
		paneltop.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
		paneltop.add(question);
		paneltop.add(indeks);
		
		JButton prikazi = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\prikazi.png"));
		JButton odustani = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		
		prikazi.setFocusPainted(false);
		prikazi.setBorderPainted(false);				
		prikazi.setOpaque(false);
		prikazi.setContentAreaFilled(false);
		odustani.setFocusPainted(false);
		odustani.setBorderPainted(false);
		odustani.setOpaque(false);
		odustani.setContentAreaFilled(false);
		
		panelbot.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
		
		panelbot.add(prikazi);
		panelbot.add(odustani);
		
		// Tabela u tablepanel
		
		String[] kolona = {"Predmeti"};
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(kolona);
		
		JTable predTable = new JTable() {
		 /**
			 * 
			 */
			private static final long serialVersionUID = -3616267117240262552L;

		public boolean isCellEditable(int row, int column) { 
			return false; 
			} 
		 };
		 
		 predTable.setModel(dtm);
		 TableColumnModel cm = predTable.getColumnModel();
		 predTable.setRowHeight(30);
		 
		 JScrollPane scrollPane = new JScrollPane(predTable);
		 scrollPane.setPreferredSize(new Dimension(350,160));
		 scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		 
		 tablepanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		 tablepanel.add(scrollPane);
		 
		 for (Object o : list ) {
			  
			 Predmet p = (Predmet) o;
					 
			 Vector<String> vek = new Vector<String>();
			 vek.add(p.getNaziv() + " " + p.getSifra());
			 dtm.addRow(vek);
		 }
	
		 predTable.addMouseListener(new MouseAdapter() { 
		 
		 public void mouseClicked(MouseEvent e) {
			 
			 // Luka Jovanovic RA 1/2019
			 //   0       1     2    3  
			 
			 String string = (String) predTable.getValueAt(predTable.getSelectedRow(), 0);
			 String[] strings = string.split(" ");
			 String index = strings[strings.length - 1];
			 indeks.setText(index);
	 
		 }
		 
		 } );
		 
		 prikazi.addActionListener(add -> { 
			 //index = indeks.getText();
			 String indexZZ = indeks.getText();
			 
			 if (indexZZ.length() == 0) 
				 return;
			 
			 if (PredmetiController.getInstance().getPredmet(indexZZ) == null) {
				
				 System.out.println("NE POSTOJI PREDMET SA TOM SIFROM");
				 //this.dispose();
				 return;
			 }
			 
			 Predmet predmet = PredmetiController.getInstance().getPredmet(indexZZ);
			 
			 this.remove(bot_inset);
			 this.remove(top_inset);
			 this.remove(panel);
			 this.setSize(300,500);
			 
			 this.setPreferredSize(new Dimension(300,500));
			 this.dispose();
			 // JFrame parent, String title, boolean modal,int width,int height, String dialogType
			 MyDialog md = new MyDialog(null, "Pronadji predmet", true,  450,250, ""); 
			 
			 CustomPanel full_pane = new CustomPanel(650,500 ,Color.white);
			 
			 full_pane.setLayout(new GridLayout(5,1,0, 15));
			 // "ime", "prezime", "datum rodjenja","adresa stanovanja","kontakt telefon","email",
			// "adresa kancelarije",
			// "broj licne karte",
			 //"titula",
			// "zvanje",
			// "Spisak predmeta"
			 
			CustomPanel sifra = new CustomPanel(650,50, Color.white);
			CustomPanel naziv = new CustomPanel(650,50, Color.white);
			CustomPanel godIzv = new CustomPanel(650,50, Color.white);
			CustomPanel semestar = new CustomPanel(650,50, Color.white);
			CustomPanel predProf = new CustomPanel(650,50, Color.white);
			 
			CustomPanel withInsets = new CustomPanel(450,500, Color.white);
			
			md.add(withInsets);
			withInsets.setLayout(new BoxLayout(withInsets, BoxLayout.X_AXIS));
			withInsets.add(new CustomPanel(50,450, Color.white));
			withInsets.add(full_pane);
			withInsets.add(new CustomPanel(50,450, Color.white));
			
			full_pane.add(sifra);
			full_pane.add(naziv);
			full_pane.add(godIzv);
			full_pane.add(semestar);
			full_pane.add(predProf);
			
			sifra.setLayout(new BoxLayout(sifra, BoxLayout.X_AXIS));
			naziv.setLayout(new BoxLayout(naziv, BoxLayout.X_AXIS));
			godIzv.setLayout(new BoxLayout(godIzv, BoxLayout.X_AXIS));
			semestar.setLayout(new BoxLayout(semestar, BoxLayout.X_AXIS));
			predProf.setLayout(new BoxLayout(predProf, BoxLayout.X_AXIS));
			
			sifra.add(new JLabel("     Sifra predmeta:*        "));
			naziv.add(new JLabel("     Naziv predmeta:*        "));
			semestar.add(new JLabel("     Semestar:*    "));
			godIzv.add( new JLabel("     Godina predavanja:*        "));
			predProf.add(new JLabel("     Predmetni profesor:*         "));
			
			JTextField _sifra = new JTextField();
			JTextField _naziv = new JTextField();
			JTextField _semestar = new JTextField();
			JTextField _godIzv = new JTextField();
			JTextField _predProf = new JTextField();
			
			_sifra.setEditable(false);
			_naziv.setEditable(false);
			_semestar.setEditable(false);
			_godIzv.setEditable(false);
			_predProf.setEditable(false);
			
			_sifra.setText(predmet.getSifra());
			_naziv.setText(predmet.getNaziv());
			_semestar.setText(Integer.toString(predmet.getSemestar()));
			_godIzv.setText(Integer.toString(predmet.getGodIzv()));
			
			String ime, prezime = null;
			
			if (predmet.getPredProf() == null) {
				ime = "null";
				prezime = "";
			}
			else {
				ime = predmet.getPredProf().getIme();
				prezime = predmet.getPredProf().getPrezime();
			}
			
			_predProf.setText(ime + " " + prezime);
			
			sifra.add(_sifra);
			naziv.add(_naziv);
			semestar.add(_semestar);
			godIzv.add(_godIzv);
			predProf.add(_predProf);
		
			md.setVisible(true);
			
		 });
 
		 prikazi.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	prikazi.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\prikazi.png"));
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	prikazi.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\prikazi_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	prikazi.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\prikazi.png"));
		    }
		});
		
		odustani.addActionListener(close -> this.dispose());
		
		odustani.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	//this.dispose();
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		    }
		});
		
	}
	
	private void findProfesors(ArrayList<Object> list) {
		
		
		CustomPanel top_inset = new CustomPanel(650, 50, Color.WHITE);
		CustomPanel bot_inset = new CustomPanel(650,60, Color.WHITE);
		CustomPanel panel = new CustomPanel(650,390, Color.WHITE);
		
		bot_inset.setLayout(new BoxLayout(bot_inset, BoxLayout.Y_AXIS));
		bot_inset.add(new CustomPanel(650,60,Color.WHITE));
	
		this.add(BorderLayout.NORTH, top_inset);
		this.add(BorderLayout.CENTER, panel);
		this.add(BorderLayout.SOUTH, bot_inset);
		
		JLabel question = new JLabel("Broj licne karte profesora: ");
		JTextField indeks = new JTextField(20);
		
		CustomPanel paneltop = new CustomPanel(650,95, Color.WHITE);
		CustomPanel tablepanel = new CustomPanel(650, 200, Color.WHITE);
		CustomPanel panelbot = new CustomPanel(650, 95, Color.WHITE); 
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(paneltop);
		panel.add(tablepanel);
		panel.add(panelbot);
		
		paneltop.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
		paneltop.add(question);
		paneltop.add(indeks);
		
		JButton prikazi = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\prikazi.png"));
		JButton odustani = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		
		prikazi.setFocusPainted(false);
		prikazi.setBorderPainted(false);				
		prikazi.setOpaque(false);
		prikazi.setContentAreaFilled(false);
		odustani.setFocusPainted(false);
		odustani.setBorderPainted(false);
		odustani.setOpaque(false);
		odustani.setContentAreaFilled(false);
		
		panelbot.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
		
		panelbot.add(prikazi);
		panelbot.add(odustani);
		
		// Tabela u tablepanel
		
		String[] kolona = {"Profesori"};
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(kolona);
		
		JTable profTable = new JTable() {
		 /**
			 * 
			 */
			private static final long serialVersionUID = -3616267117240262552L;

		public boolean isCellEditable(int row, int column) { 
			return false; 
			} 
		 };
		 
		 profTable.setModel(dtm);
		 TableColumnModel cm = profTable.getColumnModel();
		 profTable.setRowHeight(30);
		 
		 JScrollPane scrollPane = new JScrollPane(profTable);
		 scrollPane.setPreferredSize(new Dimension(350,160));
		 scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		 
		 tablepanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		 tablepanel.add(scrollPane);
		 
		 for (Object o : list ) {
			  
			 Profesor p = (Profesor) o;
					 
			 Vector<String> vek = new Vector<String>();
			 vek.add(p.getIme() + " " + p.getPrezime() + " " + p.getBrojLK());
			 dtm.addRow(vek);
		 }
	
		 profTable.addMouseListener(new MouseAdapter() { 
		 
		 public void mouseClicked(MouseEvent e) {
			 
			 // Luka Jovanovic RA 1/2019
			 //   0       1     2    3  
			 
			 String string = (String) profTable.getValueAt(profTable.getSelectedRow(), 0);
			 String[] strings = string.split(" ");
			 String index = strings[2];
			 indeks.setText(index);
	 
		 }
		 
		 } );
		 
		 prikazi.addActionListener(add -> { 
			 //index = indeks.getText();
			 String indexZZ = indeks.getText();
			 
			 if (indexZZ.length() == 0) 
				 return;
			 
			 if (ProfesoriController.getInstance().getProfesor(indexZZ) == null) {
				
				 System.out.println("NE POSTOJI PROFESOR SA TIM BROJEM LICNE KARTE");
				 //this.dispose();
				 return;
			 }
			 
			 Profesor prof = ProfesoriController.getInstance().getProfesor(indexZZ);
			 
			 this.remove(bot_inset);
			 this.remove(top_inset);
			 this.remove(panel);
			 this.setPreferredSize(new Dimension(300,950));
			 
			 CustomPanel full_pane = new CustomPanel(650,500 ,Color.white);
			 
			 full_pane.setLayout(new GridLayout(11,1,0, 15));
			 // "ime", "prezime", "datum rodjenja","adresa stanovanja","kontakt telefon","email",
			// "adresa kancelarije",
			// "broj licne karte",
			 //"titula",
			// "zvanje",
			// "Spisak predmeta"
			 
			 CustomPanel ime = new CustomPanel(650,45, Color.white);
			 CustomPanel prz = new CustomPanel(650,45, Color.white);
			 CustomPanel dr = new CustomPanel(650,45, Color.white);
			 CustomPanel as = new CustomPanel(650,45, Color.white);
			 CustomPanel kt = new CustomPanel(650,45, Color.white);
			 CustomPanel email = new CustomPanel(650,45, Color.white);
			 CustomPanel ak = new CustomPanel(650,45, Color.white);
			 CustomPanel lk = new CustomPanel(650,45, Color.white);
			 CustomPanel title = new CustomPanel(650,45, Color.white);
			 CustomPanel zv = new CustomPanel(650,45, Color.white);
			 CustomPanel predmeti = new CustomPanel(650,45, Color.white);
			 
			 ime.setLayout(new BoxLayout(ime, BoxLayout.X_AXIS));
			 prz.setLayout(new BoxLayout(prz, BoxLayout.X_AXIS));
			 dr.setLayout(new BoxLayout(dr, BoxLayout.X_AXIS));
			 as.setLayout(new BoxLayout(as, BoxLayout.X_AXIS));
			 kt.setLayout(new BoxLayout(kt, BoxLayout.X_AXIS));
			 email.setLayout(new BoxLayout(email, BoxLayout.X_AXIS));
			 ak.setLayout(new BoxLayout(ak, BoxLayout.X_AXIS));
			 lk.setLayout(new BoxLayout(lk, BoxLayout.X_AXIS));
			 title.setLayout(new BoxLayout(title, BoxLayout.X_AXIS));
			 zv.setLayout(new BoxLayout(zv, BoxLayout.X_AXIS));
			 predmeti.setLayout(new BoxLayout(predmeti, BoxLayout.X_AXIS));
			 
			 
			 ime.add( new JLabel("    Ime:    "));
			 prz.add( new JLabel("    Prezime:*   "));
			 dr.add( new JLabel("    Datum rodjenja:    "));
			 as.add( new JLabel("    Adresa stanovanja:    "));
			 kt.add(new JLabel("    Broj telefona:    "));
			 email.add(new JLabel("    E-mail adresa:    "));
			 ak.add(new JLabel("    Adresa Kancelarije:    "));
			 lk.add(new JLabel("    Broj licne karte:    "));
			 title.add(new JLabel("    Titula:    "));
			 zv.add(new JLabel("    Zvanje:    "));
			 predmeti.add(new JLabel("    Lista predmeta:    "));	
			 
			 JTextField _ime = new JTextField();
			 JTextField _prz = new JTextField();
			 JTextField _dr = new JTextField();
			 JTextField _as = new JTextField();
			 JTextField _kt = new JTextField();
			 JTextField _email = new JTextField();
			 JTextField _ak = new JTextField();
			 JTextField _lk = new JTextField();
			 JTextField _title = new JTextField();
			 JTextField _zv = new JTextField();
			 
			 _ime.setText(prof.getIme());
			 _prz.setText(prof.getPrezime());
			 _dr.setText(ProfesoriController.getInstance().parseDate(prof.getDatRodj()));
			 _as.setText(prof.getAdresaStanovanja());
			 _kt.setText(prof.getKontaktTel());
			 _email.setText(prof.getEmail());
			 _ak.setText(prof.getAdresaKanc());
			 _lk.setText(prof.getBrojLK());
			 _title.setText(prof.getTitula());
			 _zv.setText(prof.getZvanje().toString());
			 
			 _ime.setEditable(false);
			 _prz.setEditable(false);
			 _dr.setEditable(false);
			 _as.setEditable(false);
			 _kt.setEditable(false);
			 _email.setEditable(false);
			 _ak.setEditable(false);
			 _lk.setEditable(false);
			 _title.setEditable(false);
			 _zv.setEditable(false);
			 
	 
			 ime.add( _ime);
			 prz.add(_prz);
			 dr.add(_dr);
			 as.add(_as);
			 kt.add(_kt);
			 email.add(_email);
			 ak.add(_ak);
			 lk.add(_lk);
			 title.add(_title);
			 zv.add(_zv);
			 
			 Vector<String> subjects = new Vector<String>();
			 
			 for (Predmet p : prof.getPredmeti())
				 subjects.add(p.getNaziv());
			 JComboBox<String> combo = new JComboBox<String>(subjects);
			 combo.setEditable(false);
			 predmeti.add(combo);  
						 		 
			 full_pane.add(ime);
			 full_pane.add(prz);
			 full_pane.add(dr);
			 full_pane.add(as);
			 full_pane.add(kt);
			 full_pane.add(email);
			 full_pane.add(ak);
			 full_pane.add(lk);
			 full_pane.add(title);
			 full_pane.add(zv);
			 full_pane.add(predmeti);
			 CustomPanel withInsets = new CustomPanel(450,500, Color.white);
			 
			 withInsets.setLayout(new BoxLayout(withInsets, BoxLayout.X_AXIS));
			 withInsets.add(new CustomPanel(50, 500, Color.white));
			 withInsets.add(full_pane);
			 withInsets.add(new CustomPanel(50, 500, Color.white));
			 this.add(withInsets);
			 this.setVisible(true);
			 
			 
		 });
		 
		 prikazi.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	prikazi.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\prikazi.png"));
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	prikazi.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\prikazi_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	prikazi.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\prikazi.png"));
		    }
		});
		
		odustani.addActionListener(close -> this.dispose());
		
		odustani.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	//this.dispose();
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		    }
		});
			
	}
	
	// metoda za brisanje studenta sa predmeta - GUI + funkcionalnost 
	private void removeStudentFromPredmet() {

		CustomPanel top_inset = new CustomPanel(650, 50, Color.WHITE);
		CustomPanel bot_inset = new CustomPanel(650,60, Color.WHITE);
		CustomPanel panel = new CustomPanel(650,390, Color.WHITE);

		bot_inset.setLayout(new BoxLayout(bot_inset, BoxLayout.Y_AXIS));
		bot_inset.add(new CustomPanel(650,60,Color.WHITE));

		this.add(BorderLayout.NORTH, top_inset);
		this.add(BorderLayout.CENTER, panel);
		this.add(BorderLayout.SOUTH, bot_inset);

		JLabel question = new JLabel("Broj indeksa studenta: ");
		JTextField indeks = new JTextField(20);

		CustomPanel paneltop = new CustomPanel(650,95, Color.WHITE);
		CustomPanel tablepanel = new CustomPanel(650, 200, Color.PINK);
		CustomPanel panelbot = new CustomPanel(650, 95, Color.WHITE);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(paneltop);
		panel.add(tablepanel);
		panel.add(panelbot);

		paneltop.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
		paneltop.add(question);
		paneltop.add(indeks);

		JButton obrisi = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\obrisi.png"));
		JButton odustani = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));

		obrisi.setFocusPainted(false);
		obrisi.setBorderPainted(false);
		obrisi.setOpaque(false);
		obrisi.setContentAreaFilled(false);
		odustani.setFocusPainted(false);
		odustani.setBorderPainted(false);
		odustani.setOpaque(false);
		odustani.setContentAreaFilled(false);

		panelbot.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));

		panelbot.add(obrisi);
		panelbot.add(odustani);
		
		// Tabela koja prikazuje studente koji su na predmetu
		
		String[] kolona = {"Studenti"};
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(kolona);
		
		JTable studTable = new JTable() {
		 /**
			 * 
			 */
			private static final long serialVersionUID = -3616267117240262552L;

		public boolean isCellEditable(int row, int column) { 
			return false; 
			} 
		 };
		 
		 studTable.setModel(dtm);
		 //TableColumnModel cm = studTable.getColumnModel();
		 studTable.setRowHeight(30);
		 
		 JScrollPane scrollPane = new JScrollPane(studTable);
		 scrollPane.setPreferredSize(new Dimension(350,160));
		 scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		 
		 tablepanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		 tablepanel.add(scrollPane);
		 
		 // prolazimo kroz sve studente selektovanog predmeta i dodajemo ih u tabelu studTable
		 for (Student s : PredmetiController.getInstance().getPredmet(MyPredmetTable.pw).getStudenti()) {
			 
			 Vector<String> vek = new Vector<String>();
			 vek.add(s.getIme() + " " + s.getPrezime() + " " + s.getIndeks());
			 dtm.addRow(vek);
		 }
	
		 // mouse listener za preuzimanje podatka o selektovanom studentu
		 studTable.addMouseListener(new MouseAdapter() { 
		  
			 public void mouseClicked(MouseEvent e) {
				 
				 String string = (String) studTable.getValueAt(studTable.getSelectedRow(), 0);
				 String[] strings = string.split(" ");
				 String index = strings[2] + " " + strings[3];
				 indeks.setText(index);		 			 
			 }
			 
		 } );
		 // klikom na dugme preuzimamo indeks iz tabele bacamo ga u textfield i taj tekst saljemo u odgovarajucu metodu (moguce je i upisati samo indeks u textfield) 
		 obrisi.addActionListener(add -> { 
			 
			 String indexZZ = indeks.getText();
			 if (indexZZ.length() == 0) 
				 return;
			 if (StudentiController.getInstance().getStudent(indexZZ) == null) {
				 System.out.println(" NE POSTOJI STUDENT SA TIM INDEKSOM ");
				 return;
			 }
			 PredmetiController.getInstance().removeStudentFromPredmet(StudentiController.getInstance().getStudent(indexZZ)
					 												   , PredmetiController.getInstance().getPredmet(MyPredmetTable.pw));
 
		 });
		
		obrisi.addActionListener(evt -> this.dispose());
		 
		obrisi.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	obrisi.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\obrisi.png"));
		  //  	md.dispose();
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	obrisi.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\obrisi_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	obrisi.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\obrisi.png"));
		    }
		});
		
		odustani.addActionListener(evt -> this.dispose());

		odustani.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		    //	md.dispose();
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		    }
		});

	}

	// metoda za dodavanje studenta na predmet - GUI + funkcionalnost
	private void addStudentToPredmet() {

		CustomPanel top_inset = new CustomPanel(650, 50, Color.WHITE);
		CustomPanel bot_inset = new CustomPanel(650,60, Color.WHITE);
		CustomPanel panel = new CustomPanel(650,390, Color.WHITE);

		bot_inset.setLayout(new BoxLayout(bot_inset, BoxLayout.Y_AXIS));
		bot_inset.add(new CustomPanel(650,60,Color.WHITE));

		this.add(BorderLayout.NORTH, top_inset);
		this.add(BorderLayout.CENTER, panel);
		this.add(BorderLayout.SOUTH, bot_inset);

		JLabel question = new JLabel("Broj indeksa studenta: ");
		JTextField indeks = new JTextField(20);

		CustomPanel paneltop = new CustomPanel(650,95, Color.WHITE);
		CustomPanel tablepanel = new CustomPanel(650, 200, Color.PINK);
		CustomPanel panelbot = new CustomPanel(650, 95, Color.WHITE);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(paneltop);
		panel.add(tablepanel);
		panel.add(panelbot);

		paneltop.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
		paneltop.add(question);
		paneltop.add(indeks);

		JButton dodaj = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj.png"));
		JButton odustani = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));

		dodaj.setFocusPainted(false);
		dodaj.setBorderPainted(false);
		dodaj.setOpaque(false);
		dodaj.setContentAreaFilled(false);
		odustani.setFocusPainted(false);
		odustani.setBorderPainted(false);
		odustani.setOpaque(false);
		odustani.setContentAreaFilled(false);

		panelbot.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));

		panelbot.add(dodaj);
		panelbot.add(odustani);
		
		// Tabela za dodavanje studenta na predmet
		
		String[] kolona = {"Studenti"};
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(kolona);
		
		JTable studTable = new JTable() {
		 /**
			 * 
			 */
			private static final long serialVersionUID = -3616267117240262552L;

		public boolean isCellEditable(int row, int column) { 
			return false; 
			} 
		 };
		 
		 studTable.setModel(dtm);
		 TableColumnModel cm = studTable.getColumnModel();
		 studTable.setRowHeight(30);
		 
		 JScrollPane scrollPane = new JScrollPane(studTable);
		 scrollPane.setPreferredSize(new Dimension(350,160));
		 scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		 
		 tablepanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		 tablepanel.add(scrollPane);
		 
		 // preuzimamo sve studente iz baze ali ubacujemo samo one koji su odgovarajuca godina i koji ne slusaju predmet
		 for (Student s : StudentiController.getInstance().getStudenti()) {
			  
			 boolean flag = false;
			 
			 if (s.getGodStud() != PredmetiController.getInstance().getPredmet(MyPredmetTable.pw).getGodIzv())
				 continue;
			 
			 for (Student st : PredmetiController.getInstance().getPredmet(MyPredmetTable.pw).getStudenti()) {
				 
				 if (st.getIndeks().equals(s.getIndeks())) {
					 flag = true;
					 break;
				 }
			 }
			 // koristimo flag jer ne mogu da pozovem continue za spoljasnji for iz unutrasnjeg fora
			 if (flag == true)
				 continue;
			 // dodajemo studenta u tabelu
			 Vector<String> vek = new Vector<String>();
			 vek.add(s.getIme() + " " + s.getPrezime() + " " + s.getIndeks());
			 dtm.addRow(vek);
		 }
		 // mouse listener kako bi znali koga smo selektovali
		 studTable.addMouseListener(new MouseAdapter() { 
		 
			 public void mouseClicked(MouseEvent e) {
				 
				 // Luka Jovanovic RA 1/2019
				 //   0       1     2    3  
				 
				 String string = (String) studTable.getValueAt(studTable.getSelectedRow(), 0);
				 String[] strings = string.split(" ");
				 String index = strings[2] + " " + strings[3];
				 indeks.setText(index);
		 
			 }
		 
		 } );
		 
		 dodaj.addActionListener(add -> { 
			 //preuzimamo vrednost iz text fielda i saljemo odgovarajucoj funkciji
			 String indexZZ = indeks.getText(); 
			 
			 if (indexZZ.length() == 0) 
				 return;
			 
			 if (StudentiController.getInstance().getStudent(indexZZ) == null) {
				
				 System.out.println("NE POSTOJI STUDENT SA TIM INDEKSOM");
				 //this.dispose();
				 return;
			 }
			 
			 Student ss = StudentiController.getInstance().getStudent(indexZZ);
			 Predmet p = PredmetiController.getInstance().getPredmet(MyPredmetTable.pw);
			 
			 PredmetiController.getInstance().addStudentToPredmet(ss, p);

			 // provera uspesnosti
			 for ( Student s : PredmetiController.getInstance().getPredmet(MyPredmetTable.pw).getStudenti()) 
				 System.out.println(s.getIndeks());

			 this.dispose();
		 });

		odustani.addActionListener(evt -> this.dispose());
		
		odustani.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		    //	md.dispose();
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		    }
		});
		
		//dodaj.addActionListener(evt -> this.dispose());
		
		dodaj.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	dodaj.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj.png"));
		  //  	md.dispose();
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	dodaj.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	dodaj.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj.png"));
		    }
		});

	}
	
	private void removeProfesorFromPredmet(MyDefaultPredmetTable mdpt) {

		CustomPanel top_inset = new CustomPanel(650, 50, Color.WHITE);
		CustomPanel bot_inset = new CustomPanel(650,60, Color.WHITE);
		CustomPanel panel = new CustomPanel(650,190, Color.WHITE);

		bot_inset.setLayout(new BoxLayout(bot_inset, BoxLayout.Y_AXIS));
		bot_inset.add(new CustomPanel(650,60,Color.WHITE));

		this.add(BorderLayout.NORTH, top_inset);
		this.add(BorderLayout.CENTER, panel);
		this.add(BorderLayout.SOUTH, bot_inset);

		JLabel question = null;
		boolean flag = false; 
		// menjamo labelu u zavisnosti od postojanja predmetnog profesora 
		if (PredmetiController.getInstance().getPredmet(MyPredmetTable.pw).getPredProf() != null) {
			
			question = new JLabel("Da li ste sigurni da zelite da obrisete profesora " 
									+ PredmetiController.getInstance().getPredmet(MyPredmetTable.pw).getPredProf().getIme() + " " 
									+ PredmetiController.getInstance().getPredmet(MyPredmetTable.pw).getPredProf().getPrezime() + " sa predmeta?");
			flag = true;
		}

		CustomPanel paneltop = new CustomPanel(650,95, Color.WHITE);
		CustomPanel panelbot = new CustomPanel(650, 95, Color.WHITE);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(paneltop);
		panel.add(panelbot);

		paneltop.setLayout(new GridBagLayout());
		paneltop.add(question);

		JButton brisi = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\obrisi.png"));
		JButton odustani = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));

		brisi.setFocusPainted(false);
		brisi.setBorderPainted(false);
		brisi.setOpaque(false);
		brisi.setContentAreaFilled(false);
		odustani.setFocusPainted(false);
		odustani.setBorderPainted(false);
		odustani.setOpaque(false);
		odustani.setContentAreaFilled(false);

		panelbot.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));

		panelbot.add(brisi);
		panelbot.add(odustani);
		
		// izbrisemo predmetnog profesora sa predmeta i stavljamo vrednost u tabeli na 'null'
		if (flag = true) {
			brisi.addActionListener(obrisi -> {
			
				PredmetiController.getInstance().removeProfesorFromPredmet(PredmetiController.getInstance().getPredmet(MyPredmetTable.pw).getPredProf(),
																		   PredmetiController.getInstance().getPredmet(MyPredmetTable.pw));
				
				 mdpt.setValueAt("null", MyPredmetTable.selected, 4);
			
			});
		}

		odustani.addActionListener(evt -> this.dispose());
		brisi.addActionListener(evt -> this.dispose());
		
		brisi.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	brisi.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\obrisi.png"));
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	brisi.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\obrisi_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	brisi.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\obrisi.png"));
		    }
		});
		
		odustani.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));

		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		    }
		});

	}

	private void addProfesorToPredmet(MyDefaultPredmetTable mdpt) {

		CustomPanel top_inset = new CustomPanel(650, 50, Color.WHITE);
		CustomPanel bot_inset = new CustomPanel(650,60, Color.WHITE);
		CustomPanel panel = new CustomPanel(650,390, Color.WHITE);

		bot_inset.setLayout(new BoxLayout(bot_inset, BoxLayout.Y_AXIS));
		bot_inset.add(new CustomPanel(650,60,Color.WHITE));

		this.add(BorderLayout.NORTH, top_inset);
		this.add(BorderLayout.CENTER, panel);
		this.add(BorderLayout.SOUTH, bot_inset);

		JLabel question = new JLabel("Broj licne karte profesora: ");
		JTextField brojLKtxt = new JTextField(20);

		CustomPanel paneltop = new CustomPanel(650,95, Color.WHITE);
		// mesto za tabelu profesora
		CustomPanel tablepanel = new CustomPanel(650, 200, Color.WHITE);
		CustomPanel panelbot = new CustomPanel(650, 95, Color.WHITE);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(paneltop);
		panel.add(tablepanel);
		panel.add(panelbot);

		paneltop.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
		paneltop.add(question);
		paneltop.add(brojLKtxt);

		JButton dodaj = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj.png"));
		JButton odustani = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));

		dodaj.setFocusPainted(false);
		dodaj.setBorderPainted(false);
		dodaj.setOpaque(false);
		dodaj.setContentAreaFilled(false);
		odustani.setFocusPainted(false);
		odustani.setBorderPainted(false);
		odustani.setOpaque(false);
		odustani.setContentAreaFilled(false);

		panelbot.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));

		panelbot.add(dodaj);
		panelbot.add(odustani);

		// Tabela u tablepanel - koja ce prikazati sve profesore
		
		String[] kolona = {"PROFESORI"};
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(kolona);
		
		JTable profTable = new JTable() {
		 /**
			 * 
			 */
			private static final long serialVersionUID = -3616267117240262552L;

		public boolean isCellEditable(int row, int column) { 
			return false; 
			} 
		 };
		 
		 profTable.setModel(dtm);
		 TableColumnModel cm = profTable.getColumnModel();
		 profTable.setRowHeight(30);
		 // Ubacivanje podataka u tabelu
		 for(Profesor p : BazaProfesora.getInstance().getProfesori()) {
			 
			 Vector<String> red = new Vector<>();
			 red.add(p.getIme() + " " + p.getPrezime() + " (" + p.getBrojLK() + ")");
			 dtm.addRow(red);
 
		 }
		 
		 profTable.addMouseListener(new MouseAdapter() {
			 // preuzimanje vrednosti iz selektovanog reda 
			 public void mouseClicked(MouseEvent me) {
				 
				 String profa = (String) profTable.getValueAt(profTable.getSelectedRow(), 0);
				 String[] delovi = profa.split(" ");
				 String brojLK = delovi[2].substring(1, delovi[2].length()-1);
				 brojLKtxt.setText(brojLK);
				 System.out.println(brojLK);			 
				 
			 }
		});
		 
		 dodaj.addActionListener(add -> {
			 
			 String _brojLK = brojLKtxt.getText();
			 
			 if (_brojLK.length() == 0) 
				 return ;
			 if (ProfesoriController.getInstance().getProfesor(_brojLK) == null) {				 
				 System.out.println("Ne postoji profesor sa tim brojem licne karte");
				 return;
			 }
			 
			 Profesor prof = BazaProfesora.getInstance().getProfesor(_brojLK);
			 Predmet predm = BazaPredmeta.getInstance().getPredmet(MyPredmetTable.pw);
			 
			 PredmetiController.getInstance().addProfesorToPredmet(prof, predm);
			 
			 mdpt.setValueAt(prof.getBrojLK(), MyPredmetTable.selected, 4);
			 
			 dispose();

		 });
		 
		 JScrollPane scrollPane = new JScrollPane(profTable);
		 scrollPane.setPreferredSize(new Dimension(350,160));
		 scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		 
		 tablepanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		 tablepanel.add(scrollPane);
		
		 
		 odustani.addActionListener(evt -> this.dispose());
		 dodaj.addActionListener(evt -> this.dispose());
		 
		 dodaj.addMouseListener(new MouseAdapter() {
			 
			 @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	dodaj.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj.png"));
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	dodaj.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	dodaj.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj.png"));
		    }
		});
		
		odustani.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		    }
		});

}

	
}