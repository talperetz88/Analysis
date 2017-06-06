package GUI;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JToolBar;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class DisplayAreasOfInterestGUI extends JFrame {
	File[] listOfFiles;
	public DisplayAreasOfInterestGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100,100,722,533);
		getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(229, 77, 139, 20);
		getContentPane().add(comboBox);
		
		JButton btnDisplay = new JButton("Display");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DisplayImages n = new DisplayImages("C:\\תמונות\\תמנות\\" + comboBox.getSelectedItem().toString()+"\\");
			}
		});
		btnDisplay.setBounds(229, 140, 122, 35);
		getContentPane().add(btnDisplay);
		
		JLabel lblSelectTheWanted = new JLabel("select the wanted area ");
		lblSelectTheWanted.setBounds(10, 58, 156, 54);
		getContentPane().add(lblSelectTheWanted);
		
		File folder = new File("C:\\תמונות\\תמנות\\");
		 listOfFiles = folder.listFiles();
		String fileName = null;
		for (int i = 0; i < listOfFiles.length; i++){
			if(listOfFiles[i].isDirectory()){
				comboBox.addItem(listOfFiles[i].getName());
			}
		}
		this.setVisible(true);
	}

}
