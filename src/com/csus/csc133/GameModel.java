package com.csus.csc133;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CN;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;

import java.lang.Math;

public class GameModel extends Observable {
	
	//Game Model Fields
	private int gameWidth ;
	private int gameHeight;
	private float gameTime = 0;
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
	private int frameTime;
	
	private String obj1 = "";
	private String obj2 = "";
			
	
	GameModel(int frame){
		
		playerChar = StudentPlayer.getPlayer(this);
		this.frameTime = frame;
		
	}
	
	public void init(){
		//Generate the player object
		//This is handled outside of the vector for the sake of convenience
		int width = (int) (gameWidth - gameWidth *.1);
		int height = (int)(gameHeight - gameHeight*.1);
		
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
		roll = rand.nextInt(4)+3;
		//make restroom(s)
		for(int i = 0; i <= roll; i++) {
			gameObjs.add(new Restroom((rand.nextFloat() * rand.nextInt(height)),
					(rand.nextFloat() * rand.nextInt(width))));
		}
		
		roll = rand.nextInt(4)+3;
		//make water dispenser(s)
		for(int i = 0; i <= roll; i++) {
			gameObjs.add(new WaterDispenser((rand.nextFloat() * rand.nextInt(height)),
					(rand.nextFloat() * rand.nextInt(width))));
		}
		
		roll = rand.nextInt(4)+4;
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
		playerChar.placeCenter(this);
		
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
		return gameWidth;
	}
	public int getHeight() {
		return gameHeight;
	}
	
	public void setBounds(int w, int h) {
		this.gameWidth = w;
		this.gameHeight = h;
		setChanged();
		notifyObservers();
	}
	
	/*
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
	} */
	
	public void incTime() {
		//This handles the "f" command
		this.gameTime += (frameTime/1000.0);
		boolean aLectOpen = false;
		
		checkCollisions();
		//Stores the indexes of all lecture halls, this is to more easily pick a random hall 
		ArrayList <Integer> handleHalls = new ArrayList<Integer>();
		for (int i = 0; i < gameObjs.size(); i++) {
			//Handles a confused student's movement
			if(gameObjs.get(i) instanceof StudentConfused) {
				StudentConfused handleConf = (StudentConfused) gameObjs.get(i);
				handleConf.studentMove(this.gameWidth, this.gameHeight, frameTime);
				handleConf.incTime(frameTime);
				gameObjs.set(i, handleConf);
			}
			//Handles a happy student's movement
			else if(gameObjs.get(i) instanceof StudentHappy) {
				StudentHappy handleHappy = (StudentHappy) gameObjs.get(i);
				handleHappy.studentMove(this.gameWidth, this.gameHeight, frameTime);
				handleHappy.incTime(frameTime);
				gameObjs.set(i, handleHappy);
			}
			//Handle generic student movement
			else if(gameObjs.get(i) instanceof Student) {
				Student handleS = (Student) gameObjs.get(i);
				handleS.studentMove(this.gameWidth, this.gameHeight, frameTime);
				handleS.incTime(frameTime);
				gameObjs.set(i, handleS);
			}
			//Decrement the time remaining of any ongoing lecture
			if(gameObjs.get(i) instanceof LectureHall) {
				LectureHall handleLect = (LectureHall) gameObjs.get(i);
				handleHalls.add(i);
				handleLect.decTimeRem(frameTime);
				if(handleLect.isOpen()) {
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
		checkOOB();
		setChanged();
		notifyObservers();
	}
	
	//This simulates the lecture ending/being entered
	/*
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
	/*/
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
	
	public float getGameTime() {
		return gameTime;
	}
	
	public void endGame(String reason) {
		this.gameRunning = false;
		Command confirmBox = new Command("Good Game! (Exit)");
		Command abtBox = Dialog.show("The game is finished", reason + "\nYour final time is: " + gameTime, confirmBox);
		CN.exitApplication();
	}
	
	public GameObjectCollection getCol() {
		return gameObjs;
	}
	public void setCol(GameObjectCollection newCol) {
		this.gameObjs = newCol;
	}
	
	public void checkCollisions() {
		GameObject handle;
		boolean pCollide = playerChar.collides(strat1);
		if((strat1.getTimeRem() > -2) && (strat1.willTalk())) {
			pCollide = false;
		}
		if(playerChar.getTimeRem() > -2) {
			pCollide = false;
		}
		
		if(pCollide) {
			float time = Student.Talking(playerChar, strat1);
			playerChar.setTimeRem(time);
			playerChar.setColor(ColorUtil.rgb(255, 175, 200));
			
			strat1.setTimeRem(time);
			strat1.setColor(ColorUtil.rgb(255, 175, 200));
			
		}
		
		pCollide = playerChar.collides(strat2);
		if((strat2.getTimeRem() > -2) && (strat2.willTalk())) {
			pCollide = false;
		}
		if(playerChar.getTimeRem() > -2){
			pCollide = false;
		}
		
		if(pCollide) {
			float time = Student.Talking(playerChar, strat2);
			playerChar.setTimeRem(time);
			playerChar.setColor(ColorUtil.rgb(255, 175, 200));
			
			strat2.setTimeRem(time);
			strat2.setColor(ColorUtil.rgb(255, 175, 200));
		}
		
		for(int i = 0; i < gameObjs.size(); i++) {
			handle = gameObjs.get(i);
			pCollide = playerChar.collides(handle);
			
			if(pCollide) {
				obj1 = "StudentPlayer";
				obj2 = getObjType(handle);
				commandCode = 6;
				if(handle instanceof Student) {
					Student handleS = (Student) handle;
					if((handleS.getTimeRem() > -2) && (handleS.willTalk())) {
						pCollide = false;
					}
					if(playerChar.getTimeRem() > -2) {
						pCollide = false;
					}
					
					if(pCollide ) {
						float time = Student.Talking(playerChar, handleS);
						playerChar.setTimeRem(time);
						playerChar.setColor(ColorUtil.rgb(255, 175, 200));
						
						handleS.setTimeRem(time);
						handleS.setColor(ColorUtil.rgb(255, 175, 200));
						gameObjs.set(i, handleS);
						
					}
				}
				if(handle instanceof LectureHall) {
					LectureHall handleL = (LectureHall) handle;
					if(handleL.isOpen()) {
						if(handleL.getTimeRem() <= 0) {
							playerChar.incAbsenseTime();
							
						}
							handleL.lectureEntered();
						gameObjs.set(i, handleL);
					
					}
				}
				if(handle instanceof WaterDispenser) {
					playerChar.drinkWater();
				}
				if(handle instanceof Restroom) {
					playerChar.restRoom();
				}
			}
			if((i < gameObjs.size()-1) && (handle instanceof Student)) {
				for(int j = i+1; j<gameObjs.size(); j++) {
					Student handle0 = (Student)gameObjs.get(i);
					handle = gameObjs.get(j);
					boolean sCollide = handle0.collides(handle);
					if(sCollide) {
						obj1 = getObjType(handle0);
						obj2 = getObjType(handle);
						commandCode = 6;
						if(handle instanceof Student) {
							Student handleS = (Student)handle;
							if((handleS.getTimeRem() > -2) && (handleS.willTalk())) {
								sCollide = false;
							}
							if(handle0.getTimeRem() > -2) {
								sCollide = false;
							}
							
							if(sCollide) {
								float time = Student.Talking(handle0, handleS);
								handle0.setTimeRem(time);
								handle0.setColor(ColorUtil.rgb(255, 175, 200));
								gameObjs.set(i, handleS);
								
								handleS.setTimeRem(time);
								handleS.setColor(ColorUtil.rgb(255, 175, 200));
								gameObjs.set(j, handleS);
							}
						}
						if(handle instanceof LectureHall) {
							//Nothing
						}
						if(handle instanceof WaterDispenser) {
							handle0.drinkWater();
							gameObjs.set(i, handle0);
						}
						if(handle instanceof Restroom) {
							handle0.restRoom();
							gameObjs.set(i, handle0);
						}
					}
				}
			}
		}
	}
	public void checkOOB() {
		for(int i = 0; i< gameObjs.size(); i++) {
			if(gameObjs.get(i) instanceof Facility) {
				//System.out.println("Object is out of Bounds");
				if((gameObjs.get(i).getX() > gameWidth )||(gameObjs.get(i).getX() < 0)) {
					GameObject handle = gameObjs.get(i);
					handle.setX(rand.nextInt((int)(gameWidth*0.9)));
					gameObjs.set(i, handle);
				}
				if((gameObjs.get(i).getY() > gameHeight )||(gameObjs.get(i).getY() < 0)) {
					GameObject handle = gameObjs.get(i);
					handle.setY(rand.nextInt((int)(gameHeight*0.9)));
					gameObjs.set(i, handle);
				}
			}
		}
	}
	private String getObjType(GameObject item) {
		String ret = "";
		if(item instanceof Student) {
			ret  = ret + "Student";
			if(item instanceof StudentAngry) {
				ret += "Angry";
			}
			else if(item instanceof StudentBiking) {
				ret += "Biking";
			}
			else if(item instanceof StudentConfused) {
				ret += "Confused";
			}
			else if(item instanceof StudentFriendly) {
				ret += "Friendly";
			}
			else if(item instanceof StudentHappy) {
				ret += "Happy";
			}
			else if(item instanceof StudentNonstop) {
				ret += "Nonstop";
			}
			else if(item instanceof StudentSleeping) {
				ret += "Sleeping";
			}
			else if(item instanceof StudentRunning) {
				ret += "Running";
			}
			else if(item instanceof StudentStrategy) {
				ret += "Strategy";
			}
		}
		if(item instanceof Facility) {
			if(item instanceof LectureHall) {
				LectureHall lect = (LectureHall) item;
				ret = lect.getName() + " Hall";
			}
			else if(item instanceof WaterDispenser) {
				ret = "WaterDispenser";
			}
			else if(item instanceof Restroom) {
				ret = "RestRoom";
			}
		}
		return ret;
		
	}
	
	public String getCol1() {
		return obj1;
	}
	public String getCol2() {
		return obj2;
	}
	
	public void placeAt(int selected, GameObject handle) {
		gameObjs.set(selected, handle);
	}
	
}
