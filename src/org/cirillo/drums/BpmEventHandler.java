package org.cirillo.drums;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/* 
 *  @Author Dennis Cirillo
 */

public class BpmEventHandler implements ActionListener, MouseWheelListener {

	// Increase sequencer beats per minute by input value
	public void increaseBpm(int gap) {
		if(DrumLoop.bpm < 999) {
			DrumLoop.bpm += gap;
			DrumMachinePanel.setBpmDisplay();
		}
	}
	// Decrease sequencer beats per minute by input value
	public void decreaseBpm(int gap) {
		if(DrumLoop.bpm > 10) {
			DrumLoop.bpm -= gap;
			DrumMachinePanel.setBpmDisplay();			
		}
	}

	// Get action from bpm buttons
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "bpmUp":
			// Increase bpm by 1
			increaseBpm(1);
			break;
		case "bpmDown":
			// Decrease bpm by 1
			decreaseBpm(1);
			break;
		}
	}

	// Get mousewheel movement from bpm input gui
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int wheel = e.getWheelRotation();

		switch (wheel) {
		case 1:
			// Increase bpm by 1 on mousewheel spinning forward
			decreaseBpm(1);
			break;
		case -1:
			// Decrease bpm by 1 on mousewheel spinning backward
			increaseBpm(1);
			break;
		}
	}
}
