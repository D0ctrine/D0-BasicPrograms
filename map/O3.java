package map;

public class O3 extends block{

	public O3(int x, int y) {
		setX(x);
		setY(y);
		setIc("./img/º®5.png");
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