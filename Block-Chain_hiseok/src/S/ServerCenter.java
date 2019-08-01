package S;

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
					serverS.bind(new InetSocketAddress("10.0.0.103", 8888));
					WithClient w = null;
					while (true) {
						System.out.println("서버 대기 중 ~~~");
						serverClient = serverS.accept();
						System.out.println(num+"번 클라이 언트가 입장 하였습니다.");
						w = new WithClient(serverClient);
						w.setName("client" + num);
						clientSocket.add(w);
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
	

}
