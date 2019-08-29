package map;

import java.awt.Image;

import javax.swing.ImageIcon;

import main.myball;
 
abstract public class block {
	int x;
	int y;
	Image ic = new ImageIcon("./img/box.jpg").getImage();
	int height = ic.getHeight(null);
	int width = ic.getWidth(null);
	myball ball = myball.getInstance();
	public boolean Downstop() {
		boolean flag = false;
		return flag;
	}
	public boolean Leftstop() {
		boolean flag = false;
		if(ball.getBall_x() < (getX() + getWidth() + 2)
				&& ball.getBall_x() > (getX() + getWidth() - 2)
				&& ball.getBall_y() > getY()-3) {
			flag=true;
		}
		return flag;
	}
	public boolean Rightstop() {
		boolean flag = false;
		if((  ball.getBall_x()+8) < (getX() + getWidth() + 2)
				&& ball.getBall_x()+8 > (getX() + getWidth() - 2)
				&& ball.getBall_y() > getY()-3) {
			flag=true;
		}
		return flag;
	}
	public boolean Upstop() {
		boolean flag = false;
		if(ball.getBall_y()<(getY()+getHeight()+2) && ball.getBall_y()>(getY()+getHeight()-2)) {
			if((ball.getBall_x()+8)>getX() && ball.getBall_x()<(getX()+getWidth())) {
				flag = true;
			}
		}
		return flag;
	}
	public int getX(){
		return x;
	}


	public int getY() {
		return y;
	}

	public int getWidth(){
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	public int getHeight() {
		return height;
	}
	public void setWidth(int width){
		this.width = width;
	}

	public Image getIc(){
		return ic;
	}

	public void setIc(String ic){
		this.ic = new ImageIcon(ic).getImage();
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
}