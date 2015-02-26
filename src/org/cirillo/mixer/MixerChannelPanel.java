package org.cirillo.mixer;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import org.cirillo.synthesizer.LoadFont;
import org.cirillo.synthesizer.MSynthesizer;
import org.cirillo.synthesizer.SliderKnob;

/*
 * @author Dennis Cirillo
 */

public class MixerChannelPanel extends JPanel implements ChangeListener {

	private static final long serialVersionUID = 1L;

	private Image image = null;
	private int ch;
	private JToggleButton chTglBtn;
	private JTextField volumeDisplay;
	private SliderKnob sliderKnob;

	public MixerChannelPanel(int ch) {
	
		this.ch = ch;

		// Set mixerpanel background color
		setBackground(Color.DARK_GRAY);

		// Set Layout to null, layout is set from SynthGui
		setLayout(null);

		// Create new slider for channel volume
		JSlider chVolumeSlider = new JSlider();
		chVolumeSlider.setPaintTrack(false);
		sliderKnob = new SliderKnob(chVolumeSlider, "img/sliderKnob.png");
		chVolumeSlider.setUI(sliderKnob);
		chVolumeSlider.setOrientation(SwingConstants.VERTICAL);
		chVolumeSlider.setBounds(1, 55, 30, 105);
		chVolumeSlider.setOpaque(false);
		chVolumeSlider.addChangeListener(this);
		add(chVolumeSlider);

		// Create new textfield for displaying channel volume
		volumeDisplay = new JTextField();
		volumeDisplay.setText("64");
		volumeDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		volumeDisplay.setForeground(Color.WHITE);
		volumeDisplay.setFont( LoadFont.DS_DIGITAL(16));
		volumeDisplay.setColumns(10);
		volumeDisplay.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.GRAY, Color.BLACK, Color.BLACK));
		volumeDisplay.setBackground(new Color(91, 111, 138));
		volumeDisplay.setBounds(1, 30, 30, 20);
		volumeDisplay.setEditable(false);
		add(volumeDisplay);

		// Create new togglebutton for toggle channel on/off
		chTglBtn = new JToggleButton("");
		chTglBtn.setSelectedIcon(new ImageIcon("img/mixerChOnBtn.png"));
		chTglBtn.setIcon(new ImageIcon("img/mixerChOffBtn.png"));
		chTglBtn.setFocusPainted(false);
		chTglBtn.setBorder(new LineBorder(new Color(0, 0, 0)));
		chTglBtn.setBounds(1, 16, 30, 12);
		chTglBtn.addItemListener( new MixerToggleHandler() );
		add(chTglBtn);

		// Create new label for displaying channel number
		JLabel lblNewLabel = new JLabel("Ch" + (ch+1));
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(1, 1, 30, 14);
		add(lblNewLabel);
		image = new ImageIcon("img/mixerBgLines.png").getImage();
	}

	// Paint slider background image 
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
	}

	// Get slider value on change
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		volumeDisplay.setText(Integer.toString(source.getValue()));
		MSynthesizer.getInstance().setMixerInput(ch, source.getValue());			
		repaint();
	}
}
