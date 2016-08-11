package com.chess;

import java.awt.Point;
import java.awt.image.BufferedImage;

import com.imagefile.ImageFile;

public class Chess {
	private Point point;
	private BufferedImage showImage;
	private int type;
	private int heigh;
	private int width;
	public Chess(Point point,int type,int height,int width){
		this.point = point;
		this.type = type;
		this.heigh=height;
		this.width = width;
		if(type ==1){
			this.showImage = ImageFile.whitechess;
		}else{
			this.showImage = ImageFile.blackchess;
		}
	}
	
	public int getHeigh() {
		return heigh;
	}

	public void setHeigh(int heigh) {
		this.heigh = heigh;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Point getPoint() {
		return point;
	}

	public int getType() {
		return type;
	}

	public BufferedImage getShowImage() {
		return showImage;
	}	
}
