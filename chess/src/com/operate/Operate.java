package com.operate;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JFrame;

import com.app.Dynamic;
import com.chess.Chess;
import com.jdialog.BeginMove;
import com.map.Map;
import com.ruler.IsOneLine;

public class Operate extends MouseAdapter{

	private Map map;
	private JFrame owner;
	
	public Operate(Map map,JFrame owner){
		this.map = map;
		this.owner = owner;
	}
	public void mouseClicked(MouseEvent me){
		map.setPoint(me.getPoint());
		//下子
	
		if (map.getChessNumber() < map.getChessNumbers()&&!map.isEat()) {
			Point pos = map.getChessaddress().isOnAddress(map.getPoint());
			// 实现棋子不重叠
			Iterator<Chess> it = map.getChess().getChess().iterator();
			while (it.hasNext()) {
				Chess t = it.next();
				if (pos == t.getPoint()&&(t.getType()==0||t.getType()==1)) {
					map.setHave(false);
				}
			}
			if (pos != null && map.isHave()) {
				if (map.isWhite())// 实现交替下棋子
				{
					map.setType(0) ;
					map.setWhite(false);
					map.setBlack(true);
					map.setBlackNum(map.getBlackNum()+1);
					System.out.println("黑棋数"+map.getBlackNum());
				} else {
					map.setType(1);
					map.setBlack(false);
					map.setWhite(true);
					map.setWhiteNum(map.getWhiteNum()+1);
					System.out.println("白棋数"+map.getWhiteNum());
					
				}
				map.setChessNumber(map.getChessNumber()+1);				
				//System.out.println(map.getChessNumber()+1+"棋子");
				//System.out.println(map.getChessNumbers()+"棋子总数");
				Chess obj = new Chess(pos, map.getchessType(), 50, 50);
				map.getChess().addChess(obj);
				//添加棋子下完，开始走棋子提示
				if(map.getChessNumber()==map.getChessNumbers()){
				new BeginMove(map,"棋子已经下完，开始走棋子!",true);
				}
				//判断是否成三，如果成三则可以吃掉对方一颗棋子
				if(new IsOneLine(map.getChess()).oneline(pos,map.getchessType())){
					map.setEat(true);
					
					if(map.getchessType()==0){
						map.setBlackscore(map.getBlackscore()+1);
						System.out.println("黑子成三分数 "+map.getBlackscore());
						new BeginMove(owner,"黑子成三，可以吃一颗白子!",true);
					}else{
						map.setWhitescore(map.getWhitescore()+1);
						System.out.println("白子成三分数 "+map.getWhitescore());
						new BeginMove(owner,"白子成三，可以吃一颗黑子!",true);
					}
				}
				if(map.getChessNumber()==map.getChessNumbers()){
					map.setType(0);
				}
			}
		
			map.setHave(true);// 重新初始标记为为下棋子，为下一次检测做准备
		}
		else if (!map.isChosed() && map.getChessNumber() >= map.getChessNumbers()&&!map.isEat()) {
			Chess move = new moveChess(map.getChess()).judge(map.getPoint(),map.getchessType());
			if(move!=null){
				
			map.setM(new Chess(move.getPoint(), move.getType(), move.getHeigh(),move.getWidth()));
			if(map.getM().getType()==map.getchessType()){
				if (map.getchessType()==1)// 实现交替下棋子
				{
					map.setType(0);
					
				} else {
					map.setType(1);
				}
			map.setChosed(true);
			}
			}
		} else if(!map.isEat()){
			Point pos = map.getChessaddress().isOnAddress(map.getPoint());
			if (pos != null) {
				// 实现棋子不重叠
				Iterator<Chess> is = map.getChess().getChess().iterator();
				while (is.hasNext()) {
					Chess t = is.next();						
					if (pos == t.getPoint()&&(t.getType()==0||t.getType()==1)) {
						map.setHave(false);
						break;
					}						
				}
				//判断棋子是否只走一格
				if(map.isHave()&&(map.countDistant(pos,map.getM().getPoint())==60||map.countDistant(pos,map.getM().getPoint())==90||map.countDistant(pos,map.getM().getPoint())==150||map.countDistant(pos,map.getM().getPoint())==210)){
						map.setHave(true);
					}
				else map.setHave(false);
				//如果满足只走一格，并且不重复，才下棋子
				if (map.isHave()) {//从棋子集合中找出要下的那颗棋子，改变他的大小好坐标
					Iterator<Chess> it = map.getChess().getChess().iterator();
					while (it.hasNext()) {
						Chess t = (Chess) it.next();
						if(map.getM().getPoint().equals(t.getPoint())&&(t.getType()==0||t.getType()==1)) {
							Point po = map.getM().getPoint();
							t.setPoint(pos);
							t.setHeigh(50);
							t.setWidth(50);
							map.setChosed(true);
							if(new IsOneLine(map.getChess()).oneline(t.getPoint(),t.getType())){
								map.setType(t.getType());
								map.setEat(true);
								if(map.getchessType()==0){
									map.setBlackscore(map.getBlackscore()+1);
									System.out.println("黑子成三分数 "+map.getBlackscore());
									new BeginMove(map,"黑子成三，可以吃一颗白子!",true);
								}else{
									map.setWhitescore(map.getWhitescore()+1);
									System.out.println("白子成三分数 "+map.getWhitescore());
									new BeginMove(map,"白子成三，可以吃一颗黑子!",true);
								}
							}
							break;
						}
					}
				}					
			}
			if (map.isHave()&&map.isChosed()) {
				map.setChosed(false);
			}
			map.setHave(true);// 重新初始标记为为下棋子，为下一次检测做准备
		}
		else{
			//完成吃子过程
			if(new EatChess(map.getChess(),map).eat(map.getPoint(),map.getchessType())){
				if (map.getchessType()==1)// 实现交替下棋子
				{
					map.setType(0);
					
				} else {
					map.setType(1);
				}
			map.setEat(false);
			int num1=0,num2=0;
			Iterator<Chess> black = map.getChess().getChess().iterator();
			while (black.hasNext()) {
				Chess t = black.next();
				if (t.getType()==0) {
					num1++;
				}
			}
			Iterator<Chess> white = map.getChess().getChess().iterator();
			while (white.hasNext()) {
				Chess t = white.next();
				if (t.getType()==1) {
					num2++;
				}
			}
			if(num2<3&&map.getChessNumber()==18){
				new BeginMove(map,"黑棋胜利!",true);
				owner.dispose();
				new Dynamic();
			}else if(num1<3&&map.getChessNumber()==18){
				new BeginMove(map,"白棋胜利!",true);
				owner.dispose();
				new Dynamic();
			}
			}
			else
				map.setEat(true);
		}
	}
}
