package map;

public class N1 extends block{

	public N1(int x, int y) {
		setX(x);
		setY(y);
//		setIc("./img/��7.png");
		setHeight(getIc().getHeight(null));
		setWidth(getIc().getWidth(null));		
	}

	@Override
	public boolean Downstop() {
		// TODO Auto-generated method stub
		return false;
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