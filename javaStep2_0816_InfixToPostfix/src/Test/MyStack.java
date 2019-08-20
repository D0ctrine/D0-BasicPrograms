package Test;

public class MyStack {

	private String[] data = new String[10];
	private int pointer = -1; // 항상 마지막의 데이터를 가르키고 있는 변수
	
	public void push(String d) {
		if(pointer==data.length) {
			System.out.println("Stack Full");
		}else {
		pointer++;
		data[pointer] = d;
		}
		
	}
	public String pop() {
		if(pointer == -1) {
			System.out.println("Stack empty");
			return null;
		}else {
		String imsiS = data[pointer];
		data[pointer] = null;
		pointer--;
		return imsiS;
		}
	}
	
	public int pointerIndex() {
//		System.out.println("현재포인터 위치 : "+pointer);
		return pointer;
	}
	public String getData() {
		
		return data[pointer];
	}
	
}
