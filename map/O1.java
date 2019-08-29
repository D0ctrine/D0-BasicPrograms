package map;

import main.myball;
import map.block;

public class O1 extends block{

	public O1(int x, int y) {
		setX(x);
		setY(y);
		setIc("./img/º®7.png");
		setHeight(getIc().getHeight(null));
		setWidth(getIc().getWidth(null));
	}
	myball ball = myball.getInstance();
	boolean o1 = false;
	public boolean isO1() {
		return o1;
	}

	public void setO1(boolean o1) {
		this.o1 = o1;
	}

	@Override
	public boolean Downstop() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if ((ball.getBall_x() + 8) > getX() & (getX() + getWidth()) > ball.getBall_x()) {
					if ((ball.getBall_y() + 8) > (getY() - 2)	& (getY() + 2) > (ball.getBall_y() + 8)) {
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