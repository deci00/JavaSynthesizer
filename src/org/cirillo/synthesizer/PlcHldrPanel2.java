package org.cirillo.synthesizer;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/*
 * @author Dennis Cirillo
 */

public class PlcHldrPanel2 extends JPanel {

	private static final long serialVersionUID = 1L;

	private Image image = null;

	public PlcHldrPanel2() {
		
		setLayout(null);
		
		// New Imageicon for placeholder background
		image = new ImageIcon("img/plchldr2bg.jpg").getImage();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
	}
}
