/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.reports;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.io.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.view.*;
public class MyReportViewer extends JFrame{
 /*
 * You can use this constructor when you generate reports without
 * passing parametes
 */

 /*
 * You can use this constructor when you call an iReport by
 * passing parameters
 */
 public MyReportViewer(String fileName,HashMap param){
 //this(fileName);
 //Map<String, Object> parameters = new HashMap<String, Object>();
//parameters=
 //try block
 try {
 // Instantiate MySQL Connector J driver
 Class.forName("com.mysql.jdbc.Driver").newInstance();
 // Open a connection to the database
 // use corect port, database instance, username and password
 Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/richcolour_payroll", "root", "abc@123");
 //Way 1 to call iReposrts
 //JasperDesign jasperDesign = JasperManager.loadXmlDesign(fileName);
 //JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
 //JasperPrint print = JasperFillManager.fillReport(jasperReport, parameter, con);
 //Way 2 to call iReposrts
 // Here the filename must be a " .jrxml " file
 //JasperReport jasperReport = JasperCompileManager.compileReport(fileName);
 //JasperPrint print = JasperFillManager.fillReport(fileName, parameter, con);
 //Way 3 (Here the filename should be in .jasper extension i.e., the compiled report)
 JasperPrint print = JasperFillManager.fillReport("C:\\Users\\U Computers\\Documents\\NetBeansProjects\\richcolor ra\\richcolor\\richColorHRM\\src\\client\\reports\\report1.jasper",param,con); 
JRViewer viewer=new JRViewer(print);
 // get contaent pane
 Container c=getContentPane();
 // add the report to the frame
 c.add(viewer);
 }
 catch(ClassNotFoundException cnfe) {
 cnfe.printStackTrace();
 }
 catch(SQLException sqle) {
 sqle.printStackTrace();
 }
 catch(JRException jre) {
 jre.printStackTrace();
 }
 catch(Exception e){
 e.printStackTrace();
 }
 setBounds(10,10,600,500);
 setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 }
 /**
 * @param args the command line arguments
 */
 public static void main(String[] args) {
 // way 1 - call a report without passing passing parameters
 // Here you must make sure that you provide the correct path to .jsper file
 //MyReportViewer viewer=new MyReportViewer("C:\\Users\\U Computers\\Documents\\NetBeansProjects\\richcolor ra\\richcolor\\richColorHRM\\src\\client\\reports\\garment.jasper");
 // way 2 - call a report by passing passing parameters
 // you may pass any number of parameters. All the parameters must added
 // to the hash map
 HashMap param=new HashMap();
 param.put("district","Ampara");
 MyReportViewer viewer=new MyReportViewer("C:\\Users\\U Computers\\Documents\\NetBeansProjects\\richcolor ra\\richcolor\\richColorHRM\\src\\client\\reports\\report1.jasper",param);
 viewer.setVisible(true);
 }
}