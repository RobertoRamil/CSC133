package com.csus.csc133;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.plaf.Border;

//Center  GUI
public class ViewMap extends Container implements Observer{
	public ViewMap() {
		getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.rgb(255, 0, 0)));
		getAllStyles().setBgColor(ColorUtil.rgb(200, 200, 200));
		
		add(new Label("Center/View Map"));
	}

	@Override
	public void update(Observable gameModel, Object data) {
		// TODO Auto-generated method stub
		System.out.println("Map updated");
	}

}
