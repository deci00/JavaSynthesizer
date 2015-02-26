package org.cirillo.synthesizer;

import javax.swing.JOptionPane;

/*
 * @author Dennis Cirillo
 */

public class ExceptionHandler {
	
	// General message for all event dialogs
	private String generalMsg = "See eventlog for more information";
	private SynthGui synthGui = SynthGui.getInstance();
	
	// Get errorcode, errormessage, exception, and boolean for programexit
	public ExceptionHandler(int errorCode, String errorMessage, Exception ex, boolean exit) {
		
		// Write to eventlog
		new EventLogWriter(errorCode, errorMessage, ex);
		
		// Show error dialog and error message
		JOptionPane.showMessageDialog(synthGui.getSystemPanel(), errorMessage+"\n"+generalMsg, "Critical error", JOptionPane.ERROR_MESSAGE);

		// Shutdown application
		if(exit) {			
			System.exit(0);
		}
	}
}
