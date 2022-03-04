package dashboardapp;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author ESWAR
 */

public class Branch_Details_PieChart extends JFrame{
    
    public Branch_Details_PieChart(String ChartName){
        
        Toolkit t1 = getToolkit();
        Dimension size = t1.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
        
        
       PieDataset pieData = createDataset();
        JFreeChart chart = createChart(pieData,ChartName);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500,300));
        setContentPane(chartPanel);
       createDataset();
       }
    
    private PieDataset createDataset(){
        DefaultPieDataset result = new DefaultPieDataset();        
        try
        {
                        Conn con=new Conn();
			PreparedStatement ps=con.c.prepareStatement("select branch,count(*) from student group by branch");
			ResultSet rs=ps.executeQuery();
                        while(rs.next())
                        {
                            result.setValue(rs.getString(1),rs.getInt(2));
                            
                            
                        }
			
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        return result;
       // return result;
        
    }
    private JFreeChart createChart(PieDataset dataset,String title){
        JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true,true,false);
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(90);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        
        return chart;
    }
    
    /*public static void main(String args[]){
        Branch_Details_PieChart pc = new Branch_Details_PieChart("Department Data");
        pc.pack();
        pc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pc.setVisible(true);
        
      
    }*/
}
