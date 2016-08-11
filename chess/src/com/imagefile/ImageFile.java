package com.imagefile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageFile {
	public static BufferedImage gamebackground;
	public static BufferedImage whitechess;
	public static BufferedImage blackchess;
	public static BufferedImage whitechess1;
	public static BufferedImage blackchess1;
	public static ImageIcon beginMove;
	
	public static void initialize(){
		try {
			gamebackground = ImageIO.read(new File("src\\com\\imagefile\\background.jpg"));
			whitechess = ImageIO.read(new File("src\\com\\imagefile\\whitechess.png"));
			blackchess = ImageIO.read(new File("src\\com\\imagefile\\blackchess.png"));
			whitechess1 = ImageIO.read(new File("src\\com\\imagefile\\whitechess1.png"));
			blackchess1 = ImageIO.read(new File("src\\com\\imagefile\\blackchess1.png"));
//	
			beginMove = new ImageIcon("src\\com\\imagefile\\beginMove.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
