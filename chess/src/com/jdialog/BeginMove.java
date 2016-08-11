package com.jdialog;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.imagefile.ImageFile;

public class BeginMove extends JDialog {
	JFrame owner;
	private JButton okButton;
	private JLabel showJLabel;
	private JLabel image;
	
	public BeginMove(JFrame owner,String name,boolean is){
		
		super(owner,true);
		okButton = new JButton("ȷ��");
		showJLabel = new JLabel(name);
		image = new JLabel(ImageFile.beginMove);
		
		//����ȷ���رմ���		
		okButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me) {
				if(me.getSource()==okButton){
					BeginMove.this.dispose();
				}
			}
		});
		
		this.setLayout(null);
		//������ʾ�����ִ�С
		showJLabel.setBounds(50, 50, 200, 30);
		showJLabel.setFont(new Font("�����п�",3,15));
		this.add(showJLabel);
		okButton.setBounds(90,100,100,40);
		this.add(okButton);
		image.setBounds(0,0,800,600);
		this.add(image);
		this.setTitle("��ʾ");		
		this.setSize(300,200);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
