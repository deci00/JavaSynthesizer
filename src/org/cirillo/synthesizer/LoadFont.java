package org.cirillo.synthesizer;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;

/*
 * @author Dennis Cirillo
 */

public class LoadFont {

	private static Font digitalFont;

	private LoadFont(){};

	public static Font DS_DIGITAL(float setSize) {
		try {
			File f = new File("res/font/DS-DIGII.TTF");
			FileInputStream in = new FileInputStream(f);
			Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, in);
			digitalFont = dynamicFont.deriveFont(setSize);
		} 

		catch(Exception e) {
			new ExceptionHandler(1, "Cant load truetype font", e, true);
		}
		return digitalFont;
	}
}
