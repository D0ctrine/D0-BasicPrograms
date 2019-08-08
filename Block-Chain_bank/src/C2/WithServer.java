package C2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import Blockchain.Nooby;
import DAO.dao;
import DAO.dto;

public class WithServer {
	String id = null;
	boolean flag = true;
	String Error = "";
	InputStream reMsg = null;
	OutputStream sendMsg = null;
	Socket myClient = null;
	Scanner in = new Scanner(System.in);
	FileInputStream fi = null; // Byte방식으로 jvm에서 외부로 input하는 객체변수
	FileOutputStream fo = null;
	ArrayList<String> idbox = new ArrayList<String>();
	int i = 0;
	ArrayList<dto> dtobox = null;
	dao d = dao.getInstance();
	ArrayList<String> hashbox = new ArrayList<String>();

	public WithServer(Socket s) {

		this.myClient = s;
		receiveData();
		hashbox = new Nooby().blockmain(hashbox);
		if (read(hashbox)) {
			setHash(hashbox);
		}
	}

	public WithServer(Socket s, int i) {

		this.myClient = s;
		receiveData();

	}

	private void setHash(ArrayList<String> g) {
		System.out.println("hashbox size : " + g.size());
		for (int i = 0; i < g.size(); i++) {
			if (i == 0) {
				insert(g.get(0).substring(0, g.get(0).indexOf("/")),
						g.get(0).substring(g.get(0).indexOf("/") + 1) + "\r\n" + "[First Block]");
			} else {
				insert(g.get(i).substring(0, g.get(i).indexOf("/")),
						g.get(i).substring(g.get(i).indexOf("/") + 1) + "\r\n" + "이전 해시값 정보 : "
								+ g.get(i - 1).substring(0, g.get(i - 1).indexOf("/")) + "\r\n지금 블록 순서 : " + (i + 1)
								+ "번째");
			}
		}
	}

	private void receiveData() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					reMsg = myClient.getInputStream();
					// while (true) {
					byte[] reBuf = new byte[256];
					reMsg.read(reBuf);
					String msg = new String(reBuf).trim();
					if (msg.indexOf("개인장부가 위조") != -1) {
						hashbox = new Nooby().blockmain(hashbox);
						setHash(hashbox);
					}
					System.out.println(id + " / " + msg);
					// }
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
			// fi = new FileInputStream(targetFile);
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

		byte[] readM = new byte[16];
		String fname = null;

		try {
			for (int i = 0; i < hashbox.size() - 1; i++) {
				fname = hashbox.get(i).substring(0, hashbox.get(i).indexOf("/")) + ".txt";
				fi=null;
				fi = new FileInputStream("C:/Users/Hu-203-07/eclipse-workspace/Block-Chain_hiseok/Block2/" + fname);
				String imsi1 = "";
				if (fi != null) {
					while ((k = fi.read(readM)) != -1) {
						imsi1 += new String(readM, 0, k);
					}

					if (imsi1.indexOf(hashbox.get(i).substring(hashbox.get(i).indexOf("/") + 1)) == -1) { // 있다면
						Error = "[11]Error From (" + (i + 1) + ") Block : [Hash data : 내부 값이 위조되었습니다! ]";
						errorSend();
					}
					this.i = i;
				}
			}

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(fname + "존재하지 않습니다. 위조 의심");
			Error = "[admin]Error From (" + (i + 1) + ") Block : [Hash data : "
					+ hashbox.get(i).substring(0, hashbox.get(i).indexOf("/")) + " ]";
			flag = false;
			e.printStackTrace();
			errorSend();
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

	public void errorSend() {
		sendData(Error);
		flag = false;
		new Thread(new Runnable() {
			public void run() {
				Socket client = null;
				try {
					client = new Socket("10.0.0.103", 8888);
					WithServer ws = new WithServer(client, 0);
					System.out.println("Error 보냅니다");
					ws.sendData(Error);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				Socket client = null;
				try {
					client = new Socket("10.0.0.103", 8886);
					WithServer ws = new WithServer(client, 0);
					ws.sendData(Error);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	public void setF() {
		if (fo == null) {

			// 파일 저장
			try {
				// fo = new FileOutputStream(targetFile);

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



	void insert(String target, String msg) {
		try {
			fo = new FileOutputStream(
					"C:/Users/Hu-203-07/eclipse-workspace/Block-Chain_hiseok/Block2/" + target + ".txt");
			fo.write((msg).getBytes());
			fo.flush();

		} catch (Exception e) {
			e.printStackTrace();
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
