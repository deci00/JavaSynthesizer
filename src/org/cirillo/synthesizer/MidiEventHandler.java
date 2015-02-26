package org.cirillo.synthesizer;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/*
 * @author Dennis Cirillo
 */

public class MidiEventHandler implements ItemListener {

	@Override
	public void itemStateChanged(ItemEvent e) {
		switch (e.getStateChange()) {
		case 1:
			new MidiInput();
			break;
		case 2:
			SynthKeys.setMouseHold(false);
		default:
			break;
		}	
	}
}
