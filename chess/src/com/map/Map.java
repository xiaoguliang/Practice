package com.map;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.chess.Chess;
import com.imagefile.ImageFile;
import com.operate.Operate;
import com.operate.moveChess;

public class Map extends JFrame implements Runnable,ActionListener {
	
//	Paint
	private Point point = new Point(0,0);
	private int chessType = 1;
	private boolean black = false;//�ж��µ��Ǻ��ӻ��ǰ���
	private boolean white = true;
	private boolean isHave = true;//�ж������Ƿ��ص�
	private int chessNumber =0;//�������Ӹ���
	private moveChess move;
	private int whiteNum;
	private int blackNum;
	private int whitescore;
	private int blackscore;
	

	boolean isChosed= false;//�ж������Ƿ�ѡ��
	
	boolean isEat = false;//�ж��Ƿ����
	final int chessNumbers = 18;
	Chess m;
	JFrame owner;
	
	private JButton exit;
 
	private View chess;
	private ChessAddress chessaddress;
	private Image img;
	
	public Map(){
		final Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();		
		
		//�������ͼ��
		img= kit.getImage("src\\com\\imagefile\\mouse.png");
		Cursor dynamiteCursor = kit.createCustomCursor(img, new Point(3,3), "dynamite stick");
		this.setCursor(dynamiteCursor);
		
	    this.owner = Map.this;
		this.setTitle("������");
		final Container con =getContentPane();
		
		ImageFile.initialize();
		
		exit = new JButton("�˳�");
		
		chess = new View();
		chessaddress = new ChessAddress();	
		
		con.addMouseListener(new Operate(Map.this,owner));//Ϊ��ǰ��������������
		exit.addActionListener(this);
		exit.setBounds(680, 525, 100, 30);

		this.add(exit);	
		new Thread(this).start();
		
		
	this.setLayout(new BorderLayout());
	System.out.println("tupian");
		System.out.println("tupian");
		this.add(new Paint(this),BorderLayout.CENTER);
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);		
	}
	
	//ʵ�����Ĳ���ˢ��
	public void run() {
		while(true){
			this.repaint();
		try {
			Thread.sleep(50);			
		} catch (InterruptedException e) {
			e.printStackTrace();
			}
		}		
	}
	
	//���������ľ���
	public double countDistant(Point p1,Point p2){
		double distant=0;
		distant = Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y));
		return distant;
	}
	public void actionPerformed(ActionEvent e) {
		   if(e.getSource()==exit){
			   System.exit(0);
		   }
		   }
	public int getWhitescore() {
		return whitescore;
	}

	public void setWhitescore(int whitescore) {
		this.whitescore = whitescore;
	}

	public int getBlackscore() {
		return blackscore;
	}

	public void setBlackscore(int blackscore) {
		this.blackscore = blackscore;
	}
		
	public int getWhiteNum() {
		return whiteNum;
	}

	public void setWhiteNum(int whiteNum) {
		this.whiteNum = whiteNum;
	}

	public int getBlackNum() {
		return blackNum;
	}

	public void setBlackNum(int blackNum) {
		this.blackNum = blackNum;
	}

	
	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public int getchessType() {
		return chessType;
	}

	public void setType(int chessType) {
		this.chessType = chessType;
	}

	public boolean isBlack() {
		return black;
	}

	public void setBlack(boolean black) {
		this.black = black;
	}

	public boolean isWhite() {
		return white;
	}

	public void setWhite(boolean white) {
		this.white = white;
	}

	public boolean isHave() {
		return isHave;
	}

	public void setHave(boolean isHave) {
		this.isHave = isHave;
	}

	public int getChessNumber() {
		return chessNumber;
	}

	public void setChessNumber(int chessNumber) {
		this.chessNumber = chessNumber;
	}

	public moveChess getMove() {
		return move;
	}

	public void setMove(moveChess move) {
		this.move = move;
	}

	public boolean isChosed() {
		return isChosed;
	}

	public void setChosed(boolean isChosed) {
		this.isChosed = isChosed;
	}

	public boolean isEat() {
		return isEat;
	}

	public void setEat(boolean isEat) {
		this.isEat = isEat;
	}

	public Chess getM() {
		return m;
	}

	public void setM(Chess m) {
		this.m = m;
	}
	
	public View getChess() {
		return chess;
	}

	public void setChess(View chess) {
		this.chess = chess;
	}

	public ChessAddress getChessaddress() {
		return chessaddress;
	}

	public void setChessaddress(ChessAddress chessaddress) {
		this.chessaddress = chessaddress;
	}

	public int getChessNumbers() {
		return chessNumbers;
	};
}
