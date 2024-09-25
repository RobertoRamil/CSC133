package com.csus.csc133;

import java.util.Random;

public abstract class Student extends GameObject implements IMoveable {
	//Variables for each class
	static int DEFAULT_SPEED = 200;
	static int DEFAULT_TALKATIVELEVEL = 2;
	private int head = 0;
	private int speed;
	private int talkativeLevel;
	private int timeRemain = 0;
	private int Hydration = 200;
	private int waterIntake = 0;
	private int sweatingRate = 3;
	private int absenceTime = 0;
	
	//these are here for nonstop and sleeping  student since they dont do these
	private boolean talk = true; 
	private boolean walk = true;
	
	//list of types of students
	static String [] Title = new String[] {"Angry", "Biking", "Car", "Confused", "Friendly", "Happy", "NonStop", "Sleeping", "Running"};
	private int TitleID;
	private Random r = new Random();
	
	
	public Student(int x, int y, int speed, int talkativeLevel, int sweatingRate) {
		super(x,y);
		super.setSize(r.nextInt(20)+40);
		this.speed = speed * DEFAULT_SPEED;
		this.talkativeLevel = talkativeLevel * DEFAULT_TALKATIVELEVEL;
		this.sweatingRate *= sweatingRate;
	
	}
	//main move function may be changed based on the assingment
	public void move()	{
		if (timeRemain > 0) {
			timeRemain--;
		}
		else {
			
			setX((int)(getX() + Math.cos(Math.toRadians(90-head))*speed));
			setY((int)(getY() + Math.sin(Math.toRadians(90-head))*speed));
			
			
		}
		Hydration -= sweatingRate;
		
		//making sure the student is not out of bounds when moving
		if (getX()<0) {
			setX(0);
		}
		else if (getX()>1024) {
			setX(1024);
		}
		if(getY() < 0) {
			setY(0);
		}
		else if (getY() > 768) {
			setY(768);
		}
	}
	
	public void drinkWater() {
		//drink water function
		waterIntake += (200-Hydration);
		Hydration = 200;
		System.out.println("Student Drank Water.");
	}
	public void restroomCollide() {
		//reset water intake by using the bathroom
		System.out.println("Student has Used the Restroom and reset his waterIntake.");
		waterIntake = 0;
	}
	public static void studentCollide(Student s1, Student s2) {
		//uses the student collide wtih setting the talking to the max of the two students
		if(s1.willTalk() && s2.willTalk()) {
			//Ensures students will talk in the first place (sleeping and nonstop students won't talk)
			int maxTime = Math.max(s1.getTalkivelevel(), s2.getTalkivelevel());
			s1.setTimeRemain(maxTime);
			s2.setTimeRemain(maxTime);
		}
	}
	
	//getters and setters for certain values that will be changed a lot.
	public boolean willTalk() {
		return talk;
	}
	public void setTalkStatus(boolean x) {
		this.talk = x;
	}
	
	public boolean willWalk() {
		return walk;
	}
	
	public void setID(int val) {
		this.TitleID = val;
	}
	public int getID() {
		return TitleID;
	}
	public String getTitle() {
		return Title[TitleID];
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void setHead(int head) {
		this.head = head;
	}
	public void setTimeRemain(int num) {
		this.timeRemain = num;
	}
	public void incAbsence() {
		this.absenceTime++;
	}
	public int getHead() {
		return head;
	}
	public int getSpeed() {
		return speed;
	}
	public int getTimeRemain() {
		return timeRemain;
	}
	public int getHydration() {
		return Hydration;
	}
	public int getWaterIntake() {
		return waterIntake;
	}
	public int getSweatingRate() {
		return sweatingRate;
	}
	public int getAbsenceTime() {
		return absenceTime;
	}
	public int getTalkivelevel() {
		return talkativeLevel;
	}

}
