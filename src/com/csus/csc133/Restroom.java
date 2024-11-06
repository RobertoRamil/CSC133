package com.csus.csc133;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Restroom extends Facility {
	
	public Restroom(float x, float y) {
		super(x, y);
		setStatusID(2);
		
		super.setColor(ColorUtil.GREEN);
		super.setSize(50);
	}

	@Override
	public void  draw(Graphics g, int getX, int getY) {
		g.setColor((int) this.getColor());
		g.fillRect((int)(getX()-getSize()/2 + getX), (int)(getY()-getSize()/2 + getY), (int)getSize(), (int)getSize());
	}
}
