package org.cirillo.synthesizer;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/*
 * @author Dennis Cirillo
 */

public class MSynthesizer {

	private static MSynthesizer instance = null;
	private Synthesizer synth;
	private MidiChannel[] midiChannels;

	private int keyVelocity = 600; // Simulates how hard the key is hit 1-1000
	private int channel = 1; // default channel 1-16

	private static boolean isPlaying = false;

	public static MSynthesizer getInstance() {
		if(instance==null) {
			instance = new MSynthesizer();
		}
		return instance;
	}

	private MSynthesizer() {
		create();
	}

	private void create() {
		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
			midiChannels = synth.getChannels();
		}
		// Create new exceptionhandler if unable to start synthesizer and exit program
		catch(MidiUnavailableException e) {
			new ExceptionHandler(3, "Unable to start synthesizer", e, true);
		}
	}

	// Change instrument in current channel
	public void loadInstrument(Instrument instr) {
		midiChannels[channel].programChange(instr.getPatch().getProgram());
	}

	// Play note when key is stroked
	public void playNote(int note) {
		isPlaying = true;
		midiChannels[channel].noteOn(note, keyVelocity);			

		// Create new Notes object for convert midikey numbers to notes
		Notes notes = new Notes();
		notes.printOctave(note);
	}

	public void playDrum(int note, int ch) {
		midiChannels[ch].noteOn(note, keyVelocity);
	}

	public void stopDrum(int note, int ch) {
		midiChannels[ch].noteOff(note, keyVelocity);
	}

	// Stop note when key is released
	public void stopNote(int note) {
		isPlaying = false;
		midiChannels[channel].noteOff(note, keyVelocity);
		System.out.println("playing in channel: " + channel);
	}

	// Set pitch bend to active channel
	public void setPitch(int pitch) {
		if(isPlaying){
			midiChannels[channel].setPitchBend(pitch);
		}
	}

	// Stops all sound on channel
	public void stop() {
		midiChannels[channel].allSoundOff();
	}

	// Set channel notes will be played in 
	public void setMixerInput(int ch, int volume) {
		try {
			midiChannels[ch].controlChange(7, volume);
		}catch(Exception e) {
			new ExceptionHandler(1, "Mixer failed in channel("+ch+")", e, true);
		}
	}

	// Select active midichannel
	public void setChannel(char val) {
		switch (val) {
		case '-':
			if(channel > 0) {
				channel--;
			}
			break;
		case '+':
			if(channel < 15) {
				channel++;
			}
			break;
		}
		InstrumentsPanel.getInstance().displayChannel(channel);
	}
	
	public int getChannel() {
		return channel;
	}
}
