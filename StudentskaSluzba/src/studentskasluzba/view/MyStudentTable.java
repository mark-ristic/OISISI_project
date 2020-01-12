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

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import studentskasluzba.controller.StudentiController;
import studentskasluzba.model.BazaStudenata;
import studentskasluzba.model.Predmet;
import studentskasluzba.model.Student;
import studentskasluzba.model.Student.Status;


public class MyStudentTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1619216202948702447L;
	public static int selected; 
	private JScrollPane pane ;
	public static String indexGlobal; 
	private MyDefaultStudentTable mdst;
	public boolean isCellEditable(int row, int column) {                
        return column==11;               
	}
	
	public JScrollPane getJScrollPane() {
		return this.pane;
	}
	
	public MyDefaultStudentTable getMyDefaultStudentTable() {
		return this.mdst;
	}
	
	public MyStudentTable() { 
		// default vrednost za izabran red kako bi znali da li je korisnik uopste selektovao studenta
		selected = -1;
		mdst = new MyDefaultStudentTable();
		this.setModel(mdst);
		this.setBackground(Color.orange);
		this.setForeground(Color.black);
		
		/* String ime, String prezime, DateFormat datRodj, String adresaStanovanja, int kontaktTel,
		String email, String indeks, DateFormat datumUpisa, int godStud, Status status, double prosek,
		ArrayList<Predmet> predmeti */
		
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
		cm.getColumn(3).setPreferredWidth(120);
		cm.getColumn(4).setPreferredWidth(120);
		cm.getColumn(5).setPreferredWidth(120);
		cm.getColumn(6).setPreferredWidth(120);
		cm.getColumn(7).setPreferredWidth(120);
		cm.getColumn(8).setPreferredWidth(120);
		cm.getColumn(9).setPreferredWidth(120);
		cm.getColumn(10).setPreferredWidth(120);
		cm.getColumn(11).setPreferredWidth(120);
		this.setRowHeight(30);
		
		this.getColumn("Spisak predmeta").setCellRenderer(new ButtonRenderer());
		this.getColumn("Spisak predmeta").setCellEditor(new ButtonEditor(new JCheckBox()));
		
		pane = new JScrollPane(this);
		pane.setBounds(0, 0, 1240, 395);
	
		// row niz od 12 objekata koji popunjavamo sa podacima iz panela za dodavanje studenta
		
		Object[] row = new Object[12];
		// action listener za dodavanje studenta
		StudentPanelAdd.ds.addActionListener( add -> {
			
			row[0] = StudentPanelAdd.imetxt.getText();
			String ime = StudentPanelAdd.imetxt.getText();
			row[1] = StudentPanelAdd.prezimetxt.getText();
			String prezime = StudentPanelAdd.prezimetxt.getText();
			row[2] = StudentPanelAdd.datumRodjtxt.getText();
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy."); 
			String dateInString = StudentPanelAdd.datumRodjtxt.getText();
			Date datRodj = null;
			try {
				datRodj = formatter.parse(dateInString);
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
 
			
			row[3] = StudentPanelAdd.adresatxt.getText();
			String adresaStanovanja = StudentPanelAdd.adresatxt.getText();
			row[4] = StudentPanelAdd.teltxt.getText();
			String kontaktTel = StudentPanelAdd.teltxt.getText();
			row[5] = StudentPanelAdd.emailtxt.getText();
			String email = StudentPanelAdd.emailtxt.getText();
			row[6] = StudentPanelAdd.indekstxt.getText();
			String indeks = StudentPanelAdd.indekstxt.getText();
			row[7] = StudentPanelAdd.datumUpisatxt.getText();
			
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd.MM.yyyy."); 
			String dateInString1 = StudentPanelAdd.datumUpisatxt.getText();
			Date datumUpisa = null;
			try {
				datumUpisa = formatter1.parse(dateInString1);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					
			
			int godStud = StudentPanelAdd.trenGodCombo.getSelectedIndex()	;
			
			row[8] = Integer.toString(godStud + 1);
				
			Status status; 
			if (StudentPanelAdd.budzet.isSelected()) {
				row[9] = "B";
				status = Status.valueOf("B");
			}
			else {
				row[9] = "S";
				status = Status.valueOf("S");
			}
			
			
			
			row[10] = Double.parseDouble(StudentPanelAdd.prosektxt.getText());
			row[11] = "PREDMETI-" + indeks;
			
			ArrayList<Predmet> predmeti = new ArrayList<Predmet>();
			
			Double prosek = Double.parseDouble(StudentPanelAdd.prosektxt.getText());
			// poziv funkcije za dodavanje studenta u bazu i tabelu
			StudentiController.getInstance().dodajStudenta(ime, prezime, datRodj, adresaStanovanja, kontaktTel, email, indeks,
														  datumUpisa, godStud+1, status, prosek, predmeti, row, mdst);
			
			System.out.println(StudentPanelAdd.trenGodCombo.getSelectedIndex());
			// resetujemo panel 
			StudentPanelAdd.imetxt.setText("");
			StudentPanelAdd.prezimetxt.setText("");
			StudentPanelAdd.datumRodjtxt.setText("");
			StudentPanelAdd.adresatxt.setText("");
			StudentPanelAdd.teltxt.setText("");
			StudentPanelAdd.emailtxt.setText("");
			StudentPanelAdd.indekstxt.setText("");
			StudentPanelAdd.datumUpisatxt.setText("");	
			StudentPanelAdd.prosektxt.setText("");
			StudentPanelAdd.trenGodCombo.setSelectedIndex(0);
		});
		
		this.addMouseListener(new MouseAdapter() {
			// mouse listener kako bi znali kog studenta smo selektovali
			public void mouseClicked(MouseEvent e) {
				
				selected = getSelectedRow();
				String index = (String) getValueAt(getSelectedRow(), 6);
				indexGlobal = index;
				System.out.println("index" + index + " " + this.getClass().getSimpleName());
				//MyToolbar.removeStudent.addActionListener(event -> BazaStudenata.getInstance().izbrisiStudenta(index));
				
				System.out.println(StudentiController.getInstance().getStudent(index).getPredmeti().size());
				
				
			 if (getSelectedRow() != -1) {
				 //action listener za brisanje studenta
				StudentPanelRemove.yes.addActionListener(event -> {
					
					StudentiController.getInstance().izbrisiStudenta(index, mdst,  getSelectedRow());
					
					
				});
			 }
				// Izmena Studenta - popunjavanje JTextFieldova podacima selektovanog studenta
				MyToolbarStudent.editStudent.addActionListener(editevent -> {
					
					String indexZZ = (String) getValueAt(getSelectedRow(), 6);
					indexGlobal = indexZZ;
					Student s = StudentiController.getInstance().getStudent(indexZZ);
					
					System.out.println(s.getIndeks() + "indekgetter" + index + " " + this.getClass().getSimpleName());
					
					StudentPanelEdit.imetxt1.setText(s.getIme());
					StudentPanelEdit.prezimetxt1.setText(s.getPrezime());
					StudentPanelEdit.datRodjtxt1.setText(StudentiController.getInstance().parseDate(s.getDatRodj()));
					
					StudentPanelEdit.adresatxt1.setText(s.getAdresaStanovanja());
					StudentPanelEdit.teltxt1.setText(s.getKontaktTel());
					StudentPanelEdit.emailtxt1.setText(s.getEmail());
					StudentPanelEdit.indekstxt1.setText(s.getIndeks());
					StudentPanelEdit.datumUpisatxt1.setText(StudentiController.getInstance().parseDate(s.getDatumUpisa()));
					StudentPanelEdit.prosektxt1.setText(Double.toString(s.getProsek()));
					
					StudentPanelEdit.indekstxt1.setEditable(false);
					
					
					if (s.getGodStud() == 1) 
						StudentPanelEdit.trenGodCombo1.setSelectedIndex(0);
					if (s.getGodStud() == 2) 
						StudentPanelEdit.trenGodCombo1.setSelectedIndex(1);
					if (s.getGodStud() == 3) 
						StudentPanelEdit.trenGodCombo1.setSelectedIndex(2);
					if (s.getGodStud() == 4)
						StudentPanelEdit.trenGodCombo1.setSelectedIndex(3);
					
					if (s.getStatus().equals(Student.Status.B))
						StudentPanelEdit.budzet1.setSelected(true);
					else
						StudentPanelEdit.samofin1.setSelected(true);
					// dopunjavamo action listener za izmenu studenta
					StudentPanelEdit.es.addActionListener( editevent1 -> {
						
						String statuszz = (StudentPanelEdit.budzet1.isSelected() == true) ? "B" : "S";
						
						SimpleDateFormat formatter1337 = new SimpleDateFormat("dd.MM.yyyy."); 
						String dateInString = StudentPanelEdit.datRodjtxt1.getText();
						Date datRodj = null;
						try {
							datRodj = formatter1337.parse(dateInString);
						} catch (ParseException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						SimpleDateFormat formatter1 = new SimpleDateFormat("dd.MM.yyyy."); 
						String dateInString1 = StudentPanelEdit.datumUpisatxt1.getText();
						Date datumUpisa = null;
						try {
							datumUpisa = formatter1.parse(dateInString1);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						
						// poziv funkcije za dodavanje studenta
						StudentiController.getInstance().izmeniStudenta(
								StudentPanelEdit.imetxt1.getText(),
								 StudentPanelEdit.prezimetxt1.getText(),
								 datRodj,
								 StudentPanelEdit.adresatxt1.getText(),
								 StudentPanelEdit.teltxt1.getText(),
								 StudentPanelEdit.emailtxt1.getText(),
								 StudentPanelEdit.indekstxt1.getText(),
								 datumUpisa,
								 StudentPanelEdit.trenGodCombo1.getSelectedIndex()+1,
								 Student.Status.valueOf(statuszz),
								 Double.parseDouble(StudentPanelEdit.prosektxt1.getText()),
								 BazaStudenata.getInstance().getStudent(StudentPanelEdit.indekstxt1.getText()).getPredmeti(),
								 mdst,
								 getSelectedRow()
						);			
					});	
				});
			
			}
		});
		
		
	}
	// unutrasnje klase kako bismo omogucili dodavanje jbuttona u celiju
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
	    private MyDefaultStudentTable mdst = getMyDefaultStudentTable() ;

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
	        	indexGlobal = sifra;
	        	MyDialog md = new MyDialog(null, "PRIKAZ", true, 450,480, "showSubjects" );
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
