package com.operate;

import java.awt.Point;
import java.util.Iterator;

import com.chess.Chess;
import com.map.ChessAddress;
import com.map.View;

public class moveChess {
	ChessAddress chessaddress;	
	private View chess;
	public moveChess(View chess){
		this.chess = chess;
		chessaddress = new ChessAddress();
	}	
	public Chess judge(Point point,int type){
		Point pos =chessaddress.isOnAddress(point);
		if(pos!=null){
		//实现棋子不重叠
		Iterator <Chess> it = chess.getChess().iterator();
		while(it.hasNext()){
			Chess t = (Chess)it.next();			
			if(	pos.equals(t.getPoint())&&t.getType()==type&&(t.getType()==0||t.getType()==1)){//如果选中棋子
				t.setHeigh(60);
				t.setWidth(60);
				return t;
			}
		}
		}
		return null;
	}	
}
