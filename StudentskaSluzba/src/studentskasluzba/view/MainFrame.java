package studentskasluzba.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 153782876756455625L;
	
// Parametar je naziv frame-a
	MainFrame(String naziv){
		
	// Probald kovetni az en formatomat, hogy ne legyen konfliktus
		super(naziv);
		
		execDefaultToolkit();
		// glavni prozor se sastoji od 3 panela => tbPanel + centerPanel + statusBar
		// centralni panel 
		CustomPanel centerPanel = new CustomPanel(1440, 620, Color.BLUE);
		
		// Toolbar paneli
		CustomPanel tbStudent = new CustomPanel(1440, 65, Color.CYAN);
		CustomPanel tbProfesor = new CustomPanel(1440, 65, Color.RED);
		CustomPanel tbPredmet = new CustomPanel(1440, 65, Color.GREEN);
		
		// inicijalna vidljivost panela
		tbProfesor.setVisible(false);
		tbPredmet.setVisible(false);
		tbStudent.setVisible(true);
		
		// tabovi
		StudentTab studentTab = new StudentTab();
		ProfesorTab profesorTab = new ProfesorTab();
		PredmetTab predmetTab = new PredmetTab();
		// glavni TabbedPane 
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		
		// spajanje panela u tabbedPane
		decoratePanels(tabbedPane, studentTab, profesorTab, predmetTab, centerPanel);
		
		// Menu part 
		this.setJMenuBar(new MyMenu(this));
		
		// kreiramo sve toolbarove
		decorateToolbars(tbStudent,tbProfesor,tbPredmet);
		
		// sastavljamo citav panel
		buildFrame(tbStudent,tbProfesor,tbPredmet,centerPanel);
		
		// TODO: napraviti da se menjaju toolbarovi sa odgovarajucim tabovima
		execTabListener(tabbedPane, tbStudent, tbProfesor, tbPredmet);
		
		
	}
	
	private void decoratePanels(JTabbedPane tabbedPane, StudentTab studentTab, ProfesorTab profesorTab, PredmetTab predmetTab, CustomPanel centerPanel) {
		
		tabbedPane.addTab("Studenti", studentTab);
		tabbedPane.addTab("Profesori", profesorTab);
		tabbedPane.addTab("Predmeti", predmetTab);
		
		centerPanel.setLayout(new GridLayout(1,1));
		centerPanel.add(tabbedPane);
		
	}
	
	private void buildFrame(CustomPanel tbStudent, CustomPanel tbProfesor, CustomPanel tbPredmet, CustomPanel centerPanel) {
		
		this.add(BorderLayout.NORTH, tbPredmet);
		this.add(BorderLayout.NORTH, tbProfesor);
		this.add(BorderLayout.NORTH, tbStudent); // mora zadnje
		this.add(BorderLayout.CENTER, centerPanel);	
		this.add(BorderLayout.SOUTH, new MyStatusBar(1440, 45, Color.YELLOW));
		
	}
	
	private void decorateToolbars(CustomPanel tbStudent, CustomPanel tbProfesor, CustomPanel tbPredmet) {
		
		// Toolbar za studente
		tbStudent.setLayout(new BoxLayout(tbStudent, BoxLayout.X_AXIS));
		tbStudent.add((new MyToolbarStudent(this)));
		// Toolbar za profesore
		tbProfesor.setLayout(new BoxLayout(tbProfesor,BoxLayout.X_AXIS));
		tbProfesor.add((new MyToolbarProfesor(this)));
		// Toolbar za predmete
		tbPredmet.setLayout(new BoxLayout(tbPredmet, BoxLayout.X_AXIS));
		tbPredmet.add(new MyToolbarPredmet(this));
	
	}
	
	private void execTabListener(JTabbedPane tabbedPane, CustomPanel tbStudent,CustomPanel tbProfesor, CustomPanel tbPredmet) {
		
		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				
				if (tbStudent.isVisible() == false && 
						tabbedPane.getSelectedIndex() == 0 && 
						(tbProfesor.isVisible() == true || tbPredmet.isVisible() == true)) {
				
					tbStudent.setVisible(true);
					tbPredmet.setVisible(false);
					tbProfesor.setVisible(false);
					add(tbStudent, BorderLayout.NORTH);	
				}
				if (tbProfesor.isVisible() == false && 
						tabbedPane.getSelectedIndex() == 1 && 
						(tbStudent.isVisible() == true || tbPredmet.isVisible() == true)) {
					
					tbStudent.setVisible(false);
					tbPredmet.setVisible(false);
					tbProfesor.setVisible(true);
					add(tbProfesor, BorderLayout.NORTH);
				}
				if (tbPredmet.isVisible() == false && 
						tabbedPane.getSelectedIndex() == 2 && 
						(tbStudent.isVisible() == true || tbProfesor.isVisible() == true)) {
					
					tbStudent.setVisible(false);
					tbPredmet.setVisible(true);
					tbProfesor.setVisible(false);
					add(tbPredmet, BorderLayout.NORTH);
				}
			}	
		});
		
	}
	
	private void execDefaultToolkit() {
		
			// 	rezolucija 3/4*width x 3/4*height
			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension screenSize = tk.getScreenSize();
			int height = screenSize.height;
			int width = screenSize.width;
			width = width * 3/ 4 ; 
			height = height * 3/4 ; 
			setSize(width, height); 
			setTitle("Studentska sluzba");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
		
	}
	
}
