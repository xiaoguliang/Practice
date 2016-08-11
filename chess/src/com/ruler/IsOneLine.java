package com.ruler;

import java.awt.Point;
import java.util.Iterator;

import com.chess.Chess;
import com.map.ChessAddress;
import com.map.View;

public class IsOneLine {
	ChessAddress chessaddress;
	private View chess;
	public IsOneLine(View chess){
		this.chess = chess;
		chessaddress = new ChessAddress();
	}
	public double countDistant(Point p1,Point p2){
		double distant=0;
		distant = Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y));
		return distant;
	}
	public boolean oneline(Point p,int type){
		int count=0;
		int distant1 = 60,distant2 =90,distant3=180,distant4 =150,distant5=300,distant6 =210,distant7=420,distant8=120;
		int count1=0,count2=0,count3=0,count4=0;
		Iterator <Chess> it = chess.getChess().iterator();
		while(it.hasNext()){
			count++;
			Chess t = (Chess)it.next();
			if(t.getType()==0||t.getType()==1){
			if(t.getType()==type)
			{
			if(p.x==t.getPoint().x&&(countDistant(p,t.getPoint())==distant4||countDistant(p,t.getPoint())==distant5)){
				count1++;
			}
			if(p.y==t.getPoint().y&&(countDistant(p,t.getPoint())==distant4||countDistant(p,t.getPoint())==distant5)){
				count4++;
			}
			if(p.x==t.getPoint().x&&(countDistant(p,t.getPoint())==distant8||countDistant(p,t.getPoint())==distant1||countDistant(p,t.getPoint())==distant2 ||countDistant(p,t.getPoint())==distant3||countDistant(p,t.getPoint())==distant6 ||countDistant(p,t.getPoint())==distant7)){
				if((p.x==290&&countDistant(p,t.getPoint())==180||countDistant(p,t.getPoint())==300))//||countDistant(p,t.getPoint())==420)排除掉中间时一边为60，一边为180或者300，或者420的情况
				{
					//count2++;
					//System.out.println(count2);
					count2=count2;
				}
				else{
				count2++;
				}
			}
			if(p.y==t.getPoint().y&&(countDistant(p,t.getPoint())==distant8||countDistant(p,t.getPoint())==distant1||countDistant(p,t.getPoint())==distant2 ||countDistant(p,t.getPoint())==distant3 ||countDistant(p,t.getPoint())==distant6 ||countDistant(p,t.getPoint())==distant7)){
				if(p.y==320&&(countDistant(p,t.getPoint())==180||countDistant(p,t.getPoint())==300||countDistant(p,t.getPoint())==420))//排除掉中间时一边为60，一边为180或者300，或者420的情况
				{
					count3=count3;
				}
				else{
				count3++;
				}
			}	
			}
			}
		}
		if((count1==2||count2==2||count3==2||count4==2)){
			if(type==1)
			{
				System.out.println("白子成三!");
				return true;				
			}
			else 
				System.out.println("黑子成三!");
			return true;
		}
		else
			return false;
	}
}
