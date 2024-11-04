package com.csus.csc133;
import java.util.Random;

public class StudentHappy extends Student {
	private Random rand = new Random(System.currentTimeMillis());
	
	public StudentHappy(float x, float y, float direction){
		super(x,y,direction);
		super.setStatus(6);
	}
	
	public void studentMove(int width, int height) {
		int roll = rand.nextInt(10); //roll to see if the student does a jump ~10% chance
		int mult = 1;
		if(roll == 1 ) {
			mult = 10; //multiply speed by 10 for this movement step
		}
		setX((float) (super.getX() + Math.cos(Math.toRadians(90 - super.getHead()))  * (10*super.getSpeed())));
		if(super.getX() >= width) {
			setX((float) width);
		}
		if(super.getX() <= 0) {
			setX(0);
		}
		setY((float) (super.getX() + Math.sin(Math.toRadians(90 - super.getHead()))* (10*super.getSpeed())));
		if(super.getY() >= height) {
			setY((float) height);
		}
		if(super.getY() <= 0) {
			setY(0);
		}
		else {
			super.studentMove(width,height);
		}
	}

}
