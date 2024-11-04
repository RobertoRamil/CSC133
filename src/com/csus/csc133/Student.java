package com.csus.csc133;
import java.lang.Math;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public abstract class Student extends GameObject {
	//Default values
	String [] statusTypes = new String[] {"Player", "Angry", "Biking", "Car", "Confused", "Friendly", "Happy", "Nonstop", "Sleeping", "Running"};
	
	private int statusID;
	//Default values
	private float defSpeed = 10;
	private float defTalkLvl = 2;
	private float defSweatRate = 3;
	private int defHydrate = 100;
	//Student attributes
	private float speed;
	private float talkLvl;
	private float head = 0;
	private float timeRem = 0;
	private float hydrate = 100;
	private float waterIn;
	private float sweatRate;

	private boolean talk = true;
	private boolean move = true;
	
	//Set randomness with a seed
	private Random rand = new Random(System.currentTimeMillis());
	
	public Student(float x, float y, float direction){
		super(x, y);
		this.head = direction;
		if(head >= 360) {
			head %= 360;
		}
		if(head < 0) {
			head += 360;
		}
		
		//this.status = statusTypes[StatusIndex];
		//this.statusID = StatusIndex;
		this.speed = defSpeed;
		this.talkLvl = defTalkLvl;
		this.sweatRate = defSweatRate;
		super.setSize(rand.nextInt(21)+40);
	}
	//Checks
	public boolean willTalk() {
		return talk;
	}
	public void setTalkStatus(boolean x) {
		this.talk = x;
	}
	
	public boolean willMove() {
		return move;
	}
	public void setWalkStatus(boolean x) {
		this.move = x;
	}
	

	
	//Other Methods
	
	//Simulates a collision with a waterDispenser
	public void drinkWater() {
		this.waterIn += (defHydrate - hydrate);
		this.hydrate = defHydrate;
	}
	
	//Simulates a collision with a restRoom
	public void restRoom() {
		this.waterIn = 0;
	}
	
	
	
	//A static method to set any 2 students to talking
	public static void Talking(Student s1, Student s2) {
		if(s1.willTalk() && s2.willTalk()) {//Ensures students will talk in the first place (sleeping and nonstop students won't talk)
			float maxTime = Math.max(s1.getTalk(), s2.getTalk());
			s1.setTimeRem(maxTime);
			s1.setColor(ColorUtil.rgb(255, 175, 200));
			s2.setTimeRem(maxTime);
			s2.setColor(ColorUtil.rgb(255, 175, 200));
		}
	}
	
	public void Turn(int direction, int specificTurn) {
		//direction of 0 = left, direction of <= 1 = right
		float turnAmt;
		
		//Specific turn amt is for the sake of handling confusedStudents, with non-confused students it should ALWAYS be passed 0 as an argument
		if(specificTurn == 0) {
			if(direction == 0) {
				turnAmt = -15;
			}
			else{
				turnAmt = 15;
			}
		}
		else {
			turnAmt = specificTurn;
		}
		this.head += turnAmt;
		
		//This ensures that the head does not go to unnecessarily high/negative numbers
		if(head >= 360) {
			head %= 360; //360 deg == 0 deg so the remainder of this will still be the correct angle
		}
		if(head < 0) {
			head += 360; //If the degrees becomes negative you can make it do an entire 360 in the positive direction and the degrees are equivalent i.e.: -270 == 90
		}
	}
	
	//Generic method for student movement
	public void studentMove(int width, int height) {
		if(willMove()) {//eliminates non-stop and sleeping students
			if(timeRem == 0) {//Ensure a talking student does not move
				//Standard Student movement
				if((this.statusID != -1)) {
					hydrate -= sweatRate;
					setX((float) (super.getX() + Math.cos(Math.toRadians(90 - head))*speed));
					//These 2 if statements ensure the student does not exit the level bounds
					if(super.getX() >= width) {
						setX((float) width);
					}
					if(super.getX() <= 0) {
						setX(0);
					}
					setY((float) (super.getX() + Math.sin(Math.toRadians(90 - head))*speed));
					//These 2 if statements ensure the student does not exit the level bounds
					if(super.getY() >= height) {
						setY((float) height);
					}
					if(super.getY() <= 0) {
						setY(0);
					}
				}
				//allows the student strategy to simulate confused movement
				else if(this.statusID == -1) {
					int turnAmt = rand.nextInt(180);
					//left or right
					int LorR = rand.nextInt(1);
					if(LorR == 0) {
						turnAmt *= -1;
						Turn(0, turnAmt);
					}
					else {
						Turn(1, turnAmt);
					}
					hydrate -= sweatRate;
					setX((float) (super.getX() + Math.cos(Math.toRadians(90 - head))*speed));
					//These 2 if statements ensure the student does not exit the level bounds
					if(super.getX() >= width) {
						setX((float) width);
					}
					if(super.getX() <= 0) {
						setX(0);
					}
					setY((float) (super.getX() + Math.sin(Math.toRadians(90 - head))*speed));
					//These 2 if statements ensure the student does not exit the level bounds
					if(super.getY() >= height) {
						setY((float) height);
					}
					if(super.getY() <= 0) {
						setY(0);
					}
				}
			}
		}
	}
	
	public void incTime() {
		if(timeRem > 0) {
			timeRem --;
		}
		else if (timeRem == 0) {
			if(super.getColor() == ColorUtil.rgb(255, 0, 0)) {
				super.setColor(ColorUtil.rgb(255, 0, 0));
			}
		}
		
	}
	//Get/set methods
	public int getTypeID() {
		return statusID;
	}
	public void setStatus(int val) {
		this.statusID = val;
	}
	
	public String getType() {
		return statusTypes[statusID];
	}
	
	public float getHead() {
		return head;
	}
	public void setHead(float val) {
		this.head = val;
	}
	
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float val) {
		this.speed = val;
	}
	
	public float getHydration() {
		return hydrate;
	}
	
	public float getTalk() {
		return talkLvl;
	}
	public void setTalk(float val) {
		this.talkLvl = val;
	}
	
	public float getTimeRem() {
		return timeRem;
	}
	public void setTimeRem(float value){
		this.timeRem = value;
	}
	
	public float getWaterIn() {
		return waterIn;
	}
	
	public float getSweat() {
		return sweatRate;
	}
	public void setSweat(float val) {
		this.sweatRate = val;
	}
	
	
}
