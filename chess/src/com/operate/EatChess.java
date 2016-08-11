package com.operate;

import java.awt.Point;
import java.util.Iterator;

import javax.swing.JFrame;

import com.chess.Chess;
import com.jdialog.BeginMove;
import com.map.ChessAddress;
import com.map.View;
import com.ruler.IsOneLine;

public class EatChess {
	ChessAddress chessaddress;	
	private View chess;
	JFrame owner;
	public EatChess(View chess,JFrame owner){
		this.owner = owner;
		this.chess = chess;
		chessaddress = new ChessAddress();
	}
	public boolean eat(Point point,int type){
		Point pos =chessaddress.isOnAddress(point);
		if(pos!=null){
		//ʵ�����Ӳ��ص�
		Iterator <Chess> it = chess.getChess().iterator();
		while(it.hasNext()){
			Chess t = (Chess)it.next();	
			if(t.getType()==0||t.getType()==1){
			if(	pos.equals(t.getPoint())&&t.getType()!=type){//���ѡ������
				if(new IsOneLine(chess).oneline(pos,t.getType())){
					new BeginMove(owner,"�Է����ӳ��������ܳԣ�����ѡ!",true);
				}
				else{
				//����������
				t.setType(2);
				return true;
				}
			}
			}
		}
		}
		return false;
	}
}
