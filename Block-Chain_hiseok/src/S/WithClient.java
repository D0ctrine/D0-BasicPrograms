package S;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class WithClient extends Thread { // 접속 한 클라이언트랑 통신하기 위한 클래스
	int i=0;
	InputStream reMsg = null;
	OutputStream sendMsg = null;
	Scanner in = new Scanner(System.in);
	Socket myClient = null;
	
 	@Override
	public void run() {

		//sendData();
		receiveData();
	}

	public WithClient(Socket s) {
		this.myClient = s;
		try {
			reMsg = myClient.getInputStream();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void receiveData() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						byte[] reBuf = new byte[256];
						reMsg.read(reBuf);
						String msg = new String(reBuf).trim();
						if(msg.indexOf("Error From (")!=-1) {
							System.out.println("Error로 시작한다는디");//클라이언트 2에게 확인하기
						}
						System.out.println(myClient.getInetAddress() + " / " + msg);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
	
//	private void sendData() {
//		new Thread(new Runnable() {
//		@Override
//		public void run() {
//			try {
//				while(true) {
//					String msg= in.nextLine();
//					
//					myClient.getOutputStream().write(msg.getBytes());
//				}
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		}).start();
//	}
	public void sendData(String msg) {
			try {
				myClient.getOutputStream().write(msg.getBytes());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	public void sendData(String msg,Socket s) {
		try {
			s.getOutputStream().write(msg.getBytes());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
