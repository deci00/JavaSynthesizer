package org.cirillo.synthesizer;

import javax.sound.midi.*;
import java.util.List;

/*
 * @author Dennis Cirillo
 * Util for midi keyboard input
 */

public class MidiInput implements Receiver{

	public MidiInput() {
	
		MidiDevice device;
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
		
		for (int i = 0; i < infos.length; i++) {
			try {
				
				// Add mididevices to array
				device = MidiSystem.getMidiDevice(infos[i]);
				
				// Show device name to display
				Display display = Display.getInstance();
				display.setMidiInfo(infos[1].toString());

				// Get all transmitters
				List<Transmitter> transmitters = device.getTransmitters();
			
				// Set receivers for all transmitters
				for(int j = 0; j<transmitters.size();j++) {
					transmitters.get(j).setReceiver( this );
				}

				Transmitter trans = device.getTransmitter();
				trans.setReceiver(this);

				device.open();
			

			
			} catch (MidiUnavailableException e) {
				new ExceptionHandler(1, "Midi keyboard: ", e, false);
			}
		}
	}
	
	@Override
	public void close() {
	
	}
	// TODO convert midi bytedata to notes
	@Override
	public void send(MidiMessage message, long timeStamp) {
		System.out.println("midi: " + message.getMessage());
	}
}
