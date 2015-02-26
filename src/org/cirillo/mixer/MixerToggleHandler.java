// currently no used, it handles toggle event on mixer toggle buttons

package org.cirillo.mixer;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MixerToggleHandler implements ItemListener {

	/*
	 * @author Dennis Cirillo
	 */

	@Override
	public void itemStateChanged(ItemEvent e) {
		switch (e.getStateChange()) {
		case 1:
			openChannel();
			break;
		case 2:
			closeChannel();
		}
	}
		
	public void openChannel() {
	// do stuff later 
	}
	public void closeChannel() {
	// do stuff later	
	}
}
