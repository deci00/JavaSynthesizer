package org.cirillo.synthesizer;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/*
 * @author Dennis Cirillo
 */

public class Instruments {
	
	private static Instruments instance = null;

	private Instrument[] instrument;
	private Synthesizer synthesizer;
	private int selectedInstrument = 0;
	
	private Instruments() {}
	
	public static Instruments getInstance() {
		if(instance==null) {
			instance = new Instruments();
		}
		return instance;
	}

	public void selectInstrument(char val) {

		MSynthesizer msynth = MSynthesizer.getInstance();
		try {

			//Get available instruments from synthesizer soundbank
			synthesizer = MidiSystem.getSynthesizer();
			instrument = synthesizer.getDefaultSoundbank().getInstruments();

			switch(val) {
			case '+':
				//Select next instrument
				
				if(selectedInstrument < instrument.length-1) {    				
					selectedInstrument++;
					msynth.loadInstrument(instrument[selectedInstrument]);
				}
				break;
			case '-':
				//Select previous instrument
				if(selectedInstrument != 0) {
					selectedInstrument--;
					msynth.loadInstrument(instrument[selectedInstrument]);
				}
				break;	
			}

			// Show selected instrument in instrument display
			InstrumentsPanel.displayInstrument(instrument[selectedInstrument].getName().toString() + " " + selectedInstrument);

		} catch(MidiUnavailableException e) {
			// New Exception handler
			new ExceptionHandler(3, "Midi unavailable", e, true);
			
		}
	}	
}