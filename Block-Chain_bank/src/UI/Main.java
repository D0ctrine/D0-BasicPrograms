package UI;

import java.awt.EventQueue;

import C.CMain;
import C2.CMain1;
import C3.CMain2;
import S.SMain;
import S2.SMain1;
import S3.SMain2;


public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		new Thread(new Runnable() {
//			public void run() {
//				System.out.println("Server 시작");
//				new SMain();
//			}
//		}).start();
		
		try {
			FirstBit frame = new FirstBit();
			frame.setVisible(true);
		} catch (Exception e) { 
			e.printStackTrace();
		}
	
//		Thread t3 = new Thread(new Runnable() {
//			public void run() {
//				new SMain1();
//			}
//		});
//		Thread t2 = new Thread(new Runnable() {
//			public void run() {
//				new SMain2();
//			}
//		});
		
//		t1.start();
//		t2.start();
//		t3.start();
	}

	

}