package org.cirillo.synthesizer;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

/*
 * @author Dennis Cirillo
 */

public class SliderKnob extends BasicSliderUI {
	 
    private Image knobImage;
    
    public SliderKnob( JSlider aSlider, String fileName ) {
        super( aSlider );
        
        // Remove track and border from slider
        aSlider.setPaintTrack(false);
        aSlider.setBorder(null);
        
        try {
        	// Set knobImage from imagefile
            this.knobImage = ImageIO.read( new File(fileName) );

            // New exception if unable to load sliderimage
        } catch ( IOException e ) {
            new ExceptionHandler(2, "Unable to load synth graphics", e, true);
        }
    }
   
    public void paintThumb(Graphics g)  {        
        g.drawImage( this.knobImage, thumbRect.x+3, thumbRect.y-2, 15, 20, null );
    }
}