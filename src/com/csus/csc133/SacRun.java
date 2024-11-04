/*
* This file is the skeleton code of SacRun for the CSC133 assignment at the 
* Computer Science Department of California State University,
* Sacramento. Note that the CSC133 assignments are confidential and 
* copyrighted. It is not allowed to publish any CSC133 source code 
* to the public. Any existing source code for CSC133 assignments on the   
* internet represents that this student did/will disclose confidential  
* materials to the public and violate the designer's copyright.
*
*/

package com.csus.csc133;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.events.*;
import com.codename1.ui.layouts.*;

public class SacRun extends Form{
	
	private GameModel gm;
	private boolean playerMove = false;
	private StudentPlayer handlePlayer;
	private Container vMap;
	private Container vStat;
	private Container vMessage;
	private Container vButtons;
	private Toolbar tool;
	private Random rand = new Random(System.currentTimeMillis());
	
	//Command lists
	private PlayerCommands[] pCom = new PlayerCommands[5];
	private WorldCommands[] wCom = new WorldCommands[7];
	
	public SacRun(){
		gm = new GameModel();

		A2(); // gets weird if things are not loaded in before the map is loaded
		while(vMap.getWidth() == 0 || vMap.getHeight() == 0)	{
			//do nothing wait until GUI loads
		}
		gm.setBounds(vMap.getWidth(), vMap.getHeight());
		gm.init();
		
	}
	
	private void A2() {
		//Initialize Commands
		createCommands();
		//Creating the main UI
		vMap = new ViewMap();
		gm.setBounds(vMap.getWidth(), vMap.getHeight());
		vStat = new ViewStatus();
		vMessage = new ViewMessage();
		vButtons = new ViewButtons(pCom,wCom);
		setLayout(new BorderLayout());
		gm.addObserver((Observer) vStat);
		gm.addObserver((Observer) vMessage);
		gm.addObserver((Observer) vMap);
		
		add(BorderLayout.CENTER, vMap);
		add(BorderLayout.EAST, vStat);
		add(BorderLayout.WEST, vButtons);
		add(BorderLayout.SOUTH, vMessage);
		
		
		
		createToolbar();
		this.show();
		gm.simulateChange();
	}

	private void createCommands() {
		// TODO initialize the commands
		pCom[0] = new PlayerCommands("Move", this);
		pCom[1] = new PlayerCommands("Stop", this);
		pCom[2] = new PlayerCommands("Left", this);
		pCom[3] = new PlayerCommands("Right", this);
		pCom[4] = new PlayerCommands("Change Strategy", this);
		
		wCom[0] = new WorldCommands("Lecture Hall", this);
		wCom[1] = new WorldCommands("Restroom", this);
		wCom[2] = new WorldCommands("Water Dispenser", this);
		wCom[3] = new WorldCommands("Student", this);
		wCom[4] = new WorldCommands("Next Frame", this);
		wCom[5] = new WorldCommands("About", this);
		wCom[6] = new WorldCommands("Exit", this);
	}
	private void createToolbar() {
	//Create the toolbar
			tool = new Toolbar();
			setToolbar(tool);
			
			tool.addCommandToSideMenu(pCom[4]);
			tool.addCommandToSideMenu(wCom[5]);
			tool.addCommandToSideMenu(wCom[6]);
			
			tool.addCommandToRightBar(wCom[0]);
			tool.addCommandToRightBar(wCom[5]);
			
	}
	
	//Below are the functions used to make the player commands of buttons work
	public void move() {
		System.out.println("Player moving");
		updateCommandCode(1);
		playerMove = true;
		//Makes movement begin
	}
	
	public void stop() {
		System.out.println("Player stopped");
		playerMove = false;
		updateCommandCode(2);
		//Makes movement stop
	}
	
	public void left() {
		System.out.println("Player turned left");
		handlePlayer = StudentPlayer.getPlayer(gm);
		handlePlayer.Turn(0, 0);
		gm.setPlayer(handlePlayer);
		updateCommandCode(3);
		//turn left
	}
	
	public void right() {
		System.out.println("Player Turned right");
		handlePlayer = StudentPlayer.getPlayer(gm);
		handlePlayer.Turn(1, 0);
		gm.setPlayer(handlePlayer);
		updateCommandCode(4);
		//turn right
	}
	
	public void changeStrat() {
		StudentStrategy handle1 = gm.getStrat1();
		StudentStrategy handle2 = gm.getStrat2();
		
		int randomint = rand.nextInt(3);
		handle1.setStrategy(randomint);
		randomint = rand.nextInt(3);
		handle2.setStrategy(randomint);
		
		gm.setStrat1(handle1);
		gm.setStrat2(handle2);
		System.out.println("hm");
		updateCommandCode(5);
	}
	
	//Below are the functions used to make the world commands of buttons work
	public void lectureHall() {
		gm.simFirstLectComplete();
		updateCommandCode(6);
	}
	
	public void restroom() {
		handlePlayer = StudentPlayer.getPlayer(gm);
		handlePlayer.restRoom();
		gm.setPlayer(handlePlayer);
		updateCommandCode(7);
	}
	
	public void waterDispenser() {
		handlePlayer = StudentPlayer.getPlayer(gm);
		handlePlayer.drinkWater();
		gm.setPlayer(handlePlayer);
		//TODO:Simulate player-water col
		updateCommandCode(8);
	}
	
	public void student() {
		//creates a dialog box with text field, the user can let the player interact with a student of the desired number
		//0 = angry, 1 = bike, 2 = car, 3 = conf, 4 = friend, 5 = happy, 6 = nonstop, 7 = sleeping, 8 = run, 9 = strat
		boolean inputValid = false;
		Command confirm = new Command("Confirm");
		int value;
		TextField myTextField = new TextField();
		while(!inputValid) {
			myTextField = new TextField();
			Command studentBox = Dialog.show("What Student type?", myTextField, confirm);

			try {
				value = Integer.parseInt(myTextField.getText());
				if(value >=0 && value <= 9) {
					inputValid = true;
				}
				else {
					Command error = Dialog.show("Input invalid", myTextField.getText()+" is not valid input", confirm);
				}
			}catch(Exception e) {
				Command error = Dialog.show("Input invalid", myTextField.getText()+" is not valid input", confirm);
			}
		}
		value = Integer.parseInt(myTextField.getText());
		gm.collideStudent(value);
		
		updateCommandCode(9);
	}
	
	public void nextFrame() {
		System.out.println("Frame++");
		//handlePlayer = gm.getPlayer();
		handlePlayer = StudentPlayer.getPlayer(gm);
		
		if(playerMove) {
			handlePlayer.studentMove(gm.getWidth(),gm.getHeight());
		}
		handlePlayer.incTime();
		gm.setPlayer(handlePlayer);
		gm.incTime();
		updateCommandCode(10);
	}
	
	public void about() {
		//
		Command confirmBox = new Command("Confirm");
		Command abtBox = Dialog.show("A2", "Roberto Ramil Fall 2024", confirmBox);
		updateCommandCode(11);
	}
	
	public void exit() {
		updateCommandCode(12);
		Boolean exitBox = Dialog.show("Exit", "Are you sure you would like to exit?", "Yes", "No");
		if(exitBox) {
			CN.exitApplication();
		}
		
	}
	public boolean getGameStatus() {
		return gm.isGameRunning();
	}
	
	public void updateCommandCode(int num) {
		gm.setCommandCode(num);
	}
	
	public void gameOver() {
		gm.endGame("");
	}
}

