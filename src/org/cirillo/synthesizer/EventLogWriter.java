package org.cirillo.synthesizer;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/*
 * @author Dennis Cirillo
 */

public class EventLogWriter {

	private BufferedWriter bw;
	
	public EventLogWriter(int eventCode, String errorMessage, Exception ex) {

		try {
			// Create new bufferedWriter, filewriter and append to eventlog.txt
			bw = new BufferedWriter( new FileWriter("Event/eventlog.txt", true));

			//Write Date and exception to file
			bw.write(new Date().toString() + " : Exception: " + ex + " : Errormessage: " + errorMessage);
			bw.newLine();

		} catch (FileNotFoundException e) {
			// new Exception handler
			new ExceptionHandler(eventCode, "Eventlog.txt was not found", e, false);
		} catch (IOException e2) {
			//New Exception handler (moment 22?)
			new ExceptionHandler(eventCode, "Unable to write to eventlog", e2, false);
		} finally {
			try {
			// Close bufferedWriter if empty	
			if(bw  != null) {
				bw.flush();
				bw.close();
			}
			} catch (IOException e3) {
				// New Exception handler
				new ExceptionHandler(eventCode, "Unable to close bufferedWriter", e3, false);
			}
		}
	}

}
