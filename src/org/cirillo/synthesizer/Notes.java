package org.cirillo.synthesizer;

/*
 * @author Dennis Cirillo
 */

public class Notes {
	
	private String notes = "C C#D D#E F F#G G#A A#B ";
	private int octave;
	private String note;

	// Convert midi note number to note
	public void printOctave(int noteNum) {
		
		
		octave = noteNum / 12 - 1;
        note = notes.substring((noteNum % 12) * 2, (noteNum % 12) * 2 + 2);

        // Show note in main display
        Display display = Display.getInstance();
        display.setHistory(note+octave);
	}
}