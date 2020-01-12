package studentskasluzba.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
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