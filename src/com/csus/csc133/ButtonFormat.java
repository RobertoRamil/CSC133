package com.csus.csc133;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.plaf.Border;

public class ButtonFormat extends Button{
	ButtonFormat(){
		super("NONE");
		getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		getAllStyles().setBgTransparency(255);
		getAllStyles().setBgColor(ColorUtil.rgb(115, 150, 200));
		getAllStyles().setFgColor(ColorUtil.BLACK);
	}

}
