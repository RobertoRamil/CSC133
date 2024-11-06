package com.csus.csc133;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class GameObject implements Shape{
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
	
	public void drawBox(Graphics g, int x, int y) {
		g.setColor(ColorUtil.rgb(255, 0, 0));
		g.drawRect((int)(getX()-getSize()/2 + x), (int)(getY()-getSize()/2 + y), (int)getSize(), (int)getSize());
	}
	
	public boolean collides(GameObject other) {
		int L1 = (int) (this.getX() - this.getSize() / 2); 
		int R1 = (int) (this.getX() + this.getSize() / 2);
		int T1 = (int) (this.getY() - this.getSize() / 2);
		int B1 = (int) (this.getY() + this.getSize() / 2);
		
		int L2 = (int) (other.getX() - other.getSize() / 2);
		int R2 = (int) (other.getX() + other.getSize() / 2);
		int T2 = (int) (other.getY() - other.getSize() / 2);
		int B2 = (int) (other.getY() + other.getSize() / 2);
		
		if ((R1 < L2) || (R2 < L1) || (T2 > B1) || (T1 > B2))
			return false;
		return true; 
	}
	
	@Override
	public void draw(Graphics g, int x, int y) {
		//TODO
	}

}
