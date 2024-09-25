package com.csus.csc133;

import com.codename1.ui.*;
import com.codename1.ui.events.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

public class SacRun extends Form{
	
	private GameModel gm;
	
	public SacRun(){
		gm = new GameModel();
		
		//A1();
		A2();
			
		gm.init();
		
	}
	
	//UI provided for A1 only, remove it in A2
	/*private void A1() {
		Label myLabel=new Label("Enter a Command:");
		TextField myTextField=new TextField();
		this.add(myLabel).add(myTextField);
		this.show();
		myTextField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				String sCommand=myTextField.getText().toString();
				myTextField.clear();
				if(sCommand.isEmpty()) return;
				handleInput(sCommand.charAt(0));
			}
		});
	}*/
	private void A2() {
		this.setLayout(new BorderLayout());
		//left side
		Container LeftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Container RightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		//Container CenterContainer = new Container(new BoxLayout(initialPressX));


		Button bMove = new Button("Move");
		Button bStop = new Button("Stop");
		Button bTurnLeft = new Button("Turn Left");
		Button bTurnRight = new Button("Turn Right");
		Button bChangeStrat = new Button("Change Strategie");
		Button bLectureHall = new Button("Lecture Hall");
		Button bRestroom = new Button("Restroom");
		Button bWaterDis = new Button("WaterDispenser");
		Button bStudent = new Button("Student");
		Button bNextFrame = new Button("Next Frame");
		
		LeftContainer.addAll(bMove, bStop, bTurnLeft, bTurnRight,
				bChangeStrat, bLectureHall, bRestroom, bWaterDis,
				bStudent, bNextFrame);
		
		this.add(BorderLayout.WEST, LeftContainer);
		this.add(BorderLayout.EAST, RightContainer);
		//this.add(BorderLayout.CENTER, CenterContainer);
		
		bMove.addActionListener(evt -> gm.Move());
	    bStop.addActionListener(evt -> gm.StopMove());
	    bTurnLeft.addActionListener(evt -> gm.TurnLeft());
	    bTurnRight.addActionListener(evt -> gm.TurnRight()); 
	    bChangeStrat.addActionListener(evt -> gm.ChangeStrat());
	    bLectureHall.addActionListener(evt -> gm.LectureHall()); 
	    bRestroom.addActionListener(evt -> gm.Restroom());
	    bWaterDis.addActionListener(evt -> gm.WaterDispenser());
	    bStudent.addActionListener(evt -> gm.Student());
	    bNextFrame.addActionListener(evt -> gm.advanceFrame());
	    
	    
		
		
		
		this.show();

	}
	
	//Handle Actions for the Buttons
	
	private void showAbout() {
		Dialog.show("About", "A2\nRoberto Ramil\nFall2024", "ok", null);
	}
	
	private void showExit() {
		boolean confirm = Dialog.show("Exit", "Are you sure you want to quit?", "Yes", "no" );
		if (confirm) Display.getInstance().exitApplication();
	}

		
}
