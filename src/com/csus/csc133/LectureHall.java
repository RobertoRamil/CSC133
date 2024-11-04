package com.csus.csc133;
import java.util.ArrayList;
import java.util.Random;

public class LectureHall extends Facility{
	private Lecture lect;
	private String name;
	private static String[] halls = new String[] {"Alpine", "Amador", "AIRC", "Benica", "Brighton", "Calaveras", "Capistrano", "Douglas", "Del Norte", "Eureka", "Mendocino", "Riverside"};
	private ArrayList <Integer> usedNames = new ArrayList<Integer>();
	private Random rand = new Random(System.currentTimeMillis());
	
	public LectureHall(float x, float y, int time, String name) {
		super(x, y);
		this.lect = new Lecture(time, name);
		setStatusID(0);
		super.setSize(100);
		boolean nameChosen = false;
		int tryname;
		while(!nameChosen){
			boolean nameDup = false;
			tryname = rand.nextInt(10);
			for(int i = 0; i < usedNames.size(); i++) {
				if(tryname == usedNames.get(i)) {
					nameDup = true;
				}
			}
			if(!nameDup) {
				this.name = halls[tryname];
				nameChosen = true;
				usedNames.add(tryname);
			}
		}
		
		
	}
	
	//These methods retreive data about the lecture within the lecture hall
	public Lecture getLect() {
		return lect;
	}
	public float getTimeRem() {
		return lect.getTimeRem();
	}
	public void decTimeRem() {
		lect.decTimeRem();
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

}
