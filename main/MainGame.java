package main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JFrame;

public class MainGame extends JFrame {
	
	ArrayList<blockM> bbox = new ArrayList<blockM>();
	MyCanvas mc = new MyCanvas();
	MainGame(){
		
		
		this.setBounds(100, 100, 600, 800);
		this.setLayout(new BorderLayout());
		this.add(mc,"Center");
		this.setVisible(true);
		key();
	}
	
	private void key() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					try {
						Thread.sleep(20);
						mc.repaint();
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		}).start();
	}

		
		
		
	class MyCanvas extends Canvas{
		MyCanvas(){
			this.setSize(600, 800);
			this.setBackground(Color.BLACK);
		}
		
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			
			
//			for( int i=0;i<10;i++) {
//				bb= new blockM(bbox.get(i).getX()+bbox.get(i).getWidth(), 10);
//				bbox.add(bb);
//			}
			for(int i=0;i<10;i++) {
				blockM bb = new blockM(10, 10);
				g.drawImage(bb.getIc(), bb.getX()+bb.getWidth()*i, bb.getY(), this);
				
			}
		}
	}
	
}
