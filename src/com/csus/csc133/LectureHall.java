package com.csus.csc133;

import java.util.Random;

public class LectureHall extends Facility{

	public Lecture lecture;


	public LectureHall(int x, int y, int time, String name) {
		super(x, y, 90 );
		this.lecture = new Lecture(time, name);
		super.setID(0);
		//constructor stub
	}
	
	public Lecture getLect() {
		return lecture;
	}
	public String getName() {
		return lecture.getName();
	}
	public int getTime() {
		//getting how much time is left in the lecture.
		return lecture.getTime();
	}
	public boolean inSession() {
		//sees if a lecture is in session
		return lecture.inSession();
	}
	public void decTimeRem() {
		lecture.decTimeRem();
	}
	
	public void genNewLecture( String name, int time) {
		//makes a new lecture only if there is non at the time.
		if(!lecture.inSession()) {
			lecture.makeLecture(name, time);
		}
	}
	public void lectureEnter() {
		//student is in lecture
		lecture.lectureEnter();
	}
	public void handleCollide(Student s)	{
		//handle colididing with the student s and calling the correct functions.
		if (lecture.getTime()>0) {
			System.out.println(lecture.getName() + " has time: " + lecture.getTime() + " left.");
			lectureEnter();
			System.out.println("StudetnPlayer has Colllided with Lecutre: " + lecture.getName());
			
		}
	}
}
