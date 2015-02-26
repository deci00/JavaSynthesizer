package org.cirillo.drums;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;

/*
 * @author Dennis Cirillo
 */

public class DrumPatterns {

	
	private ArrayList<JToggleButton> patternBtns;

	// Pattern buttons location
	private int ptnBtnX = 1;
	private int ptnBtnY = 1;
	private int ptnBtnW = 20;
	private int ptnBtnH = 20;
	
	private final int PATTERNS = 16;
	private final int SPACE_BETWEEN_STEPS = 25;

	// Get pattern drum type and pattern vertical location
	public DrumPatterns(String type, int y) {
		// Set pattern vertical location
		ptnBtnY = y;

		// Create new Arraylist for pattern buttons
		patternBtns = new ArrayList<JToggleButton>();

		// Adds pattern buttons to arraylist
		for (int button = 0; button < PATTERNS; button++) {
			patternBtns.add( new JToggleButton());
			patternBtns.get(button).setActionCommand(type);
			patternBtns.get(button).setBounds(ptnBtnX, ptnBtnY, ptnBtnW, ptnBtnH);
			patternBtns.get(button).setIcon(new ImageIcon("img/drmBtn_off_alt.png"));
			patternBtns.get(button).setSelectedIcon(new ImageIcon("img/drmBtn_on_alt.png"));
			patternBtns.get(button).setBorder(new LineBorder(new Color(0, 0, 0)));
			patternBtns.get(button).setFocusPainted(false);
			ptnBtnX += SPACE_BETWEEN_STEPS;
		}
	}


	public ArrayList<JToggleButton> ptnBtns() {
		return patternBtns;
	}

	
	public void resetPatterns() {
		for (int i = 0; i < patternBtns.size(); i++) {
			patternBtns.get(i).setSelected(false);
		}
	}
}
