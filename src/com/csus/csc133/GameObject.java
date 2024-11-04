package com.csus.csc133;

import com.codename1.charts.util.ColorUtil;

public class GameObject {
	private float x;
	private float y;
	private int color;
	private int size;
	
	
	GameObject(float x2, float y2) {
		this.setX(x2);
		this.setY(y2);
		this.color = ColorUtil.rgb(255, 0, 0);
	}
	
	//Will be used later to implement actual collision, but will likely be inserted into a class handling bounding boxes
	public void handleCollide(GameObject item) {
		
	}
	
	//Only simple get and set methods
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getColor() {
		return color;
	}
	public void setColor(int val) {
		this.color = val;
	}
	
	public float getSize() {
		return size;
	}
	public void setSize(int val) {
		this.size = val;
	}
	
	

}
