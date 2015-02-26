package org.cirillo.mixer;

import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/*
 * @author Dennis Cirillo
 */

public class MixerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public static ArrayList<JPanel> channelPanels;
	
	private final int CHANNELS = 16;
	private final int SPACE_BETWEEN_CHANNELS = 32;
	
	private final int CHANNEL_HEIGHT = 190;
	private final int CHANNEL_WIDTH = 32;
	private final int CHANNEL_TOP_MARGIN = 1;

	
	public MixerPanel() {
		
		// New arraylist for midichannel panels
		channelPanels = new ArrayList<JPanel>();

		// Make panel transparent
		setOpaque(false);
		
		// Sets x position for channel control
		int x = 0;
		
		// Create channel and add to mixerpanel
		for (int channel = 0; channel < CHANNELS; channel++) {
			channelPanels.add( new MixerChannelPanel(channel));
			channelPanels.get(channel).setBorder( new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			channelPanels.get(channel).setBounds(x, CHANNEL_TOP_MARGIN, CHANNEL_WIDTH, CHANNEL_HEIGHT);
			add(channelPanels.get(channel));
			x += SPACE_BETWEEN_CHANNELS;
		}
	}
}
