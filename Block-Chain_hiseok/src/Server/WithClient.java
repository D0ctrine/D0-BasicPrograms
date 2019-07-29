package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class WithClient extends Thread { 

	String id = null;

	InputStream reMsg = null;
	OutputStream sendMsg = null;

	Scanner in = new Scanner(System.in);
	Socket myClient = null;

	@Override
	public void run() {
		streamSet();
		sendData();
		receiveData();
	}

	public WithClient(Socket s) {

		this.myClient = s;

	}

	private void receiveData() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (true) {
						byte[] reBuf = new byte[100];
						reMsg.read(reBuf);
						String msg = new String(reBuf).trim();
						System.out.println(id + "/" + msg);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}).start();

	}

	private void sendData() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						String re = in.nextLine();
						sendMsg.write(re.getBytes());

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}).start();
	}

	private void streamSet() {
		try {
			reMsg = myClient.getInputStream();
			
			byte[] reBuf = new byte[100];
			reMsg.read();
			id= new String(reBuf);
			id= id.trim();
			
			System.out.println(id);
			
			String re = id + "! welcome";
			sendMsg = myClient.getOutputStream();
			sendMsg.write(reBuf);
			
			
		}catch (IOException e){
			e.printStackTrace();
		}

	}
}
