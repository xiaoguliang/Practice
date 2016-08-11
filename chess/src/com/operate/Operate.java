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
		//����
	
		if (map.getChessNumber() < map.getChessNumbers()&&!map.isEat()) {
			Point pos = map.getChessaddress().isOnAddress(map.getPoint());
			// ʵ�����Ӳ��ص�
			Iterator<Chess> it = map.getChess().getChess().iterator();
			while (it.hasNext()) {
				Chess t = it.next();
				if (pos == t.getPoint()&&(t.getType()==0||t.getType()==1)) {
					map.setHave(false);
				}
			}
			if (pos != null && map.isHave()) {
				if (map.isWhite())// ʵ�ֽ���������
				{
					map.setType(0) ;
					map.setWhite(false);
					map.setBlack(true);
					map.setBlackNum(map.getBlackNum()+1);
					System.out.println("������"+map.getBlackNum());
				} else {
					map.setType(1);
					map.setBlack(false);
					map.setWhite(true);
					map.setWhiteNum(map.getWhiteNum()+1);
					System.out.println("������"+map.getWhiteNum());
					
				}
				map.setChessNumber(map.getChessNumber()+1);				
				//System.out.println(map.getChessNumber()+1+"����");
				//System.out.println(map.getChessNumbers()+"��������");
				Chess obj = new Chess(pos, map.getchessType(), 50, 50);
				map.getChess().addChess(obj);
				//����������꣬��ʼ��������ʾ
				if(map.getChessNumber()==map.getChessNumbers()){
				new BeginMove(map,"�����Ѿ����꣬��ʼ������!",true);
				}
				//�ж��Ƿ�����������������ԳԵ��Է�һ������
				if(new IsOneLine(map.getChess()).oneline(pos,map.getchessType())){
					map.setEat(true);
					
					if(map.getchessType()==0){
						map.setBlackscore(map.getBlackscore()+1);
						System.out.println("���ӳ������� "+map.getBlackscore());
						new BeginMove(owner,"���ӳ��������Գ�һ�Ű���!",true);
					}else{
						map.setWhitescore(map.getWhitescore()+1);
						System.out.println("���ӳ������� "+map.getWhitescore());
						new BeginMove(owner,"���ӳ��������Գ�һ�ź���!",true);
					}
				}
				if(map.getChessNumber()==map.getChessNumbers()){
					map.setType(0);
				}
			}
		
			map.setHave(true);// ���³�ʼ���ΪΪ�����ӣ�Ϊ��һ�μ����׼��
		}
		else if (!map.isChosed() && map.getChessNumber() >= map.getChessNumbers()&&!map.isEat()) {
			Chess move = new moveChess(map.getChess()).judge(map.getPoint(),map.getchessType());
			if(move!=null){
				
			map.setM(new Chess(move.getPoint(), move.getType(), move.getHeigh(),move.getWidth()));
			if(map.getM().getType()==map.getchessType()){
				if (map.getchessType()==1)// ʵ�ֽ���������
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
				// ʵ�����Ӳ��ص�
				Iterator<Chess> is = map.getChess().getChess().iterator();
				while (is.hasNext()) {
					Chess t = is.next();						
					if (pos == t.getPoint()&&(t.getType()==0||t.getType()==1)) {
						map.setHave(false);
						break;
					}						
				}
				//�ж������Ƿ�ֻ��һ��
				if(map.isHave()&&(map.countDistant(pos,map.getM().getPoint())==60||map.countDistant(pos,map.getM().getPoint())==90||map.countDistant(pos,map.getM().getPoint())==150||map.countDistant(pos,map.getM().getPoint())==210)){
						map.setHave(true);
					}
				else map.setHave(false);
				//�������ֻ��һ�񣬲��Ҳ��ظ�����������
				if (map.isHave()) {//�����Ӽ������ҳ�Ҫ�µ��ǿ����ӣ��ı����Ĵ�С������
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
									System.out.println("���ӳ������� "+map.getBlackscore());
									new BeginMove(map,"���ӳ��������Գ�һ�Ű���!",true);
								}else{
									map.setWhitescore(map.getWhitescore()+1);
									System.out.println("���ӳ������� "+map.getWhitescore());
									new BeginMove(map,"���ӳ��������Գ�һ�ź���!",true);
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
			map.setHave(true);// ���³�ʼ���ΪΪ�����ӣ�Ϊ��һ�μ����׼��
		}
		else{
			//��ɳ��ӹ���
			if(new EatChess(map.getChess(),map).eat(map.getPoint(),map.getchessType())){
				if (map.getchessType()==1)// ʵ�ֽ���������
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
				new BeginMove(map,"����ʤ��!",true);
				owner.dispose();
				new Dynamic();
			}else if(num1<3&&map.getChessNumber()==18){
				new BeginMove(map,"����ʤ��!",true);
				owner.dispose();
				new Dynamic();
			}
			}
			else
				map.setEat(true);
		}
	}
}
