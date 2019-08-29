package map;

import java.awt.Image;

import javax.swing.ImageIcon;

import main.myball;
import map.block;

public class blockH extends block {

	myball ball = myball.getInstance();
	public blockH(int x,int y){
		setX(x);
		setY(y);
		setIc("./img/¼¼·Î.png");
		setHeight(getIc().getHeight(null));
		setWidth(getIc().getWidth(null));
	}

	@Override
	public boolean Downstop() {
		// TODO Auto-generated method stub
						if ((ball.getBall_x() + 8) > (getX() + 2) && (getX() + getWidth()) > ball.getBall_x()) {
							if ((ball.getBall_y() + 8) > (getY() - 2) && (getY()+2)>(ball.getBall_y()+8)) {
								ball.setdownFlag(false);
								ball.setJump(true);
							} else {
								ball.setdownFlag(true);
							}
						} else {
							ball.setdownFlag(true);
						}

					
			
			return ball.getJump();
	}

	@Override
	public boolean Leftstop() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Rightstop() {
		// TODO Auto-generated method stub
		return false;
	}
}