package com.csus.csc133;

import java.util.ArrayList;
import java.util.Random;

public class GameModel {
	Random r = new Random();
	private int  WIDTH = 1024;
	private int HEIGHT = 768;
	public int gametime = 0;
	
	private boolean gameRunning = true;
	
	private StudentPlayer SPlayer;
	
	private ArrayList <GameObject> gameObjs = new ArrayList<GameObject>();
	
	private String[] lecNames = new String[] {"Math", "Engl", "Phys", "Bio", "Hist", "CSC"};
	
	
	
	public void init(){
		//Student Classes being added to the ArrayList
		//generating 1-2 of each.
					
		for (int i = 0; i< r.nextInt(2)+1; i++) gameObjs.add(new StudentAngry(r.nextInt(WIDTH), r.nextInt(HEIGHT), 1, 2, 1));
		for (int i = 0; i< r.nextInt(2)+1; i++) gameObjs.add(new StudentBiking(r.nextInt(WIDTH), r.nextInt(HEIGHT), 3, 1, 2));
		for (int i = 0; i< r.nextInt(2)+1; i++) gameObjs.add(new StudentCar(r.nextInt(WIDTH), r.nextInt(HEIGHT), 5, 1, 0));
		for (int i = 0; i< r.nextInt(2)+1; i++) gameObjs.add(new StudentConfused(r.nextInt(WIDTH), r.nextInt(HEIGHT), 1, 1, 1));
		for (int i = 0; i< r.nextInt(2)+1; i++) gameObjs.add(new StudentFriendly(r.nextInt(WIDTH), r.nextInt(HEIGHT), 1, .5, 1));
		for (int i = 0; i< r.nextInt(2)+1; i++) gameObjs.add(new StudentHappy(r.nextInt(WIDTH), r.nextInt(HEIGHT), 1, 1, 1));
		for (int i = 0; i< r.nextInt(2)+1; i++) gameObjs.add(new StudentNonstop(r.nextInt(WIDTH), r.nextInt(HEIGHT), 1, 0, 1));
		for (int i = 0; i< r.nextInt(2)+1; i++) gameObjs.add(new StudentSleeping(r.nextInt(WIDTH), r.nextInt(HEIGHT), 0, 1, 0));
		for (int i = 0; i< r.nextInt(2)+1; i++) gameObjs.add(new StudentRunning(r.nextInt(WIDTH), r.nextInt(HEIGHT), 1, 1, 2));
		//Different Facilities being added
		gameObjs.add(new LectureHall(r.nextInt(WIDTH), r.nextInt(HEIGHT), r.nextInt(1000), lecNames[r.nextInt(5)]));
		//making 2 to 4 of them
		for (int i =0;i <= r.nextInt(2)+2;i++) gameObjs.add(new Restroom(r.nextInt(WIDTH), r.nextInt(HEIGHT)));
		for (int i =0;i <= r.nextInt(2)+2;i++) gameObjs.add(new WaterDispenser(r.nextInt(WIDTH), r.nextInt(HEIGHT)));
		
		//Finally Student Player which is completely different since its movable.
		SPlayer = new StudentPlayer(5,8,1,1,1);

		
		
		
	}
	public void Move() {
		SPlayer.startMoving();
		SPlayer.move();
	};
	public void StopMove() {
		SPlayer.stopMoving();
	};
	public void TurnLeft() {
		SPlayer.turnLeft();
    	SPlayer.move();
	};
	public void TurnRight() {
		SPlayer.turnRight();
		SPlayer.move();
	};
	public void LectureHall() {
		for (int i = 0; i< gameObjs.size(); i++) {
        		if (gameObjs.get(i) instanceof LectureHall) {
        			LectureHall temp = (LectureHall) gameObjs.get(i);
        			temp.handleCollide(SPlayer);
        		}
        	}
        	SPlayer.move();
	}
	public void Restroom() {
		SPlayer.restroomCollide();
		SPlayer.move();
	}
	public void WaterDispenser() {
		SPlayer.drinkWater();
        SPlayer.move();
	}
	
	//Only Used for A1 because we are testing collide with another student
	/*
	 * private void testStudentCollide() {
	 * 
	 * 
	 * 
	 * Student temp = (Student) gameObjs.get(r.nextInt(8));
	 * Student.studentCollide(SPlayer, temp);
	 * 
	 * }
	 */

	public void outputGameInfo() {
		
		//outputs for StudentPlayer since its special
		System.out.println("Time : " + gametime + " ==========================================");
		System.out.println("StudentPlayer, pos(" + Math.round((float)SPlayer.getX()*10.0)/10.0+ ", "+ Math.round((float)SPlayer.getY()*10.0)/10.0 +"), Head: "
							+ Math.round((float)SPlayer.getHead()*10.0)/10.0+ ", Speed : " + Math.round((float)SPlayer.getSpeed()*10.0)/10.0 + ", Hydration: "
							+ Math.round((float)SPlayer.getHydration()*10.0)/10.0 + ", TalkativeLevel: " + Math.round((float)SPlayer.getTalkivelevel()*10.0)/10.0
							+ ", TimeRemain: " + Math.round((float)SPlayer.getTimeRemain()*10.0)/10.0 + ", AbsenceTime: "
							+ Math.round((float)SPlayer.getAbsenceTime()*10.0)/10.0 + ", WaterIntake: " + Math.round((float)SPlayer.getWaterIntake()*10.0)/10.0);
		
		for (int i = 0; i<gameObjs.size(); i++) {
			if(gameObjs.get(i) instanceof Student) {
				String text = "";
				Student temp = (Student) gameObjs.get(i);
				// do text later
				if (temp instanceof StudentBiking) {
					text = ", Biking";
				} else if (temp instanceof StudentCar){
					text = ", Driving";
				} else if (temp instanceof StudentSleeping) {
					text = ", zzzZZZ!";
				} else if (temp instanceof StudentRunning) {
					text = ", Running";
				}
				System.out.println("Student" + temp.getTitle() + ", pos(" + Math.round((float)temp.getX()*10.0)/10.0+ ", " + Math.round((float)temp.getY()*10.0)/10.0
									+ "), Head: " + Math.round((float)temp.getHead()*10.0)/10.0+", Speed: "+ Math.round((float)temp.getSpeed()*10.0)/10.0
									+ ", Hydration: " + Math.round((float)temp.getHydration()*10.0)/10.0 + ", TalkiveLevel: " +Math.round((float)temp.getTalkivelevel()*10.0)/10.0
									+ ", TimeRemain: " + Math.round((float)temp.getTimeRemain()*10.0)/10.0 + text);
			} else if(gameObjs.get(i) instanceof Facility) {
				Facility temp = (Facility) gameObjs.get(i);
				String text = "";
				if (temp instanceof LectureHall) {
					LectureHall tempH = (LectureHall) temp;
					String text2 = "NULL";
					if (tempH.inSession()) {
						text2 = Integer.toString(tempH.getTime());
					}
					System.out.println(tempH.getTitle()+ ", pos (" + Math.round((float)tempH.getX()*10.0)/10.0 + ", " + Math.round((float)tempH.getY()*10.0)/10.0
										+ "), " +tempH.getName() + ", Current Lecture Time Remaining: " + text2);
				} else System.out.println(temp.getTitle()+ ", pos (" + Math.round((float)temp.getX()*10.0)/10.0 + ", " + Math.round((float)temp.getY()*10.0)/10.0+ ")");
			}
			
		}
		
	}

	public void advanceFrame() {
		gametime++;
		for (int i = 0; i< gameObjs.size(); i++) {
			// for conufsed student since they are differetn from others
			if(gameObjs.get(i) instanceof StudentConfused) {
				StudentConfused conf = (StudentConfused) gameObjs.get(i);
				conf.move();
				gameObjs.set(i, conf);
			}
			else if (gameObjs.get(i) instanceof StudentHappy) {
				StudentHappy hap = (StudentHappy) gameObjs.get(i);
				hap.move();
				gameObjs.set(i, hap);
			}
			else if (gameObjs.get(i) instanceof Student) {
				Student stud = (Student) gameObjs.get(i);
				stud.move();
				gameObjs.set(i, stud);
			}
			if (gameObjs.get(i) instanceof LectureHall) {
				LectureHall lec= (LectureHall) gameObjs.get(i);
				lec.decTimeRem();
				if(!lec.inSession()) {
					int roll = r.nextInt(10);
					if (roll ==1){
						roll = r.nextInt(5);
						int time = r.nextInt(14)+1;
						lec.genNewLecture(lecNames[roll],time);
					}
				}
				gameObjs.set(i, lec);
				if(lec.getTime()<0) {
					SPlayer.incAbsence();
					lec.lectureEnter();
				}
				
			}
			
		}
		if((SPlayer.getAbsenceTime() > 5) || (SPlayer.getHydration() <= 0)||(SPlayer.getWaterIntake() >= 100)) {
				gameRunning = false;
				System.out.println("Game has Ended ");
		}
	}
	public void ChangeStrat() {
		// TODO Auto-generated method stub

	}
	public void Student() {
		// TODO Auto-generated method stub
	}
   
}

