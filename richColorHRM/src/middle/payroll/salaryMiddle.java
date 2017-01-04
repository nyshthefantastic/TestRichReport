/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.payroll;
import common.dbconnct;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;
/**
 *
 * @author Achala Kavinda
 */
public class salaryMiddle {

    Connection conn=null;
    Statement st;
    ResultSet rs=null;
    int id=0;
    String [] dataS=new String [15];
    
    
    public salaryMiddle() {
        conn = dbconnct.connect();
    }
    
    
    
    
    public ResultSet getQuery(String sql){    
    try {
            Statement statement = conn.createStatement();
            return statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public String populateFields(String eid){
      dataS[0]=getBsalary(eid);
      dataS[1]=getBRA();
      
        
        
    }
     private String getEpf(String eid){
        rs=getQuery("SELECT epf FROM employee WHERE epfNo='" + eid + "'");
        try{
        if(rs.next()){
            String bsalary=rs.getString("bSalary");
            return bsalary;
        
        
        }
        }catch(Exception e){
        
        
        }
        return null;
    }
    private String getBRA(){
        rs=getQuery("SELECT amount FROM rates '");
        try{
        if(rs.next()){
            String id=rs.getString("id");
            if(id.equals("1")){
            String amount=rs.getString("amount");
            return amount;
            }
        
        }
        }catch(Exception e){
        
        
        }
        return null;
    }
    private String getBsalary(String eid){
        rs=getQuery("SELECT bSalary FROM employee WHERE epfNo='" + eid + "'");
        try{
        if(rs.next()){
            String bsalary=rs.getString("bSalary");
            return bsalary;
        
        
        }
        }catch(Exception e){
        
        
        }
        return null;
    }
}
   
