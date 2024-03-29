package C;

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
	ArrayList<String> idbox = new ArrayList<String>();
	int i=0;
	ArrayList<dto> dtobox = null;
	dao d = dao.getInstance();
	ArrayList<String> hashbox = new ArrayList<String>();
	public WithServer(Socket s) {
		
		this.myClient = s;
		
		receiveData();
		hashbox = new Nooby().blockmain(hashbox);
		if(read(hashbox)) {
			setHash();
		}
		
		
	}
private void setHash() {
	System.out.println("해시파일 세팅");
		
		System.out.println("hashbox size : "+hashbox.size());
		for(int i=0;i<hashbox.size();i++) {
			if(i==0) {
				insert(hashbox.get(0),"First Block");
			}else {
				insert(hashbox.get(i),"이전 해시값 정보 : "+hashbox.get(i-1)+"\r\n지금 블록 순서 : "+(i+1)+"번째");
			}
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

	private void sendData(String msg) {
				try {
						sendMsg = myClient.getOutputStream();
						sendMsg.write(msg.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
	}

	String read(int i) {

		int k = 0;
		String imsi1 = "";
		byte[] readM = new byte[16];

		try {

			//fi = new FileInputStream(targetFile);

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
	Boolean read(ArrayList<String> hashbox) {
		int k = 0;
		boolean flag = true;
		byte[] readM = new byte[16];
		String fname=null;

		try {
			for(int i=0;i<hashbox.size();i++) {
				fname=hashbox.get(i)+".txt";
				
			fi = new FileInputStream(fname);

			
			}

		} catch (Exception e) {
		//	e.printStackTrace();
			
			System.out.println(fname+"존재하지 않습니다. 위조 의심");
			sendData("Error From ("+(i+1)+") Block : [Hash data : "+hashbox.get(i)+" ]");
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
		return flag;
	}
	public void setF() {
		if (fo == null) {

			// 파일 저장
			try {
				//fo = new FileOutputStream(targetFile);

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

			// 파일 저장
			try {
				fo = new FileOutputStream("C:/Users/Hu-203-07/eclipse-workspace/Block-Chain_hiseok/Block/"+filename+".txt");

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
	void insert(String target,String msg) {
		try {
			
			fo = new FileOutputStream("C:/Users/Hu-203-07/eclipse-workspace/Block-Chain_hiseok/Block/"+target+".txt");

			fo.write((msg).getBytes());
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
