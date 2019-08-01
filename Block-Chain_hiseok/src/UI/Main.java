package UI;

import java.awt.EventQueue;

import C.CMain;


public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new ServerCenter();
		//new CStart();
		new Runnable() {
			public void run() {
				
				new Main();
			}
		};
		new Runnable() {
			public void run() {
				new CMain();
				
			}
		};
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					FirstBit frame = new FirstBit();
					frame.setVisible(true);
				} catch (Exception e) { 
					e.printStackTrace();
				}
			}
		});
	}

}
