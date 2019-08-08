package S3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class WithClient extends Thread { // 접속 한 클라이언트랑 통신하기 위한 클래스
	int i=0;
	InputStream reMsg = null;
	OutputStream sendMsg = null;
	Scanner in = new Scanner(System.in);
	Socket myClient = null;
	ArrayList<String> ipbox = new ArrayList<String>();
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
							i++;
//							if(i==1) {
//								timer();
//							}
//							for(int g=0;g<ipbox.size();g++) {
//								if(!ipbox.get(g).equals(myClient.getPort()+"")) {
//									ipbox.add("포트:"+myClient.getPort()+" / IP:"+myClient.getInetAddress());
//									if(ipbox.size()==3) {
//										System.out.println("DB서버망이 위조되었습니다. 개인장부를 업로드해주세요~!");
//									}
//								}
//							}
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
	public void timer() {
		new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					Thread.sleep(30000);
					if(ipbox.size()==1) {
						System.out.println(ipbox.get(0)+"의 개인장부가 위조되었습니다.");
					}
					ipbox = new ArrayList<String>();
					i=0;
				} catch (InterruptedException e) {
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
