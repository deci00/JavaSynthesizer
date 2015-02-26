// Synth aboutbox, not finished

package org.cirillo.synthesizer;

/*
 * @author Dennis Cirillo 
 */

import java.awt.Dimension;
import javax.swing.JDialog;

public class SynthAboutBox extends JDialog {

	private static final long serialVersionUID = 1L;
	
	public SynthAboutBox() {
		setTitle("About Cirillo Synthesizer");
		setSize( new Dimension(250, 300));
		setLocationRelativeTo(SynthGui.getInstance().getSystemPanel());
	}
}