package com.csus.csc133;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.geom.Shape;
import com.codename1.ui.plaf.Border;


//Center  GUI
public class ViewMap extends Container implements Observer{
	private GameModel gm;
	private SacRun form;
	private int selected = -4;
	private boolean change = false;
	public ViewMap(GameModel gm, SacRun form) {
		this.gm = gm;
		this.form = form;
		getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.rgb(255, 0, 0)));
		getAllStyles().setBgColor(ColorUtil.rgb(200, 200, 200));
		
		//add(new Label("Center/View Map"));
	}
	
	public void pointerPressed (int x, int y) {
		int thisX = x - this.getAbsoluteX();
		int thisY = y - this.getAbsoluteY();
		//When pressed do an AABB collision check with all items
		if(change) {
			change = false;
			form.moveItem(thisX,thisY, selected);
		}
		else {	
			//Check if player was clicked
			selected = -4;
			if(mouseClickOnObj(gm.getPlayer(), thisX, thisY)) {
				//System.out.println("Player Selected");
				selected = -3;
			}
			if(mouseClickOnObj(gm.getStrat1(), thisX,thisY)) {
				selected = -2;
			}
			if(mouseClickOnObj(gm.getStrat2(), thisX,thisY)) {
				selected = -1;
			}
			GameObjectCollection handle = gm.getCol();
			for(int i = 0; i < gm.getCol().size(); i++) {
				if(mouseClickOnObj(handle.get(i), thisX,thisY)) {
					selected = i;
				}
			}
		}
		repaint();
	}
	
	public int selectedItem() {
		return selected;
	}
	
	public void awaitClick() {
		change = true;
	}
	
	private boolean mouseClickOnObj(GameObject obj, int x, int y) {
		if(		((obj.getY()- obj.getSize()/2)< y && y<(obj.getY()+ obj.getSize()/2)) &&
				((obj.getX()- obj.getSize()/2)< x && x<(obj.getX()+ obj.getSize()/2))) {
			return true;
		}
		return false;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		gm.getPlayer().draw(g, getX(), getY());
		if(selected == -3) {
			gm.getPlayer().drawBox(g, getX(), getY());
		}
		gm.getStrat1().draw(g, getX(), getY());
		if(selected ==-2) {
			gm.getStrat1().drawBox(g, getX(), getY());
		}
		gm.getStrat2().draw(g, getX(), getY());
		if(selected == -1) {
			gm.getStrat2().drawBox(g, getX(), getY());
		}
		
		GameObjectCollection handle = gm.getCol();
		handle.reset();
		GameObject item;
		int i = 0;
		while(handle.hasNext()) {
			item = (GameObject) handle.getNext();
			item.draw(g, getX(), getY());
			if(selected == i) {
				item.drawBox(g, getX(), getY());
			}
			i++;
		}
		//System.out.println("Displaying Objects");
	}

	@Override
	public void update(Observable gameModel, Object data) {
		// TODO Auto-generated method stub
		//System.out.println("Map updated");
		repaint();
	}

}
