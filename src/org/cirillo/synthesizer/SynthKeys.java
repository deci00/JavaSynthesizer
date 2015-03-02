package org.cirillo.synthesizer;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

/*
 * @author Dennis Cirillo
 */

public class SynthKeys extends JLayeredPane implements MouseListener, MouseWheelListener {

	private static final long serialVersionUID = 1L;

	// hold number of each key
	private int[] white_keys = new int[29];
	private int[] black_keys = new int[20];

	
	// Notes
	private final List<Integer> black_notes = Arrays.asList(37,39,42,44,46,49,51,54,56,58,61,63,66,68,70,73,75,78,80,82);
	private final List<Integer> white_notes = Arrays.asList(36,38,40,41,43,45,47,48,50,52,53,55,57,59,60,62,64,65,67,69,71,72,74,76,77,79,81,83,84);

	// Count what note each key will have
	private int cw = 0;
	private int cb = 0;
	
	// Key images
	private final Icon w_keysImg = new ImageIcon("img/white_key.png");
	private final Icon b_keysImg = new ImageIcon("img/black_key.png");
	
	
	MSynthesizer synth = MSynthesizer.getInstance();

	// Mouse input variables
	private static boolean mouseHold = false;
	private boolean isHold = false;

	// Mousewheel pitch variable
	private int pitchlevel = 64;
	
	public SynthKeys() {
		generateKeys();
	}

	public void generateKeys() {

		// Setting panel layout to null, Layout is set in SynthGui
		setLayout(null);

		// Create white keys and add to panel
		for (int i = 0; i < white_keys.length; i++) {
			JButton w_key = new JButton(w_keysImg);
			w_key.setName(Integer.toString(white_notes.get(cw)));
			w_key.setSize( new Dimension(25,140));
			w_key.setLocation(3 + i * 25, 3);
			w_key.setBackground(Color.white);
			w_key.setFocusPainted(false);
			w_key.addMouseListener(this);
			w_key.addMouseWheelListener(this);
			add(w_key, 0, -1);

			// Counter for adding notes to keys
			cw++; 
		}

		// Create black keys and add to panel
		for (int i = 0; i < black_keys.length+7; i++) {
			
			//Spacing black keys
			int j = i % 7;
			if (j == 2 || j == 6)
				continue;
			
			JButton b_key = new JButton(b_keysImg);	
			b_key.setName(Integer.toString(black_notes.get(cb)));
			b_key.setSize( new Dimension(21,100));			
			b_key.setLocation(3 + i * 25+15, 3);		
			b_key.setBackground(Color.black);
			b_key.setFocusPainted(false);
			b_key.addMouseListener(this);
			b_key.addMouseWheelListener(this);
			add(b_key, 1, -1);

			//counter for adding notes to keys
			cb++; 
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//not used
	}

	@Override
	// Locks keys in pressed mode when hoover
	public void mouseEntered(MouseEvent e) {
		if(mouseHold || isHold) {
			System.out.println("mouse pressed: " + e.getComponent().getName());
			synth.playNote(Integer.parseInt(e.getComponent().getName()));			
		}
	}

	// Releases keys in pressed mode when hover
	@Override
	public void mouseExited(MouseEvent e) {
		if(mouseHold || isHold) {
			System.out.println("mouse released: " + e.getComponent().getName());
			synth.stopNote(Integer.parseInt(e.getComponent().getName()));
		}
	}

	// Simulates synth key pressed
	@Override
	public void mousePressed(MouseEvent e) {
		isHold = true;
		System.out.println("mouse pressed: " + e.getComponent().getName());
		synth.playNote(Integer.parseInt(e.getComponent().getName()));
	}

	// Simulates synthkey released
	@Override
	public void mouseReleased(MouseEvent e) {
		isHold = false;
		System.out.println("mouse released: " + e.getComponent().getName());
		synth.stopNote(Integer.parseInt(e.getComponent().getName()));
		synth.stop();
	}
	
	// Sets synth keys to auto pressed hold
	public static void setMouseHold(boolean hld) {
		mouseHold = hld;
	}

	// Pitch bend with mousewheel
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int wheel = e.getWheelRotation();

		switch (wheel) {
		case -1:
			if(pitchlevel<126) {
				pitchlevel+=7;
			}
			break;
		case 1:
			if(pitchlevel>=6) {
				pitchlevel-=7;				
			}
			break;
		default:
			pitchlevel = 64;				
			break;
		}

		//Set pitch bend on synth
		MSynthesizer.getInstance().setPitch(pitchlevel*163);

		//Change value of pitchbend slider
		Pitch.getInstance().slidervalue(pitchlevel);
	}
}
