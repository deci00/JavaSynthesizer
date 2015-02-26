package org.cirillo.synthesizer;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;
import org.cirillo.mixer.*;
import org.cirillo.drums.DrumMachinePanel;

/*
 * @author Dennis Cirillo
 */

public class SynthGui {
	
	private static SynthGui instance = null;
	
	private JFrame synthFrame;	
	private JPanel systemPanel;

	private SynthGui() {
		initialize();	
	}
	
	public static SynthGui getInstance() {
		if(instance==null){
			instance = new SynthGui();
		}
		return instance;
	}

	private void initialize() {
		
		synthFrame = new JFrame();
		synthFrame.setResizable(false);
		synthFrame.getContentPane().setBackground(Color.DARK_GRAY);
		synthFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		synthFrame.setTitle("Cirillo Synthesizer");
		synthFrame.setBounds(100, 100, 955, 500);
		synthFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set synth icon
		Image icon = Toolkit.getDefaultToolkit().getImage("img/synth_icon.png");
		synthFrame.setIconImage(icon);
		
		systemPanel = new JPanel();
		systemPanel.setBackground(Color.DARK_GRAY);
		synthFrame.getContentPane().add(systemPanel, BorderLayout.NORTH);
		
		PlcHldrPanel1 plcHldrPanel1 = new PlcHldrPanel1();
		plcHldrPanel1.setBorder(null);
		
		PlcHldrPanel2 plcHldrPanel2 = new PlcHldrPanel2();
		plcHldrPanel2.setBorder(null);
		
		GroupLayout gl_systemPanel = new GroupLayout(systemPanel);
		gl_systemPanel.setHorizontalGroup(
			gl_systemPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(plcHldrPanel2, GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE)
				.addComponent(plcHldrPanel1, GroupLayout.PREFERRED_SIZE, 949, Short.MAX_VALUE)
		);
		gl_systemPanel.setVerticalGroup(
			gl_systemPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_systemPanel.createSequentialGroup()
					.addComponent(plcHldrPanel2, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(plcHldrPanel1, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
		);
		
	
		MixerPanel mixerPanel = new MixerPanel();
		mixerPanel.setBorder(null);
		GroupLayout gl_mixer = new GroupLayout(mixerPanel);
		gl_mixer.setHorizontalGroup(
			gl_mixer.createParallelGroup(Alignment.LEADING)
				.addGap(0, 516, Short.MAX_VALUE)
		);
		gl_mixer.setVerticalGroup(
			gl_mixer.createParallelGroup(Alignment.LEADING)
				.addGap(0, 126, Short.MAX_VALUE)
		);
		mixerPanel.setLayout(gl_mixer);
		
		
		DrumMachinePanel drumMachinePanel = new DrumMachinePanel();
		GroupLayout gl_plcHldrPanel2 = new GroupLayout(plcHldrPanel2);
		gl_plcHldrPanel2.setHorizontalGroup(
			gl_plcHldrPanel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_plcHldrPanel2.createSequentialGroup()
					.addComponent(mixerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(drumMachinePanel, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_plcHldrPanel2.setVerticalGroup(
			gl_plcHldrPanel2.createParallelGroup(Alignment.TRAILING)
				.addComponent(mixerPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING, gl_plcHldrPanel2.createSequentialGroup()
					.addGap(19)
					.addComponent(drumMachinePanel, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
		);
		plcHldrPanel2.setLayout(gl_plcHldrPanel2);
		
	
		Display displayPanel = Display.getInstance();
		displayPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(128, 128, 128), new Color(128, 128, 128), new Color(0, 0, 0), new Color(0, 0, 0)));
		
		
		InstrumentsPanel instrumentsPanel = InstrumentsPanel.getInstance();
		instrumentsPanel.setBackground(Color.GRAY);
		GroupLayout gl_plcHldrPanel1 = new GroupLayout(plcHldrPanel1);
		gl_plcHldrPanel1.setHorizontalGroup(
			gl_plcHldrPanel1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_plcHldrPanel1.createSequentialGroup()
					.addGap(312)
					.addComponent(displayPanel, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
					.addComponent(instrumentsPanel, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
		);
		gl_plcHldrPanel1.setVerticalGroup(
			gl_plcHldrPanel1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_plcHldrPanel1.createSequentialGroup()
					.addGroup(gl_plcHldrPanel1.createParallelGroup(Alignment.LEADING)
						.addComponent(instrumentsPanel, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
						.addGroup(gl_plcHldrPanel1.createSequentialGroup()
							.addContainerGap()
							.addComponent(displayPanel, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		plcHldrPanel1.setLayout(gl_plcHldrPanel1);
		systemPanel.setLayout(gl_systemPanel);
		
		// Create new pitch panel and add to gui ------------------------ //
		Pitch pitchPanel = Pitch.getInstance();
		synthFrame.getContentPane().add(pitchPanel, BorderLayout.WEST);
		GroupLayout gl_leftPanel = new GroupLayout(pitchPanel);
		gl_leftPanel.setHorizontalGroup(
			gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 65, Short.MAX_VALUE)
		);
		gl_leftPanel.setVerticalGroup(
			gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 122, Short.MAX_VALUE)
		);
		pitchPanel.setLayout(gl_leftPanel);
		
		// Create new Keyscontrol panel and add to gui --------------- //
		KeysControl keysControl = KeysControl.getInstance();
		keysControl.setBackground(Color.DARK_GRAY);
		synthFrame.getContentPane().add(keysControl, BorderLayout.EAST);
		GroupLayout gl_rightPanel = new GroupLayout(keysControl);
		gl_rightPanel.setHorizontalGroup(
			gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 150, Short.MAX_VALUE)
		);
		gl_rightPanel.setVerticalGroup(
			gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 122, Short.MAX_VALUE)
		);
		keysControl.setLayout(gl_rightPanel);
		
		// Create synth keys and add to centerpanel
		SynthKeys keys = new SynthKeys();
		keys.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		synthFrame.getContentPane().add(keys, BorderLayout.CENTER);
		
		// Create new synth menu
		JMenuBar menuBar = new SynthMenu();
		synthFrame.setJMenuBar(menuBar);
	}
	
	public JPanel getSystemPanel() {
		return systemPanel;
	}
	
	public void visible(boolean visible) {
		synthFrame.setVisible(visible);
	}
}
