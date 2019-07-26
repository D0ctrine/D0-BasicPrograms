package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;


public class dao {
	private static dao daoInstance;
	Scanner in = new Scanner(System.in);
	private dao() {
	}
	
	public static dao getInstance() { 
		if(daoInstance == null){
			daoInstance = new dao();
		}
		return daoInstance;
	}
	
	public Connection getConnection(){
		Connection conn = null;
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		 conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","1111");
		}catch(Exception e) {
			e.getStackTrace();
			System.out.println("Connection Faile /"+e);
		}
		return conn;
	}
	
	public void insert() {
		
		Connection conn = getConnection();
		PreparedStatement ppst = null;
	

		System.out.println("���̵� �Է����ּ���.");
		String id = in.nextLine();
		int checknum=0;
		
			
			
		try {
			
		ppst = conn.prepareStatement("insert into customlist values(?,?,?,?)");
//		ppst.setString(1, membDto.getId());
//		ppst.setString(2, membDto.getName());
//		ppst.setString(3, membDto.getAddr());
//		ppst.setInt(4, membDto.getPoint());
		
		ppst.executeUpdate();
		System.out.println("�Է�ó���� �Ϸ�Ǿ����ϴ�.");
		}catch(Exception e) {
			System.out.println("SQL Error");
		}finally {
			try {
				if(ppst != null) ppst.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				System.out.println("connection close error");
			}//end catch
		}//end finally
	}//end insert()
	
	public void update() {
		Connection conne = getConnection();
		PreparedStatement ppst1 = null;

		
		try {
		
		ppst1 = conne.prepareStatement("update customlist set name=?,point=?,addr=? where id=?");
//		ppst1.setString(1, mod.getName());
//		ppst1.setInt(2, mod.getPoint());
//		ppst1.setString(3, mod.getAddr());
//		ppst1.setString(4, mod.getId());

		int k = ppst1.executeUpdate();
		}catch(Exception e) {
			System.out.println("SQL Error");
		}finally {
			try {
				if(ppst1 != null) ppst1.close();
				if(conne != null) conne.close();
			}catch(Exception e) {
				System.out.println("connection close error");
			}//end catch
		}//end finally
	}
	
	public void delete() {
		Connection conne = getConnection();
		PreparedStatement ppst1 = null;
		
		try {
		
		ppst1 = conne.prepareStatement("delete from customlist where id=?");
//		ppst1.setString(1,id);
		int a = ppst1.executeUpdate();
		}catch(Exception e) {
			System.out.println("SQL Error");
		}finally {
			try {
				if(ppst1 != null) ppst1.close();
				if(conne != null) conne.close();
			}catch(Exception e) {
				System.out.println("connection close error");
			}//end catch
		}//end finally
	}
	
	public void getList() {
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		ResultSet rs = null;
		try {
			ppst = conn.prepareStatement("select * from customlist");
			
			rs= ppst.executeQuery();
			int i=0;
			if(rs.next()) {
				
				do {
//					dto.setId(rs.getString("id"));
//					dto.setName(rs.getString("name"));
//					dto.setAddr(rs.getString("addr"));
//					dto.setPoint(rs.getInt("point"));
//					memberDTOList.add(dto);
//					System.out.println("���̵� : "+memberDTOList.get(i).getId());
//					System.out.println("�̸� : "+memberDTOList.get(i).getName());
//					System.out.println("�ּ� :"+memberDTOList.get(i).getAddr());
//					System.out.println("�ܿ� ����Ʈ :"+memberDTOList.get(i).getPoint());
					
					i++;
					
				}while(rs.next());
					
				}//end if
				}//end try
				catch(Exception e) {
					System.out.println("SQL Error");
				}finally {
					try {
						if(ppst != null) ppst.close();
						if(conn != null) conn.close();
						if(rs != null) conn.close();
					}catch(Exception e) {
						System.out.println("connection close error");
					}//end catch
				}//end finally
		
	}
}
