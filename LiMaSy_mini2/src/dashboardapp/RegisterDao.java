/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboardapp;

/**
 *
 * @author Manikanta
 */

import java.sql.*;
import javax.swing.JOptionPane;
public class RegisterDao {
	
public static boolean checkBook(int bookcallno){
	boolean status=false;
	try{
		Conn con=new Conn();
		String sql = "select * from book where book_id=?";
        PreparedStatement st = con.c.prepareStatement(sql);
		st.setInt(1,bookcallno);
	    ResultSet rs=st.executeQuery();
		status=rs.next();
		con.c.close();
	}catch(Exception e){JOptionPane.showMessageDialog(null,"2");}
	return status;
}

public static int save(int bookcallno,String bookname,String a,String p,String studentid,String studentname,String studentcontact,String r){
	int status=0;
	try{
		Conn con=new Conn();
		
		status=updatebook(bookcallno);//updating quantity and issue
		
		if(status>0){
			 String sql = "insert into cart values(?,?,?,?,?,?,?,?)";
             PreparedStatement ps = con.c.prepareStatement(sql);
             ps.setInt(1,bookcallno);
             ps.setString(2,bookname);
             ps.setString(3,a);
             ps.setString(4,p);
             ps.setString(5,studentid);
             ps.setString(6,studentname);
             ps.setString(7,studentcontact);
             ps.setString(8,r);
             status=ps.executeUpdate();
		}
		
		con.c.close();
	}catch(Exception e){JOptionPane.showMessageDialog(null,"3");}
	return status;
}
public static int updatebook(int bookcallno){
	int status=0;
	int quantity=0;
	try{
		Conn con=new Conn();
		
		PreparedStatement ps=con.c.prepareStatement("select quantity from book where book_id=?");
		ps.setInt(1,bookcallno);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			quantity=rs.getInt("quantity");
		}
		
		if(quantity>0){
		PreparedStatement ps2=con.c.prepareStatement("update book set quantity=? where book_id=?");
		ps2.setInt(1,quantity-1);
		ps2.setInt(2,bookcallno);
		
		status=ps2.executeUpdate();
		}
		con.c.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}
}

