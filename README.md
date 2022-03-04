# E-Library-Management-System

1. Required NetBeans 12.0 LTS to run the project. Install NetBeans 12.0 .

2. Install MYSQL installer .

3. Extract TEAM-01 zip file. It contains folders(csv, documentation, jars, LiMaSy_mini2.rar(project Zip file), presentation, documentation, readme.txt, Tables Detais.txt)

3. Extract LiMaSy_mini2.rar and open LiMaSy folder in NetBeans.

4. Open MYSQL command line and execute the queries from the Tables Details.txt (present inside Main.rar).

5. In Netbeans, open project LiMaSy_mini2 -> Source Packages - > Conn.Java

   There, you can find the below line.

   c =DriverManager.getConnection("jdbc:mysql://localhost:3306/limasy","root","mani9087");

   change "mani9087" to your MYSQL root account password   

6. Import csv files data into MYSQL tables as below.
   
   Import account.csv to account table.(This is not a command)
  
   Import book.csv to book table.
   
   Import student.csv to student table.

7. Now run the start_main.java from the LiMaSy_mini2 project.

8. The project will work perfectly.
