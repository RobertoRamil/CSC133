package com.csus.csc133;

import java.util.Random;


public abstract class GameObject {
	private int x;
	private int y;
	private int color;
	private int size;
	
	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void handleCollide(Student s) {
		//TODO
	}

	public int getX() { 
		return this.x; 
	}
	public int getY() {
		return y;
	}
	public void setX(int x) { 
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setSize(int num) {
		this.size = num;
	}
	
}
