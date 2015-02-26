package org.cirillo.synthesizer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

/*
 * @author Dennis Cirillo 
 */

public class PlcHldrPanel1 extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image image = null;
	
	
	public PlcHldrPanel1() {
		
		// Set layout to null, layout is set in SynthGui
		setLayout(null);
		
		// Set backgorund color
		setBackground(new Color(20,20,20));
		
		// New label for synth logo
		JLabel cirilloLbl = new JLabel("");
		
		// New iconimage for synth logo label
		cirilloLbl.setIcon(new ImageIcon("img/cirillo.png"));
		
		// Synth logo location
		cirilloLbl.setBounds(10, 11, 313, 83);
		
		// Add logo to panel
		add(cirilloLbl);
		
		// New imageicon for placeholder background
		image = new ImageIcon("img/mainBG.png").getImage();
	}

	// Paint placeholder background to panel
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
	}
}
