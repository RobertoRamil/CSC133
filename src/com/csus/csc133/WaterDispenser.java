

package com.csus.csc133;

import com.codename1.ui.Graphics;
import com.codename1.charts.util.ColorUtil;

public class WaterDispenser extends Facility {
	
	public WaterDispenser(float x, float y) {
		super(x, y);
		setStatusID(1);
		super.setColor(ColorUtil.BLUE);
		super.setSize(50);
	}
	
	@Override
	public void draw(Graphics g, int getX, int getY) {
		g.setColor((int) this.getColor());
		g.fillArc((int)(getX()-getSize()/2 + getX), (int)(getY()-getSize()/2 + getY), (int)getSize(), (int)getSize(), 0, 360);

	}

}
