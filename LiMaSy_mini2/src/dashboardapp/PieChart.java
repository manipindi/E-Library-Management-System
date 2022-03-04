/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboardapp;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

/**
 *
 * @author ESWAR
 */
public class PieChart extends JFrame{
    
    public PieChart(String ChartName){
        
        Toolkit t1 = getToolkit();
        Dimension size = t1.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
        
        PieDataset pieData = createDataset();
        JFreeChart chart = createChart(pieData,ChartName);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500,300));
        setContentPane(chartPanel);
       }
    
    private PieDataset createDataset(){
        int[] topbcount = new int[5];
        String[] topbname = new String[5];
       try {
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/limasy","root","mani9087");  
//here sonoo is database name, root is username and password  
            Statement stmt=con.createStatement();
            String sql = "select book_id, count(book_id) from returnbook group by book_id order by count(book_id) desc limit 5";
            ResultSet rs=stmt.executeQuery(sql); 
            //int rows = 5;
            
            int i=0;
            while(rs.next()){
                topbname[i] = rs.getString(1);
                topbcount[i] = rs.getInt(2);
               // System.out.println(rs.getString(1)+"  "+rs.getInt(2));
               i+=1;
            }  
            con.close();
            
            
                  //  Conn con = new Conn();
                 /*   
                   PreparedStatement ps=con.c.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
			
			
                    
                    while(rs.next()){
                        for(int i=0;i<rows;i++){
                        topbname[i] = rs.getString("book_name");
                        topbcount[i] = rs.getInt("nooftimetaken");
                        
                    }
                    }
                    for(int i =0;i<rows;i++){
                        System.out.println(topbname[i]);
                  }
                    
                    
                       */
                 DefaultPieDataset pieDataSet = new DefaultPieDataset();
		
        } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, "Enter Correct Details");
		}
       String[] topbnames = new String[5];
       try{
           
           for(int j =0;j<5;j++)
           {
                     //  System.out.println(topbname[j]+" " +topbcount[j]);
                      Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/limasy","root","mani9087");  
//here sonoo is database name, root is username and password  
            Statement stmt=con.createStatement();
            String sql = "select bname from book where book_id="+topbname[j]+";";
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next()){
                topbnames[j] = rs.getString("bname");
              //  System.out.println(topbnames[j]);
                        }
            
             }
       }catch(Exception e3){
           JOptionPane.showMessageDialog(null, "Enter Correct Details");
    
       }
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue(topbnames[0],topbcount[0]);
        result.setValue(topbnames[1],topbcount[1]);
        result.setValue(topbnames[2],topbcount[2]);
        result.setValue(topbnames[3],topbcount[3]);
        result.setValue(topbnames[4],topbcount[4]);
        
        
        return result;
        
    }
    private JFreeChart createChart(PieDataset dataset,String title){
        JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true,true,false);
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(90);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        
        return chart;
    }
    
    
    
}
