package org.cirillo.synthesizer;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

/*
 * @author Dennis Cirillo
 */

public class InstrumentsPanel extends JPanel implements ActionListener, MouseWheelListener {
	
	private static InstrumentsPanel instance = null;

	private static final long serialVersionUID = 1L;
	private static JTextField instrumentDisplay;
	public static JTextField channelDisplay;
	private Instruments instruments = Instruments.getInstance();

	private InstrumentsPanel() {
		create();
	}
	
	public static InstrumentsPanel getInstance() {
		if(instance==null) {
			instance = new InstrumentsPanel();
		}
		return instance;
	}
	
	private void create() {
		setBackground(Color.DARK_GRAY);

		// Create new textfield for displaying selected instrument
		instrumentDisplay = new JTextField();
		instrumentDisplay.setText("Instruments");
		instrumentDisplay.setBounds(16, 10, 144, 20);
		instrumentDisplay.setForeground(Color.WHITE);
		instrumentDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		instrumentDisplay.setFont( LoadFont.DS_DIGITAL(18) );
		instrumentDisplay.setBackground(new Color(91, 111, 138));
		instrumentDisplay.setEditable(false);
		instrumentDisplay.setColumns(10);
		instrumentDisplay.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.GRAY, Color.BLACK, Color.BLACK));
		instrumentDisplay.addMouseWheelListener( this );

		// Create new button for select previous instrument in instruments array
		JButton prevInstrBtn = new JButton("");
		prevInstrBtn.setFocusPainted(false);
		prevInstrBtn.setIcon(new ImageIcon("img/btnPrev.png"));
		prevInstrBtn.addActionListener(this);
		prevInstrBtn.setActionCommand("prevInstr");
		prevInstrBtn.setBounds(160, 10, 20, 20);
		prevInstrBtn.setBorder(new LineBorder(new Color(0, 0, 0)));
		prevInstrBtn.addMouseWheelListener( this );

		// Create new button for select next instrument in instruments array
		JButton nextInstrBtn = new JButton("");
		nextInstrBtn.setFocusPainted(false);
		nextInstrBtn.setIcon(new ImageIcon("img/btnNext.png"));
		nextInstrBtn.addActionListener(this);
		nextInstrBtn.setActionCommand("nextInstr");
		nextInstrBtn.setBounds(180, 10, 20, 20);
		nextInstrBtn.setBorder(new LineBorder(new Color(0, 0, 0)));
		nextInstrBtn.addMouseWheelListener( this );

		//Set panel layout to null and add display and buttons to panel. (Layouts are set in SynthGui class)
		setLayout(null);
		add(instrumentDisplay);
		add(prevInstrBtn);
		add(nextInstrBtn);

		// Make panel transparent for viewing systempanel background
		setOpaque(false);

		// Create new textfield for dipslaying selected midichannel
		channelDisplay = new JTextField();
		channelDisplay.setText("Output CH " + MSynthesizer.getInstance().getChannel());
		channelDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		channelDisplay.setForeground(Color.WHITE);
		channelDisplay.setFont( LoadFont.DS_DIGITAL(18) );
		channelDisplay.setEditable(false);
		channelDisplay.setColumns(10);
		channelDisplay.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.GRAY, Color.BLACK, Color.BLACK));
		channelDisplay.setBackground(new Color(91, 111, 138));
		channelDisplay.setBounds(16, 34, 144, 20);
		add(channelDisplay);

		// Create new button for move to previous midichannel
		JButton prevChl = new JButton("");
		prevChl.setFocusPainted(false);
		prevChl.setIcon(new ImageIcon("img/btnPrev.png"));
		prevChl.setBorder(new LineBorder(new Color(0, 0, 0)));
		prevChl.setActionCommand("prevChl");
		prevChl.addActionListener(this);
		prevChl.setBounds(160, 34, 20, 20);
		add(prevChl);

		// Create new button for move to next midichannel
		JButton nextChl = new JButton("");
		nextChl.setFocusPainted(false);
		nextChl.setIcon(new ImageIcon("img/btnNext.png"));
		nextChl.setBorder(new LineBorder(new Color(0, 0, 0)));
		nextChl.setActionCommand("nextChl");
		nextChl.addActionListener(this);
		nextChl.setBounds(180, 34, 20, 20);
		add(nextChl);

		// Load default instrument (loads first instrument in array on start)
		loadDefaultInstrument();
	}

	// Key actions for selecting instruments and midichannels
	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "prevInstr":
			prevInstrument();
			break;
		case "nextInstr":
			nextInstrument();
			break;
		case "prevChl":
			prevChannel();		
			break;
		case "nextChl":
			nextChannel();
			break;
		}
	}
	// Select next instrument
	public void nextInstrument() {
		instruments.selectInstrument('+');
	}

	// Select previous instrument
	public void prevInstrument() {
		instruments.selectInstrument('-');
	}

	// Select next midichannel
	public void nextChannel() {
		MSynthesizer.getInstance().setChannel('+');
	}

	//Select previous channel
	public void prevChannel() {
		MSynthesizer.getInstance().setChannel('-');
	}

	// Display selected instrument
	public static void displayInstrument(String instr) {
		instrumentDisplay.setText(instr);
	}

	// Loads first instrument on program start
	public void loadDefaultInstrument() {
		instruments.selectInstrument('-');
	}

	// Display selected channel
	public void displayChannel(int ch) {
		channelDisplay.setText("Output CH " + (ch+1) + "   ");
	}

	// Select instrument with mousewheel when hoovering instrumentdisplay and selection buttons
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int wheel = e.getWheelRotation();
		switch (wheel) {
		case 1:
			instruments.selectInstrument('-');
			break;
		case -1:
			instruments.selectInstrument('+');
			break;
		}
	}
}
