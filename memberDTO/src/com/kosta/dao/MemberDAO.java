package com.kosta.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberDAO {
	
	   // db���� �޼��� 
	 private Connection  getConnection()
	     {
		 
			String className="oracle.jdbc.OracleDriver";
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			String user="hr";
			String pwd="hr";
		    Connection conn=null;
		      try {
		    	    Class.forName(className);
		    	    conn=DriverManager.getConnection(url, user, pwd);
		    	  
		      }catch(SQLException|ClassNotFoundException e)
		      {
		    	  System.out.println(e);
		      }
	           return conn; 	
	            	
	     }
	// insert ��� �޼��� 
	     public int  insert(String  id, String  pwd, String mname, String email)
	     {
	    	  Connection conn= getConnection();
	    	  
	    	  PreparedStatement pstmt=null;
	    	  
	    	  StringBuilder sql=new StringBuilder();
	    	  sql.append("  insert  into  member (memberno, id, pwd, mname, email, mdate) ");
	    	  sql.append("  values (memberseq.nextval, ?,?,?,?, sysdate  )                     ");
	    	  int result=0;
	    	  try {
	    		  pstmt=conn.prepareStatement(sql.toString());
	    		  pstmt.setString(1, id);
	    		  pstmt.setString(2, pwd);
	    		  pstmt.setString(3, mname);
	    		  pstmt.setString(4, email);
	    		  result=pstmt.executeUpdate();
	    		  
	    	  }catch(SQLException e) {
	    		  System.out.println(e);
	    	  }finally {
	    		     close(pstmt, conn);
	    	  }
	    	  return result;
	    	 
	     }
	 
	     private  void  close(PreparedStatement pstmt, Connection conn)  // pstmt, conn close �ϴ� ���
	      {
	    	    if(pstmt!=null) try { pstmt.close();} catch(SQLException e) {}   
	    	    if(conn!=null) try { conn.close();} catch(SQLException e) {}
	      }
	    
	     
	     
	    // update ��� �޼��� 
	     
	     public int update(String pwd, String email, String id) {
	 		
	 		Connection conn=getConnection();
	 		PreparedStatement pstmt=null;
	 			
	 		int result=0;				
	 		try {
	 			StringBuilder sql=new StringBuilder();
	 			sql.append(" update   member     ");
	 			sql.append(" set   			     ");
	 			sql.append("          pwd =?     ");
	 			sql.append("         ,email = ?  ");
	 			sql.append("   where                      ");
	 			sql.append("            id=?             ");
	  
	 			pstmt=conn.prepareStatement(sql.toString());
	 			pstmt.setString(1, pwd);
	 			pstmt.setString(2, email);
	 			pstmt.setString(3, id);
	 			
	 			result=pstmt.executeUpdate();
	 			
	 		}catch (SQLException e) {
	 				System.out.println(e);
	 		}finally {
	 				close(pstmt, conn);
	 		}
	 		return result;
	 	}
	    // delete ��� �޼���
	    // select ��� �޼��� 
	    
	     public ArrayList<MemberDTO> getAll()
	     {
	    	   //db�����ؼ� �ڷḦ �޾ƾ� �� 
	    	 //�ڷᱸ��   list map   set  =>   ArrayList  : db�����Ŀ� ArrayList�� ��Ƽ� ����
	     }
	     
	 // close ��� �޼��� 
	
	
	
	
	
}
