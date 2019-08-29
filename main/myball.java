package main;

import movement.moveaction;

public class myball implements moveaction {
	int ball_x = 50;
	int ball_y = 270;
	
	boolean downflag = true;
	boolean jump = false;
	boolean rightjump = false;
	boolean leftjump = false;
	int startjump = getBall_y();
	public int getStartjump() {
		return startjump;
	}

	public void setStartjump() {
		this.startjump = this.getBall_y();
	}

	public boolean isRightjump() {
		return rightjump;
	}

	public void setRightjump(boolean rightjump) {
		this.rightjump = rightjump;
	}

	public boolean isLeftjump() {
		return leftjump;
	}

	public int getGo_up() {
		return go_up;
	}

	public void setGo_up(int go_up) {
		this.go_up = go_up;
	}

	public void setLeftjump(boolean leftjump) {
		this.leftjump = leftjump;
	}

	int go_up = 30;

	public boolean getJump() {
		return jump;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}

	public boolean getdownFlag() {
		return downflag;
	}

	public void setdownFlag(boolean flag) {
		this.downflag = flag;
	}

	static myball ballInstance = null;

	public static myball getInstance() {
		if (ballInstance == null) {
			ballInstance = new myball();
		}
		return ballInstance;
	}

	public int getBall_x() {
		return ball_x;
	}

	public void setBall_x(int ball_x) {
		this.ball_x = ball_x;
	}

	public void moveBall_x(int ball_x) {
		this.ball_x += ball_x;
	}

	public void moveBall_y(int ball_y) {
		this.ball_y += ball_y;
	}

	public int getBall_y() {
		return ball_y;
	}

	public void setBall_y(int ball_y) {
		this.ball_y = ball_y;
	}

	
	
	int maxjump = startjump - go_up;
	
	@Override
	public void autojump() {
		// TODO Auto-generated method stub
		
		if (getBall_y() > maxjump) {
			setdownFlag(false);
			try {
				moveBall_y(-3);
				Thread.sleep(2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			setdownFlag(true);
			jump = false;

		}

	}

	@Override
	public void leftmetjump() {
		// TODO Auto-generated method stub
		if (getBall_x() < 311 || getBall_y() < maxjump) {
			moveBall_x(15);
			moveBall_y(-5);
			try {
				Thread.sleep(14);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			moveBall_x(19);
			moveBall_y(-5);
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			moveBall_x(20);
			moveBall_y(-10);

			setdownFlag(true);
			setJump(true);
			setRightjump(false);
		}
	}

	@Override
	public void rightmetjump() {
		// TODO Auto-generated method stub

	}

	@Override
	public void superjump() {
		// TODO Auto-generated method stub

	}

	@Override
	public void goleft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void goright() {
		// TODO Auto-generated method stub
			moveBall_x(ball_x);
	}

	@Override
	public void fall() {
		// TODO Auto-generated method stub

		try {
			moveBall_y(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}