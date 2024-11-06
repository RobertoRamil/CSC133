package com.csus.csc133;

import com.codename1.ui.CN;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class WorldCommands extends Command {
	SacRun form;
	private int commandCode = 0;
	
	public WorldCommands(String command, SacRun form) {
		super(command);
		this.form = form;
	}
	
	public void actionPerformed(ActionEvent event) {
		commandCode = 0;
		//Command codes negative to tell the viewMessage the game is over
		switch(getCommandName()) {

		case "Next Frame": //command code 10
			form.nextFrame();
			break;
		case "About": //command code 11
			form.about();
			break;
		case "Exit": //command code 12
			form.exit();
			break;
		case "Pause":
			form.pause();
			break;
		case "Play":
			form.play();
			break;
		case "Change Location":
			form.changeLocation();
			break;
		//TODO: update view message to display current command can make a function in "SacRun" to update this maybe "updateStatus" and pass the event name as the argument
		}
	}

}
