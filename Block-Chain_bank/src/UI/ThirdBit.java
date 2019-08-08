package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import Blockchain.Nooby;
import C.CMain;
import C2.CMain1;
import C3.CMain2;
import DAO.dao;
import DAO.dto;
import S.SMain;
import S3.SMain2;

public class ThirdBit extends JFrame implements ActionListener {
	private JPanel contentPane2;
	private JPanel contentPane3;
	private JPanel contentPane4;
	JTextArea textArea = new JTextArea();
	JTextArea textArea_1 = new JTextArea();
	String id1="";
	int countMoney=0;
	ThirdBit(String id) {
		this.id1 = id;
	}
	JLabel label_2 = new JLabel("2. 계좌 금액 : [ "+countMoney+"원 ]");
	public void ThirdBit_1() {
		setResizable(false);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 425);
		contentPane2 = new JPanel();
		contentPane2.setForeground(Color.GRAY);
		contentPane2.setBackground(new Color(0, 128, 128));
		contentPane2.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane2);
		contentPane2.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 255, 255));
		panel.setBounds(5, 5, 542, 1);
		contentPane2.add(panel);
		panel.setLayout(null);

		JTextPane textPane = new JTextPane();
		textPane.setBounds(342, 128, -121, -65);
		contentPane2.add(textPane);

		Component verticalGlue = Box.createVerticalGlue();
		verticalGlue.setBounds(225, 154, 6, -121);
		contentPane2.add(verticalGlue);

		Label label = new Label("D2BC");
		label.setBackground(new Color(0, 128, 128));
		label.setForeground(Color.RED);
		label.setBounds(513, 364, 39, 23);
		contentPane2.add(label);

		JLabel lblNewLabel = new JLabel("< ID_"+id1+"님의 계좌 정보 >");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
		lblNewLabel.setBounds(143, 56, 203, 52);
		contentPane2.add(lblNewLabel);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(102, 204, 102));
		panel_1.setBounds(108, 125, 343, 214);
		contentPane2.add(panel_1);
		panel_1.setLayout(null);

		JLabel label_1 = new JLabel("1. 계좌 번호 : [ "+dao.getInstance().getAC(id1)+" ]");
		label_1.setFont(new Font("굴림", Font.BOLD, 15));
		label_1.setBounds(35, 23, 270, 42);
		panel_1.add(label_1);

		
		label_2.setFont(new Font("굴림", Font.BOLD, 15));
		label_2.setBounds(35, 86, 296, 42);
		panel_1.add(label_2);

		JButton btnNewButton = new JButton("3. 계좌 내역 조회");
		btnNewButton.addActionListener(this);

		btnNewButton.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton.setBounds(35, 169, 272, 35);
		panel_1.add(btnNewButton);
		JMenuItem menuItem = new JMenuItem("뒤로가기");
		menuItem.setBackground(new Color(255, 153, 0));
		menuItem.setBounds(0, 365, 103, 22);
		contentPane2.add(menuItem);
		menuItem.addActionListener(this);
		check();

	}
	dao d = dao.getInstance();
	private void check() {
		dto DTO = null;
		System.out.println("check start~!!");
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				ArrayList<dto> dtobox = null;
				label_2.setText("2. 계좌 금액 : [ "+d.getMoney(id1)+"원 ]");	
				dtobox = d.checksendflag(id1, d.getAC(id1));
					for(int i=0;i<dtobox.size();i++) {
							d.setMoney(dtobox.get(i).getSender(), (d.getMoney(dtobox.get(i).getSender())-dtobox.get(i).getMoney())+"");
							d.setMoneyAC(dtobox.get(i).getReceiver(), (d.getMoneyAC(dtobox.get(i).getReceiver())+dtobox.get(i).getMoney())+"");
							d.ckeckflag();
					}
			}

			};
			ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
			service.scheduleAtFixedRate(runnable, 0, 5, TimeUnit.SECONDS);
	}
	public void ThirdBit_2() {
		setResizable(false);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 425);
		contentPane3 = new JPanel();
		contentPane3.setForeground(Color.GRAY);
		contentPane3.setBackground(new Color(0, 128, 128));
		contentPane3.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane3);
		contentPane3.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 255, 255));
		panel.setBounds(5, 5, 542, 1);
		contentPane3.add(panel);
		panel.setLayout(null);

		JTextPane textPane = new JTextPane();
		textPane.setBounds(342, 128, -121, -65);
		contentPane3.add(textPane);

		Component verticalGlue = Box.createVerticalGlue();
		verticalGlue.setBounds(225, 154, 6, -121);
		contentPane3.add(verticalGlue);

		Label label = new Label("D2BC");
		label.setBackground(new Color(0, 128, 128));
		label.setForeground(Color.RED);
		label.setBounds(513, 364, 39, 23);
		contentPane3.add(label);

		JLabel lblNewLabel = new JLabel("< ID_"+id1+" 님 출금 >");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
		lblNewLabel.setBounds(143, 56, 203, 52);
		contentPane3.add(lblNewLabel);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(102, 204, 102));
		panel_1.setBounds(108, 125, 343, 214);
		contentPane3.add(panel_1);
		panel_1.setLayout(null);

		JLabel label_1 = new JLabel("[ 출금 계좌 ]");
		label_1.setFont(new Font("굴림", Font.BOLD, 15));
		label_1.setBounds(35, 23, 112, 42);
		panel_1.add(label_1);

		JLabel label_2 = new JLabel("[ 금액 ]");
		label_2.setFont(new Font("굴림", Font.BOLD, 15));
		label_2.setBounds(35, 86, 120, 42);
		panel_1.add(label_2);

		JButton btnNewButton = new JButton("보내기");
		btnNewButton.addActionListener(this);

		btnNewButton.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton.setBounds(35, 169, 272, 35);
		panel_1.add(btnNewButton);

		JLabel label_3 = new JLabel("로");
		label_3.setFont(new Font("굴림", Font.BOLD, 15));
		label_3.setBounds(292, 52, 39, 42);
		panel_1.add(label_3);

		JLabel label_4 = new JLabel("원 보내기");
		label_4.setFont(new Font("굴림", Font.BOLD, 15));
		label_4.setBounds(265, 117, 78, 42);
		panel_1.add(label_4);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(35, 127, 214, 24);
		panel_1.add(textArea);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(35, 62, 214, 24);
		panel_1.add(textArea_1);
		JMenuItem menuItem = new JMenuItem("뒤로가기");
		menuItem.setBackground(new Color(255, 153, 0));
		menuItem.setBounds(0, 365, 103, 22);
		contentPane3.add(menuItem);
		menuItem.addActionListener(this);

	}

	public void ThirdBit_3() {
		setResizable(false);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 425);
		contentPane4 = new JPanel();
		contentPane4.setForeground(Color.GRAY);
		contentPane4.setBackground(new Color(0, 128, 128));
		contentPane4.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane4);
		contentPane4.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 255, 255));
		panel.setBounds(5, 5, 542, 1);
		contentPane4.add(panel);
		panel.setLayout(null);

		JTextPane textPane = new JTextPane();
		textPane.setBounds(342, 128, -121, -65);
		contentPane4.add(textPane);

		Component verticalGlue = Box.createVerticalGlue();
		verticalGlue.setBounds(225, 154, 6, -121);
		contentPane4.add(verticalGlue);

		Label label = new Label("D2BC");
		label.setBackground(new Color(0, 128, 128));
		label.setForeground(Color.RED);
		label.setBounds(513, 364, 39, 23);
		contentPane4.add(label);

		JLabel lblNewLabel = new JLabel("< ID_"+id1+" 님 입금 / 송금 >");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
		lblNewLabel.setBounds(143, 56, 203, 52);
		contentPane4.add(lblNewLabel);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(102, 204, 102));
		panel_1.setBounds(108, 125, 343, 214);
		contentPane4.add(panel_1);
		panel_1.setLayout(null);

		JLabel label_1 = new JLabel("[ 받으실 분 ]");
		label_1.setFont(new Font("굴림", Font.BOLD, 15));
		label_1.setBounds(35, 23, 112, 42);
		panel_1.add(label_1);

		JLabel label_2 = new JLabel("[ 보내실 금액 ]");
		label_2.setFont(new Font("굴림", Font.BOLD, 15));
		label_2.setBounds(35, 86, 120, 42);
		panel_1.add(label_2);

		JButton btnNewButton = new JButton("보내기");
		btnNewButton.addActionListener(this);

		btnNewButton.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton.setBounds(35, 169, 272, 35);
		panel_1.add(btnNewButton);

		JLabel label_3 = new JLabel("님께");
		label_3.setFont(new Font("굴림", Font.BOLD, 15));
		label_3.setBounds(292, 52, 39, 42);
		panel_1.add(label_3);

		JLabel label_4 = new JLabel("원 보내기");
		label_4.setFont(new Font("굴림", Font.BOLD, 15));
		label_4.setBounds(265, 117, 78, 42);
		panel_1.add(label_4);
		
		textArea.setBounds(35, 127, 214, 24);
		panel_1.add(textArea);
		
		textArea_1.setBounds(35, 62, 214, 24);
		panel_1.add(textArea_1);
		JMenuItem menuItem = new JMenuItem("뒤로가기");
		menuItem.setBackground(new Color(255, 153, 0));
		menuItem.setBounds(0, 365, 103, 22);
		contentPane4.add(menuItem);
		menuItem.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("3. 계좌 내역 조회")) {
			this.setVisible(false);
			new select(id1);
		} else if (e.getActionCommand().equals("뒤로가기")) {
			this.setVisible(false);
			SecondBit frame = new SecondBit(id1);
		} else if (e.getActionCommand().equals("보내기")) {
			int moneycheck = dao.getInstance().getMoney(id1);
			int opcion = JOptionPane.showConfirmDialog(null, "[ID : "+dao.getInstance().getID(textArea_1.getText())+" ]"+"보내시는 분이 맞습니까?", "Search", JOptionPane.YES_NO_OPTION);
			
			if (opcion == 0) { //The ISSUE is here
			
			if(moneycheck>=Integer.parseInt(textArea.getText())) {
				
//				new Thread(new Runnable() {
//					public void run() {
//						new CMain();
//					}
//				}).start();
//				new Thread(new Runnable() {
//					public void run() {
//						new CMain2();
//					}
//				}).start();
//				new Thread(new Runnable() {
//					public void run() {
//						new CMain1();
//					}
//				}).start();
				dao.getInstance().send(id1, textArea_1.getText(), textArea.getText());
				System.out.println(id1+"/"+textArea.getText()+"/"+textArea_1.getText());
			}else {
				JOptionPane.showMessageDialog(null, "개인이 소유한 금액을 초과하셨습니다!", "Alert", JOptionPane.INFORMATION_MESSAGE);
			}
			
			}
			textArea.setText("");
			textArea_1.setText("");
			//this.setVisible(false);
		} 
	}

}
