package S2;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerCenter extends Thread {
	ServerSocket serverS = null; // 서버를 구동하기 위한 소켓
	Socket serverClient = null; // 클라이언트와 통신을 하기 위한 소켓
	Scanner in = new Scanner(System.in);
	ArrayList<WithClient> clientSocket = new ArrayList<>();
	ServerCenter() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					int num = 1;
					serverS = new ServerSocket();
					serverS.bind(new InetSocketAddress("10.0.0.103", 8887));
					WithClient w = null;
					while (true) {
						System.out.println("서버 대기 중 ~~~");
						serverClient = serverS.accept();
						System.out.println(num+"번 클라이 언트가 입장 하였습니다.");
						w = new WithClient(serverClient);
						w.setName("admin / Block2 / Port : " + serverClient.getLocalPort());
						clientSocket.add(w);
						if(clientSocket.size()==2) {
							timer();
						}
						num++;
						w.start();
					}
				} catch (Exception e) {
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
							
							if(clientSocket.size()==3) {
								System.out.println("DB서버망이 위조되었습니다. 개인장부를 업로드해주세요~!");
								for(int i=1;i<clientSocket.size();i++) {
									clientSocket.get(i).sendData("DB서버망이 해킹되었습니다. 개인장부를 업로드해주세요~!");
								}
							}else if(clientSocket.size()==2) {
								System.out.println(clientSocket.get(1)+"의 개인장부가 위조되었습니다.");
								clientSocket.get(1).sendData(clientSocket.get(1)+"의 개인장부가 위조되었습니다.");
							}
							
					for(int i=0;clientSocket.size()>1;i++) {
						clientSocket.remove(1);
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}).start();
		
	}

}
