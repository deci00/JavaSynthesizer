package org.cirillo.synthesizer;

import javax.swing.UIManager;
/*
 * @author Dennis Cirillo
 */

public class Main {

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			// Do nothing, native laf will autoload
		}
		SynthGui.getInstance().visible(true);
	}
}


