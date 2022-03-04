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


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
	
	 Connection c;
	    Statement s;
	    public Conn(){  
	        try{  
	            Class.forName("com.mysql.cj.jdbc.Driver");  
	            c =DriverManager.getConnection("jdbc:mysql://localhost:3306/limasy","root","mani9087");    
	            s =c.createStatement();  
	            
	           
	        }catch(Exception e){ 
	            System.out.println(e);
	        }  
	    }  

}

