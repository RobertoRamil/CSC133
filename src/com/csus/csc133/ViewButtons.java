package com.csus.csc133;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.layouts.BoxLayout;

public class ViewButtons extends Container {
	private ButtonFormat[] buttons = new ButtonFormat[10];
	
	public ViewButtons(Command[] pCom, Command[] wCom) {
		//getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		getAllStyles().setBgColor(ColorUtil.rgb(200, 200, 200));
		
		setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		for(int i = 0; i < pCom.length; i++) {
			buttons[i] = new ButtonFormat();
			buttons[i].setCommand(pCom[i]);
			add(buttons[i]);
		}
		for(int i = 0; i < 5; i++) {
			buttons[i+5] = new ButtonFormat();
			buttons[i+5].setCommand(wCom[i]);
			add(buttons[i+5]);
		}
	}
	
	public void paused(Command[] wCom) {
		buttons[5].setCommand(wCom[0]);
	}
	public void unpause(Command[] wCom) {
		buttons[5].setCommand(wCom[1]);
	}
}
