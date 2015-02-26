package org.cirillo.synthesizer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/*
 * @author Dennis Cirillo
 */

public class SynthMenu extends JMenuBar implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	public SynthMenu() {

		// Create new menu and menuitems
		JMenu mnFile = new JMenu("Synthesizer");
		add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		mntmExit.setActionCommand("exit");
		mntmExit.addActionListener( this );

		JMenu mnOptions = new JMenu("Options");
		add(mnOptions);

		JLabel lblNoOptionsYet = new JLabel("No options yet!");
		mnOptions.add(lblNoOptionsYet);

		JMenu mnHelp = new JMenu("Help");
		add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		mntmAbout.setActionCommand("about");
		mntmAbout.addActionListener( this );
	}

	// Get menu action
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "exit":
			exit();
			break;
		case "about":
			aboutBox();
			break;
		}
	}

	// Show exit dialog and exit program if Yes_option
	public void exit() {
		int dialogresult = JOptionPane.showConfirmDialog(SynthGui.getInstance().getSystemPanel(),"Are you sure you want to exit?","Exit synthesizer", JOptionPane.OK_CANCEL_OPTION);
		if (dialogresult == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	// Show about dialog
	public void aboutBox(){
		JDialog about = new SynthAboutBox();
		about.setVisible(true);
	}
}
