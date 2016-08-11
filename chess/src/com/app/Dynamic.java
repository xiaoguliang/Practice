package com.app;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.map.Map;

public class Dynamic extends JFrame implements Runnable {

	private BufferedImage start;
	private BufferedImage background;
	
	private BufferedImage exit;

	private boolean isStart = true;

	public Dynamic() {
		this.setTitle("欢乐成三棋");
		try {
			start = ImageIO.read(new File("image\\start1.png"));
	
			exit = ImageIO.read(new File("image\\exit1.png"));
			background = ImageIO.read(new File("image\\back.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Thread(this).start();

		this.addMouseListener(new Listener());
		this.addMouseMotionListener(new MouseMotionListener() {

			public void mouseDragged(MouseEvent e) {
			}

			public void mouseMoved(MouseEvent e) {
				try {

					if ((e.getX() > 240 && e.getX() < 440)
							&& (e.getY() > 250 && e.getY() < 320)) {
						start = ImageIO.read(new File("image\\start2.png"));
						} else {
							start = ImageIO.read(new File("image\\start1.png"));
					}
					if ((e.getX() > 240 && e.getX() < 440)
							&& (e.getY() > 350 && e.getY() < 420)) {
						exit = ImageIO.read(new File("image\\exit2.png"));
					} else {
						exit = ImageIO.read(new File("image\\exit1.png"));
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		});

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(670, 450);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private class Listener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if ((e.getX() > 240 && e.getX() < 440)
					&& (e.getY() > 250 && e.getY() < 320)) {
				int isOk = JOptionPane.showConfirmDialog(null, "游戏即将开始，你准备好了吗!",
						"询问......", JOptionPane.CANCEL_OPTION);
				if (isOk == 0) {
					Dynamic.this.dispose();
					isStart = false;
					new Map();
				}

			}else if ((e.getX() > 240 && e.getX() < 440)
					&& (e.getY() > 350 && e.getY() < 420)) {
				int isOk = JOptionPane.showConfirmDialog(null, "你确定要退出吗!",
						"提示......", JOptionPane.CANCEL_OPTION);
				if (isOk == 0) {
					Dynamic.this.dispose();
				}
			}
		}
	}

	public void paint(Graphics g) {
		BufferedImage image = new BufferedImage(670, 450,
				BufferedImage.TYPE_3BYTE_BGR);
		Graphics g2 = image.getGraphics();
		g2.drawImage(background, 0, 0, this);

		g2.drawImage(start, 240, 250, this);
		g2.drawImage(exit, 240, 350, this);

		g.drawImage(image, 0, 0, this);
	}

	public static void main(String[] args) {
		new Dynamic();
	}

	public void run() {
		while (isStart) {
			this.repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
