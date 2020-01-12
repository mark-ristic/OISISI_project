package studentskasluzba.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import studentskasluzba.controller.ProfesoriController;
import studentskasluzba.model.Predmet;
import studentskasluzba.model.Profesor;
import studentskasluzba.model.Profesor.ProfesorType;

public class MyProfesorTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3311552477873744491L;
	public static int selected; 
	public static String brojLKGlobal;
	private MyDefaultProfesorTable mdpt;
	private JScrollPane pane;
	
	public MyDefaultProfesorTable getMyDefaultProfesorTable() {
		return this.mdpt;
	}
	
	public boolean isCellEditable(int row, int column) {
		return column == 10; 
	}
	
	public JScrollPane getJScrollPane() {
		return this.pane;
	}
	
	public MyProfesorTable() {
		// default vrednost za izabran red kako bi znali da li je korisnik uopste selektovao profesora
		selected = -1;
		mdpt = new MyDefaultProfesorTable();
		this.setModel(mdpt);
		this.setBackground(Color.white);
		this.setForeground(Color.black);
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(this.getModel());
		this.setRowSorter(sorter);
		ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
		for (int i = 0 ; i < 5; i++) {
			sortKeys.add(new RowSorter.SortKey(i, SortOrder.ASCENDING));
			sortKeys.add(new RowSorter.SortKey(i, SortOrder.DESCENDING));
		}
		
		TableColumnModel cm = this.getColumnModel();
		
		cm.getColumn(0).setPreferredWidth(120);
		cm.getColumn(1).setPreferredWidth(120);
		cm.getColumn(2).setPreferredWidth(120);
		cm.getColumn(3).setPreferredWidth(180);
		cm.getColumn(4).setPreferredWidth(120);
		cm.getColumn(5).setPreferredWidth(120);
		cm.getColumn(6).setPreferredWidth(180);
		cm.getColumn(7).setPreferredWidth(120);
		cm.getColumn(8).setPreferredWidth(60);
		cm.getColumn(9).setPreferredWidth(60);
		cm.getColumn(10).setPreferredWidth(240);
		this.setRowHeight(30);
		
		this.getColumn("Spisak predmeta").setCellRenderer(new ButtonRenderer());
		this.getColumn("Spisak predmeta").setCellEditor(new ButtonEditor(new JCheckBox()));
		
		pane = new JScrollPane(this);
		pane.setBounds(0,0,1240,395);
		
		// niz od 11 objekata koje popunjujemo podacima iz panela za dodavanje profesora
		Object[] row = new Object[11];
		
		System.out.println("selected is " + selected);
		
ProfesorPanelAdd.dp.addActionListener( add -> {
			
			/* String ime, String prezime, Date datRodj, String adresaStanovanja, int kontaktTel,
			String email, String adresaKanc, int brojLK, String titula, ProfesorType zvanje, ArrayList<Predmet> predmeti */
			
			row[0] = ProfesorPanelAdd.imetxt.getText();
			String ime = ProfesorPanelAdd.imetxt.getText();
			row[1] = ProfesorPanelAdd.prezimetxt.getText();
			String prezime = ProfesorPanelAdd.prezimetxt.getText();
			row[2] = ProfesorPanelAdd.datumRodjtxt.getText();
			
			String datRodjregex = ProfesorPanelAdd.datumRodjtxt.getText();
			
			String adresaStanovanja = ProfesorPanelAdd.adresatxt.getText();
			row[3] = adresaStanovanja;
			
			String kontaktTel = ProfesorPanelAdd.teltxt.getText();
			row[4] = kontaktTel;
			
			String email = ProfesorPanelAdd.emailtxt.getText()	;
			row[5] = email	;
			
			String adresaKanc = ProfesorPanelAdd.adresaKanctxt.getText();
			row[6] = adresaKanc;
			
			String brojLK = ProfesorPanelAdd.brojLKtxt.getText();
			row[7] = ProfesorPanelAdd.brojLKtxt.getText()	;
			
			String titula = ProfesorPanelAdd.titulatxt.getText();
			row[8] = titula;
			
			ProfesorType zvanje = null;
			if (ProfesorPanelAdd.stalni.isSelected()) 
				zvanje = Profesor.ProfesorType.Redovni;
			if (ProfesorPanelAdd.vanredni.isSelected())
				zvanje = Profesor.ProfesorType.Vanredni;
			if (ProfesorPanelAdd.docent.isSelected())
				zvanje = Profesor.ProfesorType.Docent;
			
			row[9] = zvanje.toString(); 
			
			row[10] = "PREDMETI-" + ProfesorPanelAdd.brojLKtxt.getText();
			
			
			boolean checkRegexFlag = ProfesoriController.getInstance().checkRegex(ime, prezime, datRodjregex, kontaktTel, brojLK, email);
			
			if(checkRegexFlag == true) {
				return;
			}
			
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy."); 
			String dateInString = ProfesorPanelAdd.datumRodjtxt.getText();
			Date datRodj = null;
			try {
				datRodj = formatter.parse(dateInString);
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			
			
			ProfesoriController.getInstance().dodajProfesora(ime, prezime, datRodj, adresaStanovanja, kontaktTel, email, adresaKanc, brojLK, 
															titula, zvanje, new ArrayList<Predmet>(), row, mdpt);
			 
			ProfesorPanelAdd.imetxt.setText("");
			ProfesorPanelAdd.prezimetxt.setText("");
			ProfesorPanelAdd.datumRodjtxt.setText("");
			ProfesorPanelAdd.adresatxt.setText("");
			ProfesorPanelAdd.teltxt.setText("");
			ProfesorPanelAdd.emailtxt.setText("");
			ProfesorPanelAdd.adresaKanctxt.setText("");
			ProfesorPanelAdd.brojLKtxt.setText("");
			ProfesorPanelAdd.titulatxt.setText("");	

		});
		// mouse listener kako bi znali koga smo selektovali
		this.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				
				String brojLK = (String) getValueAt(getSelectedRow(), 7);
				brojLKGlobal = brojLK;
				selected = getSelectedRow();
				System.out.println("selected is   " + selected);
				System.out.print("brojLK" + (String) getValueAt(getSelectedRow(), 7) + " " + this.getClass().getSimpleName());
				
			if (ProfesoriController.getInstance().getProfesor(brojLK).getPredmeti().isEmpty() == false) {
				
				for (Predmet p : ProfesoriController.getInstance().getProfesor(brojLK).getPredmeti() )
					System.out.println(p.getNaziv());
			}
			else
				System.out.println("PRAZNA LISTA" + " " + this.getClass().getName());
				
			System.out.println(ProfesoriController.getInstance().getProfesor(brojLK).getIme());
				
				
				
				if (getSelectedRow() != -1) {
					// action listener za brisanje profesora
					ProfesorPanelRemove.brisi.addActionListener(remove -> {

						ProfesoriController.getInstance().izbrisiProfesora(brojLK, mdpt, getSelectedRow());					
					});	
				}
				// popunjavanje jtextfieldova kada zelimo da izmenimo podatke profesora
				MyToolbarProfesor.editProf.addActionListener(editevent -> {
					
					String brojLK1337 = (String) getValueAt(getSelectedRow(), 7);
					brojLKGlobal = brojLK1337;
					Profesor p = ProfesoriController.getInstance().getProfesor(brojLK1337);
					
					ProfesorPanelEdit.imetxt1.setText(p.getIme());
					ProfesorPanelEdit.prezimetxt1.setText(p.getPrezime());
					ProfesorPanelEdit.datumRodjtxt1.setText(ProfesoriController.getInstance().parseDate(p.getDatRodj()));
					ProfesorPanelEdit.adresatxt1.setText(p.getAdresaStanovanja());
					ProfesorPanelEdit.teltxt1.setText(p.getKontaktTel());
					ProfesorPanelEdit.brojLKtxt1.setText(p.getBrojLK());
					ProfesorPanelEdit.emailtxt1.setText(p.getEmail());
					ProfesorPanelEdit.adresaKanctxt1.setText(p.getAdresaKanc());
					ProfesorPanelEdit.titulatxt1.setText(p.getTitula());
					
					ProfesorPanelEdit.brojLKtxt1.setEditable(false);
					
					Vector<String> v = new Vector<String>();
					
					for (Predmet pt : p.getPredmeti())
						v.add(pt.getNaziv());
					ProfesorPanelEdit.combo = new JComboBox<String>(v);
					
					if (p.getZvanje().toString().equals("Redovni"))
						ProfesorPanelEdit.stalni.setSelected(true);
					if (p.getZvanje().toString().equals("Vanredni"))
						ProfesorPanelEdit.vanredni.setSelected(true);
					if (p.getZvanje().toString().equals("Docent"))
						ProfesorPanelEdit.docent.setSelected(true);
					
					// action listener za izmenu profesora - preuzimamo nazad vrednosti iz jtextfieldova
					ProfesorPanelEdit.ep.addActionListener(  ep -> {
						
						String ime = ProfesorPanelEdit.imetxt1.getText();
						String prezime = ProfesorPanelEdit.prezimetxt1.getText();
						String datumRodj = ProfesorPanelEdit.datumRodjtxt1.getText();
						String telefon = ProfesorPanelEdit.teltxt1.getText();
						String email = ProfesorPanelEdit.emailtxt1.getText();
						String brojLKregex = ProfesorPanelEdit.brojLKtxt1.getText();
						
						
						boolean flag = ProfesoriController.getInstance().checkRegex(ime, prezime, datumRodj, telefon, brojLKregex, email);
						
						if (flag == true)
							return;
						
						SimpleDateFormat formatter1337 = new SimpleDateFormat("dd.MM.yyyy."); 
						String dateInString = ProfesorPanelEdit.datumRodjtxt1.getText();
						Date datRodj = null;
						try {
							datRodj = formatter1337.parse(dateInString);
						} catch (ParseException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						String zvanje = null;
						if (ProfesorPanelEdit.stalni.isSelected())
							zvanje = "Redovni";
						else if (ProfesorPanelEdit.vanredni.isSelected())
							zvanje = "Vanredni";
						else if (ProfesorPanelEdit.docent.isSelected())
							zvanje = "Docent";
						ArrayList<Predmet> predmeti = (ArrayList<Predmet>) p.getPredmeti();
						// poziv funkcije za izmenu profesora
						ProfesoriController.getInstance().izmeniProfesora(ProfesorPanelEdit.imetxt1.getText(), 
																		  ProfesorPanelEdit.prezimetxt1.getText(), 
																		  datRodj, 
																		  ProfesorPanelEdit.adresatxt1.getText(), 
																		  ProfesorPanelEdit.teltxt1.getText(), 
																		  ProfesorPanelEdit.emailtxt1.getText(), 
																		  ProfesorPanelEdit.adresaKanctxt1.getText(), 
																		  ProfesorPanelEdit.brojLKtxt1.getText(), 
																		  ProfesorPanelEdit.titulatxt1.getText(), 
																		  Profesor.ProfesorType.valueOf(zvanje) ,
																		  predmeti,
																		  mdpt, 
																		  getSelectedRow());
						
					});
					
				});
				
			}
			
		});
		
		
	}
	// unutrasnje klase koje omogucavaju dodavanje jbuttona u celiju tabele
	class ButtonRenderer extends JButton implements TableCellRenderer {

	    public ButtonRenderer() {
	        setOpaque(true);
	    }

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value,
	            boolean isSelected, boolean hasFocus, int row, int column) {
	        if (isSelected) {
	            setForeground(table.getSelectionForeground());
	            setBackground(table.getSelectionBackground());
	        } else {
	            setForeground(table.getForeground());
	            setBackground(UIManager.getColor("Button.background"));
	        }
	        setText((value == null) ? "" : value.toString());
	        return this;
	    }
	}
	
	class ButtonEditor extends DefaultCellEditor {

	    protected JButton button;
	    private String label;
	    private boolean isPushed;
	    private MyDefaultProfesorTable mdpt = getMyDefaultProfesorTable() ;

	    public ButtonEditor(JCheckBox checkBox) {
	        super(checkBox);
	        button = new JButton();
	        button.setOpaque(true);
	        button.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                fireEditingStopped();
	            }
	        });
	    }

	    @Override
	    public Component getTableCellEditorComponent(JTable table, Object value,
	            boolean isSelected, int row, int column) {
	        if (isSelected) {
	            button.setForeground(table.getSelectionForeground());
	            button.setBackground(table.getSelectionBackground());
	        } else {
	            button.setForeground(table.getForeground());
	            button.setBackground(table.getBackground());
	        }
	        label = (value == null) ? "" : value.toString();
	        button.setText(label);
	        isPushed = true;
	        return button;
	    }

	    @Override
	    public Object getCellEditorValue() {
	        if (isPushed) {
	        	
	        	String[] strings = label.split("-");
	        	String sifra = strings[1];    	 
	        	brojLKGlobal = sifra;
	        	System.out.println(brojLKGlobal);
	        	MyDialog md = new MyDialog(null, "PRIKAZ", true, 450,480, "showSubjectsForProfs" );
	           
	        }
	        isPushed = false;
	        return label;
	    }

	    @Override
	    public boolean stopCellEditing() {
	        isPushed = false;
	        return super.stopCellEditing();
	    }
	}
}
