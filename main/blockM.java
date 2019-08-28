package main;

import java.awt.Image;

import javax.swing.ImageIcon;

public class blockM {
	int x;
	int y;
	Image ic = new ImageIcon("./img/°¡·Î.jpg").getImage();
	int height = ic.getHeight(null);
	int width = ic.getWidth(null);
	
	blockM(int x,int y){
		this.x = x;
		this.y= y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Image getIc() {
		return ic;
	}

	public void setIc(Image ic) {
		this.ic = ic;
	}
}
