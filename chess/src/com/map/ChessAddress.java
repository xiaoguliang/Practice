package com.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChessAddress {
	private Point p;
	private List<Point> point = new ArrayList<Point>();
	public ChessAddress(){
		p= new Point(80,110);//左邊第一豎直
		point.add(p);
		p= new Point(80,320);
		point.add(p);
		p= new Point(80,530);
		point.add(p);
		p= new Point(140,170);//左邊第二豎直
		point.add(p);
		p= new Point(140,320);
		point.add(p);
		p= new Point(140,470);
		point.add(p);
		p= new Point(200,230);//左邊第三豎直
		point.add(p);
		p= new Point(200,320);
		point.add(p);
		p= new Point(200,410);
		point.add(p);
		p= new Point(290,110);//中間上豎直
		point.add(p);
		p= new Point(290,170);
		point.add(p);
		p= new Point(290,230);
		point.add(p);
		p= new Point(290,410);//中間下豎直
		point.add(p);
		p= new Point(290,470);
		point.add(p);
		p= new Point(290,530);
		point.add(p);
		p= new Point(380,230);//右邊第三豎直
		point.add(p);
		p= new Point(380,320);
		point.add(p);
		p= new Point(380,410);
		point.add(p);
		p= new Point(440,170);//右邊第二豎直
		point.add(p);
		p= new Point(440,320);
		point.add(p);
		p= new Point(440,470);
		point.add(p);
		p= new Point(500,110);//右邊第二豎直
		point.add(p);
		p= new Point(500,320);
		point.add(p);
		p= new Point(500,530);
		point.add(p);
	}
	public Point isOnAddress(Point p2){
			Iterator <Point> it = point.iterator();
			while(it.hasNext()){
				Point point = it.next();
				if(point.x>p2.x&&point.x<p2.x+50&&point.y>p2.y&&point.y<p2.y+50){					
					return point;
				}
			}
			return null;
		}
}
