package map;

import java.awt.Image;

import javax.swing.ImageIcon;

import main.myball;
import map.block;

public class blockW extends block {

	myball ball = myball.getInstance();
	boolean flag = false;
	public blockW(int x, int y) {
		setX(x);
		setY(y);
		setIc("./img/°¡·Î.png");
		setHeight(getIc().getHeight(null));
		setWidth(getIc().getWidth(null));
	}
	boolean blockW = false;
	public boolean isBlockW() {
		return blockW;
	}

	public void setBlockW(boolean blockW) {
		this.blockW = blockW;
	}

	@Override
	public boolean Downstop() {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						if ((ball.getBall_y() + 8) > (getY() - 2)	& (getY() + 2) > (ball.getBall_y() + 8)) {
							if ((ball.getBall_x() + 8) > getX() & (getX() + getWidth()) > ball.getBall_x()) {
								ball.setStartjump();
								ball.setdownFlag(false);
							} 
						}
					}
				}).start();
		
		return ball.getdownFlag();
	}

	@Override
	public boolean Leftstop() {
		// TODO Auto-generated method stub
		if(ball.getBall_x()<(getX()+32)) {
			ball.setdownFlag(false);
			ball.setJump(false);
			ball.setRightjump(true);
		}
		
		return ball.isRightjump();
	}

	@Override
	public boolean Rightstop() {
		// TODO Auto-generated method stub
		return false;
	}
}