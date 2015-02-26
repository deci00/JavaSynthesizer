package org.cirillo.synthesizer;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

/*
 * @author Dennis Cirillo
 */

public class Pitch extends JPanel implements ChangeListener {

	private static final long serialVersionUID = 1L;

	private static Pitch instance = null;

	private JSlider pitchSlider;
	private int pitchLevel;
	private JTextField pitchDisplay;
	private Image image = null;
	private SliderKnob pitchSliderKnob;

	public static Pitch getInstance() {
		if(instance==null) {
			instance = new Pitch();
		}
		return instance;
	}

	private Pitch() {
		create();
	}

	private void create() {

		setLayout(null);
		setBackground(new Color(20, 20, 20));

		image = new ImageIcon("img/pitchBg.png").getImage();

		// Create new slider for pitch
		pitchSlider = new JSlider();
		pitchSliderKnob = new SliderKnob(pitchSlider, "img/sliderKnob.png");
		pitchSlider.setUI(pitchSliderKnob);
		pitchSlider.setValue(64);
		pitchSlider.setMaximum(127);
		pitchSlider.setMinimum(0);
		pitchSlider.setToolTipText("Set pitch");
		pitchSlider.setOrientation(SwingConstants.VERTICAL);
		pitchSlider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pitch", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.control));
		pitchSlider.setBackground(Color.DARK_GRAY);
		pitchSlider.setBounds(10, 35, 47, 75);
		pitchSlider.addChangeListener(this);
		// Center pitch when mouse is released
		pitchSlider.addMouseListener(new MouseAdapter() { 
			public void mouseReleased(MouseEvent me) { 
				pitchSlider.setValue(64);
			} 
		}); 
		pitchSlider.setOpaque(false);
		add(pitchSlider);

		// Create new textfield for display pitchlevel
		pitchDisplay = new JTextField();
		pitchDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		pitchDisplay.setForeground(Color.RED);
		pitchDisplay.setFont( LoadFont.DS_DIGITAL(23) );
		pitchDisplay.setText("64");
		pitchDisplay.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.GRAY, Color.BLACK, Color.BLACK));
		pitchDisplay.setBackground(Color.BLACK);
		pitchDisplay.setBounds(10, 114, 47, 22);
		add(pitchDisplay);
		pitchDisplay.setColumns(10);
	}

	
	@Override
	public void stateChanged(ChangeEvent e) {
		//Get value of pitch slider
		JSlider source = (JSlider)e.getSource();
		System.out.println(source.getValue());
		pitchLevel = source.getValue();	

		//Set pitchlevel to display
		pitchDisplay.setText(Integer.toString(pitchLevel));

		//Set pitchlevel to synthesizer
		pitchLevel*=163;
		MSynthesizer.getInstance().setPitch(pitchLevel);	

		//Repaint sliderknob on movement
		repaint();
	}

	public void slidervalue(int slider) {
		pitchSlider.setValue(slider);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
	}
}
