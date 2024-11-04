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
		case "Lecture Hall": //command code 6
			form.lectureHall();
			break;
		case "Restroom": //command code 7
			form.restroom();
			break;
		case "Water Dispenser": //command code 8
			form.waterDispenser();
			break;
		case "Student": //command code 9
			form.student();
			break;
		case "Next Frame": //command code 10
			System.out.println("hm");
			form.nextFrame();
			break;
		case "About": //command code 11
			form.about();
			break;
		case "Exit": //command code 12
			form.exit();
			break;
		}
		//TODO: update view message to display current command can make a function in "SacRun" to update this maybe "updateStatus" and pass the event name as the argument
	}

}
