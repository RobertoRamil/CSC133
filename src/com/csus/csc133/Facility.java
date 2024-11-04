package com.csus.csc133;

public abstract class Facility extends GameObject{
	//Stores the names of different facilities
	private String[] facilType = new String[] {"LectureHall", "Waterdispenser", "Restroom"};

	//index for the facility types
	private int statusID;
	
	Facility(float x, float y){
		super(x, y);
	}
	
	//get/set methods
	public int getTypeID() {
		return getStatusID();
	}
	public String getType() {
		return facilType[getStatusID()];
	}

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	
}
