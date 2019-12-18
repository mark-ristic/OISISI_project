package studentskasluzba.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 153782876756455625L;

// Parametar je naziv frame-a
MainFrame(String naziv){
		
	// Probald kovetni az en formatomat, hogy ne legyen konfliktus
		super(naziv);
		
		// MainFrame - rezolucija 3/4*width x 3/4*height
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		
		int height = screenSize.height;
		int width = screenSize.width;
		width = width * 3/ 4 ; 
		height = height * 3/4 ; 
		setSize(width, height); 
		// setTitle("Studentska sluzba");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		CustomPanel centerPanel = new CustomPanel(1440, 630, Color.BLUE);
		
		// Toolbar paneli
		CustomPanel tbStudent = new CustomPanel(1440, 55, Color.CYAN);
		// Ezt a 2-t kesobb fogjuk hasznalni
		CustomPanel tbProfesor = new CustomPanel(1440, 55, Color.RED);
		CustomPanel tbPredmet = new CustomPanel(1440, 55, Color.GREEN);
		
		// tabovi
		StudentTab studentTab = new StudentTab();
		ProfesorTab profesorTab = new ProfesorTab();
		PredmetTab predmetTab = new PredmetTab();
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Studenti", studentTab);
		tabbedPane.addTab("Profesori", profesorTab);
		tabbedPane.addTab("Predmeti", predmetTab);
		
		// MainFrame ce se sastojati od 3 glavna panela
		centerPanel.add(tabbedPane);
		
		this.add(BorderLayout.NORTH, tbStudent);
		this.add(BorderLayout.CENTER, centerPanel);
		// Ovo ce biti mesto za statusbar kasnije
		this.add(BorderLayout.SOUTH, new CustomPanel(1440, 45, Color.YELLOW));

	}
	
}
