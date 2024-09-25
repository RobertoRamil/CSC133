package com.csus.csc133;

public class StudentPlayer extends Student {

	public StudentPlayer(int x, int y, int speed, int talkativeLevel, int sweatingRate) {
		super(x, y, speed, talkativeLevel, sweatingRate);
		// TODO Auto-generated constructor stub
	}

	public void startMoving() {
		// set the movment speed to the default. 
		//its already set to that but it helps to start moving after stopping.
		System.out.println("Started Moving");
		setSpeed(DEFAULT_SPEED);
		
	}

	public void stopMoving() {
		//stop moving by changing the moment speed to 0
		System.out.println("Stopped Moving");
		super.setSpeed(0);
		
	}

	public void turnLeft() {
		// moves the head to the correct position
		//currently only 90 deg changes.
		setHead(270 + getHead());
		if(getHead() >=360) {
			setHead(getHead()-360);
		}
		
	}

	public void turnRight() {
		// moves the head to the correct position
		//currently only 90 deg changes.
		setHead(90 + getHead());
		if(getHead() >=360) {
			setHead(getHead()-360);
		}
		
	}


	

}
