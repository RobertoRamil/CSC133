package com.csus.csc133;

public class Lecture  {
	
	private int time;
	private String name;
	private boolean lectActive;
	
	public Lecture(int time, String name) {
		//sets a lecture with time and the name of it.
		this.time = time;
		this.name = name;
		//sets a boolean to true saying there is a lecture starting.
		this.lectActive = true;
	}
	public int getTime() {
		//return how much time left in the lecture.
		return time;
	}
	public void decTimeRem(){
		//if there is a lecture reduce it by 1 second
		if(lectActive) {
			time--;
		}
	
	}
	public void lectureEnter() {
		//student has now entered the lecture
		lectActive = false;
		time = 0;
	}
	public String getName() {
		return name;
	}
	
	public boolean inSession() {
		//see if there is an active lecture
		return lectActive;
	}
	public void makeLecture(String name, int time) {
		//make a new lecture at a 10% rate each game frame.
		lectActive = true;
		this.time = time;
		
	}

}
