package com.csus.csc133;
import java.util.Random;

public class StudentHappy extends Student {
	private Random rand = new Random(System.currentTimeMillis());
	
	public StudentHappy(float x, float y, float direction){
		super(x,y,direction);
		super.setStatus(6);
	}
	
	public void studentMove(int width, int height, int frameTime) {
		int roll = rand.nextInt(10); //roll to see if the student does a jump ~10% chance
		int mult = 1;
		if(roll == 1 ) {
			mult = 2; //multiply speed by 10 for this movement step
		}
		setX((float) (super.getX() + Math.cos(Math.toRadians(super.getHead()))  * (mult*super.getSpeed()*(frameTime/1000.0))));
		if(super.getX() >= width) {
			super.setX(width-40);
			this.Turn(0, 180);
		}
		if(super.getX() <= 0){
			super.setX(0+40);
			this.Turn(0, 180);
		}
		setY((float) (super.getY() + Math.sin(Math.toRadians(super.getHead()))* (mult*super.getSpeed()*(frameTime/1000.0))));
		if(super.getY() >= height) {
			super.setY(height-40);
			this.Turn(0, 180);
		}
		if(super.getY() <= 0) {
			super.setY(0+40);
			this.Turn(0, 180);
		}
		else {
			super.studentMove(width,height, frameTime);
		}
	}

}
