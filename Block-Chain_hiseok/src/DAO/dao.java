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
	
	public void insert(String id,String pw,String curmoney) {
		
		Connection conn = getConnection();
		PreparedStatement ppst = null;
	

		try {
			
		ppst = conn.prepareStatement("insert into member values(?,?,?)");
		ppst.setString(1, id);
		ppst.setString(2, pw);
		ppst.setInt(3, Integer.parseInt(curmoney));
		System.out.println("curmoney : "+curmoney);
		ppst.executeUpdate();
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
		System.out.println("Sign-in Successed");
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
		
		ppst1 = conne.prepareStatement("delete from member where id=?");
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
	
	public int checkID(String id,String pw) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		PreparedStatement ppst1 = null;
		int checkId;
		int checkPw;
		int count=0;
		try {
			ppst = conn.prepareStatement("select id from member where id=?");
			ppst.setString(1,id);
			ppst1 = conn.prepareStatement("select pw from member where pw=?");
			ppst1.setString(1,pw);
			checkId= ppst.executeUpdate();
			if(checkId==1)count=count+2;
			checkPw= ppst1.executeUpdate();
			if(checkPw==1)count++;
				}//end try
				catch(Exception e) {
					System.out.println("SQL Error");
				}finally {
					try {
						if(ppst != null) ppst.close();
						if(conn != null) conn.close();
					}catch(Exception e) {
						System.out.println("connection close error");
					}//end catch
				}//end finally
		System.out.println(count);
		return count;
	}
	public int getMoney(String id) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		ResultSet rs = null;
	
		int count=0;
		try {
			ppst = conn.prepareStatement("select curmoney from member where id=?");
			ppst.setString(1,id);
 			rs = ppst.executeQuery();
			if(rs.next()) {
				
				count = rs.getInt("CURMONEY");
			}
			
				}//end try
				catch(Exception e) {
					System.out.println("SQL Error");
				}finally {
					try {
						if(ppst != null) ppst.close();
						if(conn != null) conn.close();
					}catch(Exception e) {
						System.out.println("connection close error");
					}//end catch
				}//end finally
		System.out.println(count);
		return count;
	}
	
}
