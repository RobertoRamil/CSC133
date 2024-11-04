package com.csus.csc133;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.plaf.Border;

public class ViewMessage extends Container implements Observer{
	private Label message;
	public ViewMessage() {
		getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		getAllStyles().setBgColor(ColorUtil.rgb(200, 200, 200));
		
		message = new Label("Game Start!");
		add(message);
	}
	@Override
	public void update(Observable Obs, Object data) {
		// TODO Auto-generated method stub
		System.out.println("Message updated");
		String msg;
		GameModel gm = (GameModel) Obs;
		int comCode = gm.getCommandCode();
		switch (comCode){
		case 0:
			msg = "Game Start!";
			break;
		case 1:
			msg = "Start player movement";
			break;
		case 2:
			msg = "Stop player movement";
			break;
		case 3:
			msg = "Turning player left";
			break;
		case 4:
			msg = "Turning player right";
			break;
		case 5:
			msg = "Changing strategy";
			break;
		case 6:
			msg = "Simulating lecture hall collision";
			break;
		case 7:
			msg = "Simulating restroom collision";
			break;
		case 8:
			msg = "Simulating water dispenser collision";
			break;
		case 9:
			msg = "Simulating student collision";
			break;
		case 10:
			msg = "Incrementing the game";
			break;
		case 11:
			msg = "Displaying information about the application";
			break;
		case 12:
			msg = "Attempting to exit the game";
			break;
		default:
			msg = "An error has occured, an incorrect code has been passed";
			
		}
		message.setText(msg);
	}
}
