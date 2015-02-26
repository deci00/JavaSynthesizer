package org.cirillo.synthesizer;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;

/*
 * @author Dennis Cirillo
 */

public class Display extends JPanel {
	
	private static Display instance = null;

	private static final long serialVersionUID = 1L;
	private static JTextField displayHistory;
	private static JTextField midiInfoOutputField;
	
	private Display() {
		create();
	}
	
	public static Display getInstance(){
		if(instance==null){
			instance = new Display();
		}
		return instance;
	}
	
	private void create() {
		// Set Panel background color
		setBackground(new Color(91, 111, 138));
		
		// Create new textfield for displaying pressed note
		displayHistory = new JTextField();
		displayHistory.setHorizontalAlignment(SwingConstants.RIGHT);
		displayHistory.setEditable(false);
		displayHistory.setToolTipText("Display history of octavs");
		displayHistory.setForeground(Color.WHITE);
		displayHistory.setFont( LoadFont.DS_DIGITAL(28) );
		displayHistory.setBackground(new Color(91, 111, 138));
		displayHistory.setColumns(10);
		displayHistory.setBorder( null );
		
		// Create new textfield for displaying midi device info
		midiInfoOutputField = new JTextField();
		midiInfoOutputField.setBorder( null );
		midiInfoOutputField.setForeground(Color.WHITE);
		midiInfoOutputField.setText("Midi device: OFF ");
		midiInfoOutputField.setEditable(false);
		midiInfoOutputField.setBackground(new Color(91, 111, 138));
		midiInfoOutputField.setColumns(10);
		
		// Add textfields to panel grouplayout
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(displayHistory, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(midiInfoOutputField, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
							.addGap(119))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addComponent(midiInfoOutputField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(displayHistory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
	
	// Show pressed note on display
	public void setHistory(String history) {
		displayHistory.setText(history);
	}
	

	//Showing midi device info on display
	public void setMidiInfo(String device) {
		midiInfoOutputField.setText("Midi device: "+device);
	}
	
}
