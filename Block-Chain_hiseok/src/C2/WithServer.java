package C2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import Blockchain.Nooby;
import DAO.dao;
import DAO.dto;

public class WithServer {
	String id = null;
	InputStream reMsg = null;
	OutputStream sendMsg = null;
	Socket myClient = null;
	Scanner in = new Scanner(System.in);
	FileInputStream fi = null; // Byte방식으로 jvm에서 외부로 input하는 객체변수
	FileOutputStream fo = null;
	String targetFile = "hash.txt";
	ArrayList<String> idbox = new ArrayList<String>();
	int i=0;
	ArrayList<dto> dtobox = null;
	dao d = dao.getInstance();
	ArrayList<String> hashbox = new ArrayList<String>();

	public WithServer(Socket s) {
		this.myClient = s;
		receiveData();
		if(read(0)==null) {
			System.out.println("초기값 설정");
			setF();
		}
		setHash();
		sendData();
	}
	
	private void setHash() {
	
		hashbox = new Nooby().blockmain(hashbox);
		System.out.println("hashbox size : "+hashbox.size());
		for(int i=0;i<hashbox.size();i++) {
			//insert(hashbox.get(i)+"\r\n");
			makeF(hashbox.get(i));
		}
	}
	
	
	private void receiveData() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					reMsg = myClient.getInputStream();
					while (true) {
						byte[] reBuf = new byte[256];
						reMsg.read(reBuf);
						String msg = new String(reBuf).trim();    
						System.out.println(id + " / " + msg);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
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
					while(true) {
						String re = in.nextLine();
						sendMsg = myClient.getOutputStream();
						sendMsg.write(re.getBytes());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	String read(int i) {

		int k = 0;
		String imsi1 = "";
		byte[] readM = new byte[16];

		try {

			fi = new FileInputStream(targetFile);

			if (fi != null) {
				while ((k = fi.read(readM)) != -1) {
					imsi1 += new String(readM, 0, k);
				}
				if (i == 1) {
					System.out.println(imsi1);
				}
			} 

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (fi != null)
					fi.close();
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return imsi1;
	}
	
	public void setF() {
		if (fo == null) {

			// 파일 저장
			try {
				fo = new FileOutputStream(targetFile);

			} catch (Exception e) {

			} finally {
				try {
					if (fo != null)
						fo.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void makeF(String filename) {
		if (fo == null) {

			// 파일 저장
			try {
				fo = new FileOutputStream(filename);

			} catch (Exception e) {

			} finally {
				try {
					if (fo != null)
						fo.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	void insert(String msg,String target) {

		try {
			
			fo = new FileOutputStream(target);

			fo.write(msg.getBytes());
			fo.flush();

		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				if (fo != null)
					fo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
