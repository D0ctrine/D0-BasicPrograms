package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.dao;
import DAO.dto;
import javax.swing.JMenuItem;

public class select extends JFrame implements ActionListener {
	private JPanel contentPane;
String id1="";
List list = new List();
List list_1 = new List();
List list_2 = new List();
List list_3 = new List();

	public select(String id) {
		id1 = id;
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
		
		list_2.setBackground(new Color(255, 255, 153));
		list_2.setBounds(267, 111, 111, 276);
		contentPane.add(list_2);
		
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
		
		JLabel lblNewLabel = new JLabel("나의 거래 내역");
		lblNewLabel.setForeground(new Color(255, 153, 0));
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 25));
		lblNewLabel.setBounds(170, 33, 229, 43);
		contentPane.add(lblNewLabel);
		list.setBackground(new Color(255, 255, 153));
		
		list.setBounds(10, 111, 111, 276);
		contentPane.add(list);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(57, 98, 57, 15);
		contentPane.add(label_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setToolTipText("");
		panel_1.setBackground(new Color(255, 204, 51));
		panel_1.setBounds(12, 78, 535, 35);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_2 = new JLabel("송신자         수신자              금액            날짜");
		label_2.setBackground(new Color(255, 204, 0));
		label_2.setFont(new Font("굴림", Font.BOLD, 14));
		label_2.setBounds(32, 0, 453, 35);
		panel_1.add(label_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("뒤로가기");
		mntmNewMenuItem.setBounds(15, 16, 116, 22);
		contentPane.add(mntmNewMenuItem);
		
		list_1.setBackground(new Color(255, 255, 153));
		list_1.setBounds(120, 111, 147, 276);
		contentPane.add(list_1);
		
		list_3.setBackground(new Color(255, 255, 153));
		list_3.setBounds(373, 111, 174, 276);
		contentPane.add(list_3);
		mntmNewMenuItem.addActionListener(this);
		
		check();
		
	}
	
	dao d = dao.getInstance();
	private void check() {
		dto DTO = null;
		//초기세팅
		ArrayList<dto> dtobox = null;
		dtobox = d.checksend(id1,d.getAC(id1));
		System.out.println("");
		list.removeAll();
		list_1.removeAll();
		list_2.removeAll();
		list_3.removeAll();
		
		for(int i=0;i<dtobox.size();i++) {
			list.add((i+1)+"."+dtobox.get(i).getSender());
			list_1.add((i+1)+"."+d.getID(dtobox.get(i).getReceiver()));
			list_2.add((i+1)+".   "+dtobox.get(i).getMoney());
			list_3.add((i+1)+". "+dtobox.get(i).getDate());
			d.ckeckflag();
		}
		
		System.out.println("check Runnable 시작");
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
			
				ArrayList<dto> dtobox = null;
				dtobox = d.checksendflag(id1,d.getAC(id1));
				
					for(int i=0;i<dtobox.size();i++) {
						System.out.println("바뀐거 리스트에 추가");
						list.add(id1);
						list_1.add(dtobox.get(i).getReceiver()+"("+d.getID(dtobox.get(i).getReceiver())+")");
						list_2.add("   "+dtobox.get(i).getMoney());
						list_3.add(" "+dtobox.get(i).getDate());
					}
					d.ckeckflag();
			}

		};
			ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
			service.scheduleAtFixedRate(runnable, 0, 5, TimeUnit.SECONDS);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("뒤로가기")) {
			this.setVisible(false);
			new ThirdBit(id1).ThirdBit_1();
		}
	}
}
