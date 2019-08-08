package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.dao;

public class Sign extends JFrame implements ActionListener {
	private JPanel contentPane;
	TextField textField = new TextField();
	TextField textField_1 = new TextField();
	TextField textField_2 = new TextField();
	Random r = new Random();
	JLabel label_3 = new JLabel("나의 계좌번호 : ");
	public Sign() {
		this.setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 425);
		contentPane = new JPanel();
		contentPane.setForeground(Color.GRAY);
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_2 = new JLabel("원");
		label_2.setFont(new Font("Dialog", Font.BOLD, 23));
		label_2.setBounds(162, 262, 24, 33);
		contentPane.add(label_2);
		
		JLabel label_1 = new JLabel("현재 보유 금액");
		label_1.setFont(new Font("Dialog", Font.BOLD, 23));
		label_1.setBounds(43, 198, 163, 33);
		contentPane.add(label_1);
		JMenuItem menuItem3 = new JMenuItem("뒤로가기");
		menuItem3.setBackground(new Color(255, 153, 0));
		menuItem3.setBounds(384, 110, 106, 22);
		contentPane.add(menuItem3);
		menuItem3.addActionListener(this);
		
		JButton button = new JButton("회원가입");
		button.setBounds(402, 146, 88, 33);
		contentPane.add(button);
		button.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 255, 255));
		panel.setBounds(5, 5, 542, 1);
		contentPane.add(panel);
		panel.setLayout(null);

		Label label = new Label("D2BC");
		label.setBackground(new Color(0, 128, 128));
		label.setForeground(Color.RED);
		label.setBounds(513, 364, 39, 23);
		contentPane.add(label);
		ImageIcon bitcoin = new ImageIcon("C:/Users/HU-203-07/Desktop/비트코인.png");

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(74, 80, 39, 43);
		contentPane.add(lblId);
		lblId.setFont(new Font("Dialog", Font.BOLD, 30));
		
		textField_1.setForeground(SystemColor.desktop);
		textField_1.setBounds(54, 125, 163, 23);
		contentPane.add(textField_1);
		
		JLabel lblPassword = new JLabel("PW");
		lblPassword.setBounds(299, 325, 88, 33);
		contentPane.add(lblPassword);
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 30));
		
		textField.setForeground(SystemColor.desktop);
		textField.setBounds(286, 364, 142, 23);
		contentPane.add(textField);
		textField.setEchoChar('*');

		JLabel lblNewLabel_1 = new JLabel("[ 오류 창 알림문 ]");
		lblNewLabel_1.setBounds(382, 198, 133, 23);
		contentPane.add(lblNewLabel_1);
		
		textField_2.setForeground(Color.BLACK);
		textField_2.setBounds(56, 262, 100, 23);
		contentPane.add(textField_2);
		
		label_3.setFont(new Font("Dialog", Font.BOLD, 23));
		label_3.setBounds(107, 24, 372, 33);
		contentPane.add(label_3);
		JLabel lblNewLabel = new JLabel(bitcoin);
		lblNewLabel.setBounds(0, 0, 562, 397);
		
		contentPane.add(lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("뒤로가기")) {
			this.setVisible(false);
			new FirstBit();
		}else if(e.getActionCommand().equals("회원가입")) {
			String account = "";
			for(int i=0 ;i<14;i++) {
				int accountnum = r.nextInt(9)+1;
				account += accountnum+"";
			}
			if(!dao.getInstance().checkAC(account))label_3.setText("나의 계좌번호 : "+account);
			System.out.println("회원가입 시작~! ");
			System.out.println(textField_1.getText()+"/"+textField.getText()+"/"+textField_2.getText());
			dao.getInstance().insert(textField_1.getText(), textField.getText(),textField_2.getText(),account);
			JOptionPane.showMessageDialog(null, "회원가입 완료!", "InfoMessage", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("회원가입 끝~! ");
		}
		
	}
}
