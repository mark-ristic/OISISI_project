package studentskasluzba.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

import studentskasluzba.controller.PredmetiController;
import studentskasluzba.controller.ProfesoriController;
import studentskasluzba.model.Predmet;
import studentskasluzba.model.Profesor;
import studentskasluzba.model.Student;

public class MyPredmetTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6122771397558919864L;

	private MyDefaultPredmetTable mdpt;
	public static String pw;
	private JScrollPane scrollpane;
	public static int selected;
	
	public MyDefaultPredmetTable getMyDefaultPredmetTable() {
		return this.mdpt; 
	}
	
	public boolean isCellEditable(int row, int column) {
		return column==5;
	}
	
	public JScrollPane getJScrollPane() {
		return this.scrollpane;
	}
	
	public MyPredmetTable() {
		
		selected = -1;
		mdpt = new MyDefaultPredmetTable();
		this.setModel(mdpt);
		this.setBackground(Color.LIGHT_GRAY);
		this.setForeground(Color.BLACK);
	
		this.getColumnModel().getColumn(0).setPreferredWidth(240);
		this.getColumnModel().getColumn(1).setPreferredWidth(240);
		this.getColumnModel().getColumn(2).setPreferredWidth(240);
		this.getColumnModel().getColumn(3).setPreferredWidth(240);
		this.getColumnModel().getColumn(4).setPreferredWidth(240);
		this.getColumnModel().getColumn(5).setPreferredWidth(240);
		this.setRowHeight(30);
		
		this.getColumn("Spisak studenata").setCellRenderer(new ButtonRenderer());
		this.getColumn("Spisak studenata").setCellEditor(new ButtonEditor(new JCheckBox()));
		scrollpane = new JScrollPane(this);
		
		scrollpane.setBounds(0, 0, 1240, 395);
		
		// row cemo popunjavati sa podacima iz panela za dodavanje predmeta
		Object[] row = new Object[6];
		
		PredmetPanelAdd.dodaj.addActionListener( evt ->{
			
			row[0] = PredmetPanelAdd.sifratxt.getText();
			String sifra = PredmetPanelAdd.sifratxt.getText();
			
			row[1] = PredmetPanelAdd.nazivtxt.getText();
			String naziv = PredmetPanelAdd.nazivtxt.getText();
			
			String semestar = PredmetPanelAdd.semestartxt.getText();
			row[2] = semestar;
			
			String godIzv = Integer.toString(PredmetPanelAdd.comboGod.getSelectedIndex() + 1);
			row[3] = godIzv;
			
			String brojLK = PredmetPanelAdd.profesortxt.getText();
			
			// ako ne unesemo profesora stavi na 'null' po defaultu
			
				if(brojLK.length() == 0) {
					brojLK = "null";
				}
			row[4] = brojLK;	
			
			Profesor predProf = ProfesoriController.getInstance().getProfesor(brojLK);
			
			if (predProf != null)
				System.out.println("POSTOJI PROFESOR" + " " + this.getClass().getSimpleName());
			else
				brojLK = "null";
			
			row[5] = "STUDENTI-" + sifra;

			ArrayList<Student> studenti = new ArrayList<>();
			
			PredmetiController.getInstance().dodajPredmet(sifra, naziv, Integer.parseInt(semestar), PredmetPanelAdd.comboGod.getSelectedIndex()+1,
														  ProfesoriController.getInstance().getProfesor(brojLK), studenti, row, mdpt);
			 
			// kad dodajemo predmet iz panela, polja za unos u panelu resetujemo
			PredmetPanelAdd.sifratxt.setText("");
			PredmetPanelAdd.nazivtxt.setText("");
			PredmetPanelAdd.profesortxt.setText("");
			PredmetPanelAdd.semestartxt.setText("");
			PredmetPanelAdd.comboGod.setSelectedIndex(0);
			
		});
		
		this.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				// klikom na red iz tabele predmet selektujemo predmet koji zelimo da izmenimo
				pw = (String) getValueAt(getSelectedRow(), 0);
				selected = getSelectedRow();
				
				System.out.println(pw);
				System.out.println(PredmetiController.getInstance().getPredmet(pw).getSifra());
				
				for (Student s : PredmetiController.getInstance().getPredmet(pw).getStudenti()) {
					System.out.println(s.getIndeks());
				}
				
				if(PredmetiController.getInstance().getPredmet(pw).getPredProf() != null) {
					System.out.println(PredmetiController.getInstance().getPredmet(pw).getPredProf().getIme());
				}
				
				if(getSelectedRow() != -1) {
					PredmetPanelRemove.brisi.addActionListener(evt -> {
					
						PredmetiController.getInstance().izbrisiPredmet(pw, mdpt, getSelectedRow());
						
				});
			}
			
			// editovanje predmeta
			MyToolbarPredmet.editPredmet.addActionListener(edit ->{
				
				//String pw = (String) getValueAt(getSelectedRow(), 0);
				pw = (String) getValueAt(getSelectedRow(), 0);
				
				System.out.println(pw);
				// uzimamo selektovani predmet i njegove vrednosti ubacujemo u edit polja
				Predmet p = PredmetiController.getInstance().getPredmet(pw);
				
				PredmetPanelEdit.sifratxt.setText(p.getSifra());
				
				PredmetPanelEdit.sifratxt.setEditable(false);
				
				PredmetPanelEdit.nazivtxt.setText(p.getNaziv());
				
				if(p.getGodIzv() == 1) 
					PredmetPanelEdit.comboGod.setSelectedIndex(0);
				if(p.getGodIzv() == 2) 
					PredmetPanelEdit.comboGod.setSelectedIndex(1);
				if(p.getGodIzv() == 3) 
					PredmetPanelEdit.comboGod.setSelectedIndex(2);
				if(p.getGodIzv() == 4) 
					PredmetPanelEdit.comboGod.setSelectedIndex(3);
				
				if(p.getPredProf() == null)
					PredmetPanelEdit.profesortxt.setText("null");
				else
					PredmetPanelEdit.profesortxt.setText(p.getPredProf().getBrojLK());
				
				PredmetPanelEdit.semestartxt.setText(Integer.toString(p.getSemestar()));
				
				PredmetPanelEdit.izmeni.addActionListener(evt ->{
					
					String sifr = PredmetPanelEdit.sifratxt.getText();
					String naziv = PredmetPanelEdit.nazivtxt.getText();
					
					String brojLK = PredmetPanelEdit.profesortxt.getText();
					
					PredmetiController.getInstance().izmeniPredmet(	pw,
																	naziv,
																	Integer.parseInt(PredmetPanelEdit.semestartxt.getText()),
																	PredmetPanelEdit.comboGod.getSelectedIndex()+1,
																	ProfesoriController.getInstance().getProfesor(brojLK),
																	p.getStudenti(),
																	row,
																	mdpt,
																	getSelectedRow());
									
					
				});
			});

			}
		});	
	}
	
	// unutrasnja klasa da bismo mogli dodavati button u celiju
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

	    /**
		 * 
		 */
		private static final long serialVersionUID = -987428540093658892L;
		protected JButton button;
	    private String label;
	    private boolean isPushed;
	    private MyDefaultPredmetTable mdpt = getMyDefaultPredmetTable();

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
	    
	    // 
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
	        	pw = sifra;
	        	MyDialog md = new MyDialog(null, "PRIKAZ", true, 450,480, "showStudents" );
	            System.out.println(PredmetiController.getInstance().getPredmet(sifra).getNaziv());
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
