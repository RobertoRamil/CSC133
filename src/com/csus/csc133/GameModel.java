package com.csus.csc133;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import com.codename1.ui.CN;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;

import java.lang.Math;

//import gameObjects.*;

public class GameModel extends Observable {
	
	//Game Model Fields
	private int width ;
	private int height;
	private int gameTime = 0;
	private StudentPlayer playerChar;
	private GameObjectCollection gameObjs = new GameObjectCollection();
	private Random rand = new Random(System.currentTimeMillis());
	//To create a random subject/name for a lecture
	private String[] lectNames = new String[] {"Csc", "Math", "Engl", "Phys", "Hist" };
	
	private StudentStrategy strat1;
	private StudentStrategy strat2;
	
	private boolean gameRunning = true;
	
	//Command codes to tell view message what command was used
	private int commandCode = 0;
			
	
	GameModel(){
		
		playerChar = StudentPlayer.getPlayer(this);
		
	}
	
	public void init(){
		//Generate the player object
		//This is handled outside of the vector for the sake of convenience
		playerChar = StudentPlayer.getPlayer(this);
		
		System.out.println("Width: " + width + "\nHeight: " + height);
		//Populate the two Strategies they are stored individually
		strat1 = new StudentStrategy((rand.nextFloat() * rand.nextInt(height)),
				(rand.nextFloat() * rand.nextInt(width)),
				rand.nextInt(360));
		strat1.setStrategy(0);
		
		strat2 = new StudentStrategy((rand.nextFloat() * rand.nextInt(height)),
				(rand.nextFloat() * rand.nextInt(width)),
				rand.nextInt(360));
		strat2.setStrategy(1);
		
		int roll = rand.nextInt(2);
		//Populate student game objects

		//make angry student(s)
		for(int i = 0; i <= roll; i++) {
			gameObjs.add(new StudentAngry((rand.nextFloat() * rand.nextInt(height)),
					(rand.nextFloat() * rand.nextInt(width)),
					rand.nextInt(360)));
		}
		roll = rand.nextInt(2);
		//make biking student(s)
		for(int i = 0; i <= roll; i++) {
			gameObjs.add(new StudentBiking((rand.nextFloat() * rand.nextInt(height)),
					(rand.nextFloat() * rand.nextInt(width)),
					rand.nextInt(360)));
		}
		roll = rand.nextInt(2);
		//make car student(s)
		for(int i = 0; i <= roll; i++) {
			gameObjs.add(new StudentCar((rand.nextFloat() * rand.nextInt(height)),
					(rand.nextFloat() * rand.nextInt(width)),
					rand.nextInt(360)));
		}
		roll = rand.nextInt(2);
		//make Confused student(s)
		for(int i = 0; i <= roll; i++) {
			gameObjs.add(new StudentConfused((rand.nextFloat() * rand.nextInt(height)),
					(rand.nextFloat() * rand.nextInt(width)),
					rand.nextInt(360)));
		}
		roll = rand.nextInt(2);
		//make friendly student(s)
		for(int i = 0; i <= roll; i++) {
			gameObjs.add(new StudentFriendly((rand.nextFloat() * rand.nextInt(height)),
					(rand.nextFloat() * rand.nextInt(width)),
					rand.nextInt(360)));
		}
		roll = rand.nextInt(2);
		//make Happy student(s)
		for(int i = 0; i <= roll; i++) {
			gameObjs.add(new StudentHappy((rand.nextFloat() * rand.nextInt(height)),
					(rand.nextFloat() * rand.nextInt(width)),
					rand.nextInt(360)));
		}
		roll = rand.nextInt(2);
		//make nonstop student(s)
		for(int i = 0; i <= roll; i++) {
			gameObjs.add(new StudentNonstop((rand.nextFloat() * rand.nextInt(height)),
					(rand.nextFloat() * rand.nextInt(width)),
					rand.nextInt(360)));
		}
		roll = rand.nextInt(2);
		//make running student(s)
		for(int i = 0; i <= roll; i++) {
			gameObjs.add(new StudentRunning((rand.nextFloat() * rand.nextInt(height)),
					(rand.nextFloat() * rand.nextInt(width)),
					rand.nextInt(360)));
		}
		roll = rand.nextInt(2);
		//make sleeping student(s)
		for(int i = 0; i <= roll; i++) {
			gameObjs.add(new StudentSleeping((rand.nextFloat() * rand.nextInt(height)),
					(rand.nextFloat() * rand.nextInt(width)),
					rand.nextInt(360)));
		}
		
		
		//Populate facility game objects
		roll = rand.nextInt(4);
		//make restroom(s)
		for(int i = 0; i <= roll; i++) {
			gameObjs.add(new Restroom((rand.nextFloat() * rand.nextInt(height)),
					(rand.nextFloat() * rand.nextInt(width))));
		}
		
		roll = rand.nextInt(4);
		//make water dispenser(s)
		for(int i = 0; i <= roll; i++) {
			gameObjs.add(new WaterDispenser((rand.nextFloat() * rand.nextInt(height)),
					(rand.nextFloat() * rand.nextInt(width))));
		}
		
		roll = rand.nextInt(4);
		//determines which random lecturehall gets a lecture
		int rollLect = rand.nextInt(roll+1);
		//make lecture hall(s)
		for(int i = 0; i <= roll; i++) {
			if( i == rollLect) {
				int rollName = rand.nextInt(5);
				String lectType = lectNames[rollName];
				gameObjs.add(new LectureHall((rand.nextFloat() * rand.nextInt(height)),
					(rand.nextFloat() * rand.nextInt(width)),(rand.nextInt(10)+15), lectType));
			}
			else {
				gameObjs.add(new LectureHall((rand.nextFloat() * rand.nextInt(height)),
					(rand.nextFloat() * rand.nextInt(width)),(rand.nextInt(10)+15), ""));
			}
		}
		
		setChanged();
		notifyObservers();
	}
	
	public StudentStrategy getStrat1() {
		return strat1;
	}
	public StudentStrategy getStrat2() {
		return strat2;
	}
	
	public void setStrat1(StudentStrategy strat) {
		strat1 = strat;
	}
	public void setStrat2(StudentStrategy strat) {
		strat2 = strat;
	}
	
	public void setCommandCode(int num) {
		this.commandCode = num;
		setChanged();
		notifyObservers();
	}
	
	public int getCommandCode() {
		return commandCode;
	}
	
	//Simple get methods for the level bounds
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public void setBounds(int w, int h) {
		this.width = w;
		this.height = h;
		setChanged();
		notifyObservers();
	}
	
	public void collideStudent(int studentType) {
		//Testing for colliding with different types of students
		boolean found = false;;
		String type = "";
		if(studentType == 9) {
			Student.Talking(playerChar, strat1);
			found = true;
			type = "Strategy";
		}
		for(int i = 0; i < gameObjs.size(); i++) {
			switch(studentType) {
			case 0: //Angry
				type = "Angry";
				if(gameObjs.get(i) instanceof StudentAngry) {
					Student.Talking(playerChar, (Student) gameObjs.get(i));
					found = true;
				}
				break;
			case 1: //Bike
				type = "Biking";
				if(gameObjs.get(i) instanceof StudentBiking) {
					Student.Talking(playerChar, (Student) gameObjs.get(i));
					found = true;
				}
				break;
			case 2: //Car
				type = "Car";
				if(gameObjs.get(i) instanceof StudentCar) {
					Student.Talking(playerChar, (Student) gameObjs.get(i));
					found = true;
				}
				break;
			case 3: //Confused
				type = "Confused";
				if(gameObjs.get(i) instanceof StudentConfused) {
					Student.Talking(playerChar, (Student) gameObjs.get(i));
					found = true;
				}
				break;
			case 4: //Friend
				type = "Friendly";
				if(gameObjs.get(i) instanceof StudentFriendly) {
					Student.Talking(playerChar, (Student) gameObjs.get(i));
					found = true;
				}
				break;
			case 5: //Happy
				type = "Happy";
				if(gameObjs.get(i) instanceof StudentHappy) {
					Student.Talking(playerChar, (Student) gameObjs.get(i));
					found = true;
				}
				break;
			case 6: //NonStop
				type = "NonStop";
				if(gameObjs.get(i) instanceof StudentNonstop) {
					Student.Talking(playerChar, (Student) gameObjs.get(i));
					found = true;
				}
				break;
			case 7: //Sleeping
				type = "Sleeping";
				if(gameObjs.get(i) instanceof StudentSleeping) {
					Student.Talking(playerChar, (Student) gameObjs.get(i));
					found = true;
				}
				break;
			case 8: //Running
				type = "Running";
				if(gameObjs.get(i) instanceof StudentRunning) {
					Student.Talking(playerChar, (Student) gameObjs.get(i));
					found = true;
				}
				break;
			}
		}
		if(!found) {
			Command okay = new Command("Okay");
			System.out.println(type);
			Command inform = Dialog.show("Student not found", "Student of type: " + type + " was not found", okay);
		}
	}
	
	public void incTime() {
		//This handles the "f" command
		this.gameTime++;
		boolean aLectOpen = false;
		//Stores the indexes of all lecture halls, this is to more easily pick a random hall 
		ArrayList <Integer> handleHalls = new ArrayList<Integer>();
		for (int i = 0; i < gameObjs.size() ;i++) {
			//Handles a confused student's movement
			if(gameObjs.get(i) instanceof StudentConfused) {
				StudentConfused handleConf = (StudentConfused) gameObjs.get(i);
				handleConf.studentMove(this.width, this.height);
				handleConf.incTime();
				gameObjs.set(i, handleConf);
			}
			//Handles a happy student's movement
			else if(gameObjs.get(i) instanceof StudentHappy) {
				StudentHappy handleHappy = (StudentHappy) gameObjs.get(i);
				handleHappy.studentMove(this.width, this.height);
				handleHappy.incTime();
				gameObjs.set(i, handleHappy);
			}
			//Handle generic student movement
			else if(gameObjs.get(i) instanceof Student) {
				Student handleS = (Student) gameObjs.get(i);
				handleS.studentMove(this.width, this.height);
				handleS.incTime();
				gameObjs.set(i, handleS);
			}
			//Decrement the time remaining of any ongoing lecture
			if(gameObjs.get(i) instanceof LectureHall) {
				LectureHall handleLect = (LectureHall) gameObjs.get(i);
				handleHalls.add(i);
				handleLect.decTimeRem();
				if(!handleLect.isOpen()) {
					/*
					int roll = rand.nextInt(10);
					if(roll == 1) {
						roll = rand.nextInt(5);
						int time = rand.nextInt(14)+1;
						handleLect.genNewLect(lectNames[roll], time);
					
					}
					*/
				}
				else if(handleLect.isOpen()) {
					aLectOpen = true;
				}
				gameObjs.set(i, handleLect);
			}
			//Check if game is over
			if(playerChar.getAbsenceTime() > 5) {
				gameRunning = false;
				endGame("You had more than 5 absences!");
			}
			else if(playerChar.getHydration() <= 0) {
				gameRunning = false;
				endGame("You are too dehydrated to continue!");
			}
			else if(playerChar.getWaterIn() > 100) {
				gameRunning = false;
				endGame("You drank too much water!");
			}
		}
		//If no lectures were found to be open, roll to make a new one
		if(!aLectOpen) {
			int roll = rand.nextInt(10);
			if(roll == 1) {
				int choseHall = rand.nextInt(handleHalls.size());
				roll = rand.nextInt(5);
				int time = rand.nextInt(25)+1;
				
				LectureHall handlehall = (LectureHall) gameObjs.get(handleHalls.get(choseHall));
				handlehall.genNewLect(lectNames[roll], time);
				gameObjs.set(handleHalls.get(choseHall), handlehall);
			}
		}
			
		setChanged();
		notifyObservers();
	}
	
	//This simulates the lecture ending/being entered
	public void simFirstLectComplete() {
		LectureHall handle;
		//Since actual collisions are not yet implemented in A1 we can simply search for the first lectureHall instance
		for(int i = 0; i < gameObjs.size(); i++) {
			if(gameObjs.get(i) instanceof LectureHall) {
				handle = (LectureHall) gameObjs.get(i);
				if(handle.isOpen()) {
					if(handle.getTimeRem() < 0) {
						playerChar.incAbsenseTime();
					}
					handle.lectureEntered();
					
					gameObjs.set(i, handle);
					break;
				}
			}
		}
		setChanged();
		notifyObservers();
	}
	
	public LectureHall getFirstlect() {
		LectureHall handle;
		//no hard collision yet
		for(int i = 0; i < gameObjs.size(); i++) {
			
			if(gameObjs.get(i) instanceof LectureHall) {
				handle = (LectureHall) gameObjs.get(i);
				if(handle.getLect().isOpen()) {
					return handle;
				}
			}
		}
		return null;
	}
	
	//output the data of the player and each other student type and facilty
	public void gameInfoOut() {
		System.out.println("StudentPlayer, x:" + Math.round((playerChar.getX())*10.0)/10.0 + ", y:" +
							Math.round((playerChar.getY())*10.0)/10.0 + ", Head:"+Math.round((playerChar.getHead())*10.0)/10.0 + ", Speed:" +
							Math.round((playerChar.getSpeed())*10.0)/10.0 + ", Hydration:" + Math.round((playerChar.getHydration())*10.0)/10.0+ ", TalkiveLevel:" +
							Math.round((playerChar.getTalk())*10.0)/10.0 + ", TimeRem:"+Math.round((playerChar.getTimeRem())*10.0)/10.0 + ", Absences:" +
							playerChar.getAbsenceTime() + ", WaterIn:" + Math.round((playerChar.getWaterIn())*10.0)/10.0);
		for(int i = 0; i < gameObjs.size(); i++) {
			if(gameObjs.get(i) instanceof Student) {
				String special = "";
				Student handle = (Student) gameObjs.get(i);
				if(handle instanceof StudentBiking) {
					special = "Biking";
				}
				else if(handle instanceof StudentCar) {
					special = "Driving";
				}
				else if(handle instanceof StudentSleeping) {
					special = "zzzZZZ!";
				}
				else if(handle instanceof StudentRunning) {
					special = "Running";
				}
				System.out.println("Student"+handle.getType()+", x:"+Math.round((handle.getX())*10.0)/10.0+", y:" +
									Math.round((handle.getY())*10.0)/10.0+", Head:"+Math.round((handle.getHead())*10.0)/10.0 +", Speed:" +
									Math.round((handle.getSpeed())*10.0)/10.0+ ", Hydration:" + Math.round((handle.getHydration())*10.0)/10.0+ ", TalkiveLevel:" +
									Math.round((handle.getTalk())*10.0)/10.0+ ", TimeRem:"+Math.round((handle.getTimeRem())*10.0)/10.0 + " " + special);
			}
			else if(gameObjs.get(i) instanceof Facility) {
				Facility handleF = (Facility) gameObjs.get(i);
				if(handleF instanceof LectureHall) {
					LectureHall handleLH = (LectureHall) handleF;
					String special = "null";
					if(handleLH.isOpen()) {
						special = Float.toString((float) (Math.round((handleLH.getTimeRem())*10.0)/10.0));
					}
					System.out.println(handleLH.getType() + ", x:"+Math.round((handleLH.getX())*10.0)/10.0 + ", y:" +Math.round((handleLH.getY())*10.0)/10.0 + ", Name:" +
										handleLH.getName() + " TimeRem:" + special);
					
				}
				else {
					System.out.println(handleF.getType() + ", x:" + Math.round((handleF.getX())*10.0)/10.0 + ", y:" +Math.round((handleF.getY())*10.0)/10.0);
				}
			}
		}
	}
	
	//Handle playerChar
	public void setPlayer(StudentPlayer player) {
		this.playerChar = player;
		setChanged();
		notifyObservers();
	}
	public StudentPlayer getPlayer() {
		return playerChar;
	}
	public boolean isGameRunning() {
		return gameRunning;
	}
	
	public void simulateChange() {
		setChanged();
		notifyObservers();
	}
	
	public int getGameTime() {
		return gameTime;
	}
	
	public void endGame(String reason) {
		this.gameRunning = false;
		Command confirmBox = new Command("Good Game! (Exit)");
		Command abtBox = Dialog.show("The game is finished", reason + "\nYour final time is: " + gameTime, confirmBox);
		CN.exitApplication();
	}
}
