package com.csus.csc133;

import java.util.Random;

public abstract class Facility extends GameObject {
	//list of different facilities
	static String [] Title = new String[] {"LectureHall", "Restroom", "WaterDispense"};
	private int TitleID;
	Random r = new Random();

	public Facility(int x, int y,int w) {
		super(x, y);
		super.setSize(w);
		//constructor stub
	}
	
	//setting the id of each facility
	public void setID(int val) {
		this.TitleID = val;
	}
	//getting the id
	public int getID() {
		return TitleID;
	}
	//getting what the facility is called.
	public String getTitle() {
		return Title[TitleID];
	}

}