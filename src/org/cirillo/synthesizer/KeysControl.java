package org.cirillo.synthesizer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import java.awt.Font;

/*
 * @author Dennis Cirillo
 */

public class KeysControl extends JPanel implements ItemListener {

	private static final long serialVersionUID = 1L;
	
	private static KeysControl instance = null;

	private Image image = null;

	private KeysControl() {
		create();
	}
	
	public static KeysControl getInstance() {
		if(instance==null) {
			instance = new KeysControl();
		}
		return instance;
	}
	
	private void create() {
		// Panel background image
		image = new ImageIcon("img/rightPanel_bg.png").getImage();

		// Set panel border and layout
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(null);

		// New togglebutton for select/deselect mousebutton autohold
		JToggleButton tglKeyHoldBtn = new JToggleButton("");
		tglKeyHoldBtn.setSelectedIcon(new ImageIcon("img/blackBtn_on.png"));
		tglKeyHoldBtn.setIcon(new ImageIcon("img/blackBtn_off.png"));
		tglKeyHoldBtn.setBounds(10, 42, 50, 20);
		tglKeyHoldBtn.setActionCommand("tglKeyHold");
		tglKeyHoldBtn.addItemListener(this);
		tglKeyHoldBtn.setFocusPainted(false);
		tglKeyHoldBtn.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(tglKeyHoldBtn);

		// new Label for mouse autohold togglebutton
		JLabel lblAutohold = new JLabel("Autohold");
		lblAutohold.setToolTipText("Simulates mouse left key always pressed");
		lblAutohold.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutohold.setBackground(Color.WHITE);
		lblAutohold.setFont(new Font("Arial", Font.PLAIN, 11));
		lblAutohold.setForeground(Color.LIGHT_GRAY);
		lblAutohold.setBounds(11, 63, 46, 14);
		add(lblAutohold);

		// new togglebutton for turn mididevice on/off
		JToggleButton tglMidiInputBtn = new JToggleButton("");
		tglMidiInputBtn.setSelectedIcon(new ImageIcon("img/blackBtn_on.png"));
		tglMidiInputBtn.setIcon(new ImageIcon("img/blackBtn_off.png"));
		tglMidiInputBtn.setFocusPainted(false);
		tglMidiInputBtn.setBorder(new LineBorder(new Color(0, 0, 0)));
		tglMidiInputBtn.setActionCommand("tglKeyHold");
		tglMidiInputBtn.setBounds(10, 83, 50, 20);
		tglMidiInputBtn.addItemListener( new MidiEventHandler() );
		add(tglMidiInputBtn);

		// new label for mididevice togglebutton
		JLabel lblMidi = new JLabel("Midi input");
		lblMidi.setHorizontalAlignment(SwingConstants.CENTER);
		lblMidi.setForeground(Color.LIGHT_GRAY);
		lblMidi.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMidi.setBackground(Color.WHITE);
		lblMidi.setBounds(11, 105, 46, 14);
		add(lblMidi);
	}

	// Mousebutton autohold selection
	@Override
	public void itemStateChanged(ItemEvent e) {
		switch (e.getStateChange()) {
		case 1:
			// Select autohold on mousebutton
			SynthKeys.setMouseHold(true);
			break;
		case 2:
			// Deselect autohold on mousebutton
			SynthKeys.setMouseHold(false);
		default:
			break;
		}
	}

	// Paint panel background
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
	}
}
