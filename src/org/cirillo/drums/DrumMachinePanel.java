package org.cirillo.drums;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import org.cirillo.synthesizer.LoadFont;

/*
 * @author Dennis Cirillo
 */

public class DrumMachinePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private boolean isPlaying = false;

	private DrumPatterns kickPatterns;
	private DrumPatterns clapPatterns;
	private DrumPatterns hatPatterns;
	private DrumPatterns snarePatterns;
	
	private JButton play;
	private JButton stop;
	private JButton reset;

	private DrumLoop drumLoop;
	private Thread drumThread;

	private static JTextField drumGenreDisplay;
	private static JTextField bpmDisplay;

	public DrumMachinePanel() {

		setBorder(null);

		// Patterns
		kickPatterns = new DrumPatterns("kick", 5);
		clapPatterns = new DrumPatterns("clap", 30);
		hatPatterns = new DrumPatterns("hat", 55);
		snarePatterns = new DrumPatterns("snare", 80);

		// Control buttons
		play = new JButton("");
		play.setIcon(new ImageIcon("img/play_btn.png"));
		play.setFocusPainted( false );
		play.setBorder(new LineBorder(new Color(0, 0, 0)));
		play.setBounds(1, 110, 35, 20);
		play.setActionCommand("play");
		play.addActionListener( this );

		stop = new JButton("");
		stop.setIcon(new ImageIcon("img/stop_btn.png"));
		stop.setFocusPainted( false );
		stop.setBorder(new LineBorder(new Color(0, 0, 0)));
		stop.setBounds(40, 110, 35, 20);
		stop.setActionCommand("stop");
		stop.addActionListener( this );

		reset = new JButton("");
		reset.setIcon(new ImageIcon("img/reset_btn.png"));
		reset.setFocusPainted( false );
		reset.setBorder(new LineBorder(new Color(0, 0, 0)));
		reset.setBounds(79, 110, 35, 20);
		reset.setActionCommand("reset");
		reset.addActionListener( this );
		
		add(play);
		add(stop);
		add(reset);

		setLayout(null);
		setOpaque(false);

		// Steps
		for(int button = 0; button < kickPatterns.ptnBtns().size(); button++) {
			add(kickPatterns.ptnBtns().get(button));
			add(clapPatterns.ptnBtns().get(button));
			add(hatPatterns.ptnBtns().get(button));
			add(snarePatterns.ptnBtns().get(button));
		}

		// Genre display
		drumGenreDisplay = new JTextField();
		drumGenreDisplay.setText("Dance");
		drumGenreDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		drumGenreDisplay.setForeground(Color.WHITE);
		drumGenreDisplay.setFont( LoadFont.DS_DIGITAL(18) );
		drumGenreDisplay.setEditable(false);
		drumGenreDisplay.setColumns(10);
		drumGenreDisplay.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.GRAY, Color.BLACK, Color.BLACK));
		drumGenreDisplay.setBackground(new Color(91, 111, 138));
		drumGenreDisplay.setBounds(120, 110, 110, 20);
		add(drumGenreDisplay);

		// prev genre btn
		JButton prBtn = new JButton("");
		prBtn.setIcon(new ImageIcon("img/btnPrev.png"));
		prBtn.setFocusPainted(false);
		prBtn.setBorder(new LineBorder(new Color(0, 0, 0)));
		prBtn.setBounds(230, 110, 20, 20);
		prBtn.setActionCommand("prevGenre");
		prBtn.addActionListener( this );
		add(prBtn);

		// next genre btn
		JButton nxtBtn = new JButton("");
		nxtBtn.setIcon(new ImageIcon("img/btnNext.png"));
		nxtBtn.setFocusPainted(false);
		nxtBtn.setBorder(new LineBorder(new Color(0, 0, 0)));
		nxtBtn.setBounds(250, 110, 20, 20);
		nxtBtn.setActionCommand("nextGenre");
		nxtBtn.addActionListener( this );
		add(nxtBtn);

		// Bpm display
		bpmDisplay = new JTextField();
		bpmDisplay.setText("130 BPM");
		bpmDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		bpmDisplay.setForeground(Color.WHITE);
		bpmDisplay.setFont( LoadFont.DS_DIGITAL(18) );
		bpmDisplay.setEditable(false);
		bpmDisplay.setColumns(10);
		bpmDisplay.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.GRAY, Color.BLACK, Color.BLACK));
		bpmDisplay.setBackground(new Color(91, 111, 138));
		bpmDisplay.setBounds(276, 110, 80, 20);
		bpmDisplay.addMouseWheelListener( new BpmEventHandler() );
		add(bpmDisplay);

		// decrease bpm btn
		JButton bpmDownBtn = new JButton("");
		bpmDownBtn.setIcon(new ImageIcon("img/btnPrev.png"));
		bpmDownBtn.setFocusPainted(false);
		bpmDownBtn.setActionCommand("bpmDown");
		bpmDownBtn.setBorder(new LineBorder(new Color(0, 0, 0)));
		bpmDownBtn.setBounds(356, 110, 20, 20);
		bpmDownBtn.addActionListener( new BpmEventHandler() );
		bpmDownBtn.addMouseWheelListener( new BpmEventHandler() );
		add(bpmDownBtn);

		// increase bpm btn
		JButton bpmUpBtn = new JButton("");
		bpmUpBtn.setIcon(new ImageIcon("img/btnNext.png"));
		bpmUpBtn.setFocusPainted(false);
		bpmUpBtn.setActionCommand("bpmUp");
		bpmUpBtn.setBorder(new LineBorder(new Color(0, 0, 0)));
		bpmUpBtn.setBounds(376, 110, 20, 20);
		bpmUpBtn.addActionListener( new BpmEventHandler() );
		bpmUpBtn.addMouseWheelListener( new BpmEventHandler() );
		add(bpmUpBtn);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {

		case "play":
			play();
			break;
			
		case "stop":
			stop();
			break; 
			
		case "reset":
			reset();
			break;
			
		case "prevGenre":
			prevGenre();
			break;
			
		case "nextGenre":
			nextGenre();
			break;
		}
	}

	// Start sequencer
	public void play() {
		if(!isPlaying) {
			isPlaying = true;
			drumLoop = new DrumLoop(kickPatterns.ptnBtns(), clapPatterns.ptnBtns(), hatPatterns.ptnBtns(), snarePatterns.ptnBtns(), true);
			drumThread = new Thread( drumLoop );
			drumThread.start();
		}
	}

	// Stop sequencer
	public void stop() {
		isPlaying = false;
		DrumLoop.running = false;
	}

	// Resets sequencer
	public void reset() {
		kickPatterns.resetPatterns();
		clapPatterns.resetPatterns();
		hatPatterns.resetPatterns();
		snarePatterns.resetPatterns();
	}

	// Select previous music genre
	public void prevGenre() {
		Drums.setGenre('-');
		drumGenreDisplay.setText(Drums.getGenre());
	}

	// Select next music genre
	public void nextGenre() {
		Drums.setGenre('+');
		drumGenreDisplay.setText(Drums.getGenre());
	}

	// Sets bpm value to bpm display
	public static void setBpmDisplay() {
		bpmDisplay.setText( Long.toString( DrumLoop.bpm ) + " BPM" );
	}
}