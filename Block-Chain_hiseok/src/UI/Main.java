package UI;

import java.awt.EventQueue;

import C.CMain;
import S.SMain;


public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			
		
		Thread t3 = new Thread(new Runnable() {
			public void run() {
				try {
					FirstBit frame = new FirstBit();
					frame.setVisible(true);
				} catch (Exception e) { 
					e.printStackTrace();
				}
			}
		});
		
		
//		t1.start();
//		t2.start();
		t3.start();
	}

	

}