package com.csus.csc133;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;


public class ViewStatus extends Container implements Observer{
	private Label lectHallName;
	private Label lectTimeRem;
	private Label gameTime;
	private Label absenceNum;
	private Label hydrate;
	private Label waterIn;
	private Label talkTimeRem;
	
	public ViewStatus() {
		getAllStyles().setBgColor(ColorUtil.rgb(200, 200, 200));
		setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		
		lectHallName = new Label("Lecture occuring at: ");
		lectTimeRem = new Label("Lecture starts ins: ");
		gameTime = new Label("Game time: ");
		absenceNum = new Label("Number of absences: ");
		hydrate = new Label("Player hydration: ");
		waterIn = new Label("Player water intake: ");
		talkTimeRem = new Label("Can move again in: ");
		
		add(lectHallName);
		add(lectTimeRem);
		add(gameTime);
		add(absenceNum);
		add(hydrate);
		add(waterIn);
		add(talkTimeRem);
	}

	@Override
	public void update(Observable Obs, Object data) {
		// TODO Auto-generated method stub
		GameModel gm = (GameModel) Obs;
		LectureHall handle = gm.getFirstlect();
		//System.out.println("Stat updated");
		if(handle != null) {
			lectHallName.setText("Lecture occuring at: " +handle.getName());
			lectTimeRem.setText("Lecture starts ins: "+handle.getLect().getTimeRem());
		}
		else {
			lectHallName.setText("Lecture occuring at: NONE" );
			lectTimeRem.setText("Lecture starts ins: NO ACTIVE LECTURE");
		}
		gameTime.setText("Game time: " + gm.getGameTime());
		absenceNum.setText("Number of absences: " + gm.getPlayer().getAbsenceTime());
		hydrate.setText("Player hydration: " + gm.getPlayer().getHydration());
		waterIn.setText("Player water intake: "+ gm.getPlayer().getWaterIn());
		talkTimeRem.setText("Can move again in: "+ gm.getPlayer().getTimeRem());
	}
}
