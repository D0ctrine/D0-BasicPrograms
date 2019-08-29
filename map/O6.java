package map;

public class O6 extends block{

	public O6(int x, int y) {
		setX(x);
		setY(y);
		setIc("./img/º®2.png");
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
