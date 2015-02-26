package org.cirillo.drums;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import org.cirillo.synthesizer.ExceptionHandler;
import org.cirillo.synthesizer.MSynthesizer;

/*
 * @author Dennis Cirillo
 */

public class DrumLoop implements Runnable {

	private MSynthesizer synth = MSynthesizer.getInstance();
	
	public static long bpm = 130;
	private final int ONE_SECOND = 1000;
	private final int ONE_MINUTE = 60;
	
	private long speed;
	public static boolean running;
	
	public static boolean isMidi = false;
	private final int MIDI_CHANNEL = 9;
	
	// Midi notes
	private final int KICK = 36;
	private final int CLAP = 38;
	private final int HAT = 69;
	private final int SNARE = 74;

	// Sequence patterns
	private final int PATTERNS = 16;
	private ArrayList<JToggleButton> kickPatterns;
	private ArrayList<JToggleButton> clapPatterns;
	private ArrayList<JToggleButton> hatPatterns;
	private ArrayList<JToggleButton> snarePatterns;

	// Drums
	private Drums kickDrum;
	private Drums clapDrum;
	private Drums hatDrum;
	private Drums snareDrum;
	

	
	private ImageIcon activeButtonImage;
	
	// Get all drumpattern arraylists
	public DrumLoop(ArrayList<JToggleButton> kick, ArrayList<JToggleButton> clap, ArrayList<JToggleButton> hat, ArrayList<JToggleButton> snare,  boolean run) {

		kickPatterns = kick;
		clapPatterns = clap;
		hatPatterns = hat;
		snarePatterns = snare;
		running = run;
		activeButtonImage = new ImageIcon("img/drmBtn_active2.png");
	}

	@Override
	public void run() {
	
		while(running) {

			for (int step = 0; step < PATTERNS; step++) {

				if(!running) {
					break;
				}
				
				togglePatterns(step);
				createSounds(step);
				
				playKick(step);
				playClap(step);
				playHat(step);
				playSnare(step);

				setSpeed();

				if(isMidi) {
					stopMidi();
				}
				
				unTogglePatterns(step);
			}
		}
	}

	private void setSpeed() {
		speed = (ONE_SECOND * ONE_MINUTE / bpm) / 4;
		
		try {
			Thread.sleep(speed);
		} catch(InterruptedException e) {
			new ExceptionHandler(1, "Unkown error in drum sequencer", e, true);
		}
	}

	private void unTogglePatterns(int step) {

		kickPatterns.get(step).setBorder(new LineBorder(new Color(0, 0, 0)));
		kickPatterns.get(step).setIcon(new ImageIcon("img/drmBtn_off_alt.png"));
		kickPatterns.get(step).setSelectedIcon(new ImageIcon("img/drmBtn_on_alt.png"));

		clapPatterns.get(step).setBorder(new LineBorder(new Color(0, 0, 0)));
		clapPatterns.get(step).setIcon(new ImageIcon("img/drmBtn_off_alt.png"));
		clapPatterns.get(step).setSelectedIcon(new ImageIcon("img/drmBtn_on_alt.png"));

		hatPatterns.get(step).setBorder(new LineBorder(new Color(0, 0, 0)));
		hatPatterns.get(step).setIcon(new ImageIcon("img/drmBtn_off_alt.png"));
		hatPatterns.get(step).setSelectedIcon(new ImageIcon("img/drmBtn_on_alt.png"));

		snarePatterns.get(step).setBorder(new LineBorder(new Color(0, 0, 0)));
		snarePatterns.get(step).setIcon(new ImageIcon("img/drmBtn_off_alt.png"));
		snarePatterns.get(step).setSelectedIcon(new ImageIcon("img/drmBtn_on_alt.png"));
	}

	private void stopMidi() {
		synth.stopDrum(KICK, MIDI_CHANNEL);
		synth.stopDrum(CLAP, MIDI_CHANNEL);
		synth.stopDrum(HAT, MIDI_CHANNEL);
		synth.stopDrum(SNARE, MIDI_CHANNEL);
	}

	private void playSnare(int step) {
		if(snarePatterns.get(step).isSelected()) {
			if(isMidi){
				synth.playDrum(SNARE, MIDI_CHANNEL);
			} else {
				snareDrum.play();						
			}
		}
	}

	private void playHat(int step) {
		if(hatPatterns.get(step).isSelected()) {
			if(isMidi){
				synth.playDrum(HAT, MIDI_CHANNEL);
			} else {
				hatDrum.play();						
			}
		}
	}

	private void playClap(int step) {
		if(clapPatterns.get(step).isSelected()) {
			if(isMidi){
				synth.playDrum(CLAP, MIDI_CHANNEL);
			} else {
				clapDrum.play();						
			}
		}
	}

	private void playKick(int step) {
		if(kickPatterns.get(step).isSelected()) {
			if(isMidi){
				synth.playDrum(KICK, MIDI_CHANNEL);
			} else {
				kickDrum.play();						
			}
		}
	}

	private void createSounds(int step) {
		kickDrum = new Drums(kickPatterns.get(step).getActionCommand());
		clapDrum = new Drums(clapPatterns.get(step).getActionCommand());
		hatDrum = new Drums(hatPatterns.get(step).getActionCommand());
		snareDrum = new Drums(snarePatterns.get(step).getActionCommand());
	}

	private void togglePatterns(int step) {
		kickPatterns.get(step).setIcon( activeButtonImage );
		kickPatterns.get(step).setSelectedIcon(new ImageIcon("img/drmBtn_active2.png"));

		clapPatterns.get(step).setIcon(new ImageIcon("img/drmBtn_active2.png"));
		clapPatterns.get(step).setSelectedIcon(new ImageIcon("img/drmBtn_active2.png"));

		hatPatterns.get(step).setIcon(new ImageIcon("img/drmBtn_active2.png"));
		hatPatterns.get(step).setSelectedIcon(new ImageIcon("img/drmBtn_active2.png"));

		snarePatterns.get(step).setIcon(new ImageIcon("img/drmBtn_active2.png"));
		snarePatterns.get(step).setSelectedIcon(new ImageIcon("img/drmBtn_active2.png"));
	}
}
