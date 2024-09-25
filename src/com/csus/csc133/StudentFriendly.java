package com.csus.csc133;

public class StudentFriendly extends Student {

	public StudentFriendly(int x, int y, int speed, double talkativeLevel, int sweatingRate) {
		super(x, y, speed, (int)talkativeLevel, sweatingRate);
		super.setID(4);
		//Constructor for the Student.
	}
	

}
