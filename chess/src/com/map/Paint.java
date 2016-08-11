package com.map;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.chess.Chess;
import com.imagefile.ImageFile;

public class Paint extends JPanel{
	private final int width = 6;
	private final int heigh = 25;
	private Map map;
	public Paint(Map map){
		this.map = map;
	}
	public void paint(Graphics g){
		BufferedImage image = new BufferedImage(this.getWidth()+6,this.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
		Graphics g2 = image.getGraphics();
		g2.drawImage(ImageFile.gamebackground,-width,-heigh,800,600,this);//绘制棋盘背景
		g2.drawImage(ImageFile.blackchess1,530,200,30,30,this);
		g2.drawImage(ImageFile.whitechess1,530,306,30,30,this);
		Graphics2D g3=(Graphics2D)g2;
		BasicStroke stoke=new BasicStroke(5);	//设置线条宽度			
	
		g3.setStroke(stoke);
		g3.setColor(Color.lightGray); //设置线条颜色
		g2.drawRect(80-width,110-heigh,420,420);            //外框
		g2.drawRect(140-width,170-heigh,300,300);          //中框
		g2.drawRect(200-width,230-heigh,180,180);          //内框
		g2.drawLine(290-width,110-heigh,290-width,230-heigh);           //上竖线
		g2.drawLine(290-width,410-heigh,290-width,530-heigh);          //下竖线    
		g2.drawLine(80-width,320-heigh,200-width,320-heigh);           //左横线
		g2.drawLine(380-width,320-heigh,500-width,320-heigh);	      //右横线
//		g3.setColor(Color.black);
//		g2.drawOval(745, 200, 27, 27);
//		g3.setColor(Color.lightGray);
//		g2.drawOval(745, 255, 27, 27);
		//显示执子提示信息
		g3.setColor(Color.RED);           //设置线条颜色		
		if(map.getchessType()==0){
		g2.drawString("白棋执子", 270-width, 560-heigh);
		}else if(map.getchessType()==1){
		g2.drawString("黑棋执子", 270-width, 560-heigh);
		}		
		//绘制棋子
		Iterator <Chess> it = map.getChess().getChess().iterator();
		//g2.drawImage(ImageFile.whitechess,50,50,100,100,this);//绘制棋子圖片

		while(it.hasNext()){
			Chess t =it.next();
			if(t.getType()==0||t.getType()==1){
			g2.drawImage(t.getShowImage(),t.getPoint().x-t.getWidth()/2-width,t.getPoint().y-t.getHeigh()/2-heigh,t.getHeigh(),t.getWidth(),this);//绘制棋子圖片
		}
		}
		g3.setFont(new Font("华文楷体",0,30));
		g2.drawString("剩余下子数：", 570, 225);
		g2.drawString("剩余下子数：", 570, 330);
		g2.drawString("得分：", 655, 275);
		g2.drawString("得分：", 655, 380);
		int x=map.getChessNumbers()/2-map.getBlackNum();
		int y=map.getChessNumbers()/2-map.getWhiteNum();
		String a =""+x;
		String b =""+y;
		String c =""+map.getBlackscore();
		String d =""+map.getWhitescore();
		g2.drawString(a, 745, 225);
		g2.drawString(b, 745, 330);
		g2.drawString(c, 745, 275);
		g2.drawString(d, 745, 380);
		//设置文字属性
		g3.setFont(new Font("华文行楷",2,45));
		g2.drawString("欢乐成三棋", 170-width, 90-heigh);
		//g2.drawString("欢乐成三棋", 170-width, 70-heigh);
		g.drawImage(image, 0, 0, this);		
	}
}
