package main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import map.blockH;
import map.blockW;
import map.N1;
import map.O1;
import map.O3;
import map.O6;

public class MainGame extends JFrame implements KeyListener {

	myball ball = myball.getInstance();
	MyCanvas mc = new MyCanvas();
	JLabel title = new JLabel("°øÆ¢±â±â °ÔÀÓ ^^");
	N1 sample = new N1(10, 10);
	blockW box0 = new blockW(10, 10);
	blockW box1 = new blockW(10, 301);
	blockH box2 = new blockH(10, 40);
	O1 box3 = new O1(68, 241);
	O3 box4 = new O3(213, 181);
	O6 box5 = new O6(10, 10);
	int keyInfo[] = new int[] { -1, -1, -1, -1 }; // Å°°¡ ´­¸° Á¤º¸
	int level = 1;
	boolean level1flag = true;
	boolean level2flag = false;
	boolean level3flag = false;
	MainGame() {

		this.setBounds(100, 100, 520, 400);
		this.setLayout(new BorderLayout());
		this.add(mc, "Center");
		this.add(title, "South");
		this.setVisible(true);
		this.addKeyListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		key();
	}

public void map(int i) {
	switch(i) {
	case 1:level1flag = true;level2flag = false;level3flag=false; break;
	case 2:level1flag = false;level2flag = true;level3flag=false; break;
	case 3:level1flag = false;level2flag = false;level3flag=true; break;
	}
}
	private void key() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						if (ball != null) {
							if (ball.getBall_y() > 398) {
								ball = null;
							}
							// ÁÂ¿ì

							if (ball.getBall_x() < (box1.getX() + 32)) { // ¿ÞÂÊ¿¡¼­ ¸ØÃß±â
								keyInfo[0] = -1;
							} else if ((ball.getBall_x() + 8) > (box3.getX()) && (box3.getX()) > ball.getBall_x()
									&& ball.getBall_y() > (box3.getY()-5)) {
								keyInfo[2] = -1;
							} else if (ball.getBall_x() < (box3.getX() + box3.getWidth() + 2)
									&& ball.getBall_x() > (box3.getX() + box3.getWidth() - 2)
									&& ball.getBall_y() > box3.getY()-3) {
								keyInfo[0] = -1;
							}
							if((ball.getBall_x()+8)>(box4.getX()-2) && box4.getX()>(ball.getBall_x()+8)) {
								keyInfo[2]=-1;
							}
							
							
							keyAction();
							// »óÇÏ
							// ¹Ú½º À§¿¡¼­ ¸ØÃß°í ¶Û¶§
						
							
//							if (box3.Downstop()) {
//								ball.fall();
//							} else{
//								ball.autojump();
//							}

							if (box1.Downstop()) {
								ball.fall();
							} else{
								ball.autojump();
							}

							// ¿ÞÂÊº® ºÎµúÄ¥¶§
							if (box1.Leftstop()) {
								ball.leftmetjump();
							}

						}
						Thread.sleep(15);
						mc.repaint();
					} catch (Exception e) {
					System.out.println("Success Level1");
//					g.setFont(new Font("±Ã¼­", Font.BOLD, 30));// ÆùÆ®¼³Á¤
//					g.drawString("GameOver", 120, 140);
					}
				}
			}
		}).start();
	}

	private void keyAction() {
		if (ball.getBall_x() != 0) {

			if (keyInfo[0] == 1) {
				ball.moveBall_x(-3);
			}

			if (keyInfo[2] == 1) {
				ball.moveBall_x(3);
			}

		}
	}

	class MyCanvas extends Canvas {
		MyCanvas() {
			ball.fall();
			ball.autojump();
			this.setSize(300, 400);
			this.setBackground(Color.BLACK);
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			g.setColor(Color.white);
			if (ball != null)
				g.fillRoundRect(ball.getBall_x(), ball.getBall_y(), 8, 8, 8, 8);

			g.drawImage(box0.getIc(), box0.getX(), box0.getY(), box0.getWidth(), box0.getHeight(), this);
			g.drawImage(box2.getIc(), box2.getX(), box2.getY(), box2.getWidth(), box2.getHeight(), this);
			g.drawImage(box1.getIc(), box1.getX(), box1.getY(), box1.getWidth(), box1.getHeight(), this);
			
			g.drawImage(box5.getIc(), sample.getWidth()*15+22,box2.getY()+box1.getHeight()-60,box5.getWidth(),box5.getHeight(),this);
			g.drawImage(box3.getIc(), box3.getX(), box3.getY(), box3.getWidth(), box3.getHeight(), this);
			
			g.drawImage(box4.getIc(),box4.getX(),box4.getY(),box4.getWidth(),box4.getHeight(),this);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 37) {
			keyInfo[0] = 1;
		} else if (e.getKeyCode() == 39) {
			keyInfo[2] = 1;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
//		
		keyInfo[0] = -1;
		keyInfo[2] = -1;

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}