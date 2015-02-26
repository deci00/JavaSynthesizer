package org.cirillo.drums;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import org.cirillo.synthesizer.ExceptionHandler;

/*
 * @author Dennis Cirillo
 */

public class Drums {
	
	private String type;

	public static String[] genres = {"Dance","Techno","House","Hiphop","Midi"};
	public static String genre;
	public static int loadedGenre = 0;


	private AudioInputStream audioInputStream;
	private AudioFormat format;
	private DataLine.Info info;
	private Clip clip;

	public Drums(String type) {
		this.type = type;
		genre = genres[loadedGenre];
	}

	public void play() {

		
		if(genre=="Midi") {
			DrumLoop.isMidi=true;
		} else {
			try{
				// Create new audiInputStream from wav file
				audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/drums/"+genre+"/"+type+".wav").getAbsoluteFile());

				
				format = audioInputStream.getFormat();
				info = new DataLine.Info(Clip.class, format);
				clip = (Clip) AudioSystem.getLine(info);

				clip.open(audioInputStream);
				clip.start();

			}catch(Exception ex){
				new ExceptionHandler(1, "Unable to play audio", ex, true);
			}
		}
	}

	// Set drum genre
	public static void setGenre(char op) {
	
		switch(op) {
		// Set next genre in genres array
		case '+':
			if(loadedGenre<4) {
				loadedGenre++;
			}
			break;
			// Set previous genre in genres array
		case '-':
			if(loadedGenre>0) {
				loadedGenre--;
				// Set midi to false, midi is last pos in genre
				DrumLoop.isMidi=false;
			}
		}
	}

	// Returns genre for drumGenreDisplay
	public static String getGenre() {
		return genres[loadedGenre];
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
