package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import C.CMain;

public class SecondBit extends JFrame implements ActionListener{
	private JPanel contentPane1;
	
	ThirdBit tb=null;
	public SecondBit(String id) {
		
		tb = new ThirdBit(id);
		setResizable(false);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 425);
		contentPane1 = new JPanel();
		contentPane1.setForeground(Color.GRAY);
		contentPane1.setBackground(new Color(0, 128, 128));
		contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane1);
		contentPane1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 255, 255));
		panel.setBounds(5, 5, 542, 1);
		contentPane1.add(panel);
		panel.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(342, 128, -121, -65);
		contentPane1.add(textPane);
		
		Component verticalGlue = Box.createVerticalGlue();
		verticalGlue.setBounds(225, 154, 6, -121);
		contentPane1.add(verticalGlue);
		
		Label label = new Label("D2BC");
		label.setBackground(new Color(0, 128, 128));
		label.setForeground(Color.RED);
		label.setBounds(513, 364, 39, 23);
		contentPane1.add(label);
		
		JLabel lblLoginSuccess = new JLabel("Welcome~!!");
		lblLoginSuccess.setFont(new Font("굴림", Font.BOLD, 18));
		lblLoginSuccess.setForeground(new Color(255, 0, 0));
		lblLoginSuccess.setBounds(320, 33, 176, 23);
		contentPane1.add(lblLoginSuccess);
		
		JLabel lblNewLabel = new JLabel("ID : [ "+id+" ]");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
		lblNewLabel.setBounds(205, 29, 123, 34);
		contentPane1.add(lblNewLabel);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(102, 204, 102));
		panel_1.setBounds(108, 125, 343, 214);
		contentPane1.add(panel_1);
		panel_1.setLayout(null);
		
		JMenuItem menuItem = new JMenuItem("[ 1. 계좌 조회 ]");
		menuItem.setBounds(43, 31, 180, 22);
		panel_1.add(menuItem);
		menuItem.addActionListener(this);
		
		JMenuItem menuItem_1 = new JMenuItem("[ 2. 계좌 출금/송금 ]   ( 타은행 )");
		menuItem_1.setBounds(43, 90, 180, 22);
		panel_1.add(menuItem_1);
		menuItem_1.addActionListener(this);
		
		JMenuItem menuItem_2 = new JMenuItem("[ 3. 계좌 입금/송금 ]");
		menuItem_2.setBounds(43, 145, 180, 22);
		panel_1.add(menuItem_2);
		menuItem_2.addActionListener(this);
		
		JMenuItem menuItem3 = new JMenuItem("로그아웃");
		menuItem3.setBackground(new Color(255, 153, 0));
		menuItem3.setBounds(0, 365, 103, 22);
		contentPane1.add(menuItem3);
		menuItem3.addActionListener(this);
			
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("[ 1. 계좌 조회 ]")) {
			this.setVisible(false);
			contentPane1.setVisible(false);
			tb.ThirdBit_1();
		}else if(e.getActionCommand().equals("[ 2. 계좌 출금/송금 ]   ( 타은행 )")) {
			this.setVisible(false);
			contentPane1.setVisible(false);
			tb.ThirdBit_2();
		}else if(e.getActionCommand().equals("[ 3. 계좌 입금/송금 ]")) {
			this.setVisible(false);
			contentPane1.setVisible(false);
			tb.ThirdBit_3();
		}else if(e.getActionCommand().equals("로그아웃")) {
			this.setVisible(false);
			contentPane1.setVisible(false);
			FirstBit frame = new FirstBit();
		}

	}

	
}
