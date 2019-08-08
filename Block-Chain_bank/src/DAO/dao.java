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
	
	public void insert(String id,String pw,String curmoney,String ac) {
		
		Connection conn = getConnection();
		PreparedStatement ppst = null;
	

		try {
			
		ppst = conn.prepareStatement("insert into member values(?,?,?,?)");
		ppst.setString(1, id);
		ppst.setString(2, pw);
		ppst.setInt(3, Integer.parseInt(curmoney));
		ppst.setString(4,ac);
		ppst.executeUpdate();
		}catch(Exception e) {
			System.out.println("SQL Error / "+e);
		}finally {
			try {
				if(ppst != null) ppst.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				System.out.println("connection close error");
			}//end catch
		}//end finally
		System.out.println("Sign-in Successed");
		commit();
	}//end insert()
	
	
public void send(String sender,String receiver,String curmoney) {
		
		Connection conn = getConnection();
		PreparedStatement ppst = null;

		try {
			
		ppst = conn.prepareStatement("insert into send values(?,?,?,systimestamp,default)");
		ppst.setString(1, sender);
		ppst.setString(2, receiver);
		ppst.setString(3,curmoney);
		ppst.executeUpdate();
		}catch(Exception e) {
			System.out.println("SQL Error / "+e);
		}finally {
			try {
				if(ppst != null) ppst.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				System.out.println("connection close error");
			}//end catch
		}//end finally
		System.out.println("Sign-in Successed");
		commit();
	}//end insert()
	
public void commit() {
	Connection conn = getConnection();
	PreparedStatement ppst = null;
	try {
	ppst = conn.prepareStatement("commit");
	ppst.executeUpdate();
	}catch(Exception e) {
		System.out.println("SQL Error / "+e);
	}finally {
		try {
			if(ppst != null) ppst.close();
			if(conn != null) conn.close();
		}catch(Exception e) {
			System.out.println("connection close error");
		}//end catch
	}//end finally
}

public void ckeckflag() {
	Connection conn = getConnection();
	PreparedStatement ppst = null;

	try {
		
	ppst = conn.prepareStatement("update send set flag = 1 where flag=0");
	
	ppst.executeUpdate();
	}catch(Exception e) {
		System.out.println("SQL Error / "+e);
	}finally {
		try {
			if(ppst != null) ppst.close();
			if(conn != null) conn.close();
		}catch(Exception e) {
			System.out.println("connection close error");
		}//end catch
	}//end finally
	commit();
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
	
	public ArrayList<dto> checksend() {
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		ResultSet rs = null;
		ArrayList<dto> dtobox = new ArrayList<dto>();
		try {
			ppst = conn.prepareStatement("select * from send");
			rs = ppst.executeQuery();
			if(rs.next()) {
				
			do {
				dto DTO = new dto();
				DTO.setSender(rs.getString("SENDER"));
				DTO.setReceiver(rs.getString("RECEIVER"));
				DTO.setMoney(rs.getInt("MONEY"));
				DTO.setDate(rs.getString("DATETIME"));
				DTO.setFlag(rs.getInt("FLAG"));
				dtobox.add(DTO);
			}while(rs.next());
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
		return dtobox;
	}
	public ArrayList<dto> checksend(String id,String ac) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		dto DTO = null; 
		ResultSet rs = null;
		ArrayList<dto> dtobox = new ArrayList<dto>();
		try {
			
			ppst = conn.prepareStatement("select * from send where sender =? or receiver =? order by datetime desc");
			ppst.setString(1,id);
			ppst.setString(2,ac);
			rs = ppst.executeQuery();
			if(rs.next()) {
				
			do {
				DTO = new dto();
				DTO.setSender(rs.getString("SENDER"));
				DTO.setReceiver(rs.getString("RECEIVER"));
				DTO.setMoney(rs.getInt("MONEY"));
				DTO.setDate(rs.getString("DATETIME"));
				DTO.setFlag(rs.getInt("FLAG"));
				dtobox.add(DTO);
			}while(rs.next());
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
		
		return dtobox;
	}
	public ArrayList<dto> checksendflag(String id,String ac) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		dto DTO = new dto();
		ResultSet rs = null;
		ArrayList<dto> dtobox = new ArrayList<dto>();
		try {
			
			ppst = conn.prepareStatement("select * from send where sender =? and flag=0 or receiver =? and flag=0 order by datetime desc");
			ppst.setString(1,id);
			ppst.setString(2,ac);
			rs = ppst.executeQuery();
			if(rs.next()) {
				
			do {
				DTO.setSender(rs.getString("SENDER"));
				DTO.setReceiver(rs.getString("RECEIVER"));
				DTO.setMoney(rs.getInt("MONEY"));
				DTO.setDate(rs.getString("DATETIME"));
				DTO.setFlag(rs.getInt("FLAG"));
				dtobox.add(DTO);
			}while(rs.next());
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
		
		return dtobox;
	}
	
	public int checkID(String id,String pw) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		PreparedStatement ppst1 = null;
		PreparedStatement ppst2 = null;
		int checkId;
		int checkPw;
		int count=0;
		try {
			ppst = conn.prepareStatement("select id from member where id=?");
			ppst.setString(1,id);
			ppst1 = conn.prepareStatement("select pw from member where id=? and pw=?");
			ppst1.setString(1,id);
			ppst1.setString(2,pw);
			
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
	public boolean checkAC(String AC) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		int checkId;
		boolean flag=false;
		try {
			ppst = conn.prepareStatement("select account_num from member where account_num=?");
			ppst.setString(1,AC);
			checkId= ppst.executeUpdate();
			if(checkId>0) {
				flag = true;
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
		return flag;
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
	public int getMoneyAC(String ac) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		ResultSet rs = null;
	
		int count=0;
		try {
			ppst = conn.prepareStatement("select curmoney from member where account_num=?");
			ppst.setString(1,ac);
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
	public int setMoney(String id,String money) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;
	
		int count=0;
		try {
			ppst = conn.prepareStatement("update member set curmoney=? where id=?");
			ppst.setInt(1,Integer.parseInt(money));
			ppst.setString(2,id);
			ppst.executeUpdate();
			
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
		commit();
		return count;
	}
	public int setMoneyAC(String AC,String money) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;
	
		int count=0;
		try {
			ppst = conn.prepareStatement("update member set curmoney=? where account_num=?");
			ppst.setInt(1,Integer.parseInt(money));
			ppst.setString(2,AC);
			ppst.executeUpdate();
			System.out.println("setMoneyac 실행");
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
		commit();
		return count;
	}
	public String getAC(String id) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		ResultSet rs = null;
	
		String count="";
		try {
			ppst = conn.prepareStatement("select account_num from member where id=?");
			ppst.setString(1,id);
 			rs = ppst.executeQuery();
			if(rs.next()) {
				count = rs.getString("ACCOUNT_NUM");
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
	
	public String getID(String ac) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		ResultSet rs = null;
		String count="";
		try {
			ppst = conn.prepareStatement("select id from member where account_num=?");
			ppst.setString(1,ac);
 			rs = ppst.executeQuery();
			if(rs.next()) {
				count = rs.getString("ID");
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
		return count;
	}
	
}
