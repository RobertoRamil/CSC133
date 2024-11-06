package com.csus.csc133;
import java.util.ArrayList;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;

public class LectureHall extends Facility{
	private Lecture lect;
	private String name;
	private static String[] halls = new String[] {"Alpine", "Amador", "AIRC", "Benica", "Brighton", "Calaveras", "Capistrano", "Douglas", "Del Norte", "Eureka", "Mendocino", "Riverside"};
	private ArrayList <Integer> usedNames = new ArrayList<Integer>();
	private static int lecnum = 0;
	private Random rand = new Random(System.currentTimeMillis());
	
	public LectureHall(float x, float y, int time, String name) {
		super(x, y);
		this.lect = new Lecture(time, name);
		setStatusID(0);
		super.setSize(50);
		super.setColor(ColorUtil.BLUE);
		this.name = halls[lecnum];
		lecnum++;
		
		
	}
	
	//These methods retreive data about the lecture within the lecture hall
	public Lecture getLect() {
		return lect;
	}
	public float getTimeRem() {
		return lect.getTimeRem();
	}
	public void decTimeRem(int frameTime) {
		lect.decTimeRem(frameTime);
	}
	
	public String getClassType() {
		return lect.getName();
	}
	
	public boolean isOpen() {
		return lect.isOpen();
	}
	
	public void lectureEntered() {
		lect.lectureEntered();
	}
	
	public void genNewLect(String name, int time) {
		if(!lect.isOpen()) {
			lect.makeLect(name, time);
		}
	}
	
	public String getName() {
		return this.name;
	}

	@Override
	public void draw(Graphics g, int getX, int getY) {
		g.setColor((int) this.getColor());
		g.fillRect((int)(getX()-getSize()/2 + getX), (int)(getY()-getSize()/2 + getY), (int)getSize(), (int)getSize());
		
		g.setColor(ColorUtil.rgb(255, 255, 255));
		g.drawString(name,(int)(getX()-getSize()/2 + getX), (int)(getY() + getY));
	}
}
