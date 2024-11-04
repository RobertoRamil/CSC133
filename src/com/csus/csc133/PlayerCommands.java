package com.csus.csc133;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PlayerCommands extends Command {
	SacRun form;
	private int commandCode = 0;
	
	public PlayerCommands(String command, SacRun form) {
		super(command);
		this.form = form;
	}
	
	public void actionPerformed(ActionEvent event) {
		commandCode = 0;
		//Command codes negative to tell the viewMessage the game is over
		switch(getCommandName()) {
		case "Move": //command code 1
			form.move();
			break;
		case "Stop": //command code 2
			form.stop();
			break;
		case "Left": //command code 3
			form.left();
			break;
		case "Right": //command code 4
			form.right();
			break;
		case "Change Strategy": //command code 5
			form.changeStrat();
			break;
		}
		//TODO: update view message to display current command can make a function in "SacRun" to update this maybe "updateStatus" and pass the event name as the argument
	}

}
